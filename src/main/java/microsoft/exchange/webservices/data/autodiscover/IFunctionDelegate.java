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

import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;

import java.net.URI;
import java.util.List;

/**
 * The Interface FuncDelegateInterface.
 *
 * @param <T1>      the generic type
 * @param <T2>      the generic type
 * @param <TResult> the generic type
 */
public interface IFunctionDelegate<T1 extends List<?>, T2 extends List<?>, TResult> {

  /**
   * Func.
   *
   * @param arg1 the arg1
   * @param arg2 the arg2
   * @param arg3 the arg3
   * @param arg4 the arg4
   * @return the t result
   * @throws Exception the exception
   */
  TResult func(T1 arg1, T2 arg2, ExchangeVersion arg3, URI arg4) throws Exception;

}
