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

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.*;

/**
 * Defines the EwsXmlReader class.
 */
class EwsXmlReader {

  /**
   * The Read write buffer size.
   */
  private static final int ReadWriteBufferSize = 4096;

  /**
   * The xml reader.
   */
  private XMLEventReader xmlReader = null;

  /**
   * The present event.
   */
  private XMLEvent presentEvent;

  /**
   * The prev event.
   */
  private XMLEvent prevEvent;

  /**
   * Initializes a new instance of the EwsXmlReader class.
   *
   * @param stream the stream
   * @throws Exception
   */
  public EwsXmlReader(InputStream stream) throws Exception {
    this.xmlReader = initializeXmlReader(stream);
  }

  /**
   * Initializes the XML reader.
   *
   * @param stream the stream
   * @return An XML reader to use.
   * @throws Exception
   */
  protected XMLEventReader initializeXmlReader(InputStream stream)
      throws XMLStreamException, Exception {

    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    inputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
    //inputFactory.setProperty(XMLInputFactory.RESOLVER, null);

    return inputFactory.createXMLEventReader(stream);
  }


  /**
   * Formats the name of the element.
   *
   * @param namespacePrefix  The namespace prefix
   * @param localElementName Element name
   * @return the string
   */
  private static String formatElementName(String namespacePrefix,
      String localElementName) {

    return isNullOrEmpty(namespacePrefix) ? localElementName :
        namespacePrefix + ":" + localElementName;
  }

  /**
   * Read XML element.
   *
   * @param xmlNamespace The XML namespace
   * @param localName    Name of the local
   * @param nodeType     Type of the node
   * @throws Exception the exception
   */
  private void internalReadElement(XmlNamespace xmlNamespace,
      String localName, XmlNodeType nodeType) throws Exception {

    if (xmlNamespace == XmlNamespace.NotSpecified) {
      this.internalReadElement("", localName, nodeType);
    } else {
      this.read(nodeType);

      if ((!this.getLocalName().equals(localName)) ||
          (!this.getNamespaceUri().equals(EwsUtilities
              .getNamespaceUri(xmlNamespace)))) {
        throw new ServiceXmlDeserializationException(
            String
                .format(
                    Strings.UnexpectedElement,
                    EwsUtilities
                        .getNamespacePrefix(
                            xmlNamespace),
                    localName, nodeType.toString(), this
                        .getName(), this.getNodeType()
                        .toString()));
      }
    }
  }

  /**
   * Read XML element.
   *
   * @param namespacePrefix The namespace prefix
   * @param localName       Name of the local
   * @param nodeType        Type of the node
   * @throws Exception the exception
   */
  private void internalReadElement(String namespacePrefix, String localName,
      XmlNodeType nodeType) throws Exception {
    read(nodeType);

    if ((!this.getLocalName().equals(localName)) ||
        (!this.getNamespacePrefix().equals(namespacePrefix))) {
      throw new ServiceXmlDeserializationException(String.format(
          Strings.UnexpectedElement, namespacePrefix, localName,
          nodeType.toString(), this.getName(), this.getNodeType()
              .toString()));
    }
  }

  /**
   * Reads the specified node type.
   *
   * @throws ServiceXmlDeserializationException  the service xml deserialization exception
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   */
  public void read() throws ServiceXmlDeserializationException,
      XMLStreamException {
    // The caller to EwsXmlReader.Read expects
    // that there's another node to
    // read. Throw an exception if not true.
    while (true) {
      if (!xmlReader.hasNext()) {
        throw new ServiceXmlDeserializationException(
            Strings.UnexpectedEndOfXmlDocument);
      } else {
        XMLEvent event = xmlReader.nextEvent();
        if (event.getEventType() == XMLStreamConstants.CHARACTERS) {
          Characters characters = (Characters) event;
          if (characters.isIgnorableWhiteSpace()
              || characters.isWhiteSpace()) {
            continue;
          }
        }
        this.prevEvent = this.presentEvent;
        this.presentEvent = event;
        break;
      }
    }
  }

