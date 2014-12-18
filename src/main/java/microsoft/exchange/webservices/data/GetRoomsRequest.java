/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents a GetRooms request.
 */
final class GetRoomsRequest extends SimpleServiceRequestBase {

  /**
   * Represents a GetRooms request.
   *
   * @param service the service
   * @throws Exception
   */
  protected GetRoomsRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.GetRoomsRequest;
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    this.getRoomList().writeToXml(writer, XmlNamespace.Messages,
        XmlElementNames.RoomList);
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetRoomsResponse;
  }

  /**
   * Parses the response.
   *
   * @param reader the reader
   * @return Response object.
   * @throws Exception the exception
   */
  @Override
  protected Object parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    GetRoomsResponse response = new GetRoomsResponse();
    response.loadFromXml(reader, XmlElementNames.GetRoomsResponse);
    return response;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2010;
  }

  /**
   * Executes this request.
   *
   * @return Service response.
   * @throws Exception the exception
   */
  protected GetRoomsResponse execute() throws Exception {
    GetRoomsResponse serviceResponse = (GetRoomsResponse) this
        .internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }

  /**
   * Gets  the room list to retrieve rooms from.
   *
   * @return the room list
   */
  protected EmailAddress getRoomList() {
    return this.roomList;
  }

  /**
   * Sets the room list.
   *
   * @param value the new room list
   */
  protected void setRoomList(EmailAddress value) {
    this.roomList = value;
  }

  /**
   * The room list.
   */
  private EmailAddress roomList;

}
