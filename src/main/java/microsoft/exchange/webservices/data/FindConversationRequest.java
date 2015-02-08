package microsoft.exchange.webservices.data;

/**
 * Represents a request to a Find Conversation operation
 */
final class FindConversationRequest extends SimpleServiceRequestBase {


  private ConversationIndexedItemView view;
  private SearchFilter.IsEqualTo searchFilter;
  private FolderIdWrapper folderId;

  /**
   * @throws Exception
   */
  protected FindConversationRequest(ExchangeService service)
      throws Exception {
    super(service);
  }


  /**
   * Gets or sets the view controlling the number of conversations returned.
   */
  protected ConversationIndexedItemView getIndexedItemView() {
    return this.view;
  }

  protected void setIndexedItemView(ConversationIndexedItemView value) {
    this.view = value;
  }



  /**
   * Gets or sets the search filter.
   */
  protected SearchFilter.IsEqualTo getConversationViewFilter() {

    return this.searchFilter;
  }

  protected void setConversationViewFilter(SearchFilter.IsEqualTo value) {
    this.searchFilter = value;

  }

  /**
   * Gets or sets folder id
   */
  protected FolderIdWrapper getFolderId() {
    return this.folderId;
  }

  protected void setFolderId(FolderIdWrapper value) {
    this.folderId = value;
  }


  /**
   * Validate request.
   *
   * @throws Exception
   * @throws microsoft.exchange.webservices.data.ServiceLocalException
   */
  @Override
  protected void validate() throws ServiceLocalException, Exception {
    super.validate();
    this.view.internalValidate(this);
  }


  /**
   * Writes XML attributes.
   *
   * @param writer The writer.
   * @throws ServiceXmlSerializationException
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    super.writeAttributesToXml(writer);
  }


  /**
   * Writes XML attributes.
   *
   * @param writer The writer.
   * @throws Exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    this.getIndexedItemView().writeToXml(writer);

    if (this.getConversationViewFilter() != null) {
      writer.writeStartElement(XmlNamespace.Messages,
          XmlElementNames.Restriction);
      this.getConversationViewFilter().writeToXml(writer);
      writer.writeEndElement(); // Restriction
    }

    this.getIndexedItemView().writeOrderByToXml(writer);

    writer.writeStartElement(XmlNamespace.Messages,
        XmlElementNames.ParentFolderId);
    this.getFolderId().writeToXml(writer);
    writer.writeEndElement();
  }

  /**
   * Parses the response.
   *
   * @param reader The reader.
   * @return Response object.
   * @throws Exception
   */
  @Override
  protected Object parseResponse(EwsServiceXmlReader reader)
      throws Exception {
    FindConversationResponse response = new FindConversationResponse();
    response.loadFromXml(reader,
        XmlElementNames.FindConversationResponse);
    return response;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getXmlElementName() {
    return XmlElementNames.FindConversation;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name.
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.FindConversationResponse;
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2010_SP1;
  }

  /**
   * Executes this request.
   *
   * @return Service response.
   * @throws Exception
   * @throws microsoft.exchange.webservices.data.ServiceLocalException
   */
  protected FindConversationResponse execute()
      throws ServiceLocalException, Exception {
    FindConversationResponse serviceResponse =
        (FindConversationResponse) this.internalExecute();
    serviceResponse.throwIfNecessary();
    return serviceResponse;
  }
}

