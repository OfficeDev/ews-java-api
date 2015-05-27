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
import microsoft.exchange.webservices.data.core.enumeration.service.ConversationFlagStatus;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.Importance;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.property.complex.ConversationId;
import microsoft.exchange.webservices.data.property.complex.ICreateComplexPropertyDelegate;
import microsoft.exchange.webservices.data.property.complex.ItemIdCollection;
import microsoft.exchange.webservices.data.property.complex.StringList;
import microsoft.exchange.webservices.data.property.definition.BoolPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.ComplexPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.DateTimePropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.GenericPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.IntPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.StringPropertyDefinition;

import java.util.EnumSet;

/**
 * Represents the schema for Conversation.
 */
@Schema
public class ConversationSchema extends ServiceObjectSchema {

  /**
   * Field URIs for Item.
   */
  private static class FieldUris {
    /**
     * The Constant ConversationId.
     */
    public static final String ConversationId =
        "conversation:ConversationId";

    /**
     * The Constant ConversationTopic.
     */
    public static final String ConversationTopic =
        "conversation:ConversationTopic";

    /**
     * The Constant UniqueRecipients.
     */
    public static final String UniqueRecipients =
        "conversation:UniqueRecipients";

    /**
     * The Constant GlobalUniqueRecipients.
     */
    public static final String GlobalUniqueRecipients =
        "conversation:GlobalUniqueRecipients";

    /**
     * The Constant UniqueUnreadSenders.
     */
    public static final String UniqueUnreadSenders =
        "conversation:UniqueUnreadSenders";

    /**
     * The Constant GlobalUniqueUnreadSenders.
     */
    public static final String GlobalUniqueUnreadSenders =
        "conversation:GlobalUniqueUnreadSenders";

    /**
     * The Constant UniqueSenders.
     */
    public static final String UniqueSenders = "conversation:UniqueSenders";

    /**
     * The Constant GlobalUniqueSenders.
     */
    public static final String GlobalUniqueSenders =
        "conversation:GlobalUniqueSenders";

    /**
     * The Constant LastDeliveryTime.
     */
    public static final String LastDeliveryTime =
        "conversation:LastDeliveryTime";

    /**
     * The Constant GlobalLastDeliveryTime.
     */
    public static final String GlobalLastDeliveryTime =
        "conversation:GlobalLastDeliveryTime";

    /**
     * The Constant Categories.
     */
    public static final String Categories = "conversation:Categories";

    /**
     * The Constant GlobalCategories.
     */
    public static final String GlobalCategories =
        "conversation:GlobalCategories";

    /**
     * The Constant FlagStatus.
     */
    public static final String FlagStatus = "conversation:FlagStatus";

    /**
     * The Constant GlobalFlagStatus.
     */
    public static final String GlobalFlagStatus =
        "conversation:GlobalFlagStatus";

    /**
     * The Constant HasAttachments.
     */
    public static final String HasAttachments =
        "conversation:HasAttachments";

    /**
     * The Constant GlobalHasAttachments.
     */
    public static final String GlobalHasAttachments =
        "conversation:GlobalHasAttachments";

    /**
     * The Constant MessageCount.
     */
    public static final String MessageCount = "conversation:MessageCount";

    /**
     * The Constant GlobalMessageCount.
     */
    public static final String GlobalMessageCount =
        "conversation:GlobalMessageCount";

    /**
     * The Constant UnreadCount.
     */
    public static final String UnreadCount = "conversation:UnreadCount";

    /**
     * The Constant GlobalUnreadCount.
     */
    public static final String GlobalUnreadCount =
        "conversation:GlobalUnreadCount";

    /**
     * The Constant Size.
     */
    public static final String Size = "conversation:Size";

    /**
     * The Constant GlobalSize.
     */
    public static final String GlobalSize = "conversation:GlobalSize";

    /**
     * The Constant ItemClasses.
     */
    public static final String ItemClasses = "conversation:ItemClasses";

    /**
     * The Constant GlobalItemClasses.
     */
    public static final String GlobalItemClasses =
        "conversation:GlobalItemClasses";

    /**
     * The Constant Importance.
     */
    public static final String Importance = "conversation:Importance";

    /**
     * The Constant GlobalImportance.
     */
    public static final String GlobalImportance =
        "conversation:GlobalImportance";

    /**
     * The Constant ItemIds.
     */
    public static final String ItemIds = "conversation:ItemIds";

    /**
     * The Constant GlobalItemIds.
     */
    public static final String GlobalItemIds = "conversation:GlobalItemIds";

  }


