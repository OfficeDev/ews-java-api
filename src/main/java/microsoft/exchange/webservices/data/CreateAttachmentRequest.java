/**************************************************************************
 * copyright file="CreateAttachmentRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateAttachmentRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents a CreateAttachment request.
 * 
 */

final class CreateAttachmentRequest extends
		MultiResponseServiceRequest<CreateAttachmentResponse> {

	/** The parent item id. */
	private String parentItemId;

	/** The attachments. */
	private ArrayList<Attachment> attachments = new ArrayList<Attachment>();

	/**
	 * Gets the attachments.
	 * 
	 * @return attachments
	 */
	public ArrayList<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected CreateAttachmentRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * Validate request..
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParam(this.parentItemId, "ParentItemId");
	}

	/**
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected response messages.
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return this.attachments.size();
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.CreateAttachment;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.CreateAttachmentResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.CreateAttachmentResponseMessage;
	}

	/**
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}
	
	/**
	 * 
	 * Gets a value indicating whether the TimeZoneContext SOAP header should be
	 * emitted.
	 */
	protected boolean emitTimeZoneHeader() throws ServiceLocalException ,Exception{
		{

			ListIterator<Attachment> items = this.getAttachments()
					.listIterator();

			while (items.hasNext())

			{

				ItemAttachment itemAttachment = (ItemAttachment) items.next();

				if ((itemAttachment.getItem() != null)
						&& itemAttachment
								.getItem()
								.getIsTimeZoneHeaderRequired(false /* isUpdateOperation */)) {
					return true;
				}
			}

			return false;
		}
	}

	/**
	 * Gets the parent item id.
	 * 
	 * @return parentItemId
	 */
	public String getParentItemId() {
		return parentItemId;
	}

	/**
	 * Sets the parent item id.
	 * 
	 * @param parentItemId
	 *            the new parent item id
	 */
	public void setParentItemId(String parentItemId) {
		this.parentItemId = parentItemId;
	}

	/**
	 * Writes the elements to XML.
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
				XmlElementNames.ParentItemId);
		writer.writeAttributeValue(XmlAttributeNames.Id, this.parentItemId);
		writer.writeEndElement();

		writer.writeStartElement(XmlNamespace.Messages,
				XmlElementNames.Attachments);
		for (Attachment attachment : this.attachments) {
			attachment.writeToXml(writer, attachment.getXmlElementName());
		}
		writer.writeEndElement();

	}

	/**
	 * Creates the service response.
	 * 
	 * @param service
	 *            the service
	 * @param responseIndex
	 *            the response index
	 * @return the creates the attachment response
	 */
	@Override
	protected CreateAttachmentResponse createServiceResponse(
			ExchangeService service, int responseIndex) {
		return new CreateAttachmentResponse(
				this.attachments.get(responseIndex));
	}

}
