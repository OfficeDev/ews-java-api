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

package microsoft.exchange.webservices.data.property.complex.recurrence.pattern;

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.property.time.DayOfTheWeek;
import microsoft.exchange.webservices.data.core.enumeration.property.time.DayOfTheWeekIndex;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.time.Month;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentOutOfRangeException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;
import microsoft.exchange.webservices.data.property.complex.IComplexPropertyChangedDelegate;
import microsoft.exchange.webservices.data.property.complex.recurrence.DayOfTheWeekCollection;
import microsoft.exchange.webservices.data.property.complex.recurrence.range.EndDateRecurrenceRange;
import microsoft.exchange.webservices.data.property.complex.recurrence.range.NoEndRecurrenceRange;
import microsoft.exchange.webservices.data.property.complex.recurrence.range.NumberedRecurrenceRange;
import microsoft.exchange.webservices.data.property.complex.recurrence.range.RecurrenceRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * Represents a recurrence pattern, as used by Appointment and Task item.
 */
public abstract class Recurrence extends ComplexProperty {

  /**
   * The start date.
   */
  private Date startDate;

  /**
   * The number of occurrences.
   */
  private Integer numberOfOccurrences;

  /**
   * The end date.
   */
  private Date endDate;

  /**
   * Initializes a new instance.
   */
  public Recurrence() {
    super();
  }