  /**
   * Reads the specified node type.
   *
   * @param nodeType Type of the node.
   * @throws Exception the exception
   */
  public void read(XmlNodeType nodeType) throws Exception {
    this.read();
    if (!this.getNodeType().equals(nodeType)) {
      throw new ServiceXmlDeserializationException(String
          .format(Strings.UnexpectedElementType, nodeType, this
              .getNodeType()));
    }
  }

  /**
   * Read attribute value from QName.
   *
   * @param qName QName of the attribute
   * @return Attribute Value
   * @throws Exception thrown if attribute value can not be read
   */
  private String readAttributeValue(QName qName) throws Exception {
    if (this.presentEvent.isStartElement()) {
      StartElement startElement = this.presentEvent.asStartElement();
      Attribute attr = startElement.getAttributeByName(qName);
      if (null != attr) {
        return attr.getValue();
      } else {
        return null;
      }
    } else {
      String errMsg = String.format("Could not fetch attribute %s", qName
          .toString());
      throw new Exception(errMsg);
    }
  }

  /**
   * Reads the attribute value.
   *
   * @param xmlNamespace  The XML namespace.
   * @param attributeName Name of the attribute
   * @return Attribute Value
   * @throws Exception the exception
   */
  public String readAttributeValue(XmlNamespace xmlNamespace,
      String attributeName) throws Exception {
    if (xmlNamespace == XmlNamespace.NotSpecified) {
      return this.readAttributeValue(attributeName);
    } else {
      QName qName = new QName(EwsUtilities.getNamespaceUri(xmlNamespace),
          attributeName);
      return readAttributeValue(qName);
    }
  }

  /**
   * Reads the attribute value.
   *
   * @param attributeName Name of the attribute
   * @return Attribute value.
   * @throws Exception the exception
   */
  public String readAttributeValue(String attributeName) throws Exception {
    QName qName = new QName(attributeName);
    return readAttributeValue(qName);
  }

  /**
   * Reads the attribute value.
   *
   * @param <T>           the generic type
   * @param cls           the cls
   * @param attributeName the attribute name
   * @return T
   * @throws Exception the exception
   */
  public <T> T readAttributeValue(Class<T> cls, String attributeName)
      throws Exception {
    return EwsUtilities.parse(cls, this.readAttributeValue(attributeName));
  }

  /**
   * Reads a nullable attribute value.
   *
   * @param <T>           the generic type
   * @param cls           the cls
   * @param attributeName the attribute name
   * @return T
   * @throws Exception the exception
   */
  public <T> T readNullableAttributeValue(Class<T> cls, String attributeName)
      throws Exception {
    String attributeValue = this.readAttributeValue(attributeName);
    if (attributeValue == null) {
      return null;
    } else {
      return EwsUtilities.parse(cls, attributeValue);
    }
  }

  /**
   * Reads the element value.
   *
   * @param namespacePrefix the namespace prefix
   * @param localName       the local name
   * @return String
   * @throws Exception the exception
   */
  public String readElementValue(String namespacePrefix, String localName)
      throws Exception {
    if (!this.isStartElement(namespacePrefix, localName)) {
      this.readStartElement(namespacePrefix, localName);
    }

    String value = null;

    if (!this.isEmptyElement()) {
      value = this.readValue();
    }
    return value;
  }

  /**
   * Reads the element value.
   *
   * @param xmlNamespace the xml namespace
   * @param localName    the local name
   * @return String
   * @throws Exception the exception
   */
  public String readElementValue(XmlNamespace xmlNamespace, String localName)
      throws Exception {

    if (!this.isStartElement(xmlNamespace, localName)) {
      this.readStartElement(xmlNamespace, localName);
    }

    String value = null;

    if (!this.isEmptyElement()) {
      value = this.readValue();
    } else {
      this.read();
    }

    return value;
  }

  /**
   * Read element value.
   *
   * @return String
   * @throws Exception the exception
   */
  public String readElementValue() throws Exception {
    this.ensureCurrentNodeIsStartElement();

    return this.readElementValue(this.getNamespacePrefix(), this
        .getLocalName());
  }

