/**************************************************************************
 * copyright file="FolderSchema.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderSchema.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents the schema for folders.
 * 
 */
@Schema
public class FolderSchema extends ServiceObjectSchema {

	/**
	 * Field URIs for folders.
	 * 
	 */
	private static class FieldUris {

		/** The Constant FolderId. */
		public final static String FolderId = "folder:FolderId";

		/** The Constant ParentFolderId. */
		public final static String ParentFolderId = "folder:ParentFolderId";

		/** The Constant DisplayName. */
		public final static String DisplayName = "folder:DisplayName";

		/** The Constant UnreadCount. */
		public final static String UnreadCount = "folder:UnreadCount";

		/** The Constant TotalCount. */
		public final static String TotalCount = "folder:TotalCount";

		/** The Constant ChildFolderCount. */
		public final static String ChildFolderCount = "folder:ChildFolderCount";

		/** The Constant FolderClass. */
		public final static String FolderClass = "folder:FolderClass";

		/** The Constant ManagedFolderInformation. */
		public final static String ManagedFolderInformation = 
			"folder:ManagedFolderInformation";

		/** The Constant EffectiveRights. */
		public final static String EffectiveRights = "folder:EffectiveRights";

		/** The Constant PermissionSet. */
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
		new ComplexPropertyDefinition<ManagedFolderInformation>(
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

	/** This must be declared after the property definitions. */
	protected static final FolderSchema Instance = new FolderSchema();

	/**
	 * Registers properties. IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN
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
