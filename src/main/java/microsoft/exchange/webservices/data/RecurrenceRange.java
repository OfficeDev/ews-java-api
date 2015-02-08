package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents recurrence range with start and end dates.
 */
abstract class RecurrenceRange extends ComplexProperty {

  /**
   * The start date.
   */
  private Date startDate;

  /**
   * The recurrence.
   */
  private Recurrence recurrence;

  /**
   * Initializes a new instance.
   */
  protected RecurrenceRange() {
    super();
  }

  /**
   * Initializes a new instance.
   *
   * @param startDate the start date
   */
  protected RecurrenceRange(Date startDate) {
    this();
    this.startDate = startDate;
  }

  /**
   * Changes handler.
   */
  protected void changed() {
    if (this.recurrence != null) {
      this.recurrence.changed();
    }
  }

  /**
   * Setup the recurrence.
   *
   * @param recurrence the new up recurrence
   * @throws InstantiationException     the instantiation exception
   * @throws IllegalAccessException     the illegal access exception
   * @throws ServiceValidationException the service validation exception
   * @throws Exception                  the exception
   */
  protected void setupRecurrence(Recurrence recurrence)
      throws InstantiationException, IllegalAccessException,
      ServiceValidationException, Exception {
    recurrence.setStartDate(this.getStartDate());
  }

  /**
   * Writes elements to XML..
   *
   * @param writer the writer
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlSerializationException    the service xml serialization exception
   */
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    Date d = this.startDate;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedString = df.format(d);

    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.StartDate,
        formattedString);
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader the reader
   * @return True if element was read
   * @throws ServiceXmlDeserializationException  the service xml deserialization exception
   * @throws InstantiationException              the instantiation exception
   * @throws IllegalAccessException              the illegal access exception
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws java.text.ParseException            the parse exception
   * @throws Exception                           the exception
   */
  protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws ServiceXmlDeserializationException, InstantiationException,
      IllegalAccessException, XMLStreamException, ParseException,
      Exception {
    if (reader.getLocalName().equals(XmlElementNames.StartDate)) {
      //this.startDate = reader.readElementValueAsDateTime();
      Date startDate = reader.readElementValueAsUnspecifiedDate();
      if (startDate != null) {
        this.startDate = startDate;
        return true;
      }
      return false;
    } else {
      return false;
    }
  }

  /**
   * Gets the name of the XML element.
   *
   * @return recurrence
   */
  protected abstract String getXmlElementName();

  /**
   * Gets or sets the recurrence.
   *
   * @return recurrence
   */
  protected Recurrence getRecurrence() {
    return this.recurrence;
  }

  /**
   * Sets the recurrence.
   *
   * @param value the new recurrence
   */
  protected void setRecurrence(Recurrence value) {
    this.recurrence = value;
  }

  /**
   * Gets the start date.
   *
   * @return startDate
   */
  protected Date getStartDate() {
    return this.startDate;

  }

  /**
   * Sets the start date.
   *
   * @param value the new start date
   */
  protected void setStartDate(Date value) {
    this.canSetFieldValue(this.startDate, value);
  }

}
