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
import microsoft.exchange.webservices.data.core.enumeration.property.ImAddressKey;
import microsoft.exchange.webservices.data.misc.OutParam;

/**
 * Represents a dictionary of Instant Messaging addresses.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class ImAddressDictionary extends DictionaryProperty<ImAddressKey, ImAddressEntry> {

  /**
   * Gets the field URI.
   *
   * @return Field URI.
   */
  @Override
  protected String getFieldURI() {
    return "contacts:ImAddress";
  }

  /**
   * Creates instance of dictionary entry.
   *
   * @return New instance.
   */
  @Override
  protected ImAddressEntry createEntryInstance() {
    return new ImAddressEntry();
  }

  /**
   * Gets  the Instant Messaging address at the specified key.
   *
   * @param key the key
   * @return The Instant Messaging address at the specified key.
   */
  public String getImAddressKey(ImAddressKey key) {
    return this.getEntries().get(key).getImAddress();
  }

  /**
   * Sets the im address key.
   *
   * @param key   the key
   * @param value the value
   */
  public void setImAddressKey(ImAddressKey key, String value) {
    if (value == null) {
      this.internalRemove(key);
    } else {
      ImAddressEntry entry;

      if (this.getEntries().containsKey(key)) {
        entry = this.getEntries().get(key);
        entry.setImAddress(value);
        this.changed();
      } else {
        entry = new ImAddressEntry(key, value);
        this.internalAdd(entry);
      }
    }
  }

  /**
   * Tries to get the IM address associated with the specified key.
   *
   * @param key      the key
   * @param outParam the out param
   * @return true if the Dictionary contains an IM address associated with the
   * specified key; otherwise, false.
   */
  public boolean tryGetValue(ImAddressKey key, OutParam<String> outParam) {
    ImAddressEntry entry = null;

    if (this.getEntries().containsKey(key)) {
      entry = this.getEntries().get(key);
      outParam.setParam(entry.getImAddress());

      return true;
    } else {
      outParam = null;
      return false;
    }
  }
}
