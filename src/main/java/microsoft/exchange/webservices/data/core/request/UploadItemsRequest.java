package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.data.core.*;
import microsoft.exchange.webservices.data.core.enumeration.misc.*;
import microsoft.exchange.webservices.data.core.enumeration.service.error.*;
import microsoft.exchange.webservices.data.core.response.*;
import microsoft.exchange.webservices.data.core.service.item.*;

import java.util.*;

public class UploadItemsRequest extends MultiResponseServiceRequest<UploadItemsResponse> {

  private List<UploadItem> items;

  /**
   * The item ids.
   */

  /**
   * Initializes a new instance of the class.
   *
   * @param service           the service
   * @param errorHandlingMode the error handling mode
   * @throws Exception
   */
  public UploadItemsRequest(ExchangeService service, ServiceErrorHandling errorHandlingMode)
          throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Validate request.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParam(items, "items");
    for (UploadItem i : items) {
      i.validate();
    }
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return items.size();
  }

  /**
   * Creates the service response.
   *
   * @param service       the service
   * @param responseIndex the response index
   * @return Service response.
   */
  @Override
  protected UploadItemsResponse createServiceResponse(ExchangeService service,
                                                      int responseIndex) {
    return new UploadItemsResponse();
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  @Override
  public String getXmlElementName() {
    return XmlElementNames.UploadItems;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.UploadItemsResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.UploadItemsResponseMessage;
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
          throws Exception {
    if (!items.isEmpty()) {
      writer.writeStartElement(XmlNamespace.Messages, XmlElementNames.Items);
      items.stream().forEach(item -> item.writeToXml(writer));
      writer.writeEndElement();
    }
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2010_SP1;
  }

  public void setItems(List<UploadItem> items) {
    this.items = items;
  }

  public List<UploadItem> getItems() {
    return items;
  }
}
