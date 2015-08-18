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

package microsoft.exchange.webservices.data.autodiscover;

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.enumeration.misc.TraceFlags;
import microsoft.exchange.webservices.data.core.exception.dns.DnsException;
import microsoft.exchange.webservices.data.dns.DnsClient;
import microsoft.exchange.webservices.data.dns.DnsSrvRecord;

import javax.xml.stream.XMLStreamException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that reads AutoDiscover configuration information from DNS.
 */
class AutodiscoverDnsClient {

  /**
   * SRV DNS prefix to lookup.
   */
  private static final String AutoDiscoverSrvPrefix = "_autodiscover._tcp.";

  /**
   * We are only interested in records that use SSL.
   */
  private static final int SslPort = 443;

  /**
   * Random selector in the case of ties.
   */
  private static Random RandomTieBreakerSelector = new Random();

  /**
   * AutodiscoverService using this DNS reader.
   */
  private AutodiscoverService service;

  /**
   * Initializes a new instance of the class.
   *
   * @param service the service
   */
  protected AutodiscoverDnsClient(AutodiscoverService service) {
    this.service = service;
  }

  /**
   * Extracts a valid autodiscover hostname, if any, from a dns srv response.
   *
   * @param dnsNameTarget The hostname response returned by DNS
   * @return Autodiscover hostname (will be null if dnsNameTarget is invalid).
   */
  protected static String extractHostnameFromDnsSrv(String dnsNameTarget) {
    if (dnsNameTarget == null || dnsNameTarget.isEmpty()) {
      return null;
    } else {
      if (dnsNameTarget.endsWith(".")) {
        dnsNameTarget = dnsNameTarget.substring(0, dnsNameTarget.length()-1);
      }
      return dnsNameTarget;
    }
  }

  /**
   * Finds the Autodiscover host from DNS SRV records.
   *
   * @param domain the domain
   * @return Autodiscover hostname (will be null if lookup failed).
   * @throws IOException signals that an I/O exception has occurred.
   */
  protected String findAutodiscoverHostFromSrv(String domain)
      throws XMLStreamException, IOException {
    String domainToMatch = AutoDiscoverSrvPrefix + domain;

    DnsSrvRecord dnsSrvRecord = this
        .findBestMatchingSrvRecord(domainToMatch);
    if (dnsSrvRecord != null) {
      String hostName = extractHostnameFromDnsSrv(dnsSrvRecord.getNameTarget());
      if (hostName != null) {
        this.service.traceMessage(TraceFlags.AutodiscoverConfiguration, String
            .format("DNS query for SRV record for domain %s found %s", domain, hostName));
        return hostName;
      }
    }
    this.service.traceMessage(TraceFlags.AutodiscoverConfiguration,
                              "No appropriate SRV record was found.");
    return null;
  }

  /**
   * Finds the best matching SRV record.
   *
   * @param domain the domain
   * @return DnsSrvRecord (will be null if lookup failed)
   * @throws XMLStreamException the XML stream exception
   * @throws IOException signals that an I/O exception has occurred.
   */
  private DnsSrvRecord findBestMatchingSrvRecord(String domain)
      throws XMLStreamException, IOException {
    List<DnsSrvRecord> dnsSrvRecordList;
    try {
      // Make DnsQuery call to get collection of SRV records.
      dnsSrvRecordList = DnsClient.dnsQuery(DnsSrvRecord.class, domain, this.service.getDnsServerAddress());
    } catch (DnsException ex) {
      String dnsExcMessage = String.format("DnsQuery returned error '%s'.", ex.getMessage());
      this.service
          .traceMessage(
              TraceFlags.AutodiscoverConfiguration,
              dnsExcMessage);
      return null;
    } catch (SecurityException ex) {
      // In restricted environments, we may not be allowed to call
      // un-managed code.
      this.service.traceMessage(TraceFlags.AutodiscoverConfiguration,
          String.format(
              "DnsQuery cannot be called. Security error: %s.",
              ex.getMessage()));
      return null;
    }

    this.service.traceMessage(TraceFlags.AutodiscoverConfiguration, String
        .format("%d SRV records were returned.", dnsSrvRecordList
            .size()));

    // If multiple records were returned, they will be returned sorted by
    // priority
    // (and weight) order. Need to find the index of the first record that
    // supports SSL.
    int priority = Integer.MIN_VALUE;
    int weight = Integer.MAX_VALUE;
    boolean recordFound = false;
    for (DnsSrvRecord dnsSrvRecord : dnsSrvRecordList) {
      if (dnsSrvRecord.getPort() == SslPort) {
        priority = dnsSrvRecord.getPriority();
        weight = dnsSrvRecord.getWeight();
        recordFound = true;
        break;
      }
    }

    // Records were returned but nothing matched our criteria.
    if (!recordFound) {
      this.service.traceMessage(TraceFlags.AutodiscoverConfiguration,
          "No appropriate SRV records were found.");

      return null;
    }

    List<DnsSrvRecord> bestDnsSrvRecordList = new ArrayList<DnsSrvRecord>();
    for (DnsSrvRecord dnsSrvRecord : dnsSrvRecordList) {
      if (dnsSrvRecord.getPort() == SslPort &&
          dnsSrvRecord.getPriority() == priority &&
          dnsSrvRecord.getWeight() == weight) {
        bestDnsSrvRecordList.add(dnsSrvRecord);
      }
    }

    // The list must contain at least one matching record since we found one
    // earlier.
    EwsUtilities.ewsAssert(dnsSrvRecordList.size() > 0, "AutodiscoverDnsClient.FindBestMatchingSrvRecord",
                           "At least one DNS SRV record must match the criteria.");

    // If we have multiple records with the same priority and weight,
    // randomly pick one.
    int recordIndex = (bestDnsSrvRecordList.size() > 1) ?
        RandomTieBreakerSelector
            .nextInt(bestDnsSrvRecordList.size()) :
        0;

    DnsSrvRecord bestDnsSrvRecord = bestDnsSrvRecordList.get(recordIndex);

    String traceMessage = String.format("Returning SRV record %d " +
            "of %d records. " +
            "Target: %s, Priority: %d, Weight: %d",
        recordIndex, dnsSrvRecordList.size(),
        bestDnsSrvRecord.getNameTarget(),
        bestDnsSrvRecord.getPriority(),
        bestDnsSrvRecord.getWeight());
    this.service.traceMessage(TraceFlags.
        AutodiscoverConfiguration, traceMessage);


    return bestDnsSrvRecord;
  }

}
