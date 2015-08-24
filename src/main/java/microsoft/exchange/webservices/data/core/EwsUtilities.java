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

package microsoft.exchange.webservices.data.core;

import microsoft.exchange.webservices.data.ISelfValidate;
import microsoft.exchange.webservices.data.attribute.EwsEnum;
import microsoft.exchange.webservices.data.attribute.RequiredServerVersion;
import microsoft.exchange.webservices.data.core.request.HttpWebRequest;
import microsoft.exchange.webservices.data.core.service.ICreateServiceObjectWithAttachmentParam;
import microsoft.exchange.webservices.data.core.service.ICreateServiceObjectWithServiceParam;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.service.ServiceObjectInfo;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.notification.EventType;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.FileAsMapping;
import microsoft.exchange.webservices.data.core.enumeration.search.ItemTraversal;
import microsoft.exchange.webservices.data.core.enumeration.property.MailboxType;
import microsoft.exchange.webservices.data.core.enumeration.service.MeetingRequestsDeliveryScope;
import microsoft.exchange.webservices.data.core.enumeration.property.RuleProperty;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentNullException;
import microsoft.exchange.webservices.data.core.exception.http.EWSHttpException;
import microsoft.exchange.webservices.data.core.exception.misc.FormatException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceVersionException;
import microsoft.exchange.webservices.data.misc.TimeSpan;
import microsoft.exchange.webservices.data.property.complex.ItemAttachment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EWS utilities.
 */
public final class EwsUtilities {

  private static final Log LOG = LogFactory.getLog(EwsUtilities.class);

  /**
   * The Constant XSFalse.
   */
  public static final String XSFalse = "false";

  /**
   * The Constant XSTrue.
   */
  public static final String XSTrue = "true";

  /**
   * The Constant EwsTypesNamespacePrefix.
   */
  public static final String EwsTypesNamespacePrefix = "t";

  /**
   * The Constant EwsMessagesNamespacePrefix.
   */
  public static final String EwsMessagesNamespacePrefix = "m";

  /**
   * The Constant EwsErrorsNamespacePrefix.
   */
  public static final String EwsErrorsNamespacePrefix = "e";

  /**
   * The Constant EwsSoapNamespacePrefix.
   */
  public static final String EwsSoapNamespacePrefix = "soap";

  /**
   * The Constant EwsXmlSchemaInstanceNamespacePrefix.
   */
  public static final String EwsXmlSchemaInstanceNamespacePrefix = "xsi";

  /**
   * The Constant PassportSoapFaultNamespacePrefix.
   */
  public static final String PassportSoapFaultNamespacePrefix = "psf";

  /**
   * The Constant WSTrustFebruary2005NamespacePrefix.
   */
  public static final String WSTrustFebruary2005NamespacePrefix = "wst";

  /**
   * The Constant WSAddressingNamespacePrefix.
   */
  public static final String WSAddressingNamespacePrefix = "wsa";

  /**
   * The Constant AutodiscoverSoapNamespacePrefix.
   */
  public static final String AutodiscoverSoapNamespacePrefix = "a";

  /**
   * The Constant WSSecurityUtilityNamespacePrefix.
   */
  public static final String WSSecurityUtilityNamespacePrefix = "wsu";

  /**
   * The Constant WSSecuritySecExtNamespacePrefix.
   */
  public static final String WSSecuritySecExtNamespacePrefix = "wsse";

  /**
   * The Constant EwsTypesNamespace.
   */
  public static final String EwsTypesNamespace =
      "http://schemas.microsoft.com/exchange/services/2006/types";

  /**
   * The Constant EwsMessagesNamespace.
   */
  public static final String EwsMessagesNamespace =
      "http://schemas.microsoft.com/exchange/services/2006/messages";

  /**
   * The Constant EwsErrorsNamespace.
   */
  public static final String EwsErrorsNamespace =
      "http://schemas.microsoft.com/exchange/services/2006/errors";

  /**
   * The Constant EwsSoapNamespace.
   */
  public static final String EwsSoapNamespace =
      "http://schemas.xmlsoap.org/soap/envelope/";

  /**
   * The Constant EwsSoap12Namespace.
   */
  public static final String EwsSoap12Namespace =
      "http://www.w3.org/2003/05/soap-envelope";

  /**
   * The Constant EwsXmlSchemaInstanceNamespace.
   */
  public static final String EwsXmlSchemaInstanceNamespace =
      "http://www.w3.org/2001/XMLSchema-instance";

  /**
   * The Constant PassportSoapFaultNamespace.
   */
  public static final String PassportSoapFaultNamespace =
      "http://schemas.microsoft.com/Passport/SoapServices/SOAPFault";

  /**
   * The Constant WSTrustFebruary2005Namespace.
   */
  public static final String WSTrustFebruary2005Namespace =
      "http://schemas.xmlsoap.org/ws/2005/02/trust";

  /**
   * The Constant WSAddressingNamespace.
   */
  public static final String WSAddressingNamespace =
      "http://www.w3.org/2005/08/addressing";
  // "http://schemas.xmlsoap.org/ws/2004/08/addressing";

  /**
   * The Constant AutodiscoverSoapNamespace.
   */
  public static final String AutodiscoverSoapNamespace =
      "http://schemas.microsoft.com/exchange/2010/Autodiscover";

