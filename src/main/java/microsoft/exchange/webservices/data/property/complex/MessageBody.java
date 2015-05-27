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
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlDeserializationException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.stream.XMLStreamException;

/**
 * Represents the body of a message.
 */
public final class MessageBody extends ComplexProperty {

  private static final Log log = LogFactory.getLog(MessageBody.class);

  /**
   * The body type.
   */
  private BodyType bodyType;

  /**
   * The text.
   */
  private String text;

  /**
   * Initializes a new instance.
   */
  public MessageBody() {

  }

  /**
   * Initializes a new instance.
   *
   * @param bodyType The type of the message body's text.
   * @param text     The text of the message body.
   */
  public MessageBody(BodyType bodyType, String text) {
    this();
    this.bodyType = bodyType;
    this.text = text;
  }

  /**
   * Initializes a new instance.
   *
   * @param text The text of the message body, assumed to be HTML.
   */
  public MessageBody(String text) {
    this(BodyType.HTML, text);
  }

  /**
   * Defines an implicit conversation between a string and MessageBody.
   *
   * @param textBody The string to convert to MessageBody, assumed to be HTML.
   * @return A MessageBody initialized with the specified string.
   */
  public static MessageBody getMessageBodyFromText(String textBody) {
    return new MessageBody(BodyType.HTML, textBody);
  }

  /**
   * Defines an implicit conversion of MessageBody into a string.
   *
   * @param messageBody The MessageBody to convert to a string.
   * @return A string containing the text of the MessageBody.
   * @throws Exception the exception
   */
  public static String getStringFromMessageBody(MessageBody messageBody)
      throws Exception {
    EwsUtilities.validateParam(messageBody, "messageBody");
    return messageBody.text;
  }

  /**
   * Reads attribute from XML.
   *
   * @param reader The reader.
   * @throws Exception the exception
   */
  public void readAttributesFromXml(EwsServiceXmlReader reader)
      throws Exception {
    this.bodyType = reader.readAttributeValue(BodyType.class,
        XmlAttributeNames.BodyType);
  }

  /**
   * Reads text value from XML.
   *
   * @param reader the reader
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlDeserializationException the service xml deserialization exception
   */
  @Override
  public void readTextValueFromXml(EwsServiceXmlReader reader)
      throws XMLStreamException, ServiceXmlDeserializationException {
    if (log.isDebugEnabled()) {
       log.debug("Reading text value from XML. BodyType = " + this.getBodyType() +
          ", keepWhiteSpace = " +
          ((this.getBodyType() == BodyType.Text) ? "true." : "false."));
    }
    this.text = reader.readValue(this.getBodyType() == BodyType.Text);
    if (log.isDebugEnabled()) {
       log.debug("Text value read:\n---\n" + this.text + "\n---");
    }
  }

  /**
   * Writes attribute to XML.
   *
   * @param writer The writer.
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  public void writeAttributesToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    writer.writeAttributeValue(XmlAttributeNames.BodyType, this
        .getBodyType());
  }

  /**
   * Writes elements to XML.
   *
   * @param writer The writer.
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws ServiceXmlSerializationException {
    if (null != this.text && !this.text.isEmpty()) {
      writer.writeValue(this.getText(), XmlElementNames.Body);
    }
  }

  /**
   * Gets the type of the message body's text.
   *
   * @return BodyType enum
   */
  public BodyType getBodyType() {
    return this.bodyType;
  }

  /**
   * Sets the type of the message body's text.
   *
   * @param bodyType BodyType enum
   */
  public void setBodyType(BodyType bodyType) {
    if (this.canSetFieldValue(this.bodyType, bodyType)) {
      this.bodyType = bodyType;
      this.changed();
    }
  }

  /**
   * Gets the text of the message body.
   *
   * @return message body text
   */
  private String getText() {
    return this.text;
  }

  /**
   * Sets the text of the message body.
   *
   * @param text message body text
   */
  public void setText(String text) {
    if (this.canSetFieldValue(this.text, text)) {
      this.text = text;
      this.changed();
    }
  }

  /**
   * Returns a String that represents the current Object.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return (this.text == null) ? "" : this.text;
  }
}
