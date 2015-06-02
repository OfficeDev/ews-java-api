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

package microsoft.exchange.webservices.data.core.service.response;

import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.item.MeetingResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;

/**
 * Represents a meeting acceptance message.
 */
public final class AcceptMeetingInvitationMessage extends
    CalendarResponseMessage<MeetingResponse> {

  /**
   * The tentative.
   */
  private boolean tentative;

  /**
   * Initializes a new instance of the AcceptMeetingInvitationMessage class.
   *
   * @param referenceItem the reference item
   * @param tentative     the tentative
   * @throws Exception the exception
   */
  public AcceptMeetingInvitationMessage(Item referenceItem, boolean tentative) throws Exception {
    super(referenceItem);
    this.tentative = tentative;
  }

  /**
   * This methods lets subclasses of ServiceObject override the default
   * mechanism by which the XML element name associated with their type is
   * retrieved.
   *
   * @return The XML element name associated with this type. If this method
   * returns null or empty, the XML element name associated with this
   * type is determined by the EwsObjectDefinition attribute that
   * decorates the type, if present.
   * <p>
   * Item and folder classes that can be returned by EWS MUST rely on
   * the EwsObjectDefinition attribute for XML element name determination.
   * </p>
   */
  @Override public String getXmlElementName() {
    // getXmlElementOverride is pvt and getXmlElementName returns
    // getXmlElementOverride
    if (this.tentative) {
      return XmlElementNames.TentativelyAcceptItem;
    } else {
      return XmlElementNames.AcceptItem;
    }
  }

  /**
   * Gets the minimum required server version.
   *
   * @return Earliest Exchange version in which this service object type is
   * supported.
   */
  @Override public ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the tentative.
   *
   * @return Gets a value indicating whether the associated meeting is
   * tentatively accepted.
   */
  public boolean getTentative() {
    return this.tentative;
  }

}
