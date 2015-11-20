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

import microsoft.exchange.webservices.data.core.response.DelegateUserResponse;
import microsoft.exchange.webservices.data.core.enumeration.service.MeetingRequestsDeliveryScope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents the results of a GetDelegates operation.
 */
public final class DelegateInformation {

  /**
   * The delegate user response.
   */
  private Collection<DelegateUserResponse> delegateUserResponses;

  /**
   * The meeting reqests delivery scope.
   */
  private MeetingRequestsDeliveryScope meetingReqestsDeliveryScope;

  /**
   * Initializes a DelegateInformation object.
   *
   * @param delegateUserResponses       the delegate user response
   * @param meetingReqestsDeliveryScope the meeting reqests delivery scope
   */
  public DelegateInformation(List<DelegateUserResponse> delegateUserResponses,
      MeetingRequestsDeliveryScope meetingReqestsDeliveryScope) {
    this.delegateUserResponses = new ArrayList<DelegateUserResponse>(
        delegateUserResponses);
    this.meetingReqestsDeliveryScope = meetingReqestsDeliveryScope;
  }

  /**
   * Gets a list of response for each of the delegate users concerned by the
   * operation.
   *
   * @return the delegate user response
   */
  public Collection<DelegateUserResponse> getDelegateUserResponses() {
    return delegateUserResponses;
  }

  /**
   * Gets a value indicating if and how meeting request are delivered to
   * delegates.
   *
   * @return the meeting reqests delivery scope
   */
  public MeetingRequestsDeliveryScope getMeetingReqestsDeliveryScope() {
    return meetingReqestsDeliveryScope;
  }

}
