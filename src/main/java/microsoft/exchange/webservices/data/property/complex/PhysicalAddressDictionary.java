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
import microsoft.exchange.webservices.data.core.enumeration.property.PhysicalAddressKey;
import microsoft.exchange.webservices.data.misc.OutParam;

/**
 * Represents a dictionary of physical addresses.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class PhysicalAddressDictionary extends
                                             DictionaryProperty<PhysicalAddressKey, PhysicalAddressEntry> {

  /**
   * Creates instance of dictionary entry.
   *
   * @return New instance.
   */
  @Override
  protected PhysicalAddressEntry createEntryInstance() {
    return new PhysicalAddressEntry();
  }

  /**
   * Gets  the physical address at the specified key.
   *
   * @param key the key
   * @return The physical address at the specified key.
   */
  public PhysicalAddressEntry getPhysicalAddress(PhysicalAddressKey key) {
    return this.getEntries().get(key);
  }

  /**
   * Sets the physical address.
   *
   * @param key   the key
   * @param value the value
   */
  public void setPhysicalAddress(PhysicalAddressKey key,
      PhysicalAddressEntry value) {
    if (value == null) {
      this.internalRemove(key);
    } else {
      value.setKey(key);
      this.internalAddOrReplace(value);
    }
  }

  /**
   * Tries to get the physical address associated with the specified key.
   *
   * @param key      the key
   * @param outparam the outparam
   * @return true if the Dictionary contains a physical address associated
   * with the specified key; otherwise, false.
   */
  public boolean tryGetValue(PhysicalAddressKey key,
      OutParam<PhysicalAddressEntry> outparam) {
    if (this.getEntries().containsKey(key)) {
      outparam.setParam(this.getEntries().get(key));
    }
    return this.getEntries().containsKey(key);
  }

}
