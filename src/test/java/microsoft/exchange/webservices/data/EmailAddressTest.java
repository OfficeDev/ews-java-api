package microsoft.exchange.webservices.data;

import org.junit.Assert;
import org.junit.Test;

public class EmailAddressTest {

	@Test
	public void testEquals() throws Exception {
		{
			EmailAddress emailAddress = new EmailAddress();

			Assert.assertFalse(emailAddress.equals(null));
			Assert.assertFalse(emailAddress.equals(""));
			Assert.assertFalse(emailAddress.equals("wrong@address"));
			Assert.assertFalse(emailAddress.equals("correct@address"));
			Assert.assertFalse(emailAddress.equals("correct@address"));
		}

		{
			EmailAddress emailAddress = new EmailAddress("");

			Assert.assertFalse(emailAddress.equals(null));
			Assert.assertFalse(emailAddress.equals(""));
			Assert.assertFalse(emailAddress.equals("wrong@address"));
			Assert.assertFalse(emailAddress.equals("correct@address"));
			Assert.assertFalse(emailAddress.equals("correct@address"));
		}

		{
			EmailAddress emailAddress = new EmailAddress("   \ncoRRect@aDDress \t  ");

			Assert.assertFalse(emailAddress.equals(null));
			Assert.assertFalse(emailAddress.equals(""));
			Assert.assertFalse(emailAddress.equals("wrong@address"));
			Assert.assertTrue(emailAddress.equals("correct@address"));
			Assert.assertTrue(emailAddress.equals("CORRECT@address   \n "));
		}

		{
			EmailAddress emailAddress = new EmailAddress("correct@address");

			Assert.assertFalse(emailAddress.equals(null));
			Assert.assertFalse(emailAddress.equals(""));
			Assert.assertFalse(emailAddress.equals("wrong@address"));
			Assert.assertTrue(emailAddress.equals("correct@address"));
			Assert.assertTrue(emailAddress.equals("CORRECT@address   \n "));
		}
	}

}
