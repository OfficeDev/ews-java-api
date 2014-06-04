/**************************************************************************
 * copyright file="UpdateDelegateRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the UpdateDelegateRequest class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an UpdateDelegate request.
 */
public class UpdateDelegateRequest extends
		DelegateManagementRequestBase<DelegateManagementResponse> {

	/** The delegate users. */
	private List<DelegateUser> delegateUsers = new ArrayList<DelegateUser>();

	/** The meeting requests delivery scope. */
	private MeetingRequestsDeliveryScope meetingRequestsDeliveryScope;

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected UpdateDelegateRequest(ExchangeService service)
			throws Exception {
		super(service);
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
		EwsUtilities.validateParamCollection(
				this.getDelegateUsers().iterator(), "DelegateUsers");
		for(DelegateUser delegateUser:this.getDelegateUsers()) {
			delegateUser.validateUpdateDelegate();
		}
	}

	/**
	 * Writes XML elements.
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

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.UpdateDelegateResponse;
	}

	/**
	 * Creates the response.
	 * 
	 * @return Response object.
	 */
	@Override
	protected DelegateManagementResponse createResponse() {
		return new DelegateManagementResponse(true, this.delegateUsers);
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return Xml element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.UpdateDelegate;
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
	 * Gets the meeting requests delivery scope. <value>The meeting
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
	 * @param value
	 *            the new meeting requests delivery scope
	 */
	public void setMeetingRequestsDeliveryScope(
			MeetingRequestsDeliveryScope value) {
		this.meetingRequestsDeliveryScope = value;
	}

	/**
	 * Gets the delegate users. <value>The delegate users.</value>
	 * 
	 * @return the delegate users
	 */
	public List<DelegateUser> getDelegateUsers() {
		return this.delegateUsers;
	}

}
