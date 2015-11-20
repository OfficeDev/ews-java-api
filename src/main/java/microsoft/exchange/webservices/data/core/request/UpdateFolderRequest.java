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
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.core.response.UpdateFolderResponse;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;

import java.util.ArrayList;

/**
 * Represents an UpdateFolder request.
 */
public final class UpdateFolderRequest extends
    MultiResponseServiceRequest<ServiceResponse> {

  /**
   * The folder.
   */
  private ArrayList<Folder> folders = new ArrayList<Folder>();

  /**
   * Initializes a new instance of the UpdateFolderRequest class.
   *
   * @param service           The Servcie
   * @param errorHandlingMode Indicates how errors should be handled.
   * @throws Exception
   */
  public UpdateFolderRequest(ExchangeService service, ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * validates request.
   *
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  @Override
  protected void validate() throws ServiceLocalException, Exception {
    super.validate();
    EwsUtilities.validateParamCollection(this.getFolders().iterator(), "Folders");
    for (int i = 0; i < this.getFolders().size(); i++) {
      Folder folder = this.getFolders().get(i);

      if ((folder == null) || folder.isNew()) {
        throw new IllegalArgumentException(String.format("Folders[%d] is either null or does not have an Id.", i));
      }

      folder.validate();
    }
  }

  /**
   * Creates the service response.
   *
   * @param session       The session
   * @param responseIndex Index of the response.
   * @return Service response.
   */
  @Override
  protected ServiceResponse createServiceResponse(ExchangeService session,
      int responseIndex) {
    return new UpdateFolderResponse(this.getFolders().get(responseIndex));
  }

  /**
   * Gets the name of the XML element.
   *
   * @return Xml element name.
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.UpdateFolder;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return Xml element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.UpdateFolderResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return Xml element name.
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.UpdateFolderResponseMessage;
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages.
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return this.getFolders().size();
  }

  /**
   * Writes to xml.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    writer.writeStartElement(XmlNamespace.Messages,
        XmlElementNames.FolderChanges);

    for (Folder folder : this.folders) {
      folder.writeToXmlForUpdate(writer);
    }

    writer.writeEndElement();
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
   * Gets the folder.
   *
   * @return the folder
   */
  public ArrayList<Folder> getFolders() {
    return this.folders;
  }
}
