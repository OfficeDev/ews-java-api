/**************************************************************************
 * copyright file="CalendarResponseMessageBase.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CalendarResponseMessageBase.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents the base class for all calendar-related response messages.
 * 
 * 
 * @param <TMessage>
 *            The type of message that is created when this response message is
 *            saved.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class CalendarResponseMessageBase<TMessage extends EmailMessage>
		extends ResponseObject<TMessage> {

	/**
	 * Initializes a new instance of the CalendarResponseMessageBase class.
	 * 
	 * @param referenceItem
	 *            the reference item
	 * @throws Exception
	 *             the exception
	 */
	CalendarResponseMessageBase(Item referenceItem) throws Exception {
		super(referenceItem);
	}

	/**
	 * Saves the response in the specified folder. Calling this method results
	 * in a call to EWS.
	 * 
	 * @param destinationFolderId
	 *            The Id of the folder in which to save the response.
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a results of this operation.
	 * @throws Exception
	 *             the exception
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
	 * @param destinationFolderName
	 *            The name of the folder in which to save the response.
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a results of this operation.
	 * @throws Exception
	 *             the exception
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
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a results of this operation.
	 * @throws Exception
	 *             the exception
	 */
	public CalendarActionResults calendarSave() throws Exception {
		return new CalendarActionResults(this.internalCreate(null,
				MessageDisposition.SaveOnly));
	}

	/**
	 * Sends this response without saving a copy. Calling this method results in
	 * a call to EWS.
	 * 
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a results of this operation.
	 * @throws Exception
	 *             the exception
	 */
	public CalendarActionResults calendarSend() throws Exception {
		return new CalendarActionResults(this.internalCreate(null,
				MessageDisposition.SendOnly));
	}

	/**
	 * Sends this response ans saves a copy in the specified folder. Calling
	 * this method results in a call to EWS.
	 * 
	 * @param destinationFolderId
	 *            The Id of the folder in which to save the copy of the message.
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a results of this operation.
	 * @throws Exception
	 *             the exception
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
	 * @param destinationFolderName
	 *            the destination folder name
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a results of this operation.
	 * @throws Exception
	 *             the exception
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
	 * @return A CalendarActionResults object containing the various items that
	 *         were created or modified as a results of this operation.
	 * @throws Exception
	 *             the exception
	 */
	public CalendarActionResults calendarSendAndSaveCopy() throws Exception {
		return new CalendarActionResults(this.internalCreate(null,
				MessageDisposition.SendAndSaveCopy));
	}

}