  public static final String WSSecurityUtilityNamespace =
      "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
  public static final String WSSecuritySecExtNamespace =
      "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

  /**
   * The service object info.
   */
  private static final LazyMember<ServiceObjectInfo> SERVICE_OBJECT_INFO =
      new LazyMember<ServiceObjectInfo>(
        new ILazyMember<ServiceObjectInfo>() {
          public ServiceObjectInfo createInstance() {
            return new ServiceObjectInfo();
          }
        }
      );

  private static final String XML_SCHEMA_DATE_FORMAT = "yyyy-MM-dd'Z'";
  private static final String XML_SCHEMA_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

  private static final Pattern PATTERN_TIME_SPAN = Pattern.compile("-P");
  private static final Pattern PATTERN_YEAR = Pattern.compile("(\\d+)Y");
  private static final Pattern PATTERN_MONTH = Pattern.compile("(\\d+)M");
  private static final Pattern PATTERN_DAY = Pattern.compile("(\\d+)D");
  private static final Pattern PATTERN_HOUR = Pattern.compile("(\\d+)H");
  private static final Pattern PATTERN_MINUTES = Pattern.compile("(\\d+)M");
  private static final Pattern PATTERN_SECONDS = Pattern.compile("(\\d+)\\."); // Need to escape dot, otherwise it matches any char
  private static final Pattern PATTERN_MILLISECONDS = Pattern.compile("(\\d+)S");


  private EwsUtilities() {
    throw new UnsupportedOperationException();
  }


  /**
   * Gets the builds the version.
   *
   * @return the builds the version
   */
  public static String getBuildVersion() {
    return "0.0.0.0";
  }

  /**
   * The enum version dictionaries.
   */
  private static final LazyMember<Map<Class<?>, Map<String, ExchangeVersion>>>
      ENUM_VERSION_DICTIONARIES =
      new LazyMember<Map<Class<?>, Map<String, ExchangeVersion>>>(
          new ILazyMember<Map<Class<?>, Map<String, ExchangeVersion>>>() {
            @Override
            public Map<Class<?>, Map<String, ExchangeVersion>>
            createInstance() {
              Map<Class<?>, Map<String, ExchangeVersion>> enumDicts =
                  new HashMap<Class<?>, Map<String,
                      ExchangeVersion>>();
              enumDicts.put(WellKnownFolderName.class,
                  buildEnumDict(WellKnownFolderName.class));
              enumDicts.put(ItemTraversal.class,
                  buildEnumDict(ItemTraversal.class));
              enumDicts.put(FileAsMapping.class,
                  buildEnumDict(FileAsMapping.class));
              enumDicts.put(EventType.class,
                  buildEnumDict(EventType.class));
              enumDicts.put(MeetingRequestsDeliveryScope.class,
                  buildEnumDict(MeetingRequestsDeliveryScope.
                      class));
              return enumDicts;
            }
          });
  /**
   * Dictionary of enum type to schema-name-to-enum-value maps.
   */
  private static final LazyMember<Map<Class<?>, Map<String, String>>>
      SCHEMA_TO_ENUM_DICTIONARIES =
      new LazyMember<Map<Class<?>, Map<String, String>>>(
          new ILazyMember<Map<Class<?>, Map<String, String>>>() {
            @Override
            public Map<Class<?>, Map<String, String>> createInstance() {
              Map<Class<?>, Map<String, String>> enumDicts =
                  new HashMap<Class<?>, Map<String, String>>();
              enumDicts.put(EventType.class,
                  buildSchemaToEnumDict(EventType.class));
              enumDicts.put(MailboxType.class,
                  buildSchemaToEnumDict(MailboxType.class));
              enumDicts.put(FileAsMapping.class,
                  buildSchemaToEnumDict(FileAsMapping.class));
              enumDicts.put(RuleProperty.class,
                  buildSchemaToEnumDict(RuleProperty.class));
              return enumDicts;

            }
          });

  /**
   * Dictionary of enum type to enum-value-to-schema-name maps.
   */
  public static final LazyMember<Map<Class<?>, Map<String, String>>>
      ENUM_TO_SCHEMA_DICTIONARIES =
      new LazyMember<Map<Class<?>, Map<String, String>>>(
          new ILazyMember<Map<Class<?>, Map<String, String>>>() {
            @Override
            public Map<Class<?>, Map<String, String>> createInstance() {
              Map<Class<?>, Map<String, String>> enumDicts =
                  new HashMap<Class<?>, Map<String, String>>();
              enumDicts.put(EventType.class,
                  buildEnumToSchemaDict(EventType.class));
              enumDicts.put(MailboxType.class,
                  buildEnumToSchemaDict(MailboxType.class));
              enumDicts.put(FileAsMapping.class,
                  buildEnumToSchemaDict(FileAsMapping.class));
              enumDicts.put(RuleProperty.class,
                  buildEnumToSchemaDict(RuleProperty.class));
              return enumDicts;
            }
          });

  /**
   * Regular expression for legal domain names.
   */
  public static final String DomainRegex = "^[-a-zA-Z0-9_.]+$";

  /**
   * Asserts that the specified condition if true.
   *
   * @param condition Assertion.
   * @param caller    The caller.
   * @param message   The message to use if assertion fails.
   */
  public static void ewsAssert(
    final boolean condition, final String caller, final String message
  ) {
    if (!condition) {
      throw new RuntimeException(String.format("[%s] %s", caller, message));
    }
  }

