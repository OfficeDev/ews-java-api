/**************************************************************************
 * copyright file="ByteArrayArray.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ByteArrayArray.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Represents an array of byte arrays
 * 
 */
public class ByteArrayArray extends ComplexProperty {
	final static String ItemXmlElementName = "Base64Binary";
	private List<byte[]> content = new ArrayList<byte[]>();

	ByteArrayArray() {
	}

	/**
	 * 
	 * Gets the content of the array of byte arrays
	 */
	public byte[][] getContent() {
		return (byte[][]) this.content.toArray();
	}

	/**
	 * Tries to read element from XML.
	 * 
	 */

	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {

		if (reader.getLocalName().equalsIgnoreCase(
				ByteArrayArray.ItemXmlElementName)) {
			this.content.add(reader.readBase64ElementValue());
			return true;
		} else {
			return false;
		}

	}

	/**
	 * The Writer
	 */

	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		for (byte[] item : this.content) {
			writer.writeStartElement(XmlNamespace.Types,
					ByteArrayArray.ItemXmlElementName);
			writer.writeBase64ElementValue(item);
			writer.writeEndElement();
		}

	}

}
