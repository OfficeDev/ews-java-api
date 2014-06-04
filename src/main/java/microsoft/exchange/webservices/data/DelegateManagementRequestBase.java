/**************************************************************************
 * copyright file="DelegateManagementRequestBase.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DelegateManagementRequestBase class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an abstract delegate management request. <typeparam
 * name="TResponse">The type of the response.</typeparam>
 * 
 * @param <TResponse>
 *            the generic type
 */
abstract class DelegateManagementRequestBase
		<TResponse extends DelegateManagementResponse>
		extends SimpleServiceRequestBase {

	/** The mailbox. */
	private Mailbox mailbox;

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected DelegateManagementRequestBase(ExchangeService service)
			throws Exception {
		super(service);
	}

	/**
	 * Validate request.
	 * 
	 * @throws microsoft.exchange.webservices.data.ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws ServiceLocalException, Exception {
		super.validate();
		EwsUtilities.validateParam(this.getMailbox(), "Mailbox");
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
		this.getMailbox().writeToXml(writer, XmlNamespace.Messages,
				XmlElementNames.Mailbox);
	}

	/**
	 * Creates the response.
	 * 
	 * @return Response object.
	 */
	protected abstract TResponse createResponse();

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
		DelegateManagementResponse response = this.createResponse();
		response.loadFromXml(reader, this.getResponseXmlElementName());
		return response;
	}

	/**
	 * Executes this request.
	 * 
	 * @return Response object.
	 * @throws Exception
	 *             the exception
	 */
	protected TResponse execute() throws Exception {
		TResponse serviceResponse = (TResponse) this.internalExecute();
		serviceResponse.throwIfNecessary();
		return serviceResponse;
	}

	/**
	 * Gets  the mailbox. <value>The mailbox.</value>
	 * 
	 * @return the mailbox
	 */
	public Mailbox getMailbox() {
		return this.mailbox;
	}

	/**
	 * Sets the mailbox.
	 * 
	 * @param mailbox
	 *            the new mailbox
	 */
	public void setMailbox(Mailbox mailbox) {
		this.mailbox = mailbox;
	}
}
