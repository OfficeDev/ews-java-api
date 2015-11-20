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
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.TimeSpan;
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;

/**
 * Represents a time zone period as defined in the EWS schema.
 */
public class TimeZonePeriod extends ComplexProperty {

  /**
   * The Constant StandardPeriodId.
   */
  protected final static String StandardPeriodId = "Std";

  /**
   * The Constant StandardPeriodName.
   */
  protected final static String StandardPeriodName = "Standard";

  /**
   * The Constant DaylightPeriodId.
   */
  protected final static String DaylightPeriodId = "Dlt";

  /**
   * The Constant DaylightPeriodName.
   */
  protected final static String DaylightPeriodName = "Daylight";

  /**
   * The bias.
   */
  private TimeSpan bias;

  /**
   * The name.
   */
  private String name;

  /**
   * The id.
   */
  private String id;

  /**
   * Reads the attribute from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  public void readAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.id = reader.readAttributeValue(XmlAttributeNames.Id);
    this.name = reader.readAttributeValue(XmlAttributeNames.Name);
    this.bias = EwsUtilities.getXSDurationToTimeSpan(reader.readAttributeValue(XmlAttributeNames.Bias));
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
    writer.writeAttributeValue(XmlAttributeNames.Bias, EwsUtilities
        .getTimeSpanToXSDuration(this.bias));
    writer.writeAttributeValue(XmlAttributeNames.Name, this.name);
    writer.writeAttributeValue(XmlAttributeNames.Id, this.id);
  }

  /**
   * Loads from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader) throws Exception {
    this.loadFromXml(reader, XmlElementNames.Period);
  }

  /**
   * Writes to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  public void writeToXml(EwsServiceXmlWriter writer) throws Exception {
    this.writeToXml(writer, XmlElementNames.Period);
  }

  /**
   * Initializes a new instance of the TimeZonePeriod class.
   */
  public TimeZonePeriod() {
    super();
  }

  /**
   * Gets a value indicating whether this period represents the Standard
   * period.
   *
   * @return true if this instance is standard period; otherwise, false
   */
  protected boolean isStandardPeriod() {
    return this.name.equals(TimeZonePeriod.StandardPeriodName);
  }

  /**
   * Gets the bias to UTC associated with this period.
   *
   * @return the bias
   */
  protected TimeSpan getBias() {
    return bias;
  }

  /**
   * Sets the bias.
   *
   * @param bias the new bias
   */
  protected void setBias(TimeSpan bias) {
    this.bias = bias;
  }

  /**
   * Gets the name of this period.
   *
   * @return the name
   */
  protected String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  protected void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the id of this period.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  protected void setId(String id) {
    this.id = id;
  }

}
