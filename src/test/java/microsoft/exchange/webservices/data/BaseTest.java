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

package microsoft.exchange.webservices.data;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * A base class with "Common-Services"
 */
@RunWith(JUnit4.class)
public abstract class BaseTest {

  /**
   * Mock for the ExchangeServiceBase
   */
  protected static ExchangeServiceBase exchangeServiceBaseMock;

  /**
   * Mock for the ExchangeService
   */
  protected static ExchangeService exchangeServiceMock;

  /**
   * Setup Mocks
   */
  @BeforeClass
  public static final void setUpBaseClass() throws Exception {
    // Mock up ExchangeServiceBase
    exchangeServiceBaseMock = new ExchangeServiceBase() {
      @Override
      protected void processHttpErrorResponse(HttpWebRequest httpWebResponse,
                                              Exception webException)
          throws Exception {
        throw webException;
      }
    };
    exchangeServiceMock = new ExchangeService();
  }
}
