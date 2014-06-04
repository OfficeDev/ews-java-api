/**************************************************************************
 * copyright file="IAsyncResult.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the IAsyncResult.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.concurrent.Future;

/***
 * Represents the stauts of Asynchronous operation.
 * 
 *
 */

public interface IAsyncResult extends Future {

	public Object getAsyncState();

	public WaitHandle getAsyncWaitHanle();

	public boolean getCompleteSynchronously();

	public boolean getIsCompleted();
}
