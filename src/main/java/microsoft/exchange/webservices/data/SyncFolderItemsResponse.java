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
 * Represents the response to a folder items synchronization operation.
 */
public final class SyncFolderItemsResponse extends
    SyncResponse<Item, ItemChange> {

  /**
   * Initializes a new instance of the class.
   *
   * @param propertySet the property set
   */
  protected SyncFolderItemsResponse(PropertySet propertySet) {
    super(propertySet);
  }

  /**
   * Gets the name of the includes last in range XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getIncludesLastInRangeXmlElementName() {
    return XmlElementNames.IncludesLastItemInRange;
  }

  /**
   * Creates an item change instance.
   *
   * @return ItemChange instance
   */
  @Override
  protected ItemChange createChangeInstance() {
    return new ItemChange();
  }

  /**
   * Gets a value indicating whether this request returns full or summary
   * properties. <value> <c>true</c> if summary properties only; otherwise,
   * <c>false</c>. </value>
   *
   * @return the summary properties only
   */
  @Override
  protected boolean getSummaryPropertiesOnly() {
    return true;
  }
}
