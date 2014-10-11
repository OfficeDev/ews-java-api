package microsoft.exchange.webservices.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Testclass for methods of UserConfigurationDictionary
 *
 * @author serious6
 */
@RunWith(JUnit4.class)
public class UserConfigurationDictionaryTest {

    /**
     * Mock for the UserConfigurationDictionary
     */
    private UserConfigurationDictionary userConfigurationDictionary;
    /**
     * Mock for the ExchangeServiceBase
     */
    private ExchangeServiceBase exchangeServiceMock;

    /**
     * Setup Mocks and the UserConfigurationDictionary Object
     *
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        // Initialise a UserConfigurationDictionary Testobject
        this.userConfigurationDictionary = new UserConfigurationDictionary();

        // Mock up ExchangeServiceBase
        this.exchangeServiceMock = new ExchangeServiceBase() {
            @Override
            protected void processHttpErrorResponse(HttpWebRequest httpWebResponse, Exception webException) throws Exception {
                throw webException;
            }
        };
    }

    /**
     * Adding a Double Value to the Dictionary witch is not allowed
     *
     * @throws Exception
     */
    @Test(expected = ServiceLocalException.class)
    public void testAddUnsupportedElementsToDictionary() throws Exception {
        this.userConfigurationDictionary.addElement("someDouble", (Double) 1.0);
    }

    /**
     * testAddSupportedElementsToDictionary
     *
     * @throws Exception
     */
    @Test
    public void testAddSupportedElementsToDictionary() throws Exception {
        fillDictionaryWithValidEntries();
    }

    /**
     * Fills the Dictionary with
     *
     * @throws Exception
     */
    private void fillDictionaryWithValidEntries() throws Exception {
        // Adding Test Values to the Object
        final int testInt = 1;
        final long testLong = 1l;
        final String testString = "someVal";
        final String[] testStringArray = new String[]{"test1", "test2", "test3"};
        final Date testDate = new Date();
        final boolean testBoolean = true;
        final byte testByte = Byte.decode("0x10");
        final byte[] testByteArray = testString.getBytes();

        Assert.assertNotNull(this.userConfigurationDictionary);

        this.userConfigurationDictionary.addElement("someString", testString);
        Assert.assertTrue(this.userConfigurationDictionary.containsKey("someString"));
        Assert.assertEquals(testString, this.userConfigurationDictionary.getElements("someString"));
        Assert.assertTrue(this.userConfigurationDictionary.getElements("someString") instanceof String);

        this.userConfigurationDictionary.addElement("someLong", testLong);
        Assert.assertTrue(this.userConfigurationDictionary.containsKey("someLong"));
        Assert.assertEquals(testString, this.userConfigurationDictionary.getElements("someLong"));
        Assert.assertTrue(this.userConfigurationDictionary.getElements("someLong") instanceof Long);

        this.userConfigurationDictionary.addElement("someInteger", testInt);
        Assert.assertTrue(this.userConfigurationDictionary.containsKey("someInteger"));
        Assert.assertEquals(testString, this.userConfigurationDictionary.getElements("someInteger"));
        Assert.assertTrue(this.userConfigurationDictionary.getElements("someInteger") instanceof Integer);

        this.userConfigurationDictionary.addElement("someString[]", testStringArray);
        Assert.assertTrue(this.userConfigurationDictionary.containsKey("someString[]"));
        Assert.assertEquals(testString, this.userConfigurationDictionary.getElements("someString[]"));
        Assert.assertTrue(this.userConfigurationDictionary.getElements("someString[]") instanceof String[]);

        this.userConfigurationDictionary.addElement("someDate", testDate);
        Assert.assertTrue(this.userConfigurationDictionary.containsKey("someDate"));
        Assert.assertEquals(testString, this.userConfigurationDictionary.getElements("someDate"));
        Assert.assertTrue(this.userConfigurationDictionary.getElements("someDate") instanceof Date);

        this.userConfigurationDictionary.addElement("someBoolean", testBoolean);
        Assert.assertTrue(this.userConfigurationDictionary.containsKey("someBoolean"));
        Assert.assertEquals(testString, this.userConfigurationDictionary.getElements("someBoolean"));
        Assert.assertTrue(this.userConfigurationDictionary.getElements("someBoolean") instanceof Boolean);

        this.userConfigurationDictionary.addElement("someByte", testByte);
        Assert.assertTrue(this.userConfigurationDictionary.containsKey("someByte"));
        Assert.assertEquals(testString, this.userConfigurationDictionary.getElements("someByte"));
        Assert.assertTrue(this.userConfigurationDictionary.getElements("someByte") instanceof Byte);

        this.userConfigurationDictionary.addElement("someByte[]", testByteArray);
        Assert.assertTrue(this.userConfigurationDictionary.containsKey("someByte[]"));
        Assert.assertEquals(testString, this.userConfigurationDictionary.getElements("someByte[]"));
        Assert.assertTrue(this.userConfigurationDictionary.getElements("someByte[]") instanceof byte[]);
    }

    /**
     * Tests the Method writeElementsToXml(...)
     * with all valid Elements
     */
    @Test
    public void testWriteElementsToXml() throws Exception {
        // Mock up needed Classes
        OutputStream output = new ByteArrayOutputStream();
        EwsServiceXmlWriter testWriter = new EwsServiceXmlWriter(exchangeServiceMock, output);

        // Adding Test Values to the Object
        fillDictionaryWithValidEntries();

        // Write the Elements
        this.userConfigurationDictionary.writeElementsToXml(testWriter);
    }
}
