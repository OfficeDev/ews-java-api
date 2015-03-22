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

import microsoft.exchange.webservices.data.util.Base64EncoderStream;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;
import java.util.UUID;

@RunWith(JUnit4.class)
public class IFunctionsTest {

  @Test
  public void testToString() {
    final IFunctions.ToString f = IFunctions.ToString.INSTANCE;
    Assert.assertEquals("null", f.func(null));
    Assert.assertEquals("", f.func(""));
    Assert.assertEquals("1", f.func(1));
  }

  @Test
  public void testToBoolean() {
    final IFunctions.ToBoolean f = IFunctions.ToBoolean.INSTANCE;
    Assert.assertFalse(f.func(null));
    Assert.assertFalse(f.func(""));
    Assert.assertFalse(f.func("false"));
    Assert.assertTrue(f.func("true"));
  }

  @Test
  public void testStringToObject() {
    final IFunctions.StringToObject f = IFunctions.StringToObject.INSTANCE;
    Assert.assertNull(f.func(null));
    Assert.assertEquals("", f.func(""));
  }

  @Test
  public void testToUUID() {
    final IFunctions.ToUUID f = IFunctions.ToUUID.INSTANCE;
    try {
      Assert.assertNull(f.func(null));
    } catch (final Throwable ex) {
      final UUID uuid = UUID.randomUUID();
      Assert.assertEquals(uuid, f.func(uuid.toString()));
    }
  }

  @Test
  public void testBase64Decoder() {
    final String value = "123";
    final IFunctions.Base64Decoder f = IFunctions.Base64Decoder.INSTANCE;
    Assert.assertArrayEquals(Base64EncoderStream.decode(value), (byte[]) f.func(value));
  }

  @Test
  public void testBase64Encoder() {
    final byte[] value = StringUtils.getBytesUtf8("123");
    final IFunctions.Base64Encoder f = IFunctions.Base64Encoder.INSTANCE;
    Assert.assertEquals(Base64EncoderStream.encode(value), f.func(value));
  }

  @Test
  public void testToLowerCase() {
    final IFunctions.ToLowerCase f = IFunctions.ToLowerCase.INSTANCE;
    Assert.assertNull(f.func(null));
    Assert.assertEquals("", f.func(""));
    Assert.assertEquals("abc", f.func("AbC"));
  }

  @Test
  public void testDateTimeToXSDateTime() {
    final IFunctions.DateTimeToXSDateTime f = IFunctions.DateTimeToXSDateTime.INSTANCE;
    final Date value = new Date();
    Assert.assertEquals(EwsUtilities.dateTimeToXSDateTime(value), f.func(value));
  }

}
