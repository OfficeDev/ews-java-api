/**************************************************************************
 * copyright file="MoveCopyRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MoveCopyRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an abstract Move/Copy request.
 * 
 * 
 * @param <TServiceObject>
 *            The type of the service object.
 * @param <TResponse>
 *            The type of the response.
 */
abstract class MoveCopyRequest<TServiceObject extends ServiceObject, 
		TResponse extends ServiceResponse> extends
		 MultiResponseServiceRequest<TResponse> {

	/** The destination folder id. */
	private FolderId destinationFolderId;

	/**
	 * Validates request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		EwsUtilities.validateParam(this.getDestinationFolderId(),
				"DestinationFolderId");
		this.getDestinationFolderId().validate(
				this.getService().getRequestedServerVersion());
	}

	/**
	 * Initializes a new instance of the MoveCopyRequest class.
	 * 
	 * @param service
	 *            The Service
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected MoveCopyRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * Writes the ids as XML.
	 * 
	 * @param writer
	 *            The Writer
	 * @throws Exception
	 *             the exception
	 */
	protected abstract void writeIdsToXml(EwsServiceXmlWriter writer)
			throws Exception;

	/**
	 * Writes XML elements.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		writer.writeStartElement(XmlNamespace.Messages,
				XmlElementNames.ToFolderId);
		this.getDestinationFolderId().writeToXml(writer);
		writer.writeEndElement();

		this.writeIdsToXml(writer);
	}

	/**
	 * Gets the destination folder id.
	 * 
	 * @return the destination folder id
	 */
	public FolderId getDestinationFolderId() {
		return this.destinationFolderId;
	}

	/**
	 * Sets the destination folder id.
	 * 
	 * @param destinationFolderId
	 *            the new destination folder id
	 */
	public void setDestinationFolderId(FolderId destinationFolderId) {
		this.destinationFolderId = destinationFolderId;
	}

}
