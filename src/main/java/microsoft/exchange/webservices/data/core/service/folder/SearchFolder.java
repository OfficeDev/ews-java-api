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

package microsoft.exchange.webservices.data.core.service.folder;

import microsoft.exchange.webservices.data.attribute.ServiceObjectDefinition;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.schema.SearchFolderSchema;
import microsoft.exchange.webservices.data.core.service.schema.ServiceObjectSchema;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.SearchFolderParameters;

/**
 * Represents a search folder.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.SearchFolder, returnedByServer = true)
public class SearchFolder extends Folder {

  /**
   * Binds to an existing search folder and loads the specified set of
   * property. Calling this method results in a call to EWS.
   *
   * @param service     the service
   * @param id          the id
   * @param propertySet the property set
   * @return A SearchFolder instance representing the search folder
   * corresponding to the specified Id.
   * @throws Exception the exception
   */
  public static SearchFolder bind(ExchangeService service, FolderId id,
      PropertySet propertySet) throws Exception {
    return service.bindToFolder(SearchFolder.class, id, propertySet);
  }

  /**
   * Binds to an existing search folder and loads its first class property.
   * Calling this method results in a call to EWS.
   *
   * @param service the service
   * @param id      the id
   * @return A SearchFolder instance representing the search folder
   * corresponding to the specified Id.
   * @throws Exception the exception
   */
  public static SearchFolder bind(ExchangeService service, FolderId id)
      throws Exception {
    return SearchFolder.bind(service, id, PropertySet
        .getFirstClassProperties());
  }

  /**
   * Binds to an existing search folder and loads the specified set of
   * property. Calling this method results in a call to EWS.
   *
   * @param service     the service
   * @param name        the name
   * @param propertySet the property set
   * @return A SearchFolder instance representing the search folder with the
   * specified name.
   * @throws Exception the exception
   */
  public static SearchFolder bind(ExchangeService service,
      WellKnownFolderName name, PropertySet propertySet)
      throws Exception {
    return SearchFolder.bind(service, new FolderId(name), propertySet);
  }

  /**
   * Binds to an existing search folder and loads the specified set of
   * property. Calling this method results in a call to EWS.
   *
   * @param service the service
   * @param name    the name
   * @return A SearchFolder instance representing the search folder with the
   * specified name.
   * @throws Exception the exception
   */
  public static SearchFolder bind(ExchangeService service,
      WellKnownFolderName name) throws Exception {
    return SearchFolder.bind(service, new FolderId(name), PropertySet
        .getFirstClassProperties());
  }

  /**
   * Initializes an unsaved local instance of the class. To bind to an
   * existing search folder, use SearchFolder.Bind() instead.
   *
   * @param service the service
   * @throws Exception the exception
   */
  public SearchFolder(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Internal method to return the schema associated with this type of object.
   *
   * @return The schema associated with this type of object.
   */
  @Override public ServiceObjectSchema getSchema() {
    return SearchFolderSchema.Instance;
  }

  /**
   * Validates this instance.
   *
   * @throws Exception the exception
   */
  @Override public void validate() throws Exception {
    super.validate();
    if (this.getSearchParameters() != null) {
      this.getSearchParameters().validate();
    }
  }

  /**
   * Gets the minimum required server version.
   *
   * @return Earliest Exchange version in which this service object type is
   * supported.
   */
  @Override public ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the search parameters associated with the search folder.
   *
   * @return the search parameters
   * @throws Exception the exception
   */
  public SearchFolderParameters getSearchParameters() throws Exception {
    return getPropertyBag().getObjectFromPropertyDefinition(
        SearchFolderSchema.SearchParameters);
  }

}
