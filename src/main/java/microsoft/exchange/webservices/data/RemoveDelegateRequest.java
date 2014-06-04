/**************************************************************************
 * copyright file="RemoveDelegateRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the RemoveDelegateRequest class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a RemoveDelete request.
 */
class RemoveDelegateRequest extends
		DelegateManagementRequestBase<DelegateManagementResponse> {

	/** The user ids. */
	private List<UserId> userIds = new ArrayList<UserId>();

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected RemoveDelegateRequest(ExchangeService service)
			throws Exception {
		super(service);
	}

	/**
	 * Asserts the valid.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParamCollection(this.getUserIds().iterator(),
				"UserIds");
	}

	/**
	 * Asserts the valid.
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
		writer
				.writeStartElement(XmlNamespace.Messages,
						XmlElementNames.UserIds);

		for (UserId userId : this.getUserIds()) {
			userId.writeToXml(writer, XmlElementNames.UserId);
		}

		writer.writeEndElement(); // UserIds
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.RemoveDelegateResponse;
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.RemoveDelegate;
	}

	/**
	 * Creates the response.
	 * 
	 * @return Service response
	 */
	@Override
	protected DelegateManagementResponse createResponse() {
		return new DelegateManagementResponse(false, null);
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
	 * Gets the user ids. <value>The user ids.</value>
	 * 
	 * @return the user ids
	 */
	public List<UserId> getUserIds() {
		return this.userIds;
	}
}
