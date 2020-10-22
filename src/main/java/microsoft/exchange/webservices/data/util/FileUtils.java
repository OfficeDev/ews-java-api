package microsoft.exchange.webservices.data.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class FileUtils {

  private static final Log LOG = LogFactory.getLog(FileUtils.class);

  private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
  private static final int EOF = -1;

  private FileUtils() {}

  public static long copyLarge(final InputStream input, final OutputStream output) throws IOException {
    return copyLarge(input, output, new byte[DEFAULT_BUFFER_SIZE]);
  }

  public static long copyLarge(InputStream input, OutputStream output, byte[] buffer) throws IOException {
    long count = 0;
    int n = 0;
    while (EOF != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
      count += n;
    }
    return count;
  }

  public static byte[] getBytes(final InputStream input) {
    if (input != null) {
      final ByteArrayOutputStream output = new ByteArrayOutputStream(DEFAULT_BUFFER_SIZE);
      try {
        FileUtils.copyLarge(input, output);
      } catch (Exception e) {
        LOG.warn("An error occurred during copying file", e);
      } finally {
        IOUtils.closeQuietly(output);
      }

      return output.toByteArray();
    }

    return null;
  }
}
