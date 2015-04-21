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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@RunWith(JUnit4.class)
public class EwsUtilitiesTest {

  @Test
  public void testGetBuildVersion() {
    Assert.assertEquals("Build version must be 0s", "0.0.0.0", EwsUtilities.getBuildVersion());
  }

  @Test
  public void testParseDouble() throws IllegalAccessException, ParseException, InstantiationException {
    Assert.assertEquals(new Double(14.0), EwsUtilities.parse(Double.class, "14.0"));
  }

  @Test
  public void testParseNumber() throws IllegalAccessException, ParseException, InstantiationException {
    Assert.assertEquals(new Integer(14), EwsUtilities.parse(Number.class, "14"));
  }

  @Test
  public void testParseInt() throws IllegalAccessException, ParseException, InstantiationException {
    Assert.assertEquals(new Integer(14), EwsUtilities.parse(Integer.class, "14"));
  }

  @Test
  public void testParseDate() throws IllegalAccessException, ParseException, InstantiationException {
    Calendar calendar = new GregorianCalendar();
    calendar.set(Calendar.YEAR, 2015);
    calendar.set(Calendar.MONTH, 6);
    calendar.set(Calendar.DAY_OF_MONTH, 26);
    calendar.set(Calendar.HOUR_OF_DAY, 14);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
    Assert.assertEquals(calendar.getTime(), EwsUtilities.parse(Date.class, "2015-07-26T14:00:00Z"));
  }

  @Test
  public void testParseBoolean() throws IllegalAccessException, ParseException, InstantiationException {
    Assert.assertEquals(Boolean.FALSE, EwsUtilities.parse(Boolean.class, "false"));
  }

  @Test
  public void testParseString() throws IllegalAccessException, ParseException, InstantiationException {
    Assert.assertEquals("14", EwsUtilities.parse(String.class, "14"));
  }

  @Test
  public void testGetItemTypeFromXmlElementName() {
    Assert.assertEquals(Task.class, EwsUtilities.getItemTypeFromXmlElementName("Task"));
    Assert.assertEquals(EmailMessage.class, EwsUtilities.getItemTypeFromXmlElementName("Message"));
    Assert.assertEquals(PostItem.class, EwsUtilities.getItemTypeFromXmlElementName("PostItem"));
    Assert.assertEquals(SearchFolder.class, EwsUtilities.getItemTypeFromXmlElementName("SearchFolder"));
    Assert.assertEquals(Conversation.class, EwsUtilities.getItemTypeFromXmlElementName("Conversation"));
    Assert.assertEquals(Folder.class, EwsUtilities.getItemTypeFromXmlElementName("Folder"));
    Assert.assertEquals(CalendarFolder.class, EwsUtilities.getItemTypeFromXmlElementName("CalendarFolder"));
    Assert.assertEquals(MeetingMessage.class, EwsUtilities.getItemTypeFromXmlElementName("MeetingMessage"));
    Assert.assertEquals(Contact.class, EwsUtilities.getItemTypeFromXmlElementName("Contact"));
    Assert.assertEquals(Item.class, EwsUtilities.getItemTypeFromXmlElementName("Item"));
    Assert.assertEquals(Appointment.class, EwsUtilities.getItemTypeFromXmlElementName("CalendarItem"));
    Assert.assertEquals(ContactsFolder.class, EwsUtilities.getItemTypeFromXmlElementName("ContactsFolder"));
    Assert.assertEquals(MeetingRequest.class, EwsUtilities.getItemTypeFromXmlElementName("MeetingRequest"));
    Assert.assertEquals(TasksFolder.class, EwsUtilities.getItemTypeFromXmlElementName("TasksFolder"));
    Assert.assertEquals(MeetingCancellation.class, EwsUtilities.getItemTypeFromXmlElementName("MeetingCancellation"));
    Assert.assertEquals(MeetingResponse.class, EwsUtilities.getItemTypeFromXmlElementName("MeetingResponse"));
    Assert.assertEquals(ContactGroup.class, EwsUtilities.getItemTypeFromXmlElementName("DistributionList"));
  }

  @Test
  public void testEwsAssert() {
    EwsUtilities.EwsAssert(true, null, null);

    try {
      EwsUtilities.EwsAssert(false, "a", "b");
    } catch (final RuntimeException ex) {
      Assert.assertEquals("[a] b", ex.getMessage());
    }
  }

}
