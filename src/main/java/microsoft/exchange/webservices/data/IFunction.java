/**************************************************************************
 * copyright file="IFunction.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IFunction.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Interface FuncInterface.
 * 
 * @param <T>
 *            the generic type
 * @param <TResult>
 *            the generic type
 */
interface IFunction<T, TResult> {

	/**
	 * Func.
	 * 
	 * @param arg
	 *            the arg
	 * @return the t result
	 */
	TResult func(T arg);
}
