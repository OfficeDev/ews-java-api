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

package microsoft.exchange.webservices.data.core.enumeration.availability;

/**
 * Defines the type of free/busy information returned by a GetUserAvailability
 * operation.
 */
public enum FreeBusyViewType {

  // No view could be returned. This value cannot be specified in a call to
  // GetUserAvailability.
  /**
   * The None.
   */
  None,

  // Represents an aggregated free/busy stream. In cross-forest scenarios in
  // which the target user in one forest
  // does not have an Availability service configured, the Availability
  // service of the requestor retrieves the
  // target users free/busy information from the free/busy public folder.
  // Because public folder only store
  // free/busy information in merged form, MergedOnly is the only available
  // information.
  /**
   * The Merged only.
   */
  MergedOnly,

  // Represents the legacy status information: free, busy, tentative, and OOF.
  // This also includes the start/end
  // times of the appointments. This view is richer than the legacy free/busy
  // view because individual meeting
  // start and end times are provided instead of an aggregated free/busy
  // stream.
  /**
   * The Free busy.
   */
  FreeBusy,

  // Represents all the property in FreeBusy with a stream of merged
  // free/busy availability information.
  /**
   * The Free busy merged.
   */
  FreeBusyMerged,

  // Represents the legacy status information: free, busy, tentative, and OOF;
  // the start/end times of the
  // appointments; and various property of the appointment such as subject,
  // location, and importance.
  // This requested view will return the maximum amount of information for
  // which the requesting user is privileged.
  // If merged free/busy information only is available, as with requesting
  // information for users in a Microsoft
  // Exchange Server 2003 forest, MergedOnly will be returned. Otherwise,
  // FreeBusy or Detailed will be returned.
  /**
   * The Detailed.
   */
  Detailed,

  // Represents all the property in Detailed with a stream of merged
  // free/busy availability
  // information. If only merged free/busy information is available, for
  // example if the mailbox exists on a computer
  // running Exchange 2003, MergedOnly will be returned. Otherwise,
  // FreeBusyMerged or DetailedMerged will be returned.
  /**
   * The Detailed merged.
   */
  DetailedMerged

}
