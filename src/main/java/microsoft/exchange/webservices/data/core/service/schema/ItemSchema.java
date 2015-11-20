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
import microsoft.exchange.webservices.data.core.enumeration.property.Importance;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.core.enumeration.property.Sensitivity;
import microsoft.exchange.webservices.data.property.complex.ConversationId;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.ICreateComplexPropertyDelegate;
import microsoft.exchange.webservices.data.property.complex.InternetMessageHeaderCollection;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.property.complex.MimeContent;
import microsoft.exchange.webservices.data.property.complex.StringList;
import microsoft.exchange.webservices.data.property.complex.UniqueBody;
import microsoft.exchange.webservices.data.property.definition.AttachmentsPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.BoolPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.ByteArrayPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.ComplexPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.DateTimePropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.EffectiveRightsPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.GenericPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.IntPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.ResponseObjectsPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.StringPropertyDefinition;

import java.util.EnumSet;

/**
 * Represents the schema for generic item.
 */
@Schema
public class ItemSchema extends ServiceObjectSchema {

  /**
   * The Interface FieldUris.
   */
  private static interface FieldUris {

    /**
     * The Item id.
     */
    String ItemId = "item:ItemId";

    /**
     * The Parent folder id.
     */
    String ParentFolderId = "item:ParentFolderId";

    /**
     * The Item class.
     */
    String ItemClass = "item:ItemClass";

    /**
     * The Mime content.
     */
    String MimeContent = "item:MimeContent";

    /**
     * The Attachments.
     */
    String Attachments = "item:Attachments";

    /**
     * The Subject.
     */
    String Subject = "item:Subject";

    /**
     * The Date time received.
     */
    String DateTimeReceived = "item:DateTimeReceived";

    /**
     * The Size.
     */
    String Size = "item:Size";

    /**
     * The Categories.
     */
    String Categories = "item:Categories";

    /**
     * The Has attachments.
     */
    String HasAttachments = "item:HasAttachments";

    /**
     * The Importance.
     */
    String Importance = "item:Importance";

    /**
     * The In reply to.
     */
    String InReplyTo = "item:InReplyTo";

    /**
     * The Internet message headers.
     */
    String InternetMessageHeaders = "item:InternetMessageHeaders";

    /**
     * The Is associated.
     */
    String IsAssociated = "item:IsAssociated";

    /**
     * The Is draft.
     */
    String IsDraft = "item:IsDraft";

    /**
     * The Is from me.
     */
    String IsFromMe = "item:IsFromMe";

    /**
     * The Is resend.
     */
    String IsResend = "item:IsResend";

    /**
     * The Is submitted.
     */
    String IsSubmitted = "item:IsSubmitted";

    /**
     * The Is unmodified.
     */
    String IsUnmodified = "item:IsUnmodified";

    /**
     * The Date time sent.
     */
    String DateTimeSent = "item:DateTimeSent";

    /**
     * The Date time created.
     */
    String DateTimeCreated = "item:DateTimeCreated";

    /**
     * The Body.
     */
    String Body = "item:Body";

    /**
     * The Response objects.
     */
    String ResponseObjects = "item:ResponseObjects";

    /**
     * The Sensitivity.
     */
    String Sensitivity = "item:Sensitivity";

    /**
     * The Reminder due by.
     */
    String ReminderDueBy = "item:ReminderDueBy";

    /**
     * The Reminder is set.
     */
    String ReminderIsSet = "item:ReminderIsSet";

    /**
     * The Reminder minutes before start.
     */
    String ReminderMinutesBeforeStart = "item:ReminderMinutesBeforeStart";

    /**
     * The Display to.
     */
    String DisplayTo = "item:DisplayTo";

    /**
     * The Display cc.
     */
    String DisplayCc = "item:DisplayCc";

    /**
     * The Culture.
     */
    String Culture = "item:Culture";

    /**
     * The Effective rights.
     */
    String EffectiveRights = "item:EffectiveRights";

    /**
     * The Last modified name.
     */
    String LastModifiedName = "item:LastModifiedName";

    /**
     * The Last modified time.
     */
    String LastModifiedTime = "item:LastModifiedTime";

    /**
     * The Web client read form query string.
     */
    String WebClientReadFormQueryString =
        "item:WebClientReadFormQueryString";

    /**
     * The Web client edit form query string.
     */
    String WebClientEditFormQueryString =
        "item:WebClientEditFormQueryString";

    /**
     * The Conversation id.
     */
    String ConversationId = "item:ConversationId";

