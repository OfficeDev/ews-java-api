package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.base.BaseTest;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceResult;
import microsoft.exchange.webservices.data.core.response.GetUserConfigurationResponse;
import microsoft.exchange.webservices.data.core.response.ServiceResponseCollection;
import microsoft.exchange.webservices.data.misc.OutParam;
import microsoft.exchange.webservices.data.property.complex.UserConfigurationDictionary;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.InputStream;

@RunWith(JUnit4.class)
public class UserConfigurationTest extends BaseTest {

  @Test
  public void testNilDictionaryEntry() throws Exception {
    GetUserConfigurationRequest request = new GetUserConfigurationRequest(exchangeServiceMock);
    request.setName("Calendar");

    InputStream inputStream = getClass().getResourceAsStream("/nil.xml");
    EwsServiceXmlReader ewsXmlReader = new EwsServiceXmlReader(inputStream, exchangeServiceMock);
    ServiceResponseCollection<GetUserConfigurationResponse> responses = request.readResponse(ewsXmlReader);

    Assert.assertEquals(ServiceResult.Success, responses.getOverallResult());
    Assert.assertNull(getDictionaryEntryValue(responses, "RequestInPolicy"));
  }

  private Object getDictionaryEntryValue(ServiceResponseCollection<GetUserConfigurationResponse> responses,
                                         String requestInPolicy) {
    UserConfigurationDictionary dictionary = responses.iterator().next().getUserConfiguration().getDictionary();
    OutParam<Object> value = new OutParam<Object>();
    dictionary.tryGetValue(requestInPolicy, value);
    return value.getParam();
  }

}
