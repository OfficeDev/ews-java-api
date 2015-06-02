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

package microsoft.exchange.webservices.data.property.definition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.EnumSet;

@RunWith(JUnit4.class)
public class ByteArrayPropertyDefinitionTest {

  private ByteArrayPropertyDefinition testObject;

  private static final String TEST_STRING = "Lorem ipsum dolor sit amet";
  private static final String BASE64_ENCODEDSTRING = Base64.encodeBase64String(TEST_STRING.getBytes());

  /**
   * setup
   */
  @Before
  public void init(){
    this.testObject =
        new ByteArrayPropertyDefinition("myTestObject", "myTestUri",
                                        EnumSet.of(PropertyDefinitionFlags.None),
                                        ExchangeVersion.Exchange2010_SP2);
  }

  /**
   * Test for ByteArrayPropertyDefinition.toString()
   * This Test should guarantee that toString() byte encoding works
   */
  @Test
  public void testToString(){
    String result = testObject.toString(TEST_STRING.getBytes());
    assertNotNull(result);
    assertEquals(BASE64_ENCODEDSTRING, result);
  }

}
