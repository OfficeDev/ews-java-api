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

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.enumeration.sync.ChangeType;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.sync.Change;
import microsoft.exchange.webservices.data.sync.ChangeCollection;
import microsoft.exchange.webservices.data.sync.ItemChange;

/**
 * Represents the base response class for synchronuization operations.
 *
 * @param <TServiceObject> ServiceObject type.
 * @param <TChange>        Change type.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class SyncResponse<TServiceObject extends ServiceObject,
    TChange extends Change> extends ServiceResponse {

  /**
   * The changes.
   */
  private ChangeCollection<TChange> changes = new ChangeCollection<TChange>();

  /**
   * The property set.
   */
  private PropertySet propertySet;

  /**
   * Initializes a new instance of the class.
   *
   * @param propertySet the property set
   */
  protected SyncResponse(PropertySet propertySet) {
    super();
    this.propertySet = propertySet;
    EwsUtilities.ewsAssert(this.propertySet != null, "SyncResponse.ctor", "PropertySet should not be null");
  }

  /**
   * Gets the name of the includes last in range XML element.
   *
   * @return XML element name.
   */
  protected abstract String getIncludesLastInRangeXmlElementName();

  /**
   * Creates the change instance.
   *
   * @return TChange instance
   */
  protected abstract TChange createChangeInstance();

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws ServiceLocalException, Exception {
    this.changes.setSyncState(reader.readElementValue(
        XmlNamespace.Messages, XmlElementNames.SyncState));
    this.changes.setMoreChangesAvailable(!reader.readElementValue(
        Boolean.class, XmlNamespace.Messages, this
            .getIncludesLastInRangeXmlElementName()));

    reader.readStartElement(XmlNamespace.Messages, XmlElementNames.Changes);
    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if (reader.isStartElement()) {
          TChange change = this.createChangeInstance();

          if (reader.getLocalName().equals(XmlElementNames.Create)) {
            change.setChangeType(ChangeType.Create);
          } else if (reader.getLocalName().equals(
              XmlElementNames.Update)) {
            change.setChangeType(ChangeType.Update);
          } else if (reader.getLocalName().equals(
              XmlElementNames.Delete)) {
            change.setChangeType(ChangeType.Delete);
          } else if (reader.getLocalName().equals(
              XmlElementNames.ReadFlagChange)) {
            change.setChangeType(ChangeType.ReadFlagChange);
          } else {
            reader.skipCurrentElement();
          }

          if (change != null) {
            reader.read();
            reader.ensureCurrentNodeIsStartElement();

            if (change.getChangeType().equals(ChangeType.Delete)
                || change.getChangeType().equals(
                ChangeType.ReadFlagChange)) {
              change.setId(change.createId());
              change.getId().loadFromXml(reader,
                  change.getId().getXmlElementName());

              if (change.getChangeType().equals(
                  ChangeType.ReadFlagChange)) {
                reader.read();
                reader.ensureCurrentNodeIsStartElement();
                ItemChange itemChange = null;
                if (change instanceof ItemChange) {
                  itemChange = (ItemChange) change;
                }
                EwsUtilities
                    .ewsAssert(itemChange != null, "SyncResponse." + "ReadElementsFromXml",
                               "ReadFlagChange is only " + "valid on ItemChange");

                itemChange.setIsRead(reader.readElementValue(
                    Boolean.class, XmlNamespace.Types,
                    XmlElementNames.IsRead));
              }
            } else {

              change.setServiceObject(EwsUtilities
                  .createEwsObjectFromXmlElementName(null,
                      reader.getService(), reader
                          .getLocalName()));

              change.getServiceObject().loadFromXml(reader,
                  true, /* clearPropertyBag */
                  this.propertySet, this.getSummaryPropertiesOnly());
            }

            reader.readEndElementIfNecessary(XmlNamespace.Types,
                change.getChangeType().toString());

            this.changes.add(change);
          }
        }
      } while (!reader.isEndElement(XmlNamespace.Messages,
          XmlElementNames.Changes));
    } else {
      reader.read();
    }
  }

  /**
   * Gets a list of changes that occurred on the synchronized folder.
   *
   * @return the changes
   */
  public ChangeCollection<TChange> getChanges() {
    return this.changes;
  }

  /**
   * Gets a value indicating whether this request returns full or summary
   * property.
   *
   * @return the summary property only
   */
  protected abstract boolean getSummaryPropertiesOnly();

}
