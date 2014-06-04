/**************************************************************************
 * copyright file="PhysicalAddressDictionary.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the PhysicalAddressDictionary.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents a dictionary of physical addresses.
 * 
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class PhysicalAddressDictionary extends
		DictionaryProperty<PhysicalAddressKey, PhysicalAddressEntry> {

	/***
	 * Creates instance of dictionary entry.
	 * 
	 * @return New instance.
	 */
	@Override
	protected PhysicalAddressEntry createEntryInstance() {
		return new PhysicalAddressEntry();
	}

	/**
	 * * Gets  the physical address at the specified key.
	 * 
	 * @param key
	 *            the key
	 * @return The physical address at the specified key.
	 */
	public PhysicalAddressEntry getPhysicalAddress(PhysicalAddressKey key) {
		return this.getEntries().get(key);
	}

	/**
	 * Sets the physical address.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setPhysicalAddress(PhysicalAddressKey key,
			PhysicalAddressEntry value) {
		if (value == null) {
			this.internalRemove(key);
		} else {
			value.setKey(key);
			this.internalAddOrReplace(value);
		}
	}

	/**
	 * * Tries to get the physical address associated with the specified key.
	 * 
	 * @param key
	 *            the key
	 * @param outparam
	 *            the outparam
	 * @return true if the Dictionary contains a physical address associated
	 *         with the specified key; otherwise, false.
	 */
	public boolean tryGetValue(PhysicalAddressKey key,
			OutParam<PhysicalAddressEntry> outparam) {
		if (this.getEntries().containsKey(key)) {
			outparam.setParam(this.getEntries().get(key));
		}
		return this.getEntries().containsKey(key);
	}

}
