/**************************************************************************
 * copyright file="EmailAddressDictionary.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EmailAddressDictionary.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a dictionary of e-mail addresses.
 * 
 * 
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class EmailAddressDictionary extends
		DictionaryProperty<EmailAddressKey, EmailAddressEntry> {

	/***
	 * Gets the field URI.
	 * 
	 * @return Field URI.
	 */
	@Override
	protected String getFieldURI() {
		return "contacts:EmailAddress";
	}

	/***
	 * Creates instance of dictionary entry.
	 * 
	 * @return New instance.
	 */
	@Override
	protected EmailAddressEntry createEntryInstance() {
		return new EmailAddressEntry();
	}

	/**
	 * * Gets the e-mail address at the specified key.
	 * 
	 * @param key
	 *            the key
	 * @return The e-mail address at the specified key.
	 */
	public EmailAddress getEmailAddress(EmailAddressKey key) {
		return this.getEntries().get(key).getEmailAddress();
	}

	/**
	 * Sets the email address.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setEmailAddress(EmailAddressKey key, EmailAddress value) {
		if (value == null) {
			this.internalRemove(key);
		} else {
			EmailAddressEntry entry;

			if (this.getEntries().containsKey(key)) {
				entry = this.getEntries().get(key);
				entry.setEmailAddress(value);
				complexPropertyChanged( entry );
				this.changed();
			} else {
				entry = new EmailAddressEntry(key, value);
				this.internalAdd(entry);
			}
		}
	}

	/**
	 * * Tries to get the e-mail address associated with the specified key.
	 * 
	 * @param key
	 *            the key
	 * @param outparam
	 *            the outparam
	 * @return true if the Dictionary contains an e-mail address associated with
	 *         the specified key; otherwise, false.
	 */
	public boolean tryGetValue(EmailAddressKey key,
			OutParam<EmailAddress> outparam) {
		EmailAddressEntry entry = null;

		if (this.getEntries().containsKey(key)) {
			entry = this.getEntries().get(key);
			outparam.setParam(entry.getEmailAddress());

			return true;
		} else {
			outparam = null;
			return false;
		}
	}
}
