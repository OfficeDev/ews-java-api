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

package microsoft.exchange.webservices.data.util;

import microsoft.exchange.webservices.base.util.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.TimeZone;

@RunWith(JUnit4.class)
public class TimeZoneUtilsTest {

  @Test(expected = UnsupportedOperationException.class)
  public void testTimeZoneUtilsConstructor() throws Throwable {
    TestUtils.checkUtilClassConstructor(TimeZoneUtils.class);
  }

  @Test
  public void testGetMicrosoftTimeZoneName() {
    checkGetMicrosoftTimeZoneName("Africa/Abidjan", "Greenwich Standard Time");
  }

  @Test
  public void testGetMicrosoftTimeZoneNameBad() {
    // null-argument is not allowed.
    try {
      Assert.fail(TimeZoneUtils.getMicrosoftTimeZoneName(null));
    } catch (final IllegalArgumentException ignored) {}

    // Case-insensitive ID is not supported.
    try {
      checkGetMicrosoftTimeZoneName("africa/abidjan", "UTC");
    } catch (final AssertionError ignored) {}
  }

  private void checkGetMicrosoftTimeZoneName(final String id, final String name) {
    final TimeZone timeZone = TimeZone.getTimeZone(id);
    final String zoneName = TimeZoneUtils.getMicrosoftTimeZoneName(timeZone);
    Assert.assertEquals(name, zoneName);
  }

}
