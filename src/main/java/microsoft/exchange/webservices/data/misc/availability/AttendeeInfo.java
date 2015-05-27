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

import microsoft.exchange.webservices.data.ISelfValidate;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.availability.MeetingAttendeeType;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

/**
 * Represents information about an attendee for which to request availability
 * information.
 */
public final class AttendeeInfo implements ISelfValidate {

  /**
   * The smtp address.
   */
  private String smtpAddress;

  /**
   * The attendee type.
   */
  private MeetingAttendeeType attendeeType = MeetingAttendeeType.Required;

  /**
   * The exclude conflicts.
   */
  private boolean excludeConflicts;

  /**
   * Initializes a new instance of the AttendeeInfo class.
   */
  public AttendeeInfo() {
  }

  /**
   * Initializes a new instance of the AttendeeInfo class.
   *
   * @param smtpAddress      the smtp address
   * @param attendeeType     the attendee type
   * @param excludeConflicts the exclude conflicts
   */
  public AttendeeInfo(String smtpAddress, MeetingAttendeeType attendeeType,
      boolean excludeConflicts) {
    this();
    this.smtpAddress = smtpAddress;
    this.attendeeType = attendeeType;
    this.excludeConflicts = excludeConflicts;
  }

  /**
   * Initializes a new instance of the AttendeeInfo class.
   *
   * @param smtpAddress the smtp address
   */
  public AttendeeInfo(String smtpAddress) {
    this(smtpAddress, MeetingAttendeeType.Required, false);
    this.smtpAddress = smtpAddress;
  }

  /**
   * Defines an implicit conversion between a string representing an SMTP
   * address and AttendeeInfo.
   *
   * @param smtpAddress the smtp address
   * @return An AttendeeInfo initialized with the specified SMTP address.
   */
  public static AttendeeInfo getAttendeeInfoFromString(String smtpAddress) {
    return new AttendeeInfo(smtpAddress);
  }

  /**
   * Writes to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  public void writeToXml(EwsServiceXmlWriter writer) throws Exception {
    writer.writeStartElement(XmlNamespace.Types,
        XmlElementNames.MailboxData);

    writer.writeStartElement(XmlNamespace.Types, XmlElementNames.Email);
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Address,
        this.smtpAddress);
    writer.writeEndElement(); // Email

    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.AttendeeType, this.attendeeType);

    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.ExcludeConflicts, this.excludeConflicts);

    writer.writeEndElement(); // MailboxData
  }

  /**
   * Gets the SMTP address of this attendee.
   *
   * @return the smtp address
   */
  public String getSmtpAddress() {
    return smtpAddress;
  }

  /**
   * Sets the smtp address.
   *
   * @param smtpAddress the new smtp address
   */
  public void setSmtpAddress(String smtpAddress) {
    this.smtpAddress = smtpAddress;
  }

  /**
   * Gets the type of this attendee.
   *
   * @return the attendee type
   */
  public MeetingAttendeeType getAttendeeType() {
    return attendeeType;
  }

  /**
   * Sets the attendee type.
   *
   * @param attendeeType the new attendee type
   */
  public void setAttendeeType(MeetingAttendeeType attendeeType) {
    this.attendeeType = attendeeType;
  }

  /**
   * Gets a value indicating whether times when this attendee is not
   * available should be returned.
   *
   * @return true, if is exclude conflicts
   */
  public boolean isExcludeConflicts() {
    return excludeConflicts;
  }

  /**
   * Sets the exclude conflicts.
   *
   * @param excludeConflicts the new exclude conflicts
   */
  public void setExcludeConflicts(boolean excludeConflicts) {
    this.excludeConflicts = excludeConflicts;
  }

  /**
   * Validates this instance.
   *
   * @throws Exception the exception
   */
  public void validate() throws Exception {
    EwsUtilities.validateParam(this.smtpAddress, "SmtpAddress");
  }
}
