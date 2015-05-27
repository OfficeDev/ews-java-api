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

package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.DelegateUser;

/**
 * Represents the response to an individual delegate user manipulation (add,
 * remove, update) operation.
 */
public final class DelegateUserResponse extends ServiceResponse {

  /**
   * The read delegate user.
   */
  private boolean readDelegateUser;

  /**
   * The delegate user.
   */
  private DelegateUser delegateUser;

  /**
   * Initializes a new instance of the class.
   *
   * @param readDelegateUser the read delegate user
   * @param delegateUser     the delegate user
   */
  protected DelegateUserResponse(boolean readDelegateUser,
      DelegateUser delegateUser) {
    super();
    this.readDelegateUser = readDelegateUser;
    this.delegateUser = delegateUser;
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (this.readDelegateUser) {
      if (this.delegateUser == null) {
        this.delegateUser = new DelegateUser();
      }

      reader.readStartElement(XmlNamespace.Messages,
          XmlElementNames.DelegateUser);

      this.delegateUser.loadFromXml(reader, XmlNamespace.Messages, reader
          .getLocalName());
    }
  }

  /**
   * The delegate user that was involved in the operation.
   *
   * @return the delegate user
   */
  public DelegateUser getDelegateUser() {
    return this.delegateUser;
  }

}
