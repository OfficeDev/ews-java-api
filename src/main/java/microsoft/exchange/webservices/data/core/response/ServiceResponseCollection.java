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

package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceResult;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * Represents a strongly typed list of service response.
 *
 * @param <TResponse> The type of response stored in the list.
 */
public final class ServiceResponseCollection<TResponse extends ServiceResponse>
    implements Iterable<TResponse> {

  /**
   * The response.
   */
  private Vector<TResponse> responses = new Vector<TResponse>();

  /**
   * The overall result.
   */
  private ServiceResult overallResult = ServiceResult.Success;

  /**
   * Initializes a new instance.
   */
  public ServiceResponseCollection() {

  }

  /**
   * Adds specified response.
   *
   * @param response The response.
   */
  public void add(TResponse response) {
    EwsUtilities.ewsAssert(response != null, "EwsResponseList.Add", "response is null");
    if (response.getResult().ordinal() > this.overallResult.ordinal()) {
      this.overallResult = response.getResult();
    }
    this.responses.add(response);
  }

  /**
   * Gets the total number of response in the list.
   *
   * @return total number of response in the list.
   */
  public int getCount() {
    return this.responses.size();
  }

  /**
   * Gets the response at the specified index.
   *
   * @param index The zero-based index of the response to get.
   * @return The response at the specified index.
   * @throws IndexOutOfBoundsException the index out of bounds exception
   */
  public TResponse getResponseAtIndex(int index)
      throws IndexOutOfBoundsException {
    if (index < 0 || index >= this.getCount()) {
      throw new IndexOutOfBoundsException("Index out of Range");
    }
    return this.responses.get(index);
  }

  /**
   * Gets a value indicating the overall result of the request that
   * generated this response collection. If all of the response have their
   * Result property set to Success, OverallResult returns Success. If at
   * least one response has its Result property set to Warning and all other
   * response have their Result property set to Success, OverallResult
   * returns Warning. If at least one response has a its Result set to Error,
   * OverallResult returns Error.
   *
   * @return the overall result
   */
  public ServiceResult getOverallResult() {
    return this.overallResult;
  }

  /**
   * Returns an iterator over a set of elements of type T.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<TResponse> iterator() {
    return responses.iterator();
  }

  /**
   * Gets the enumerator.
   *
   * @return the enumerator
   */
  public Enumeration<TResponse> getEnumerator() {
    return this.responses.elements();
  }
}
