/**************************************************************************
 * copyright file="DelegateUser.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DelegateUser.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a delegate user.
 */
public final class DelegateUser extends ComplexProperty {

	/** The user id. */
	private UserId userId = new UserId();

	/** The permissions. */
	private DelegatePermissions permissions = new DelegatePermissions();

	/** The receive copies of meeting messages. */
	private boolean receiveCopiesOfMeetingMessages;

	/** The view private items. */
	private boolean viewPrivateItems;

	/**
	 * Initializes a new instance of the <see cref="DelegateUser"/> class.
	 * 
	 */
	public DelegateUser() {
		super();
		this.receiveCopiesOfMeetingMessages = false;
		this.viewPrivateItems = false;
	}

	/**
	 * Initializes a new instance of the <see cref="DelegateUser"/> class.
	 * 
	 * @param primarySmtpAddress
	 *            the primary smtp address
	 */
	public DelegateUser(String primarySmtpAddress) {
		this();
		this.userId.setPrimarySmtpAddress(primarySmtpAddress);
	}

	/**
	 * Initializes a new instance of the <see cref="DelegateUser"/> class.
	 * 
	 * @param standardUser
	 *            the standard user
	 */
	public DelegateUser(StandardUser standardUser) {
		this();

		this.userId.setStandardUser(standardUser);
	}

	/**
	 * Gets the user Id of the delegate user.
	 * 
	 * @return the user id
	 */
	public UserId getUserId() {
		return this.userId;
	}

	/**
	 * Gets the list of delegate user's permissions.
	 * 
	 * @return the permissions
	 */
	public DelegatePermissions getPermissions() {
		return this.permissions;
	}

	/**
	 * Gets  a value indicating if the delegate user should receive
	 * copies of meeting requests.
	 * 
	 * @return the receive copies of meeting messages
	 */
	public boolean getReceiveCopiesOfMeetingMessages() {
		return this.receiveCopiesOfMeetingMessages;

	}

	/**
	 * Sets the receive copies of meeting messages.
	 * 
	 * @param value
	 *            the new receive copies of meeting messages
	 */
	public void setReceiveCopiesOfMeetingMessages(boolean value) {
		this.receiveCopiesOfMeetingMessages = value;
	}

	/**
	 * Gets  a value indicating if the delegate user should be
	 * able to view the principal's private items.
	 * 
	 * @return the view private items
	 */
	public boolean getViewPrivateItems() {
		return this.viewPrivateItems;

	}

	/**
	 * Gets  a value indicating if the delegate user should be able to
	 * view the principal's private items.
	 * 
	 * @param value
	 *            the new view private items
	 */
	public void setViewPrivateItems(boolean value) {

		this.viewPrivateItems = value;
	}

	/**
	 * Tries to read element from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @return true, if successful
	 * @throws Exception
	 *             the exception
	 */
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
	throws Exception {
		if (reader.getLocalName().equals(XmlElementNames.UserId)) {

			this.userId = new UserId();
			this.userId.loadFromXml(reader, reader.getLocalName());
			return true;
		} else if (reader.getLocalName().equals(XmlElementNames.UserId)) {

			this.permissions.reset();
			this.permissions.loadFromXml(reader, reader.getLocalName());
			return true;
		} else if (reader.getLocalName().equals(
				XmlElementNames.ReceiveCopiesOfMeetingMessages)) {

			this.receiveCopiesOfMeetingMessages = reader
			.readElementValue(Boolean.class);
			return true;
		} else if (reader.getLocalName().equals(
				XmlElementNames.ViewPrivateItems)) {

			this.viewPrivateItems = reader.readElementValue(Boolean.class);
			return true;
		} else {

			return false;
		}
	}

	/**
	 * Writes elements to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
	throws Exception {
		this.getUserId().writeToXml(writer, XmlElementNames.UserId);
		this.getPermissions().writeToXml(writer,
				XmlElementNames.DelegatePermissions);

		writer.writeElementValue(XmlNamespace.Types,
				XmlElementNames.ReceiveCopiesOfMeetingMessages,
				this.receiveCopiesOfMeetingMessages);

		writer.writeElementValue(XmlNamespace.Types,
				XmlElementNames.ViewPrivateItems, this.viewPrivateItems);
	}

	/**
	 * Validates this instance.
	 * 
	 * @throws ServiceValidationException
	 *             the service validation exception
	 */
	protected void internalValidate() throws ServiceValidationException {
		if (this.getUserId() == null) {
			throw new ServiceValidationException(
					Strings.UserIdForDelegateUserNotSpecified);
		} else if (!this.getUserId().isValid()) {
			throw new ServiceValidationException(
					Strings.DelegateUserHasInvalidUserId);
		}
	}
	
	/**
	 * Validates this instance for AddDelegate.	
	 * @throws Exception 
	 * @throws ServiceValidationException 
	 */
	protected void validateAddDelegate() throws ServiceValidationException,
	Exception {
		{
			this.permissions.validateAddDelegate();
		}
	}
	
	/**
	 * Validates this instance for UpdateDelegate.	 
	 */
	protected void validateUpdateDelegate() throws Exception {
		{
			this.permissions.validateUpdateDelegate();
		}
	}
}