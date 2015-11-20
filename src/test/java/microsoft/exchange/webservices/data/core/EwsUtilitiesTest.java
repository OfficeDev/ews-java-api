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

package microsoft.exchange.webservices.data.core;

import static org.junit.Assert.assertEquals;

import microsoft.exchange.webservices.data.core.service.folder.CalendarFolder;
import microsoft.exchange.webservices.data.core.service.folder.ContactsFolder;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.folder.SearchFolder;
import microsoft.exchange.webservices.data.core.service.folder.TasksFolder;
import microsoft.exchange.webservices.data.core.service.item.Appointment;
import microsoft.exchange.webservices.data.core.service.item.Contact;
import microsoft.exchange.webservices.data.core.service.item.ContactGroup;
import microsoft.exchange.webservices.data.core.service.item.Conversation;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.item.MeetingCancellation;
import microsoft.exchange.webservices.data.core.service.item.MeetingMessage;
import microsoft.exchange.webservices.data.core.service.item.MeetingRequest;
import microsoft.exchange.webservices.data.core.service.item.MeetingResponse;
import microsoft.exchange.webservices.data.core.service.item.PostItem;
import microsoft.exchange.webservices.data.core.service.item.Task;
import microsoft.exchange.webservices.data.misc.TimeSpan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(JUnit4.class)
public class EwsUtilitiesTest {

  @Test
  public void testGetBuildVersion() {
    assertEquals("Build version must be 0s", "0.0.0.0", EwsUtilities.getBuildVersion());
  }

  @Test
  public void testGetItemTypeFromXmlElementName() {
    assertEquals(Task.class, EwsUtilities.getItemTypeFromXmlElementName("Task"));
    assertEquals(EmailMessage.class, EwsUtilities.getItemTypeFromXmlElementName("Message"));
    assertEquals(PostItem.class, EwsUtilities.getItemTypeFromXmlElementName("PostItem"));
    assertEquals(SearchFolder.class, EwsUtilities.getItemTypeFromXmlElementName("SearchFolder"));
    assertEquals(Conversation.class, EwsUtilities.getItemTypeFromXmlElementName("Conversation"));
    assertEquals(Folder.class, EwsUtilities.getItemTypeFromXmlElementName("Folder"));
    assertEquals(CalendarFolder.class, EwsUtilities.getItemTypeFromXmlElementName("CalendarFolder"));
    assertEquals(MeetingMessage.class, EwsUtilities.getItemTypeFromXmlElementName("MeetingMessage"));
    assertEquals(Contact.class, EwsUtilities.getItemTypeFromXmlElementName("Contact"));
    assertEquals(Item.class, EwsUtilities.getItemTypeFromXmlElementName("Item"));
    assertEquals(Appointment.class, EwsUtilities.getItemTypeFromXmlElementName("CalendarItem"));
    assertEquals(ContactsFolder.class, EwsUtilities.getItemTypeFromXmlElementName("ContactsFolder"));
    assertEquals(MeetingRequest.class, EwsUtilities.getItemTypeFromXmlElementName("MeetingRequest"));
    assertEquals(TasksFolder.class, EwsUtilities.getItemTypeFromXmlElementName("TasksFolder"));
    assertEquals(MeetingCancellation.class, EwsUtilities.getItemTypeFromXmlElementName("MeetingCancellation"));
    assertEquals(MeetingResponse.class, EwsUtilities.getItemTypeFromXmlElementName("MeetingResponse"));
    assertEquals(ContactGroup.class, EwsUtilities.getItemTypeFromXmlElementName("DistributionList"));
  }

  @Test
  public void testEwsAssert() {
    EwsUtilities.ewsAssert(true, null, null);

    try {
      EwsUtilities.ewsAssert(false, "a", "b");
    } catch (final RuntimeException ex) {
      assertEquals("[a] b", ex.getMessage());
    }
  }

  @Test
  public void testParseBigInt() throws ParseException {
    assertEquals(BigInteger.TEN, EwsUtilities.parse(BigInteger.class, BigInteger.TEN.toString()));
  }

  @Test
  public void testParseBigDec() throws ParseException {
    assertEquals(BigDecimal.TEN, EwsUtilities.parse(BigDecimal.class, BigDecimal.TEN.toString()));
  }

  @Test
  public void testParseString() throws ParseException {
    final String input = "lorem ipsum dolor sit amet";
    assertEquals(input, EwsUtilities.parse(input.getClass(), input));
  }

  @Test
  public void testParseDouble() throws ParseException {
    Double input = Double.MAX_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = 0.0;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Double.MIN_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseInteger() throws ParseException {
    Integer input = Integer.MAX_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = 0;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Integer.MIN_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseBoolean() throws ParseException {
    Boolean input = Boolean.TRUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Boolean.FALSE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseLong() throws ParseException {
    Long input = Long.MAX_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = 0l;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Long.MIN_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseFloat() throws ParseException {
    Float input = Float.MAX_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = 0f;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Float.MIN_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseShort() throws ParseException {
    Short input = Short.MAX_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = 0;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Short.MIN_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseByte() throws ParseException {
    Byte input = Byte.MAX_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = 0;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));

    input = Byte.MIN_VALUE;
    assertEquals(input, EwsUtilities.parse(input.getClass(), input.toString()));
  }

  @Test
  public void testParseDate() throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    String input = sdf.format(new Date());
    assertEquals(input, EwsUtilities.parse(input.getClass(), input));
  }

  @Test
  public void testParseNullValue() throws ParseException {
    final String input = null;
    assertEquals(input, EwsUtilities.parse(String.class, input));
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
}
