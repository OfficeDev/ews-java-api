/**************************************************************************
 * copyright file="RuleCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Implements a rule collection.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Represents a collection of rules.
 */
public final class RuleCollection extends 
ComplexProperty implements Iterable<Rule>{

	/**
	 * The OutlookRuleBlobExists flag.
	 */
	private boolean outlookRuleBlobExists;

	/**
	 * The rules in the rule collection.
	 */
	private ArrayList<Rule> rules;

	/**
	 * Initializes a new instance of the RuleCollection class.
	 */
	protected RuleCollection() {   
		super();
		this.rules = new ArrayList<Rule>();
	}

	/**
	 * Gets a value indicating whether an Outlook rule blob exists in the user's
	 * mailbox. To update rules with EWS when the Outlook rule blob exists, call
	 * SetInboxRules passing true as the
	 *  value of the removeOutlookBlob parameter.
	 */
	public boolean getOutlookRuleBlobExists() {
		return this.outlookRuleBlobExists;
	}

	protected void setOutlookRuleBlobExists(boolean value) {
		this.outlookRuleBlobExists = value;
	}

	/**
	 * Gets the number of rules in this collection.
	 */
	public int getCount() {
		return this.rules.size();
	}

	/**
	 * Gets the rule at the specified index in the collection.
	 * @param index The index of the rule to get.
	 * @return The rule at the specified index.
	 * @throws ArgumentOutOfRangeException 
	 */
	public Rule getRule(int index) throws ArgumentOutOfRangeException {
		if (index < 0 || index >= this.rules.size()) {
			throw new ArgumentOutOfRangeException("Index");
		}

		return this.rules.get(index);

	}


	/**
	 * Tries to read element from XML.
	 * @param reader The reader.
	 * @return True if element was read.
	 * @throws Exception 
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader) 
	throws Exception {
		if (reader.isStartElement(XmlNamespace.Types, XmlElementNames.Rule)) {
			Rule rule = new Rule();
			rule.loadFromXml(reader, XmlElementNames.Rule);
			this.rules.add(rule);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Get an enumerator for the collection
	 */
	@Override
	public Iterator<Rule> iterator() {
		return this.rules.iterator();
	}

}
