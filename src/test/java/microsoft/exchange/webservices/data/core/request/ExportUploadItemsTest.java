package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.data.core.*;
import microsoft.exchange.webservices.data.core.enumeration.misc.*;
import microsoft.exchange.webservices.data.core.enumeration.service.error.*;
import microsoft.exchange.webservices.data.core.exception.service.remote.*;
import microsoft.exchange.webservices.data.core.response.*;
import microsoft.exchange.webservices.data.core.service.item.*;
import microsoft.exchange.webservices.data.core.service.item.UploadItem.*;
import microsoft.exchange.webservices.data.property.complex.*;
import org.apache.commons.io.*;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.mockito.*;

import java.io.*;
import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class ExportUploadItemsTest {

  private static final String DATA = "DATA";

  private static byte[] fixture(String folder, String name) {
    try (InputStream in = fixtureStream(folder, name)) {
      return IOUtils.toByteArray(in);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static InputStream fixtureStream(String folder, String name) {
    return ExportUploadItemsTest.class
            .getResourceAsStream(String.format("/%s/%s.xml", folder, name));
  }

  private final ExchangeService exchangeService = Mockito.mock(ExchangeService.class);

  @Before
  public void setUp() {
    when(exchangeService.getRequestedServerVersion()).thenReturn(ExchangeVersion.Exchange2013);
    when(exchangeService.getDateTimePrecision()).thenReturn(DateTimePrecision.Default);
  }

  @Test
  public void testExportRequest() throws Exception {
    ExportItemsRequest request = new ExportItemsRequest(exchangeService, ServiceErrorHandling.ReturnErrors);
    request.getItemIds().addRange(Arrays.asList(new ItemId("12345"), new ItemId("23456")));

    try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
      EwsServiceXmlWriter writer = new EwsServiceXmlWriter(exchangeService, os);
      request.writeToXml(writer);
      assertThat(os.toByteArray(), is(equalTo(fixture("test-data", "ExportItemsRequest"))));
    }

    try (InputStream in = fixtureStream("test-data", "ExportItemsResponse")) {
      EwsServiceXmlReader reader = new EwsServiceXmlReader(in, exchangeService);
      ServiceResponseCollection<ExportItemsResponse> responses = request.readResponse(reader);
      assertThat(responses.getCount(), is(equalTo(2)));

      ExportItemsResponse response1 = responses.getResponseAtIndex(0);
      assertThat(response1.getItemId().getUniqueId(), is(equalTo("AAMkADNiMDU5YzBhLTgzYzgtNGMxZi1iYTZlLTgzOTkxMzBjYmVhZgBGAAAAAABPTDZfYrvVTr8HgEV+HADsBwCLjN8GwkCsQKoM14gF2rmPAAAAAAENAACLjN8GwkCsQKoM14gF2rmPAAAiEWxoAAA=")));
      ExportItemsResponse response2 = responses.getResponseAtIndex(1);
      assertThat(response2.getItemId().getUniqueId(), is(equalTo("AAMkADNiMDU5YzBhLTgzYzgtNGMxZi1iYTZlLTgzOTkxMzBjYmVhZgBGAAAAAABPTDZfYrvVTr8HgEV+HADsBwCLjN8GwkCsQKoM14gF2rmPAAAAAAENAACLjN8GwkCsQKoM14gF2rmPAAAiEWxoAAB=")));
    }
  }

  @Test(expected = ServiceResponseException.class)
  public void testExportRequestFails() throws Exception {
    ExportItemsRequest request = new ExportItemsRequest(exchangeService, ServiceErrorHandling.ThrowOnError);
    request.getItemIds().addRange(Arrays.asList(new ItemId("12345")));

    try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
      EwsServiceXmlWriter writer = new EwsServiceXmlWriter(exchangeService, os);
      request.writeToXml(writer);
    }

    try (InputStream in = fixtureStream("test-data", "ExportItemsResponseFail")) {
      EwsServiceXmlReader reader = new EwsServiceXmlReader(in, exchangeService);
      ServiceResponseCollection<ExportItemsResponse> responses = request.readResponse(reader);
      responses.getResponseAtIndex(0).throwIfNecessary();
    }
  }

  @Test
  public void testUploadRequest() throws Exception {
    UploadItemsRequest request = new UploadItemsRequest(exchangeService, ServiceErrorHandling.ReturnErrors);

    UploadItem uploadItem1 = new UploadItem();
    uploadItem1.setCreateAction(CreateAction.CreateNew);
    uploadItem1.setParentFolderId(new FolderId("1234"));
    uploadItem1.setData(DATA.getBytes("UTF-8"));

    UploadItem uploadItem2 = new UploadItem();
    uploadItem2.setCreateAction(CreateAction.Update);
    uploadItem2.setParentFolderId(new FolderId("2345"));
    uploadItem2.setItemId(new ItemId("3456"));
    uploadItem2.setData(DATA.getBytes("UTF-8"));

    request.setItems(Arrays.asList(uploadItem1, uploadItem2));

    try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
      EwsServiceXmlWriter writer = new EwsServiceXmlWriter(exchangeService, os);
      request.writeToXml(writer);
      assertThat(os.toByteArray(), is(equalTo(fixture("test-data", "UploadItemsRequest"))));
    }

    try (InputStream in = fixtureStream("test-data", "UploadItemsResponse")) {
      EwsServiceXmlReader reader = new EwsServiceXmlReader(in, exchangeService);
      ServiceResponseCollection<UploadItemsResponse> responses = request.readResponse(reader);
      assertThat(responses.getCount(), is(equalTo(2)));

      UploadItemsResponse response1 = responses.getResponseAtIndex(0);
      assertThat(response1.getItemId().getUniqueId(), is(equalTo("AAMkADhhUFZ/AAA=")));
      UploadItemsResponse response2 = responses.getResponseAtIndex(1);
      assertThat(response2.getItemId().getUniqueId(), is(equalTo("AAMkADhhOGZ7AAA=")));
    }
  }

  @Test(expected = Exception.class)
  public void testUploadRequestValidateEmpty() throws Exception {
    UploadItem item = new UploadItem();
    item.validate();
  }

  @Test
  public void testUploadRequestValidateFine() throws Exception {
    UploadItem item = new UploadItem();
    item.setData(new byte[]{});
    item.setParentFolderId(new FolderId("1234"));
    item.setCreateAction(CreateAction.CreateNew);
    item.validate();
  }

  @Test(expected = Exception.class)
  public void testUploadRequestValidateUpdateFails() throws Exception {
    UploadItem item = new UploadItem();
    item.setData(new byte[]{});
    item.setParentFolderId(new FolderId("1234"));
    item.setCreateAction(CreateAction.Update);
    item.validate();
  }

  @Test
  public void testUploadRequestValidateUpdateFine() throws Exception {
    UploadItem item = new UploadItem();
    item.setData(new byte[]{});
    item.setParentFolderId(new FolderId("1234"));
    item.setCreateAction(CreateAction.Update);
    item.setItemId(new ItemId("1234"));
    item.validate();
  }
}
