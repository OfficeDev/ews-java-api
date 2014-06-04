/**************************************************************************
 * copyright file="TimeZoneDefinition.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TimeZoneDefinition.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Represents a time zone as defined by the EWS schema.
 */
public class TimeZoneDefinition extends ComplexProperty implements Comparator<TimeZoneTransition>{
    /** Prefix for generated ids.*/   
    private static String NoIdPrefix = "NoId_";

	/** The Standard period id. */
	protected final String StandardPeriodId = "Std";
	
	/** The Standard period name. */
	protected final String StandardPeriodName = "Standard";
	
	/** The Daylight period id. */
	protected final String DaylightPeriodId = "Dlt";
	
	/** The Daylight period name. */
	protected final String DaylightPeriodName = "Daylight";

	/** The name. */
	protected String name;

	/** The id. */
	protected String id;

	/** The periods. */
	private Map<String, TimeZonePeriod> periods = 
		new HashMap<String, TimeZonePeriod>();

	/** The transition groups. */
	private Map<String, TimeZoneTransitionGroup> transitionGroups = 
		new HashMap<String, TimeZoneTransitionGroup>();

	/** The transitions. */
	private List<TimeZoneTransition> transitions =
		new ArrayList<TimeZoneTransition>();

	/**
	 * Compares the transitions.
	 * 
	 * @param x
	 *            The first transition.
	 * @param y
	 *            The second transition.
	 * @return A negative number if x is less than y, 0 if x and y are equal, a
	 *         positive number if x is greater than y.
	 */
	@Override
	public int compare(TimeZoneTransition x, TimeZoneTransition y) {
		if (x == y) {
			return 0;
		} else if (x instanceof TimeZoneTransition) {
			return -1;
		} else if (y instanceof TimeZoneTransition) {
			return 1;
		} else {
			AbsoluteDateTransition firstTransition = (AbsoluteDateTransition)x;
			AbsoluteDateTransition secondTransition = (AbsoluteDateTransition)y;

			return firstTransition.getDateTime().compareTo(
					secondTransition.getDateTime());
		}
	}

	/**
	 * Initializes a new instance of the TimeZoneDefinition class.
	 */
	protected TimeZoneDefinition() {
		super();
	}


	/**
	 * Adds a transition group with a single transition to the specified period.
	 * 
	 * @param timeZonePeriod
	 *            the time zone period
	 * @return A TimeZoneTransitionGroup.
	 */
	private TimeZoneTransitionGroup createTransitionGroupToPeriod(
			TimeZonePeriod timeZonePeriod) {
		TimeZoneTransition transitionToPeriod = new TimeZoneTransition(this,
				timeZonePeriod);

		TimeZoneTransitionGroup transitionGroup = new TimeZoneTransitionGroup(
				this, String.valueOf(this.transitionGroups.size()));
		transitionGroup.getTransitions().add(transitionToPeriod);
		this.transitionGroups.put(transitionGroup.getId(), transitionGroup);
		return transitionGroup;
	}

