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

package microsoft.exchange.webservices.data.property.complex.time;


import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.util.TimeZoneUtils;

import java.util.Date;
import java.util.TimeZone;

/**
 * A TimeZoneDefinition class that allows mapping from a Java/Olson TimeZone to a MS TimeZone.
 */
public class OlsonTimeZoneDefinition extends TimeZoneDefinition {

  /**
   * Create a TimeZoneDefinition compatible with java.util.TimeZone
   * @param timeZone a java time zone object, will be converted to Microsoft timezone.
   */
  public OlsonTimeZoneDefinition(TimeZone timeZone) {
    final String microsoftTimeZoneName = TimeZoneUtils.getMicrosoftTimeZoneName(timeZone);
    if (microsoftTimeZoneName != null) {
      this.id = microsoftTimeZoneName;
    }
    this.name = timeZone.getDisplayName(timeZone.inDaylightTime(new Date()), TimeZone.LONG);
  }

  @Override
  public void validate() throws ServiceLocalException {
    if (this.id == null) {
      throw new ServiceLocalException("Invalid TimeZone (" + this.name + ") Specified");
    }
  }
}