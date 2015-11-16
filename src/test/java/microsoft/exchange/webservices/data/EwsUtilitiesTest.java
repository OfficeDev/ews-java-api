/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class EwsUtilitiesTest {
  @Test
  public void testGetBuildVersion() {
    assertEquals("Build version must be 0s", "0.0.0.0", EwsUtilities.getBuildVersion());
  }

  @Test
  public void testGetXSDurationToTimeSpan() {
    TimeSpan result = EwsUtilities.getXSDurationToTimeSpan("PT0H");
    assertEquals(0, result.getHours());
    assertEquals(0, result.getMinutes());
    assertEquals(0, result.getSeconds());
    assertEquals(0, result.getMilliseconds());

    result = EwsUtilities.getXSDurationToTimeSpan("PT23H56M22.123S");
    assertEquals(23, result.getHours());
    assertEquals(56, result.getMinutes());
    assertEquals(22, result.getSeconds());
    assertEquals(123, result.getMilliseconds());

    result = EwsUtilities.getXSDurationToTimeSpan("PT1H");
    assertEquals(1, result.getHours());
    assertEquals(0, result.getMinutes());
    assertEquals(0, result.getSeconds());
    assertEquals(0, result.getMilliseconds());
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
