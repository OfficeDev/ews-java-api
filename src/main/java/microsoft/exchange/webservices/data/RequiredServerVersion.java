/**************************************************************************
 * copyright file="RequiredServerVersion.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the RequiredServerVersion.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface RequiredServerVersion.
 */
@Target( { ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@interface RequiredServerVersion {

	/**
	 * Version.
	 * 
	 * @return the exchange version
	 */
	ExchangeVersion version();
}
