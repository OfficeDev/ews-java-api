/**************************************************************************
 * copyright file="CreateItemRequestBase.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateItemRequestBase.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Collection;

/***
 * Represents an abstract CreateItem request.
 * 
 * 
 * @param <TServiceObject>
 *            The type of the service object.
 * @param <TResponse>
 *            The type of the response.
 */
abstract class CreateItemRequestBase<TServiceObject extends ServiceObject, 
TResponse extends ServiceResponse>
		extends CreateRequest<TServiceObject, TResponse> {

	/** The message disposition. */
	private MessageDisposition messageDisposition = null;

	/** The send invitations mode. */
	private SendInvitationsMode sendInvitationsMode = null;

	/**
	 * * Initializes a new instance.
	 * 
	 * @param service
	 *            The service.
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected CreateItemRequestBase(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * Validate the request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParam(this.getItems(), "Items");
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.CreateItem;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.CreateItemResponse;
	}

	/**
	 * Gets the name of the response message XML element. XML element name.
	 * 
	 * @return the response message xml element name
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.CreateItemResponseMessage;
	}

	/**
	 * Gets the name of the parent folder XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getParentFolderXmlElementName() {
		return XmlElementNames.SavedItemFolderId;
	}

	/**
	 * Gets the name of the object collection XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getObjectCollectionXmlElementName() {
		return XmlElementNames.Items;
	}

	/**
	 * Writes the attributes to XML.
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
		if (this.messageDisposition != null) {
			writer.writeAttributeValue(XmlAttributeNames.MessageDisposition,
					this.getMessageDisposition());
		}
		if (this.sendInvitationsMode != null) {
			writer.writeAttributeValue(
					XmlAttributeNames.SendMeetingInvitations,
					this.sendInvitationsMode);
		}
	}

	/**
	 * Gets the message disposition.
	 * 
	 * @return the message disposition
	 */
	public MessageDisposition getMessageDisposition() {
		return messageDisposition;
	}

	/**
	 * Sets the message disposition.
	 * 
	 * @param value
	 *            the new message disposition
	 */
	public void setMessageDisposition(MessageDisposition value) {
		messageDisposition = value;
	}

	/**
	 * Gets  the send invitations mode.
	 * 
	 * @return the send invitations mode
	 */
	public SendInvitationsMode getSendInvitationsMode() {
		return sendInvitationsMode;
	}

	/**
	 * Sets the send invitations mode.
	 * 
	 * @param value
	 *            the new send invitations mode
	 */
	public void setSendInvitationsMode(SendInvitationsMode value) {
		sendInvitationsMode = value;
	}

	/**
	 * Gets  the items.
	 * 
	 * @param value
	 *            the new items
	 */
	public void setItems(Collection<TServiceObject> value) {
		this.setObjects(value);
	}

	/**
	 * Gets the items.
	 * 
	 * @return the items
	 */
	public Iterable<TServiceObject> getItems() {
		return this.getObjects();
	}

}
