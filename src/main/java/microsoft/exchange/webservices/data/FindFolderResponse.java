/**************************************************************************
 * copyright file="FindFolderResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FindFolderResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the response to a folder search operation.
 */
final class FindFolderResponse extends ServiceResponse {

	/** The results. */
	private FindFoldersResults results = new FindFoldersResults();

	/** The property set. */
	private PropertySet propertySet;

	/**
	 * Reads response elements from XML.
	 * 
	 * @param reader
	 *            The reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		reader.readStartElement(XmlNamespace.Messages,
				XmlElementNames.RootFolder);

		this.results.setTotalCount(reader.readAttributeValue(Integer.class,
				XmlAttributeNames.TotalItemsInView));
		this.results.setMoreAvailable(!reader.readAttributeValue(Boolean.class,
				XmlAttributeNames.IncludesLastItemInRange));

		// Ignore IndexedPagingOffset attribute if MoreAvailable is false.
		this.results.setNextPageOffset(results.isMoreAvailable() ? reader
				.readNullableAttributeValue(Integer.class,
						XmlAttributeNames.IndexedPagingOffset) : null);

		reader.readStartElement(XmlNamespace.Types, XmlElementNames.Folders);
		if (!reader.isEmptyElement()) {
			do {
				reader.read();

				if (reader.getNodeType().nodeType == XMLNodeType.START_ELEMENT) {
					Folder folder = EwsUtilities
							.createEwsObjectFromXmlElementName(Folder.class,
									reader.getService(), reader.getLocalName());

					if (folder == null) {
						reader.skipCurrentElement();
					} else {
						folder.loadFromXml(reader, true, /* clearPropertyBag */
						this.propertySet, true /* summaryPropertiesOnly */);

						this.results.getFolders().add(folder);
					}
				}
			} while (!reader.isEndElement(XmlNamespace.Types,
					XmlElementNames.Folders));
		} else {
			reader.read();
		}

		reader
				.readEndElement(XmlNamespace.Messages,
						XmlElementNames.RootFolder);
	}

	/**
	 * Initializes a new instance of the FindFolderResponse class.
	 * 
	 * @param propertySet
	 *            The property set from, the request.
	 */
	protected FindFolderResponse(PropertySet propertySet) {
		super();
		this.propertySet = propertySet;

		EwsUtilities.EwsAssert(this.propertySet != null,
				"FindFolderResponse.ctor", "PropertySet should not be null");
	}

	/**
	 * Gets the results of the search operation.
	 * 
	 * @return the results
	 */
	public FindFoldersResults getResults() {
		return this.results;
	}

}
