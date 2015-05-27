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

package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.FlaggedForAction;
import microsoft.exchange.webservices.data.core.enumeration.property.Importance;
import microsoft.exchange.webservices.data.core.enumeration.property.Sensitivity;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

/**
 * Represents the set of conditions and exception available for a rule.
 */
public final class RulePredicates extends ComplexProperty {

  /**
   * The HasCategories predicate.
   */
  private StringList categories;

  /**
   * The ContainsBodyStrings predicate.
   */
  private StringList containsBodyStrings;
  /**
   * The ContainsHeaderStrings predicate.
   */
  private StringList containsHeaderStrings;

  /**
   * The ContainsRecipientStrings predicate.
   */
  private StringList containsRecipientStrings;

  /**
   * The ContainsSenderStrings predicate.
   */
  private StringList containsSenderStrings;

  /**
   * The ContainsSubjectOrBodyStrings predicate.
   */
  private StringList containsSubjectOrBodyStrings;

  /**
   * The ContainsSubjectStrings predicate.
   */
  private StringList containsSubjectStrings;

  /**
   * The FlaggedForAction predicate.
   */
  private FlaggedForAction flaggedForAction;

  /**
   * The FromAddresses predicate.
   */
  private EmailAddressCollection fromAddresses;

  /**
   * The FromConnectedAccounts predicate.
   */
  private StringList fromConnectedAccounts;

  /**
   * The HasAttachments predicate.
   */
  private boolean hasAttachments;

  /**
   * The Importance predicate.
   */
  private Importance importance;

  /**
   * The IsApprovalRequest predicate.
   */
  private boolean isApprovalRequest;

  /**
   * The IsAutomaticForward predicate.
   */
  private boolean isAutomaticForward;

  /**
   * The IsAutomaticReply predicate.
   */
  private boolean isAutomaticReply;

  /**
   * The IsEncrypted predicate.
   */
  private boolean isEncrypted;

  /**
   * The IsMeetingRequest predicate.
   */
  private boolean isMeetingRequest;

  /**
   * The IsMeetingResponse predicate.
   */
  private boolean isMeetingResponse;

  /**
   * The IsNDR predicate.
   */
  private boolean isNonDeliveryReport;

  /**
   * The IsPermissionControlled predicate.
   */
  private boolean isPermissionControlled;

  /**
   * The IsSigned predicate.
   */
  private boolean isSigned;

  /**
   * The IsVoicemail predicate.
   */
  private boolean isVoicemail;

  /**
   * The IsReadReceipt  predicate.
   */
  private boolean isReadReceipt;

  /**
   * ItemClasses predicate.
   */
  private StringList itemClasses;

  /**
   * The MessageClassifications predicate.
   */
  private StringList messageClassifications;

  /**
   * The NotSentToMe predicate.
   */
  private boolean notSentToMe;

  /**
   * SentCcMe predicate.
   */
  private boolean sentCcMe;

  /**
   * The SentOnlyToMe predicate.
   */
  private boolean sentOnlyToMe;

  /**
   * The SentToAddresses predicate.
   */
  private EmailAddressCollection sentToAddresses;

  /**
   * The SentToMe predicate.
   */
  private boolean sentToMe;

  /**
   * The SentToOrCcMe predicate.
   */
  private boolean sentToOrCcMe;

  /**
   * The Sensitivity predicate.
   */
  private Sensitivity sensitivity;

  /**
   * The Sensitivity predicate.
   */
  private RulePredicateDateRange withinDateRange;

  /**
   * The Sensitivity predicate.
   */
  private RulePredicateSizeRange withinSizeRange;

  /**
   * Initializes a new instance of the RulePredicates class.
   */
  protected RulePredicates() {
    super();
    this.categories = new StringList();
    this.containsBodyStrings = new StringList();
    this.containsHeaderStrings = new StringList();
    this.containsRecipientStrings = new StringList();
    this.containsSenderStrings = new StringList();
    this.containsSubjectOrBodyStrings = new StringList();
    this.containsSubjectStrings = new StringList();
    this.fromAddresses =
        new EmailAddressCollection(XmlElementNames.Address);
    this.fromConnectedAccounts = new StringList();
    this.itemClasses = new StringList();
    this.messageClassifications = new StringList();
    this.sentToAddresses =
        new EmailAddressCollection(XmlElementNames.Address);
    this.withinDateRange = new RulePredicateDateRange();
    this.withinSizeRange = new RulePredicateSizeRange();
  }

