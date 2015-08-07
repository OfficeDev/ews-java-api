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

package microsoft.exchange.webservices.data.misc.availability;

import microsoft.exchange.webservices.base.BaseTest;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.security.XmlNodeType;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;

public class TimeWindowTest extends BaseTest {

  @Test
  public void testWriteToXmlUnscopedDatesOnlyUsesUTC() {
    // Thu, 01 Jan 2015 0:0:00 UTC
    final Date midnight = new Date(1420070400000l);
    // Thu, 01 Jan 2015 23:59:59 GMT
    final Date just_before_midnight = new Date(1420156799000l);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    EwsServiceXmlWriter writer;

    try {
      // build the test xml markup
      writer = new EwsServiceXmlWriter(exchangeServiceMock, outputStream);
      writer.writeStartDocument();
      writer.writeStartElement(XmlNamespace.NotSpecified, "test");
      writer.writeAttributeValue("xmlns:" + XmlNamespace.Types.getNameSpacePrefix(), XmlNamespace.Types.getNameSpaceUri());
      TimeWindow tw = new TimeWindow();
      tw.setStartTime(midnight);
      tw.setEndTime(just_before_midnight);
      tw.writeToXmlUnscopedDatesOnly(writer, XmlElementNames.Duration);
      writer.writeEndElement();

      // read the test markup
      InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
      EwsServiceXmlReader reader = new EwsServiceXmlReader(inputStream, exchangeServiceMock);
      reader.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
      reader.readStartElement(XmlNamespace.NotSpecified, "test");
      reader.readStartElement(XmlNamespace.Types, XmlElementNames.Duration);
      TimeWindow checkTw = new TimeWindow();

      checkTw.loadFromXml(reader);

      // Test that the dates have not shifted.
      Assert.assertEquals(midnight, checkTw.getStartTime());
      Assert.assertEquals(midnight, checkTw.getEndTime());
    } catch (Exception e) {
      Assert.fail(e.getMessage());
    }
  }
}
