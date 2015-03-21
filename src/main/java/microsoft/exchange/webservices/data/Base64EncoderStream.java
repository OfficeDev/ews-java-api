/*
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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import java.util.Vector;

/**
 * The Class Base64EncoderStream.
 */
class Base64EncoderStream {
  /**
   * Encode.
   *
   * @param vby the vby
   * @return the string
   */
  public static String encode(byte[] vby) {
    if (vby == null || vby.length == 0) {
      return "";
    }

    return StringUtils.newStringUtf8(Base64.encodeBase64Chunked(vby));
  }

  /**
   * Decode.
   *
   * @param stringToDecode the string to decode.
   * @return size
   */
  public static byte[] decode(String stringToDecode) {
    if (stringToDecode == null || stringToDecode.length() == 0) {
      return new byte[0];
    }

    return Base64.decodeBase64(stringToDecode);
  }
}
