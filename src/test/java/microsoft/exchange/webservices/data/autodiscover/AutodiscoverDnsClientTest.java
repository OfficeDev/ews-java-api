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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AutodiscoverDnsClientTest {

  final String validResponse = "autodiscover.contoso.com";
  final String trailingDotResponse = "autodiscover.contoso.com.";

  /**
   * If DNS gives us a null, we should return a null
   */
  @Test public void textExtractNullHostnameFromDnsSrv() {
    assertEquals(AutodiscoverDnsClient.extractHostnameFromDnsSrv(null), null);
  }

  /**
   * If DNS gives us back an empty string, we should return a null
   */
  @Test public void textExtractEmptyHostnameFromDnsSrv() {
    assertEquals(AutodiscoverDnsClient.extractHostnameFromDnsSrv(""), null);
  }

  /**
   * If DNS gives us back a plain domain, we should pass it through.
   */
  @Test public void textExtractValidHostnameFromDnsSrv() {
    assertEquals(AutodiscoverDnsClient.extractHostnameFromDnsSrv(validResponse), validResponse);
  }

  /**
   * If DNS gives us back a domain with a trailing dot, we should strip it.
   */
  @Test public void textExtractTrailingDotHostnameFromDnsSrv() {
    assertEquals(AutodiscoverDnsClient.extractHostnameFromDnsSrv(trailingDotResponse), validResponse);
  }
}
