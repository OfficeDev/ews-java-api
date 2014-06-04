/**************************************************************************
 * copyright file="CreateRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Collection;

/***
 * Represents an abstract Create request.
 * 
 * @param <TServiceObject>
 *            The type of the service object.
 * @param <TResponse>
 *            The type of the response.
 */
abstract class CreateRequest<TServiceObject extends ServiceObject, 
TResponse extends ServiceResponse>
		extends MultiResponseServiceRequest<TResponse> {

	/** The parent folder id. */
	private FolderId parentFolderId;

	/** The objects. */
	private Collection<TServiceObject> objects;

	/**
	 * * Initializes a new instance.
	 * 
	 * @param service
	 *            The service.
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected CreateRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * Validates the request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		if (this.getParentFolderId() != null) {
			this.getParentFolderId().validate(
					this.getService().getRequestedServerVersion());
		}
	}

	/**
	 * Gets the expected response message count.
	 * 
	 * @return the expected response message count
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return EwsUtilities.getEnumeratedObjectCount(this.objects.iterator());
	}

	/***
	 * Gets the name of the parent folder XML element.
	 * 
	 * @return The name of the parent folder XML element.
	 */
	protected abstract String getParentFolderXmlElementName();

	/***
	 * Gets the name of the object collection XML element.
	 * 
	 * @return The name of the object collection XML element.
	 */
	protected abstract String getObjectCollectionXmlElementName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * microsoft.exchange.webservices.ServiceRequestBase#writeElementsToXml(
	 * microsoft.exchange.webservices.EwsServiceXmlWriter)
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		if (this.parentFolderId != null) {
			writer.writeStartElement(XmlNamespace.Messages, this
					.getParentFolderXmlElementName());
			this.getParentFolderId().writeToXml(writer);
			writer.writeEndElement();
		}

		writer.writeStartElement(XmlNamespace.Messages, this
				.getObjectCollectionXmlElementName());
		if (null != this.objects) {
			for (ServiceObject obj : this.objects) {
				obj.writeToXml(writer);
			}
		}
		writer.writeEndElement();

	}

	/**
	 * * Gets the service objects.
	 * 
	 * @return Iterator
	 */
	protected Iterable<TServiceObject> getObjects() {
		return this.objects;
	}

	/***
	 * Sets the service objects.
	 * 
	 * @param value
	 *            Iterator<TServiceObject>
	 */
	protected void setObjects(Collection<TServiceObject> value) {
		this.objects = value;
	}

	/***
	 * Gets the parent folder id.
	 * 
	 * @return FolderId.
	 */
	public FolderId getParentFolderId() {
		return this.parentFolderId;
	}

	/***
	 * Sets the parent folder id.
	 * 
	 * @param value
	 *            FolderId.
	 */
	public void setParentFolderId(FolderId value) {
		this.parentFolderId = value;
	}
}
