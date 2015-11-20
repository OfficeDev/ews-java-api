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

package microsoft.exchange.webservices.data.core.service.schema;

import microsoft.exchange.webservices.data.attribute.Schema;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.ContactSource;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.FileAsMapping;
import microsoft.exchange.webservices.data.core.enumeration.property.PhysicalAddressIndex;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.property.complex.ByteArrayArray;
import microsoft.exchange.webservices.data.property.complex.CompleteName;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.EmailAddressCollection;
import microsoft.exchange.webservices.data.property.complex.EmailAddressDictionary;
import microsoft.exchange.webservices.data.property.complex.ICreateComplexPropertyDelegate;
import microsoft.exchange.webservices.data.property.complex.ImAddressDictionary;
import microsoft.exchange.webservices.data.property.complex.PhoneNumberDictionary;
import microsoft.exchange.webservices.data.property.complex.PhysicalAddressDictionary;
import microsoft.exchange.webservices.data.property.complex.StringList;
import microsoft.exchange.webservices.data.property.definition.BoolPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.ByteArrayPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.ComplexPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.ContainedPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.DateTimePropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.GenericPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.IndexedPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.StringPropertyDefinition;

import java.util.EnumSet;

/**
 * Represents the schema for contacts.
 */
@Schema
public class ContactSchema extends ItemSchema {

  /**
   * FieldURIs for contacts.
   */
  private interface FieldUris {

    /**
     * The File as.
     */
    String FileAs = "contacts:FileAs";

    /**
     * The File as mapping.
     */
    String FileAsMapping = "contacts:FileAsMapping";

    /**
     * The Display name.
     */
    String DisplayName = "contacts:DisplayName";

    /**
     * The Given name.
     */
    String GivenName = "contacts:GivenName";

    /**
     * The Initials.
     */
    String Initials = "contacts:Initials";

    /**
     * The Middle name.
     */
    String MiddleName = "contacts:MiddleName";

    /**
     * The Nick name.
     */
    String NickName = "contacts:Nickname";

    /**
     * The Complete name.
     */
    String CompleteName = "contacts:CompleteName";

    /**
     * The Company name.
     */
    String CompanyName = "contacts:CompanyName";

    /**
     * The Email address.
     */
    String EmailAddress = "contacts:EmailAddress";

    /**
     * The Email addresses.
     */
    String EmailAddresses = "contacts:EmailAddresses";

    /**
     * The Physical addresses.
     */
    String PhysicalAddresses = "contacts:PhysicalAddresses";

    /**
     * The Phone number.
     */
    String PhoneNumber = "contacts:PhoneNumber";

    /**
     * The Phone numbers.
     */
    String PhoneNumbers = "contacts:PhoneNumbers";

    /**
     * The Assistant name.
     */
    String AssistantName = "contacts:AssistantName";

    /**
     * The Birthday.
     */
    String Birthday = "contacts:Birthday";

    /**
     * The Business home page.
     */
    String BusinessHomePage = "contacts:BusinessHomePage";

    /**
     * The Children.
     */
    String Children = "contacts:Children";

    /**
     * The Companies.
     */
    String Companies = "contacts:Companies";

    /**
     * The Contact source.
     */
    String ContactSource = "contacts:ContactSource";

    /**
     * The Department.
     */
    String Department = "contacts:Department";

    /**
     * The Generation.
     */
    String Generation = "contacts:Generation";

    /**
     * The Im address.
     */
    String ImAddress = "contacts:ImAddress";

    /**
     * The Im addresses.
     */
    String ImAddresses = "contacts:ImAddresses";

    /**
     * The Job title.
     */
    String JobTitle = "contacts:JobTitle";

    /**
     * The Manager.
     */
    String Manager = "contacts:Manager";

    /**
     * The Mileage.
     */
    String Mileage = "contacts:Mileage";

    /**
     * The Office location.
     */
    String OfficeLocation = "contacts:OfficeLocation";

    /**
     * The Physical address city.
     */
    String PhysicalAddressCity = "contacts:PhysicalAddress:City";

    /**
     * The Physical address country or region.
     */
    String PhysicalAddressCountryOrRegion =
        "contacts:PhysicalAddress:CountryOrRegion";

