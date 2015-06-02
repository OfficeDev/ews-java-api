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

package microsoft.exchange.webservices.data.core.enumeration.property;

/**
 * defines how a complex property behaves.
 */
public enum PropertyDefinitionFlags {

  /**
   * No specific behavior.
   */
  None,

  /**
   * The property is automatically instantiated when it is read.
   */
  AutoInstantiateOnRead,

  /**
   * The existing instance of the property is reusable.
   */
  ReuseInstance,

  /**
   * The property can be set.
   */
  CanSet,

  /**
   * The property can be updated.
   */
  CanUpdate,

  /**
   * The property can be deleted.
   */
  CanDelete,

  /**
   * The property can be searched.
   */
  CanFind,

  /**
   * The property must be loaded explicitly.
   */
  MustBeExplicitlyLoaded,

  /**
   * Only meaningful for "collection" property. With this flag, the item in the collection gets updated,
   * instead of creating and adding new item to the collection.
   * Should be used together with the ReuseInstance flag.
   */

  UpdateCollectionItems;

}

