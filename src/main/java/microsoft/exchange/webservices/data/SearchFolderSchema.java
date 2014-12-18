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

import java.util.EnumSet;

/**
 * The Class SearchFolderSchema.
 */
@Schema
public class SearchFolderSchema extends FolderSchema {

  /**
   * Field URIs for search folders.
   */
  private static interface FieldUris {

    /**
     * The Search parameters.
     */
    String SearchParameters = "folder:SearchParameters";
  }


  /**
   * Defines the SearchParameters property.
   */
  public static final PropertyDefinition SearchParameters =
      new ComplexPropertyDefinition<SearchFolderParameters>(
          SearchFolderParameters.class,
          XmlElementNames.SearchParameters,
          FieldUris.SearchParameters,
          EnumSet.of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.AutoInstantiateOnRead),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate
              <SearchFolderParameters>() {
            @Override
            public SearchFolderParameters createComplexProperty() {
              return new SearchFolderParameters();
            }
          });

  // This must be declared after the property definitions
  /**
   * The Constant Instance.
   */
  static final SearchFolderSchema Instance = new SearchFolderSchema();

  /**
   * Registers properties.
   */
  // IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
  // same order as they are defined in types.xsd)
  @Override
  protected void registerProperties() {
    super.registerProperties();

    this.registerProperty(SearchParameters);
  }
}
