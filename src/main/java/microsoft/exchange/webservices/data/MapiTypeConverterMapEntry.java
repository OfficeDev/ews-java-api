/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Represents an entry in the MapiTypeConverter map.
 */
class MapiTypeConverterMapEntry {

  /**
   * Map CLR types used for MAPI properties to matching default values.
   */
  private static LazyMember<Map<Class<?>, Object>> defaultValueMap = new LazyMember<Map<Class<?>, Object>>(
      new ILazyMember<Map<Class<?>, Object>>() {
        public Map<Class<?>, Object> createInstance() {

          Map<Class<?>, Object> map = new HashMap<Class<?>, Object>();

          map.put(Boolean.class, false);
          map.put(Byte[].class, null);
          map.put(Short.class, new Short((short) 0));
          map.put(Integer.class, 0);
          map.put(Long.class, new Long(0));
          map.put(Float.class, new Float(0.0));
          map.put(Double.class, new Double(0.0));
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          try {
            map.put(Date.class, formatter.parse("0001-01-01 12:00:00"));
          } catch (ParseException e) {
            e.printStackTrace();
          }
          map.put(UUID.class, UUID.fromString("00000000-0000-0000-0000-000000000000"));
          map.put(String.class, null);

          return map;

        }
      });
  /**
   * The is array.
   */
  boolean isArray;

  /**
   * The type.
   */
  Class<?> type;

  /**
   * The convert to string.
   */
  IFunction<Object, String> convertToString;

  /**
   * The parse.
   */
  IFunction<String, Object> parse;

  /**
   * Initializes a new instance of the MapiTypeConverterMapEntry class.
   *
   * @param type The type. y default, converting a type to string is done by
   *             calling value.ToString. Instances can override this behavior.
   *             <p/>
   *             By default, converting a string to the appropriate value type
   *             is done by calling Convert.ChangeType Instances may override
   *             this behavior.
   */
  protected MapiTypeConverterMapEntry(Class<?> type) {
    EwsUtilities.EwsAssert(
        defaultValueMap.getMember().containsKey(type),
        "MapiTypeConverterMapEntry ctor",
        String.format("No default value entry for type {0}", type.getName()));

    this.type = type;
    this.convertToString = new IFunction<Object, String>() {
      public String func(Object o) {
        return String.valueOf(o);
      }
    };

    this.parse = new IFunction<String, Object>() {
      public Object func(String o) {
        return o;
      }
    };
  }

  /**
   * Change value to a value of compatible type.
   * <p/>
   * The type of a simple value should match exactly or be convertible to the
   * appropriate type. An array value has to be a single dimension (rank),
   * contain at least one value and contain elements that exactly match the
   * expected type. (We could relax this last requirement so that, for
   * example, you could pass an array of Int32 that could be converted to an
   * array of Double but that seems like overkill).
   *
   * @param value The value.
   * @return New value.
   * @throws Exception the exception
   */
  protected Object changeType(Object value) throws Exception {
    if (this.getIsArray()) {
      this.validateValueAsArray(value);
      return value;
    } else if (value.getClass() == this.getType()) {
      return value;
    } else {
      try {
        if (this.getType().isInstance(Integer.valueOf(0))) {
          Object o = null;
          o = Integer.parseInt(value + "");
          return o;
        } else if (this.getType().isInstance(new Date())) {
          DateFormat df = new SimpleDateFormat(
              "yyyy-MM-dd'T'HH:mm:ss'Z'");
          return df.parse(value + "");
        } else if (this.getType().isInstance(Boolean.valueOf(false))) {
          Object o = null;
          o = Boolean.parseBoolean(value + "");
          return o;
        } else if (this.getType().isInstance(String.class)) {
          return value;
        }
        return null;
      } catch (ClassCastException ex) {
        throw new ArgumentException(String.format(
            Strings.ValueOfTypeCannotBeConverted, "%s", "%s"
            , this.getType()), ex);
      }
    }
  }

