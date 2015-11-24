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

package microsoft.exchange.webservices.data.autodiscover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import microsoft.exchange.webservices.data.core.EwsXmlReader;

public class AlternateMailboxCollectionTest {

  private static final String PARTIAL_RESULT =
      "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:a=\"http://www.w3.org/2005/08/addressing\">"
          + "<s:Header><a:Action s:mustUnderstand=\"1\">http://schemas.microsoft.com/exchange/2010/Autodiscover/Autodiscover/GetUserSettingsResponse</a:Action>"
          + "<h:ServerVersionInfo xmlns:h=\"http://schemas.microsoft.com/exchange/2010/Autodiscover\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">"
          + "<h:MajorVersion>15</h:MajorVersion><h:MinorVersion>0</h:MinorVersion><h:MajorBuildNumber>1076</h:MajorBuildNumber>"
          + "<h:MinorBuildNumber>0</h:MinorBuildNumber><h:Version>Exchange2013_SP1</h:Version></h:ServerVersionInfo>"
          + "</s:Header>"
          + "<s:Body><GetUserSettingsResponseMessage xmlns=\"http://schemas.microsoft.com/exchange/2010/Autodiscover\">"
          + "<Response xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">"
          + "<UserResponses><UserResponse><UserSettings><UserSetting i:type=\"AlternateMailboxCollectionSetting\">"
          + "<Name>AlternateMailboxes</Name><AlternateMailboxes>"
          + "<AlternateMailbox><Type>Archive</Type>"
          + "<DisplayName>Personal Archive - Testaccount008</DisplayName>"
          + "<LegacyDN>ou=Exchange Administrative Group/cn=Testaccount008 Test</LegacyDN>"
          + "<Server>cb7c4056-a738-4004-bd0d-65b58d3b7bc1@test.com</Server>"
          + "<OwnerSmtpAddress>testaccount008@test.com</OwnerSmtpAddress>"
          + "</AlternateMailbox><AlternateMailbox><Type>Delegate</Type>"
          + "<DisplayName>Testaccount007 Test</DisplayName>"
          + "<SmtpAddress>testaccount007@test.com</SmtpAddress>"
          + "<OwnerSmtpAddress>testaccount007@test.com</OwnerSmtpAddress>"
          + "</AlternateMailbox></AlternateMailboxes></UserSetting></UserSettings>"
          + "</UserResponse></UserResponses></Response></GetUserSettingsResponseMessage></s:Body></s:Envelope>";


  @Test
  public void testGetEntries() throws Exception {
    final AlternateMailboxCollection amc =
        AlternateMailboxCollection.loadFromXml(new EwsXmlReader(
            new ByteArrayInputStream(PARTIAL_RESULT.getBytes())));

    assertEquals(2, amc.getEntries().size());

    final AlternateMailbox first = amc.getEntries().get(0);
    assertEquals("Archive", first.getType());
    assertEquals("Personal Archive - Testaccount008", first.getDisplayName());
    assertEquals("ou=Exchange Administrative Group/cn=Testaccount008 Test",
        first.getLegacyDN());
    assertEquals("cb7c4056-a738-4004-bd0d-65b58d3b7bc1@test.com",
        first.getServer());
    assertNull(first.getSmtpAddress());
    assertEquals("testaccount008@test.com", first.getOwnerSmtpAddress());

    final AlternateMailbox second = amc.getEntries().get(1);
    assertEquals("Delegate", second.getType());
    assertEquals("Testaccount007 Test", second.getDisplayName());
    assertNull(second.getLegacyDN());
    assertNull(second.getServer());
    assertEquals("testaccount007@test.com", second.getSmtpAddress());
    assertEquals("testaccount007@test.com", second.getOwnerSmtpAddress());
  }
}
