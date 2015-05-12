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

package microsoft.exchange.webservices.data.core;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Represents an xml reader used by the ExchangeService to parse multi-response streams,
 * such as GetStreamingEvents.
 * <p>
 * Necessary because the basic EwsServiceXmlReader does not
 * use normalization (see E14:60369), and in order to turn normalization off, it is
 * necessary to use an XmlTextReader, which does not allow the ConformanceLevel.Auto that
 * a multi-response stream requires.
 * If ever there comes a time we need to deal with multi-response streams with user-generated
 * content, we will need to tackle that parsing problem separately.
 * </p>
 */
public class EwsServiceMultiResponseXmlReader extends EwsServiceXmlReader {

  /**
   * Initializes a new instance of the
   * EwsServiceMultiResponseXmlReader class.
   *
   * @param stream  The stream.
   * @param service The service.
   * @throws Exception
   */
  private EwsServiceMultiResponseXmlReader(InputStream stream,
      ExchangeService service) throws Exception {
    super(stream, service);
  }

  /**
   * Creates a new instance of the EwsServiceMultiResponseXmlReader class.
   *
   * @param stream the stream
   * @param service the service
   * @return an instance of EwsServiceMultiResponseXmlReader wrapped around the input stream
   * @throws Exception on error
   */
  public static EwsServiceMultiResponseXmlReader create(InputStream stream, ExchangeService service) throws Exception {
    return new EwsServiceMultiResponseXmlReader(stream, service);
  }

  /**
   * Creates the XML reader.
   *
   * @param stream The stream
   * @return an XML reader to use
   * @throws XMLStreamException the XML stream exception
   */
  private static XMLEventReader createXmlReader(InputStream stream)
      throws XMLStreamException {

    // E14:240522 The ProhibitDtd property is used to indicate whether XmlReader should process DTDs or not. By default,
    // it will do so. EWS doesn't use DTD references so we want to turn this off. Also, the XmlResolver property is
    // set to an instance of XmlUrlResolver by default. We don't want XmlTextReader to try to resolve this DTD reference
    // so we disable the XmlResolver as well.
    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    InputStreamReader isr = new InputStreamReader(stream);
    BufferedReader in = new BufferedReader(isr);
    return inputFactory.createXMLEventReader(in);
  }


  /**
   * Initializes the XML reader.
   *
   * @param stream The stream. An XML reader to use.
   * @throws Exception on error
   */
  @Override
  protected XMLEventReader initializeXmlReader(InputStream stream)
      throws Exception {
    return createXmlReader(stream);
  }

}
