/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.EnumSet;

/**
 * Represents a task delegation property definition.
 */
final class TaskDelegationStatePropertyDefinition extends
    GenericPropertyDefinition<TaskDelegationState> {

  /**
   * The No match.
   */
  private static final String NoMatch = "NoMatch";

  /**
   * The Own new.
   */
  private static final String OwnNew = "OwnNew";

  /**
   * The Owned.
   */
  private static final String Owned = "Owned";

  /**
   * The Accepted.
   */
  private static final String Accepted = "Accepted";

  /**
   * Initializes a new instance of the "TaskDelegationStatePropertyDefinition" class.
   *
   * @param xmlElementName Name of the XML element.
   * @param uri            The URI.
   * @param flags          The flags.
   * @param version        The version.
   */
  protected TaskDelegationStatePropertyDefinition(String xmlElementName,
      String uri, EnumSet<PropertyDefinitionFlags> flags,
      ExchangeVersion version) {
    super(TaskDelegationState.class, xmlElementName, uri, flags, version);
  }

  /**
   * The Enum Status.
   */
  public enum Status {

    /**
     * The No match.
     */
    NoMatch,
    /**
     * The Own new.
     */
    OwnNew,
    /**
     * The Owned.
     */
    Owned,
    /**
     * The Accepted.
     */
    Accepted;
  }

  /**
   * Parses the specified value.
   *
   * @param value The value.
   * @return Typed value.
   */
  @Override
  protected Object parse(String value) {
    switch (Status.valueOf(value)) {
      case NoMatch:
        return TaskDelegationState.NoDelegation;
      case OwnNew:
        return TaskDelegationState.Unknown;
      case Owned:
        return TaskDelegationState.Accepted;
      case Accepted:
        return TaskDelegationState.Declined;
      default:
        EwsUtilities.EwsAssert(false,
            "TaskDelegationStatePropertyDefinition.Parse", String
                .format("TaskDelegationStatePropertyDefinition." +
                    "Parse():" +
                    " value %s cannot be handled.", value));

        return null; // To keep the compiler happy
    }
  }

  /**
   * Convert instance to string.
   *
   * @param value The value.
   * @return String representation of property value.
   */
  @Override
  protected String toString(Object value) {
    TaskDelegationState taskDelegationState = (TaskDelegationState) value;

    if (taskDelegationState.equals(TaskDelegationState.NoDelegation)) {
      return NoMatch;
    } else if (taskDelegationState.equals(TaskDelegationState.Unknown)) {
      return OwnNew;
    } else if (taskDelegationState.equals(TaskDelegationState.Accepted)) {
      return Owned;
    }
    if (taskDelegationState.equals(TaskDelegationState.Declined)) {
      return Accepted;
    } else {
      EwsUtilities.EwsAssert(false,
          "TaskDelegationStatePropertyDefinition.ToString",
          "Invalid TaskDelegationState value.");
      return null; // To keep the compiler happy
    }

  }

}
