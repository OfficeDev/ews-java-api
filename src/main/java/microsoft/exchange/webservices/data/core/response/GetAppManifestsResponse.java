package microsoft.exchange.webservices.data.core.response;

import static microsoft.exchange.webservices.data.core.XmlElementNames.Apps;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.property.complex.ClientApp;
import microsoft.exchange.webservices.data.security.XmlNodeType;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiyangli on 8/9/16.
 */
public class GetAppManifestsResponse extends ServiceResponse {

  public static final String UnexpectedElement = "An element node '{0}:{1}' of the type {2} was expected, but node '{3}' of type {4} was found.";

  private List<InputStream> manifests = new ArrayList<InputStream>();

  private List<ClientApp> apps = new ArrayList<ClientApp>();

  /*
  * Initializes a new instance of the GetAppManifestsResponse class.
  * */
  public GetAppManifestsResponse() {
    super();
  }

  public List<InputStream> getManifests() {
    return manifests;
  }

  public List<ClientApp> getApps() {
    return apps;
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  public void readElementsFromXml(EwsServiceXmlReader reader) throws Exception {
    this.manifests.clear();
    super.readElementsFromXml(reader);

    reader.read();

    // We can have a response from Exchange 2013 (first time this API was introduced)
    // or the newer response, starting in Exchange 2013 SP1, (X-EWS-TargetVersion: 2.5 or above)
    boolean exchange2013Response;
    if (reader.getLocalName().equals(XmlElementNames.Manifests)) {
      exchange2013Response = true;
    } else if (reader.getLocalName().equals(Apps)) {
      exchange2013Response = false;
    } else {
      throw new ServiceXmlDeserializationException(
          MessageFormat.format(
              UnexpectedElement,
              EwsUtilities.getNamespacePrefix(XmlNamespace.Messages),
              XmlElementNames.Manifests,
              XmlNodeType.START_ELEMENT,
              reader.getLocalName(),
              reader.getNodeType()));
    }

    if (!reader.isEmptyElement())
    {
      // Because we don't have an element for count of returned object,
      // we have to test the element to determine if it is StartElement of return object or EndElement
      reader.read();

      if (exchange2013Response)
      {
        this.readFromExchange2013(reader);
      }
      else
      {
        this.readFromExchange2013Sp1(reader);
      }
    }
  }

  /*
  * Read the response from Exchange 2013.
  * This method assumes that the reader is currently at the Manifests element.
  * @param reader The reader
  * */
  private void readFromExchange2013(EwsServiceXmlReader reader) throws Exception {
    ////<GetAppManifestsResponse ResponseClass="Success" xmlns="http://schemas.microsoft.com/exchange/services/2006/messages">
    ////<ResponseCode>NoError</ResponseCode>
    ////<m:Manifests xmlns:m="http://schemas.microsoft.com/exchange/services/2006/messages">
    ////<m:Manifest>[base 64 encoded manifest]</m:Manifest>                              <--- reader should be at this node at the beginning of loop
    ////<m:Manifest>[base 64 encoded manifest]</m:Manifest>
    //// ....
    ////</m:Manifests>                                                                   <--- reader should be at this node at the end of the loop
    while (reader.isStartElement(XmlNamespace.Messages, XmlElementNames.Manifest))
    {
      InputStream manifest = ClientApp.readToXmlDocument(reader);
      this.manifests.add(manifest);
      ClientApp clientApp = new ClientApp();
      clientApp.setManifest(manifest);
      this.apps.add(clientApp);
    }
  }

  /*
  * Read the response from Exchange 2013 sp1.
  * This method assumes that the reader is currently at the Manifests element.
  * @param reader The reader
  * */
  private void readFromExchange2013Sp1(EwsServiceXmlReader reader) throws Exception {
    ////<GetAppManifestsResponse ResponseClass="Success" xmlns="http://schemas.microsoft.com/exchange/services/2006/messages">
    ////  <ResponseCode>NoError</ResponseCode>
    ////  <m:Apps xmlns:m="http://schemas.microsoft.com/exchange/services/2006/messages">
    ////    <t:App xmlns:t="http://schemas.microsoft.com/exchange/services/2006/types">       <--- reader should be at this node at the beginning of the loop
    ////      <t:Metadata>
    ////        <t:EndNodeUrl>http://o15.officeredir.microsoft.com/r/rlidMktplcExchRedirect?app=outlook.exe&amp;ver=15&amp;clid=1033&amp;p1=15d0d766d0&amp;p2=4&amp;p3=0&amp;p4=WA&amp;p5=en-US\WA102996382&amp;Scope=2&amp;CallBackURL=https%3a%2f%2fexhv-4880%2fecp%2fExtension%2finstallFromURL.slab%3fexsvurl%3d1&amp;DeployId=EXHV-4680dom.extest.microsoft.com</t:EndNodeUrl>
    ////        <t:AppStatus>2.3</t:AppStatus>
    ////        <t:ActionUrl>http://o15.officeredir.microsoft.com/r/rlidMktplcExchRedirect?app=outlook.exe&amp;ver=15&amp;clid=1033&amp;p1=15d0d766d0&amp;p2=4&amp;p3=0&amp;p4=WA&amp;p5=en-US\WA102996382&amp;Scope=2&amp;CallBackURL=https%3a%2f%2fexhv-4880%2fecp%2fExtension%2finstallFromURL.slab%3fexsvurl%3d1&amp;DeployId=EXHV-4680dom.extest.microsoft.com</t:ActionUrl>
    ////      </t:Metadata>
    ////      <t:Manifest>[base 64 encoded manifest]</t:Manifest>
    ////    </t:App>
    ////    <t:App xmlns:t="http://schemas.microsoft.com/exchange/services/2006/types">
    ////      ....
    ////  <m:Apps>    <----- reader should be at this node at the end of the loop
    while (reader.isStartElement(XmlNamespace.Types, XmlElementNames.App))
    {
      ClientApp clientApp = new ClientApp();
      clientApp.loadFromXml(reader, XmlElementNames.App);
      this.apps.add(clientApp);
      this.manifests.add(clientApp.getManifest());
      reader.ensureCurrentNodeIsEndElement(XmlNamespace.Types, XmlElementNames.App);
      reader.read();
    }
  }
}
