/**************************************************************************
 * copyright file="GetPasswordExpirationDateResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EwsServiceMultiResponseXmlReader.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Date;

public class GetPasswordExpirationDateResponse extends ServiceResponse {
	
	private Date passwordExpirationDate;
	
	/***
	 * Initializes a  new instance of the GetPasswordExpirationDateResponse class.
	 */
	protected GetPasswordExpirationDateResponse(){
		super();
	}
	
	/***
	 * Reads response elements from XML
	 * @param reader  The Reader
	 */
	protected void readElementsFromXml(EwsServiceXmlReader reader)throws Exception{
		super.readElementsFromXml(reader);
		this.passwordExpirationDate = (Date)reader.readElementValueAsDateTime(
				XmlNamespace.NotSpecified,
				XmlElementNames.PasswordExpirationDate);
		
	}
	
	/***
	 * passwrod Expiration Date
	 * @return
	 */
	public Date getPasswordExpirationDate(){
		return this.passwordExpirationDate;
	}
	
	

}
