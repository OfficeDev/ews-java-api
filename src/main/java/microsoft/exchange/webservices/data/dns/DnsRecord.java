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

package microsoft.exchange.webservices.data.dns;

import microsoft.exchange.webservices.data.core.exception.dns.DnsException;

/**
 * Represents a DnsRecord.
 */
abstract class DnsRecord {
        /*
         * Name field of this DNS Record
	 */
  /**
   * The name.
   */
  private String name;
        /*
	 * The suggested time for this dnsRecord to be valid
	 */
  /**
   * The time to live.
   */
  private int timeToLive;

  /**
   * Retrieves the value of the name property.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the value of the timeToLive property.
   *
   * @return timeToLive
   */
  public int getTimeToLive() {
    return timeToLive;
  }

  /**
   * loads the DNS Record.
   *
   * @param value the value
   * @throws DnsException the dns exception
   */
  protected void load(String value) throws DnsException {

  }
}
