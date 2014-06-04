/**************************************************************************
 * copyright file="DelegatePermissions.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DelegatePermissions.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

/***
 * Represents the permissions of a delegate user.
 */
public final class DelegatePermissions extends ComplexProperty {

	private Map<String, DelegateFolderPermission> delegateFolderPermissions;

	/**
	 * * Initializes a new instance of the class.
	 */

	protected DelegatePermissions() {
		super();
		this.delegateFolderPermissions = new HashMap<String,
		 DelegateFolderPermission>();
		
		delegateFolderPermissions.put(
			XmlElementNames.CalendarFolderPermissionLevel, 
			new DelegateFolderPermission());
		delegateFolderPermissions.put(
			XmlElementNames.TasksFolderPermissionLevel, 
			new DelegateFolderPermission());
		delegateFolderPermissions.put(
			XmlElementNames.InboxFolderPermissionLevel,
			new DelegateFolderPermission());
		delegateFolderPermissions.put(
			XmlElementNames.ContactsFolderPermissionLevel, 
			new DelegateFolderPermission());
		delegateFolderPermissions.put(
			XmlElementNames.NotesFolderPermissionLevel, 
			new DelegateFolderPermission());
		delegateFolderPermissions.put(
			XmlElementNames.JournalFolderPermissionLevel, 
			new DelegateFolderPermission());
	}

	/**
	 * * Gets the delegate user's permission on the principal's calendar.
	 * 
	 * @return the calendar folder permission level
	 */
	public DelegateFolderPermissionLevel getCalendarFolderPermissionLevel() {
		return this.delegateFolderPermissions.get(XmlElementNames.
				CalendarFolderPermissionLevel).getPermissionLevel();

	}

	/**
	 * * sets the delegate user's permission on the principal's calendar.
	 * 
	 * @param value
	 *            the new calendar folder permission level
	 */
	public void setCalendarFolderPermissionLevel(
			DelegateFolderPermissionLevel value) {
		this.delegateFolderPermissions.get(XmlElementNames.
				CalendarFolderPermissionLevel).setPermissionLevel(value);
	}

	/**
	 * * Gets  the delegate user's permission on the principal's tasks
	 * folder.
	 * 
	 * @return the tasks folder permission level
	 */
	public DelegateFolderPermissionLevel getTasksFolderPermissionLevel() {
		return this.delegateFolderPermissions.get(XmlElementNames.
				TasksFolderPermissionLevel).getPermissionLevel();

	}

	/**
	 * Sets the tasks folder permission level.
	 * 
	 * @param value
	 *            the new tasks folder permission level
	 */
	public void setTasksFolderPermissionLevel(
			DelegateFolderPermissionLevel value) {

		this.delegateFolderPermissions.get(XmlElementNames.
				TasksFolderPermissionLevel).setPermissionLevel(value);
	}

	/**
	 * * Gets the delegate user's permission on the principal's inbox.
	 * 
	 * @return the inbox folder permission level
	 */
	public DelegateFolderPermissionLevel getInboxFolderPermissionLevel() {
		return this.delegateFolderPermissions.get(XmlElementNames.
				InboxFolderPermissionLevel).
		getPermissionLevel();
	}

	/**
	 * Sets the inbox folder permission level.
	 * 
	 * @param value
	 *            the new inbox folder permission level
	 */
	public void setInboxFolderPermissionLevel(
			DelegateFolderPermissionLevel value) {
		this.delegateFolderPermissions.get(XmlElementNames.
				InboxFolderPermissionLevel).
		setPermissionLevel(value);
	}

	/**
	 * * Gets  the delegate user's permission on the principal's contacts
	 * folder.
	 * 
	 * @return the contacts folder permission level
	 */
	public DelegateFolderPermissionLevel getContactsFolderPermissionLevel() {
		return this.delegateFolderPermissions.get(
				XmlElementNames.ContactsFolderPermissionLevel).
		getPermissionLevel();
	}

	/**
	 * Sets the contacts folder permission level.
	 * 
	 * @param value
	 *            the new contacts folder permission level
	 */
	public void setContactsFolderPermissionLevel(
			DelegateFolderPermissionLevel value) {
		this.delegateFolderPermissions.get(
				XmlElementNames.ContactsFolderPermissionLevel).
		setPermissionLevel(value);
	}

	/**
	 * * Gets the delegate user's permission on the principal's notes
	 * folder.
	 * 
	 * @return the notes folder permission level
	 */
	public DelegateFolderPermissionLevel getNotesFolderPermissionLevel() {
		return this.delegateFolderPermissions.get(XmlElementNames.
				NotesFolderPermissionLevel).
		getPermissionLevel();
	}

	/**
	 * Sets the notes folder permission level.
	 * 
	 * @param value
	 *            the new notes folder permission level
	 */
	public void setNotesFolderPermissionLevel(
			DelegateFolderPermissionLevel value) {
		this.delegateFolderPermissions.get(XmlElementNames.
				NotesFolderPermissionLevel).
		setPermissionLevel(value);
	}

	/**
	 * * Gets  the delegate user's permission on the principal's journal
	 * folder.
	 * 
	 * @return the journal folder permission level
	 */
	public DelegateFolderPermissionLevel getJournalFolderPermissionLevel() {
		return this.delegateFolderPermissions.get(XmlElementNames.
				JournalFolderPermissionLevel).
		getPermissionLevel();
	}

