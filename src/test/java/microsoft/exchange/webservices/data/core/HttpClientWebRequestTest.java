package microsoft.exchange.webservices.data.core;

import microsoft.exchange.webservices.base.BaseTest;
import microsoft.exchange.webservices.data.core.request.HttpClientWebRequest;
import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

public class HttpClientWebRequestTest extends BaseTest {
  @Test
  public void testGet() throws Exception {
    HttpClientWebRequest request = new HttpClientWebRequest(
        exchangeServiceMock.httpClient, exchangeServiceMock.httpContext);
    request.setUrl(new URL("http://localhost"));
    request.setRequestMethod("GET");
    request.prepareConnection();
  }

  @Test
  public void testPost() throws Exception {
    HttpClientWebRequest request = new HttpClientWebRequest(
        exchangeServiceMock.httpClient, exchangeServiceMock.httpContext);
    request.setUrl(new URL("http://localhost"));
    request.setRequestMethod("POST");
    request.prepareConnection();
  }

  @Test
  public void testUnsupported() throws Exception {
    HttpClientWebRequest request = new HttpClientWebRequest(
        exchangeServiceMock.httpClient, exchangeServiceMock.httpContext);
    request.setUrl(new URL("http://localhost"));
    request.setRequestMethod("PUT");
    try {
      request.prepareConnection();
      Assert.fail("Should not have been able to prepare connection with unsupported method");
    } catch (IllegalStateException e) {
      // Fall through...
    }
  }
}
