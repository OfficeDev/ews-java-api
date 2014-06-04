/**************************************************************************
 * copyright file="RuleOperationErrorCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Implements a RuleOperationErrorCollection collection.
 **************************************************************************/
package microsoft.exchange.webservices.data;
/***
 * Represents a collection of rule operation errors.
 * 
 * 
 */
public final class RuleOperationErrorCollection extends 
ComplexPropertyCollection<RuleOperationError>{

	/***
	 * 
	 * Initializes a new instance of the
	 *  <see cref="RuleOperationErrorCollection"/> class.
	 * 
	 */
	protected RuleOperationErrorCollection() {
		super();      
	}

	/***
	 * Creates an RuleOperationError object from an XML element name.
	 * 
	 * @param xmlElementName
	 *            The XML element name from which 
	 *            to create the RuleOperationError object.
	 * @return A RuleOperationError object.
	 */
	@Override
	protected  RuleOperationError createComplexProperty(String xmlElementName) {
		if (xmlElementName.equals(XmlElementNames.RuleOperationError)) {
			return new RuleOperationError();
		}
		else {
			return null;
		}
	}
	
	/***
	 * Retrieves the XML element name corresponding
	 *  to the provided RuleOperationError object.
	 * 
	 * @param operationError
	 *           The RuleOperationError object
	 *            from which to determine the XML element name.
	 * @return The XML element name corresponding
	 *  to the provided RuleOperationError object.
	 */
	@Override
	protected String getCollectionItemXmlElementName(RuleOperationError
			operationError){
		return XmlElementNames.RuleOperationError;
	}
}
