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

package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents the results of an ExpandGroup operation.
 */
public final class ExpandGroupResults implements Iterable<EmailAddress> {

  /**
   * True, if all members are returned. EWS always returns true on ExpandDL,
   * i.e. all members are returned.
   */
  private boolean includesAllMembers;

  /**
   * DL members.
   */
  private Collection<EmailAddress> members = new ArrayList<EmailAddress>();

  /**
   * Initializes a new instance of the class.
   */
  public ExpandGroupResults() {
  }

  /**
   * Gets the number of members that were returned by the ExpandGroup
   * operation. Count might be less than the total number of members in the
   * group, in which case the value of the IncludesAllMembers is false.
   *
   * @return the count
   */
  public int getCount() {
    return this.getMembers().size();
  }

  /**
   * Gets a value indicating whether all the members of the group have been
   * returned by ExpandGroup.
   *
   * @return the includes all members
   */
  public boolean getIncludesAllMembers() {
    return this.includesAllMembers;
  }

  /**
   * Gets the members of the expanded group.
   *
   * @return the members
   */
  public Collection<EmailAddress> getMembers() {
    return this.members;
  }

  /**
   * Gets the members of the expanded group.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader) throws Exception {
    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.DLExpansion);
    if (!reader.isEmptyElement()) {
      int totalItemsInView = reader.readAttributeValue(Integer.class,
          XmlAttributeNames.TotalItemsInView);
      this.includesAllMembers = reader.readAttributeValue(Boolean.class,
          XmlAttributeNames.IncludesLastItemInRange);

      for (int i = 0; i < totalItemsInView; i++) {
        EmailAddress emailAddress = new EmailAddress();

        reader.readStartElement(XmlNamespace.Types,
            XmlElementNames.Mailbox);
        emailAddress.loadFromXml(reader, XmlElementNames.Mailbox);

        this.getMembers().add(emailAddress);
      }

      reader.readEndElement(XmlNamespace.Messages,
          XmlElementNames.DLExpansion);
    } else {
      reader.read();
    }
  }

  /**
   * Returns an iterator over a set of elements of type T.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<EmailAddress> iterator() {

    return members.iterator();
  }
}
