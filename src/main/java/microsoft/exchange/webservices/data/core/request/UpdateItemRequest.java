/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.UpdateItemResponse;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.MessageDisposition;
import microsoft.exchange.webservices.data.core.enumeration.service.SendInvitationsOrCancellationsMode;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.property.complex.FolderId;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class UpdateItemRequest.
 */
public final class UpdateItemRequest extends
    MultiResponseServiceRequest<UpdateItemResponse> {

  /**
   * The item.
   */
  private List<Item> items = new ArrayList<Item>();

  /**
   * The saved item destination folder.
   */
  private FolderId savedItemsDestinationFolder;

  /**
   * The conflict resolution mode.
   */
  private ConflictResolutionMode conflictResolutionMode;

  /**
   * The message disposition.
   */
  private MessageDisposition messageDisposition;

  /**
   * The send invitations or cancellations mode.
   */
  private SendInvitationsOrCancellationsMode
      sendInvitationsOrCancellationsMode;

  /**
   * Instantiates a new update item request.
   *
   * @param service           the service
   * @param errorHandlingMode the error handling mode
   * @throws Exception
   */
  public UpdateItemRequest(ExchangeService service, ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /*
   * (non-Javadoc)
   *
   * @see microsoft.exchange.webservices.ServiceRequestBase#validate()
   */
  @Override
  protected void validate() throws ServiceLocalException, Exception {
    super.validate();
    EwsUtilities.validateParamCollection(this.getItems().iterator(), "Items");
    for (int i = 0; i < this.getItems().size(); i++) {
      if ((this.getItems().get(i) == null) ||
          this.getItems().get(i).isNew()) {
        throw new ArgumentException(String.format("Items[%d] is either null or does not have an Id.", i));
      }
    }

    if (this.savedItemsDestinationFolder != null) {
      this.savedItemsDestinationFolder.validate(this.getService()
          .getRequestedServerVersion());
    }

    // Validate each item.
    for (Item item : this.getItems()) {
      item.validate();
    }
  }

  /*
   * (non-Javadoc)
   *
   * @seemicrosoft.exchange.webservices.MultiResponseServiceRequest#
   * createServiceResponse(microsoft.exchange.webservices.ExchangeService,
   * int)
   */
  @Override
  protected UpdateItemResponse createServiceResponse(ExchangeService service,
      int responseIndex) {
    return new UpdateItemResponse(this.getItems().get(responseIndex));
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * microsoft.exchange.webservices.ServiceRequestBase#getXmlElementName()
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.UpdateItem;
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * microsoft.exchange.webservices.ServiceRequestBase
   * #getResponseXmlElementName
   * ()
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.UpdateItemResponse;
  }

  /*
   * (non-Javadoc)
   *
   * @seemicrosoft.exchange.webservices.MultiResponseServiceRequest#
   * getResponseMessageXmlElementName()
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.UpdateItemResponseMessage;
  }

  /*
   * (non-Javadoc)
   *
   * @seemicrosoft.exchange.webservices.MultiResponseServiceRequest#
   * getExpectedResponseMessageCount()
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return this.items.size();
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * microsoft.exchange.webservices.ServiceRequestBase#writeAttributesToXml
   * (microsoft.exchange.webservices.EwsServiceXmlWriter)
   */
  @Override
  protected void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    super.writeAttributesToXml(writer);

    if (this.messageDisposition != null) {
      writer.writeAttributeValue(XmlAttributeNames.MessageDisposition,
          this.messageDisposition);
    }

    writer.writeAttributeValue(XmlAttributeNames.ConflictResolution,
        this.conflictResolutionMode);

    if (this.sendInvitationsOrCancellationsMode != null) {
      writer.writeAttributeValue(
          XmlAttributeNames.SendMeetingInvitationsOrCancellations,
          this.sendInvitationsOrCancellationsMode);
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * microsoft.exchange.webservices.ServiceRequestBase#writeElementsToXml(
   * microsoft.exchange.webservices.EwsServiceXmlWriter)
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    if (this.savedItemsDestinationFolder != null) {
      writer.writeStartElement(XmlNamespace.Messages,
          XmlElementNames.SavedItemFolderId);
      this.savedItemsDestinationFolder.writeToXml(writer);
      writer.writeEndElement();
    }

    writer.writeStartElement(XmlNamespace.Messages,
        XmlElementNames.ItemChanges);

    for (Item item : this.items) {
      item.writeToXmlForUpdate(writer);
    }

    writer.writeEndElement();
  }

  /*
   * (non-Javadoc)
   *
   * @seemicrosoft.exchange.webservices.ServiceRequestBase#
   * getMinimumRequiredServerVersion()
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the message disposition.
   *
   * @return the message disposition
   */
  public MessageDisposition getMessageDisposition() {
    return this.messageDisposition;
  }

  /**
   * Sets the message disposition.
   *
   * @param value the new message disposition
   */
  public void setMessageDisposition(MessageDisposition value) {
    this.messageDisposition = value;
  }

  /**
   * Gets the conflict resolution mode.
   *
   * @return the conflict resolution mode
   */
  public ConflictResolutionMode getConflictResolutionMode() {
    return this.conflictResolutionMode;
  }

  /**
   * Sets the conflict resolution mode.
   *
   * @param value the new conflict resolution mode
   */
  public void setConflictResolutionMode(ConflictResolutionMode value) {
    this.conflictResolutionMode = value;
  }

  /**
   * Gets the send invitations or cancellations mode.
   *
   * @return the send invitations or cancellations mode
   */
  public SendInvitationsOrCancellationsMode
  getSendInvitationsOrCancellationsMode() {
    return this.sendInvitationsOrCancellationsMode;
  }

  /**
   * Sets the send invitations or cancellations mode.
   *
   * @param value the new send invitations or cancellations mode
   */
  public void setSendInvitationsOrCancellationsMode(
      SendInvitationsOrCancellationsMode value) {
    this.sendInvitationsOrCancellationsMode = value;
  }

  /**
   * Gets the item.
   *
   * @return the item
   */
  public List<Item> getItems() {
    return this.items;
  }

  /**
   * Gets the saved item destination folder.
   *
   * @return the saved item destination folder
   */
  public FolderId getSavedItemsDestinationFolder() {
    return this.savedItemsDestinationFolder;
  }

  /**
   * Sets the saved item destination folder.
   *
   * @param value the new saved item destination folder
   */
  public void setSavedItemsDestinationFolder(FolderId value) {
    this.savedItemsDestinationFolder = value;
  }

}
