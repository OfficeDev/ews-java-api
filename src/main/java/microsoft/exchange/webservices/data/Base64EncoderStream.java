/**************************************************************************
 * copyright file="Base64EncoderStream.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Base64EncoderStream.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Vector;

/**
 * The Class Base64EncoderStream.
 */
 class Base64EncoderStream {

	/**
	 * Encode.
	 * 
	 * @param uc
	 *            the uc
	 * @return the char
	 */
	private static char encode(byte uc) {
		if (uc < 26) {
			return (char)('A' + uc);
		}
		if (uc < 52) {
			return (char)('a' + (uc - 26));
		}
		if (uc < 62) {
			return (char)('0' + (uc - 52));
		}
		if (uc == 62) {
			return '+';
		}
		return '/';
	};

	/**
	 * Checks if is base64.
	 * 
	 * @param c
	 *            the c
	 * @return true, if is base64
	 */
	private static boolean isBase64(char c) {
		if (c >= 'A' && c <= 'Z') {
			return true;
		}
		if (c >= 'a' && c <= 'z') {
			return true;
		}
		if (c >= '0' && c <= '9') {
			return true;
		}
		if (c == '+') {
			return true;
		}
		;
		if (c == '/') {
			return true;
		}
		;
		if (c == '=') {
			return true;
		}
		;
		return false;
	};

	/**
	 * Decode.
	 * 
	 * @param c
	 *            the c
	 * @return the byte
	 */
	private static byte decode(char c) {
		if (c >= 'A' && c <= 'Z') {
			return (byte)(c - 'A');
		}
		if (c >= 'a' && c <= 'z') {
			return (byte)(c - 'a' + 26);
		}
		if (c >= '0' && c <= '9') {
			return (byte)(c - '0' + 52);
		}
		if (c == '+') {
			return 62;
		}
		;
		return 63;
	};

	/**
	 * Encode.
	 * 
	 * @param vby
	 *            the vby
	 * @return the string
	 */
	public static String encode(byte[] vby) {

		StringBuffer retval = new StringBuffer();
		String retValString = retval.toString();
		if (vby.length == 0) {
			return retValString;
		}
		;
		for (int i = 0; i < vby.length; i += 3) {

			byte by1 = 0;
			byte by2 = 0;
			byte by3 = 0;
			by1 = vby[i];
			if (i + 1 < vby.length) {
				by2 = vby[i + 1];
			}
			;
			if (i + 2 < vby.length) {
				by3 = vby[i + 2];
			}
			byte by4 = 0;
			byte by5 = 0;
			byte by6 = 0;
			byte by7 = 0;
			by4 = (byte)(by1 >> 2);
			by5 = (byte)(((by1 & 0x3) << 4) | (by2 >> 4));
			by6 = (byte)(((by2 & 0xf) << 2) | (by3 >> 6));
			by7 = (byte)(by3 & 0x3f);
			retval.append(encode(by4));
			retval.append(encode(by5));
			if (i + 1 < vby.length) {
				retval.append(encode(by6));
			} else {
				retval.append("=");
			}
			;
			if (i + 2 < vby.length) {
				retval.append(encode(by7));
			} else {
				retval.append("=");
			}
			;
			if (i % (76 / 4 * 3) == 0) {
				retval.append("\r\n");
			}
		}
		;
		retValString = retval.toString();
		return retValString;
	};

	/**
	 * Decode.
	 * 
	 * @param dstr
	 *            the _str
	 * @return size
	 */
	public static byte[] decode(String dstr) {
		StringBuffer str = new StringBuffer();
		for (int j = 0; j < dstr.length(); j++) {
			if (isBase64(dstr.charAt(j))) {
				str.append(dstr.charAt(j));
			}
		}
		Vector<Byte> retval = new Vector();
		if (str.length() == 0) {
			return new byte[retval.size()];
		}
		for (int i = 0; i < str.length(); i += 4) {
			char c1 = 'A', c2 = 'A', c3 = 'A', c4 = 'A';
			c1 = str.charAt(i);
			if (i + 1 < str.length()) {
				c2 = str.charAt(i + 1);
			}
			;
			if (i + 2 < str.length()) {
				c3 = str.charAt(i + 2);
			}
			;
			if (i + 3 < str.length()) {
				c4 = str.charAt(i + 3);
			}
			;
			byte by1 = 0, by2 = 0, by3 = 0, by4 = 0;
			by1 = decode(c1);
			by2 = decode(c2);
			by3 = decode(c3);
			by4 = decode(c4);
			retval.add(Byte
					.valueOf((byte)((byte)(by1 << 2) | (byte)(by2 >> 4))));
			if (c3 != '=') {
				retval.add(Byte
						.valueOf((byte)(((by2 & 0xf) << 4) | (by3 >> 2))));
			}
			if (c4 != '=') {
				retval.add(Byte.valueOf((byte)(((by3 & 0x3) << 6) | by4)));
			}
			;
		}
		;
		byte[] byteArry = new byte[retval.size()];
		for (int i = 0; i < retval.size(); i++) {
			byteArry[i] = retval.get(i);
		}
		return byteArry;
	};

}
