/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import org.apache.commons.codec.binary.Base64OutputStream;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a file attachment.
 */
public final class FileAttachment extends Attachment {

  /**
   * The file name.
   */
  private String fileName;

  /**
   * The content stream.
   */
  private InputStream contentStream;

  /**
   * The content.
   */
  private byte[] content;

  /**
   * The load to stream.
   */
  private OutputStream loadToStream;

  /**
   * The is contact photo.
   */
  private boolean isContactPhoto;

  /**
   * Initializes a new instance.
   *
   * @param owner the owner
   */
  protected FileAttachment(Item owner) {
    super(owner);
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  protected String getXmlElementName() {
    return XmlElementNames.FileAttachment;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void validate(int attachmentIndex) throws ServiceValidationException {
    if ((this.fileName == null || this.fileName.isEmpty())
        && this.content == null && this.contentStream == null) {
      throw new ServiceValidationException(String.format(
          Strings.FileAttachmentContentIsNotSet,
          attachmentIndex));
    }
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader the reader
   * @return True if element was read.
   * @throws Exception the exception
   */
  @Override
  protected boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    boolean result = super.tryReadElementFromXml(reader);

    if (!result) {
      if (reader.getLocalName().equals(XmlElementNames.IsContactPhoto)) {
        this.isContactPhoto = reader.readElementValue(Boolean.class);
      } else if (reader.getLocalName().equals(XmlElementNames.Content)) {
        if (this.loadToStream != null) {
          reader.readBase64ElementValue(this.loadToStream);
        } else {
          // If there's a file attachment content handler, use it.
          // Otherwise
          // load the content into a byte array.
          // TODO: Should we mark the attachment to indicate that
          // content is stored elsewhere?
          if (reader.getService().getFileAttachmentContentHandler() != null) {
            OutputStream outputStream = reader.getService()
                .getFileAttachmentContentHandler()
                .getOutputStream(getId());
            if (outputStream != null) {
              reader.readBase64ElementValue(outputStream);
            } else {
              this.content = reader.readBase64ElementValue();
            }
          } else {
            this.content = reader.readBase64ElementValue();
          }
        }

        result = true;
      }
    }

    return result;
  }


  /**
   * For FileAttachment, the only thing need to patch is the AttachmentId.
   *
   * @param reader The reader.
   * @return true if element was read
   */
  @Override
  protected boolean tryReadElementFromXmlToPatch(EwsServiceXmlReader reader) throws Exception {
    return super.tryReadElementFromXml(reader);
  }


  /**
   * Writes elements and content to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    super.writeElementsToXml(writer);
    // ExchangeVersion ev=writer.getService().getRequestedServerVersion();
    if (writer.getService().getRequestedServerVersion().ordinal() >
        ExchangeVersion.Exchange2007_SP1
            .ordinal()) {
      writer.writeElementValue(XmlNamespace.Types,
          XmlElementNames.IsContactPhoto, this.isContactPhoto);
    }

    writer.writeStartElement(XmlNamespace.Types, XmlElementNames.Content);

    if (!(this.fileName == null || this.fileName.isEmpty())) {
      File fileStream = new File(this.fileName);
      FileInputStream fis = null;
      try {
        fis = new FileInputStream(fileStream);
        writer.writeBase64ElementValue(fis);
      } finally {
        if (fis != null) {
          fis.close();
        }
      }

    } else if (this.contentStream != null) {
      writer.writeBase64ElementValue(this.contentStream);
    } else if (this.content != null) {
      writer.writeBase64ElementValue(this.content);
    } else {
      EwsUtilities.EwsAssert(false, "FileAttachment.WriteElementsToXml",
          "The attachment's content is not set.");
    }

    writer.writeEndElement();
  }

  /**
   * Loads the content of the file attachment into the specified stream.
   * Calling this method results in a call to EWS.
   *
   * @param stream the stream
   * @throws Exception the exception
   */
  public void load(OutputStream stream) throws Exception {
    this.loadToStream = stream;

    try {
      this.load();
    } finally {
      this.loadToStream = null;
    }
  }

  /**
   * Loads the content of the file attachment into the specified file.
   * Calling this method results in a call to EWS.
   *
   * @param fileName the file name
   * @throws Exception the exception
   */
  public void load(String fileName) throws Exception {
    File fileStream = new File(fileName);
    FileOutputStream fos = new FileOutputStream(fileStream);
    this.loadToStream = fos;
    try {
      this.load();
    } finally {
      this.loadToStream.flush();
      this.loadToStream = null;
    }

    this.fileName = fileName;
    this.content = null;
    this.contentStream = null;
  }

  /**
   * Streams the decoded content of this attachment into the specified stream.
   * Calling this method results in a call to EWS.
   *
   * @param outputStream the stream to receive the content
   * @throws Exception the exception
   */
  public void streamContent(OutputStream outputStream) throws Exception {
    File responseFile = File.createTempFile("response", ".tmp");
    responseFile.deleteOnExit();
    BufferedOutputStream os = null;
    try {
      os = new BufferedOutputStream(new FileOutputStream(responseFile));
      this.getOwner().getService().streamGetAttachmentResponse(this, os);
      os.flush();
      os.close();
      writeContentFromResponseFile(new FileInputStream(responseFile), outputStream);
    } finally {
      if (os != null) {
        os.close();
      }
      responseFile.delete();
    }
  }

  /**
   * Helper method to read through the responseInputStream (which is required to be xml) and
   * write the attachment's content to the outputStream argument.
   * @param responseInputStream An {@link InputStream} that is assumed to be an xml document
   *                            with a FileAttachment's "Content" element within it.
   * @param outputStream An {@link OutputStream} provided by the caller that will be filled
   *                     with the binary attachment content.
   * @throws IOException If an exception occurs.
   */
  public static void writeContentFromResponseFile(final InputStream responseInputStream, OutputStream outputStream) throws IOException {

    InputStream is = new BufferedInputStream(responseInputStream);
    final List<String> patterns = Arrays.asList(":Content>", ":Content/>");
    try {
      // read ahead until we match the "pattern" variable in the responseInputStream
      readUntilPatternMatch(is, patterns);

      // now that we've found the beginning of the Base64-encoded element value, wrap it with
      // a Base64ValueStream so it stops reading when the delimiting "<" character is found.
      Base64ValueStream base64ValueStream = new Base64ValueStream(is);

      // Use a Base64OutputStream to write to outputStream so the base64 data is decoded
      // during the writing operation.
      Base64OutputStream base64OutputStream = new Base64OutputStream(
          new BufferedOutputStream(outputStream), false, 0, null);
      int b;
      try {
        while (-1 != (b = base64ValueStream.read())) {
          base64OutputStream.write(b);
        }
        base64OutputStream.flush();
      } finally {
        base64OutputStream.close();
        base64ValueStream.close();
      }
    } finally {
      is.close();
    }
  }

  /**
   * Helper function used to keep reading through the specified {@link InputStream} until the (UTF8-encoded) bytes of
   * at least one of the specified patterns are found.
   *
   * @param is An {@link InputStream}.
   * @param patterns The patterns to find.
   * @throws IOException If an exception occurs or none of the patterns are found..
   */
  static void readUntilPatternMatch(InputStream is, List<String> patterns) throws IOException {
    final int numPatterns = patterns.size();
    final byte[][] patternBytes = new byte[numPatterns][];
    final int[] patternLengths = new int[numPatterns];
    for (int i = 0; i < patterns.size(); ++i) {
      // Get the bytes and length of each pattern.
      patternBytes[i] = patterns.get(i).getBytes("UTF-8");
      patternLengths[i] = patternBytes[i].length;
    }

    // Track where we are in matching each pattern.
    int[] patternIndices = new int[numPatterns];
    Arrays.fill(patternIndices, 0);
    long bytesRead = 0;
    boolean matched = false;
    int b = -1;

    while (!matched && (-1 != (b = is.read()))) {
      bytesRead++;
      // Loop through each of the patterns.
      for (int i = 0; i < numPatterns; ++i) {
        // Check if the current InputStream byte matches the next byte in the current pattern.
        if (b == patternBytes[i][patternIndices[i]]) {
          // Increment the index for the current pattern if matched.
          ++patternIndices[i];
        } else {
          // Re-start the index for the current pattern if no match.
          patternIndices[i] = 0;
        }
        // Break out of the for loop if we matched the current pattern.
        if (patternIndices[i] == patternLengths[i]) {
          matched = true;
          break;
        }
      }
    }

    if (b == -1) {
      throw new IOException(String.format(
          "read %s bytes, never found patterns [%s]",
          bytesRead, Arrays.toString(patterns.toArray())));
    }
  }

  /**
   * Helper class used to read through an XML value's Base64-encoded element value,
   * reporting "finished" when the "<" character is encountered.
   */
  static class Base64ValueStream extends FilterInputStream {

    public Base64ValueStream(InputStream is) {
      super(is);
    }

    private boolean foundEnd = false;

    @Override
    public int read() throws IOException {
      if (foundEnd) {
        return -1;
      }
      int result = super.read();
      if (result == (byte)'<') {
        foundEnd = true;
        result = -1;
      }
      return result;
    }
  }

  /**
   * Gets the name of the file the attachment is linked to.
   *
   * @return the file name
   */
  public String getFileName() {
    return this.fileName;
  }

  /**
   * Sets the file name.
   *
   * @param fileName the new file name
   */
  protected void setFileName(String fileName) {
    this.throwIfThisIsNotNew();

    this.fileName = fileName;
    this.content = null;
    this.contentStream = null;
  }

  /**
   * Gets  the content stream.Gets the name of the file the attachment
   * is linked to.
   *
   * @return The content stream
   */
  protected InputStream getContentStream() {
    return this.contentStream;
  }

  /**
   * Sets the content stream.
   *
   * @param contentStream the new content stream
   */
  protected void setContentStream(InputStream contentStream) {
    this.throwIfThisIsNotNew();

    this.contentStream = contentStream;
    this.content = null;
    this.fileName = null;
  }

  /**
   * Gets the content of the attachment into memory. Content is set only
   * when Load() is called.
   *
   * @return the content
   */
  public byte[] getContent() {
    return this.content;
  }

  /**
   * Sets the content.
   *
   * @param content the new content
   */
  protected void setContent(byte[] content) {
    this.throwIfThisIsNotNew();

    this.content = content;
    this.fileName = null;
    this.contentStream = null;
  }

  /**
   * Gets  a value indicating whether this attachment is a contact
   * photo.
   *
   * @return true, if is contact photo
   * @throws ServiceVersionException the service version exception
   */
  public boolean isContactPhoto() throws ServiceVersionException {
    EwsUtilities.validatePropertyVersion(this.getOwner().getService(),
        ExchangeVersion.Exchange2010, "IsContactPhoto");
    return this.isContactPhoto;
  }

  /**
   * Sets the checks if is contact photo.
   *
   * @param isContactPhoto the new checks if is contact photo
   * @throws ServiceVersionException the service version exception
   */
  public void setIsContactPhoto(boolean isContactPhoto)
      throws ServiceVersionException {
    EwsUtilities.validatePropertyVersion(this.getOwner().getService(),
        ExchangeVersion.Exchange2010, "IsContactPhoto");
    this.throwIfThisIsNotNew();
    this.isContactPhoto = isContactPhoto;
  }

}
