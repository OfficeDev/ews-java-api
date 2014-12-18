/**************************************************************************
 Exchange Web Services Java API
 Copyright (c) Microsoft Corporation
 All rights reserved.
 MIT License
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the ""Software""), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **************************************************************************/

package microsoft.exchange.webservices.data;

import java.util.ArrayList;

/**
 * Represents a user setting that is a collection of protocol connection.
 */
public final class ProtocolConnectionCollection {

  /**
   * The connections.
   */
  private ArrayList<ProtocolConnection> connections;

  /**
   * Initializes a new instance of the <see
   * cref="ProtocolConnectionCollection"/> class.
   */
  ProtocolConnectionCollection() {
    this.connections = new ArrayList<ProtocolConnection>();
  }

  /**
   * Read user setting with ProtocolConnectionCollection value.
   *
   * @param reader EwsServiceXmlReader
   * @return the protocol connection collection
   * @throws Exception the exception
   */
  static ProtocolConnectionCollection LoadFromXml(EwsXmlReader reader)
      throws Exception {
    ProtocolConnectionCollection value = new ProtocolConnectionCollection();
    ProtocolConnection connection = null;

    do {
      reader.read();

      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName().equals(
            XmlElementNames.ProtocolConnection)) {
          connection = ProtocolConnection.loadFromXml(reader);
          if (connection != null) {
            value.getConnections().add(connection);
          }
        }
      }
    } while (!reader.isEndElement(XmlNamespace.Autodiscover,
        XmlElementNames.ProtocolConnections));

    return value;
  }

  /**
   * Gets the Connections.
   *
   * @return the connections
   */
  public ArrayList<ProtocolConnection> getConnections() {
    return this.connections;
  }

  /**
   * Sets the connections.
   *
   * @param value the new connections
   */
  void setConnections(ArrayList<ProtocolConnection> value) {
    this.connections = value;
  }
}
