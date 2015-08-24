/*
 * The MIT License Copyright (c) 2012 Microsoft Corporation
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.core;

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.misc.TimeSpan;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class XSDurationTest {

  // Tests for EwsUtilities.getXSDurationToTimeSpan()

  private static final String PERIOD_HOURS = "-PT13H";
  private static final String PERIOD_HOURS_MINUTES = "-PT5H30M";
  private static final String PERIOD_FULL = "PT2H30M59.0S";
  private static final String PERIOD_FULL_NEGATIVE = "-PT2H30M59.0S";
  private static final String PERIOD_OVERFLOW = "PT2H100M59.0S";
  private static final String PERIOD_FAIL = "P2H30M59.0S";
  
  
  @Test
  public void testPeriodHours() {
    TimeSpan timeSpan = EwsUtilities.getXSDurationToTimeSpan(PERIOD_HOURS);
    Assert.assertEquals("-P0DT13H0M0.0S", EwsUtilities.getTimeSpanToXSDuration(timeSpan));
  }

  @Test
  public void testPeriodHoursMinutes() {
    TimeSpan timeSpan = EwsUtilities.getXSDurationToTimeSpan(PERIOD_HOURS_MINUTES);
    Assert.assertEquals("-P0DT5H30M0.0S", EwsUtilities.getTimeSpanToXSDuration(timeSpan));
  }

  @Test
  public void testPeriodFull() {
    TimeSpan timeSpan = EwsUtilities.getXSDurationToTimeSpan(PERIOD_FULL);
    Assert.assertEquals("P0DT2H30M59.0S", EwsUtilities.getTimeSpanToXSDuration(timeSpan));
  }

  @Test
  public void testPeriodFullNegative() {
    TimeSpan timeSpan = EwsUtilities.getXSDurationToTimeSpan(PERIOD_FULL_NEGATIVE);
    Assert.assertEquals("-P0DT2H30M59.0S", EwsUtilities.getTimeSpanToXSDuration(timeSpan));
  }
  
  @Test
  public void testPeriodFail2() {
    TimeSpan timeSpan = EwsUtilities.getXSDurationToTimeSpan(PERIOD_OVERFLOW);
    Assert.assertEquals("P0DT3H40M59.0S", EwsUtilities.getTimeSpanToXSDuration(timeSpan));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPeriodFail() {
    TimeSpan timeSpan = EwsUtilities.getXSDurationToTimeSpan(PERIOD_FAIL);
    Assert.assertEquals("-P0DT2H30M59.0S", EwsUtilities.getTimeSpanToXSDuration(timeSpan));
  }
  
 

}
