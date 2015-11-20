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
import microsoft.exchange.webservices.data.core.enumeration.availability.FreeBusyViewType;
import microsoft.exchange.webservices.data.core.enumeration.property.LegacyFreeBusyStatus;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.availability.CalendarEvent;
import microsoft.exchange.webservices.data.property.complex.availability.WorkingHours;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents the availability of an individual attendee.
 */
public final class AttendeeAvailability extends ServiceResponse {

  /**
   * The calendar events.
   */
  private Collection<CalendarEvent> calendarEvents =
      new ArrayList<CalendarEvent>();

  /**
   * The merged free busy status.
   */
  private Collection<LegacyFreeBusyStatus> mergedFreeBusyStatus =
      new ArrayList<LegacyFreeBusyStatus>();

  /**
   * The view type.
   */
  private FreeBusyViewType viewType;

  /**
   * The working hours.
   */
  private WorkingHours workingHours;

  /**
   * Initializes a new instance of the AttendeeAvailability class.
   */
  public AttendeeAvailability() {
    super();
  }

  /**
   * Loads the free busy view from XML.
   *
   * @param reader   the reader
   * @param viewType the view type
   * @throws Exception the exception
   */
  public void loadFreeBusyViewFromXml(EwsServiceXmlReader reader, FreeBusyViewType viewType) throws Exception {
    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.FreeBusyView);

    String viewTypeString = reader.readElementValue(XmlNamespace.Types,
        XmlElementNames.FreeBusyViewType);

    for (Object o : FreeBusyViewType.class.getEnumConstants()) {
      if (o.toString().equals(viewTypeString)) {
        this.viewType = (FreeBusyViewType) o;
        break;
      }
    }
    do {
      reader.read();

      if (reader.isStartElement()) {
        if (reader.getLocalName()
            .equals(XmlElementNames.MergedFreeBusy)) {
          String mergedFreeBusy = reader.readElementValue();

          for (int i = 0; i < mergedFreeBusy.length(); i++) {

            Byte b = Byte.parseByte(mergedFreeBusy.charAt(i) + "");
            for (LegacyFreeBusyStatus legacyStatus : LegacyFreeBusyStatus.values()) {
              if (b == legacyStatus.getBusyStatus()) {
                this.mergedFreeBusyStatus.add(legacyStatus);
                break;
              }
            }

          }

        } else if (reader.getLocalName().equals(
            XmlElementNames.CalendarEventArray)) {
          do {
            reader.read();

            if (reader.isStartElement(XmlNamespace.Types,
                XmlElementNames.CalendarEvent)) {
              CalendarEvent calendarEvent = new CalendarEvent();

              calendarEvent.loadFromXml(reader,
                  XmlElementNames.CalendarEvent);

              this.calendarEvents.add(calendarEvent);
            }
          } while (!reader.isEndElement(XmlNamespace.Types,
              XmlElementNames.CalendarEventArray));

        } else if (reader.getLocalName().equals(
            XmlElementNames.WorkingHours)) {
          this.workingHours = new WorkingHours();
          this.workingHours
              .loadFromXml(reader, reader.getLocalName());

          break;
        }
      }
    } while (!reader.isEndElement(XmlNamespace.Messages,
        XmlElementNames.FreeBusyView));
  }

  /**
   * Gets a collection of calendar events for the attendee.
   *
   * @return the calendar events
   */
  public Collection<CalendarEvent> getCalendarEvents() {
    return this.calendarEvents;
  }

  /**
   * Gets a collection of merged free/busy status for the attendee.
   *
   * @return the merged free busy status
   */
  public Collection<LegacyFreeBusyStatus> getMergedFreeBusyStatus() {
    return mergedFreeBusyStatus;
  }

  /**
   * Gets the free/busy view type that wes retrieved for the attendee.
   *
   * @return the view type
   */
  public FreeBusyViewType getViewType() {
    return viewType;
  }

  /**
   * Gets the working hours of the attendee.
   *
   * @return the working hours
   */
  public WorkingHours getWorkingHours() {
    return workingHours;
  }

}
