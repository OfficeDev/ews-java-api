package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Represents a suggestion for a specific date.
 */
public final class Suggestion extends ComplexProperty {

  /**
   * The date.
   */
  private Date date;

  /**
   * The quality.
   */
  private SuggestionQuality quality;

  /**
   * The time suggestions.
   */
  private Collection<TimeSuggestion> timeSuggestions =
      new ArrayList<TimeSuggestion>();

  /**
   * Initializes a new instance of the Suggestion class.
   */
  protected Suggestion() {
    super();
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader the reader
   * @return True if appropriate element was read.
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlDeserializationException  the service xml deserialization exception
   * @throws Exception                           the exception
   */
  @Override
  protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws XMLStreamException, ServiceXmlDeserializationException,
      Exception {
    if (reader.getLocalName().equals(XmlElementNames.Date)) {
      SimpleDateFormat sdfin = new SimpleDateFormat(
          "yyyy-MM-dd'T'HH:mm:ss");
      Date tempDate = sdfin.parse(reader.readElementValue());
      this.date = tempDate;
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.DayQuality)) {
      this.quality = reader.readElementValue(SuggestionQuality.class);
      return true;
    } else if (reader.getLocalName()
        .equals(XmlElementNames.SuggestionArray)) {
      if (!reader.isEmptyElement()) {
        do {
          reader.read();

          if (reader.isStartElement(XmlNamespace.Types,
              XmlElementNames.Suggestion)) {
            TimeSuggestion timeSuggestion = new TimeSuggestion();

            timeSuggestion.loadFromXml(reader, reader
                .getLocalName());

            this.timeSuggestions.add(timeSuggestion);
          }
        } while (!reader.isEndElement(XmlNamespace.Types,
            XmlElementNames.SuggestionArray));
      }

      return true;
    } else {
      return false;
    }

  }

  /**
   * Gets the date and time of the suggestion.
   *
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * Gets the quality of the suggestion.
   *
   * @return the quality
   */
  public SuggestionQuality getQuality() {
    return quality;
  }

  /**
   * Gets a collection of suggested times within the suggested day.
   *
   * @return the time suggestions
   */
  public Collection<TimeSuggestion> getTimeSuggestions() {
    return timeSuggestions;
  }

}
