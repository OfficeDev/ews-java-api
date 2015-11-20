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

package microsoft.exchange.webservices.data.autodiscover;

import microsoft.exchange.webservices.data.autodiscover.configuration.ConfigurationSettingsBase;
import microsoft.exchange.webservices.data.autodiscover.configuration.outlook.OutlookConfigurationSettings;
import microsoft.exchange.webservices.data.autodiscover.enumeration.AutodiscoverEndpoints;
import microsoft.exchange.webservices.data.autodiscover.enumeration.AutodiscoverErrorCode;
import microsoft.exchange.webservices.data.autodiscover.exception.AutodiscoverLocalException;
import microsoft.exchange.webservices.data.autodiscover.exception.AutodiscoverRemoteException;
import microsoft.exchange.webservices.data.autodiscover.request.AutodiscoverRequest;
import microsoft.exchange.webservices.data.autodiscover.request.GetDomainSettingsRequest;
import microsoft.exchange.webservices.data.autodiscover.request.GetUserSettingsRequest;
import microsoft.exchange.webservices.data.autodiscover.response.GetDomainSettingsResponse;
import microsoft.exchange.webservices.data.autodiscover.response.GetDomainSettingsResponseCollection;
import microsoft.exchange.webservices.data.autodiscover.response.GetUserSettingsResponse;
import microsoft.exchange.webservices.data.autodiscover.response.GetUserSettingsResponseCollection;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.ExchangeServiceBase;
import microsoft.exchange.webservices.data.core.request.HttpClientWebRequest;
import microsoft.exchange.webservices.data.core.request.HttpWebRequest;
import microsoft.exchange.webservices.data.credential.WSSecurityBasedCredentials;
import microsoft.exchange.webservices.data.autodiscover.enumeration.DomainSettingName;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.TraceFlags;
import microsoft.exchange.webservices.data.autodiscover.enumeration.UserSettingName;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.http.EWSHttpException;
import microsoft.exchange.webservices.data.core.exception.misc.FormatException;
import microsoft.exchange.webservices.data.autodiscover.exception.MaximumRedirectionHopsExceededException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceVersionException;
import microsoft.exchange.webservices.data.misc.OutParam;
import microsoft.exchange.webservices.data.security.XmlNodeType;

import javax.xml.stream.XMLStreamException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

/**
 * Represents a binding to the Exchange Autodiscover Service.
 */
