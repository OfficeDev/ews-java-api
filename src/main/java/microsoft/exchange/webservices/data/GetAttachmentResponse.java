/**************************************************************************
 * copyright file="GetAttachmentResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetAttachmentResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the response to an individual attachment retrieval request.
 * 
 */
final class GetAttachmentResponse extends ServiceResponse {

	/** The attachment. */
	private Attachment attachment;

	/**
	 * Initializes a new instance of the GetAttachmentResponse class.
	 * 
	 * @param attachment
	 *            the attachment
	 */
	protected GetAttachmentResponse(Attachment attachment) {
		super();
		EwsUtilities.EwsAssert(attachment != null,
				"GetAttachmentResponse.ctor", "attachment is null");

		this.attachment = attachment;
	}

	/**
	 * Reads response elements from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		super.readElementsFromXml(reader);

		reader.readStartElement(XmlNamespace.Messages,
				XmlElementNames.Attachments);
		if (!reader.isEmptyElement()) {
			XMLNodeType x = new XMLNodeType(XMLNodeType.START_ELEMENT);
			reader.read(x);

			this.attachment.loadFromXml(reader, reader.getLocalName());

			reader.readEndElement(XmlNamespace.Messages,
					XmlElementNames.Attachments);
		} else {
			reader.read();
		}
	}

	/**
	 * Gets the attachment that was retrieved.
	 * 
	 * @return the attachment
	 */
	protected Attachment getAttachment() {
		return this.attachment;
	}

}