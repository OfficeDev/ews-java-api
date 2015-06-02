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
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.property.PhoneNumberKey;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

/**
 * Represents an entry of a PhoneNumberDictionary.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class PhoneNumberEntry extends DictionaryEntryProperty<PhoneNumberKey> {

  /**
   * The phone number.
   */
  private String phoneNumber;

  /**
   * Initializes a new instance of the "PhoneNumberEntry" class.
   */
  protected PhoneNumberEntry() {
    super(PhoneNumberKey.class);
  }

  /**
   * Initializes a new instance of the <see cref="PhoneNumberEntry"/> class.
   *
   * @param key         The key.
   * @param phoneNumber The phone number.
   */
  protected PhoneNumberEntry(PhoneNumberKey key, String phoneNumber) {
    super(PhoneNumberKey.class, key);
    this.phoneNumber = phoneNumber;
  }

  /**
   * Reads the text value from XML.
   *
   * @param reader accepts EwsServiceXmlReader
   * @throws Exception throws Exception
   */
  @Override
  public void readTextValueFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.phoneNumber = reader.readValue();
  }

  /**
   * Writes elements to XML.
   *
   * @param writer The writer.
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeValue(this.phoneNumber, XmlElementNames.PhoneNumber);
  }

  /**
   * Gets the phone number of the entry.
   *
   * @return the phone number
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  /**
   * Sets the phone number of the entry.
   *
   * @param value the new phone number
   */
  public void setPhoneNumber(Object value) {
    //this.canSetFieldValue((String) this.phoneNumber, value);
    if (this.canSetFieldValue(this.phoneNumber, value)) {
      this.phoneNumber = (String) value;
    }
  }
}
