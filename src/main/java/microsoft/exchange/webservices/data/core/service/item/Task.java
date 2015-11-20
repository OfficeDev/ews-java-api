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

package microsoft.exchange.webservices.data.core.service.item;

import microsoft.exchange.webservices.data.attribute.Attachable;
import microsoft.exchange.webservices.data.attribute.ServiceObjectDefinition;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.schema.ServiceObjectSchema;
import microsoft.exchange.webservices.data.core.service.schema.TaskSchema;
import microsoft.exchange.webservices.data.core.enumeration.service.calendar.AffectedTaskOccurrence;
import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
import microsoft.exchange.webservices.data.core.enumeration.service.DeleteMode;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.MessageDisposition;
import microsoft.exchange.webservices.data.core.enumeration.property.TaskDelegationState;
import microsoft.exchange.webservices.data.core.enumeration.service.TaskMode;
import microsoft.exchange.webservices.data.core.enumeration.service.TaskStatus;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceResponseException;
import microsoft.exchange.webservices.data.property.complex.ItemAttachment;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.property.complex.StringList;
import microsoft.exchange.webservices.data.property.complex.recurrence.pattern.Recurrence;

import java.util.Date;

/**
 * Represents a Task item. Properties available on tasks are defined in the
 * TaskSchema class.
 */
@Attachable
@ServiceObjectDefinition(xmlElementName = XmlElementNames.Task)
public class Task extends Item {

  private static final double PERCENT_MIN = 0.0D;
  private static final double PERCENT_MAX = 100.0D;


