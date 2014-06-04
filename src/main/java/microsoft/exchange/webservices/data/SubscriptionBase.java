/**************************************************************************
 * copyright file="SubscriptionBase.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the SubscriptionBase.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 *Represents the base class for event subscriptions.
 * 
 */
@EditorBrowsable(state = EditorBrowsableState.Never)
public abstract class SubscriptionBase {

	/** The service. */
	private ExchangeService service;

	/** The id. */
	private String id;

	/** The watermark. */
	private String watermark;

	/**
	 * Instantiates a new subscription base.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception
	 *             the exception
	 */
	protected SubscriptionBase(ExchangeService service) throws Exception {
		EwsUtilities.validateParam(service, "service");
		// EwsUtilities.validateParam(service, "service");

		this.service = service;
	}

	/**
	 * Instantiates a new subscription base.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @throws Exception
	 *             the exception
	 */
	protected SubscriptionBase(ExchangeService service, String id)
	throws Exception {
		this(service);
		EwsUtilities.validateParam(id, "id");

		this.id = id;
	}

	/**
	 * Instantiates a new subscription base.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @param watermark
	 *            the watermark
	 * @throws Exception
	 *             the exception
	 */
	protected SubscriptionBase(ExchangeService service, String id,
			String watermark) throws Exception {
		this(service, id);
		this.watermark = watermark;
	}

	/**
	 * Load from xml.
	 * 
	 * @param reader
	 *            the reader
	 * @throws Exception
	 *             the exception
	 */
	protected void loadFromXml(EwsServiceXmlReader reader) throws Exception {
		this.id = reader.readElementValue(XmlNamespace.Messages,
				XmlElementNames.SubscriptionId);
		if(this.getUsesWatermark()) {
			this.watermark = reader.readElementValue(XmlNamespace.Messages,
					XmlElementNames.Watermark);		}

	}

	/**
	 * Gets the session.
	 * 
	 * @return the session
	 */
	protected ExchangeService getService() {
		return this.service;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	protected void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the water mark.
	 * 
	 * @param watermark
	 *            the new water mark
	 */
	protected void setWaterMark(String watermark) {
		this.watermark = watermark;
	}

	/**
	 * Gets the water mark.
	 * 
	 * @return the water mark
	 */
	public String getWaterMark() {
		return this.watermark;
	}

	/**
	 * Gets whether or not this subscription uses watermarks.
	 */
	protected  boolean getUsesWatermark() {
		return true;
	}

}
