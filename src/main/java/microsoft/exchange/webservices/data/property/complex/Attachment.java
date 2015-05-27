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

package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceVersionException;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinitionBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * Represents an attachment to an item.
 */
public abstract class Attachment extends ComplexProperty {

  private static final Log LOG = LogFactory.getLog(Attachment.class);

  /**
   * The owner.
   */
  private Item owner;

  /**
   * The id.
   */
  private String id;

  /**
   * The name.
   */
  private String name;

  /**
   * The content type.
   */
  private String contentType;

  /**
   * The content id.
   */
  private String contentId;

  /**
   * The content location.
   */
  private String contentLocation;

  /**
   * The size.
   */
  private int size;

  /**
   * The last modified time.
   */
  private Date lastModifiedTime;

  /**
   * The is inline.
   */
  private boolean isInline;

  /**
   * Initializes a new instance.
   *
   * @param owner The owner.
   */
  protected Attachment(Item owner) {
    this.owner = owner;
  }

  /**
   * Throws exception if this is not a new service object.
   */
  protected void throwIfThisIsNotNew() {
    if (!this.isNew()) {
      throw new UnsupportedOperationException("Attachments can't be updated.");
    }
  }

  /**
   * Sets value of field.
   * <p/>
   * We override the base implementation. Attachments cannot be modified so
   * any attempts the change a property on an existing attachment is an error.
   *
   * @param <T>   the generic type
   * @param field The field
   * @param value The value.
   * @return true, if successful
   */
  public <T> boolean canSetFieldValue(T field, T value) {
    this.throwIfThisIsNotNew();
    return super.canSetFieldValue(field, value);
  }

  /**
   * Gets the Id of the attachment.
   *
   * @return the id
   */
  public String getId() {
    return this.id;
  }

  /**
   * Gets the name of the attachment.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name.
   *
   * @param value the new name
   */
  public void setName(String value) {
    if (this.canSetFieldValue(this.name, value)) {
      this.name = value;
      this.changed();
    }
  }

  /**
   * Gets  the content type of the attachment.
   *
   * @return the content type
   */
  public String getContentType() {
    return this.contentType;
  }

  /**
   * Sets the content type.
   *
   * @param value the new content type
   */
  public void setContentType(String value) {
    if (this.canSetFieldValue(this.contentType, value)) {
      this.contentType = value;
      this.changed();
    }
  }

  /**
   * Gets  the content Id of the attachment. ContentId can be used as a
   * custom way to identify an attachment in order to reference it from within
   * the body of the item the attachment belongs to.
   *
   * @return the content id
   */
  public String getContentId() {
    return this.contentId;
  }

  /**
   * Sets the content id.
   *
   * @param value the new content id
   */
  public void setContentId(String value) {
    if (this.canSetFieldValue(this.contentId, value)) {
      this.contentId = value;
      this.changed();
    }
  }

  /**
   * Gets  the content location of the attachment. ContentLocation can
   * be used to associate an attachment with a Url defining its location on
   * the Web.
   *
   * @return the content location
   */
  public String getContentLocation() {
    return this.contentLocation;
  }

  /**
   * Sets the content location.
   *
   * @param value the new content location
   */
  public void setContentLocation(String value) {
    if (this.canSetFieldValue(this.contentLocation, value)) {
      this.contentLocation = value;
      this.changed();
    }
  }

  /**
   * Gets the size of the attachment.
   *
   * @return the size
   * @throws ServiceVersionException throws ServiceVersionException
   */
  public int getSize() throws ServiceVersionException {
    EwsUtilities.validatePropertyVersion(this.getOwner().getService(), ExchangeVersion.Exchange2010, "Size");
    return this.size;
  }

  /**
   * Gets the date and time when this attachment was last modified.
   *
   * @return the last modified time
   * @throws ServiceVersionException the service version exception
   */
  public Date getLastModifiedTime() throws ServiceVersionException {

    EwsUtilities.validatePropertyVersion(this.getOwner().getService(),
        ExchangeVersion.Exchange2010, "LastModifiedTime");

    return this.lastModifiedTime;

  }

  /**
   * Gets  a value indicating whether this is an inline attachment.
   * Inline attachments are not visible to end users.
   *
   * @return the checks if is inline
   * @throws ServiceVersionException the service version exception
   */
  public boolean getIsInline() throws ServiceVersionException {
    EwsUtilities.validatePropertyVersion(this.getOwner().getService(),
        ExchangeVersion.Exchange2010, "IsInline");
    return this.isInline;

  }

