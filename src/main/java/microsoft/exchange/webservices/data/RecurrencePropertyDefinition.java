/**************************************************************************
 * copyright file="RecurrencePropertyDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the RecurrencePropertyDefinition.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/***
 * Represenrs recurrence property definition.
 */
public class RecurrencePropertyDefinition extends PropertyDefinition {

	/**
	 * Initializes a new instance of the RecurrencePropertyDefinition class.
	 * 
	 * @param xmlElementName
	 *            the xml element name
	 * @param uri
	 *            the uri
	 * @param flags
	 *            the flags
	 * @param version
	 *            the version
	 */
	protected RecurrencePropertyDefinition(String xmlElementName, String uri,
			EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {

		super(xmlElementName, uri, flags, version);

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
	protected void loadPropertyValueFromXml(EwsServiceXmlReader reader,
			PropertyBag propertyBag) throws Exception {
		reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types,
				XmlElementNames.Recurrence);

		Recurrence recurrence = null;

		reader.read(new XMLNodeType(XMLNodeType.START_ELEMENT)); // This is the
		// pattern
		// element

		if (reader.getLocalName().equals(
				XmlElementNames.RelativeYearlyRecurrence)) {

			recurrence = new Recurrence.RelativeYearlyPattern();
		} else if (reader.getLocalName().equals(
				XmlElementNames.AbsoluteYearlyRecurrence)) {

			recurrence = new Recurrence.YearlyPattern();
		} else if (reader.getLocalName().equals(
				XmlElementNames.RelativeMonthlyRecurrence)) {

			recurrence = new Recurrence.RelativeMonthlyPattern();
		} else if (reader.getLocalName().equals(
				XmlElementNames.AbsoluteMonthlyRecurrence)) {

			recurrence = new Recurrence.MonthlyPattern();
		} else if (reader.getLocalName()
				.equals(XmlElementNames.DailyRecurrence)) {

			recurrence = new Recurrence.DailyPattern();
		} else if (reader.getLocalName().equals(
				XmlElementNames.DailyRegeneration)) {

			recurrence = new Recurrence.DailyRegenerationPattern();
		} else if (reader.getLocalName().equals(
				XmlElementNames.WeeklyRecurrence)) {

			recurrence = new Recurrence.WeeklyPattern();
		} else if (reader.getLocalName().equals(
				XmlElementNames.WeeklyRegeneration)) {

			recurrence = new Recurrence.WeeklyRegenerationPattern();
		} else if (reader.getLocalName().equals(
				XmlElementNames.MonthlyRegeneration)) {

			recurrence = new Recurrence.MonthlyRegenerationPattern();
		} else if (reader.getLocalName().equals(
				XmlElementNames.YearlyRegeneration)) {

			recurrence = new Recurrence.YearlyRegenerationPattern();
		} else {

			throw new ServiceXmlDeserializationException(String.format(
					Strings.InvalidRecurrencePattern, reader.getLocalName()));
		}

		recurrence.loadFromXml(reader, reader.getLocalName());

		reader.read(new XMLNodeType(XMLNodeType.START_ELEMENT)); // This is the
		// range
		// element

		RecurrenceRange range;

		if (reader.getLocalName().equals(XmlElementNames.NoEndRecurrence)) {

			range = new NoEndRecurrenceRange();
		} else if (reader.getLocalName().equals(
				XmlElementNames.EndDateRecurrence)) {

			range = new EndDateRecurrenceRange();
		} else if (reader.getLocalName().equals(
				XmlElementNames.NumberedRecurrence)) {

			range = new NumberedRecurrenceRange();
		} else {
			throw new ServiceXmlDeserializationException(String.format(
					Strings.InvalidRecurrenceRange, reader.getLocalName()));
		}

		range.loadFromXml(reader, reader.getLocalName());
		range.setupRecurrence(recurrence);

		reader.readEndElementIfNecessary(XmlNamespace.Types,
				XmlElementNames.Recurrence);

		propertyBag.setObjectFromPropertyDefinition(this, recurrence);
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
	 * @throws Exception
	 *             the exception
	 */
	protected void writePropertyValueToXml(EwsServiceXmlWriter writer,
			PropertyBag propertyBag, boolean isUpdateOperation)
	throws Exception {
		Recurrence value = (Recurrence)propertyBag
		.getObjectFromPropertyDefinition(this);

		if (value != null) {
			value.writeToXml(writer, XmlElementNames.Recurrence);
		}
	}

	/**
	 * Gets the property type.
	 */
	@Override
	public Class getType() {
		return Recurrence.class; 
	}

}
