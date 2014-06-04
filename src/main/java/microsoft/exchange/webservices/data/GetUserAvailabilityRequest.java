/**************************************************************************
 * copyright file="GetUserAvailabilityRequest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the GetUserAvailabilityRequest.java.
 **************************************************************************/

package microsoft.exchange.webservices.data;

/**
 *Represents a GetUserAvailability request.
 * 
 */
final class GetUserAvailabilityRequest extends SimpleServiceRequestBase {

	/** The attendees. */
	private Iterable<AttendeeInfo> attendees;

	/** The time window. */
	private TimeWindow timeWindow;

	/** The requested data. */
	private AvailabilityData requestedData = 
		AvailabilityData.FreeBusyAndSuggestions;

	/** The options. */
	private AvailabilityOptions options;

	/**
	 * Initializes a new instance of the "GetUserAvailabilityRequest" class.
	 * 
	 * @param service
	 *            the service
	 * @throws Exception 
	 */
	protected GetUserAvailabilityRequest(ExchangeService service)
			throws Exception {
		super(service);
	}

	/**
	 * Gets the name of the XML element.
	 * 
	 * @return XML element name.
	 */
	@Override
	protected String getXmlElementName() {
		return XmlElementNames.GetUserAvailabilityRequest;
	}

	/**
	 * Gets a value indicating whether free/busy data is requested.
	 * 
	 * @return true, if is free busy view requested
	 */
	protected boolean isFreeBusyViewRequested() {
		return this.requestedData == AvailabilityData.FreeBusy ||
				 this.requestedData == AvailabilityData.
				FreeBusyAndSuggestions;
	}

	/**
	 * Gets a value indicating whether suggestions are requested.
	 * 
	 * @return true, if is suggestions view requested
	 */
	protected boolean isSuggestionsViewRequested() {
		return this.requestedData == AvailabilityData.Suggestions ||
				 this.requestedData == AvailabilityData.
				FreeBusyAndSuggestions;
	}

	/**
	 * Validate request.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void validate() throws Exception {
		super.validate();

		this.options.validate(this.timeWindow.getDuration());
	}

	/**
	 * Writes XML elements.
	 * 
	 * @param writer
	 *            the writer
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected void writeElementsToXml(EwsServiceXmlWriter writer)
			throws Exception {
		// Only serialize the TimeZone property against an Exchange 2007 SP1
		// server.
		// Against Exchange 2010, the time zone is emitted in the request's SOAP
		// header.
		//if (writer.getService().getRequestedServerVersion() == 
		//ExchangeVersion.Exchange2007_SP1) {
			LegacyAvailabilityTimeZone legacyTimeZone = 
				new LegacyAvailabilityTimeZone();

			legacyTimeZone.writeToXml(writer, XmlElementNames.TimeZone);
		

		writer.writeStartElement(XmlNamespace.Messages,
				XmlElementNames.MailboxDataArray);

		for (AttendeeInfo attendee : this.attendees) {
			attendee.writeToXml(writer);
		}

		writer.writeEndElement(); // MailboxDataArray

		this.options.writeToXml(writer, this);
	}

	/**
	 * Gets the name of the response XML element.
	 * 
	 * @return XML element name
	 */
	@Override
	protected String getResponseXmlElementName() {
		return XmlElementNames.GetUserAvailabilityResponse;
	}

	/**
	 * Parses the response.
	 * 
	 * @param reader
	 *            the reader
	 * @return Response object.
	 * @throws Exception
	 *             the exception
	 */
	@Override
	protected Object parseResponse(EwsServiceXmlReader reader) 
		throws Exception {
		GetUserAvailabilityResults serviceResponse = 
			new GetUserAvailabilityResults();

		if (this.isFreeBusyViewRequested()) {
			serviceResponse
					.setAttendeesAvailability(new ServiceResponseCollection
							<AttendeeAvailability>());

			reader.readStartElement(XmlNamespace.Messages,
					XmlElementNames.FreeBusyResponseArray);

			do {
				reader.read();

				if (reader.isStartElement(XmlNamespace.Messages,
						XmlElementNames.FreeBusyResponse)) {
					AttendeeAvailability freeBusyResponse = 
						new AttendeeAvailability();

					freeBusyResponse.loadFromXml(reader,
							XmlElementNames.ResponseMessage);

					if (freeBusyResponse.getErrorCode().equals(
							ServiceError.NoError)) {
						freeBusyResponse.loadFreeBusyViewFromXml(reader,
								this.options.getRequestedFreeBusyView());
					}

					serviceResponse.getAttendeesAvailability().add(
							freeBusyResponse);
				}
			} while (!reader.isEndElement(XmlNamespace.Messages,
					XmlElementNames.FreeBusyResponseArray));
		}

		if (this.isSuggestionsViewRequested()) {
			serviceResponse.setSuggestionsResponse(new SuggestionsResponse());

			reader.readStartElement(XmlNamespace.Messages,
					XmlElementNames.SuggestionsResponse);

			serviceResponse.getSuggestionsResponse().loadFromXml(reader,
					XmlElementNames.ResponseMessage);

			if (serviceResponse.getSuggestionsResponse().getErrorCode().equals(
					ServiceError.NoError)) {
				serviceResponse.getSuggestionsResponse()
						.loadSuggestedDaysFromXml(reader);
			}

			reader.readEndElement(XmlNamespace.Messages,
					XmlElementNames.SuggestionsResponse);
		}

		return serviceResponse;
	}

	/**
	 * Gets the request version.
	 * 
	 * @return Earliest Exchange version in which this request is supported.
	 */
	@Override
	protected ExchangeVersion getMinimumRequiredServerVersion() {
		return ExchangeVersion.Exchange2007_SP1;
	}

	/**
	 * Executes this request.
	 * 
	 * @return Service response.
	 * @throws Exception
	 *             the exception
	 */
	protected GetUserAvailabilityResults execute() throws Exception {
		return (GetUserAvailabilityResults)this.internalExecute();
	}

	/**
	 * Gets  the attendees.
	 * 
	 * @return the attendees
	 */
	public Iterable<AttendeeInfo> getAttendees() {
		return attendees;
	}

	/**
	 * Sets the attendees.
	 * 
	 * @param attendees
	 *            the new attendees
	 */
	public void setAttendees(Iterable<AttendeeInfo> attendees) {
		this.attendees = attendees;
	}

	/**
	 * Gets the time window in which to retrieve user availability
	 * information.
	 * 
	 * @return the time window
	 */
	public TimeWindow getTimeWindow() {
		return timeWindow;
	}

	/**
	 * Sets the time window.
	 * 
	 * @param timeWindow
	 *            the new time window
	 */
	public void setTimeWindow(TimeWindow timeWindow) {
		this.timeWindow = timeWindow;
	}

	/**
	 * Gets  a value indicating what data is requested (free/busy and/or
	 * suggestions).
	 * 
	 * @return the requested data
	 */
	public AvailabilityData getRequestedData() {
		return requestedData;
	}

	/**
	 * Sets the requested data.
	 * 
	 * @param requestedData
	 *            the new requested data
	 */
	public void setRequestedData(AvailabilityData requestedData) {
		this.requestedData = requestedData;
	}

	/**
	 * Gets an object that allows you to specify options controlling the
	 * information returned by the GetUserAvailability request.
	 * 
	 * @return the options
	 */
	public AvailabilityOptions getOptions() {
		return options;
	}

	/**
	 * Sets the options.
	 * 
	 * @param options
	 *            the new options
	 */
	public void setOptions(AvailabilityOptions options) {
		this.options = options;
	}

}
