/**************************************************************************
 * copyright file="EwsTraceListener.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EwsTraceListener.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.io.PrintStream;

/**
 * * EwsTraceListener logs request/responses to a text writer.
 * 
 * @see EwsTraceEvent
 */
class EwsTraceListener implements ITraceListener {

	/** The writer. */
	private PrintStream writer;

	/**
	 * Initializes a new instance of the class. Uses System.Out as output.
	 */
	protected EwsTraceListener() {
		this(System.out);
	}

	/**
	 * Initializes a new instance of the class.
	 * 
	 * @param writer
	 *            the writer
	 */
	protected EwsTraceListener(PrintStream writer) {
		this.writer = writer;
	}

	/**
	 * Handles a trace message.
	 * 
	 * @param traceType
	 *            the trace type
	 * @param traceMessage
	 *            the trace message
	 */
	@Override
	public void trace(String traceType, String traceMessage) {
		// this.writer.println(String.format("%s : %s", traceType,
		// traceMessage));
		this.writer.println(traceMessage);
		this.writer.flush();
	}

}
