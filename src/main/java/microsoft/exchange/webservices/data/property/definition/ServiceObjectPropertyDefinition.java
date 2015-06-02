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

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

/**
 * Represents a property definition for a service object.
 */
public abstract class ServiceObjectPropertyDefinition extends
    PropertyDefinitionBase {

  /**
   * The uri.
   */
  private String uri;

  /**
   * Gets the name of the XML element.
   *
   * @return the name of the XML element.
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.FieldURI;
  }

  /**
   * Gets the minimum Exchange version that supports this property.
   *
   * @return The minimum Exchange version that supports this property.
   */
  @Override
  public ExchangeVersion getVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.FieldURI, this.getUri());
  }

  /**
   * Initializes a new instance.
   */
  protected ServiceObjectPropertyDefinition() {
    super();
  }

  /**
   * Initializes a new instance.
   *
   * @param uri The URI.
   */
  protected ServiceObjectPropertyDefinition(String uri) {
    this();
    EwsUtilities.ewsAssert(!(uri == null || uri.isEmpty()), "ServiceObjectPropertyDefinition.ctor",
                           "uri is null or empty");
    this.uri = uri;
  }

  /**
   * Gets the URI of the property definition.
   *
   * @return The URI of the property definition.
   */
  public String getUri() {
    return uri;
  }
}
