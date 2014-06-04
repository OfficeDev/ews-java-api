/**************************************************************************
 * copyright file="GetInboxRulesResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetInboxRulesResponse class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the response to a GetInboxRules operation.
 */
final class GetInboxRulesResponse extends ServiceResponse{
	/**
	 * Rule collection.
	 */
	private RuleCollection ruleCollection;

	/**
	 * Initializes a new instance of the 
	 * <see cref="GetInboxRulesResponse"/> class.
	 */
	protected GetInboxRulesResponse() {
		super();
		this.ruleCollection = new RuleCollection();
	}

	/**
	 * Reads response elements from XML.
	 * @param reader The reader.
	 */
	@Override
	protected  void readElementsFromXml(EwsServiceXmlReader reader)
	throws Exception {
		reader.read();
		this.ruleCollection.setOutlookRuleBlobExists(reader.
				readElementValue(Boolean.class,
				XmlNamespace.Messages, 
				XmlElementNames.OutlookRuleBlobExists));
		reader.read();
		if (reader.isStartElement(XmlNamespace.NotSpecified, XmlElementNames.InboxRules)) {
			this.ruleCollection.loadFromXml(reader, 
					XmlNamespace.NotSpecified, 
					XmlElementNames.InboxRules);
		}
	}

	/**
	 * Gets the rule collection in the response.
	 */
	protected RuleCollection getRules() {
		return this.ruleCollection;
	}
}

