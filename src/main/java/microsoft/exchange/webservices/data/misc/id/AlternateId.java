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
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.IdFormat;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

/**
 * Represents an Id expressed in a specific format.
 */
public class AlternateId extends AlternateIdBase {

  /**
   * Name of schema type used for AlternateId.
   */
  public final static String SchemaTypeName = "AlternateIdType";

  /**
   * Id.
   */
  private String id;

  /**
   * SMTP address of the mailbox that the id belongs to.
   */
  private String mailbox;

  /**
   * Type (primary or archive) mailbox to which the Id belongs
   */
  private boolean isArchive;

  /**
   * Initializes a new instance of the class.
   */
  public AlternateId() {
    super();
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param format  the format
   * @param id      the id
   * @param mailbox the mailbox
   */
  public AlternateId(IdFormat format, String id, String mailbox) {
    super(format);
    this.setUniqueId(id);
    this.setMailbox(mailbox);
  }

  /**
   * Initializes a new instance of the AlternateId class.
   *
   * @param format    The format the Id is expressed in.
   * @param id        The Id.
   * @param mailbox   The SMTP address of the mailbox that the Id belongs to.
   * @param isArchive Primary (false) or archive (true) mailbox.
   */
  public AlternateId(
      IdFormat format,
      String id,
      String mailbox,
      boolean isArchive) {
    super(format);
    this.setUniqueId(id);
    this.setMailbox(mailbox);
    this.setIsArchive(isArchive);
  }

  /**
   * Gets the Id.
   *
   * @return the unique id
   */
  public String getUniqueId() {
    return this.id;
  }

  /**
   * Sets the unique id.
   *
   * @param id the new unique id
   */
  public void setUniqueId(String id) {
    this.id = id;
  }

  /**
   * Gets the mailbox to which the Id belongs.
   *
   * @return the mailbox
   */
  public String getMailbox() {
    return this.mailbox;
  }

  /**
   * Sets the mailbox.
   *
   * @param mailbox the new mailbox
   */
  public void setMailbox(String mailbox) {
    this.mailbox = mailbox;
  }

  /**
   * Gets the type (primary or archive) mailbox to which the Id belongs.
   */
  public boolean getIsArchive() {
    return this.isArchive;
  }

  /**
   * Sets the type (primary or archive) mailbox to which the Id belongs.
   *
   * @param isArchive the new isArchive
   */
  public void setIsArchive(boolean isArchive) {
    this.isArchive = isArchive;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.AlternateId;
  }

  /**
   * Gets the name of the XML element.
   *
   * @param writer the writer
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    super.writeAttributesToXml(writer);
    writer.writeAttributeValue(XmlAttributeNames.Id, this.getUniqueId());
    writer.writeAttributeValue(XmlAttributeNames.Mailbox,
        this.getMailbox());
    //.getMailbox() == null || this.getMailbox().isEmpty()) ? ""
    //: this.getMailbox());
    if (this.getIsArchive()) {
      writer.writeAttributeValue(XmlAttributeNames.IsArchive, true);
    }

  }

  /**
   * Gets the name of the XML element.
   *
   * @param reader the reader
   * @throws Exception// the exception
   */
  @Override public void loadAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.loadAttributesFromXml(reader);

    this.setUniqueId(reader.readAttributeValue(XmlAttributeNames.Id));
    this.setMailbox(reader.readAttributeValue(XmlAttributeNames.Mailbox));
    String isArchive = reader.readAttributeValue(
        XmlAttributeNames.IsArchive);

    if (!(isArchive == null || isArchive.isEmpty())) {
      this.isArchive = reader.readAttributeValue(Boolean.class,
          XmlAttributeNames.IsArchive);
    } else {
      this.isArchive = false;
    }
  }

  /**
   * Validate this instance.
   */
  @Override
  protected void internalValidate() throws Exception {
    EwsUtilities.validateParam(this.getMailbox(), "mailbox");
  }
}


