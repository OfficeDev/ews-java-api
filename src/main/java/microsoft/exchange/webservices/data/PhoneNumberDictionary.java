/**************************************************************************
 * copyright file="PhoneNumberDictionary.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PhoneNumberDictionary.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a dictionary of phone numbers.
 * 
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class PhoneNumberDictionary extends
		DictionaryProperty<PhoneNumberKey, PhoneNumberEntry> {

	/***
	 * Gets the field URI.
	 * 
	 * @return Field URI.
	 */
	@Override
	protected String getFieldURI() {
		return "contacts:PhoneNumber";
	}

	/***
	 * Creates instance of dictionary entry.
	 * 
	 * @return New instance.
	 */
	@Override
	protected PhoneNumberEntry createEntryInstance() {
		return new PhoneNumberEntry();
	}

	/**
	 * * Gets  the phone number at the specified key.
	 * 
	 * @param key
	 *            the key
	 * @return The phone number at the specified key.
	 */
	public String getPhoneNumber(PhoneNumberKey key) {
		return this.getEntries().get(key).getPhoneNumber();
	}

	/**
	 * Sets the phone number.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setPhoneNumber(PhoneNumberKey key, String value) {
		if (value == null) {
			this.internalRemove(key);
		} else {
			PhoneNumberEntry entry;

			if (this.getEntries().containsKey(key)) {
				entry = this.getEntries().get(key);
				entry.setPhoneNumber(value);
				complexPropertyChanged( entry );
				this.changed();
			} else {
				entry = new PhoneNumberEntry(key, value);
				this.internalAdd(entry);
			}
		}
	}

	/**
	 * * Tries to get the phone number associated with the specified key.
	 * 
	 * @param key
	 *            the key
	 * @param outparam
	 *            the outparam
	 * @return true if the Dictionary contains a phone number associated with
	 *         the specified key; otherwise, false.
	 */
	public boolean tryGetValue(PhoneNumberKey key, OutParam<String> outparam) {
		PhoneNumberEntry entry = null;

		if (this.getEntries().containsKey(key)) {
			entry = this.getEntries().get(key);
			outparam.setParam(entry.getPhoneNumber());
			return true;
		} else {
			outparam = null;
			return false;
		}
	}
}
