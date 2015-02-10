/**
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package microsoft.exchange.webservices.data;

import java.util.Vector;

/**
 * The Class Base64EncoderStream.
 */
class Base64EncoderStream {

  /**
   * Encode.
   *
   * @param uc the uc
   * @return the char
   */
  private static char encode(byte uc) {
    if (uc < 26) {
      return (char) ('A' + uc);
    }
    if (uc < 52) {
      return (char) ('a' + (uc - 26));
    }
    if (uc < 62) {
      return (char) ('0' + (uc - 52));
    }
    if (uc == 62) {
      return '+';
    }
    return '/';
  }

  /**
   * Checks if is base64.
   *
   * @param c the c
   * @return true, if is base64
   */
  private static boolean isBase64(char c) {
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') ||
        c == '+' || c == '/' || c == '=';
  }

  /**
   * Decode.
   *
   * @param c the c
   * @return the byte
   */
  private static byte decode(char c) {
    if (c >= 'A' && c <= 'Z') {
      return (byte) (c - 'A');
    }
    if (c >= 'a' && c <= 'z') {
      return (byte) (c - 'a' + 26);
    }
    if (c >= '0' && c <= '9') {
      return (byte) (c - '0' + 52);
    }
    if (c == '+') {
      return 62;
    }

    return 63;
  }

  /**
   * Encode.
   *
   * @param vby the vby
   * @return the string
   */
  public static String encode(byte[] vby) {
    if (vby.length == 0) {
      return "";
    }

    StringBuilder encodedString = new StringBuilder();

    for (int i = 0; i < vby.length; i += 3) {
      byte by1 = vby[i];
      byte by2 = 0;
      byte by3 = 0;

      if (i + 1 < vby.length) {
        by2 = vby[i + 1];
      }

      if (i + 2 < vby.length) {
        by3 = vby[i + 2];
      }
      byte by4 = (byte) (by1 >> 2);
      byte by5 = (byte) (((by1 & 0x3) << 4) | (by2 >> 4));
      byte by6 = (byte) (((by2 & 0xf) << 2) | (by3 >> 6));
      byte by7 = (byte) (by3 & 0x3f);

      encodedString.append(encode(by4));
      encodedString.append(encode(by5));

      if (i + 1 < vby.length) {
        encodedString.append(encode(by6));
      } else {
        encodedString.append("=");
      }

      if (i + 2 < vby.length) {
        encodedString.append(encode(by7));
      } else {
        encodedString.append("=");
      }

      if (i % (76 / 4 * 3) == 0) {
        encodedString.append("\r\n");
      }
    }

    return encodedString.toString();
  }

  /**
   * Decode.
   *
   * @param stringToDecode the string to decode.
   * @return size
   */
  public static byte[] decode(String stringToDecode) {
    StringBuilder str = new StringBuilder();
    for (int j = 0; j < stringToDecode.length(); j++) {
      if (isBase64(stringToDecode.charAt(j))) {
        str.append(stringToDecode.charAt(j));
      }
    }

    Vector<Byte> byteVector = new Vector<Byte>();
    if (str.length() == 0) {
      return new byte[byteVector.size()];
    }

    for (int i = 0; i < str.length(); i += 4) {
      char c1, c2 = 'A', c3 = 'A', c4 = 'A';
      c1 = str.charAt(i);
      if (i + 1 < str.length()) {
        c2 = str.charAt(i + 1);
      }

      if (i + 2 < str.length()) {
        c3 = str.charAt(i + 2);
      }

      if (i + 3 < str.length()) {
        c4 = str.charAt(i + 3);
      }

      byte by1, by2, by3, by4;
      by1 = decode(c1);
      by2 = decode(c2);
      by3 = decode(c3);
      by4 = decode(c4);
      byteVector.add((byte) ((byte) (by1 << 2) | (byte) (by2 >> 4)));

      if (c3 != '=') {
        byteVector.add((byte) (((by2 & 0xf) << 4) | (by3 >> 2)));
      }

      if (c4 != '=') {
        byteVector.add((byte) (((by3 & 0x3) << 6) | by4));
      }
    }

    byte[] byteArray = new byte[byteVector.size()];
    for (int i = 0; i < byteVector.size(); i++) {
      byteArray[i] = byteVector.get(i);
    }

    return byteArray;
  }
}
