/**************************************************************************
 * copyright file="AsyncCallbackImplementation.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the AsyncCallbackImplementation.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.concurrent.Future;

public class AsyncCallbackImplementation extends AsyncCallback {

	@Override
	public Object processMe(Future task) {
		System.out.println("In Async Callback" + task.isDone());
		return null;
	}

}
