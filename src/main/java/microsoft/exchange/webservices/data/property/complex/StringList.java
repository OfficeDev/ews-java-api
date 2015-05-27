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
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;

import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list of strings.
 */
public class StringList extends ComplexProperty implements Iterable<String> {

  /**
   * The item.
   */
  private List<String> items = new ArrayList<String>();

  /**
   * The item xml element name.
   */
  private String itemXmlElementName = XmlElementNames.String;

  /**
   * Initializes a new instance of the "StringList" class.
   */
  public StringList() {
  }

  /**
   * Initializes a new instance of the <see cref="StringList"/> class.
   *
   * @param strings The strings.
   */
  public StringList(Iterable<String> strings) {
    this.addRange(strings);
  }

  /**
   * Initializes a new instance of the "StringList" class.
   *
   * @param itemXmlElementName Name of the item XML element.
   */
  public StringList(String itemXmlElementName) {
    this.itemXmlElementName = itemXmlElementName;
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader accepts EwsServiceXmlReader
   * @return True if element was read
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlDeserializationException the service xml deserialization exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws XMLStreamException, ServiceXmlDeserializationException {
    boolean returnValue = false;
    if (reader.getLocalName().equals(this.itemXmlElementName)) {
      if (!reader.isEmptyElement()) {
        this.add(reader.readValue());
        returnValue = true;
      } else {
        reader.read();

        returnValue = true;
      }

    }
    return returnValue;
  }

  /**
   * Writes elements to XML.
   *
   * @param writer accepts EwsServiceXmlWriter
   * @throws ServiceXmlSerializationException the service xml serialization exception
   * @throws XMLStreamException the XML stream exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException, XMLStreamException {
    for (String item : this.items) {
      writer.writeStartElement(XmlNamespace.Types,
          this.itemXmlElementName);
      writer.writeValue(item, this.itemXmlElementName);
      writer.writeEndElement();
    }
  }

  /**
   * Adds a string to the list.
   *
   * @param s The string to add.
   */
  public void add(String s) {
    this.items.add(s);
    this.changed();
  }

  /**
   * Adds multiple strings to the list.
   *
   * @param strings The strings to add.
   */
  public void addRange(Iterable<String> strings) {
    boolean changed = false;

    for (String s : strings) {
      if (!this.contains(s)) {
        this.items.add(s);
        changed = true;
      }
    }
    if (changed) {
      this.changed();
    }
  }

  /**
   * Determines whether the list contains a specific string.
   *
   * @param s The string to check the presence of.
   * @return True if s is present in the list, false otherwise.
   */
  public boolean contains(String s) {
    return this.items.contains(s);
  }

  /**
   * Removes a string from the list.
   *
   * @param s The string to remove.
   * @return True is s was removed, false otherwise.
   */
  public boolean remove(String s) {
    boolean result = this.items.remove(s);
    if (result) {
      this.changed();
    }
    return result;
  }

  /**
   * Removes the string at the specified position from the list.
   *
   * @param index The index of the string to remove.
   */
  public void removeAt(int index) {
    if (index < 0 || index >= this.getSize()) {
      throw new ArrayIndexOutOfBoundsException("index is out of range.");
    }
    this.items.remove(index);
    this.changed();
  }

  /**
   * Clears the list.
   */
  public void clearList() {
    this.items.clear();
    this.changed();
  }

  /**
   * Returns a string representation of the object. In general, the
   * <code>toString</code> method returns a string that "textually represents"
   * this object. The result should be a concise but informative
   * representation that is easy for a person to read. It is recommended that
   * all subclasses override this method.
   * <p/>
   * The <code>toString</code> method for class <code>Object</code> returns a
   * string consisting of the name of the class of which the object is an
   * instance, the at-sign character `<code>@</code>', and the unsigned
   * hexadecimal representation of the hash code of the object. In other
   * words, this method returns a string equal to the value of: <blockquote>
   * <p/>
   * <pre>
   * getClass().getName() + '@' + Integer.toHexString(hashCode())
   * </pre>
   * <p/>
   * </blockquote>
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    StringBuffer temp = new StringBuffer();
    for (String str : this.items) {
      temp.append(str.concat(","));
    }
    String tempString = temp.toString();
    return tempString;
  }

  /**
   * Gets the number of strings in the list.
   *
   * @return the size
   */
  public int getSize() {
    return this.items.size();
  }

  /**
   * Gets the string at the specified index.
   *
   * @param index The index of the string to get or set.
   * @return The string at the specified index.
   */
  public String getString(int index) {
    if (index < 0 || index >= this.getSize()) {
      throw new ArrayIndexOutOfBoundsException("index is out of range.");
    }
    return this.items.get(index);
  }

  /**
   * Sets the string at the specified index.
   *
   * @param index  The index
   * @param object The object.
   */
  public void setString(int index, Object object) {
    if (index < 0 || index >= this.getSize()) {
      throw new ArrayIndexOutOfBoundsException("index is out of range.");
    }

    if (this.items.get(index) != object) {
      this.items.set(index, (String) object);
      this.changed();
    }
  }

  /**
   * Gets an iterator that iterates through the elements of the collection.
   *
   * @return An Iterator for the collection.
   */
  public Iterator<String> getIterator() {
    return this.items.iterator();
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * <p/>
   * The <code>equals</code> method implements an equivalence relation on
   * non-null object references:
   * <ul>
   * <li>It is <i>reflexive</i>: for any non-null reference value
   * <code>x</code>, <code>x.equals(x)</code> should return <code>true</code>.
   * <li>It is <i>symmetric</i>: for any non-null reference values
   * <code>x</code> and <code>y</code>, <code>x.equals(y)</code> should return
   * <code>true</code> if and only if <code>y.equals(x)</code> returns
   * <code>true</code>.
   * <li>It is <i>transitive</i>: for any non-null reference values
   * <code>x</code>, <code>y</code>, and <code>z</code>, if
   * <code>x.equals(y)</code> returns <code>true</code> and
   * <code>y.equals(z)</code> returns <code>true</code>, then
   * <code>x.equals(z)</code> should return <code>true</code>.
   * <li>It is <i>consistent</i>: for any non-null reference values
   * <code>x</code> and <code>y</code>, multiple invocations of
   * <tt>x.equals(y)</tt> consistently return <code>true</code> or
   * consistently return <code>false</code>, provided no information used in
   * <code>equals</code> comparisons on the objects is modified.
   * <li>For any non-null reference value <code>x</code>,
   * <code>x.equals(null)</code> should return <code>false</code>.
   * </ul>
   * <p/>
   * The <tt>equals</tt> method for class <code>Object</code> implements the
   * most discriminating possible equivalence relation on objects; that is,
   * for any non-null reference values <code>x</code> and <code>y</code>, this
   * method returns <code>true</code> if and only if <code>x</code> and
   * <code>y</code> refer to the same object (<code>x == y</code> has the
   * value <code>true</code>).
   * <p/>
   * Note that it is generally necessary to override the <tt>hashCode</tt>
   * method whenever this method is overridden, so as to maintain the general
   * contract for the <tt>hashCode</tt> method, which states that equal
   * objects must have equal hash codes.
   *
   * @param obj the reference object with which to compare.
   * @return if this object is the same as the obj argument; otherwise.
   * @see #hashCode()
   * @see java.util.Hashtable
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof StringList) {
      StringList other = (StringList) obj;
      return this.toString().equals(other.toString());
    } else {
      return false;
    }
  }

  /**
   * Serves as a hash function for a particular type.
   *
   * @return A hash code for the current "T:System.Object".
   */
  @Override
  public int hashCode() {
    return this.toString().hashCode();
  }

  /**
   * Returns an iterator over a set of elements of type T.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<String> iterator() {
    return items.iterator();
  }
}