  /**
   * Reads the element value.
   *
   * @param <T>          the generic type
   * @param cls          the cls
   * @param xmlNamespace the xml namespace
   * @param localName    the local name
   * @return T
   * @throws Exception the exception
   */
  public <T> T readElementValue(Class<T> cls, XmlNamespace xmlNamespace,
      String localName) throws Exception {
    if (!this.isStartElement(xmlNamespace, localName)) {
      this.readStartElement(xmlNamespace, localName);
    }

    T value = null;

    if (!this.isEmptyElement()) {
      value = this.readValue(cls);
    }

    return value;
  }

  /**
   * Read element value.
   *
   * @param <T> the generic type
   * @param cls the cls
   * @return T
   * @throws Exception the exception
   */
  public <T> T readElementValue(Class<T> cls) throws Exception {
    this.ensureCurrentNodeIsStartElement();

    T value = null;

    if (!this.isEmptyElement()) {
      value = this.readValue(cls);
    }

    return value;
  }

  /**
   * Reads the value. Should return content element or text node as string
   * Present event must be START ELEMENT. After executing this function
   * Present event will be set on END ELEMENT
   *
   * @return String
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlDeserializationException  the service xml deserialization exception
   */
  public String readValue() throws XMLStreamException,
      ServiceXmlDeserializationException {
    String errMsg = String.format("Could not read value from %s.",
        XmlNodeType.getString(this.presentEvent.getEventType()));
    if (this.presentEvent.isStartElement()) {
      // Go to next event and check for Characters event
      this.read();
      if (this.presentEvent.isCharacters()) {
        StringBuffer elementValue = new StringBuffer();
        do {
          if (this.getNodeType().nodeType == XmlNodeType.CHARACTERS) {
            Characters characters = (Characters) this.presentEvent;
            if (!characters.isIgnorableWhiteSpace()
                && !characters.isWhiteSpace()) {
              if (characters.getData().length() != 0) {
                elementValue.append(characters.getData());
              }
            }
          }
          this.read();
        } while (!this.presentEvent.isEndElement());
        // Characters chars = this.presentEvent.asCharacters();
        // String elementValue = chars.getData();
        // Advance to next event post Characters (ideally it will be End
        // Element)
        // this.read();
        return elementValue.toString();
      } else {
        errMsg = errMsg + "Could not find "
            + XmlNodeType.getString(XmlNodeType.CHARACTERS);
        throw new ServiceXmlDeserializationException(errMsg);
      }
    } else if (this.presentEvent.getEventType() == XmlNodeType.CHARACTERS
        && this.presentEvent.isCharacters()) {
                        /*
			 * if(this.presentEvent.asCharacters().getData().equals("<")) {
			 */
      StringBuffer data = new StringBuffer(this.presentEvent
          .asCharacters().getData());
      do {
        this.read();
        if (this.getNodeType().nodeType == XmlNodeType.CHARACTERS) {
          Characters characters = (Characters) this.presentEvent;
          if (!characters.isIgnorableWhiteSpace()
              && !characters.isWhiteSpace()) {
            if (characters.getData().length() != 0) {
              data.append(characters.getData());
            }
          }
        }
      } while (!this.presentEvent.isEndElement());
      return data.toString();// this.presentEvent. = new XMLEvent();
			/*
			 * } else { Characters chars = this.presentEvent.asCharacters();
			 * String elementValue = chars.getData(); // Advance to next event
			 * post Characters (ideally it will be End // Element) this.read();
			 * return elementValue; }
			 */
    } else {
      errMsg = errMsg + "Expected is "
          + XmlNodeType.getString(XmlNodeType.START_ELEMENT);
      throw new ServiceXmlDeserializationException(errMsg);
    }

  }