  /**
   * Converts a string to value consistent with type.
   * <p/>
   * For array types, this method is called for each array element.
   *
   * @param stringValue String to convert to a value.
   * @return value
   * @throws ServiceXmlDeserializationException                  the service xml deserialization exception
   * @throws microsoft.exchange.webservices.data.FormatException the format exception
   */
  protected Object convertToValue(String stringValue)
      throws ServiceXmlDeserializationException, FormatException {
    try {
      return this.getParse().func(stringValue);
    } catch (ClassCastException ex) {
      throw new ServiceXmlDeserializationException(String
          .format(Strings.ValueCannotBeConverted, stringValue, this
              .getType()), ex);
    } catch (NumberFormatException ex) {
      throw new ServiceXmlDeserializationException(String
          .format(Strings.ValueCannotBeConverted, stringValue, this.getType()), ex);
    }

  }

  /**
   * Converts a string to value consistent with type (or uses the default value if the string is null or empty).
   *
   * @param stringValue to convert to a value.
   * @return Value.
   * @throws microsoft.exchange.webservices.data.FormatException
   * @throws ServiceXmlDeserializationException
   */
  protected Object ConvertToValueOrDefault(String stringValue)
      throws ServiceXmlDeserializationException, FormatException {
    return (stringValue == null || stringValue.isEmpty()) ?
        this.getDefaultValue() :
        this.convertToValue(stringValue);
  }

  /**
   * Validates array value.
   *
   * @param value the value
   * @throws microsoft.exchange.webservices.data.ArgumentException     the argument exception
   * @throws microsoft.exchange.webservices.data.ArgumentNullException the argument exception
   */
  private void validateValueAsArray(Object value) throws ArgumentException, ArgumentNullException {
    if (value == null) {
      throw new ArgumentNullException("value");
    }

    if (value instanceof ArrayList) {
      ArrayList<?> arrayList = (ArrayList<?>) value;
      if (arrayList.isEmpty()) {
        throw new ArgumentException(Strings.ArrayMustHaveAtLeastOneElement);
      }

      if (arrayList.get(0).getClass() != this.getType()) {
        throw new ArgumentException(String.format(Strings.IncompatibleTypeForArray, value.getClass(),
            this.getType()));
      }
    }
  }

  /**
   * Gets the dim. If `array' is an array object returns its dimensions;
   * otherwise returns 0
   *
   * @param array the array
   * @return the dim
   */
  public static int getDim(Object array) {
    int dim = 0;
    Class<?> cls = array.getClass();
    while (cls.isArray()) {
      dim++;
      cls = cls.getComponentType();
    }
    return dim;
  }

  /**
   * Gets  the type.
   *
   * @return the type
   */

  protected Class<?> getType() {
    return this.type;
  }

  /**
   * Sets the type.
   *
   * @param cls the new type
   */
  protected void setType(Class<?> cls) {
    type = cls;
  }

  /**
   * Gets  a value indicating whether this instance is array.
   *
   * @return the checks if is array
   */
  protected boolean getIsArray() {
    return isArray;

  }

  /**
   * Sets the checks if is array.
   *
   * @param value the new checks if is array
   */
  protected void setIsArray(boolean value) {
    isArray = value;
  }

  /**
   * Gets the string to object converter. For array types, this method is
   * called for each array element.
   *
   * @return the convert to string
   */
  protected IFunction<Object, String> getConvertToString() {
    return convertToString;
  }

  /**
   * Sets the string to object converter.
   *
   * @param value the value
   */
  protected void setConvertToString(IFunction<Object, String> value) {
    convertToString = value;
  }

  /**
   * Gets the string parser. For array types, this method is called for each
   * array element.
   *
   * @return the parses the
   */
  protected IFunction<String, Object> getParse() {
    return parse;
  }

  /**
   * Sets the string parser.
   *
   * @param value the value
   */
  protected void setParse(IFunction<String, Object> value) {
    parse = value;
  }

  /**
   * Gets the default value for the type.
   *
   * @return Type
   */
  protected Object getDefaultValue() {
    return defaultValueMap.getMember().get(this.type);
  }
}
