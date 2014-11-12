package microsoft.exchange.webservices.data;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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

		assertThat(emailAddresses.contains(""), is(false));
		assertThat(emailAddresses.contains("999@yyy.zzz"), is(false));
		assertThat(emailAddresses.contains("1@yyy.zzz"), is(true));
		assertThat(emailAddresses.contains("  \t  xxx@YYY.zZz  \n "), is(true));
	}

}