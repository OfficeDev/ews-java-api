/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.Collection;

/**
 * Represents the results of a GetUserAvailability operation.
 */
public final class GetUserAvailabilityResults {

  /**
   * The attendees availability.
   */
  private ServiceResponseCollection<AttendeeAvailability>
      attendeesAvailability;

  /**
   * The suggestions response.
   */
  private SuggestionsResponse suggestionsResponse;

  /**
   * Initializes a new instance of the GetUserAvailabilityResults class.
   */
  protected GetUserAvailabilityResults() {
  }

  /**
   * Gets  the suggestions response for the requested meeting time.
   *
   * @return the suggestions response
   */
  protected SuggestionsResponse getSuggestionsResponse() {
    return this.suggestionsResponse;
  }

  /**
   * Sets the suggestions response.
   *
   * @param value the new suggestions response
   */
  protected void setSuggestionsResponse(SuggestionsResponse value) {
    this.suggestionsResponse = value;
  }

  /**
   * Gets a collection of AttendeeAvailability objects representing
   * availability information for each of the specified attendees.
   *
   * @return the attendees availability
   */
  public ServiceResponseCollection<AttendeeAvailability>
  getAttendeesAvailability() {
    return this.attendeesAvailability;
  }

  /**
   * Sets the attendees availability.
   *
   * @param value the new attendees availability
   */
  protected void setAttendeesAvailability(
      ServiceResponseCollection<AttendeeAvailability> value) {
    this.attendeesAvailability = value;
  }

  /**
   * Gets a collection of suggested meeting times for the specified time
   * period.
   *
   * @return the suggestions
   * @throws microsoft.exchange.webservices.data.ServiceResponseException the service response exception
   */
  public Collection<Suggestion> getSuggestions()
      throws ServiceResponseException {
    if (this.suggestionsResponse == null) {
      return null;
    } else {
      this.suggestionsResponse.throwIfNecessary();

      return this.suggestionsResponse.getSuggestions();
    }

  }
}