  /**
   * Gets the namespace prefix from an XmlNamespace enum value.
   *
   * @param xmlNamespace The XML namespace
   * @return Namespace prefix string.
   */
  public static String getNamespacePrefix(XmlNamespace xmlNamespace) {
    return xmlNamespace.getNameSpacePrefix();
  }

  /**
   * Gets the namespace URI from an XmlNamespace enum value.
   *
   * @param xmlNamespace The XML namespace.
   * @return Uri as string
   */
  public static String getNamespaceUri(XmlNamespace xmlNamespace) {
    return xmlNamespace.getNameSpaceUri();
  }

  /**
   * Gets the namespace from uri.
   *
   * @param namespaceUri the namespace uri
   * @return the namespace from uri
   */
  public static XmlNamespace getNamespaceFromUri(String namespaceUri) {
    if (EwsErrorsNamespace.equals(namespaceUri)) {
      return XmlNamespace.Errors;
    } else if (EwsTypesNamespace.equals(namespaceUri)) {
      return XmlNamespace.Types;
    } else if (EwsMessagesNamespace.equals(namespaceUri)) {
      return XmlNamespace.Messages;
    } else if (EwsSoapNamespace.equals(namespaceUri)) {
      return XmlNamespace.Soap;
    } else if (EwsSoap12Namespace.equals(namespaceUri)) {
      return XmlNamespace.Soap12;
    } else if (EwsXmlSchemaInstanceNamespace.equals(namespaceUri)) {
      return XmlNamespace.XmlSchemaInstance;
    } else if (PassportSoapFaultNamespace.equals(namespaceUri)) {
      return XmlNamespace.PassportSoapFault;
    } else if (WSTrustFebruary2005Namespace.equals(namespaceUri)) {
      return XmlNamespace.WSTrustFebruary2005;
    } else if (WSAddressingNamespace.equals(namespaceUri)) {
      return XmlNamespace.WSAddressing;
    } else {
      return XmlNamespace.NotSpecified;
    }
  }

  /**
   * Creates the ews object from xml element name.
   *
   * @param <TServiceObject> the generic type
   * @param itemClass        the item class
   * @param service          the service
   * @param xmlElementName   the xml element name
   * @return the t service object
   * @throws Exception the exception
   */
  @SuppressWarnings("unchecked")
  public static <TServiceObject extends ServiceObject>
  TServiceObject createEwsObjectFromXmlElementName(
      Class<?> itemClass, ExchangeService service, String xmlElementName)
      throws Exception {
    final ServiceObjectInfo member = EwsUtilities.SERVICE_OBJECT_INFO.getMember();
    final Map<String, Class<?>> map = member.getXmlElementNameToServiceObjectClassMap();

    final Class<?> ic = map.get(xmlElementName);
    if (ic != null) {
      final Map<Class<?>, ICreateServiceObjectWithServiceParam>
          serviceParam = member.getServiceObjectConstructorsWithServiceParam();
      final ICreateServiceObjectWithServiceParam creationDelegate =
          serviceParam.get(ic);

      if (creationDelegate != null) {
        return (TServiceObject) creationDelegate
            .createServiceObjectWithServiceParam(service);
      } else {
        throw new IllegalArgumentException("No appropriate constructor could be found for this item class.");
      }
    }

    return (TServiceObject) itemClass.newInstance();
  }

  /**
   * Creates the item from item class.
   *
   * @param itemAttachment the item attachment
   * @param itemClass      the item class
   * @param isNew          the is new
   * @return the item
   * @throws Exception the exception
   */
  public static Item createItemFromItemClass(
      ItemAttachment itemAttachment, Class<?> itemClass, boolean isNew)
      throws Exception {
    final ServiceObjectInfo member = EwsUtilities.SERVICE_OBJECT_INFO.getMember();
    final Map<Class<?>, ICreateServiceObjectWithAttachmentParam>
      dataMap = member.getServiceObjectConstructorsWithAttachmentParam();
    final ICreateServiceObjectWithAttachmentParam creationDelegate =
      dataMap.get(itemClass);

    if (creationDelegate != null) {
      return (Item) creationDelegate
          .createServiceObjectWithAttachmentParam(itemAttachment, isNew);
    }
    throw new IllegalArgumentException("No appropriate constructor could be found for this item class.");
  }

  /**
   * Creates the item from xml element name.
   *
   * @param itemAttachment the item attachment
   * @param xmlElementName the xml element name
   * @return the item
   * @throws Exception the exception
   */
  public static Item createItemFromXmlElementName(
      ItemAttachment itemAttachment, String xmlElementName)
      throws Exception {
    final ServiceObjectInfo member = EwsUtilities.SERVICE_OBJECT_INFO.getMember();
    final Map<String, Class<?>> map =
      member.getXmlElementNameToServiceObjectClassMap();

    final Class<?> itemClass = map.get(xmlElementName);
    if (itemClass != null) {
      return createItemFromItemClass(itemAttachment, itemClass, false);
    }
    return null;
  }

