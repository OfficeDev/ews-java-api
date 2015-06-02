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

import java.util.EnumSet;

/**
 * Represents Boolean property definition.
 */
public final class BoolPropertyDefinition extends GenericPropertyDefinition<Boolean> {

  /**
   * Initializes a new instance.
   *
   * @param xmlElementName Name of the XML element.
   * @param uri            The URI.
   * @param version        The version.
   */
  public BoolPropertyDefinition(String xmlElementName, String uri,
      ExchangeVersion version) {
    super(Boolean.class, xmlElementName, uri, version);
  }

  /**
   * Initializes a new instance.
   *
   * @param xmlElementName Name of the XML element.
   * @param uri            The URI.
   * @param flags          The flags.
   * @param version        The version.
   */
  public BoolPropertyDefinition(String xmlElementName, String uri, EnumSet<PropertyDefinitionFlags> flags,
      ExchangeVersion version) {
    super(Boolean.class, xmlElementName, uri, flags, version);
  }

  /**
   * Initializes a new instance.
   *
   * @param xmlElementName Name of the XML element.
   * @param uri            The URI.
   * @param flags          The flags.
   * @param version        The version.
   * @param isNullable     Indicates that this property definition is for a nullable
   *                       property.
   */
  public BoolPropertyDefinition(String xmlElementName, String uri,
      EnumSet<PropertyDefinitionFlags> flags, ExchangeVersion version,
      boolean isNullable) {
    super(Boolean.class, xmlElementName, uri, flags, version, isNullable);
  }

  /**
   * Convert instance to string.
   *
   * @param value The value.
   * @return String representation of property value.
   */
  @Override
  /**
   * Convert instance to string.
   * @param value The value.
   * @return String representation of Boolean property.
   */
  protected String toString(Boolean value) {
    return EwsUtilities.boolToXSBool((Boolean) value);
  }
}
