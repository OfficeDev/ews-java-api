/**************************************************************************
 * copyright file="ExchangeServerInfo.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ExchangeServerInfo.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * 
 * Represents Exchange server information.
 * 
 */
public final class ExchangeServerInfo {

	/** The major version. */
	private int majorVersion;

	/** The minor version. */
	private int minorVersion;

	/** The major build number. */
	private int majorBuildNumber;

	/** The minor build number. */
	private int minorBuildNumber;

	/** The version string. */
	private String versionString;

	/*
	 * Default constructor
	 */
	/**
	 * Instantiates a new exchange server info.
	 */
	protected ExchangeServerInfo() {

	}

	/**
	 * * Parse current element to extract server information.
	 * 
	 * @param reader
	 *            EwsServiceXmlReader
	 * @return ExchangeServerInfo
	 * @throws Exception
	 *             the exception
	 */
	protected static ExchangeServerInfo parse(EwsServiceXmlReader reader)
		throws Exception {
		EwsUtilities.EwsAssert(reader.hasAttributes(),
				"ExchangeServerVersion.Parse",
				"Current element doesn't have attributes");

		ExchangeServerInfo info = new ExchangeServerInfo();
		info.majorVersion = reader.readAttributeValue(Integer.class,
				"MajorVersion");
		info.minorVersion = reader.readAttributeValue(Integer.class,
				"MinorVersion");
		info.majorBuildNumber = reader.readAttributeValue(Integer.class,
				"MajorBuildNumber");
		info.minorBuildNumber = reader.readAttributeValue(Integer.class,
				"MinorBuildNumber");
		info.versionString = reader.readAttributeValue("Version");
		return info;
	}

	/**
	 * Gets the Major Exchange server version number.
	 * 
	 * @return the major version
	 */
	public int getMajorVersion() {
		return this.majorVersion;
	}

	/**
	 * Sets the major version.
	 * 
	 * @param majorVersion
	 *            the new major version
	 */
	protected void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}

	/**
	 * Gets the Minor Exchange server version number.
	 * 
	 * @return the minor version
	 */
	public int getMinorVersion() {
		return minorVersion;
	}

	/**
	 * Sets the minor version.
	 * 
	 * @param minorVersion
	 *            the new minor version
	 */
	protected void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	/**
	 * Gets the Major Exchange server build number.
	 * 
	 * @return the major build number
	 */
	public int getMajorBuildNumber() {
		return majorBuildNumber;
	}

	/**
	 * Sets the major build number.
	 * 
	 * @param majorBuildNumber
	 *            the new major build number
	 */
	protected void setMajorBuildNumber(int majorBuildNumber) {
		this.majorBuildNumber = majorBuildNumber;
	}

	/**
	 * Gets the Minor Exchange server build number.
	 * 
	 * @return the minor build number
	 */
	public int getMinorBuildNumber() {
		return minorBuildNumber;
	}

	/**
	 * Sets the minor build number.
	 * 
	 * @param minorBuildNumber
	 *            the new minor build number
	 */
	protected void setMinorBuildNumber(int minorBuildNumber) {
		this.minorBuildNumber = minorBuildNumber;
	}

	/**
	 * Gets the Exchange server version string (e.g. "Exchange2010")
	 * 
	 * @return the version string
	 */
	// / The version is a string rather than an enum since its possible for the
	// client to
	// / be connected to a later server for which there would be no appropriate
	// enum value.
	public String getVersionString() {
		return versionString;
	}

	/**
	 * Sets the version string.
	 * 
	 * @param versionString
	 *            the new version string
	 */
	protected void setVersionString(String versionString) {
		this.versionString = versionString;
	}

	/**
	 * Override ToString method.
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {
		return String
				.format("%d,%2d,%4d,%3d", this.majorVersion, this.minorVersion,
						this.majorBuildNumber, this.minorBuildNumber);
	}
}
