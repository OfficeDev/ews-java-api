/**************************************************************************
 * copyright file="Base64.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Base64.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a base 64 class.
 */
 class Base64 {
	
	/** The data. */
	static byte[] dataArry;
	
	/** The char set. */
	static String strSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZab" +
			"cdefghijklmnopqrstuvwxyz0123456789+/";

	static {
		dataArry = new byte[64];
		for (int i = 0; i < 64; i++) {
			byte s = (byte)strSet.charAt(i);
			dataArry[i] = s;
		}
	}

	/**
	 * Encodes String.
	 * 
	 * @param data
	 *            The String to be encoded
	 * @return String
	 * 			  encoded value of String
	 */
	public static String encode(String data) {
		return encode(data.getBytes());
	}

	/**
	 * Encodes  byte array.
	 *
	 * @param byteArry 
	 * 			  The value
	 * @return String 
	 * 				encoded result of byte array
	 */

	public static String encode(byte[] byteArry) {
		return encode(byteArry, 0, byteArry.length);
	}

	/**
	 * Encodes  byte array.
	 *
	 * @param byteArry The byte array to encode
	 * @param startIndex The starting index of array
	 * @param length Length of byte array
	 * @return String, encoded result of byte array
	 */

	public static String encode(byte[] byteArry, int startIndex, int length) {
		byte[] resArry = new byte[(length + 2) / 3 * 4 + length / 72];
		int curState = 0; // Current state
		int prev = 0; // previous byte
		int presLength = 0; // Current length of bytes decoded
		int max = length + startIndex;		
		int x = 0;
		int resIndex = 0;

		for (int i = startIndex; i < max; i++) {
			x = byteArry[i];
			switch (++curState) {
			case 1:
				resArry[resIndex++] = dataArry[(x >> 2) & 0x3f];
				break;
			case 2:
				resArry[resIndex++] = dataArry[((prev << 4) & 0x30) |
						 ((x >> 4) & 0xf)];
				break;
			case 3:
				resArry[resIndex++] = dataArry[((prev << 2) & 0x3C) |
						 ((x >> 6) & 0x3)];
				resArry[resIndex++] = dataArry[x & 0x3F];
				curState = 0;
				break;
			}
			prev = x;
			if (++presLength >= 72) {
				resArry[resIndex++] = (byte)'\n';
				presLength = 0;
			}
		}

		switch (curState) {
		case 1:
			resArry[resIndex++] = dataArry[(prev << 4) & 0x30];
			resArry[resIndex++] = (byte)'=';
			resArry[resIndex++] = (byte)'=';
			break;
		case 2:
			resArry[resIndex++] = dataArry[(prev << 2) & 0x3c];
			resArry[resIndex++] = (byte)'=';
			break;
		}
		return new String(resArry);
	}

	/**
	 * Decodes String value.
	 *
	 * @param data Encoded value
	 * @return The byte array of decoded value
	 */

	public static byte[] decode(String data) {
		int last = 0; // end state
		if (data.endsWith("=")) {
			last++;
		}
		if (data.endsWith("==")) {
			last++;
		}
		int decodeLen = (data.length() + 3) / 4 * 3 - last;
		byte[] byteArry = new byte[decodeLen];
		int index = 0;
		try {
			for (int i = 0; i < data.length(); i++) {
				int valueAt = strSet.indexOf(data.charAt(i));
				if (valueAt == -1) {
					break;
				}
				switch (i % 4) {
				case 0:
					byteArry[index] = (byte)(valueAt << 2);
					break;
				case 1:
					byteArry[index++] |= (byte)((valueAt >> 4) & 0x3);
					byteArry[index] = (byte)(valueAt << 4);
					break;
				case 2:
					byteArry[index++] |= (byte)((valueAt >> 2) & 0xf);
					byteArry[index] = (byte)(valueAt << 6);
					break;
				case 3:
					byteArry[index++] |= (byte)(valueAt & 0x3f);
					break;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return byteArry;
	}
}