  /**
   * Gets the categories that an incoming message
   * should be stamped with for the condition or exception to apply.
   * To disable this predicate, empty the list.
   */
  public StringList getCategories() {
    return this.categories;
  }

  /**
   * Gets the strings that should appear in the body of
   * incoming messages for the condition or exception to apply.
   * To disable this predicate, empty the list.
   */
  public StringList getContainsBodyStrings() {
    return this.containsBodyStrings;
  }

  /**
   * Gets the strings that should appear in the
   * headers of incoming messages for the condition or
   * exception to apply. To disable this predicate, empty the list.
   */
  public StringList getContainsHeaderStrings() {
    return this.containsHeaderStrings;
  }

  /**
   * Gets the strings that should appear in either the
   * To or Cc fields of incoming messages for the condition
   * or exception to apply. To disable this predicate, empty the list.
   */
  public StringList getContainsRecipientStrings() {
    return this.containsRecipientStrings;
  }

  /**
   * Gets the strings that should appear
   * in the From field of incoming messages
   * for the condition or exception to apply.
   * To disable this predicate, empty  the list.
   */
  public StringList getContainsSenderStrings() {
    return this.containsSenderStrings;
  }

  /**
   * Gets the strings that should appear in either
   * the body or the subject of incoming messages for the
   * condition or exception to apply.
   * To disable this predicate, empty the list.
   */
  public StringList getContainsSubjectOrBodyStrings() {
    return this.containsSubjectOrBodyStrings;
  }

  /**
   * Gets the strings that should appear in the subject
   * of incoming messages for the condition or exception
   * to apply. To disable this predicate, empty the list.
   */
  public StringList getContainsSubjectStrings() {
    return this.containsSubjectStrings;
  }

  /**
   * Gets or sets the flag for action value that should
   * appear on incoming messages for the condition or execption to apply.
   * To disable this predicate, set it to null.
   */
  public FlaggedForAction getFlaggedForAction() {

    return this.flaggedForAction;
  }

  public void setFlaggedForAction(FlaggedForAction value) {
    if (this.canSetFieldValue(this.flaggedForAction, value)) {
      this.flaggedForAction = value;
      this.changed();
    }
  }

  /**
   * Gets the e-mail addresses of the senders of incoming
   * messages for the condition or exception to apply.
   * To disable this predicate, empty the list.
   */
  public EmailAddressCollection getFromAddresses() {
    return this.fromAddresses;
  }

  /**
   * Gets or sets a value indicating whether incoming messages must have
   * attachments for the condition or exception to apply.
   */
  public boolean getHasAttachments() {
    return this.hasAttachments;
  }

  public void setHasAttachments(boolean value) {
    if (this.canSetFieldValue(this.hasAttachments, value)) {
      this.hasAttachments = value;
      this.changed();
    }
  }

  /**
   * Gets or sets the importance that should be stamped on incoming messages
   * for the condition or exception to apply.
   * To disable this predicate, set it to null.
   */
  public Importance getImportance() {
    return this.importance;
  }

  public void setImportance(Importance value) {
    if (this.canSetFieldValue(this.importance, value)) {
      this.importance = value;
      this.changed();
    }
  }

  /**
   * Gets or sets a value indicating whether incoming messages must be
   * approval request for the condition or exception to apply.
   */
  public boolean getIsApprovalRequest() {
    return this.isApprovalRequest;
  }

  public void setIsApprovalRequest(boolean value) {
    if (this.canSetFieldValue(this.isApprovalRequest, value)) {

      this.isApprovalRequest = value;
      this.changed();
    }
  }

  /**
   * Gets or sets a value indicating whether incoming messages must be
   * automatic forwards for the condition or exception to apply.
   */
  public boolean getIsAutomaticForward() {
    return this.isAutomaticForward;
  }

