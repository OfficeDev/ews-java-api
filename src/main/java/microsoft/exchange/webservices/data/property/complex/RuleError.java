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

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.property.error.RuleErrorCode;
import microsoft.exchange.webservices.data.core.enumeration.property.RuleProperty;

/**
 * Defines the RuleError class.
 */
public final class RuleError extends ComplexProperty {

  /**
   * The Rule property.
   */
  private RuleProperty ruleProperty;

  /**
   * The Rule validation error code.
   */
  private RuleErrorCode errorCode;

  /**
   * The Error message.
   */
  private String errorMessage;

  /**
   * The Field value.
   */
  private String value;

  /**
   * The Initializes a new instance of the RuleError class.
   */
  protected RuleError() {
    super();
  }

  /**
   * Gets the property which failed validation.
   *
   * @return ruleProperty
   */
  public RuleProperty getRuleProperty() {
    return this.ruleProperty;
  }

  /**
   * Gets the validation error code.
   *
   * @return ruleProperty
   */
  public RuleErrorCode getErrorCode() {
    return this.errorCode;
  }

  /**
   * Gets the error message.
   *
   * @return ruleProperty
   */
  public String getErrorMessage() {
    return this.errorMessage;
  }

  /**
   * Gets the value that failed validation.
   */
  public String getValue() {
    return this.value;
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader The reader
   * @return True if element was read
   * @throws Exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equals(XmlElementNames.FieldURI)) {
      this.ruleProperty = reader.readElementValue(RuleProperty.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.ErrorCode)) {
      this.errorCode = reader.readElementValue(RuleErrorCode.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.ErrorMessage)) {
      this.errorMessage = reader.readElementValue();
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.FieldValue)) {
      this.value = reader.readElementValue();
      return true;
    } else {
      return false;
    }
  }
}