    /**
     * The Physical address state.
     */
    String PhysicalAddressState = "contacts:PhysicalAddress:State";

    /**
     * The Physical address street.
     */
    String PhysicalAddressStreet = "contacts:PhysicalAddress:Street";

    /**
     * The Physical address postal code.
     */
    String PhysicalAddressPostalCode =
        "contacts:PhysicalAddress:PostalCode";

    /**
     * The Postal address index.
     */
    String PostalAddressIndex = "contacts:PostalAddressIndex";

    /**
     * The Profession.
     */
    String Profession = "contacts:Profession";

    /**
     * The Spouse name.
     */
    String SpouseName = "contacts:SpouseName";

    /**
     * The Surname.
     */
    String Surname = "contacts:Surname";

    /**
     * The Wedding anniversary.
     */
    String WeddingAnniversary = "contacts:WeddingAnniversary";

    /**
     * The Has picture.
     */
    String HasPicture = "contacts:HasPicture";

    /**
     * The PhoneticFullName.
     */

    String PhoneticFullName = "contacts:PhoneticFullName";

    /**
     * The PhoneticFirstName.
     */

    String PhoneticFirstName = "contacts:PhonetiFirstName";

    /**
     * The PhoneticFirstName.
     */

    String PhoneticLastName = "contacts:PhonetiLastName";

    /**
     * The Aias.
     */

    String Alias = "contacts:Alias";

    /**
     * The Notes.
     */

    String Notes = "contacts:Notes";

    /**
     * The Photo.
     */

    String Photo = "contacts:Photo";

    /**
     * The UserSMIMECertificate.
     */

    String UserSMIMECertificate = "contacts:UserSMIMECertificate";

    /**
     * The MSExchangeCertificate.
     */

    String MSExchangeCertificate = "contacts:MSExchageCertificate";

    /**
     * The DirectoryId.
     */

    String DirectoryId = "contacts:DirectoryId";

    /**
     * The ManagerMailbox.
     */

    String ManagerMailbox = "contacts:ManagerMailbox";

    /**
     * The DirectReports.
     */

    String DirectReports = "contacts:DirectReports";
  }


