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

package microsoft.exchange.webservices.data.messaging;

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.request.DisconnectPhoneCallRequest;
import microsoft.exchange.webservices.data.core.request.GetPhoneCallRequest;
import microsoft.exchange.webservices.data.core.request.PlayOnPhoneRequest;
import microsoft.exchange.webservices.data.core.response.GetPhoneCallResponse;
import microsoft.exchange.webservices.data.core.response.PlayOnPhoneResponse;
import microsoft.exchange.webservices.data.property.complex.ItemId;

/**
 * Represents the Unified Messaging functionalities.
 */
public final class UnifiedMessaging {

  /**
   * The service.
   */
  private ExchangeService service;

  /**
   * Constructor.
   *
   * @param service the service
   */
  public UnifiedMessaging(ExchangeService service) {
    this.service = service;
  }

  /**
   * Calls a phone and reads a message to the person who picks up.
   *
   * @param itemId     the item id
   * @param dialString the dial string
   * @return An object providing status for the phone call.
   * @throws Exception the exception
   */
  public PhoneCall playOnPhone(ItemId itemId, String dialString)
      throws Exception {
    EwsUtilities.validateParam(itemId, "itemId");
    EwsUtilities.validateParam(dialString, "dialString");

    PlayOnPhoneRequest request = new PlayOnPhoneRequest(service);
    request.setDialString(dialString);
    request.setItemId(itemId);
    PlayOnPhoneResponse serviceResponse = request.execute();

    PhoneCall callInformation = new PhoneCall(service, serviceResponse
        .getPhoneCallId());

    return callInformation;
  }

  /**
   * Retrieves information about a current phone call.
   *
   * @param id the id
   * @return An object providing status for the phone call.
   * @throws Exception the exception
   */
  protected PhoneCall getPhoneCallInformation(PhoneCallId id)
      throws Exception {
    GetPhoneCallRequest request = new GetPhoneCallRequest(service);
    request.setId(id);
    GetPhoneCallResponse response = request.execute();

    return response.getPhoneCall();
  }

  /**
   * Disconnects a phone call.
   *
   * @param id the id
   * @throws Exception the exception
   */
  protected void disconnectPhoneCall(PhoneCallId id) throws Exception {
    DisconnectPhoneCallRequest request = new DisconnectPhoneCallRequest(
        service);
    request.setId(id);
    request.execute();
  }
}
