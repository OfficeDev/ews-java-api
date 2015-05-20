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
import microsoft.exchange.webservices.data.core.enumeration.service.MeetingRequestsDeliveryScope;
import microsoft.exchange.webservices.data.core.enumeration.misc.error.ServiceError;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

/**
 * The Class GetDelegateResponse.
 */
public final class GetDelegateResponse extends DelegateManagementResponse {

  /**
   * Represents the response to a delegate user retrieval operation.
   */
  private MeetingRequestsDeliveryScope meetingRequestsDeliveryScope =
      MeetingRequestsDeliveryScope.NoForward;

  /**
   * Initializes a new instance of the class.
   *
   * @param readDelegateUsers the read delegate users
   */
  public GetDelegateResponse(boolean readDelegateUsers) {
    super(readDelegateUsers, null);
  }

  /**
   * Reads response elements from XML
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.readElementsFromXml(reader);

    if (this.getErrorCode() == ServiceError.NoError) {
      // This is a hack. If there were no response messages, the reader
      // will already be on the
      // DeliverMeetingRequests start element, so we don't need to read
      // it.
      if (this.getDelegateUserResponses().size() > 0) {
        reader.read();
      }
      if (reader.isStartElement(XmlNamespace.Messages, XmlElementNames.DeliverMeetingRequests)) {
        this.meetingRequestsDeliveryScope = reader
            .readElementValue(MeetingRequestsDeliveryScope.class);
      }
    }
  }

  /**
   * Gets a value indicating if and how meeting request are delivered to
   * delegates.
   *
   * @return the meeting request delivery scope
   */
  public MeetingRequestsDeliveryScope getMeetingRequestsDeliveryScope() {
    return this.meetingRequestsDeliveryScope;
  }
}
