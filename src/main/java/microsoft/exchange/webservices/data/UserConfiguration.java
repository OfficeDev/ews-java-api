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

package microsoft.exchange.webservices.data;

import java.util.EnumSet;

import javax.xml.stream.XMLStreamException;

/**
 * Represents an object that can be used to store user-defined configuration settings.
 */
public class UserConfiguration {

  /**
   * The Constant PropertiesAvailableForNewObject.
   */
  private final static EnumSet<UserConfigurationProperties>
      PropertiesAvailableForNewObject =
      EnumSet.of(UserConfigurationProperties.BinaryData,
                 UserConfigurationProperties.Dictionary,
                 UserConfigurationProperties.XmlData);

  /**
   * For consistency with ServiceObject behavior, access to ItemId is
   * permitted for a new object.
   */
  /**
   * The object version.
   */
  private static ExchangeVersion ObjectVersion = ExchangeVersion.Exchange2010;
  /**
   * The No properties.
   */
  private final UserConfigurationProperties NoProperties =
      UserConfigurationProperties.values()[0];

  /**
   * The service.
   */
  private ExchangeService service;

  /**
   * The name.
   */
  private String name;

  /**
   * The parent folder id.
   */
  private FolderId parentFolderId = null;

  /**
   * The item id.
   */
  private ItemId itemId = null;

  /**
   * The dictionary.
   */
  private UserConfigurationDictionary dictionary = null;

  /**
   * The xml data.
   */
  private byte[] xmlData = null;

  /**
   * The binary data.
   */
  private byte[] binaryData = null;

  /**
   * The properties available for access.
   */
  private EnumSet<UserConfigurationProperties> propertiesAvailableForAccess;

  /**
   * The updated properties.
   */
  private EnumSet<UserConfigurationProperties> updatedProperties;

  /**
   * Indicates whether changes trigger an update or create operation.
   */
  private boolean isNew = false;

  /**
   * Initializes a new instance of <see cref="UserConfiguration"/> class.
   *
   * @param service The service to which the user configuration is bound.
   * @throws Exception the exception
   */
  public UserConfiguration(ExchangeService service) throws Exception {
    this(service, PropertiesAvailableForNewObject);
  }

  /**
   * Initializes a new instance of <see cref="UserConfiguration"/> class.
   *
   * @param service             The service to which the user configuration is bound.
   * @param requestedProperties The properties requested for this user configuration.
   * @throws Exception the exception
   */
  protected UserConfiguration(ExchangeService service,
                              EnumSet<UserConfigurationProperties> requestedProperties)
      throws Exception {
    EwsUtilities.validateParam(service, "service");

    if (service.getRequestedServerVersion().ordinal() < UserConfiguration.ObjectVersion.ordinal()) {
      throw new ServiceVersionException(String.format(
          Strings.ObjectTypeIncompatibleWithRequestVersion, this
              .getClass().getName(), UserConfiguration.ObjectVersion));
    }

    this.service = service;
    this.isNew = true;

    this.initializeProperties(requestedProperties);
  }

  /**
   * Writes a byte array to Xml.
   *
   * @param writer         The writer.
   * @param byteArray      Byte array to write.
   * @param xmlElementName Name of the Xml element.
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlSerializationException    the service xml serialization exception
   */
  private static void writeByteArrayToXml(EwsServiceXmlWriter writer,
                                          byte[] byteArray, String xmlElementName)
      throws XMLStreamException,
             ServiceXmlSerializationException {
    EwsUtilities.EwsAssert(writer != null,
                           "UserConfiguration.WriteByteArrayToXml", "writer is null");
    EwsUtilities.EwsAssert(xmlElementName != null,
                           "UserConfiguration.WriteByteArrayToXml",
                           "xmlElementName is null");

    writer.writeStartElement(XmlNamespace.Types, xmlElementName);

    if (byteArray != null && byteArray.length > 0) {
      writer.writeValue(Base64EncoderStream.encode(byteArray),
                        xmlElementName);
    }

    writer.writeEndElement();
  }

  /**
   * Writes to Xml.
   *
   * @param writer         The writer.
   * @param xmlNamespace   The XML namespace.
   * @param name           The user configuration name.
   * @param parentFolderId The Id of the folder containing the user configuration.
   * @throws Exception the exception
   */
  protected static void writeUserConfigurationNameToXml(
      EwsServiceXmlWriter writer, XmlNamespace xmlNamespace, String name,
      FolderId parentFolderId) throws Exception {
    EwsUtilities.EwsAssert(writer != null,
                           "UserConfiguration.WriteUserConfigurationNameToXml",
                           "writer is null");
    EwsUtilities.EwsAssert(name != null,
                           "UserConfiguration.WriteUserConfigurationNameToXml",
                           "name is null");
    EwsUtilities.EwsAssert(parentFolderId != null,
                           "UserConfiguration.WriteUserConfigurationNameToXml",
                           "parentFolderId is null");

    writer.writeStartElement(xmlNamespace,
                             XmlElementNames.UserConfigurationName);

    writer.writeAttributeValue(XmlAttributeNames.Name, name);

    parentFolderId.writeToXml(writer);

    writer.writeEndElement();
  }

