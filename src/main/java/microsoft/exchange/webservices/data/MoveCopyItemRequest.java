/**************************************************************************
 * copyright file="MoveCopyItemRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the MoveCopyItemRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * * Represents an abstract Move/Copy Item request. <typeparam
 * name="TResponse">The type of the response.</typeparam>
 * 
 * @param <TResponse>
 *            the generic type
 */
public abstract class MoveCopyItemRequest<TResponse extends ServiceResponse>
extends MoveCopyRequest<Item, TResponse> {

	/***
	 * Represents an abstract Move/Copy Item request. <typeparam
	 * name="TResponse">The type of the response.</typeparam>
	 */
	private ItemIdWrapperList itemIds = new ItemIdWrapperList();
	private Boolean newItemIds;

	/**
	 * * Validates request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public void validate() throws Exception {
		super.validate();
		EwsUtilities.validateParam(this.getItemIds(), "ItemIds");
	}

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @param errorHandlingMode
	 *            the error handling mode
	 * @throws Exception 
	 */
	protected MoveCopyItemRequest(ExchangeService service,
			ServiceErrorHandling errorHandlingMode)
	throws Exception {
		super(service, errorHandlingMode);
	}

	/**
	 * * Writes the ids as XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeIdsToXml(EwsServiceXmlWriter writer) throws Exception {
		this.getItemIds().writeToXml(writer, XmlNamespace.Messages,
				XmlElementNames.ItemIds);
		if (this.getReturnNewItemIds() != null) {
            writer.writeElementValue(
                XmlNamespace.Messages,
                XmlElementNames.ReturnNewItemIds,
                this.getReturnNewItemIds());
        }
	}

	/***
	 * Gets the expected response message count.
	 * 
	 * @return Number of expected response messages.
	 */
	@Override
	protected int getExpectedResponseMessageCount() {
		return this.getItemIds().getCount();
	}

	/**
	 * * Gets the item ids. <value>The item ids.</value>
	 * 
	 * @return the item ids
	 */
	protected ItemIdWrapperList getItemIds() {
		return this.itemIds;
	}

	protected Boolean getReturnNewItemIds() {
		return this.newItemIds;
	}
	
	protected void setReturnNewItemIds(Boolean value) {
		this.newItemIds = value;
	}
}
