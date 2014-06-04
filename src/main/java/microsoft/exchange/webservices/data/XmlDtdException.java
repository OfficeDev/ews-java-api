/**************************************************************************
 * copyright file="XmlDtdException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the XmlDtdException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Exception class for banned xml parsing
 *
 */
class XmlDtdException extends XmlException {
	/**
	 * Gets the xml exception message.
	 */

@Override
    public String getMessage()
    {
       return "For security reasons DTD is prohibited in this XML document.";
    }
}
