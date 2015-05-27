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

package microsoft.exchange.webservices.data.core.enumeration.property;

/**
 * Defines base property sets that are used as the base for custom property
 * sets.
 */
public enum BasePropertySet {

  // Only includes the Id of item and folder.
  /**
   * The Id only.
   */
  IdOnly("IdOnly"),

  // Includes all the first class property of item and folder.
  /**
   * The First class property.
   */
  FirstClassProperties("AllProperties");

  /**
   * The base shape value.
   */
  private String baseShapeValue;

  /**
   * Instantiates a new base property set.
   *
   * @param baseShapeValue the base shape value
   */
  BasePropertySet(String baseShapeValue) {
    this.baseShapeValue = baseShapeValue;
  }

  /**
   * Gets the base shape value.
   *
   * @return the base shape value
   */
  public String getBaseShapeValue() {
    return this.baseShapeValue;
  }

}
