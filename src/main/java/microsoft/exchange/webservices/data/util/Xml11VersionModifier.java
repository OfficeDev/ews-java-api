package microsoft.exchange.webservices.data.util;

import com.github.rwitzel.streamflyer.core.AfterModification;
import com.github.rwitzel.streamflyer.core.Modifier;
import com.github.rwitzel.streamflyer.util.ModificationFactory;
import com.github.rwitzel.streamflyer.xml.XmlPrologRidiculouslyLongException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Xml11VersionModifier implements Modifier {

  //
  // constants
  //

  public final int INITIAL_NUMBER_OF_CHARACTERS = 8192;


  private enum Xml11VersionModifierState {
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

  private Xml11VersionModifierState state = Xml11VersionModifierState.INITIAL;

  //
  // constructors
  //

  public Xml11VersionModifier() {

    this.factory = new ModificationFactory(0, INITIAL_NUMBER_OF_CHARACTERS);
    this.xmlVersion = "1.1";
  }

  //
  // Modifier.* methods
  //

  /**
   * @see com.github.rwitzel.streamflyer.core.Modifier#modify(java.lang.StringBuilder, int, boolean)
   */
  @Override public AfterModification modify(StringBuilder characterBuffer,
      int firstModifiableCharacterInBuffer, boolean endOfStreamHit) {

    switch (state) {

      case NO_LONGER_MODIFYING:

        return factory.skipEntireBuffer(characterBuffer, firstModifiableCharacterInBuffer, endOfStreamHit);

      case INITIAL:

        state = Xml11VersionModifierState.PROLOG_REQUEST;

        // you never know how many whitespace characters are in the prolog
        return factory.modifyAgainImmediately(INITIAL_NUMBER_OF_CHARACTERS, firstModifiableCharacterInBuffer);

      case PROLOG_REQUEST:

        // (Should we do aware of BOMs here? No. I consider it the
        // responsibility of the caller to provide characters without BOM.)

        Matcher
            matcher =
            Pattern.compile("<\\?xml[^>]*version\\s*=\\s*['\"]((1.0)|(1.1))['\"].*").matcher(characterBuffer);
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

        state = Xml11VersionModifierState.NO_LONGER_MODIFYING;

        return factory.skipEntireBuffer(characterBuffer, firstModifiableCharacterInBuffer, endOfStreamHit);

      default:
        throw new IllegalStateException("state " + state + " not supported");

    }

  }
}
