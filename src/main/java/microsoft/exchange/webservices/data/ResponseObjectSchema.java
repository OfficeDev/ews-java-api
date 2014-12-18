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
 * Represents ResponseObject schema definition.
 */
class ResponseObjectSchema extends ServiceObjectSchema {

  /**
   * The Reference item id.
   */
  public static PropertyDefinition ReferenceItemId =
      new ComplexPropertyDefinition<ItemId>(
          ItemId.class,
          XmlElementNames.ReferenceItemId, EnumSet.of(
          PropertyDefinitionFlags.AutoInstantiateOnRead,
          PropertyDefinitionFlags.CanSet),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<ItemId>() {
            public ItemId createComplexProperty() {
              return new ItemId();
            }
          });

  /**
   * The Body prefix.
   */
  public static final PropertyDefinition BodyPrefix =
      new ComplexPropertyDefinition<MessageBody>(
          MessageBody.class,
          XmlElementNames.NewBodyContent, EnumSet
          .of(PropertyDefinitionFlags.CanSet),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<MessageBody>() {
            public MessageBody createComplexProperty() {
              return new MessageBody();
            }
          });

  /**
   * This must be declared after the property definitions.
   */
  protected static final ResponseObjectSchema Instance =
      new ResponseObjectSchema();

  /**
   * Registers properties. IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN
   * SCHEMA ORDER (i.e. the same order as they are defined in types.xsd)
   */
  @Override
  protected void registerProperties() {
    super.registerProperties();
    this.registerProperty(ResponseObjectSchema.ReferenceItemId);
  }

}
