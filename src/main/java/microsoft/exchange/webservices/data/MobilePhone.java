/**************************************************************************
 * copyright file="MobilePhone.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Defines the MobilePhone class. class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a mobile phone.
 */
public final class MobilePhone implements ISelfValidate{

	/**
	 * Name of the mobile phone.
	 */
	private String name;

	/**
	 * Phone number of the mobile phone.
	 */
	private String phoneNumber;

	/**
	 * Initializes a new instance of the <see cref="MobilePhone"/> class.
	 */
	public MobilePhone() {
	}

	/**
	 * Initializes a new instance of the MobilePhone class.
	 * @param name The name associated with the mobile phone.
	 * @param phoneNumber The mobile phone number.
	 */
	public MobilePhone(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets or sets the name associated with this mobile phone.
	 */
	public String getName() {
		return this.name; }

	public void setName(String value) { 
		this.name = value; }


	/**
	 * Gets or sets the number of this mobile phone.
	 */
	public String getPhoneNumber() {
		return this.phoneNumber; }
	public void setPhoneNumber(String value) { 
		this.phoneNumber = value; }


	/**
	 * Validates this instance.//>!(contentType == null || contentType.isEmpty()
	 * @throws ServiceValidationException 
	 */	
	public void validate() throws ServiceValidationException {
		if(this.getPhoneNumber() == null || this.getPhoneNumber().isEmpty()) {
			throw new ServiceValidationException(
					"PhoneNumber cannot be empty.");
		}
	}
}
