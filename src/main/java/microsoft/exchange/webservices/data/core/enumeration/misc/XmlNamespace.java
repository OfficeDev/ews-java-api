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

package microsoft.exchange.webservices.data.core.enumeration.misc;

import microsoft.exchange.webservices.data.core.EwsUtilities;

/**
 * Defines the namespaces as used by the EwsXmlReader, EwsServiceXmlReader, and
 * EwsServiceXmlWriter classes.
 */
public enum XmlNamespace {
        /*
	 * The namespace is not specified.
	 */
  /**
   * The Not specified.
   */
  NotSpecified("", ""),

  /**
   * The Messages.
   */
  Messages(EwsUtilities.EwsMessagesNamespacePrefix,
      EwsUtilities.EwsMessagesNamespace),

  /**
   * The Types.
   */
  Types(EwsUtilities.EwsTypesNamespacePrefix, EwsUtilities.EwsTypesNamespace),

  /**
   * The Errors.
   */
  Errors(EwsUtilities.EwsErrorsNamespacePrefix,
      EwsUtilities.EwsErrorsNamespace),

  /**
   * The Soap.
   */
  Soap(EwsUtilities.EwsSoapNamespacePrefix, EwsUtilities.EwsSoapNamespace),

  /**
   * The Soap12.
   */
  Soap12(EwsUtilities.EwsSoapNamespacePrefix,
      EwsUtilities.EwsSoap12Namespace),

  /**
   * The Xml schema instance.
   */
  XmlSchemaInstance(EwsUtilities.EwsXmlSchemaInstanceNamespacePrefix,
      EwsUtilities.EwsXmlSchemaInstanceNamespace),

  /**
   * The Passport soap fault.
   */
  PassportSoapFault(EwsUtilities.PassportSoapFaultNamespacePrefix,
      EwsUtilities.PassportSoapFaultNamespace),

  /**
   * The WS trust february2005.
   */
  WSTrustFebruary2005(EwsUtilities.WSTrustFebruary2005NamespacePrefix,
      EwsUtilities.WSTrustFebruary2005Namespace),

  /**
   * The WS addressing.
   */
  WSAddressing(EwsUtilities.WSAddressingNamespacePrefix,
      EwsUtilities.WSAddressingNamespace),

  /**
   * The Autodiscover.
   */
  Autodiscover(EwsUtilities.AutodiscoverSoapNamespacePrefix,
      EwsUtilities.AutodiscoverSoapNamespace);

  /**
   * The prefix.
   */
  private String prefix;

  /**
   * The name space uri.
   */
  private String nameSpaceUri;

  /**
   * Instantiates a new xml namespace.
   *
   * @param prefix       the prefix
   * @param nameSpaceUri the name space uri
   */
  XmlNamespace(String prefix, String nameSpaceUri) {
    this.prefix = prefix;
    this.nameSpaceUri = nameSpaceUri;
  }

  /**
   * Gets the name space uri.
   *
   * @return the name space uri
   */
  public String getNameSpaceUri() {
    return this.nameSpaceUri;
  }

  /**
   * Gets the name space prefix.
   *
   * @return the name space prefix
   */
  public String getNameSpacePrefix() {
    return this.prefix;
  }
}
