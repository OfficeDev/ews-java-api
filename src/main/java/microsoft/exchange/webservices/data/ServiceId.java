/**************************************************************************
 * copyright file="ServiceId.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceId.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the Id of an Exchange object.
 * 
 */
public abstract class ServiceId extends ComplexProperty {

	/** The change key. */
	private String changeKey;

	/** The unique id. */
	private String uniqueId;

	/**
	 * * Initializes a new instance.
	 */
	protected ServiceId() {
		super();
	}

	/**
	 * * Initializes a new instance.
	 * 
	 * @param uniqueId
	 *            The unique id.
	 * @throws Exception
	 *             the exception
	 */
	protected ServiceId(String uniqueId) throws Exception {
		this();
		EwsUtilities.validateParam(uniqueId, "uniqueId");
		this.uniqueId = uniqueId;
	}

	/**
	 * * Read attributes from XML.
	 * 
	 * @param reader
	 *            The reader.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readAttributesFromXml(EwsServiceXmlReader reader)
			throws Exception {
		this.uniqueId = reader.readAttributeValue(XmlAttributeNames.Id);
		this.changeKey = reader.readAttributeValue(XmlAttributeNames.ChangeKey);

	}

	/**
	 * * Writes attributes to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeAttributeValue(XmlAttributeNames.Id, this.getUniqueId());
		writer.writeAttributeValue(XmlAttributeNames.ChangeKey, this
				.getChangeKey());
	}

	/***
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	protected abstract String getXmlElementName();

	/**
	 * * Writes to XML.
	 * 
	 * @param writer
	 *            The writer.
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
		this.writeToXml(writer, this.getXmlElementName());
	}

	/***
	 * Assigns from existing id.
	 * 
	 * @param source
	 *            The source.
	 */
	protected void assign(ServiceId source) {
		this.uniqueId = source.getUniqueId();
		this.changeKey = source.getChangeKey();
	}

	/***
	 * True if this instance is valid, false otherthise.
	 * 
	 * @return true if this instance is valid; otherwise,false
	 */
	protected boolean isValid() {
		return (null != this.uniqueId && !this.uniqueId.isEmpty());
	}

	/***
	 * Gets the unique Id of the Exchange object.
	 * 
	 * @return unique Id of the Exchange object.
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/***
	 * Sets the unique Id of the Exchange object.
	 * 
	 * @param uniqueId
	 *            unique Id of the Exchange object.
	 */
	protected void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	/***
	 * Gets the change key associated with the Exchange object. The change key
	 * represents the version of the associated item or folder.
	 * 
	 * @return change key associated with the Exchange object.
	 */
	public String getChangeKey() {
		return changeKey;
	}

	/***
	 * Sets the change key associated with the Exchange object. The change key
	 * represents the version of the associated item or folder.
	 * 
	 * @param changeKey
	 *            change key associated with the Exchange object.
	 */
	protected void setChangeKey(String changeKey) {
		this.changeKey = changeKey;
	}

	/**
	 * * Determines whether two ServiceId instances are equal (including
	 * ChangeKeys).
	 * 
	 * @param other
	 *            The ServiceId to compare with the current ServiceId.
	 * @return true if equal otherwise false.
	 */
	public boolean sameIdAndChangeKey(ServiceId other) {
		if (this.equals(other)) {
			return ((this.getChangeKey() == null) && 
					(other.getChangeKey() == null)) || 
					this.getChangeKey().equals(other.getChangeKey());
		} else {
			return false;
		}
	}

	/***
	 * Determines whether the specified instance is equal to the current
	 * instance. We do not consider the ChangeKey for ServiceId.Equals.
	 * 
	 * @param obj
	 *            The object to compare with the current instance
	 * @return true if the specified object is equal to the current instance,
	 *         otherwise, false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		} else {
			if (!(obj instanceof ServiceId)) {
				return false;
			} else {
				ServiceId other = (ServiceId) obj;
				if (!(this.isValid() && other.isValid())) {
					return false;
				} else {
					return this.getUniqueId().equals(other.getUniqueId());
				}
			}
		}
	}

	/***
	 * Serves as a hash function for a particular type. We do not consider the
	 * change key in the hash code computation.
	 * 
	 * @return A hash code for the current
	 */
	@Override
	public int hashCode() {
		return this.isValid() ? this.getUniqueId().hashCode() : super
				.hashCode();
	}

	/**
	 * * Returns a string that represents the current instance.
	 * 
	 * @return A string that represents the current instance.
	 */
	@Override
	public String toString() {
		return (this.uniqueId == null) ? "" : this.uniqueId;
	}
}