public class AutodiscoverService extends ExchangeServiceBase
    implements IAutodiscoverRedirectionUrl, IFunctionDelegate {

  // region Private members
  /**
   * The domain.
   */
  private String domain;

  /**
   * The is external.
   */
  private Boolean isExternal = true;

  /**
   * The url.
   */
  private URI url;

  /**
   * The redirection url validation callback.
   */
  private IAutodiscoverRedirectionUrl
      redirectionUrlValidationCallback;

  /**
   * The dns client.
   */
  private AutodiscoverDnsClient dnsClient;

  /**
   * The dns server address.
   */
  private String dnsServerAddress;

  /**
   * The enable scp lookup.
   */
  private boolean enableScpLookup = true;

  // Autodiscover legacy path
  /**
   * The Constant AutodiscoverLegacyPath.
   */
  private static final String AutodiscoverLegacyPath =
      "/autodiscover/autodiscover.xml";

  // Autodiscover legacy HTTPS Url
  /**
   * The Constant AutodiscoverLegacyHttpsUrl.
   */
  private static final String AutodiscoverLegacyHttpsUrl = "https://%s" +
      AutodiscoverLegacyPath;
  // Autodiscover legacy HTTP Url
  /**
   * The Constant AutodiscoverLegacyHttpUrl.
   */
  private static final String AutodiscoverLegacyHttpUrl = "http://%s" +
      AutodiscoverLegacyPath;
  // Autodiscover SOAP HTTPS Url
  /**
   * The Constant AutodiscoverSoapHttpsUrl.
   */
  private static final String AutodiscoverSoapHttpsUrl =
      "https://%s/autodiscover/autodiscover.svc";
  // Autodiscover SOAP WS-Security HTTPS Url
  /**
   * The Constant AutodiscoverSoapWsSecurityHttpsUrl.
   */
  private static final String AutodiscoverSoapWsSecurityHttpsUrl =
      AutodiscoverSoapHttpsUrl +
          "/wssecurity";

  /**
   * Autodiscover SOAP WS-Security symmetrickey HTTPS Url
   */
  private static final String AutodiscoverSoapWsSecuritySymmetricKeyHttpsUrl =
      AutodiscoverSoapHttpsUrl + "/wssecurity/symmetrickey";

  /**
   * Autodiscover SOAP WS-Security x509cert HTTPS Url
   */
  private static final String AutodiscoverSoapWsSecurityX509CertHttpsUrl =
      AutodiscoverSoapHttpsUrl + "/wssecurity/x509cert";


  // Autodiscover request namespace
  /**
   * The Constant AutodiscoverRequestNamespace.
   */
  private static final String AutodiscoverRequestNamespace =
      "http://schemas.microsoft.com/exchange/autodiscover/" +
          "outlook/requestschema/2006";
  // Maximum number of Url (or address) redirections that will be followed by
  // an Autodiscover call
  /**
   * The Constant AutodiscoverMaxRedirections.
   */
  protected static final int AutodiscoverMaxRedirections = 10;
  // HTTP header indicating that SOAP Autodiscover service is enabled.
  /**
   * The Constant AutodiscoverSoapEnabledHeaderName.
   */
  private static final String AutodiscoverSoapEnabledHeaderName =
      "X-SOAP-Enabled";
  // HTTP header indicating that WS-Security Autodiscover service is enabled.
  /**
   * The Constant AutodiscoverWsSecurityEnabledHeaderName.
   */
  private static final String AutodiscoverWsSecurityEnabledHeaderName =
      "X-WSSecurity-Enabled";


  /**
   * HTTP header indicating that WS-Security/SymmetricKey Autodiscover service is enabled.
   */

  private static final String AutodiscoverWsSecuritySymmetricKeyEnabledHeaderName =
      "X-WSSecurity-SymmetricKey-Enabled";


  /**
   * HTTP header indicating that WS-Security/X509Cert Autodiscover service is enabled.
   */

  private static final String AutodiscoverWsSecurityX509CertEnabledHeaderName =
      "X-WSSecurity-X509Cert-Enabled";


  // Minimum request version for Autodiscover SOAP service.
  /**
   * The Constant MinimumRequestVersionForAutoDiscoverSoapService.
   */
  private static final ExchangeVersion
      MinimumRequestVersionForAutoDiscoverSoapService =
      ExchangeVersion.Exchange2010;

  /**
   * Default implementation of AutodiscoverRedirectionUrlValidationCallback.
   * Always returns true indicating that the URL can be used.
   *
   * @param redirectionUrl the redirection url
   * @return Returns true.
   * @throws AutodiscoverLocalException the autodiscover local exception
   */
  private boolean defaultAutodiscoverRedirectionUrlValidationCallback(
      String redirectionUrl) throws AutodiscoverLocalException {
    throw new AutodiscoverLocalException(String.format(
        "Autodiscover blocked a potentially insecure redirection to %s. To allow Autodiscover to follow the "
        + "redirection, use the AutodiscoverUrl(string, AutodiscoverRedirectionUrlValidationCallback) "
        + "overload.", redirectionUrl));
  }

  // Legacy Autodiscover

  /**
   * Calls the Autodiscover service to get configuration settings at the
   * specified URL.
   *
   * @param <TSettings>  the generic type
   * @param cls          the cls
   * @param emailAddress the email address
   * @param url          the url
   * @return The requested configuration settings. (TSettings The type of the
   * settings to retrieve)
   * @throws Exception the exception
   */
  private <TSettings extends ConfigurationSettingsBase>
  TSettings getLegacyUserSettingsAtUrl(
      Class<TSettings> cls, String emailAddress, URI url)
      throws Exception {
    this
        .traceMessage(TraceFlags.AutodiscoverConfiguration,
                      String.format("Trying to call Autodiscover for %s on %s.", emailAddress, url));

    TSettings settings = cls.newInstance();

    HttpWebRequest request = null;
    try {
      request = this.prepareHttpWebRequestForUrl(url);

      this.traceHttpRequestHeaders(
          TraceFlags.AutodiscoverRequestHttpHeaders,
          request);
      // OutputStreamWriter out = new
      // OutputStreamWriter(request.getOutputStream());
      OutputStream urlOutStream = request.getOutputStream();

      // If tracing is enabled, we generate the request in-memory so that we
      // can pass it along to the ITraceListener. Then we copy the stream to
      // the request stream.
      if (this.isTraceEnabledFor(TraceFlags.AutodiscoverRequest)) {
        ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();

        PrintWriter writer = new PrintWriter(memoryStream);
        this.writeLegacyAutodiscoverRequest(emailAddress, settings, writer);
        writer.flush();

        this.traceXml(TraceFlags.AutodiscoverRequest, memoryStream);
        // out.write(memoryStream.toString());
        // out.close();
        memoryStream.writeTo(urlOutStream);
        urlOutStream.flush();
        urlOutStream.close();
        memoryStream.close();
      } else {
        PrintWriter writer = new PrintWriter(urlOutStream);
        this.writeLegacyAutodiscoverRequest(emailAddress, settings, writer);

      /*  Flush Start */
        writer.flush();
        urlOutStream.flush();
        urlOutStream.close();
      /* Flush End */
      }
      request.executeRequest();
      request.getResponseCode();
      URI redirectUrl;
      OutParam<URI> outParam = new OutParam<URI>();
      if (this.tryGetRedirectionResponse(request, outParam)) {
        redirectUrl = outParam.getParam();
        settings.makeRedirectionResponse(redirectUrl);
        return settings;
      }
      InputStream serviceResponseStream = request.getInputStream();
      // If tracing is enabled, we read the entire response into a
      // MemoryStream so that we
      // can pass it along to the ITraceListener. Then we parse the response
      // from the
      // MemoryStream.
      if (this.isTraceEnabledFor(TraceFlags.AutodiscoverResponse)) {
        ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();

        while (true) {
          int data = serviceResponseStream.read();
          if (-1 == data) {
            break;
          } else {
            memoryStream.write(data);
          }
        }
        memoryStream.flush();

        this.traceResponse(request, memoryStream);
        ByteArrayInputStream memoryStreamIn = new ByteArrayInputStream(
            memoryStream.toByteArray());
        EwsXmlReader reader = new EwsXmlReader(memoryStreamIn);
        reader.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
        settings.loadFromXml(reader);

      } else {
        EwsXmlReader reader = new EwsXmlReader(serviceResponseStream);
        reader.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
        settings.loadFromXml(reader);
      }

      serviceResponseStream.close();
    } finally {
      if (request != null) {
        try {
          request.close();
        } catch (Exception e2) {
          // Ignore exception while closing the request.
        }
      }
    }

    return settings;
  }

  /**
   * Writes the autodiscover request.
   *
   * @param emailAddress the email address
   * @param settings     the settings
   * @param writer       the writer
   * @throws java.io.IOException Signals that an I/O exception has occurred.
   */
  private void writeLegacyAutodiscoverRequest(String emailAddress,
      ConfigurationSettingsBase settings, PrintWriter writer)
      throws IOException {
    writer.write(String.format("<Autodiscover xmlns=\"%s\">", AutodiscoverRequestNamespace));
    writer.write("<Request>");
    writer.write(String.format("<EMailAddress>%s</EMailAddress>",
        emailAddress));
    writer.write(
        String.format("<AcceptableResponseSchema>%s</AcceptableResponseSchema>", settings.getNamespace()));
    writer.write("</Request>");
    writer.write("</Autodiscover>");
  }

  /**
   * Gets a redirection URL to an SSL-enabled Autodiscover service from the
   * standard non-SSL Autodiscover URL.
   *
   * @param domainName the domain name
   * @return A valid SSL-enabled redirection URL. (May be null)
   * @throws EWSHttpException the EWS http exception
   * @throws XMLStreamException the XML stream exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws ServiceLocalException the service local exception
   * @throws URISyntaxException the uRI syntax exception
   */
  private URI getRedirectUrl(String domainName)
      throws EWSHttpException, XMLStreamException, IOException, ServiceLocalException, URISyntaxException {
    String url = String.format(AutodiscoverLegacyHttpUrl, "autodiscover." + domainName);

    traceMessage(TraceFlags.AutodiscoverConfiguration,
                 String.format("Trying to get Autodiscover redirection URL from %s.", url));

    HttpWebRequest request = null;

    try {
      request = new HttpClientWebRequest(httpClient, httpContext);
      request.setProxy(getWebProxy());

      try {
        request.setUrl(URI.create(url).toURL());
      } catch (MalformedURLException e) {
        String strErr = String.format("Incorrect format : %s", url);
        throw new ServiceLocalException(strErr);
      }

      request.setRequestMethod("GET");
      request.setAllowAutoRedirect(false);

      // Do NOT allow authentication as this single request will be made over plain HTTP.
      request.setAllowAuthentication(false);

      prepareCredentials(request);

      request.prepareConnection();
      try {
        request.executeRequest();
      } catch (IOException e) {
        traceMessage(TraceFlags.AutodiscoverConfiguration, "No Autodiscover redirection URL was returned.");
        return null;
      }

      OutParam<URI> outParam = new OutParam<URI>();
      if (tryGetRedirectionResponse(request, outParam)) {
        return outParam.getParam();
      }
    } finally {
      if (request != null) {
        try {
          request.close();
        } catch (Exception e) {
          // Ignore exception when closing the request
        }
      }
    }

    traceMessage(TraceFlags.AutodiscoverConfiguration, "No Autodiscover redirection URL was returned.");
    return null;
  }

  /**
   * Tries the get redirection response.
   *
   * @param request     the request
   * @param redirectUrl the redirect URL
   * @return true if a valid redirection URL was found
   * @throws XMLStreamException the XML stream exception
   * @throws IOException signals that an I/O exception has occurred.
   * @throws EWSHttpException the EWS http exception
   */
  private boolean tryGetRedirectionResponse(HttpWebRequest request,
      OutParam<URI> redirectUrl) throws XMLStreamException, IOException,
      EWSHttpException {
    // redirectUrl = null;
    if (AutodiscoverRequest.isRedirectionResponse(request)) {
      // Get the redirect location and verify that it's valid.
      String location = request.getResponseHeaderField("Location");

      if (!(location == null || location.isEmpty())) {
        try {
          redirectUrl.setParam(new URI(location));

          // Check if URL is SSL and that the path matches.
          if ((redirectUrl.getParam().getScheme().toLowerCase()
              .equals("https")) &&
              (redirectUrl.getParam().getPath()
                  .equalsIgnoreCase(
                      AutodiscoverLegacyPath))) {
            this.traceMessage(TraceFlags.AutodiscoverConfiguration,
                String.format("Redirection URL found: '%s'",
                    redirectUrl.getParam().toString()));

            return true;
          }
        } catch (URISyntaxException ex) {
          this
              .traceMessage(
                  TraceFlags.AutodiscoverConfiguration,
                  String
                      .format(
                          "Invalid redirection URL " +
                              "was returned: '%s'",
                          location));
          return false;
        }
      }
    }
    return false;
  }

  /**
   * Calls the legacy Autodiscover service to retrieve configuration settings.
   *
   * @param <TSettings>  the generic type
   * @param cls          the cls
   * @param emailAddress The email address to retrieve configuration settings for.
   * @return The requested configuration settings.
   * @throws Exception the exception
   */
  protected <TSettings extends ConfigurationSettingsBase>
  TSettings getLegacyUserSettings(
      Class<TSettings> cls, String emailAddress) throws Exception {
                /*int currentHop = 1;
		return this.internalGetConfigurationSettings(cls, emailAddress,
				currentHop);*/

    // If Url is specified, call service directly.
    if (this.url != null) {
      // this.Uri is intended for Autodiscover SOAP service, convert to Legacy endpoint URL.
      URI autodiscoverUrl = new URI(this.url.toString() + AutodiscoverLegacyPath);
      return this.getLegacyUserSettingsAtUrl(cls, emailAddress, autodiscoverUrl);
    }

    // If Domain is specified, figure out the endpoint Url and call service.
    else if (!(this.domain == null || this.domain.isEmpty())) {
      URI autodiscoverUrl = new URI(String.format(AutodiscoverLegacyHttpsUrl, this.domain));
      return this.getLegacyUserSettingsAtUrl(cls,
          emailAddress, autodiscoverUrl);
    } else {
      // No Url or Domain specified, need to
      //figure out which endpoint to use.
      int currentHop = 1;
      OutParam<Integer> outParam = new OutParam<Integer>();
      outParam.setParam(currentHop);
      List<String> redirectionEmailAddresses = new ArrayList<String>();
      return this.internalGetLegacyUserSettings(
          cls,
          emailAddress,
          redirectionEmailAddresses,
          outParam);
    }
  }

  /**
   * Calls the Autodiscover service to retrieve configuration settings.
   *
   * @param <TSettings>  the generic type
   * @param cls          the cls
   * @param emailAddress The email address to retrieve configuration settings for.
   * @param currentHop   Current number of redirection urls/addresses attempted so far.
   * @return The requested configuration settings.
   * @throws Exception the exception
   */
  private <TSettings extends ConfigurationSettingsBase>
  TSettings internalGetLegacyUserSettings(
      Class<TSettings> cls,
      String emailAddress,
      List<String> redirectionEmailAddresses,
      OutParam<Integer> currentHop)
      throws Exception {
    String domainName = EwsUtilities.domainFromEmailAddress(emailAddress);

    int scpUrlCount;
    OutParam<Integer> outParamInt = new OutParam<Integer>();
    List<URI> urls = this.getAutodiscoverServiceUrls(domainName, outParamInt);
    scpUrlCount = outParamInt.getParam();
    if (urls.size() == 0) {
      throw new ServiceValidationException(
          "This Autodiscover request requires that either the Domain or Url be specified.");
    }

    // Assume caller is not inside the Intranet, regardless of whether SCP
    // Urls
    // were returned or not. SCP Urls are only relevent if one of them
    // returns
    // valid Autodiscover settings.
    this.isExternal = true;

    int currentUrlIndex = 0;

    // Used to save exception for later reporting.
    Exception delayedException = null;
    TSettings settings;

    do {
      URI autodiscoverUrl = urls.get(currentUrlIndex);
      boolean isScpUrl = currentUrlIndex < scpUrlCount;

      try {
        settings = this.getLegacyUserSettingsAtUrl(cls,
            emailAddress, autodiscoverUrl);

        switch (settings.getResponseType()) {
          case Success:
            // Not external if Autodiscover endpoint found via SCP
            // returned the settings.
            if (isScpUrl) {
              this.isExternal = false;
            }
            this.url = autodiscoverUrl;
            return settings;
          case RedirectUrl:
            if (currentHop.getParam() < AutodiscoverMaxRedirections) {
              currentHop.setParam(currentHop.getParam() + 1);

              this
                  .traceMessage(
                      TraceFlags.AutodiscoverResponse,
                      String
                          .format(
                              "Autodiscover " +
                                  "service " +
                                  "returned " +
                                  "redirection URL '%s'.",
                              settings
                                  .getRedirectTarget()));

              urls.add(currentUrlIndex, new URI(
                  settings.getRedirectTarget()));

              break;
            } else {
              throw new MaximumRedirectionHopsExceededException();
            }
          case RedirectAddress:
            if (currentHop.getParam() < AutodiscoverMaxRedirections) {
              currentHop.setParam(currentHop.getParam() + 1);

              this
                  .traceMessage(
                      TraceFlags.AutodiscoverResponse,
                      String
                          .format(
                              "Autodiscover " +
                                  "service " +
                                  "returned " +
                                  "redirection email " +
                                  "address '%s'.",
                              settings
                                  .getRedirectTarget()));
              // Bug E14:255576 If this email address was already tried, we may have a loop
              // in SCP lookups. Disable consideration of SCP records.
              this.disableScpLookupIfDuplicateRedirection(
                  settings.getRedirectTarget(),
                  redirectionEmailAddresses);

              return this.internalGetLegacyUserSettings(cls,
                  settings.getRedirectTarget(),
                  redirectionEmailAddresses,
                  currentHop);
            } else {
              throw new MaximumRedirectionHopsExceededException();
            }
          case Error:
            // Don't treat errors from an SCP-based Autodiscover service
            // to be conclusive.
            // We'll try the next one and record the error for later.
            if (isScpUrl) {
              this
                  .traceMessage(
                      TraceFlags.AutodiscoverConfiguration,
                      "Error returned by " +
                          "Autodiscover service " +
                          "found via SCP, treating " +
                          "as inconclusive.");

              delayedException = new AutodiscoverRemoteException(
                  "The Autodiscover service returned an error.", settings.getError());
              currentUrlIndex++;
            } else {
              throw new AutodiscoverRemoteException("The Autodiscover service returned an error.", settings.getError());
            }
            break;
          default:
            EwsUtilities
                .ewsAssert(false, "Autodiscover.GetConfigurationSettings",
                           "An unexpected error has occured. This code path should never be reached.");
            break;
        }
      } catch (XMLStreamException ex) {
        this.traceMessage(TraceFlags.AutodiscoverConfiguration, String
            .format("%s failed: XML parsing error: %s", url, ex
                .getMessage()));

        // The content at the URL wasn't a valid response, let's try the
        // next.
        currentUrlIndex++;
      } catch (IOException ex) {
        this.traceMessage(
            TraceFlags.AutodiscoverConfiguration,
            String.format("%s failed: I/O error: %s",
                url, ex.getMessage()));

        // The content at the URL wasn't a valid response, let's try the next.
        currentUrlIndex++;
      } catch (Exception ex) {
        HttpWebRequest response = null;
        URI redirectUrl;
        OutParam<URI> outParam1 = new OutParam<URI>();
        if ((response != null) &&
            this.tryGetRedirectionResponse(response, outParam1)) {
          redirectUrl = outParam1.getParam();
          this.traceMessage(TraceFlags.AutodiscoverConfiguration,
              String.format(
                  "Host returned a redirection to url %s",
                  redirectUrl.toString()));

          currentHop.setParam(currentHop.getParam() + 1);
          urls.add(currentUrlIndex, redirectUrl);
        } else {
          if (response != null) {
            this.processHttpErrorResponse(response, ex);

          }

          this.traceMessage(TraceFlags.AutodiscoverConfiguration,
              String.format("%s failed: %s (%s)", url, ex
                  .getClass().getName(), ex.getMessage()));

          // The url did not work, let's try the next.
          currentUrlIndex++;
        }
      }
    } while (currentUrlIndex < urls.size());

    // If we got this far it's because none of the URLs we tried have
    // worked. As a next-to-last chance, use GetRedirectUrl to
    // try to get a redirection URL using an HTTP GET on a non-SSL
    // Autodiscover endpoint. If successful, use this
    // redirection URL to get the configuration settings for this email
    // address. (This will be a common scenario for
    // DataCenter deployments).
    URI redirectionUrl = this.getRedirectUrl(domainName);
    OutParam<TSettings> outParam = new OutParam<TSettings>();
    if ((redirectionUrl != null)
        && this.tryLastChanceHostRedirection(cls, emailAddress,
        redirectionUrl, outParam)) {
      settings = outParam.getParam();
      return settings;
    } else {
      // Getting a redirection URL from an HTTP GET failed too. As a last
      // chance, try to get an appropriate SRV Record
      // using DnsQuery. If successful, use this redirection URL to get
      // the configuration settings for this email address.
      redirectionUrl = this.getRedirectionUrlFromDnsSrvRecord(domainName);
      if ((redirectionUrl != null)
          && this.tryLastChanceHostRedirection(cls, emailAddress,
          redirectionUrl, outParam)) {
        return outParam.getParam();
      }

      // If there was an earlier exception, throw it.
      if (delayedException != null) {
        throw delayedException;
      }

      throw new AutodiscoverLocalException("The Autodiscover service couldn't be located.");
    }
  }

  /**
   * Get an autodiscover SRV record in DNS and construct autodiscover URL.
   *
   * @param domainName Name of the domain.
   * @return Autodiscover URL (may be null if lookup failed)
   * @throws Exception the exception
   */
  protected URI getRedirectionUrlFromDnsSrvRecord(String domainName)
      throws Exception {

    this
        .traceMessage(
            TraceFlags.AutodiscoverConfiguration,
            String
                .format(
                    "Trying to get Autodiscover host " +
                        "from DNS SRV record for %s.",
                    domainName));

    String hostname = this.dnsClient
        .findAutodiscoverHostFromSrv(domainName);
    if (!(hostname == null || hostname.isEmpty())) {
      this
          .traceMessage(TraceFlags.AutodiscoverConfiguration,
              String.format(
                  "Autodiscover host %s was returned.",
                  hostname));

      return new URI(String.format(AutodiscoverLegacyHttpsUrl,
          hostname));
    } else {
      this.traceMessage(TraceFlags.AutodiscoverConfiguration,
          "No matching Autodiscover DNS SRV records were found.");

      return null;
    }
  }

  /**
   * Tries to get Autodiscover settings using redirection Url.
   *
   * @param <TSettings>    the generic type
   * @param cls            the cls
   * @param emailAddress   The email address.
   * @param redirectionUrl Redirection Url.
   * @param settings       The settings.
   * @return boolean The boolean.
   * @throws AutodiscoverLocalException  the autodiscover local exception
   * @throws AutodiscoverRemoteException the autodiscover remote exception
   * @throws Exception                   the exception
   */
  private <TSettings extends ConfigurationSettingsBase> boolean
  tryLastChanceHostRedirection(
      Class<TSettings> cls, String emailAddress, URI redirectionUrl,
      OutParam<TSettings> settings) throws AutodiscoverLocalException,
      AutodiscoverRemoteException, Exception {
    List<String> redirectionEmailAddresses = new ArrayList<String>();

    // Bug 60274: Performing a non-SSL HTTP GET to retrieve a redirection
    // URL is potentially unsafe. We allow the caller
    // to specify delegate to be called to determine whether we are allowed
    // to use the redirection URL.
    if (this
        .callRedirectionUrlValidationCallback(redirectionUrl.toString())) {
      for (int currentHop = 0; currentHop < AutodiscoverService.AutodiscoverMaxRedirections; currentHop++) {
        try {
          settings.setParam(this.getLegacyUserSettingsAtUrl(cls,
              emailAddress, redirectionUrl));

          switch (settings.getParam().getResponseType()) {
            case Success:
              return true;
            case Error:
              throw new AutodiscoverRemoteException("The Autodiscover service returned an error.", settings.getParam()
                  .getError());
            case RedirectAddress:
              // If this email address was already tried,
              //we may have a loop
              // in SCP lookups. Disable consideration of SCP records.
              this.disableScpLookupIfDuplicateRedirection(settings.getParam().getRedirectTarget(),
                  redirectionEmailAddresses);
              OutParam<Integer> outParam = new OutParam<Integer>();
              outParam.setParam(currentHop);
              settings.setParam(
                  this.internalGetLegacyUserSettings(cls,
                      emailAddress,
                      redirectionEmailAddresses,
                      outParam));
              currentHop = outParam.getParam();
              return true;
            case RedirectUrl:
              try {
                redirectionUrl = new URI(settings.getParam()
                    .getRedirectTarget());
              } catch (URISyntaxException ex) {
                this
                    .traceMessage(
                        TraceFlags.
                            AutodiscoverConfiguration,
                        String
                            .format(
                                "Service " +
                                    "returned " +
                                    "invalid " +
                                    "redirection " +
                                    "URL %s",
                                settings
                                    .getParam()
                                    .getRedirectTarget()));
                return false;
              }
              break;
            default:
              String failureMessage = String.format(
                  "Autodiscover call at %s failed with error %s, target %s",
                  redirectionUrl,
                  settings.getParam().getResponseType(),
                  settings.getParam().getRedirectTarget());
              this.traceMessage(
                  TraceFlags.AutodiscoverConfiguration, failureMessage);

              return false;
          }
        } catch (XMLStreamException ex) {
          // If the response is malformed, it wasn't a valid
          // Autodiscover endpoint.
          this
              .traceMessage(TraceFlags.AutodiscoverConfiguration,
                  String.format(
                      "%s failed: XML parsing error: %s",
                      redirectionUrl.toString(), ex
                          .getMessage()));
          return false;
        } catch (IOException ex) {
          this.traceMessage(
              TraceFlags.AutodiscoverConfiguration,
              String.format("%s failed: I/O error: %s",
                  redirectionUrl, ex.getMessage()));
          return false;
        } catch (Exception ex) {
          // TODO: BUG response is always null
          HttpWebRequest response = null;
          OutParam<URI> outParam = new OutParam<URI>();
          if ((response != null)
              && this.tryGetRedirectionResponse(response,
              outParam)) {
            redirectionUrl = outParam.getParam();
            this
                .traceMessage(
                    TraceFlags.AutodiscoverConfiguration,
                    String
                        .format(
                            "Host returned a " +
                                "redirection" +
                                " to url %s",
                            redirectionUrl));

          } else {
            if (response != null) {
              this.processHttpErrorResponse(response, ex);
            }

            this
                .traceMessage(
                    TraceFlags.AutodiscoverConfiguration,
                    String.format("%s failed: %s (%s)",
                        url, ex.getClass().getName(),
                        ex.getMessage()));
            return false;
          }
        }
      }
    }

    return false;
  }

  /**
   * Disables SCP lookup if duplicate email address redirection.
   *
   * @param emailAddress              The email address to use.
   * @param redirectionEmailAddresses The list of prior redirection email addresses.
   */
  private void disableScpLookupIfDuplicateRedirection(
      String emailAddress,
      List<String> redirectionEmailAddresses) {
    // SMTP addresses are case-insensitive so entries are converted to lower-case.
    emailAddress = emailAddress.toLowerCase();

    if (redirectionEmailAddresses.contains(emailAddress)) {
      this.enableScpLookup = false;
    } else {
      redirectionEmailAddresses.add(emailAddress);
    }
  }

  /**
   * Gets user settings from Autodiscover legacy endpoint.
   *
   * @param emailAddress      The email address to use.
   * @param requestedSettings The requested settings.
   * @return GetUserSettingsResponse
   * @throws Exception on error
   */
  protected GetUserSettingsResponse internalGetLegacyUserSettings(
      String emailAddress,
      List<UserSettingName> requestedSettings) throws Exception {
    // Cannot call legacy Autodiscover service with WindowsLive and other WSSecurity-based credential
    if ((this.getCredentials() != null) && (this.getCredentials() instanceof WSSecurityBasedCredentials)) {
      throw new AutodiscoverLocalException(
          "WindowsLiveCredentials can't be used with this Autodiscover endpoint.");
    }

    OutlookConfigurationSettings settings = this.getLegacyUserSettings(
        OutlookConfigurationSettings.class,
        emailAddress);



    return settings.convertSettings(emailAddress, requestedSettings);
  }

  /**
   * Calls the SOAP Autodiscover service
   * for user settings for a single SMTP address.
   *
   * @param smtpAddress       SMTP address.
   * @param requestedSettings The requested settings.
   * @return GetUserSettingsResponse
   * @throws Exception on error
   */
  protected GetUserSettingsResponse internalGetSoapUserSettings(
      String smtpAddress,
      List<UserSettingName> requestedSettings) throws Exception {
    List<String> smtpAddresses = new ArrayList<String>();
    smtpAddresses.add(smtpAddress);

    List<String> redirectionEmailAddresses = new ArrayList<String>();
    redirectionEmailAddresses.add(smtpAddress.toLowerCase());

    for (int currentHop = 0; currentHop < AutodiscoverService.AutodiscoverMaxRedirections; currentHop++) {
      GetUserSettingsResponse response = this.getUserSettings(smtpAddresses,
          requestedSettings).getTResponseAtIndex(0);

      switch (response.getErrorCode()) {
        case RedirectAddress:
          this.traceMessage(
              TraceFlags.AutodiscoverResponse,
              String.format("Autodiscover service returned redirection email address '%s'.",
                  response.getRedirectTarget()));

          smtpAddresses.clear();
          smtpAddresses.add(response.getRedirectTarget().
              toLowerCase());
          this.url = null;
          this.domain = null;

          // If this email address was already tried,
          //we may have a loop
          // in SCP lookups. Disable consideration of SCP records.
          this.disableScpLookupIfDuplicateRedirection(response.getRedirectTarget(),
              redirectionEmailAddresses);
          break;

        case RedirectUrl:
          this.traceMessage(
              TraceFlags.AutodiscoverResponse,
              String.format("Autodiscover service returned redirection URL '%s'.",
                  response.getRedirectTarget()));

          //this.url = new URI(response.getRedirectTarget());
          this.url = this.getCredentials().adjustUrl(new URI(response.getRedirectTarget()));
          break;

        case NoError:
        default:
          return response;
      }
    }

    throw new AutodiscoverLocalException("The Autodiscover service couldn't be located.");
  }

  /**
   * Gets the user settings using Autodiscover SOAP service.
   *
   * @param smtpAddresses The SMTP addresses of the users.
   * @param settings      The settings.
   * @return GetUserSettingsResponseCollection Object.
   * @throws Exception the exception
   */
  protected GetUserSettingsResponseCollection getUserSettings(
      final List<String> smtpAddresses, List<UserSettingName> settings)
      throws Exception {
    EwsUtilities.validateParam(smtpAddresses, "smtpAddresses");
    EwsUtilities.validateParam(settings, "settings");

    return this.getSettings(
        GetUserSettingsResponseCollection.class, UserSettingName.class,
        smtpAddresses, settings, null, this,
        new IFuncDelegate<String>() {
          public String func() throws FormatException {
            return EwsUtilities
                .domainFromEmailAddress(smtpAddresses.get(0));
          }
        });
  }

  /**
   * Gets user or domain settings using Autodiscover SOAP service.
   *
   * @param <TGetSettingsResponseCollection> the generic type
   * @param <TSettingName>                   the generic type
   * @param cls                              the cls
   * @param cls1                             the cls1
   * @param identities                       Either the domains or the SMTP addresses of the users.
   * @param settings                         The settings.
   * @param requestedVersion                 Requested version of the Exchange service.
   * @param getSettingsMethod                The method to use.
   * @param getDomainMethod                  The method to calculate the domain value.
   * @return TGetSettingsResponse Collection.
   * @throws Exception the exception
   */
  private <TGetSettingsResponseCollection, TSettingName>
  TGetSettingsResponseCollection getSettings(
      Class<TGetSettingsResponseCollection> cls,
      Class<TSettingName> cls1,
      List<String> identities,
      List<TSettingName> settings,
      ExchangeVersion requestedVersion,
      IFunctionDelegate<List<String>, List<TSettingName>,
          TGetSettingsResponseCollection> getSettingsMethod,
      IFuncDelegate<String> getDomainMethod) throws Exception {
    TGetSettingsResponseCollection response;

    // Autodiscover service only exists in E14 or later.
    if (this.getRequestedServerVersion().compareTo(
        MinimumRequestVersionForAutoDiscoverSoapService) < 0) {
      throw new ServiceVersionException(String.format(
          "The Autodiscover service only supports %s or a later version.",
          MinimumRequestVersionForAutoDiscoverSoapService));
    }

    // If Url is specified, call service directly.
    if (this.url != null) {
      URI autodiscoverUrl = this.url;
      response = getSettingsMethod.func(identities, settings,
          requestedVersion, this.url);
      this.url = autodiscoverUrl;
      return response;
    }
    // If Domain is specified, determine endpoint Url and call service.
    else if (!(this.domain == null || this.domain.isEmpty())) {
      URI autodiscoverUrl = this.getAutodiscoverEndpointUrl(this.domain);
      response = getSettingsMethod.func(identities, settings,
          requestedVersion,
          autodiscoverUrl);

      // If we got this far, response was successful, set Url.
      this.url = autodiscoverUrl;
      return response;
    }
    // No Url or Domain specified, need to figure out which endpoint(s) to
    // try.
    else {
      // Assume caller is not inside the Intranet, regardless of whether
      // SCP Urls
      // were returned or not. SCP Urls are only relevent if one of them
      // returns
      // valid Autodiscover settings.
      this.isExternal = true;

      URI autodiscoverUrl;

      String domainName = getDomainMethod.func();
      int scpHostCount;
      OutParam<Integer> outParam = new OutParam<Integer>();
      List<String> hosts = this.getAutodiscoverServiceHosts(domainName,
          outParam);
      scpHostCount = outParam.getParam();
      if (hosts.size() == 0) {
        throw new ServiceValidationException(
            "This Autodiscover request requires that either the Domain or Url be specified.");
      }

      for (int currentHostIndex = 0; currentHostIndex < hosts.size(); currentHostIndex++) {
        String host = hosts.get(currentHostIndex);
        boolean isScpHost = currentHostIndex < scpHostCount;
        OutParam<URI> outParams = new OutParam<URI>();
        if (this.tryGetAutodiscoverEndpointUrl(host, outParams)) {
          autodiscoverUrl = outParams.getParam();
          response = getSettingsMethod.func(identities, settings,
              requestedVersion,
              autodiscoverUrl);

          // If we got this far, the response was successful, set Url.
          this.url = autodiscoverUrl;

          // Not external if Autodiscover endpoint found via SCP
          // returned the settings.
          if (isScpHost) {
            this.isExternal = false;
          }

          return response;
        }
      }

      // Next-to-last chance: try unauthenticated GET over HTTP to be
      // redirected to appropriate service endpoint.
      autodiscoverUrl = this.getRedirectUrl(domainName);
      OutParam<URI> outParamUrl = new OutParam<URI>();
      if ((autodiscoverUrl != null) &&
          this
              .callRedirectionUrlValidationCallback(
                  autodiscoverUrl.toString()) &&
          this.tryGetAutodiscoverEndpointUrl(autodiscoverUrl
              .getHost(), outParamUrl)) {
        autodiscoverUrl = outParamUrl.getParam();
        response = getSettingsMethod.func(identities, settings,
            requestedVersion,
            autodiscoverUrl);

        // If we got this far, the response was successful, set Url.
        this.url = autodiscoverUrl;

        return response;
      }

      // Last Chance: try to read autodiscover SRV Record from DNS. If we
      // find one, use
      // the hostname returned to construct an Autodiscover endpoint URL.
      autodiscoverUrl = this
          .getRedirectionUrlFromDnsSrvRecord(domainName);
      if ((autodiscoverUrl != null) &&
          this
              .callRedirectionUrlValidationCallback(
                  autodiscoverUrl.toString()) &&
          this.tryGetAutodiscoverEndpointUrl(autodiscoverUrl
              .getHost(), outParamUrl)) {
        autodiscoverUrl = outParamUrl.getParam();
        response = getSettingsMethod.func(identities, settings,
            requestedVersion,
            autodiscoverUrl);

        // If we got this far, the response was successful, set Url.
        this.url = autodiscoverUrl;

        return response;
      } else {
        throw new AutodiscoverLocalException("The Autodiscover service couldn't be located.");
      }
    }
  }

  /**
   * Gets settings for one or more users.
   *
   * @param smtpAddresses    The SMTP addresses of the users.
   * @param settings         The settings.
   * @param requestedVersion Requested version of the Exchange service.
   * @param autodiscoverUrl  The autodiscover URL.
   * @return GetUserSettingsResponse collection.
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  private GetUserSettingsResponseCollection internalGetUserSettings(
      List<String> smtpAddresses, List<UserSettingName> settings,
      ExchangeVersion requestedVersion,
      URI autodiscoverUrl) throws ServiceLocalException, Exception {
    // The response to GetUserSettings can be a redirection. Execute
    // GetUserSettings until we get back
    // a valid response or we've followed too many redirections.
    for (int currentHop = 0; currentHop < AutodiscoverService.AutodiscoverMaxRedirections; currentHop++) {
      GetUserSettingsRequest request = new GetUserSettingsRequest(this,
          autodiscoverUrl);
      request.setSmtpAddresses(smtpAddresses);
      request.setSettings(settings);
      GetUserSettingsResponseCollection response = request.execute();

      // Did we get redirected?
      if (response.getErrorCode() == AutodiscoverErrorCode.RedirectUrl
          && response.getRedirectionUrl() != null) {
        this.traceMessage(
            TraceFlags.AutodiscoverConfiguration,
            String.format("Request to %s returned redirection to %s",
                autodiscoverUrl.toString(), response.getRedirectionUrl()));

        autodiscoverUrl = response.getRedirectionUrl();
      } else {
        return response;
      }
    }

    this.traceMessage(TraceFlags.AutodiscoverConfiguration, String.format(
        "Maximum number of redirection hops %d exceeded",
        AutodiscoverMaxRedirections));

    throw new MaximumRedirectionHopsExceededException();
  }

  /**
   * Gets the domain settings using Autodiscover SOAP service.
   *
   * @param domains          The domains.
   * @param settings         The settings.
   * @param requestedVersion Requested version of the Exchange service.
   * @return GetDomainSettingsResponse collection.
   * @throws Exception the exception
   */
  protected GetDomainSettingsResponseCollection getDomainSettings(
      final List<String> domains, List<DomainSettingName> settings,
      ExchangeVersion requestedVersion)
      throws Exception {
    EwsUtilities.validateParam(domains, "domains");
    EwsUtilities.validateParam(settings, "settings");

    return this.getSettings(
        GetDomainSettingsResponseCollection.class,
        DomainSettingName.class, domains, settings,
        requestedVersion, this,
        new IFuncDelegate<String>() {
          public String func() {
            return domains.get(0);
          }
        });
  }

  /**
   * Gets settings for one or more domains.
   *
   * @param domains          The domains.
   * @param settings         The settings.
   * @param requestedVersion Requested version of the Exchange service.
   * @param autodiscoverUrl  The autodiscover URL.
   * @return GetDomainSettingsResponse Collection.
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  private GetDomainSettingsResponseCollection internalGetDomainSettings(
      List<String> domains, List<DomainSettingName> settings,
      ExchangeVersion requestedVersion,
      URI autodiscoverUrl) throws ServiceLocalException, Exception {
    // The response to GetDomainSettings can be a redirection. Execute
    // GetDomainSettings until we get back
    // a valid response or we've followed too many redirections.
    for (int currentHop = 0; currentHop < AutodiscoverService.AutodiscoverMaxRedirections; currentHop++) {
      GetDomainSettingsRequest request = new GetDomainSettingsRequest(
          this, autodiscoverUrl);
      request.setDomains(domains);
      request.setSettings(settings);
      request.setRequestedVersion(requestedVersion);
      GetDomainSettingsResponseCollection response = request.execute();

      // Did we get redirected?
      if (response.getErrorCode() == AutodiscoverErrorCode.RedirectUrl
          && response.getRedirectionUrl() != null) {
        autodiscoverUrl = response.getRedirectionUrl();
      } else {
        return response;
      }
    }

    this.traceMessage(TraceFlags.AutodiscoverConfiguration, String.format(
        "Maximum number of redirection hops %d exceeded",
        AutodiscoverMaxRedirections));

    throw new MaximumRedirectionHopsExceededException();
  }

  /**
   * Gets the autodiscover endpoint URL.
   *
   * @param host The host.
   * @return URI The URI.
   * @throws Exception the exception
   */
  private URI getAutodiscoverEndpointUrl(String host) throws Exception {
    URI autodiscoverUrl = null;
    OutParam<URI> outParam = new OutParam<URI>();
    if (this.tryGetAutodiscoverEndpointUrl(host, outParam)) {
      return autodiscoverUrl;
    } else {
      throw new AutodiscoverLocalException(
          "No appropriate Autodiscover SOAP or WS-Security endpoint is available.");
    }
  }

  /**
   * Tries the get Autodiscover Service endpoint URL.
   *
   * @param host The host.
   * @param url  the url
   * @return boolean The boolean.
   * @throws Exception the exception
   */
  private boolean tryGetAutodiscoverEndpointUrl(String host,
      OutParam<URI> url)
      throws Exception {
    EnumSet<AutodiscoverEndpoints> endpoints;
    OutParam<EnumSet<AutodiscoverEndpoints>> outParam =
        new OutParam<EnumSet<AutodiscoverEndpoints>>();
    if (this.tryGetEnabledEndpointsForHost(host, outParam)) {
      endpoints = outParam.getParam();
      url
          .setParam(new URI(String.format(AutodiscoverSoapHttpsUrl,
              host)));

      // Make sure that at least one of the non-legacy endpoints is
      // available.
      if ((!endpoints.contains(AutodiscoverEndpoints.Soap)) &&
          (!endpoints.contains(
              AutodiscoverEndpoints.WsSecurity))
        // (endpoints .contains( AutodiscoverEndpoints.WSSecuritySymmetricKey) ) &&
        //(endpoints .contains( AutodiscoverEndpoints.WSSecurityX509Cert))
          ) {
        this
            .traceMessage(
                TraceFlags.AutodiscoverConfiguration,
                String
                    .format(
                        "No Autodiscover endpoints " +
                            "are available  for host %s",
                        host));

        return false;
      }

      // If we have WLID credential, make sure that we have a WS-Security
      // endpoint
			/*
			if (this.getCredentials() instanceof WindowsLiveCredentials) {
				if (endpoints.contains(AutodiscoverEndpoints.WsSecurity)) {
					this
							.traceMessage(
									TraceFlags.AutodiscoverConfiguration,
									String
											.format(
													"No Autodiscover " +
													"WS-Security " +
													"endpoint is available" +
													" for host %s",
													host));

					return false;
				} else {
					url.setParam(new URI(String.format(
							AutodiscoverSoapWsSecurityHttpsUrl, host)));
				}
			}
			   else if (this.getCredentials() instanceof PartnerTokenCredentials)
                {
                    if (endpoints.contains( AutodiscoverEndpoints.WSSecuritySymmetricKey))
                    {
                        this.traceMessage(
                            TraceFlags.AutodiscoverConfiguration,
                            String.format("No Autodiscover WS-Security/SymmetricKey endpoint is available for host {0}", host));

                        return false;
                    }
                    else
                    {
                        url.setParam( new URI(String.format(AutodiscoverSoapWsSecuritySymmetricKeyHttpsUrl, host)));
                    }
                }
                else if (this.getCredentials()instanceof X509CertificateCredentials)
                {
                    if ((endpoints.contains(AutodiscoverEndpoints.WSSecurityX509Cert))
                    {
                        this.traceMessage(
                            TraceFlags.AutodiscoverConfiguration,
                            String.format("No Autodiscover WS-Security/X509Cert endpoint is available for host {0}", host));

                        return false;
                    }
                    else
                    {
                        url.setParam( new URI(String.format(AutodiscoverSoapWsSecurityX509CertHttpsUrl, host)));
                    }
                }
				  */
      return true;


    } else {
      this
          .traceMessage(
              TraceFlags.AutodiscoverConfiguration,
              String
                  .format(
                      "No Autodiscover endpoints " +
                          "are available for host %s",
                      host));

      return false;
    }
  }

  /**
   * Gets the list of autodiscover service URLs.
   *
   * @param domainName   Domain name.
   * @param scpHostCount Count of hosts found via SCP lookup.
   * @return List of Autodiscover URLs.
   * @throws java.net.URISyntaxException the URI Syntax exception
   */
  protected List<URI> getAutodiscoverServiceUrls(String domainName,
      OutParam<Integer> scpHostCount) throws URISyntaxException {
    List<URI> urls;

    urls = new ArrayList<URI>();

    scpHostCount.setParam(urls.size());

    // As a fallback, add autodiscover URLs base on the domain name.
    urls.add(new URI(String.format(AutodiscoverLegacyHttpsUrl,
        domainName)));
    urls.add(new URI(String.format(AutodiscoverLegacyHttpsUrl,
        "autodiscover." + domainName)));

    return urls;
  }

  /**
   * Gets the list of autodiscover service hosts.
   *
   * @param domainName Domain name.
   * @param outParam   the out param
   * @return List of hosts.
   * @throws java.net.URISyntaxException the uRI syntax exception
   * @throws ClassNotFoundException      the class not found exception
   */
  protected List<String> getAutodiscoverServiceHosts(String domainName,
      OutParam<Integer> outParam) throws URISyntaxException,
      ClassNotFoundException {

    List<URI> urls = this.getAutodiscoverServiceUrls(domainName, outParam);
    List<String> lst = new ArrayList<String>();
    for (URI url : urls) {
      lst.add(url.getHost());
    }
    return lst;
  }

  /**
   * Gets the enabled autodiscover endpoints on a specific host.
   *
   * @param host      The host.
   * @param endpoints Endpoints found for host.
   * @return Flags indicating which endpoints are enabled.
   * @throws Exception the exception
   */
  private boolean tryGetEnabledEndpointsForHost(String host,
      OutParam<EnumSet<AutodiscoverEndpoints>> endpoints) throws Exception {
    this.traceMessage(TraceFlags.AutodiscoverConfiguration, String.format(
        "Determining which endpoints are enabled for host %s", host));

    // We may get redirected to another host. And therefore need to limit the number of redirections we'll
    // tolerate.
    for (int currentHop = 0; currentHop < AutodiscoverMaxRedirections; currentHop++) {
      URI autoDiscoverUrl = new URI(String.format(AutodiscoverLegacyHttpsUrl, host));

      endpoints.setParam(EnumSet.of(AutodiscoverEndpoints.None));

      HttpWebRequest request = null;
      try {
        request = new HttpClientWebRequest(httpClient, httpContext);
        request.setProxy(getWebProxy());

        try {
          request.setUrl(autoDiscoverUrl.toURL());
        } catch (MalformedURLException e) {
          String strErr = String.format("Incorrect format : %s", url);
          throw new ServiceLocalException(strErr);
        }

        request.setRequestMethod("GET");
        request.setAllowAutoRedirect(false);
        request.setPreAuthenticate(false);
        request.setUseDefaultCredentials(this.getUseDefaultCredentials());

        prepareCredentials(request);

        request.prepareConnection();
        try {
          request.executeRequest();
        } catch (IOException e) {
          return false;
        }

        OutParam<URI> outParam = new OutParam<URI>();
        if (this.tryGetRedirectionResponse(request, outParam)) {
          URI redirectUrl = outParam.getParam();
          this.traceMessage(TraceFlags.AutodiscoverConfiguration,
              String.format("Host returned redirection to host '%s'", redirectUrl.getHost()));

          host = redirectUrl.getHost();
        } else {
          endpoints.setParam(this.getEndpointsFromHttpWebResponse(request));

          this.traceMessage(TraceFlags.AutodiscoverConfiguration,
              String.format("Host returned enabled endpoint flags: %s", endpoints.getParam().toString()));

          return true;
        }
      } finally {
        if (request != null) {
          try {
            request.close();
          } catch (Exception e) {
            // Connection can't be closed. We'll ignore this...
          }
        }
      }
    }

    this.traceMessage(TraceFlags.AutodiscoverConfiguration,
        String.format("Maximum number of redirection hops %d exceeded", AutodiscoverMaxRedirections));

    throw new MaximumRedirectionHopsExceededException();
  }

  /**
   * Gets the endpoints from HTTP web response.
   *
   * @param request the request
   * @return Endpoints enabled.
   * @throws EWSHttpException the EWS http exception
   */
  private EnumSet<AutodiscoverEndpoints> getEndpointsFromHttpWebResponse(
      HttpWebRequest request) throws EWSHttpException {
    EnumSet<AutodiscoverEndpoints> endpoints = EnumSet
        .noneOf(AutodiscoverEndpoints.class);
    endpoints.add(AutodiscoverEndpoints.Legacy);

    if (!(request.getResponseHeaders().get(
        AutodiscoverSoapEnabledHeaderName) == null || request
        .getResponseHeaders().get(AutodiscoverSoapEnabledHeaderName)
        .isEmpty())) {
      endpoints.add(AutodiscoverEndpoints.Soap);
    }
    if (!(request.getResponseHeaders().get(
        AutodiscoverWsSecurityEnabledHeaderName) == null || request
        .getResponseHeaders().get(
            AutodiscoverWsSecurityEnabledHeaderName).isEmpty())) {
      endpoints.add(AutodiscoverEndpoints.WsSecurity);
    }
		
		/* if (! (request.getResponseHeaders().get(
				 AutodiscoverWsSecuritySymmetricKeyEnabledHeaderName) !=null || request
				 .getResponseHeaders().get(
				 AutodiscoverWsSecuritySymmetricKeyEnabledHeaderName).isEmpty()))
         {
             endpoints .add( AutodiscoverEndpoints.WSSecuritySymmetricKey);
         }
         if (!(request.getResponseHeaders().get(
        		 AutodiscoverWsSecurityX509CertEnabledHeaderName)!=null ||
        		 request.getResponseHeaders().get(
                		 AutodiscoverWsSecurityX509CertEnabledHeaderName).isEmpty()))
        		 
         {
             endpoints .add(AutodiscoverEndpoints.WSSecurityX509Cert);
         }*/

    return endpoints;
  }

  /**
   * Traces the response.
   *
   * @param request      the request
   * @param memoryStream the memory stream
   * @throws XMLStreamException the XML stream exception
   * @throws IOException signals that an I/O exception has occurred.
   * @throws EWSHttpException the EWS http exception
   */
  public void traceResponse(HttpWebRequest request, ByteArrayOutputStream memoryStream) throws XMLStreamException,
      IOException, EWSHttpException {
    this.processHttpResponseHeaders(
        TraceFlags.AutodiscoverResponseHttpHeaders, request);
    String contentType = request.getResponseContentType();
    if (!(contentType == null || contentType.isEmpty())) {
      contentType = contentType.toLowerCase();
      if (contentType.toLowerCase().startsWith("text/") ||
          contentType.toLowerCase().
              startsWith("application/soap")) {
        this.traceXml(TraceFlags.AutodiscoverResponse, memoryStream);
      } else {
        this.traceMessage(TraceFlags.AutodiscoverResponse,
            "Non-textual response");
      }
    }
  }

  /**
   * Creates an HttpWebRequest instance and initializes it with the
   * appropriate parameters, based on the configuration of this service
   * object.
   *
   * @param url The URL that the HttpWebRequest should target
   * @return HttpWebRequest The HttpWebRequest
   * @throws ServiceLocalException       the service local exception
   * @throws java.net.URISyntaxException the uRI syntax exception
   */
  public HttpWebRequest prepareHttpWebRequestForUrl(URI url)
      throws ServiceLocalException, URISyntaxException {
    return this.prepareHttpWebRequestForUrl(url, false,
        // acceptGzipEncoding
        false); // allowAutoRedirect
  }

  /**
   * Calls the redirection URL validation callback. If the redirection URL
   * validation callback is null, use the default callback which does not
   * allow following any redirections.
   *
   * @param redirectionUrl The redirection URL.
   * @return True if redirection should be followed.
   * @throws AutodiscoverLocalException the autodiscover local exception
   */
  private boolean callRedirectionUrlValidationCallback(String redirectionUrl)
      throws AutodiscoverLocalException {
    IAutodiscoverRedirectionUrl callback =
        (this.redirectionUrlValidationCallback == null) ? this
            : this.redirectionUrlValidationCallback;
    return callback
        .autodiscoverRedirectionUrlValidationCallback(redirectionUrl);
  }

  /**
   * Processes an HTTP error response.
   *
   * @param httpWebResponse The HTTP web response.
   * @throws Exception the exception
   */
  @Override public void processHttpErrorResponse(HttpWebRequest httpWebResponse, Exception webException) throws Exception {
    this.internalProcessHttpErrorResponse(
        httpWebResponse,
        webException,
        TraceFlags.AutodiscoverResponseHttpHeaders,
        TraceFlags.AutodiscoverResponse);
  }

  /*
   * (non-Javadoc)
   *
   * @see microsoft.exchange.webservices.AutodiscoverRedirectionUrlInterface#
   * autodiscoverRedirectionUrlValidationCallback(java.lang.String)
   */
  public boolean autodiscoverRedirectionUrlValidationCallback(
      String redirectionUrl) throws AutodiscoverLocalException {
    return defaultAutodiscoverRedirectionUrlValidationCallback(
        redirectionUrl);
  }

  /**
   * Initializes a new instance of the "AutodiscoverService" class.
   *
   * @throws ArgumentException on validation error
   */
  public AutodiscoverService() throws ArgumentException {
    this(ExchangeVersion.Exchange2010);
  }

  /**
   * Initializes a new instance of the "AutodiscoverService" class.
   *
   * @param requestedServerVersion The requested server version
   * @throws ArgumentException on validation error
   */
  public AutodiscoverService(ExchangeVersion requestedServerVersion)
      throws ArgumentException {
    this(null, null, requestedServerVersion);
  }

  /**
   * Initializes a new instance of the "AutodiscoverService" class.
   *
   * @param domain The domain that will be used to determine the URL of the service
   * @throws ArgumentException on validation error
   */
  public AutodiscoverService(String domain) throws ArgumentException {
    this(null, domain);
  }

  /**
   * Initializes a new instance of the "AutodiscoverService" class.
   *
   * @param domain                 The domain that will be used to determine the URL of the service
   * @param requestedServerVersion The requested server version
   * @throws ArgumentException on validation error
   */
  public AutodiscoverService(String domain,
      ExchangeVersion requestedServerVersion) throws ArgumentException {
    this(null, domain, requestedServerVersion);
  }

  /**
   * Initializes a new instance of the "AutodiscoverService" class.
   *
   * @param url The URL of the service
   * @throws ArgumentException on validation error
   */
  public AutodiscoverService(URI url) throws ArgumentException {
    this(url, url.getHost());
  }

  /**
   * Initializes a new instance of the "AutodiscoverService" class.
   *
   * @param url                    The URL of the service
   * @param requestedServerVersion The requested server version
   * @throws ArgumentException on validation error
   */
  public AutodiscoverService(URI url,
      ExchangeVersion requestedServerVersion) throws ArgumentException {
    this(url, url.getHost(), requestedServerVersion);
  }

  /**
   * Initializes a new instance of the "AutodiscoverService" class.
   *
   * @param url    The URL of the service
   * @param domain The domain that will be used to determine the URL of the service
   * @throws ArgumentException on validation error
   */
  public AutodiscoverService(URI url, String domain)
      throws ArgumentException {
    super();
    EwsUtilities.validateDomainNameAllowNull(domain, "domain");
    this.url = url;
    this.domain = domain;
    this.dnsClient = new AutodiscoverDnsClient(this);
  }

  /**
   * Initializes a new instance of the "AutodiscoverService" class.
   *
   * @param url                    The URL of the service.
   * @param domain                 The domain that will be used to determine the URL of the
   *                               service.
   * @param requestedServerVersion The requested server version.
   * @throws ArgumentException on validation error
   */
  public AutodiscoverService(URI url, String domain,
      ExchangeVersion requestedServerVersion) throws ArgumentException {
    super(requestedServerVersion);
    EwsUtilities.validateDomainNameAllowNull(domain, "domain");

    this.url = url;
    this.domain = domain;
    this.dnsClient = new AutodiscoverDnsClient(this);
  }

  /**
   * Initializes a new instance of the AutodiscoverService class.
   *
   * @param service                The other service.
   * @param requestedServerVersion The requested server version.
   */
  public AutodiscoverService(ExchangeServiceBase service,
      ExchangeVersion requestedServerVersion) {
    super(service, requestedServerVersion);
    this.dnsClient = new AutodiscoverDnsClient(this);
  }

  /**
   * Initializes a new instance of the "AutodiscoverService" class.
   *
   * @param service The service.
   */
  public AutodiscoverService(ExchangeServiceBase service) {
    super(service, service.getRequestedServerVersion());
  }

  /**
   * Retrieves the specified settings for single SMTP address.
   * <p>This method will run the entire Autodiscover "discovery"
   * algorithm and will follow address and URL redirections.</p>

   * @param userSmtpAddress  The SMTP addresses of the user.
   * @param userSettingNames The user setting names.
   * @return A UserResponse object containing the requested settings for the
   * specified user.
   * @throws Exception on error
   */
  public GetUserSettingsResponse getUserSettings(String userSmtpAddress,
      UserSettingName... userSettingNames) throws Exception {
    List<UserSettingName> requestedSettings = new ArrayList<UserSettingName>();
    requestedSettings.addAll(Arrays.asList(userSettingNames));

    if (userSmtpAddress == null || userSmtpAddress.isEmpty()) {
      throw new ServiceValidationException("A valid SMTP address must be specified.");
    }

    if (requestedSettings.size() == 0) {
      throw new ServiceValidationException("At least one setting must be requested.");
    }

    if (this.getRequestedServerVersion().compareTo(MinimumRequestVersionForAutoDiscoverSoapService) < 0) {
      return this.internalGetLegacyUserSettings(userSmtpAddress,
          requestedSettings);
    } else {
      return this.internalGetSoapUserSettings(userSmtpAddress,
          requestedSettings);
    }

  }

  /**
   * Retrieves the specified settings for a set of users.
   *
   * @param userSmtpAddresses the user smtp addresses
   * @param userSettingNames  The user setting names.
   * @return A GetUserSettingsResponseCollection object containing the
   * response for each individual user.
   * @throws Exception the exception
   */
  public GetUserSettingsResponseCollection getUsersSettings(
      Iterable<String> userSmtpAddresses,
      UserSettingName... userSettingNames) throws Exception {
    if (this.getRequestedServerVersion().compareTo(MinimumRequestVersionForAutoDiscoverSoapService) < 0) {
      throw new ServiceVersionException(
          String.format("The Autodiscover service only supports %s or a later version.",
              MinimumRequestVersionForAutoDiscoverSoapService));
    }
    List<String> smtpAddresses = new ArrayList<String>();
    smtpAddresses.addAll((Collection<? extends String>) userSmtpAddresses);
    List<UserSettingName> settings = new ArrayList<UserSettingName>();
    settings.addAll(Arrays.asList(userSettingNames));
    return this.getUserSettings(smtpAddresses, settings);
  }

  /**
   * Retrieves the specified settings for a domain.
   *
   * @param domain             The domain.
   * @param requestedVersion   Requested version of the Exchange service.
   * @param domainSettingNames The domain setting names.
   * @return A DomainResponse object containing the requested settings for the
   * specified domain.
   * @throws Exception the exception
   */
  public GetDomainSettingsResponse getDomainSettings(String domain,
      ExchangeVersion requestedVersion,
      DomainSettingName... domainSettingNames) throws Exception {
    List<String> domains = new ArrayList<String>(1);
    domains.add(domain);

    List<DomainSettingName> settings = new ArrayList<DomainSettingName>();
    settings.addAll(Arrays.asList(domainSettingNames));

    return this.getDomainSettings(domains, settings, requestedVersion).
        getTResponseAtIndex(0);
  }

  /**
   * Retrieves the specified settings for a set of domains.
   *
   * @param domains            the domains
   * @param requestedVersion   Requested version of the Exchange service.
   * @param domainSettingNames The domain setting names.
   * @return A GetDomainSettingsResponseCollection object containing the
   * response for each individual domain.
   * @throws Exception the exception
   */
  public GetDomainSettingsResponseCollection getDomainSettings(
      Iterable<String> domains, ExchangeVersion requestedVersion,
      DomainSettingName... domainSettingNames)
      throws Exception {
    List<DomainSettingName> settings = new ArrayList<DomainSettingName>();
    settings.addAll(Arrays.asList(domainSettingNames));

    List<String> domainslst = new ArrayList<String>();
    domainslst.addAll((Collection<? extends String>) domains);

    return this.getDomainSettings(domainslst, settings, requestedVersion);
  }

  /**
   * Gets the domain this service is bound to. When this property is
   * set, the domain name is used to automatically determine the Autodiscover service URL.
   *
   * @return the domain
   */
  public String getDomain() {
    return this.domain;
  }

  /**
   * Sets the domain this service is bound to. When this property is
   * set, the domain
   * name is used to automatically determine the Autodiscover service URL.
   *
   * @param value the new domain
   * @throws ArgumentException on validation error
   */
  public void setDomain(String value) throws ArgumentException {
    EwsUtilities.validateDomainNameAllowNull(value, "Domain");

    // If Domain property is set to non-null value, Url property is nulled.
    if (value != null) {
      this.url = null;
    }
    this.domain = value;
  }

  /**
   * Gets the url this service is bound to.
   *
   * @return the url
   */
  public URI getUrl() {
    return this.url;
  }

  /**
   * Sets the url this service is bound to.
   *
   * @param value the new url
   */
  public void setUrl(URI value) {
    // If Url property is set to non-null value, Domain property is set to
    // host portion of Url.
    if (value != null) {
      this.domain = value.getHost();
    }
    this.url = value;
  }

  public Boolean isExternal() {
    return this.isExternal;
  }

  protected void setIsExternal(Boolean value) {
    this.isExternal = value;
  }


  /**
   * Gets the redirection url validation callback.
   *
   * @return the redirection url validation callback
   */
  public IAutodiscoverRedirectionUrl
  getRedirectionUrlValidationCallback() {
    return this.redirectionUrlValidationCallback;
  }

  /**
   * Sets the redirection url validation callback.
   *
   * @param value the new redirection url validation callback
   */
  public void setRedirectionUrlValidationCallback(
      IAutodiscoverRedirectionUrl value) {
    this.redirectionUrlValidationCallback = value;
  }

  /**
   * Gets the dns server address.
   *
   * @return the dns server address
   */
  protected String getDnsServerAddress() {
    return this.dnsServerAddress;
  }

  /**
   * Sets the dns server address.
   *
   * @param value the new dns server address
   */
  protected void setDnsServerAddress(String value) {
    this.dnsServerAddress = value;
  }

  /**
   * Gets a value indicating whether the AutodiscoverService should
   * perform SCP (ServiceConnectionPoint) record lookup when determining
   * the Autodiscover service URL.
   *
   * @return the enable scp lookup
   */
  public boolean getEnableScpLookup() {
    return this.enableScpLookup;
  }

  /**
   * Sets the enable scp lookup.
   *
   * @param value the new enable scp lookup
   */
  public void setEnableScpLookup(boolean value) {
    this.enableScpLookup = value;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * microsoft.exchange.webservices.FuncDelegateInterface#func(java.util.List,
   * java.util.List, java.net.URI)
   */
  @Override
  public Object func(List arg1, List arg2, ExchangeVersion arg3, URI arg4)
      throws ServiceLocalException, Exception {
    if (arg2.get(0).getClass().equals(DomainSettingName.class)) {
      return internalGetDomainSettings(arg1, arg2, arg3, arg4);
    } else if (arg2.get(0).getClass().equals(UserSettingName.class)) {
      return internalGetUserSettings(arg1, arg2, arg3, arg4);
    } else {
      return null;
    }
  }

}
