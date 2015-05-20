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

package microsoft.exchange.webservices.data.core.enumeration.search;

/**
 * Defines the location where a ResolveName operation searches for contacts.
 */
public enum ResolveNameSearchLocation {

  // The name is resolved against the Global Address List.
  /**
   * The Directory only.
   */
  DirectoryOnly,

  // The name is resolved against the Global Address List and then against the
  // Contacts folder if no match was found.
  /**
   * The Directory then contacts.
   */
  DirectoryThenContacts,

  // The name is resolved against the Contacts folder.
  /**
   * The Contacts only.
   */
  ContactsOnly,

  // The name is resolved against the Contacts folder and then against the
  // Global Address List if no match was found.
  /**
   * The Contacts then directory.
   */
  ContactsThenDirectory
}
