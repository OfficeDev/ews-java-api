package microsoft.exchange.webservices.data;

import org.apache.http.Header;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class ByteArrayOSRequestEntity extends BasicHttpEntity {

  private ByteArrayOutputStream os = null;

  /**
   * Constructor for ByteArrayOSRequestEntity.
   */
  ByteArrayOSRequestEntity(OutputStream os) {
    super();
    this.os = (ByteArrayOutputStream) os;
  }

  @Override
  public long getContentLength() {
    return os.size();
  }

  @Override
  public Header getContentType() {
    return new BasicHeader("Content-Type", "text/xml; charset=utf-8");
  }

  @Override
  public boolean isRepeatable() {
    return true;
  }

  @Override
  public void writeTo(OutputStream out) throws IOException {
    os.writeTo(out);
  }

  @Override
  public boolean isStreaming() {
    return false;
  }
}
