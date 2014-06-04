/**************************************************************************
 * copyright file="IFunctionDelegate.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IFunctionDelegate.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.xml.stream.XMLStreamException;

/**
 * The Interface FuncDelegateInterface.
 * 
 * @param <T1>
 *            the generic type
 * @param <T2>
 *            the generic type
 * @param <T3>
 *            the generic type
 * @param <TResult>
 *            the generic type
 */
interface IFunctionDelegate<T1 extends List, T2 extends List,
		T3 extends ExchangeVersion, T4 extends URI, TResult> {

	/**
	 * Func.
	 * 
	 * @param arg1
	 *            the arg1
	 * @param arg2
	 *            the arg2
	 * @param arg3
	 *            the arg3
	 * @return the t result
	 * @throws AutodiscoverLocalException
	 *             the autodiscover local exception
	 * @throws javax.xml.stream.XMLStreamException
	 *             the xML stream exception
	 * @throws java.io.IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ServiceLocalException
	 *             the service local exception
	 * @throws Exception
	 *             the exception
	 */
    TResult func(T1 arg1, T2 arg2, T3 arg3, T4 arg4)
        throws AutodiscoverLocalException, XMLStreamException, IOException,
			ServiceLocalException, Exception;

}
