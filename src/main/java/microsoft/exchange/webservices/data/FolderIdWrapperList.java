/**************************************************************************
 * copyright file="FolderIdWrapperList.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderIdWrapperList.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/***
 * Represents a list a abstracted folder Ids.
 */
class FolderIdWrapperList implements Iterable<AbstractFolderIdWrapper> {

	/** The ids. */
	private List<AbstractFolderIdWrapper> ids = new 
			ArrayList<AbstractFolderIdWrapper>();

	/**
	 * Adds the specified folder.
	 * 
	 * @param folder
	 *            the folder
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	protected void add(Folder folder) throws ServiceLocalException {
		this.ids.add(new FolderWrapper(folder));
	}

	/**
	 * Adds the range.
	 * 
	 * @param folders
	 *            the folders
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	protected void addRangeFolder(Iterable<Folder> folders)
			throws ServiceLocalException {
		if (folders != null) {
			for (Folder folder : folders) {
				this.add(folder);
			}
		}
	}

	/**
	 * Adds the specified folder id.
	 * 
	 * @param folderId
	 *            the folder id
	 */
	protected void add(FolderId folderId) {
		this.ids.add(new FolderIdWrapper(folderId));
	}

	/**
	 * Adds the range of folder ids.
	 * 
	 * @param folderIds
	 *            the folder ids
	 */
	protected void addRangeFolderId(Iterable<FolderId> folderIds) {
		if (folderIds != null) {
			for (FolderId folderId : folderIds) {
				this.add(folderId);
			}
		}
	}

	/**
	 * Writes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @param ewsNamesapce
	 *            the ews namesapce
	 * @param xmlElementName
	 *            the xml element name
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer,
			XmlNamespace ewsNamesapce, String xmlElementName) throws Exception {
		if (this.getCount() > 0) {
			writer.writeStartElement(ewsNamesapce, xmlElementName);

			for (AbstractFolderIdWrapper folderIdWrapper : this.ids) {
				folderIdWrapper.writeToXml(writer);
			}

			writer.writeEndElement();
		}
	}

	/**
	 * Gets the id count.
	 * 
	 * @return the count
	 */
	protected int getCount() {
		return this.ids.size();
	}

	/**
	 * Gets the <see
	 * cref="Microsoft.Exchange.WebServices.Data.AbstractFolderIdWrapper"/> at
	 * the specified index.
	 * 
	 * @param i
	 *            the i
	 * @return the index
	 */
	protected AbstractFolderIdWrapper getFolderIdWrapperList(int i) {
		return this.ids.get(i);
	}

	/**
	 * Validates list of folderIds against a specified request version.
	 * 
	 * @param version
	 *            the version
	 * @throws ServiceVersionException
	 *             the service version exception
	 */
	protected void validate(ExchangeVersion version)
			throws ServiceVersionException {
		for (AbstractFolderIdWrapper folderIdWrapper : this.ids) {
			folderIdWrapper.validate(version);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<AbstractFolderIdWrapper> iterator() {
		return ids.iterator();
	}

}
