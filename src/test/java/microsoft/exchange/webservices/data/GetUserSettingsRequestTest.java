package microsoft.exchange.webservices.data;


import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Testclass for methods of GetUserSettingsRequest
 */
@RunWith(Parameterized.class)
public class GetUserSettingsRequestTest extends BaseTest {

  /**
   * The ExchangeVersion which is under test
   */
  private final ExchangeVersion exchangeVersion;

  /**
   * The AutodiscoverService which is under test
   */
  private final AutodiscoverService autodiscoverService;

  /**
   * A mocked URI via HTTPS
   */
  private final URI uriMockHttps = URI.create("https://localhost");

  /**
   * A mocked URI via HTTP
   */
  private final URI uriMockHttp = URI.create("http://localhost");

  /**
   * Returns the Parameters which where handled to the constructor
   *
   * @return the available Services
   * @throws ArgumentException
   */
  @Parameterized.Parameters
  public static List<Object[]> getAutodiscoverServices() throws ArgumentException {
    return new ArrayList<Object[]>() {

      /**
       * Constant serialized ID used for compatibility.
       */
      private static final long serialVersionUID = 1L;

      {
        for (ExchangeVersion exchangeVersion : ExchangeVersion.values()) {
          add(new Object[] {exchangeVersion, new AutodiscoverService(exchangeVersion)});
        }
      }
    };
  }

  /**
   * Construct the Testobject with given Parameters
   *
   * @param exchangeVersion
   * @param autodiscoverService
   */
  public GetUserSettingsRequestTest(final ExchangeVersion exchangeVersion,
      final AutodiscoverService autodiscoverService) {
    this.exchangeVersion = exchangeVersion;
    this.autodiscoverService = autodiscoverService;
  }

  /**
   * setup
   */
  @Before
  public void setup() {
    Assert.assertThat(this.exchangeVersion, IsNull.notNullValue());
    Assert.assertThat(this.autodiscoverService, IsNull.notNullValue());
    Assert.assertThat(uriMockHttp, IsNull.notNullValue());
    Assert.assertThat(uriMockHttps, IsNull.notNullValue());
  }

  /**
   * Nothing should be writen to the Outputstream if expectPartnerToken is not set
   *
   * @throws ServiceValidationException
   * @throws XMLStreamException
   * @throws ServiceXmlSerializationException
   */
  @Test
  public void testWriteExtraCustomSoapHeadersToXmlWithoutPartnertoken()
      throws ServiceValidationException, XMLStreamException, ServiceXmlSerializationException {
    // HTTPS
    GetUserSettingsRequest getUserSettingsRequest =
        new GetUserSettingsRequest(autodiscoverService, uriMockHttps);

    // Test without expected Partnertoken
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    getUserSettingsRequest.writeExtraCustomSoapHeadersToXml(
        new EwsServiceXmlWriter(exchangeServiceBaseMock, byteArrayOutputStream));

    // nothing should be writen to the outputstream
    Assert.assertArrayEquals(byteArrayOutputStream.toByteArray(), new ByteArrayOutputStream().toByteArray());

    // HTTP
    getUserSettingsRequest = new GetUserSettingsRequest(autodiscoverService, uriMockHttp);

    // Test without expected Partnertoken
    byteArrayOutputStream = new ByteArrayOutputStream();
    getUserSettingsRequest.writeExtraCustomSoapHeadersToXml(
        new EwsServiceXmlWriter(exchangeServiceBaseMock, byteArrayOutputStream));

    // nothing should be writen to the outputstream
    Assert.assertArrayEquals(byteArrayOutputStream.toByteArray(), new ByteArrayOutputStream().toByteArray());
  }

  /**
   * Test if content is added correctly if expectPartnerToken is set
   *
   * @throws ServiceValidationException
   * @throws XMLStreamException
   * @throws ServiceXmlSerializationException
   */
  @Test
  public void testWriteExtraCustomSoapHeadersToXmlWithPartnertoken()
      throws ServiceValidationException, XMLStreamException, ServiceXmlSerializationException {
    GetUserSettingsRequest getUserSettingsRequest =
        new GetUserSettingsRequest(autodiscoverService, uriMockHttps, Boolean.TRUE);

    // Test without expected Partnertoken
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    getUserSettingsRequest.writeExtraCustomSoapHeadersToXml(
        new EwsServiceXmlWriter(exchangeServiceBaseMock, byteArrayOutputStream));

    // data should be added the same way as mentioned
    Assert.assertThat(byteArrayOutputStream.toByteArray(),
        IsNot.not(new ByteArrayOutputStream().toByteArray()));

    //TODO Test if the output is really correct
  }

  /**
   * Initialising a GetUserSettingsRequest with Http should lead to an ServiceValidationException
   *
   * @throws ServiceValidationException
   * @throws XMLStreamException
   * @throws ServiceXmlSerializationException
   */
  @Test(expected = ServiceValidationException.class)
  public void testWriteExtraCustomSoapHeadersToXmlWithPartnertoken2()
      throws ServiceValidationException, XMLStreamException, ServiceXmlSerializationException {
    GetUserSettingsRequest getUserSettingsRequest =
        new GetUserSettingsRequest(autodiscoverService, uriMockHttp, Boolean.TRUE);
  }
}
