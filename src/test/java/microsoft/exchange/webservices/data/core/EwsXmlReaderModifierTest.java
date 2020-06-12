package microsoft.exchange.webservices.data.core;

import microsoft.exchange.webservices.data.security.XmlNodeType;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class EwsXmlReaderModifierTest {

  @Test public void testReadValidDocumentXml10() throws Exception {
    final String validDocument = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><test>testContent</test>";
    byte[] bytes = validDocument.getBytes("UTF-8");
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes));
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    String content = impl.readValue();
    Assert.assertEquals(content, "testContent");
    impl.read(new XmlNodeType(XmlNodeType.END_DOCUMENT));
  }

  @Test public void testReadInvalidDocumentXml10With0xA1() throws Exception {
    final String invalidDocument = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><test>test\u001AContent</test>";
    byte[] bytes = invalidDocument.getBytes("UTF-8");
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes));
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    String content = impl.readValue();
    Assert.assertEquals(content, "testContent");
    impl.read(new XmlNodeType(XmlNodeType.END_DOCUMENT));
  }

  @Test public void testReadInvalidDocumentXml10WithNullCharacter() throws Exception {
    final String
        invalidDocument =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?><test>test\u0000Content\0</test>";
    byte[] bytes = invalidDocument.getBytes("UTF-8");
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes));
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    String content = impl.readValue();
    Assert.assertEquals(content, "testContent");
    impl.read(new XmlNodeType(XmlNodeType.END_DOCUMENT));
  }

  @Test public void testReadValidDocumentXml11() throws Exception {
    final String validDocument = "<?xml version=\"1.1\" encoding=\"UTF-8\"?><test>testContent</test>";
    byte[] bytes = validDocument.getBytes("UTF-8");
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes));
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    String content = impl.readValue();
    Assert.assertEquals(content, "testContent");
    impl.read(new XmlNodeType(XmlNodeType.END_DOCUMENT));
  }

  @Test public void testReadInvalidDocumentXml11With0xA1() throws Exception {
    final String invalidDocument = "<?xml version=\"1.1\" encoding=\"UTF-8\"?><test>test\u001AContent</test>";
    byte[] bytes = invalidDocument.getBytes("UTF-8");
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes));
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    String content = impl.readValue();
    Assert.assertEquals(content, "testContent");
    impl.read(new XmlNodeType(XmlNodeType.END_DOCUMENT));
  }

  @Test public void testReadInvalidDocumentXml11WithNullCharacter() throws Exception {
    final String invalidDoc = "<?xml version=\"1.1\" encoding=\"UTF-8\"?><test>test\u0000Content\0</test>";
    byte[] bytes = invalidDoc.getBytes("UTF-8");
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes));
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    String content = impl.readValue();
    Assert.assertEquals(content, "testContent");
    impl.read(new XmlNodeType(XmlNodeType.END_DOCUMENT));
  }

  @Test public void testReadInvalidDocumentWithNullCharacter() throws Exception {
    final String invalidDoc = "<test>test\u0000Content\0</test>";
    byte[] bytes = invalidDoc.getBytes();
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes));
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    String content = impl.readValue();
    Assert.assertEquals(content, "testContent");
    impl.read(new XmlNodeType(XmlNodeType.END_DOCUMENT));
  }
}
