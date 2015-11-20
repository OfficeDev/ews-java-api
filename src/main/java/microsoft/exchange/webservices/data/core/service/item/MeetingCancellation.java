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

package microsoft.exchange.webservices.data.core.service.item;

import microsoft.exchange.webservices.data.attribute.ServiceObjectDefinition;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.response.RemoveFromCalendar;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.misc.CalendarActionResults;
import microsoft.exchange.webservices.data.property.complex.ItemAttachment;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents a meeting cancellation message. Properties available on meeting
 * messages are defined in the MeetingMessageSchema class.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.MeetingCancellation)
public class MeetingCancellation extends MeetingMessage {

  private static final Log LOG = LogFactory.getLog(MeetingCancellation.class);

  /**
   * Initializes a new instance of the class.
   *
   * @param parentAttachment The parent attachment.
   * @throws Exception the exception
   */
  public MeetingCancellation(ItemAttachment parentAttachment)
      throws Exception {
    super(parentAttachment);
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param service EWS service to which this object belongs.
   * @throws Exception the exception
   */
  public MeetingCancellation(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Binds to an existing meeting cancellation message and loads the specified
   * set of property. Calling this method results in a call to EWS.
   *
   * @param service     The service to use to bind to the meeting cancellation
   *                    message.
   * @param id          The Id of the meeting cancellation message to bind to.
   * @param propertySet The set of property to load.
   * @return A MeetingCancellation instance representing the meeting
   * cancellation message corresponding to the specified Id.
   */
  public static MeetingCancellation bind(ExchangeService service, ItemId id,
      PropertySet propertySet) {
    try {
      return service.bindToItem(MeetingCancellation.class, id,
          propertySet);
    } catch (Exception e) {
      LOG.error(e);
      return null;
    }
  }

  /**
   * Binds to an existing meeting cancellation message and loads the specified
   * set of property. Calling this method results in a call to EWS.
   *
   * @param service The service to use to bind to the meeting cancellation
   *                message.
   * @param id      The Id of the meeting cancellation message to bind to.
   * @return A MeetingCancellation instance representing the meeting
   * cancellation message corresponding to the specified Id.
   */
  public static MeetingCancellation bind(ExchangeService service, ItemId id) {
    return MeetingCancellation.bind(service, id, PropertySet
        .getFirstClassProperties());
  }

  /**
   * Removes the meeting associated with the cancellation message from the
   * user's calendar.
   *
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  public CalendarActionResults removeMeetingFromCalendar()
      throws ServiceLocalException, Exception {
    return new CalendarActionResults(new RemoveFromCalendar(this)
        .internalCreate(null, null));
  }

  /**
   * Gets the minimum required server version.
   *
   * @return Earliest Exchange version in which this service object type is
   * supported.
   */
  @Override public ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }
}
