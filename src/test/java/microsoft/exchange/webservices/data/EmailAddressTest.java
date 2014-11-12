package microsoft.exchange.webservices.data;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmailAddressTest {

	@Test
	public void testEquals() throws Exception {
		{
			EmailAddress emailAddress = new EmailAddress();

			assertThat(emailAddress.equals(null), is(false));
			assertThat(emailAddress.equals(""), is(false));
			assertThat(emailAddress.equals("wrong@address"), is(false));
			assertThat(emailAddress.equals("correct@address"), is(false));
			assertThat(emailAddress.equals(" correct@address "), is(false));
		}

		{
			EmailAddress emailAddress = new EmailAddress("");

			assertThat(emailAddress.equals(null), is(false));
			assertThat(emailAddress.equals(""), is(false));
			assertThat(emailAddress.equals("wrong@address"), is(false));
			assertThat(emailAddress.equals("correct@address"), is(false));
			assertThat(emailAddress.equals(" correct@address "), is(false));
		}

		{
			EmailAddress emailAddress = new EmailAddress("   \ncoRRect@aDDress \t  ");

			assertThat(emailAddress.equals(null), is(false));
			assertThat(emailAddress.equals(""), is(false));
			assertThat(emailAddress.equals("wrong@address"), is(false));
			assertThat(emailAddress.equals("correct@address"), is(true));
			assertThat(emailAddress.equals("CORRECT@address   \n "), is(true));
		}

		{
			EmailAddress emailAddress = new EmailAddress("correct@address");

			assertThat(emailAddress.equals(null), is(false));
			assertThat(emailAddress.equals(""), is(false));
			assertThat(emailAddress.equals("wrong@address"), is(false));
			assertThat(emailAddress.equals("correct@address"), is(true));
			assertThat(emailAddress.equals("CORRECT@address   \n "), is(true));
		}
	}

}