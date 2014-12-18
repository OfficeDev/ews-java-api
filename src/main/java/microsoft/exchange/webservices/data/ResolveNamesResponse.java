/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents the response to a name resolution operation.
 */
final class ResolveNamesResponse extends ServiceResponse {

  /**
   * The resolutions.
   */
  private NameResolutionCollection resolutions;

  /**
   * Initializes a new instance of the class.
   *
   * @param service the service
   */
  protected ResolveNamesResponse(ExchangeService service) {
    super();
    EwsUtilities.EwsAssert(service != null, "ResolveNamesResponse.ctor",
        "service is null");

    this.resolutions = new NameResolutionCollection(service);
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.readElementsFromXml(reader);
    this.resolutions.loadFromXml(reader);
  }

  /**
   * Override base implementation so that API does not throw when name
   * resolution fails to find a match. EWS returns an error in this case but
   * the API will just return an empty NameResolutionCollection.
   *
   * @throws ServiceResponseException the service response exception
   */
  @Override
  protected void internalThrowIfNecessary() throws ServiceResponseException {
    if (this.getErrorCode() != ServiceError.ErrorNameResolutionNoResults) {
      super.internalThrowIfNecessary();
    }
  }

  /**
   * Gets a list of name resolution suggestions.
   *
   * @return the resolutions
   */
  public NameResolutionCollection getResolutions() {
    return this.resolutions;
  }
}
