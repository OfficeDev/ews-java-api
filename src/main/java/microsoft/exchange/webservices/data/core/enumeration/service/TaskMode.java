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

package microsoft.exchange.webservices.data.core.enumeration.service;

/**
 * Defines the modes of a Task.
 */
public enum TaskMode {

  // The task is normal
  /**
   * The Normal.
   */
  Normal(0),

  // The task is a task assignment request
  /**
   * The Request.
   */
  Request(1),

  // The task assignment request was accepted
  /**
   * The Request accepted.
   */
  RequestAccepted(2),

  // The task assignment request was declined
  /**
   * The Request declined.
   */
  RequestDeclined(3),

  // The task has been updated
  /**
   * The Update.
   */
  Update(4),

  // The task is self delegated
  /**
   * The Self delegated.
   */
  SelfDelegated(5);

  /**
   * The task mode.
   */
  private final int taskMode;

  /**
   * Instantiates a new task mode.
   *
   * @param taskMode the task mode
   */
  TaskMode(int taskMode) {
    this.taskMode = taskMode;
  }
}
