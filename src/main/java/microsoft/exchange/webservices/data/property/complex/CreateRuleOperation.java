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

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;

/**
 * Represents an operation to create a new rule.
 */
public final class CreateRuleOperation extends RuleOperation {


  /**
   * Inbox rule to be created.
   */
  private Rule rule;

  /**
   * Initializes a new instance of the
   * <see cref="CreateRuleOperation"/> class.
   */
  public CreateRuleOperation() {
    super();
  }

  /**
   * Initializes a new instance of the
   * <see cref="CreateRuleOperation"/> class.
   *
   * @param rule The inbox rule to create.
   */
  public CreateRuleOperation(Rule rule) {
    super();
    this.rule = rule;
  }

  /**
   * Gets or sets the rule to be created.
   */
  public Rule getRule() {

    return this.rule;
  }

  public void setRule(Rule value) {

    if (this.canSetFieldValue(this.rule, value)) {
      this.rule = value;
      this.changed();

    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer The writer.
   * @throws Exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    this.getRule().writeToXml(writer, XmlElementNames.Rule);
  }

  /**
   * Validates this instance.
   *
   * @throws Exception
   */
  @Override
  protected void internalValidate() throws Exception {
    EwsUtilities.validateParam(this.rule, "Rule");
  }

  /**
   * Gets the Xml element name of the CreateRuleOperation object.
   */
  @Override public String getXmlElementName() {

    return XmlElementNames.CreateRuleOperation;
  }

}
