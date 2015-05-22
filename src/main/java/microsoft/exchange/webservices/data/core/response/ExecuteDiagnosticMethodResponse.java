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

package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.security.XmlNodeType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import java.util.Iterator;


/**
 * Represents the response to a ExecuteDiagnosticMethod operation
 */
public final class ExecuteDiagnosticMethodResponse extends ServiceResponse {


  /**
   * Initializes a new instance of the ExecuteDiagnosticMethodResponse class.
   *
   * @param service The service
   */
  public ExecuteDiagnosticMethodResponse(ExchangeService service) {
    super();
    EwsUtilities.ewsAssert(service != null, "ExecuteDiagnosticMethodResponse.ctor", "service is null");
  }

  /**
   * Reads response elements from XML.
   *
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

      if (xmleve.getEventType() == XmlNodeType.END_ELEMENT) {
        Node node = currentElement.getParentNode();
        if (node instanceof Document) {
          currentElement = ((Document) node).getDocumentElement();
        } else {
          currentElement = (Element) currentElement.getParentNode();
        }
      }

      if (xmleve.getEventType() == XmlNodeType.START_ELEMENT) {
        // startElement((StartElement) xmleve,doc);
        StartElement ele = (StartElement) xmleve;
        Element element = null;
        element = document.createElementNS(ele.getName()
            .getNamespaceURI(), ele.getName().getLocalPart());

        Iterator<Attribute> ite = ele.getAttributes();

        while (ite.hasNext()) {
          Attribute attr = ite.next();
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
  public Document getReturnValue() {
    return returnValue;
  }

  /**
   * Sets the return value.
   */
  private void setReturnValue(Document value) {
    returnValue = value;
  }
}
