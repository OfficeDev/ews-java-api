package microsoft.exchange.webservices.data;

public class WebProxyCredentials {

  private String username;

  private String password;

  private String domain;

  public WebProxyCredentials(String username, String password, String domain) {
    this.username = username;
    this.password = password;
    this.domain = domain;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getDomain() {
    return domain;
  }
}
