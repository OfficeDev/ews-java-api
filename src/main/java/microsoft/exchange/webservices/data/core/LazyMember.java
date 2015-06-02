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

/**
 * Wrapper class for lazy members. Does lazy initialization of member on first
 * access.
 *
 * @param <T> Type of the lazy member
 *            <p>
 *            If we find ourselves creating a whole bunch of these in our code,
 *            we need to rethink this. Each lazy member holds the actual member
 *            and a delegate. That can turn into a whole lot of overhead
 *            </p>
 */
public class LazyMember<T> {

  /**
   * The lazy member.
   */
  private volatile T lazyMember;

  /**
   * The lazy implementation.
   */
  private final ILazyMember<T> lazyImplementation;

  /**
   * Public accessor for the lazy member. Lazy initializes the member on first
   * access
   *
   * @return the member
   */
  public T getMember() {
    T result = this.lazyMember;
    if (result == null) {  // first check (no locking)
      synchronized (this) {
        result = this.lazyMember;
        if (result == null) { // second check (with locking)
          this.lazyMember = result = lazyImplementation.createInstance();
        }
      }
    }
    return result;
  }

  /**
   * Constructor.
   *
   * @param lazyImplementation The initialization delegate to call for the item on first
   *                           access
   */
  public LazyMember(ILazyMember<T> lazyImplementation) {
    this.lazyImplementation = lazyImplementation;
  }
}
