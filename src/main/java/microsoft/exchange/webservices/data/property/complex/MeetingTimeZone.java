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

package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.TimeSpan;
import microsoft.exchange.webservices.data.property.complex.time.TimeZoneDefinition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents a time zone in which a meeting is defined.
 */
public final class MeetingTimeZone extends ComplexProperty {

  private static final Log LOG = LogFactory.getLog(MeetingTimeZone.class);

  /**
   * The name.
   */
  private String name;

  /**
   * The base offset.
   */
  private TimeSpan baseOffset;

  /**
   * The standard.
   */
  private TimeChange standard;

  /**
   * The daylight.
   */
  private TimeChange daylight;

  /**
   * Initializes a new instance of the MeetingTimeZone class.
   *
   * @param timeZone The time zone used to initialize this instance.
   */
  public MeetingTimeZone(TimeZoneDefinition timeZone) {
    // Unfortunately, MeetingTimeZone does not support all the time
    // transition types
    // supported by TimeZoneInfo. That leaves us unable to accurately
    // convert TimeZoneInfo
    // into MeetingTimeZone. So we don't... Instead, we emit the time zone's
    // Id and
    // hope the server will find a match (which it should).
    this.name = timeZone.getId();
  }

  /**
   * Initializes a new instance of the MeetingTimeZone class.
   */
  public MeetingTimeZone() {
    super();
  }

  /**
   * Initializes a new instance of the MeetingTimeZone class.
   *
   * @param name The name of the time zone.
   */
  public MeetingTimeZone(String name) {
    this();
    this.name = name;
  }

  /**
   * Gets the minimum required server version.
   *
   * @param reader the reader
   * @return Earliest Exchange version in which this service object type is
   * supported.
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equals(XmlElementNames.BaseOffset)) {
      this.baseOffset = EwsUtilities.getXSDurationToTimeSpan(reader.readElementValue());
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.Standard)) {
      this.standard = new TimeChange();
      this.standard.loadFromXml(reader, reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.Daylight)) {
      this.daylight = new TimeChange();
      this.daylight.loadFromXml(reader, reader.getLocalName());
      return true;
    } else {
      return false;
    }
  }

  /**
   * Reads the attribute from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  public void readAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.name = reader.readAttributeValue(XmlAttributeNames.TimeZoneName);
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  public void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.TimeZoneName, this
        .getName());
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    if (this.baseOffset != null) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.BaseOffset, EwsUtilities
              .getTimeSpanToXSDuration(this.getBaseOffset()));
    }

    if (this.getStandard() != null) {
      this.getStandard().writeToXml(writer, XmlElementNames.Standard);
    }

    if (this.getDaylight() != null) {
      this.getDaylight().writeToXml(writer, XmlElementNames.Daylight);
    }
  }

  /**
   * Converts this meeting time zone into a TimeZoneInfo structure.
   *
   * @return the time zone
   */
  public TimeZoneDefinition toTimeZoneInfo() {
    TimeZoneDefinition result = null;

    try {
      result = new TimeZoneDefinition();
      //TimeZone.getTimeZone(this.getName());
      result.setId(this.getName());
    } catch (Exception e) {
      // Could not find a time zone with that Id on the local system.
      LOG.error(e);
    }

    // Again, we cannot accurately convert MeetingTimeZone into TimeZoneInfo
    // because TimeZoneInfo doesn't support absolute date transitions. So if
    // there is no system time zone that has a matching Id, we return null.
    return result;
  }

  /**
   * Gets  the name of the time zone.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name.
   *
   * @param value the new name
   */
  public void setName(String value) {
    if (this.canSetFieldValue(this.name, value)) {
      this.name = value;
      this.changed();
    }
  }

  /**
   * Gets the base offset of the time zone from the UTC time zone.
   *
   * @return the base offset
   */
  public TimeSpan getBaseOffset() {
    return this.baseOffset;
  }

  /**
   * Sets the base offset.
   *
   * @param value the new base offset
   */
  public void setBaseOffset(TimeSpan value) {
    if (this.canSetFieldValue(this.name, value)) {
      this.baseOffset = value;
      this.changed();
    }
  }

  /**
   * Gets  a TimeChange defining when the time changes to Standard
   * Time.
   *
   * @return the standard
   */
  public TimeChange getStandard() {
    return this.standard;
  }

  /**
   * Sets the standard.
   *
   * @param value the new standard
   */
  public void setStandard(TimeChange value) {
    if (this.canSetFieldValue(this.standard, value)) {
      this.standard = value;
      this.changed();
    }
  }

  /**
   * Gets  a TimeChange defining when the time changes to Daylight
   * Saving Time.
   *
   * @return the daylight
   */
  public TimeChange getDaylight() {
    return this.daylight;
  }

  /**
   * Sets the daylight.
   *
   * @param value the new daylight
   */
  public void setDaylight(TimeChange value) {
    if (this.canSetFieldValue(this.daylight, value)) {
      this.daylight = value;
      this.changed();
    }
  }

}