  /**
   * Defines the Id property.
   */
  public static final PropertyDefinition Id = new ComplexPropertyDefinition<ConversationId>(
      ConversationId.class,
      XmlElementNames.ConversationId, FieldUris.ConversationId, EnumSet
      .of(PropertyDefinitionFlags.CanFind),
      ExchangeVersion.Exchange2010_SP1,
      new ICreateComplexPropertyDelegate<ConversationId>() {
        public ConversationId createComplexProperty() {
          return new ConversationId();
        }
      });

  /**
   * Defines the Topic property.
   */
  public static final PropertyDefinition Topic =
      new StringPropertyDefinition(
          XmlElementNames.ConversationTopic,
          FieldUris.ConversationTopic,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the UniqueRecipients property.
   */
  public static final PropertyDefinition UniqueRecipients = new
      ComplexPropertyDefinition<StringList>(
      StringList.class,
      XmlElementNames.UniqueRecipients,
      FieldUris.UniqueRecipients, EnumSet
      .of(PropertyDefinitionFlags.CanFind),
      ExchangeVersion.Exchange2010_SP1,
      new ICreateComplexPropertyDelegate<StringList>() {
        public StringList createComplexProperty() {
          return new StringList();
        }
      });


  /**
   * Defines the GlobalUniqueRecipients property.
   */
  public static final PropertyDefinition GlobalUniqueRecipients =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.GlobalUniqueRecipients,
          FieldUris.GlobalUniqueRecipients,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            public StringList createComplexProperty() {
              return new StringList();
            }
          });

  /**
   * Defines the UniqueUnreadSenders property.
   */
  public static final PropertyDefinition UniqueUnreadSenders =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.UniqueUnreadSenders,
          FieldUris.UniqueUnreadSenders,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            public StringList createComplexProperty() {
              return new StringList();
            }
          });

  /**
   * Defines the GlobalUniqueUnreadSenders property.
   */
  public static final PropertyDefinition GlobalUniqueUnreadSenders =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.GlobalUniqueUnreadSenders,
          FieldUris.GlobalUniqueUnreadSenders,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            public StringList createComplexProperty() {
              return new StringList();
            }
          });

  /**
   * Defines the UniqueSenders property.
   */
  public static final PropertyDefinition UniqueSenders =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.UniqueSenders,
          FieldUris.UniqueSenders,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            public StringList createComplexProperty() {
              return new StringList();
            }
          });

  /**
   * Defines the GlobalUniqueSenders property.
   */
  public static final PropertyDefinition GlobalUniqueSenders =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.GlobalUniqueSenders,
          FieldUris.GlobalUniqueSenders,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            public StringList createComplexProperty() {
              return new StringList();
            }
          });

  /**
   * Defines the LastDeliveryTime property.
   */
  public static final PropertyDefinition LastDeliveryTime =
      new DateTimePropertyDefinition(
          XmlElementNames.LastDeliveryTime,
          FieldUris.LastDeliveryTime,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the GlobalLastDeliveryTime property.
   */
  public static final PropertyDefinition GlobalLastDeliveryTime =
      new DateTimePropertyDefinition(
          XmlElementNames.GlobalLastDeliveryTime,
          FieldUris.GlobalLastDeliveryTime,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the Categories property.
   */
  public static final PropertyDefinition Categories =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.Categories,
          FieldUris.Categories,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            public StringList createComplexProperty() {
              return new StringList();
            }
          });

  /**
   * Defines the GlobalCategories property.
   */
  public static final PropertyDefinition GlobalCategories =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.GlobalCategories,
          FieldUris.GlobalCategories,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            public StringList createComplexProperty() {
              return new StringList();
            }
          });

  /**
   * Defines the FlagStatus property.
   */
  public static final PropertyDefinition FlagStatus =
      new GenericPropertyDefinition<ConversationFlagStatus>(
          ConversationFlagStatus.class,
          XmlElementNames.FlagStatus,
          FieldUris.FlagStatus,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the GlobalFlagStatus property.
   */
  public static final PropertyDefinition GlobalFlagStatus =
      new GenericPropertyDefinition<ConversationFlagStatus>(
          ConversationFlagStatus.class,
          XmlElementNames.GlobalFlagStatus,
          FieldUris.GlobalFlagStatus,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the HasAttachments property.
   */
  public static final PropertyDefinition HasAttachments =
      new BoolPropertyDefinition(
          XmlElementNames.HasAttachments,
          FieldUris.HasAttachments,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the GlobalHasAttachments property.
   */
  public static final PropertyDefinition GlobalHasAttachments =
      new BoolPropertyDefinition(
          XmlElementNames.GlobalHasAttachments,
          FieldUris.GlobalHasAttachments,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the MessageCount property.
   */
  public static final PropertyDefinition MessageCount =
      new IntPropertyDefinition(
          XmlElementNames.MessageCount,
          FieldUris.MessageCount,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the GlobalMessageCount property.
   */
  public static final PropertyDefinition GlobalMessageCount =
      new IntPropertyDefinition(
          XmlElementNames.GlobalMessageCount,
          FieldUris.GlobalMessageCount,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the UnreadCount property.
   */
  public static final PropertyDefinition UnreadCount =
      new IntPropertyDefinition(
          XmlElementNames.UnreadCount,
          FieldUris.UnreadCount,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the GlobalUnreadCount property.
   */
  public static final PropertyDefinition GlobalUnreadCount =
      new IntPropertyDefinition(
          XmlElementNames.GlobalUnreadCount,
          FieldUris.GlobalUnreadCount,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the Size property.
   */
  public static final PropertyDefinition Size =
      new IntPropertyDefinition(
          XmlElementNames.Size,
          FieldUris.Size,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the GlobalSize property.
   */
  public static final PropertyDefinition GlobalSize =
      new IntPropertyDefinition(
          XmlElementNames.GlobalSize,
          FieldUris.GlobalSize,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the ItemClasses property.
   */
  public static final PropertyDefinition ItemClasses =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.ItemClasses,
          FieldUris.ItemClasses,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            public StringList createComplexProperty() {
              return new StringList(XmlElementNames.
                  ItemClass);
            }
          });

  /**
   * Defines the GlobalItemClasses property.
   */
  public static final PropertyDefinition GlobalItemClasses =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.GlobalItemClasses,
          FieldUris.GlobalItemClasses,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            public StringList createComplexProperty() {
              return new StringList(XmlElementNames.
                  ItemClass);
            }
          });

  /**
   * Defines the Importance property.
   */
  public static final PropertyDefinition Importance =
      new GenericPropertyDefinition<microsoft.exchange.webservices.data.core.enumeration.property.Importance>(
          Importance.class,
          XmlElementNames.Importance,
          FieldUris.Importance,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the GlobalImportance property.
   */
  public static final PropertyDefinition GlobalImportance =
      new GenericPropertyDefinition<Importance>(
          Importance.class,
          XmlElementNames.GlobalImportance,
          FieldUris.GlobalImportance,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the ItemIds property.
   */
  public static final PropertyDefinition ItemIds =
      new ComplexPropertyDefinition<ItemIdCollection>(
          ItemIdCollection.class,
          XmlElementNames.ItemIds,
          FieldUris.ItemIds,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<ItemIdCollection>() {
            public ItemIdCollection createComplexProperty() {
              return new ItemIdCollection();
            }
          });

  /**
   * Defines the GlobalItemIds property.
   */
  public static final PropertyDefinition GlobalItemIds =
      new ComplexPropertyDefinition<ItemIdCollection>(
          ItemIdCollection.class,
          XmlElementNames.GlobalItemIds,
          FieldUris.GlobalItemIds,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<ItemIdCollection>() {
            public ItemIdCollection createComplexProperty() {
              return new ItemIdCollection();
            }
          });

  /**
   * This must be declared after the property definitions
   */
  public static final ConversationSchema Instance =
      new ConversationSchema();

  /**
   * Registers property.
   */
  @Override
  protected void registerProperties() {
    super.registerProperties();

    this.registerProperty(Id);
    this.registerProperty(Topic);
    this.registerProperty(UniqueRecipients);
    this.registerProperty(GlobalUniqueRecipients);
    this.registerProperty(UniqueUnreadSenders);
    this.registerProperty(GlobalUniqueUnreadSenders);
    this.registerProperty(UniqueSenders);
    this.registerProperty(GlobalUniqueSenders);
    this.registerProperty(LastDeliveryTime);
    this.registerProperty(GlobalLastDeliveryTime);
    this.registerProperty(Categories);
    this.registerProperty(GlobalCategories);
    this.registerProperty(FlagStatus);
    this.registerProperty(GlobalFlagStatus);
    this.registerProperty(HasAttachments);
    this.registerProperty(GlobalHasAttachments);
    this.registerProperty(MessageCount);
    this.registerProperty(GlobalMessageCount);
    this.registerProperty(UnreadCount);
    this.registerProperty(GlobalUnreadCount);
    this.registerProperty(Size);
    this.registerProperty(GlobalSize);
    this.registerProperty(ItemClasses);
    this.registerProperty(GlobalItemClasses);
    this.registerProperty(Importance);
    this.registerProperty(GlobalImportance);
    this.registerProperty(ItemIds);
    this.registerProperty(GlobalItemIds);
  }

  /**
   * Initializes a new instance of
   * the ConversationSchema class.
   */
  protected ConversationSchema() {
    super();
  }



}
