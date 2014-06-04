/**************************************************************************
 * copyright file="EmptyFolderRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EmptyFolderRequest class.
 **************************************************************************/
package microsoft.exchange.webservices.data;
/***
 * 
 * Represents an EmptyFolder request.
 * 
 */
final class EmptyFolderRequest extends DeleteRequest<ServiceResponse>{

	private FolderIdWrapperList folderIds = new FolderIdWrapperList();
	private boolean deleteSubFolders;
	
	/***
	 * Initializes a new instance of the EmptyFolderRequest class.		
	 * @param service The service.		           
	 * @param errorHandlingMode Indicates how errors should be handled.		  
	 * @throws Exception 
	 */
	protected EmptyFolderRequest(ExchangeService service, 
			ServiceErrorHandling errorHandlingMode) 
	throws Exception {  
		super(service, errorHandlingMode);     
	}

	/***
	 * Validates request.	 
	 * @throws Exception 
	 * @throws ServiceLocalException 
	 */
	@Override
	protected  void validate() throws ServiceLocalException, Exception {
		super.validate();
		EwsUtilities.validateParam(this.getFolderIds(), "FolderIds");
		this.getFolderIds().validate(this.getService().
				getRequestedServerVersion());
	}

	/***
	 * Gets the expected response message count.	 
	 * @return Number of expected response messages.</returns>
	 */
	@Override
	protected  int getExpectedResponseMessageCount() {
		return this.getFolderIds().getCount();
	}

	/***
	 * Creates the service response.
	 * 
	 * @param service
	 *            The service.
	 *            
	 *  @param responseIndex
	 *  		  Index of the response.
	 *  
	 *  @return Service object
	 */
	@Override
	protected ServiceResponse createServiceResponse(ExchangeService service,
			int responseIndex) {
		return new ServiceResponse();
	}

	/***
	 * Gets the name of the XML element.	
	 *  @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.EmptyFolder;
	}
	
	/***
	 * Gets the name of the response XML element.	 
	 * @return XML element name.
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.EmptyFolderResponse;
	}
	
	/***
	 * Gets the name of the response message XML element.	
	 *  @return XML element name.
	 */
	@Override
	protected String getResponseMessageXmlElementName() {
		return XmlElementNames.EmptyFolderResponseMessage;
	}
	
	/***
	 * Writes XML elements.	 
	 * @param writer The writer.
	 * @throws Exception 
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer) 
	throws Exception {
		this.getFolderIds().writeToXml(
				writer,
				XmlNamespace.Messages,
				XmlElementNames.FolderIds);
	}
	
	/***
	 * Writes XML attributes.
	 * 
	 * @param writer
	 *      The writer.
	 * @throws ServiceXmlSerializationException 
	 */
	@Override
	protected void writeAttributesToXml(EwsServiceXmlWriter writer) 
	throws ServiceXmlSerializationException {
		super.writeAttributesToXml(writer);
		writer.writeAttributeValue(XmlAttributeNames.DeleteSubFolders, 
				this.deleteSubFolders);
	}
	
	/***
	 * Gets the request version.	
	 * @return Earliest Exchange version 
	 * in which this request is supported.	     
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2010_SP1;
	}
	
	/***
	 * Gets the folder ids.	
	 * @return The folder ids.	 
	 */
	protected FolderIdWrapperList getFolderIds() {
		return this.folderIds; 
	}
	
	/***
	 * Gets a value indicating whether empty
	 *  folder should also delete sub folders.
	 * 
	 * @value true if empty folder should also
	 *  delete sub folders, otherwise false.
	 *     
	 */
	protected boolean getDeleteSubFolders() {
		return deleteSubFolders;
	}
	
	/***
	 * Sets a value indicating whether empty 
	 * folder should also delete sub folders.	 
	 * @value true if empty folder should also 
	 * delete sub folders, otherwise false.	 
	 */
	protected void setDeleteSubFolders(boolean value) {
		this.deleteSubFolders = value;
	}

}