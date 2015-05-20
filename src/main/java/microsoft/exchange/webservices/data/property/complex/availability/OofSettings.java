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

package microsoft.exchange.webservices.data.property.complex.availability;

import microsoft.exchange.webservices.data.ISelfValidate;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.property.OofExternalAudience;
import microsoft.exchange.webservices.data.core.enumeration.property.OofState;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceXmlSerializationException;
import microsoft.exchange.webservices.data.misc.availability.OofReply;
import microsoft.exchange.webservices.data.misc.availability.TimeWindow;
import microsoft.exchange.webservices.data.property.complex.ComplexProperty;

import javax.xml.stream.XMLStreamException;

/**
 * Represents a user's Out of Office (OOF) settings.
 */
public final class OofSettings extends ComplexProperty implements ISelfValidate {

  /**
   * The state.
   */
  private OofState state = OofState.Disabled;

  /**
   * The external audience.
   */
  private OofExternalAudience externalAudience = OofExternalAudience.None;

  /**
   * The allow external oof.
   */
  private OofExternalAudience allowExternalOof = OofExternalAudience.None;

  /**
   * The duration.
   */
  private TimeWindow duration;

  /**
   * The internal reply.
   */
  private OofReply internalReply;

  /**
   * The external reply.
   */
  private OofReply externalReply;

  /**
   * Serializes an OofReply. Emits an empty OofReply in case the one passed in
   * is null.
   *
   * @param oofReply       The oof reply
   * @param writer         The writer
   * @param xmlElementName Name of the xml element
   * @throws XMLStreamException the XML stream exception
   * @throws ServiceXmlSerializationException the service xml serialization exception
   */
  private void serializeOofReply(OofReply oofReply,
      EwsServiceXmlWriter writer, String xmlElementName)
      throws XMLStreamException, ServiceXmlSerializationException {
    if (oofReply != null) {
      oofReply.writeToXml(writer, xmlElementName);
    } else {
      OofReply.writeEmptyReplyToXml(writer, xmlElementName);
    }
  }

  /**
   * Initializes a new instance of OofSettings.
   */
  public OofSettings()

  {
    super();
  }

  /**
   * Tries to read element from XML.
   *
   * @param reader The reader
   * @return True if appropriate element was read.
   * @throws Exception the exception
   */
  @Override
  public boolean tryReadElementFromXml(EwsServiceXmlReader reader)
      throws Exception {
    if (reader.getLocalName().equals(XmlElementNames.OofState)) {
      this.state = reader.readValue(OofState.class);
      return true;
    } else if (reader.getLocalName().equals(
        XmlElementNames.ExternalAudience)) {
      this.externalAudience = reader.readValue(OofExternalAudience.class);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.Duration)) {
      this.duration = new TimeWindow();
      this.duration.loadFromXml(reader);
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.InternalReply)) {
      this.internalReply = new OofReply();
      this.internalReply.loadFromXml(reader, reader.getLocalName());
      return true;
    } else if (reader.getLocalName().equals(XmlElementNames.ExternalReply)) {
      this.externalReply = new OofReply();
      this.externalReply.loadFromXml(reader, reader.getLocalName());
      return true;
    } else {
      return false;
    }
  }

  /**
   * Writes elements to XML.
   *
   * @param writer The writer
   * @throws Exception the exception
   */
  @Override
  public void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    super.writeElementsToXml(writer);

    writer.writeElementValue(XmlNamespace.Types, XmlElementNames.OofState,
        this.getState());

    writer.writeElementValue(XmlNamespace.Types,
        XmlElementNames.ExternalAudience, this.getExternalAudience());

    if (this.getDuration() != null && this.getState() == OofState.Scheduled) {
      this.getDuration().writeToXml(writer, XmlElementNames.Duration);
    }

    this.serializeOofReply(this.getInternalReply(), writer,
        XmlElementNames.InternalReply);
    this.serializeOofReply(this.getExternalReply(), writer,
        XmlElementNames.ExternalReply);
  }

  /**
   * Gets the user's OOF state.
   *
   * @return The user's OOF state.
   */
  public OofState getState() {
    return state;
  }

  /**
   * Sets the user's OOF state.
   *
   * @param state the new state
   */
  public void setState(OofState state) {
    this.state = state;
  }

  /**
   * Gets a value indicating who should receive external OOF messages.
   *
   * @return the external audience
   */
  public OofExternalAudience getExternalAudience() {
    return externalAudience;
  }

  /**
   * Sets a value indicating who should receive external OOF messages.
   *
   * @param externalAudience the new external audience
   */
  public void setExternalAudience(OofExternalAudience externalAudience) {
    this.externalAudience = externalAudience;
  }

  /**
   * Gets the duration of the OOF status when State is set to
   * OofState.Scheduled.
   *
   * @return the duration
   */
  public TimeWindow getDuration() {
    return duration;
  }

  /**
   * Sets the duration of the OOF status when State is set to
   * OofState.Scheduled.
   *
   * @param duration the new duration
   */
  public void setDuration(TimeWindow duration) {
    this.duration = duration;
  }

  /**
   * Gets the OOF response sent other users in the user's domain or trusted
   * domain.
   *
   * @return the internal reply
   */
  public OofReply getInternalReply() {
    return internalReply;
  }

  /**
   * Sets the OOF response sent other users in the user's domain or trusted
   * domain.
   *
   * @param internalReply the new internal reply
   */
  public void setInternalReply(OofReply internalReply) {
    this.internalReply = internalReply;
  }

  /**
   * Gets the OOF response sent to addresses outside the user's domain or
   * trusted domain.
   *
   * @return the external reply
   */
  public OofReply getExternalReply() {
    return externalReply;
  }

  /**
   * Sets the OOF response sent to addresses outside the user's domain or
   * trusted domain.
   *
   * @param externalReply the new external reply
   */
  public void setExternalReply(OofReply externalReply) {
    this.externalReply = externalReply;
  }

  /**
   * Gets a value indicating the authorized external OOF notification.
   *
   * @return the allow external oof
   */
  public OofExternalAudience getAllowExternalOof() {
    return allowExternalOof;
  }

  /**
   * Sets a value indicating the authorized external OOF notification.
   *
   * @param allowExternalOof the new allow external oof
   */
  public void setAllowExternalOof(OofExternalAudience allowExternalOof) {
    this.allowExternalOof = allowExternalOof;
  }

  /**
   * Validates this instance.
   *
   * @throws Exception the exception
   */
  @Override
  public void validate() throws Exception {
    if (this.getState() == OofState.Scheduled) {
      if (this.getDuration() == null) {
        throw new ArgumentException("Duration must be specified when State is equal to Scheduled.");
      }

      EwsUtilities.validateParam(this.getDuration(), "Duration");
    }
  }

}
