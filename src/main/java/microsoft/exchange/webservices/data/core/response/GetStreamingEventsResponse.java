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
import microsoft.exchange.webservices.data.core.request.HangingServiceRequestBase;
import microsoft.exchange.webservices.data.core.enumeration.misc.HangingRequestDisconnectReason;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.notification.GetStreamingEventsResults;
import microsoft.exchange.webservices.data.security.XmlNodeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the response to a subscription event retrieval operation.
 */
public final class GetStreamingEventsResponse extends ServiceResponse {

  private GetStreamingEventsResults results = new GetStreamingEventsResults();
  private HangingServiceRequestBase request;


  /**
   * Enumeration of ConnectionStatus that can be returned by the server.
   */
  private enum ConnectionStatus {
    /**
     * Simple heartbeat
     */
    OK,

    /**
     * Server is closing the connection.
     */
    Closed
  }

  /**
   * Initializes a new instance of the GetStreamingEventsResponse class.
   *
   * @param request The request
   *                Request to disconnect when we get a close message.
   */
  public GetStreamingEventsResponse(HangingServiceRequestBase request) {
    super();
    List<String> string = new ArrayList<String>();
    this.setErrorSubscriptionIds(string);
    this.request = request;
  }

  /**
   * Reads response elements from XML.
   *
   * @throws Exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.readElementsFromXml(reader);

    reader.read();

    if (reader.getLocalName().equals(XmlElementNames.Notifications)) {
      this.results.loadFromXml(reader);
    } else if (reader.getLocalName().equals(XmlElementNames.ConnectionStatus)) {
      String connectionStatus = reader.readElementValue(XmlNamespace.
          Messages, XmlElementNames.ConnectionStatus);

      if (connectionStatus.equals(ConnectionStatus.Closed.toString())) {
        this.request.disconnect(
            HangingRequestDisconnectReason.Clean, null);
      }
    }
  }

  /**
   * Loads extra error details from XML
   *
   * @throws Exception
   */
  @Override
  protected boolean loadExtraErrorDetailsFromXml(EwsServiceXmlReader reader,
      String xmlElementName) throws Exception {
    boolean baseReturnVal = super.
        loadExtraErrorDetailsFromXml(reader, xmlElementName);

    if (reader.isStartElement(XmlNamespace.Messages, XmlElementNames.ErrorSubscriptionIds)) {
      do {
        reader.read();

        if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT &&
            reader.getLocalName().equals(XmlElementNames.SubscriptionId)) {
          this.getErrorSubscriptionIds().add(
              reader.readElementValue(XmlNamespace.Messages,
                  XmlElementNames.SubscriptionId));
        }
      }
      while (!reader.isEndElement(XmlNamespace.Messages,
          XmlElementNames.ErrorSubscriptionIds));

      return true;
    } else {
      return baseReturnVal;
    }
  }

  /**
   * Gets event results from subscription.
   */
  public GetStreamingEventsResults getResults() {
    return this.results;
  }

  private List<String> errorSubscriptionIds;

  /**
   * Gets the error subscription ids.
   */
  public List<String> getErrorSubscriptionIds() {
    return this.errorSubscriptionIds;
  }

  /**
   * Sets the error subscription ids.
   */
  private void setErrorSubscriptionIds(List<String> value) {
    this.errorSubscriptionIds = value;
  }


}
