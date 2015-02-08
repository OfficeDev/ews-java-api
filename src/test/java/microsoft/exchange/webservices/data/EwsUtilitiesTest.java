package microsoft.exchange.webservices.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EwsUtilitiesTest {
  @Test
  public void testGetBuildVersion() {
    Assert.assertEquals("Build version must be 0s", "0.0.0.0", EwsUtilities.getBuildVersion());
  }

  @Test
  public void testStringEquals() {
    Assert.assertTrue(EwsUtilities.stringEquals(null, null));
    Assert.assertTrue(EwsUtilities.stringEquals("x", "x"));

    Assert.assertFalse(EwsUtilities.stringEquals(null, "x"));
    Assert.assertFalse(EwsUtilities.stringEquals("x", null));
    Assert.assertFalse(EwsUtilities.stringEquals("x", "X"));
  }
}