  public void setIsAutomaticForward(boolean value) {
    if (this.canSetFieldValue(this.isAutomaticForward, value)) {
      this.isAutomaticForward = value;
      this.changed();
    }
  }

  /**
   * Gets or sets a value indicating whether incoming messages must be
   * automatic replies for the condition or exception to apply.
   */
  public boolean getIsAutomaticReply() {
    return this.isAutomaticReply;
  }

  public void setIsAutomaticReply(boolean value) {
    if (this.canSetFieldValue(this.isAutomaticReply, value)) {
      this.isAutomaticReply = value;
      this.changed();
    }
  }


  /**
   * Gets or sets a value indicating whether incoming messages must be
   * S/MIME encrypted for the condition or exception to apply.
   */
  public boolean getIsEncrypted() {
    return this.isEncrypted;
  }

  public void setIsEncrypted(boolean value) {
    if (this.canSetFieldValue(this.isEncrypted, value)) {
      this.isEncrypted = value;
      this.changed();
    }
  }

  /**
   * Gets or sets a value indicating whether incoming messages must be
   * meeting request for the condition or exception to apply.
   */
  public boolean getIsMeetingRequest() {
    return this.isMeetingRequest;
  }

  public void setIsMeetingRequest(boolean value) {
    if (this.canSetFieldValue(this.isEncrypted, value)) {

      this.isEncrypted = value;
      this.changed();
    }

  }


  /**
   * Gets or sets a value indicating whether incoming messages must be
   * meeting response for the condition or exception to apply.
   */
  public boolean getIsMeetingResponse() {

    return this.isMeetingResponse;
  }

  public void setIsMeetingResponse(boolean value) {
    if (this.canSetFieldValue(this.isMeetingResponse, value)) {
      this.isMeetingResponse = value;
      this.changed();
    }
  }

  /**
   * Gets or sets a value indicating whether incoming messages must be
   * non-delivery reports (NDR) for the condition or exception to apply.
   */
  public boolean getIsNonDeliveryReport() {
    return this.isNonDeliveryReport;
  }

  public void setIsNonDeliveryReport(boolean value) {
    if (this.canSetFieldValue(this.isNonDeliveryReport, value)) {
      this.isNonDeliveryReport = value;
      this.changed();
    }
  }

  /**
   * Gets or sets a value indicating whether incoming messages must be
   * permission controlled (RMS protected) for the condition or exception
   * to apply.
   */
  public boolean getIsPermissionControlled() {
    return this.isPermissionControlled;
  }

  public void setIsPermissionControlled(boolean value) {
    if (this.canSetFieldValue(this.isPermissionControlled, value)) {
      this.isPermissionControlled = value;
      this.changed();
    }
  }


  /**
   * Gets or sets a value indicating whether incoming messages must be
   * S/MIME signed for the condition or exception to apply.
   */
  public boolean getIsSigned() {
    return this.isSigned;
  }

  public void setIsSigned(boolean value) {
    if (this.canSetFieldValue(this.isSigned, value)) {
      this.isSigned = value;
      this.changed();
    }
  }


  /**
   * Gets or sets a value indicating whether incoming messages must be
   * voice mails for the condition or exception to apply.
   */
  public boolean getIsVoicemail() {
    return this.isVoicemail;
  }

  public void setIsVoicemail(boolean value) {
    if (this.canSetFieldValue(this.isVoicemail, value)) {
      this.isVoicemail = value;
      this.changed();
    }
  }


  /**
   * Gets or sets a value indicating whether incoming messages must be
   * read receipts for the condition or exception to apply.
   */
  public boolean getIsReadReceipt() {
    return this.isReadReceipt;
  }

  public void setIsReadReceipt(boolean value) {
    if (this.canSetFieldValue(this.isReadReceipt, value)) {
      this.isReadReceipt = value;
      this.changed();
    }
  }

  /**
   * Gets the e-mail account names from which incoming messages must have
   * been aggregated for the condition or exception to apply. To disable
   * this predicate, empty the list.
   */
  public StringList getFromConnectedAccounts() {
    return this.fromConnectedAccounts;
  }