  /**
   * Initializes an unsaved local instance of Task.To bind to an existing
   * task, use Task.Bind() instead.
   *
   * @param service the service
   * @throws Exception the exception
   */
  public Task(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Initializes a new instance of the class.
   *
   * @param parentAttachment the parent attachment
   * @throws Exception the exception
   */
  public Task(ItemAttachment parentAttachment) throws Exception {
    super(parentAttachment);
  }

  /**
   * Binds to an existing task and loads the specified set of property.
   * Calling this method results in a call to EWS.
   *
   * @param service     the service
   * @param id          the id
   * @param propertySet the property set
   * @return A Task instance representing the task corresponding to the
   * specified Id.
   * @throws Exception the exception
   */
  public static Task bind(ExchangeService service, ItemId id,
      PropertySet propertySet) throws Exception {
    return service.bindToItem(Task.class, id, propertySet);
  }

  /**
   * Binds to an existing task and loads its first class property. Calling
   * this method results in a call to EWS.
   *
   * @param service the service
   * @param id      the id
   * @return A Task instance representing the task corresponding to the
   * specified Id.
   * @throws Exception the exception
   */
  public static Task bind(ExchangeService service, ItemId id)
      throws Exception {
    return Task.bind(service, id, PropertySet.getFirstClassProperties());
  }

  /**
   * Internal method to return the schema associated with this type of object.
   *
   * @return The schema associated with this type of object.
   */
  @Override public ServiceObjectSchema getSchema() {
    return TaskSchema.Instance;
  }

  /**
   * Gets the minimum required server version.
   *
   * @return Earliest Exchange version in which this service object type is
   * supported.
   */
  @Override public ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets a value indicating whether a time zone SOAP header should be
   * emitted in a CreateItem or UpdateItem request so this item can be
   * property saved or updated.
   *
   * @param isUpdateOperation the is update operation
   * @return if a time zone SOAP header should be emitted; otherwise, .
   */
  @Override public boolean getIsTimeZoneHeaderRequired(boolean isUpdateOperation) {
    return true;
  }

  /**
   * Deletes the current occurrence of a recurring task. After the current
   * occurrence isdeleted, the task represents the next occurrence. Developers
   * should call Load to retrieve the new property values of the task. Calling
   * this method results in a call to EWS.
   *
   * @param deleteMode the delete mode
   * @throws ServiceLocalException the service local exception
   * @throws Exception             the exception
   */
  public void deleteCurrentOccurrence(DeleteMode deleteMode)
      throws ServiceLocalException, Exception {
    this.internalDelete(deleteMode, null,
        AffectedTaskOccurrence.SpecifiedOccurrenceOnly);
  }

  /**
   * Applies the local changes that have been made to this task. Calling
   * this method results in at least one call to EWS. Mutliple calls to EWS
   * might be made if attachments have been added or removed.
   *
   * @param conflictResolutionMode the conflict resolution mode
   * @return A Task object representing the completed occurrence if the task
   * is recurring and the update marks it as completed; or a Task
   * object representing the current occurrence if the task is
   * recurring and the uypdate changed its recurrence pattern; or null
   * in every other case.
   * @throws ServiceResponseException the service response exception
   * @throws Exception                the exception
   */
  public Task updateTask(ConflictResolutionMode conflictResolutionMode)
      throws ServiceResponseException, Exception {
    return (Task) this.internalUpdate(null /* parentFolder */,
        conflictResolutionMode, MessageDisposition.SaveOnly, null);
  }

  // Properties

  /**
   * Gets the actual amount of time that is spent on the task.
   *
   * @return the actual work
   * @throws ServiceLocalException the service local exception
   */
  public Integer getActualWork() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.ActualWork);
  }

  /**
   * Sets the checks if is read.
   *
   * @param value the new checks if is read
   * @throws Exception the exception
   */
  public void setActualWork(Integer value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        TaskSchema.ActualWork, value);
  }

  /**
   * Gets the date and time the task was assigned.
   *
   * @return the assigned time
   * @throws ServiceLocalException the service local exception
   */
  public Date getAssignedTime() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.AssignedTime);
  }

  /**
   * Gets the billing information of the task.
   *
   * @return the billing information
   * @throws ServiceLocalException the service local exception
   */
  public String getBillingInformation() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.BillingInformation);
  }

  /**
   * Sets the billing information.
   *
   * @param value the new billing information
   * @throws Exception the exception
   */
  public void setBillingInformation(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        TaskSchema.BillingInformation, value);
  }

  /**
   * Gets the number of times the task has changed since it was created.
   *
   * @return the change count
   * @throws ServiceLocalException the service local exception
   */
  public Integer getChangeCount() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.ChangeCount);
  }

  /**
   * Gets a list of companies associated with the task.
   *
   * @return the companies
   * @throws ServiceLocalException the service local exception
   */
  public StringList getCompanies() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.Companies);
  }

  /**
   * Sets the companies.
   *
   * @param value the new companies
   * @throws Exception the exception
   */
  public void setCompanies(StringList value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        TaskSchema.Companies, value);
  }

  /**
   * Gets the date and time on which the task was completed.
   *
   * @return the complete date
   * @throws ServiceLocalException the service local exception
   */
  public Date getCompleteDate() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.CompleteDate);
  }

  /**
   * Sets the complete date.
   *
   * @param value the new complete date
   * @throws Exception the exception
   */
  public void setCompleteDate(Date value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        TaskSchema.CompleteDate, value);
  }

  /**
   * Gets a list of contacts associated with the task.
   *
   * @return the contacts
   * @throws ServiceLocalException the service local exception
   */
  public StringList getContacts() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.Contacts);
  }

  /**
   * Sets the contacts.
   *
   * @param value the new contacts
   * @throws Exception the exception
   */
  public void setContacts(StringList value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        TaskSchema.Contacts, value);
  }

  /**
   * Gets the current delegation state of the task.
   *
   * @return the delegation state
   * @throws ServiceLocalException the service local exception
   */
  public TaskDelegationState getDelegationState()
      throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.DelegationState);
  }

  /**
   * Gets the name of the delegator of this task.
   *
   * @return the delegator
   * @throws ServiceLocalException the service local exception
   */
  public String getDelegator() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.Delegator);
  }

  /**
   * Gets a list of contacts associated with the task.
   *
   * @return the due date
   * @throws ServiceLocalException the service local exception
   */
  public Date getDueDate() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.DueDate);
  }

  /**
   * Sets the due date.
   *
   * @param value the new due date
   * @throws Exception the exception
   */
  public void setDueDate(Date value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        TaskSchema.DueDate, value);
  }

  /**
   * Gets a value indicating the mode of the task.
   *
   * @return the mode
   * @throws ServiceLocalException the service local exception
   */
  public TaskMode getMode() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(TaskSchema.Mode);
  }

  /**
   * Gets a value indicating whether the task is complete.
   *
   * @return the checks if is complete
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsComplete() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.IsComplete);
  }

  /**
   * Gets a value indicating whether the task is recurring.
   *
   * @return the checks if is recurring
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsRecurring() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.IsRecurring);
  }

  /**
   * Gets a value indicating whether the task is a team task.
   *
   * @return the checks if is team task
   * @throws ServiceLocalException the service local exception
   */
  public Boolean getIsTeamTask() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.IsTeamTask);
  }

  /**
   * Gets the mileage of the task.
   *
   * @return the mileage
   * @throws ServiceLocalException the service local exception
   */
  public String getMileage() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.Mileage);
  }

  /**
   * Sets the mileage.
   *
   * @param value the new mileage
   * @throws Exception the exception
   */
  public void setMileage(String value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        TaskSchema.Mileage, value);
  }

  /**
   * Gets the name of the owner of the task.
   *
   * @return the owner
   * @throws ServiceLocalException the service local exception
   */
  public String getOwner() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.Owner);
  }

  /**
   * Gets the completion percentage of the task.
   * PercentComplete must be between
   * 0 and 100.
   *
   * @return the percent complete
   * @throws ServiceLocalException the service local exception
   */
  public Double getPercentComplete() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.PercentComplete);
  }

  /**
   * Sets the completion percentage of the task.
   * PercentComplete must be between
   * 0.0 and 100.0 .
   *
   * @param value the new percent complete
   * @throws Exception the exception
   * @deprecated use Double parameter instead
   */
  @Deprecated
  public void setPercentComplete(String value) throws Exception {
    setPercentComplete(Double.valueOf(value));
  }

  /**
   * Sets the completion percentage of the task.
   * PercentComplete must be between
   * 0.0 and 100.0 .
   *
   * @param value the new percent complete
   * @throws Exception the exception
   */
  public void setPercentComplete(Double value) throws Exception {
    if (value == null || Double.isNaN(value) || value < PERCENT_MIN || value > PERCENT_MAX) {
      throw new IllegalArgumentException(
          String.format("%s must be between %f and %f",
              String.valueOf(value), PERCENT_MIN, PERCENT_MAX));
    }
    this.getPropertyBag().setObjectFromPropertyDefinition(
        TaskSchema.PercentComplete, value);
  }

  /**
   * Gets the recurrence pattern for this task. Available recurrence
   * pattern classes include Recurrence.DailyPattern,
   * Recurrence.MonthlyPattern and Recurrence.YearlyPattern.
   *
   * @return the recurrence
   * @throws ServiceLocalException the service local exception
   */
  public Recurrence getRecurrence() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.Recurrence);
  }

  /**
   * Sets the recurrence.
   *
   * @param value the new recurrence
   * @throws Exception the exception
   */
  public void setRecurrence(Recurrence value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        TaskSchema.Recurrence, value);
  }

  /**
   * Gets the date and time on which the task starts.
   *
   * @return the start date
   * @throws ServiceLocalException the service local exception
   */
  public Date getStartDate() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.StartDate);
  }

  /**
   * Sets the start date.
   *
   * @param value the new start date
   * @throws Exception the exception
   */
  public void setStartDate(Date value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        TaskSchema.StartDate, value);
  }

  /**
   * Gets the status of the task.
   *
   * @return the status
   * @throws ServiceLocalException the service local exception
   */
  public TaskStatus getStatus() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(TaskSchema.Status);
  }

  /**
   * Sets the status.
   *
   * @param value the new status
   * @throws Exception the exception
   */
  public void setStatus(TaskStatus value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        TaskSchema.Status, value);
  }

  /**
   * Gets a string representing the status of the task, localized according to
   * the PreferredCulture property of the ExchangeService object the task is
   * bound to.
   *
   * @return the status description
   * @throws ServiceLocalException the service local exception
   */
  public String getStatusDescription() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.StatusDescription);
  }

  /**
   * Gets the total amount of work spent on the task.
   *
   * @return the total work
   * @throws ServiceLocalException the service local exception
   */
  public Integer getTotalWork() throws ServiceLocalException {
    return getPropertyBag().getObjectFromPropertyDefinition(
        TaskSchema.TotalWork);
  }

  /**
   * Sets the total work.
   *
   * @param value the new total work
   * @throws Exception the exception
   */
  public void setTotalWork(Integer value) throws Exception {
    this.getPropertyBag().setObjectFromPropertyDefinition(
        TaskSchema.TotalWork, value);
  }

  /**
   * Gets the default setting for how to treat affected task occurrences on
   * Delete. <value>AffectedTaskOccurrence.AllOccurrences: All affected Task
   * occurrences will be deleted.</value>
   *
   * @return the default affected task occurrences
   */
  @Override
  protected AffectedTaskOccurrence getDefaultAffectedTaskOccurrences() {
    return AffectedTaskOccurrence.AllOccurrences;
  }

}
