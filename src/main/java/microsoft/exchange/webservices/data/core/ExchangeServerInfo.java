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

package microsoft.exchange.webservices.data.core;

/**
 * Represents Exchange server information.
 */
public final class ExchangeServerInfo {

  /**
   * The major version.
   */
  private int majorVersion;

  /**
   * The minor version.
   */
  private int minorVersion;

  /**
   * The major build number.
   */
  private int majorBuildNumber;

  /**
   * The minor build number.
   */
  private int minorBuildNumber;

  /**
   * The version string.
   */
  private String versionString;

	/*
         * Default constructor
	 */

  /**
   * Instantiates a new exchange server info.
   */
  public ExchangeServerInfo() {

  }

  /**
   * Parse current element to extract server information.
   *
   * @param reader EwsServiceXmlReader
   * @return ExchangeServerInfo
   * @throws Exception the exception
   */
  public static ExchangeServerInfo parse(EwsServiceXmlReader reader)
      throws Exception {
    EwsUtilities.ewsAssert(reader.hasAttributes(), "ExchangeServerVersion.Parse",
                           "Current element doesn't have attribute");

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
   * @param majorVersion the new major version
   */
  public void setMajorVersion(int majorVersion) {
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
   * @param minorVersion the new minor version
   */
  public void setMinorVersion(int minorVersion) {
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
   * @param majorBuildNumber the new major build number
   */
  public void setMajorBuildNumber(int majorBuildNumber) {
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
   * @param minorBuildNumber the new minor build number
   */
  public void setMinorBuildNumber(int minorBuildNumber) {
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
   * @param versionString the new version string
   */
  public void setVersionString(String versionString) {
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
