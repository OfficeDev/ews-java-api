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

package microsoft.exchange.webservices.data.core.requests;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.responses.ServiceResponse;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.enumerations.DeleteMode;
import microsoft.exchange.webservices.data.enumerations.ServiceErrorHandling;
import microsoft.exchange.webservices.data.exceptions.ServiceXmlSerializationException;

/**
 * Represents an abstract Delete request.
 *
 * @param <TResponse> The type of the response.
 */
abstract class DeleteRequest<TResponse extends ServiceResponse> extends
    MultiResponseServiceRequest<TResponse> {

  /**
   * Delete mode. Default is SoftDelete.
   */
  private DeleteMode deleteMode = DeleteMode.SoftDelete;

  /**
   * Initializes a new instance of the DeleteRequest class.
   *
   * @param service           The Servcie
   * @param errorHandlingMode Indicates how errors should be handled.
   * @throws Exception
   */
  protected DeleteRequest(ExchangeService service,
      ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Writes XML attributes.
   *
   * @param writer The writer.
   * @throws microsoft.exchange.webservices.data.exceptions.ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    super.writeAttributesToXml(writer);

    try {
      writer.writeAttributeValue(XmlAttributeNames.DeleteType, this
          .getDeleteMode());
    } catch (ServiceXmlSerializationException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets the delete mode.
   *
   * @return the delete mode
   */
  public DeleteMode getDeleteMode() {
    return this.deleteMode;
  }

  /**
   * Gets the delete mode.e
   *
   * @param deleteMode the new delete mode
   */
  public void setDeleteMode(DeleteMode deleteMode) {
    this.deleteMode = deleteMode;
  }

}
