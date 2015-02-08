package microsoft.exchange.webservices.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EmailAddressTest {

  @Test
  public void testEmailAddressToString() {
    Assert.assertTrue(EwsUtilities.stringEquals(null, null));
    EmailAddress address = new EmailAddress();
    address.setAddress("ews@ews.com");
    Assert.assertEquals(address.toString(), "ews@ews.com");
    address.setName("ews");
    Assert.assertEquals(address.toString(), "ews <ews@ews.com>");
  }

}
