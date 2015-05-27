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
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;

/**
 * Represents a collection of attendees.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public final class AttendeeCollection extends ComplexPropertyCollection<Attendee> {

  /**
   * Initializes a new instance of the AttendeeCollection class.
   */
  public AttendeeCollection() {
    super();
  }

  /**
   * Adds an attendee to the collection.
   *
   * @param attendee the attendee
   */
  public void add(Attendee attendee) {
    this.internalAdd(attendee);
  }

  /**
   * Adds an attendee to the collection.
   *
   * @param smtpAddress the smtp address
   * @return An Attendee instance initialized with the provided SMTP address.
   * @throws Exception the exception
   */
  public Attendee add(String smtpAddress) throws Exception {
    Attendee result = new Attendee(smtpAddress);

    this.internalAdd(result);

    return result;
  }

  /**
   * Adds an attendee to the collection.
   *
   * @param name        the name
   * @param smtpAddress the smtp address
   * @return An Attendee instance initialized with the provided name and SMTP
   * address.
   */
  public Attendee add(String name, String smtpAddress) {
    Attendee result = new Attendee(name, smtpAddress);

    this.internalAdd(result);

    return result;
  }

  /**
   * Clears the collection.
   */
  public void clear() {
    this.internalClear();
  }

  /**
   * Removes an attendee from the collection.
   *
   * @param index the index
   */
  public void removeAt(int index) {
    if (index < 0 || index >= this.getCount()) {
      throw new IllegalArgumentException("parameter \'index\' : " + "index is out of range.");
    }

    this.internalRemoveAt(index);
  }

  /**
   * Removes an attendee from the collection.
   *
   * @param attendee the attendee
   * @return True if the attendee was successfully removed from the
   * collection, false otherwise.
   * @throws Exception the exception
   */
  public boolean remove(Attendee attendee) throws Exception {
    EwsUtilities.validateParam(attendee, "attendee");

    return this.internalRemove(attendee);
  }

  /**
   * Creates an Attendee object from an XML element name.
   *
   * @param xmlElementName the xml element name
   * @return An Attendee object.
   */
  @Override
  protected Attendee createComplexProperty(String xmlElementName) {
    if (xmlElementName.equalsIgnoreCase(XmlElementNames.Attendee)) {
      return new Attendee();
    } else {
      return null;
    }
  }

  /**
   * Retrieves the XML element name corresponding to the provided Attendee
   * object.
   *
   * @param attendee the attendee
   * @return The XML element name corresponding to the provided Attendee
   * object.
   */
  @Override
  protected String getCollectionItemXmlElementName(Attendee attendee) {
    return XmlElementNames.Attendee;
  }
}
