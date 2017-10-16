package microsoft.exchange.webservices.data.core;

import microsoft.exchange.webservices.data.security.XmlNodeType;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.xml.stream.XMLStreamException;

import java.io.ByteArrayInputStream;

public class EwsXmlReaderIgnoreErrorsTest {

  /*
  From: https://github.com/OfficeDev/ews-java-api/pull/409/commits/e7b7505bc2d8e5432b69d412392387f71fe7bdb5
   */

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  final String validDocument =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                                "<test>testContent</test>";

  final String invalidDocument = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                                 "<test>test&#x5;Content</test>";

  @Test
  public void testReadValidDocument() throws Exception {
    byte[] bytes = validDocument.getBytes("UTF-8");
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes), false);
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    String content = impl.readValue();
    Assert.assertEquals(content, "testContent");
    impl.read(new XmlNodeType(XmlNodeType.END_DOCUMENT));
  }

  @Test
  public void testReadInvalidDocument() throws Exception {
    byte[] bytes = invalidDocument.getBytes("UTF-8");
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes), false);
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    exception.expect(XMLStreamException.class);
    String content = impl.readValue();
    Assert.assertEquals(content, "test\u0005Content");
  }

  @Test
  public void testReadInvalidDocumentWithIgnoreErrors() throws Exception {
    byte[] bytes = invalidDocument.getBytes("UTF-8");
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes), true);
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    String content = impl.readValue();
    Assert.assertEquals(content, "test\u0005Content");
    impl.read(new XmlNodeType(XmlNodeType.END_DOCUMENT));
  }
}
