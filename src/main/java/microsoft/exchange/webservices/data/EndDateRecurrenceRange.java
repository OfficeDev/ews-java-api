/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents recurrent range with an end date.
 */
final class EndDateRecurrenceRange extends RecurrenceRange {

  /**
   * The end date.
   */
  private Date endDate;

  /**
   * Initializes a new instance.
   */
  public EndDateRecurrenceRange() {
    super();
  }

  /**
   * Initializes a new instance.
   *
   * @param startDate the start date
   * @param endDate   the end date
   */
  public EndDateRecurrenceRange(Date startDate, Date endDate) {
    super(startDate);
    this.endDate = endDate;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return The name of the XML element
   */
  protected String getXmlElementName() {
    return XmlElementNames.EndDateRecurrence;
  }

  /**
   * Setups the recurrence.
   *
   * @param recurrence the new up recurrence
   * @throws Exception the exception
   */
  protected void setupRecurrence(Recurrence recurrence) throws Exception {
    super.setupRecurrence(recurrence);
    recurrence.setEndDate(this.endDate);
  }

  /**
   * Writes the elements to XML.
   *
   * @param writer the writer
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlSerializationException    the service xml serialization exception
   */
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    Date d = this.endDate;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedString = df.format(d);

    super.writeElementsToXml(writer);

    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.EndDate,
        formattedString);
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader the reader
   * @return True if element was read
   * @throws Exception the exception
   */
  protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (super.tryReadElementFromXml(reader)) {
      return true;
    } else {
      if (reader.getLocalName().equals(XmlElementNames.EndDate)) {

        Date temp = reader.readElementValueAsUnspecifiedDate();

        if (temp != null) {
          this.endDate = temp;
        }
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * Gets the end date.
   *
   * @return endDate
   */
  public Date getEndDate() {
    return this.endDate;
  }

  /**
   * sets the end date.
   *
   * @param value the new end date
   */
  public void setEndDate(Date value) {
    this.canSetFieldValue(this.endDate, value);
  }

}
