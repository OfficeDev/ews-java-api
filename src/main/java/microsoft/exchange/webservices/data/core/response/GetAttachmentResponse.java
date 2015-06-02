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

package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.Attachment;
import microsoft.exchange.webservices.data.security.XmlNodeType;

/**
 * Represents the response to an individual attachment retrieval request.
 */
public final class GetAttachmentResponse extends ServiceResponse {

  /**
   * The attachment.
   */
  private Attachment attachment;

  /**
   * Initializes a new instance of the GetAttachmentResponse class.
   *
   * @param attachment the attachment
   */
  public GetAttachmentResponse(Attachment attachment) {
    super();
    EwsUtilities.ewsAssert(attachment != null, "GetAttachmentResponse.ctor", "attachment is null");

    this.attachment = attachment;
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.readElementsFromXml(reader);

    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.Attachments);
    if (!reader.isEmptyElement()) {
      XmlNodeType x = new XmlNodeType(XmlNodeType.START_ELEMENT);
      reader.read(x);

      this.attachment.loadFromXml(reader, reader.getLocalName());

      reader.readEndElement(XmlNamespace.Messages,
          XmlElementNames.Attachments);
    } else {
      reader.read();
    }
  }

  /**
   * Gets the attachment that was retrieved.
   *
   * @return the attachment
   */
  protected Attachment getAttachment() {
    return this.attachment;
  }

}
