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
 * Utility class to convert between MAPI Property type values and strings.
 */
class MapiTypeConverter {

  /**
   * The mapi type converter map.
   */
  private static LazyMember<MapiTypeConverterMap> mapiTypeConverterMap = new
      LazyMember<MapiTypeConverterMap>(new
                                           ILazyMember<MapiTypeConverterMap>() {

                                             @Override
                                             public MapiTypeConverterMap createInstance() {
                                               MapiTypeConverterMap map = new MapiTypeConverterMap();

                                               map.put(MapiPropertyType.ApplicationTime,
                                                   new MapiTypeConverterMapEntry(Double.class));

                                               MapiTypeConverterMapEntry mapitype =
                                                   new MapiTypeConverterMapEntry(
                                                       Double.class);
                                               mapitype.setIsArray(true);
                                               map.put(MapiPropertyType.ApplicationTimeArray, mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(Byte[].class);
                                               mapitype.setParse(new IFunction<String, Object>() {
                                                 public Object func(String s) {
                                                   return Base64EncoderStream.decode(s);
                                                 }
                                               });
                                               mapitype
                                                   .setConvertToString(new IFunction<Object,
                                                       String>() {
                                                     public String func(Object o) {
                                                       return Base64EncoderStream
                                                           .encode((byte[]) o);
                                                     }
                                                   });
                                               map.put(MapiPropertyType.Binary, mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(Byte[].class);
                                               mapitype.setParse(new IFunction<String, Object>() {
                                                 public Object func(String s) {
                                                   return Base64EncoderStream.decode(s);
                                                 }
                                               });
                                               mapitype
                                                   .setConvertToString(new IFunction<Object,
                                                       String>() {
                                                     public String func(Object o) {
                                                       return Base64EncoderStream
                                                           .encode((byte[]) o);
                                                     }
                                                   });
                                               mapitype.setIsArray(true);
                                               map.put(MapiPropertyType.BinaryArray, mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(Boolean.class);
                                               mapitype.setParse(new IFunction<String, Object>() {
                                                 public Object func(String s) {
                                                   return Boolean.parseBoolean(s);
                                                 }
                                               });
                                               mapitype
                                                   .setConvertToString(new IFunction<Object,
                                                       String>() {
                                                     public String func(Object o) {
                                                       return o.toString().toLowerCase();
                                                     }
                                                   });
                                               map.put(MapiPropertyType.Boolean, mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(UUID.class);
                                               mapitype.setParse(new IFunction<String, Object>() {
                                                 public Object func(String s) {
                                                   return UUID.fromString(s);
                                                 }
                                               });
                                               mapitype
                                                   .setConvertToString(new IFunction<Object,
                                                       String>() {
                                                     public String func(Object o) {
                                                       return o.toString();
                                                     }
                                                   });
                                               map.put(MapiPropertyType.CLSID, mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(UUID.class);
                                               mapitype.setParse(new IFunction<String, Object>() {
                                                 public Object func(String s) {
                                                   return UUID.fromString(s);
                                                 }
                                               });
                                               mapitype
                                                   .setConvertToString(new IFunction<Object,
                                                       String>() {
                                                     public String func(Object o) {
                                                       return o.toString();
                                                     }
                                                   });
                                               mapitype.setIsArray(true);
                                               map.put(MapiPropertyType.CLSIDArray, mapitype);

                                               map.put(MapiPropertyType.Currency,
                                                   new MapiTypeConverterMapEntry(Long.class));

                                               mapitype = new MapiTypeConverterMapEntry(Long.class);
                                               mapitype.setIsArray(true);
                                               map.put(MapiPropertyType.CurrencyArray, mapitype);

                                               map.put(MapiPropertyType.Double,
                                                   new MapiTypeConverterMapEntry(Double.class));

                                               mapitype = new MapiTypeConverterMapEntry(Double.class);
                                               mapitype.setIsArray(true);
                                               map.put(MapiPropertyType.DoubleArray, mapitype);

                                               map.put(MapiPropertyType.Error,
                                                   new MapiTypeConverterMapEntry(Integer.class));

                                               map.put(MapiPropertyType.Float,
                                                   new MapiTypeConverterMapEntry(Float.class));

                                               mapitype = new MapiTypeConverterMapEntry(Float.class);
                                               mapitype.setIsArray(true);
                                               map.put(MapiPropertyType.FloatArray, mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(Integer.class);
                                               mapitype.setParse(new IFunction<String, Object>() {
                                                 public Object func(String s) {
                                                   return MapiTypeConverter.parseMapiIntegerValue(s);
                                                 }
                                               });
                                               map.put(MapiPropertyType.Integer,
                                                   mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(Integer.class);
                                               mapitype.setIsArray(true);
                                               map.put(MapiPropertyType.IntegerArray, mapitype);

                                               map.put(MapiPropertyType.Long,
                                                   new MapiTypeConverterMapEntry(Long.class));

                                               mapitype = new MapiTypeConverterMapEntry(Long.class);
                                               mapitype.setIsArray(true);
                                               map.put(MapiPropertyType.LongArray, mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(String.class);
                                               mapitype.setParse(new IFunction<String, Object>() {
                                                 public Object func(String s) {
                                                   return s;
                                                 }
                                               });
                                               map.put(MapiPropertyType.Object, mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(String.class);
                                               mapitype.setParse(new IFunction<String, Object>() {
                                                 public Object func(String s) {
                                                   return s;
                                                 }
                                               });
                                               mapitype.setIsArray(true);
                                               map.put(MapiPropertyType.ObjectArray, mapitype);

                                               map.put(MapiPropertyType.Short,
                                                   new MapiTypeConverterMapEntry(Short.class));

                                               mapitype = new MapiTypeConverterMapEntry(Short.class);
                                               mapitype.setIsArray(true);
                                               map.put(MapiPropertyType.ShortArray, mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(String.class);
                                               mapitype.setParse(new IFunction<String, Object>() {
                                                 public Object func(String s) {
                                                   return s;
                                                 }
                                               });
                                               map.put(MapiPropertyType.String, mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(String.class);
                                               mapitype.setParse(new IFunction<String, Object>() {
                                                 public Object func(String s) {
                                                   return s;
                                                 }
                                               });
                                               mapitype.setIsArray(true);
                                               map.put(MapiPropertyType.StringArray, mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(Date.class);
                                               mapitype.setParse(new IFunction<String, Object>() {
                                                 public Object func(String s) {
                                                   String utcPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
                                                   Date dt = null;
                                                   String errMsg = String
                                                       .format(
                                                           "Date String %s not in " +
                                                               "valid UTC/local format",
                                                           s);
                                                   DateFormat utcFormatter = new SimpleDateFormat(
                                                       utcPattern);
                                                   if (s.endsWith("Z")) {
                                                     try {
                                                       dt = utcFormatter.parse(s);
                                                     } catch (ParseException e) {
                                                       s = s.substring(0, 10) + "T12:00:00Z";
                                                       try {
                                                         dt = utcFormatter.parse(s);
                                                       } catch (ParseException e1) {
                                                         e.printStackTrace();
                                                         throw new IllegalArgumentException(
                                                             errMsg, e);
                                                       }
                                                     }
                                                   } else if (s.endsWith("z")) {
                                                     // String in UTC format yyyy-MM-ddTHH:mm:ssZ
                                                     utcFormatter = new SimpleDateFormat(
                                                         "yyyy-MM-dd'T'HH:mm:ss'z'");
                                                     try {
                                                       dt = utcFormatter.parse(s);
                                                     } catch (ParseException e) {
                                                       throw new IllegalArgumentException(e);
                                                     }
                                                   } else {
                                                     utcFormatter = new SimpleDateFormat(
                                                         "yyyy-MM-dd'T'HH:mm:ss");
                                                     try {
                                                       dt = utcFormatter.parse(s);
                                                     } catch (ParseException e) {
                                                       throw new IllegalArgumentException(e);
                                                     }
                                                   }
                                                   return dt;
                                                 }
                                               });
                                               mapitype
                                                   .setConvertToString(new IFunction<Object,
                                                       String>() {
                                                     public String func(Object o) {
                                                       return EwsUtilities
                                                           .dateTimeToXSDateTime((Date) o);
                                                     }
                                                   });
                                               map.put(MapiPropertyType.SystemTime, mapitype);

                                               mapitype = new MapiTypeConverterMapEntry(Date.class);
                                               mapitype.setParse(new IFunction<String, Object>() {
                                                 public Object func(String s) {
                                                   String utcPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
                                                   Date dt = null;
                                                   String errMsg = String
                                                       .format(
                                                           "Date String %s not in " +
                                                               "valid UTC/local format",
                                                           s);
                                                   DateFormat utcFormatter = new SimpleDateFormat(
                                                       utcPattern);
                                                   if (s.endsWith("Z")) {
                                                     try {
                                                       dt = utcFormatter.parse(s);
                                                     } catch (ParseException e) {
                                                       s = s.substring(0, 10) + "T12:00:00Z";
                                                       try {
                                                         dt = utcFormatter.parse(s);
                                                       } catch (ParseException e1) {
                                                         e.printStackTrace();
                                                         throw new IllegalArgumentException(
                                                             errMsg, e);
                                                       }
                                                     }
                                                   } else if (s.endsWith("z")) {
                                                     // String in UTC format yyyy-MM-ddTHH:mm:ssZ
                                                     utcFormatter = new SimpleDateFormat(
                                                         "yyyy-MM-dd'T'HH:mm:ss'z'");
                                                     try {
                                                       dt = utcFormatter.parse(s);
                                                     } catch (ParseException e) {
                                                       throw new IllegalArgumentException(e);
                                                     }
                                                   } else {
                                                     utcFormatter = new SimpleDateFormat(
                                                         "yyyy-MM-dd'T'HH:mm:ss");
                                                     try {
                                                       dt = utcFormatter.parse(s);
                                                     } catch (ParseException e) {
                                                       throw new IllegalArgumentException(e);
                                                     }
                                                   }
                                                   return dt;
                                                 }
                                               });
                                               mapitype
                                                   .setConvertToString(new IFunction<Object,
                                                       String>() {
                                                     public String func(Object o) {
                                                       return EwsUtilities
                                                           .dateTimeToXSDateTime((Date) o);
                                                     }
                                                   });
                                               mapitype.setIsArray(true);
                                               map.put(MapiPropertyType.SystemTimeArray, mapitype);

                                               return map;
                                             }

                                           });

  /**
   * Converts the string list to array.
   *
   * @param mapiPropType Type of the MAPI property.
   * @param strings      the strings
   * @return Array of objects.
   * @throws Exception the exception
   */
  protected static List<Object> convertToValue(MapiPropertyType mapiPropType,
      Iterator<String> strings) throws Exception {
    EwsUtilities.validateParam(strings, "strings");

    MapiTypeConverterMapEntry typeConverter = getMapiTypeConverterMap()
        .get(mapiPropType);
    List<Object> array = new ArrayList<Object>();

    int index = 0;

    while (strings.hasNext()) {
      Object value = typeConverter.ConvertToValueOrDefault(strings.next());
      array.add(index, value);

    }
    return array;
  }

  /**
   * Converts a string to value consistent with MAPI type.
   *
   * @param mapiPropType the mapi prop type
   * @param stringValue  the string value
   * @return the object
   * @throws ServiceXmlDeserializationException                  the service xml deserialization exception
   * @throws microsoft.exchange.webservices.data.FormatException the format exception
   */
  protected static Object convertToValue(MapiPropertyType mapiPropType,
      String stringValue) throws ServiceXmlDeserializationException,
      FormatException {
    return getMapiTypeConverterMap().get(mapiPropType).convertToValue(
        stringValue);

  }

  /**
   * Converts a value to a string.
   *
   * @param mapiPropType the mapi prop type
   * @param value        the value
   * @return String value.
   */
  protected static String convertToString(MapiPropertyType mapiPropType,
      Object value) {
                /*
		 * if(! (value instanceof FuncInterface<?,?>)){ return null; }
		 */
    return (value == null) ? "" : getMapiTypeConverterMap().get(
        mapiPropType).getConvertToString().func(value);
  }

  /**
   * Change value to a value of compatible type.
   *
   * @param mapiType the mapi type
   * @param value    the value
   * @return the object
   * @throws Exception the exception
   */
  protected static Object changeType(MapiPropertyType mapiType, Object value)
      throws Exception {
    EwsUtilities.validateParam(value, "value");

    return getMapiTypeConverterMap().get(mapiType).changeType(value);
  }

  /**
   * Converts a MAPI Integer value.
   * Usually the value is an integer but there are cases where the value has been "schematized" to an
   * Enumeration value (e.g. NoData) which we have no choice but to fallback and represent as a string.
   *
   * @param s The string value.
   * @return Integer value or the original string if the value could not be parsed as such.
   */
  protected static Object parseMapiIntegerValue(String s) {
    int intValue;
    try {
      intValue = Integer.parseInt(s.trim());
      return new Integer(intValue);
    } catch (NumberFormatException e) {
      return s;
    }
  }

  /**
   * Determines whether MapiPropertyType is an array type.
   *
   * @param mapiType the mapi type
   * @return true, if is array type
   */
  protected static boolean isArrayType(MapiPropertyType mapiType) {
    return getMapiTypeConverterMap().get(mapiType).getIsArray();
  }

  /**
   * Gets the MAPI type converter map.
   *
   * @return the mapi type converter map
   */
  protected static Map<MapiPropertyType, MapiTypeConverterMapEntry>
  getMapiTypeConverterMap() {

    return mapiTypeConverterMap.getMember();
  }
}
