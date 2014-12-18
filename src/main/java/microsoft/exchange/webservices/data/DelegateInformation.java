/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents the results of a GetDelegates operation.
 */
public final class DelegateInformation {

  /**
   * The delegate user responses.
   */
  private Collection<DelegateUserResponse> delegateUserResponses;

  /**
   * The meeting reqests delivery scope.
   */
  private MeetingRequestsDeliveryScope meetingReqestsDeliveryScope;

  /**
   * Initializes a DelegateInformation object.
   *
   * @param delegateUserResponses       the delegate user responses
   * @param meetingReqestsDeliveryScope the meeting reqests delivery scope
   */
  protected DelegateInformation(
      List<DelegateUserResponse> delegateUserResponses,
      MeetingRequestsDeliveryScope meetingReqestsDeliveryScope) {
    this.delegateUserResponses = new ArrayList<DelegateUserResponse>(
        delegateUserResponses);
    this.meetingReqestsDeliveryScope = meetingReqestsDeliveryScope;
  }

  /**
   * Gets a list of responses for each of the delegate users concerned by the
   * operation.
   *
   * @return the delegate user responses
   */
  public Collection<DelegateUserResponse> getDelegateUserResponses() {
    return delegateUserResponses;
  }

  /**
   * Gets a value indicating if and how meeting requests are delivered to
   * delegates.
   *
   * @return the meeting reqests delivery scope
   */
  public MeetingRequestsDeliveryScope getMeetingReqestsDeliveryScope() {
    return meetingReqestsDeliveryScope;
  }

}
