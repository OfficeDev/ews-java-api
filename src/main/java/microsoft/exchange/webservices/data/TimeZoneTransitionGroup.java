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

package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group of time zone period transitions.
 */
class TimeZoneTransitionGroup extends ComplexProperty {

  /**
   * The time zone definition.
   */
  private TimeZoneDefinition timeZoneDefinition;

  /**
   * The id.
   */
  private String id;

  /**
   * The transitions.
   */
  private List<TimeZoneTransition> transitions =
      new ArrayList<TimeZoneTransition>();

  /**
   * The transition to standard.
   */
  private TimeZoneTransition transitionToStandard;

  /**
   * The transition to daylight.
   */
  private TimeZoneTransition transitionToDaylight;

  /**
   * Loads from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  protected void loadFromXml(EwsServiceXmlReader reader) throws Exception {
    this.loadFromXml(reader, XmlElementNames.TransitionsGroup);
  }

  /**
   * Writes to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
    this.writeToXml(writer, XmlElementNames.TransitionsGroup);
  }

  /**
   * Reads the attributes from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  protected void readAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.id = reader.readAttributeValue(XmlAttributeNames.Id);
  }

  /**
   * Writes the attributes to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.Id, this.id);
  }

  /**
   * Writes the attributes to XML.
   *
   * @param reader the reader
   * @return true, if successful
   * @throws Exception the exception
   */
  @Override
  protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    reader.ensureCurrentNodeIsStartElement();

    TimeZoneTransition transition = TimeZoneTransition.create(
        this.timeZoneDefinition, reader.getLocalName());

    transition.loadFromXml(reader);

    EwsUtilities.EwsAssert(transition.getTargetPeriod() != null,
        "TimeZoneTransitionGroup.TryReadElementFromXml",
        "The transition's target period is null.");

    this.transitions.add(transition);

    return true;
  }

  /**
   * Writes elements to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    for (TimeZoneTransition transition : this.transitions) {
      transition.writeToXml(writer);
    }
  }

  /**
   * Validates this transition group.
   *
   * @throws microsoft.exchange.webservices.data.ServiceLocalException the service local exception
   */
  public void validate() throws ServiceLocalException {
    // There must be exactly one or two transitions in the group.
    if (this.transitions.size() < 1 || this.transitions.size() > 2) {
      throw new ServiceLocalException(
          Strings.InvalidOrUnsupportedTimeZoneDefinition);
    }

    // If there is only one transition, it must be of type
    // TimeZoneTransition
    if (this.transitions.size() == 1
        && !(this.transitions.get(0).getClass() ==
        TimeZoneTransition.class)) {
      throw new ServiceLocalException(
          Strings.InvalidOrUnsupportedTimeZoneDefinition);
    }

    // If there are two transitions, none of them should be of type
    // TimeZoneTransition
    if (this.transitions.size() == 2) {
      for (TimeZoneTransition transition : this.transitions) {
        if (transition.getClass() == TimeZoneTransition.class) {
          throw new ServiceLocalException(
              Strings.InvalidOrUnsupportedTimeZoneDefinition);
        }
      }
    }

    // All the transitions in the group must be to a period.
    for (TimeZoneTransition transition : this.transitions) {
      if (transition.getTargetPeriod() == null) {
        throw new ServiceLocalException(
            Strings.InvalidOrUnsupportedTimeZoneDefinition);
      }
    }
  }

  /**
   * The Class CustomTimeZoneCreateParams.
   */
  protected static class CustomTimeZoneCreateParams {

    /**
     * The base offset to utc.
     */
    private TimeSpan baseOffsetToUtc;

    /**
     * The standard display name.
     */
    private String standardDisplayName;

    /**
     * The daylight display name.
     */
    private String daylightDisplayName;

    /**
     * Initializes a new instance of the class.
     */
    protected CustomTimeZoneCreateParams() {
    }

    /**
     * Gets  the base offset to UTC.
     *
     * @return the base offset to utc
     */
    protected TimeSpan getBaseOffsetToUtc() {
      return this.baseOffsetToUtc;
    }

    /**
     * Sets the base offset to utc.
     *
     * @param baseOffsetToUtc the new base offset to utc
     */
    protected void setBaseOffsetToUtc(TimeSpan baseOffsetToUtc) {
      this.baseOffsetToUtc = baseOffsetToUtc;
    }

    /**
     * Gets the display name of the standard period.
     *
     * @return the standard display name
     */
    protected String getStandardDisplayName() {
      return this.standardDisplayName;
    }

    /**
     * Sets the standard display name.
     *
     * @param standardDisplayName the new standard display name
     */
    protected void setStandardDisplayName(String standardDisplayName) {
      this.standardDisplayName = standardDisplayName;
    }

    /**
     * Gets the display name of the daylight period.
     *
     * @return the daylight display name
     */
    protected String getDaylightDisplayName() {
      return this.daylightDisplayName;
    }

    /**
     * Sets the daylight display name.
     *
     * @param daylightDisplayName the new daylight display name
     */
    protected void setDaylightDisplayName(String daylightDisplayName) {
      this.daylightDisplayName = daylightDisplayName;
    }

    /**
     * Gets a value indicating whether the custom time zone should have a
     * daylight period. <value> <c>true</c> if the custom time zone should
     * have a daylight period; otherwise, <c>false</c>. </value>
     *
     * @return the checks for daylight period
     */
    protected boolean getHasDaylightPeriod() {
      return (!(this.daylightDisplayName == null ||
          this.daylightDisplayName.isEmpty()));
    }
  }

  /**
   * Gets a value indicating whether this group contains a transition to the
   * Daylight period. <value><c>true</c> if this group contains a transition
   * to daylight; otherwise, <c>false</c>.</value>
   *
   * @return the supports daylight
   */
  protected boolean getSupportsDaylight() {
    return this.transitions.size() == 2;
  }

  /**
   * Gets the offset to UTC based on this group's transitions.
   *
   * @return the custom time zone creation params
   */
  protected CustomTimeZoneCreateParams getCustomTimeZoneCreationParams() {
    CustomTimeZoneCreateParams result = new CustomTimeZoneCreateParams();

    if (this.transitionToDaylight != null) {
      result.setDaylightDisplayName(this.transitionToDaylight
          .getTargetPeriod().getName());
    }

    result.setStandardDisplayName(this.transitionToStandard
        .getTargetPeriod().getName());

    // Assume that the standard period's offset is the base offset to UTC.
    // EWS returns a positive offset for time zones that are behind UTC, and
    // a negative one for time zones ahead of UTC. TimeZoneInfo does it the
    // other
    // way around.
    // result.BaseOffsetToUtc =
    // -this.TransitionToStandard.TargetPeriod.Bias;

    return result;
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param timeZoneDefinition the time zone definition
   */
  protected TimeZoneTransitionGroup(TimeZoneDefinition timeZoneDefinition) {
    super();
    this.timeZoneDefinition = timeZoneDefinition;
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param timeZoneDefinition the time zone definition
   * @param id                 the id
   */
  protected TimeZoneTransitionGroup(TimeZoneDefinition timeZoneDefinition,
      String id) {
    this(timeZoneDefinition);
    this.id = id;
  }

  /**
   * Gets the id of this group.
   *
   * @return the id
   */
  protected String getId() {
    return this.id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets the transitions in this group.
   *
   * @return the transitions
   */
  protected List<TimeZoneTransition> getTransitions() {
    return this.transitions;
  }
}