  public static Class<?> getItemTypeFromXmlElementName(String xmlElementName) {
    final ServiceObjectInfo member = EwsUtilities.SERVICE_OBJECT_INFO.getMember();
    final Map<String, Class<?>> map = member.getXmlElementNameToServiceObjectClassMap();
    return map.get(xmlElementName);
  }

  /**
   * Finds the first item of type TItem (not a descendant type) in the
   * specified collection.
   *
   * @param <TItem> TItem is the type of the item to find.
   * @param cls     the cls
   * @param items   the item
   * @return A TItem instance or null if no instance of TItem could be found.
   */
  @SuppressWarnings("unchecked")
  public static <TItem extends Item> TItem findFirstItemOfType(
    Class<TItem> cls, Iterable<Item> items
  ) {
    for (Item item : items) {
      // We're looking for an exact class match here.
      final Class<? extends Item> itemClass = item.getClass();
      if (itemClass.equals(cls)) {
        return (TItem) item;
      }
    }

    return null;
  }

  /**
   * Write trace start element.
   *
   * @param writer         the writer to write the start element to
   * @param traceTag       the trace tag
   * @param includeVersion if true, include build version attribute
   * @throws XMLStreamException the XML stream exception
   */
  private static void writeTraceStartElement(
      XMLStreamWriter writer,
      String traceTag,
      boolean includeVersion) throws XMLStreamException {
    writer.writeStartElement("Trace");
    writer.writeAttribute("Tag", traceTag);
    writer.writeAttribute("Tid", Thread.currentThread().getId() + "");
    Date d = new Date();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'");
    df.setTimeZone(TimeZone.getTimeZone("UTC"));
    String formattedString = df.format(d);
    writer.writeAttribute("Time", formattedString);

    if (includeVersion) {
      writer.writeAttribute("Version", EwsUtilities.getBuildVersion());
    }
  }

  /**
   * .
   *
   * @param entryKind the entry kind
   * @param logEntry  the log entry
   * @return the string
   * @throws XMLStreamException the XML stream exception
   * @throws IOException signals that an I/O exception has occurred.
   */
  public static String formatLogMessage(String entryKind, String logEntry)
      throws XMLStreamException, IOException {
    String lineSeparator = System.getProperty("line.separator");
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    XMLOutputFactory factory = XMLOutputFactory.newInstance();
    XMLStreamWriter writer = factory.createXMLStreamWriter(outStream);
    EwsUtilities.writeTraceStartElement(writer, entryKind, false);
    writer.writeCharacters(lineSeparator);
    writer.writeCharacters(logEntry);
    writer.writeCharacters(lineSeparator);
    writer.writeEndElement();
    writer.writeCharacters(lineSeparator);
    writer.flush();
    writer.close();
    outStream.flush();
    String formattedLogMessage = outStream.toString();
    formattedLogMessage = formattedLogMessage.replaceAll("&apos;", "'");
    formattedLogMessage = formattedLogMessage.replaceAll("&quot;", "\"");
    formattedLogMessage = formattedLogMessage.replaceAll("&gt;", ">");
    formattedLogMessage = formattedLogMessage.replaceAll("&lt;", "<");
    formattedLogMessage = formattedLogMessage.replaceAll("&amp;", "&");
    outStream.close();
    return formattedLogMessage;
  }

  /**
   * Format http response headers.
   *
   * @param response the response
   * @return the string
   * @throws EWSHttpException the EWS http exception
   */
  public static String formatHttpResponseHeaders(HttpWebRequest response)
      throws EWSHttpException {
    final int code = response.getResponseCode();
    final String contentType = response.getResponseContentType();
    final Map<String, String> headers = response.getResponseHeaders();

    return code + " " + contentType + "\n"
       + EwsUtilities.formatHttpHeaders(headers) + "\n";
  }

  /**
   * Format request HTTP headers.
   *
   * @param request The HTTP request.
   */
  public static String formatHttpRequestHeaders(HttpWebRequest request)
      throws URISyntaxException, EWSHttpException {
    final String method = request.getRequestMethod().toUpperCase();
    final String path = request.getUrl().toURI().getPath();
    final Map<String, String> property = request.getRequestProperty();
    final String headers = EwsUtilities.formatHttpHeaders(property);

    return String.format("%s %s HTTP/%s\n", method, path, "1.1") + headers + "\n";
  }

