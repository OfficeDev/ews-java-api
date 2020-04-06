/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.core;

import microsoft.exchange.webservices.data.security.XmlNodeType;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.xml.stream.XMLStreamException;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class EwsXmlWithInvalidCharactersReaderTest {

  @Rule public final ExpectedException exception = ExpectedException.none();

  private final String
      validDocument =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<test>testContent</test>";

  private final String
      invalidDocument =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<test>test&#x5;Content</test>";

  @Test public void testReadValidDocumentXml10() throws Exception {
    byte[] bytes = validDocument.getBytes(StandardCharsets.UTF_8);
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes), false);
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    String content = impl.readValue();
    Assert.assertEquals(content, "testContent");
    impl.read(new XmlNodeType(XmlNodeType.END_DOCUMENT));
  }

  @Test public void testReadInValidDocumentAsXml10() throws Exception {
    byte[] bytes = invalidDocument.getBytes(StandardCharsets.UTF_8);
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes), false);
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    exception.expect(XMLStreamException.class);

    impl.readValue();
  }

  @Test public void testReadInvalidDocumentAsXml11() throws Exception {
    byte[] bytes = invalidDocument.getBytes("UTF-8");
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes), true);
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    String content = impl.readValue();
    Assert.assertEquals(content, "test\u0005Content");
    impl.read(new XmlNodeType(XmlNodeType.END_DOCUMENT));
  }

  @Test public void testReadValidDocumentXml11() throws Exception {
    byte[] bytes = validDocument.getBytes(StandardCharsets.UTF_8);
    EwsXmlReader impl = new EwsXmlReader(new ByteArrayInputStream(bytes), true);
    impl.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
    impl.read(new XmlNodeType(XmlNodeType.START_ELEMENT));
    String content = impl.readValue();
    Assert.assertEquals(content, "testContent");
    impl.read(new XmlNodeType(XmlNodeType.END_DOCUMENT));
  }
}