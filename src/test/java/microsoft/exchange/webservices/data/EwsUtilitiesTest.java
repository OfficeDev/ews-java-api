package microsoft.exchange.webservices.data;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

@RunWith(JUnit4.class)
public class EwsUtilitiesTest {
    @Test
    public void testGetBuildVersion() {
        Assert.assertEquals("Build version must be 0s", "0.0.0.0", EwsUtilities.getBuildVersion());
    }
}
