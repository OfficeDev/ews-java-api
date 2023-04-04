package microsoft.exchange.webservices.data.core.service.item;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.InputStream;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.enumeration.service.calendar.AppointmentType;
import microsoft.exchange.webservices.data.security.XmlNodeType;

@RunWith(JUnit4.class)
public class MeetingResponseTest {

	private static MeetingResponse getInput(String folder, String name) throws Exception {

		try (InputStream inputStream = MeetingResponseTest.class
				.getResourceAsStream(String.format("/%s/%s.xml", folder, name))) {
			EwsServiceXmlReader reader = new EwsServiceXmlReader(inputStream, null);

			// read xml preamble
			reader.read(new XmlNodeType(XmlNodeType.START_DOCUMENT));
			reader.readStartElement(XmlNamespace.Soap, XmlElementNames.SOAPEnvelopeElementName);

			// read soap header
			reader.readStartElement(XmlNamespace.Soap, XmlElementNames.SOAPHeaderElementName);
			do {
				reader.read();

				if (reader.isStartElement(XmlNamespace.Types, XmlElementNames.ServerVersionInfo)) {
					reader.readAttributeValue("Version");
				}

				// Ignore anything else inside the SOAP header
			} while (!reader.isEndElement(XmlNamespace.Soap, XmlElementNames.SOAPHeaderElementName));

			// body
			reader.readStartElement(XmlNamespace.Soap, XmlElementNames.SOAPBodyElementName);

			reader.readStartElement(XmlNamespace.Messages, XmlElementNames.GetItemResponse);
			reader.readStartElement(XmlNamespace.Messages, XmlElementNames.ResponseMessages);
			reader.readStartElement(XmlNamespace.Messages, XmlElementNames.GetItemResponseMessage);
			reader.readElementValue(XmlNamespace.Messages, XmlElementNames.ResponseCode);
			reader.readStartElement(XmlNamespace.Messages, XmlElementNames.Items);

			return readMeetingResponse(reader);
		}
	}

	private static MeetingResponse readMeetingResponse(EwsServiceXmlReader reader) throws Exception {
		reader.readStartElement(XmlNamespace.Types, XmlElementNames.MeetingResponse);
		ExchangeService exchangeService = new ExchangeService();
		MeetingResponse meetingResponse = new MeetingResponse(exchangeService);
		meetingResponse.loadFromXml(reader, false);

		return meetingResponse;
	}

	@Test
	public void testLoadXml() throws Exception {
		MeetingResponse meetingResponse = getInput("test-data", "GetMeetingResponse");
		assertThat(meetingResponse.getStart(),
				is(equalTo(new DateTime(2019, 1, 31, 10, 30, 0, DateTimeZone.UTC).toDate())));
		assertThat(meetingResponse.getEnd(),
				is(equalTo(new DateTime(2019, 1, 31, 11, 30, 0, DateTimeZone.UTC).toDate())));
		assertThat(meetingResponse.getProposedStart(),
				is(equalTo(new DateTime(2019, 1, 31, 12, 0, 0, DateTimeZone.UTC).toDate())));
		assertThat(meetingResponse.getProposedEnd(),
				is(equalTo(new DateTime(2019, 1, 31, 14, 30, 0, DateTimeZone.UTC).toDate())));
		assertThat(meetingResponse.getLocation(), is(equalTo("the location")));
		assertThat(meetingResponse.getAppointmentType(), is(equalTo(AppointmentType.Single)));
	}

}
