/**************************************************************************
 * copyright file="FileAsMapping.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the FileAsMapping.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Defines the way the FileAs property of a contact is automatically formatted.
 */
public enum FileAsMapping {
	// No automatic formatting is used.
	/** The None. */
	None,

	// Surname, GivenName
	/** The Surname comma given name. */
	@EwsEnum(schemaName = "LastCommaFirst")
	SurnameCommaGivenName,

	// GivenName Surname
	/** The Given name space surname. */
	@EwsEnum(schemaName = "FirstSpaceLast")
	GivenNameSpaceSurname,

	// Company
	/** The Company. */
	Company,

	// Surname, GivenName (Company)
	/** The Surname comma given name company. */
	@EwsEnum(schemaName = "LastCommaFirstCompany")
	SurnameCommaGivenNameCompany,

	// Company (SurnameGivenName)
	/** The Company surname given name. */
	@EwsEnum(schemaName = "CompanyLastFirst")
	CompanySurnameGivenName,

	// SurnameGivenName
	/** The Surname given name. */
	@EwsEnum(schemaName = "LastFirst")
	SurnameGivenName,

	// SurnameGivenName (Company)
	/** The Surname given name company. */
	@EwsEnum(schemaName = "LastFirstCompany")
	SurnameGivenNameCompany,

	// Company (Surname, GivenName)
	/** The Company surname comma given name. */
	@EwsEnum(schemaName = "CompanyLastCommaFirst")
	CompanySurnameCommaGivenName,

	// SurnameGivenName Suffix
	/** The Surname given name suffix. */
	@EwsEnum(schemaName = "LastFirstSuffix")
	SurnameGivenNameSuffix,

	// Surname GivenName (Company)
	/** The Surname space given name company. */
	@EwsEnum(schemaName = "LastSpaceFirstCompany")
	SurnameSpaceGivenNameCompany,

	// Company (Surname GivenName)
	/** The Company surname space given name. */
	@EwsEnum(schemaName = "CompanyLastSpaceFirst")
	CompanySurnameSpaceGivenName,

	// Surname GivenName
	/** The Surname space given name. */
	@EwsEnum(schemaName = "LastSpaceFirst")
	SurnameSpaceGivenName,

	// Display Name (Exchange 2010 or later).
	/** The Display name. */
	@RequiredServerVersion(version = ExchangeVersion.Exchange2010)
	DisplayName,

	// GivenName (Exchange 2010 or later).
	/** The Given name. */
	@RequiredServerVersion(version = ExchangeVersion.Exchange2010)
	@EwsEnum(schemaName = "FirstName")
	GivenName,

	// Surname GivenName Middle Suffix (Exchange 2010 or later).
	/** The Surname given name middle suffix. */
	@RequiredServerVersion(version = ExchangeVersion.Exchange2010)
	@EwsEnum(schemaName = "LastFirstMiddleSuffix")
	SurnameGivenNameMiddleSuffix,

	// Surname (Exchange 2010 or later).
	/** The Surname. */
	@RequiredServerVersion(version = ExchangeVersion.Exchange2010)
	@EwsEnum(schemaName = "LastName")
	Surname,

	// Empty (Exchange 2010 or later).
	/** The Empty. */
	@RequiredServerVersion(version = ExchangeVersion.Exchange2010)
	Empty
}