  /**
   * Gets the item classes that must be stamped on incoming messages for
   * the condition or exception to apply. To disable this predicate,
   * empty the list.
   */
  public StringList getItemClasses() {
    return this.itemClasses;
  }

  /**
   * Gets the message classifications that
   * must be stamped on incoming messages
   * for the condition or exception to apply. To disable this predicate,
   * empty the list.
   */
  public StringList getMessageClassifications() {

    return this.messageClassifications;

  }

  /**
   * Gets or sets a value indicating whether the owner of the mailbox must
   * NOT be a To recipient of the incoming messages for the condition or
   * exception to apply.
   */

  public boolean getNotSentToMe() {
    return this.notSentToMe;
  }

  public void setNotSentToMe(boolean value) {
    if (this.canSetFieldValue(this.notSentToMe, value)) {
      this.notSentToMe = value;
      this.changed();
    }
  }


  /**
   * Gets or sets a value indicating whether the owner of the mailbox must be
   * a Cc recipient of incoming messages
   * for the condition or exception to apply.
   */
  public boolean getSentCcMe() {
    return this.sentCcMe;
  }

  public void setSentCcMe(boolean value) {
    if (this.canSetFieldValue(this.sentCcMe, value)) {
      this.sentCcMe = value;
      this.changed();
    }
  }


  /**
   * Gets or sets a value indicating whether the owner of the mailbox must be
   * the only To recipient of incoming
   * messages for the condition or exception
   * to apply.
   */
  public boolean getSentOnlyToMe() {
    return this.sentOnlyToMe;
  }

  public void setSentOnlyToMe(boolean value) {
    if (this.canSetFieldValue(this.sentOnlyToMe, value)) {
      this.sentOnlyToMe = value;
      this.changed();
    }
  }


  /**
   * Gets the e-mail addresses incoming messages must have been sent to for
   * the condition or exception to apply. To disable this predicate, empty
   * the list.
   */
  public EmailAddressCollection getSentToAddresses() {
    return this.sentToAddresses;

  }

  /**
   * Gets or sets a value indicating whether the owner of the mailbox must be
   * a To recipient of incoming messages
   * for the condition or exception to apply.
   */
  public boolean getSentToMe() {
    return this.sentToMe;
  }

  public void setSentToMe(boolean value) {
    if (this.canSetFieldValue(this.sentToMe, value)) {
      this.sentToMe = value;
      this.changed();
    }
  }


  /**
   * Gets or sets a value indicating whether the owner of the mailbox must be
   * either a To or Cc recipient of incoming messages for the condition or
   * exception to apply.
   */
  public boolean getSentToOrCcMe() {
    return this.sentToOrCcMe;
  }

  public void setSentToOrCcMe(boolean value) {
    if (this.canSetFieldValue(this.sentToOrCcMe, value)) {
      this.sentToOrCcMe = value;
      this.changed();
    }
  }


  /**
   * Gets or sets the sensitivity that must be stamped on incoming messages
   * for the condition or exception to apply.
   * To disable this predicate, set it
   * to null.
   */
  public Sensitivity getSensitivity() {
    return this.sensitivity;
  }

  public void setSensitivity(Sensitivity value) {
    if (this.canSetFieldValue(this.sensitivity, value)) {
      this.sensitivity = value;
      this.changed();
    }
  }

  /**
   * Gets the date range within which
   * incoming messages must have been received
   * for the condition or exception to apply.
   * To disable this predicate, set both
   * its Start and End property to null.
   */
  public RulePredicateDateRange getWithinDateRange() {
    return this.withinDateRange;

  }

  /**
   * Gets the minimum and maximum sizes incoming messages must have for the
   * condition or exception to apply. To disable this predicate, set both its
   * MinimumSize and MaximumSize property to null.
   */
  public RulePredicateSizeRange getWithinSizeRange() {
    return this.withinSizeRange;

  }

