/**************************************************************************
 * copyright file="GetEventsResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetEventsResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the response to a subscription event retrieval operation.
 * 
 */
final class GetEventsResponse extends ServiceResponse {

	/** The results. */
	private GetEventsResults results = new GetEventsResults();

	/**
	 * Initializes a new instance.
	 */
	protected GetEventsResponse() {
		super();
	}

	/**
	 * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		super.readElementsFromXml(reader);
		this.results.loadFromXml(reader);
	}

	/**
	 * gets the results.
	 * 
	 * @return the results.
	 * 
	 */
	protected GetEventsResults getResults() {
		return results;
	}

}
