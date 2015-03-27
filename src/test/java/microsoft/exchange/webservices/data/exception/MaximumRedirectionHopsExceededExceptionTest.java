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

package microsoft.exchange.webservices.data.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MaximumRedirectionHopsExceededExceptionTest {

  private final String msg = "some message";
  private final Exception rootCause = new Exception();


  @Test public void testMaximumRedirectionHopsExceededException() {

    MaximumRedirectionHopsExceededException impl = new MaximumRedirectionHopsExceededException();
    assertNull(impl.getMessage());
    assertNull(impl.getCause());
  }

  @Test public void testMaximumRedirectionHopsExceededExceptionString() {

    MaximumRedirectionHopsExceededException impl = new MaximumRedirectionHopsExceededException(msg);
    assertEquals(msg, impl.getMessage());
    assertNull(impl.getCause());
  }

  @Test public void testMaximumRedirectionHopsExceededExceptionStringException() {

    MaximumRedirectionHopsExceededException
        impl =
        new MaximumRedirectionHopsExceededException(msg, rootCause);
    assertEquals(msg, impl.getMessage());
    assertSame(rootCause, impl.getCause());
  }

}
