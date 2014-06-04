/**************************************************************************
 * copyright file="SendItemRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SendItemRequest class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a SendItem request.
 */
final class SendItemRequest extends
		MultiResponseServiceRequest<ServiceResponse> {

	/** The items. */
	private Iterable<Item> items;

	/** The saved copy destination folder id. */
	private FolderId savedCopyDestinationFolderId;

	/**
	 * * Asserts the valid.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParam(this.items, "Items");

		if (this.savedCopyDestinationFolderId != null) {
			this.savedCopyDestinationFolderId.validate(this.getService()
					.getRequestedServerVersion());
		}
	}

	/**
	 * * Creates the service response.
	 * 
	 * @param service
	 *            the service
	 * @param responseIndex
	 *            the response index
	 * @return Service response.
	 */
	@Override
	protected ServiceResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new ServiceResponse();
	}

	/***
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected response messages.
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return EwsUtilities.getEnumeratedObjectCount(this.items.iterator());
	}

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.SendItem;
	}

	/***
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.SendItemResponse;
	}

	/***
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.SendItemResponseMessage;
	}

	/**
	 * * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		super.writeAttributesToXml(writer);

		writer.writeAttributeValue(XmlAttributeNames.SaveItemToFolder,
				this.savedCopyDestinationFolderId != null);
	}

	/**
	 * * Writes the elements to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws ServiceLocalException, Exception {
		writer
				.writeStartElement(XmlNamespace.Messages,
						XmlElementNames.ItemIds);

		for (Item item : this.getItems()) {
			item.getId().writeToXml(writer, XmlElementNames.ItemId);
		}

		writer.writeEndElement(); // ItemIds

		if (this.savedCopyDestinationFolderId != null) {
			writer.writeStartElement(XmlNamespace.Messages,
					XmlElementNames.SavedItemFolderId);
			this.savedCopyDestinationFolderId.writeToXml(writer);
			writer.writeEndElement();
		}
	}

	/**
	 * * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected SendItemRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * * Gets the items. <value>The items.</value>
	 * 
	 * @return the items
	 */
	public Iterable<Item> getItems() {
		return this.items;
	}

	/**
	 * Sets the items.
	 * 
	 * @param items
	 *            the new items
	 */
	public void setItems(Iterable<Item> items) {
		this.items = items;
	}

	/**
	 * * Gets the saved copy destination folder id. <value>The saved
	 * copy destination folder id.</value>
	 * 
	 * @return the saved copy destination folder id
	 */
	public FolderId getSavedCopyDestinationFolderId() {
		return this.savedCopyDestinationFolderId;
	}

	/**
	 * Sets the saved copy destination folder id.
	 * 
	 * @param savedCopyDestinationFolderId
	 *            the new saved copy destination folder id
	 */
	public void setSavedCopyDestinationFolderId(
			FolderId savedCopyDestinationFolderId) {
		this.savedCopyDestinationFolderId = savedCopyDestinationFolderId;
	}

}
