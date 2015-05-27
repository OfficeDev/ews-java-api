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

package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.ConnectingIdType;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

/**
 * Represents an impersonated user Id.
 */
public final class ImpersonatedUserId {

  /**
   * The id type.
   */
  private ConnectingIdType idType;

  /**
   * The id.
   */
  private String id;

  /**
   * Instantiates a new impersonated user id.
   */
  public ImpersonatedUserId() {
  }

  /**
   * Initializes a new instance of ConnectingId.
   *
   * @param idType The type of this Id.
   * @param id     The user Id.
   */
  public ImpersonatedUserId(ConnectingIdType idType, String id) {
    this();
    this.idType = idType;
    this.id = id;
  }

  /**
   * Writes to XML.
   *
   * @param writer The writer
   * @throws Exception the exception
   */
  public void writeToXml(EwsServiceXmlWriter writer) throws Exception {
    if (this.id == null || this.id.isEmpty()) {
      throw new Exception("The Id property must be set.");
    }

    writer.writeStartElement(XmlNamespace.Types,
        XmlElementNames.ExchangeImpersonation);
    writer.writeStartElement(XmlNamespace.Types,
        XmlElementNames.ConnectingSID);

    // For 2007 SP1, use PrimarySmtpAddress for type SmtpAddress
    String connectingIdTypeLocalName = (this.idType ==
        ConnectingIdType.SmtpAddress) &&
        (writer.getService().getRequestedServerVersion() ==
            ExchangeVersion.Exchange2007_SP1) ?
        XmlElementNames.PrimarySmtpAddress :
        this.getIdType().toString();

    writer.writeElementValue(XmlNamespace.Types, connectingIdTypeLocalName,
        this.id);

    writer.writeEndElement(); // ConnectingSID
    writer.writeEndElement(); // ExchangeImpersonation
  }

  /**
   * Gets  the type of the Id.
   *
   * @return the id type
   */
  public ConnectingIdType getIdType() {
    return idType;
  }

  /**
   * Sets the id type.
   *
   * @param idType the new id type
   */
  public void setIdType(ConnectingIdType idType) {
    this.idType = idType;
  }

  /**
   * Gets  the user Id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(String id) {
    this.id = id;
  }

}
