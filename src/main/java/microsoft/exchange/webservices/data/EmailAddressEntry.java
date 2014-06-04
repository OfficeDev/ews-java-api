/**************************************************************************
 * copyright file="EmailAddressEntry.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EmailAddressEntry.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents an entry of an EmailAddressDictionary.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class EmailAddressEntry extends
		DictionaryEntryProperty<EmailAddressKey> implements
		IComplexPropertyChangedDelegate {
	// / The email address.
	/** The email address. */
	private EmailAddress emailAddress;

	/**
	 * Initializes a new instance of the <see cref="EmailAddressEntry"/> class.
	 */
	protected EmailAddressEntry() {
		super(EmailAddressKey.class);
		this.emailAddress = new EmailAddress();
		this.emailAddress.addOnChangeEvent(this);
	}

	/**
	 * Initializes a new instance of the "EmailAddressEntry" class.
	 * 
	 * @param key
	 *            The key.
	 * @param emailAddress
	 *            The email address.
	 */
	protected EmailAddressEntry(EmailAddressKey key, 
			EmailAddress emailAddress) {
		super(EmailAddressKey.class, key);
		this.emailAddress = emailAddress;
	}

	/**
	 * Reads the attributes from XML.
	 * 
	 * @param reader
	 *            accepts EwsServiceXmlReader
	 * @throws Exception
	 *             throws Exception
	 */
	@Override
	protected void readAttributesFromXml(EwsServiceXmlReader reader)
			throws Exception {
		super.readAttributesFromXml(reader);
		this.getEmailAddress().setName(
				reader.readAttributeValue((String)XmlAttributeNames.Name));
		this
				.getEmailAddress()
				.setRoutingType(
						reader
								.readAttributeValue((String)XmlAttributeNames.
										RoutingType));
		String mailboxTypeString = reader
				.readAttributeValue(XmlAttributeNames.MailboxType);
		if ((mailboxTypeString != null) && (!mailboxTypeString.isEmpty())) {
			this.getEmailAddress().setMailboxType(
					EwsUtilities.parse(MailboxType.class, mailboxTypeString));
		} else {
			this.getEmailAddress().setMailboxType(null);
		}
	}

	/**
	 * Reads the text value from XML.
	 * 
	 * @param reader
	 *            accepts EwsServiceXmlReader
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void readTextValueFromXml(EwsServiceXmlReader reader)
			throws Exception {
		this.getEmailAddress().setAddress(reader.readValue());
	}

	/**
	 * Writes the attributes to XML.
	 * 
	 * @param writer
	 *            accepts EwsServiceXmlWriter
	 * @throws ServiceXmlSerializationException
	 *             throws ServiceXmlSerializationException
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		super.writeAttributesToXml(writer);
		if (writer.getService().getRequestedServerVersion().ordinal() > 
		ExchangeVersion.Exchange2007_SP1
				.ordinal()) {
			writer.writeAttributeValue(XmlAttributeNames.Name, this
					.getEmailAddress().getName());
			writer.writeAttributeValue(XmlAttributeNames.RoutingType, this
					.getEmailAddress().getRoutingType());
			if (this.getEmailAddress().getMailboxType() != MailboxType.Unknown) {
				writer.writeAttributeValue(XmlAttributeNames.MailboxType, this
						.getEmailAddress().getMailboxType());
			}
		}
	}

	/**
	 * Writes elements to XML.
	 * 
	 * @param writer
	 *            accepts EwsServiceXmlWriter
	 * @throws ServiceXmlSerializationException
	 *             throws ServiceXmlSerializationException
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		writer.writeValue(this.getEmailAddress().getAddress(),
				XmlElementNames.EmailAddress);
	}

	/**
	 * Gets the e-mail address of the entry.
	 * 
	 * @return the email address
	 */
	public EmailAddress getEmailAddress() {
		return this.emailAddress;
		// set { this.SetFieldValue<EmailAddress>(ref this.emailAddress, value);
		// }
	}

	/**
	 * Sets the e-mail address of the entry.
	 * 
	 * @param value
	 *            the new email address
	 */
	public void setEmailAddress(Object value) {
		//this.canSetFieldValue((EmailAddress) this.emailAddress, value);
		if( this.canSetFieldValue((EmailAddress) this.emailAddress, value) ) {
			this.emailAddress = (EmailAddress)value;
		}
	}

	/**
	 * E-mail address was changed.
	 * 
	 * @param complexProperty
	 *            the complex property
	 */
	@SuppressWarnings("unused")
	private void emailAddressChanged(ComplexProperty complexProperty) {
		this.changed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * microsoft.exchange.webservices.ComplexPropertyChangedDelegateInterface
	 * #complexPropertyChanged(microsoft.exchange.webservices.ComplexProperty)
	 */
	@Override
	public void complexPropertyChanged(ComplexProperty complexProperty) {
		this.emailAddressChanged(complexProperty);

	}

}
