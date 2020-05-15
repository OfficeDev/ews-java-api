package microsoft.exchange.webservices.data.core.modifier;

import com.github.rwitzel.streamflyer.xml.InvalidXmlCharacterModifier;

public class InvalidBothSchemaXmlCharacterModifier extends InvalidXmlCharacterModifier {

  public InvalidBothSchemaXmlCharacterModifier(String replacement, String xmlVersion) {
    super(replacement, xmlVersion);
  }

  protected String getInvalidXmlCharacterRegex_Xml10() {
    // Combined InvalidXmlCharacterRegex_Xml10 and InvalidXmlCharacterRegex_Xml11
    return "[^\\u0001-\\uD7FF\\u0009\\u000A\\u000D\\uE000-\\uFFFD\\u10000-\\u10FFFF]";
  }

}
