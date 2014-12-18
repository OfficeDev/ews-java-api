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
import java.util.List;

/**
 * Represents a user setting that is a collection of alternate mailboxes.
 */
public final class AlternateMailboxCollection {

  private ArrayList<AlternateMailbox> entries;

  /**
   * Initializes a new instance of the class
   */
  protected AlternateMailboxCollection() {
    this.setEntries(new ArrayList<AlternateMailbox>());
  }

  /**
   * Loads instance of AlternateMailboxCollection from XML.
   *
   * @param reader the reader
   * @return AlternateMailboxCollection
   * @throws Exception the exception
   */
  protected static AlternateMailboxCollection loadFromXml(EwsXmlReader reader)
      throws Exception {
    AlternateMailboxCollection instance = new AlternateMailboxCollection();

    do {
      reader.read();

      if ((reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) &&
          (reader.getLocalName()
              .equals(XmlElementNames.AlternateMailbox))) {
        instance.getEntries().add(
            AlternateMailbox.loadFromXml(reader));
      }
    } while (!reader.isEndElement(XmlNamespace.Autodiscover,
        XmlElementNames.AlternateMailbox));

    return instance;
  }

  /**
   * Gets the collection of alternate mailboxes.
   */
  public List<AlternateMailbox> getEntries() {
    return this.entries;
  }

  private void setEntries(ArrayList<AlternateMailbox> value) {
    this.entries = value;
  }
}
