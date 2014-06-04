/**************************************************************************
 * copyright file="ISelfValidate.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ISelfValidate.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Interface ISelfValidate.
 */
interface ISelfValidate {

	/**
	 * Validate.
	 * 
	 * @throws ServiceValidationException
	 *             the service validation exception
	 * @throws Exception
	 *             the exception
	 */
	void validate() throws ServiceValidationException, Exception;
}
