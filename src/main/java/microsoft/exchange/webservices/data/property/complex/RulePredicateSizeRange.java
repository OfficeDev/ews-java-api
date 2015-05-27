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
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the minimum and maximum size of a message.
 */
public final class RulePredicateSizeRange extends ComplexProperty {
  /**
   * Minimum Size.
   */
  private Integer minimumSize;

  /**
   * Mamixmum Size.
   */
  private Integer maximumSize;

  /**
   * Initializes a new instance of the RulePredicateSizeRange class.
   */
  protected RulePredicateSizeRange() {
    super();
  }

  /**
   * Gets or sets the minimum size, in kilobytes.
   * If MinimumSize is set to null, no minimum size applies.
   */
  public Integer getMinimumSize() {

    return this.minimumSize;
  }

  public void setMinimumSize(Integer value) {
    if (this.canSetFieldValue(this.minimumSize, value)) {
      this.minimumSize = value;
      this.changed();
    }
  }

  /**
   * Gets or sets the maximum size, in kilobytes.
   * If MaximumSize is set to null, no maximum size applies.
   */
  public Integer getMaximumSize() {
    return this.maximumSize;
  }

  public void setMaximumSize(Integer value) {
    if (this.canSetFieldValue(this.maximumSize, value)) {
      this.maximumSize = value;
      this.changed();
    }

  }


  /**
   * Tries to read element from XML.
   *
   * @param reader The reader.
   * @return True if element was read.
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {

    if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.MinimumSize)) {
      this.minimumSize = reader.readElementValue(Integer.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.MaximumSize)) {
      this.maximumSize = reader.readElementValue(Integer.class);
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
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException, XMLStreamException {
    if (this.getMinimumSize() != null) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.MinimumSize, this.getMinimumSize());
    }
    if (this.getMaximumSize() != null) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.MaximumSize, this.getMaximumSize());
    }
  }

  /**
   * Validates this instance.
   */
  @Override
  protected void internalValidate()
      throws ServiceValidationException, Exception {
    super.internalValidate();
    if (this.minimumSize != null &&
        this.maximumSize != null &&
        this.minimumSize > this.maximumSize) {
      throw new ServiceValidationException(
          "MinimumSize cannot be larger than MaximumSize.");
    }
  }
}



