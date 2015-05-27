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

package microsoft.exchange.webservices.data.autodiscover;

import microsoft.exchange.webservices.data.autodiscover.response.AutodiscoverResponse;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.security.XmlNodeType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a collection of response to a call to the Autodiscover service.
 *
 * @param <TResponse> The type of the response in the collection.
 */
public abstract class AutodiscoverResponseCollection
    <TResponse extends AutodiscoverResponse>
    extends AutodiscoverResponse implements Iterable<TResponse> {

  /**
   * The response.
   */
  private List<TResponse> responses;

  /**
   * Initializes a new instance of the AutodiscoverResponseCollection class.
   */
  public AutodiscoverResponseCollection() {
    super();
    this.responses = new ArrayList<TResponse>();
  }

  /**
   * Gets the number of response in the collection.
   *
   * @return the count
   */
  public int getCount() {
    return this.responses.size();
  }

  /**
   * Gets the response at the specified index.
   *
   * @param index the index
   * @return the t response at index
   */
  public TResponse getTResponseAtIndex(int index) {
    return this.responses.get(index);
  }

  /**
   * Gets the response.
   *
   * @return the response
   */
  public List<TResponse> getResponses() {
    return responses;
  }

  /**
   * Loads response from XML.
   *
   * @param reader         the reader
   * @param endElementName End element name.
   * @throws Exception the exception
   */
  public void loadFromXml(EwsXmlReader reader, String endElementName)
      throws Exception {
    do {
      reader.read();

      if (reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) {
        if (reader.getLocalName().equals(
            this.getResponseCollectionXmlElementName())) {
          this.loadResponseCollectionFromXml(reader);
        } else {
          super.loadFromXml(reader, endElementName);
        }
      }
    } while (!reader
        .isEndElement(XmlNamespace.Autodiscover, endElementName));
  }

  /**
   * Loads response from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  private void loadResponseCollectionFromXml(EwsXmlReader reader)
      throws Exception {
    if (!reader.isEmptyElement()) {
      do {
        reader.read();
        if ((reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) &&
            (reader.getLocalName().equals(this
                .getResponseInstanceXmlElementName()))) {
          TResponse response = this.createResponseInstance();
          response.loadFromXml(reader, this
              .getResponseInstanceXmlElementName());
          this.responses.add(response);
        }
      } while (!reader.isEndElement(XmlNamespace.Autodiscover, this
          .getResponseCollectionXmlElementName()));
    } else {
      reader.read();
    }
  }

  /**
   * Gets the name of the response collection XML element.
   *
   * @return Response collection XMl element name.
   */
  protected abstract String getResponseCollectionXmlElementName();

  /**
   * Gets the name of the response instance XML element.
   *
   * @return Response collection XMl element name.
   */
  protected abstract String getResponseInstanceXmlElementName();

  /**
   * Create a response instance.
   *
   * @return TResponse.
   */
  protected abstract TResponse createResponseInstance();

  /**
   * Gets an Iterator that iterates through the elements of the collection.
   *
   * @return An Iterator for the collection.
   */
  public Iterator<TResponse> iterator() {
    return this.responses.iterator();
  }
}
