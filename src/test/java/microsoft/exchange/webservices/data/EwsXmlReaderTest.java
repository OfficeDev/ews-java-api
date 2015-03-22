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

package microsoft.exchange.webservices.data;

import microsoft.exchange.webservices.data.core.EwsXmlReader;
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
