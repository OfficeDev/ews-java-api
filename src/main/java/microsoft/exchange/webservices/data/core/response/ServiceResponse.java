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

package microsoft.exchange.webservices.data.core.response;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.service.schema.ServiceObjectSchema;
import microsoft.exchange.webservices.data.core.enumeration.misc.error.ServiceError;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceResult;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.service.remote.ServiceResponseException;
import microsoft.exchange.webservices.data.misc.SoapFaultDetails;
import microsoft.exchange.webservices.data.property.definition.ExtendedPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.IndexedPropertyDefinition;
import microsoft.exchange.webservices.data.property.definition.PropertyDefinitionBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the standard response to an Exchange Web Services operation.
 */
public class ServiceResponse {

  /**
   * The result.
   */
  private ServiceResult result;

  /**
   * The error code.
   */
  private ServiceError errorCode;

  /**
   * The error message.
   */
  private String errorMessage;

  /**
   * The error details.
   */
  private Map<String, String> errorDetails = new HashMap<String, String>();

  /**
   * The error property.
   */
  private Collection<PropertyDefinitionBase> errorProperties =
      new ArrayList<PropertyDefinitionBase>();

  /**
   * Initializes a new instance.
   */
  public ServiceResponse() {
  }

  /**
   * Initializes a new instance.
   *
   * @param soapFaultDetails The SOAP fault details.
   */
  public ServiceResponse(SoapFaultDetails soapFaultDetails) {
    this.result = ServiceResult.Error;
    this.errorCode = soapFaultDetails.getResponseCode();
    this.errorMessage = soapFaultDetails.getFaultString();
    this.errorDetails = soapFaultDetails.getErrorDetails();
  }

  /**
   * Loads response from XML.
   *
   * @param reader         the reader
   * @param xmlElementName the xml element name
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader, String xmlElementName)
      throws Exception {
    if (!reader.isStartElement(XmlNamespace.Messages, xmlElementName)) {
      reader.readStartElement(XmlNamespace.Messages, xmlElementName);
    }

    this.result = reader.readAttributeValue(ServiceResult.class,
        XmlAttributeNames.ResponseClass);

    if (this.result == ServiceResult.Success ||
        this.result == ServiceResult.Warning) {
      if (this.result == ServiceResult.Warning) {
        this.errorMessage = reader.readElementValue(
            XmlNamespace.Messages, XmlElementNames.MessageText);
      }

      this.errorCode = reader.readElementValue(ServiceError.class,
          XmlNamespace.Messages, XmlElementNames.ResponseCode);

      if (this.result == ServiceResult.Warning) {
        reader.readElementValue(int.class, XmlNamespace.Messages,
            XmlElementNames.DescriptiveLinkKey);
      }

      // Bug E14:212308 -- If batch processing stopped, EWS returns an
      // empty element. Skip over it.
      if (this.getBatchProcessingStopped()) {
        do {
          reader.read();
        } while (!reader.isEndElement(XmlNamespace.Messages,
            xmlElementName));
      } else {

        this.readElementsFromXml(reader);
        //read end tag if it is an empty element.
        if (reader.isEmptyElement()) {
          reader.read();
        }
        reader.readEndElementIfNecessary(XmlNamespace.
            Messages, xmlElementName);
      }
    } else {
      this.errorMessage = reader.readElementValue(XmlNamespace.Messages,
          XmlElementNames.MessageText);
      this.errorCode = reader.readElementValue(ServiceError.class,
          XmlNamespace.Messages, XmlElementNames.ResponseCode);
      reader.readElementValue(int.class, XmlNamespace.Messages,
          XmlElementNames.DescriptiveLinkKey);

      while (!reader.isEndElement(XmlNamespace.
          Messages, xmlElementName)) {
        reader.read();

        if (reader.isStartElement()) {
          if (!this.loadExtraErrorDetailsFromXml(reader, reader.getLocalName())) {
            reader.skipCurrentElement();
          }

        }
      }
    }

    this.mapErrorCodeToErrorMessage();

    this.loaded();
  }

  /**
   * Parses the message XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  protected void parseMessageXml(EwsServiceXmlReader reader)
      throws Exception {
    do {
      reader.read();
      if (reader.isStartElement()) {
        if (reader.getLocalName().equals(XmlElementNames.Value)) {
          this.errorDetails.put(reader
              .readAttributeValue(XmlAttributeNames.Name), reader
              .readElementValue());
        } else if (reader.getLocalName().equals(
            XmlElementNames.FieldURI)) {
          this.errorProperties
              .add(ServiceObjectSchema
                  .findPropertyDefinition(reader.readAttributeValue(XmlAttributeNames.
                                                                        FieldURI)));
        } else if (reader.getLocalName().equals(
            XmlElementNames.IndexedFieldURI)) {
          this.errorProperties
              .add(new IndexedPropertyDefinition(
                  reader
                      .readAttributeValue(XmlAttributeNames.
                          FieldURI),
                  reader
                      .readAttributeValue(XmlAttributeNames.
                          FieldIndex)));
        } else if (reader.getLocalName().equals(
            XmlElementNames.ExtendedFieldURI)) {
          ExtendedPropertyDefinition extendedPropDef =
              new ExtendedPropertyDefinition();
          extendedPropDef.loadFromXml(reader);
          this.errorProperties.add(extendedPropDef);
        }
      }
    } while (!reader.isEndElement(XmlNamespace.Messages,
        XmlElementNames.MessageXml));
  }



  /**
   * Called when the response has been loaded from XML.
   */
  protected void loaded() {
  }

