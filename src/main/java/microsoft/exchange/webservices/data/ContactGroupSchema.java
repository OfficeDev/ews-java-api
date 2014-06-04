/**************************************************************************
 * copyright file="ContactGroupSchema.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ContactGroupSchema.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents the schema for contact groups.
 * 
 */
@Schema
public class ContactGroupSchema extends ItemSchema {

	
	// Defines the DisplayName property.	
	/** The Constant DisplayName. */
	public static final PropertyDefinition DisplayName =
		ContactSchema.DisplayName;

	
	// Defines the FileAs property.	
	/** The Constant FileAs. */
	public static final PropertyDefinition FileAs = ContactSchema.FileAs;


	// Defines the Members property.	
	/** The Constant Members. */
	public static final PropertyDefinition Members = 
			new ComplexPropertyDefinition<GroupMemberCollection>(
					GroupMemberCollection.class,
			XmlElementNames.Members,
			FieldUris.Members,
			EnumSet.of(PropertyDefinitionFlags.AutoInstantiateOnRead,
					PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanUpdate),
			ExchangeVersion.Exchange2010,
			new ICreateComplexPropertyDelegate
			<GroupMemberCollection>() {
				@Override
				public GroupMemberCollection createComplexProperty() {
					return new GroupMemberCollection();
				}
			});

	
	//This must be declared after the property definitions.	
	/** The Constant Instance. */
	protected static final ContactGroupSchema Instance = 
		new ContactGroupSchema();


	//  Initializes a new instance of the
    // <see cref="ContactGroupSchema"/> class.	
	/**
	 * Instantiates a new contact group schema.
	 */
	protected ContactGroupSchema() {
		super();
	}
	
	//Registers properties.	
	// IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. 
	// the same order as they are defined in types.xsd)
	/**
	 * Registers properties.
	 * 
	 * IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
	 * same order as they are defined in types.xsd)
	 */
	@Override
	protected void registerProperties() {
		super.registerProperties();

		this.registerProperty(DisplayName);
		this.registerProperty(FileAs);
		this.registerProperty(Members);
	}

	
	//  Field URIs for Members.	
	/**
	 * The Interface FieldUris.
	 */
	private static interface FieldUris {
		/**
		 * FieldUri for members.
		 */
		String Members = "distributionlist:Members";
	}

}