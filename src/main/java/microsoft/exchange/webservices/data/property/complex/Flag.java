package microsoft.exchange.webservices.data.property.complex;

import java.util.Date;
import javax.xml.stream.XMLStreamException;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.enumeration.service.ConversationFlagStatus;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

public class Flag extends ComplexProperty {
  private ConversationFlagStatus flagStatus;
  private Date startDate;
  private Date dueDate;
  private Date completeDate;

  public Flag() {
  }
  
  public ConversationFlagStatus getStatus () {
    return this.flagStatus;
  }
  
  public void setStatus (ConversationFlagStatus flagStatus) {
    this.flagStatus = flagStatus;
  }
  
  public Date getStartDate () {
    return this.startDate;
  }
  
  public void setStartDate (Date startDate) {
    this.startDate = startDate;
  }
  
  public Date getDueDate () {
    return this.dueDate;
  }
  
  public void setDueDate (Date dueDate) {
    this.dueDate = dueDate;
  }
  
  public Date getCompleteDate () {
    return this.completeDate;
  }
  
  public void setCompleteDate (Date completeDate) {
    this.completeDate = completeDate;
  }

  /**
  * Tries to read element from XML 
  * @param reader The reader
  * @returns true if element was read
  **/
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader) throws Exception {
    try {
      String localName = reader.getLocalName();
      if (localName.equalsIgnoreCase(XmlElementNames.FlagStatus)) {
        this.flagStatus = reader.readElementValue(ConversationFlagStatus.class);
        return true;
      } else if (localName.equalsIgnoreCase(XmlElementNames.StartDate)) {
        this.startDate = reader.readElementValueAsDateTime();
        return true;
      } else if (localName.equalsIgnoreCase(XmlElementNames.DueDate)) {
        this.dueDate = reader.readElementValueAsDateTime();
        return true;
      } else if (localName.equalsIgnoreCase(XmlElementNames.CompleteDate)) {
        this.completeDate = reader.readElementValueAsDateTime();
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }      
  }

  /**
  * Writes elements to XML
  * @param writer The writer
  **/
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer) throws ServiceXmlSerializationException, XMLStreamException {
      writer.writeElementValue(XmlNamespace.Types, XmlElementNames.FlagStatus, this.flagStatus);

      if (this.flagStatus == ConversationFlagStatus.Flagged) {
          writer.writeElementValue(XmlNamespace.Types, XmlElementNames.StartDate, this.startDate);
          writer.writeElementValue(XmlNamespace.Types, XmlElementNames.DueDate, this.dueDate);
      } else if (this.flagStatus == ConversationFlagStatus.Complete) {
          writer.writeElementValue(XmlNamespace.Types, XmlElementNames.CompleteDate, this.completeDate);
      }
  }

  /**
  * Validates this instance. 
  * @throws Exception
  **/
  public void Validate() throws Exception {
      EwsUtilities.validateParam(this.flagStatus, "FlagStatus");
  }
  
}