  /**
   * Called after the response has been loaded from XML in order to map error
   * codes to "better" error messages.
   */
  protected void mapErrorCodeToErrorMessage() {
    // Bug E14:69560 -- Use a better error message when an item cannot be
    // updated because its changeKey is old.
    if (this.getErrorCode() == ServiceError.ErrorIrresolvableConflict) {
      this.setErrorMessage(
          "The operation can't be performed because the item is out of date. Reload the item and try again.");
    }
  }

  /**
   * Reads response elements from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  protected void readElementsFromXml(EwsServiceXmlReader reader) throws Exception {
  }

  /**
   * Loads extra error details from XML
   *
   * @param reader         The reader.
   * @param xmlElementName The current element name of the extra error details.
   * @return True if the expected extra details is loaded;
   * False if the element name does not match the expected element.
   */
  protected boolean loadExtraErrorDetailsFromXml(EwsServiceXmlReader reader,
      String xmlElementName) throws Exception {
    if (reader.isStartElement(XmlNamespace.Messages, XmlElementNames.MessageXml) &&
        !reader.isEmptyElement()) {
      this.parseMessageXml(reader);

      return true;
    } else {
      return false;
    }
  }

  /**
   * Throws a ServiceResponseException if this response has its Result
   * property set to Error.
   *
   * @throws ServiceResponseException the service response exception
   */
  public void throwIfNecessary() throws ServiceResponseException {
    this.internalThrowIfNecessary();
  }

  /**
   * Internal method that throws a ServiceResponseException if this response
   * has its Result property set to Error.
   *
   * @throws ServiceResponseException the service response exception
   */
  protected void internalThrowIfNecessary() throws ServiceResponseException {
    if (this.result == ServiceResult.Error) {
      throw new ServiceResponseException(this);
    }
  }

  /**
   * Gets a value indicating whether a batch request stopped processing before
   * the end.
   *
   * @return A value indicating whether a batch request stopped processing
   * before the end.
   */
  protected boolean getBatchProcessingStopped() {
    return (this.result == ServiceResult.Warning)
        && (this.errorCode == ServiceError.ErrorBatchProcessingStopped);
  }

  /**
   * Gets the result associated with this response.
   *
   * @return The result associated with this response.
   */
  public ServiceResult getResult() {
    return result;
  }

  /**
   * Gets the error code associated with this response.
   *
   * @return The error code associated with this response.
   */
  public ServiceError getErrorCode() {
    return errorCode;
  }

  /**
   * Gets a detailed error message associated with the response. If Result
   * is set to Success, ErrorMessage returns null. ErrorMessage is localized
   * according to the PreferredCulture property of the ExchangeService object
   * that was used to call the method that generated the response.
   *
   * @return the error message
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * Sets a detailed error message associated with the response.
   *
   * @param errorMessage The error message associated with the response.
   */
  protected void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * Gets error details associated with the response. If Result is set to
   * Success, ErrorDetailsDictionary returns null. Error details will only
   * available for some error codes. For example, when error code is
   * ErrorRecurrenceHasNoOccurrence, the ErrorDetailsDictionary will contain
   * keys for EffectiveStartDate and EffectiveEndDate.
   *
   * @return The error details dictionary.
   */
  public Map<String, String> getErrorDetails() {
    return errorDetails;
  }

  /**
   * Gets information about property errors associated with the response. If
   * Result is set to Success, ErrorProperties returns null. ErrorProperties
   * is only available for some error codes. For example, when the error code
   * is ErrorInvalidPropertyForOperation, ErrorProperties will contain the
   * definition of the property that was invalid for the request.
   *
   * @return the error property
   */
  public Collection<PropertyDefinitionBase> getErrorProperties() {
    return this.errorProperties;
  }
}
