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

package microsoft.exchange.webservices.data.core.service.schema;

import microsoft.exchange.webservices.data.attribute.Schema;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.PropertyDefinitionFlags;
import microsoft.exchange.webservices.data.core.enumeration.service.TaskMode;
import microsoft.exchange.webservices.data.core.enumeration.service.TaskStatus;
import microsoft.exchange.webservices.data.property.complex.ICreateComplexPropertyDelegate;
import microsoft.exchange.webservices.data.property.complex.StringList;
import microsoft.exchange.webservices.data.property.definition.BoolPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.ComplexPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.DateTimePropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.DoublePropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.GenericPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.IntPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.RecurrencePropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.StringPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.TaskDelegationStatePropertyDefinition;

import java.util.EnumSet;

/**
 * Represents the schema for task item.
 */
@Schema
public class TaskSchema extends ItemSchema {

  /**
   * Field URIs for tasks.
   */
  private static class FieldUris {

    /**
     * The Constant ActualWork.
     */
    public final static String ActualWork = "task:ActualWork";

    /**
     * The Constant AssignedTime.
     */
    public final static String AssignedTime = "task:AssignedTime";

    /**
     * The Constant BillingInformation.
     */
    public final static String BillingInformation =
        "task:BillingInformation";

    /**
     * The Constant ChangeCount.
     */
    public final static String ChangeCount = "task:ChangeCount";

    /**
     * The Constant Companies.
     */
    public final static String Companies = "task:Companies";

    /**
     * The Constant CompleteDate.
     */
    public final static String CompleteDate = "task:CompleteDate";

    /**
     * The Constant Contacts.
     */
    public final static String Contacts = "task:Contacts";

    /**
     * The Constant DelegationState.
     */
    public final static String DelegationState = "task:DelegationState";

    /**
     * The Constant Delegator.
     */
    public final static String Delegator = "task:Delegator";

    /**
     * The Constant DueDate.
     */
    public final static String DueDate = "task:DueDate";

    /**
     * The Constant IsAssignmentEditable.
     */
    public final static String IsAssignmentEditable =
        "task:IsAssignmentEditable";

    /**
     * The Constant IsComplete.
     */
    public final static String IsComplete = "task:IsComplete";

    /**
     * The Constant IsRecurring.
     */
    public final static String IsRecurring = "task:IsRecurring";

    /**
     * The Constant IsTeamTask.
     */
    public final static String IsTeamTask = "task:IsTeamTask";

    /**
     * The Constant Mileage.
     */
    public final static String Mileage = "task:Mileage";

    /**
     * The Constant Owner.
     */
    public final static String Owner = "task:Owner";

    /**
     * The Constant PercentComplete.
     */
    public final static String PercentComplete = "task:PercentComplete";

    /**
     * The Constant Recurrence.
     */
    public final static String Recurrence = "task:Recurrence";

    /**
     * The Constant StartDate.
     */
    public final static String StartDate = "task:StartDate";

    /**
     * The Constant Status.
     */
    public final static String Status = "task:Status";

    /**
     * The Constant StatusDescription.
     */
    public final static String StatusDescription = "task:StatusDescription";

    /**
     * The Constant TotalWork.
     */
    public final static String TotalWork = "task:TotalWork";
  }


  /**
   * Defines the ActualWork property.
   */
  public static final PropertyDefinition ActualWork =
      new IntPropertyDefinition(
          XmlElementNames.ActualWork, FieldUris.ActualWork, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,

          true); // isNullable

  /**
   * Defines the AssignedTime property.
   */
  public static final PropertyDefinition AssignedTime =
      new DateTimePropertyDefinition(
          XmlElementNames.AssignedTime, FieldUris.AssignedTime, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1, true);

