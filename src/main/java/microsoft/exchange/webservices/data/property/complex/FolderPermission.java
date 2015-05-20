/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ILazyMember;
import microsoft.exchange.webservices.data.core.LazyMember;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.permission.folder.FolderPermissionLevel;
import microsoft.exchange.webservices.data.core.enumeration.permission.folder.FolderPermissionReadAccess;
import microsoft.exchange.webservices.data.core.enumeration.permission.PermissionScope;
import microsoft.exchange.webservices.data.core.enumeration.property.StandardUser;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents a permission on a folder.
 */
public final class FolderPermission extends ComplexProperty implements IComplexPropertyChangedDelegate {

  private static final Log LOG = LogFactory.getLog(FolderPermission.class);

  private static LazyMember<Map<FolderPermissionLevel, FolderPermission>>
      defaultPermissions =
      new LazyMember<Map<FolderPermissionLevel, FolderPermission>>(
          new ILazyMember<Map<FolderPermissionLevel, FolderPermission>>() {
            @Override
            public Map<FolderPermissionLevel, FolderPermission>
            createInstance() {
              Map<FolderPermissionLevel, FolderPermission> result =
                  new HashMap<FolderPermissionLevel, FolderPermission>();

              /** The default permissions. */
              FolderPermission permission = new FolderPermission();
              permission.canCreateItems = false;
              permission.canCreateSubFolders = false;
              permission.deleteItems = PermissionScope.None;
              permission.editItems = PermissionScope.None;
              permission.isFolderContact = false;
              permission.isFolderOwner = false;
              permission.isFolderVisible = false;
              permission.readItems = FolderPermissionReadAccess.None;

              result.put(FolderPermissionLevel.None, permission);

              permission = new FolderPermission();
              permission.canCreateItems = true;
              permission.canCreateSubFolders = false;
              permission.deleteItems = PermissionScope.None;
              permission.editItems = PermissionScope.None;
              permission.isFolderContact = false;
              permission.isFolderOwner = false;
              permission.isFolderVisible = true;
              permission.readItems = FolderPermissionReadAccess.None;

              result.put(FolderPermissionLevel.Contributor, permission);

              permission = new FolderPermission();
              permission.canCreateItems = false;
              permission.canCreateSubFolders = false;
              permission.deleteItems = PermissionScope.None;
              permission.editItems = PermissionScope.None;
              permission.isFolderContact = false;
              permission.isFolderOwner = false;
              permission.isFolderVisible = true;
              permission.readItems = FolderPermissionReadAccess.
                  FullDetails;

              result.put(FolderPermissionLevel.Reviewer, permission);

              permission = new FolderPermission();
              permission.canCreateItems = true;
              permission.canCreateSubFolders = false;
              permission.deleteItems = PermissionScope.Owned;
              permission.editItems = PermissionScope.None;
              permission.isFolderContact = false;
              permission.isFolderOwner = false;
              permission.isFolderVisible = true;
              permission.readItems = FolderPermissionReadAccess.
                  FullDetails;

              result.put(FolderPermissionLevel.NoneditingAuthor,
                  permission);

              permission = new FolderPermission();
              permission.canCreateItems = true;
              permission.canCreateSubFolders = false;
              permission.deleteItems = PermissionScope.Owned;
              permission.editItems = PermissionScope.Owned;
              permission.isFolderContact = false;
              permission.isFolderOwner = false;
              permission.isFolderVisible = true;
              permission.readItems = FolderPermissionReadAccess.
                  FullDetails;

              result.put(FolderPermissionLevel.Author, permission);

              permission = new FolderPermission();
              permission.canCreateItems = true;
              permission.canCreateSubFolders = true;
              permission.deleteItems = PermissionScope.Owned;
              permission.editItems = PermissionScope.Owned;
              permission.isFolderContact = false;
              permission.isFolderOwner = false;
              permission.isFolderVisible = true;
              permission.readItems = FolderPermissionReadAccess.
                  FullDetails;

              result.put(FolderPermissionLevel.PublishingAuthor,
                  permission);

              permission = new FolderPermission();
              permission.canCreateItems = true;
              permission.canCreateSubFolders = false;
              permission.deleteItems = PermissionScope.All;
              permission.editItems = PermissionScope.All;
              permission.isFolderContact = false;
              permission.isFolderOwner = false;
              permission.isFolderVisible = true;
              permission.readItems = FolderPermissionReadAccess.
                  FullDetails;

              result.put(FolderPermissionLevel.Editor, permission);

              permission = new FolderPermission();
              permission.canCreateItems = true;
              permission.canCreateSubFolders = true;
              permission.deleteItems = PermissionScope.All;
              permission.editItems = PermissionScope.All;
              permission.isFolderContact = false;
              permission.isFolderOwner = false;
              permission.isFolderVisible = true;
              permission.readItems = FolderPermissionReadAccess.
                  FullDetails;

              result.put(FolderPermissionLevel.PublishingEditor,
                  permission);

              permission = new FolderPermission();
              permission.canCreateItems = true;
              permission.canCreateSubFolders = true;
              permission.deleteItems = PermissionScope.All;
              permission.editItems = PermissionScope.All;
              permission.isFolderContact = true;
              permission.isFolderOwner = true;
              permission.isFolderVisible = true;
              permission.readItems = FolderPermissionReadAccess.
                  FullDetails;

              result.put(FolderPermissionLevel.Owner, permission);

              permission = new FolderPermission();
              permission.canCreateItems = false;
              permission.canCreateSubFolders = false;
              permission.deleteItems = PermissionScope.None;
              permission.editItems = PermissionScope.None;
              permission.isFolderContact = false;
              permission.isFolderOwner = false;
              permission.isFolderVisible = false;
              permission.readItems = FolderPermissionReadAccess.TimeOnly;

              result.put(FolderPermissionLevel.FreeBusyTimeOnly,
                  permission);

              permission = new FolderPermission();
              permission.canCreateItems = false;
              permission.canCreateSubFolders = false;
              permission.deleteItems = PermissionScope.None;
              permission.editItems = PermissionScope.None;
              permission.isFolderContact = false;
              permission.isFolderOwner = false;
              permission.isFolderVisible = false;
              permission.readItems = FolderPermissionReadAccess.
                  TimeAndSubjectAndLocation;

              result
                  .put(FolderPermissionLevel.
                          FreeBusyTimeAndSubjectAndLocation,
                      permission);
              return result;
            }
          });
  //End Region

