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

package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.misc.id.AlternateId;
import microsoft.exchange.webservices.data.misc.id.AlternateIdBase;
import microsoft.exchange.webservices.data.misc.id.AlternatePublicFolderId;
import microsoft.exchange.webservices.data.misc.id.AlternatePublicFolderItemId;

/**
 * Represents the response to an individual Id conversion operation.
 */
public final class ConvertIdResponse extends ServiceResponse {

  /**
   * The converted id.
   */
  private AlternateIdBase convertedId;

  /**
   * Initializes a new instance of the class.
   */
  public ConvertIdResponse() {
    super();
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws InstantiationException the instantiation exception
   * @throws IllegalAccessException the illegal access exception
   * @throws ServiceLocalException  the service local exception
   * @throws Exception              the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws InstantiationException, IllegalAccessException, ServiceLocalException, Exception {
    super.readElementsFromXml(reader);
    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.AlternateId);
    String alternateIdClass = reader.readAttributeValue(
        XmlNamespace.XmlSchemaInstance, XmlAttributeNames.Type);

    int aliasSeparatorIndex = alternateIdClass.indexOf(':');

    if (aliasSeparatorIndex > -1) {
      alternateIdClass = alternateIdClass
          .substring(aliasSeparatorIndex + 1);
    }

    // Alternate Id classes are responsible fro reading the AlternateId end
    // element when necessary
    if (alternateIdClass.equals(AlternateId.SchemaTypeName)) {
      this.convertedId = new AlternateId();
    } else if (alternateIdClass
        .equals(AlternatePublicFolderId.SchemaTypeName)) {
      this.convertedId = new AlternatePublicFolderId();
    } else if (alternateIdClass
        .equals(AlternatePublicFolderItemId.SchemaTypeName)) {
      this.convertedId = new AlternatePublicFolderItemId();
    } else {
      EwsUtilities
          .ewsAssert(false, "ConvertIdResponse.ReadElementsFromXml",
                     String.format("Unknown alternate Id class: %s", alternateIdClass));
    }

    this.convertedId.loadAttributesFromXml(reader);
    reader.readEndElement(XmlNamespace.Messages,
        XmlElementNames.AlternateId);
  }

  /**
   * Reads response elements from XML.
   *
   * @return the converted id
   */
  public AlternateIdBase getConvertedId() {
    return this.convertedId;
  }

}
