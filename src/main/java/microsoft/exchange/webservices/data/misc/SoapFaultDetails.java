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

package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.error.ServiceError;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.security.XmlNodeType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents SoapFault details.
 */
public class SoapFaultDetails {

  private static final Log LOG = LogFactory.getLog(SoapFaultDetails.class);

  /**
   * The fault code.
   */
  private String faultCode;

  /**
   * The fault string.
   */
  private String faultString;

  /**
   * The fault actor.
   */
  private String faultActor;

  /**
   * The response code.
   */
  private ServiceError responseCode = ServiceError.ErrorInternalServerError;

  /**
   * The message.
   */
  private String message;

  /**
   * The error code.
   */
  private ServiceError errorCode = ServiceError.NoError;

  /**
   * The exception type.
   */
  private String exceptionType;

  /**
   * The line number.
   */
  private int lineNumber;

  /**
   * The position within line.
   */
  private int positionWithinLine;

  /**
   * Dictionary of key/value pairs from the MessageXml node in the fault.
   * Usually empty but there are a few cases where SOAP faults may include
   * MessageXml details (e.g. CASOverBudgetException includes BackoffTime
   * value).
   */
  private Map<String, String> errorDetails = new HashMap<String, String>();

  /**
   * Parses the.
   *
   * @param reader        the reader
   * @param soapNamespace the soap namespace
   * @return the soap fault details
   * @throws Exception the exception
   */
  public static SoapFaultDetails parse(EwsXmlReader reader, XmlNamespace soapNamespace) throws Exception {
    SoapFaultDetails soapFaultDetails = new SoapFaultDetails();

    do {
      reader.read();
      if (reader.getNodeType().equals(
          new XmlNodeType(XmlNodeType.START_ELEMENT))) {
        String localName = reader.getLocalName();
        if (localName.equals(XmlElementNames.SOAPFaultCodeElementName)) {
          soapFaultDetails.setFaultCode(reader.readElementValue());
        } else if (localName
            .equals(XmlElementNames.SOAPFaultStringElementName)) {
          soapFaultDetails.setFaultString(reader.readElementValue());
        } else if (localName
            .equals(XmlElementNames.SOAPFaultActorElementName)) {
          soapFaultDetails.setFaultActor(reader.readElementValue());
        } else if (localName
            .equals(XmlElementNames.SOAPDetailElementName)) {
          soapFaultDetails.parseDetailNode(reader);
        }
      }
    } while (!reader.isEndElement(soapNamespace,
        XmlElementNames.SOAPFaultElementName));

    return soapFaultDetails;
  }

