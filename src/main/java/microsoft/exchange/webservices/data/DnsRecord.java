/**************************************************************************
 * copyright file="DnsRecord.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DnsRecord.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents a DnsRecord.
 */
abstract class DnsRecord {
	/*
	 * Name field of this DNS Record
	 */
	/** The name. */
	private String name;
	/*
	 * The suggested time for this dnsRecord to be valid
	 */
	/** The time to live. */
	private int timeToLive;

	/**
	 * Retrieves the value of the name property.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the value of the timeToLive property.
	 * 
	 * @return timeToLive
	 */
	public int getTimeToLive() {
		return timeToLive;
	}

	/**
	 * loads the DNS Record.
	 * 
	 * @param value
	 *            the value
	 * @throws DnsException
	 *             the dns exception
	 */
	protected void load(String value) throws DnsException {

	}
}