  /**
   * Variants of pre-defined permission levels that Outlook also displays with
   * the same levels.
   */
  private static LazyMember<List<FolderPermission>> levelVariants =
      new LazyMember<List<FolderPermission>>(
          new ILazyMember<List<FolderPermission>>() {
            @Override
            public List<FolderPermission> createInstance() {
              List<FolderPermission> results =
                  new ArrayList<FolderPermission>();

              FolderPermission permissionNone = FolderPermission.
                  defaultPermissions
                  .getMember().get(FolderPermissionLevel.None);
              FolderPermission permissionOwner = FolderPermission.
                  defaultPermissions
                  .getMember().get(FolderPermissionLevel.Owner);

              // PermissionLevelNoneOption1
              FolderPermission permission;
              try {
                permission = (FolderPermission) permissionNone.clone();
                permission.isFolderVisible = true;
                results.add(permission);

                // PermissionLevelNoneOption2
                permission = (FolderPermission) permissionNone.clone();
                permission.isFolderContact = true;
                results.add(permission);

                // PermissionLevelNoneOption3
                permission = (FolderPermission) permissionNone.clone();
                permission.isFolderContact = true;
                permission.isFolderVisible = true;
                results.add(permission);

                // PermissionLevelOwnerOption1
                permission = (FolderPermission) permissionOwner.clone();
                permission.isFolderContact = false;
                results.add(permission);

              } catch (CloneNotSupportedException e) {
                LOG.error(e);
              }
              return results;
            }
          });

  /**
   * The user id.
   */
  private UserId userId;

  /**
   * The can create item.
   */
  private boolean canCreateItems;

  /**
   * The can create sub folder.
   */
  private boolean canCreateSubFolders;

  /**
   * The is folder owner.
   */
  private boolean isFolderOwner;

  /**
   * The is folder visible.
   */
  private boolean isFolderVisible;

  /**
   * The is folder contact.
   */
  private boolean isFolderContact;

  /**
   * The edit item.
   */
  private PermissionScope editItems = PermissionScope.None;

  /**
   * The delete item.
   */
  private PermissionScope deleteItems = PermissionScope.None;

  /**
   * The read item.
   */
  private FolderPermissionReadAccess readItems = FolderPermissionReadAccess.None;

  /**
   * The permission level.
   */
  private FolderPermissionLevel permissionLevel = FolderPermissionLevel.None;

  /**
   * Determines whether the specified folder permission is the same as this
   * one. The comparison does not take UserId and PermissionLevel into
   * consideration.
   *
   * @param permission the permission
   * @return True is the specified folder permission is equal to this one,
   * false otherwise.
   */
  private boolean isEqualTo(FolderPermission permission) {

    return this.canCreateItems == permission.canCreateItems &&
        this.canCreateSubFolders == permission.canCreateSubFolders &&
        this.isFolderContact == permission.isFolderContact &&
        this.isFolderVisible == permission.isFolderVisible &&
        this.isFolderOwner == permission.isFolderOwner &&
        this.editItems == permission.editItems &&
        this.deleteItems == permission.deleteItems &&
        this.readItems == permission.readItems;
  }

