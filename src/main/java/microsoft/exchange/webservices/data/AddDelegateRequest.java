/**************************************************************************
 * copyright file="AddDelegateRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AddDelegateRequest class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.List;

/***
 * Represents an AddDelegate request.
 */
class AddDelegateRequest extends
		DelegateManagementRequestBase<DelegateManagementResponse> {

	/** The delegate users. */
	private List<DelegateUser> delegateUsers = new ArrayList<DelegateUser>();

	/** The meeting requests delivery scope. */
	private MeetingRequestsDeliveryScope meetingRequestsDeliveryScope;

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected AddDelegateRequest(ExchangeService service)
			throws Exception {
		super(service);
	}

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParamCollection(
				this.getDelegateUsers().iterator(), "DelegateUsers");
		 for(DelegateUser delegateUser : this.getDelegateUsers()) {
             delegateUser.validateUpdateDelegate();
         }

         if (this.meetingRequestsDeliveryScope!=null) {
             EwsUtilities.validateEnumVersionValue(this.
            		 getMeetingRequestsDeliveryScope(), 
            		 this.getService().getRequestedServerVersion());
         }
	}

	/**
	 * * Writes the elements to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		super.writeElementsToXml(writer);

		writer.writeStartElement(XmlNamespace.Messages,
				XmlElementNames.DelegateUsers);

		for (DelegateUser delegateUser : this.getDelegateUsers()) {
			delegateUser.writeToXml(writer, XmlElementNames.DelegateUser);
		}

		writer.writeEndElement(); // DelegateUsers

		if (this.getMeetingRequestsDeliveryScope() != null) {
			writer.writeElementValue(XmlNamespace.Messages,
					XmlElementNames.DeliverMeetingRequests, this
							.getMeetingRequestsDeliveryScope());
		}
	}

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.AddDelegate;
	}

	/***
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.AddDelegateResponse;
	}

	/***
	 * Creates the response.
	 * 
	 * @return Service response.
	 */
	@Override
	protected DelegateManagementResponse createResponse() {
		return new DelegateManagementResponse(true, this.delegateUsers);
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
	 * * Gets the meeting requests delivery scope. <value>The meeting
	 * requests delivery scope.</value>
	 * 
	 * @return the meeting requests delivery scope
	 */
	public MeetingRequestsDeliveryScope getMeetingRequestsDeliveryScope() {
		return this.meetingRequestsDeliveryScope;
	}

	/**
	 * Sets the meeting requests delivery scope.
	 * 
	 * @param meetingRequestsDeliveryScope
	 *            the new meeting requests delivery scope
	 */
	public void setMeetingRequestsDeliveryScope(
			MeetingRequestsDeliveryScope meetingRequestsDeliveryScope) {
		this.meetingRequestsDeliveryScope = meetingRequestsDeliveryScope;
	}

	/**
	 * * Gets the delegate users. <value>The delegate users.</value>
	 * 
	 * @return the delegate users
	 */
	public List<DelegateUser> getDelegateUsers() {
		return this.delegateUsers;
	}

}
