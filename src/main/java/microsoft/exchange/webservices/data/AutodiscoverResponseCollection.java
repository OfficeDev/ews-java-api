/**************************************************************************
 * copyright file="AutodiscoverResponseCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AutodiscoverResponseCollection.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of responses to a call to the Autodiscover service.
 * 
 * @param <TResponse>
 *            The type of the responses in the collection.
 */
public abstract class AutodiscoverResponseCollection
<TResponse extends AutodiscoverResponse>
		extends AutodiscoverResponse implements Iterable<TResponse> {

	/** The responses. */
	private List<TResponse> responses;

	/**
	 * * Initializes a new instance of the AutodiscoverResponseCollection class.
	 */
	protected AutodiscoverResponseCollection() {
		this.responses = new ArrayList<TResponse>();
	}

	/**
	 * * Gets the number of responses in the collection.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return this.responses.size();
	}

	/**
	 * * Gets the response at the specified index.
	 * 
	 * @param index
	 *            the index
	 * @return the t response at index
	 */
	public TResponse getTResponseAtIndex(int index) {
		return this.responses.get(index);
	}

	/**
	 * Gets the responses.
	 * 
	 * @return the responses
	 */
	protected List<TResponse> getResponses() {
		return responses;
	}

	/**
	 * * Loads response from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @param endElementName
	 *            End element name.
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsXmlReader reader, String endElementName)
			throws Exception {
		do {
			reader.read();

			if (reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) {
				if (reader.getLocalName().equals(
						this.getResponseCollectionXmlElementName())) {
					this.loadResponseCollectionFromXml(reader);
				} else {
					super.loadFromXml(reader, endElementName);
				}
			}
		} while (!reader
				.isEndElement(XmlNamespace.Autodiscover, endElementName));
	}

	/**
	 * * Loads response from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	private void loadResponseCollectionFromXml(EwsXmlReader reader)
			throws Exception {
		if (!reader.isEmptyElement()) {
			do {
				reader.read();
				if ((reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) &&
						 (reader.getLocalName().equals(this
								.getResponseInstanceXmlElementName()))) {
					TResponse response = this.createResponseInstance();
					response.loadFromXml(reader, this
							.getResponseInstanceXmlElementName());
					this.responses.add(response);
				}
			} while (!reader.isEndElement(XmlNamespace.Autodiscover, this
					.getResponseCollectionXmlElementName()));
		}else {
			reader.read();
		}
	}

	/***
	 * Gets the name of the response collection XML element.
	 * 
	 * @return Response collection XMl element name.
	 */
	protected abstract String getResponseCollectionXmlElementName();

	/***
	 * Gets the name of the response instance XML element.
	 * 
	 * @return Response collection XMl element name.
	 */
	protected abstract String getResponseInstanceXmlElementName();

	/***
	 * Create a response instance.
	 * 
	 * @return TResponse.
	 */
	protected abstract TResponse createResponseInstance();

	/***
	 * Gets an Iterator that iterates through the elements of the collection.
	 * 
	 * @return An Iterator for the collection.
	 */
	public Iterator<TResponse> iterator() {
		return this.responses.iterator();
	}
}
