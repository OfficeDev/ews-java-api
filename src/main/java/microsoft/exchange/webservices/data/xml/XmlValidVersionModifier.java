/**
 * Copyright (C) 2011 rwoo@gmx.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package microsoft.exchange.webservices.data.xml;

import com.github.rwitzel.streamflyer.core.AfterModification;
import com.github.rwitzel.streamflyer.core.Modifier;
import com.github.rwitzel.streamflyer.internal.thirdparty.ZzzValidate;
import com.github.rwitzel.streamflyer.util.ModificationFactory;
import com.github.rwitzel.streamflyer.xml.InvalidXmlCharacterModifier;
import com.github.rwitzel.streamflyer.xml.XmlPrologRidiculouslyLongException;

import org.apache.commons.io.input.XmlStreamReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a copy of {@link com.github.rwitzel.streamflyer.xml.XmlVersionModifier}, that generates valid prolog.
 * Details <a href="https://github.com/rwitzel/streamflyer/issues/9">here</a>.
 * <p>
 *
 * Replaces the XML version in the XML prolog with the given XML version. Adds an XML prolog if the stream does not
 * contain an XML prolog.
 * <p>
 * <h1>Contents</h1>
 * <p>
 * <b> <a href="#g1">1. How and when do I use this modifier?</a><br/>
 * <a href="#g2">2. Do I have to care about BOMs at the beginning of the stream?</a> <br/>
 * <a href="g3">3. Is there any known limitation?</a> <br/>
 * <a href="#g4">4. How much memory does the modifier consume?</a><br/>
 * </b> <!-- ++++++++++++++++++++++++++++++ -->
 * <p>
 * <h3 id="g1">1. How and when do I use this modifier?</h3>
 * <p>
 * This modifier is an alternative to {@link InvalidXmlCharacterModifier} if you a have characters in an XML stream that
 * are valid for XML 1.1 documents but invalid for XML 1.1 documents. In this case you use this modifier to change the
 * XML version in the prolog of the document.
 * <p>
 * EXAMPLE:
 * <code><pre class="prettyprint lang-java">// choose the input stream to modify
ByteArrayInputStream inputStream = new ByteArrayInputStream(
        bytesWithBom);

// wrap the input stream by BOM skipping reader
Reader reader = new XmlStreamReader(inputStream);

// create the reader that changes the XML version to 1.1
ModifyingReader modifyingReader = new ModifyingReader(reader,
        new XmlVersionModifier("1.1", 8192));

// use the modifying reader instead of the original reader
String xml = IOUtils.toString(modifyingReader);

assertTrue(xml.startsWith("&lt;?xml version='1.1'"));
</pre></code>
 * <h3 id="g2">2. Do I have to care about BOMs at the beginning of the stream?</h3>
 * <p>
 * Yes, you must use a BOM skipping reader that wraps the input stream. Apache's Commons IO {@link XmlStreamReader} does
 * this for you.
 * <h3 id="g3">3. Is there any known limitation?</h3>
 * <p>
 * Yes, this modifier throws a {@link XmlPrologRidiculouslyLongException} if the prolog of the XML document contains
 * more than {@link #INITIAL_NUMBER_OF_CHARACTERS} characters. This can only happen if there is a lot of whitespace
 * within the prolog, which is highly unlikely but not forbidden by the XML specification. You should know that even the
 * <code>XmlReader</code> of Apache Commons which you probably use to detect the encoding cannot deal with such a kind
 * of prolog.
 * <h3 id="#g4">4. How much memory does the modifier consume?</h3>
 * <p>
 * The memory consumption of this modifier during the stream processing is roughly given by the second argument of
 * {@link #XmlValidVersionModifier(String, int)} but the initial memory consumption is given by
 * {@link #INITIAL_NUMBER_OF_CHARACTERS}.
 *
 * @author rwoo
 * @since 27.06.2011
 */
public class XmlValidVersionModifier implements Modifier {

    //
    // constants
    //

    public final int INITIAL_NUMBER_OF_CHARACTERS = 4096;

    /**
     * The internal state of {@link XmlValidVersionModifier}.
     * <p>
     * The state transitions are: from <code>INITIAL</code> to <code>PROLOG_REQUEST</code> to
     * <code>NO_LONGER_MODIFYING</code>.
     */
    private enum XmlVersionModifierState {
        /**
         * The initial state. No input read yet.
         */
        INITIAL,

        /**
         * The modifier has requested to read the XML prolog.
         */
        PROLOG_REQUEST,

        /**
         * The modifier has read the XML prolog, modified it if necessary. Nothing more to do for the modifier.
         */
        NO_LONGER_MODIFYING
    }

    //
    // injected properties
    //

    protected ModificationFactory factory;

    protected String xmlVersion;

    //
    // properties that represent the mutable state
    //

    private XmlVersionModifierState state = XmlVersionModifierState.INITIAL;

    //
    // constructors
    //

    public XmlValidVersionModifier(String xmlVersion, int newNumberOfChars) {

        ZzzValidate.notNull(xmlVersion, "xmlVersion must not be null");

        this.factory = new ModificationFactory(0, newNumberOfChars);
        this.xmlVersion = xmlVersion;
    }

    //
    // Modifier.* methods
    //

    /**
     * @see Modifier#modify(StringBuilder, int, boolean)
     */
    @Override
    public AfterModification modify(StringBuilder characterBuffer, int firstModifiableCharacterInBuffer,
            boolean endOfStreamHit) {

        switch (state) {

        case NO_LONGER_MODIFYING:

            return factory.skipEntireBuffer(characterBuffer, firstModifiableCharacterInBuffer, endOfStreamHit);

        case INITIAL:

            state = XmlVersionModifierState.PROLOG_REQUEST;

            // you never know how many whitespace characters are in the prolog
            return factory.modifyAgainImmediately(INITIAL_NUMBER_OF_CHARACTERS, firstModifiableCharacterInBuffer);

        case PROLOG_REQUEST:

            // (Should we do aware of BOMs here? No. I consider it the
            // responsibility of the caller to provide characters without BOM.)

            Matcher matcher = Pattern.compile("<\\?xml[^>]*version\\s*=\\s*['\"]((1.0)|(1.1))['\"].*").matcher(
                    characterBuffer);
            if (matcher.matches()) {

                // replace version in prolog
                characterBuffer.replace(matcher.start(1), matcher.end(1), xmlVersion);
            } else {
                // is there a prolog that is too long?
                Matcher matcher2 = Pattern.compile("<\\?xml.*").matcher(characterBuffer);
                if (matcher2.matches()) {
                    // this is not normal at all -> throw exception
                    throw new XmlPrologRidiculouslyLongException(characterBuffer.toString());
                }

                // insert prolog
                characterBuffer.insert(0, "<?xml version='" + xmlVersion + "'?>");
            }

            state = XmlVersionModifierState.NO_LONGER_MODIFYING;

            return factory.skipEntireBuffer(characterBuffer, firstModifiableCharacterInBuffer, endOfStreamHit);

        default:
            throw new IllegalStateException("state " + state + " not supported");

        }

    }
}
