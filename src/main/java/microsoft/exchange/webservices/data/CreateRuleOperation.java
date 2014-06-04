/**************************************************************************
 * copyright file="CreateRuleOperation.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateRuleOperation class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an operation to create a new rule.
 */
public final class CreateRuleOperation extends RuleOperation{


	/**
	 * Inbox rule to be created.
	 */
	private Rule rule;

	/**
	 * Initializes a new instance of the 
	 * <see cref="CreateRuleOperation"/> class.
	 */
	public CreateRuleOperation() {
		super();
	}

	/**
	 * Initializes a new instance of the 
	 * <see cref="CreateRuleOperation"/> class.
	 * @param rule The inbox rule to create.
	 */
	public CreateRuleOperation(Rule rule) {
		super();
		this.rule = rule;
	}

	/**
	 * Gets or sets the rule to be created.
	 */
	public Rule getRule() {

		return this.rule;
	}

	public void setRule(Rule value) {

		if (this.canSetFieldValue(this.rule, value)) {
			this.rule = value;
			this.changed();

		}
	}

	/**
	 * Writes elements to XML.
	 * @param writer The writer.
	 * @throws Exception 
	 */
	@Override
	protected  void writeElementsToXml(EwsServiceXmlWriter writer)
	throws Exception {
		this.getRule().writeToXml(writer, XmlElementNames.Rule);
	}

	/**
	 *  Validates this instance.
	 * @throws Exception 
	 */
	@Override
	protected void internalValidate() throws Exception {
		EwsUtilities.validateParam(this.rule, "Rule");
	}

	/**
	 * Gets the Xml element name of the CreateRuleOperation object.
	 */
	@Override
	protected String getXmlElementName() {

		return XmlElementNames.CreateRuleOperation;
	}

}