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
import microsoft.exchange.webservices.data.core.ILazyMember;
import microsoft.exchange.webservices.data.core.LazyMember;
import microsoft.exchange.webservices.data.core.SimplePropertyBag;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.enumeration.property.PhysicalAddressKey;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an entry of an PhysicalAddressDictionary.
 */
public final class PhysicalAddressEntry extends DictionaryEntryProperty<PhysicalAddressKey> implements
                                                                                            IPropertyBagChangedDelegate<String> {

  /**
   * The property bag.
   */
  private SimplePropertyBag<String> propertyBag;

  /**
   * Initializes a new instance of PhysicalAddressEntry.
   */
  public PhysicalAddressEntry() {
    super(PhysicalAddressKey.class);
    this.propertyBag = new SimplePropertyBag<String>();
    this.propertyBag.addOnChangeEvent(this);
  }

  /**
   * Property was changed.
   *
   * @param simplePropertyBag the simple property bag
   */
  public void propertyBagChanged(SimplePropertyBag<String> simplePropertyBag) {
    this.changed();
  }

  /**
   * Gets the street.
   *
   * @return the street
   * @throws Exception the exception
   */
  public String getStreet() throws Exception {
    return (String) this.propertyBag
        .getSimplePropertyBag(PhysicalAddressSchema.Street);
  }

  /**
   * Sets the street.
   *
   * @param value the new street
   * @throws Exception the exception
   */
  public void setStreet(String value) throws Exception {
    this.propertyBag.setSimplePropertyBag(PhysicalAddressSchema.Street,
        value);

  }

  /**
   * Gets the city.
   *
   * @return the city
   * @throws Exception the exception
   */
  public String getCity() throws Exception {
    return (String) this.propertyBag
        .getSimplePropertyBag(PhysicalAddressSchema.City);
  }

  /**
   * Sets the city.
   *
   * @param value the new city
   */
  public void setCity(String value) {
    this.propertyBag
        .setSimplePropertyBag(PhysicalAddressSchema.City, value);
  }

  /**
   * Gets the state.
   *
   * @return the state
   * @throws Exception the exception
   */
  public String getState() throws Exception {
    return (String) this.propertyBag
        .getSimplePropertyBag(PhysicalAddressSchema.State);
  }

  /**
   * Sets the state.
   *
   * @param value the new state
   */
  public void setState(String value) {
    this.propertyBag.setSimplePropertyBag(PhysicalAddressSchema.State,
        value);
  }

  /**
   * Gets the country or region.
   *
   * @return the country or region
   * @throws Exception the exception
   */
  public String getCountryOrRegion() throws Exception {
    return (String) this.propertyBag
        .getSimplePropertyBag(PhysicalAddressSchema.CountryOrRegion);
  }

  /**
   * Sets the country or region.
   *
   * @param value the new country or region
   */
  public void setCountryOrRegion(String value) {
    this.propertyBag.setSimplePropertyBag(
        PhysicalAddressSchema.CountryOrRegion, value);
  }

  /**
   * Gets the postal code.
   *
   * @return the postal code
   */
  public String getPostalCode() {
    return (String) this.propertyBag
        .getSimplePropertyBag(PhysicalAddressSchema.PostalCode);
  }

  /**
   * Sets the postal code.
   *
   * @param value the new postal code
   */
  public void setPostalCode(String value) {
    this.propertyBag.setSimplePropertyBag(PhysicalAddressSchema.PostalCode,
        value);
  }

  /**
   * Clears the change log.
   */
  @Override public void clearChangeLog() {
    this.propertyBag.clearChangeLog();
  }

  /**
   * Writes elements to XML.
   *
   * @param reader the reader
   * @return true, if successful
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (PhysicalAddressSchema.getXmlElementNames().contains(
        reader.getLocalName())) {
      this.propertyBag.setSimplePropertyBag(reader.getLocalName(), reader
          .readElementValue());
      return true;
    } else {
      return false;
    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer the writer
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws XMLStreamException, ServiceXmlSerializationException {
    for (String xmlElementName : PhysicalAddressSchema.getXmlElementNames()) {
      writer.writeElementValue(XmlNamespace.Types, xmlElementName,
          this.propertyBag.getSimplePropertyBag(xmlElementName));

    }
  }

  /**
   * Writes the update to XML.
   *
   * @param writer                        the writer
   * @param ewsObject                     the ews object
   * @param ownerDictionaryXmlElementName the owner dictionary xml element name
   * @return true if update XML was written
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected boolean writeSetUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject, String ownerDictionaryXmlElementName)
      throws XMLStreamException, ServiceXmlSerializationException {
    List<String> fieldsToSet = new ArrayList<String>();

    for (String xmlElementName : this.propertyBag.getAddedItems()) {
      fieldsToSet.add(xmlElementName);
    }

    for (String xmlElementName : this.propertyBag.getModifiedItems()) {
      fieldsToSet.add(xmlElementName);
    }

    for (String xmlElementName : fieldsToSet) {
      writer.writeStartElement(XmlNamespace.Types, ewsObject
          .getSetFieldXmlElementName());

      writer.writeStartElement(XmlNamespace.Types,
          XmlElementNames.IndexedFieldURI);
      writer.writeAttributeValue(XmlAttributeNames.FieldURI,
          getFieldUri(xmlElementName));
      writer.writeAttributeValue(XmlAttributeNames.FieldIndex, this
          .getKey().toString());
      writer.writeEndElement(); // IndexedFieldURI

      writer.writeStartElement(XmlNamespace.Types, ewsObject
          .getXmlElementName());
      writer.writeStartElement(XmlNamespace.Types,
          ownerDictionaryXmlElementName);
      writer.writeStartElement(XmlNamespace.Types, XmlElementNames.Entry);
      this.writeAttributesToXml(writer);
      writer.writeElementValue(XmlNamespace.Types, xmlElementName,
          this.propertyBag.getSimplePropertyBag(xmlElementName));
      writer.writeEndElement(); // Entry
      writer.writeEndElement(); // ownerDictionaryXmlElementName
      writer.writeEndElement(); // ewsObject.GetXmlElementName()

      writer.writeEndElement(); // ewsObject.GetSetFieldXmlElementName()
    }

    for (String xmlElementName : this.propertyBag.getRemovedItems()) {
      this.internalWriteDeleteFieldToXml(writer, ewsObject,
          xmlElementName);
    }

    return true;
  }

  /**
   * Writes the delete update to XML.
   *
   * @param writer    the writer
   * @param ewsObject the ews object
   * @return true if update XML was written
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  protected boolean writeDeleteUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject) throws XMLStreamException,
      ServiceXmlSerializationException {
    for (String xmlElementName : PhysicalAddressSchema.getXmlElementNames()) {
      this.internalWriteDeleteFieldToXml(writer, ewsObject,
          xmlElementName);
    }
    return true;
  }

  /**
   * Gets the field URI.
   *
   * @param xmlElementName the xml element name
   * @return Field URI.
   */
  private static String getFieldUri(String xmlElementName) {
    return "contacts:PhysicalAddress:" + xmlElementName;
  }

  /**
   * Write field deletion to XML.
   *
   * @param writer              the writer
   * @param ewsObject           the ews object
   * @param fieldXmlElementName the field xml element name
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  private void internalWriteDeleteFieldToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject, String fieldXmlElementName)
      throws XMLStreamException, ServiceXmlSerializationException {
    writer.writeStartElement(XmlNamespace.Types, ewsObject
        .getDeleteFieldXmlElementName());
    writer.writeStartElement(XmlNamespace.Types,
        XmlElementNames.IndexedFieldURI);
    writer.writeAttributeValue(XmlAttributeNames.FieldURI,
        getFieldUri(fieldXmlElementName));
    writer.writeAttributeValue(XmlAttributeNames.FieldIndex, this.getKey()
        .toString());
    writer.writeEndElement(); // IndexedFieldURI
    writer.writeEndElement(); // ewsObject.GetDeleteFieldXmlElementName()
  }

  /**
   * Schema definition for PhysicalAddress.
   */
  private static class PhysicalAddressSchema {

    /**
     * The Constant Street.
     */
    public static final String Street = "Street";

    /**
     * The Constant City.
     */
    public static final String City = "City";

    /**
     * The Constant State.
     */
    public static final String State = "State";

    /**
     * The Constant CountryOrRegion.
     */
    public static final String CountryOrRegion = "CountryOrRegion";

    /**
     * The Constant PostalCode.
     */
    public static final String PostalCode = "PostalCode";

    /**
     * List of XML element names.
     */
    private static LazyMember<List<String>> xmlElementNames =
        new LazyMember<List<String>>(

            new ILazyMember<List<String>>() {
              @Override
              public List<String> createInstance() {
                List<String> result = new ArrayList<String>();
                result.add(Street);
                result.add(City);
                result.add(State);
                result.add(CountryOrRegion);
                result.add(PostalCode);
                return result;
              }
            });

    /**
     * Gets the XML element names.
     *
     * @return The XML element names.
     */
    public static List<String> getXmlElementNames() {
      return xmlElementNames.getMember();
    }
  }

}
