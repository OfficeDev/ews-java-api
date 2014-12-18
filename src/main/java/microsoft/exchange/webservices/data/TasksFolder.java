/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 * Represents a folder containing task items.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.TasksFolder)
public class TasksFolder extends Folder {

  /**
   * Initializes an unsaved local instance of the class.
   *
   * @param service the service
   * @throws Exception the exception
   */
  public TasksFolder(ExchangeService service) throws Exception {
    super(service);
  }

  /**
   * Binds to an existing tasks folder and loads the specified set of
   * properties. Calling this method results in a call to EWS.
   *
   * @param service     the service
   * @param id          the id
   * @param propertySet the property set
   * @return A TasksFolder instance representing the task folder corresponding
   * to the specified Id.
   * @throws Exception the exception
   */
  public static TasksFolder bind(ExchangeService service, FolderId id,
      PropertySet propertySet) throws Exception {
    return service.bindToFolder(TasksFolder.class, id, propertySet);
  }

  /**
   * Binds to an existing tasks folder and loads its first class properties.
   * Calling this method results in a call to EWS.
   *
   * @param service the service
   * @param id      the id
   * @return A TasksFolder instance representing the task folder corresponding
   * to the specified Id.
   * @throws Exception the exception
   */
  public static TasksFolder bind(ExchangeService service, FolderId id)
      throws Exception {
    return TasksFolder.bind(service, id, PropertySet
        .getFirstClassProperties());
  }

  /**
   * Binds to an existing tasks folder and loads specified set of properties.
   * Calling this method results in a call to EWS.
   *
   * @param service     the service
   * @param name        the name
   * @param propertySet the property set
   * @return A TasksFolder instance representing the tasks folder with the
   * specified name.
   * @throws Exception the exception
   */
  public static TasksFolder bind(ExchangeService service,
      WellKnownFolderName name, PropertySet propertySet)
      throws Exception {
    return TasksFolder.bind(service, new FolderId(name), propertySet);
  }

  /**
   * Binds to an existing tasks folder and loads its first class properties.
   * Calling this method results in a call to EWS.
   *
   * @param service the service
   * @param name    the name
   * @return A TasksFolder instance representing the tasks folder with the
   * specified name.
   * @throws Exception the exception
   */
  public static TasksFolder bind(ExchangeService service,
      WellKnownFolderName name) throws Exception {
    return TasksFolder.bind(service, new FolderId(name), PropertySet
        .getFirstClassProperties());
  }

  /**
   * Gets the minimum required server version.
   *
   * @return Earliest Exchange version in which this service object type is
   * supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }
}
