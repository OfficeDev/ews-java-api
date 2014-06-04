/**************************************************************************
 * copyright file="DelegateUserResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DelegateUserResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the response to an individual delegate user manipulation (add,
 * remove, update) operation.
 */
public final class DelegateUserResponse extends ServiceResponse {

	/** The read delegate user. */
	private boolean readDelegateUser;

	/** The delegate user. */
	private DelegateUser delegateUser;

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param readDelegateUser
	 *            the read delegate user
	 * @param delegateUser
	 *            the delegate user
	 */
	protected DelegateUserResponse(boolean readDelegateUser,
			DelegateUser delegateUser) {
		super();
		this.readDelegateUser = readDelegateUser;
		this.delegateUser = delegateUser;
	}

	/**
	 * * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (this.readDelegateUser) {
			if (this.delegateUser == null) {
				this.delegateUser = new DelegateUser();
			}

			reader.readStartElement(XmlNamespace.Messages,
					XmlElementNames.DelegateUser);

			this.delegateUser.loadFromXml(reader, XmlNamespace.Messages, reader
					.getLocalName());
		}
	}

	/**
	 * * The delegate user that was involved in the operation.
	 * 
	 * @return the delegate user
	 */
	public DelegateUser getDelegateUser() {
		return this.delegateUser;
	}

}