  /**
   * Binds to an existing user configuration and loads the specified properties. Calling this method
   * results in a call to EWS.
   *
   * @param service        The service to which the user configuration is bound.
   * @param name           The name of the user configuration.
   * @param parentFolderId The Id of the folder containing the user configuration.
   * @param properties     The properties to load.
   * @return A user configuration instance.
   * @throws IndexOutOfBoundsException the index out of bounds exception
   * @throws Exception                 the exception
   */
  public static UserConfiguration bind(ExchangeService service, String name,
                                       FolderId parentFolderId,
                                       UserConfigurationProperties properties)
      throws IndexOutOfBoundsException, Exception {

    UserConfiguration result = service.getUserConfiguration(name,
                                                            parentFolderId, properties);
    result.isNew = false;
    return result;
  }

  /**
   * Binds to an existing user configuration and loads the specified properties.
   *
   * @param service          The service to which the user configuration is bound.
   * @param name             The name of the user configuration.
   * @param parentFolderName The name of the folder containing the user configuration.
   * @param properties       The properties to load.
   * @return A user configuration instance.
   * @throws IndexOutOfBoundsException the index out of bounds exception
   * @throws Exception                 the exception
   */
  public static UserConfiguration bind(ExchangeService service, String name,
                                       WellKnownFolderName parentFolderName,
                                       UserConfigurationProperties properties)
      throws IndexOutOfBoundsException, Exception {
    return UserConfiguration.bind(service, name, new FolderId(
        parentFolderName), properties);
  }

  /**
   * Gets the name of the user configuration.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name.
   *
   * @param value the new name
   */
  protected void setName(String value) {
    this.name = value;
  }

  /**
   * Gets the Id of the folder containing the user configuration.
   *
   * @return the parent folder id
   */
  public FolderId getParentFolderId() {
    return this.parentFolderId;
  }

  /**
   * Sets the parent folder id.
   *
   * @param value the new parent folder id
   */
  protected void setParentFolderId(FolderId value) {
    this.parentFolderId = value;
  }

  /**
   * Gets the Id of the user configuration.
   *
   * @return the item id
   */
  public ItemId getItemId() {
    return this.itemId;
  }

  /**
   * Gets the dictionary of the user configuration.
   *
   * @return the dictionary
   */
  public UserConfigurationDictionary getDictionary() {
    return this.dictionary;
  }

  /**
   * Gets the xml data of the user configuration.
   *
   * @return the xml data
   * @throws microsoft.exchange.webservices.data.PropertyException the property exception
   */
  public byte[] getXmlData() throws PropertyException {

    this.validatePropertyAccess(UserConfigurationProperties.XmlData);

    return this.xmlData;
  }

  /**
   * Sets the xml data.
   *
   * @param value the new xml data
   */
  public void setXmlData(byte[] value) {
    this.xmlData = value;

    this.markPropertyForUpdate(UserConfigurationProperties.XmlData);
  }

  /**
   * Gets the binary data of the user configuration.
   *
   * @return the binary data
   * @throws microsoft.exchange.webservices.data.PropertyException the property exception
   */
  public byte[] getBinaryData() throws PropertyException {
    this.validatePropertyAccess(UserConfigurationProperties.BinaryData);

    return this.binaryData;

  }

  /**
   * Sets the binary data.
   *
   * @param value the new binary data
   */
  public void setBinaryData(byte[] value) {
    this.binaryData = value;
    this.markPropertyForUpdate(UserConfigurationProperties.BinaryData);
  }

  /**
   * Gets a value indicating whether this user configuration has been modified.
   *
   * @return the checks if is dirty
   */
  public boolean getIsDirty() {
    return (!this.updatedProperties.contains(NoProperties))
           || this.dictionary.getIsDirty();
  }

  /**
   * Saves the user configuration. Calling this method results in a call to EWS.
   *
   * @param name           The name of the user configuration.
   * @param parentFolderId The Id of the folder in which to save the user configuration.
   * @throws Exception the exception
   */
  public void save(String name, FolderId parentFolderId) throws Exception {
    EwsUtilities.validateParam(name, "name");
    EwsUtilities.validateParam(parentFolderId, "parentFolderId");

    parentFolderId.validate(this.service.getRequestedServerVersion());

    if (!this.isNew) {
      throw new InvalidOperationException(
          Strings.CannotSaveNotNewUserConfiguration);
    }

    this.parentFolderId = parentFolderId;
    this.name = name;

    this.service.createUserConfiguration(this);

    this.isNew = false;

    this.resetIsDirty();
  }

