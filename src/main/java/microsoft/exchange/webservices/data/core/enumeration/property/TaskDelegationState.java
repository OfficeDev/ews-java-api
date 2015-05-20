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

package microsoft.exchange.webservices.data.core.enumeration.property;

/**
 * This maps to the bogus TaskDelegationState in the EWS schema.
 *  The schema enum has 6 values, but XSO should never return anything but
 *  values between 0 and 3, so we should be safe without mappings for
 *  EWS's Declined and Max values
 */


/**
 * Defines the delegation state of a task.
 */
public enum TaskDelegationState {

  // The task is not delegated
  /**
   * The No delegation.
   */
  NoDelegation, // Maps to NoMatch

  // The task's delegation state is unknown.
  /**
   * The Unknown.
   */
  Unknown, // Maps to OwnNew

  // The task was delegated and the delegation was accepted.
  /**
   * The Accepted.
   */
  Accepted, // Maps to Owned

  // The task was delegated but the delegation was declined.
  /**
   * The Declined.
   */
  Declined
  // Maps to Accepted

  // The original Declined value has no mapping
  // The original Max value has no mapping

}
