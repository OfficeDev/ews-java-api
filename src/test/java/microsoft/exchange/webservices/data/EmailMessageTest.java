package microsoft.exchange.webservices.data;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class EmailMessageTest extends BaseTest {

  private EmailMessage emailMessage;

  @Before
  public void setup() throws Exception {
    emailMessage = new EmailMessage(this.exchangeServiceMock);
  }

  @Test
  public void testSetReplyToNoneSet() throws Exception {
    emailMessage.setReplyTo();
    EmailAddressCollection replyTo = emailMessage.getReplyTo();
    assertThat(replyTo.getCount(), Is.is(0));
  }

  @Test
  public void testSetReplyToSetEmpty() throws Exception {
    EmailAddress emailAddress = new EmailAddress("noreply@nowhere.com");
    emailMessage.setReplyTo(emailAddress);
    EmailAddressCollection replyTo = emailMessage.getReplyTo();
    assertThat(replyTo.getCount(), Is.is(1));
    emailMessage.setReplyTo();
    replyTo = emailMessage.getReplyTo();
    assertThat(replyTo.getCount(), Is.is(0));
  }

  @Test
  public void testSetReplyToOneSet() throws Exception {
    String smtpAddress = "noreply@nowhere.com";
    EmailAddress emailAddress = new EmailAddress(smtpAddress);
    emailMessage.setReplyTo(emailAddress);
    EmailAddressCollection replyTo = emailMessage.getReplyTo();
    assertThat(replyTo.getCount(), Is.is(1));
    assertThat(replyTo.getPropertyAtIndex(0).getAddress(), IsEqual.equalTo(smtpAddress));
  }

  @Test
  public void testSetReplyToTwoSet() throws Exception {
    String smtpAddress1 = "noreply1@nowhere.com";
    String smtpAddress2 = "noreply2@nowhere.com";
    Set<String> addresses = new HashSet<String>();
    addresses.add(smtpAddress1);
    addresses.add(smtpAddress2);

    EmailAddress emailAddress1 = new EmailAddress(smtpAddress1);
    EmailAddress emailAddress2 = new EmailAddress(smtpAddress2);
    emailMessage.setReplyTo(emailAddress1, emailAddress2);
    EmailAddressCollection replyTo = emailMessage.getReplyTo();
    assertThat(replyTo.getCount(), Is.is(2));
    for (EmailAddress emailAddress : replyTo.getItems()) {
      assertThat(addresses.remove(emailAddress.getAddress()), Is.is(true));
    }
  }
}