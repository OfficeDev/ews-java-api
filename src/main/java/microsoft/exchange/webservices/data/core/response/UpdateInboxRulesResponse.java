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

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.RuleOperationErrorCollection;

/**
 * Represents the response to a UpdateInboxRulesResponse operation.
 */
public final class UpdateInboxRulesResponse extends ServiceResponse {

  /**
   * Rule operation error collection.
   */
  private RuleOperationErrorCollection errors;

  /**
   * Initializes a new instance of the UpdateInboxRulesResponse class.
   */
  public UpdateInboxRulesResponse() {
    super();
    this.errors = new RuleOperationErrorCollection();
  }

  /**
   * Loads extra error details from XML
   *
   * @param reader         The reader.
   * @param xmlElementName The current element name of the extra error details.
   * @return True if the expected extra details is loaded,
   * False if the element name does not match the expected element.
   * @throws Exception
   */
  @Override
  protected boolean loadExtraErrorDetailsFromXml(EwsServiceXmlReader reader,
      String xmlElementName) throws Exception {
    if (xmlElementName.equals(XmlElementNames.MessageXml)) {
      return super.loadExtraErrorDetailsFromXml(reader, xmlElementName);
    } else if (xmlElementName.equals(XmlElementNames.RuleOperationErrors)) {
      this.getErrors().loadFromXml(reader,
          XmlNamespace.Messages, xmlElementName);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets the rule operation errors in the response.
   */
  public RuleOperationErrorCollection getErrors() {
    return this.errors;
  }
}
