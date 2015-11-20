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
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.EmailAddressCollection;

/**
 * Represents the response to a GetRoomLists operation.
 */
public final class GetRoomListsResponse extends ServiceResponse {

  /**
   * The room lists.
   */
  private EmailAddressCollection roomLists = new EmailAddressCollection();

  /**
   * Represents the response to a GetRoomLists operation.
   */
  public GetRoomListsResponse() {
    super();
  }

  /**
   * Gets all room list returned.
   *
   * @return the room lists
   */
  public EmailAddressCollection getRoomLists() {
    return this.roomLists;
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.roomLists.clear();
    super.readElementsFromXml(reader);

    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.RoomLists);

    if (!reader.isEmptyElement()) {
      // Because we don't have an element for count of returned object,
      // we have to test the element to determine if it is return object
      // or EndElement
      reader.read();
      while (reader.isStartElement(XmlNamespace.Types,
          XmlElementNames.Address)) {
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.loadFromXml(reader, XmlElementNames.Address);
        this.roomLists.add(emailAddress);
        reader.read();
      }
      reader.ensureCurrentNodeIsEndElement(XmlNamespace.Messages,
          XmlElementNames.RoomLists);
    } else {
      reader.read();
    }
    return;
  }

}
