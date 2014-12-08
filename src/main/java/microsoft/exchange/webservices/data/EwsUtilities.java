/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EWS utilities.
 */
class EwsUtilities {

  /**
   * The Constant XSFalse.
   */
  protected static final String XSFalse = "false";

  /**
   * The Constant XSTrue.
   */
  protected static final String XSTrue = "true";

  /**
   * The Constant EwsTypesNamespacePrefix.
   */
  protected static final String EwsTypesNamespacePrefix = "t";

  /**
   * The Constant EwsMessagesNamespacePrefix.
   */
  protected static final String EwsMessagesNamespacePrefix = "m";

  /**
   * The Constant EwsErrorsNamespacePrefix.
   */
  protected static final String EwsErrorsNamespacePrefix = "e";

  /**
   * The Constant EwsSoapNamespacePrefix.
   */
  protected static final String EwsSoapNamespacePrefix = "soap";

  /**
   * The Constant EwsXmlSchemaInstanceNamespacePrefix.
   */
  protected static final String EwsXmlSchemaInstanceNamespacePrefix = "xsi";

  /**
   * The Constant PassportSoapFaultNamespacePrefix.
   */
  protected static final String PassportSoapFaultNamespacePrefix = "psf";

  /**
   * The Constant WSTrustFebruary2005NamespacePrefix.
   */
  protected static final String WSTrustFebruary2005NamespacePrefix = "wst";

  /**
   * The Constant WSAddressingNamespacePrefix.
   */
  protected static final String WSAddressingNamespacePrefix = "wsa";

  /**
   * The Constant AutodiscoverSoapNamespacePrefix.
   */
  protected static final String AutodiscoverSoapNamespacePrefix = "a";

  /**
   * The Constant WSSecurityUtilityNamespacePrefix.
   */
  protected static final String WSSecurityUtilityNamespacePrefix = "wsu";

  /**
   * The Constant WSSecuritySecExtNamespacePrefix.
   */
  protected static final String WSSecuritySecExtNamespacePrefix = "wsse";

  /**
   * The Constant EwsTypesNamespace.
   */
  protected static final String EwsTypesNamespace =
      "http://schemas.microsoft.com/exchange/services/2006/types";

  /**
   * The Constant EwsMessagesNamespace.
   */
  protected static final String EwsMessagesNamespace =
      "http://schemas.microsoft.com/exchange/services/2006/messages";

  /**
   * The Constant EwsErrorsNamespace.
   */
  protected static final String EwsErrorsNamespace =
      "http://schemas.microsoft.com/exchange/services/2006/errors";

  /**
   * The Constant EwsSoapNamespace.
   */
  protected static final String EwsSoapNamespace =
      "http://schemas.xmlsoap.org/soap/envelope/";

  /**
   * The Constant EwsSoap12Namespace.
   */
  protected static final String EwsSoap12Namespace =
      "http://www.w3.org/2003/05/soap-envelope";

  /**
   * The Constant EwsXmlSchemaInstanceNamespace.
   */
  protected static final String EwsXmlSchemaInstanceNamespace =
      "http://www.w3.org/2001/XMLSchema-instance";

  /**
   * The Constant PassportSoapFaultNamespace.
   */
  protected static final String PassportSoapFaultNamespace =
      "http://schemas.microsoft.com/Passport/SoapServices/SOAPFault";

  /**
   * The Constant WSTrustFebruary2005Namespace.
   */
  protected static final String WSTrustFebruary2005Namespace =
      "http://schemas.xmlsoap.org/ws/2005/02/trust";

  /**
   * The Constant WSAddressingNamespace.
   */
  protected static final String WSAddressingNamespace =
      "http://www.w3.org/2005/08/addressing";
  // "http://schemas.xmlsoap.org/ws/2004/08/addressing";

  /**
   * The Constant AutodiscoverSoapNamespace.
   */
  protected static final String AutodiscoverSoapNamespace =
      "http://schemas.microsoft.com/exchange/2010/Autodiscover";

  protected static final String WSSecurityUtilityNamespace =
      "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
  protected static final String WSSecuritySecExtNamespace =
      "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

  /**
   * The service object info.
   */
  private static LazyMember<ServiceObjectInfo> serviceObjectInfo =
      new LazyMember<ServiceObjectInfo>(new
                                            ILazyMember<ServiceObjectInfo>() {
                                              public ServiceObjectInfo createInstance() {
                                                return new ServiceObjectInfo();
                                              }
                                            });


