/**************************************************************************
 * copyright file="IAutodiscoverRedirectionUrl.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IAutodiscoverRedirectionUrl.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines a delegate that is used by the AutodiscoverService to ask whether a
 * redirectionUrl can be used.
 * 
 */
public interface IAutodiscoverRedirectionUrl {

	/**
	 * Autodiscover redirection url validation callback.
	 * 
	 * @param redirectionUrl
	 *            the redirection url
	 * @return true, if successful
	 * @throws microsoft.exchange.webservices.data.AutodiscoverLocalException
	 *             the autodiscover local exception
	 */
    boolean autodiscoverRedirectionUrlValidationCallback(
            String redirectionUrl) throws AutodiscoverLocalException;
}
