/**************************************************************************
 * copyright file="Conflict.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Conflict.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents a conflict in a meeting time suggestion.
 * 
 */
public final class Conflict extends ComplexProperty {

	/** The conflict type. */
	private ConflictType conflictType;

	/** The number of members. */
	private int numberOfMembers;

	/** The number of members available. */
	private int numberOfMembersAvailable;

	/** The number of members with conflict. */
	private int numberOfMembersWithConflict;

	/** The number of members with no data. */
	private int numberOfMembersWithNoData;

	/** The free busy status. */
	private LegacyFreeBusyStatus freeBusyStatus;

	/**
	 * Initializes a new instance of the Conflict class.
	 * 
	 * @param conflictType
	 *            the conflict type
	 */
	protected Conflict(ConflictType conflictType) {
		super();
		this.conflictType = conflictType;
	}

	/**
	 * Tries to read element from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @return True if appropriate element was read.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (reader.getLocalName().equals(XmlElementNames.NumberOfMembers)) {
			this.numberOfMembers = reader.readElementValue(Integer.class);
			return true;
		} else if (reader.getLocalName().equals(
				XmlElementNames.NumberOfMembersAvailable)) {
			this.numberOfMembersAvailable = reader
					.readElementValue(Integer.class);
			return true;
		} else if (reader.getLocalName().equals(
				XmlElementNames.NumberOfMembersWithConflict)) {
			this.numberOfMembersWithConflict = reader
					.readElementValue(Integer.class);
			return true;
		} else if (reader.getLocalName().equals(
				XmlElementNames.NumberOfMembersWithNoData)) {
			this.numberOfMembersWithNoData = reader
					.readElementValue(Integer.class);
			return true;
		} else if (reader.getLocalName().equals(XmlElementNames.BusyType)) {
			this.freeBusyStatus = reader
					.readElementValue(LegacyFreeBusyStatus.class);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the type of the conflict.
	 * 
	 * @return the conflict type
	 */
	public ConflictType getConflictType() {
		return conflictType;
	}

	/**
	 * Gets the number of users, resources, and rooms in the conflicting group.
	 * The value of this property is only meaningful when ConflictType is equal
	 * to ConflictType.GroupConflict.
	 * 
	 * @return the number of members
	 */
	public int getNumberOfMembers() {
		return numberOfMembers;
	}

	/**
	 * Gets the number of members who are available (whose status is Free) in
	 * the conflicting group. The value of this property is only meaningful when
	 * ConflictType is equal to ConflictType.GroupConflict.
	 * 
	 * @return the number of members available
	 */
	public int getNumberOfMembersAvailable() {
		return numberOfMembersAvailable;
	}

	/**
	 * Gets the number of members who have a conflict (whose status is Busy, OOF
	 * or Tentative) in the conflicting group. The value of this property is
	 * only meaningful when ConflictType is equal to ConflictType.GroupConflict.
	 * 
	 * @return the number of members with conflict
	 */
	public int getNumberOfMembersWithConflict() {
		return numberOfMembersWithConflict;
	}

	/**
	 * Gets the number of members who do not have published free/busy data in
	 * the conflicting group. The value of this property is only meaningful when
	 * ConflictType is equal to ConflictType.GroupConflict.
	 * 
	 * @return the number of members with no data
	 */
	public int getNumberOfMembersWithNoData() {
		return numberOfMembersWithNoData;
	}

	/**
	 * Gets the free/busy status of the conflicting attendee. The value of this
	 * property is only meaningful when ConflictType is equal to
	 * ConflictType.IndividualAttendee.
	 * 
	 * @return the free busy status
	 */
	public LegacyFreeBusyStatus getFreeBusyStatus() {
		return freeBusyStatus;
	}

}
