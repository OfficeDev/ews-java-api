/**************************************************************************
 * copyright file="DeleteRuleOperation.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DeleteRuleOperation class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents an operation to delete an existing rule.
 */
public final class DeleteRuleOperation extends RuleOperation {
	/**
	 * Id of the inbox rule to delete.
	 */
	private String ruleId;

	/**
	 * Initializes a new instance of the 
	 * <see cref="DeleteRuleOperation"/> class.
	 */
	public DeleteRuleOperation() {
		super();
	}

	/**
	 * Initializes a new instance of the 
	 * <see cref="DeleteRuleOperation"/> class.
	 * @param ruleId The Id of the inbox rule to delete.
	 */
	public DeleteRuleOperation(String ruleId) {
		super();
		this.ruleId = ruleId;
	}

	/**
	 * Gets or sets the Id of the rule to delete.
	 */
	public String getRuleId() {
		return this.ruleId;
	}
	public void setRuleId(String value) {
		if (this.canSetFieldValue(this.ruleId, value)) {
			this.ruleId = value;
			this.changed();
		}
	}

	/**
	 * Writes elements to XML.
	 * @param writer The writer.
	 */
	 @Override
	 protected  void writeElementsToXml(EwsServiceXmlWriter writer) 
	 throws ServiceXmlSerializationException, XMLStreamException {
		 writer.writeElementValue(XmlNamespace.Types, 
				 XmlElementNames.RuleId, this.getRuleId());
	 }

	 /**
	  *  Validates this instance.
	  */
	 @Override
	 protected void internalValidate() throws Exception {
		 EwsUtilities.validateParam(this.ruleId, "RuleId");
	 }

	 /**
	  * Gets the Xml element name of the DeleteRuleOperation object.
	  */
	 @Override
	 protected String getXmlElementName() {
		 return XmlElementNames.DeleteRuleOperation;

	 }
}