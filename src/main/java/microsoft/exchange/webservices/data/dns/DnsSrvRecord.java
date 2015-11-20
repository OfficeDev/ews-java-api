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

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Represents a DNS SRV Record.
 */
public class DnsSrvRecord extends DnsRecord {
        /*
         * The string representing the target host
	 */
  /**
   * The target.
   */
  private String target;

	/*
         * priority of the target host specified in the owner name.
	 */
  /**
   * The priority.
   */
  private int priority;
	/*
	 * weight of the target host
	 */
  /**
   * The weight.
   */
  private int weight;
	/*
	 * port used on the target for the service
	 */
  /**
   * The port.
   */
  private int port;

  /**
   * Retrieves the value of the target property.
   *
   * @return target
   */
  public String getNameTarget() {
    return this.target;
  }

  /**
   * Retrieves the value of the priority property.
   *
   * @return priority
   */
  public int getPriority() {
    return priority;
  }

  /**
   * Retrieves the value of the weight property.
   *
   * @return weight
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Retrieves the value of the port property.
   *
   * @return port
   */
  public int getPort() {
    return port;
  }

  /**
   * Initializes a new instance of the DnsSrvRecord class.
   *
   * @param srvRecord srvRecord that is fetched from JNDI
   * @throws DnsException the dns exception
   */
  protected void load(String srvRecord) throws DnsException {
    super.load(null);
    StringTokenizer strTokens = new StringTokenizer(srvRecord);
    try {
      while (strTokens.hasMoreTokens()) {
        String priority = strTokens.nextToken();
        this.priority = Integer.parseInt(priority);

        String weight = strTokens.nextToken();
        this.weight = Integer.parseInt(weight);

        String port = strTokens.nextToken();
        this.port = Integer.parseInt(port);

        String target = strTokens.nextToken();
        this.target = target;
      }
    } catch (NumberFormatException ne) {
      throw new DnsException("NumberFormatException " + ne.getMessage());
    } catch (NoSuchElementException ne) {
      throw new DnsException("NoSuchElementException " + ne.getMessage());
    }

  }
}
