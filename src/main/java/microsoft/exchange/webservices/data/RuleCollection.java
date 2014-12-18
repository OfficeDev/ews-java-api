/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a collection of rules.
 */
public final class RuleCollection extends
    ComplexProperty implements Iterable<Rule> {

  /**
   * The OutlookRuleBlobExists flag.
   */
  private boolean outlookRuleBlobExists;

  /**
   * The rules in the rule collection.
   */
  private ArrayList<Rule> rules;

  /**
   * Initializes a new instance of the RuleCollection class.
   */
  protected RuleCollection() {
    super();
    this.rules = new ArrayList<Rule>();
  }

  /**
   * Gets a value indicating whether an Outlook rule blob exists in the user's
   * mailbox. To update rules with EWS when the Outlook rule blob exists, call
   * SetInboxRules passing true as the
   * value of the removeOutlookBlob parameter.
   */
  public boolean getOutlookRuleBlobExists() {
    return this.outlookRuleBlobExists;
  }

  protected void setOutlookRuleBlobExists(boolean value) {
    this.outlookRuleBlobExists = value;
  }

  /**
   * Gets the number of rules in this collection.
   */
  public int getCount() {
    return this.rules.size();
  }

  /**
   * Gets the rule at the specified index in the collection.
   *
   * @param index The index of the rule to get.
   * @return The rule at the specified index.
   * @throws ArgumentOutOfRangeException
   */
  public Rule getRule(int index) throws ArgumentOutOfRangeException {
    if (index < 0 || index >= this.rules.size()) {
      throw new ArgumentOutOfRangeException("Index");
    }

    return this.rules.get(index);

  }


  /**
   * Tries to read element from XML.
   *
   * @param reader The reader.
   * @return True if element was read.
   * @throws Exception
   */
  @Override
  protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.isStartElement(XmlNamespace.Types, XmlElementNames.Rule)) {
      Rule rule = new Rule();
      rule.loadFromXml(reader, XmlElementNames.Rule);
      this.rules.add(rule);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Get an enumerator for the collection
   */
  @Override
  public Iterator<Rule> iterator() {
    return this.rules.iterator();
  }

}
