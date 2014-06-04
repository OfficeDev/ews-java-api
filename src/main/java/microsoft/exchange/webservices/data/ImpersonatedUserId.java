/**************************************************************************
 * copyright file="ImpersonatedUserId.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ImpersonatedUserId.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an impersonated user Id.
 * 
 */
public final class ImpersonatedUserId {

	/** The id type. */
	private ConnectingIdType idType;

	/** The id. */
	private String id;

	/**
	 * Instantiates a new impersonated user id.
	 */
	public ImpersonatedUserId() {
	}

	/**
	 * Initializes a new instance of ConnectingId.
	 * 
	 * @param idType
	 *            The type of this Id.
	 * @param id
	 *            The user Id.
	 */
	public ImpersonatedUserId(ConnectingIdType idType, String id) {
		this();
		this.idType = idType;
		this.id = id;
	}

	/**
	 * Writes to XML.
	 * 
	 * @param writer
	 *            The writer
	 * @throws Exception
	 *             the exception
	 */
	protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
		if (this.id == null || this.id.isEmpty()) {
			throw new Exception(Strings.IdPropertyMustBeSet);
		}

		writer.writeStartElement(XmlNamespace.Types,
				XmlElementNames.ExchangeImpersonation);
		writer.writeStartElement(XmlNamespace.Types,
				XmlElementNames.ConnectingSID);

		// For 2007 SP1, use PrimarySmtpAddress for type SmtpAddress
		String connectingIdTypeLocalName = (this.idType == 
			ConnectingIdType.SmtpAddress) &&
				 (writer.getService().getRequestedServerVersion() == 
					 ExchangeVersion.Exchange2007_SP1) ? 
						 XmlElementNames.PrimarySmtpAddress :
				 this.getIdType().toString();

		writer.writeElementValue(XmlNamespace.Types, connectingIdTypeLocalName,
				this.id);

		writer.writeEndElement(); // ConnectingSID
		writer.writeEndElement(); // ExchangeImpersonation
	}

	/**
	 * Gets  the type of the Id.
	 * 
	 * @return the id type
	 */
	public ConnectingIdType getIdType() {
		return idType;
	}

	/**
	 * Sets the id type.
	 * 
	 * @param idType
	 *            the new id type
	 */
	public void setIdType(ConnectingIdType idType) {
		this.idType = idType;
	}

	/**
	 * Gets  the user Id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

}
