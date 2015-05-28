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

package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.ILazyMember;
import microsoft.exchange.webservices.data.core.LazyMember;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.ResolveNamesResponse;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.search.ResolveNameSearchLocation;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.FolderIdWrapperList;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a ResolveNames request.
 */
public final class ResolveNamesRequest extends
    MultiResponseServiceRequest<ResolveNamesResponse> {

  /**
   * The Search scope map.
   */
  private static LazyMember<Map<ResolveNameSearchLocation, String>>
      searchScopeMap =
      new LazyMember<Map<ResolveNameSearchLocation, String>>(
          new ILazyMember<Map<ResolveNameSearchLocation, String>>() {
            @Override
            public Map<ResolveNameSearchLocation, String>
            createInstance() {

              Map<ResolveNameSearchLocation, String> map =
                  new HashMap<ResolveNameSearchLocation, String>();

              map.put(ResolveNameSearchLocation.DirectoryOnly,
                  "ActiveDirectory");
              map.put(ResolveNameSearchLocation.DirectoryThenContacts,
                  "ActiveDirectoryContacts");
              map.put(ResolveNameSearchLocation.ContactsOnly,
                  "Contacts");
              map.put(ResolveNameSearchLocation.ContactsThenDirectory,
                  "ContactsActiveDirectory");

              return map;
            }

          });

  /**
   * The name to resolve.
   */
  private String nameToResolve;

  /**
   * The return full contact data.
   */
  private boolean returnFullContactData;

  /**
   * The search location.
   */
  private ResolveNameSearchLocation searchLocation;

  /**
   * The Contact PropertySet.   *
   */
  private PropertySet contactDataPropertySet;

  /**
   * The parent folder ids.
   */
  private FolderIdWrapperList parentFolderIds = new FolderIdWrapperList();



  /**
   * Asserts the valid.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateNonBlankStringParam(this.
        getNameToResolve(), "NameToResolve");
  }

  /**
   * Creates the service response.
   *
   * @param service       the service
   * @param responseIndex the response index
   * @return Service response
   */
  @Override
  protected ResolveNamesResponse createServiceResponse(
      ExchangeService service, int responseIndex) {
    return new ResolveNamesResponse(service);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.ResolveNames;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.ResolveNamesResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.ResolveNamesResponseMessage;
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param service the service
   * @throws Exception
   */
  public ResolveNamesRequest(ExchangeService service)
      throws Exception {
    super(service, ServiceErrorHandling.ThrowOnError);
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return 1;
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.ReturnFullContactData,
        this.returnFullContactData);

    String searchScope = null;
    if (searchScopeMap.getMember().containsKey(searchLocation)) {
      searchScope = searchScopeMap.getMember().get(searchLocation);
    }

    EwsUtilities
        .ewsAssert((!(searchScope == null || searchScope.isEmpty())),
                   "ResolveNameRequest.WriteAttributesToXml",
                   "The specified search location cannot be mapped to an EWS search scope.");

    String propertySet = null;
    if (this.getContactDataPropertySet() != null) {
      //((PropertyBag)PropertySet.getDefaultPropertySetDictionary( ).getMember()).tryGetValue(this.contactDataPropertySet.getBasePropertySet(),  propertySet);
      if (PropertySet.getDefaultPropertySetMap().getMember()
          .containsKey(this.getContactDataPropertySet().getBasePropertySet())) {
        propertySet = PropertySet.getDefaultPropertySetMap().getMember()
            .get(this.getContactDataPropertySet().getBasePropertySet());
      }
    }

    if (!this.getService().getExchange2007CompatibilityMode()) {
      writer.writeAttributeValue(XmlAttributeNames.
          SearchScope, searchScope);
    }
    if (!(propertySet == null)) {
      writer.writeAttributeValue(XmlAttributeNames.ContactDataShape, propertySet);
    }
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    this.getParentFolderIds().writeToXml(writer, XmlNamespace.Messages,
        XmlElementNames.ParentFolderIds);

    writer.writeElementValue(XmlNamespace.Messages,
        XmlElementNames.UnresolvedEntry, this.getNameToResolve());
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the name to resolve.
   *
   * @return the name to resolve
   */
  public String getNameToResolve() {
    return this.nameToResolve;
  }

  /**
   * Sets the name to resolve.
   *
   * @param nameToResolve the new name to resolve
   */
  public void setNameToResolve(String nameToResolve) {
    this.nameToResolve = nameToResolve;
  }

  /**
   * Gets a value indicating whether to return full contact data or not.
   * "true" if should return full contact data; otherwise, "false".
   *
   * @return the return full contact data
   */
  public boolean getReturnFullContactData() {
    return this.returnFullContactData;
  }

  /**
   * Sets the return full contact data.
   *
   * @param returnFullContactData the new return full contact data
   */
  public void setReturnFullContactData(boolean returnFullContactData) {
    this.returnFullContactData = returnFullContactData;
  }

  /**
   * Gets the search location.
   *
   * @return the search location
   */
  public ResolveNameSearchLocation getSearchLocation() {
    return this.searchLocation;
  }

  /**
   * Sets the search location.
   *
   * @param searchLocation the new search location
   */
  public void setSearchLocation(ResolveNameSearchLocation searchLocation) {
    this.searchLocation = searchLocation;
  }

  /**
   * Gets the parent folder ids.
   *
   * @return the parent folder ids
   */
  public FolderIdWrapperList getParentFolderIds() {
    return this.parentFolderIds;
  }

  /**
   * Gets or sets the PropertySet for Contact Data
   * <p/>
   * The PropertySet
   */
  public void setContactDataPropertySet(PropertySet propertySet) {


    this.contactDataPropertySet = propertySet;
  }

  /**
   * Gets or sets the PropertySet for Contact Data
   *
   * @return The PropertySet
   */
  public PropertySet getContactDataPropertySet() {
    return this.contactDataPropertySet;
  }

}
