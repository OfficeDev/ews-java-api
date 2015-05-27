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
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.ICreateComplexPropertyDelegate;
import microsoft.exchange.webservices.data.property.complex.ManagedFolderInformation;
import microsoft.exchange.webservices.data.property.definition.ComplexPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.EffectiveRightsPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.IntPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PermissionSetPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.StringPropertyDefinition;

import java.util.EnumSet;

/**
 * Represents the schema for folder.
 */
@Schema
public class FolderSchema extends ServiceObjectSchema {

  /**
   * Field URIs for folder.
   */
  private static class FieldUris {

    /**
     * The Constant FolderId.
     */
    public final static String FolderId = "folder:FolderId";

    /**
     * The Constant ParentFolderId.
     */
    public final static String ParentFolderId = "folder:ParentFolderId";

    /**
     * The Constant DisplayName.
     */
    public final static String DisplayName = "folder:DisplayName";

    /**
     * The Constant UnreadCount.
     */
    public final static String UnreadCount = "folder:UnreadCount";

    /**
     * The Constant TotalCount.
     */
    public final static String TotalCount = "folder:TotalCount";

    /**
     * The Constant ChildFolderCount.
     */
    public final static String ChildFolderCount = "folder:ChildFolderCount";

    /**
     * The Constant FolderClass.
     */
    public final static String FolderClass = "folder:FolderClass";

    /**
     * The Constant ManagedFolderInformation.
     */
    public final static String ManagedFolderInformation =
        "folder:ManagedFolderInformation";

    /**
     * The Constant EffectiveRights.
     */
    public final static String EffectiveRights = "folder:EffectiveRights";

    /**
     * The Constant PermissionSet.
     */
    public final static String PermissionSet = "folder:PermissionSet";
  }


  /**
   * Defines the Id property.
   */
  public static final PropertyDefinition Id =
      new ComplexPropertyDefinition<FolderId>(
          FolderId.class,
          XmlElementNames.FolderId, FieldUris.FolderId, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<FolderId>() {
            public FolderId createComplexProperty() {
              return new FolderId();
            }
          }

      );

  /**
   * Defines the FolderClass property.
   */
  public static final PropertyDefinition FolderClass =
      new StringPropertyDefinition(
          XmlElementNames.FolderClass, FieldUris.FolderClass, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the ParentFolderId property.
   */
  public static final PropertyDefinition ParentFolderId =
      new ComplexPropertyDefinition<FolderId>(
          FolderId.class,
          XmlElementNames.ParentFolderId, FieldUris.ParentFolderId, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<FolderId>() {
            public FolderId createComplexProperty() {
              return new FolderId();
            }
          });

  /**
   * Defines the ChildFolderCount property.
   */
  public static final PropertyDefinition ChildFolderCount =
      new IntPropertyDefinition(
          XmlElementNames.ChildFolderCount, FieldUris.ChildFolderCount,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the DisplayName property.
   */
  public static final PropertyDefinition DisplayName =
      new StringPropertyDefinition(
          XmlElementNames.DisplayName, FieldUris.DisplayName, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the UnreadCount property.
   */
  public static final PropertyDefinition UnreadCount =
      new IntPropertyDefinition(
          XmlElementNames.UnreadCount, FieldUris.UnreadCount, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the TotalCount property.
   */
  public static final PropertyDefinition TotalCount =
      new IntPropertyDefinition(
          XmlElementNames.TotalCount, FieldUris.TotalCount, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the ManagedFolderInformation property.
   */
  public static final PropertyDefinition ManagedFolderInformation =
      new ComplexPropertyDefinition<microsoft.exchange.webservices.data.property.complex.ManagedFolderInformation>(
          ManagedFolderInformation.class,
          XmlElementNames.ManagedFolderInformation,
          FieldUris.ManagedFolderInformation,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate
              <ManagedFolderInformation>() {
            public ManagedFolderInformation createComplexProperty() {
              return new ManagedFolderInformation();
            }
          });

  /**
   * Defines the EffectiveRights property.
   */
  public static final PropertyDefinition EffectiveRights =
      new EffectiveRightsPropertyDefinition(
          XmlElementNames.EffectiveRights, FieldUris.EffectiveRights, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Permissions property.
   */
  public static final PropertyDefinition Permissions =
      new PermissionSetPropertyDefinition(
          XmlElementNames.PermissionSet, FieldUris.PermissionSet, EnumSet.of(
          PropertyDefinitionFlags.AutoInstantiateOnRead,
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * This must be declared after the property definitions.
   */
  public static final FolderSchema Instance = new FolderSchema();

  /**
   * Registers property. IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN
   * SCHEMA ORDER (i.e. the same order as they are defined in types.xsd)
   */
  @Override
  protected void registerProperties() {
    super.registerProperties();

    this.registerProperty(Id);
    this.registerProperty(ParentFolderId);
    this.registerProperty(FolderClass);
    this.registerProperty(DisplayName);
    this.registerProperty(TotalCount);
    this.registerProperty(ChildFolderCount);
    this.registerProperty(ServiceObjectSchema.extendedProperties);
    this.registerProperty(ManagedFolderInformation);
    this.registerProperty(EffectiveRights);
    this.registerProperty(Permissions);
    this.registerProperty(UnreadCount);
  }
}
