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

package microsoft.exchange.webservices.data.autodiscover.configuration.outlook;

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.autodiscover.IFunc;
import microsoft.exchange.webservices.data.autodiscover.WebClientUrl;
import microsoft.exchange.webservices.data.autodiscover.WebClientUrlCollection;
import microsoft.exchange.webservices.data.autodiscover.response.GetUserSettingsResponse;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.ILazyMember;
import microsoft.exchange.webservices.data.core.LazyMember;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.autodiscover.enumeration.OutlookProtocolType;
import microsoft.exchange.webservices.data.autodiscover.enumeration.UserSettingName;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.security.XmlNodeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents a supported Outlook protocol in an Outlook configurations settings
 * account.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
final class OutlookProtocol {

  /**
   * The Constant EXCH.
   */
  private final static String EXCH = "EXCH";

  /**
   * The Constant EXPR.
   */
  private final static String EXPR = "EXPR";

  /**
   * The Constant WEB.
   */
  private final static String WEB = "WEB";

  /**
   * Converters to translate common Outlook protocol settings.
   * Each entry maps to a lambda expression used to
   * get the matching property from the OutlookProtocol instance.
   */
  private static LazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>
      commonProtocolSettings =
      new LazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>(
          new ILazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>() {
            public Map<UserSettingName, IFunc<OutlookProtocol, Object>> createInstance() {

              Map<UserSettingName, IFunc<OutlookProtocol, Object>> results =
                  new HashMap<UserSettingName, IFunc<OutlookProtocol, Object>>();

              results.put(UserSettingName.EcpDeliveryReportUrlFragment,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.ecpUrlMt;
                    }
                  });
              results.put(UserSettingName.EcpEmailSubscriptionsUrlFragment,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.ecpUrlAggr;
                    }
                  });
              results.put(UserSettingName.EcpPublishingUrlFragment,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.ecpUrlPublish;
                    }
                  });
              results.put(UserSettingName.EcpRetentionPolicyTagsUrlFragment,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.ecpUrlRet;
                    }
                  });
              results.put(UserSettingName.EcpTextMessagingUrlFragment,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.ecpUrlSms;
                    }
                  });
              results.put(UserSettingName.EcpVoicemailUrlFragment,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.ecpUrlUm;
                    }
                  });
              return results;
            }
          });


  /**
   * Converters to translate internal (EXCH) Outlook protocol settings.
   * Each entry maps to a lambda expression used to
   * get the matching property from the OutlookProtocol instance.
   */
  private static LazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>
      internalProtocolSettings =
      new LazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>(
          new ILazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>() {
            public Map<UserSettingName, IFunc<OutlookProtocol, Object>> createInstance() {

              Map<UserSettingName, IFunc<OutlookProtocol, Object>> results =
                  new HashMap<UserSettingName, IFunc<OutlookProtocol, Object>>();

              results.put(UserSettingName.ActiveDirectoryServer,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.activeDirectoryServer;
                    }
                  });
              results.put(UserSettingName.CrossOrganizationSharingEnabled,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return String.valueOf(arg.sharingEnabled);
                    }
                  });
              results.put(UserSettingName.InternalEcpUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.ecpUrl;
                    }
                  });
              results.put(UserSettingName.InternalEcpDeliveryReportUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.convertEcpFragmentToUrl(arg.ecpUrlMt);
                    }
                  });
              results.put(UserSettingName.InternalEcpEmailSubscriptionsUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.convertEcpFragmentToUrl(arg.ecpUrlAggr);
                    }
                  });
              results.put(UserSettingName.InternalEcpPublishingUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.convertEcpFragmentToUrl(arg.ecpUrlPublish);
                    }
                  });
              results.put(UserSettingName.InternalEcpRetentionPolicyTagsUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.convertEcpFragmentToUrl(arg.ecpUrlRet);
                    }
                  });
              results.put(UserSettingName.InternalEcpTextMessagingUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.convertEcpFragmentToUrl(arg.ecpUrlSms);
                    }
                  });
              results.put(UserSettingName.InternalEcpVoicemailUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.convertEcpFragmentToUrl(arg.ecpUrlUm);
                    }
                  });
              results.put(UserSettingName.InternalEwsUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.exchangeWebServicesUrl == null ?
                          arg.availabilityServiceUrl : arg.exchangeWebServicesUrl;
                    }
                  });
              results.put(UserSettingName.InternalMailboxServerDN,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.serverDN;
                    }
                  });
              results.put(UserSettingName.InternalRpcClientServer,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.server;
                    }
                  });
              results.put(UserSettingName.InternalOABUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.offlineAddressBookUrl;
                    }
                  });
              results.put(UserSettingName.InternalUMUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.unifiedMessagingUrl;
                    }
                  });
              results.put(UserSettingName.MailboxDN,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.mailboxDN;
                    }
                  });
              results.put(UserSettingName.PublicFolderServer,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.publicFolderServer;
                    }
                  });
              results.put(UserSettingName.GroupingInformation,
                new IFunc<OutlookProtocol, Object>() {
                  public Object func(OutlookProtocol arg) {
                    return arg.groupingInformation;
                  }
                });

              return results;
            }
          });

  /**
   * Converters to translate external (EXPR) Outlook protocol settings.
   * Each entry maps to a lambda expression used to
   * get the matching property from the OutlookProtocol instance.
   */
  private static LazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>
      externalProtocolSettings =
      new LazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>(
          new ILazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>() {
            public Map<UserSettingName, IFunc<OutlookProtocol, Object>> createInstance() {

              Map<UserSettingName, IFunc<OutlookProtocol, Object>> results =
                  new HashMap<UserSettingName, IFunc<OutlookProtocol, Object>>();

              results.put(UserSettingName.ExternalEcpDeliveryReportUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.convertEcpFragmentToUrl(arg.ecpUrlRet);
                    }
                  });
              results.put(UserSettingName.ExternalEcpEmailSubscriptionsUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.convertEcpFragmentToUrl(arg.ecpUrlAggr);
                    }
                  });
              results.put(UserSettingName.ExternalEcpPublishingUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.convertEcpFragmentToUrl(arg.ecpUrlPublish);
                    }
                  });
              results.put(UserSettingName.ExternalEcpRetentionPolicyTagsUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.convertEcpFragmentToUrl(arg.ecpUrlRet);
                    }
                  });
              results.put(UserSettingName.ExternalEcpTextMessagingUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.convertEcpFragmentToUrl(arg.ecpUrlSms);
                    }
                  });
              results.put(UserSettingName.ExternalEcpUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.ecpUrl;
                    }
                  });
              results.put(UserSettingName.ExternalEcpVoicemailUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.convertEcpFragmentToUrl(arg.ecpUrlUm);
                    }
                  });
              results.put(UserSettingName.ExternalEwsUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.exchangeWebServicesUrl == null ?
                          arg.availabilityServiceUrl : arg.exchangeWebServicesUrl;
                    }
                  });
              results.put(UserSettingName.ExternalMailboxServer,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.server;
                    }
                  });
              results.put(
                  UserSettingName.ExternalMailboxServerAuthenticationMethods,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.authPackage;
                    }
                  });
              results.put(
                  UserSettingName.ExternalMailboxServerRequiresSSL,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return String.valueOf(arg.sslEnabled);
                    }
                  });
              results.put(UserSettingName.ExternalOABUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.offlineAddressBookUrl;
                    }
                  });
              results.put(UserSettingName.ExternalUMUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.unifiedMessagingUrl;
                    }
                  });
              results.put(UserSettingName.ExchangeRpcUrl,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.exchangeRpcUrl;
                    }
                  });
              return results;
            }
          });


  /**
   * Merged converter dictionary for translating
   * internal (EXCH) Outlook protocol settings.
   * Each entry maps to a lambda expression used to
   * get the matching property from the OutlookProtocol instance.
   */
  private static LazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>
      internalProtocolConverterDictionary =
      new LazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>(
          new ILazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>() {
            public Map<UserSettingName, IFunc<OutlookProtocol, Object>> createInstance() {

              Map<UserSettingName, IFunc<OutlookProtocol, Object>> results =
                  new HashMap<UserSettingName, IFunc<OutlookProtocol, Object>>();
              for (Entry<UserSettingName, IFunc<OutlookProtocol, Object>> kv : commonProtocolSettings
                  .getMember().entrySet()) {
                results.put(kv.getKey(), kv.getValue());
              }
              for (Entry<UserSettingName, IFunc<OutlookProtocol, Object>> kv : internalProtocolSettings
                  .getMember().entrySet()) {
                results.put(kv.getKey(), kv.getValue());
              }
              return results;
            }
          });


  /**
   * Merged converter dictionary for translating
   * external (EXPR) Outlook protocol settings.
   * Each entry maps to a lambda expression used to
   * get the matching property from the OutlookProtocol instance.
   */
  private static LazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>
      externalProtocolConverterDictionary =
      new LazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>(
          new ILazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>() {
            public Map<UserSettingName, IFunc<OutlookProtocol, Object>> createInstance() {

              Map<UserSettingName, IFunc<OutlookProtocol, Object>> results =
                  new HashMap<UserSettingName, IFunc<OutlookProtocol, Object>>();
              for (Entry<UserSettingName, IFunc<OutlookProtocol, Object>> kv : commonProtocolSettings
                  .getMember().entrySet()) {
                results.put(kv.getKey(), kv.getValue());
              }
              for (Entry<UserSettingName, IFunc<OutlookProtocol, Object>> kv : externalProtocolSettings
                  .getMember().entrySet()) {
                results.put(kv.getKey(), kv.getValue());
              }
              return results;
            }
          });


  /**
   * Converters to translate Web (WEB) Outlook protocol settings.
   * Each entry maps to a lambda expression used to
   * get the matching property from the OutlookProtocol instance.
   */
  private static LazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>
      webProtocolConverterDictionary =
      new LazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>(
          new ILazyMember<Map<UserSettingName, IFunc<OutlookProtocol, Object>>>() {
            public Map<UserSettingName, IFunc<OutlookProtocol, Object>> createInstance() {

              Map<UserSettingName, IFunc<OutlookProtocol, Object>> results =
                  new HashMap<UserSettingName, IFunc<OutlookProtocol, Object>>();

              results.put(UserSettingName.InternalWebClientUrls,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.internalOutlookWebAccessUrls;
                    }
                  });
              results.put(UserSettingName.ExternalWebClientUrls,
                  new IFunc<OutlookProtocol, Object>() {
                    public Object func(OutlookProtocol arg) {
                      return arg.externalOutlookWebAccessUrls;
                    }
                  });
              return results;
            }
          });

  /**
   * Each entry maps to a lambda expression used to
   * get the matching property from the OutlookProtocol instance.
   */
  private static LazyMember<List<UserSettingName>>
      availableUserSettings =
      new LazyMember<List<UserSettingName>>(
          new ILazyMember<List<UserSettingName>>() {
            public List<UserSettingName> createInstance() {

              List<UserSettingName> results =
                  new ArrayList<UserSettingName>();

              results.addAll(commonProtocolSettings.
                  getMember().keySet());
              results.addAll(internalProtocolSettings.
                  getMember().keySet());
              results.addAll(externalProtocolSettings.
                  getMember().keySet());
              results.addAll(webProtocolConverterDictionary.
                  getMember().keySet());
              return results;
            }
          });


  /**
   * Map Outlook protocol name to type.
   */
  private static LazyMember<Map<String, OutlookProtocolType>>
      protocolNameToTypeMap =
      new LazyMember<Map<String, OutlookProtocolType>>(
          new ILazyMember<Map<String, OutlookProtocolType>>() {
            @Override
            public Map<String, OutlookProtocolType> createInstance() {
              Map<String, OutlookProtocolType> results =
                  new HashMap<String, OutlookProtocolType>();
              results.put(OutlookProtocol.EXCH, OutlookProtocolType.Rpc);
              results.put(OutlookProtocol.EXPR, OutlookProtocolType.RpcOverHttp);
              results.put(OutlookProtocol.WEB, OutlookProtocolType.Web);
              return results;

            }
          });


  /**
   * The constant activeDirectoryServer.
   */
  private String activeDirectoryServer;
  /**
   * The constant authPackage.
   */
  private String authPackage;
  /**
   * The constant availabilityServiceUrl.
   */
  private String availabilityServiceUrl;
  /**
   * The constant ecpUrl.
   */
  private String ecpUrl;
  /**
   * The constant ecpUrlAggr.
   */
  private String ecpUrlAggr;
  /**
   * The constant ecpUrlMt.
   */
  private String ecpUrlMt;
  /**
   * The constant ecpUrlPublish.
   */
  private String ecpUrlPublish;
  /**
   * The constant ecpUrlRet.
   */
  private String ecpUrlRet;
  /**
   * The constant ecpUrlSms.
   */
  private String ecpUrlSms;
  /**
   * The constant ecpUrlUm.
   */
  private String ecpUrlUm;
  /**
   * The constant exchangeWebServicesUrl.
   */
  private String exchangeWebServicesUrl;
  /**
   * The constant mailboxDN.
   */
  private String mailboxDN;
  /**
   * The constant offlineAddressBookUrl.
   */
  private String offlineAddressBookUrl;
  /**
   * The constant exchangeRpcUrl.
   */
  private String exchangeRpcUrl;
  /**
   * The constant publicFolderServer.
   */
  private String publicFolderServer;
  /**
   * The constant server.
   */
  private String server;
  /**
   * The constant serverDN.
   */
  private String serverDN;
  /**
   * The constant unifiedMessagingUrl.
   */
  private String unifiedMessagingUrl;
  /**
   * The constant sharingEnabled.
   */
  private boolean sharingEnabled;
  /**
   * The constant sslEnabled.
   */
  private boolean sslEnabled;
  /**
   * The constant externalOutlookWebAccessUrls.
   */
  private WebClientUrlCollection externalOutlookWebAccessUrls;
  /**
   * The constant internalOutlookWebAccessUrls.
   */
  private WebClientUrlCollection internalOutlookWebAccessUrls;
  /**
   * The constant groupingInformation.
   */
  private String groupingInformation;


  /**
   * Initializes a new instance of the OutlookProtocol class.
   */
  protected OutlookProtocol() {
    this.internalOutlookWebAccessUrls = new WebClientUrlCollection();
    this.externalOutlookWebAccessUrls = new WebClientUrlCollection();
  }


  /**
   * Parses the XML using the specified reader and creates an Outlook
   * protocol.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  protected void loadFromXml(EwsXmlReader reader)
      throws Exception {
    do {
      reader.read();
      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName().equals(XmlElementNames.Type)) {
          this.setProtocolType(OutlookProtocol.
              protocolNameToType(reader.readElementValue()));
        } else if (reader.getLocalName().equals(XmlElementNames.AuthPackage)) {
          this.authPackage = reader.readElementValue();
        } else if (reader.getLocalName().equals(XmlElementNames.Server)) {
          this.server = reader.readElementValue();
        } else if (reader.getLocalName().equals(XmlElementNames.ServerDN)) {
          this.serverDN = reader.readElementValue();
        } else if (reader.getLocalName().equals(XmlElementNames.ServerVersion)) {
          reader.readElementValue();
        } else if (reader.getLocalName().equals(XmlElementNames.AD)) {
          this.activeDirectoryServer = reader.readElementValue();
        } else if (reader.getLocalName().equals(XmlElementNames.MdbDN)) {
          this.mailboxDN = reader.readElementValue();
        } else if (reader.getLocalName().equals(XmlElementNames.EWSUrl)) {
          this.exchangeWebServicesUrl = reader.readElementValue();
        } else if (reader.getLocalName().equals(XmlElementNames.ASUrl)) {
          this.availabilityServiceUrl = reader.readElementValue();
        } else if (reader.getLocalName().equals(XmlElementNames.OOFUrl)) {
          reader.readElementValue();
        } else if (reader.getLocalName().equals(XmlElementNames.UMUrl)) {
          this.unifiedMessagingUrl = reader.readElementValue();
        } else if (reader.getLocalName().equals(XmlElementNames.OABUrl)) {
          this.offlineAddressBookUrl = reader.readElementValue();
        } else if (reader.getLocalName().equals(
            XmlElementNames.PublicFolderServer)) {
          this.publicFolderServer = reader.readElementValue();
        } else if (reader.getLocalName().equals(
            XmlElementNames.Internal)) {
          OutlookProtocol.loadWebClientUrlsFromXml(reader,
              this.internalOutlookWebAccessUrls, reader.getLocalName());
        } else if (reader.getLocalName().equals(
            XmlElementNames.External)) {
          OutlookProtocol.loadWebClientUrlsFromXml(reader,
              this.externalOutlookWebAccessUrls, reader.getLocalName());
        } else if (reader.getLocalName().equals(
            XmlElementNames.Ssl)) {
          String sslStr = reader.readElementValue();
          this.sslEnabled = sslStr.equalsIgnoreCase("On");
        } else if (reader.getLocalName().equals(
            XmlElementNames.SharingUrl)) {
          this.sharingEnabled = reader.
              readElementValue().length() > 0;
        } else if (reader.getLocalName().equals(
            XmlElementNames.EcpUrl)) {
          this.ecpUrl = reader.readElementValue();
        } else if (reader.getLocalName().equals(
            XmlElementNames.EcpUrl_um)) {
          this.ecpUrlUm = reader.readElementValue();
        } else if (reader.getLocalName().equals(
            XmlElementNames.EcpUrl_aggr)) {
          this.ecpUrlAggr = reader.readElementValue();
        } else if (reader.getLocalName().equals(
            XmlElementNames.EcpUrl_sms)) {
          this.ecpUrlSms = reader.readElementValue();
        } else if (reader.getLocalName().equals(
            XmlElementNames.EcpUrl_mt)) {
          this.ecpUrlMt = reader.readElementValue();
        } else if (reader.getLocalName().equals(
            XmlElementNames.EcpUrl_ret)) {
          this.ecpUrlRet = reader.readElementValue();
        } else if (reader.getLocalName().equals(
            XmlElementNames.EcpUrl_publish)) {
          this.ecpUrlPublish = reader.readElementValue();
        } else if (reader.getLocalName().equals(
            XmlElementNames.ExchangeRpcUrl)) {
            this.exchangeRpcUrl = reader.readElementValue();
        } else if (reader.getLocalName().equals(
            XmlElementNames.GroupingInformation)) {
            this.groupingInformation = reader.readElementValue();
        } else {
          reader.skipCurrentElement();
        }
      }
    } while (!reader.isEndElement(XmlNamespace.NotSpecified,
        XmlElementNames.Protocol));
  }

  /**
   * Convert protocol name to protocol type.
   *
   * @param protocolName Name of the protocol.
   * @return OutlookProtocolType
   */
  private static OutlookProtocolType protocolNameToType(String
      protocolName) {
    OutlookProtocolType protocolType = null;
    if (!(protocolNameToTypeMap.getMember().containsKey(protocolName))) {
      protocolType = OutlookProtocolType.Unknown;
    } else {
      protocolType = protocolNameToTypeMap.getMember().get(protocolName);
    }
    return protocolType;

  }

  /**
   * Loads web client urls from XML.
   *
   * @param reader        The reader.
   * @param webClientUrls The web client urls.
   * @param elementName   Name of the element.
   * @throws Exception
   */
  private static void loadWebClientUrlsFromXml(EwsXmlReader reader,
      WebClientUrlCollection webClientUrls, String elementName) throws Exception {
    do {
      reader.read();

      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName().equals(XmlElementNames.OWAUrl)) {
          String authMethod = reader.readAttributeValue(
              XmlAttributeNames.AuthenticationMethod);
          String owaUrl = reader.readElementValue();
          WebClientUrl webClientUrl =
              new WebClientUrl(authMethod, owaUrl);
          webClientUrls.getUrls().add(webClientUrl);
        } else {
          reader.skipCurrentElement();
        }
      }
    }
    while (!reader.isEndElement(XmlNamespace.NotSpecified, elementName));
  }


  /**
   * Convert ECP fragment to full ECP URL.
   *
   * @param fragment The fragment.
   * @return Full URL string (or null if either portion is empty.
   */
  private String convertEcpFragmentToUrl(String fragment) {
    return ((this.ecpUrl == null || this.ecpUrl.isEmpty()) ||
        (fragment == null || fragment.isEmpty())) ? null : (this.ecpUrl + fragment);
  }

  /**
   * Convert OutlookProtocol to GetUserSettings response.
   *
   * @param requestedSettings The requested settings.
   * @param response          The response.
   */
  protected void convertToUserSettings(
      List<UserSettingName> requestedSettings,
      GetUserSettingsResponse response) {
    if (this.getConverterDictionary() != null) {
      // In English: collect converters that are contained in the requested settings.
      Map<UserSettingName, IFunc<OutlookProtocol, Object>> converterQuery =
          new HashMap<UserSettingName, IFunc<OutlookProtocol, Object>>();
      Map<UserSettingName, IFunc<OutlookProtocol, Object>> t =
          this.getConverterDictionary();
      for (Entry<UserSettingName, IFunc<OutlookProtocol, Object>> map : t.entrySet()) {
        if (requestedSettings.contains(map.getKey())) {
          converterQuery.put(map.getKey(), map.getValue());
        }
      }

      for (Entry<UserSettingName, IFunc<OutlookProtocol, Object>> kv : converterQuery.entrySet()) {
        Object value = kv.getValue().func(this);
        if (value != null) {
          response.getSettings().put(kv.getKey(), value);
        }
      }
    }
  }

  private OutlookProtocolType protocolType;

  /**
   * Gets the type of the protocol.
   *
   * @return The type of the protocol.
   */
  protected OutlookProtocolType getProtocolType() {
    return this.protocolType;
  }

  /**
   * Sets the type of the protocol.
   */
  protected void setProtocolType(OutlookProtocolType protocolType) {
    this.protocolType = protocolType;
  }

  /**
   * Gets the converter dictionary for protocol type.
   *
   * @return The converter dictionary.
   */
  private Map<UserSettingName, IFunc<OutlookProtocol, Object>>
  getConverterDictionary() {
    switch (this.getProtocolType()) {
      case Rpc:
        return internalProtocolConverterDictionary.getMember();
      case RpcOverHttp:
        return externalProtocolConverterDictionary.getMember();
      case Web:
        return webProtocolConverterDictionary.getMember();
      default:
        return null;
    }
  }


  /**
   * Gets the available user settings.
   *
   * @return availableUserSettings
   */
  protected static List<UserSettingName> getAvailableUserSettings() {
    return availableUserSettings.getMember();
  }
}

