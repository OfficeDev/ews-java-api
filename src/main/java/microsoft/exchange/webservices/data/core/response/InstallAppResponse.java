package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.enumeration.misc.error.ServiceError;

/**
 * Created by yiyangli on 8/9/16.
 */
public class InstallAppResponse extends ServiceResponse {

  private boolean wasFirstInstall;

  /*
  * Initializes a new instance of the InstallAppResponse class.
  * */
  public InstallAppResponse() {
    super();
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  public void readElementsFromXml(EwsServiceXmlReader reader) throws Exception {
    super.readElementsFromXml(reader);

    if (this.getErrorCode().equals(ServiceError.NoError) && reader.isStartElement(XmlNamespace.NotSpecified, XmlElementNames.WasFirstInstall)) {
      this.wasFirstInstall = reader.readElementValue(boolean.class, XmlNamespace.NotSpecified, XmlElementNames.WasFirstInstall);
    }
  }

  public boolean isWasFirstInstall() {
    return this.wasFirstInstall;
  }
}