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

package microsoft.exchange.webservices.data.property.complex.time;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the base class for all time zone transitions.
 */
public class TimeZoneTransition extends ComplexProperty {

  /**
   * The Period target.
   */
  private final String PeriodTarget = "Period";

  /**
   * The Group target.
   */
  private final String GroupTarget = "Group";

  /**
   * The time zone definition.
   */
  private TimeZoneDefinition timeZoneDefinition;

  /**
   * The target period.
   */
  private TimeZonePeriod targetPeriod;

  /**
   * The target group.
   */
  private TimeZoneTransitionGroup targetGroup;

  /**
   * Creates a time zone period transition of the appropriate type given an
   * XML element name.
   *
   * @param timeZoneDefinition the time zone definition
   * @param xmlElementName     the xml element name
   * @return A TimeZonePeriodTransition instance.
   * @throws ServiceLocalException the service local exception
   */
  public static TimeZoneTransition create(TimeZoneDefinition timeZoneDefinition, String xmlElementName)
      throws ServiceLocalException {
    if (xmlElementName.equals(XmlElementNames.AbsoluteDateTransition)) {
      return new AbsoluteDateTransition(timeZoneDefinition);
    } else if (xmlElementName
        .equals(XmlElementNames.AbsoluteDateTransition)) {
      return new AbsoluteDateTransition(timeZoneDefinition);
    } else if (xmlElementName
        .equals(XmlElementNames.RecurringDayTransition)) {
      return new RelativeDayOfMonthTransition(timeZoneDefinition);
    } else if (xmlElementName
        .equals(XmlElementNames.RecurringDateTransition)) {
      return new AbsoluteDayOfMonthTransition(timeZoneDefinition);
    } else if (xmlElementName.equals(XmlElementNames.Transition)) {
      return new TimeZoneTransition(timeZoneDefinition);
    } else {
      throw new ServiceLocalException(String
          .format("Unknown time zone transition type: %s",
              xmlElementName));
    }
  }

  /**
   * Gets the XML element name associated with the transition.
   *
   * @return The XML element name associated with the transition.
   */
  protected String getXmlElementName() {
    return XmlElementNames.Transition;
  }

  /**
   * Tries to read element from XML.The reader.
   *
   * @param reader The
   *               reader.
   * @return True if element was read.
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equals(XmlElementNames.To)) {
      String targetKind = reader
          .readAttributeValue(XmlAttributeNames.Kind);
      String targetId = reader.readElementValue();
      if (targetKind.equals(PeriodTarget)) {
        if (!this.timeZoneDefinition.getPeriods().containsKey(targetId)) {
         
          throw new ServiceLocalException(String.format(
              "Invalid transition. A period with the specified Id couldn't be found: %s", targetId));
        } else {
        	 this.targetPeriod = this.timeZoneDefinition.getPeriods()
                     .get(targetId);
        }
      } else if (targetKind.equals(GroupTarget)) {
        if (!this.timeZoneDefinition.getTransitionGroups().containsKey(
            targetId)) {
         
          throw new ServiceLocalException(String.format(
              "Invalid transition. A transition group with the specified ID couldn't be found: %s", targetId));
        } else {
        	 this.targetGroup = this.timeZoneDefinition
                     .getTransitionGroups().get(targetId);
        }
      } else {
        throw new ServiceLocalException("The time zone transition target isn't supported.");
      }

      return true;
    } else {
      return false;
    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   * @throws XMLStreamException the XML stream exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException, XMLStreamException {
    writer.writeStartElement(XmlNamespace.Types, XmlElementNames.To);

    if (this.targetPeriod != null) {
      writer.writeAttributeValue(XmlAttributeNames.Kind, PeriodTarget);
      writer.writeValue(this.targetPeriod.getId(), XmlElementNames.To);
    } else if (this.targetGroup != null) {
      writer.writeAttributeValue(XmlAttributeNames.Kind, GroupTarget);
      writer.writeValue(this.targetGroup.getId(), XmlElementNames.To);
    }

    writer.writeEndElement(); // To
  }

  /**
   * Loads from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader) throws Exception {
    this.loadFromXml(reader, this.getXmlElementName());
  }

  /**
   * Writes to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  public void writeToXml(EwsServiceXmlWriter writer) throws Exception {
    this.writeToXml(writer, this.getXmlElementName());
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param timeZoneDefinition the time zone definition
   */
  protected TimeZoneTransition(TimeZoneDefinition timeZoneDefinition) {
    super();
    this.timeZoneDefinition = timeZoneDefinition;
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param timeZoneDefinition the time zone definition
   * @param targetGroup        the target group
   */
  protected TimeZoneTransition(TimeZoneDefinition timeZoneDefinition,
      TimeZoneTransitionGroup targetGroup) {
    this(timeZoneDefinition);
    this.targetGroup = targetGroup;
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param timeZoneDefinition the time zone definition
   * @param targetPeriod       the target period
   */
  public TimeZoneTransition(TimeZoneDefinition timeZoneDefinition, TimeZonePeriod targetPeriod) {
    this(timeZoneDefinition);
    this.targetPeriod = targetPeriod;
  }

  /**
   * Gets the target period of the transition.
   *
   * @return the target period
   */
  protected TimeZonePeriod getTargetPeriod() {
    return this.targetPeriod;
  }

  /**
   * Gets the target transition group of the transition.
   *
   * @return the target group
   */
  public TimeZoneTransitionGroup getTargetGroup() {
    return this.targetGroup;
  }

}