  /**
   * Tries to read element from XML.
   *
   * @param reader The reader
   * @return True if element was read.
   * @throws Exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader
      reader) throws Exception {

    if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Categories)) {
      this.categories.loadFromXml(reader, reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.ContainsBodyStrings)) {
      this.containsBodyStrings.loadFromXml(reader, reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.ContainsHeaderStrings)) {
      this.containsHeaderStrings.loadFromXml(reader,
          reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.ContainsRecipientStrings)) {
      this.containsRecipientStrings.loadFromXml(reader,
          reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.ContainsSenderStrings)) {
      this.containsSenderStrings.loadFromXml(reader,
          reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.ContainsSubjectOrBodyStrings)) {
      this.containsSubjectOrBodyStrings.loadFromXml(reader,
          reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.ContainsSubjectStrings)) {
      this.containsSubjectStrings.loadFromXml(reader,
          reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.FlaggedForAction)) {
      this.flaggedForAction = reader.
          readElementValue(FlaggedForAction.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.FromAddresses)) {
      this.fromAddresses.loadFromXml(reader, reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.FromConnectedAccounts)) {
      this.fromConnectedAccounts.loadFromXml(reader,
          reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.HasAttachments)) {
      this.hasAttachments = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Importance)) {
      this.importance = reader.readElementValue(Importance.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.IsApprovalRequest)) {
      this.isApprovalRequest = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.IsAutomaticForward)) {
      this.isAutomaticForward = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.IsAutomaticReply)) {
      this.isAutomaticReply = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.IsEncrypted)) {
      this.isEncrypted = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.IsMeetingRequest)) {
      this.isMeetingRequest = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.IsMeetingResponse)) {
      this.isMeetingResponse = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.IsNDR)) {
      this.isNonDeliveryReport = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.IsPermissionControlled)) {
      this.isPermissionControlled = reader.
          readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.IsSigned)) {
      this.isSigned = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.IsVoicemail)) {
      this.isVoicemail = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.IsReadReceipt)) {
      this.isReadReceipt = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.ItemClasses)) {
      this.itemClasses.loadFromXml(reader, reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.MessageClassifications)) {
      this.messageClassifications.loadFromXml(reader,
          reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.NotSentToMe)) {
      this.notSentToMe = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.SentCcMe)) {
      this.sentCcMe = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.SentOnlyToMe)) {
      this.sentOnlyToMe = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.SentToAddresses)) {
      this.sentToAddresses.loadFromXml(reader, reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.SentToMe)) {
      this.sentToMe = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.SentToOrCcMe)) {
      this.sentToOrCcMe = reader.readElementValue(Boolean.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Sensitivity)) {
      this.sensitivity = reader.readElementValue(Sensitivity.class);
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.WithinDateRange)) {
      this.withinDateRange.loadFromXml(reader, reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.WithinSizeRange)) {
      this.withinSizeRange.loadFromXml(reader, reader.getLocalName());
      return true;
    } else {
      return false;
    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer The writer.
   * @throws Exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    if (this.getCategories().getSize() > 0) {
      this.getCategories().writeToXml(writer, XmlElementNames.Categories);
    }

    if (this.getContainsBodyStrings().getSize() > 0) {
      this.getContainsBodyStrings().writeToXml(writer,
          XmlElementNames.ContainsBodyStrings);
    }

    if (this.getContainsHeaderStrings().getSize() > 0) {
      this.getContainsHeaderStrings().writeToXml(writer,
          XmlElementNames.ContainsHeaderStrings);
    }

    if (this.getContainsRecipientStrings().getSize() > 0) {
      this.getContainsRecipientStrings().writeToXml(writer,
          XmlElementNames.ContainsRecipientStrings);
    }

    if (this.getContainsSenderStrings().getSize() > 0) {
      this.getContainsSenderStrings().writeToXml(writer,
          XmlElementNames.ContainsSenderStrings);
    }

    if (this.getContainsSubjectOrBodyStrings().getSize() > 0) {
      this.getContainsSubjectOrBodyStrings().writeToXml(writer,
          XmlElementNames.ContainsSubjectOrBodyStrings);
    }

    if (this.getContainsSubjectStrings().getSize() > 0) {
      this.getContainsSubjectStrings().writeToXml(writer,
          XmlElementNames.ContainsSubjectStrings);
    }

    if (this.getFlaggedForAction() != null) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.FlaggedForAction,
          this.getFlaggedForAction().values());
    }

    if (this.getFromAddresses().getCount() > 0) {
      this.getFromAddresses().writeToXml(writer,
          XmlElementNames.FromAddresses);
    }

    if (this.getFromConnectedAccounts().getSize() > 0) {
      this.getFromConnectedAccounts().writeToXml(writer,
          XmlElementNames.FromConnectedAccounts);
    }

    if (this.getHasAttachments() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.HasAttachments,
          this.getHasAttachments());
    }

    if (this.getImportance() != null) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.Importance,
          this.getImportance());
    }

    if (this.getIsApprovalRequest() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.IsApprovalRequest,
          this.getIsApprovalRequest());
    }

    if (this.getIsAutomaticForward() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.IsAutomaticForward,
          this.getIsAutomaticForward());
    }

    if (this.getIsAutomaticReply() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.IsAutomaticReply,
          this.getIsAutomaticReply());
    }

    if (this.getIsEncrypted() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.IsEncrypted,
          this.getIsEncrypted());
    }

    if (this.getIsMeetingRequest() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.IsMeetingRequest,
          this.getIsMeetingRequest());
    }

    if (this.getIsMeetingResponse() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.IsMeetingResponse,
          this.getIsMeetingResponse());
    }

    if (this.getIsNonDeliveryReport() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.IsNDR,
          this.getIsNonDeliveryReport());
    }

    if (this.getIsPermissionControlled() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.IsPermissionControlled,
          this.getIsPermissionControlled());
    }

    if (this.getIsReadReceipt() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.IsReadReceipt,
          this.getIsReadReceipt());
    }

    if (this.getIsSigned() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.IsSigned,
          this.getIsSigned());
    }

    if (this.getIsVoicemail() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.IsVoicemail,
          this.getIsVoicemail());
    }

    if (this.getItemClasses().getSize() > 0) {
      this.getItemClasses().writeToXml(writer,
          XmlElementNames.ItemClasses);
    }

    if (this.getMessageClassifications().getSize() > 0) {
      this.getMessageClassifications().writeToXml(writer,
          XmlElementNames.MessageClassifications);
    }

    if (this.getNotSentToMe() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.NotSentToMe,
          this.getNotSentToMe());
    }

    if (this.getSentCcMe() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.SentCcMe,
          this.getSentCcMe());
    }

    if (this.getSentOnlyToMe() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.SentOnlyToMe,
          this.getSentOnlyToMe());
    }

    if (this.getSentToAddresses().getCount() > 0) {
      this.getSentToAddresses().writeToXml(writer,
          XmlElementNames.SentToAddresses);
    }

    if (this.getSentToMe() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.SentToMe,
          this.getSentToMe());
    }

    if (this.getSentToOrCcMe() != false) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.SentToOrCcMe,
          this.getSentToOrCcMe());
    }

    if (this.getSensitivity() != null) {
      writer.writeElementValue(
          XmlNamespace.Types,
          XmlElementNames.Sensitivity,
          this.getSensitivity().values());
    }

    if (this.getWithinDateRange().getStart() != null || this.getWithinDateRange().getEnd() != null) {
      this.getWithinDateRange().writeToXml(writer,
          XmlElementNames.WithinDateRange);
    }

    if (this.getWithinSizeRange().getMaximumSize() != null
        || this.getWithinSizeRange().getMinimumSize() != null) {
      this.getWithinSizeRange().writeToXml(writer,
          XmlElementNames.WithinSizeRange);
    }
  }

  /**
   * Validates this instance.
   */
  @Override
  protected void internalValidate() throws Exception {
    super.internalValidate();
    EwsUtilities.validateParam(this.fromAddresses, "FromAddresses");
    EwsUtilities.validateParam(this.sentToAddresses, "SentToAddresses");
    EwsUtilities.validateParam(this.withinDateRange, "WithinDateRange");
    EwsUtilities.validateParam(this.withinSizeRange, "WithinSizeRange");
  }
}