  /**
   * Initializes a new instance.
   *
   * @param startDate the start date
   */
  public Recurrence(Date startDate) {
    this();
    this.startDate = startDate;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return the xml element name
   */
  public abstract String getXmlElementName();

  /**
   * Gets a value indicating whether this instance is regeneration pattern.
   *
   * @return true, if is regeneration pattern
   */
  public boolean isRegenerationPattern() {
    return false;
  }

  /**
   * Write property to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  public void internalWritePropertiesToXml(EwsServiceXmlWriter writer) throws Exception {
  }

  /**
   * Writes elements to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  public final void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    writer.writeStartElement(XmlNamespace.Types, this.getXmlElementName());
    this.internalWritePropertiesToXml(writer);
    writer.writeEndElement();

    RecurrenceRange range = null;

    if (!this.hasEnd()) {
      range = new NoEndRecurrenceRange(this.getStartDate());
    } else if (this.getNumberOfOccurrences() != null) {
      range = new NumberedRecurrenceRange(this.startDate,
          this.numberOfOccurrences);
    } else {
      if (this.getEndDate() != null) {
        range = new EndDateRecurrenceRange(this.getStartDate(), this
            .getEndDate());
      }
    }
    if (range != null) {
      range.writeToXml(writer, range.getXmlElementName());
    }

  }

  /**
   * Gets a property value or throw if null. *
   *
   * @param <T>   the generic type
   * @param cls   the cls
   * @param value the value
   * @param name  the name
   * @return Property value
   * @throws ServiceValidationException the service validation exception
   */
  public <T> T getFieldValueOrThrowIfNull(Class<T> cls, Object value,
      String name) throws ServiceValidationException {
    if (value != null) {
      return (T) value;
    } else {
      throw new ServiceValidationException(String.format(
          "The recurrence pattern's %s property must be specified.",
          name));
    }
  }

  /**
   * Gets the date and time when the recurrence start.
   *
   * @return Date
   * @throws ServiceValidationException the service validation exception
   */
  public Date getStartDate() throws ServiceValidationException {
    return this.getFieldValueOrThrowIfNull(Date.class, this.startDate,
        "StartDate");

  }

  /**
   * sets the date and time when the recurrence start.
   *
   * @param value the new start date
   */
  public void setStartDate(Date value) {
    this.startDate = value;
  }

  /**
   * Gets a value indicating whether the pattern has a fixed number of
   * occurrences or an end date.
   *
   * @return boolean
   */
  public boolean hasEnd() {

    return ((this.numberOfOccurrences != null) || (this.endDate != null));
  }

  /**
   * Sets up this recurrence so that it never ends. Calling NeverEnds is
   * equivalent to setting both NumberOfOccurrences and EndDate to null.
   */
  public void neverEnds() {
    this.numberOfOccurrences = null;
    this.endDate = null;
    this.changed();
  }

  /**
   * Validates this instance.
   *
   * @throws Exception
   */
  @Override
  public void internalValidate() throws Exception {
    super.internalValidate();

    if (this.startDate == null) {
      throw new ServiceValidationException("The recurrence pattern's StartDate property must be specified.");
    }
  }

  /**
   * Gets the number of occurrences after which the recurrence ends.
   * Setting NumberOfOccurrences resets EndDate.
   *
   * @return the number of occurrences
   */
  public Integer getNumberOfOccurrences() {
    return this.numberOfOccurrences;

  }

  /**
   * Gets the number of occurrences after which the recurrence ends.
   * Setting NumberOfOccurrences resets EndDate.
   *
   * @param value the new number of occurrences
   * @throws ArgumentException the argument exception
   */
  public void setNumberOfOccurrences(Integer value) throws ArgumentException {
    if (value < 1) {
      throw new ArgumentException("NumberOfOccurrences must be greater than 0.");
    }

    if (this.canSetFieldValue(this.numberOfOccurrences, value)) {
      numberOfOccurrences = value;
      this.changed();
    }

    this.endDate = null;

  }

  /**
   * Gets the date after which the recurrence ends. Setting EndDate resets
   * NumberOfOccurrences.
   *
   * @return the end date
   */
  public Date getEndDate() {

    return this.endDate;
  }

  /**
   * sets the date after which the recurrence ends. Setting EndDate resets
   * NumberOfOccurrences.
   *
   * @param value the new end date
   */
  public void setEndDate(Date value) {

    if (this.canSetFieldValue(this.endDate, value)) {
      this.endDate = value;
      this.changed();
    }

    this.numberOfOccurrences = null;

  }

  /**
   * Represents a recurrence pattern where each occurrence happens a specific
   * number of days after the previous one.
   */
  public final static class DailyPattern extends IntervalPattern {

    /**
     * Gets the name of the XML element.
     *
     * @return the xml element name
     */
    @Override
    public String getXmlElementName() {
      return XmlElementNames.DailyRecurrence;
    }

    /**
     * Initializes a new instance of the DailyPattern class.
     */

    public DailyPattern() {
      super();
    }

    /**
     * Initializes a new instance of the DailyPattern class.
     *
     * @param startDate The date and time when the recurrence starts.
     * @param interval  The number of days between each occurrence.
     * @throws ArgumentOutOfRangeException the argument out of range exception
     */
    public DailyPattern(Date startDate, int interval)
        throws ArgumentOutOfRangeException {
      super(startDate, interval);
    }

  }


  /**
   * Represents a regeneration pattern, as used with recurring tasks, where
   * each occurrence happens a specified number of days after the previous one
   * is completed.
   */

  public final static class DailyRegenerationPattern extends IntervalPattern {

    /**
     * Initializes a new instance of the DailyRegenerationPattern class.
     */
    public DailyRegenerationPattern() {
      super();
    }

    /**
     * Initializes a new instance of the DailyRegenerationPattern class.
     *
     * @param startDate The date and time when the recurrence starts.
     * @param interval  The number of days between each occurrence.
     * @throws ArgumentOutOfRangeException the argument out of range exception
     */
    public DailyRegenerationPattern(Date startDate, int interval)
        throws ArgumentOutOfRangeException {
      super(startDate, interval);

    }

    /**
     * Gets the name of the XML element.
     *
     * @return the xml element name
     */
    public String getXmlElementName() {
      return XmlElementNames.DailyRegeneration;
    }

    /**
     * Gets a value indicating whether this instance is a regeneration
     * pattern.
     *
     * @return true, if is regeneration pattern
     */
    public boolean isRegenerationPattern() {
      return true;
    }

  }


  /**
   * Represents a recurrence pattern where each occurrence happens at a
   * specific interval after the previous one.
   * [EditorBrowsable(EditorBrowsableState.Never)]
   */
  @EditorBrowsable(state = EditorBrowsableState.Never)
  public abstract static class IntervalPattern extends Recurrence {

    /**
     * The interval.
     */
    private int interval = 1;

    /**
     * Initializes a new instance of the IntervalPattern class.
     */
    public IntervalPattern() {
      super();
    }

    /**
     * Initializes a new instance of the IntervalPattern class.
     *
     * @param startDate The date and time when the recurrence starts.
     * @param interval  The number of days between each occurrence.
     * @throws ArgumentOutOfRangeException the argument out of range exception
     */
    public IntervalPattern(Date startDate, int interval)
        throws ArgumentOutOfRangeException {

      super(startDate);
      if (interval < 1) {
        throw new ArgumentOutOfRangeException("interval", "The interval must be greater than or equal to 1.");
      }

      this.setInterval(interval);
    }

    /**
     * Write property to XML.
     *
     * @param writer the writer
     * @throws Exception the exception
     */
    @Override
    public void internalWritePropertiesToXml(EwsServiceXmlWriter writer) throws Exception {
      super.internalWritePropertiesToXml(writer);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.Interval, this.getInterval());
    }

    /**
     * Tries to read element from XML.
     *
     * @param reader the reader
     * @return true, if successful
     * @throws Exception the exception
     */
    @Override
    public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
        throws Exception {
      if (super.tryReadElementFromXml(reader)) {
        return true;
      } else {

        if (reader.getLocalName().equals(XmlElementNames.Interval)) {
          this.interval = reader.readElementValue(Integer.class);
          return true;
        } else {
          return false;
        }
      }
    }

    /**
     * Gets the interval between occurrences.
     *
     * @return the interval
     */
    public int getInterval() {
      return this.interval;
    }

    /**
     * Sets the interval.
     *
     * @param value the new interval
     * @throws ArgumentOutOfRangeException the argument out of range exception
     */
    public void setInterval(int value) throws ArgumentOutOfRangeException {

      if (value < 1) {
        throw new ArgumentOutOfRangeException("value", "The interval must be greater than or equal to 1.");
      }

      if (this.canSetFieldValue(this.interval, value)) {
        this.interval = value;
        this.changed();
      }

    }

  }


