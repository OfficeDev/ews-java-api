/**************************************************************************
 * copyright file="ResponseObjectsPropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ResponseObjectsPropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents response object property defintion.
 */
public class ResponseObjectsPropertyDefinition extends PropertyDefinition {

	/**
	 * Initializes a new instance of the ResponseObjectsPropertyDefinition
	 * class.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @param uri
	 *            the uri
	 * @param version
	 *            the version
	 */
	protected ResponseObjectsPropertyDefinition(String xmlElementName,
			String uri, ExchangeVersion version) {
		super(xmlElementName, uri, version);

	}

	/**
	 * Loads from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @param propertyBag
	 *            the property bag
	 * @throws Exception
	 *             the exception
	 */
	protected final void loadPropertyValueFromXml(EwsServiceXmlReader reader,
			PropertyBag propertyBag) throws Exception {
		EnumSet<ResponseActions> value = EnumSet.noneOf(ResponseActions.class);
		value.add(ResponseActions.None);

		reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types, this
				.getXmlElement());

		if (!reader.isEmptyElement()) {
			do {
				reader.read();

				if (reader.isStartElement()) {

					if (reader.getLocalName()
							.equals(XmlElementNames.AcceptItem)) {

						value.add(ResponseActions.Accept);
					} else if (reader.getLocalName().equals(
							XmlElementNames.TentativelyAcceptItem)) {

						value.add(ResponseActions.TentativelyAccept);
					} else if (reader.getLocalName().equals(
							XmlElementNames.DeclineItem)) {

						value.add(ResponseActions.Decline);
					} else if (reader.getLocalName().equals(
							XmlElementNames.ReplyToItem)) {

						value.add(ResponseActions.Reply);
					} else if (reader.getLocalName().equals(
							XmlElementNames.ForwardItem)) {

						value.add(ResponseActions.Forward);
					} else if (reader.getLocalName().equals(
							XmlElementNames.ReplyAllToItem)) {

						value.add(ResponseActions.ReplyAll);
					} else if (reader.getLocalName().equals(
							XmlElementNames.CancelCalendarItem)) {

						value.add(ResponseActions.Cancel);
					} else if (reader.getLocalName().equals(
							XmlElementNames.RemoveItem)) {

						value.add(ResponseActions.RemoveFromCalendar);
					} else if (reader.getLocalName().equals(
							XmlElementNames.SuppressReadReceipt)) {

						value.add(ResponseActions.SuppressReadReceipt);
					} else if (reader.getLocalName().equals(
							XmlElementNames.PostReplyItem)) {

						value.add(ResponseActions.PostReply);
					}
				}

			} while (!reader.isEndElement(XmlNamespace.Types, this
					.getXmlElement()));
		} else {
			reader.read();
		}

		propertyBag.setObjectFromPropertyDefinition(this, value);
	}

	/**
	 * Writes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @param propertyBag
	 *            the property bag
	 * @param isUpdateOperation
	 *            the is update operation
	 */
	protected void writePropertyValueToXml(EwsServiceXmlWriter writer,
			PropertyBag propertyBag, boolean isUpdateOperation) {
		// ResponseObjects is a read-only property, no need to implement this.
	}

	/**
	 * Gets a value indicating whether this property 
	 * definition is for a nullable type (ref, int?, bool?...).
	 */
	@Override
	protected  boolean isNullable() {
		return false; 
	}

	/**
	 * Gets the property type.
	 */
	@Override
	public Class getType() {
		return ResponseActions.class; 
	}
}
