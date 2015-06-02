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

import microsoft.exchange.webservices.data.ISelfValidate;
import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.security.XmlNodeType;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a property that can be sent to or retrieved from EWS.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class ComplexProperty implements ISelfValidate, ComplexFunctionDelegate<EwsServiceXmlReader> {

  /**
   * The xml namespace.
   */
  private XmlNamespace xmlNamespace = XmlNamespace.Types;

  /**
   * Initializes a new instance.
   */
  protected ComplexProperty() {

  }

  /**
   * Gets the namespace.
   *
   * @return the namespace.
   */
  public XmlNamespace getNamespace() {
    return xmlNamespace;
  }

  /**
   * Sets the namespace.
   *
   * @param xmlNamespace the namespace.
   */
  public void setNamespace(XmlNamespace xmlNamespace) {
    this.xmlNamespace = xmlNamespace;
  }

  /**
   * Instance was changed.
   */
  public void changed() {
    if (!onChangeList.isEmpty()) {
      for (IComplexPropertyChangedDelegate change : onChangeList) {
        change.complexPropertyChanged(this);
      }
    }
  }

  /**
   * Sets value of field.
   *
   * @param <T>   Field type.
   * @param field The field.
   * @param value The value.
   * @return true, if successful
   */
  public <T> boolean canSetFieldValue(T field, T value) {
    boolean applyChange;
    if (field == null) {
      applyChange = value != null;
    } else {
      if (field instanceof Comparable<?>) {
        Comparable<T> c = (Comparable<T>) field;
        applyChange = value != null && c.compareTo(value) != 0;
      } else {
        applyChange = true;
      }
    }
    return applyChange;
  }

  /**
   * Clears the change log.
   */
  public void clearChangeLog() {
  }

  /**
   * Reads the attribute from XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  public void readAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
  }

  /**
   * Reads the text value from XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  public void readTextValueFromXml(EwsServiceXmlReader reader)
      throws Exception {
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader The reader.
   * @return True if element was read.
   * @throws Exception the exception
   */
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    return false;
  }

  /**
   * Tries to read element from XML to patch this property.
   *
   * @param reader The reader.
   *               True if element was read.
   */
  public boolean tryReadElementFromXmlToPatch(EwsServiceXmlReader reader) throws Exception {
    return false;
  }

  /**
   * Writes the attribute to XML.
   *
   * @param writer The writer.
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  public void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
  }

  /**
   * Writes elements to XML.
   *
   * @param writer The writer.
   * @throws Exception the exception
   */
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
  }

  /**
   * Loads from XML.
   *
   * @param reader         The reader.
   * @param xmlNamespace   the xml namespace
   * @param xmlElementName Name of the XML element.
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader, XmlNamespace xmlNamespace, String xmlElementName) throws Exception {

		/*reader.ensureCurrentNodeIsStartElement(xmlNamespace, xmlElementName);
                this.readAttributesFromXml(reader);

		if (!reader.isEmptyElement()) {
			do {
				reader.read();

				switch (reader.getNodeType().nodeType) {
				case XmlNodeType.START_ELEMENT:
					if (!this.tryReadElementFromXml(reader)) {
						reader.skipCurrentElement();
					}
					break;
				case XmlNodeType.CHARACTERS:
					this.readTextValueFromXml(reader);
					break;
				}
			} while (!reader.isEndElement(xmlNamespace, xmlElementName));
		} else {
			// Adding this code to skip the END_ELEMENT of an Empty Element.
			reader.read();
			reader.isEndElement(xmlNamespace, xmlElementName);
		} */

    this.internalLoadFromXml(reader, xmlNamespace, xmlElementName);
  }

  /**
   * Loads from XML to update this property.
   *
   * @param reader         The reader.
   * @param xmlElementName Name of the XML element.
   * @throws Exception
   */
  public void updateFromXml(EwsServiceXmlReader reader, String xmlElementName) throws Exception {
    this.updateFromXml(reader, this.getNamespace(), xmlElementName);
  }

  /**
   * Loads from XML to update itself.
   *
   * @param reader         The reader.
   * @param xmlNamespace   The XML namespace.
   * @param xmlElementName Name of the XML element.
   */
  public void updateFromXml(
      EwsServiceXmlReader reader,
      XmlNamespace xmlNamespace,
      String xmlElementName) throws Exception {
    this.internalupdateLoadFromXml(reader, xmlNamespace, xmlElementName);
  }

  /**
   * Loads from XML
   *
   * @param reader         The Reader.
   * @param xmlNamespace   The Xml NameSpace.
   * @param xmlElementName The Xml ElementName
   */
  private void internalLoadFromXml(
      EwsServiceXmlReader reader,
      XmlNamespace xmlNamespace,
      String xmlElementName) throws Exception {
    reader.ensureCurrentNodeIsStartElement(xmlNamespace, xmlElementName);

    this.readAttributesFromXml(reader);

    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        switch (reader.getNodeType().nodeType) {
          case XmlNodeType.START_ELEMENT:
            if (!this.tryReadElementFromXml(reader)) {
              reader.skipCurrentElement();
            }
            break;
          case XmlNodeType.CHARACTERS:
            this.readTextValueFromXml(reader);
            break;
        }
      } while (!reader.isEndElement(xmlNamespace, xmlElementName));
    } else {
      // Adding this code to skip the END_ELEMENT of an Empty Element.
      reader.read();
      reader.isEndElement(xmlNamespace, xmlElementName);
    }
  }

  private void internalupdateLoadFromXml(
      EwsServiceXmlReader reader,
      XmlNamespace xmlNamespace,
      String xmlElementName) throws Exception {
    reader.ensureCurrentNodeIsStartElement(xmlNamespace, xmlElementName);

    this.readAttributesFromXml(reader);

    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        switch (reader.getNodeType().nodeType) {
          case XmlNodeType.START_ELEMENT:
            if (!this.tryReadElementFromXmlToPatch(reader)) {
              reader.skipCurrentElement();
            }
            break;
          case XmlNodeType.CHARACTERS:
            this.readTextValueFromXml(reader);
            break;
        }
      } while (!reader.isEndElement(xmlNamespace, xmlElementName));
    }
  }

  /**
   * Loads from XML.
   *
   * @param reader         The reader.
   * @param xmlElementName Name of the XML element.
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader, String xmlElementName)
      throws Exception {
    this.loadFromXml(reader, this.getNamespace(), xmlElementName);
  }

  /**
   * Writes to XML.
   *
   * @param writer         The writer.
   * @param xmlNamespace   The XML namespace.
   * @param xmlElementName Name of the XML element.
   * @throws Exception the exception
   */
  public void writeToXml(EwsServiceXmlWriter writer, XmlNamespace xmlNamespace, String xmlElementName) throws Exception {
    writer.writeStartElement(xmlNamespace, xmlElementName);
    this.writeAttributesToXml(writer);
    this.writeElementsToXml(writer);
    writer.writeEndElement();
  }

  /**
   * Writes to XML.
   *
   * @param writer         The writer.
   * @param xmlElementName Name of the XML element.
   * @throws Exception the exception
   */
  public void writeToXml(EwsServiceXmlWriter writer, String xmlElementName)
      throws Exception {
    this.writeToXml(writer, this.getNamespace(), xmlElementName);
  }

  /**
   * Change events occur when property changed.
   */
  private List<IComplexPropertyChangedDelegate> onChangeList =
      new ArrayList<IComplexPropertyChangedDelegate>();

  /**
   * Set event to happen when property changed.
   *
   * @param change change event
   */
  public void addOnChangeEvent(IComplexPropertyChangedDelegate change) {
    onChangeList.add(change);
  }

  /**
   * Remove the event from happening when property changed.
   *
   * @param change change event
   */
  public void removeChangeEvent(IComplexPropertyChangedDelegate change) {
    onChangeList.remove(change);
  }

  /**
   * Clears change events list.
   */
  protected void clearChangeEvents() {
    onChangeList.clear();
  }

  /**
   * Implements ISelfValidate.validate. Validates this instance.
   *
   * @throws Exception the exception
   */
  public void validate() throws Exception {
    this.internalValidate();
  }

  /**
   * Validates this instance.
   *
   * @throws Exception the exception
   */
  protected void internalValidate() throws Exception {
  }

  public Boolean func(EwsServiceXmlReader reader) throws Exception {
    return !this.tryReadElementFromXml(reader);
  }
}
