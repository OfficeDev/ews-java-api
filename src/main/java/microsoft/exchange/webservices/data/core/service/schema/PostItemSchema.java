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

package microsoft.exchange.webservices.data.core.service.schema;

import microsoft.exchange.webservices.data.attribute.Schema;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.property.definition.DateTimePropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;

import java.util.EnumSet;

/**
 * Represents the schema for post item.
 */
@Schema
public final class PostItemSchema extends ItemSchema {

  /**
   * Field URIs for PostItem.
   */
  private static interface FieldUris {

    /**
     * The Posted time.
     */
    String PostedTime = "postitem:PostedTime";
  }


  /**
   * Defines the ConversationIndex property.
   */
  public static final PropertyDefinition ConversationIndex =
      EmailMessageSchema.ConversationIndex;

  /**
   * Defines the ConversationTopic property.
   */
  public static final PropertyDefinition ConversationTopic =
      EmailMessageSchema.ConversationTopic;

  /**
   * Defines the From property.
   */
  public static final PropertyDefinition From = EmailMessageSchema.From;

  /**
   * Defines the InternetMessageId property.
   */
  public static final PropertyDefinition InternetMessageId =
      EmailMessageSchema.InternetMessageId;

  /**
   * Defines the IsRead property.
   */
  public static final PropertyDefinition IsRead = EmailMessageSchema.IsRead;

  /**
   * Defines the PostedTime property.
   */
  public static final PropertyDefinition PostedTime =
      new DateTimePropertyDefinition(
          XmlElementNames.PostedTime, FieldUris.PostedTime, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the References property.
   */
  public static final PropertyDefinition References =
      EmailMessageSchema.References;

  /**
   * Defines the Sender property.
   */
  public static final PropertyDefinition Sender = EmailMessageSchema.Sender;

  // This must be after the declaration of property definitions
  /**
   * The Constant Instance.
   */
  public static final PostItemSchema Instance = new PostItemSchema();

  /**
   * Registers property.
   * <p/>
   * IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
   * same order as they are defined in types.xsd)
   */
  @Override
  protected void registerProperties() {
    super.registerProperties();

    this.registerProperty(ConversationIndex);
    this.registerProperty(ConversationTopic);
    this.registerProperty(From);
    this.registerProperty(InternetMessageId);
    this.registerProperty(IsRead);
    this.registerProperty(PostedTime);
    this.registerProperty(References);
    this.registerProperty(Sender);
  }

  /**
   * Initializes a new instance of the PostItemSchema class.
   */
  protected PostItemSchema() {
    super();
  }
}
