/**************************************************************************
 * copyright file="ContactGroup.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ContactGroup.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a Contact Group. Properties available on contact groups are
 * defined in the ContactGroupSchema class.
 * 
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.DistributionList, returnedByServer = true)
public class ContactGroup extends Item {

	/**
	 * Initializes an unsaved local instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception
	 *             the exception
	 */
	public ContactGroup(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Initializes an new instance of the class.
	 * 
	 * @param parentAttachment
	 *            the parent attachment
	 * @throws Exception
	 *             the exception
	 */
	protected ContactGroup(ItemAttachment parentAttachment) throws Exception {
		super(parentAttachment);
	}

	/**
	 * Gets the name under which this contact group is filed as.
	 * 
	 * @return the file as
	 * @throws Exception
	 *             the exception
	 */
	@RequiredServerVersion(version = ExchangeVersion.Exchange2010)
	public String getFileAs() throws Exception {
		return (String)this
				.getObjectFromPropertyDefinition(ContactSchema.FileAs);
	}

	/**
	 * Gets  the display name of the contact group.
	 * 
	 * @return the display name
	 * @throws Exception
	 *             the exception
	 */
	public String getDisplayName() throws Exception {
		return (String)this
				.getObjectFromPropertyDefinition(ContactSchema.DisplayName);
	}

	/**
	 * Sets the display name.
	 * 
	 * @param value
	 *            the new display name
	 * @throws Exception
	 *             the exception
	 */
	public void setDisplayName(String value) throws Exception {
		this.getPropertyBag().setObjectFromPropertyDefinition(
				ContactSchema.DisplayName, value);
	}

	/**
	 * Gets the members of the contact group.
	 * 
	 * @return the members
	 * @throws Exception
	 *             the exception
	 */
	@RequiredServerVersion(version = ExchangeVersion.Exchange2010)
	public GroupMemberCollection getMembers() throws Exception {
		return (GroupMemberCollection)this
				.getObjectFromPropertyDefinition(ContactGroupSchema.Members);

	}

	/**
	 * Binds to an existing contact group and loads the specified set of
	 * properties.Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @param propertySet
	 *            the property set
	 * @return A ContactGroup instance representing the contact group
	 *         corresponding to the specified Id
	 * @throws Exception
	 *             the exception
	 */
	public static ContactGroup bind(ExchangeService service, ItemId id,
			PropertySet propertySet) throws Exception {
		return service.bindToItem(ContactGroup.class, id, propertySet);
	}

	/**
	 * Binds to an existing contact group and loads the specified set of
	 * properties.Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @return A ContactGroup instance representing the contact group
	 *         corresponding to the specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static ContactGroup bind(ExchangeService service, ItemId id)
			throws Exception {
		return ContactGroup.bind(service, id, PropertySet
				.getFirstClassProperties());
	}

	/**
	 * Internal method to return the schema associated with this type of object.
	 * 
	 * @return The schema associated with this type of object.
	 */
	@Override
	protected ServiceObjectSchema getSchema() {
		return ContactGroupSchema.Instance;
	}

	/**
	 * Gets the minimum required server version.
	 * 
	 * @return Earliest Exchange version in which this service object type is
	 *         supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * Sets the subject.
	 * 
	 * @param subject
	 *            the new subject
	 * @throws ServiceObjectPropertyException
	 *             the service object property exception
	 */
	@Override
	protected void setSubject(String subject)
			throws ServiceObjectPropertyException {
		// Set is disabled in client API even though it is implemented in
		// protocol for Item.Subject.
		// Setting Subject out of sync with DisplayName breaks interop with OLK.
		// See E14:70417, 65663, 6529.
		throw new ServiceObjectPropertyException(Strings.PropertyIsReadOnly,
				ContactGroupSchema.Subject);
	}
}
