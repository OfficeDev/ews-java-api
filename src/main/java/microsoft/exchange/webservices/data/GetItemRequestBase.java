/**************************************************************************
 * copyright file="GetItemRequestBase.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetItemRequestBase.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * * Represents an abstract GetItem request.
 * 
 * @param <TResponse>
 *            the generic type
 */
abstract class GetItemRequestBase<TResponse extends ServiceResponse> extends
		GetRequest<Item, TResponse> {

	/** The item ids. */
	private ItemIdWrapperList itemIds = new ItemIdWrapperList();

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected GetItemRequestBase(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
			throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * * Validate request.
	 * 
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	protected void validate() throws ServiceLocalException, Exception {
		super.validate();
		EwsUtilities.validateParamCollection(this.getItemIds().iterator(),
				"ItemIds");
	}

	/***
	 *Gets the expected response message count.
	 * 
	 * @return Number of expected response messages
	 */
	protected int getExpectedResponseMessageCount() {
		return this.itemIds.getCount();
	}

	/***
	 *Gets the type of the service object this request applies to.
	 * 
	 * @return The type of service object the request applies to
	 */
	protected ServiceObjectType getServiceObjectType() {
		return ServiceObjectType.Item;
	}

	/**
	 * * Writes XML elements.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		super.writeElementsToXml(writer);

		this.itemIds.writeToXml(writer, XmlNamespace.Messages,
				XmlElementNames.ItemIds);
	}

	/***
	 *Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	protected String getXmlElementName() {
		return XmlElementNames.GetItem;
	}

	/***
	 *Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	protected String getResponseXmlElementName() {
		return XmlElementNames.GetItemResponse;
	}

	/***
	 *Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.GetItemResponseMessage;
	}

	/***
	 *Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported
	 */
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * * Gets the item ids.
	 * 
	 * @return the item ids
	 */
	public ItemIdWrapperList getItemIds() {
		return this.itemIds;
	}
}
