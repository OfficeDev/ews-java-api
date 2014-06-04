/**************************************************************************
 * copyright file="EditorBrowsable.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EditorBrowsable.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface EditorBrowsable.
 */
@Target( { ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@interface EditorBrowsable {

	/**
	 * State.
	 * 
	 * @return the editor browsable state
	 */
	EditorBrowsableState state();
}