  /**
   * Copies source stream to target.
   *
   * @param source The source stream.
   * @param target The target stream.
   */
  protected static void copyStream(ByteArrayOutputStream source, ByteArrayOutputStream target)
      throws Exception {
    // See if this is a MemoryStream -- we can use WriteTo.

    	
   /* 	InputStream inputStream = new FileInputStream ("D:\\EWS ManagedAPI sp2\\Rp\\xml\\useravailrequest.xml");

    	 byte buf[]=new byte[1024];
    	 int len;
    	 while((len=inputStream.read(buf))>0)
    	 {
    	  target.write(buf,0, len);
    	 }
    	*/

    	/*PrintWriter pw = new PrintWriter(source,true);
            PrintWriter pw1 = new PrintWriter(target,true);
    	pw1.println(pw.toString());*/

    ByteArrayOutputStream memContentStream = source;
    if (memContentStream != null) {
      memContentStream.writeTo(target);
      memContentStream.flush();
    } else {
      // Otherwise, copy data through a buffer

      int c;
      ByteArrayInputStream inStream = new ByteArrayInputStream(source.toByteArray());

      while ((c = inStream.read()) != -1) {
        target.write((char) c);

      }
    }
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
   * A null-safe case sensitive comparison of two specified strings.
   *
   * @param first  The first string, can be null.
   * @param second The second string, can be null.
   * @return true: equals, false: otherwise.
   */
  public static boolean stringEquals(String first, String second) {
    return (first == null && second == null) || (first != null && first.equals(second));
  }

  /**
   * The enum version dictionaries.
   */
  private static LazyMember<Map<Class<?>, Map<String, ExchangeVersion>>>
      enumVersionDictionaries =
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
  private static LazyMember<Map<Class<?>, Map<String, String>>>
      schemaToEnumDictionaries =
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
  protected static LazyMember<Map<Class<?>, Map<String, String>>>
      enumToSchemaDictionaries =
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
   * Dictionary to map from special CLR type names to their "short" names.
   */
  private static LazyMember<Map<String, String>>
      typeNameToShortNameMap =
      new LazyMember<Map<String, String>>(
          new ILazyMember<Map<String, String>>() {
            public Map<String, String> createInstance() {
              Map<String, String> result =
                  new HashMap<String, String>();
              result.put("Boolean", "bool");
              result.put("Int16", "short");
              result.put("Int32", "int");
              result.put("String", "string");
              return result;
            }
          });

  /**
   * Regular expression for legal domain names.
   */
  protected static final String DomainRegex = "^[-a-zA-Z0-9_.]+$";

  /**
   * Asserts that the specified condition if true.
   *
   * @param condition Assertion.
   * @param caller    The caller.
   * @param message   The message to use if assertion fails.
   */
  protected static void EwsAssert(boolean condition, String caller,
      String message) {
    assert condition : String.format("[%s] %s",
        caller, message);
  }

  /**
   * Gets the namespace prefix from an XmlNamespace enum value.
   *
   * @param xmlNamespace The XML namespace
   * @return Namespace prefix string.
   */
  protected static String getNamespacePrefix(XmlNamespace xmlNamespace) {
    return xmlNamespace.getNameSpacePrefix();
  }

  /**
   * Gets the namespace URI from an XmlNamespace enum value.
   *
   * @param xmlNamespace The XML namespace.
   * @return Uri as string
   */
  protected static String getNamespaceUri(XmlNamespace xmlNamespace) {
    return xmlNamespace.getNameSpaceUri();
  }

  /**
   * Gets the namespace from uri.
   *
   * @param namespaceUri the namespace uri
   * @return the namespace from uri
   */
  protected static XmlNamespace getNamespaceFromUri(String namespaceUri) {
    if (namespaceUri.equals(EwsErrorsNamespace)) {
      return XmlNamespace.Errors;
    }
    if (namespaceUri.equals(EwsTypesNamespace)) {
      return XmlNamespace.Types;
    }
    if (namespaceUri.equals(EwsMessagesNamespace)) {
      return XmlNamespace.Messages;
    }
    if (namespaceUri.equals(EwsSoapNamespace)) {
      return XmlNamespace.Soap;
    }
    if (namespaceUri.equals(EwsSoap12Namespace)) {
      return XmlNamespace.Soap12;
    }
    if (namespaceUri.equals(EwsXmlSchemaInstanceNamespace)) {
      return XmlNamespace.XmlSchemaInstance;
    }
    if (namespaceUri.equals(PassportSoapFaultNamespace)) {
      return XmlNamespace.PassportSoapFault;
    }
    if (namespaceUri.equals(WSTrustFebruary2005Namespace)) {
      return XmlNamespace.WSTrustFebruary2005;
    }
    if (namespaceUri.equals(WSAddressingNamespace)) {
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
  protected static <TServiceObject extends ServiceObject>
  TServiceObject createEwsObjectFromXmlElementName(
      Class<?> itemClass, ExchangeService service, String xmlElementName)
      throws Exception {
    ICreateServiceObjectWithServiceParam creationDelegate;
    if (EwsUtilities.serviceObjectInfo.getMember()
        .getXmlElementNameToServiceObjectClassMap().containsKey(
            xmlElementName)) {
      itemClass = EwsUtilities.serviceObjectInfo.getMember()
          .getXmlElementNameToServiceObjectClassMap().get(
              xmlElementName);
      if (EwsUtilities.serviceObjectInfo.getMember()
          .getServiceObjectConstructorsWithServiceParam()
          .containsKey(itemClass)) {
        creationDelegate = EwsUtilities.serviceObjectInfo.getMember()
            .getServiceObjectConstructorsWithServiceParam().get(
                itemClass);
        return (TServiceObject) creationDelegate
            .createServiceObjectWithServiceParam(service);
      } else {
        throw new IllegalArgumentException(
            Strings.NoAppropriateConstructorForItemClass);
      }
    } else {
      return (TServiceObject) itemClass.newInstance();
    }
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
  protected static Item createItemFromItemClass(
      ItemAttachment itemAttachment, Class itemClass, boolean isNew)
      throws Exception {
    ICreateServiceObjectWithAttachmentParam creationDelegate;
    if (EwsUtilities.serviceObjectInfo.getMember()
        .getServiceObjectConstructorsWithAttachmentParam().containsKey(
            itemClass)) {

      creationDelegate = EwsUtilities.serviceObjectInfo.getMember()
          .getServiceObjectConstructorsWithAttachmentParam().get(
              itemClass);
      return (Item) creationDelegate
          .createServiceObjectWithAttachmentParam(itemAttachment,
              isNew);
    } else {
      throw new IllegalArgumentException(
          Strings.NoAppropriateConstructorForItemClass);
    }
  }

  /**
   * Creates the item from xml element name.
   *
   * @param itemAttachment the item attachment
   * @param xmlElementName the xml element name
   * @return the item
   * @throws Exception the exception
   */
  protected static Item createItemFromXmlElementName(
      ItemAttachment itemAttachment, String xmlElementName)
      throws Exception {
    Class<?> itemClass;
    if (EwsUtilities.serviceObjectInfo.getMember()
        .getXmlElementNameToServiceObjectClassMap().containsKey(
            xmlElementName)) {
      itemClass = EwsUtilities.serviceObjectInfo.getMember()
          .getXmlElementNameToServiceObjectClassMap().get(
              xmlElementName);
      return createItemFromItemClass(itemAttachment, itemClass, false);
    } else {
      return null;
    }
  }

  /**
   *
   */
  protected static Class getItemTypeFromXmlElementName(String xmlElementName) {

    return EwsUtilities.serviceObjectInfo.getMember().getXmlElementNameToServiceObjectClassMap()
        .get(xmlElementName).getClass();

  }

  /**
   * Finds the first item of type TItem (not a descendant type) in the specified collection.
   *
   * @param <TItem> TItem is the type of the item to find.
   * @param cls     the cls
   * @param items   the items
   * @return A TItem instance or null if no instance of TItem could be found.
   */

  static <TItem extends Item> TItem findFirstItemOfType(Class<TItem> cls,
      Iterable<Item> items) {
    for (Item item : items) {
      // We're looking for an exact class match here.
      if (item.getClass().equals(cls)) {
        return (TItem) item;
      }
    }

    return null;
  }

  /**
   * Write trace start element.
   *
   * @param writer         The writer to write the start element to.
   * @param traceTag       The trace tag.
   * @param includeVersion If true, include build version attribute.
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
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws java.io.IOException                 Signals that an I/O exception has occurred.
   */
  protected static String formatLogMessage(String entryKind, String logEntry)
      throws XMLStreamException, IOException {
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    XMLOutputFactory factory = XMLOutputFactory.newInstance();
    XMLStreamWriter writer = factory.createXMLStreamWriter(outStream);
    EwsUtilities.writeTraceStartElement(writer, entryKind, false);
    writer.writeCharacters(System.getProperty("line.separator"));
    writer.writeCharacters(logEntry);
    writer.writeCharacters(System.getProperty("line.separator"));
    writer.writeEndElement();
    writer.writeCharacters(System.getProperty("line.separator"));
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
   * @throws EWSHttpException the eWS http exception
   */
  protected static String formatHttpResponseHeaders(HttpWebRequest response)
      throws EWSHttpException {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("%d %s\n", response.getResponseCode(), response
        .getResponseContentType()));

    sb.append(EwsUtilities.formatHttpHeaders(response.
        getResponseHeaders()));
    sb.append("\n");
    return sb.toString();
  }

  /**
   * Format request HTTP headers.
   *
   * @param request The HTTP request.
   */
  protected static String formatHttpRequestHeaders(HttpWebRequest request)
      throws URISyntaxException, EWSHttpException {
    StringBuilder sb = new StringBuilder();
    sb.append(
        String.format(
            "%s %s HTTP/%s\n",
            request.getRequestMethod().toUpperCase(),
            request.getUrl().toURI().getPath(),
            "1.1"));

    sb.append(EwsUtilities.formatHttpHeaders(request.getRequestProperty()));
    sb.append("\n");
    return sb.toString();
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
  protected static String formatLogMessageWithXmlContent(String traceTypeStr,
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
  protected static String boolToXSBool(Boolean value) {
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
  protected static <T extends Enum> void parseEnumValueList(Class<T> c,
      List<T> list, String value, char... separators) {
    EwsUtilities.EwsAssert(c.isEnum(), "EwsUtilities.ParseEnumValueList",
        "T is not an enum type.");

    StringBuffer regexp = new StringBuffer("");
    regexp.append("[");
    for (char s : separators) {
      regexp.append("[");
      regexp.append(Pattern.quote(s + ""));
      regexp.append("]");
    }
    regexp.append("]");

    String[] enumValues = value.split(regexp.toString());

    for (String enumValue : enumValues) {
      // list.add((T)Enum.parse(c, enumValue, false));
      for (Object o : c.getEnumConstants()) {
        if (o.toString().equals(enumValue)) {
          list.add((T) o);
        }
      }
    }
  }

  /**
   * Converts an enum to a string, using the mapping dictionaries if appropriate.
   *
   * @param value The enum value to be serialized
   * @return String representation of enum to be used in the protocol
   */
  protected static String serializeEnum(Object value) {
    Map<String, String> enumToStringDict;
    String strValue = value.toString();
    if (enumToSchemaDictionaries.getMember().
        containsKey(value.getClass())) {
      enumToStringDict = enumToSchemaDictionaries.getMember().get(
          value.getClass());
      Enum<?> e = (Enum<?>) value;
      if (enumToStringDict.containsKey(e.name())) {
        strValue = enumToStringDict.get(e.name());
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
   * @throws InstantiationException   the instantiation exception
   * @throws IllegalAccessException   the illegal access exception
   * @throws java.text.ParseException the parse exception
   */
  protected static <T> T parse(Class<T> cls, String value)
      throws InstantiationException, IllegalAccessException,
      ParseException {

    if (cls.isEnum()) {
      Map<String, String> stringToEnumDict;
      if (schemaToEnumDictionaries.getMember().containsKey(cls)) {
        stringToEnumDict = schemaToEnumDictionaries.getMember()
            .get(cls);
        if (stringToEnumDict.containsKey(value)) {
          String strEnumName = stringToEnumDict.get(value);
          for (Object o : cls.getEnumConstants()) {
            if (o.toString().equals(strEnumName)) {
              return (T) o;
            }
          }
          return null;
        } else {
          for (Object o : cls.getEnumConstants()) {
            if (o.toString().equals(value)) {
              return (T) o;
            }
          }
          return null;
        }
      } else {
        for (Object o : cls.getEnumConstants()) {
          if (o.toString().equals(value)) {
            return (T) o;
          }
        }
        return null;
      }
    } else if (cls.isInstance(Integer.valueOf(0)))
    // else if( cls.isInstance(new Integer(0)))
    {
      Object o = null;
      o = Integer.parseInt(value);
      return (T) o;
    } else if (cls.isInstance(new Date())) {
      Object o = null;
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
      df.setTimeZone(TimeZone.getTimeZone("UTC"));
      return (T) df.parse(value);
    } else if (cls.isInstance(Boolean.valueOf(false)))
    // else if( cls.isInstance(new Boolean(false)))
    {
      Object o = null;
      o = Boolean.parseBoolean(value);
      return (T) o;
    } else if (cls.isInstance(new String())) {
      return (T) value;
    } else if (cls.isInstance(Double.valueOf(0.0))) {
      Object o = null;
      o = Double.parseDouble(value);
      return (T) o;
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
  protected static void validateParamCollection(EventType[] eventTypes,
      String paramName) throws Exception {

    validateParam(eventTypes, paramName);

    int count = 0;

    for (Object event : eventTypes) {

      try {
        validateParam(event, String.format("collection[%d] , ", count));
      } catch (Exception e) {
        throw new IllegalArgumentException(String.format(
            "The element at position %d is invalid", count), e);
      }

      count++;
    }

    if (count == 0) {
      throw new IllegalArgumentException(String.format(
          Strings.CollectionIsEmpty, paramName));
    }
  }



  /**
   * Convert DateTime to XML Schema date.
   *
   * @param date the date
   * @return String representation of DateTime.
   */
  static String dateTimeToXSDate(Date date) {
    String format = "yyyy-MM-dd'Z'";
    DateFormat utcFormatter = new SimpleDateFormat(format);
    utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    return utcFormatter.format(date);
  }

  /**
   * Dates the DateTime into an XML schema date time.
   *
   * @param date the date
   * @return String representation of DateTime.
   */
  protected static String dateTimeToXSDateTime(Date date) {
    String format = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    DateFormat utcFormatter = new SimpleDateFormat(format);
    utcFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    return utcFormatter.format(date);
  }

  /**
   * Takes a System.TimeSpan structure and converts it into an xs:duration string as defined by the W3
   * Consortiums Recommendation "XML Schema Part 2: Datatypes Second Edition",
   * http://www.w3.org/TR/xmlschema-2/#duration
   *
   * @param timeOffset structure to convert
   * @return xs:duration formatted string
   */
  protected static String getTimeSpanToXSDuration(TimeSpan timeOffset) {

		/*
                 * SimpleDateFormat dateformatter = new SimpleDateFormat("dd:HH:mm:ss");
		 * return dateformatter.format(timeOffset.toString());
		 */
    // Optional '-' offset
    String offsetStr = (timeOffset.getTotalSeconds() < 0) ? "-" : "";

    // The TimeSpan structure does not have a Year or Month
    // property, therefore we wouldn't be able to return an xs:duration
    // string from a TimeSpan that included the nY or nM components.

    return String.format("%sP%sDT%sH%sM%sS", offsetStr, Math.abs(timeOffset
        .getDays()), Math.abs(timeOffset.getHours()), Math
        .abs(timeOffset.getMinutes()), Math
        .abs(timeOffset.getSeconds())
        + "." + Math.abs(timeOffset.getMilliseconds()));
  }

  /**
   * Takes an xs:duration string as defined by the W3 Consortiums Recommendation "XML Schema Part 2: Datatypes
   * Second Edition", http://www.w3.org/TR/xmlschema-2/#duration, and converts it into a System.TimeSpan
   * structure This method uses the following approximations: 1 year = 365 days 1 month = 30 days
   * Additionally, it only allows for four decimal points of seconds precision.
   *
   * @param xsDuration xs:duration string to convert
   * @return System.TimeSpan structure
   */
  protected static TimeSpan getXSDurationToTimeSpan(String xsDuration) {
    // TODO: Need to check whether this should be the equivalent or not
    Pattern timeSpanParser = Pattern.compile("-P");
    Matcher m = timeSpanParser.matcher(xsDuration);
    boolean negative = false;
    System.out.println(m.find());
    if (m.find()) {
      negative = true;
    }
    System.out.println(m.group());

    // Year
    m = Pattern.compile("(\\d+)Y").matcher(xsDuration);
    System.out.println(m.find());
    int year = 0;
    if (m.find()) {
      year = Integer.parseInt(m.group().substring(0,
          m.group().indexOf("Y")));
    }

    // Month
    m = Pattern.compile("(\\d+)M").matcher(xsDuration);
    System.out.println(m.find());
    int month = 0;
    if (m.find()) {
      month = Integer.parseInt(m.group().substring(0,
          m.group().indexOf("M")));
    }

    // Day
    m = Pattern.compile("(\\d+)D").matcher(xsDuration);
    System.out.println(m.find());
    int day = 0;
    if (m.find()) {
      day = Integer.parseInt(m.group().substring(0,
          m.group().indexOf("D")));
    }

    // Hour
    m = Pattern.compile("(\\d+)H").matcher(xsDuration);
    System.out.println(m.find());
    int hour = 0;
    if (m.find()) {
      hour = Integer.parseInt(m.group().substring(0,
          m.group().indexOf("H")));
    }

    // Minute
    m = Pattern.compile("(\\d+)M").matcher(xsDuration);
    System.out.println(m.find());
    int minute = 0;
    if (m.find()) {
      minute = Integer.parseInt(m.group().substring(0,
          m.group().indexOf("M")));
    }

    // Seconds
    m = Pattern.compile("(\\d+).").matcher(xsDuration);
    System.out.println(m.find());
    int seconds = 0;
    if (m.find()) {
      seconds = Integer.parseInt(m.group().substring(0,
          m.group().indexOf(".")));
    }

    int milliseconds = 0;
    m = Pattern.compile("(\\d+)S").matcher(xsDuration);
    System.out.println(m.find());
    if (m.find()) {
      // Only allowed 4 digits of precision
      if (m.group().length() > 5) {
        milliseconds = Integer.parseInt(m.group().substring(0, 4));
      } else {
        seconds = Integer.parseInt(m.group().substring(0,
            m.group().indexOf("S")));
      }
    }

    // Apply conversions of year and months to days.
    // Year = 365 days
    // Month = 30 days
    day = day + (year * 365) + (month * 30);
    // TimeSpan retval = new TimeSpan(day, hour, minute, seconds,
    // milliseconds);
    long retval = (((((((day * 24) + hour) * 60) + minute) * 60) +
        seconds) * 1000) + milliseconds;
    if (negative) {
      retval = -retval;
    }
    return new TimeSpan(retval);

  }

  /**
   * Takes an xs:duration string as defined by the W3 Consortiums Recommendation "XML Schema Part 2: Datatypes
   * Second Edition", http://www.w3.org/TR/xmlschema-2/#duration, and converts it into a System.TimeSpan
   * structure This method uses the following approximations: 1 year = 365 days 1 month = 30 days
   * Additionally, it only allows for four decimal points of seconds precision.
   *
   * @param xsDuration xs:duration string to convert
   * @return System.TimeSpan structure
   */
  protected static TimeSpan getXSDurationToTimeSpanValue(String xsDuration) {
    // TODO: Need to check whether this should be the equivalent or not
    Pattern timeSpanParser = Pattern.compile("-P");
    Matcher m = timeSpanParser.matcher(xsDuration);
    boolean negative = false;
    //System.out.println(m.find());
    if (m.find()) {
      negative = true;
    }

    //System.out.println(m.find());
    // Year
    m = Pattern.compile("(\\d+)Y").matcher(xsDuration);
    //System.out.println(m.find());
    int year = 0;
    if (m.find()) {
      year = Integer.parseInt(m.group().substring(0,
          m.group().indexOf("Y")));
    }

    // Month
    m = Pattern.compile("(\\d+)M").matcher(xsDuration);
    //System.out.println(m.find());
    int month = 0;
    if (m.find()) {
      month = Integer.parseInt(m.group().substring(0,
          m.group().indexOf("M")));
    }

    // Day
    m = Pattern.compile("(\\d+)D").matcher(xsDuration);

    //System.out.println(m.find());

    long day = 0;
    if (m.find()) {
      day = Integer.parseInt(m.group().substring(0,
          m.group().indexOf("D")));
    }

    // Hour
    m = Pattern.compile("(\\d+)H").matcher(xsDuration);

    //System.out.println(m.find());

    int hour = 0;
    if (m.find()) {
      hour = Integer.parseInt(m.group().substring(0,
          m.group().indexOf("H")));
    }

    // Minute
    m = Pattern.compile("(\\d+)M").matcher(xsDuration);

    //System.out.println(m.find());

    int minute = 0;
    if (m.find()) {
      minute = Integer.parseInt(m.group().substring(0,
          m.group().indexOf("M")));
    }

    // Seconds
    m = Pattern.compile("(\\d+).").matcher(xsDuration);

    //System.out.println(m.find());

    int seconds = 0;

    //		if (m.find())
    //			seconds = Integer.parseInt(m.group().substring(0,
    //					m.group().indexOf(".")));

    int milliseconds = 0;
    m = Pattern.compile("(\\d+)S").matcher(xsDuration);

    //System.out.println(m.find());

    if (m.find()) {
      // Only allowed 4 digits of precision
      if (m.group().length() > 5) {
        milliseconds = Integer.parseInt(m.group().substring(0, 4));
      } else {
        seconds = Integer.parseInt(m.group().substring(0,
            m.group().indexOf("S")));
      }
    }

    // Apply conversions of year and months to days.
    // Year = 365 days
    // Month = 30 days
    //	day = day + (year * 365) + (month * 30);
    //TimeSpan retval = new TimeSpan(day, hour, minute, seconds,
    // milliseconds);

    long retval = ((((((((day * 24) + hour) * 60) + minute) * 60) +
        seconds) * 1000) + milliseconds);
    //		long retval=1010010;
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
   * Gets the printable name of a CLR type.
   *
   * @param type The class.
   * @return Printable name.
   */
  public static String getPrintableTypeName(Class type) {
    // Note: building array of generic parameters is
    //done recursively. Each parameter could be any type.
    Type[] genericArgs = type.getGenericInterfaces();
    if (genericArgs.length > 0) {
      // Convert generic type to printable form (e.g. List<Item>)
      String genericPrefix = type.getName().substring(0,
          type.getName().indexOf('`'));
      StringBuilder nameBuilder = new StringBuilder(genericPrefix);

      //List<Type> genericList = new ArrayList<Type>();
      StringBuffer genericArgsStr = new StringBuffer();
      for (int i = 0; i < genericArgs.length; i++) {

        if (!"".equals(genericArgsStr.toString())) {
          genericArgsStr.append(",");
        }
        genericArgsStr.append(getPrintableTypeName(
            genericArgs[i].getClass()));
      }
      nameBuilder.append("<");
      nameBuilder.append(genericArgsStr.toString());
      nameBuilder.append(">");
      return nameBuilder.toString();
    } else if (type.isArray()) {
      // Convert array type to printable form.
      String arrayPrefix = type.getName().substring(0,
          type.getName().indexOf('['));
      StringBuilder nameBuilder =
          new StringBuilder(EwsUtilities.
              getSimplifiedTypeName(arrayPrefix));

      for (int rank = 0; rank < getDim(type); rank++) {
        nameBuilder.append("[]");
      }
      return nameBuilder.toString();
    } else {
      return EwsUtilities.getSimplifiedTypeName(type.getName());
    }
  }

  /**
   * Gets the printable name of a CLR type.
   *
   * @param typeName The type name.
   * @return Printable name.
   */
  private static String getSimplifiedTypeName(String typeName) {
    // If type has a shortname (e.g. int for Int32) map to the short name.
    return typeNameToShortNameMap.getMember().containsKey(typeName) ?
        typeNameToShortNameMap.getMember().get(typeName) : typeName;
  }

  /**
   * Gets the domain name from an email address.
   *
   * @param emailAddress The email address.
   * @return Domain name.
   * @throws FormatException the format exception
   */
  protected static String domainFromEmailAddress(String emailAddress)
      throws FormatException {
    String[] emailAddressParts = emailAddress.split("@");

    if (emailAddressParts.length != 2
        || (emailAddressParts[1] == null || emailAddressParts[1]
        .isEmpty())) {
      throw new FormatException(Strings.InvalidEmailAddress);
    }

    return emailAddressParts[1];
  }

  public static int getDim(Object array) {
    int dim = 0;
    Class c = array.getClass();
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
  protected static void validateParamAllowNull(Object param, String paramName)
      throws Exception {
    if (param instanceof ISelfValidate) {
      ISelfValidate selfValidate = (ISelfValidate) param;
      try {
        selfValidate.validate();
      } catch (ServiceValidationException e) {
        throw new Exception(String.format("%s %s",
            Strings.ValidationFailed, paramName), e);
      }
    }

    if (param instanceof ServiceObject) {
      ServiceObject ewsObject = (ServiceObject) param;
      if (ewsObject.isNew()) {
        throw new Exception(String.format("%s %s",
            Strings.ObjectDoesNotHaveId, paramName));
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
  protected static void validateParam(Object param, String paramName)
      throws Exception {
    boolean isValid = false;

    if (param != null && param instanceof String) {
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
  protected static <T> void validateParamCollection(Iterator<T> collection,
      String paramName) throws Exception {

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
      throw new IllegalArgumentException(String.format(
          Strings.CollectionIsEmpty, paramName));
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
  protected static void validateNonBlankStringParamAllowNull(String param,
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
        throw new ArgumentException(Strings.
            ArgumentIsBlankString, paramName);
      }
    }
  }


  /**
   * Validates string parameter to be non-empty string (null value not allowed).
   *
   * @param param     The string parameter.
   * @param paramName Name of the parameter.
   * @throws ArgumentNullException
   * @throws ArgumentException
   * @throws ServiceLocalException
   */
  protected static void validateNonBlankStringParam(String param,
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
  protected static void validateEnumVersionValue(Enum<?> enumValue,
      ExchangeVersion requestVersion) throws ServiceVersionException {
    Map<String, ExchangeVersion> enumVersionDict = enumVersionDictionaries
        .getMember().get(enumValue.getClass());
    // String strValue = enumValue.toString();
    if (enumVersionDict.containsKey(enumValue.toString())) {
      ExchangeVersion enumVersion = enumVersionDict.get(enumValue
          .toString());
      int i = requestVersion.compareTo(enumVersion);
      if (i < 0) {
        throw new ServiceVersionException(String.format("%S,%S,%S,%S",
            Strings.EnumValueIncompatibleWithRequestVersion,
            enumValue.toString(), enumValue.getClass().getName(),
            enumVersion));
      }
    }
  }

  /**
   * Validates service object version against the request version.
   *
   * @param serviceObject  The service object.
   * @param requestVersion The request version.
   * @throws ServiceVersionException Raised if this service object type requires a later version of Exchange.
   */
  protected static void validateServiceObjectVersion(
      ServiceObject serviceObject, ExchangeVersion requestVersion)
      throws ServiceVersionException {
    ExchangeVersion minimumRequiredServerVersion = serviceObject
        .getMinimumRequiredServerVersion();

    if (requestVersion.ordinal() < minimumRequiredServerVersion.ordinal()) {
      String msg = String.format(
          Strings.ObjectTypeIncompatibleWithRequestVersion,
          serviceObject.getClass().getName(),
          minimumRequiredServerVersion.toString());
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
  protected static void validatePropertyVersion(
      ExchangeService service,
      ExchangeVersion minimumServerVersion,
      String propertyName) throws ServiceVersionException {
    if (service.getRequestedServerVersion().ordinal() <
        minimumServerVersion.ordinal()) {
      throw new ServiceVersionException(
          String.format(
              Strings.PropertyIncompatibleWithRequestVersion,
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
  protected static void validateMethodVersion(ExchangeService service,
      ExchangeVersion minimumServerVersion, String methodName)
      throws ServiceVersionException {
    if (service.getRequestedServerVersion().ordinal() <
        minimumServerVersion.ordinal())

    {
      throw new ServiceVersionException(String.format(
          Strings.MethodIncompatibleWithRequestVersion, methodName,
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
  protected static void validateClassVersion(
      ExchangeService service,
      ExchangeVersion minimumServerVersion,
      String className) throws ServiceVersionException {
    if (service.getRequestedServerVersion().ordinal() <
        minimumServerVersion.ordinal()) {
      throw new ServiceVersionException(
          String.format(
              Strings.ClassIncompatibleWithRequestVersion,
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
  protected static void validateDomainNameAllowNull(String domainName,
      String paramName) throws ArgumentException {
    if (domainName != null) {
      Pattern domainNamePattern = Pattern.compile(DomainRegex);
      Matcher domainNameMatcher = domainNamePattern.matcher(domainName);
      if (!domainNameMatcher.find()) {
        throw new ArgumentException(String.format(Strings.
            InvalidDomainName, domainName), paramName);
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
  protected static <T> int getEnumeratedObjectCount(Iterator<T> objects) {
    int count = 0;
    while (objects != null && objects.hasNext()) {
      @SuppressWarnings("unused")
      Object obj = objects.next();
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
  protected static <T> Object getEnumeratedObjectAt(Iterable<T> objects,
      int index) {
    int count = 0;
    for (Object obj : objects) {
      if (count == index) {
        return obj;
      }
      count++;
    }
    throw new IndexOutOfBoundsException(
        Strings.IEnumerableDoesNotContainThatManyObject);
  }


  /**
   * Count characters in string that match a condition.
   *
   * @param str           The string.
   * @param charPredicate Predicate to evaluate for each character in the string.
   * @return Count of characters that match condition expressed by predicate.
   * @throws ServiceLocalException
   */
  protected static int countMatchingChars(String str,
      IPredicate<Character> charPredicate) throws ServiceLocalException {
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      if (charPredicate.predicate(Character.valueOf(str.charAt(i)))) {
        count++;
      }
    }

    return count;
  }

  /**
   * Determines whether every element in the collection matches the conditions defined by the specified
   * predicate.
   *
   * @param <T>        Entry type.
   * @param collection The collection.
   * @param predicate  Predicate that defines the conditions to check against the elements.
   * @return True if every element in the collection matches the conditions defined by the specified
   * predicate; otherwise, false.
   * @throws ServiceLocalException
   */
  protected static <T> boolean trueForAll(Iterable<T> collection,
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
  protected static <T> void forEach(Iterable<T> collection, IAction<T> action) {
    for (T entry : collection) {
      action.action(entry);
    }
  }
}
