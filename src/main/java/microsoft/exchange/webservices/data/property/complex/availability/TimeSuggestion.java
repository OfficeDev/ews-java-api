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

package microsoft.exchange.webservices.data.property.complex.availability;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.property.ConflictType;
import microsoft.exchange.webservices.data.core.enumeration.availability.SuggestionQuality;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Represents an availability time suggestion.
 */
public final class TimeSuggestion extends ComplexProperty {

  /**
   * The meeting time.
   */
  private Date meetingTime;

  /**
   * The is work time.
   */
  private boolean isWorkTime;

  /**
   * The quality.
   */
  private SuggestionQuality quality;

  /**
   * The conflicts.
   */
  private Collection<Conflict> conflicts = new ArrayList<Conflict>();

  /**
   * Initializes a new instance of the TimeSuggestion class.
   */
  protected TimeSuggestion() {
    super();
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader the reader
   * @return True if appropriate element was read.
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equals(XmlElementNames.MeetingTime)) {
      this.meetingTime = reader
          .readElementValueAsUnbiasedDateTimeScopedToServiceTimeZone();
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.IsWorkTime)) {
      this.isWorkTime = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equals(
        XmlElementNames.SuggestionQuality)) {
      this.quality = reader.readElementValue(SuggestionQuality.class);
      return true;
    } else if (reader.getLocalName().equals(
        XmlElementNames.AttendeeConflictDataArray)) {
      if (!reader.isEmptyElement()) {
        do {
          reader.read();

          if (reader.isStartElement()) {
            Conflict conflict = null;

            if (reader.getLocalName().equals(
                XmlElementNames.UnknownAttendeeConflictData)) {
              conflict = new Conflict(
                  ConflictType.UnknownAttendeeConflict);
            } else if (reader
                .getLocalName()
                .equals(
                    XmlElementNames.
                        TooBigGroupAttendeeConflictData)) {
              conflict = new Conflict(
                  ConflictType.GroupTooBigConflict);
            } else if (reader.getLocalName().equals(
                XmlElementNames.
                    IndividualAttendeeConflictData)) {
              conflict = new Conflict(
                  ConflictType.IndividualAttendeeConflict);
            } else if (reader.getLocalName().equals(
                XmlElementNames.GroupAttendeeConflictData)) {
              conflict = new Conflict(ConflictType.GroupConflict);
            } else {
              EwsUtilities
                  .ewsAssert(false, "TimeSuggestion." + "TryReadElementFromXml",
                             String.format("The %s element name " +
                                           "does not map " +
                                           "to any AttendeeConflict " +
                                           "descendant.", reader.getLocalName()));

              // The following line to please the compiler
            }
            conflict.loadFromXml(reader, reader.getLocalName());

            this.conflicts.add(conflict);
          }
        } while (!reader.isEndElement(XmlNamespace.Types,
            XmlElementNames.AttendeeConflictDataArray));
      }

      return true;
    } else {
      return false;
    }

  }

  /**
   * Gets the suggested time.
   *
   * @return the meeting time
   */
  public Date getMeetingTime() {
    return meetingTime;
  }

  /**
   * Gets a value indicating whether the suggested time is within working
   * hours.
   *
   * @return true, if is work time
   */
  public boolean isWorkTime() {
    return isWorkTime;
  }

  /**
   * Gets the quality of the suggestion.
   *
   * @return the quality
   */
  public SuggestionQuality getQuality() {
    return quality;
  }

  /**
   * Gets a collection of conflicts at the suggested time.
   *
   * @return the conflicts
   */
  public Collection<Conflict> getConflicts() {
    return conflicts;
  }

}
