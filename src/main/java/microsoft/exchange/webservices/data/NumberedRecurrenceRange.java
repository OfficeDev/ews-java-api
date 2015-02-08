package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;
import java.util.Date;

/**
 * The Class NumberedRecurrenceRange.
 */
final class NumberedRecurrenceRange extends RecurrenceRange {

  /**
   * The number of occurrences.
   */
  private Integer numberOfOccurrences;

  /**
   * Initializes a new instance.
   */
  public NumberedRecurrenceRange() {
    super();
  }

  /**
   * Initializes a new instance.
   *
   * @param startDate           the start date
   * @param numberOfOccurrences the number of occurrences
   */
  public NumberedRecurrenceRange(Date startDate,
      Integer numberOfOccurrences) {
    super(startDate);
    this.numberOfOccurrences = numberOfOccurrences;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return The name of the XML element
   */
  protected String getXmlElementName() {
    return XmlElementNames.NumberedRecurrence;
  }

  /**
   * Setups the recurrence.
   *
   * @param recurrence the new up recurrence
   * @throws Exception the exception
   */
  protected void setupRecurrence(Recurrence recurrence) throws Exception {
    super.setupRecurrence(recurrence);
    recurrence.setNumberOfOccurrences(this.numberOfOccurrences);
  }

  /**
   * Writes the elements to XML..
   *
   * @param writer the writer
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlSerializationException    the service xml serialization exception
   */
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    super.writeElementsToXml(writer);

    if (this.numberOfOccurrences != null) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.NumberOfOccurrences,
          this.numberOfOccurrences);
    }
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
      if (reader.getLocalName().equals(
          XmlElementNames.NumberOfOccurrences)) {
        this.numberOfOccurrences = reader
            .readElementValue(Integer.class);
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * Gets the number of occurrences.
   *
   * @return numberOfOccurrences
   */

  public Integer getNumberOfOccurrences() {
    return this.numberOfOccurrences;
  }

  /**
   * sets the number of occurrences.
   *
   * @param value the new number of occurrences
   */
  public void setNumberOfOccurrences(Integer value) {
    this.canSetFieldValue(this.numberOfOccurrences, value);

  }

}
