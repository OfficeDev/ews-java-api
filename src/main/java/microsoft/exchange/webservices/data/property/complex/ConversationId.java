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
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentNullException;

/**
 * Represents the Id of a Conversation.
 */
public class ConversationId extends ServiceId {

  /**
   * Initializes a new instance of the ConversationId class.
   */
  public ConversationId() {
    super();
  }

  /**
   * Defines an implicit conversion between string and ConversationId.
   *
   * @param uniqueId the unique id
   * @return A ConversationId initialized with the specified unique Id.
   * @throws Exception the exception
   */
  public static ConversationId getConversationIdFromUniqueId(String uniqueId)
      throws Exception {
    return new ConversationId(uniqueId);
  }

  /**
   * Defines an implicit conversion between ConversationId and String.
   *
   * @param conversationId the conversation id
   * @return A ConversationId initialized with the specified unique Id.
   * @throws ArgumentNullException the argument null exception
   */
  public static String getStringFromConversationId(
      ConversationId conversationId) throws ArgumentNullException {
    if (conversationId == null) {
      throw new ArgumentNullException("conversationId");
    }

    if (null == conversationId.getUniqueId()
        || conversationId.getUniqueId().isEmpty()) {
      return "";
    } else {
      // Ignoring the change key info
      return conversationId.getUniqueId();
    }
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  public String getXmlElementName() {
    return XmlElementNames.ConversationId;
  }

  /**
   * Initializes a new instance of ConversationId.
   *
   * @param uniqueId the unique id
   * @throws Exception the exception
   */
  public ConversationId(String uniqueId) throws Exception {
    super(uniqueId);
  }

  /**
   * Gets a string representation of the Conversation Id.
   *
   * @return The string representation of the conversation id.
   */
  @Override
  public String toString() {
    // We have ignored the change key portion
    return this.getUniqueId();
  }
}
