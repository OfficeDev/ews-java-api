/**************************************************************************
 * copyright file="ContactsFolder.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ContactsFolder.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 *Represents a folder containing contacts.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.ContactsFolder)
public class ContactsFolder extends Folder {

	/**
	 * * Initializes an unsaved local instance of the class.To bind to an
	 * existing contacts folder, use ContactsFolder.Bind() instead.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception
	 *             the exception
	 */
	public ContactsFolder(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * * Binds to an existing contacts folder and loads the specified set of
	 * properties.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @param propertySet
	 *            the property set
	 * @return A ContactsFolder instance representing the contacts folder
	 *         corresponding to the specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static ContactsFolder bind(ExchangeService service, FolderId id,
			PropertySet propertySet) throws Exception {
		return service.bindToFolder(ContactsFolder.class, id, propertySet);
	}

	/**
	 * * Binds to an existing contacts folder and loads its first class
	 * properties.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @return A ContactsFolder instance representing the contacts folder
	 *         corresponding to the specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static ContactsFolder bind(ExchangeService service, FolderId id)
			throws Exception {
		return ContactsFolder.bind(service, id, PropertySet
				.getFirstClassProperties());
	}

	/**
	 * * Binds to an existing contacts folder and loads the specified set of
	 * properties.
	 * 
	 * @param service
	 *            the service
	 * @param name
	 *            the name
	 * @param propertySet
	 *            the property set
	 * @return A ContactsFolder instance representing the contacts folder
	 *         corresponding to the specified name.
	 * @throws Exception
	 *             the exception
	 */
	public static ContactsFolder bind(ExchangeService service,
			WellKnownFolderName name, PropertySet propertySet) 
		throws Exception {
		return ContactsFolder.bind(service, new FolderId(name), propertySet);
	}

	/**
	 * * Binds to an existing contacts folder and loads its first class
	 * properties.
	 * 
	 * @param service
	 *            the service
	 * @param name
	 *            the name
	 * @return A ContactsFolder instance representing the contacts folder
	 *         corresponding to the specified name.
	 * @throws Exception
	 *             the exception
	 */
	public static ContactsFolder bind(ExchangeService service,
			WellKnownFolderName name) throws Exception {
		return ContactsFolder.bind(service, new FolderId(name), PropertySet
				.getFirstClassProperties());
	}

	/**
	 * * Gets the minimum required server version.
	 * 
	 * @return Earliest Exchange version in which this service object type is
	 *         supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}
}
