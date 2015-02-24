package microsoft.exchange.webservices.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.doReturn;

public class EwsXmlReaderTest {

  @Mock(name="presentEvent") XMLEvent presentEvent;
  @Mock(name="xmlReader") XMLEventReader xmlReader;
  @InjectMocks EwsXmlReader impl;
  @Mock Characters character;

  @Before
  public void setUp() throws Exception {
    impl = new EwsXmlReader(new ByteArrayInputStream(("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        + "<test></test>").getBytes("UTF-8")));
    MockitoAnnotations.initMocks(this);

  }

  @Test
  public void testReadValueWhenCharacterDataIsNull() throws Exception {

    doReturn(false).when(presentEvent).isStartElement();
    doReturn(XmlNodeType.CHARACTERS).when(presentEvent).getEventType();
    doReturn(true).when(presentEvent).isCharacters();
    doReturn(character).when(presentEvent).asCharacters();

    //next event, then end event, then no more event
    doReturn(true).doReturn(true).doReturn(false).when(xmlReader).hasNext();
    Characters nextEvent = Mockito.mock(Characters.class);
    doReturn(true).when(nextEvent).isCharacters();
    doReturn(XmlNodeType.CHARACTERS).when(nextEvent).getEventType();
    XMLEvent endEvent = Mockito.mock(XMLEvent.class);
    doReturn(nextEvent).doReturn(endEvent).when(xmlReader).nextEvent();
    doReturn(true).when(endEvent).isEndElement();

    impl.readValue(true);  //must not throw npe even if character.getData() is null

    Assert.assertNull(character.getData());
  }

  @Test
  public void testReadValueWhenCharacterDataIsNullForStartElement() throws Exception {

    doReturn(true).when(presentEvent).isStartElement();
    doReturn(XmlNodeType.CHARACTERS).when(presentEvent).getEventType();
    doReturn(true).when(presentEvent).isCharacters();
    doReturn(character).when(presentEvent).asCharacters();

    //next event, then end event, then no more event
    doReturn(true).doReturn(true).doReturn(false).when(xmlReader).hasNext();
    Characters nextEvent = Mockito.mock(Characters.class);
    doReturn(true).when(nextEvent).isCharacters();
    doReturn(XmlNodeType.CHARACTERS).when(nextEvent).getEventType();
    XMLEvent endEvent = Mockito.mock(XMLEvent.class);
    doReturn(nextEvent).doReturn(endEvent).when(xmlReader).nextEvent();
    doReturn(true).when(endEvent).isEndElement();

    impl.readValue(true);  //must not throw npe even if character.getData() is null

    Assert.assertNull(character.getData());
  }

}