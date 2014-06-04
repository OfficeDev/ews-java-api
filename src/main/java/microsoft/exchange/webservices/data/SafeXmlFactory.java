/**************************************************************************
 * copyright file="SafeXmlFactory.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SafeXmlFactory.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;


public class SafeXmlFactory {
	public static XMLInputFactory factory = XMLInputFactory.newInstance();
	
	
	public static XMLStreamReader createSafeXmlTextReader( InputStream stream) throws Exception{
		XMLStreamReader xsr =  factory.createXMLStreamReader(stream);
		return xsr;
		
	}
	
 
	public static XMLStreamReader createSafeXmlTextReader(String url )throws Exception {
		FileInputStream fis = new FileInputStream(url);
		XMLStreamReader xtr = factory.createXMLStreamReader(url,fis);
		return xtr;
	}
	
	public static XMLStreamReader createSafeXmlTextReader(XMLStreamReader reader) throws Exception {
		
		 XMLStreamReader xmlr =
			 factory.createXMLStreamReader((Reader)reader);
		 return xmlr;
	                

	}
	
	
	
	
	
	
	

}
