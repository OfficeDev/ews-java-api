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

import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.security.XmlNodeType;

import java.util.ArrayList;

/**
 * Represents a user setting that is a collection of Exchange web client URLs.
 */
public final class WebClientUrlCollection {

  /**
   * The urls.
   */
  private ArrayList<WebClientUrl> urls;

  /**
   * Initializes a new instance of the {@link WebClientUrlCollection} class.
   */
  public WebClientUrlCollection() {
    this.urls = new ArrayList<WebClientUrl>();
  }

  /**
   * Loads instance of WebClientUrlCollection from XML.
   *
   * @param reader The reader.
   * @return the web client url collection
   * @throws Exception the exception
   */
  public static WebClientUrlCollection loadFromXml(EwsXmlReader reader)
      throws Exception {
    WebClientUrlCollection instance = new WebClientUrlCollection();

    do {
      reader.read();

      if ((reader.getNodeType().getNodeType() == XmlNodeType.START_ELEMENT) &&
          (reader.getLocalName()
              .equals(XmlElementNames.WebClientUrl))) {
        instance.getUrls().add(WebClientUrl.loadFromXml(reader));
      }
    } while (!reader.isEndElement(XmlNamespace.Autodiscover,
        XmlElementNames.WebClientUrls));

    return instance;
  }

  /**
   * Gets the URLs.
   *
   * @return the urls
   */
  public ArrayList<WebClientUrl> getUrls() {
    return this.urls;

  }
}
