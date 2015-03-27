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
 * Represents a collection of rule validation errors.
 */
public final class RuleErrorCollection extends ComplexPropertyCollection<RuleError> {

  /**
   * Initializes a new instance of the RuleErrorCollection class.
   */
  protected RuleErrorCollection() {
    super();
  }

  /**
   * Creates an RuleError object from an XML element name.
   *
   * @param xmlElementName The XML element name from
   *                       which to create the RuleError object.
   * @return A RuleError object.
   */
  @Override
  protected RuleError createComplexProperty(String xmlElementName) {
    if (xmlElementName.equals(XmlElementNames.Error)) {
      return new RuleError();
    } else {
      return null;
    }
  }

  /**
   * Retrieves the XML element name corresponding
   * to the provided RuleError object.
   *
   * @param ruleValidationError The RuleError object from which
   *                            to determine the XML element name.
   * @return The XML element name corresponding
   * to the provided RuleError object.
   */
  @Override
  protected String getCollectionItemXmlElementName(RuleError
      ruleValidationError) {
    return XmlElementNames.Error;
  }
}
