/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.exceptions.ServiceLocalException;
import microsoft.exchange.webservices.data.properties.complex.UserConfigurationDictionary;
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
 */
@RunWith(JUnit4.class)
public class UserConfigurationDictionaryTest extends BaseTest {

  /**
   * Mock for the UserConfigurationDictionary
   */
  protected UserConfigurationDictionary userConfigurationDictionary;

  @Before
  public void setup() throws Exception {
    // Initialise a UserConfigurationDictionary Testobject
    this.userConfigurationDictionary = new UserConfigurationDictionary();
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
    final String[] testStringArray = new String[] {"test1", "test2", "test3"};
    final Date testDate = new Date();
    final boolean testBoolean = true;
    final byte testByte = Byte.decode("0x10");
    final byte[] testByteArray = testString.getBytes();
    final Byte[] testByteArray2 = new Byte[testByteArray.length];
    for (int currentIndex = 0; currentIndex < testByteArray.length; currentIndex++) {
      testByteArray2[currentIndex] = testByteArray[currentIndex];
    }

    Assert.assertNotNull(this.userConfigurationDictionary);

    this.userConfigurationDictionary.addElement("someString", testString);
    Assert.assertTrue(this.userConfigurationDictionary.containsKey("someString"));
    Assert.assertEquals(testString, this.userConfigurationDictionary.getElements("someString"));
    Assert.assertTrue(this.userConfigurationDictionary.getElements("someString") instanceof String);

    this.userConfigurationDictionary.addElement("someLong", testLong);
    Assert.assertTrue(this.userConfigurationDictionary.containsKey("someLong"));
    Assert.assertEquals(testLong, this.userConfigurationDictionary.getElements("someLong"));
    Assert.assertTrue(this.userConfigurationDictionary.getElements("someLong") instanceof Long);

    this.userConfigurationDictionary.addElement("someInteger", testInt);
    Assert.assertTrue(this.userConfigurationDictionary.containsKey("someInteger"));
    Assert.assertEquals(testInt, this.userConfigurationDictionary.getElements("someInteger"));
    Assert.assertTrue(this.userConfigurationDictionary.getElements("someInteger") instanceof Integer);

    this.userConfigurationDictionary.addElement("someString[]", testStringArray);
    Assert.assertTrue(this.userConfigurationDictionary.containsKey("someString[]"));
    Assert.assertEquals(testStringArray, this.userConfigurationDictionary.getElements("someString[]"));
    Assert.assertTrue(this.userConfigurationDictionary.getElements("someString[]") instanceof String[]);

    this.userConfigurationDictionary.addElement("someDate", testDate);
    Assert.assertTrue(this.userConfigurationDictionary.containsKey("someDate"));
    Assert.assertEquals(testDate, this.userConfigurationDictionary.getElements("someDate"));
    Assert.assertTrue(this.userConfigurationDictionary.getElements("someDate") instanceof Date);

    this.userConfigurationDictionary.addElement("someBoolean", testBoolean);
    Assert.assertTrue(this.userConfigurationDictionary.containsKey("someBoolean"));
    Assert.assertEquals(testBoolean, this.userConfigurationDictionary.getElements("someBoolean"));
    Assert.assertTrue(this.userConfigurationDictionary.getElements("someBoolean") instanceof Boolean);

    this.userConfigurationDictionary.addElement("someByte", testByte);
    Assert.assertTrue(this.userConfigurationDictionary.containsKey("someByte"));
    Assert.assertEquals(testByte, this.userConfigurationDictionary.getElements("someByte"));
    Assert.assertTrue(this.userConfigurationDictionary.getElements("someByte") instanceof Byte);

    this.userConfigurationDictionary.addElement("someByte[]", testByteArray);
    Assert.assertTrue(this.userConfigurationDictionary.containsKey("someByte[]"));
    Assert.assertEquals(testByteArray, this.userConfigurationDictionary.getElements("someByte[]"));
    Assert.assertTrue(this.userConfigurationDictionary.getElements("someByte[]") instanceof byte[]);

    this.userConfigurationDictionary.addElement("someByte2[]", testByteArray2);
    Assert.assertTrue(this.userConfigurationDictionary.containsKey("someByte2[]"));
    Assert.assertEquals(testByteArray2, this.userConfigurationDictionary.getElements("someByte2[]"));
    Assert.assertTrue(this.userConfigurationDictionary.getElements("someByte2[]") instanceof Byte[]);
  }

  /**
   * Tests the Method writeElementsToXml(...)
   * with all valid Elements
   */
  @Test
  public void testWriteElementsToXml() throws Exception {
    // Mock up needed Classes
    OutputStream output = new ByteArrayOutputStream();
    EwsServiceXmlWriter testWriter = new EwsServiceXmlWriter(exchangeServiceBaseMock, output);

    // Adding Test Values to the Object
    fillDictionaryWithValidEntries();

    // Write the Elements
    this.userConfigurationDictionary.writeElementsToXml(testWriter);
  }
}
