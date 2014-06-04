/**************************************************************************
 * copyright file="RulePredicateSizeRange.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the RulePredicateSizeRange class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the minimum and maximum size of a message.
 */
public final class RulePredicateSizeRange extends ComplexProperty {
	/**
	 * Minimum Size.
	 */
	private Integer minimumSize;

	/**
	 * Mamixmum Size.
	 */
	private Integer maximumSize;

	/**
	 * Initializes a new instance of the RulePredicateSizeRange class.
	 */
	protected RulePredicateSizeRange() {
		super();
	}

	/**
	 * Gets or sets the minimum size, in kilobytes. 
	 * If MinimumSize is set to null, no minimum size applies.
	 */
	public Integer getMinimumSize() {

		return this.minimumSize;
	}
	public void setMinimumSize(Integer value) {
		if (this.canSetFieldValue(this.minimumSize, value)) {
			this.minimumSize = value;
			this.changed();
		}}

	/**
	 * Gets or sets the maximum size, in kilobytes. 
	 * If MaximumSize is set to null, no maximum size applies. 
	 */
	public Integer getMaximumSize() {
		return this.maximumSize;
	}
	public void setMaximumSize(Integer value) {
		if (this.canSetFieldValue(this.maximumSize, value)) {
			this.maximumSize = value;
			this.changed();
		}

	}


	/**
	 * Tries to read element from XML.
	 * @param reader The reader.
	 * @return True if element was read.
	 */
	@Override
	protected  boolean tryReadElementFromXml(EwsServiceXmlReader reader) 
	throws Exception {

		if(reader.getLocalName().equalsIgnoreCase(XmlElementNames.MinimumSize)) {
			this.minimumSize = reader.readElementValue(Integer.class);
			return true;
		}

		else if(reader.getLocalName().equalsIgnoreCase(XmlElementNames.MaximumSize)) {
			this.maximumSize = reader.readElementValue(Integer.class);
			return true;
		}

		else {
			return false;
		}

	}

	/**
	 * Writes elements to XML.
	 * @param writer The writer.
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer) 
	throws ServiceXmlSerializationException, XMLStreamException {
		if (this.getMinimumSize() != null) {
			writer.writeElementValue(XmlNamespace.Types,
					XmlElementNames.MinimumSize, this.getMinimumSize());
		}
		if (this.getMaximumSize()!= null) {
			writer.writeElementValue(XmlNamespace.Types, 
					XmlElementNames.MaximumSize, this.getMaximumSize());
		}
	}

	/**
	 * Validates this instance.
	 */
	@Override
	protected void internalValidate() 
	throws ServiceValidationException, Exception {
		super.internalValidate();
		if (this.minimumSize != null &&
				this.maximumSize != null &&
				this.minimumSize > this.maximumSize) {
			throw new ServiceValidationException(
					"MinimumSize cannot be larger than MaximumSize.");
		}
	}
}



