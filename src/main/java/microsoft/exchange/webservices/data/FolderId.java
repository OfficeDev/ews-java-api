/**************************************************************************
 * copyright file="FolderId.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FolderId.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/***
 * Represents the Id of a folder.
 * 
 */
public final class FolderId extends ServiceId {
	
	/** The folder name. */
	private WellKnownFolderName folderName;
	
	/** The mailbox. */
	private Mailbox mailbox;
	
	/**
	 * Initializes a new instance.
	 */
	protected FolderId() {
		super();
	}

	/**
	 * Initializes a new instance.Use this constructor to link this FolderId to
	 * an existing folder that you have the unique Id of.
	 * 
	 * @param uniqueId
	 *            the unique id
	 * @throws Exception
	 *             the exception
	 */
	public FolderId(String uniqueId) throws Exception {
		super(uniqueId);
	}
	
	/**
	 * Initializes a new instance.Use this constructor to link this FolderId to
	 * a well known folder (e.g. Inbox, Calendar or Contacts)
	 * 
	 * @param folderName
	 *            the folder name
	 */
	public FolderId(WellKnownFolderName folderName) {
		super();
		this.folderName = folderName;
	}

	/**
	 * Initializes a new instance.Use this constructor to link this FolderId to
	 * a well known folder (e.g. Inbox, Calendar or Contacts) in a specific
	 * mailbox.
	 * 
	 * @param folderName
	 *            the folder name
	 * @param mailbox
	 *            the mailbox
	 */
	public FolderId(WellKnownFolderName folderName, Mailbox mailbox) {
		this(folderName);
		this.mailbox = mailbox;
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name
	 */
	protected String getXmlElementName() {
		if (this.getFolderName() != null) {
			return XmlElementNames.DistinguishedFolderId;
		} else {
			return XmlElementNames.FolderId;
		}
	}

	/**
	 * Writes attributes to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws ServiceXmlSerializationException
	 *             the service xml serialization exception
	 */
	protected void writeAttributesToXml(EwsServiceXmlWriter writer)
			throws ServiceXmlSerializationException {
		if (this.getFolderName() != null) {
			writer.writeAttributeValue(XmlAttributeNames.Id, this
					.getFolderName().toString().toLowerCase());

			if (this.mailbox != null) {
				try {
					this.mailbox.writeToXml(writer, XmlElementNames.Mailbox);
				} catch (Exception e) {
					throw new ServiceXmlSerializationException(e.getMessage());
				}
			}
		} else {
			super.writeAttributesToXml(writer);
		}
	}

	/**
	 * Validates FolderId against a specified request version.
	 * 
	 * @param version
	 *            the version
	 * @throws ServiceVersionException
	 *             the service version exception
	 */
	protected void validate(ExchangeVersion version)
			throws ServiceVersionException {
		// The FolderName property is a WellKnownFolderName, an enumeration
		// type. If the property
		// is set, make sure that the value is valid for the request version.
		if (this.getFolderName() != null) {
			EwsUtilities
					.validateEnumVersionValue(this.getFolderName(), version);
		}
	}

	/**
	 * Gets the name of the folder associated with the folder Id. Name and Id
	 * are mutually exclusive; if one is set, the other is null.
	 * 
	 * @return the folder name
	 */
	public WellKnownFolderName getFolderName() {
		return this.folderName;
	}

	/**
	 * Gets the mailbox of the folder. Mailbox is only set when FolderName is
	 * set.
	 * 
	 * @return the mailbox
	 */
	public Mailbox getMailbox() {
		return this.mailbox;
	}

	/**
	 * Defines an implicit conversion between string and FolderId.
	 * 
	 * @param uniqueId
	 *            the unique id
	 * @return A FolderId initialized with the specified unique Id
	 * @throws Exception
	 *             the exception
	 */
	 public static FolderId getFolderIdFromString(String uniqueId) 
			throws Exception {
		return new FolderId(uniqueId);
	}

	/**
	 * Defines an implicit conversion between WellKnownFolderName and FolderId.
	 * 
	 * @param folderName
	 *            the folder name
	 * @return A FolderId initialized with the specified folder name
	 */
	 public static FolderId getFolderIdFromWellKnownFolderName(
			WellKnownFolderName folderName) {
		return new FolderId(folderName);
	}

	/**
	 * True if this instance is valid, false otherwise.
	 * 
	 * @return the checks if is valid
	 */
	protected boolean getIsValid() {
		if (this.folderName != null) {
			return (this.mailbox == null) || this.mailbox.isValid();
		} else {
			return super.isValid();
		}
	}

	/**
	 * Determines whether the specified is equal to the current.
	 * 
	 * @param obj
	 *            the obj
	 * @return true if the specified is equal to the current
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this || (obj == null && this == null)) {
			return true;
		} else if (obj instanceof FolderId) {
			FolderId other = (FolderId) obj;

			if (this.folderName != null) {
				if (other.folderName != null
						&& this.folderName.equals(other.folderName)) {
					if (this.mailbox != null) {
						return this.mailbox.equals(other.mailbox);
					} else if (other.mailbox == null) {
						return true;
					}
				}
			} else if (super.equals(other)) {
				return true;
			}

			return false;
		} else {
			return false;
		}
	}

	/**
	 * Serves as a hash function for a particular type.
	 * 
	 * @return A hash code for the current
	 */
	@Override
	public int hashCode() {
		int hashCode;

		if (this.folderName != null) {
			hashCode = this.folderName.hashCode();

			if ((this.mailbox != null) && this.mailbox.isValid()) {
				hashCode = hashCode ^ this.mailbox.hashCode();
			}
		} else {
			hashCode = super.hashCode();
		}

		return hashCode;
	}

	/**
	 * Returns a String that represents the current Object.
	 * 
	 * @return the string
	 */
	public String toString() {
		if (this.isValid()) {
			if (this.folderName != null) {
				if ((this.mailbox != null) && mailbox.isValid()) {
					return String.format("%s,(%s)", this.folderName,
							this.mailbox.toString());
				} else {
					return this.folderName.toString();
				}
			} else {
				return super.toString();
			}
		} else {
			return "";
		}
	}
}
