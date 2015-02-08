package microsoft.exchange.webservices.data;

/**
 * Represents the response to an individual attachment deletion operation.
 */
public final class DeleteAttachmentResponse extends ServiceResponse {

  /**
   * The attachment.
   */
  private Attachment attachment;

  /**
   * Initializes a new instance of the DeleteAttachmentResponse class.
   *
   * @param attachment the attachment
   */
  protected DeleteAttachmentResponse(Attachment attachment) {
    super();
    EwsUtilities.EwsAssert(attachment != null,
        "DeleteAttachmentResponse.ctor", "attachment is null");

    this.attachment = attachment;
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws microsoft.exchange.webservices.data.ServiceLocalException the service local exception
   * @throws Exception                                                 the exception
   */
  @Override
  protected void readElementsFromXml(EwsServiceXmlReader reader)
      throws ServiceLocalException, Exception {
    super.readElementsFromXml(reader);

    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.RootItemId);

    String changeKey = reader
        .readAttributeValue(XmlAttributeNames.RootItemChangeKey);
    if (!(null == changeKey || changeKey.isEmpty())) {
      this.attachment.getOwner().getRootItemId().setChangeKey(changeKey);
    }
    reader.readEndElement(XmlNamespace.Messages,
        XmlElementNames.RootItemId);
  }

  /**
   * Gets the attachment that was deleted.
   *
   * @return the attachment
   */
  protected Attachment getAttachment() {
    return this.attachment;
  }
}
