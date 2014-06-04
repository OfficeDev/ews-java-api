/**************************************************************************
 * copyright file="GetPasswordExpirationDateRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetPasswordExpirationDateRequest.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

public  final class GetPasswordExpirationDateRequest extends SimpleServiceRequestBase {

	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		// TODO Auto-generated method stub
		return ExchangeVersion.Exchange2010_SP1;
	}
	
	/**
	 * Initializes a new instance of the GetPasswordExpirationDateRequest class 
	 * @param service
	 * @throws Exception 
	 */
	protected GetPasswordExpirationDateRequest(ExchangeService service) throws Exception{
		super(service);
	}
	
	protected String getResponseXmlElementName(){
		return XmlElementNames.GetPasswordExpirationDateResponse;
	}
	
	/***
	 * Gets the name of the XML Element.
	 * returns XML element name
	 */

	
	protected String getXmlElementName(){
		return XmlElementNames.GetPasswordExpirationDateRequest;
	}
	
	 

	 
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws XMLStreamException, ServiceXmlSerializationException,
			ServiceLocalException, InstantiationException,
			IllegalAccessException, ServiceValidationException, Exception {
		 writer.writeElementValue(XmlNamespace.Messages,
				 XmlElementNames.MailboxSmtpAddress,
				 this.getMailboxSmtpAddress());
		 
	}
   /***
    * Parses the response
    * @param reader
    * @return GEtPasswordExpirationDateResponse
    */
	
	protected Object parseResponse(EwsServiceXmlReader reader)throws Exception{
		GetPasswordExpirationDateResponse response = new GetPasswordExpirationDateResponse();
		response.loadFromXml(reader,XmlElementNames.GetPasswordExpirationDateResponse);
		return response;
		
	}
	
	/***
	 * Gets the request version
	 * @return Earliest Exchange version in which this request is supported.
	 *//*
	protected ExchangeVersion getMinimumRequiredServerVersion(){
		return ExchangeVersion.Exchange2010_SP1;
	}*/
	
	/***
	 * Executes this request.
	 * @return Service response.
	 */
	
	protected GetPasswordExpirationDateResponse execute()throws Exception{
		GetPasswordExpirationDateResponse serviceResponse = (GetPasswordExpirationDateResponse)this.internalExecute();
		serviceResponse.throwIfNecessary();
		return serviceResponse;
	}
	
	/***
	 *  Gets  room list to retrice rooms from.
	 * @return
	 */
	protected String getMailboxSmtpAddress(){
		return this.mailboxSmtpAddress;
	}
	
	
	/***
	 * 
	 */
	
	protected void setMailboxSmtpAddress(String mailboxSmtpAddress){
		this. mailboxSmtpAddress =  mailboxSmtpAddress;
	}
	
	private String mailboxSmtpAddress;
	
}
