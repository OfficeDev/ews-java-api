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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.property.complex.recurrence.pattern.Recurrence.MonthlyPattern;
import microsoft.exchange.webservices.data.property.complex.recurrence.pattern.Recurrence.YearlyPattern;

import org.junit.Test;
import org.mockito.Mockito;

public class RecurrenceReaderTest {

  @Test
  public void testMonthlyPattern() throws Exception {

    EwsServiceXmlReader reader = Mockito.mock(EwsServiceXmlReader.class);
    doReturn(XmlElementNames.DayOfMonth).when(reader).getLocalName();
    doReturn(1).when(reader).readElementValue(Integer.class);

    MonthlyPattern monthly = new MonthlyPattern();
    monthly.tryReadElementFromXml(reader);

    assertEquals(1, monthly.getDayOfMonth());
  }

  @Test
  public void testYearlyPattern() throws Exception {

    EwsServiceXmlReader reader = Mockito.mock(EwsServiceXmlReader.class);
    doReturn(XmlElementNames.DayOfMonth).when(reader).getLocalName();
    doReturn(1).when(reader).readElementValue(Integer.class);

    YearlyPattern yearly = new YearlyPattern();
    yearly.tryReadElementFromXml(reader);

    assertEquals(1, yearly.getDayOfMonth());
  }

}
