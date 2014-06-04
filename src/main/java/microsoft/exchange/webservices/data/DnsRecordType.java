/**************************************************************************
 * copyright file="DnsRecordType.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DnsRecordType.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * DNS record types.
 */
enum DnsRecordType {
	// RFC 1034/1035 Address Record
	/** The A. */
	A(0x0001),

	// Canonical Name Record
	/** The CNAME. */
	CNAME(0x0005),

	// / Start of Authority Record
	/** The SOA. */
	SOA(0x0006),

	// / Pointer Record
	/** The PTR. */
	PTR(0x000c),

	// / Mail Exchange Record
	/** The MX. */
	MX(0x000f),

	// / Text Record
	/** The TXT. */
	TXT(0x0010),

	// / RFC 1886 (IPv6 Address)
	/** The AAAA. */
	AAAA(0x001c),

	// / Service location - RFC 2052
	/** The SRV. */
	SRV(0x0021);

	/** The dns record. */
	@SuppressWarnings("unused")
	private final int dnsRecord;

	/**
	 * Instantiates a new dns record type.
	 * 
	 * @param dnsRecord
	 *            the dns record
	 */
	DnsRecordType(int dnsRecord) {
		this.dnsRecord = dnsRecord;
	}
}
