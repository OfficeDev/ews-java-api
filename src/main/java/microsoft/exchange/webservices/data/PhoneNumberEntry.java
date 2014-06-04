/**************************************************************************
 * copyright file="PhoneNumberEntry.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PhoneNumberEntry.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an entry of a PhoneNumberDictionary.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class PhoneNumberEntry extends
		DictionaryEntryProperty<PhoneNumberKey> {

	/** The phone number. */
	private String phoneNumber;

	/**
	 * Initializes a new instance of the "PhoneNumberEntry" class.
	 */
	protected PhoneNumberEntry() {
		super(PhoneNumberKey.class);
	}

	/**
	 * Initializes a new instance of the <see cref="PhoneNumberEntry"/> class.
	 * 
	 * @param key
	 *            The key.
	 * @param phoneNumber
	 *            The phone number.
	 */
	protected PhoneNumberEntry(PhoneNumberKey key, String phoneNumber) {
		super(PhoneNumberKey.class, key);
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Reads the text value from XML.
	 * 
	 * @param reader
	 *            accepts EwsServiceXmlReader
	 * @throws Exception
	 *             throws Exception
	 */
	@Override
	protected void readTextValueFromXml(EwsServiceXmlReader reader)
			throws Exception {
		this.phoneNumber = reader.readValue();
	}

	/**
	 * Writes elements to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeValue(this.phoneNumber, XmlElementNames.PhoneNumber);
	}

	/**
	 * Gets the phone number of the entry.
	 * 
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets the phone number of the entry.
	 * 
	 * @param value
	 *            the new phone number
	 */
	public void setPhoneNumber(Object value) {
		//this.canSetFieldValue((String) this.phoneNumber, value);
		if( this.canSetFieldValue((String) this.phoneNumber, value) ) {
			this.phoneNumber = (String)value;
		}
	}
}
