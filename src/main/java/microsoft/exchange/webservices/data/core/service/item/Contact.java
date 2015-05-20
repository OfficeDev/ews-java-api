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

package microsoft.exchange.webservices.data.core.service.item;

import microsoft.exchange.webservices.data.attribute.Attachable;
import microsoft.exchange.webservices.data.attribute.ServiceObjectDefinition;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.schema.ContactSchema;
import microsoft.exchange.webservices.data.core.service.schema.ServiceObjectSchema;
import microsoft.exchange.webservices.data.core.enumeration.service.ContactSource;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.FileAsMapping;
import microsoft.exchange.webservices.data.core.enumeration.property.PhysicalAddressIndex;
import microsoft.exchange.webservices.data.core.exception.service.local.PropertyException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceVersionException;
import microsoft.exchange.webservices.data.misc.OutParam;
import microsoft.exchange.webservices.data.property.complex.Attachment;
import microsoft.exchange.webservices.data.property.complex.ByteArrayArray;
import microsoft.exchange.webservices.data.property.complex.CompleteName;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.EmailAddressCollection;
import microsoft.exchange.webservices.data.property.complex.EmailAddressDictionary;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
import microsoft.exchange.webservices.data.property.complex.ImAddressDictionary;
import microsoft.exchange.webservices.data.property.complex.ItemAttachment;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.complex.PhoneNumberDictionary;
import microsoft.exchange.webservices.data.property.complex.PhysicalAddressDictionary;
import microsoft.exchange.webservices.data.property.complex.StringList;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

/**
 * Represents a contact. Properties available on contacts are defined in the
 * ContactSchema class.
 */
@Attachable
@ServiceObjectDefinition(xmlElementName = XmlElementNames.Contact, returnedByServer = true)
public class Contact extends Item {

  /**
   * The Contact picture name.
   */
  private final String ContactPictureName = "ContactPicture.jpg";

