/**************************************************************************
 * copyright file="CalendarFolder.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the CalendarFolder.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a folder containing appointments.
 */
@ServiceObjectDefinition(xmlElementName = XmlElementNames.CalendarFolder)
public class CalendarFolder extends Folder{

	/**
	 * Binds to an existing calendar folder and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @param propertySet
	 *            the property set
	 * @return A CalendarFolder instance representing the calendar folder
	 *         corresponding to the specified Id
	 * @throws Exception
	 *             the exception
	 */
	public static CalendarFolder bind(ExchangeService service, FolderId id,
			PropertySet propertySet) throws Exception {
		return service.bindToFolder(CalendarFolder.class, id, propertySet);
	}

	/**
	 * Binds to an existing calendar folder and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param id
	 *            the id
	 * @return A CalendarFolder instance representing the calendar folder
	 *         corresponding to the specified Id
	 * @throws Exception
	 *             the exception
	 */
	public static CalendarFolder bind(ExchangeService service, FolderId id)
			throws Exception {
		return CalendarFolder.bind(service, id, PropertySet
				.getFirstClassProperties());
	}

	/**
	 * Binds to an existing calendar folder and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param name
	 *            the name
	 * @param propertySet
	 *            the property set
	 * @return A CalendarFolder instance representing the calendar folder with
	 *         the specified name.
	 * @throws Exception
	 *             the exception
	 */
	public static CalendarFolder bind(ExchangeService service,
			WellKnownFolderName name, PropertySet
			propertySet) throws Exception {
		return CalendarFolder.bind(service, new FolderId(name), propertySet);
	}

	/**
	 * Binds to an existing calendar folder and loads the specified set of
	 * properties. Calling this method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @param name
	 *            the name
	 * @return A CalendarFolder instance representing the calendar folder with
	 *         the specified name.
	 * @throws Exception
	 *             the exception
	 */
	public static CalendarFolder bind(ExchangeService service,
			WellKnownFolderName name) throws Exception {
		return CalendarFolder.bind(service, new FolderId(name), PropertySet
				.getFirstClassProperties());
	}

	/**
	 * Initializes an unsaved local instance of "CalendarFolder". To bind to an
	 * existing calendar folder, use CalendarFolder.Bind() instead. Calling this
	 * method results in a call to EWS.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception
	 *             the exception
	 */
	public CalendarFolder(ExchangeService service) throws Exception {
		super(service);
	}

	/**
	 * Obtains a list of appointments by searching the contents of this folder
	 * and performing recurrence expansion for recurring appointments. Calling
	 * this method results in a call to EWS.
	 * 
	 * @param view
	 *            the view
	 * @return An object representing the results of the search operation.
	 * @throws Exception
	 *             the exception
	 */
	public FindItemsResults<Appointment> findAppointments(CalendarView view)
			throws Exception {
		EwsUtilities.validateParam(view, "view");

		ServiceResponseCollection<FindItemResponse<Appointment>> responses = 
			this.internalFindItems((SearchFilter)null, view, null
					/* groupBy */);

		return responses.getResponseAtIndex(0).getResults();
	}

	/**
	 * Obtains a list of appointments by searching the contents of this folder
	 * and performing recurrence expansion.
	 * 
	 * @return Earliest Exchange version in which this service object type is
	 *         supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}
}
