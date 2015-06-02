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

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.CreateFolderResponse;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;

import java.util.Collection;

/**
 * Represents a CreateFolder request.
 */
public final class CreateFolderRequest extends CreateRequest<Folder, ServiceResponse> {

  /**
   * Initializes a new instance of the CreateFolderRequest class.
   *
   * @param service           The service
   * @param errorHandlingMode Indicates how errors should be handled.
   * @throws Exception
   */
  public CreateFolderRequest(ExchangeService service, ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Validate request.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParam(this.getFolders(), "Folders");

    // Validate each folder.
    for (Folder folder : this.getFolders()) {
      folder.validate();
    }
  }

  /**
   * Creates the service response.
   *
   * @param service       the service
   * @param responseIndex Index of the response.
   * @return Service response.
   */
  @Override
  protected ServiceResponse createServiceResponse(ExchangeService service,
      int responseIndex) {
    return new CreateFolderResponse((Folder) EwsUtilities
        .getEnumeratedObjectAt(this.getFolders(), responseIndex));
  }

  /**
   * Gets the name of the XML element.
   *
   * @return Xml element name
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.CreateFolder;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return Xml element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.CreateFolderResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return Xml element name
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.CreateFolderResponseMessage;
  }

  /**
   * Gets the name of the parent folder XML element.
   *
   * @return Xml element name
   */
  @Override
  protected String getParentFolderXmlElementName() {
    return XmlElementNames.ParentFolderId;
  }

  /**
   * Gets the name of the object collection XML element.
   *
   * @return Xml element name
   */
  @Override
  protected String getObjectCollectionXmlElementName() {
    return XmlElementNames.Folders;
  }

  /**
   * Gets the request version. Earliest Exchange version in which this request
   * is supported.
   *
   * @return the minimum required server version
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the folder.
   *
   * @return the folder
   */
  public Iterable<Folder> getFolders() {
    return this.getObjects();
  }

  /**
   * Sets the folder.
   *
   * @param folder the new folder
   */
  public void setFolders(Iterable<Folder> folder) {
    this.setObjects((Collection<Folder>) folder);
  }

}