	/**
	 * Reads the attributes from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readAttributesFromXml(EwsServiceXmlReader reader)
			throws Exception {
		this.name = reader.readAttributeValue(XmlAttributeNames.Name);
		this.id = reader.readAttributeValue(XmlAttributeNames.Id);
		
		// E14:319057 -- EWS can return a TimeZone definition with no Id. Generate a new Id in this case.
		if(this.id == null || this.id.isEmpty()){
			String nameValue = (this.getName() == null || this.
					getName().isEmpty()) ? "" : this.getName();
            this.setId(NoIdPrefix + Math.abs(nameValue.hashCode()));
        }
	}

	/**
	 * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		// The Name attribute is only supported in Exchange 2010 and above.
		if (writer.getService().getRequestedServerVersion() != ExchangeVersion.Exchange2007_SP1) {
			writer.writeAttributeValue(XmlAttributeNames.Name, this.name);
		}

		writer.writeAttributeValue(XmlAttributeNames.Id, this.id);
	}

	/**
	 * Tries to read element from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @return True if element was read.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
			throws Exception {
		if (reader.getLocalName().equals(XmlElementNames.Periods)) {
			do {
				reader.read();
				if (reader.isStartElement(XmlNamespace.Types,
						XmlElementNames.Period)) {
					TimeZonePeriod period = new TimeZonePeriod();
					period.loadFromXml(reader);

					this.periods.put(period.getId(), period);
				}
			} while (!reader.isEndElement(XmlNamespace.Types,
					XmlElementNames.Periods));

			return true;
		} else if (reader.getLocalName().equals(
				XmlElementNames.TransitionsGroups)) {
			do {
				reader.read();
				if (reader.isStartElement(XmlNamespace.Types,
						XmlElementNames.TransitionsGroup)) {
					TimeZoneTransitionGroup transitionGroup =
						new TimeZoneTransitionGroup(
							this);

					transitionGroup.loadFromXml(reader);

					this.transitionGroups.put(transitionGroup.getId(),
							transitionGroup);
				}
			} while (!reader.isEndElement(XmlNamespace.Types,
					XmlElementNames.TransitionsGroups));

			return true;
		} else if (reader.getLocalName().equals(XmlElementNames.Transitions)) {
			do {
				reader.read();
				if (reader.isStartElement()) {
					TimeZoneTransition transition = TimeZoneTransition.create(
							this, reader.getLocalName());

					transition.loadFromXml(reader);

					this.transitions.add(transition);
				}
			} while (!reader.isEndElement(XmlNamespace.Types,
					XmlElementNames.Transitions));

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Loads from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsServiceXmlReader reader) throws Exception {
		this.loadFromXml(reader, XmlElementNames.TimeZoneDefinition);
		 Collections.sort(this.transitions, new TimeZoneDefinition());
	}

	/**
	 * Writes elements to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		// We only emit the full time zone definition against Exchange 2010
		// servers and above.
		if (writer.getService().getRequestedServerVersion() != ExchangeVersion.Exchange2007_SP1) {
			if (this.periods.size() > 0) {
				writer.writeStartElement(XmlNamespace.Types,
						XmlElementNames.Periods);

				Iterator it = this.periods.values().iterator();
				while (it.hasNext()) {
					((TimeZonePeriod) it.next()).writeToXml(writer);
				}

				writer.writeEndElement(); // Periods
			}

			if (this.transitionGroups.size() > 0) {
				writer.writeStartElement(XmlNamespace.Types,
						XmlElementNames.TransitionsGroups);
				for (int i = 0; i < this.transitionGroups.size(); i++) {
					Object key[] = this.transitionGroups.keySet().toArray();
					this.transitionGroups.get(key[i]).writeToXml(writer);
				}
				writer.writeEndElement(); // TransitionGroups
			}

			if (this.transitions.size() > 0) {
				writer.writeStartElement(XmlNamespace.Types,
						XmlElementNames.Transitions);

				for (TimeZoneTransition transition : this.transitions) {
					transition.writeToXml(writer);
				}

				writer.writeEndElement(); // Transitions
			}
		}
	}

	/**
	 * Writes to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
		this.writeToXml(writer, XmlElementNames.TimeZoneDefinition);
	}

	/**
	 * Validates this time zone definition.
	 * 
	 * @throws ServiceLocalException
	 *             the service local exception
	 */
	public void validate() throws ServiceLocalException {
		// The definition must have at least one period, one transition group
		// and one transition,
		// and there must be as many transitions as there are transition groups.
		if (this.periods.size() < 1 || this.transitions.size() < 1
				|| this.transitionGroups.size() < 1
				|| this.transitionGroups.size() != this.transitions.size()) {
			throw new ServiceLocalException(
					Strings.InvalidOrUnsupportedTimeZoneDefinition);
		}

		// The first transition must be of type TimeZoneTransition.
		if (this.transitions.get(0).getClass() != TimeZoneTransition.class) {
			throw new ServiceLocalException(
					Strings.InvalidOrUnsupportedTimeZoneDefinition);
		}

		// All transitions must be to transition groups and be either
		// TimeZoneTransition or
		// AbsoluteDateTransition instances.
		for (TimeZoneTransition transition : this.transitions) {
			Class<?> transitionType = transition.getClass();

			if (transitionType != TimeZoneTransition.class
					&& transitionType != AbsoluteDateTransition.class) {
				throw new ServiceLocalException(
						Strings.InvalidOrUnsupportedTimeZoneDefinition);
			}

			if (transition.getTargetGroup() == null) {
				throw new ServiceLocalException(
						Strings.InvalidOrUnsupportedTimeZoneDefinition);
			}
		}

		// All transition groups must be valid.
		for (TimeZoneTransitionGroup transitionGroup : this.transitionGroups
				.values()) {
			transitionGroup.validate();
		}
	}

	/**
	 * Gets the name of this time zone definition.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the Id of this time zone definition.
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	protected void setId(String id) {
		this.id = id;
	}

	/**
	 * Adds a transition group with a single transition to the specified period.
	 *
	 * @return A TimeZoneTransitionGroup.
	 */
	protected Map<String, TimeZonePeriod> getPeriods() {
		return this.periods;
	}

	/**
	 * Gets the transition groups associated with this time zone definition,
	 * indexed by Id.
	 *
	 * @return the transition groups
	 */
	protected Map<String, TimeZoneTransitionGroup> getTransitionGroups() {
		return this.transitionGroups;
	}

	/***
	 * Writes to XML.
	 * 
	 * @param writer
	 *            accepts EwsServiceXmlWriter
	 * @param xmlElementName
	 *            accepts String
	 * @throws Exception
	 *             throws Exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer, String xmlElementName)
			throws Exception {
		this.writeToXml(writer, this.getNamespace(), xmlElementName);
	}

}
