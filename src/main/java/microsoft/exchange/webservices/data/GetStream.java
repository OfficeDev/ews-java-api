/**************************************************************************
 * copyright file="GetStream.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetStream.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.concurrent.*;

class GetStream implements Callable {
	HttpWebRequest request;
	String mName = "";

	public GetStream(HttpWebRequest request, String method) {
		this.request = request;
		this.mName = method;
	}

	public Object call() throws java.io.IOException, EWSHttpException,
			HttpErrorException {

		if (this.mName.equalsIgnoreCase("getOutputStream"))
			return (Object) this.getOutputStream();
		return null;
	}

	public ByteArrayOutputStream getOutputStream() throws EWSHttpException {
		return (ByteArrayOutputStream) request.getOutputStream();

	}

	/*
	 * public ByteArrayOutputStream getOutputStream() throws Exception { return
	 * (ByteArrayOutputStream)request.getOutputStream();
	 * 
	 * }
	 */

}
