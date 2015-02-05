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
 * Defines the effective user rights associated with an item or folder.
 */
public enum EffectiveRights {
  /**
   * The None.
   * The user has no acces right on the item or folder.
   */
  None,
  /**
   * The Create associated.
   * The user can create associated items (FAI)
   */
  CreateAssociated,
  /**
   * The Create contents.
   * The user can create items.
   */
  CreateContents,
  /**
   * The Create hierarchy.
   * The user can create sub-folders.
   */
  CreateHierarchy,
  /**
   * The Delete.
   * The user can delete items and/or folders.
   */
  Delete,
  /**
   * The Modify.
   * The user can modify the properties of items and/or folders.
   */
  Modify,
  /**
   * The Read.
   * The user can read the contents of items.
   */
  Read,
  /**
   * The View Private Items.
   * The user can view private items.
   */
  ViewPrivateItems;

}
