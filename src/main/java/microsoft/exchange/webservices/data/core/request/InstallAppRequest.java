package microsoft.exchange.webservices.data.core.request;


import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.response.InstallAppResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;

/**
 * Created by yiyangli on 8/9/16.
 */
public final class InstallAppRequest extends SimpleServiceRequestBase<InstallAppResponse> {

  private InputStream manifestStream;
  private String marketplaceAssetId;
  private String marketplaceContentMarket;
  private boolean sendWelcomeEmail;

  /**
   * Initializes a new instance of the InstallAppRequest class.
   *
   * @param service the service.
   * @param manifestStream The manifest's plain text XML stream.
   * @param marketplaceAssetId The asset id of the addin in marketpalce
   * @param marketplaceContentMarket The target market for the content
   * @param sendWelcomeEmail Whether to send email on installation
   * @throws Exception the exception
   */
  public InstallAppRequest(ExchangeService service, InputStream manifestStream, String marketplaceAssetId, String marketplaceContentMarket, boolean sendWelcomeEmail) throws Exception{
    super(service);
    this.manifestStream = manifestStream;
    this.marketplaceAssetId = marketplaceAssetId;
    this.marketplaceContentMarket = marketplaceContentMarket;
    this.sendWelcomeEmail = sendWelcomeEmail;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  public String getXmlElementName() {
    return XmlElementNames.InstallAppRequest;
  }



  /**
   * Writes XML elements.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer) throws Exception {

    writer.writeStartElement(XmlNamespace.Messages, XmlElementNames.Manifest);

    writer.writeBase64ElementValue(manifestStream);

    writer.writeEndElement();

    if (!StringUtils.isEmpty(this.marketplaceAssetId))
    {
      writer.writeElementValue(XmlNamespace.Messages, XmlElementNames.MarketplaceAssetId, this.marketplaceAssetId);

      if (!StringUtils.isEmpty(this.marketplaceContentMarket))
      {
        writer.writeElementValue(XmlNamespace.Messages, XmlElementNames.MarketplaceContentMarket, this.marketplaceContentMarket);
      }

      writer.writeElementValue(XmlNamespace.Messages, XmlElementNames.SendWelcomeEmail, this.sendWelcomeEmail);
    }

  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  public String getResponseXmlElementName() {
    return XmlElementNames.InstallAppResponse;
  }


  @Override
  public InstallAppResponse parseResponse(EwsServiceXmlReader reader) throws Exception {
    InstallAppResponse response = new InstallAppResponse();
    response.loadFromXml(reader, XmlElementNames.InstallAppResponse);
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
  public InstallAppResponse execute() throws Exception{
    InstallAppResponse serviceResponse = this.internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }

}