    /**
     * The Unique body.
     */
    String UniqueBody = "item:UniqueBody";

    String StoreEntryId = "item:StoreEntryId";
  }


  /**
   * Defines the Id property.
   */
  public static final PropertyDefinition Id = new ComplexPropertyDefinition<ItemId>(
      ItemId.class,
      XmlElementNames.ItemId, FieldUris.ItemId, EnumSet
      .of(PropertyDefinitionFlags.CanFind),
      ExchangeVersion.Exchange2007_SP1,
      new ICreateComplexPropertyDelegate<ItemId>() {
        public ItemId createComplexProperty() {
          return new ItemId();
        }
      });

  /**
   * Defines the Body property.
   */
  public static final PropertyDefinition Body = new
      ComplexPropertyDefinition<MessageBody>(
      MessageBody.class,
      XmlElementNames.Body, FieldUris.Body, EnumSet.of(
      PropertyDefinitionFlags.CanSet,
      PropertyDefinitionFlags.CanUpdate,
      PropertyDefinitionFlags.CanDelete),
      ExchangeVersion.Exchange2007_SP1,
      new ICreateComplexPropertyDelegate<MessageBody>() {
        public MessageBody createComplexProperty() {
          return new MessageBody();
        }
      });

  /**
   * Defines the ItemClass property.
   */
  public static final PropertyDefinition ItemClass = new StringPropertyDefinition(
      XmlElementNames.ItemClass, FieldUris.ItemClass, EnumSet.of(
      PropertyDefinitionFlags.CanSet,
      PropertyDefinitionFlags.CanUpdate,
      PropertyDefinitionFlags.CanFind),
      ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Subject property.
   */
  public static final PropertyDefinition Subject = new
      StringPropertyDefinition(
      XmlElementNames.Subject, FieldUris.Subject, EnumSet.of(
      PropertyDefinitionFlags.CanSet,
      PropertyDefinitionFlags.CanUpdate,
      PropertyDefinitionFlags.CanDelete,
      PropertyDefinitionFlags.CanFind),
      ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the MimeContent property.
   */
  public static final PropertyDefinition MimeContent =
      new ComplexPropertyDefinition<microsoft.exchange.webservices.data.property.complex.MimeContent>(
          MimeContent.class,
          XmlElementNames.MimeContent, FieldUris.MimeContent, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.MustBeExplicitlyLoaded),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<MimeContent>() {
            public MimeContent createComplexProperty() {
              return new MimeContent();
            }
          });

  /**
   * Defines the ParentFolderId property.
   */
  public static final PropertyDefinition ParentFolderId =
      new ComplexPropertyDefinition<FolderId>(
          FolderId.class,
          XmlElementNames.ParentFolderId, FieldUris.ParentFolderId,
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<FolderId>() {
            public FolderId createComplexProperty() {
              return new FolderId();
            }
          });

  /**
   * Defines the Sensitivity property.
   */
  public static final PropertyDefinition Sensitivity =
      new GenericPropertyDefinition<microsoft.exchange.webservices.data.core.enumeration.property.Sensitivity>(
          Sensitivity.class,
          XmlElementNames.Sensitivity, FieldUris.Sensitivity, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Attachments property.
   */
  public static final PropertyDefinition Attachments = new AttachmentsPropertyDefinition();

  /**
   * Defines the DateTimeReceived property.
   */
  public static final PropertyDefinition DateTimeReceived =
      new DateTimePropertyDefinition(
          XmlElementNames.DateTimeReceived, FieldUris.DateTimeReceived,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Size property.
   */
  public static final PropertyDefinition Size = new IntPropertyDefinition(
      XmlElementNames.Size, FieldUris.Size, EnumSet
      .of(PropertyDefinitionFlags.CanFind),
      ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Categories property.
   */
  public static final PropertyDefinition Categories =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.Categories, FieldUris.Categories, EnumSet.of(
          PropertyDefinitionFlags.AutoInstantiateOnRead,
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            public StringList createComplexProperty() {
              return new StringList();
            }
          });

  /**
   * Defines the Importance property.
   */
  public static final PropertyDefinition Importance =
      new GenericPropertyDefinition<microsoft.exchange.webservices.data.core.enumeration.property.Importance>(
          Importance.class,
          XmlElementNames.Importance, FieldUris.Importance, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the InReplyTo property.
   */
  public static final PropertyDefinition InReplyTo =
      new StringPropertyDefinition(
          XmlElementNames.InReplyTo, FieldUris.InReplyTo, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the IsSubmitted property.
   */
  public static final PropertyDefinition IsSubmitted =
      new BoolPropertyDefinition(
          XmlElementNames.IsSubmitted, FieldUris.IsSubmitted, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the IsAssociated property.
   */
  public static final PropertyDefinition IsAssociated =
      new BoolPropertyDefinition(
          XmlElementNames.IsAssociated, FieldUris.IsAssociated, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010);

  /**
   * Defines the IsDraft property.
   */
  public static final PropertyDefinition IsDraft = new BoolPropertyDefinition(
      XmlElementNames.IsDraft, FieldUris.IsDraft, EnumSet
      .of(PropertyDefinitionFlags.CanFind),
      ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the IsFromMe property.
   */
  public static final PropertyDefinition IsFromMe =
      new BoolPropertyDefinition(
          XmlElementNames.IsFromMe, FieldUris.IsFromMe, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the IsResend property.
   */
  public static final PropertyDefinition IsResend =
      new BoolPropertyDefinition(
          XmlElementNames.IsResend, FieldUris.IsResend, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the IsUnmodified property.
   */
  public static final PropertyDefinition IsUnmodified =
      new BoolPropertyDefinition(
          XmlElementNames.IsUnmodified, FieldUris.IsUnmodified, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the InternetMessageHeaders property.
   */
  public static final PropertyDefinition InternetMessageHeaders =
      new ComplexPropertyDefinition<InternetMessageHeaderCollection>(
          InternetMessageHeaderCollection.class,
          XmlElementNames.InternetMessageHeaders,
          FieldUris.InternetMessageHeaders,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate
              <InternetMessageHeaderCollection>() {
            public InternetMessageHeaderCollection createComplexProperty() {
              return new InternetMessageHeaderCollection();
            }
          });

  /**
   * Defines the DateTimeSent property.
   */
  public static final PropertyDefinition DateTimeSent =
      new DateTimePropertyDefinition(
          XmlElementNames.DateTimeSent, FieldUris.DateTimeSent, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the DateTimeCreated property.
   */
  public static final PropertyDefinition DateTimeCreated =
      new DateTimePropertyDefinition(
          XmlElementNames.DateTimeCreated, FieldUris.DateTimeCreated, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the AllowedResponseActions property.
   */
  public static final PropertyDefinition AllowedResponseActions =
      new ResponseObjectsPropertyDefinition(
          XmlElementNames.ResponseObjects, FieldUris.ResponseObjects,
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the ReminderDueBy property.
   */

  public static final PropertyDefinition ReminderDueBy =
      new DateTimePropertyDefinition(
          XmlElementNames.ReminderDueBy, FieldUris.ReminderDueBy, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the IsReminderSet property.
   */
  public static final PropertyDefinition IsReminderSet =
      new BoolPropertyDefinition(
          XmlElementNames.ReminderIsSet, // Note: server-side the name is
          // ReminderIsSet
          FieldUris.ReminderIsSet, EnumSet.of(PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the ReminderMinutesBeforeStart property.
   */
  public static final PropertyDefinition ReminderMinutesBeforeStart =
      new IntPropertyDefinition(
          XmlElementNames.ReminderMinutesBeforeStart,
          FieldUris.ReminderMinutesBeforeStart, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the DisplayCc property.
   */
  public static final PropertyDefinition DisplayCc =
      new StringPropertyDefinition(
          XmlElementNames.DisplayCc, FieldUris.DisplayCc, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the DisplayTo property.
   */
  public static final PropertyDefinition DisplayTo =
      new StringPropertyDefinition(
          XmlElementNames.DisplayTo, FieldUris.DisplayTo, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the HasAttachments property.
   */
  public static final PropertyDefinition HasAttachments =
      new BoolPropertyDefinition(
          XmlElementNames.HasAttachments, FieldUris.HasAttachments, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Culture property.
   */
  public static final PropertyDefinition Culture =
      new StringPropertyDefinition(
          XmlElementNames.Culture, FieldUris.Culture, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the EffectiveRights property.
   */
  public static final PropertyDefinition EffectiveRights =
      new EffectiveRightsPropertyDefinition(
          XmlElementNames.EffectiveRights, FieldUris.EffectiveRights, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the LastModifiedName property.
   */
  public static final PropertyDefinition LastModifiedName =
      new StringPropertyDefinition(
          XmlElementNames.LastModifiedName, FieldUris.LastModifiedName,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the LastModifiedTime property.
   */
  public static final PropertyDefinition LastModifiedTime =
      new DateTimePropertyDefinition(
          XmlElementNames.LastModifiedTime, FieldUris.LastModifiedTime,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the WebClientReadFormQueryString property.
   */
  public static final PropertyDefinition WebClientReadFormQueryString =
      new StringPropertyDefinition(
          XmlElementNames.WebClientReadFormQueryString,
          FieldUris.WebClientReadFormQueryString, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010);

  /**
   * Defines the WebClientEditFormQueryString property.
   */
  public static final PropertyDefinition WebClientEditFormQueryString =
      new StringPropertyDefinition(
          XmlElementNames.WebClientEditFormQueryString,
          FieldUris.WebClientEditFormQueryString, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010);

  /**
   * Defines the ConversationId property.
   */
  public static final PropertyDefinition ConversationId =
      new ComplexPropertyDefinition<microsoft.exchange.webservices.data.property.complex.ConversationId>(
          ConversationId.class,
          XmlElementNames.ConversationId, FieldUris.ConversationId, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010,
          new ICreateComplexPropertyDelegate<ConversationId>() {
            public ConversationId createComplexProperty() {
              return new ConversationId();
            }
          });

  /**
   * Defines the UniqueBody property.
   */
  public static final PropertyDefinition UniqueBody =
      new ComplexPropertyDefinition<microsoft.exchange.webservices.data.property.complex.UniqueBody>(
          UniqueBody.class,
          XmlElementNames.UniqueBody, FieldUris.UniqueBody, EnumSet
          .of(PropertyDefinitionFlags.MustBeExplicitlyLoaded),
          ExchangeVersion.Exchange2010,
          new ICreateComplexPropertyDelegate<UniqueBody>() {
            public UniqueBody createComplexProperty() {
              return new UniqueBody();
            }
          });

  /**
   * Defines the StoreEntryId property.
   */

  public static final PropertyDefinition StoreEntryId =
      new ByteArrayPropertyDefinition(
          XmlElementNames.StoreEntryId,
          FieldUris.StoreEntryId,
          EnumSet
              .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP2);



  /**
   * The Constant Instance.
   */
  protected static final ItemSchema Instance = new ItemSchema();

  /**
   * Gets the single instance of ItemSchema.
   *
   * @return single instance of ItemSchema
   */
  public static ItemSchema getInstance() {
    return Instance;
  }

  /**
   * Registers property.
   * <p>
   * IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
   * same order as they are defined in types.xsd)
   * </p>
   */
  @Override
  protected void registerProperties() {
    super.registerProperties();
    this.registerProperty(MimeContent);
    this.registerProperty(Id);
    this.registerProperty(ParentFolderId);
    this.registerProperty(ItemClass);
    this.registerProperty(Subject);
    this.registerProperty(Sensitivity);
    this.registerProperty(Body);
    this.registerProperty(Attachments);
    this.registerProperty(DateTimeReceived);
    this.registerProperty(Size);
    this.registerProperty(Categories);
    this.registerProperty(Importance);
    this.registerProperty(InReplyTo);
    this.registerProperty(IsSubmitted);
    this.registerProperty(IsDraft);
    this.registerProperty(IsFromMe);
    this.registerProperty(IsResend);
    this.registerProperty(IsUnmodified);
    this.registerProperty(InternetMessageHeaders);
    this.registerProperty(DateTimeSent);
    this.registerProperty(DateTimeCreated);
    this.registerProperty(AllowedResponseActions);
    this.registerProperty(ReminderDueBy);
    this.registerProperty(IsReminderSet);
    this.registerProperty(ReminderMinutesBeforeStart);
    this.registerProperty(DisplayCc);
    this.registerProperty(DisplayTo);
    this.registerProperty(HasAttachments);
    this.registerProperty(ServiceObjectSchema.extendedProperties);
    this.registerProperty(Culture);
    this.registerProperty(EffectiveRights);
    this.registerProperty(LastModifiedName);
    this.registerProperty(LastModifiedTime);
    this.registerProperty(IsAssociated);
    this.registerProperty(WebClientReadFormQueryString);
    this.registerProperty(WebClientEditFormQueryString);
    this.registerProperty(ConversationId);
    this.registerProperty(UniqueBody);
    this.registerProperty(StoreEntryId);

  }

  /**
   * Initializes a new instance.
   */
  protected ItemSchema() {
    super();
  }
}
