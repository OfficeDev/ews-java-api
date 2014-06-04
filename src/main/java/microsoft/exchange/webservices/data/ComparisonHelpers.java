/**************************************************************************
 * copyright file="AttendeeCollection.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ComparisonHelpers.java.
**************************************************************************/
package microsoft.exchange.webservices.data;

import java.util.ArrayList;

/***
 * Represents a set of helper methods for performing string comparisons.
 */
class ComparisonHelpers {

	/***
	 * Case insensitive check if the collection contains the string.
	 * @param collectionThe collection of objects, only strings are checked
	 * @param match String to match
	 * @return true, if match contained in the collection
	 */
	 protected static boolean caseInsensitiveContains(ArrayList collection, 
			 String match) {
         for(Object obj :collection) {
             String str = (String)obj;
             if (str != null) {
                 if (str.equalsIgnoreCase(match)) {
                     return true;
                 }
             }
         }

         return false;
     }
}