  /**
   * Formats HTTP headers.
   *
   * @param headers The headers.
   * @return Headers as a string
   */
  private static String formatHttpHeaders(Map<String, String> headers) {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String> header : headers.entrySet()) {
      sb.append(String.format("%s : %s\n", header.getKey(), header.getValue()));
    }
    return sb.toString();
  }

  /**
   * Format XML content in a MemoryStream for message.
   *
   * @param traceTypeStr Kind of the entry.
   * @param stream       The memory stream.
   * @return XML log entry as a string.
   */
  public static String formatLogMessageWithXmlContent(String traceTypeStr,
      ByteArrayOutputStream stream) {
    try {
      return formatLogMessage(traceTypeStr, stream.toString());
    } catch (Exception e) {
      return stream.toString();
    }
  }

  /**
   * Convert bool to XML Schema bool.
   *
   * @param value Bool value.
   * @return String representing bool value in XML Schema.
   */
  public static String boolToXSBool(Boolean value) {
    return value ? EwsUtilities.XSTrue : EwsUtilities.XSFalse;
  }

  /**
   * Parses an enum value list.
   *
   * @param <T>        the generic type
   * @param c          the c
   * @param list       the list
   * @param value      the value
   * @param separators the separators
   */
  public static <T extends Enum<?>> void parseEnumValueList(Class<T> c,
      List<T> list, String value, char... separators) {
    EwsUtilities.ewsAssert(c.isEnum(), "EwsUtilities.ParseEnumValueList", "T is not an enum type.");

    StringBuilder regexp = new StringBuilder();
    regexp.append("[");
    for (char s : separators) {
      regexp.append("[");
      regexp.append(Pattern.quote(s + ""));
      regexp.append("]");
    }
    regexp.append("]");

    String[] enumValues = value.split(regexp.toString());

    for (String enumValue : enumValues) {
      for (T o : c.getEnumConstants()) {
        if (o.toString().equals(enumValue)) {
          list.add(o);
        }
      }
    }
  }

  /**
   * Converts an enum to a string, using the mapping dictionaries if
   * appropriate.
   *
   * @param value The enum value to be serialized
   * @return String representation of enum to be used in the protocol
   */
  public static String serializeEnum(Object value) {
    String strValue = value.toString();
    final Map<Class<?>, Map<String, String>> member =
      ENUM_TO_SCHEMA_DICTIONARIES.getMember();

    final Map<String, String> enumToStringDict = member.get(value.getClass());
    if (enumToStringDict != null) {
      final Enum<?> e = (Enum<?>) value;
      final String enumStr = enumToStringDict.get(e.name());
      if (enumStr != null) {
        strValue = enumStr;
      }
    }
    return strValue;
  }

  /**
   * Parses the.
   *
   * @param <T>   the generic type
   * @param cls   the cls
   * @param value the value
   * @return the t
   * @throws java.text.ParseException the parse exception
   */
  @SuppressWarnings("unchecked")
  public static <T> T parse(Class<T> cls, String value) throws ParseException {
    if (cls.isEnum()) {
      final Map<Class<?>, Map<String, String>> member = SCHEMA_TO_ENUM_DICTIONARIES.getMember();

      String val = value;
      final Map<String, String> stringToEnumDict = member.get(cls);
      if (stringToEnumDict != null) {
        final String strEnumName = stringToEnumDict.get(value);
        if (strEnumName != null) {
          val = strEnumName;
        }
      }
      for (T o : cls.getEnumConstants()) {
        if (o.toString().equals(val)) {
          return o;
        }
      }
      return null;
    }else if (Number.class.isAssignableFrom(cls)){
      if (Double.class.isAssignableFrom(cls)){
        return (T) ((Double) Double.parseDouble(value));
      }else if (Integer.class.isAssignableFrom(cls)) {
        return (T) ((Integer) Integer.parseInt(value));
      }else if (Long.class.isAssignableFrom(cls)){
        return (T) ((Long) Long.parseLong(value));
      }else if (Float.class.isAssignableFrom(cls)){
        return (T) ((Float) Float.parseFloat(value));
      }else if (Byte.class.isAssignableFrom(cls)){
        return (T) ((Byte) Byte.parseByte(value));
      }else if (Short.class.isAssignableFrom(cls)){
        return (T) ((Short) Short.parseShort(value));
      }else if (BigInteger.class.isAssignableFrom(cls)){
        return (T) (new BigInteger(value));
      }else if (BigDecimal.class.isAssignableFrom(cls)){
        return (T) (new BigDecimal(value));
      }
    } else if (Date.class.isAssignableFrom(cls)) {
      DateFormat df = createDateFormat(XML_SCHEMA_DATE_TIME_FORMAT);
      return (T) df.parse(value);
    } else if (Boolean.class.isAssignableFrom(cls)) {
      return (T) ((Boolean) Boolean.parseBoolean(value));
    } else if (String.class.isAssignableFrom(cls)) {
      return (T) value;
    }
    return null;
  }



  /**
   * Builds the schema to enum mapping dictionary.
   *
   * @param <E> Type of the enum.
   * @param c   Class
   * @return The mapping from enum to schema name
   */
  private static <E extends Enum<E>> Map<String, String>
  buildSchemaToEnumDict(Class<E> c) {
    Map<String, String> dict = new HashMap<String, String>();

    Field[] fields = c.getDeclaredFields();
    for (Field f : fields) {
      if (f.isEnumConstant() && f.isAnnotationPresent(EwsEnum.class)) {
        EwsEnum ewsEnum = f.getAnnotation(EwsEnum.class);
        String fieldName = f.getName();
        String schemaName = ewsEnum.schemaName();
        if (!schemaName.isEmpty()) {
          dict.put(schemaName, fieldName);
        }
      }
    }
    return dict;
  }

  /**
   * Validate param collection.
   *
   * @param eventTypes the event types
   * @param paramName  the param name
   * @throws Exception the exception
   */
  public static void validateParamCollection(EventType[] eventTypes,
      String paramName) throws Exception {
    validateParam(eventTypes, paramName);
    int count = 0;

    for (EventType event : eventTypes) {
      try {
        validateParam(event, String.format("collection[%d] , ", count));
      } catch (Exception e) {
        throw new IllegalArgumentException(String.format(
            "The element at position %d is invalid", count), e);
      }
      count++;
    }

    if (count == 0) {
      throw new IllegalArgumentException(
        String.format("The collection \"%s\" is empty.", paramName)
      );
    }
  }

  /**
   * Convert DateTime to XML Schema date.
   *
   * @param date the date
   * @return String representation of DateTime.
   */
  public static String dateTimeToXSDate(Date date) {
    return formatDate(date, XML_SCHEMA_DATE_FORMAT);
  }

  /**
   * Dates the DateTime into an XML schema date time.
   *
   * @param date the date
   * @return String representation of DateTime.
   */
  public static String dateTimeToXSDateTime(Date date) {
    return formatDate(date, XML_SCHEMA_DATE_TIME_FORMAT);
  }

  /**
   * Takes a System.TimeSpan structure and converts it into an xs:duration
   * string as defined by the W3 Consortiums Recommendation
   * "XML Schema Part 2: Datatypes Second Edition",
   * http://www.w3.org/TR/xmlschema-2/#duration
   *
   * @param timeOffset structure to convert
   * @return xs:duration formatted string
   */
  public static String getTimeSpanToXSDuration(TimeSpan timeOffset) {
    // Optional '-' offset
    String offsetStr = (timeOffset.getTotalSeconds() < 0) ? "-" : "";
    long days = Math.abs(timeOffset.getDays());
    long hours = Math.abs(timeOffset.getHours());
    long minutes = Math.abs(timeOffset.getMinutes());
    long seconds = Math.abs(timeOffset.getSeconds());
    long milliseconds = Math.abs(timeOffset.getMilliseconds());

    // The TimeSpan structure does not have a Year or Month
    // property, therefore we wouldn't be able to return an xs:duration
    // string from a TimeSpan that included the nY or nM components.
    return offsetStr + "P" + days + "DT" + hours + "H" + minutes + "M"
       + seconds + "." + milliseconds + "S";
  }

  /**
   * Takes an xs:duration string as defined by the W3 Consortiums
   * Recommendation "XML Schema Part 2: Datatypes Second Edition",
   * http://www.w3.org/TR/xmlschema-2/#duration, and converts it into a
   * System.TimeSpan structure This method uses the following approximations:
   * 1 year = 365 days 1 month = 30 days Additionally, it only allows for four
   * decimal points of seconds precision.
   *
   * @param xsDuration xs:duration string to convert
   * @return System.TimeSpan structure
   */
  public static TimeSpan getXSDurationToTimeSpan(String xsDuration) {
    // TODO: Need to check whether this should be the equivalent or not
    Matcher m = PATTERN_TIME_SPAN.matcher(xsDuration);
    boolean negative = false;
    if (m.find()) {
      negative = true;
    }

    // Removing leading '-'
    if (negative) {
      xsDuration = xsDuration.replace("-P", "P");
    }

    Period period = Period.parse(xsDuration, ISOPeriodFormat.standard());
      
    long retval = period.toStandardDuration().getMillis();
    
    if (negative) {
      retval = -retval;
    }

    return new TimeSpan(retval);

  }

  /**
   * Time span to xs time.
   *
   * @param timeSpan the time span
   * @return the string
   */
  public static String timeSpanToXSTime(TimeSpan timeSpan) {
    DecimalFormat myFormatter = new DecimalFormat("00");
    return String.format("%s:%s:%s", myFormatter.format(timeSpan.getHours()), myFormatter.format(timeSpan
        .getMinutes()), myFormatter.format(timeSpan.getSeconds()));
  }

  /**
   * Gets the domain name from an email address.
   *
   * @param emailAddress The email address.
   * @return Domain name.
   * @throws FormatException the format exception
   */
  public static String domainFromEmailAddress(String emailAddress)
      throws FormatException {
    String[] emailAddressParts = emailAddress.split("@");

    if (emailAddressParts.length != 2
        || (emailAddressParts[1] == null || emailAddressParts[1]
        .isEmpty())) {
      throw new FormatException("The e-mail address is formed incorrectly.");
    }

    return emailAddressParts[1];
  }

  public static int getDim(Object array) {
    int dim = 0;
    Class<?> c = array.getClass();
    while (c.isArray()) {
      c = c.getComponentType();
      dim++;
    }
    return (dim);
  }

  /**
   * Validates parameter (and allows null value).
   *
   * @param param     The param.
   * @param paramName Name of the param.
   * @throws Exception the exception
   */
  public static void validateParamAllowNull(Object param, String paramName)
      throws Exception {
    if (param instanceof ISelfValidate) {
      ISelfValidate selfValidate = (ISelfValidate) param;
      try {
        selfValidate.validate();
      } catch (ServiceValidationException e) {
        throw new Exception(String.format("%s %s", "Validation failed.", paramName), e);
      }
    }

    if (param instanceof ServiceObject) {
      ServiceObject ewsObject = (ServiceObject) param;
      if (ewsObject.isNew()) {
        throw new Exception(String.format("%s %s", "This service object doesn't have an ID.", paramName));
      }
    }
  }

  /**
   * Validates parameter (null value not allowed).
   *
   * @param param     The param.
   * @param paramName Name of the param.
   * @throws Exception the exception
   */
  public static void validateParam(Object param, String paramName) throws Exception {
    boolean isValid;

    if (param instanceof String) {
      String strParam = (String) param;
      isValid = !strParam.isEmpty();
    } else {
      isValid = param != null;
    }

    if (!isValid) {
      throw new Exception(String.format("Argument %s not valid",
          paramName));
    }
    validateParamAllowNull(param, paramName);
  }

  /**
   * Validates parameter collection.
   *
   * @param <T>        the generic type
   * @param collection The collection.
   * @param paramName  Name of the param.
   * @throws Exception the exception
   */
  public static <T> void validateParamCollection(Iterator<T> collection, String paramName) throws Exception {
    validateParam(collection, paramName);
    int count = 0;

    while (collection.hasNext()) {
      T obj = collection.next();
      try {
        validateParam(obj, String.format("collection[%d],", count));
      } catch (Exception e) {
        throw new IllegalArgumentException(String.format(
            "The element at position %d is invalid", count), e);
      }
      count++;
    }

    if (count == 0) {
      throw new IllegalArgumentException(
        String.format("The collection \"%s\" is empty.", paramName)
      );
    }
  }

  /**
   * Validates string parameter to be non-empty string (null value allowed).
   *
   * @param param     The string parameter.
   * @param paramName Name of the parameter.
   * @throws ArgumentException
   * @throws ServiceLocalException
   */
  public static void validateNonBlankStringParamAllowNull(String param,
      String paramName) throws ArgumentException, ServiceLocalException {
    if (param != null) {
      // Non-empty string has at least one character
      //which is *not* a whitespace character
      if (param.length() == countMatchingChars(param,
          new IPredicate<Character>() {
            @Override
            public boolean predicate(Character obj) {
              return Character.isWhitespace(obj);
            }
          })) {
        throw new ArgumentException("The string argument contains only white space characters.", paramName);
      }
    }
  }


  /**
   * Validates string parameter to be
   * non-empty string (null value not allowed).
   *
   * @param param     The string parameter.
   * @param paramName Name of the parameter.
   * @throws ArgumentNullException
   * @throws ArgumentException
   * @throws ServiceLocalException
   */
  public static void validateNonBlankStringParam(String param,
      String paramName) throws ArgumentNullException, ArgumentException, ServiceLocalException {
    if (param == null) {
      throw new ArgumentNullException(paramName);
    }

    validateNonBlankStringParamAllowNull(param, paramName);
  }

  /**
   * Validate enum version value.
   *
   * @param enumValue      the enum value
   * @param requestVersion the request version
   * @throws ServiceVersionException the service version exception
   */
  public static void validateEnumVersionValue(Enum<?> enumValue,
      ExchangeVersion requestVersion) throws ServiceVersionException {
    final Map<Class<?>, Map<String, ExchangeVersion>> member =
      ENUM_VERSION_DICTIONARIES.getMember();
    final Map<String, ExchangeVersion> enumVersionDict =
      member.get(enumValue.getClass());

    final ExchangeVersion enumVersion = enumVersionDict.get(enumValue.toString());
    if (enumVersion != null) {
      final int i = requestVersion.compareTo(enumVersion);
      if (i < 0) {
        throw new ServiceVersionException(
          String.format(
            "Enumeration value %s in enumeration type %s is only valid for Exchange version %s or later.",
            enumValue.toString(),
            enumValue.getClass().getName(),
            enumVersion
          )
        );
      }
    }
  }

  /**
   * Validates service object version against the request version.
   *
   * @param serviceObject  The service object.
   * @param requestVersion The request version.
   * @throws ServiceVersionException Raised if this service object type requires a later version
   *                                 of Exchange.
   */
  public static void validateServiceObjectVersion(
      ServiceObject serviceObject, ExchangeVersion requestVersion)
      throws ServiceVersionException {
    ExchangeVersion minimumRequiredServerVersion = serviceObject
        .getMinimumRequiredServerVersion();

    if (requestVersion.ordinal() < minimumRequiredServerVersion.ordinal()) {
      String msg = String.format(
          "The object type %s is only valid for Exchange Server version %s or later versions.",
          serviceObject.getClass().getName(), minimumRequiredServerVersion.toString());
      throw new ServiceVersionException(msg);
    }
  }

  /**
   * Validates property version against the request version.
   *
   * @param service              The Exchange service.
   * @param minimumServerVersion The minimum server version
   * @param propertyName         The property name
   * @throws ServiceVersionException The service version exception
   */
  public static void validatePropertyVersion(
      ExchangeService service,
      ExchangeVersion minimumServerVersion,
      String propertyName) throws ServiceVersionException {
    if (service.getRequestedServerVersion().ordinal() <
        minimumServerVersion.ordinal()) {
      throw new ServiceVersionException(
          String.format("The property %s is valid only for Exchange %s or later versions.",
              propertyName,
              minimumServerVersion));
    }
  }

  /**
   * Validate method version.
   *
   * @param service              the service
   * @param minimumServerVersion the minimum server version
   * @param methodName           the method name
   * @throws ServiceVersionException the service version exception
   */
  public static void validateMethodVersion(ExchangeService service,
      ExchangeVersion minimumServerVersion, String methodName)
      throws ServiceVersionException {
    if (service.getRequestedServerVersion().ordinal() <
        minimumServerVersion.ordinal())

    {
      throw new ServiceVersionException(String.format(
          "Method %s is only valid for Exchange Server version %s or later.", methodName,
          minimumServerVersion));
    }
  }

  /**
   * Validates class version against the request version.
   *
   * @param service              the service
   * @param minimumServerVersion The minimum server version that supports the method.
   * @param className            Name of the class.
   * @throws ServiceVersionException
   */
  public static void validateClassVersion(
      ExchangeService service,
      ExchangeVersion minimumServerVersion,
      String className) throws ServiceVersionException {
    if (service.getRequestedServerVersion().ordinal() <
        minimumServerVersion.ordinal()) {
      throw new ServiceVersionException(
          String.format("Class %s is only valid for Exchange version %s or later.",
              className,
              minimumServerVersion));
    }
  }

  /**
   * Validates domain name (null value allowed)
   *
   * @param domainName Domain name.
   * @param paramName  Parameter name.
   * @throws ArgumentException
   */
  public static void validateDomainNameAllowNull(String domainName, String paramName) throws
                                                                                      ArgumentException {
    if (domainName != null) {
      Pattern domainNamePattern = Pattern.compile(DomainRegex);
      Matcher domainNameMatcher = domainNamePattern.matcher(domainName);
      if (!domainNameMatcher.find()) {
        throw new ArgumentException(String.format("'%s' is not a valid domain name.", domainName), paramName);
      }
    }
  }

  /**
   * Builds the enum dict.
   *
   * @param <E> the element type
   * @param c   the c
   * @return the map
   */
  private static <E extends Enum<E>> Map<String, ExchangeVersion>
  buildEnumDict(Class<E> c) {
    Map<String, ExchangeVersion> dict =
        new HashMap<String, ExchangeVersion>();
    Field[] fields = c.getDeclaredFields();
    for (Field f : fields) {
      if (f.isEnumConstant()
          && f.isAnnotationPresent(RequiredServerVersion.class)) {
        RequiredServerVersion ewsEnum = f
            .getAnnotation(RequiredServerVersion.class);
        String fieldName = f.getName();
        ExchangeVersion exchangeVersion = ewsEnum.version();
        dict.put(fieldName, exchangeVersion);
      }
    }
    return dict;
  }

  /**
   * Builds the enum to schema mapping dictionary.
   *
   * @param c class type
   * @return The mapping from enum to schema name
   */
  private static Map<String, String> buildEnumToSchemaDict(Class<?> c) {
    Map<String, String> dict = new HashMap<String, String>();
    Field[] fields = c.getFields();
    for (Field f : fields) {
      if (f.isEnumConstant() && f.isAnnotationPresent(EwsEnum.class)) {
        EwsEnum ewsEnum = f.getAnnotation(EwsEnum.class);
        String fieldName = f.getName();
        String schemaName = ewsEnum.schemaName();
        if (!schemaName.isEmpty()) {
          dict.put(fieldName, schemaName);
        }
      }
    }
    return dict;
  }

  /**
   * Gets the enumerated object count.
   *
   * @param <T>     the generic type
   * @param objects The objects.
   * @return Count of objects in iterator.
   */
  public static <T> int getEnumeratedObjectCount(Iterator<T> objects) {
    int count = 0;
    while (objects != null && objects.hasNext()) {
      objects.next();
      count++;
    }
    return count;
  }

  /**
   * Gets the enumerated object at.
   *
   * @param <T>     the generic type
   * @param objects the objects
   * @param index   the index
   * @return the enumerated object at
   */
  public static <T> Object getEnumeratedObjectAt(Iterable<T> objects, int index) {
    int count = 0;
    for (Object obj : objects) {
      if (count == index) {
        return obj;
      }
      count++;
    }
    throw new IndexOutOfBoundsException("The IEnumerable doesn't contain that many objects.");
  }


  /**
   * Count characters in string that match a condition.
   *
   * @param str           The string.
   * @param charPredicate Predicate to evaluate for each character in the string.
   * @return Count of characters that match condition expressed by predicate.
   * @throws ServiceLocalException
   */
  public static int countMatchingChars(
    String str, IPredicate<Character> charPredicate
  ) throws ServiceLocalException {
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      if (charPredicate.predicate(str.charAt(i))) {
        count++;
      }
    }
    return count;
  }

  /**
   * Determines whether every element in the collection
   * matches the conditions defined by the specified predicate.
   *
   * @param <T>        Entry type.
   * @param collection The collection.
   * @param predicate  Predicate that defines the conditions to check against the elements.
   * @return True if every element in the collection matches
   * the conditions defined by the specified predicate; otherwise, false.
   * @throws ServiceLocalException
   */
  public static <T> boolean trueForAll(Iterable<T> collection,
      IPredicate<T> predicate) throws ServiceLocalException {
    for (T entry : collection) {
      if (!predicate.predicate(entry)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Call an action for each member of a collection.
   *
   * @param <T>        Collection element type.
   * @param collection The collection.
   * @param action     The action to apply.
   */
  public static <T> void forEach(Iterable<T> collection, IAction<T> action) {
    for (T entry : collection) {
      action.action(entry);
    }
  }


  private static String formatDate(Date date, String format) {
    final DateFormat utcFormatter = createDateFormat(format);
    return utcFormatter.format(date);
  }

  private static DateFormat createDateFormat(String format) {
    final DateFormat utcFormatter = new SimpleDateFormat(format);
    utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    return utcFormatter;
  }

}