  /**
   * Tries to read value.
   *
   * @param value the value
   * @return boolean
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlDeserializationException  the service xml deserialization exception
   */
  public boolean tryReadValue(OutParam<String> value)
      throws XMLStreamException, ServiceXmlDeserializationException {
    if (!this.isEmptyElement()) {
      this.read();

      if (this.presentEvent.isCharacters()) {
        value.setParam(this.readValue());
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  /**
   * Reads the value.
   *
   * @param <T> the generic type
   * @param cls the cls
   * @return T
   * @throws Exception the exception
   */
  public <T> T readValue(Class<T> cls) throws Exception {
    return EwsUtilities.parse(cls, this.readValue());
  }

  /**
   * Reads the base64 element value.
   *
   * @return byte[]
   * @throws ServiceXmlDeserializationException  the service xml deserialization exception
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws java.io.IOException                 Signals that an I/O exception has occurred.
   */
  public byte[] readBase64ElementValue()
      throws ServiceXmlDeserializationException, XMLStreamException,
      IOException {
    this.ensureCurrentNodeIsStartElement();

    byte[] buffer = null;

    ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();

    buffer = Base64EncoderStream.decode(this.xmlReader.getElementText()
        .toString());
    byteArrayStream.write(buffer);

    return byteArrayStream.toByteArray();

  }

  /**
   * Reads the base64 element value.
   *
   * @param outputStream the output stream
   * @throws Exception the exception
   */
  public void readBase64ElementValue(OutputStream outputStream)
      throws Exception {
    this.ensureCurrentNodeIsStartElement();

    byte[] buffer = null;
    buffer = Base64EncoderStream.decode(this.xmlReader.getElementText()
        .toString());
    outputStream.write(buffer);
    outputStream.flush();
  }

  /**
   * Reads the start element.
   *
   * @param namespacePrefix the namespace prefix
   * @param localName       the local name
   * @throws Exception the exception
   */
  public void readStartElement(String namespacePrefix, String localName)
      throws Exception {
    this.internalReadElement(namespacePrefix, localName, new XmlNodeType(
        XmlNodeType.START_ELEMENT));
  }

  /**
   * Reads the start element.
   *
   * @param xmlNamespace the xml namespace
   * @param localName    the local name
   * @throws Exception the exception
   */
  public void readStartElement(XmlNamespace xmlNamespace, String localName)
      throws Exception {
    this.internalReadElement(xmlNamespace, localName, new XmlNodeType(
        XmlNodeType.START_ELEMENT));
  }

  /**
   * Reads the end element.
   *
   * @param namespacePrefix the namespace prefix
   * @param elementName     the element name
   * @throws Exception the exception
   */
  public void readEndElement(String namespacePrefix, String elementName)
      throws Exception {
    this.internalReadElement(namespacePrefix, elementName, new XmlNodeType(
        XmlNodeType.END_ELEMENT));
  }

  /**
   * Reads the end element.
   *
   * @param xmlNamespace the xml namespace
   * @param localName    the local name
   * @throws Exception the exception
   */
  public void readEndElement(XmlNamespace xmlNamespace, String localName)
      throws Exception {

    this.internalReadElement(xmlNamespace, localName, new XmlNodeType(
        XmlNodeType.END_ELEMENT));

  }

  /**
   * Reads the end element if necessary.
   *
   * @param xmlNamespace the xml namespace
   * @param localName    the local name
   * @throws Exception the exception
   */
  public void readEndElementIfNecessary(XmlNamespace xmlNamespace,
      String localName) throws Exception {

    if (!(this.isStartElement(xmlNamespace, localName) && this
        .isEmptyElement())) {
      if (!this.isEndElement(xmlNamespace, localName)) {
        this.readEndElement(xmlNamespace, localName);
      }
    }
  }

  /**
   * Determines whether current element is a start element.
   *
   * @return boolean
   */
  public boolean isStartElement() {
    return this.presentEvent.isStartElement();
  }

  /**
   * Determines whether current element is a start element.
   *
   * @param namespacePrefix the namespace prefix
   * @param localName       the local name
   * @return boolean
   */
  public boolean isStartElement(String namespacePrefix, String localName) {
    boolean isStart = false;
    if (this.presentEvent.isStartElement()) {
      StartElement startElement = this.presentEvent.asStartElement();
      QName qName = startElement.getName();
      isStart = qName.getLocalPart().equals(localName)
          && qName.getPrefix().equals(namespacePrefix);
    }
    return isStart;
  }

  /**
   * Determines whether current element is a start element.
   *
   * @param xmlNamespace the xml namespace
   * @param localName    the local name
   * @return true for matching start element; false otherwise.
   */
  public boolean isStartElement(XmlNamespace xmlNamespace, String localName) {
    return this.isStartElement()
        && EwsUtilities.stringEquals(this.getLocalName(), localName)
        && (
        EwsUtilities.stringEquals(this.getNamespacePrefix(), EwsUtilities.getNamespacePrefix(xmlNamespace)) ||
            EwsUtilities.stringEquals(this.getNamespaceUri(), EwsUtilities.getNamespaceUri(xmlNamespace)));
  }

  /**
   * Determines whether current element is a end element.
   *
   * @param namespacePrefix the namespace prefix
   * @param localName       the local name
   * @return boolean
   */
  public boolean isEndElement(String namespacePrefix, String localName) {
    boolean isEndElement = false;
    if (this.presentEvent.isEndElement()) {
      EndElement endElement = this.presentEvent.asEndElement();
      QName qName = endElement.getName();
      isEndElement = qName.getLocalPart().equals(localName)
          && qName.getPrefix().equals(namespacePrefix);

    }
    return isEndElement;
  }

  /**
   * Determines whether current element is a end element.
   *
   * @param xmlNamespace the xml namespace
   * @param localName    the local name
   * @return boolean
   */
  public boolean isEndElement(XmlNamespace xmlNamespace, String localName) {

    boolean isEndElement = false;
		/*
		 * if(localName.equals("Body")) { return true; } else
		 */
    if (this.presentEvent.isEndElement()) {
      EndElement endElement = this.presentEvent.asEndElement();
      QName qName = endElement.getName();
      isEndElement = qName.getLocalPart().equals(localName)
          && (qName.getPrefix().equals(
          EwsUtilities.getNamespacePrefix(xmlNamespace)) ||
          qName.getNamespaceURI().equals(
              EwsUtilities.getNamespaceUri(
                  xmlNamespace)));

    }
    return isEndElement;
  }

  /**
   * Skips the element.
   *
   * @param namespacePrefix the namespace prefix
   * @param localName       the local name
   * @throws Exception the exception
   */
  public void skipElement(String namespacePrefix, String localName)
      throws Exception {
    if (!this.isEndElement(namespacePrefix, localName)) {
      if (!this.isStartElement(namespacePrefix, localName)) {
        this.readStartElement(namespacePrefix, localName);
      }

      if (!this.isEmptyElement()) {
        do {
          this.read();
        } while (!this.isEndElement(namespacePrefix, localName));
      }
    }
  }

  /**
   * Skips the element.
   *
   * @param xmlNamespace the xml namespace
   * @param localName    the local name
   * @throws Exception the exception
   */
  public void skipElement(XmlNamespace xmlNamespace, String localName)
      throws Exception {
    if (!this.isEndElement(xmlNamespace, localName)) {
      if (!this.isStartElement(xmlNamespace, localName)) {
        this.readStartElement(xmlNamespace, localName);
      }

      if (!this.isEmptyElement()) {
        do {
          this.read();
        } while (!this.isEndElement(xmlNamespace, localName));
      }
    }
  }

  /**
   * Skips the current element.
   *
   * @throws Exception the exception
   */
  public void skipCurrentElement() throws Exception {
    this.skipElement(this.getNamespacePrefix(), this.getLocalName());
  }

  /**
   * Ensures the current node is start element.
   *
   * @param xmlNamespace the xml namespace
   * @param localName    the local name
   * @throws ServiceXmlDeserializationException the service xml deserialization exception
   */
  public void ensureCurrentNodeIsStartElement(XmlNamespace xmlNamespace,
      String localName) throws ServiceXmlDeserializationException {

    if (!this.isStartElement(xmlNamespace, localName)) {
      throw new ServiceXmlDeserializationException(
          String
              .format(
                  Strings.ElementNotFound,
                  localName, xmlNamespace));
    }
  }

  /**
   * Ensures the current node is start element.
   *
   * @throws ServiceXmlDeserializationException the service xml deserialization exception
   */
  public void ensureCurrentNodeIsStartElement()
      throws ServiceXmlDeserializationException {
    XmlNodeType presentNodeType = new XmlNodeType(this.presentEvent
        .getEventType());
    if (!this.presentEvent.isStartElement()) {
      throw new ServiceXmlDeserializationException(String.format(
          Strings.ExpectedStartElement,
          this.presentEvent.toString(), presentNodeType.toString()));
    }
  }

  /**
   * Ensures the current node is start element.
   *
   * @param xmlNamespace the xml namespace
   * @param localName    the local name
   * @throws Exception the exception
   */
  public void ensureCurrentNodeIsEndElement(XmlNamespace xmlNamespace,
      String localName) throws Exception {
    if (!this.isEndElement(xmlNamespace, localName)) {
      if (!(this.isStartElement(xmlNamespace, localName) && this
          .isEmptyElement())) {
        throw new ServiceXmlDeserializationException(
            String
                .format(
                    Strings.ElementNotFound,
                    xmlNamespace, localName));
      }
    }
  }

  /**
   * Outer XML as string.
   *
   * @return String
   * @throws ServiceXmlDeserializationException  the service xml deserialization exception
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   */
  public String readOuterXml() throws ServiceXmlDeserializationException,
      XMLStreamException {
    if (!this.isStartElement()) {
      throw new ServiceXmlDeserializationException(
          Strings.CurrentPositionNotElementStart);
    }

    XMLEvent startEvent = this.presentEvent;
    XMLEvent event;
    StringBuilder str = new StringBuilder();
    str.append(startEvent);
    do {
      event = this.xmlReader.nextEvent();
      str.append(event);
    } while (!checkEndElement(startEvent, event));

    return str.toString();
  }

  /**
   * Reads the Inner XML at the given location.
   *
   * @return String
   * @throws ServiceXmlDeserializationException  the service xml deserialization exception
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   */
  public String readInnerXml() throws ServiceXmlDeserializationException,
      XMLStreamException {
    if (!this.isStartElement()) {
      throw new ServiceXmlDeserializationException(
          Strings.CurrentPositionNotElementStart);
    }

    XMLEvent startEvent = this.presentEvent;
    StringBuilder str = new StringBuilder();
    do {
      XMLEvent event = this.xmlReader.nextEvent();
      if (checkEndElement(startEvent, event)) {
        break;
      }
      str.append(event);
    } while (true);

    return str.toString();
  }

  /**
   * Check end element.
   *
   * @param startEvent the start event
   * @param endEvent   the end event
   * @return true, if successful
   */
  public static boolean checkEndElement(XMLEvent startEvent,
      XMLEvent endEvent) {

    boolean isEndElement = false;
    if (endEvent.isEndElement()) {
      QName qEName = endEvent.asEndElement().getName();
      QName qSName = startEvent.asStartElement().getName();
      isEndElement = qEName.getLocalPart().equals(qSName.getLocalPart())
          && (qEName.getPrefix().equals(qSName.getPrefix()) || qEName
          .getNamespaceURI().equals(qSName.
              getNamespaceURI()));

    }
    return isEndElement;
  }

  /**
   * Gets the XML reader for node.
   *
   * @return null
   * @throws javax.xml.stream.XMLStreamException
   * @throws ServiceXmlDeserializationException
   * @throws java.io.FileNotFoundException
   */
  protected XMLEventReader getXmlReaderForNode()
      throws FileNotFoundException, ServiceXmlDeserializationException, XMLStreamException {
    return readSubtree(); //this.xmlReader.ReadSubtree();
  }

  public XMLEventReader readSubtree()
      throws XMLStreamException, FileNotFoundException, ServiceXmlDeserializationException {

    if (!this.isStartElement()) {
      throw new ServiceXmlDeserializationException(
          Strings.CurrentPositionNotElementStart);
    }

    XMLEventReader eventReader = null;
    InputStream in = null;
    XMLEvent startEvent = this.presentEvent;
    XMLEvent event = startEvent;
    StringBuilder str = new StringBuilder();
    str.append(startEvent);
    do {
      event = this.xmlReader.nextEvent();
      str.append(event);
    } while (!checkEndElement(startEvent, event));

    try {

      XMLInputFactory inputFactory = XMLInputFactory.newInstance();

      try {
        in = new ByteArrayInputStream(str.toString().getBytes("UTF-8"));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
      eventReader = inputFactory.createXMLEventReader(in);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return eventReader;
  }

  /**
   * Reads to the next descendant element with the specified local name and
   * namespace.
   *
   * @param xmlNamespace The namespace of the element you with to move to.
   * @param localName    The local name of the element you wish to move to.
   * @throws javax.xml.stream.XMLStreamException
   */
  public void ReadToDescendant(XmlNamespace xmlNamespace, String localName) throws XMLStreamException {
    readToDescendant(localName, EwsUtilities.getNamespaceUri(xmlNamespace));
  }

  public boolean readToDescendant(String localName, String namespaceURI) throws XMLStreamException {

    if (!this.isStartElement()) {
      return false;
    }
    XMLEvent startEvent = this.presentEvent;
    XMLEvent event = this.presentEvent;
    do {
      if (event.isStartElement()) {
        QName qEName = event.asStartElement().getName();
        if (qEName.getLocalPart().equals(localName) &&
            qEName.getNamespaceURI().equals(namespaceURI)) {
          return true;
        }
      }
      event = this.xmlReader.nextEvent();
    } while (!checkEndElement(startEvent, event));

    return false;
  }



  /**
   * Gets a value indicating whether this instance has attributes.
   *
   * @return boolean
   */
  public boolean hasAttributes() {

    if (this.presentEvent.isStartElement()) {
      StartElement startElement = this.presentEvent.asStartElement();
      return startElement.getAttributes().hasNext();
    } else {
      return false;
    }
  }

  /**
   * Gets a value indicating whether current element is empty.
   *
   * @return boolean
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   */
  public boolean isEmptyElement() throws XMLStreamException {
    boolean isPresentStartElement = this.presentEvent.isStartElement();
    boolean isNextEndElement = this.xmlReader.peek().isEndElement();
    return isPresentStartElement && isNextEndElement;
  }

  /**
   * Gets the local name of the current element.
   *
   * @return String
   */
  public String getLocalName() {

    String localName = null;

    if (this.presentEvent.isStartElement()) {
      localName = this.presentEvent.asStartElement().getName()
          .getLocalPart();
    } else {

      localName = this.presentEvent.asEndElement().getName()
          .getLocalPart();
    }
    return localName;
  }

  /**
   * Gets the namespace prefix.
   *
   * @return String
   */
  protected String getNamespacePrefix() {
    if (this.presentEvent.isStartElement()) {
      return this.presentEvent.asStartElement().getName().getPrefix();
    }
    if (this.presentEvent.isEndElement()) {
      return this.presentEvent.asEndElement().getName().getPrefix();
    }
    return null;
  }

  /**
   * Gets the namespace URI.
   *
   * @return String
   */
  protected String getNamespaceUri() {

    String nameSpaceUri = null;
    if (this.presentEvent.isStartElement()) {
      nameSpaceUri = this.presentEvent.asStartElement().getName()
          .getNamespaceURI();
    } else {

      nameSpaceUri = this.presentEvent.asEndElement().getName()
          .getNamespaceURI();
    }
    return nameSpaceUri;
  }

  /**
   * Gets the type of the node.
   *
   * @return XmlNodeType
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   */
  public XmlNodeType getNodeType() throws XMLStreamException {
    XMLEvent event = this.presentEvent;
    XmlNodeType nodeType = new XmlNodeType(event.getEventType());
    return nodeType;
  }

  /**
   * Gets the name of the current element.
   *
   * @return Object
   */
  protected Object getName() {
    String name = null;
    if (this.presentEvent.isStartElement()) {
      name = this.presentEvent.asStartElement().getName().toString();
    } else {

      name = this.presentEvent.asEndElement().getName().toString();
    }
    return name;
  }

  /**
   * Checks is the string is null or empty.
   *
   * @param namespacePrefix the namespace prefix
   * @return true, if is null or empty
   */
  private static boolean isNullOrEmpty(String namespacePrefix) {
    return (namespacePrefix == null || namespacePrefix.isEmpty());

  }

}
