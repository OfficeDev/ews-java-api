package microsoft.exchange.webservices.data;

/**
 * Indicates which occurrence of a recurring task should be deleted.
 */
public enum AffectedTaskOccurrence {

  // All occurrences of the recurring task will be deleted.
  /**
   * The All occurrences.
   */
  AllOccurrences,

  // Only the current occurrence of the recurring task will be deleted.
  /**
   * The Specified occurrence only.
   */
  SpecifiedOccurrenceOnly
}
