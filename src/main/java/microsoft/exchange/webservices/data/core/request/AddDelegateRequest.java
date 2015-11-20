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

package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.response.DelegateManagementResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.MeetingRequestsDeliveryScope;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.DelegateUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an AddDelegate request.
 */
public class AddDelegateRequest extends
    DelegateManagementRequestBase<DelegateManagementResponse> {

  /**
   * The delegate users.
   */
  private List<DelegateUser> delegateUsers = new ArrayList<DelegateUser>();

  /**
   * The meeting request delivery scope.
   */
  private MeetingRequestsDeliveryScope meetingRequestsDeliveryScope;

  /**
   * Initializes a new instance of the class.
   *
   * @param service the service
   * @throws Exception
   */
  public AddDelegateRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Initializes a new instance of the class.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParamCollection(this.getDelegateUsers().iterator(), "DelegateUsers");
    for (DelegateUser delegateUser : this.getDelegateUsers()) {
      delegateUser.validateUpdateDelegate();
    }

    if (this.meetingRequestsDeliveryScope != null) {
      EwsUtilities.validateEnumVersionValue(this.
              getMeetingRequestsDeliveryScope(),
          this.getService().getRequestedServerVersion());
    }
  }

  /**
   * Writes the elements to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    super.writeElementsToXml(writer);

    writer.writeStartElement(XmlNamespace.Messages,
        XmlElementNames.DelegateUsers);

    for (DelegateUser delegateUser : this.getDelegateUsers()) {
      delegateUser.writeToXml(writer, XmlElementNames.DelegateUser);
    }

    writer.writeEndElement(); // DelegateUsers

    if (this.getMeetingRequestsDeliveryScope() != null) {
      writer.writeElementValue(XmlNamespace.Messages,
          XmlElementNames.DeliverMeetingRequests, this
              .getMeetingRequestsDeliveryScope());
    }
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.AddDelegate;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.AddDelegateResponse;
  }

  /**
   * Creates the response.
   *
   * @return Service response.
   */
  @Override
  protected DelegateManagementResponse createResponse() {
    return new DelegateManagementResponse(true, this.delegateUsers);
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the meeting request delivery scope. <value>The meeting
   * request delivery scope.</value>
   *
   * @return the meeting request delivery scope
   */
  public MeetingRequestsDeliveryScope getMeetingRequestsDeliveryScope() {
    return this.meetingRequestsDeliveryScope;
  }

  /**
   * Sets the meeting request delivery scope.
   *
   * @param meetingRequestsDeliveryScope the new meeting request delivery scope
   */
  public void setMeetingRequestsDeliveryScope(
      MeetingRequestsDeliveryScope meetingRequestsDeliveryScope) {
    this.meetingRequestsDeliveryScope = meetingRequestsDeliveryScope;
  }

  /**
   * Gets the delegate users. <value>The delegate users.</value>
   *
   * @return the delegate users
   */
  public List<DelegateUser> getDelegateUsers() {
    return this.delegateUsers;
  }

}
