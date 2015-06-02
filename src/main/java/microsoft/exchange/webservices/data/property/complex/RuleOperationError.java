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
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentOutOfRangeException;

import java.util.Iterator;

/**
 * Defines the RuleOperationError class.
 */
public final class RuleOperationError extends ComplexProperty implements Iterable<RuleError> {
  /**
   * Index of the operation mapping to the error.
   */
  private int operationIndex;

  /**
   * RuleOperation object mapping to the error.
   */
  private RuleOperation operation;

  /**
   * RuleError Collection.
   */
  private RuleErrorCollection ruleErrors;

  /**
   * Initializes a new instance of the RuleOperationError class.
   */
  protected RuleOperationError() {
    super();
  }

  /**
   * Gets the operation that resulted in an error.
   *
   * @return operation
   */
  public RuleOperation getOperation() {
    return this.operation;
  }

  /**
   * Gets the number of rule errors in the list.
   *
   * @return count
   */
  public int getCount() {
    return this.ruleErrors.getCount();
  }

  /**
   * Gets the rule error at the specified index.
   *
   * @return Index
   * @throws ArgumentOutOfRangeException
   */
  public RuleError getRuleError(int index)
      throws ArgumentOutOfRangeException {
    if (index < 0 || index >= this.getCount()) {
      throw new ArgumentOutOfRangeException("index");
    }

    return this.ruleErrors.getPropertyAtIndex(index);

  }


  /**
   * Tries to read element from XML.
   *
   * @return true
   * @throws Exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equals(XmlElementNames.OperationIndex)) {
      this.operationIndex = reader.readElementValue(Integer.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.ValidationErrors)) {
      this.ruleErrors = new RuleErrorCollection();
      this.ruleErrors.loadFromXml(reader, reader.getLocalName());
      return true;
    } else {
      return false;
    }
  }

  /**
   * Set operation property by the index of a given opeation enumerator.
   */
  public void setOperationByIndex(Iterator<RuleOperation> operations) {
    for (int i = 0; i <= this.operationIndex; i++) {
      operations.next();
    }
    this.operation = operations.next();
  }

  /**
   * Gets an iterator that iterates through the elements of the collection.
   *
   * @return An Iterator for the collection.
   */
  public Iterator<RuleError> iterator() {
    return this.ruleErrors.iterator();
  }
}