  /**
   * Create a copy of this FolderPermission instance.
   *
   * @return Clone of this instance.
   */
        /*
	 * private FolderPermission Clone() throws CloneNotSupportedException {
	 * return (FolderPermission)this.clone(); }
	 */

  /**
   * Determines the permission level of this folder permission based on its
   * individual settings, and sets the PermissionLevel property accordingly.
   */
  private void AdjustPermissionLevel() {
    for (Entry<FolderPermissionLevel, FolderPermission> keyValuePair : defaultPermissions
        .getMember().entrySet()) {
      if (this.isEqualTo(keyValuePair.getValue())) {
        this.permissionLevel = keyValuePair.getKey();
        return;
      }
    }
    this.permissionLevel = FolderPermissionLevel.Custom;
  }

  /**
   * Copies the values of the individual permissions of the specified folder
   * permission to this folder permissions.
   *
   * @param permission the permission
   */
  private void AssignIndividualPermissions(FolderPermission permission) {
    this.canCreateItems = permission.canCreateItems;
    this.canCreateSubFolders = permission.canCreateSubFolders;
    this.isFolderContact = permission.isFolderContact;
    this.isFolderOwner = permission.isFolderOwner;
    this.isFolderVisible = permission.isFolderVisible;
    this.editItems = permission.editItems;
    this.deleteItems = permission.deleteItems;
    this.readItems = permission.readItems;
  }

  /**
   * Initializes a new instance of the FolderPermission class.
   */
  public FolderPermission() {
    super();
    this.userId = new UserId();
  }

  /**
   * Initializes a new instance of the FolderPermission class.
   *
   * @param userId          the user id
   * @param permissionLevel the permission level
   * @throws Exception the exception
   */
  public FolderPermission(UserId userId,
      FolderPermissionLevel permissionLevel)
      throws Exception {
    EwsUtilities.validateParam(userId, "userId");

    this.userId = userId;
    this.permissionLevel = permissionLevel;
  }

  /**
   * Initializes a new instance of the FolderPermission class.
   *
   * @param primarySmtpAddress the primary smtp address
   * @param permissionLevel    the permission level
   */
  public FolderPermission(String primarySmtpAddress,
      FolderPermissionLevel permissionLevel) {
    this.userId = new UserId(primarySmtpAddress);
    this.permissionLevel = permissionLevel;
  }

  /**
   * Initializes a new instance of the FolderPermission class.
   *
   * @param standardUser    the standard user
   * @param permissionLevel the permission level
   */
  public FolderPermission(StandardUser standardUser,
      FolderPermissionLevel permissionLevel) {
    this.userId = new UserId(standardUser);
    this.permissionLevel = permissionLevel;
  }

  /**
   * Validates this instance.
   *
   * @param isCalendarFolder the is calendar folder
   * @param permissionIndex  the permission index
   * @throws ServiceValidationException the service validation exception
   * @throws ServiceLocalException      the service local exception
   */
  void validate(boolean isCalendarFolder, int permissionIndex)
      throws ServiceValidationException, ServiceLocalException {
    // Check UserId
    if (!this.userId.isValid()) {
      throw new ServiceValidationException(String.format(
          "The UserId in the folder permission at index %d is invalid. "
          + "The StandardUser, PrimarySmtpAddress, or SID property must be set.", permissionIndex));
    }

    // If this permission is to be used for a non-calendar folder make sure
    // that read access and permission level aren't set to Calendar-only
    // values
    if (!isCalendarFolder) {
      if ((this.readItems == FolderPermissionReadAccess.TimeAndSubjectAndLocation)
          || (this.readItems == FolderPermissionReadAccess.
          TimeOnly)) {
        throw new ServiceLocalException(String.format(
            "Permission read access value %s cannot be used with non-calendar folder.",
            this.readItems));
      }

      if ((this.permissionLevel == FolderPermissionLevel.FreeBusyTimeAndSubjectAndLocation)
          || (this.permissionLevel == FolderPermissionLevel.
          FreeBusyTimeOnly)) {
        throw new ServiceLocalException(String.format(
            "Permission level value %s cannot be used with non-calendar folder.",
            this.permissionLevel));
      }
    }
  }

  /**
   * Gets the Id of the user the permission applies to.
   *
   * @return the user id
   */

  public UserId getUserId() {
    return this.userId;
  }

