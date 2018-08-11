package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.response.GetAppMarketplaceUrlResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by yiyangli on 8/9/16.
 */
public class GetAppMarketplaceUrlRequest extends SimpleServiceRequestBase<GetAppMarketplaceUrlResponse>  {

  private String apiVersionSupported;
  private String schemaVersionSupported;

  /**
   * Initializes a new instance of the GetAppManifestsRequest class.
   *
   * @param service the service
   * @throws Exception
   */
  public GetAppMarketplaceUrlRequest(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Gets the api version supported by the client.
   *
   * @return the proxy
   */
  public String getApiVersionSupported() {
    return apiVersionSupported;
  }


  /**
   * Sets the api version supported by the client.
   *
   * @param apiVersionSupported The Api Version supported by the client.
   */
  public void setApiVersionSupported(String apiVersionSupported) {
    this.apiVersionSupported = apiVersionSupported;
  }

  /**
   * Gets the Schema version supported by the client.
   *
   * @return the proxy
   */
  public String getSchemaVersionSupported() {
    return schemaVersionSupported;
  }

  /**
   * Sets the schema version supported by the client.
   *
   * @param schemaVersionSupported The Schema Version supported by the client.
   */
  public void setSchemaVersionSupported(String schemaVersionSupported) {
    this.schemaVersionSupported = schemaVersionSupported;
  }

  /**
   * Validate request.
   */
  @Override
  public void validate() throws Exception {
    super.validate();
    EwsUtilities.validateNonBlankStringParamAllowNull(this.apiVersionSupported, "apiVersionSupported");
    EwsUtilities.validateNonBlankStringParamAllowNull(this.schemaVersionSupported, "schemaVersionSupported");
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  public String getXmlElementName() {
    return XmlElementNames.GetAppMarketplaceUrlRequest;
  }

  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer) throws Exception {
    if (!StringUtils.isEmpty(this.apiVersionSupported)) {
      writer.writeElementValue(XmlNamespace.Messages, XmlElementNames.ApiVersionSupported, this.apiVersionSupported);
    }

    if (!StringUtils.isEmpty(this.schemaVersionSupported)) {
      writer.writeElementValue(XmlNamespace.Messages, XmlElementNames.SchemaVersionSupported, this.schemaVersionSupported);
    }
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  public String getResponseXmlElementName() {
    return XmlElementNames.GetAppMarketplaceUrlResponse;
  }

  @Override
  public GetAppMarketplaceUrlResponse parseResponse(EwsServiceXmlReader reader) throws Exception {
    GetAppMarketplaceUrlResponse response = new GetAppMarketplaceUrlResponse();
    response.loadFromXml(reader, XmlElementNames.GetAppMarketplaceUrlResponse);
    return response;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  public ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2013;
  }

  /**
   * Executes this request.
   *
   * @return Service response.
   * @throws Exception the exception
   */
  public GetAppMarketplaceUrlResponse execute() throws Exception{
    GetAppMarketplaceUrlResponse serviceResponse = this.internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }
}
