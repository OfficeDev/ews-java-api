/**************************************************************************
 * copyright file="SafeXmlSchema.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SafeXmlSchema.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.io.InputStream;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;
import  javax.xml.validation.Schema;

/**
 * XmlSchema with protection against DTD parsing in read overloads
 
 */

public class SafeXmlSchema extends Schema{

	@Override
	public Validator newValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidatorHandler newValidatorHandler() {
		// TODO Auto-generated method stub
		return null;
	}
	  /**
       * Reads an XML Schema from the supplied stream.
       * @param stream The supplied data stream.
      * @param validationEventHandler The validation event handler that receives information about the XML Schema syntax errors
      * @return The XmlSchema object representing the XML Schema.
      * @throws javax.xml.stream.XMLStreamException
      */
	public static Schema Read(InputStream stream, ValidationEventHandler validationEventHandler) throws XMLStreamException
    {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

		return (Schema) inputFactory.createXMLEventReader(stream);    
        }
	
	/**
	 * Reads an XML Schema from the supplied TextReader.
	 * @param reader The TextReader containing the XML Schema to read
	 * @param validationEventHandler The validation event handler that receives information about the XML Schema syntax errors.
	 * @return The XmlSchema object representing the XML Schema.
	 * @throws javax.xml.stream.XMLStreamException
	 */
	
    public static Schema Read(XMLStreamReader reader, ValidationEventHandler validationEventHandler) throws XMLStreamException
    {
    
       	XMLInputFactory inputFactory = XMLInputFactory.newInstance();

		return (Schema) inputFactory.createXMLEventReader(reader);    
    }
      
}
