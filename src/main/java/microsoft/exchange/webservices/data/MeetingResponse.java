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
 * Represents a response to a meeting request. Properties available on meeting
 * messages are defined in the MeetingMessageSchema class.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.MeetingResponse)
public class MeetingResponse extends MeetingMessage {

  /**
   * Initializes a new instance of the class.
   *
   * @param parentAttachment The parentAttachment
   * @throws Exception the exception
   */
  protected MeetingResponse(ItemAttachment parentAttachment)
      throws Exception {
    super(parentAttachment);
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param service EWS service to which this object belongs.
   * @throws Exception the exception
   */
  protected MeetingResponse(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Binds to an existing meeting response and loads the specified set of
   * properties. Calling this method results in a call to EWS.
   *
   * @param service     The service to use to bind to the meeting response.
   * @param id          The Id of the meeting response to bind to.
   * @param propertySet The set of properties to load.
   * @return A MeetingResponse instance representing the meeting response
   * corresponding to the specified Id.
   */
  public static MeetingResponse bind(ExchangeService service, ItemId id,
      PropertySet propertySet) {
    try {
      return service.bindToItem(MeetingResponse.class, id, propertySet);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Binds to an existing meeting response and loads the specified set of
   * properties. Calling this method results in a call to EWS.
   *
   * @param service The service to use to bind to the meeting response.
   * @param id      The Id of the meeting response to bind to.
   * @return A MeetingResponse instance representing the meeting response
   * corresponding to the specified Id.
   */
  public static MeetingResponse bind(ExchangeService service, ItemId id) {
    return MeetingResponse.bind(service, id, PropertySet
        .getFirstClassProperties());
  }

  /**
   * Gets the minimum required server version.
   *
   * @return Earliest Exchange version in which this service object type is
   * supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }
}
