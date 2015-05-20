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

package microsoft.exchange.webservices.data.misc.id;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.IdFormat;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

/**
 * Represents the Id of a public folder expressed in a specific format.
 */
public class AlternatePublicFolderId extends AlternateIdBase {

  /**
   * Name of schema type used for AlternatePublicFolderId element.
   */
  public final static String SchemaTypeName =
      "AlternatePublicFolderIdType";

  private String folderId;

  /**
   * Initializes a new instance of AlternatePublicFolderId.
   */
  public AlternatePublicFolderId() {
    super();
  }

  /**
   * Initializes a new instance of AlternatePublicFolderId.
   *
   * @param format   the format
   * @param folderId the folder id
   */
  public AlternatePublicFolderId(IdFormat format, String folderId) {
    super(format);
    this.setFolderId(folderId);
  }

  /**
   * The Id of the public folder.
   *
   * @return the folder id
   */
  public String getFolderId() {
    return this.folderId;

  }

  /**
   * Sets the folder id.
   *
   * @param folderId the new folder id
   */
  public void setFolderId(String folderId) {
    this.folderId = folderId;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.AlternatePublicFolderId;
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
    super.writeAttributesToXml(writer);
    writer.writeAttributeValue(XmlAttributeNames.FolderId, this
        .getFolderId());
  }

  /**
   * Loads the attribute from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override public void loadAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.loadAttributesFromXml(reader);
    this.setFolderId(reader.readAttributeValue(XmlAttributeNames.FolderId));
  }

}
