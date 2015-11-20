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

package microsoft.exchange.webservices.data.autodiscover.response;

import microsoft.exchange.webservices.data.autodiscover.exception.error.DomainSettingError;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.autodiscover.enumeration.DomainSettingName;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.security.XmlNodeType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the response to a GetDomainSettings call for an individual domain.
 */
public final class GetDomainSettingsResponse extends AutodiscoverResponse {

  private static final Log LOG = LogFactory.getLog(GetDomainSettingsResponse.class);

  /**
   * The domain.
   */
  private String domain;

  /**
   * The redirect target.
   */
  private String redirectTarget;

  /**
   * The settings.
   */
  private Map<DomainSettingName, Object> settings;

  /**
   * The domain setting errors.
   */
  private Collection<DomainSettingError> domainSettingErrors;

  /**
   * Initializes a new instance of the {@link GetDomainSettingsResponse} class.
   */
  public GetDomainSettingsResponse() {
    super();
    this.domain = "";
    this.settings = new HashMap<DomainSettingName, Object>();
    this.domainSettingErrors = new ArrayList<DomainSettingError>();
  }

  /**
   * Gets the domain this response applies to.
   *
   * @return the domain
   */
  public String getDomain() {
    return this.domain;
  }

  /**
   * Sets the domain.
   *
   * @param value the new domain
   */
  public void setDomain(String value) {
    this.domain = value;
  }

  /**
   * Gets the redirectionTarget (URL or email address).
   *
   * @return the redirect target
   */
  public String getRedirectTarget() {
    return this.redirectTarget;
  }

  /**
   * Gets the requested settings for the domain.
   *
   * @return the settings
   */
  public Map<DomainSettingName, Object> getSettings() {
    return this.settings;
  }

  /**
   * Gets error information for settings that could not be returned.
   *
   * @return the domain setting errors
   */
  public Collection<DomainSettingError> getDomainSettingErrors() {
    return this.domainSettingErrors;
  }

  /**
   * Loads response from XML.
   *
   * @param reader         The reader.
   * @param endElementName End element name.
   * @throws Exception the exception
   */
  @Override public void loadFromXml(EwsXmlReader reader, String endElementName)
      throws Exception {
    do {
      reader.read();

      if (reader.getNodeType().nodeType == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName()
            .equals(XmlElementNames.RedirectTarget)) {
          this.redirectTarget = reader.readElementValue();
        } else if (reader.getLocalName().equals(
            XmlElementNames.DomainSettingErrors)) {
          this.loadDomainSettingErrorsFromXml(reader);
        } else if (reader.getLocalName().equals(
            XmlElementNames.DomainSettings)) {
          try {
            this.loadDomainSettingsFromXml(reader);
          } catch (Exception e) {
            LOG.error(e);
          }
        } else {
          super.loadFromXml(reader, endElementName);
          break;
        }
      }
    } while (!reader
        .isEndElement(XmlNamespace.Autodiscover, endElementName));
  }

  /**
   * Loads from XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  protected void loadDomainSettingsFromXml(EwsXmlReader reader)
      throws Exception {
    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if ((reader.getNodeType().nodeType == XmlNodeType.START_ELEMENT) &&
            (reader.getLocalName()
                .equals(XmlElementNames.DomainSetting))) {
          String settingClass = reader.readAttributeValue(
              XmlNamespace.XmlSchemaInstance,
              XmlAttributeNames.Type);

          if (settingClass
              .equals(XmlElementNames.DomainStringSetting)) {

            this.readSettingFromXml(reader);
          } else {
            EwsUtilities
                .ewsAssert(false, "GetDomainSettingsResponse." + "LoadDomainSettingsFromXml",
                           String.format("%s,%s", "Invalid setting " + "class '%s' returned", settingClass));
            break;
          }
        }
      } while (!reader.isEndElement(XmlNamespace.Autodiscover,
          XmlElementNames.DomainSettings));
    } else {
      reader.read();
    }
  }

  /**
   * Reads domain setting from XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  private void readSettingFromXml(EwsXmlReader reader) throws Exception {
    DomainSettingName name = null;
    Object value = null;

    do {
      reader.read();

      if (reader.getNodeType().nodeType == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName().equals(
            XmlElementNames.DomainStringSetting)) {
          name = reader.readElementValue(DomainSettingName.class);
        } else if (reader.getLocalName().equals(XmlElementNames.Value)) {
          value = reader.readElementValue();
        }
      }
    } while (!reader.isEndElement(XmlNamespace.Autodiscover,
        XmlElementNames.DomainSetting));

    EwsUtilities.ewsAssert(name != null, "GetDomainSettingsResponse.ReadSettingFromXml",
                           "Missing name element in domain setting");

    this.settings.put(name, value);
  }

  /**
   * Loads the domain setting errors.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  private void loadDomainSettingErrorsFromXml(EwsXmlReader reader)
      throws Exception {
    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if ((reader.getNodeType().nodeType == XmlNodeType.START_ELEMENT) &&
            (reader.getLocalName()
                .equals(XmlElementNames.DomainSettingError))) {
          DomainSettingError error = new DomainSettingError();
          error.loadFromXml(reader);
          domainSettingErrors.add(error);
        }
      } while (!reader.isEndElement(XmlNamespace.Autodiscover,
          XmlElementNames.DomainSettingErrors));
    } else {
      reader.read();
    }
  }
}
