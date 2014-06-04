/**************************************************************************
 * copyright file="ResolveNamesResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ResolveNamesResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the response to a name resolution operation.
 */
final class ResolveNamesResponse extends ServiceResponse {

	/** The resolutions. */
	private NameResolutionCollection resolutions;

	/**
	 * * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 */
	protected ResolveNamesResponse(ExchangeService service) {
		super();
		EwsUtilities.EwsAssert(service != null, "ResolveNamesResponse.ctor",
				"service is null");

		this.resolutions = new NameResolutionCollection(service);
	}

	/**
	 * * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		super.readElementsFromXml(reader);
		this.resolutions.loadFromXml(reader);
	}

	/**
	 * * Override base implementation so that API does not throw when name
	 * resolution fails to find a match. EWS returns an error in this case but
	 * the API will just return an empty NameResolutionCollection.
	 * 
	 * @throws ServiceResponseException
	 *             the service response exception
	 */
	@Override
	protected void internalThrowIfNecessary() throws ServiceResponseException {
		if (this.getErrorCode() != ServiceError.ErrorNameResolutionNoResults) {
			super.internalThrowIfNecessary();
		}
	}

	/**
	 * * Gets a list of name resolution suggestions.
	 * 
	 * @return the resolutions
	 */
	public NameResolutionCollection getResolutions() {
		return this.resolutions;
	}
}
