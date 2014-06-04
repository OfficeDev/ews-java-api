/**************************************************************************
 * copyright file="AutodiscoverError.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AutodiscoverError.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Defines the AutodiscoverError class.
 * 
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class AutodiscoverError {

	/** The time. */
	private String time;

	/** The id. */
	private String id;

	/** The error code. */
	private int errorCode;

	/** The message. */
	private String message;

	/** The debug data. */
	private String debugData;

	/**
	 * Initializes a new instance of the AutodiscoverError class.
	 */
	private AutodiscoverError() {
	}

	/**
	 * Parses the XML through the specified reader and creates an Autodiscover
	 * error.
	 * 
	 * @param reader
	 *            the reader
	 * @return AutodiscoverError
	 * @throws Exception
	 *             the exception
	 */
	protected static AutodiscoverError parse(EwsXmlReader reader)
			throws Exception {
		AutodiscoverError error = new AutodiscoverError();
		error.time = reader.readAttributeValue(XmlAttributeNames.Time);
		error.id = reader.readAttributeValue(XmlAttributeNames.Id);

		do {
			reader.read();

			if (reader.getNodeType().getNodeType() == XMLNodeType.START_ELEMENT) {
				if (reader.getLocalName().equalsIgnoreCase(
						XmlElementNames.ErrorCode)) {
					error.errorCode = reader.readElementValue(Integer.class);
				} else if (reader.getLocalName().equalsIgnoreCase(
						XmlElementNames.Message)) {
					error.message = reader.readElementValue();
				} else if (reader.getLocalName().equalsIgnoreCase(
						XmlElementNames.DebugData)) {
					error.debugData = reader.readElementValue();
				} else {
					reader.skipCurrentElement();
				}
			}
		} while (!reader.isEndElement(XmlNamespace.NotSpecified,
				XmlElementNames.Error));

		return error;
	}

	/**
	 * Gets the time when the error was returned.
	 * 
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Gets a hash of the name of the computer that is running Microsoft
	 * Exchange Server that has the Client Access server role installed.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the error code.
	 * 
	 * @return the error code
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Gets the error message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the debug data.
	 * 
	 * @return the debug data
	 */
	public String getDebugData() {
		return debugData;
	}

}
