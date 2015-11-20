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

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

/**
 * Represents the Id of an occurrence of a recurring appointment.
 */
public final class AppointmentOccurrenceId extends ItemId {

  /**
   * Index of the occurrence.
   */
  private int occurrenceIndex;

  /**
   * Initializes a new instance.
   *
   * @param recurringMasterUniqueId the recurring master unique id
   * @param occurrenceIndex         the occurrence index
   * @throws Exception the exception
   */
  public AppointmentOccurrenceId(String recurringMasterUniqueId,
      int occurrenceIndex) throws Exception {
    super(recurringMasterUniqueId);
    this.occurrenceIndex = occurrenceIndex;
  }

  /**
   * Gets  the index of the occurrence. Note that the occurrence index
   * starts at one not zero.
   *
   * @return the occurrence index
   */
  public int getOccurrenceIndex() {
    return occurrenceIndex;
  }

  /**
   * Sets the occurrence index.
   *
   * @param occurrenceIndex the new occurrence index
   */
  public void setOccurrenceIndex(int occurrenceIndex) {
    if (occurrenceIndex < 1) {
      throw new IllegalArgumentException("OccurrenceIndex must be greater than 0.");
    }
    this.occurrenceIndex = occurrenceIndex;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  @Override
  public String getXmlElementName() {
    return XmlElementNames.OccurrenceItemId;
  }

  /**
   * Gets the name of the XML element.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  public void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.RecurringMasterId, this
        .getUniqueId());
    writer.writeAttributeValue(XmlAttributeNames.InstanceIndex, this
        .getOccurrenceIndex());
  }

}