  /**
   * Defines the BillingInformation property.
   */
  public static final PropertyDefinition BillingInformation =
      new StringPropertyDefinition(
          XmlElementNames.BillingInformation, FieldUris.BillingInformation,
          EnumSet.of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanDelete,
              PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the ChangeCount property.
   */
  public static final PropertyDefinition ChangeCount =
      new IntPropertyDefinition(
          XmlElementNames.ChangeCount, FieldUris.ChangeCount, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Companies property.
   */
  public static final PropertyDefinition Companies =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.Companies, FieldUris.Companies, EnumSet.of(
          PropertyDefinitionFlags.AutoInstantiateOnRead,
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            public StringList createComplexProperty() {
              return new StringList();
            }
          });

  /**
   * Defines the CompleteDate property.
   */
  public static final PropertyDefinition CompleteDate =
      new DateTimePropertyDefinition(
          XmlElementNames.CompleteDate, FieldUris.CompleteDate, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1, true); // isNullable

  /**
   * Defines the Contacts property.
   */
  public static final PropertyDefinition Contacts =
      new ComplexPropertyDefinition<StringList>(
          StringList.class,
          XmlElementNames.Contacts, FieldUris.Contacts, EnumSet.of(
          PropertyDefinitionFlags.AutoInstantiateOnRead,
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1,
          new ICreateComplexPropertyDelegate<StringList>() {
            public StringList createComplexProperty() {
              return new StringList();
            }
          });

  /**
   * Defines the DelegationState property.
   */
  public static final PropertyDefinition DelegationState =
      new TaskDelegationStatePropertyDefinition(
          XmlElementNames.DelegationState, FieldUris.DelegationState, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Delegator property.
   */
  public static final PropertyDefinition Delegator =
      new StringPropertyDefinition(
          XmlElementNames.Delegator, FieldUris.Delegator, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the DueDate property.
   */
  public static final PropertyDefinition DueDate =
      new DateTimePropertyDefinition(
          XmlElementNames.DueDate, FieldUris.DueDate, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1, true); // isNullable

  /**
   * Defines the Mode property.
   */
  public static final PropertyDefinition Mode =
      new GenericPropertyDefinition<TaskMode>(
          TaskMode.class,
          XmlElementNames.IsAssignmentEditable,
          FieldUris.IsAssignmentEditable, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the IsComplete property.
   */
  public static final PropertyDefinition IsComplete =
      new BoolPropertyDefinition(
          XmlElementNames.IsComplete, FieldUris.IsComplete, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the IsRecurring property.
   */
  public static final PropertyDefinition IsRecurring =
      new BoolPropertyDefinition(
          XmlElementNames.IsRecurring, FieldUris.IsRecurring, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the IsTeamTask property.
   */
  public static final PropertyDefinition IsTeamTask =
      new BoolPropertyDefinition(
          XmlElementNames.IsTeamTask, FieldUris.IsTeamTask, EnumSet
          .of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Mileage property.
   */
  public static final PropertyDefinition Mileage =
      new StringPropertyDefinition(
          XmlElementNames.Mileage, FieldUris.Mileage, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Owner property.
   */
  public static final PropertyDefinition Owner = new StringPropertyDefinition(
      XmlElementNames.Owner, FieldUris.Owner, EnumSet
      .of(PropertyDefinitionFlags.CanFind),
      ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the PercentComplete property.
   */
  public static final PropertyDefinition PercentComplete =
      new DoublePropertyDefinition(
          XmlElementNames.PercentComplete, FieldUris.PercentComplete, EnumSet
          .of(PropertyDefinitionFlags.CanSet,
              PropertyDefinitionFlags.CanUpdate,
              PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the Recurrence property.
   */
  public static final PropertyDefinition Recurrence =
      new RecurrencePropertyDefinition(
          XmlElementNames.Recurrence, FieldUris.Recurrence, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the StartDate property.
   */
  public static final PropertyDefinition StartDate =
      new DateTimePropertyDefinition(
          XmlElementNames.StartDate, FieldUris.StartDate, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1, true); // isNullable

  /**
   * Defines the Status property.
   */
  public static final PropertyDefinition Status =
      new GenericPropertyDefinition<TaskStatus>(
          TaskStatus.class,
          XmlElementNames.Status, FieldUris.Status, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the StatusDescription property.
   */
  public static final PropertyDefinition StatusDescription =
      new StringPropertyDefinition(
          XmlElementNames.StatusDescription, FieldUris.StatusDescription,
          EnumSet.of(PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1);

  /**
   * Defines the TotalWork property.
   */
  public static final PropertyDefinition TotalWork =
      new IntPropertyDefinition(
          XmlElementNames.TotalWork, FieldUris.TotalWork, EnumSet.of(
          PropertyDefinitionFlags.CanSet,
          PropertyDefinitionFlags.CanUpdate,
          PropertyDefinitionFlags.CanDelete,
          PropertyDefinitionFlags.CanFind),
          ExchangeVersion.Exchange2007_SP1, true); // isNullable

  /**
   * This must be declared after the property definitions.
   */
  public static final TaskSchema Instance = new TaskSchema();

  /**
   * This must be declared after the property definitions.
   */
  @Override
  protected void registerProperties() {
    super.registerProperties();

    this.registerProperty(ActualWork);
    this.registerProperty(AssignedTime);
    this.registerProperty(BillingInformation);
    this.registerProperty(ChangeCount);
    this.registerProperty(Companies);
    this.registerProperty(CompleteDate);
    this.registerProperty(Contacts);
    this.registerProperty(DelegationState);
    this.registerProperty(Delegator);
    this.registerProperty(DueDate);
    this.registerProperty(Mode);
    this.registerProperty(IsComplete);
    this.registerProperty(IsRecurring);
    this.registerProperty(IsTeamTask);
    this.registerProperty(Mileage);
    this.registerProperty(Owner);
    this.registerProperty(PercentComplete);
    this.registerProperty(Recurrence);
    this.registerProperty(StartDate);
    this.registerProperty(Status);
    this.registerProperty(StatusDescription);
    this.registerProperty(TotalWork);
  }

  /**
   * Initializes a new instance of the class.
   */
  TaskSchema() {
    super();
  }

}
