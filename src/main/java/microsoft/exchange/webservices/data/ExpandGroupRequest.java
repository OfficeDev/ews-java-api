/**************************************************************************
 * copyright file="ExpandGroupRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ExpandGroupRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an ExpandGroup request.
 */
public class ExpandGroupRequest extends
		MultiResponseServiceRequest<ExpandGroupResponse> {

	/** The email address. */
	private EmailAddress emailAddress;

	/**
	 * Represents an ExpandGroup request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParam(this.getEmailAddress(), "EmailAddress");
	}

	/**
	 * Creates the service response.
	 * 
	 * @param service
	 *            the service
	 * @param responseIndex
	 *            the response index
	 * @return Service response.
	 */
	@Override
	protected ExpandGroupResponse createServiceResponse(
			ExchangeService service, int responseIndex) {
		return new ExpandGroupResponse();
	}

	/**
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected response messages.
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return 1;
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.ExpandDL;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.ExpandDLResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.ExpandDLResponseMessage;
	}

	/**
	 * writes XML elements.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		if (this.getEmailAddress() != null) {
			this.getEmailAddress().writeToXml(writer, XmlNamespace.Messages,
					XmlElementNames.Mailbox);
		}
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
	 * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected ExpandGroupRequest(ExchangeService service)
			throws Exception {
		super(service, ServiceErrorHandling.ThrowOnError);
	}

	/**
	 * Gets  the email address. <value>The email address.</value>
	 * 
	 * @return the email address
	 */
	public EmailAddress getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * Sets the email address.
	 * 
	 * @param emailAddress
	 *            the new email address
	 */
	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}
}
