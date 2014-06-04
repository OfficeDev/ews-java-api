/**************************************************************************
 * copyright file="EwsServiceXmlWriter.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EwsServiceXmlWriter.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/**
 * * Stax based XML Writer implementation.
 */
class EwsServiceXmlWriter implements IDisposable {

	/** The is disposed. */
	private boolean isDisposed;

	/** The service. */
	private ExchangeServiceBase service;

	/** The xml writer. */
	private XMLStreamWriter xmlWriter;

	/** The is time zone header emitted. */
	private boolean isTimeZoneHeaderEmitted;

	/** The Buffer size. */
	private static final int BufferSize = 4096;
	
	/**The  requireWSSecurityUtilityNamespace **/
	  
	protected boolean requireWSSecurityUtilityNamespace;

	/**
	 * * Initializes a new instance.
	 * 
	 * @param service
	 *            The service.
	 * @param stream
	 *            The stream.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 */
	protected EwsServiceXmlWriter(ExchangeServiceBase service, 
			OutputStream stream) throws XMLStreamException {
		this.service = service;
		XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
		xmlWriter = xmlof.createXMLStreamWriter(stream, "utf-8");

	}

	/**
	 * * Try to convert object to a string.
	 * 
	 * @param value
	 *            The value.
	 * @param str
	 *            the str
	 * @return True if object was converted, false otherwise. A null object will
	 *         be "successfully" converted to a null string.
	 */
	protected boolean tryConvertObjectToString(Object value,
			OutParam<String> str) {
		boolean converted = true;
		str.setParam(null);
		if (value != null) {
			if (value.getClass().isEnum()) {
				str.setParam(EwsUtilities.serializeEnum(value));
			} else if (value.getClass().equals(Boolean.class)) {
				str.setParam(EwsUtilities.boolToXSBool((Boolean)value));
			} else if (value instanceof Date) {
				str
				.setParam(this.service
						.convertDateTimeToUniversalDateTimeString(
								(Date)value));
			} else if (value.getClass().isPrimitive()) {
				str.setParam(value.toString());
			} else if (value instanceof String) {
				str.setParam(value.toString());
			} else if (value instanceof ISearchStringProvider) {
				ISearchStringProvider searchStringProvider = 
					(ISearchStringProvider)value;
				str.setParam(searchStringProvider.getSearchString());
			} else if (value instanceof Integer) {
				str.setParam(value.toString());
			} else {
				converted = false;
			}
		}
		return converted;
	}

