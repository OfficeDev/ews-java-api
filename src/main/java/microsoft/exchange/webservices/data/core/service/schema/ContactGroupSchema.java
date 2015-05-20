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

package microsoft.exchange.webservices.data.core.service.schema;

import microsoft.exchange.webservices.data.attribute.Schema;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.property.complex.GroupMemberCollection;
import microsoft.exchange.webservices.data.property.complex.ICreateComplexPropertyDelegate;
import microsoft.exchange.webservices.data.property.definition.ComplexPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;

import java.util.EnumSet;

/**
 * Represents the schema for contact groups.
 */
@Schema
public class ContactGroupSchema extends ItemSchema {


  // Defines the DisplayName property.
  /**
   * The Constant DisplayName.
   */
  public static final PropertyDefinition DisplayName =
      ContactSchema.DisplayName;


  // Defines the FileAs property.
  /**
   * The Constant FileAs.
   */
  public static final PropertyDefinition FileAs = ContactSchema.FileAs;


  // Defines the Members property.
  /**
   * The Constant Members.
   */
  public static final PropertyDefinition Members =
      new ComplexPropertyDefinition<GroupMemberCollection>(
          GroupMemberCollection.class,
          XmlElementNames.Members,
          FieldUris.Members,
          EnumSet.of(PropertyDefinitionFlags.AutoInstantiateOnRead,
              PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate),
          ExchangeVersion.Exchange2010,
          new ICreateComplexPropertyDelegate<GroupMemberCollection>() {
            @Override
            public GroupMemberCollection createComplexProperty() {
              return new GroupMemberCollection();
            }
          });


  //This must be declared after the property definitions.
  /**
   * The Constant Instance.
   */
  public static final ContactGroupSchema Instance =
      new ContactGroupSchema();


  //  Initializes a new instance of the
  // <see cref="ContactGroupSchema"/> class.

  /**
   * Instantiates a new contact group schema.
   */
  protected ContactGroupSchema() {
    super();
  }

  //Registers property.
  // IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e.
  // the same order as they are defined in types.xsd)

  /**
   * Registers property.
   * <p>
   * IMPORTANT NOTE: PROPERTIES MUST BE REGISTERED IN SCHEMA ORDER (i.e. the
   * same order as they are defined in types.xsd)
   * </p>
   */
  @Override
  protected void registerProperties() {
    super.registerProperties();

    this.registerProperty(DisplayName);
    this.registerProperty(FileAs);
    this.registerProperty(Members);
  }


  //  Field URIs for Members.


  /**
   * The Interface FieldUris.
   */
  private static interface FieldUris {
    /**
     * FieldUri for members.
     */
    String Members = "distributionlist:Members";
  }

}
