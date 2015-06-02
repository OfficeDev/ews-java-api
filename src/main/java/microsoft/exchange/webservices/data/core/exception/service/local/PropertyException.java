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

package microsoft.exchange.webservices.data.core.exception.service.local;

import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;

/**
 * Represents an error that occurs when an operation on a property fails.
 */
public class PropertyException extends ServiceLocalException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The name.
   */
  private String name;

  /**
   * Instantiates a new property exception.
   */
  public PropertyException() {
    super();
  }

  /**
   * Instantiates a new property exception.
   *
   * @param name the name
   */
  public PropertyException(String name) {
    super();
    this.name = name;
  }

  /**
   * Instantiates a new property exception.
   *
   * @param message the message
   * @param name    the name
   */
  public PropertyException(String message, String name) {
    super(message);
    this.name = name;
  }

  /**
   * Instantiates a new property exception.
   *
   * @param message        the message
   * @param name           the name
   * @param innerException the inner exception
   */
  public PropertyException(String message, String name,
      Exception innerException) {
    super(message, innerException);
    this.name = name;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

}
