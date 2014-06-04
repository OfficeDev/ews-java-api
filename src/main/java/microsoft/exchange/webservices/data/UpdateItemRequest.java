/**************************************************************************
 * copyright file="UpdateItemRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the UpdateItemRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class UpdateItemRequest.
 */
final class UpdateItemRequest extends
		MultiResponseServiceRequest<UpdateItemResponse> {

	/** The items. */
	private List<Item> items = new ArrayList<Item>();

	/** The saved items destination folder. */
	private FolderId savedItemsDestinationFolder;

	/** The conflict resolution mode. */
	private ConflictResolutionMode conflictResolutionMode;

	/** The message disposition. */
	private MessageDisposition messageDisposition;

	/** The send invitations or cancellations mode. */
	private SendInvitationsOrCancellationsMode 
			sendInvitationsOrCancellationsMode;

	/**
	 * Instantiates a new update item request.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected UpdateItemRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see microsoft.exchange.webservices.ServiceRequestBase#validate()
	 */
	@Override
	protected void validate() throws ServiceLocalException, Exception {
		super.validate();
		EwsUtilities.validateParamCollection(this.getItems().iterator(),
				"Items");
		for (int i = 0; i < this.getItems().size(); i++) {
			if ((this.getItems().get(i) == null) ||
					 this.getItems().get(i).isNew()) {
				throw new ArgumentException(String.format(
						Strings.ItemToUpdateCannotBeNullOrNew, i));
			}
		}

		if (this.savedItemsDestinationFolder != null) {
			this.savedItemsDestinationFolder.validate(this.getService()
					.getRequestedServerVersion());
		}

		// Validate each item.
		for (Item item : this.getItems()) {
			item.validate();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seemicrosoft.exchange.webservices.MultiResponseServiceRequest#
	 * createServiceResponse(microsoft.exchange.webservices.ExchangeService,
	 * int)
	 */
	@Override
	protected UpdateItemResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new UpdateItemResponse(this.getItems().get(responseIndex));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * microsoft.exchange.webservices.ServiceRequestBase#getXmlElementName()
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.UpdateItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * microsoft.exchange.webservices.ServiceRequestBase
	 * #getResponseXmlElementName
	 * ()
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.UpdateItemResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seemicrosoft.exchange.webservices.MultiResponseServiceRequest#
	 * getResponseMessageXmlElementName()
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.UpdateItemResponseMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seemicrosoft.exchange.webservices.MultiResponseServiceRequest#
	 * getExpectedResponseMessageCount()
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return this.items.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * microsoft.exchange.webservices.ServiceRequestBase#writeAttributesToXml
	 * (microsoft.exchange.webservices.EwsServiceXmlWriter)
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		super.writeAttributesToXml(writer);

		if (this.messageDisposition != null) {
			writer.writeAttributeValue(XmlAttributeNames.MessageDisposition,
					this.messageDisposition);
		}

		writer.writeAttributeValue(XmlAttributeNames.ConflictResolution,
				this.conflictResolutionMode);

		if (this.sendInvitationsOrCancellationsMode != null) {
			writer.writeAttributeValue(
					XmlAttributeNames.SendMeetingInvitationsOrCancellations,
					this.sendInvitationsOrCancellationsMode);
		}
	}

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
		if (this.savedItemsDestinationFolder != null) {
			writer.writeStartElement(XmlNamespace.Messages,
					XmlElementNames.SavedItemFolderId);
			this.savedItemsDestinationFolder.writeToXml(writer);
			writer.writeEndElement();
		}

		writer.writeStartElement(XmlNamespace.Messages,
				XmlElementNames.ItemChanges);

		for (Item item : this.items) {
			item.writeToXmlForUpdate(writer);
		}

		writer.writeEndElement();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seemicrosoft.exchange.webservices.ServiceRequestBase#
	 * getMinimumRequiredServerVersion()
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * Gets the message disposition.
	 * 
	 * @return the message disposition
	 */
	public MessageDisposition getMessageDisposition() {
		return this.messageDisposition;
	}

	/**
	 * Sets the message disposition.
	 * 
	 * @param value
	 *            the new message disposition
	 */
	public void setMessageDisposition(MessageDisposition value) {
		this.messageDisposition = value;
	}

	/**
	 * Gets the conflict resolution mode.
	 * 
	 * @return the conflict resolution mode
	 */
	public ConflictResolutionMode getConflictResolutionMode() {
		return this.conflictResolutionMode;
	}

	/**
	 * Sets the conflict resolution mode.
	 * 
	 * @param value
	 *            the new conflict resolution mode
	 */
	public void setConflictResolutionMode(ConflictResolutionMode value) {
		this.conflictResolutionMode = value;
	}

	/**
	 * Gets the send invitations or cancellations mode.
	 * 
	 * @return the send invitations or cancellations mode
	 */
	public SendInvitationsOrCancellationsMode 
			getSendInvitationsOrCancellationsMode() {
		return this.sendInvitationsOrCancellationsMode;
	}

	/**
	 * Sets the send invitations or cancellations mode.
	 * 
	 * @param value
	 *            the new send invitations or cancellations mode
	 */
	public void setSendInvitationsOrCancellationsMode(
			SendInvitationsOrCancellationsMode value) {
		this.sendInvitationsOrCancellationsMode = value;
	}

	/**
	 * Gets the items.
	 * 
	 * @return the items
	 */
	public List<Item> getItems() {
		return this.items;
	}

	/**
	 * Gets the saved items destination folder.
	 * 
	 * @return the saved items destination folder
	 */
	public FolderId getSavedItemsDestinationFolder() {
		return this.savedItemsDestinationFolder;
	}

	/**
	 * Sets the saved items destination folder.
	 * 
	 * @param value
	 *            the new saved items destination folder
	 */
	public void setSavedItemsDestinationFolder(FolderId value) {
		this.savedItemsDestinationFolder = value;
	}

}
