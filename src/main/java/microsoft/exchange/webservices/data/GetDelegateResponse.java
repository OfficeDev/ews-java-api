/**************************************************************************
 * copyright file="GetDelegateResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetDelegateResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Class GetDelegateResponse.
 */
final class GetDelegateResponse extends DelegateManagementResponse {

	/**
	 * Represents the response to a delegate user retrieval operation.
	 */
	private MeetingRequestsDeliveryScope meetingRequestsDeliveryScope = 
		MeetingRequestsDeliveryScope.NoForward;

	/**
	 * Initializes a new instance of the class.	 
	 * @param readDelegateUsers
	 *            the read delegate users
	 */
	protected GetDelegateResponse(boolean readDelegateUsers) {
		super(readDelegateUsers, null);
	}

	/**
	 * Reads response elements from XML	
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
	throws Exception {
		super.readElementsFromXml(reader);

		if (this.getErrorCode() == ServiceError.NoError) {
			// This is a hack. If there were no response messages, the reader
			// will already be on the
			// DeliverMeetingRequests start element, so we don't need to read
			// it.
			if (this.getDelegateUserResponses().size() > 0) {
				reader.read();
			}
			if (reader.isStartElement(XmlNamespace.Messages, XmlElementNames.DeliverMeetingRequests))
			{
				this.meetingRequestsDeliveryScope = reader
				.readElementValue(MeetingRequestsDeliveryScope.class);
			}
		}
	}

	/**
	 * Gets a value indicating if and how meeting requests are delivered to
	 * delegates.	
	 * @return the meeting requests delivery scope
	 */
	protected MeetingRequestsDeliveryScope getMeetingRequestsDeliveryScope() {
		return this.meetingRequestsDeliveryScope;
	}
}
