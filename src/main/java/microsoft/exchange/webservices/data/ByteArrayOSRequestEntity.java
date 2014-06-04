/**************************************************************************
 * copyright file="ByteArrayOSRequestEntity.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ByteArrayOSRequestEntity.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.httpclient.methods.RequestEntity;

class ByteArrayOSRequestEntity implements RequestEntity{

	private ByteArrayOutputStream os = null;
	
	/**
	 * Constructor for ByteArrayOSRequestEntity.
	 */
	ByteArrayOSRequestEntity(OutputStream os) {
		super();
		this.os = (ByteArrayOutputStream) os;
	}
	
	@Override
	public long getContentLength() {
		return os.size();
	}

	@Override
	public String getContentType() {
		return "text/xml; charset=utf-8";
	}

	@Override
	public boolean isRepeatable() {
		return true;
	}

	@Override
	public void writeRequest(OutputStream out) throws IOException {
		os.writeTo(out);		
	}

}
