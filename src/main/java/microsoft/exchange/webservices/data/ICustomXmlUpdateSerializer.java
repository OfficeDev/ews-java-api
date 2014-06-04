/**************************************************************************
 * copyright file="ICustomXmlUpdateSerializer.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ICustomXmlUpdateSerializer.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/***
 * Interface defined for properties that produce their own update serialization.
 * 
 */
interface ICustomXmlUpdateSerializer {

	/**
	 * * Writes the update to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @param ewsObject
	 *            The ews object.
	 * @param propertyDefinition
	 *            Property definition.
	 * @return True if property generated serialization.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws ServiceValidationException
	 *             the service validation exception
	 * @throws Exception
	 *             the exception
	 */
	boolean writeSetUpdateToXml(EwsServiceXmlWriter writer,
                                ServiceObject ewsObject, PropertyDefinition propertyDefinition)
			throws XMLStreamException, ServiceXmlSerializationException,
			InstantiationException, IllegalAccessException,
			ServiceValidationException, Exception;

	/**
	 * * Writes the deletion update to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @param ewsObject
	 *            The ews object.
	 * @return True if property generated serialization.
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 * @throws Exception
	 *             the exception
	 */
	boolean writeDeleteUpdateToXml(EwsServiceXmlWriter writer,
                                   ServiceObject ewsObject) throws XMLStreamException,
			ServiceXmlSerializationException, Exception;
}