  /**
   * Parses the detail node.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  private void parseDetailNode(EwsXmlReader reader) throws Exception {
    do {
      reader.read();
      if (reader.getNodeType().equals(
          new XmlNodeType(XmlNodeType.START_ELEMENT))) {
        String localName = reader.getLocalName();
        if (localName
            .equals(XmlElementNames.EwsResponseCodeElementName)) {
          try {
            this.setResponseCode(reader
                .readElementValue(ServiceError.class));
          } catch (Exception e) {
            LOG.error(e);

            // ServiceError couldn't be mapped to enum value, treat
            // as an ISE
            this
                .setResponseCode(ServiceError.
                    ErrorInternalServerError);
          }

        } else if (localName
            .equals(XmlElementNames.EwsMessageElementName)) {
          this.setMessage(reader.readElementValue());
        } else if (localName.equals(XmlElementNames.EwsLineElementName)) {
          this.setLineNumber(reader.readElementValue(Integer.class));
        } else if (localName
            .equals(XmlElementNames.EwsPositionElementName)) {
          this.setPositionWithinLine(reader
              .readElementValue(Integer.class));
        } else if (localName
            .equals(XmlElementNames.EwsErrorCodeElementName)) {
          try {
            this.setErrorCode(reader
                .readElementValue(ServiceError.class));
          } catch (Exception e) {
            LOG.error(e);

            // ServiceError couldn't be mapped to enum value, treat
            // as an ISE
            this
                .setErrorCode(ServiceError.
                    ErrorInternalServerError);
          }

        } else if (localName
            .equals(XmlElementNames.EwsExceptionTypeElementName)) {
          try {
            this.setExceptionType(reader.readElementValue());
          } catch (Exception e) {
            LOG.error(e);
            this.setExceptionType(null);
          }
        } else if (localName.equals(XmlElementNames.MessageXml)) {
          this.parseMessageXml(reader);
        }
      }
    } while (!reader.isEndElement(XmlNamespace.NotSpecified,
        XmlElementNames.SOAPDetailElementName));
  }

  /**
   * Parses the message xml.
   *
   * @param reader the reader
   * @throws Exception                          the exception
   * @throws ServiceXmlDeserializationException the service xml deserialization exception
   */
  private void parseMessageXml(EwsXmlReader reader) throws Exception, ServiceXmlDeserializationException, Exception {
    // E14:172881: E12 and E14 return the MessageXml element in different
    // namespaces (types namespace for E12, errors namespace in E14). To
    // avoid this problem, the parser will match the namespace from the
    // start and end elements.
    XmlNamespace elementNS = EwsUtilities.getNamespaceFromUri(reader.getNamespaceUri());

    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if (reader.isStartElement() && !reader.isEmptyElement()) {
          String localName = reader.getLocalName();
          if (localName.equals(XmlElementNames.Value)) {
            this.errorDetails.put(reader
                    .readAttributeValue(XmlAttributeNames.Name),
                reader.readElementValue());
          }
        }
      } while (!reader
          .isEndElement(elementNS, XmlElementNames.MessageXml));
    } else {
      reader.read();
    }

  }

  /**
   * Gets the fault code.
   *
   * @return the fault code
   */
  protected String getFaultCode() {
    return faultCode;
  }

  /**
   * Sets the fault code.
   *
   * @param faultCode the new fault code
   */
  protected void setFaultCode(String faultCode) {
    this.faultCode = faultCode;
  }

  /**
   * Gets the fault string.
   *
   * @return the fault string
   */
  public String getFaultString() {
    return faultString;
  }

  /**
   * Sets the fault string.
   *
   * @param faultString the new fault string
   */
  protected void setFaultString(String faultString) {
    this.faultString = faultString;
  }

  /**
   * Gets the fault actor.
   *
   * @return the fault actor
   */
  protected String getFaultActor() {
    return faultActor;
  }

  /**
   * Sets the fault actor.
   *
   * @param faultActor the new fault actor
   */
  protected void setFaultActor(String faultActor) {
    this.faultActor = faultActor;
  }

  /**
   * Gets the response code.
   *
   * @return the response code
   */
  public ServiceError getResponseCode() {
    return responseCode;
  }

  /**
   * Sets the response code.
   *
   * @param responseCode the new response code
   */
  protected void setResponseCode(ServiceError responseCode) {
    this.responseCode = responseCode;
  }

  /**
   * Gets the message.
   *
   * @return the message
   */
  protected String getMessage() {
    return message;
  }

  /**
   * Sets the message.
   *
   * @param message the new message
   */
  protected void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets the error code.
   *
   * @return the error code
   */
  protected ServiceError getErrorCode() {
    return errorCode;
  }

  /**
   * Sets the error code.
   *
   * @param errorCode the new error code
   */
  protected void setErrorCode(ServiceError errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * Gets the exception type.
   *
   * @return the exception type
   */
  protected String getExceptionType() {
    return exceptionType;
  }

  /**
   * Sets the exception type.
   *
   * @param exceptionType the new exception type
   */
  protected void setExceptionType(String exceptionType) {
    this.exceptionType = exceptionType;
  }

  /**
   * Gets the line number.
   *
   * @return the line number
   */
  protected int getLineNumber() {
    return lineNumber;
  }

  /**
   * Sets the line number.
   *
   * @param lineNumber the new line number
   */
  protected void setLineNumber(int lineNumber) {
    this.lineNumber = lineNumber;
  }

  /**
   * Gets the position within line.
   *
   * @return the position within line
   */
  protected int getPositionWithinLine() {
    return positionWithinLine;
  }

  /**
   * Sets the position within line.
   *
   * @param positionWithinLine the new position within line
   */
  protected void setPositionWithinLine(int positionWithinLine) {
    this.positionWithinLine = positionWithinLine;
  }

  /**
   * Gets the error details.
   *
   * @return the error details
   */
  public Map<String, String> getErrorDetails() {
    return errorDetails;
  }

  /**
   * Sets the error details.
   *
   * @param errorDetails the error details
   */
  protected void setErrorDetails(Map<String, String> errorDetails) {
    this.errorDetails = errorDetails;
  }
}
