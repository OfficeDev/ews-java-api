/**************************************************************************
 * copyright file="RuleError.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the RuleError.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Defines the RuleError class. 
 */
public final class RuleError extends ComplexProperty{

	/** The Rule property. */
	private RuleProperty ruleProperty;

	/** The Rule validation error code.*/
	private RuleErrorCode errorCode;

	/** The Error message.*/
	private String errorMessage;

	/** The Field value.*/
	private String value;

	/** The Initializes a new instance of the RuleError class.*/
	protected RuleError() {
		super();
	}

	/** Gets the property which failed validation.
	 * @return ruleProperty
	 */
	public RuleProperty getRuleProperty() {
		return this.ruleProperty;
	}

	/** Gets the validation error code.
	 * @return ruleProperty
	 */
	public RuleErrorCode getErrorCode() {
		return this.errorCode;
	}

	/** Gets the error message.
	 * @return ruleProperty
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}

	/** Gets the value that failed validation.*/
	public String getValue() {
		return this.value;
	}

	/** Tries to read element from XML.
	 * @param reader The reader
	 * @return True if element was read
	 * @throws Exception 
	 */
	@Override
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
	throws Exception {
		if(reader.getLocalName().equals(XmlElementNames.FieldURI)) {
			this.ruleProperty = reader.readElementValue(RuleProperty.class);
			return true;
		} 
		else if(reader.getLocalName().equals(XmlElementNames.ErrorCode)) {
			this.errorCode = reader.readElementValue(RuleErrorCode.class);
			return true;
		}
		else if(reader.getLocalName().equals(XmlElementNames.ErrorMessage)) {
			this.errorMessage = reader.readElementValue();
			return true;
		}
		else if(reader.getLocalName().equals(XmlElementNames.FieldValue)) {
			this.value = reader.readElementValue();
			return true;
		}
		else {
			return false;
		}
	}
}