  /**
   * Saves the user configuration. Calling this method results in a call to EWS.
   *
   * @param name             The name of the user configuration.
   * @param parentFolderName The name of the folder in which to save the user configuration.
   * @throws Exception the exception
   */
  public void save(String name, WellKnownFolderName parentFolderName)
      throws Exception {
    this.save(name, new FolderId(parentFolderName));
  }

  /**
   * Updates the user configuration by applying local changes to the Exchange server. Calling this
   * method results in a call to EWS
   *
   * @throws Exception the exception
   */

  public void update() throws Exception {
    if (this.isNew) {
      throw new InvalidOperationException(
          Strings.CannotUpdateNewUserConfiguration);
    }

    if (this.isPropertyUpdated(UserConfigurationProperties.BinaryData)
        || this
        .isPropertyUpdated(UserConfigurationProperties.
                               Dictionary)
        || this.isPropertyUpdated(UserConfigurationProperties.
                                      XmlData)) {

      this.service.updateUserConfiguration(this);
    }

    this.resetIsDirty();
  }

  /**
   * Deletes the user configuration. Calling this method results in a call to EWS.
   *
   * @throws Exception the exception
   */
  public void delete() throws Exception {
    if (this.isNew) {
      throw new InvalidOperationException(
          Strings.DeleteInvalidForUnsavedUserConfiguration);
    } else {
      this.service
          .deleteUserConfiguration(this.name, this.parentFolderId);
    }
  }

  /**
   * Loads the specified properties on the user configuration. Calling this method results in a call
   * to EWS.
   *
   * @param properties The properties to load.
   * @throws Exception the exception
   */
  public void load(UserConfigurationProperties properties) throws Exception {
    this.initializeProperties(EnumSet.of(properties));
    this.service.loadPropertiesForUserConfiguration(this, properties);
  }

  /**
   * Writes to XML.
   *
   * @param writer         The writer.
   * @param xmlNamespace   The XML namespace.
   * @param xmlElementName Name of the XML element.
   * @throws Exception the exception
   */
  protected void writeToXml(EwsServiceXmlWriter writer,
                            XmlNamespace xmlNamespace, String xmlElementName) throws Exception {
    EwsUtilities.EwsAssert(writer != null, "UserConfiguration.WriteToXml",
                           "writer is null");
    EwsUtilities.EwsAssert(xmlElementName != null,
                           "UserConfiguration.WriteToXml", "xmlElementName is null");

    writer.writeStartElement(xmlNamespace, xmlElementName);

    // Write the UserConfigurationName element
    writeUserConfigurationNameToXml(writer, XmlNamespace.Types, this.name,
                                    this.parentFolderId);

    // Write the Dictionary element
    if (this.isPropertyUpdated(UserConfigurationProperties.Dictionary)) {
      this.dictionary.writeToXml(writer, XmlElementNames.Dictionary);
    }

    // Write the XmlData element
    if (this.isPropertyUpdated(UserConfigurationProperties.XmlData)) {
      this.writeXmlDataToXml(writer);
    }

    // Write the BinaryData element
    if (this.isPropertyUpdated(UserConfigurationProperties.BinaryData)) {
      this.writeBinaryDataToXml(writer);
    }

    writer.writeEndElement();
  }

  /**
   * Determines whether the specified property was updated.
   *
   * @param property property to evaluate.
   * @return Boolean indicating whether to send the property Xml.
   */
  private boolean isPropertyUpdated(UserConfigurationProperties property) {
    boolean isPropertyDirty = false;
    boolean isPropertyEmpty = false;

    switch (property) {
      case Dictionary:
        isPropertyDirty = this.getDictionary().getIsDirty();
        isPropertyEmpty = this.getDictionary().getCount() == 0;
        break;
      case XmlData:
        isPropertyDirty = this.updatedProperties.contains(property);
        isPropertyEmpty = (this.xmlData == null) ||
                          (this.xmlData.length == 0);
        break;
      case BinaryData:
        isPropertyDirty = this.updatedProperties.contains(property);
        isPropertyEmpty = (this.binaryData == null) ||
                          (this.binaryData.length == 0);
        break;
      default:
        EwsUtilities.EwsAssert(false,
                               "UserConfiguration.IsPropertyUpdated",
                               "property not supported: " + property.toString());
        break;
    }

    // Consider the property updated, if it's been modified, and either
    // . there's a value or
    // . there's no value but the operation is update.
    return isPropertyDirty && ((!isPropertyEmpty) || (!this.isNew));
  }

