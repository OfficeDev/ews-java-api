/**************************************************************************
 * copyright file="CreateResponseObjectResponse.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CreateResponseObjectResponse.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * 
 *Represents response to generic Create request.
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
final class CreateResponseObjectResponse extends CreateItemResponseBase {

	/**
	 * Gets Item instance.
	 * 
	 * @param service
	 *            The service.
	 * @param xmlElementName
	 *            Name of the XML element.
	 * @return Item.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected Item getObjectInstance(ExchangeService service,
			String xmlElementName) throws Exception {
		try {
			return EwsUtilities.createEwsObjectFromXmlElementName(Item.class,
					service, xmlElementName);
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Initializes a new instance of the CreateResponseObjectResponse class.
	 */
	protected CreateResponseObjectResponse() {
		super();
	}

}
