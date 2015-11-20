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

package microsoft.exchange.webservices.data.core.enumeration.permission.folder;

//TODO : Do we want to include more information about 
//what those levels actually allow users to do?


/**
 * Defines permission levels for calendar folder.
 */
public enum FolderPermissionLevel {

  // No permission is granted.
  /**
   * The None.
   */
  None,

  // The Owner level.
  /**
   * The Owner.
   */
  Owner,

  // The Publishing Editor level.
  /**
   * The Publishing editor.
   */
  PublishingEditor,

  // The Editor level.
  /**
   * The Editor.
   */
  Editor,

  // The Pusnlishing Author level.
  /**
   * The Publishing author.
   */
  PublishingAuthor,

  // The Author level.
  /**
   * The Author.
   */
  Author,

  // The Non-editing Author level.
  /**
   * The Nonediting author.
   */
  NoneditingAuthor,

  // The Reviewer level.
  /**
   * The Reviewer.
   */
  Reviewer,

  // The Contributor level.
  /**
   * The Contributor.
   */
  Contributor,

  // The Free/busy Time Only level. (Can only be applied to Calendar folder).
  /**
   * The Free busy time only.
   */
  FreeBusyTimeOnly,

  // The Free/busy Time, Subject and Location level. (Can only be applied to
  // Calendar folder).
  /**
   * The Free busy time and subject and location.
   */
  FreeBusyTimeAndSubjectAndLocation,

  // The Custom level.
  /**
   * The Custom.
   */
  Custom
}
