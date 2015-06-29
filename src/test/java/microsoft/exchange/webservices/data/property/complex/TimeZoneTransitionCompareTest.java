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

package microsoft.exchange.webservices.data.property.complex;

import static org.mockito.Mockito.doReturn;

import java.util.Date;

import microsoft.exchange.webservices.data.property.complex.time.AbsoluteDateTransition;
import microsoft.exchange.webservices.data.property.complex.time.TimeZoneDefinition;
import microsoft.exchange.webservices.data.property.complex.time.TimeZoneTransition;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class TimeZoneTransitionCompareTest {

  @Test
  public void testAbsoluteDateTransitionsEqual() {
    TimeZoneDefinition timeZoneDefinition = new TimeZoneDefinition();

    Date date = new Date();

    AbsoluteDateTransition first = Mockito.mock(AbsoluteDateTransition.class);
    AbsoluteDateTransition second = Mockito.mock(AbsoluteDateTransition.class);

    doReturn(date).when(first).getDateTime();
    doReturn(date).when(second).getDateTime();

    Assert.assertEquals(0, timeZoneDefinition.compare(first, second));
  }

  @Test
  public void testAbsoluteDateTransitionsLess() {
    TimeZoneDefinition timeZoneDefinition = new TimeZoneDefinition();

    Date date1 = new Date();
    Date date2 = new Date(date1.getTime() + 1);
    
    AbsoluteDateTransition first = Mockito.mock(AbsoluteDateTransition.class);
    AbsoluteDateTransition second = Mockito.mock(AbsoluteDateTransition.class);

    doReturn(date1).when(first).getDateTime();
    doReturn(date2).when(second).getDateTime();

    Assert.assertEquals(-1, timeZoneDefinition.compare(first, second));
  }

  @Test
  public void testAbsoluteDateTransitionsGreater() {
    TimeZoneDefinition timeZoneDefinition = new TimeZoneDefinition();

    Date date1 = new Date();
    Date date2 = new Date(date1.getTime() - 1);
    
    AbsoluteDateTransition first = Mockito.mock(AbsoluteDateTransition.class);
    AbsoluteDateTransition second = Mockito.mock(AbsoluteDateTransition.class);

    doReturn(date1).when(first).getDateTime();
    doReturn(date2).when(second).getDateTime();

    Assert.assertEquals(1, timeZoneDefinition.compare(first, second));
  }

  @Test
  public void testAbsoluteDateTransitionAndTimeZoneTransition() {
    TimeZoneDefinition timeZoneDefinition = new TimeZoneDefinition();

    Date date1 = new Date();
    
    AbsoluteDateTransition first = Mockito.mock(AbsoluteDateTransition.class);
    TimeZoneTransition second = Mockito.mock(TimeZoneTransition.class);

    doReturn(date1).when(first).getDateTime();

    Assert.assertEquals(1, timeZoneDefinition.compare(first, second));
  }

  @Test
  public void testAbsoluteDateTransitionAndNull() {
    TimeZoneDefinition timeZoneDefinition = new TimeZoneDefinition();
   
    Date date1 = new Date();   
    AbsoluteDateTransition first = Mockito.mock(AbsoluteDateTransition.class);  
    doReturn(date1).when(first).getDateTime();

    Assert.assertEquals(1, timeZoneDefinition.compare(first, null));
  }

  @Test
  public void testNullAndAbsoluteDateTransition() {
    TimeZoneDefinition timeZoneDefinition = new TimeZoneDefinition();
    
    Date date1 = new Date();
    AbsoluteDateTransition second = Mockito.mock(AbsoluteDateTransition.class);    
    doReturn(date1).when(second).getDateTime();

    Assert.assertEquals(-1, timeZoneDefinition.compare(null, second));
  }

  @Test
  public void testCompareSameObject() {
    TimeZoneDefinition timeZoneDefinition = new TimeZoneDefinition();

    Date date1 = new Date();    
    AbsoluteDateTransition first = Mockito.mock(AbsoluteDateTransition.class);
    doReturn(date1).when(first).getDateTime();

    Assert.assertEquals(0, timeZoneDefinition.compare(first, first));
  }

}
