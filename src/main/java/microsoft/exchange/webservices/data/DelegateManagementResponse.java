/**************************************************************************
 * copyright file="DelegateManagementResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DelegateManagementResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***
 * Represents the response to a delegate managent-related operation.
 */
class DelegateManagementResponse extends ServiceResponse {

	/** The read delegate users. */
	private boolean readDelegateUsers;

	/** The delegate users. */
	private List<DelegateUser> delegateUsers;

	/** The delegate user responses. */
	private Collection<DelegateUserResponse> delegateUserResponses;

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param readDelegateUsers
	 *            the read delegate users
	 * @param delegateUsers
	 *            the delegate users
	 */
	protected DelegateManagementResponse(boolean readDelegateUsers,
			List<DelegateUser> delegateUsers) {
		super();
		this.readDelegateUsers = readDelegateUsers;
		this.delegateUsers = delegateUsers;
	}

	/**
	 * * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (this.getErrorCode() == ServiceError.NoError) {
			this.delegateUserResponses = new ArrayList<DelegateUserResponse>();

			reader.read();

			if (reader.isStartElement(XmlNamespace.Messages,
					XmlElementNames.ResponseMessages)) {
				int delegateUserIndex = 0;
				do {
					reader.read();
					if (reader.isStartElement(XmlNamespace.Messages,
							XmlElementNames.DelegateUserResponseMessageType)) {
						DelegateUser delegateUser = null;
						if (this.readDelegateUsers &&
								 (this.delegateUsers != null)) {
							delegateUser = this.delegateUsers
									.get(delegateUserIndex);
						}

						DelegateUserResponse delegateUserResponse = 
							new DelegateUserResponse(
								readDelegateUsers, delegateUser);
						delegateUserResponse
								.loadFromXml(
										reader,
										XmlElementNames.
										DelegateUserResponseMessageType);
						this.delegateUserResponses.add(delegateUserResponse);

						delegateUserIndex++;
					}
				} while (!reader.isEndElement(XmlNamespace.Messages,
						XmlElementNames.ResponseMessages));
			}
		}
	}

	/**
	 * * Gets a collection of responses for each of the delegate users concerned
	 * by the operation.
	 * 
	 * @return the delegate user responses
	 */
	protected Collection<DelegateUserResponse> getDelegateUserResponses() {
		return this.delegateUserResponses;
	}
}
