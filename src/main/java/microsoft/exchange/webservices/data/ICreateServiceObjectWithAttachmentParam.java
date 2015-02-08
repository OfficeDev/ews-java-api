package microsoft.exchange.webservices.data;

/**
 * The Interface ICreateServiceObjectWithAttachmentParam.
 */
interface ICreateServiceObjectWithAttachmentParam {

  /**
   * Creates the service object with attachment param.
   *
   * @param itemAttachment the item attachment
   * @param isNew          the is new
   * @return the object
   * @throws Exception the exception
   */
  Object createServiceObjectWithAttachmentParam(
      ItemAttachment itemAttachment, boolean isNew) throws Exception;

}
