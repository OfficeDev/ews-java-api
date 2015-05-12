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

package microsoft.exchange.webservices.data.property.complex;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.enumeration.BodyType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UniqueBodyTest {

  UniqueBody impl;

  final String text = "test";

  final BodyType bodyType = BodyType.HTML;

  @Mock EwsServiceXmlReader reader;
  @Mock EwsServiceXmlWriter writer;


  @Before public void setUp() throws Exception {
    impl = new UniqueBody();
    MockitoAnnotations.initMocks(this);
  }

  @Test public void testReadAttributesFromXml() throws Exception {
    doReturn(BodyType.Text).when(reader).readAttributeValue(BodyType.class, XmlAttributeNames.BodyType);
    impl.readAttributesFromXml(reader);
    assertEquals(BodyType.Text, impl.getBodyType());
  }

  @Test public void testReadTextValueFromXml() throws Exception {
    setTextToImpl(text);
    assertEquals(text, impl.getText());
    assertEquals(text, UniqueBody.getStringFromUniqueBody(impl));
  }

  @Test public void testWriteAttributesToXml() throws Exception {
    impl.writeAttributesToXml(writer);
    verify(writer).writeAttributeValue(XmlAttributeNames.BodyType, impl.getBodyType());
  }

  @Test public void testWriteElementsToXml() throws Exception {
    impl.writeElementsToXml(writer);
    verify(writer, never()).writeValue(this.text, XmlElementNames.UniqueBody);
    setTextToImpl(text);
    impl.writeElementsToXml(writer);
    verify(writer).writeValue(text, XmlElementNames.UniqueBody);
  }

  @Test public void testToString() throws Exception {
    assertEquals("", impl.toString());
    setTextToImpl(text);
    assertEquals(text, impl.toString());
  }

  private void setTextToImpl(String myText) throws Exception {
    doReturn(myText).when(reader).readValue();
    impl.readTextValueFromXml(reader);
  }
}