	/***
	 * Performs application-defined tasks associated with freeing, releasing, or
	 * resetting unmanaged resources.
	 */
	@Override
	public void dispose() {
		if (!this.isDisposed) {
			try {
				this.xmlWriter.close();
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
			this.isDisposed = true;
		}
	}

	/**
	 * * Flushes this instance.
	 * 
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 */
	public void flush() throws XMLStreamException {
		this.xmlWriter.flush();
	}

	/**
	 * * Writes the start element.
	 * 
	 * @param xmlNamespace
	 *            The XML namespace.
	 * @param localName
	 *            The local name of the element.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 */
	public void writeStartElement(XmlNamespace xmlNamespace, String localName)
	throws XMLStreamException {
		String strPrefix = EwsUtilities.getNamespacePrefix(xmlNamespace);
		String strNameSpace = EwsUtilities.getNamespaceUri(xmlNamespace);
		this.xmlWriter.writeStartElement(strPrefix, localName, strNameSpace);
	}

	/**
	 * * Writes the end element.
	 * 
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 */
	public void writeEndElement() throws XMLStreamException {
		this.xmlWriter.writeEndElement();
	}

	/**
	 * * Writes the attribute value.
	 * 
	 * @param localName
	 *            The local name of the attribute.
	 * @param value
	 *            The value.
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	public void writeAttributeValue(String localName, Object value)
	throws ServiceXmlSerializationException {
		this.writeAttributeValue(localName, 
				false /* alwaysWriteEmptyString */, value);		
	}

	/**
	 * Writes the attribute value.  Optionally emits empty string values.	 
	 * @param localName The local name of the attribute.	
	 * @param alwaysWriteEmptyString Always emit the empty string as the value.	
	 * @param value The value.	
	 * @throws ServiceXmlSerializationException 
	 */	
	public void writeAttributeValue(String localName,
			boolean alwaysWriteEmptyString,
			Object value) throws ServiceXmlSerializationException {
		OutParam<String> stringOut = new OutParam<String>();
		String stringValue = null;
		if (this.tryConvertObjectToString(value, stringOut)) {
			stringValue = stringOut.getParam();
			if ((null != stringValue) && (alwaysWriteEmptyString || (stringValue.length() != 0))) {
				this.writeAttributeString(localName, stringValue);
			}
		} else {
			throw new ServiceXmlSerializationException(String.format(
					Strings.AttributeValueCannotBeSerialized, value.getClass()
					.getName(), localName));
		}
	}

	/**
	 * * Writes the attribute value.
	 * 
	 * @param namespacePrefix
	 *            The namespace prefix.
	 * @param localName
	 *            The local name of the attribute.
	 * @param value
	 *            The value.
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	public void writeAttributeValue(String namespacePrefix, String localName,
			Object value) throws ServiceXmlSerializationException {
		OutParam<String> stringOut = new OutParam<String>();
		String stringValue = null;
		if (this.tryConvertObjectToString(value, stringOut)) {
			stringValue = stringOut.getParam();
			if (null != stringValue && !stringValue.isEmpty()) {
				this.writeAttributeString(namespacePrefix, localName,
						stringValue);
			}
		} else {
			throw new ServiceXmlSerializationException(String.format(
					Strings.AttributeValueCannotBeSerialized, value.getClass()
					.getName(), localName));
		}
	}

	/***
	 * Writes the attribute value.
	 * 
	 * @param localName
	 *            The local name of the attribute.
	 * @param stringValue
	 *            The string value.
	 * @throws ServiceXmlSerializationException
	 *             Thrown if string value isn't valid for XML.
	 */
	protected void writeAttributeString(String localName, String stringValue)
	throws ServiceXmlSerializationException {
		try {
			this.xmlWriter.writeAttribute(localName, stringValue);
		} catch (XMLStreamException e) {
			// Bug E14:65046: XmlTextWriter will throw ArgumentException 
			//if string includes invalid characters.
			throw new ServiceXmlSerializationException(String.format(
					Strings.InvalidAttributeValue, stringValue, localName), e);
		}
	}

	/***
	 * Writes the attribute value.
	 * 
	 * @param namespacePrefix
	 *            The namespace prefix.
	 * @param localName
	 *            The local name of the attribute.
	 * @param stringValue
	 *            The string value.
	 * @throws ServiceXmlSerializationException
	 *             Thrown if string value isn't valid for XML.
	 */
	protected void writeAttributeString(String namespacePrefix,
			String localName, String stringValue)
	throws ServiceXmlSerializationException {
		try {
			this.xmlWriter.writeAttribute(namespacePrefix, "", localName,
					stringValue);
		} catch (XMLStreamException e) {
			// Bug E14:65046: XmlTextWriter will throw ArgumentException 
			//if string includes invalid characters.
			throw new ServiceXmlSerializationException(String.format(
					Strings.InvalidAttributeValue, stringValue, localName), e);
		}
	}

	/***
	 * Writes string value.
	 * 
	 * @param value
	 *            The value.
	 * @param name
	 *            Element name (used for error handling)
	 * @throws ServiceXmlSerializationException
	 *             Thrown if string value isn't valid for XML.
	 */
	public void writeValue(String value, String name)
	throws ServiceXmlSerializationException {
		try {
			this.xmlWriter.writeCharacters(value);
		} catch (XMLStreamException e) {
			// Bug E14:65046: XmlTextWriter will throw ArgumentException
			//if string includes invalid characters.
			throw new ServiceXmlSerializationException(String.format(
					Strings.InvalidElementStringValue, value, name), e);
		}
	}

	/**
	 * * Writes the element value.
	 * 
	 * @param xmlNamespace
	 *            The XML namespace.
	 * @param localName
	 *            The local name of the element.
	 * @param displayName
	 *            The name that should appear in the exception message when the
	 *            value can not be serialized.
	 * @param value
	 *            The value.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeElementValue(XmlNamespace xmlNamespace,
			String localName, String displayName, Object value)
	throws XMLStreamException, ServiceXmlSerializationException {
		String stringValue = null;
		OutParam<String> strOut = new OutParam<String>();
		
		if (this.tryConvertObjectToString(value, strOut)) {
			stringValue = strOut.getParam();
			if (null != stringValue) {
				// allow an empty string to create an empty element (like <Value
				// />).
				this.writeStartElement(xmlNamespace, localName);
				this.writeValue(stringValue, displayName);
				this.writeEndElement();
			}
		} else {
			throw new ServiceXmlSerializationException(String.format(
					Strings.ElementValueCannotBeSerialized, value.getClass()
					.getName(), localName));
		}
	}

	public void writeNode(Node xmlNode) throws XMLStreamException {
		if (xmlNode != null) {
			writeNode(xmlNode,this.xmlWriter);
			//this.xmlWriter.writeCharacters(xmlNode.);
			//this.xmlWriter.writeDTD(xmlNode);
			//xmlNode.WriteTo(this.xmlWriter);
		}
	}

	/**
	 * 
	 * @param xmlNode
	 * @param xmlStreamWriter
	 * @throws javax.xml.stream.XMLStreamException
	 */
	public static void writeNode(Node xmlNode, XMLStreamWriter xmlStreamWriter)
			throws XMLStreamException {
		if (xmlNode instanceof Element) {
			addElement((Element) xmlNode, xmlStreamWriter);
		} else if (xmlNode instanceof Text) {
			xmlStreamWriter.writeCharacters(((Text) xmlNode).getNodeValue());
		} else if (xmlNode instanceof CDATASection) {
			xmlStreamWriter.writeCData(((CDATASection) xmlNode).getData());
		} else if (xmlNode instanceof Comment) {
			xmlStreamWriter.writeComment(((Comment) xmlNode).getData());
		} else if (xmlNode instanceof EntityReference) {
			xmlStreamWriter.writeEntityRef(((EntityReference) xmlNode)
					.getNodeValue());
		} else if (xmlNode instanceof ProcessingInstruction) {
			ProcessingInstruction procInst = (ProcessingInstruction) xmlNode;
			xmlStreamWriter.writeProcessingInstruction(procInst.getTarget(),
					procInst.getData());
		} else if (xmlNode instanceof Document) {
			writeToDocument((Document) xmlNode, xmlStreamWriter);
		}
	}

	/**
	 * 
	 * @param document
	 * @param xmlStreamWriter
	 * @throws javax.xml.stream.XMLStreamException
	 */
	public static void writeToDocument(Document document,
			XMLStreamWriter xmlStreamWriter) throws XMLStreamException {

		xmlStreamWriter.writeStartDocument();
		Element rootElement = document.getDocumentElement();
		addElement(rootElement, xmlStreamWriter);
		xmlStreamWriter.writeEndDocument();
	}

	/**
	 * 
	 * @param element
	 * @param writer
	 * @throws javax.xml.stream.XMLStreamException
	 */
	public static void addElement(Element element, XMLStreamWriter writer)
			throws XMLStreamException {
		String nameSpace = element.getNamespaceURI();
		String prefix = element.getPrefix();
		String localName = element.getLocalName();
		if (prefix == null) {
			prefix = "";
		}
		if (localName == null) {
			localName = element.getNodeName();

			if (localName == null) {
				throw new IllegalStateException(
						"Element's local name cannot be null!");
			}
		}

		String decUri = writer.getNamespaceContext().getNamespaceURI(prefix);
		boolean declareNamespace = decUri == null || !decUri.equals(nameSpace);

		if (nameSpace == null || nameSpace.length() == 0) {
			writer.writeStartElement(localName);
		} else {
			writer.writeStartElement(prefix, localName, nameSpace);
		}

		NamedNodeMap attrs = element.getAttributes();
		for (int i = 0; i < attrs.getLength(); i++) {
			Node attr = attrs.item(i);

			String name = attr.getNodeName();
			String attrPrefix = "";
			int prefixIndex = name.indexOf(':');
			if (prefixIndex != -1) {
				attrPrefix = name.substring(0, prefixIndex);
				name = name.substring(prefixIndex + 1);
			}

			if ("xmlns".equals(attrPrefix)) {
				writer.writeNamespace(name, attr.getNodeValue());
				if (name.equals(prefix)
						&& attr.getNodeValue().equals(nameSpace)) {
					declareNamespace = false;
				}
			} else {
				if ("xmlns".equals(name) && "".equals(attrPrefix)) {
					writer.writeNamespace("", attr.getNodeValue());
					if (attr.getNodeValue().equals(nameSpace)) {
						declareNamespace = false;
					}
				} else {
					writer.writeAttribute(attrPrefix, attr.getNamespaceURI(),
							name, attr.getNodeValue());
				}
			}
		}

		if (declareNamespace) {
			if (nameSpace == null) {
				writer.writeNamespace(prefix, "");
			} else {
				writer.writeNamespace(prefix, nameSpace);
			}
		}

		NodeList nodes = element.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			writeNode(n, writer);
		}


		writer.writeEndElement();

	}



	/**
	 * * Writes the element value.
	 * 
	 * @param xmlNamespace
	 *            The XML namespace.
	 * @param localName
	 *            The local name of the element.
	 * @param value
	 *            The value.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	public void writeElementValue(XmlNamespace xmlNamespace, String localName,
			Object value) throws XMLStreamException,
			ServiceXmlSerializationException {
		this.writeElementValue(xmlNamespace, localName, localName, value);
	}

	/**
	 * * Writes the base64-encoded element value.
	 * 
	 * @param buffer
	 *            The buffer.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 */
	public void writeBase64ElementValue(byte[] buffer)
	throws XMLStreamException {

		String strValue = Base64.encode(buffer);
		this.xmlWriter.writeCharacters(strValue);//Base64.encode(buffer));
	}

	/**
	 * * Writes the base64-encoded element value.
	 * 
	 * @param stream
	 *            The stream.
	 * @throws java.io.IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 */
	public void writeBase64ElementValue(InputStream stream) throws IOException,
	XMLStreamException {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[BufferSize];
		try {
			for (int readNum; (readNum = stream.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			bos.close();
		}
		byte[] bytes = bos.toByteArray();	      
		String strValue = Base64.encode(bytes);
		this.xmlWriter.writeCharacters(strValue);

	}
	/***
	 * Gets the internal XML writer.
	 * 
	 * @return the internal writer
	 */
	public XMLStreamWriter getInternalWriter() {
		return xmlWriter;
	}

	/***
	 * Gets the service.
	 * 
	 * @return The service.
	 */
	public ExchangeServiceBase getService() {
		return service;
	}

	/***
	 *  Gets a value indicating whether the SOAP message need WSSecurity Utility namespace.
	 * 
	 *  
	 */
	public boolean isRequireWSSecurityUtilityNamespace() {
		return requireWSSecurityUtilityNamespace;
	}

	/***
	 *   Sets a value indicating whether the SOAP message need WSSecurity Utility namespace.
	 * 
	 * @param requireWSSecurityUtilityNamespace
	 *            
	 */
	public void setRequireWSSecurityUtilityNamespace(boolean requireWSSecurityUtilityNamespace) {
		this.requireWSSecurityUtilityNamespace = requireWSSecurityUtilityNamespace;
	}
	
	/***
	 * Gets a value indicating whether the time zone SOAP header was emitted
	 * through this writer.
	 * 
	 * @return true if the time zone SOAP header was emitted; otherwise false.
	 */
	public boolean isTimeZoneHeaderEmitted() {
		return isTimeZoneHeaderEmitted;
	}

	/***
	 * Sets a value indicating whether the time zone SOAP header was emitted
	 * through this writer.
	 * 
	 * @param isTimeZoneHeaderEmitted
	 *            true if the time zone SOAP header was emitted; otherwise
	 *            false.
	 */
	public void setTimeZoneHeaderEmitted(boolean isTimeZoneHeaderEmitted) {
		this.isTimeZoneHeaderEmitted = isTimeZoneHeaderEmitted;
	}

	/**
	 * Write start document.
	 * 
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 */
	protected void writeStartDocument() throws XMLStreamException {
		this.xmlWriter.writeStartDocument("utf-8", "1.0");
	}
}
