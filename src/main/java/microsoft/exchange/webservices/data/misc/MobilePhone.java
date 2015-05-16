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

import microsoft.exchange.webservices.data.ISelfValidate;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceValidationException;

/**
 * Represents a mobile phone.
 */
public final class MobilePhone implements ISelfValidate {

  /**
   * Name of the mobile phone.
   */
  private String name;

  /**
   * Phone number of the mobile phone.
   */
  private String phoneNumber;

  /**
   * Initializes a new instance of the <see cref="MobilePhone"/> class.
   */
  public MobilePhone() {
  }

  /**
   * Initializes a new instance of the MobilePhone class.
   *
   * @param name        The name associated with the mobile phone.
   * @param phoneNumber The mobile phone number.
   */
  public MobilePhone(String name, String phoneNumber) {
    this.name = name;
    this.phoneNumber = phoneNumber;
  }

  /**
   * Gets or sets the name associated with this mobile phone.
   */
  public String getName() {
    return this.name;
  }

  public void setName(String value) {
    this.name = value;
  }


  /**
   * Gets or sets the number of this mobile phone.
   */
  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String value) {
    this.phoneNumber = value;
  }


  /**
   * Validates this instance.
   *
   * @throws ServiceValidationException on validation error
   */
  public void validate() throws ServiceValidationException {
    if (this.getPhoneNumber() == null || this.getPhoneNumber().isEmpty()) {
      throw new ServiceValidationException(
          "PhoneNumber cannot be empty.");
    }
  }
}
