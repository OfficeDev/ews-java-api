/**************************************************************************
 * copyright file="UpdateInboxRulesResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the UpdateInboxRulesResponse class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the response to a UpdateInboxRulesResponse operation.
 * @param <RuleOperationErrorCollection>
 */
final class UpdateInboxRulesResponse extends ServiceResponse{
	
	/***
	 * Rule operation error collection.
	 */	
	private RuleOperationErrorCollection errors;

	/***
	 * Initializes a new instance of the UpdateInboxRulesResponse class.
	 */     
	protected UpdateInboxRulesResponse() {
		super();
		this.errors = new RuleOperationErrorCollection();
	}

	/***
	 * Loads extra error details from XML
	 * @param reader The reader.
	 * @param xmlElementName The current element name of the extra error details.
	 * @return True if the expected extra details is loaded, 
	 * False if the element name does not match the expected element.
	 * @throws Exception 
	 */    
	@Override
	protected  boolean loadExtraErrorDetailsFromXml(EwsServiceXmlReader reader,
			String xmlElementName) throws Exception {
		if (xmlElementName.equals(XmlElementNames.MessageXml)) {
			return super.loadExtraErrorDetailsFromXml(reader, xmlElementName);
		}
		else if (xmlElementName.equals(XmlElementNames.RuleOperationErrors)) {
			this.getErrors().loadFromXml(reader,
					XmlNamespace.Messages, xmlElementName);
			return true;
		}
		else {
			return false;
		}
	}

	/***
	 * Gets the rule operation errors in the response.
	 */     
	protected RuleOperationErrorCollection getErrors() {
		return this.errors;
	}
}
