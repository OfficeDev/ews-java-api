/**************************************************************************
 * copyright file="AttendeeCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AttendeeCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * 
 * Represents a collection of attendees.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class AttendeeCollection extends
		ComplexPropertyCollection<Attendee> {

	/***
	 * Initializes a new instance of the AttendeeCollection class.
	 */
	protected AttendeeCollection() {
		super();
	}

	/**
	 * * Adds an attendee to the collection.
	 * 
	 * @param attendee
	 *            the attendee
	 */
	public void add(Attendee attendee) {
		this.internalAdd(attendee);
	}

	/**
	 * * Adds an attendee to the collection.
	 * 
	 * @param smtpAddress
	 *            the smtp address
	 * @return An Attendee instance initialized with the provided SMTP address.
	 * @throws Exception
	 *             the exception
	 */
	public Attendee add(String smtpAddress) throws Exception {
		Attendee result = new Attendee(smtpAddress);

		this.internalAdd(result);

		return result;
	}

	/**
	 * * Adds an attendee to the collection.
	 * 
	 * @param name
	 *            the name
	 * @param smtpAddress
	 *            the smtp address
	 * @return An Attendee instance initialized with the provided name and SMTP
	 *         address.
	 */
	public Attendee add(String name, String smtpAddress) {
		Attendee result = new Attendee(name, smtpAddress);

		this.internalAdd(result);

		return result;
	}

	/***
	 * Clears the collection.
	 */
	public void clear() {
		this.internalClear();
	}

	/**
	 * * Removes an attendee from the collection.
	 * 
	 * @param index
	 *            the index
	 */
	public void removeAt(int index) {
		if (index < 0 || index >= this.getCount()) {
			throw new IllegalArgumentException("parameter \'index\' : " + 
					Strings.IndexIsOutOfRange);
		}

		this.internalRemoveAt(index);
	}

	/**
	 * * Removes an attendee from the collection.
	 * 
	 * @param attendee
	 *            the attendee
	 * @return True if the attendee was successfully removed from the
	 *         collection, false otherwise.
	 * @throws Exception
	 *             the exception
	 */
	public boolean remove(Attendee attendee) throws Exception {
		EwsUtilities.validateParam(attendee, "attendee");

		return this.internalRemove(attendee);
	}

	/**
	 * * Creates an Attendee object from an XML element name.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @return An Attendee object.
	 */
	@Override
	protected Attendee createComplexProperty(String xmlElementName) {
		if (xmlElementName.equalsIgnoreCase(XmlElementNames.Attendee)) {
			return new Attendee();
		} else {
			return null;
		}
	}

	/**
	 * * Retrieves the XML element name corresponding to the provided Attendee
	 * object.
	 * 
	 * @param attendee
	 *            the attendee
	 * @return The XML element name corresponding to the provided Attendee
	 *         object.
	 */
	@Override
	protected String getCollectionItemXmlElementName(Attendee attendee) {
		return XmlElementNames.Attendee;
	}
}
