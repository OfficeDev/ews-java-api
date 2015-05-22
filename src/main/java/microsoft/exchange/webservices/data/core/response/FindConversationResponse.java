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
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.item.Conversation;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.security.XmlNodeType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents the response to a Conversation search operation.
 */
public final class FindConversationResponse extends ServiceResponse {
  List<Conversation> conversations = new ArrayList<Conversation>();

  /**
   * Initializes a new instance of the FindConversationResponse class.
   */
  public FindConversationResponse() {
    super();
  }

  /**
   * Gets the results of the operation.
   */
  public Collection<Conversation> getConversations() {

    return this.conversations;

  }

  /**
   * Read Conversations from XML.
   *
   * @param reader The reader.
   * @throws Exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    EwsUtilities.ewsAssert(conversations != null, "FindConversationResponse.ReadElementsFromXml",
                           "conversations is null.");

    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.Conversations);
    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
          Conversation item = EwsUtilities.
              createEwsObjectFromXmlElementName(Conversation.class,
                  reader.getService(), reader.getLocalName());

          if (item == null) {
            reader.skipCurrentElement();
          } else {
            item.loadFromXml(
                reader,
                true, /* clearPropertyBag */
                null,
                false  /* summaryPropertiesOnly */);

            conversations.add(item);
          }
        }
      }
      while (!reader.isEndElement(XmlNamespace.Messages,
          XmlElementNames.Conversations));
    }
  }
}
