/**************************************************************************
 * copyright file="ServiceObjectDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceObjectDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * 
 *
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface ServiceObjectDefinition.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ServiceObjectDefinition {

	/**
	 * The name of the XML element.
	 * 
	 * @return the string
	 */
	String xmlElementName();

	/**
	 * True if this ServiceObject can be returned by the server as an object,
	 * false otherwise.
	 * 
	 * @return true, if successful
	 */
	boolean returnedByServer() default true;
}
