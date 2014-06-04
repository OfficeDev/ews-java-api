/**************************************************************************
 * copyright file="NameResolution.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the NameResolution.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a suggested name resolution.
 */
public final class NameResolution {

	/** The owner. */
	private NameResolutionCollection owner;

	/** The mailbox. */
	private EmailAddress mailbox = new EmailAddress();

	/** The contact. */
	private Contact contact;

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param owner
	 *            the owner
	 */
	protected NameResolution(NameResolutionCollection owner) {
		EwsUtilities.EwsAssert(owner != null, "NameResolution.ctor",
				"owner is null.");

		this.owner = owner;
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
		reader.readStartElement(XmlNamespace.Types, XmlElementNames.Resolution);
		reader.readStartElement(XmlNamespace.Types, XmlElementNames.Mailbox);
		this.mailbox.loadFromXml(reader, XmlElementNames.Mailbox);

		reader.read();
		if (reader.isStartElement(XmlNamespace.Types, XmlElementNames.Contact)) {
			this.contact = new Contact(this.owner.getSession());
			this.contact.loadFromXml(reader, true /* clearPropertyBag */,
					PropertySet.FirstClassProperties,
					false /* summaryPropertiesOnly */);

			reader.readEndElement(XmlNamespace.Types,
					XmlElementNames.Resolution);
		} else {
			reader.ensureCurrentNodeIsEndElement(XmlNamespace.Types,
					XmlElementNames.Resolution);
		}
	}

	/**
	 * Gets the mailbox of the suggested resolved name.
	 * 
	 * @return the mailbox
	 */
	public EmailAddress getMailbox() {
		return this.mailbox;
	}

	/**
	 * Gets the contact information of the suggested resolved name. This
	 * property is only available when ResolveName is called with
	 * returnContactDetails = true.
	 * 
	 * @return the contact
	 */
	public Contact getContact() {
		return this.contact;
	}
}
