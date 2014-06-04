/**************************************************************************
 * copyright file="EwsServiceMultiResponseXmlReader.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EwsServiceMultiResponseXmlReader.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/***
 * Represents an xml reader used by the ExchangeService to parse multi-response streams, 
 * such as GetStreamingEvents.  
 * 
 * Necessary because the basic EwsServiceXmlReader does not 
 * use normalization (see E14:60369), and in order to turn normalization off, it is 
 * necessary to use an XmlTextReader, which does not allow the ConformanceLevel.Auto that
 * a multi-response stream requires.
 * If ever there comes a time we need to deal with multi-response streams with user-generated
 * content, we will need to tackle that parsing problem separately.
 */
 class EwsServiceMultiResponseXmlReader extends EwsServiceXmlReader {
	 
	/**
	 *  Initializes a new instance of the 
	 *  EwsServiceMultiResponseXmlReader class.
	 * @param stream The stream.
	 * @param service The service.
	 * @throws Exception 
	 */
     private EwsServiceMultiResponseXmlReader(InputStream stream,
    		 ExchangeService service) throws Exception   {
    	 super(stream, service);
     }     
     
     /**
      * Creates a new instance of the EwsServiceMultiResponseXmlReader class.
      * @param stream The stream.
      * @param service The service.
      * @return an instance of EwsServiceMultiResponseXmlReader 
      * wrapped around the input stream.
     * @throws Exception 
      */
     protected static EwsServiceMultiResponseXmlReader create(InputStream stream, 
    		 ExchangeService service) throws Exception {
         EwsServiceMultiResponseXmlReader reader = 
        	 new EwsServiceMultiResponseXmlReader(stream, service);
         return reader;
     }
     
	 /**
	  * Creates the XML reader.
	  * @param stream The stream.
	  * @return An XML reader to use.
	 * @throws javax.xml.stream.XMLStreamException
	  */	 
	 private static XMLEventReader createXmlReader(InputStream stream) 
	 throws XMLStreamException {
		 
		 // E14:240522 The ProhibitDtd property is used to indicate whether XmlReader should process DTDs or not. By default, 
         // it will do so. EWS doesn't use DTD references so we want to turn this off. Also, the XmlResolver property is
         // set to an instance of XmlUrlResolver by default. We don't want XmlTextReader to try to resolve this DTD reference 
         // so we disable the XmlResolver as well.
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStreamReader isr = new InputStreamReader (stream);  
	      	  BufferedReader in = new BufferedReader (isr);  
			return inputFactory.createXMLEventReader(in);    
		}
	 
	 
	 /**
	  * Initializes the XML reader.
	  * @param stream The stream.
	  * An XML reader to use.
	 * @throws Exception 
	  */
	 @Override
	 protected  XMLEventReader initializeXmlReader(InputStream stream) 
	 throws Exception {
         return createXmlReader(stream);
     }

}
