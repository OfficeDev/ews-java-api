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

package microsoft.exchange.webservices.data.property.complex;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.stream.XMLStreamException;

import java.util.Date;

/**
 * Encapsulates information on the deleted occurrence of a recurring
 * appointment.
 */
public class DeletedOccurrenceInfo extends ComplexProperty {

  private static final Log LOG = LogFactory.getLog(DeletedOccurrenceInfo.class);

  /**
   * The original start date and time of the deleted occurrence. The EWS
   * schema contains a Start property for deleted occurrences but it's really
   * the original start date and time of the occurrence.
   */
  private Date originalStart;

  /**
   * Initializes a new instance of the "DeletedOccurrenceInfo" class.
   */
  protected DeletedOccurrenceInfo() {
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader The reader.
   * @return True if element was read.
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equalsIgnoreCase(XmlElementNames.Start)) {
      try {
        this.originalStart = reader.readElementValueAsDateTime();
      } catch (ServiceXmlDeserializationException e) {
        LOG.error(e);
      } catch (XMLStreamException e) {
        LOG.error(e);
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets the original start date and time of the deleted occurrence.
   *
   * @return the original start
   */
  public Date getOriginalStart() {
    return this.originalStart;
  }

}
