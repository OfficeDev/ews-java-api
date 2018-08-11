package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by yiyangli on 8/9/16.
 */
public class ClientApp extends ComplexProperty {
  public static final String Manifest = "Manifest";
  public static final String Metadata = "Metadata";

  /**
   * Initializes a new instance of the ClientApp class.
   */
  public ClientApp() {
    super();
    this.setNamespace(XmlNamespace.Types);
  }

  private InputStream manifest;

  private ClientAppMetadata metaData;

  public InputStream getManifest() {
    return manifest;
  }

  public void setManifest(InputStream manifest) {
    this.manifest = manifest;
  }

  public ClientAppMetadata getMetaData() {
    return metaData;
  }

  public void setMetaData(ClientAppMetadata metaData) {
    this.metaData = metaData;
  }

  /*
          * Helper to convert to xml dcouemnt from the current value.
          * @param reader The reader.
          * @return The xml document.
          * */
  public static InputStream readToXmlDocument(EwsServiceXmlReader reader) throws Exception {
    ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
    reader.readBase64ElementValue(memoryStream);

    return new ByteArrayInputStream(memoryStream.toByteArray());
  }

  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader) throws Exception {
    if (reader.getLocalName().equals(Manifest)) {
      this.setManifest(ClientApp.readToXmlDocument(reader));
      return true;
    }
    if (reader.getLocalName().equals(Metadata)) {
      this.setMetaData(new ClientAppMetadata());
      this.metaData.loadFromXml(reader, XmlNamespace.Types, Metadata);
      return true;
    }
    return false;
  }
}
