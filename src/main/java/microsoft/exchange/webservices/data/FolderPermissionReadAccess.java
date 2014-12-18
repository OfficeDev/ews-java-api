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

/**
 * Defines a user's read access permission on items in a non-calendar folder.
 */
public enum FolderPermissionReadAccess {

  // The user has no read access on the items in the folder.
  /**
   * The None.
   */
  None,

  // The user can read the start and end date and time of appointments. (Can
  // only be applied to Calendar folders).
  /**
   * The Time only.
   */
  TimeOnly,

  // The user can read the start and end date and time, subject and location
  // of appointments. (Can only be applied to Calendar folders).
  /**
   * The Time and subject and location.
   */
  TimeAndSubjectAndLocation,

  // The user has access to the full details of items.
  /**
   * The Full details.
   */
  FullDetails
}
