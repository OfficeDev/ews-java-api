/**************************************************************************
 * copyright file="SyncFolderHierarchyRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SyncFolderHierarchyRequest class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a SyncFolderHierarchy request.
 */
class SyncFolderHierarchyRequest extends
		MultiResponseServiceRequest<SyncFolderHierarchyResponse> {

	/** The property set. */
	private PropertySet propertySet;

	/** The sync folder id. */
	private FolderId syncFolderId;

	/** The sync state. */
	private String syncState;

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected SyncFolderHierarchyRequest(ExchangeService service)
			throws Exception {
		super(service, ServiceErrorHandling.ThrowOnError);
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
	protected SyncFolderHierarchyResponse createServiceResponse(
			ExchangeService service, int responseIndex) {
		return new SyncFolderHierarchyResponse(this.getPropertySet());
	}

	/***
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected responses
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return 1;
	}

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.SyncFolderHierarchy;
	}

	/***
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.SyncFolderHierarchyResponse;
	}

	/***
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.SyncFolderHierarchyResponseMessage;
	}

	/**
	 * * Validates request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParam(this.getPropertySet(), "PropertySet");
		if (this.getSyncFolderId() != null) {
			this.getSyncFolderId().validate(
					this.getService().getRequestedServerVersion());
		}

		this.getPropertySet()
				.validateForRequest(this, false /* summaryPropertiesOnly */);
	}

	/**
	 * * Writes XML elements.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		this.getPropertySet().writeToXml(writer, ServiceObjectType.Folder);

		if (this.getSyncFolderId() != null) {
			writer.writeStartElement(XmlNamespace.Messages,
					XmlElementNames.SyncFolderId);
			this.getSyncFolderId().writeToXml(writer);
			writer.writeEndElement();
		}

		writer.writeElementValue(XmlNamespace.Messages,
				XmlElementNames.SyncState, this.getSyncState());
	}

	/***
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * * Gets or sets the property set. <value>The property set.</value>
	 * 
	 * @return the property set
	 */
	public PropertySet getPropertySet() {
		return this.propertySet;
	}

	/**
	 * Sets the property set.
	 * 
	 * @param value
	 *            the new property set
	 */
	public void setPropertySet(PropertySet value) {
		this.propertySet = value;
	}

	/**
	 * * Gets or sets the property set. <value>The property set.</value>
	 * 
	 * @return the sync folder id
	 */
	public FolderId getSyncFolderId() {
		return this.syncFolderId;
	}

	/**
	 * Sets the sync folder id.
	 * 
	 * @param value
	 *            the new sync folder id
	 */
	public void setSyncFolderId(FolderId value) {
		this.syncFolderId = value;
	}

	/**
	 * * Gets or sets the state of the sync. <value>The state of the
	 * sync.</value>
	 * 
	 * @return the sync state
	 */
	public String getSyncState() {
		return this.syncState;
	}

	/**
	 * Sets the sync state.
	 * 
	 * @param value
	 *            the new sync state
	 */
	public void setSyncState(String value) {
		this.syncState = value;
	}

}