	/**
	 * Sets the journal folder permission level.
	 * 
	 * @param value
	 *            the new journal folder permission level
	 */
	public void setJournalFolderPermissionLevel(
			DelegateFolderPermissionLevel value) {
		this.delegateFolderPermissions.get(XmlElementNames.
				JournalFolderPermissionLevel).
		setPermissionLevel(value);
	}

	/**
	 * Reset.
	 */
	protected void reset() {		
		for(DelegateFolderPermission delegateFolderPermission : this.delegateFolderPermissions.values())
		{
			delegateFolderPermission.reset();
		}
	}

	/**
	 * * Tries to read element from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @return Returns true if element was read.
	 * @throws Exception
	 *             the exception
	 */
	protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
	throws Exception {
		DelegateFolderPermission delegateFolderPermission = null;	

		if (this.delegateFolderPermissions.containsKey(reader.getLocalName())) {
			delegateFolderPermission = this.delegateFolderPermissions.
			get(reader.getLocalName());
			delegateFolderPermission.initialize(reader.
					readElementValue(DelegateFolderPermissionLevel.class));
		}


		return delegateFolderPermission != null;
	}

	/**
	 * * Writes elements to XML.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
	throws Exception {
		this.writePermissionToXml(writer,
				XmlElementNames.CalendarFolderPermissionLevel);

		this.writePermissionToXml(writer,
				XmlElementNames.TasksFolderPermissionLevel);

		this.writePermissionToXml(writer,
				XmlElementNames.InboxFolderPermissionLevel);

		this.writePermissionToXml(writer,
				XmlElementNames.ContactsFolderPermissionLevel);

		this.writePermissionToXml(writer,
				XmlElementNames.NotesFolderPermissionLevel);

		this.writePermissionToXml(writer,
				XmlElementNames.JournalFolderPermissionLevel);
	}

	/**
	 * Write permission to Xml.
	 * @param writer The writer.
	 * @param xmlElementName The element name.
	 */
	private void writePermissionToXml(
			EwsServiceXmlWriter writer, 
			String xmlElementName) throws ServiceXmlSerializationException, 
			XMLStreamException {
		DelegateFolderPermissionLevel delegateFolderPermissionLevel = 
			this.delegateFolderPermissions.
		get(xmlElementName).getPermissionLevel();
		// E14 Bug 298307: UpdateDelegate fails if 
		//Custom permission level is round tripped
        //
		if (delegateFolderPermissionLevel != DelegateFolderPermissionLevel.Custom) {
			writer.writeElementValue(
					XmlNamespace.Types,
					xmlElementName,
					delegateFolderPermissionLevel);
		}
			}

	/**
	 * Validates this instance for AddDelegate.
	 * @throws ServiceValidationException 
	 */
	protected void validateAddDelegate() throws ServiceValidationException {
		for (DelegateFolderPermission delegateFolderPermission : this.delegateFolderPermissions.values()) {
			if(delegateFolderPermission.getPermissionLevel() == DelegateFolderPermissionLevel.Custom) {
				throw new ServiceValidationException(Strings.
						CannotSetDelegateFolderPermissionLevelToCustom);
			}
		}
	}

	/**
	 * Validates this instance for UpdateDelegate.
	 * @throws ServiceValidationException 
	 */
	protected void validateUpdateDelegate() throws ServiceValidationException {
		for (DelegateFolderPermission delegateFolderPermission : this.delegateFolderPermissions.values()) {
			if(delegateFolderPermission.getPermissionLevel() == DelegateFolderPermissionLevel.Custom && 
					!delegateFolderPermission.isExistingPermissionLevelCustom) {
				throw new ServiceValidationException(Strings.
						CannotSetDelegateFolderPermissionLevelToCustom);
			}
		}
	}

	/**
	 * Represents a folder's DelegateFolderPermissionLevel
	 */
	private static class DelegateFolderPermission {

		/**
		 * Intializes this DelegateFolderPermission.
		 * @param permissionLevel The DelegateFolderPermissionLevel
		 */
		protected void initialize(
			DelegateFolderPermissionLevel permissionLevel) {
			this.setPermissionLevel(permissionLevel) ;
			this.setIsExistingPermissionLevelCustom(permissionLevel==
				DelegateFolderPermissionLevel.Custom);
		}

		/**
		 * Resets this DelegateFolderPermission.
		 */
		protected void reset() {
			this.initialize(DelegateFolderPermissionLevel.None);
		}


		private DelegateFolderPermissionLevel permissionLevel = DelegateFolderPermissionLevel.None;

		/**
		 * Gets the delegate user's permission on a principal's folder.
		 */
		protected DelegateFolderPermissionLevel getPermissionLevel() { 
			return this.permissionLevel;
		}

		/**
		 * Sets the delegate user's permission on a principal's folder.
		 */
		protected  void setPermissionLevel(
			DelegateFolderPermissionLevel value) { 
			this.permissionLevel = value;
		}


		private boolean isExistingPermissionLevelCustom;

		/**
		 * Gets IsExistingPermissionLevelCustom.
		 */
		protected boolean getIsExistingPermissionLevelCustom() { 
			return this.isExistingPermissionLevelCustom;
		}

		/**
		 * Sets IsExistingPermissionLevelCustom.
		 */
		private void setIsExistingPermissionLevelCustom(Boolean value) { 
			this.isExistingPermissionLevelCustom = value;
		}

	}
}