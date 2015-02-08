package microsoft.exchange.webservices.data;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Represents a DNS SRV Record.
 */
class DnsSrvRecord extends DnsRecord {
        /*
         * The string representing the target host
	 */
  /**
   * The target.
   */
  private String target;

	/*
         * priority of the target host specified in the owner name.
	 */
  /**
   * The priority.
   */
  private int priority;
	/*
	 * weight of the target host
	 */
  /**
   * The weight.
   */
  private int weight;
	/*
	 * port used on the target for the service
	 */
  /**
   * The port.
   */
  private int port;

  /**
   * Retrieves the value of the target property.
   *
   * @return target
   */
  protected String getNameTarget() {
    return this.target;
  }

  /**
   * Retrieves the value of the priority property.
   *
   * @return priority
   */
  protected int getPriority() {
    return priority;
  }

  /**
   * Retrieves the value of the weight property.
   *
   * @return weight
   */
  protected int getWeight() {
    return weight;
  }

  /**
   * Retrieves the value of the port property.
   *
   * @return port
   */
  protected int getPort() {
    return port;
  }

  /**
   * Initializes a new instance of the DnsSrvRecord class.
   *
   * @param srvRecord srvRecord that is fetched from JNDI
   * @throws microsoft.exchange.webservices.data.DnsException the dns exception
   */
  protected void load(String srvRecord) throws DnsException {
    super.load(null);
    StringTokenizer strTokens = new StringTokenizer(srvRecord);
    try {
      while (strTokens.hasMoreTokens()) {
        String priority = strTokens.nextToken();
        this.priority = Integer.parseInt(priority);

        String weight = strTokens.nextToken();
        this.weight = Integer.parseInt(weight);

        String port = strTokens.nextToken();
        this.port = Integer.parseInt(port);

        String target = strTokens.nextToken();
        this.target = target;
      }
    } catch (NumberFormatException ne) {
      throw new DnsException("NumberFormatException " + ne.getMessage());
    } catch (NoSuchElementException ne) {
      throw new DnsException("NoSuchElementException " + ne.getMessage());
    }

  }
}