  /**
   * Represents a recurrence pattern where each occurrence happens on a
   * specific day a specific number of months after the previous one.
   */

  public final static class MonthlyPattern extends IntervalPattern {

    /**
     * The day of month.
     */
    private Integer dayOfMonth;

    /**
     * Initializes a new instance of the MonthlyPattern class.
     */
    public MonthlyPattern() {
      super();

    }

    /**
     * Initializes a new instance of the MonthlyPattern class.
     *
     * @param startDate  the start date
     * @param interval   the interval
     * @param dayOfMonth the day of month
     * @throws ArgumentOutOfRangeException the argument out of range exception
     */
    public MonthlyPattern(Date startDate, int interval, int dayOfMonth)
        throws ArgumentOutOfRangeException {
      super(startDate, interval);

      this.setDayOfMonth(dayOfMonth);
    }

    // / Gets the name of the XML element.

    /*
     * (non-Javadoc)
     *
     * @see microsoft.exchange.webservices.Recurrence#getXmlElementName()
     */
    @Override
    public String getXmlElementName() {
      return XmlElementNames.AbsoluteMonthlyRecurrence;
    }

    /**
     * Write property to XML.
     *
     * @param writer the writer
     * @throws Exception the exception
     */
    @Override
    public void internalWritePropertiesToXml(EwsServiceXmlWriter writer)
        throws Exception {
      super.internalWritePropertiesToXml(writer);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.DayOfMonth, this.getDayOfMonth());
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
      if (super.tryReadElementFromXml(reader)) {
        return true;
      } else {
        if (reader.getLocalName().equals(XmlElementNames.DayOfMonth)) {
          this.dayOfMonth = reader.readElementValue(Integer.class);
          return true;
        } else {
          return false;
        }
      }
    }

    /**
     * Validates this instance.
     *
     * @throws Exception
     */
    @Override
    public void internalValidate() throws Exception {
      super.internalValidate();

      if (this.dayOfMonth == null) {
        throw new ServiceValidationException("DayOfMonth must be between 1 and 31.");
      }
    }

    /**
     * Gets the day of month.
     *
     * @return the day of month
     * @throws ServiceValidationException the service validation exception
     */
    public int getDayOfMonth() throws ServiceValidationException {
      return this.getFieldValueOrThrowIfNull(Integer.class, this.dayOfMonth,
          "DayOfMonth");

    }

