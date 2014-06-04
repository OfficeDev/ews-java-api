/**************************************************************************
 * copyright file="SetRuleOperation.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SetRuleOperation.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents an operation to update an existing rule.
 */
public class SetRuleOperation extends RuleOperation {
	/**
	 * Inbox rule to be updated.
	 */
	private Rule rule;

	/**
	 * Initializes a new instance of the SetRuleOperation class.
	 */
	public SetRuleOperation() {
		super();
	}

	/**
	 * Initializes a new instance of the SetRuleOperation class.
	 * @param rule The rule
	 * The inbox rule to update.
	 */
	public SetRuleOperation(Rule rule) {
		super();
		this.rule = rule;
	}

	/**
	 * Gets the rule to be updated.
	 */
	public Rule getRule() {
		return this.rule;
	}

	/**
	 * Sets the rule to be updated.
	 */	
	public void setRule(Rule value) {
		if (this.canSetFieldValue(this.rule, value)) {
			this.rule = value;
			this.changed();
		}
	}

	/**
	 * Tries to read element from XML.
	 * @param reader The reader
	 * @return True if element was read.
	 */
	@Override
	protected  boolean tryReadElementFromXml(EwsServiceXmlReader reader) 
	throws Exception {
		if(reader.getLocalName().equals(XmlElementNames.Rule)) {
			this.rule = new Rule();
			this.rule.loadFromXml(reader, reader.getLocalName());
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Writes elements to XML.
	 * @param writer The writer.
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
	throws Exception {
		this.rule.writeToXml(writer, XmlElementNames.Rule);
	}

	/**
	 * Validates this instance.
	 * @throws Exception 
	 */
	@Override
	protected void internalValidate() throws Exception {
		EwsUtilities.validateParam(this.rule, "Rule");
	}

	/**
	 * Gets the Xml element name of the SetRuleOperation object.
	 */
	@Override
	protected String getXmlElementName() {    
		return XmlElementNames.SetRuleOperation;
	}
}