/**************************************************************************
 * copyright file="DnsClient.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the DnsClient.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * Class that represents DNS Query client.
 */
class DnsClient {

	/**
	 * Performs Dns query.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param cls
	 *            DnsRecord Type
	 * @param domain
	 *            the domain
	 * @param dnsServerAddress
	 *            IPAddress of DNS server to use (may be null)
	 * @return DnsRecord The DNS record list (never null but may be empty)
	 * @throws DnsException
	 *             the dns exception
	 */

	protected static <T extends DnsRecord> List<T> dnsQuery(Class<T> cls,
			String domain, String dnsServerAddress) throws DnsException {

		List<T> dnsRecordList = new ArrayList<T>();
		try {

			// Set up environment for creating initial context
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put("java.naming.factory.initial",
					"com.sun.jndi.dns.DnsContextFactory");
			env.put("java.naming.provider.url", "dns://" + dnsServerAddress);

			// Create initial context
			DirContext ictx = new InitialDirContext(env);

			// Retrieve SRV record context attributes for the specified domain
			Attributes contextAttributes = ictx.getAttributes(domain,
					new String[] { EWSConstants.SRVRECORD });
			if (contextAttributes != null) {
				NamingEnumeration<?> attributes = contextAttributes.getAll();
				if (attributes != null) {
					while (attributes.hasMore()) {
						Attribute attr = (Attribute) attributes.next();
						NamingEnumeration<?> srvValues = attr.getAll();
						if (srvValues != null) {
							while (srvValues.hasMore()) {
								T dnsRecord = cls.newInstance();

								// Loads the DNS SRV record
								dnsRecord.load((String) srvValues.next());
								dnsRecordList.add(dnsRecord);
							}
						}
					}
				}
			}
		} catch (NamingException ne) {
			throw new DnsException(ne.getMessage());
		} catch (Exception e) {
			throw new DnsException(e.getMessage());
		}
		return dnsRecordList;
	}
}
