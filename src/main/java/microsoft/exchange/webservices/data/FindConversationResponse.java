/**************************************************************************
 * copyright file="FindConversationResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FindConversationResponse class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/***
 * Represents the response to a Conversation search operation.
 */
 final class FindConversationResponse extends ServiceResponse {
	List<Conversation> conversations = new ArrayList<Conversation>();

	/***
	 * Initializes a new instance of the FindConversationResponse class.
	 */       
	protected FindConversationResponse() {
		super();
	}

	/***
	 * Gets the results of the operation.
	 */       
	protected Collection<Conversation> getConversations() {

		return this.conversations;

	}

	/***
	 * Read Conversations from XML.
	 * @param reader The reader.
	 * @throws Exception 
	 */       
	@Override
	protected  void readElementsFromXml(EwsServiceXmlReader reader)
	throws Exception {
		EwsUtilities.EwsAssert(
				conversations != null,
				"FindConversationResponse.ReadElementsFromXml",
		"conversations is null.");

		reader.readStartElement(XmlNamespace.Messages, 
				XmlElementNames.Conversations);
		if (!reader.isEmptyElement()) {
			do {
				reader.read();

				if (reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) {
					Conversation item = EwsUtilities.
					createEwsObjectFromXmlElementName(Conversation.class,
							reader.getService(),reader.getLocalName());

					if (item == null) {
						reader.skipCurrentElement();
					}
					else {
						item.loadFromXml(
								reader,
								true, /* clearPropertyBag */
								null,
								false  /* summaryPropertiesOnly */);

						conversations.add(item);
					}
				}
			}
			while (!reader.isEndElement(XmlNamespace.Messages,
					XmlElementNames.Conversations));
		}
	}
}
