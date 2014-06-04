/**************************************************************************
 * copyright file="FindItemRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FindItemRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * 
 * Represents a FindItem request.
 * 
 * @param <TItem>
 *            The type of the item.
 */
final class FindItemRequest<TItem extends Item> extends
		FindRequest<FindItemResponse<TItem>> {

	/** The group by. */
	private Grouping groupBy;

	/**
	 * Initializes a new instance of the FindItemRequest class.
	 * 
	 * @param service
	 *            The Service
	 * @param errorHandlingMode
	 *            Indicates how errors should be handled.
	 * @throws Exception 
	 */
	protected FindItemRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * Creates the service response.
	 * 
	 * @param service
	 *            The service
	 * @param responseIndex
	 *            Index of the response.
	 * @return Service response.
	 */
	@Override
	protected FindItemResponse<TItem> createServiceResponse(
			ExchangeService service, int responseIndex) {
		return new FindItemResponse<TItem>(this.getGroupBy() != null, this
				.getView().getPropertySetOrDefault());
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.FindItem;
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.FindItemResponse;
	}

	/**
	 * Gets the name of the response message XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.FindItemResponseMessage;
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
	 * Gets the group by.
	 * 
	 * @return the group by
	 */
	public Grouping getGroupBy() {
		return this.groupBy;
	}

	/**
	 * Sets the group by.
	 * 
	 * @param value
	 *            the new group by
	 */
	public void setGroupBy(Grouping value) {
		this.groupBy = value;

	}

}
