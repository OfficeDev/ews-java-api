package microsoft.exchange.webservices.data.core.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.response.ResolveNamesResponse;
import microsoft.exchange.webservices.data.core.response.ServiceResponseCollection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
@RunWith(JUnit4.class)
public class ResolveNamesParseResponseTest {

  @Test
  public void testParseValidResponse() throws Exception {

    final ResolveNamesResponse response = new FakeRequest().parseStringResponse(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
            + "   <s:Header>\n"
            + "      <h:ServerVersionInfo xmlns:h=\"http://schemas.microsoft.com/exchange/services/2006/types\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" MajorVersion=\"15\" MinorVersion=\"20\" MajorBuildNumber=\"4734\" MinorBuildNumber=\"11\" Version=\"V2018_01_08\" />\n"
            + "   </s:Header>\n"
            + "   <s:Body>\n"
            + "      <m:ResolveNamesResponse xmlns:m=\"http://schemas.microsoft.com/exchange/services/2006/messages\" xmlns:t=\"http://schemas.microsoft.com/exchange/services/2006/types\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
            + "         <m:ResponseMessages>\n"
            + "            <m:ResolveNamesResponseMessage ResponseClass=\"Success\">\n"
            + "               <m:ResponseCode>NoError</m:ResponseCode>\n"
            + "               <m:ResolutionSet TotalItemsInView=\"1\" IncludesLastItemInRange=\"true\">\n"
            + "                  <t:Resolution>\n"
            + "                     <t:Mailbox>\n"
            + "                        <t:Name>Lev Sivashov</t:Name>\n"
            + "                        <t:EmailAddress>lev@yoxel.com</t:EmailAddress>\n"
            + "                        <t:RoutingType>SMTP</t:RoutingType>\n"
            + "                        <t:MailboxType>Contact</t:MailboxType>\n"
            + "                        <t:ItemId Id=\"zzz\" ChangeKey=\"ddd\" />\n"
            + "                     </t:Mailbox>\n"
            + "                  </t:Resolution>\n"
            + "               </m:ResolutionSet>\n"
            + "            </m:ResolveNamesResponseMessage>\n"
            + "         </m:ResponseMessages>\n"
            + "      </m:ResolveNamesResponse>\n"
            + "   </s:Body>\n"
            + "</s:Envelope>")
        .getResponseAtIndex(0);

    assertThat(response.getResolutions().getCount(), is(1));
    assertThat(response.getResolutions().iterator().next().getMailbox().getAddress(), is("lev@yoxel.com"));
  }

  @Test
  public void testParseInvalidResponse() throws Exception {
    // TotalItemsInView = 2, but there's only one Resolution element.

    final ResolveNamesResponse response = new FakeRequest().parseStringResponse(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
            + "   <s:Header>\n"
            + "      <h:ServerVersionInfo xmlns:h=\"http://schemas.microsoft.com/exchange/services/2006/types\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" MajorVersion=\"15\" MinorVersion=\"20\" MajorBuildNumber=\"4734\" MinorBuildNumber=\"11\" Version=\"V2018_01_08\" />\n"
            + "   </s:Header>\n"
            + "   <s:Body>\n"
            + "      <m:ResolveNamesResponse xmlns:m=\"http://schemas.microsoft.com/exchange/services/2006/messages\" xmlns:t=\"http://schemas.microsoft.com/exchange/services/2006/types\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
            + "         <m:ResponseMessages>\n"
            + "            <m:ResolveNamesResponseMessage ResponseClass=\"Success\">\n"
            + "               <m:ResponseCode>NoError</m:ResponseCode>\n"
            + "               <m:ResolutionSet TotalItemsInView=\"2\" IncludesLastItemInRange=\"true\">\n"
            + "                  <t:Resolution>\n"
            + "                     <t:Mailbox>\n"
            + "                        <t:Name>Lev Sivashov</t:Name>\n"
            + "                        <t:EmailAddress>lev@yoxel.com</t:EmailAddress>\n"
            + "                        <t:RoutingType>SMTP</t:RoutingType>\n"
            + "                        <t:MailboxType>Contact</t:MailboxType>\n"
            + "                        <t:ItemId Id=\"xxx\" ChangeKey=\"yyy\" />\n"
            + "                     </t:Mailbox>\n"
            + "                  </t:Resolution>\n"
            + "               </m:ResolutionSet>\n"
            + "            </m:ResolveNamesResponseMessage>\n"
            + "         </m:ResponseMessages>\n"
            + "      </m:ResolveNamesResponse>\n"
            + "   </s:Body>\n"
            + "</s:Envelope>")
        .getResponseAtIndex(0);

    assertThat(response.getResolutions().getCount(), is(1));
    assertThat(response.getResolutions().iterator().next().getMailbox().getAddress(), is("lev@yoxel.com"));
  }


  private static class FakeRequest extends ResolveNamesRequest {

    public FakeRequest() throws Exception {
      super(new ExchangeService());
    }

    public ServiceResponseCollection<ResolveNamesResponse> parseStringResponse(String content) throws Exception {
      return this.readResponse(
          new EwsServiceXmlReader(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)), getService()));
    }

  }
}
