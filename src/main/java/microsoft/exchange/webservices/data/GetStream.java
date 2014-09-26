/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
			return this.getOutputStream();
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
