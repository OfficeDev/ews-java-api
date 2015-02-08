package microsoft.exchange.webservices.data;

/**
 * Interface defined for types that can produce a string representation for use
 * in search filters.
 */
public interface ISearchStringProvider {
  /**
   * Get a string representation for using this instance in a search filter.
   *
   * @return String representation of instance.
   */
  String getSearchString();
}
