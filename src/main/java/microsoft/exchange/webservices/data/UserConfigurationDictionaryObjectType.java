/**************************************************************************
 * copyright file="UserConfigurationDictionaryObjectType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the UserConfigurationDictionaryObjectType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Identifies the user configuration dictionary key and value types.
 */
public enum UserConfigurationDictionaryObjectType {

	// DateTime type.
	/** The Date time. */
	DateTime,

	// Boolean type.
	/** The Boolean. */
	Boolean,

	// Byte type.
	/** The Byte. */
	Byte,

	// String type.
	/** The String. */
	String,

	// 32-bit integer type.
	/** The Integer32. */
	Integer32,

	// 32-bit unsigned integer type.
	/** The Unsigned integer32. */
	UnsignedInteger32,

	// 64-bit integer type.
	/** The Integer64. */
	Integer64,

	// 64-bit unsigned integer type.
	/** The Unsigned integer64. */
	UnsignedInteger64,

	// String array type.
	/** The String array. */
	StringArray,

	// Byte array type
	/** The Byte array. */
	ByteArray,

}
