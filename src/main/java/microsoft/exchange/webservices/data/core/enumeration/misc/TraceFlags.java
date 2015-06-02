/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.core.enumeration.misc;

/**
 * Defines flags to control tracing details.
 */
public enum TraceFlags {
        /*
	 * No tracing.
	 */
  /**
   * The None.
   */
  None,
	/*
	 * Trace EWS request messages.
	 */
  /**
   * The Ews request.
   */
  EwsRequest,
	/*
	 * Trace EWS response messages.
	 */
  /**
   * The Ews response.
   */
  EwsResponse,
	/*
	 * Trace EWS response HTTP headers.
	 */
  /**
   * The Ews response http headers.
   */
  EwsResponseHttpHeaders,
	/*
	 * Trace Autodiscover request messages.
	 */
  /**
   * The Autodiscover request.
   */
  AutodiscoverRequest,
	/*
	 * Trace Autodiscover response messages.
	 */
  /**
   * The Autodiscover response.
   */
  AutodiscoverResponse,
	/*
	 * Trace Autodiscover response HTTP headers.
	 */
  /**
   * The Autodiscover response http headers.
   */
  AutodiscoverResponseHttpHeaders,
	/*
	 * Trace Autodiscover configuration logic.
	 */
  /**
   * The Autodiscover configuration.
   */
  AutodiscoverConfiguration,

	/*
	 * Trace messages used in debugging the Exchange Web Services Managed API
	 */
  /**
   * The Debug Message.
   */
  DebugMessage,

	/*
	 * Trace EWS request HTTP headers.
	 */
  /**
   * The Ews Request Http Headers.
   */
  EwsRequestHttpHeaders,

	/*
	 * Trace Autodiscover request HTTP headers.
	 */
  /**
   * The Autodiscover Request HttpHeaders
   */
  AutodiscoverRequestHttpHeaders,

}
