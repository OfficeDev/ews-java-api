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

package microsoft.exchange.webservices.data.security;

import microsoft.exchange.webservices.data.core.exception.misc.ArgumentNullException;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentOutOfRangeException;

/**
 * Table of atomized String objects.
 */
public abstract class XmlNameTable {

  /**
   * Initializes a new instance of the XmlNameTable class.
   */
  protected XmlNameTable() {
  }

  /**
   * When overridden in a derived class, atomizes the specified String and
   * adds it to the XmlNameTable.
   *
   * @param array : The name to add.
   * @return The new atomized String or the existing one if it already exists.
   * @throws ArgumentNullException array is null.
   */
  public abstract String Add(String array);

  /**
   * Reads an XML Schema from the supplied stream.
   *
   * @param array  The character array containing the name to add.
   * @param offset Zero-based index into the array specifying the first character
   *               of the name.
   * @param length The number of characters in the name.
   * @return The new atomized String or the existing one if it already exists.
   * If length is zero, String.Empty is returned
   * @throws ArgumentOutOfRangeException 0 > offset -or- offset >= array.Length -or- length >
   *                                     array.Length The above conditions do not cause an exception
   *                                     to be thrown if length =0.
   * @throws ArgumentOutOfRangeException length < 0.
   */
  public abstract String Add(char[] array, int offset, int length);

  /**
   * When overridden in a derived class, gets the atomized String containing
   * the same value as the specified String.
   *
   * @param array The name to look up.
   * @return The atomized String or null if the String has not already been
   * atomized.
   * @throws ArgumentNullException : array is null.
   */
  public abstract String Get(String array);

  /**
   * When overridden in a derived class, gets the atomized String containing
   * the same characters as the specified range of characters in the given
   * array.
   *
   * @param array  The character array containing the name to add.
   * @param offset Zero-based index into the array specifying the first character
   *               of the name.
   * @param length The number of characters in the name.
   * @return The atomized String or null if the String has not already been
   * atomized. If length is zero, String.Empty is returned
   * @throws ArgumentOutOfRangeException 0 > offset -or- offset >= array.Length -or- length >
   *                                     array.Length The above conditions do not cause an exception
   *                                     to be thrown if length =0.
   * @throws ArgumentOutOfRangeException length < 0.
   */
  public abstract String Get(char[] array, int offset, int length);

}
