/**************************************************************************
 * copyright file="IFileAttachmentContentHandler.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IFileAttachmentContentHandler.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.io.OutputStream;

/**
 * Defines a file attachment content handler. Application can implement
 * IFileAttachmentContentHandler /// to provide a stream in which the content of
 * file attachment should be written.
 * 
 */
public interface IFileAttachmentContentHandler {

	/**
	 * Provides a stream to which the content of the attachment with the
	 * specified Id should be written.
	 * 
	 * @param attachmentId
	 *            The Id of the attachment that is being loaded.
	 * @return A Stream to which the content of the attachment will be written.
	 */
	OutputStream getOutputStream(String attachmentId);
}
