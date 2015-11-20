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

package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.core.XmlElementNames;

/**
 * Represents the Id of an Exchange item.
 */
public class ItemId extends ServiceId {

  /**
   * Initializes a new instance.
   */
  public ItemId() {
    super();
  }

  /**
   * Defines an implicit conversion between string and ItemId.
   *
   * @param uniqueId The unique Id to convert to ItemId.
   * @return An ItemId initialized with the specified unique Id.
   * @throws Exception the exception
   */
  public static ItemId getItemIdFromString(String uniqueId) throws Exception {
    return new ItemId(uniqueId);
  }

  /**
   * Initializes a new instance of ItemId.
   *
   * @param uniqueId The unique Id used to initialize the ItemId.
   * @throws Exception the exception
   */
  public ItemId(String uniqueId) throws Exception {
    super(uniqueId);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  public String getXmlElementName() {
    return XmlElementNames.ItemId;
  }
}
