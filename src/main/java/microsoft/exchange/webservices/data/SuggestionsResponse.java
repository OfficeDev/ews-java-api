/**************************************************************************
 * copyright file="SuggestionsResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SuggestionsResponse.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents the base response class to subscription creation operations.
 * 
 */
final class SuggestionsResponse extends ServiceResponse {

	/** The day suggestions. */
	private Collection<Suggestion> daySuggestions = new ArrayList<Suggestion>();

	/**
	 * Initializes a new instance of the SuggestionsResponse class.
	 */
	protected SuggestionsResponse() {
		super();
	}

	/**
	 * Loads the suggested days from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	protected void loadSuggestedDaysFromXml(EwsServiceXmlReader reader)
			throws Exception {
		reader.readStartElement(XmlNamespace.Messages,
				XmlElementNames.SuggestionDayResultArray);

		do {
			reader.read();

			if (reader.isStartElement(XmlNamespace.Types,
					XmlElementNames.SuggestionDayResult)) {
				Suggestion daySuggestion = new Suggestion();

				daySuggestion.loadFromXml(reader, reader.getLocalName());

				this.daySuggestions.add(daySuggestion);
			}
		} while (!reader.isEndElement(XmlNamespace.Messages,
				XmlElementNames.SuggestionDayResultArray));
	}

	/**
	 * Gets a list of suggested days.
	 * 
	 * @return the suggestions
	 */
	protected Collection<Suggestion> getSuggestions() {
		return this.daySuggestions;
	}
}
