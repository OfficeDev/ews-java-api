/**************************************************************************
 * copyright file="SearchFolderSchema.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SearchFolderSchema.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * The Class SearchFolderSchema.
 */
@Schema
public class SearchFolderSchema extends FolderSchema {

	/**
	 * Field URIs for search folders.
	 * 
	 */
	private static interface FieldUris {

		/** The Search parameters. */
		String SearchParameters = "folder:SearchParameters";
	}

	/**
	 * Defines the SearchParameters property.
	 */
	public static final PropertyDefinition SearchParameters =
		new ComplexPropertyDefinition<SearchFolderParameters>(
				SearchFolderParameters.class,
			XmlElementNames.SearchParameters,
			FieldUris.SearchParameters,
			EnumSet.of(PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.CanUpdate,
					PropertyDefinitionFlags.AutoInstantiateOnRead),
			ExchangeVersion.Exchange2007_SP1,
			new ICreateComplexPropertyDelegate
			<SearchFolderParameters>() {
				@Override
				public SearchFolderParameters createComplexProperty() {
					return new SearchFolderParameters();
				}
			});

	// This must be declared after the property definitions
	/** The Constant Instance. */
	static final SearchFolderSchema Instance = new SearchFolderSchema();

	/**
	 * Registers properties.
	 */
	// IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
	// same order as they are defined in types.xsd)
	@Override
	protected void registerProperties() {
		super.registerProperties();

		this.registerProperty(SearchParameters);
	}
}