  /**
   * Defines the FileAs property.
   */
  public static final PropertyDefinition FileAs =
      new StringPropertyDefinition(
          XmlElementNames.FileAs, FieldUris.FileAs, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the FileAsMapping property.
   */
  public static final PropertyDefinition FileAsMapping =
      new GenericPropertyDefinition<FileAsMapping>(
          FileAsMapping.class,
          XmlElementNames.FileAsMapping, FieldUris.FileAsMapping, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the DisplayName property.
   */
  public static final PropertyDefinition DisplayName =
      new StringPropertyDefinition(
          XmlElementNames.DisplayName, FieldUris.DisplayName, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the GivenName property.
   */
  public static final PropertyDefinition GivenName =
      new StringPropertyDefinition(
          XmlElementNames.GivenName, FieldUris.GivenName, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Initials property.
   */
  public static final PropertyDefinition Initials =
      new StringPropertyDefinition(
          XmlElementNames.Initials, FieldUris.Initials, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the MiddleName property.
   */
  public static final PropertyDefinition MiddleName =
      new StringPropertyDefinition(
          XmlElementNames.MiddleName, FieldUris.MiddleName, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the NickName property.
   */
  public static final PropertyDefinition NickName =
      new StringPropertyDefinition(
          XmlElementNames.NickName, FieldUris.NickName, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the CompleteName property.
   */
  public static final PropertyDefinition CompleteName =
      new ComplexPropertyDefinition<microsoft.exchange.webservices.data.property.complex.CompleteName>(
          CompleteName.class,
          XmlElementNames.CompleteName, FieldUris.CompleteName, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<CompleteName>() {
            @Override
            public CompleteName createComplexProperty() {
              return new CompleteName();
            }
          });

  /**
   * Defines the CompanyName property.
   */
  public static final PropertyDefinition CompanyName =
      new StringPropertyDefinition(
          XmlElementNames.CompanyName, FieldUris.CompanyName, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the EmailAddresses property.
   */
  public static final PropertyDefinition EmailAddresses =
      new ComplexPropertyDefinition<EmailAddressDictionary>(
          EmailAddressDictionary.class,
          XmlElementNames.EmailAddresses,
          FieldUris.EmailAddresses,
          EnumSet.of(PropertyDefinitionFlags.AutoInstantiateOnRead,
              PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate
              <EmailAddressDictionary>() {
            @Override
            public EmailAddressDictionary createComplexProperty() {
              return new EmailAddressDictionary();
            }
          });

  /**
   * Defines the PhysicalAddresses property.
   */
  public static final PropertyDefinition PhysicalAddresses =
      new ComplexPropertyDefinition<PhysicalAddressDictionary>(
          PhysicalAddressDictionary.class,
          XmlElementNames.PhysicalAddresses,
          FieldUris.PhysicalAddresses,
          EnumSet.of(PropertyDefinitionFlags.AutoInstantiateOnRead,
              PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate
              <PhysicalAddressDictionary>() {
            @Override
            public PhysicalAddressDictionary createComplexProperty() {
              return new PhysicalAddressDictionary();
            }
          });

  /**
   * Defines the PhoneNumbers property.
   */
  public static final PropertyDefinition PhoneNumbers =
      new ComplexPropertyDefinition<PhoneNumberDictionary>(
          PhoneNumberDictionary.class,
          XmlElementNames.PhoneNumbers,
          FieldUris.PhoneNumbers,
          EnumSet.of(PropertyDefinitionFlags.AutoInstantiateOnRead,
              PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate
              <PhoneNumberDictionary>() {
            @Override
            public PhoneNumberDictionary createComplexProperty() {
              return new PhoneNumberDictionary();
            }
          });

  /**
   * Defines the AssistantName property.
   */
  public static final PropertyDefinition AssistantName =
      new StringPropertyDefinition(
          XmlElementNames.AssistantName, FieldUris.AssistantName, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Birthday property.
   */
  public static final PropertyDefinition Birthday =
      new DateTimePropertyDefinition(
          XmlElementNames.Birthday, FieldUris.Birthday, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the BusinessHomePage property.
   * <p/>
   * Defined as anyURI in the EWS schema. String is fine here.
   */
  public static final PropertyDefinition BusinessHomePage =
      new StringPropertyDefinition(
          XmlElementNames.BusinessHomePage, FieldUris.BusinessHomePage,
          EnumSet.of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanDelete,
              PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Children property.
   */
  public static final PropertyDefinition Children =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.Children, FieldUris.Children, EnumSet.of(
          PropertyDefinitionFlags.AutoInstantiateOnRead,
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            @Override
            public StringList createComplexProperty() {
              return new StringList();
            }
          });

  /**
   * Defines the Companies property.
   */
  public static final PropertyDefinition Companies =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.Companies, FieldUris.Companies, EnumSet.of(
          PropertyDefinitionFlags.AutoInstantiateOnRead,
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            @Override
            public StringList createComplexProperty() {
              return new StringList();
            }
          });

  /**
   * Defines the ContactSource property.
   */
  public static final PropertyDefinition ContactSource =
      new GenericPropertyDefinition<ContactSource>(
          ContactSource.class,
          XmlElementNames.ContactSource, FieldUris.ContactSource, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Department property.
   */
  public static final PropertyDefinition Department =
      new StringPropertyDefinition(
          XmlElementNames.Department, FieldUris.Department, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Generation property.
   */
  public static final PropertyDefinition Generation =
      new StringPropertyDefinition(
          XmlElementNames.Generation, FieldUris.Generation, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the ImAddresses property.
   */
  public static final PropertyDefinition ImAddresses =
      new ComplexPropertyDefinition<ImAddressDictionary>(
          ImAddressDictionary.class,
          XmlElementNames.ImAddresses, FieldUris.ImAddresses, EnumSet.of(
          PropertyDefinitionFlags.AutoInstantiateOnRead,
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<ImAddressDictionary>() {
            @Override
            public ImAddressDictionary createComplexProperty() {
              return new ImAddressDictionary();
            }
          });

  /**
   * Defines the JobTitle property.
   */
  public static final PropertyDefinition JobTitle =
      new StringPropertyDefinition(
          XmlElementNames.JobTitle, FieldUris.JobTitle, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Manager property.
   */
  public static final PropertyDefinition Manager =
      new StringPropertyDefinition(
          XmlElementNames.Manager, FieldUris.Manager, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Mileage property.
   */
  public static final PropertyDefinition Mileage =
      new StringPropertyDefinition(
          XmlElementNames.Mileage, FieldUris.Mileage, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the OfficeLocation property.
   */
  public static final PropertyDefinition OfficeLocation =
      new StringPropertyDefinition(
          XmlElementNames.OfficeLocation, FieldUris.OfficeLocation, EnumSet
          .of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanDelete,
              PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the PostalAddressIndex property.
   */
  public static final PropertyDefinition PostalAddressIndex =
      new GenericPropertyDefinition<PhysicalAddressIndex>(
          PhysicalAddressIndex.class,
          XmlElementNames.PostalAddressIndex, FieldUris.PostalAddressIndex,
          EnumSet.of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanDelete,
              PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Profession property.
   */
  public static final PropertyDefinition Profession =
      new StringPropertyDefinition(
          XmlElementNames.Profession, FieldUris.Profession, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the SpouseName property.
   */
  public static final PropertyDefinition SpouseName =
      new StringPropertyDefinition(
          XmlElementNames.SpouseName, FieldUris.SpouseName, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Surname property.
   */
  public static final PropertyDefinition Surname =
      new StringPropertyDefinition(
          XmlElementNames.Surname, FieldUris.Surname, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the WeddingAnniversary property.
   */
  public static final PropertyDefinition WeddingAnniversary =
      new DateTimePropertyDefinition(
          XmlElementNames.WeddingAnniversary, FieldUris.WeddingAnniversary,
          EnumSet.of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanDelete,
              PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the HasPicture property.
   */
  public static final PropertyDefinition HasPicture =
      new BoolPropertyDefinition(
          XmlElementNames.HasPicture, FieldUris.HasPicture, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010);
  /**
   * Defines PhoeniticFullName property **
   */

  public static final PropertyDefinition PhoneticFullName =
      new StringPropertyDefinition(
          XmlElementNames.PhoneticFullName,
          FieldUris.PhoneticFullName,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines PhoenticFirstName property **
   */

  public static final PropertyDefinition PhoneticFirstName =
      new StringPropertyDefinition(
          XmlElementNames.PhoneticFirstName,
          FieldUris.PhoneticFirstName,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines PhoneticLastName Property **
   */

  public static final PropertyDefinition PhoneticLastName =
      new StringPropertyDefinition(
          XmlElementNames.PhoneticLastName,
          FieldUris.PhoneticLastName,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines the Alias Property  **
   */

  public static final PropertyDefinition Alias =
      new StringPropertyDefinition(
          XmlElementNames.Alias,
          FieldUris.Alias,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);


  /**
   * Defines the Notes Property **
   */

  public static final PropertyDefinition Notes =
      new StringPropertyDefinition(
          XmlElementNames.Notes,
          FieldUris.Notes,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines Photo Property   **
   */

  public static final PropertyDefinition Photo =
      new ByteArrayPropertyDefinition(
          XmlElementNames.Photo,
          FieldUris.Photo,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines UserSMIMECertificate Property **
   */

  public static final PropertyDefinition UserSMIMECertificate =
      new ComplexPropertyDefinition<ByteArrayArray>(
          ByteArrayArray.class,
          XmlElementNames.UserSMIMECertificate,
          FieldUris.UserSMIMECertificate,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<ByteArrayArray>() {
            @Override
            public ByteArrayArray createComplexProperty() {
              return new ByteArrayArray();
            }
          });

  /**
   * Defines MSExchangeCertificate Property **
   */

  public static final PropertyDefinition MSExchangeCertificate =
      new ComplexPropertyDefinition<ByteArrayArray>(
          ByteArrayArray.class,
          XmlElementNames.MSExchangeCertificate,
          FieldUris.MSExchangeCertificate,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<ByteArrayArray>() {
            @Override
            public ByteArrayArray createComplexProperty() {
              return new ByteArrayArray();
            }
          });


  /**
   * Defines DirectoryId Property **
   */

  public static final  PropertyDefinition DirectoryId =
      new StringPropertyDefinition(
          XmlElementNames.DirectoryId,
          FieldUris.DirectoryId,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1);

  /**
   * Defines ManagerMailbox Property **
   */

  public static final PropertyDefinition ManagerMailbox =
      new ContainedPropertyDefinition<EmailAddress>(
          EmailAddress.class,
          XmlElementNames.ManagerMailbox,
          FieldUris.ManagerMailbox,
          XmlElementNames.Mailbox,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<EmailAddress>() {
            @Override
            public EmailAddress createComplexProperty() {
              return new EmailAddress();
            }
          });

  /**
   * Defines DirectReports Property **
   */

  public static final PropertyDefinition DirectReports =
      new ComplexPropertyDefinition<EmailAddressCollection>(
          EmailAddressCollection.class,
          XmlElementNames.DirectReports,
          FieldUris.DirectReports,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2010_SP1,
          new ICreateComplexPropertyDelegate<EmailAddressCollection>() {
            @Override
            public EmailAddressCollection createComplexProperty()

            {
              return new EmailAddressCollection();
            }
          });



  /**
   * Defines the EmailAddress1 property.
   */
  public static final IndexedPropertyDefinition EmailAddress1 =
      new IndexedPropertyDefinition(
          FieldUris.EmailAddress, "EmailAddress1");

  /**
   * Defines the EmailAddress2 property.
   */
  public static final IndexedPropertyDefinition EmailAddress2 =
      new IndexedPropertyDefinition(
          FieldUris.EmailAddress, "EmailAddress2");

  /**
   * Defines the EmailAddress3 property.
   */
  public static final IndexedPropertyDefinition EmailAddress3 =
      new IndexedPropertyDefinition(
          FieldUris.EmailAddress, "EmailAddress3");

  /**
   * Defines the ImAddress1 property.
   */
  public static final IndexedPropertyDefinition ImAddress1 =
      new IndexedPropertyDefinition(
          FieldUris.ImAddress, "ImAddress1");

  /**
   * Defines the ImAddress2 property.
   */
  public static final IndexedPropertyDefinition ImAddress2 =
      new IndexedPropertyDefinition(
          FieldUris.ImAddress, "ImAddress2");

  /**
   * Defines the ImAddress3 property.
   */
  public static final IndexedPropertyDefinition ImAddress3 =
      new IndexedPropertyDefinition(
          FieldUris.ImAddress, "ImAddress3");

  /**
   * Defines the AssistentPhone property.
   */
  public static final IndexedPropertyDefinition AssistantPhone =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "AssistantPhone");

  /**
   * Defines the BusinessFax property.
   */
  public static final IndexedPropertyDefinition BusinessFax =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "BusinessFax");

  /**
   * Defines the BusinessPhone property.
   */
  public static final IndexedPropertyDefinition BusinessPhone =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "BusinessPhone");

  /**
   * Defines the BusinessPhone2 property.
   */
  public static final IndexedPropertyDefinition BusinessPhone2 =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "BusinessPhone2");

  /**
   * Defines the Callback property.
   */
  public static final IndexedPropertyDefinition Callback =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "Callback");

  /**
   * Defines the CarPhone property.
   */
  public static final IndexedPropertyDefinition CarPhone =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "CarPhone");

  /**
   * Defines the CompanyMainPhone property.
   */
  public static final IndexedPropertyDefinition CompanyMainPhone =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "CompanyMainPhone");

  /**
   * Defines the HomeFax property.
   */
  public static final IndexedPropertyDefinition HomeFax =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "HomeFax");

  /**
   * Defines the HomePhone property.
   */
  public static final IndexedPropertyDefinition HomePhone =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "HomePhone");

  /**
   * Defines the HomePhone2 property.
   */
  public static final IndexedPropertyDefinition HomePhone2 =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "HomePhone2");

  /**
   * Defines the Isdn property.
   */
  public static final IndexedPropertyDefinition Isdn =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "Isdn");

  /**
   * Defines the MobilePhone property.
   */
  public static final IndexedPropertyDefinition MobilePhone =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "MobilePhone");

  /**
   * Defines the OtherFax property.
   */
  public static final IndexedPropertyDefinition OtherFax =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "OtherFax");

  /**
   * Defines the OtherTelephone property.
   */
  public static final IndexedPropertyDefinition OtherTelephone =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "OtherTelephone");

  /**
   * Defines the Pager property.
   */
  public static final IndexedPropertyDefinition Pager =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "Pager");

  /**
   * Defines the PrimaryPhone property.
   */
  public static final IndexedPropertyDefinition PrimaryPhone =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "PrimaryPhone");

  /**
   * Defines the RadioPhone property.
   */
  public static final IndexedPropertyDefinition RadioPhone =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "RadioPhone");

  /**
   * Defines the Telex property.
   */
  public static final IndexedPropertyDefinition Telex =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "Telex");

  /**
   * Defines the TtyTddPhone property.
   */
  public static final IndexedPropertyDefinition TtyTddPhone =
      new IndexedPropertyDefinition(
          FieldUris.PhoneNumber, "TtyTddPhone");

  /**
   * Defines the BusinessAddressStreet property.
   */
  public static final IndexedPropertyDefinition BusinessAddressStreet =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressStreet, "Business");

  /**
   * Defines the BusinessAddressCity property.
   */
  public static final IndexedPropertyDefinition BusinessAddressCity =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressCity, "Business");

  /**
   * Defines the BusinessAddressState property.
   */
  public static final IndexedPropertyDefinition BusinessAddressState =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressState, "Business");

  /**
   * Defines the BusinessAddressCountryOrRegion property.
   */
  public static final IndexedPropertyDefinition
      BusinessAddressCountryOrRegion =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressCountryOrRegion, "Business");

  /**
   * Defines the BusinessAddressPostalCode property.
   */
  public static final IndexedPropertyDefinition BusinessAddressPostalCode =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressPostalCode, "Business");

  /**
   * Defines the HomeAddressStreet property.
   */
  public static final IndexedPropertyDefinition HomeAddressStreet =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressStreet, "Home");

  /**
   * Defines the HomeAddressCity property.
   */
  public static final IndexedPropertyDefinition HomeAddressCity =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressCity, "Home");

  /**
   * Defines the HomeAddressState property.
   */
  public static final IndexedPropertyDefinition HomeAddressState =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressState, "Home");

  /**
   * Defines the HomeAddressCountryOrRegion property.
   */
  public static final IndexedPropertyDefinition HomeAddressCountryOrRegion =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressCountryOrRegion, "Home");

  /**
   * Defines the HomeAddressPostalCode property.
   */
  public static final IndexedPropertyDefinition HomeAddressPostalCode =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressPostalCode, "Home");

  /**
   * Defines the OtherAddressStreet property.
   */
  public static final IndexedPropertyDefinition OtherAddressStreet =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressStreet, "Other");

  /**
   * Defines the OtherAddressCity property.
   */
  public static final IndexedPropertyDefinition OtherAddressCity =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressCity, "Other");