    /**
     * Sets the day of month.
     *
     * @param value the new day of month
     * @throws ArgumentOutOfRangeException the argument out of range exception
     */
    public void setDayOfMonth(int value)
        throws ArgumentOutOfRangeException {
      if (value < 1 || value > 31) {
        throw new ArgumentOutOfRangeException("DayOfMonth", "DayOfMonth must be between 1 and 31.");
      }

      if (this.canSetFieldValue(this.dayOfMonth, value)) {
        this.dayOfMonth = value;
        this.changed();
      }
    }
  }


  /**
   * Represents a regeneration pattern, as used with recurring tasks, where
   * each occurrence happens a specified number of months after the previous
   * one is completed.
   */
  public final static class MonthlyRegenerationPattern extends
      IntervalPattern {

    /**
     * Instantiates a new monthly regeneration pattern.
     */
    public MonthlyRegenerationPattern() {
      super();

    }

    /**
     * Instantiates a new monthly regeneration pattern.
     *
     * @param startDate the start date
     * @param interval  the interval
     * @throws ArgumentOutOfRangeException the argument out of range exception
     */
    public MonthlyRegenerationPattern(Date startDate, int interval)
        throws ArgumentOutOfRangeException {
      super(startDate, interval);

    }

    /**
     * Gets the name of the XML element. <value>The name of the XML
     * element.</value>
     *
     * @return the xml element name
     */
    @Override
    public String getXmlElementName() {
      return XmlElementNames.MonthlyRegeneration;
    }

    /**
     * Gets a value indicating whether this instance is regeneration
     * pattern. <value> <c>true</c> if this instance is regeneration
     * pattern; otherwise, <c>false</c>. </value>
     *
     * @return true, if is regeneration pattern
     */
    public boolean isRegenerationPattern() {
      return true;
    }
  }


  /**
   * Represents a recurrence pattern where each occurrence happens on a
   * relative day a specific number of months after the previous one.
   */
  public final static class RelativeMonthlyPattern extends IntervalPattern {

    /**
     * The day of the week.
     */
    private DayOfTheWeek dayOfTheWeek;

    /**
     * The day of the week index.
     */
    private DayOfTheWeekIndex dayOfTheWeekIndex;

    // / Initializes a new instance of the <see
    // cref="RelativeMonthlyPattern"/> class.

    /**
     * Instantiates a new relative monthly pattern.
     */
    public RelativeMonthlyPattern() {
      super();
    }

    /**
     * Instantiates a new relative monthly pattern.
     *
     * @param startDate         the start date
     * @param interval          the interval
     * @param dayOfTheWeek      the day of the week
     * @param dayOfTheWeekIndex the day of the week index
     * @throws ArgumentOutOfRangeException the argument out of range exception
     */
    public RelativeMonthlyPattern(Date startDate, int interval,
        DayOfTheWeek dayOfTheWeek, DayOfTheWeekIndex dayOfTheWeekIndex)
        throws ArgumentOutOfRangeException {
      super(startDate, interval);

      this.setDayOfTheWeek(dayOfTheWeek);
      this.setDayOfTheWeekIndex(dayOfTheWeekIndex);
    }

    /**
     * Gets the name of the XML element.
     *
     * @return the xml element name
     */
    @Override
    public String getXmlElementName() {
      return XmlElementNames.RelativeMonthlyRecurrence;
    }

    /**
     * Write property to XML.
     *
     * @param writer the writer
     * @throws Exception the exception
     */
    @Override
    public void internalWritePropertiesToXml(EwsServiceXmlWriter writer)
        throws Exception {
      super.internalWritePropertiesToXml(writer);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.DaysOfWeek, this.getDayOfTheWeek());

      writer
          .writeElementValue(XmlNamespace.Types,
              XmlElementNames.DayOfWeekIndex, this
                  .getDayOfTheWeekIndex());
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
      if (super.tryReadElementFromXml(reader)) {
        return true;
      } else {
        if (reader.getLocalName().equals(XmlElementNames.DaysOfWeek)) {

          this.dayOfTheWeek = reader
              .readElementValue(DayOfTheWeek.class);
          return true;
        } else if (reader.getLocalName().equals(
            XmlElementNames.DayOfWeekIndex)) {

          this.dayOfTheWeekIndex = reader
              .readElementValue(DayOfTheWeekIndex.class);
          return true;
        } else {

          return false;
        }
      }
    }

    /**
     * Validates this instance.
     *
     * @throws Exception
     */
    @Override
    public void internalValidate() throws Exception {
      super.internalValidate();

      if (this.dayOfTheWeek == null) {
        throw new ServiceValidationException(
            "The recurrence pattern's property DayOfTheWeek must be specified.");
      }

      if (this.dayOfTheWeekIndex == null) {
        throw new ServiceValidationException(
            "The recurrence pattern's DayOfWeekIndex property must be specified.");
      }
    }

    /**
     * Day of the week index.
     *
     * @return the day of the week index
     * @throws ServiceValidationException the service validation exception
     */
    public DayOfTheWeekIndex getDayOfTheWeekIndex()
        throws ServiceValidationException {
      return this.getFieldValueOrThrowIfNull(DayOfTheWeekIndex.class,
          this.dayOfTheWeekIndex, "DayOfTheWeekIndex");
    }

    /**
     * Day of the week index.
     *
     * @param value the value
     */
    public void setDayOfTheWeekIndex(DayOfTheWeekIndex value) {
      if (this.canSetFieldValue(this.dayOfTheWeekIndex, value)) {
        this.dayOfTheWeekIndex = value;
        this.changed();
      }

    }

    /**
     * Gets the day of the week.
     *
     * @return the day of the week
     * @throws ServiceValidationException the service validation exception
     */
    public DayOfTheWeek getDayOfTheWeek()
        throws ServiceValidationException {
      return this.getFieldValueOrThrowIfNull(DayOfTheWeek.class,
          this.dayOfTheWeek, "DayOfTheWeek");

    }

    /**
     * Sets the day of the week.
     *
     * @param value the new day of the week
     */
    public void setDayOfTheWeek(DayOfTheWeek value) {

      if (this.canSetFieldValue(this.dayOfTheWeek, value)) {
        this.dayOfTheWeek = value;
        this.changed();
      }
    }
  }


  /**
   * The Class RelativeYearlyPattern.
   */
  public final static class RelativeYearlyPattern extends Recurrence {

    /**
     * The day of the week.
     */
    private DayOfTheWeek dayOfTheWeek;

    /**
     * The day of the week index.
     */
    private DayOfTheWeekIndex dayOfTheWeekIndex;

    /**
     * The month.
     */
    private Month month;

    /**
     * Gets the name of the XML element. <value>The name of the XML
     * element.</value>
     *
     * @return the xml element name
     */
    @Override
    public String getXmlElementName() {
      return XmlElementNames.RelativeYearlyRecurrence;
    }

    /**
     * Write property to XML.
     *
     * @param writer the writer
     * @throws Exception the exception
     */
    @Override
    public void internalWritePropertiesToXml(EwsServiceXmlWriter writer)
        throws Exception {
      super.internalWritePropertiesToXml(writer);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.DaysOfWeek, this.dayOfTheWeek);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.DayOfWeekIndex, this.dayOfTheWeekIndex);

      writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Month,
          this.month);
    }

    /**
     * Tries to read element from XML.
     *
     * @param reader the reader
     * @return True if element was read.
     * @throws Exception the exception
     */
    @Override
    public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
        throws Exception {
      if (super.tryReadElementFromXml(reader)) {
        return true;
      } else {
        if (reader.getLocalName().equals(XmlElementNames.DaysOfWeek)) {

          this.dayOfTheWeek = reader
              .readElementValue(DayOfTheWeek.class);
          return true;
        } else if (reader.getLocalName().equals(
            XmlElementNames.DayOfWeekIndex)) {

          this.dayOfTheWeekIndex = reader
              .readElementValue(DayOfTheWeekIndex.class);
          return true;
        } else if (reader.getLocalName().equals(XmlElementNames.Month)) {

          this.month = reader.readElementValue(Month.class);
          return true;
        } else {

          return false;
        }
      }
    }

    /**
     * Instantiates a new relative yearly pattern.
     */
    public RelativeYearlyPattern() {
      super();

    }

    /**
     * Instantiates a new relative yearly pattern.
     *
     * @param startDate         the start date
     * @param month             the month
     * @param dayOfTheWeek      the day of the week
     * @param dayOfTheWeekIndex the day of the week index
     */
    public RelativeYearlyPattern(Date startDate, Month month,
        DayOfTheWeek dayOfTheWeek,
        DayOfTheWeekIndex dayOfTheWeekIndex) {
      super(startDate);

      this.month = month;
      this.dayOfTheWeek = dayOfTheWeek;
      this.dayOfTheWeekIndex = dayOfTheWeekIndex;
    }

    /**
     * Validates this instance.
     *
     * @throws Exception
     */
    @Override
    public void internalValidate() throws Exception {
      super.internalValidate();

      if (this.dayOfTheWeekIndex == null) {
        throw new ServiceValidationException(
            "The recurrence pattern's DayOfWeekIndex property must be specified.");
      }

      if (this.dayOfTheWeek == null) {
        throw new ServiceValidationException(
            "The recurrence pattern's property DayOfTheWeek must be specified.");
      }

      if (this.month == null) {
        throw new ServiceValidationException("The recurrence pattern's Month property must be specified.");
      }
    }

    /**
     * Gets the relative position of the day specified in DayOfTheWeek
     * within the month.
     *
     * @return the day of the week index
     * @throws ServiceValidationException the service validation exception
     */
    public DayOfTheWeekIndex getDayOfTheWeekIndex()
        throws ServiceValidationException {

      return this.getFieldValueOrThrowIfNull(DayOfTheWeekIndex.class,
          this.dayOfTheWeekIndex, "DayOfTheWeekIndex");
    }

    /**
     * Sets the relative position of the day specified in DayOfTheWeek
     * within the month.
     *
     * @param value the new day of the week index
     */
    public void setDayOfTheWeekIndex(DayOfTheWeekIndex value) {

      if (this.canSetFieldValue(this.dayOfTheWeekIndex, value)) {
        this.dayOfTheWeekIndex = value;
        this.changed();
      }
    }

    /**
     * Gets the day of the week.
     *
     * @return the day of the week
     * @throws ServiceValidationException the service validation exception
     */
    public DayOfTheWeek getDayOfTheWeek()
        throws ServiceValidationException {

      return this.getFieldValueOrThrowIfNull(DayOfTheWeek.class,
          this.dayOfTheWeek, "DayOfTheWeek");
    }

    /**
     * Sets the day of the week.
     *
     * @param value the new day of the week
     */
    public void setDayOfTheWeek(DayOfTheWeek value) {

      if (this.canSetFieldValue(this.dayOfTheWeek, value)) {
        this.dayOfTheWeek = value;
        this.changed();
      }
    }

    /**
     * Gets the month.
     *
     * @return the month
     * @throws ServiceValidationException the service validation exception
     */
    public Month getMonth() throws ServiceValidationException {

      return this.getFieldValueOrThrowIfNull(Month.class, this.month,
          "Month");

    }

    /**
     * Sets the month.
     *
     * @param value the new month
     */
    public void setMonth(Month value) {

      if (this.canSetFieldValue(this.month, value)) {
        this.month = value;
        this.changed();
      }
    }
  }


  /**
   * Represents a recurrence pattern where each occurrence happens on specific
   * days a specific number of weeks after the previous one.
   */
  public final static class WeeklyPattern extends IntervalPattern implements IComplexPropertyChangedDelegate {

    /**
     * The days of the week.
     */
    private DayOfTheWeekCollection daysOfTheWeek =
        new DayOfTheWeekCollection();

    private Calendar firstDayOfWeek;

    /**
     * Initializes a new instance of the WeeklyPattern class. specific days
     * a specific number of weeks after the previous one.
     */
    public WeeklyPattern() {
      super();

      this.daysOfTheWeek.addOnChangeEvent(this);
    }

    /**
     * Initializes a new instance of the WeeklyPattern class.
     *
     * @param startDate     the start date
     * @param interval      the interval
     * @param daysOfTheWeek the days of the week
     * @throws ArgumentOutOfRangeException the argument out of range exception
     */
    public WeeklyPattern(Date startDate, int interval,
        DayOfTheWeek... daysOfTheWeek)
        throws ArgumentOutOfRangeException {
      super(startDate, interval);

      ArrayList<DayOfTheWeek> toProcess = new ArrayList<DayOfTheWeek>(
          Arrays.asList(daysOfTheWeek));
      Iterator<DayOfTheWeek> idaysOfTheWeek = toProcess.iterator();
      this.daysOfTheWeek.addRange(idaysOfTheWeek);
    }

    /**
     * Change event handler.
     *
     * @param complexProperty the complex property
     */
    private void daysOfTheWeekChanged(ComplexProperty complexProperty) {
      this.changed();
    }

    /**
     * Gets the name of the XML element. <value>The name of the XML
     * element.</value>
     *
     * @return the xml element name
     */
    @Override
    public String getXmlElementName() {
      return XmlElementNames.WeeklyRecurrence;
    }

    /**
     * Write property to XML.
     *
     * @param writer the writer
     * @throws Exception the exception
     */
    @Override
    public void internalWritePropertiesToXml(EwsServiceXmlWriter writer)
        throws Exception {
      super.internalWritePropertiesToXml(writer);

      this.getDaysOfTheWeek().writeToXml(writer,
          XmlElementNames.DaysOfWeek);
      if (this.firstDayOfWeek != null) {

        EwsUtilities
            .validatePropertyVersion((ExchangeService) writer.getService(), ExchangeVersion.Exchange2010_SP1,
                                     "FirstDayOfWeek");

        writer.writeElementValue(
            XmlNamespace.Types,
            XmlElementNames.FirstDayOfWeek,
            this.firstDayOfWeek);
      }

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
      if (super.tryReadElementFromXml(reader)) {
        return true;
      } else {
        if (reader.getLocalName().equals(XmlElementNames.DaysOfWeek)) {

          this.getDaysOfTheWeek().loadFromXml(reader,
              reader.getLocalName());
          return true;
        } else if (reader.getLocalName().equals(XmlElementNames.FirstDayOfWeek)) {
          this.firstDayOfWeek = reader.
              readElementValue(Calendar.class,
                  XmlNamespace.Types,
                  XmlElementNames.FirstDayOfWeek);
          return true;
        } else {

          return false;
        }
      }
    }

    /**
     * Validates this instance.
     *
     * @throws Exception
     */
    @Override
    public void internalValidate() throws Exception {
      super.internalValidate();

      if (this.getDaysOfTheWeek().getCount() == 0) {
        throw new ServiceValidationException(
            "The recurrence pattern's property DaysOfTheWeek must contain at least one day of the week.");
      }
    }

    /**
     * Gets the list of the days of the week when occurrences happen.
     *
     * @return the days of the week
     */
    public DayOfTheWeekCollection getDaysOfTheWeek() {
      return this.daysOfTheWeek;
    }

    public Calendar getFirstDayOfWeek() throws ServiceValidationException {
      return this.getFieldValueOrThrowIfNull(Calendar.class,
          this.firstDayOfWeek, "FirstDayOfWeek");
    }

    public void setFirstDayOfWeek(Calendar value) {
      if (this.canSetFieldValue(this.firstDayOfWeek, value)) {
        this.firstDayOfWeek = value;
        this.changed();
      }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * microsoft.exchange.webservices.
     * ComplexPropertyChangedDelegateInterface#
     * complexPropertyChanged(microsoft.exchange.webservices.ComplexProperty
     * )
     */
    @Override
    public void complexPropertyChanged(ComplexProperty complexProperty) {
      this.daysOfTheWeekChanged(complexProperty);
    }

  }


  /**
   * Represents a regeneration pattern, as used with recurring tasks, where
   * each occurrence happens a specified number of weeks after the previous
   * one is completed.
   */
  public final static class WeeklyRegenerationPattern extends
      IntervalPattern {

    /**
     * Initializes a new instance of the WeeklyRegenerationPattern class.
     */
    public WeeklyRegenerationPattern() {

      super();
    }

    /**
     * Initializes a new instance of the WeeklyRegenerationPattern class.
     *
     * @param startDate the start date
     * @param interval  the interval
     * @throws ArgumentOutOfRangeException the argument out of range exception
     */
    public WeeklyRegenerationPattern(Date startDate, int interval)
        throws ArgumentOutOfRangeException {
      super(startDate, interval);

    }

    /**
     * Gets the name of the XML element. <value>The name of the XML
     * element.</value>
     *
     * @return the xml element name
     */
    @Override
    public String getXmlElementName() {
      return XmlElementNames.WeeklyRegeneration;
    }

    /**
     * Gets a value indicating whether this instance is regeneration
     * pattern. <value> <c>true</c> if this instance is regeneration
     * pattern; otherwise, <c>false</c>. </value>
     *
     * @return true, if is regeneration pattern
     */
    public boolean isRegenerationPattern() {
      return true;
    }
  }


  /**
   * Represents a recurrence pattern where each occurrence happens on a
   * specific day every year.
   */
  public final static class YearlyPattern extends Recurrence {

    /**
     * The month.
     */
    private Month month;

    /**
     * The day of month.
     */
    private Integer dayOfMonth;

    /**
     * Initializes a new instance of the YearlyPattern class.
     */
    public YearlyPattern() {
      super();

    }

    /**
     * Initializes a new instance of the YearlyPattern class.
     *
     * @param startDate  the start date
     * @param month      the month
     * @param dayOfMonth the day of month
     */
    public YearlyPattern(Date startDate, Month month, int dayOfMonth) {
      super(startDate);

      this.month = month;
      this.dayOfMonth = dayOfMonth;
    }

    /**
     * Gets the name of the XML element. <value>The name of the XML
     * element.</value>
     *
     * @return the xml element name
     */
    @Override
    public String getXmlElementName() {
      return XmlElementNames.AbsoluteYearlyRecurrence;
    }

    /**
     * Write property to XML.
     *
     * @param writer the writer
     * @throws Exception the exception
     */
    @Override
    public void internalWritePropertiesToXml(EwsServiceXmlWriter writer)
        throws Exception {
      super.internalWritePropertiesToXml(writer);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.DayOfMonth, this.getDayOfMonth());

      writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Month,
          this.getMonth());
    }

    /**
     * Tries to read element from XML.
     *
     * @param reader the reader
     * @return True if element was read
     * @throws Exception the exception
     */
    @Override
    public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
        throws Exception {
      if (super.tryReadElementFromXml(reader)) {
        return true;
      } else {
        if (reader.getLocalName().equals(XmlElementNames.DayOfMonth)) {

          this.dayOfMonth = reader.readElementValue(Integer.class);
          return true;
        } else if (reader.getLocalName().equals(XmlElementNames.Month)) {

          this.month = reader.readElementValue(Month.class);
          return true;
        } else {

          return false;
        }
      }
    }

    /**
     * Validates this instance.
     *
     * @throws Exception
     */
    @Override
    public void internalValidate() throws Exception {
      super.internalValidate();

      if (this.month == null) {
        throw new ServiceValidationException("The recurrence pattern's Month property must be specified.");
      }

      if (this.dayOfMonth == null) {
        throw new ServiceValidationException(
            "The recurrence pattern's DayOfMonth property must be specified.");
      }
    }

    /**
     * Gets the month of the year when each occurrence happens.
     *
     * @return the month
     * @throws ServiceValidationException the service validation exception
     */
    public Month getMonth() throws ServiceValidationException {
      return this.getFieldValueOrThrowIfNull(Month.class, this.month,
          "Month");
    }

    /**
     * Sets the month.
     *
     * @param value the new month
     */
    public void setMonth(Month value) {

      if (this.canSetFieldValue(this.month, value)) {
        this.month = value;
        this.changed();
      }
    }

    /**
     * Gets the day of the month when each occurrence happens. DayOfMonth
     * must be between 1 and 31.
     *
     * @return the day of month
     * @throws ServiceValidationException the service validation exception
     */
    public int getDayOfMonth() throws ServiceValidationException {

      return this.getFieldValueOrThrowIfNull(Integer.class, this.dayOfMonth,
          "DayOfMonth");

    }

    /**
     * Sets the day of the month when each occurrence happens. DayOfMonth
     * must be between 1 and 31.
     *
     * @param value the new day of month
     * @throws ArgumentOutOfRangeException the argument out of range exception
     */
    public void setDayOfMonth(int value)
        throws ArgumentOutOfRangeException {

      if (value < 1 || value > 31) {
        throw new ArgumentOutOfRangeException("DayOfMonth", "DayOfMonth must be between 1 and 31.");
      }

      if (this.canSetFieldValue(this.dayOfMonth, value)) {
        this.dayOfMonth = value;
        this.changed();
      }
    }
  }


  /**
   * Represents a regeneration pattern, as used with recurring tasks, where
   * each occurrence happens a specified number of years after the previous
   * one is completed.
   */
  public final static class YearlyRegenerationPattern extends
      IntervalPattern {

    /**
     * Gets the name of the XML element. <value>The name of the XML
     * element.</value>
     *
     * @return the xml element name
     */
    @Override
    public String getXmlElementName() {
      return XmlElementNames.YearlyRegeneration;
    }

    /**
     * Gets a value indicating whether this instance is regeneration
     * pattern.
     *
     * @return true, if is regeneration pattern
     */
    public boolean isRegenerationPattern() {
      return true;
    }

    /**
     * Initializes a new instance of the YearlyRegenerationPattern class.
     */
    public YearlyRegenerationPattern() {
      super();

    }

    /**
     * Initializes a new instance of the YearlyRegenerationPattern class.
     *
     * @param startDate the start date
     * @param interval  the interval
     * @throws ArgumentOutOfRangeException the argument out of range exception
     */
    public YearlyRegenerationPattern(Date startDate, int interval)
        throws ArgumentOutOfRangeException {
      super(startDate, interval);

    }
  }
}
