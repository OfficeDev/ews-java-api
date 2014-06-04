/**************************************************************************
 * copyright file="FolderPermissionCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderPermissionCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *Represents a collection of folder permissions.
 */
public final class FolderPermissionCollection extends
		ComplexPropertyCollection<FolderPermission> {

	/** The is calendar folder. */
	private boolean isCalendarFolder;

	/** The unknown entries. */
	private Collection<String> unknownEntries = new ArrayList<String>();

	/**
	 * * Initializes a new instance of the FolderPermissionCollection class.
	 * 
	 * @param owner
	 *            the owner
	 */
	protected FolderPermissionCollection(Folder owner) {
		super();
		this.isCalendarFolder = owner instanceof CalendarFolder;
	}

	/**
	 * * Gets the name of the inner collection XML element.
	 * 
	 * @return the inner collection xml element name
	 */
	private String getInnerCollectionXmlElementName() {
		return this.isCalendarFolder ? XmlElementNames.CalendarPermissions :
				 XmlElementNames.Permissions;
	}

	/**
	 * * Gets the name of the collection item XML element.
	 * 
	 * @return the collection item xml element name
	 */
	private String getCollectionItemXmlElementName() {
		return this.isCalendarFolder ? XmlElementNames.CalendarPermission :
				 XmlElementNames.Permission;
	}

	/**
	 * * Gets the name of the collection item XML element.
	 * 
	 * @param complexProperty
	 *            the complex property
	 * @return the collection item xml element name
	 */
	@Override
	protected String getCollectionItemXmlElementName(
			FolderPermission complexProperty) {
		return this.getCollectionItemXmlElementName();
	}

	/**
	 * * Loads from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @param localElementName
	 *            the local element name
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void loadFromXml(EwsServiceXmlReader reader,
			String localElementName) throws Exception {
		reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types,
				localElementName);

		reader.readStartElement(XmlNamespace.Types, this
				.getInnerCollectionXmlElementName());
		super.loadFromXml(reader, this.getInnerCollectionXmlElementName());
		reader.readEndElementIfNecessary(XmlNamespace.Types, this
				.getInnerCollectionXmlElementName());

		reader.read();

		if (reader.isStartElement(XmlNamespace.Types,
				XmlElementNames.UnknownEntries)) {
			do {
				reader.read();

				if (reader.isStartElement(XmlNamespace.Types,
						XmlElementNames.UnknownEntry)) {
					this.unknownEntries.add(reader.readElementValue());
				}
			} while (!reader.isEndElement(XmlNamespace.Types,
					XmlElementNames.UnknownEntries));
		}
	}

	/***
	 * Validates this instance.
	 */
	public void validate() {
		for (int permissionIndex = 0; permissionIndex < this.getItems().size(); permissionIndex++) {
			FolderPermission permission = this.getItems().get(permissionIndex);
			try {
				permission.validate(this.isCalendarFolder, permissionIndex);
			} catch (ServiceValidationException e) {				
				e.printStackTrace();
			} catch (ServiceLocalException e) {				
				e.printStackTrace();
			}
		}
	}

	/**
	 * * Writes the elements to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		writer.writeStartElement(XmlNamespace.Types, this
				.getInnerCollectionXmlElementName());
		for (FolderPermission folderPermission : this) {
			folderPermission.writeToXml(writer, this
					.getCollectionItemXmlElementName(folderPermission),
					this.isCalendarFolder);
		}
		writer.writeEndElement(); // this.InnerCollectionXmlElementName
	}

	/**
	 * * Creates the complex property.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @return FolderPermission instance.
	 */
	@Override
	protected FolderPermission createComplexProperty(String xmlElementName) {
		return new FolderPermission();
	}

	/**
	 * * Adds a permission to the collection.
	 * 
	 * @param permission
	 *            the permission
	 */
	public void add(FolderPermission permission) {
		this.internalAdd(permission);
	}

	/**
	 * * Adds the specified permissions to the collection.
	 * 
	 * @param permissions
	 *            the permissions
	 * @throws Exception
	 *             the exception
	 */
	public void addFolderRange(Iterator<FolderPermission> permissions)
			throws Exception {
		EwsUtilities.validateParam(permissions, "permissions");

		if (null != permissions) {
			while (permissions.hasNext()) {
				this.add(permissions.next());
			}
		}
	}

	/***
	 * Clears this collection.
	 */
	public void clear() {
		this.internalClear();
	}

	/**
	 * * Removes a permission from the collection.
	 * 
	 * @param permission
	 *            the permission
	 * @return True if the folder permission was successfully removed from the
	 *         collection, false otherwise.
	 */
	public boolean remove(FolderPermission permission) {
		return this.internalRemove(permission);
	}

	/**
	 * * Removes a permission from the collection.
	 * 
	 * @param index
	 *            the index
	 */
	public void removeAt(int index) {
		this.internalRemoveAt(index);
	}

	/**
	 * * Gets a list of unknown user Ids in the collection.
	 * 
	 * @return the unknown entries
	 */
	public Collection<String> getUnknownEntries() {
		return this.unknownEntries;
	}
}
