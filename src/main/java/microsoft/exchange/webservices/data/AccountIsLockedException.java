package microsoft.exchange.webservices.data;

import java.net.URI;

/**
 * Represents an error that occurs when the account that is
 * being accessed is locked and requires user interaction to be unlocked.
 */
public class AccountIsLockedException extends ServiceRemoteException {

  /**
   * Constant serialized ID used for compatibility.
   */
  private static final long serialVersionUID = 1L;

  private URI accountUnlockUrl;


  /**
   * Initializes a new instance of the AccountIsLockedException class.
   *
   * @param message          Error message text.
   * @param accountUnlockUrl URL for client to visit to unlock account.
   */
  public AccountIsLockedException(String message, URI accountUnlockUrl,
      Exception innerException) {

    super(message, innerException);
    this.setAccountUnlockUrl(accountUnlockUrl);
  }

  /**
   * Gets the URL of a web page where the user
   * can navigate to unlock his or her account.
   */
  public URI getAccountUnlockUrl() {
    return accountUnlockUrl;
  }


  /**
   * Sets the URL of a web page where the
   * user can navigate to unlock his or her account.
   */
  private void setAccountUnlockUrl(URI value) {
    this.accountUnlockUrl = value;
  }
}
