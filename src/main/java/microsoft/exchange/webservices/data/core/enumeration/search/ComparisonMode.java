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

package microsoft.exchange.webservices.data.core.enumeration.search;

/**
 * Defines the way values are compared in search filter.
 */
public enum ComparisonMode {

  // The comparison is exact.
  /**
   * The Exact.
   */
  Exact,

  // The comparison ignores casing.
  /**
   * The Ignore case.
   */
  IgnoreCase,

  // The comparison ignores spacing characters.
  /**
   * The Ignore non spacing characters.
   */
  IgnoreNonSpacingCharacters,

  // The comparison ignores casing and spacing characters.
  /**
   * The Ignore case and non spacing characters.
   */
  IgnoreCaseAndNonSpacingCharacters

  // From bug E12:113326
  //
  // Although the following four values are defined in
  // the EWS schema, they are useless
  // as they are all thechnically equivalent to Loose.
  // We are not exposing those values
  // in this API. When we encounter one of these
  // values on an existing search folder
  // restriction, we map it to IgnoreCaseAndNonSpacingCharacters.
  //
  // Loose,
  // LooseAndIgnoreCase,
  // LooseAndIgnoreNonSpace,
  // LooseAndIgnoreCaseAndIgnoreNonSpace
}
