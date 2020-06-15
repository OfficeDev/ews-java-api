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

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.xml.internal.stream.XMLInputFactoryImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

  private static final Log LOG = LogFactory.getLog(EwsServiceMultiResponseXmlReader.class);


  /**
   * Initializes a new instance of the
   * EwsServiceMultiResponseXmlReader class.
   *
   * @param stream  The stream.
   * @param service The service.
   * @throws Exception
   */
  private EwsServiceMultiResponseXmlReader(InputStream stream, ExchangeService service) throws Exception {
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
  private static XMLEventReader createXmlReader(InputStream stream, boolean ignoreErrors)
      throws XMLStreamException {

    XMLInputFactory inputFactory = new XMLInputFactoryImpl();
    inputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);

    InputStreamReader isr = new InputStreamReader(stream);
    BufferedReader in = new BufferedReader(isr);
    XMLEventReader reader = inputFactory.createXMLEventReader(in);
    if (ignoreErrors) {
      //continue after fatal error to prevent "invalid character reference"
      XMLErrorReporter
          errorReporter =
          (XMLErrorReporter) reader
              .getProperty(Constants.XERCES_PROPERTY_PREFIX + Constants.ERROR_REPORTER_PROPERTY);

      if (errorReporter != null) {
        errorReporter
            .setFeature(Constants.XERCES_FEATURE_PREFIX + Constants.CONTINUE_AFTER_FATAL_ERROR_FEATURE, true);
      } else {
        LOG.warn(
            "Failed to configure ignore errors for the XML Reader. Expected the Xerces parser implementation.");
      }
    }

    return reader;
  }


  /**
   * Initializes the XML reader.
   *
   * @param stream The stream. An XML reader to use.
   * @throws Exception on error
   */
  @Override
  protected XMLEventReader initializeXmlReader(InputStream stream, boolean ignoreErrors) throws Exception {
    return createXmlReader(stream, true);
  }

}
