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

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.request.GetUserAvailabilityRequest;
import microsoft.exchange.webservices.data.core.enumeration.availability.FreeBusyViewType;
import microsoft.exchange.webservices.data.core.enumeration.availability.SuggestionQuality;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

import java.util.Date;

/**
 * Represents the options of a GetAvailability request.
 */
public final class AvailabilityOptions {

  /**
   * The merged free busy interval.
   */
  private int mergedFreeBusyInterval = 30;

  /**
   * The requested free busy view.
   */
  private FreeBusyViewType requestedFreeBusyView = FreeBusyViewType.Detailed;

  /**
   * The good suggestion threshold.
   */
  private int goodSuggestionThreshold = 25;

  /**
   * The maximum suggestions per day.
   */
  private int maximumSuggestionsPerDay = 10;

  /**
   * The maximum non work hours suggestions per day.
   */
  private int maximumNonWorkHoursSuggestionsPerDay = 0;

  /**
   * The meeting duration.
   */
  private int meetingDuration = 60;

  /**
   * The minimum suggestion quality.
   */
  private SuggestionQuality minimumSuggestionQuality = SuggestionQuality.Fair;

  /**
   * The detailed suggestions window.
   */
  private TimeWindow detailedSuggestionsWindow;

  /**
   * The current meeting time.
   */
  private Date currentMeetingTime;

  /**
   * The global object id.
   */
  private String globalObjectId;

  /**
   * Validates this instance against the specified time window.
   *
   * @param timeWindow the time window
   * @throws Exception the exception
   */
  public void validate(long timeWindow) throws Exception {
    if (this.mergedFreeBusyInterval > timeWindow) {
      throw new IllegalArgumentException(
          "MergedFreeBusyInterval must be smaller than the specified time window.");
    }

    EwsUtilities.validateParamAllowNull(this.detailedSuggestionsWindow, "DetailedSuggestionsWindow");
  }

  /**
   * Writes to XML.
   *
   * @param writer  the writer
   * @param request the request
   * @throws Exception the exception
   */
  public void writeToXml(EwsServiceXmlWriter writer, GetUserAvailabilityRequest request) throws Exception {
    if (request.isFreeBusyViewRequested()) {
      writer.writeStartElement(XmlNamespace.Types,
          XmlElementNames.FreeBusyViewOptions);

      request.getTimeWindow().writeToXmlUnscopedDatesOnly(writer,
          XmlElementNames.TimeWindow);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.MergedFreeBusyIntervalInMinutes,
          this.mergedFreeBusyInterval);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.RequestedView, this.requestedFreeBusyView);

