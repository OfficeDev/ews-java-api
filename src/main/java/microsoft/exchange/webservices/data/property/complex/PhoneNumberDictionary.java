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
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.property.PhoneNumberKey;
import microsoft.exchange.webservices.data.misc.OutParam;

/**
 * Represents a dictionary of phone numbers.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class PhoneNumberDictionary extends DictionaryProperty<PhoneNumberKey, PhoneNumberEntry> {

  /**
   * Gets the field URI.
   *
   * @return Field URI.
   */
  @Override
  protected String getFieldURI() {
    return "contacts:PhoneNumber";
  }

  /**
   * Creates instance of dictionary entry.
   *
   * @return New instance.
   */
  @Override
  protected PhoneNumberEntry createEntryInstance() {
    return new PhoneNumberEntry();
  }

  /**
   * Gets  the phone number at the specified key.
   *
   * @param key The phone number key.
   * @return The phone number at the specified key if found; otherwise null.
   */
  public String getPhoneNumber(PhoneNumberKey key) {
    PhoneNumberEntry phoneNumberEntry = this.getEntries().get(key);
    if (phoneNumberEntry == null) {
      return null;
    }

    return phoneNumberEntry.getPhoneNumber();
  }

  /**
   * Sets the phone number.
   *
   * @param key   the key
   * @param value the value
   */
  public void setPhoneNumber(PhoneNumberKey key, String value) {
    if (value == null) {
      this.internalRemove(key);
    } else {
      PhoneNumberEntry entry;

      if (this.getEntries().containsKey(key)) {
        entry = this.getEntries().get(key);
        entry.setPhoneNumber(value);
        complexPropertyChanged(entry);
        this.changed();
      } else {
        entry = new PhoneNumberEntry(key, value);
        this.internalAdd(entry);
      }
    }
  }

  /**
   * Tries to get the phone number associated with the specified key.
   *
   * @param key      the key
   * @param outparam the outparam
   * @return true if the Dictionary contains a phone number associated with
   * the specified key; otherwise, false.
   */
  public boolean tryGetValue(PhoneNumberKey key, OutParam<String> outparam) {
    String phoneNumber = this.getPhoneNumber(key);
    if (phoneNumber == null) {
      return false;
    }

    outparam.setParam(phoneNumber);
    return true;
  }
}
