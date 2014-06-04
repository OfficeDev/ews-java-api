/**************************************************************************
 * copyright file="ExpandGroupResults.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ExpandGroupResults.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents the results of an ExpandGroup operation.
 */
public final class ExpandGroupResults implements Iterable<EmailAddress> {

	/**
	 * True, if all members are returned. EWS always returns true on ExpandDL,
	 * i.e. all members are returned.
	 */
	private boolean includesAllMembers;

	/**
	 * DL members.
	 */
	private Collection<EmailAddress> members = new ArrayList<EmailAddress>();

	/**
	 * Initializes a new instance of the class.
	 */
	protected ExpandGroupResults() {
	}

	/**
	 * Gets the number of members that were returned by the ExpandGroup
	 * operation. Count might be less than the total number of members in the
	 * group, in which case the value of the IncludesAllMembers is false.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return this.getMembers().size();
	}

	/**
	 * Gets a value indicating whether all the members of the group have been
	 * returned by ExpandGroup.
	 * 
	 * @return the includes all members
	 */
	public boolean getIncludesAllMembers() {
		return this.includesAllMembers;
	}

	/**
	 * Gets the members of the expanded group.
	 * 
	 * @return the members
	 */
	public Collection<EmailAddress> getMembers() {
		return this.members;
	}

	/**
	 * Gets the members of the expanded group.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsServiceXmlReader reader) throws Exception {
		reader.readStartElement(XmlNamespace.Messages,
				XmlElementNames.DLExpansion);
		if (!reader.isEmptyElement()) {
			int totalItemsInView = reader.readAttributeValue(Integer.class,
					XmlAttributeNames.TotalItemsInView);
			this.includesAllMembers = reader.readAttributeValue(Boolean.class,
					XmlAttributeNames.IncludesLastItemInRange);

			for (int i = 0; i < totalItemsInView; i++) {
				EmailAddress emailAddress = new EmailAddress();

				reader.readStartElement(XmlNamespace.Types,
						XmlElementNames.Mailbox);
				emailAddress.loadFromXml(reader, XmlElementNames.Mailbox);

				this.getMembers().add(emailAddress);
			}

			reader.readEndElement(XmlNamespace.Messages,
					XmlElementNames.DLExpansion);
		} else {
			reader.read();
		}
	}

	/**
     * Returns an iterator over a set of elements of type T.
     * 
     * @return an Iterator.
     */
	@Override
	public Iterator<EmailAddress> iterator() {

		return members.iterator();
	}
}
