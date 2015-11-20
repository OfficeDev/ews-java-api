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

package microsoft.exchange.webservices.data.property.definition;

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;
import microsoft.exchange.webservices.data.property.complex.ICreateComplexPropertyDelegate;
import microsoft.exchange.webservices.data.property.complex.IOwnedProperty;

import java.util.EnumSet;

/**
 * Represents base complex property type.
 *
 * @param <TComplexProperty> The type of the complex property.
 */
public class ComplexPropertyDefinition<TComplexProperty extends ComplexProperty>
    extends ComplexPropertyDefinitionBase {

  private Class<TComplexProperty> instance;
  /**
   * The property creation delegate.
   */
  private ICreateComplexPropertyDelegate<TComplexProperty> propertyCreationDelegate;

  /**
   * Initializes a new instance.
   *
   * @param xmlElementName           Name of the XML element.
   * @param flags                    The flags.
   * @param version                  The version.
   * @param propertyCreationDelegate Delegate used to create instances of ComplexProperty.
   */
  public ComplexPropertyDefinition(
      Class<TComplexProperty> cls,
      String xmlElementName,
      EnumSet<PropertyDefinitionFlags> flags,
      ExchangeVersion version,
      ICreateComplexPropertyDelegate<TComplexProperty>
          propertyCreationDelegate) {
    super(xmlElementName, flags, version);
    this.instance = cls;
    EwsUtilities.ewsAssert(propertyCreationDelegate != null, "ComplexPropertyDefinition ctor",
                           "CreateComplexPropertyDelegate cannot be null");

    this.propertyCreationDelegate = propertyCreationDelegate;
  }

  /**
   * Initializes a new instance.
   *
   * @param xmlElementName           Name of the XML element.
   * @param uri                      The URI.
   * @param version                  The version.
   * @param propertyCreationDelegate Delegate used to create instances of ComplexProperty.
   */
  public ComplexPropertyDefinition(
      Class<TComplexProperty> cls,
      String xmlElementName,
      String uri,
      ExchangeVersion version,
      ICreateComplexPropertyDelegate<TComplexProperty>
          propertyCreationDelegate) {
    super(xmlElementName, uri, version);
    this.instance = cls;
    this.propertyCreationDelegate = propertyCreationDelegate;
  }

  public ComplexPropertyDefinition(String xmlElementName, String uri, ExchangeVersion version,
      ICreateComplexPropertyDelegate<TComplexProperty> propertyCreationDelegate) {
    super(xmlElementName, uri, version);
    this.propertyCreationDelegate = propertyCreationDelegate;
  }

  /**
   * Instantiates a new complex property definition.
   *
   * @param xmlElementName           the xml element name
   * @param uri                      the uri
   * @param flags                    the flags
   * @param version                  the version
   * @param propertyCreationDelegate the property creation delegate
   */
  public ComplexPropertyDefinition(Class<TComplexProperty> cls, String xmlElementName, String uri,
      EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version,
      ICreateComplexPropertyDelegate<TComplexProperty> propertyCreationDelegate) {
    super(xmlElementName, uri, flags, version);
    this.instance = cls;
    this.propertyCreationDelegate = propertyCreationDelegate;
  }


  /**
   * Instantiates a new complex property definition.
   *
   * @param xmlElementName           the xml element name
   * @param attachments              the attachments
   * @param flags                    the flags
   * @param version                  the version
   * @param propertyCreationDelegate the property creation delegate
   */
  public ComplexPropertyDefinition(
      String attachments,
      String xmlElementName,
      ExchangeVersion version,
      EnumSet<PropertyDefinitionFlags> flags,
      ICreateComplexPropertyDelegate<TComplexProperty> propertyCreationDelegate) {
    // TODO Auto-generated constructor stub
    super(xmlElementName, attachments, flags, version);
    this.propertyCreationDelegate = propertyCreationDelegate;
  }

  /**
   * Creates the property instance.
   *
   * @param owner The owner.
   * @return ComplexProperty instance.
   */
  @Override public ComplexProperty createPropertyInstance(ServiceObject owner) {
    TComplexProperty complexProperty = this.propertyCreationDelegate
        .createComplexProperty();
    if (complexProperty instanceof IOwnedProperty) {
      IOwnedProperty ownedProperty = (IOwnedProperty) complexProperty;
      ownedProperty.setOwner(owner);
    }
    return complexProperty;
  }

  /**
   * Gets the property type.
   */
  @Override
  public Class<TComplexProperty> getType() {
                /*ParameterizedType parameterizedType =
                (ParameterizedType) getClass().getGenericSuperclass();
	     return (Class) parameterizedType.getActualTypeArguments()[0];

		 instance = ((Class)((ParameterizedType)this.getClass(). 
			       getGenericSuperclass()).getActualTypeArguments()[0]).
			       newInstance(); */
                /*return ((Class)((ParameterizedType)this.getClass().
			       getGenericSuperclass()).getActualTypeArguments()[0]).
			       newInstance();*/
    //return ComplexProperty.class;
    return this.instance;
  }
}
