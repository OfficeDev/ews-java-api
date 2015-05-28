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
import microsoft.exchange.webservices.data.core.ILazyMember;
import microsoft.exchange.webservices.data.core.LazyMember;
import microsoft.exchange.webservices.data.core.enumeration.property.MapiPropertyType;
import microsoft.exchange.webservices.data.core.exception.misc.FormatException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Utility class to convert between MAPI Property type values and strings.
 */
public class MapiTypeConverter {

  private static final Log LOG = LogFactory.getLog(MapiTypeConverter.class);

  private static final IFunction<String, Object> DATE_TIME_PARSER = new IFunction<String, Object>() {
    public Object func(final String s) {
      return parseDateTime(s);
    }
  };

  private static final IFunction<String, Object> MAPI_VALUE_PARSER = new IFunction<String, Object>() {
    public Object func(final String s) {
      return MapiTypeConverter.parseMapiIntegerValue(s);
    }
  };

  /**
   * The mapi type converter map.
   */
  private static final LazyMember<MapiTypeConverterMap> MAPI_TYPE_CONVERTER_MAP =
      new LazyMember<MapiTypeConverterMap>(new ILazyMember<MapiTypeConverterMap>() {
         @Override
         public MapiTypeConverterMap createInstance() {
           MapiTypeConverterMap map = new MapiTypeConverterMap();

           map.put(MapiPropertyType.ApplicationTime, new MapiTypeConverterMapEntry(Double.class));

           MapiTypeConverterMapEntry mapitype = new MapiTypeConverterMapEntry(Double.class);
           mapitype.setIsArray(true);
           map.put(MapiPropertyType.ApplicationTimeArray, mapitype);

           mapitype = new MapiTypeConverterMapEntry(Byte[].class);
           mapitype.setParse(IFunctions.Base64Decoder.INSTANCE);
           mapitype.setConvertToString(IFunctions.Base64Encoder.INSTANCE);
           map.put(MapiPropertyType.Binary, mapitype);

           mapitype = new MapiTypeConverterMapEntry(Byte[].class);
           mapitype.setParse(IFunctions.Base64Decoder.INSTANCE);
           mapitype.setConvertToString(IFunctions.Base64Encoder.INSTANCE);
           mapitype.setIsArray(true);
           map.put(MapiPropertyType.BinaryArray, mapitype);

           mapitype = new MapiTypeConverterMapEntry(Boolean.class);
           mapitype.setParse(IFunctions.ToBoolean.INSTANCE);
           mapitype.setConvertToString(IFunctions.ToLowerCase.INSTANCE);
           map.put(MapiPropertyType.Boolean, mapitype);

           mapitype = new MapiTypeConverterMapEntry(UUID.class);
           mapitype.setParse(IFunctions.ToUUID.INSTANCE);
           mapitype.setConvertToString(IFunctions.ToString.INSTANCE);
           map.put(MapiPropertyType.CLSID, mapitype);

           mapitype = new MapiTypeConverterMapEntry(UUID.class);
           mapitype.setParse(IFunctions.ToUUID.INSTANCE);
           mapitype.setConvertToString(IFunctions.ToString.INSTANCE);
           mapitype.setIsArray(true);
           map.put(MapiPropertyType.CLSIDArray, mapitype);

           map.put(MapiPropertyType.Currency, new MapiTypeConverterMapEntry(Long.class));

           mapitype = new MapiTypeConverterMapEntry(Long.class);
           mapitype.setIsArray(true);
           map.put(MapiPropertyType.CurrencyArray, mapitype);

           map.put(MapiPropertyType.Double, new MapiTypeConverterMapEntry(Double.class));

           mapitype = new MapiTypeConverterMapEntry(Double.class);
           mapitype.setIsArray(true);
           map.put(MapiPropertyType.DoubleArray, mapitype);

           map.put(MapiPropertyType.Error, new MapiTypeConverterMapEntry(Integer.class));
           map.put(MapiPropertyType.Float, new MapiTypeConverterMapEntry(Float.class));

           mapitype = new MapiTypeConverterMapEntry(Float.class);
           mapitype.setIsArray(true);
           map.put(MapiPropertyType.FloatArray, mapitype);

           mapitype = new MapiTypeConverterMapEntry(Integer.class);
           mapitype.setParse(MAPI_VALUE_PARSER);
           map.put(MapiPropertyType.Integer, mapitype);

           mapitype = new MapiTypeConverterMapEntry(Integer.class);
           mapitype.setIsArray(true);
           map.put(MapiPropertyType.IntegerArray, mapitype);

           map.put(MapiPropertyType.Long, new MapiTypeConverterMapEntry(Long.class));

           mapitype = new MapiTypeConverterMapEntry(Long.class);
           mapitype.setIsArray(true);
           map.put(MapiPropertyType.LongArray, mapitype);

           mapitype = new MapiTypeConverterMapEntry(String.class);
           mapitype.setParse(IFunctions.StringToObject.INSTANCE);
           map.put(MapiPropertyType.Object, mapitype);

           mapitype = new MapiTypeConverterMapEntry(String.class);
           mapitype.setParse(IFunctions.StringToObject.INSTANCE);
           mapitype.setIsArray(true);
           map.put(MapiPropertyType.ObjectArray, mapitype);

           map.put(MapiPropertyType.Short, new MapiTypeConverterMapEntry(Short.class));

           mapitype = new MapiTypeConverterMapEntry(Short.class);
           mapitype.setIsArray(true);
           map.put(MapiPropertyType.ShortArray, mapitype);

           mapitype = new MapiTypeConverterMapEntry(String.class);
           mapitype.setParse(IFunctions.StringToObject.INSTANCE);
           map.put(MapiPropertyType.String, mapitype);

           mapitype = new MapiTypeConverterMapEntry(String.class);
           mapitype.setParse(IFunctions.StringToObject.INSTANCE);
           mapitype.setIsArray(true);
           map.put(MapiPropertyType.StringArray, mapitype);

           mapitype = new MapiTypeConverterMapEntry(Date.class);
           mapitype.setParse(DATE_TIME_PARSER);
           mapitype.setConvertToString(IFunctions.DateTimeToXSDateTime.INSTANCE);
           map.put(MapiPropertyType.SystemTime, mapitype);

           mapitype = new MapiTypeConverterMapEntry(Date.class);
           mapitype.setParse(DATE_TIME_PARSER);
           mapitype.setConvertToString(IFunctions.DateTimeToXSDateTime.INSTANCE);
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
  public static List<Object> convertToValue(MapiPropertyType mapiPropType, Iterator<String> strings) throws Exception {
    EwsUtilities.validateParam(strings, "strings");

    MapiTypeConverterMapEntry typeConverter = getMapiTypeConverterMap()
        .get(mapiPropType);
    List<Object> array = new ArrayList<Object>();

    int index = 0;

    while (strings.hasNext()) {
      Object value = typeConverter.convertToValueOrDefault(strings.next());
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
   * @throws FormatException the format exception
   */
  public static Object convertToValue(MapiPropertyType mapiPropType, String stringValue) throws ServiceXmlDeserializationException, FormatException {
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
  public static String convertToString(MapiPropertyType mapiPropType, Object value) {
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
  public static Object changeType(MapiPropertyType mapiType, Object value)
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
      return Integer.valueOf(intValue);
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
  public static boolean isArrayType(MapiPropertyType mapiType) {
    return getMapiTypeConverterMap().get(mapiType).getIsArray();
  }

  /**
   * Gets the MAPI type converter map.
   *
   * @return the mapi type converter map
   */
  public static Map<MapiPropertyType, MapiTypeConverterMapEntry>
  getMapiTypeConverterMap() {

    return MAPI_TYPE_CONVERTER_MAP.getMember();
  }


  private static Object parseDateTime(String s) {
    String utcPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    String errMsg = String.format("Date String %s not in " + "valid UTC/local format", s);
    DateFormat utcFormatter = new SimpleDateFormat(utcPattern);
    Date dt;

    if (s.endsWith("Z")) {
      try {
        dt = utcFormatter.parse(s);
      } catch (ParseException e) {
        s = s.substring(0, 10) + "T12:00:00Z";
        try {
          dt = utcFormatter.parse(s);
        } catch (ParseException e1) {
          LOG.error(e);
          throw new IllegalArgumentException(
              errMsg, e);
        }
      }
    } else if (s.endsWith("z")) {
      // String in UTC format yyyy-MM-ddTHH:mm:ssZ
      utcFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'z'");
      try {
        dt = utcFormatter.parse(s);
      } catch (ParseException e) {
        throw new IllegalArgumentException(e);
      }
    } else {
      utcFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      try {
        dt = utcFormatter.parse(s);
      } catch (ParseException e) {
        throw new IllegalArgumentException(e);
      }
    }
    return dt;
  }

}
