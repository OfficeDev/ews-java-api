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

package microsoft.exchange.webservices.data.core.enumeration.misc.error;

public enum WebExceptionStatus {
  // Summary:
  //     No error was encountered.
  Success,
  //
  // Summary:
  //     The name resolver service could not resolve the host name.
  NameResolutionFailure,
  //
  // Summary:
  //     The remote service point could not be contacted at the transport level.
  ConnectFailure,
  //
  // Summary:
  //     A complete response was not received from the remote server.
  ReceiveFailure,
  //
  // Summary:
  //     A complete request could not be sent to the remote server.
  SendFailure,
  //
  // Summary:
  //     The request was a piplined request and the connection was closed before the
  //     response was received.
  PipelineFailure,
  //
  // Summary:
  //     The request was canceled, the System.Net.WebRequest.Abort() method was called,
  //     or an unclassifiable error occurred. This is the default value for System.Net.WebException.Status.
  RequestCanceled,
  //
  // Summary:
  //     The response received from the server was complete but indicated a protocol-level
  //     error. For example, an HTTP protocol error such as 401 Access Denied would
  //     use this status.
  ProtocolError,
  //
  // Summary:
  //     The connection was prematurely closed.
  ConnectionClosed,
  //
  // Summary:
  //     A server certificate could not be validated.
  TrustFailure,
  //
  // Summary:
  //     An error occurred while establishing a connection using SSL.
  SecureChannelFailure,
  //
  // Summary:
  //     The server response was not a valid HTTP response.
  ServerProtocolViolation,
  //
  // Summary:
  //     The connection for a request that specifies the Keep-alive header was closed
  //     unexpectedly.
  KeepAliveFailure,
  //
  // Summary:
  //     An internal asynchronous request is pending.
  Pending,
  //
  // Summary:
  //     No response was received during the time-out period for a request.
  Timeout,
  //
  // Summary:
  //     The name resolver service could not resolve the proxy host name.
  ProxyNameResolutionFailure,
  //
  // Summary:
  //     An exception of unknown type has occurred.
  UnknownError,
  //
  // Summary:
  //     A message was received that exceeded the specified limit when sending a request
  //     or receiving a response from the server.
  MessageLengthLimitExceeded,
  //
  // Summary:
  //     The specified cache entry was not found.
  CacheEntryNotFound,
  //
  // Summary:
  //     The request was not permitted by the cache policy. In general, this occurs
  //     when a request is not cacheable and the effective policy prohibits sending
  //     the request to the server. You might receive this status if a request method
  //     implies the presence of a request body, a request method requires direct
  //     interaction with the server, or a request contains a conditional header.
  RequestProhibitedByCachePolicy,
  //
  // Summary:
  //     This request was not permitted by the proxy.
  RequestProhibitedByProxy,


}

