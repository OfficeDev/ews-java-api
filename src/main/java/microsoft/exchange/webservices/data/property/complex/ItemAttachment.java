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
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinitionBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Arrays;

/**
 * Represents an item attachment.
 */
public class ItemAttachment extends Attachment implements IServiceObjectChangedDelegate {

  private static final Log LOG = LogFactory.getLog(ItemAttachment.class);

  /**
   * The item.
   */
  private Item item;

  /**
   * Initializes a new instance of the class.
   *
   * @param owner The owner of the attachment
   */
  protected ItemAttachment(Item owner) {
    super(owner);
  }

  /**
   * Gets the item associated with the attachment.
   *
   * @return the item
   */
  public Item getItem() {
    return this.item;
  }

  /**
   * Sets the item associated with the attachment.
   *
   * @param item the new item
   */
  protected void setItem(Item item) {
    this.throwIfThisIsNotNew();

    if (this.item != null) {

      this.item.removeServiceObjectChangedEvent(this);
    }
    this.item = item;
    if (this.item != null) {
      this.item.addServiceObjectChangedEvent(this);
    }
  }

  /**
   * Implements the OnChange event handler for the item associated with the
   * attachment.
   *
   * @param serviceObject ,The service object that triggered the OnChange event.
   */
  private void itemChanged(ServiceObject serviceObject) {
    this.item.getPropertyBag().changed();
  }

  /**
   * Obtains EWS XML element name for this object.
   *
   * @return The XML element name.
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.ItemAttachment;
  }

  /**
   * Tries to read the element at the current position of the reader.
   *
   * @param reader the reader
   * @return True if the element was read, false otherwise.
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    boolean result = super.tryReadElementFromXml(reader);

    if (!result) {
      this.item = EwsUtilities.createItemFromXmlElementName(this, reader.getLocalName());

      if (this.item != null) {
        try {
          this.item.loadFromXml(reader, true /* clearPropertyBag */);
        } catch (Exception e) {
          LOG.error(e);

        }
      }
    }

    return result;
  }

  /**
   * For ItemAttachment, AttachmentId and Item should be patched.
   *
   * @param reader The reader.
   *               <p/>
   *               True if element was read.
   */
  public boolean tryReadElementFromXmlToPatch(EwsServiceXmlReader reader) throws Exception {
    // update the attachment id.
    super.tryReadElementFromXml(reader);

    reader.read();

    String localName = reader.getLocalName();
    Class<?> itemClass = EwsUtilities.getItemTypeFromXmlElementName(localName);

    if (itemClass != null) {
      if (item == null || item.getClass() != itemClass) {
        throw new ServiceLocalException(
            "Attachment item type mismatch.");
      }

      this.item.loadFromXml(reader, false /* clearPropertyBag */);
      return true;
    }

    return false;
  }


  /**
   * Writes the property of this object as XML elements.
   *
   * @param writer ,The writer to write the elements to.
   * @throws Exception the exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    super.writeElementsToXml(writer);
    try {
      this.item.writeToXml(writer);
    } catch (Exception e) {
      LOG.error(e);

    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void validate(int attachmentIndex) throws Exception {
    if (this.getName() == null || this.getName().isEmpty()) {
      throw new ServiceValidationException(String.format(
          "The name of the item attachment at index %d must be set.", attachmentIndex));
    }

    // Recurse through any item attached to item attachment.
    this.validate();
  }

  /**
   * Loads this attachment.
   *
   * @param additionalProperties the additional property
   * @throws Exception the exception
   */
  public void load(PropertyDefinitionBase... additionalProperties)
      throws Exception {
    internalLoad(null /* bodyType */, Arrays.asList(additionalProperties));
  }

  /**
   * Loads this attachment.
   *
   * @param additionalProperties the additional property
   * @throws Exception the exception
   */
  public void load(Iterable<PropertyDefinitionBase> additionalProperties)
      throws Exception {
    this.internalLoad(null, additionalProperties);
  }

  /**
   * Loads this attachment.
   *
   * @param bodyType             the body type
   * @param additionalProperties the additional property
   * @throws Exception the exception
   */
  public void load(BodyType bodyType,
      PropertyDefinitionBase... additionalProperties) throws Exception {
    internalLoad(bodyType, Arrays.asList(additionalProperties));
  }

  /**
   * Loads this attachment.
   *
   * @param bodyType             the body type
   * @param additionalProperties the additional property
   * @throws Exception the exception
   */
  public void load(BodyType bodyType,
      Iterable<PropertyDefinitionBase> additionalProperties)
      throws Exception {
    this.internalLoad(bodyType, additionalProperties);
  }

  /**
   * Service object changed.
   *
   * @param serviceObject accepts ServiceObject
   */
  @Override
  public void serviceObjectChanged(ServiceObject serviceObject) {
    this.itemChanged(serviceObject);
  }

}
