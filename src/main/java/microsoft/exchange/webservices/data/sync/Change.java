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

package microsoft.exchange.webservices.data.sync;

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.service.ServiceObject;
import microsoft.exchange.webservices.data.core.enumeration.sync.ChangeType;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.property.complex.ServiceId;

/**
 * Represents a change as returned by a synchronization operation.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class Change {

  /**
   * The type of change.
   */
  private ChangeType changeType;

  /**
   * The service object the change applies to.
   */
  private ServiceObject serviceObject;

  /**
   * The Id of the service object the change applies to.
   */
  private ServiceId id;

  /**
   * Initializes a new instance of Change.
   */
  protected Change() {
  }

  /**
   * Initializes a new instance of Change.
   *
   * @return the service id
   */
  public abstract ServiceId createId();

  /**
   * Gets the type of the change.
   *
   * @return the change type
   */
  public ChangeType getChangeType() {
    return this.changeType;
  }

  /**
   * sets the type of the change.
   *
   * @param changeType the new change type
   */
  public void setChangeType(ChangeType changeType) {
    this.changeType = changeType;
  }

  /**
   * Gets  the service object the change applies to.
   *
   * @return the service object
   */
  public ServiceObject getServiceObject() {
    return this.serviceObject;
  }

  /**
   * Sets the service object.
   *
   * @param serviceObject the new service object
   */
  public void setServiceObject(ServiceObject serviceObject) {
    this.serviceObject = serviceObject;
  }

  /**
   * Gets the Id of the service object the change applies to.
   *
   * @return the id
   * @throws ServiceLocalException the service local exception
   */
  public ServiceId getId() throws ServiceLocalException {
    return this.getServiceObject() != null ? this.getServiceObject()
        .getId() : this.id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(ServiceId id) {
    this.id = id;
  }
}
