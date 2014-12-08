/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents the Id of a phone call.
 */
final class PhoneCallId extends ComplexProperty {

  /**
   * The id.
   */
  private String id;

  /**
   * Initializes a new instance of the PhoneCallId class.
   */
  protected PhoneCallId() {
  }

  /**
   * Initializes a new instance of the PhoneCallId class.
   *
   * @param id the id
   */
  protected PhoneCallId(String id) {
    this.id = id;
  }

  /**
   * Reads attributes from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  protected void readAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.id = reader.readAttributeValue(XmlAttributeNames.Id);
  }

  /**
   * Writes attributes to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.Id, this.id);
  }

  /**
   * Writes to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
    this.writeToXml(writer, XmlElementNames.PhoneCallId);
  }

  /**
   * Gets the Id of the phone call.
   *
   * @return the id
   */
  protected String getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  protected void setId(String id) {
    this.id = id;
  }

}
