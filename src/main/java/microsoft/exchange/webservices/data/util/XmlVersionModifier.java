package microsoft.exchange.webservices.data.util;

import com.github.rwitzel.streamflyer.core.AfterModification;
import com.github.rwitzel.streamflyer.internal.thirdparty.ZzzValidate;
import com.github.rwitzel.streamflyer.util.ModificationFactory;
import com.github.rwitzel.streamflyer.xml.XmlPrologRidiculouslyLongException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlVersionModifier extends com.github.rwitzel.streamflyer.xml.XmlVersionModifier {
    public XmlVersionModifier(String xmlVersion, int newNumberOfChars) {
        super(xmlVersion, newNumberOfChars);
    }

    /**
     * The internal state of {@link XmlVersionModifier}.
     * <p>
     * The state transitions are: from {@value #INITIAL} to
     * {@value #PROLOG_REQUEST} to {@value #NO_LONGER_MODIFYING}.
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
         * The modifier has read the XML prolog, modified it if necessary.
         * Nothing more to do for the modifier.
         */
        NO_LONGER_MODIFYING
    }

    //
    // properties that represent the mutable state
    //

    private XmlVersionModifierState state = XmlVersionModifierState.INITIAL;

    /**
     * @see com.github.rwitzel.streamflyer.core.Modifier#modify(java.lang.StringBuilder,
     *      int, boolean)
     */
    @Override
    public AfterModification modify(StringBuilder characterBuffer,
                                    int firstModifiableCharacterInBuffer, boolean endOfStreamHit) {

        switch (state) {

            case NO_LONGER_MODIFYING:

                return factory.skipEntireBuffer(characterBuffer,
                        firstModifiableCharacterInBuffer, endOfStreamHit);

            case INITIAL:

                state = XmlVersionModifierState.PROLOG_REQUEST;

                // you never know how many whitespace characters are in the prolog
                return factory.modifyAgainImmediately(INITIAL_NUMBER_OF_CHARACTERS,
                        firstModifiableCharacterInBuffer);

            case PROLOG_REQUEST:
                Matcher matcher = Pattern.compile(
                        "<\\?xml\\s+version\\s*=\\s*['\"](1.0|1.1)['\"].*")
                        .matcher(characterBuffer);
                if (matcher.matches()) {

                    // replace version in prolog
                    characterBuffer.replace(matcher.start(1), matcher.end(1),
                            xmlVersion);
                }
                else {
                    // is there a prolog that is too long?
                    Matcher matcher2 = Pattern.compile("<\\?xml.*").matcher(
                            characterBuffer);
                    if (matcher2.matches()) {
                        // this is not normal at all -> throw exception
                        throw new XmlPrologRidiculouslyLongException(
                                characterBuffer.toString());
                    }
                }

                state = XmlVersionModifierState.NO_LONGER_MODIFYING;

                return factory.skipEntireBuffer(characterBuffer,
                        firstModifiableCharacterInBuffer, endOfStreamHit);

            default:
                throw new IllegalStateException("state " + state + " not supported");

        }

    }
}
