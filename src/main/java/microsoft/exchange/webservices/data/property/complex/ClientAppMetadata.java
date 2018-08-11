package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

/**
 * Created by yiyangli on 8/9/16.
 */
public class ClientAppMetadata extends ComplexProperty {

  private String endNodeUrl;
  private String actionUrl;
  private String appStatus;

  public ClientAppMetadata() {
    this.setNamespace(XmlNamespace.Types);
  }

  public String getEndNodeUrl() {
    return endNodeUrl;
  }

  public void setEndNodeUrl(String endNodeUrl) {
    this.endNodeUrl = endNodeUrl;
  }

  public String getActionUrl() {
    return actionUrl;
  }

  public void setActionUrl(String actionUrl) {
    this.actionUrl = actionUrl;
  }

  public String getAppStatus() {
    return appStatus;
  }

  public void setAppStatus(String appStatus) {
    this.appStatus = appStatus;
  }

  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader) throws Exception {
    String localName = reader.getLocalName();
    if (localName.equals(XmlElementNames.EndNodeUrl)) {
      this.setEndNodeUrl(reader.readElementValue(String.class));
      return true;
    }
    if (localName.equals(XmlElementNames.ActionUrl)) {
      this.setActionUrl(reader.readElementValue(String.class));
      return true;
    }
    if (localName.equals(XmlElementNames.AppStatus)) {
      this.setActionUrl(reader.readElementValue(String.class));
      return true;
    }
    return false;
  }
}
