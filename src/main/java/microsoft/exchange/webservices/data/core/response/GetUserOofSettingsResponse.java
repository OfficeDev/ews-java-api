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

import microsoft.exchange.webservices.data.property.complex.availability.OofSettings;

/**
 * Represents response to GetUserOofSettings request.
 */
public class GetUserOofSettingsResponse extends ServiceResponse {

  /**
   * The oof settings.
   */
  private OofSettings oofSettings;

  /**
   * Initializes a new instance of the class.
   */
  public GetUserOofSettingsResponse() {
    super();
  }

  /**
   * Gets  the OOF settings.
   *
   * @return the oof settings
   */
  public OofSettings getOofSettings() {
    return this.oofSettings;
  }

  /**
   * Sets the oof settings.
   *
   * @param value the new oof settings
   */
  public void setOofSettings(OofSettings value) {
    this.oofSettings = value;
  }

}
