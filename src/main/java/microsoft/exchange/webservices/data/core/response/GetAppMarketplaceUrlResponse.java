package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

/**
 * Created by yiyangli on 8/9/16.
 */
public class GetAppMarketplaceUrlResponse extends ServiceResponse {

  private String appMarketplaceUrl;

  public String getAppMarketplaceUrl() {
    return appMarketplaceUrl;
  }

  public GetAppMarketplaceUrlResponse() {
    super();
  }

  @Override
  public void readElementsFromXml(EwsServiceXmlReader reader) throws Exception {
    super.readElementsFromXml(reader);
    this.appMarketplaceUrl = reader.readElementValue(String.class, XmlNamespace.NotSpecified, XmlElementNames.AppMarketplaceUrl);
  }
}
