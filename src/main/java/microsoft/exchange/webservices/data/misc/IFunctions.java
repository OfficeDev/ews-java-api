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

package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.EwsUtilities;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.UUID;

/**
 * Class with re-usable function implementations.
 */

public final class IFunctions {

  private IFunctions() {
    throw new UnsupportedOperationException();
  }

  public static class ToString implements IFunction<Object, String> {
    public static final ToString INSTANCE = new ToString();

    public String func(final Object o) {
      return String.valueOf(o);
    }
  }

  public static class ToBoolean implements IFunction<String, Object> {
    public static final ToBoolean INSTANCE = new ToBoolean();

    public Boolean func(final String s) {
      return Boolean.parseBoolean(s);
    }
  }

  public static class StringToObject implements IFunction<String, Object> {
    public static final StringToObject INSTANCE = new StringToObject();

    public Object func(final String o) {
      return o;
    }
  }

  public static class ToUUID implements IFunction<String, Object> {
    public static final ToUUID INSTANCE = new ToUUID();

    public Object func(final String s) {
      return UUID.fromString(s);
    }
  }

  public static class Base64Decoder implements IFunction<String, Object> {
    public static final Base64Decoder INSTANCE = new Base64Decoder();

    public Object func(final String s) {
      return Base64.decodeBase64(s);
    }
  }

  public static class Base64Encoder implements IFunction<Object, String> {
    public static final Base64Encoder INSTANCE = new Base64Encoder();

    public String func(final Object o) {
      return Base64.encodeBase64String((byte[]) o);
    }
  }

  public static class ToLowerCase implements IFunction<Object, String> {
    public static final ToLowerCase INSTANCE = new ToLowerCase();

    public String func(final Object o) {
      return o == null ? null : o.toString().toLowerCase();
    }
  }

  public static class DateTimeToXSDateTime implements IFunction<Object, String> {
    public static final DateTimeToXSDateTime INSTANCE = new DateTimeToXSDateTime();

    public String func(final Object o) {
      return EwsUtilities.dateTimeToXSDateTime((Date) o);
    }
  }

}
