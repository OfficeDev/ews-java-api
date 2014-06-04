/**************************************************************************
 * copyright file="HangingTraceStream.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the HangingTraceStream class.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.stream.XMLStreamException;

/**
 * A stream that traces everything it returns from its Read() call.
 * That trace may be retrieved at the end of the stream.
 */
class HangingTraceStream extends InputStream{

	private InputStream underlyingStream;
	private ExchangeService service;
	private ByteArrayOutputStream responseCopy;

	/**
	 * Initializes a new instance of the HangingTraceStream class.
	 * 
	 * @param stream 
	 * 				The stream.
	 * @param service
	 *           the service.
	 */
	protected HangingTraceStream(InputStream stream, ExchangeService service) {
		this.underlyingStream = stream;
		this.service = service;

	}

	/**
	 * Gets a value indicating whether the current stream supports reading.
	 * 
	 * @return true
	 */
	public  boolean getCanRead() {
		return true; 
	}

	/**
	 * Gets a value indicating whether the current stream supports seeking.
	 * 
	 * @return false
	 */
	public boolean getCanSeek()	{
		return false; 
	}

	/**
	 * Gets a value indicating whether the current stream supports writing.
	 * 
	 * @return false
	 */
	public boolean getCanWrite() {
		return false; 
	}

	/**
	 * When overridden in a derived class, clears all buffers 
	 * for this stream and causes any buffered data to be
	 *  written to the underlying device.
	 *@exception An I/O error occurs.
	 */
	/*
	 * @Override public void close() { // no-op }
	 */

	/**
	 * When overridden in a derived class, reads a sequence of
	 *  bytes from the current stream and advances the 
	 *  position within the stream by the number of bytes read.
	 * @param buffer An array of bytes. When this method returns, the buffer 
	 * contains the specified byte array with the values between
	 * @param offset The zero-based byte offset in at which to
	 *  begin storing the data read from the current stream.
	 * @param count The maximum number of bytes to be read from the current stream.
	 * @return The total number of bytes read into the buffer. 
	 * This can be less than the number of bytes requested if that 
	 * many bytes are not currently available, or zero (0) 
	 * if the end of the stream has been reached.
	 * @exception The sum of offset and count is larger than the buffer length. 
	 * @exception 
	 */
	@Override
	public int read(byte[] buffer, int offset, int count) throws IOException {
		count = 4096;
		int retVal = this.underlyingStream.read(buffer, offset, count);

		if (HangingServiceRequestBase.LogAllWireBytes)
		{
			String readString = new String(buffer, offset, count, "UTF-8");
			String logMessage = String.format(
					"HangingTraceStream ID [%d] " +
					"returned %d bytes. Bytes returned: [%s]",
					this.hashCode(),
					retVal,
					readString);

			try {
				this.service.traceMessage(
						TraceFlags.DebugMessage,
						logMessage);
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
		}

		if (this.responseCopy != null) {
			this.responseCopy.write(buffer, offset, retVal);
		}

		return retVal;
	}

	/**
	 * sets the response copy
	 * @param responsecopy 
	 * 				a copy of response
	 */
	protected void setResponseCopy(ByteArrayOutputStream responseCopy)
    {
        this.responseCopy = responseCopy;
    }

	@Override
	public int read() throws IOException {
		return 0;
	}
}