  /**
   * Initializes an unsaved local instance of {@link Contact}.
   * To bind to an existing contact, use Contact.Bind() instead.
   *
   * @param service the service
   * @throws Exception the exception
   */
  public Contact(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Initializes a new instance of the {@link Contact} class.
   *
   * @param parentAttachment the parent attachment
   * @throws Exception the exception
   */
  public Contact(ItemAttachment parentAttachment) throws Exception {
    super(parentAttachment);
  }

  /**
   * Binds to an existing contact and loads the specified set of property.
   * Calling this method results in a call to EWS.
   *
   * @param service     the service
   * @param id          the id
   * @param propertySet the property set
   * @return A Contact instance representing the contact corresponding to the
   * specified Id.
   * @throws Exception the exception
   */
  public static Contact bind(ExchangeService service, ItemId id,
      PropertySet propertySet) throws Exception {
    return service.bindToItem(Contact.class, id, propertySet);
  }

  /**
   * Binds to an existing contact and loads its first class property.
   * Calling this method results in a call to EWS.
   *
   * @param service the service
   * @param id      the id
   * @return A Contact instance representing the contact corresponding to the
   * specified Id.
   * @throws Exception the exception
   */
  public static Contact bind(ExchangeService service, ItemId id)
      throws Exception {
    return Contact.bind(service, id, PropertySet.getFirstClassProperties());
  }

  /**
   * Internal method to return the schema associated with this type of object.
   *
   * @return The schema associated with this type of object.
   */
  @Override public ServiceObjectSchema getSchema() {
    return ContactSchema.Instance;
  }

  /**
   * Gets the minimum required server version.
   *
   * @return Earliest Exchange version in which this service object type is
   * supported.
   */
  @Override public ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Sets the contact's picture using the specified byte array.
   *
   * @param content the new contact picture
   * @throws Exception the exception
   */
  public void setContactPicture(byte[] content) throws Exception {
    EwsUtilities.validateMethodVersion(this.getService(), ExchangeVersion.Exchange2010, "SetContactPicture");

    internalRemoveContactPicture();
    FileAttachment fileAttachment = getAttachments().addFileAttachment(
        ContactPictureName, content);
    fileAttachment.setIsContactPhoto(true);
  }

  /**
   * Sets the contact's picture using the specified stream.
   *
   * @param contentStream the new contact picture
   * @throws Exception the exception
   */
  public void setContactPicture(InputStream contentStream) throws Exception {
    EwsUtilities.validateMethodVersion(this.getService(),
        ExchangeVersion.Exchange2010, "SetContactPicture");

    internalRemoveContactPicture();
    FileAttachment fileAttachment = getAttachments().addFileAttachment(
        ContactPictureName, contentStream);
    fileAttachment.setIsContactPhoto(true);
  }

  /**
   * Sets the contact's picture using the specified file.
   *
   * @param fileName the new contact picture
   * @throws Exception the exception
   */
  public void setContactPicture(String fileName) throws Exception {
    EwsUtilities.validateMethodVersion(this.getService(),
        ExchangeVersion.Exchange2010, "SetContactPicture");

    internalRemoveContactPicture();
    FileAttachment fileAttachment = getAttachments().addFileAttachment(
        new File(fileName).getName(), fileName);
    fileAttachment.setIsContactPhoto(true);
  }

  /**
   * Retrieves the file attachment that holds the contact's picture.
   *
   * @return The file attachment that holds the contact's picture.
   * @throws ServiceLocalException the service local exception
   */
  public FileAttachment getContactPictureAttachment()
      throws ServiceLocalException {
    EwsUtilities.validateMethodVersion(this.getService(),
        ExchangeVersion.Exchange2010, "GetContactPictureAttachment");

    if (!this.getPropertyBag().isPropertyLoaded(ContactSchema.Attachments)) {
      throw new PropertyException("The attachment collection must be loaded.");
    }

    for (Attachment fileAttachment : this.getAttachments()) {
      if (fileAttachment instanceof FileAttachment) {
        if (((FileAttachment) fileAttachment).isContactPhoto()) {
          return (FileAttachment) fileAttachment;
        }
      }
    }
    return null;
  }

  /**
   * Removes the picture from local attachment collection.
   *
   * @throws Exception the exception
   */
  private void internalRemoveContactPicture() throws Exception {
    // Iterates in reverse order to remove file attachments that have
    // IsContactPhoto set to true.
    for (int index = this.getAttachments().getCount() - 1; index >= 0; index--) {
      FileAttachment fileAttachment = (FileAttachment) this
          .getAttachments().getPropertyAtIndex(index);
      if (fileAttachment != null) {
        if (fileAttachment.isContactPhoto()) {
          this.getAttachments().remove(fileAttachment);
        }
      }
    }

  }

  /**
   * Removes the contact's picture.
   *
   * @throws Exception the exception
   */
  public void removeContactPicture() throws Exception {
    EwsUtilities.validateMethodVersion(this.getService(),
        ExchangeVersion.Exchange2010, "RemoveContactPicture");

    if (!this.getPropertyBag().isPropertyLoaded(ContactSchema.Attachments)) {
      throw new PropertyException("The attachment collection must be loaded.");
    }

    internalRemoveContactPicture();
  }

  /**
   * Validates this instance.
   *
   * @throws ServiceVersionException the service version exception
   * @throws Exception               the exception
   */
  @Override public void validate() throws ServiceVersionException, Exception {
    super.validate();

    Object fileAsMapping;
    OutParam<Object> outParam = new OutParam<Object>();
    if (this.tryGetProperty(ContactSchema.FileAsMapping, outParam)) {
      fileAsMapping = outParam.getParam();
      // FileAsMapping is extended by 5 new values in 2010 mode. Validate
      // that they are used according the version.
      EwsUtilities.validateEnumVersionValue(
          (FileAsMapping) fileAsMapping, this.getService()
              .getRequestedServerVersion());
    }
  }

  /**
   * Gets  the name under which this contact is filed as. FileAs can be
   * manually set or can be automatically calculated based on the value of the
   * FileAsMapping property.
   *
   * @return the file as
   * @throws ServiceLocalException the service local exception
   */
  public String getFileAs() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.FileAs);

  }

  /**
   * Sets the file as.
   *
   * @param value the new file as
   * @throws Exception the exception
   */
  public void setFileAs(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.FileAs, value);
  }

  /**
   * Gets a value indicating how the FileAs property should be
   * automatically calculated.
   *
   * @return the file as mapping
   * @throws ServiceLocalException the service local exception
   */
  public FileAsMapping getFileAsMapping() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(ContactSchema.FileAsMapping);
  }

  /**
   * Sets the file as.
   *
   * @param value the new file as
   * @throws Exception the exception
   */
  public void setFileAs(FileAsMapping value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.FileAsMapping, value);
  }

  /**
   * Gets the display name of the contact.
   *
   * @return the display name
   * @throws ServiceLocalException the service local exception
   */
  public String getDisplayName() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.DisplayName);
  }

  /**
   * Sets the display name.
   *
   * @param value the new display name
   * @throws Exception the exception
   */
  public void setDisplayName(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.DisplayName, value);
  }

  /**
   * Gets  the given name of the contact.
   *
   * @return the given name
   * @throws ServiceLocalException the service local exception
   */
  public String getGivenName() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.GivenName);
  }

  /**
   * Sets the given name.
   *
   * @param value the new given name
   * @throws Exception the exception
   */
  public void setGivenName(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.GivenName, value);
  }

  /**
   * Gets  the initials of the contact.
   *
   * @return the initials
   * @throws ServiceLocalException the service local exception
   */
  public String getInitials() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.Initials);
  }

  /**
   * Sets the initials.
   *
   * @param value the new initials
   * @throws Exception the exception
   */
  public void setInitials(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.Initials, value);
  }

  /**
   * Gets the middle name of the contact.
   *
   * @return the middle name
   * @throws ServiceLocalException the service local exception
   */
  public String getMiddleName() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.MiddleName);
  }

  /**
   * Sets the middle name.
   *
   * @param value the new middle name
   * @throws Exception the exception
   */
  public void setMiddleName(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.MiddleName, value);
  }

  /**
   * Gets the nick name of the contact.
   *
   * @return the nick name
   * @throws ServiceLocalException the service local exception
   */
  public String getNickName() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.NickName);
  }

  /**
   * Sets the nick name.
   *
   * @param value the new nick name
   * @throws Exception the exception
   */
  public void setNickName(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.NickName, value);
  }

  /**
   * Gets the complete name of the contact.
   *
   * @return the complete name
   * @throws ServiceLocalException the service local exception
   */
  public CompleteName getCompleteName() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.CompleteName);
  }

  /**
   * Gets  the company name of the contact.
   *
   * @return the company name
   * @throws ServiceLocalException the service local exception
   */
  public String getCompanyName() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.CompanyName);
  }

  /**
   * Sets the company name.
   *
   * @param value the new company name
   * @throws Exception the exception
   */
  public void setCompanyName(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.CompanyName, value);
  }

  /**
   * Gets an indexed list of e-mail addresses for the contact. For example, to
   * set the first e-mail address, use the following syntax:
   * EmailAddresses[EmailAddressKey.EmailAddress1] = "john.doe@contoso.com"
   *
   * @return the email addresses
   * @throws ServiceLocalException the service local exception
   */
  public EmailAddressDictionary getEmailAddresses()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(ContactSchema.EmailAddresses);
  }

  /**
   * Gets an indexed list of physical addresses for the contact. For example,
   * to set the first business address, use the following syntax:
   * physical[PhysicalAddressKey.Business] = new PhysicalAddressEntry()
   *
   * @return the physical addresses
   * @throws ServiceLocalException the service local exception
   */
  public PhysicalAddressDictionary getPhysicalAddresses()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(ContactSchema.PhysicalAddresses);
  }

  /**
   * Gets an indexed list of phone numbers for the contact. For example, to
   * set the home phone number, use the following syntax:
   * PhoneNumbers[PhoneNumberKey.HomePhone] = "phone number"
   *
   * @return the phone numbers
   * @throws ServiceLocalException the service local exception
   */
  public PhoneNumberDictionary getPhoneNumbers()
      throws ServiceLocalException {
    return getPropertyBag()
        .getObjectFromPropertyDefinition(ContactSchema.PhoneNumbers);
  }

  /**
   * Gets the contact's assistant name.
   *
   * @return the assistant name
   * @throws ServiceLocalException the service local exception
   */
  public String getAssistantName() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.AssistantName);
  }

  /**
   * Sets the assistant name.
   *
   * @param value the new assistant name
   * @throws Exception the exception
   */
  public void setAssistantName(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.AssistantName, value);
  }

  /**
   * Gets  the contact's assistant name.
   *
   * @return the birthday
   * @throws ServiceLocalException the service local exception
   */
  public Date getBirthday() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.Birthday);

  }

  /**
   * Sets the birthday.
   *
   * @param value the new birthday
   * @throws Exception the exception
   */
  public void setBirthday(Date value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.Birthday, value);
  }

  /**
   * Gets the business home page of the contact.
   *
   * @return the business home page
   * @throws ServiceLocalException the service local exception
   */
  public String getBusinessHomePage() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.BusinessHomePage);

  }

  /**
   * Sets the business home page.
   *
   * @param value the new business home page
   * @throws Exception the exception
   */
  public void setBusinessHomePage(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.BusinessHomePage, value);
  }

  /**
   * Gets  a list of children for the contact.
   *
   * @return the children
   * @throws ServiceLocalException the service local exception
   */
  public StringList getChildren() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.Children);
  }

  /**
   * Sets the children.
   *
   * @param value the new children
   * @throws Exception the exception
   */
  public void setChildren(StringList value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.Children, value);
  }

  /**
   * Gets  a list of companies for the contact.
   *
   * @return the companies
   * @throws ServiceLocalException the service local exception
   */
  public StringList getCompanies() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.Companies);
  }

  /**
   * Sets the companies.
   *
   * @param value the new companies
   * @throws Exception the exception
   */
  public void setCompanies(StringList value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.Companies, value);
  }

  /**
   * Gets the source of the contact.
   *
   * @return the contact source
   * @throws ServiceLocalException the service local exception
   */
  public ContactSource getContactSource() throws ServiceLocalException {
    return getPropertyBag()
        .getObjectFromPropertyDefinition(ContactSchema.ContactSource);
  }

  /**
   * Gets  the department of the contact.
   *
   * @return the department
   * @throws ServiceLocalException the service local exception
   */
  public String getDepartment() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.Department);
  }

  /**
   * Sets the department.
   *
   * @param value the new department
   * @throws Exception the exception
   */
  public void setDepartment(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.Department, value);
  }

  /**
   * Gets  the generation of the contact.
   *
   * @return the generation
   * @throws ServiceLocalException the service local exception
   */
  public String getGeneration() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.Generation);
  }

  /**
   * Sets the generation.
   *
   * @param value the new generation
   * @throws Exception the exception
   */
  public void setGeneration(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.Generation, value);
  }

  /**
   * Gets an indexed list of Instant Messaging addresses for the contact. For
   * example, to set the first IM address, use the following syntax:
   * ImAddresses[ImAddressKey.ImAddress1] = "john.doe@contoso.com"
   *
   * @return the im addresses
   * @throws ServiceLocalException the service local exception
   */
  public ImAddressDictionary getImAddresses() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(ContactSchema.ImAddresses);
  }

  /**
   * Gets  the contact's job title.
   *
   * @return the job title
   * @throws ServiceLocalException the service local exception
   */
  public String getJobTitle() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.JobTitle);
  }

  /**
   * Sets the job title.
   *
   * @param value the new job title
   * @throws Exception the exception
   */
  public void setJobTitle(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.JobTitle, value);
  }

  /**
   * Gets the name of the contact's manager.
   *
   * @return the manager
   * @throws ServiceLocalException the service local exception
   */
  public String getManager() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.Manager);
  }

  /**
   * Sets the manager.
   *
   * @param value the new manager
   * @throws Exception the exception
   */
  public void setManager(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.Manager, value);
  }

  /**
   * Gets the mileage for the contact.
   *
   * @return the mileage
   * @throws ServiceLocalException the service local exception
   */
  public String getMileage() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.Mileage);
  }

  /**
   * Sets the mileage.
   *
   * @param value the new mileage
   * @throws Exception the exception
   */
  public void setMileage(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.Mileage, value);
  }

  /**
   * Gets  the location of the contact's office.
   *
   * @return the office location
   * @throws ServiceLocalException the service local exception
   */
  public String getOfficeLocation() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.OfficeLocation);
  }

  /**
   * Sets the office location.
   *
   * @param value the new office location
   * @throws Exception the exception
   */
  public void setOfficeLocation(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.OfficeLocation, value);
  }

  /**
   * Gets the index of the contact's postal address. When set,
   * PostalAddressIndex refers to an entry in the PhysicalAddresses indexed
   * list.
   *
   * @return the postal address index
   * @throws ServiceLocalException the service local exception
   */
  public PhysicalAddressIndex getPostalAddressIndex()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.PostalAddressIndex);
  }

  /**
   * Sets the postal address index.
   *
   * @param value the new postal address index
   * @throws Exception the exception
   */
  public void setPostalAddressIndex(PhysicalAddressIndex value)
      throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.PostalAddressIndex, value);
  }

  /**
   * Gets the contact's profession.
   *
   * @return the profession
   * @throws ServiceLocalException the service local exception
   */
  public String getProfession() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.Profession);
  }

  /**
   * Sets the profession.
   *
   * @param value the new profession
   * @throws Exception the exception
   */
  public void setProfession(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.Profession, value);
  }

  /**
   * Gets the name of the contact's spouse.
   *
   * @return the spouse name
   * @throws ServiceLocalException the service local exception
   */
  public String getSpouseName() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.SpouseName);
  }

  /**
   * Sets the spouse name.
   *
   * @param value the new spouse name
   * @throws Exception the exception
   */
  public void setSpouseName(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.SpouseName, value);
  }

  /**
   * Gets the surname of the contact.
   *
   * @return the surname
   * @throws ServiceLocalException the service local exception
   */
  public String getSurname() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.Surname);
  }

  /**
   * Sets the surname.
   *
   * @param value the new surname
   * @throws Exception the exception
   */
  public void setSurname(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.Surname, value);
  }

  /**
   * Gets the date of the contact's wedding anniversary.
   *
   * @return the wedding anniversary
   * @throws ServiceLocalException the service local exception
   */
  public Date getWeddingAnniversary() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.WeddingAnniversary);
  }

  /**
   * Sets the wedding anniversary.
   *
   * @param value the new wedding anniversary
   * @throws Exception the exception
   */
  public void setWeddingAnniversary(Date value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        ContactSchema.WeddingAnniversary, value);
  }

  /**
   * Gets a value indicating whether this contact has a picture associated
   * with it.
   *
   * @return the checks for picture
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getHasPicture() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        ContactSchema.HasPicture);
  }

  /**
   * Gets the funn phonetic name from the directory
   */
  public String getPhoneticFullName() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(ContactSchema.PhoneticFullName);
  }

  /**
   * Gets the funn phonetic name from the directory
   */
  public String getPhoneticFirstName() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(ContactSchema.PhoneticFirstName);
  }

  /**
   * Gets the phonetic last name from the directory
   *
   * @throws ServiceLocalException
   */
  public String getPhoneticLastName() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(ContactSchema.PhoneticLastName);
  }

  /**
   * Gets the Alias from the directory
   *
   * @throws ServiceLocalException
   */
  public String getAlias() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(ContactSchema.Alias);
  }

  /**
   * Get the Notes from the directory
   *
   * @throws ServiceLocalException
   */
  public String getNotes() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(ContactSchema.Notes);
  }

  /**
   * Gets the Photo from the directory
   *
   * @throws ServiceLocalException
   */
  public byte[] getDirectoryPhoto() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(ContactSchema.Photo);
  }

  /**
   * Gets the User SMIME certificate from the directory
   *
   * @throws ServiceLocalException
   */
  public byte[][] getUserSMIMECertificate() throws ServiceLocalException {
    ByteArrayArray array = this.getPropertyBag()
        .getObjectFromPropertyDefinition(ContactSchema.UserSMIMECertificate);
    return array.getContent();
  }

  /**
   * Gets the MSExchange certificate from the directory
   *
   * @throws ServiceLocalException
   */
  public byte[][] getMSExchangeCertificate() throws ServiceLocalException {
      ByteArrayArray array = getPropertyBag()
          .getObjectFromPropertyDefinition(ContactSchema.MSExchangeCertificate);
      return array.getContent();
  }

  /**
   * Gets the DirectoryID as Guid or DN string
   *
   * @throws ServiceLocalException
   */
  public String getDirectoryId() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(ContactSchema.DirectoryId);
  }

  /**
   * Gets the manager mailbox information
   *
   * @throws ServiceLocalException
   */
  public EmailAddress getManagerMailbox() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(ContactSchema.ManagerMailbox);
  }

  /**
   * Get the direct reports mailbox information
   *
   * @throws ServiceLocalException
   */
  public EmailAddressCollection getDirectReports() throws ServiceLocalException {
    return getPropertyBag()
        .getObjectFromPropertyDefinition(ContactSchema.DirectReports);
  }
}