  /**
   * Writes the XmlData property to Xml.
   *
   * @param writer The writer.
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlSerializationException    the service xml serialization exception
   */
  private void writeXmlDataToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    EwsUtilities.EwsAssert(writer != null,
                           "UserConfiguration.WriteXmlDataToXml", "writer is null");

    writeByteArrayToXml(writer, this.xmlData, XmlElementNames.XmlData);
  }

  /**
   * Writes the BinaryData property to Xml.
   *
   * @param writer The writer.
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlSerializationException    the service xml serialization exception
   */
  private void writeBinaryDataToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    EwsUtilities.EwsAssert(writer != null,
                           "UserConfiguration.WriteBinaryDataToXml", "writer is null");

    writeByteArrayToXml(writer, this.binaryData,
                        XmlElementNames.BinaryData);
  }


  /**
   * Loads from XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  protected void loadFromXml(EwsServiceXmlReader reader) throws Exception {
    EwsUtilities.EwsAssert(reader != null, "UserConfiguration.LoadFromXml",
                           "reader is null");

    reader.readStartElement(XmlNamespace.Messages,
                            XmlElementNames.UserConfiguration);
    reader.read(); // Position at first property element

    do {
      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName().equals(
            XmlElementNames.UserConfigurationName)) {
          String responseName = reader
              .readAttributeValue(XmlAttributeNames.Name);

          EwsUtilities.EwsAssert(this.name.equals(responseName),
                                 "UserConfiguration.LoadFromXml",
                                 "UserConfigurationName does not match: Expected: "
                                 + this.name + " Name in response: "
                                 + responseName);

          reader.skipCurrentElement();
        } else if (reader.getLocalName().equals(XmlElementNames.ItemId)) {
          this.itemId = new ItemId();
          this.itemId.loadFromXml(reader, XmlElementNames.ItemId);
        } else if (reader.getLocalName().equals(
            XmlElementNames.Dictionary)) {
          this.dictionary.loadFromXml(reader,
                                      XmlElementNames.Dictionary);
        } else if (reader.getLocalName()
            .equals(XmlElementNames.XmlData)) {
          this.xmlData = Base64EncoderStream.decode(reader
                                                        .readElementValue());
        } else if (reader.getLocalName().equals(
            XmlElementNames.BinaryData)) {
          this.binaryData = Base64EncoderStream.decode(reader
                                                           .readElementValue());
        } else {
          EwsUtilities.EwsAssert(false,
                                 "UserConfiguration.LoadFromXml",
                                 "Xml element not supported: "
                                 + reader.getLocalName());
        }
      }

      // If XmlData was loaded, read is skipped because GetXmlData
      // positions the reader at the next property.
      reader.read();
    } while (!reader.isEndElement(XmlNamespace.Messages,
                                  XmlElementNames.UserConfiguration));
  }

  /**
   * Initializes properties.
   *
   * @param requestedProperties The properties requested for this UserConfiguration.
   */
  // / InitializeProperties is called in 3 cases:
  // / . Create new object: From the UserConfiguration constructor.
  // / . Bind to existing object: Again from the constructor. The constructor
  // is called eventually by the GetUserConfiguration request.
  // / . Refresh properties: From the Load method.
  private void initializeProperties(
      EnumSet<UserConfigurationProperties> requestedProperties) {
    this.itemId = null;
    this.dictionary = new UserConfigurationDictionary();
    this.xmlData = null;
    this.binaryData = null;
    this.propertiesAvailableForAccess = requestedProperties;

    this.resetIsDirty();
  }

  /**
   * Resets flags to indicate that properties haven't been modified.
   */
  private void resetIsDirty() {
    try {
      this.updatedProperties = EnumSet.of(NoProperties);
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.dictionary.setIsDirty(false);
  }

  /**
   * Determines whether the specified property may be accessed.
   *
   * @param property Property to access.
   * @throws microsoft.exchange.webservices.data.PropertyException the property exception
   */
  private void validatePropertyAccess(UserConfigurationProperties property)
      throws PropertyException {
    if (!this.propertiesAvailableForAccess.contains(property)) {
      throw new PropertyException(
          Strings.MustLoadOrAssignPropertyBeforeAccess, property
          .toString());
    }
  }

  /**
   * Adds the passed property to updatedProperties.
   *
   * @param property Property to update.
   */
  private void markPropertyForUpdate(UserConfigurationProperties property) {
    this.updatedProperties.add(property);
    this.propertiesAvailableForAccess.add(property);

  }

}
