package microsoft.exchange.webservices.data;

/**
 * Represents the response to an individual attachment creation operation.
 */
public final class CreateAttachmentResponse extends ServiceResponse {

  /**
   * The attachment.
   */
  private Attachment attachment;

  /**
   * Initializes a new instance of the CreateAttachmentResponse class.
   *
   * @param attachment the attachment
   */
  protected CreateAttachmentResponse(Attachment attachment) {
    super();
    EwsUtilities.EwsAssert(attachment != null,
        "CreateAttachmentResponse.ctor", "attachment is null");

    this.attachment = attachment;
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws Exception {
    super.readElementsFromXml(reader);

    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.Attachments);

    // reader.read(XmlNodeType.START_ELEMENT);
    XmlNodeType x = new XmlNodeType(XmlNodeType.START_ELEMENT);
    reader.read(x);
    this.attachment.loadFromXml(reader, reader.getLocalName());

    reader.readEndElement(XmlNamespace.Messages,
        XmlElementNames.Attachments);
  }

  /**
   * Gets the attachment that was created.
   *
   * @return the attachment
   */
  protected Attachment getAttachment() {
    return this.attachment;
  }

}
