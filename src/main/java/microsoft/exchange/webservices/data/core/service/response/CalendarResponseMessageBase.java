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

package microsoft.exchange.webservices.data.core.service.response;

import microsoft.exchange.webservices.data.attribute.EditorBrowsable;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.attribute.EditorBrowsableState;
import microsoft.exchange.webservices.data.core.enumeration.service.MessageDisposition;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.misc.CalendarActionResults;
import microsoft.exchange.webservices.data.property.complex.FolderId;

/**
 * Represents the base class for all calendar-related response messages.
 *
 * @param <TMessage> The type of message that is created when this response message is
 *                   saved.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class CalendarResponseMessageBase<TMessage extends EmailMessage>
    extends ResponseObject<TMessage> {

  /**
   * Initializes a new instance of the CalendarResponseMessageBase class.
   *
   * @param referenceItem the reference item
   * @throws Exception the exception
   */
  CalendarResponseMessageBase(Item referenceItem) throws Exception {
    super(referenceItem);
  }

  /**
   * Saves the response in the specified folder. Calling this method results
   * in a call to EWS.
   *
   * @param destinationFolderId The Id of the folder in which to save the response.
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */

  public CalendarActionResults calendarSave(FolderId destinationFolderId)
      throws Exception {
    EwsUtilities.validateParam(destinationFolderId, "destinationFolderId");

    return new CalendarActionResults(this.internalCreate(
        destinationFolderId, MessageDisposition.SaveOnly));
  }

  /**
   * Saves the response in the specified folder. Calling this method results
   * in a call to EWS.
   *
   * @param destinationFolderName The name of the folder in which to save the response.
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */
  public CalendarActionResults calendarSave(
      WellKnownFolderName destinationFolderName) throws Exception {
    return new CalendarActionResults(this.internalCreate(new FolderId(
        destinationFolderName), MessageDisposition.SaveOnly));
  }

  /**
   * Saves the response in the Drafts folder. Calling this method results in a
   * call to EWS.
   *
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */
  public CalendarActionResults calendarSave() throws Exception {
    return new CalendarActionResults(this.internalCreate(null,
        MessageDisposition.SaveOnly));
  }

  /**
   * Sends this response without saving a copy. Calling this method results in
   * a call to EWS.
   *
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */
  public CalendarActionResults calendarSend() throws Exception {
    return new CalendarActionResults(this.internalCreate(null,
        MessageDisposition.SendOnly));
  }

  /**
   * Sends this response ans saves a copy in the specified folder. Calling
   * this method results in a call to EWS.
   *
   * @param destinationFolderId The Id of the folder in which to save the copy of the message.
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */

  public CalendarActionResults calendarSendAndSaveCopy(
      FolderId destinationFolderId) throws Exception {
    EwsUtilities.validateParam(destinationFolderId, "destinationFolderId");
    return new CalendarActionResults(this.internalCreate(
        destinationFolderId, MessageDisposition.SendAndSaveCopy));
  }

  /**
   * Sends this response ans saves a copy in the specified folder. Calling
   * this method results in a call to EWS.
   *
   * @param destinationFolderName the destination folder name
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */
  public CalendarActionResults calendarSendAndSaveCopy(
      WellKnownFolderName destinationFolderName) throws Exception {
    return new CalendarActionResults(this.internalCreate(new FolderId(
        destinationFolderName), MessageDisposition.SendAndSaveCopy));
  }

  /**
   * Sends this response ans saves a copy in the specified folder. Calling
   * this method results in a call to EWS.
   *
   * @return A CalendarActionResults object containing the various item that
   * were created or modified as a results of this operation.
   * @throws Exception the exception
   */
  public CalendarActionResults calendarSendAndSaveCopy() throws Exception {
    return new CalendarActionResults(this.internalCreate(null,
        MessageDisposition.SendAndSaveCopy));
  }

}
