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

package microsoft.exchange.webservices.data.core.enumeration.service;

import microsoft.exchange.webservices.data.attribute.EwsEnum;
import microsoft.exchange.webservices.data.attribute.RequiredServerVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;

/**
 * Defines the way the FileAs property of a contact is automatically formatted.
 */
public enum FileAsMapping {
  // No automatic formatting is used.
  /**
   * The None.
   */
  None,

  // Surname, GivenName
  /**
   * The Surname comma given name.
   */
  @EwsEnum(schemaName = "LastCommaFirst")
  SurnameCommaGivenName,

  // GivenName Surname
  /**
   * The Given name space surname.
   */
  @EwsEnum(schemaName = "FirstSpaceLast")
  GivenNameSpaceSurname,

  // Company
  /**
   * The Company.
   */
  Company,

  // Surname, GivenName (Company)
  /**
   * The Surname comma given name company.
   */
  @EwsEnum(schemaName = "LastCommaFirstCompany")
  SurnameCommaGivenNameCompany,

  // Company (SurnameGivenName)
  /**
   * The Company surname given name.
   */
  @EwsEnum(schemaName = "CompanyLastFirst")
  CompanySurnameGivenName,

  // SurnameGivenName
  /**
   * The Surname given name.
   */
  @EwsEnum(schemaName = "LastFirst")
  SurnameGivenName,

  // SurnameGivenName (Company)
  /**
   * The Surname given name company.
   */
  @EwsEnum(schemaName = "LastFirstCompany")
  SurnameGivenNameCompany,

  // Company (Surname, GivenName)
  /**
   * The Company surname comma given name.
   */
  @EwsEnum(schemaName = "CompanyLastCommaFirst")
  CompanySurnameCommaGivenName,

  // SurnameGivenName Suffix
  /**
   * The Surname given name suffix.
   */
  @EwsEnum(schemaName = "LastFirstSuffix")
  SurnameGivenNameSuffix,

  // Surname GivenName (Company)
  /**
   * The Surname space given name company.
   */
  @EwsEnum(schemaName = "LastSpaceFirstCompany")
  SurnameSpaceGivenNameCompany,

  // Company (Surname GivenName)
  /**
   * The Company surname space given name.
   */
  @EwsEnum(schemaName = "CompanyLastSpaceFirst")
  CompanySurnameSpaceGivenName,

  // Surname GivenName
  /**
   * The Surname space given name.
   */
  @EwsEnum(schemaName = "LastSpaceFirst")
  SurnameSpaceGivenName,

  // Display Name (Exchange 2010 or later).
  /**
   * The Display name.
   */
  @RequiredServerVersion(version = ExchangeVersion.Exchange2010)
  DisplayName,

  // GivenName (Exchange 2010 or later).
  /**
   * The Given name.
   */
  @RequiredServerVersion(version = ExchangeVersion.Exchange2010)
  @EwsEnum(schemaName = "FirstName")
  GivenName,

  // Surname GivenName Middle Suffix (Exchange 2010 or later).
  /**
   * The Surname given name middle suffix.
   */
  @RequiredServerVersion(version = ExchangeVersion.Exchange2010)
  @EwsEnum(schemaName = "LastFirstMiddleSuffix")
  SurnameGivenNameMiddleSuffix,

  // Surname (Exchange 2010 or later).
  /**
   * The Surname.
   */
  @RequiredServerVersion(version = ExchangeVersion.Exchange2010)
  @EwsEnum(schemaName = "LastName")
  Surname,

  // Empty (Exchange 2010 or later).
  /**
   * The Empty.
   */
  @RequiredServerVersion(version = ExchangeVersion.Exchange2010)
  Empty
}
