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

package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.request.HttpClientWebRequest;
import microsoft.exchange.webservices.data.core.request.HttpWebRequest;
import microsoft.exchange.webservices.data.core.exception.http.EWSHttpException;
import microsoft.exchange.webservices.data.core.exception.http.HttpErrorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.concurrent.Callable;

public class CallableMethod implements Callable<Object> {

  private static final Log LOG = LogFactory.getLog(CallableMethod.class);

  HttpWebRequest request;

  public CallableMethod(HttpWebRequest request) {
    this.request = request;
  }

  protected HttpClientWebRequest executeMethod() throws EWSHttpException, HttpErrorException, IOException {

    request.executeRequest();
    return (HttpClientWebRequest) request;
  }

  public HttpWebRequest call() {

    try {
      return executeMethod();
    } catch (EWSHttpException e) {
      // TODO Auto-generated catch block
      LOG.error(e);
    } catch (HttpErrorException e) {
      // TODO Auto-generated catch block
      LOG.error(e);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      LOG.error(e);
    }
    return request;
  }
}