  /**
   * Sets the user id.
   *
   * @param value the new user id
   */
  public void setUserId(UserId value) {
    if (this.userId != null) {
      this.userId.removeChangeEvent(this);
    }

    if (this.canSetFieldValue(this.userId, value)) {
      userId = value;
      this.changed();
    }
    if (this.userId != null) {
      this.userId.addOnChangeEvent(this);
    }
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
    this.propertyChanged(complexProperty);
  }

  /**
   * Property was changed.
   *
   * @param complexProperty the complex property
   */
  private void propertyChanged(ComplexProperty complexProperty) {
    this.changed();
  }

  /**
   * Gets  a value indicating whether the user can create new item.
   *
   * @return the can create item
   */
  public boolean getCanCreateItems() {
    return this.canCreateItems;
  }

  /**
   * Sets the can create item.
   *
   * @param value the new can create item
   */
  public void setCanCreateItems(boolean value) {
    if (this.canSetFieldValue(this.canCreateItems, value)) {
      this.canCreateItems = value;
      this.changed();
    }
    this.AdjustPermissionLevel();
  }

  /**
   * Gets  a value indicating whether the user can create
   * sub-folder.
   *
   * @return the can create sub folder
   */
  public boolean getCanCreateSubFolders() {
    return this.canCreateSubFolders;
  }

  /**
   * Sets the can create sub folder.
   *
   * @param value the new can create sub folder
   */
  public void setCanCreateSubFolders(boolean value) {
    if (this.canSetFieldValue(this.canCreateSubFolders, value)) {
      this.canCreateSubFolders = value;
      this.changed();
    }
    this.AdjustPermissionLevel();
  }

  /**
   * Gets a value indicating whether the user owns the folder.
   *
   * @return the checks if is folder owner
   */
  public boolean getIsFolderOwner() {
    return this.isFolderOwner;
  }

  /**
   * Sets the checks if is folder owner.
   *
   * @param value the new checks if is folder owner
   */
  public void setIsFolderOwner(boolean value) {
    if (this.canSetFieldValue(this.isFolderOwner, value)) {
      this.isFolderOwner = value;
      this.changed();
    }
    this.AdjustPermissionLevel();
  }

  /**
   * Gets a value indicating whether the folder is visible to the
   * user.
   *
   * @return the checks if is folder visible
   */
  public boolean getIsFolderVisible() {
    return this.isFolderVisible;
  }

  /**
   * Sets the checks if is folder visible.
   *
   * @param value the new checks if is folder visible
   */
  public void setIsFolderVisible(boolean value) {
    if (this.canSetFieldValue(this.isFolderVisible, value)) {
      this.isFolderVisible = value;
      this.changed();
    }
    this.AdjustPermissionLevel();
  }

  /**
   * Gets  a value indicating whether the user is a contact for the
   * folder.
   *
   * @return the checks if is folder contact
   */
  public boolean getIsFolderContact() {
    return this.isFolderContact;
  }

  /**
   * Sets the checks if is folder contact.
   *
   * @param value the new checks if is folder contact
   */
  public void setIsFolderContact(boolean value) {
    if (this.canSetFieldValue(this.isFolderContact, value)) {
      this.isFolderContact = value;
      this.changed();
    }
    this.AdjustPermissionLevel();
  }

  /**
   * Gets  a value indicating if/how the user can edit existing
   * item.
   *
   * @return the edits the item
   */
  public PermissionScope getEditItems() {
    return this.editItems;
  }

  /**
   * Sets the edits the item.
   *
   * @param value the new edits the item
   */
  public void setEditItems(PermissionScope value) {
    if (this.canSetFieldValue(this.editItems, value)) {
      this.editItems = value;
      this.changed();
    }
    this.AdjustPermissionLevel();
  }

  /**
   * Gets  a value indicating if/how the user can delete existing
   * item.
   *
   * @return the delete item
   */
  public PermissionScope getDeleteItems() {
    return this.deleteItems;
  }

  /**
   * Sets the delete item.
   *
   * @param value the new delete item
   */
  public void setDeleteItems(PermissionScope value) {
    if (this.canSetFieldValue(this.deleteItems, value)) {
      this.deleteItems = value;
      this.changed();
    }
    this.AdjustPermissionLevel();
  }

  /**
   * Gets  the read item access permission.
   *
   * @return the read item
   */
  public FolderPermissionReadAccess getReadItems() {
    return this.readItems;
  }

  /**
   * Sets the read item.
   *
   * @param value the new read item
   */
  public void setReadItems(FolderPermissionReadAccess value) {
    if (this.canSetFieldValue(this.readItems, value)) {
      this.readItems = value;
      this.changed();
    }
    this.AdjustPermissionLevel();
  }