  /**
   * Sets the checks if is inline.
   *
   * @param value the new checks if is inline
   * @throws ServiceVersionException the service version exception
   */
  public void setIsInline(boolean value) throws ServiceVersionException {
    EwsUtilities.validatePropertyVersion(this.getOwner().getService(),
        ExchangeVersion.Exchange2010, "IsInline");
    if (this.canSetFieldValue(this.isInline, value)) {
      this.isInline = value;
      this.changed();
    }
  }

  /**
   * True if the attachment has not yet been saved, false otherwise.
   *
   * @return true, if is new
   */
  public boolean isNew() {
    return (this.getId() == null || this.getId().isEmpty());
  }

  /**
   * Gets the owner of the attachment.
   *
   * @return the owner
   */
  public Item getOwner() {
    return this.owner;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  public abstract String getXmlElementName();

  /**
   * Tries to read element from XML.
   *
   * @param reader The reader.
   * @return True if element was read.
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {

    try {
      if (reader.getLocalName().equalsIgnoreCase(
          XmlElementNames.AttachmentId)) {
        try {
          this.id = reader.readAttributeValue(XmlAttributeNames.Id);
        } catch (Exception e) {
          LOG.error(e);
          return false;
        }
        if (this.getOwner() != null) {
          String rootItemChangeKey = reader
              .readAttributeValue(XmlAttributeNames.
                  RootItemChangeKey);
          if (null != rootItemChangeKey &&
              !rootItemChangeKey.isEmpty()) {
            this.getOwner().getRootItemId().setChangeKey(
                rootItemChangeKey);
          }
        }
        reader.readEndElementIfNecessary(XmlNamespace.Types,
            XmlElementNames.AttachmentId);
        return true;
      } else if (reader.getLocalName().equalsIgnoreCase(
          XmlElementNames.Name)) {
        this.name = reader.readElementValue();
        return true;
      } else if (reader.getLocalName().equalsIgnoreCase(
          XmlElementNames.ContentType)) {
        this.contentType = reader.readElementValue();
        return true;
      } else if (reader.getLocalName().equalsIgnoreCase(
          XmlElementNames.ContentId)) {
        this.contentId = reader.readElementValue();
        return true;
      } else if (reader.getLocalName().equalsIgnoreCase(
          XmlElementNames.ContentLocation)) {
        this.contentLocation = reader.readElementValue();
        return true;
      } else if (reader.getLocalName().equalsIgnoreCase(
          XmlElementNames.Size)) {
        this.size = reader.readElementValue(Integer.class);
        return true;
      } else if (reader.getLocalName().equalsIgnoreCase(
          XmlElementNames.LastModifiedTime)) {
        this.lastModifiedTime = reader.readElementValueAsDateTime();
        return true;
      } else if (reader.getLocalName().equalsIgnoreCase(
          XmlElementNames.IsInline)) {
        this.isInline = reader.readElementValue(Boolean.class);
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      LOG.error(e);
      return false;
    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.Name, this
        .getName());
    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.ContentType, this.getContentType());
    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.ContentId,
        this.getContentId());
    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.ContentLocation, this.getContentLocation());
    if (writer.getService().getRequestedServerVersion().ordinal() >
        ExchangeVersion.Exchange2007_SP1
            .ordinal()) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.IsInline, this.getIsInline());
    }
  }

  /**
   * Load the attachment.
   *
   * @param bodyType             Type of the body.
   * @param additionalProperties The additional property.
   * @throws Exception the exception
   */
  protected void internalLoad(BodyType bodyType,
      Iterable<PropertyDefinitionBase> additionalProperties)
      throws Exception {
    this.getOwner().getService().getAttachment(this, bodyType,
        additionalProperties);
  }

  /**
   * Validates this instance.
   *
   * @param attachmentIndex Index of this attachment.
   * @throws ServiceValidationException the service validation exception
   * @throws Exception                  the exception
   */
  abstract void validate(int attachmentIndex) throws Exception;

  /**
   * Loads the attachment. Calling this method results in a call to EWS.
   *
   * @throws Exception the exception
   */
  public void load() throws Exception {
    this.internalLoad(null, null);
  }

}
