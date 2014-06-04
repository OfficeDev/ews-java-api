/**************************************************************************
 * copyright file="CallableSingleTon.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CallableSingleTon.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.concurrent.*;
import java.util.*;

public class CallableSingleTon {
	static ExecutorService es;
	 static ExecutorService  getExecutor(){
		es = Executors.newFixedThreadPool(3);
		
		return es;
		
	}
	 
	 

}
