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
import microsoft.exchange.webservices.data.core.enumeration.misc.error.ServiceError;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.property.complex.DelegateUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents the response to a delegate managent-related operation.
 */
public class DelegateManagementResponse extends ServiceResponse {

  /**
   * The read delegate users.
   */
  private boolean readDelegateUsers;

  /**
   * The delegate users.
   */
  private List<DelegateUser> delegateUsers;

  /**
   * The delegate user response.
   */
  private Collection<DelegateUserResponse> delegateUserResponses;

  /**
   * Initializes a new instance of the class.
   *
   * @param readDelegateUsers the read delegate users
   * @param delegateUsers     the delegate users
   */
  public DelegateManagementResponse(boolean readDelegateUsers, List<DelegateUser> delegateUsers) {
    super();
    this.readDelegateUsers = readDelegateUsers;
    this.delegateUsers = delegateUsers;
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (this.getErrorCode() == ServiceError.NoError) {
      this.delegateUserResponses = new ArrayList<DelegateUserResponse>();

      reader.read();

      if (reader.isStartElement(XmlNamespace.Messages,
          XmlElementNames.ResponseMessages)) {
        int delegateUserIndex = 0;
        do {
          reader.read();
          if (reader.isStartElement(XmlNamespace.Messages,
              XmlElementNames.DelegateUserResponseMessageType)) {
            DelegateUser delegateUser = null;
            if (this.readDelegateUsers &&
                (this.delegateUsers != null)) {
              delegateUser = this.delegateUsers
                  .get(delegateUserIndex);
            }

            DelegateUserResponse delegateUserResponse =
                new DelegateUserResponse(
                    readDelegateUsers, delegateUser);
            delegateUserResponse
                .loadFromXml(
                    reader,
                    XmlElementNames.
                        DelegateUserResponseMessageType);
            this.delegateUserResponses.add(delegateUserResponse);

            delegateUserIndex++;
          }
        } while (!reader.isEndElement(XmlNamespace.Messages,
            XmlElementNames.ResponseMessages));
      }
    }
  }

  /**
   * Gets a collection of response for each of the delegate users concerned
   * by the operation.
   *
   * @return the delegate user response
   */
  public Collection<DelegateUserResponse> getDelegateUserResponses() {
    return this.delegateUserResponses;
  }
}
