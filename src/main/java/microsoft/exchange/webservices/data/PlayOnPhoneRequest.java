/**************************************************************************
 * copyright file="PlayOnPhoneRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PlayOnPhoneRequest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 *Represents a PlayOnPhone request.
 * 
 */
final class PlayOnPhoneRequest extends SimpleServiceRequestBase {

	/** The item id. */
	private ItemId itemId;

	/** The dial string. */
	private String dialString;

	/**
	 * Initializes a new instance of the PlayOnPhoneRequest class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected PlayOnPhoneRequest(ExchangeService service)
			throws Exception {
		super(service);
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.PlayOnPhone;
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
		this.itemId.writeToXml(writer, XmlNamespace.Messages,
				XmlElementNames.ItemId);
		writer.writeElementValue(XmlNamespace.Messages,
				XmlElementNames.DialString, dialString);
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name,
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.PlayOnPhoneResponse;
	}

	/**
	 * Parses the response.
	 * 
	 * @param reader
	 *            the reader
	 * @return Response object.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected Object parseResponse(EwsServiceXmlReader reader) 
	throws Exception {
		PlayOnPhoneResponse serviceResponse = new PlayOnPhoneResponse(this
				.getService());
		serviceResponse
				.loadFromXml(reader, XmlElementNames.PlayOnPhoneResponse);
		return serviceResponse;
	}

	/**
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2010;
	}

	/**
	 * Executes this request.
	 * 
	 * @return Service response.
	 * @throws Exception
	 *             the exception
	 */
	protected PlayOnPhoneResponse execute() throws Exception {
		PlayOnPhoneResponse serviceResponse = (PlayOnPhoneResponse)this
				.internalExecute();
		serviceResponse.throwIfNecessary();
		return serviceResponse;
	}

	/**
	 * Gets the item id of the message to play.
	 * 
	 * @return the item id
	 */
	protected ItemId getItemId() {
		return this.itemId;
	}

	/**
	 * Sets the item id.
	 * 
	 * @param itemId
	 *            the new item id
	 */
	protected void setItemId(ItemId itemId) {
		this.itemId = itemId;
	}

	/**
	 * Gets  the dial string.
	 * 
	 * @return the dial string
	 */
	protected String getDialString() {
		return this.dialString;
	}

	/**
	 * Sets the dial string.
	 * 
	 * @param dialString
	 *            the new dial string
	 */
	protected void setDialString(String dialString) {
		this.dialString = dialString;
	}

}
