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
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

/**
 * Represents the definition of the GroupMember property.
 */
public final class GroupMemberPropertyDefinition extends
    ServiceObjectPropertyDefinition {

  // / FieldUri of IndexedFieldURI for a group member.
  /**
   * The Constant FIELDURI.
   */
  private final static String FIELDURI = "distributionlist:Members:Member";

  // / Member key.
  // / Maps to the Index attribute of IndexedFieldURI element.
  /**
   * The key.
   */
  private String key;

  /**
   * Initializes a new instance of the GroupMemberPropertyDefinition class.
   *
   * @param key the key
   */
  public GroupMemberPropertyDefinition(String key) {
    super(FIELDURI);
    this.key = key;
  }

  /**
   * Initializes a new instance of the GroupMemberPropertyDefinition class
   * without key.
   */
  public GroupMemberPropertyDefinition() {
    super(FIELDURI);
  }

  /**
   * Gets the key.
   *
   * @return the key
   */
  public String getKey() {
    return key;
  }

  /**
   * Sets the key.
   *
   * @param key the new key
   */
  public void setKey(String key) {
    this.key = key;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  protected String getXmlElementName() {
    return XmlElementNames.IndexedFieldURI;
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    super.writeAttributesToXml(writer);
    writer.writeAttributeValue(XmlAttributeNames.FieldIndex, this.key);
  }

  /**
   * Gets the property definition's printable name.
   *
   * @return The property definition's printable name.
   */
  @Override public String getPrintableName() {
    return String.format("%s:%s", FIELDURI, this.key);
  }


  /**
   * Gets the property type.
   */
  @Override
  public Class<String> getType() {
    return String.class;
  }


}
