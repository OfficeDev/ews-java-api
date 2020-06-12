package microsoft.exchange.webservices.data.core.modifier;

import com.github.rwitzel.streamflyer.xml.InvalidXmlCharacterModifier;

public class InvalidBothSchemaXmlCharacterModifier extends InvalidXmlCharacterModifier {

  public InvalidBothSchemaXmlCharacterModifier(String replacement, String xmlVersion) {
    super(replacement, xmlVersion);
  }

  protected String getInvalidXmlCharacterRegex_Xml10() {
    return "[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\x{10000}-\\x{10FFFF}]";
  }
}
