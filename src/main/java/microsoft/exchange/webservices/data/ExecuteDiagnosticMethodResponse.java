/**************************************************************************
 * copyright file="ExecuteDiagnosticMethodResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ExecuteDiagnosticMethodResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * Represents the response to a ExecuteDiagnosticMethod operation
 */
final class ExecuteDiagnosticMethodResponse extends ServiceResponse {


	/**
	 * Initializes a new instance of the ExecuteDiagnosticMethodResponse class.
	 * @param service The service
	 */
	protected ExecuteDiagnosticMethodResponse(ExchangeService service) {
		super();
		EwsUtilities.EwsAssert(service != null,
				"ExecuteDiagnosticMethodResponse.ctor",
		"service is null");
	}

	/**
	 * Reads response elements from XML.
	 * @throws Exception 
	 */
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader) 
	throws Exception {
		reader.readStartElement(XmlNamespace.Messages, 
				XmlElementNames.ReturnValue);

		XMLEventReader returnValueReader = reader.getXmlReaderForNode();
		//this.returnValue = (Document) new SafeXmlDocument();
		{
			this.returnValue = retriveDocument(returnValueReader);
		}

		reader.skipCurrentElement();
		reader.readEndElementIfNecessary(XmlNamespace.Messages, 
				XmlElementNames.ReturnValue);
	}


	/**
	 * 
	 * @param xmlEventReader
	 * @return document
	 * @throws javax.xml.parsers.ParserConfigurationException
	 */
	public Document retriveDocument(XMLEventReader xmlEventReader)
			throws ParserConfigurationException {
		DocumentBuilderFactory dbfInstance = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = dbfInstance.newDocumentBuilder();
		Document document = documentBuilder.newDocument();

		Element currentElement = document.getDocumentElement();

		while (xmlEventReader.hasNext()) {
			XMLEvent xmleve = (XMLEvent) xmlEventReader.next();

			if (xmleve.getEventType() == XMLNodeType.END_ELEMENT) {
				Node node = currentElement.getParentNode();
				if (node instanceof Document) {
					currentElement = ((Document) node).getDocumentElement();
				} else {
					currentElement = (Element) currentElement.getParentNode();
				}
			}

			if (xmleve.getEventType() == XMLNodeType.START_ELEMENT) {
				// startElement((StartElement) xmleve,doc);
				StartElement ele = (StartElement) xmleve;
				Element element = null;
				element = document.createElementNS(ele.getName()
						.getNamespaceURI(), ele.getName().getLocalPart());

				Iterator ite = ele.getAttributes();

				while (ite.hasNext()) {
					Attribute attr = (Attribute) ite.next();
					element.setAttribute(attr.getName().getLocalPart(),
							attr.getValue());
				}

				String xmlns = EwsUtilities.WSTrustFebruary2005Namespace;//"http://schemas.xmlsoap.org/wsdl/";
				ite = ele.getNamespaces();
				while (ite.hasNext()) {
					Namespace ns = (Namespace) ite.next();
					String name = ns.getPrefix();
					if (!name.isEmpty()) {
						element.setAttributeNS(xmlns, name,
								ns.getNamespaceURI());
					} else {
						xmlns = ns.getNamespaceURI();
					}
				}

				if (currentElement == null) {
					document.appendChild(element);
				} else {
					currentElement.appendChild(element);
				}

				currentElement = element;
				element.setUserData("location", ele.getLocation(), null);
			}
		}
		return document;
	}

	private Document returnValue;

	/**
	 * Gets the return value.
	 */
	protected Document getReturnValue() {
		return returnValue;
	}

	/**
	 * Sets the return value.
	 */
	private void setReturnValue(Document value) {
		returnValue = value;
	}
}
