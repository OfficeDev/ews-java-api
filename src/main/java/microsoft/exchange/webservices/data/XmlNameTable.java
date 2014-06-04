/**************************************************************************
 * copyright file="XmlNameTable.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the XmlNameTable.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;


/**
 * Table of atomized String objects.
 */
public abstract class XmlNameTable {

	/**
	 * Initializes a new instance of the XmlNameTable class.
	 */
	protected XmlNameTable() {
	};

	/**
	 * When overridden in a derived class, atomizes the specified String and
	 * adds it to the XmlNameTable.
	 * 
	 * @param array
	 *            : The name to add.
	 * @return The new atomized String or the existing one if it already exists.
	 * @throws System.ArgumentNullException
	 *             : array is null.
	 */
	public abstract String Add(String array);

	/**
	 * Reads an XML Schema from the supplied stream.
	 * 
	 * @param array
	 *            The character array containing the name to add.
	 * @param offset
	 *            Zero-based index into the array specifying the first character
	 *            of the name.
	 * @param length
	 *            The number of characters in the name.
	 * @return The new atomized String or the existing one if it already exists.
	 *         If length is zero, String.Empty is returned
	 * @throws System.IndexOutOfRangeException
	 *             0 > offset -or- offset >= array.Length -or- length >
	 *             array.Length The above conditions do not cause an exception
	 *             to be thrown if length =0.
	 * @throws System.ArgumentOutOfRangeException
	 *             length < 0.
	 */
	public abstract String Add(char[] array, int offset, int length);

	/**
	 * When overridden in a derived class, gets the atomized String containing
	 * the same value as the specified String.
	 * 
	 * @param array
	 *            The name to look up.
	 * @return The atomized String or null if the String has not already been
	 *         atomized.
	 * @throws System.ArgumentNullException
	 *             : array is null.
	 */
	public abstract String Get(String array);

	/**
	 * When overridden in a derived class, gets the atomized String containing
	 * the same characters as the specified range of characters in the given
	 * array.
	 * 
	 * @param array
	 *            The character array containing the name to add.
	 * @param offset
	 *            Zero-based index into the array specifying the first character
	 *            of the name.
	 * @param length
	 *            The number of characters in the name.
	 * @return The atomized String or null if the String has not already been
	 *         atomized. If length is zero, String.Empty is returned
	 * @throws System.IndexOutOfRangeException
	 *             0 > offset -or- offset >= array.Length -or- length >
	 *             array.Length The above conditions do not cause an exception
	 *             to be thrown if length =0.
	 * @throws System.ArgumentOutOfRangeException
	 *             length < 0.
	 */
	public abstract String Get(char[] array, int offset, int length);

}
