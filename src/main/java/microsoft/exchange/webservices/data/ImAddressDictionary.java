/**************************************************************************
 * copyright file="ImAddressDictionary.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ImAddressDictionary.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a dictionary of Instant Messaging addresses.
 * 
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class ImAddressDictionary extends
		DictionaryProperty<ImAddressKey, ImAddressEntry> {

	/***
	 * Gets the field URI.
	 * 
	 * @return Field URI.
	 */
	@Override
	protected String getFieldURI() {
		return "contacts:ImAddress";
	}

	/***
	 * Creates instance of dictionary entry.
	 * 
	 * @return New instance.
	 */
	@Override
	protected ImAddressEntry createEntryInstance() {
		return new ImAddressEntry();
	}

	/**
	 * * Gets  the Instant Messaging address at the specified key.
	 * 
	 * @param key
	 *            the key
	 * @return The Instant Messaging address at the specified key.
	 */
	public String getImAddressKey(ImAddressKey key) {
		return this.getEntries().get(key).getImAddress();
	}

	/**
	 * Sets the im address key.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setImAddressKey(ImAddressKey key, String value) {
		if (value == null) {
			this.internalRemove(key);
		} else {
			ImAddressEntry entry;

			if (this.getEntries().containsKey(key)) {
				entry = this.getEntries().get(key);
				entry.setImAddress(value);
				this.changed();
			} else {
				entry = new ImAddressEntry(key, value);
				this.internalAdd(entry);
			}
		}
	}

	/**
	 * * Tries to get the IM address associated with the specified key.
	 * 
	 * @param key
	 *            the key
	 * @param outParam
	 *            the out param
	 * @return true if the Dictionary contains an IM address associated with the
	 *         specified key; otherwise, false.
	 */
	public boolean tryGetValue(ImAddressKey key, OutParam<String> outParam) {
		ImAddressEntry entry = null;

		if (this.getEntries().containsKey(key)) {
			entry = this.getEntries().get(key); 
			outParam.setParam(entry.getImAddress());

			return true;
		} else {
			outParam = null;
			return false;
		}
	}
}
