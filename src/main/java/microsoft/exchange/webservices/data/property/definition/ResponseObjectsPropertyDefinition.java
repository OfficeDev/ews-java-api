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

package microsoft.exchange.webservices.data.property.definition;

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.PropertyBag;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.service.ResponseActions;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;

import java.util.EnumSet;

/**
 * Represents response object property defintion.
 */
public class ResponseObjectsPropertyDefinition extends PropertyDefinition {

  /**
   * Initializes a new instance of the ResponseObjectsPropertyDefinition
   * class.
   *
   * @param xmlElementName the xml element name
   * @param uri            the uri
   * @param version        the version
   */
  public ResponseObjectsPropertyDefinition(String xmlElementName, String uri, ExchangeVersion version) {
    super(xmlElementName, uri, version);

  }

  /**
   * Loads from XML.
   *
   * @param reader      the reader
   * @param propertyBag the property bag
   * @throws Exception the exception
   */
  public final void loadPropertyValueFromXml(EwsServiceXmlReader reader, PropertyBag propertyBag) throws Exception {
    EnumSet<ResponseActions> value = EnumSet.noneOf(ResponseActions.class);
    value.add(ResponseActions.None);

    reader.ensureCurrentNodeIsStartElement(XmlNamespace.Types, this
        .getXmlElement());

    if (!reader.isEmptyElement()) {
      do {
        reader.read();

        if (reader.isStartElement()) {

          if (reader.getLocalName()
              .equals(XmlElementNames.AcceptItem)) {

            value.add(ResponseActions.Accept);
          } else if (reader.getLocalName().equals(
              XmlElementNames.TentativelyAcceptItem)) {

            value.add(ResponseActions.TentativelyAccept);
          } else if (reader.getLocalName().equals(
              XmlElementNames.DeclineItem)) {

            value.add(ResponseActions.Decline);
          } else if (reader.getLocalName().equals(
              XmlElementNames.ReplyToItem)) {

            value.add(ResponseActions.Reply);
          } else if (reader.getLocalName().equals(
              XmlElementNames.ForwardItem)) {

            value.add(ResponseActions.Forward);
          } else if (reader.getLocalName().equals(
              XmlElementNames.ReplyAllToItem)) {

            value.add(ResponseActions.ReplyAll);
          } else if (reader.getLocalName().equals(
              XmlElementNames.CancelCalendarItem)) {

            value.add(ResponseActions.Cancel);
          } else if (reader.getLocalName().equals(
              XmlElementNames.RemoveItem)) {

            value.add(ResponseActions.RemoveFromCalendar);
          } else if (reader.getLocalName().equals(
              XmlElementNames.SuppressReadReceipt)) {

            value.add(ResponseActions.SuppressReadReceipt);
          } else if (reader.getLocalName().equals(
              XmlElementNames.PostReplyItem)) {

            value.add(ResponseActions.PostReply);
          }
        }

      } while (!reader.isEndElement(XmlNamespace.Types, this
          .getXmlElement()));
    } else {
      reader.read();
    }

    propertyBag.setObjectFromPropertyDefinition(this, value);
  }

  /**
   * Writes to XML.
   *
   * @param writer            the writer
   * @param propertyBag       the property bag
   * @param isUpdateOperation the is update operation
   */
  public void writePropertyValueToXml(EwsServiceXmlWriter writer, PropertyBag propertyBag,
      boolean isUpdateOperation) {
    // ResponseObjects is a read-only property, no need to implement this.
  }

  /**
   * Gets a value indicating whether this property
   * definition is for a nullable type (ref, int?, bool?...).
   */
  @Override public boolean isNullable() {
    return false;
  }

  /**
   * Gets the property type.
   */
  @Override
  public Class<ResponseActions> getType() {
    return ResponseActions.class;
  }
}
