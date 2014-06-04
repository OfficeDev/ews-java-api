/**************************************************************************
 * copyright file="TasksFolder.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the TasksFolder.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a folder containing task items.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.TasksFolder)
public class TasksFolder extends Folder{

	/**
	 * Initializes an unsaved local instance of the class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception
	 *             the exception
	 */
	public TasksFolder(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Binds to an existing tasks folder and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @param propertySet
	 *            the property set
	 * @return A TasksFolder instance representing the task folder corresponding
	 *         to the specified Id.
	 * @throws Exception
	 *             the exception
	 */
	public static TasksFolder bind(ExchangeService service, FolderId id,
			PropertySet propertySet) throws Exception {
		return service.bindToFolder(TasksFolder.class, id, propertySet);
	}

	/**
	 * Binds to an existing tasks folder and loads its first class properties.
	 * Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @return A TasksFolder instance representing the task folder corresponding
	 *         to the specified Id.
	 * @throws Exception
	 *             the exception
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
	 * @param service
	 *            the service
	 * @param name
	 *            the name
	 * @param propertySet
	 *            the property set
	 * @return A TasksFolder instance representing the tasks folder with the
	 *         specified name.
	 * @throws Exception
	 *             the exception
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
	 * @param service
	 *            the service
	 * @param name
	 *            the name
	 * @return A TasksFolder instance representing the tasks folder with the
	 *         specified name.
	 * @throws Exception
	 *             the exception
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
	 *         supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}
}
