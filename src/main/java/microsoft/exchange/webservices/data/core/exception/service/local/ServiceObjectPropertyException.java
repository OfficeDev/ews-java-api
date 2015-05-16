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

import microsoft.exchange.webservices.data.property.definition.PropertyDefinitionBase;

/**
 * Represents an error that occurs when an operation on a property fails.
 */
public class ServiceObjectPropertyException extends PropertyException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The property definition.
   */
  private PropertyDefinitionBase propertyDefinition;

  /**
   * ServiceObjectPropertyException constructor.
   *
   * @param propertyDefinition The definition of the property that is at the origin of the
   *                           exception.
   */
  public ServiceObjectPropertyException(
      PropertyDefinitionBase propertyDefinition) {
    super(propertyDefinition.getPrintableName());
    this.propertyDefinition = propertyDefinition;
  }

  /**
   * ServiceObjectPropertyException constructor.
   *
   * @param message            Error message text.
   * @param propertyDefinition The definition of the property that is at the origin of the
   *                           exception.
   */
  public ServiceObjectPropertyException(String message,
      PropertyDefinitionBase propertyDefinition) {
    super(message, propertyDefinition.getPrintableName());
    this.propertyDefinition = propertyDefinition;
  }

  /**
   * ServiceObjectPropertyException constructor.
   *
   * @param message            Error message text.
   * @param propertyDefinition The definition of the property that is at the origin of the
   *                           exception.
   * @param innerException     the inner exception
   */
  public ServiceObjectPropertyException(String message,
      PropertyDefinitionBase propertyDefinition,
      Exception innerException) {
    super(message, propertyDefinition.getPrintableName(), innerException);
    this.propertyDefinition = propertyDefinition;
  }

  /**
   * The definition of the property that is at the origin of the exception.
   *
   * @return The definition of the property.
   */
  public PropertyDefinitionBase getPropertyDefinition() {
    return propertyDefinition;
  }

}