  /**
   * Defines the OtherAddressState property.
   */
  public static final IndexedPropertyDefinition OtherAddressState =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressState, "Other");

  /**
   * Defines the OtherAddressCountryOrRegion property.
   */
  public static final IndexedPropertyDefinition OtherAddressCountryOrRegion =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressCountryOrRegion, "Other");

  /**
   * Defines the OtherAddressPostalCode property.
   */
  public static final IndexedPropertyDefinition OtherAddressPostalCode =
      new IndexedPropertyDefinition(
          FieldUris.PhysicalAddressPostalCode, "Other");

  // This must be declared after the property definitions
  /**
   * The Constant Instance.
   */
  public static final ContactSchema Instance = new ContactSchema();

  /**
   * Registers property.
   * <p/>
   * IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
   * same order as they are defined in types.xsd)
   */
  @Override
  protected void registerProperties() {
    super.registerProperties();

    this.registerProperty(FileAs);
    this.registerProperty(FileAsMapping);
    this.registerProperty(DisplayName);
    this.registerProperty(GivenName);
    this.registerProperty(Initials);
    this.registerProperty(MiddleName);
    this.registerProperty(NickName);
    this.registerProperty(CompleteName);
    this.registerProperty(CompanyName);
    this.registerProperty(EmailAddresses);
    this.registerProperty(PhysicalAddresses);
    this.registerProperty(PhoneNumbers);
    this.registerProperty(AssistantName);
    this.registerProperty(Birthday);
    this.registerProperty(BusinessHomePage);
    this.registerProperty(Children);
    this.registerProperty(Companies);
    this.registerProperty(ContactSource);
    this.registerProperty(Department);
    this.registerProperty(Generation);
    this.registerProperty(ImAddresses);
    this.registerProperty(JobTitle);
    this.registerProperty(Manager);
    this.registerProperty(Mileage);
    this.registerProperty(OfficeLocation);
    this.registerProperty(PostalAddressIndex);
    this.registerProperty(Profession);
    this.registerProperty(SpouseName);
    this.registerProperty(Surname);
    this.registerProperty(WeddingAnniversary);
    this.registerProperty(HasPicture);
    this.registerProperty(PhoneticFullName);
    this.registerProperty(PhoneticFirstName);
    this.registerProperty(PhoneticLastName);
    this.registerProperty(Alias);
    this.registerProperty(Notes);
    this.registerProperty(Photo);
    this.registerProperty(UserSMIMECertificate);
    this.registerProperty(MSExchangeCertificate);
    this.registerProperty(DirectoryId);
    this.registerProperty(ManagerMailbox);
    this.registerProperty(DirectReports);

    this.registerIndexedProperty(EmailAddress1);
    this.registerIndexedProperty(EmailAddress2);
    this.registerIndexedProperty(EmailAddress3);
    this.registerIndexedProperty(ImAddress1);
    this.registerIndexedProperty(ImAddress2);
    this.registerIndexedProperty(ImAddress3);
    this.registerIndexedProperty(AssistantPhone);
    this.registerIndexedProperty(BusinessFax);
    this.registerIndexedProperty(BusinessPhone);
    this.registerIndexedProperty(BusinessPhone2);
    this.registerIndexedProperty(Callback);
    this.registerIndexedProperty(CarPhone);
    this.registerIndexedProperty(CompanyMainPhone);
    this.registerIndexedProperty(HomeFax);
    this.registerIndexedProperty(HomePhone);
    this.registerIndexedProperty(HomePhone2);
    this.registerIndexedProperty(Isdn);
    this.registerIndexedProperty(MobilePhone);
    this.registerIndexedProperty(OtherFax);
    this.registerIndexedProperty(OtherTelephone);
    this.registerIndexedProperty(Pager);
    this.registerIndexedProperty(PrimaryPhone);
    this.registerIndexedProperty(RadioPhone);
    this.registerIndexedProperty(Telex);
    this.registerIndexedProperty(TtyTddPhone);
    this.registerIndexedProperty(BusinessAddressStreet);
    this.registerIndexedProperty(BusinessAddressCity);
    this.registerIndexedProperty(BusinessAddressState);
    this.registerIndexedProperty(BusinessAddressCountryOrRegion);
    this.registerIndexedProperty(BusinessAddressPostalCode);
    this.registerIndexedProperty(HomeAddressStreet);
    this.registerIndexedProperty(HomeAddressCity);
    this.registerIndexedProperty(HomeAddressState);
    this.registerIndexedProperty(HomeAddressCountryOrRegion);
    this.registerIndexedProperty(HomeAddressPostalCode);
    this.registerIndexedProperty(OtherAddressStreet);
    this.registerIndexedProperty(OtherAddressCity);
    this.registerIndexedProperty(OtherAddressState);
    this.registerIndexedProperty(OtherAddressCountryOrRegion);
    this.registerIndexedProperty(OtherAddressPostalCode);

  }

  /**
   * Instantiates a new contact schema.
   */
  ContactSchema() {
    super();
  }
}
