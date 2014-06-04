/**************************************************************************
 * copyright file="GetDelegateRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetDelegateRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a GetDelegate request.
 */
class GetDelegateRequest extends
		DelegateManagementRequestBase<GetDelegateResponse> {

	/** The user ids. */
	private List<UserId> userIds = new ArrayList<UserId>();

	/** The include permissions. */
	private boolean includePermissions;

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected GetDelegateRequest(ExchangeService service)
			throws Exception {
		super(service);
	}

	/**
	 * Creates the response.
	 * 
	 * @return Service response.
	 */
	@Override
	protected GetDelegateResponse createResponse() {
		return new GetDelegateResponse(true);
	}

	/**
	 * Writes XML attributes.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		super.writeAttributesToXml(writer);
		writer.writeAttributeValue(XmlAttributeNames.IncludePermissions, this
				.getIncludePermissions());
	}

	/**
	 * Writes the elements to XML.
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

		if (this.getUserIds().size() > 0) {
			writer.writeStartElement(XmlNamespace.Messages,
					XmlElementNames.UserIds);

			for (UserId userId : this.getUserIds()) {
				userId.writeToXml(writer, XmlElementNames.UserId);
			}

			writer.writeEndElement(); // UserIds
		}
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.GetDelegateResponse;
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.GetDelegate;
	}

	/**
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported
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

	/**
	 * Gets  a value indicating whether permissions are included.
	 * 
	 * @return the include permissions
	 */
	public boolean getIncludePermissions() {
		return this.includePermissions;

	}

	/**
	 * Sets the include permissions.
	 * 
	 * @param includePermissions
	 *            the new include permissions
	 */
	public void setIncludePermissions(boolean includePermissions) {
		this.includePermissions = includePermissions;
	}
}
