/**************************************************************************
 * copyright file="DeleteRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DeleteRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an abstract Delete request.
 * 
 * @param <TResponse>
 *            The type of the response.
 */
abstract class DeleteRequest<TResponse extends ServiceResponse> extends
		MultiResponseServiceRequest<TResponse> {

	/**
	 * Delete mode. Default is SoftDelete.
	 */
	private DeleteMode deleteMode = DeleteMode.SoftDelete;

	/**
	 * Initializes a new instance of the DeleteRequest class.
	 * 
	 * @param service
	 *            The Servcie
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected DeleteRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * Writes XML attributes.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		super.writeAttributesToXml(writer);

		try {
			writer.writeAttributeValue(XmlAttributeNames.DeleteType, this
					.getDeleteMode());
		} catch (ServiceXmlSerializationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the delete mode.
	 * 
	 * @return the delete mode
	 */
	public DeleteMode getDeleteMode() {
		return this.deleteMode;
	}

	/**
	 * Gets the delete mode.e
	 * 
	 * @param deleteMode
	 *            the new delete mode
	 */
	public void setDeleteMode(DeleteMode deleteMode) {
		this.deleteMode = deleteMode;
	}

}