  /**
   * Gets  the permission level.
   *
   * @return the permission level
   */
  public FolderPermissionLevel getPermissionLevel() {
    return this.permissionLevel;
  }

  /**
   * Sets the permission level.
   *
   * @param value the new permission level
   * @throws ServiceLocalException the service local exception
   */
  public void setPermissionLevel(FolderPermissionLevel value)
      throws ServiceLocalException {
    if (this.permissionLevel != value) {
      if (value == FolderPermissionLevel.Custom) {
        throw new ServiceLocalException(
            "The PermissionLevel property can't be set to FolderPermissionLevel.Custom. "
            + "To define a custom permission, set its individual property to the values you want.");
      }

      this.AssignIndividualPermissions(defaultPermissions.getMember()
          .get(value));
      if (this.canSetFieldValue(this.permissionLevel, value)) {
        this.permissionLevel = value;
        this.changed();
      }
    }
  }

  /**
   * Gets the permission level that Outlook would display for this folder
   * permission.
   *
   * @return the display permission level
   */
  public FolderPermissionLevel getDisplayPermissionLevel() {
    // If permission level is set to Custom, see if there's a variant
    // that Outlook would map to the same permission level.
    if (this.permissionLevel == FolderPermissionLevel.Custom) {
      for (FolderPermission variant : FolderPermission.levelVariants
          .getMember()) {
        if (this.isEqualTo(variant)) {
          return variant.getPermissionLevel();
        }
      }
    }

    return this.permissionLevel;
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader the reader
   * @return True if element was read.
   * @throws Exception the exception
   */
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.UserId)) {
      this.userId = new UserId();
      this.userId.loadFromXml(reader, reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.CanCreateItems)) {
      this.canCreateItems = reader.readValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.CanCreateSubFolders)) {
      this.canCreateSubFolders = reader.readValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.IsFolderOwner)) {
      this.isFolderOwner = reader.readValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.IsFolderVisible)) {
      this.isFolderVisible = reader.readValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.IsFolderContact)) {
      this.isFolderContact = reader.readValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.EditItems)) {
      this.editItems = reader.readValue(PermissionScope.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.DeleteItems)) {
      this.deleteItems = reader.readValue(PermissionScope.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.ReadItems)) {
      this.readItems = reader.readValue(FolderPermissionReadAccess.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.PermissionLevel)
        || reader.getLocalName().equalsIgnoreCase(
        XmlElementNames.CalendarPermissionLevel)) {
      this.permissionLevel = reader
          .readValue(FolderPermissionLevel.class);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Loads from XML.
   *
   * @param reader         the reader
   * @param xmlNamespace   the xml namespace
   * @param xmlElementName the xml element name
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader, XmlNamespace xmlNamespace, String xmlElementName) throws Exception {
    super.loadFromXml(reader, xmlNamespace, xmlElementName);

    this.AdjustPermissionLevel();
  }

  /**
   * Writes elements to XML.
   *
   * @param writer           the writer
   * @param isCalendarFolder the is calendar folder
   * @throws Exception the exception
   */
  protected void writeElementsToXml(EwsServiceXmlWriter writer,
      boolean isCalendarFolder) throws Exception {
    if (this.userId != null) {
      this.userId.writeToXml(writer, XmlElementNames.UserId);
    }

    if (this.permissionLevel == FolderPermissionLevel.Custom) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.CanCreateItems, this.canCreateItems);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.CanCreateSubFolders,
          this.canCreateSubFolders);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.IsFolderOwner, this.isFolderOwner);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.IsFolderVisible, this.isFolderVisible);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.IsFolderContact, this.isFolderContact);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.EditItems, this.editItems);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.DeleteItems, this.deleteItems);

      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.ReadItems, this.readItems);
    }

    writer
        .writeElementValue(
            XmlNamespace.Types,
            isCalendarFolder ? XmlElementNames.
                CalendarPermissionLevel
                : XmlElementNames.PermissionLevel,
            this.permissionLevel);
  }

  /**
   * Writes to XML.
   *
   * @param writer           the writer
   * @param xmlElementName   the xml element name
   * @param isCalendarFolder the is calendar folder
   * @throws Exception the exception
   */
  protected void writeToXml(EwsServiceXmlWriter writer,
      String xmlElementName, boolean isCalendarFolder) throws Exception {
    writer.writeStartElement(this.getNamespace(), xmlElementName);
    this.writeAttributesToXml(writer);
    this.writeElementsToXml(writer, isCalendarFolder);
    writer.writeEndElement();
  }
}
