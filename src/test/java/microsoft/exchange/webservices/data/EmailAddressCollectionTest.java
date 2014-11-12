package microsoft.exchange.webservices.data;

import org.junit.Assert;
import org.junit.Test;

public class EmailAddressCollectionTest {

	@Test
	public void testContains() throws Exception {
		EmailAddressCollection emailAddresses = new EmailAddressCollection();
		emailAddresses.add(new EmailAddress());
		emailAddresses.add(new EmailAddress(""));
		emailAddresses.add(new EmailAddress("1@yyy.zzz"));
		emailAddresses.add(new EmailAddress("2@yyy.zzz"));
		emailAddresses.add(new EmailAddress("3@yyy.zzz"));
		emailAddresses.add(new EmailAddress("xxx@yyy.zzz"));
		emailAddresses.add(new EmailAddress("xxx@yyy.zzz"));


		Assert.assertFalse(emailAddresses.contains(""));
		Assert.assertFalse(emailAddresses.contains("999@yyy.zzz"));
		Assert.assertTrue(emailAddresses.contains("1@yyy.zzz"));
		Assert.assertTrue(emailAddresses.contains("  \t  xxx@YYY.zZz  \n "));
	}

}
