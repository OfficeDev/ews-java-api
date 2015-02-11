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

package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of items.
 *
 * @param <TItem> the generic type. The type of item the collection contains.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class ItemCollection<TItem extends Item> extends ComplexProperty
    implements Iterable<TItem> {

  /**
   * The items.
   */
  private List<TItem> items = new ArrayList<TItem>();

  /**
   * Initializes a new instance of the "ItemCollection&lt;TItem&gt;" class.
   */
  protected ItemCollection() {
    super();
  }

  /**
   * Loads from XML.
   *
   * @param reader           The reader.
   * @param localElementName Name of the local element.
   * @throws Exception the exception
   */
  @Override
  protected void loadFromXml(EwsServiceXmlReader reader,
                             String localElementName) throws Exception {
    reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types,
                                           localElementName);
    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
          TItem item = EwsUtilities
              .createEwsObjectFromXmlElementName(Item.class,
                                                 reader.getService(), reader.getLocalName());

          if (item == null) {
            reader.skipCurrentElement();
          } else {
            try {
              item.loadFromXml(reader,
                               true /* clearPropertyBag */);
            } catch (ServiceObjectPropertyException e) {
              e.printStackTrace();
            } catch (ServiceVersionException e) {
              e.printStackTrace();
            }

            this.items.add(item);
          }
        }
      } while (!reader.isEndElement(XmlNamespace.Types,
                                    localElementName));
    } else {
      reader.read();
    }
  }

  /**
   * Gets the total number of items in the collection.
   *
   * @return the count
   */
  public int getCount() {
    return this.items.size();
  }

  /**
   * Gets the item at the specified index.
   *
   * @param index The zero-based index of the item to get.
   * @return The item at the specified index.
   */
  public TItem getItem(int index) {

    if (index < 0 || index >= this.getCount()) {
      throw new ArrayIndexOutOfBoundsException(Strings.IndexIsOutOfRange);
    }
    return this.items.get(index);
  }

  /**
   * Gets an iterator that iterates through the elements of the collection.
   *
   * @return An Iterator for the collection.
   */
  public Iterator<TItem> getIterator() {
    return this.items.iterator();
  }

  /**
   * Returns an iterator over a set of elements of type T.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<TItem> iterator() {
    return this.items.iterator();
  }

}