      writer.writeEndElement(); // FreeBusyViewOptions
    }

    if (request.isSuggestionsViewRequested()) {
      writer.writeStartElement(XmlNamespace.Types,
          XmlElementNames.SuggestionsViewOptions);

      writer
          .writeElementValue(XmlNamespace.Types,
              XmlElementNames.GoodThreshold,
              this.goodSuggestionThreshold);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.MaximumResultsByDay,
          this.maximumSuggestionsPerDay);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.MaximumNonWorkHourResultsByDay,
          this.maximumNonWorkHoursSuggestionsPerDay);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.MeetingDurationInMinutes,
          this.meetingDuration);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.MinimumSuggestionQuality,
          this.minimumSuggestionQuality);

      TimeWindow timeWindowToSerialize =
          this.detailedSuggestionsWindow == null ? request
              .getTimeWindow() :
              this.detailedSuggestionsWindow;

      timeWindowToSerialize.writeToXmlUnscopedDatesOnly(writer,
          XmlElementNames.DetailedSuggestionsWindow);

      if (this.currentMeetingTime != null) {
        writer.writeElementValue(XmlNamespace.Types,
            XmlElementNames.CurrentMeetingTime,
            this.currentMeetingTime);
      }

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.GlobalObjectId, this.globalObjectId);

      writer.writeEndElement(); // SuggestionsViewOptions
    }
  }

  /**
   * Initializes a new instance of the AvailabilityOptions class.
   */
  public AvailabilityOptions() {
  }

  /**
   * Gets the time difference between two successive slots in a
   * FreeBusyMerged view. MergedFreeBusyInterval must be between 5 and 1440.
   * The default value is 30.
   *
   * @return the merged free busy interval
   */
  public int getMergedFreeBusyInterval() {
    return this.mergedFreeBusyInterval;
  }

  /**
   * Sets the merged free busy interval.
   *
   * @param value the new merged free busy interval
   */
  public void setMergedFreeBusyInterval(int value) {
    if (value < 5 || value > 1440) {
      throw new IllegalArgumentException(String.format("%s,%s,%s,%s", "%s must be between %d and %d.",
          "MergedFreeBusyInterval", 5, 1440));
    }

    this.mergedFreeBusyInterval = value;
  }

  /**
   * Gets  the requested type of free/busy view. The default value is
   * FreeBusyViewType.Detailed.
   *
   * @return the requested free busy view
   */
  public FreeBusyViewType getRequestedFreeBusyView() {
    return this.requestedFreeBusyView;
  }

  /**
   * Sets the requested free busy view.
   *
   * @param value the new requested free busy view
   */
  public void setRequestedFreeBusyView(FreeBusyViewType value) {
    this.requestedFreeBusyView = value;
  }

  /**
   * Gets  the percentage of attendees that must have the time period
   * open for the time period to qualify as a good suggested meeting time.
   * GoodSuggestionThreshold must be between 1 and 49. The default value is
   * 25.
   *
   * @return the good suggestion threshold
   */
  public int getGoodSuggestionThreshold() {
    return this.goodSuggestionThreshold;
  }

  /**
   * Sets the good suggestion threshold.
   *
   * @param value the new good suggestion threshold
   */
  public void setGoodSuggestionThreshold(int value) {
    if (value < 1 || value > 49) {
      throw new IllegalArgumentException(String.format("%s must be between %d and %d.",
          "GoodSuggestionThreshold", 1, 49));
    }

    this.goodSuggestionThreshold = value;
  }

  /**
   * Gets the number of suggested meeting times that should be
   * returned per day. MaximumSuggestionsPerDay must be between 0 and 48. The
   * default value is 10.
   *
   * @return the maximum suggestions per day
   */
  public int getMaximumSuggestionsPerDay() {
    return this.maximumSuggestionsPerDay;
  }

  /**
   * Sets the maximum suggestions per day.
   *
   * @param value the new maximum suggestions per day
   */
  public void setMaximumSuggestionsPerDay(int value) {
    if (value < 0 || value > 48) {
      throw new IllegalArgumentException(String.format("%s,%s,%s,%s", "%s must be between %d and %d.",
          "MaximumSuggestionsPerDay", 0, 48));
    }

    this.maximumSuggestionsPerDay = value;
  }

  /**
   * Gets the number of suggested meeting times outside regular
   * working hours per day. MaximumNonWorkHoursSuggestionsPerDay must be
   * between 0 and 48. The default value is 0.
   *
   * @return the maximum non work hours suggestions per day
   */
  public int getMaximumNonWorkHoursSuggestionsPerDay() {
    return this.maximumNonWorkHoursSuggestionsPerDay;
  }

  /**
   * Sets the maximum non work hours suggestions per day.
   *
   * @param value the new maximum non work hours suggestions per day
   */
  public void setMaximumNonWorkHoursSuggestionsPerDay(int value) {
    if (value < 0 || value > 48) {
      throw new IllegalArgumentException(String.format("%s must be between %d and %d.",
          "MaximumNonWorkHoursSuggestionsPerDay", 0, 48));
    }

    this.maximumNonWorkHoursSuggestionsPerDay = value;
  }

  /**
   * Gets  the duration, in minutes, of the meeting for which to obtain
   * suggestions. MeetingDuration must be between 30 and 1440. The default
   * value is 60.
   *
   * @return the meeting duration
   */
  public int getMeetingDuration() {
    return this.meetingDuration;
  }

  /**
   * Sets the meeting duration.
   *
   * @param value the new meeting duration
   */
  public void setMeetingDuration(int value) {
    if (value < 30 || value > 1440) {
      throw new IllegalArgumentException(String.format("%s,%s,%s,%s", "%s must be between %d and %d.", "MeetingDuration",
          30, 1440));
    }

    this.meetingDuration = value;
  }

  /**
   * Gets the minimum quality of suggestions that should be returned.
   * The default is SuggestionQuality.Fair.
   *
   * @return the minimum suggestion quality
   */
  public SuggestionQuality getMinimumSuggestionQuality() {
    return this.minimumSuggestionQuality;
  }

  /**
   * Sets the minimum suggestion quality.
   *
   * @param value the new minimum suggestion quality
   */
  public void setMinimumSuggestionQuality(SuggestionQuality value) {
    this.minimumSuggestionQuality = value;
  }

  /**
   * Gets the time window for which detailed information about
   * suggested meeting times should be returned.
   *
   * @return the detailed suggestions window
   */
  public TimeWindow getDetailedSuggestionsWindow() {
    return this.detailedSuggestionsWindow;
  }

  /**
   * Sets the detailed suggestions window.
   *
   * @param value the new detailed suggestions window
   */
  public void setDetailedSuggestionsWindow(TimeWindow value) {
    this.detailedSuggestionsWindow = value;
  }

  /**
   * Gets the start time of a meeting that you want to update with the
   * suggested meeting times.
   *
   * @return the current meeting time
   */
  public Date getCurrentMeetingTime() {
    return this.currentMeetingTime;
  }

  /**
   * Sets the current meeting time.
   *
   * @param value the new current meeting time
   */
  public void setCurrentMeetingTime(Date value) {
    this.currentMeetingTime = value;
  }

  /**
   * Gets the global object Id of a meeting that will be modified
   * based on the data returned by GetUserAvailability.
   *
   * @return the global object id
   */
  public String getGlobalObjectId() {
    return this.globalObjectId;
  }

  /**
   * Sets the global object id.
   *
   * @param value the new global object id
   */
  public void setGlobalObjectId(String value) {
    this.globalObjectId = value;
  }
}
