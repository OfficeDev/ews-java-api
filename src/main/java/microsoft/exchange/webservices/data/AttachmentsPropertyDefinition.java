/**************************************************************************
 * copyright file="AttachmentsPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AttachmentsPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents base Attachments property type.
 * 
 */
public final class AttachmentsPropertyDefinition extends
		ComplexPropertyDefinition<AttachmentCollection> {

	private static final EnumSet<PropertyDefinitionFlags> Exchange2010SP2PropertyDefinitionFlags = EnumSet
			.of(PropertyDefinitionFlags.AutoInstantiateOnRead,
					PropertyDefinitionFlags.CanSet,
					PropertyDefinitionFlags.ReuseInstance,
					PropertyDefinitionFlags.UpdateCollectionItems);

	public AttachmentsPropertyDefinition() {
		super(null,XmlElementNames.Attachments, "item:Attachments",
				 EnumSet
						.of(PropertyDefinitionFlags.AutoInstantiateOnRead),
						ExchangeVersion.Exchange2007_SP1,
				new ICreateComplexPropertyDelegate<AttachmentCollection>() {
					public AttachmentCollection createComplexProperty() {
						return new AttachmentCollection();
					}
				});

	}

	/***
	 * Determines whether the specified flag is set.
	 * 
	 * @param flag
	 *            The flag.
	 * @param version
	 *            Requested version.
	 * @return true/false if the specified flag is set,otherwise false.
	 * 
	 */
	@Override
	protected boolean hasFlag(PropertyDefinitionFlags flag,
			ExchangeVersion version) {
		if (version != null
				&& this.getVersion()
						.compareTo(ExchangeVersion.Exchange2010_SP2) >= 0) {
			if (AttachmentsPropertyDefinition.Exchange2010SP2PropertyDefinitionFlags
					.contains(flag)) {
				return true;
			} else {
				return false;
			}
		}
		return super.hasFlag(flag, version);
	}

}
