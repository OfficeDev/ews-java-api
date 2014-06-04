/**************************************************************************
 * copyright file="Rule.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Rule class.
 **************************************************************************/
package microsoft.exchange.webservices.data;


/***
 * Represents a rule that automatically handles incoming messages.
 * A rule consists of a set of conditions 
 * and exceptions that determine whether or
 * not a set of actions should be executed on incoming messages.
 */
public final class Rule extends ComplexProperty{

	/**
	 * The rule ID.
	 */
	private String ruleId;

	/**
	 * The rule display name.
	 */
	private String displayName;

	/**
	 * The rule priority.
	 */
	private int priority;

	/**
	 * The rule status of enabled or not.
	 */
	private boolean isEnabled;

	/**
	 * The rule status of is supported or not.
	 */
	private boolean isNotSupported;

	/**
	 * The rule status of in error or not.
	 */
	private boolean isInError;

	/**
	 * The rule conditions. 
	 */
	private RulePredicates conditions;

	/**
	 * The rule actions. 
	 */
	private RuleActions actions;

	/**
	 * The rule exceptions. 
	 */
	private RulePredicates exceptions;

	/**
	 * Initializes a new instance of the Rule class.
	 */
	public Rule() {
		super();

		/**
		 * New rule has priority as 0 by default
		 */
		this.priority = 1;

		/**
		 * New rule is enabled by default
		 */
		this.isEnabled = true;
		this.conditions = new RulePredicates();
		this.actions = new RuleActions();
		this.exceptions = new RulePredicates();
	}


	/**
	 * Gets or sets the Id of this rule.
	 */
	public String getId() {

		return this.ruleId;
	}
	public void setId(String value) {
		if (this.canSetFieldValue(this.ruleId, value)) {
			this.ruleId = value;
			this.changed();
		}
	}

	/**
	 * Gets or sets the name of this rule as it should be displayed to the user.
	 */
	public String getDisplayName() {
		return this.displayName;
	}
	public void setDisplayName(String value) {
		if (this.canSetFieldValue(this.displayName, value)) {
			this.displayName = value;
			this.changed();
		}
	}


	/**
	 * Gets or sets the priority of this rule,
	 *  which determines its execution order.
	 */
	public int getPriority() {
		return this.priority;
	}
	public void setPriority(int value) {
		if (this.canSetFieldValue(this.priority, value)) {
			this.priority = value;
			this.changed();
		}}


	/**
	 * Gets or sets a value indicating whether this rule is enabled.
	 */
	public boolean getIsEnabled() {
		return this.isEnabled;
	}
	public void setIsEnabled(boolean value){
		if (this.canSetFieldValue(this.isEnabled, value)) {
			this.isEnabled = value;
			this.changed();
		}
	}

	/**
	 * Gets a value indicating whether this rule can be modified via EWS.
	 * If IsNotSupported is true, the rule cannot be modified via EWS.
	 */
	public boolean getIsNotSupported() {
		return this.isNotSupported;

	}

	/**
	 * Gets or sets a value indicating whether
	 *  this rule has errors. A rule that is in error 
	 * cannot be processed unless it is updated and the error is corrected.
	 */
	public boolean getIsInError() {
		return this.isInError;
	}

	public void setIsInError(boolean value) {
		if (this.canSetFieldValue(this.isInError, value)) {
			this.isInError = value;
			this.changed();
		}
	}

	/**
	 * Gets the conditions that determine whether or not this rule should be
	 * executed against incoming messages.
	 */
	public RulePredicates getConditions() {
		return this.conditions;
	}

	/**
	 * Gets the actions that should be executed against incoming messages if the
	 * conditions evaluate as true.
	 */
	public RuleActions getActions() {
		return this.actions;

	}

	/**
	 * Gets the exceptions that determine 
	 * if this rule should be skipped even if 
	 * its conditions evaluate to true.
	 */
	public RulePredicates getExceptions() {
		return this.exceptions;
	}

	/**
	 * Tries to read element from XML.
	 * @param reader The reader.
	 * @return True if element was read.
	 * @throws Exception 
	 */
	@Override
	protected  boolean tryReadElementFromXml(EwsServiceXmlReader
			reader) throws Exception {
	
		if (reader.getLocalName().equals(XmlElementNames.DisplayName)) {
			this.displayName = reader.readElementValue();
			return true;
		}
		else if (reader.getLocalName().equals(XmlElementNames.RuleId)) {
			this.ruleId = reader.readElementValue();
			return true;
		}
		else if (reader.getLocalName().equals(XmlElementNames.Priority)) {
			this.priority = reader.readElementValue(Integer.class);
			return true;
		}
		else if (reader.getLocalName().equals(XmlElementNames.IsEnabled)) {
			this.isEnabled = reader.readElementValue(Boolean.class);
			return true;
		}
		else if (reader.getLocalName().equals(XmlElementNames.IsNotSupported)) {
			this.isNotSupported = reader.readElementValue(Boolean.class);
			return true;
		}
		else if (reader.getLocalName().equals(XmlElementNames.IsInError)) {
			this.isInError = reader.readElementValue(Boolean.class);
			return true;
		}
		else if (reader.getLocalName().equals(XmlElementNames.Conditions)) {
			this.conditions.loadFromXml(reader, reader.getLocalName());
			return true;
		}
		else if (reader.getLocalName().equals(XmlElementNames.Actions)) {
			this.actions.loadFromXml(reader, reader.getLocalName());
			return true;
		}
		else if (reader.getLocalName().equals(XmlElementNames.Exceptions)) {
			this.exceptions.loadFromXml(reader, reader.getLocalName());
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Writes elements to XML.
	 * @param writer The writer.
	 * @throws Exception 
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
	throws Exception {
		if(!(getId()==null || getId().isEmpty())) {
			writer.writeElementValue(
					XmlNamespace.Types, 
					XmlElementNames.RuleId, 
					this.getId());
		}

		writer.writeElementValue(
				XmlNamespace.Types, 
				XmlElementNames.DisplayName, 
				this.getDisplayName());
		writer.writeElementValue(
				XmlNamespace.Types, 
				XmlElementNames.Priority, 
				this.getPriority());
		writer.writeElementValue(
				XmlNamespace.Types, 
				XmlElementNames.IsEnabled, 
				this.getIsEnabled());
		writer.writeElementValue(
				XmlNamespace.Types,
				XmlElementNames.IsInError,
				this.getIsInError());
		this.getConditions().writeToXml(writer, XmlElementNames.Conditions);
		this.getExceptions().writeToXml(writer, XmlElementNames.Exceptions);
		this.getActions().writeToXml(writer, XmlElementNames.Actions);
	}

	
	/**
	 * Validates this instance.
	 */
	@Override
	protected void internalValidate() throws Exception {
		super.internalValidate();
		EwsUtilities.validateParam(this.displayName, "DisplayName");
		EwsUtilities.validateParam(this.conditions, "Conditions");
		EwsUtilities.validateParam(this.exceptions, "Exceptions");
		EwsUtilities.validateParam(this.actions, "Actions");
	}
}

