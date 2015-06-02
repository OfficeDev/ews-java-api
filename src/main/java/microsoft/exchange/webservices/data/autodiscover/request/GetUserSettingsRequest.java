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

package microsoft.exchange.webservices.data.autodiscover.request;

import microsoft.exchange.webservices.data.autodiscover.AutodiscoverService;
import microsoft.exchange.webservices.data.autodiscover.enumeration.AutodiscoverErrorCode;
import microsoft.exchange.webservices.data.autodiscover.response.AutodiscoverResponse;
import microsoft.exchange.webservices.data.autodiscover.response.GetUserSettingsResponseCollection;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.ExchangeServiceBase;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.autodiscover.enumeration.UserSettingName;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

import java.net.URI;
import java.util.List;

/**
 * Represents a GetUserSettings request.
 */
public class GetUserSettingsRequest extends AutodiscoverRequest {

  /**
   * Action Uri of Autodiscover.GetUserSettings method.
   */
  private static final String GetUserSettingsActionUri = EwsUtilities.
      AutodiscoverSoapNamespace +
      "/Autodiscover/GetUserSettings";

  private List<String> smtpAddresses;
  private List<UserSettingName> settings;


  // Expect this request to return the partner token.

  private boolean expectPartnerToken = false;
  private String partnerTokenReference;
  private String partnerToken;

  /**
   * Initializes a new instance of the {@link GetUserSettingsRequest} class.
   *
   * @param service the service
   * @param url     the url
   * @throws ServiceValidationException on validation error
   */
  public GetUserSettingsRequest(AutodiscoverService service, URI url) throws ServiceValidationException {
    this(service, url, false);
  }

  /**
   * Initializes a new instance of the {@link GetUserSettingsRequest} class.
   *
   * @param service autodiscover service associated with this request
   * @param url URL of Autodiscover service
   * @param expectPartnerToken expect partner token or not
   * @throws ServiceValidationException on validation error
   */
  public GetUserSettingsRequest(AutodiscoverService service, URI url, boolean expectPartnerToken)
      throws ServiceValidationException {
    super(service, url);
    this.expectPartnerToken = expectPartnerToken;

    // make an explicit https check.
    if (expectPartnerToken && !url.getScheme().equalsIgnoreCase("https")) {
      throw new ServiceValidationException("Https is required.");
    }
  }

  /**
   * Validates the request.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();

    EwsUtilities.validateParam(this.getSmtpAddresses(), "smtpAddresses");
    EwsUtilities.validateParam(this.getSettings(), "settings");

    if (this.getSettings().size() == 0) {
      throw new ServiceValidationException("At least one setting must be requested.");
    }

    if (this.getSmtpAddresses().size() == 0) {
      throw new ServiceValidationException("At least one SMTP address must be requested.");
    }

    for (String smtpAddress : this.getSmtpAddresses()) {
      if (smtpAddress == null || smtpAddress.isEmpty()) {
        throw new ServiceValidationException("A valid SMTP address must be specified.");
      }
    }
  }

  /**
   * Executes this instance.
   *
   * @return the gets the user settings response collection
   * @throws Exception the exception
   */
  public GetUserSettingsResponseCollection execute() throws Exception {
    GetUserSettingsResponseCollection responses =
        (GetUserSettingsResponseCollection) this
            .internalExecute();
    if (responses.getErrorCode() == AutodiscoverErrorCode.NoError) {
      this.postProcessResponses(responses);
    }
    return responses;
  }

  /**
   * Post-process response to GetUserSettings.
   *
   * @param responses The GetUserSettings response.
   */
  private void postProcessResponses(
      GetUserSettingsResponseCollection responses) {
    // Note:The response collection may not include all of the requested
    // users if the request has been throttled.
    for (int index = 0; index < responses.getCount(); index++) {
      responses.getResponses().get(index).setSmtpAddress(
          this.getSmtpAddresses().get(index));
    }
  }

  /**
   * Gets the name of the request XML element.
   *
   * @return Request XML element name.
   */
  @Override
  protected String getRequestXmlElementName() {
    return XmlElementNames.GetUserSettingsRequestMessage;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return Response XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.GetUserSettingsResponseMessage;
  }

