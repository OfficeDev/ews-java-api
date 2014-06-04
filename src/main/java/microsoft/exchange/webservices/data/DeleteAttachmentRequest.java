/**************************************************************************
 * copyright file="DeleteAttachmentRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DeleteAttachmentRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a DeleteAttachment request.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * The Class DeleteAttachmentRequest.
 */
final class DeleteAttachmentRequest extends
		MultiResponseServiceRequest<DeleteAttachmentResponse> {

	/** The attachments. */
	private List<Attachment> attachments = new ArrayList<Attachment>();

	/**
	 * * Initializes a new instance of the DeleteAttachmentRequest class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected DeleteAttachmentRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/***
	 * Validate request.
	 */
	@Override
	protected void validate() {
		try {
			super.validate();
			EwsUtilities.validateParamCollection(this.getAttachments()
					.iterator(), "Attachments");
			for (int i = 0; i < this.attachments.size(); i++) {
				EwsUtilities.validateParam(this.attachments.get(i).getId(),
						String.format("Attachment[%d].Id ", i));
			}
		} catch (ServiceLocalException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	/**
	 * * Creates the service response.
	 * 
	 * @param service
	 *            the service
	 * @param responseIndex
	 *            the response index
	 * @return Service object.
	 */
	@Override
	protected DeleteAttachmentResponse createServiceResponse(
			ExchangeService service, int responseIndex) {
		return new DeleteAttachmentResponse(
				this.attachments.get(responseIndex));
	}

	/***
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected response messages.
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return this.attachments.size();
	}

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.DeleteAttachment;
	}

	/***
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.DeleteAttachmentResponse;
	}

	/***
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.DeleteAttachmentResponseMessage;
	}

	/**
	 * * Writes XML elements.
	 * 
	 * @param writer
	 *            the writer
	 * @return The writer.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		writer.writeStartElement(XmlNamespace.Messages,
				XmlElementNames.AttachmentIds);

		for (Attachment attachment : this.attachments) {
			writer.writeStartElement(XmlNamespace.Types,
					XmlElementNames.AttachmentId);
			writer
					.writeAttributeValue(XmlAttributeNames.Id, attachment
							.getId());
			writer.writeEndElement();
		}

		writer.writeEndElement();
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
	 * * Gets the attachments.
	 * 
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return this.attachments;
	}
}
