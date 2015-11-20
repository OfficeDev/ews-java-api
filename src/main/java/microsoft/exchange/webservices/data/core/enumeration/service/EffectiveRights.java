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

package microsoft.exchange.webservices.data.core.enumeration.service;

/**
 * Defines the effective user rights associated with an item or folder.
 */
public enum EffectiveRights {

  // The user has no acces right on the item or folder.
  /**
   * The None.
   */
  None(0),

  // The user can create associated item (FAI)
  /**
   * The Create associated.
   */
  CreateAssociated(1),

  // The user can create item.
  /**
   * The Create contents.
   */
  CreateContents(2),

  // The user can create sub-folder.

  /**
   * The Create hierarchy.
   */
  CreateHierarchy(4),

  // The user can delete item and/or folder.
  /**
   * The Delete.
   */
  Delete(8),

  // The user can modify the property of item and/or folder.
  /**
   * The Modify.
   */
  Modify(16),

  // The user can read the contents of item.
  /**
   * The Read.
   */
  Read(32),

  /// The user can view private item.
  /**
   * The View Private Items.
   */
  ViewPrivateItems(64);


  /**
   * The effective rights.
   */
  private final int effectiveRights;

  /**
   * Instantiates a new effective rights.
   *
   * @param effectiveRights the effective rights
   */
  EffectiveRights(int effectiveRights) {
    this.effectiveRights = effectiveRights;
  }

}
