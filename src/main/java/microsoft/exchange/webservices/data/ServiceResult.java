package microsoft.exchange.webservices.data;

/**
 * Defines the result of a call to an EWS method. Values in this enumeration
 * have to be ordered from lowest to highest severity.
 */
public enum ServiceResult {
  // The call was successful
  /**
   * The Success.
   */
  Success,

  // The call triggered at least one warning
  /**
   * The Warning.
   */
  Warning,

  // The call triggered at least one error
  /**
   * The Error.
   */
  Error
}
