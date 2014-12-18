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
 * Represents response to generic Create request.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
final class CreateResponseObjectResponse extends CreateItemResponseBase {

  /**
   * Gets Item instance.
   *
   * @param service        The service.
   * @param xmlElementName Name of the XML element.
   * @return Item.
   * @throws Exception the exception
   */
  @Override
  protected Item getObjectInstance(ExchangeService service,
      String xmlElementName) throws Exception {
    try {
      return EwsUtilities.createEwsObjectFromXmlElementName(Item.class,
          service, xmlElementName);
    } catch (InstantiationException e) {
      e.printStackTrace();
      return null;
    } catch (IllegalAccessException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Initializes a new instance of the CreateResponseObjectResponse class.
   */
  protected CreateResponseObjectResponse() {
    super();
  }

}
