package microsoft.exchange.webservices.data;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Test class for the FileAttachment methods that were added to support streaming of large
 * FileAttachments at the XML response level so that we don't throw an OutOfMemoryError.
 */
public class FileAttachmentTest {

  @Test
  public void testParseResponse() throws Exception {
    verifyRepeatingResponse("Empty0", "".getBytes("UTF-8"), 0);
    verifyRepeatingResponse("Tiny1", "Tiny".getBytes("UTF-8"), 1);
    verifyRepeatingResponse("NineChars2", "NineChars".getBytes("UTF-8"), 2);
    verifyRepeatingResponse("NineChars10", "NineChars".getBytes("UTF-8"), 10);
    verifyRepeatingResponse("NineChars128k", "NineChars".getBytes("UTF-8"), 128 * 1024);
  }

  @Test
  public void testParseResponseBinary() throws Exception {
    verifyBinaryContent("Image1", ".jpg");
  }

  @Test
  public void testBadResponse() throws Exception {
    String xmlFilename = this.getClass().getSimpleName() + "BadResponseMissingContent.xml";
    InputStream is = ClassLoader.getSystemResourceAsStream(xmlFilename);
    try {
      // passing null for the OutputStream argument to prove that it will never get to it.
      
      FileAttachment.writeContentFromResponseFile(is, null);
      throw new RuntimeException("Should have thrown an exception when no Content element was found");
    } catch (IOException ex) {
      if (!ex.getMessage().contains("never found pattern")) {
        throw new RuntimeException("unexpected Exception", ex);
      }
    } finally {
      is.close();
    }
  }

  void verifyRepeatingResponse(String name, byte[] pattern, int repeated) throws Exception {

    String filename = this.getClass().getSimpleName() + name + ".xml";
    File content = File.createTempFile("content", ".tmp");
    OutputStream os = new BufferedOutputStream(new FileOutputStream(content));
    try {
      System.out.println(String.format("filename [%s], repeated [%s], content [%s]",
          filename, repeated, content.getAbsolutePath()));
      FileAttachment.writeContentFromResponseFile(ClassLoader.getSystemResourceAsStream(filename), os);
      content.deleteOnExit();
    } finally {
      os.close();
    }

    if (pattern.length == 0) {
      if (content.length() != 0) {
        throw new RuntimeException("Expected empty content file");
      }
      return;
    }
    // Now use an InputStream to read 'content' and verify we read the pattern the correct number of times.
    int byteCount;
    int readCount = 0;
    byte[] buffer = new byte[pattern.length];
    InputStream is = new BufferedInputStream(new FileInputStream(content));
    try {
      while (-1 != (byteCount = is.read(buffer))) {
        if (byteCount != buffer.length) {
          throw new RuntimeException(String.format(
              "expected to read %s bytes, actual %s bytes after %s reads",
              buffer.length, byteCount, readCount));
        }
        readCount++;
        if (readCount > repeated) {
          throw new RuntimeException(String.format(
              "pattern.length=%s, readCount=%s, should have been done after %s",
              pattern.length, readCount, repeated));
        }
        for (int i = 0; i < buffer.length; i++) {
          if (buffer[i] != pattern[i]) {
            throw new RuntimeException(String.format(
                "pattern.length=%s, after %s repeats, index %s mismatch: expected [%s], got [%s]",
                pattern.length, readCount, i, pattern[i], buffer[i]));
          }
        }
      }
      if (readCount != repeated) {
        throw new RuntimeException(String.format(
            "expected to read %s times, read %s times",
            repeated, readCount));
      }
    } finally {
      is.close();
    }
    content.delete();
  }

  void verifyBinaryContent(String name, String suffix) throws Exception {
    String xmlFilename = this.getClass().getSimpleName() + name + ".xml";
    File content = File.createTempFile("content", ".tmp");
    OutputStream os = new BufferedOutputStream(new FileOutputStream(content));
    try {
      System.out.println(String.format("filename [%s], content [%s]",
          xmlFilename, content.getAbsolutePath()));
      FileAttachment.writeContentFromResponseFile(ClassLoader.getSystemResourceAsStream(xmlFilename), os);
      content.deleteOnExit();
    } finally {
      os.close();
    }

    // Now an "expected" and "actual" InputStream to verify the content file.
    String expectedFilename = this.getClass().getSimpleName() + name + suffix;
    InputStream isExpected = ClassLoader.getSystemResourceAsStream(expectedFilename);
    InputStream isActual = new FileInputStream(content);
    try {
      long total = 0;
      int bExpected;
      int bActual;
      do {
        bExpected = isExpected.read();
        bActual = isActual.read();
        if (bExpected != bActual) {
          throw new RuntimeException(String.format("total=%s, bExpected=%s, bActual=%s",
              total, bExpected, bActual));
        }
      } while (bExpected != -1);
    } finally {
      isExpected.close();
      isActual.close();
    }
    content.delete();
  }

}
