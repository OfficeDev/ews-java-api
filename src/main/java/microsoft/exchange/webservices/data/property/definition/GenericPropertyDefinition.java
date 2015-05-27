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
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;

import java.io.Serializable;
import java.text.ParseException;
import java.util.EnumSet;

/**
 * Represents generic property definition.
 *
 * @param <TPropertyValue> Property type.
 */
public class GenericPropertyDefinition<TPropertyValue extends Serializable> extends
    TypedPropertyDefinition<TPropertyValue> {

  private Class<TPropertyValue> instance;

  /**
   * Initializes a new instance of the "GenericPropertyDefinition&lt;T&gt;"
   * class.
   *
   * @param xmlElementName Name of the XML element.
   * @param uri            The URI.
   * @param version        The version.
   */
  public GenericPropertyDefinition(Class<TPropertyValue> cls, String xmlElementName, String uri,
      ExchangeVersion version) {
    super(xmlElementName, uri, version);
    this.instance = cls;
  }

  /**
   * Initializes a new instance of the "GenericPropertyDefinition&lt;T&gt;"
   * class.
   *
   * @param xmlElementName Name of the XML element.
   * @param uri            The URI.
   * @param flags          The flags.
   * @param version        The version.
   */
  public GenericPropertyDefinition(Class<TPropertyValue> cls, String xmlElementName, String uri,
      EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version) {
    super(xmlElementName, uri, flags, version);
    this.instance = cls;
  }

  /**
   * Initializes a new instance of the GenericPropertyDefinition class.
   *
   * @param xmlElementName Name of the XML element.
   * @param uri            The URI.
   * @param flags          The flags.
   * @param version        The version.
   * @param isNullable     if set to true, property value is nullable.
   */
  protected GenericPropertyDefinition(
      Class<TPropertyValue> cls,
      String xmlElementName,
      String uri,
      EnumSet<PropertyDefinitionFlags> flags,
      ExchangeVersion version,
      boolean isNullable) {
    super(xmlElementName, uri, flags, version, isNullable);
    this.instance = cls;
  }


  /**
   * Parses the specified value.
   *
   * @param value The value
   * @return Double value from parsed value.
   * @throws java.text.ParseException
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  @Override
  protected TPropertyValue parse(String value) throws InstantiationException,
      IllegalAccessException, ParseException {

    return EwsUtilities.parse(instance, value);
  }

  /**
   * Gets the property type.
   */
  @Override
  public Class<TPropertyValue> getType() {
    return instance;
  }
}
