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

import java.util.List;

/**
 * Represents a response to a Move or Copy operation.
 */
public final class MoveCopyItemResponse extends ServiceResponse implements
		IGetObjectInstanceDelegate<ServiceObject> {

	/** The item. */
	private Item item;

	/**
	 * Represents a response to a Move or Copy operation.
	 */
	protected MoveCopyItemResponse() {
		super();
	}

	/**
	 * Gets Item instance.
	 * 
	 * @param service
	 *            the service
	 * @param xmlElementName
	 *            the xml element name
	 * @return the object instance
	 * @throws Exception
	 *             the exception
	 */
	private Item getObjectInstance(ExchangeService service,
			String xmlElementName) throws Exception {
		return EwsUtilities.createEwsObjectFromXmlElementName(Item.class,
				service, xmlElementName);
	}

	/**
	 * Reads response elements from XML.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void readElementsFromXml(EwsServiceXmlReader reader)
			throws Exception {
		super.readElementsFromXml(reader);
		List<Item> items = reader.readServiceObjectsCollectionFromXml(
				XmlElementNames.Items, this, false, /* clearPropertyBag */
				null, /* requestedPropertySet */
				false); /* summaryPropertiesOnly */

		// We only receive the copied or moved items if the copy or move
		// operation was within
		// a single mailbox. No item is returned if the operation is
		// cross-mailbox, from a
		// mailbox to a public folder or from a public folder to a mailbox.
		if (items.size() > 0) {
			this.item = items.get(0);
		}
	}

	/**
	 * Gets the object instance delegate.
	 * 
	 * @param service
	 *            the service
	 * @param xmlElementName
	 *            the xml element name
	 * @return the object instance delegate
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public ServiceObject getObjectInstanceDelegate(ExchangeService service,
			String xmlElementName) throws Exception {
		return this.getObjectInstance(service, xmlElementName);
	}

	/**
	 * Gets the copied or moved item. Item is null if the copy or move
	 * operation was between two mailboxes or between a mailbox and a public
	 * folder.
	 * 
	 * @return the item
	 */
	public Item getItem() {
		return this.item;
	}

}
