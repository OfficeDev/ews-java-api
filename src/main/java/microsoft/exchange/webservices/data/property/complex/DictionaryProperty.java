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

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.ICustomXmlUpdateSerializer;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents a generic dictionary that can be sent to or retrieved from EWS.
 * TKey The type of key. TEntry The type of entry.
 *
 * @param <TKey>   the generic type
 * @param <TEntry> the generic type
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class DictionaryProperty
    <TKey, TEntry extends DictionaryEntryProperty<TKey>>
    extends ComplexProperty implements ICustomXmlUpdateSerializer, IComplexPropertyChangedDelegate<TEntry> {

  /**
   * The entries.
   */
  private Map<TKey, TEntry> entries = new HashMap<TKey, TEntry>();

  /**
   * The removed entries.
   */
  private Map<TKey, TEntry> removedEntries = new HashMap<TKey, TEntry>();

  /**
   * The added entries.
   */
  private List<TKey> addedEntries = new ArrayList<TKey>();

  /**
   * The modified entries.
   */
  private List<TKey> modifiedEntries = new ArrayList<TKey>();

  /**
   * Entry was changed.
   *
   * @param complexProperty the complex property
   */
  private void entryChanged(final TEntry complexProperty) {
    TKey key = complexProperty.getKey();

    if (!this.addedEntries.contains(key) && !this.modifiedEntries.contains(key)) {
      this.modifiedEntries.add(key);
      this.changed();
    }
  }

  /**
   * Writes the URI to XML.
   *
   * @param writer the writer
   * @param key    the key
   * @throws Exception the exception
   */
  private void writeUriToXml(EwsServiceXmlWriter writer, TKey key)
      throws Exception {
    writer.writeStartElement(XmlNamespace.Types,
        XmlElementNames.IndexedFieldURI);
    writer.writeAttributeValue(XmlAttributeNames.FieldURI, this
        .getFieldURI());
    writer.writeAttributeValue(XmlAttributeNames.FieldIndex, this
        .getFieldIndex(key));
    writer.writeEndElement();
  }

  /**
   * Gets the index of the field.
   *
   * @param key the key
   * @return Key index.
   */
  protected String getFieldIndex(TKey key) {
    return key.toString();
  }

  /**
   * Gets the field URI.
   *
   * @return Field URI.
   */
  protected String getFieldURI() {
    return null;
  }

  /**
   * Creates the entry.
   *
   * @param reader the reader
   * @return Dictionary entry.
   */
  protected TEntry createEntry(EwsServiceXmlReader reader) {
    if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Entry)) {
      return this.createEntryInstance();
    } else {
      return null;
    }
  }

  /**
   * Creates instance of dictionary entry.
   *
   * @return New instance.
   */
  protected abstract TEntry createEntryInstance();

  /**
   * Gets the name of the entry XML element.
   *
   * @param entry the entry
   * @return XML element name.
   */
  protected String getEntryXmlElementName(TEntry entry) {
    return XmlElementNames.Entry;
  }

  /**
   * Clears the change log.
   */
  public void clearChangeLog() {
    this.addedEntries.clear();
    this.removedEntries.clear();
    this.modifiedEntries.clear();

    for (TEntry entry : this.entries.values()) {
      entry.clearChangeLog();
    }
  }

  /**
   * Add entry.
   *
   * @param entry the entry
   */
  protected void internalAdd(TEntry entry) {
    entry.addOnChangeEvent(this);

    this.entries.put(entry.getKey(), entry);
    this.addedEntries.add(entry.getKey());
    this.removedEntries.remove(entry.getKey());

    this.changed();
  }

  /**
   * Complex property changed.
   *
   * @param complexProperty accepts ComplexProperty
   */
  @Override
  public void complexPropertyChanged(final TEntry complexProperty) {
    entryChanged(complexProperty);
  }

  /**
   * Add or replace entry.
   *
   * @param entry the entry
   */
  protected void internalAddOrReplace(TEntry entry) {
    TEntry oldEntry;
    if (this.entries.containsKey(entry.getKey())) {
      oldEntry = this.entries.get(entry.getKey());
      oldEntry.removeChangeEvent(this);

      entry.addOnChangeEvent(this);

      if (!this.addedEntries.contains(entry.getKey())) {
        if (!this.modifiedEntries.contains(entry.getKey())) {
          this.modifiedEntries.add(entry.getKey());
        }
      }

      this.changed();
    } else {
      this.internalAdd(entry);
    }
  }

  /**
   * Remove entry based on key.
   *
   * @param key the key
   */
  protected void internalRemove(TKey key) {
    TEntry entry;
    if (this.entries.containsKey(key)) {
      entry = this.entries.get(key);
      entry.removeChangeEvent(this);

      this.entries.remove(key);
      this.removedEntries.put(key, entry);

      this.changed();
    }

    this.addedEntries.remove(key);
  }

  /**
   * Loads from XML.
   *
   * @param reader           the reader
   * @param localElementName the local element name
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader, String localElementName) throws Exception {
    reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types,
        localElementName);

    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if (reader.isStartElement()) {
          TEntry entry = this.createEntry(reader);

          if (entry != null) {
            entry.loadFromXml(reader, reader.getLocalName());
            this.internalAdd(entry);
          } else {
            reader.skipCurrentElement();
          }
        }
      } while (!reader.isEndElement(XmlNamespace.Types,
          localElementName));
    } else {
      reader.read();
    }
  }

  /**
   * Writes to XML.
   *
   * @param writer         The writer
   * @param xmlNamespace   The XML namespace.
   * @param xmlElementName Name of the XML element.
   * @throws Exception
   */
  @Override public void writeToXml(EwsServiceXmlWriter writer, XmlNamespace xmlNamespace,
      String xmlElementName) throws Exception {
    //  Only write collection if it has at least one element.
    if (this.entries.size() > 0) {
      super.writeToXml(
          writer,
          xmlNamespace,
          xmlElementName);
    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    for (Entry<TKey, TEntry> keyValuePair : this.entries.entrySet()) {
      keyValuePair.getValue().writeToXml(writer,
          this.getEntryXmlElementName(keyValuePair.getValue()));
    }
  }

  /**
   * Gets the entries.
   *
   * @return The entries.
   */
  protected Map<TKey, TEntry> getEntries() {
    return entries;
  }

  /**
   * Determines whether this instance contains the specified key.
   *
   * @param key the key
   * @return true if this instance contains the specified key; otherwise,
   * false.
   */
  public boolean contains(TKey key) {
    return this.entries.containsKey(key);
  }

  /**
   * Writes updates to XML.
   *
   * @param writer             the writer
   * @param ewsObject          the ews object
   * @param propertyDefinition the property definition
   * @return True if property generated serialization.
   * @throws Exception the exception
   */
  public boolean writeSetUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject, PropertyDefinition propertyDefinition)
      throws Exception {
    List<TEntry> tempEntries = new ArrayList<TEntry>();

    for (TKey key : this.addedEntries) {
      tempEntries.add(this.entries.get(key));
    }
    for (TKey key : this.modifiedEntries) {
      tempEntries.add(this.entries.get(key));
    }
    for (TEntry entry : tempEntries) {

      if (!entry.writeSetUpdateToXml(writer, ewsObject,
          propertyDefinition.getXmlElement())) {
        writer.writeStartElement(XmlNamespace.Types, ewsObject
            .getSetFieldXmlElementName());
        this.writeUriToXml(writer, entry.getKey());

        writer.writeStartElement(XmlNamespace.Types, ewsObject
            .getXmlElementName());
        //writer.writeStartElement(XmlNamespace.Types, propertyDefinition.getXmlElementName());
        writer.writeStartElement(XmlNamespace.Types, propertyDefinition.getXmlElement());
        entry.writeToXml(writer, this.getEntryXmlElementName(entry));
        writer.writeEndElement();
        writer.writeEndElement();

        writer.writeEndElement();
      }
    }

    for (TEntry entry : this.removedEntries.values()) {
      if (!entry.writeDeleteUpdateToXml(writer, ewsObject)) {
        writer.writeStartElement(XmlNamespace.Types, ewsObject
            .getDeleteFieldXmlElementName());
        this.writeUriToXml(writer, entry.getKey());
        writer.writeEndElement();
      }
    }

    return true;
  }

  /**
   * Writes deletion update to XML.
   *
   * @param writer    the writer
   * @param ewsObject the ews object
   * @return True if property generated serialization.
   */
  public boolean writeDeleteUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject) {
    return false;
  }
}