  /**
   * Gets the WS-Addressing action name.
   *
   * @return WS-Addressing action name.
   */
  @Override
  protected String getWsAddressingActionName() {
    return GetUserSettingsActionUri;
  }

  /**
   * Creates the service response.
   *
   * @return AutodiscoverResponse
   */
  @Override
  protected AutodiscoverResponse createServiceResponse() {
    return new GetUserSettingsResponseCollection();
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer The writer.
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue("xmlns",
        EwsUtilities.AutodiscoverSoapNamespacePrefix,
        EwsUtilities.AutodiscoverSoapNamespace);
  }

  /**
   * @param writer XML writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override public void writeExtraCustomSoapHeadersToXml(EwsServiceXmlWriter writer) throws XMLStreamException,
      ServiceXmlSerializationException {
    if (this.expectPartnerToken) {
      writer
          .writeElementValue(XmlNamespace.Autodiscover,
              XmlElementNames.BinarySecret,
              new String(org.apache.commons.codec.binary.Base64.
                  encodeBase64(ExchangeServiceBase.getSessionKey())));
    }
  }

  /**
   * Writes request to XML.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    writer.writeStartElement(XmlNamespace.Autodiscover,
        XmlElementNames.Request);

    writer.writeStartElement(XmlNamespace.Autodiscover,
        XmlElementNames.Users);

    for (String smtpAddress : this.getSmtpAddresses()) {
      writer.writeStartElement(XmlNamespace.Autodiscover,
          XmlElementNames.User);

      if (!(smtpAddress == null || smtpAddress.isEmpty())) {
        writer.writeElementValue(XmlNamespace.Autodiscover,
            XmlElementNames.Mailbox, smtpAddress);
      }
      writer.writeEndElement(); // User
    }
    writer.writeEndElement(); // Users

    writer.writeStartElement(XmlNamespace.Autodiscover,
        XmlElementNames.RequestedSettings);
    for (UserSettingName setting : this.getSettings()) {
      writer.writeElementValue(XmlNamespace.Autodiscover,
          XmlElementNames.Setting, setting);
    }

    writer.writeEndElement(); // RequestedSettings

    writer.writeEndElement(); // Request
  }

  /**
   * Read the partner token soap header.
   *
   * @param reader EWS XML reader
   * @throws Exception on error
   */
  @Override
  protected void readSoapHeader(EwsXmlReader reader) throws Exception {
    super.readSoapHeader(reader);

    if (this.expectPartnerToken) {
      if (reader.isStartElement(XmlNamespace.Autodiscover,
          XmlElementNames.PartnerToken)) {
        this.partnerToken = reader.readInnerXml();
      }

      if (reader.isStartElement(XmlNamespace.Autodiscover,
          XmlElementNames.PartnerTokenReference)) {
        partnerTokenReference = reader.readInnerXml();
      }
    }
  }

  /**
   * Gets the SMTP addresses.
   * @return the SMTP addresses
   */
  protected List<String> getSmtpAddresses() {
    return smtpAddresses;
  }

  /**
   * Sets the smtp addresses.
   * @param value the new smtp addresses
   */
  public void setSmtpAddresses(List<String> value) {
    this.smtpAddresses = value;
  }

  /**
   * Gets the settings.
   * @return the settings
   */
  protected List<UserSettingName> getSettings() {
    return settings;
  }

  /**
   * Sets the settings.
   *
   * @param value the new settings
   */
  public void setSettings(List<UserSettingName> value) {
    this.settings = value;

  }

  /**
   * Gets the partner token.
   * @return partner token
   */
  protected String getPartnerToken() {
    return partnerToken;
  }

  private void setPartnerToken(String value) {
    partnerToken = value;
  }

  /**
   * Gets the partner token reference.
   * @return partner token reference
   */
  protected String getPartnerTokenReference() {
    return partnerTokenReference;

  }

  private void setPartnerTokenReference(String tokenReference) {
    partnerTokenReference = tokenReference;
  }
}
