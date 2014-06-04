/**************************************************************************
 * copyright file="RuleErrorCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the RuleErrorCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;
/***
 * Represents a collection of rule validation errors.
 */
public final class RuleErrorCollection extends 
ComplexPropertyCollection<RuleError>{

	/***
	 * Initializes a new instance of the RuleErrorCollection class.	
	 */
	protected RuleErrorCollection(){
		super();
	}
	
	/***
	 * Creates an RuleError object from an XML element name.
	 * 
	 * @param xmlElementName
	 *            The XML element name from 
	 *            which to create the RuleError object.
	 * @return A RuleError object.
	 */
	@Override
	protected RuleError createComplexProperty(String xmlElementName){
		if (xmlElementName.equals(XmlElementNames.Error)){
			return new RuleError();
		}
		else {
			return null;
		}
	}
	
	/***
	 * Retrieves the XML element name corresponding 
	 * to the provided RuleError object.
	 * 
	 * @param ruleValidationError
	 *            The RuleError object from which 
	 *            to determine the XML element name.
	 * @return The XML element name corresponding 
	 * to the provided RuleError object.
	 */
	@Override
	protected String getCollectionItemXmlElementName(RuleError
			ruleValidationError){
		return XmlElementNames.Error;
	}
}
