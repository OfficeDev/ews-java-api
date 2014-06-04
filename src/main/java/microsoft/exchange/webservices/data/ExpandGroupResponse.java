/**************************************************************************
 * copyright file="ExpandGroupResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ExpandGroupResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the response to a group expansion operation.
 */
final class ExpandGroupResponse extends ServiceResponse {

	/**
	 * AD or store group members.
	 */
	private ExpandGroupResults members = new ExpandGroupResults();

	/**
	 * Initializes a new instance of the class.
	 */
	protected ExpandGroupResponse() {
		super();
	}

	/**
	 * Gets a list of the group's members.
	 * 
	 * @return the members
	 */
	public ExpandGroupResults getMembers() {
		return this.members;
	}

	/**
	 * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		super.readElementsFromXml(reader);
		this.getMembers().loadFromXml(reader);
	}

}
