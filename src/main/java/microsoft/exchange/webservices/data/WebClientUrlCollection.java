/**************************************************************************
 * copyright file="WebClientUrlCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the WebClientUrlCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;

/**
 * Represents a user setting that is a collection of Exchange web client URLs.
 * 
 */
public final class WebClientUrlCollection {

	/** The urls. */
	private ArrayList<WebClientUrl> urls;

	/**
	 * Initializes a new instance of the <see cref="WebClientUrlCollection"/>
	 * class.
	 */
	protected WebClientUrlCollection() {
		this.urls = new ArrayList<WebClientUrl>();
	}

	/**
	 * Loads instance of WebClientUrlCollection from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @return the web client url collection
	 * @throws Exception
	 *             the exception
	 */
	protected static WebClientUrlCollection loadFromXml(EwsXmlReader reader)
			throws Exception {
		WebClientUrlCollection instance = new WebClientUrlCollection();

		do {
			reader.read();

			if ((reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) &&
					 (reader.getLocalName()
							.equals(XmlElementNames.WebClientUrl))) {
				instance.getUrls().add(WebClientUrl.loadFromXml(reader));
			}
		} while (!reader.isEndElement(XmlNamespace.Autodiscover,
				XmlElementNames.WebClientUrls));

		return instance;
	}

	/**
	 * Gets the URLs.
	 * 
	 * @return the urls
	 */
	public ArrayList<WebClientUrl> getUrls() {
		return this.urls;

	}
}
