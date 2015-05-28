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

import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlAttributeNames;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.exception.misc.ArgumentOutOfRangeException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list of suggested name resolutions.
 */
public final class NameResolutionCollection implements
    Iterable<NameResolution> {

  /**
   * The service.
   */
  private ExchangeService service;

  /**
   * The includes all resolutions.
   */
  private boolean includesAllResolutions;

  /**
   * The item.
   */
  private List<NameResolution> items = new ArrayList<NameResolution>();

  /**
   * Represents a list of suggested name resolutions.
   *
   * @param service the service
   */
  public NameResolutionCollection(ExchangeService service) {
    EwsUtilities.ewsAssert(service != null, "NameResolutionSet.ctor", "service is null.");
    this.service = service;
  }

  /**
   * Loads from XML.
   *
   * @param reader the reader
   * @throws Exception the exception
   */
  public void loadFromXml(EwsServiceXmlReader reader) throws Exception {
    reader.readStartElement(XmlNamespace.Messages,
        XmlElementNames.ResolutionSet);
    int totalItemsInView = reader.readAttributeValue(Integer.class,
        XmlAttributeNames.TotalItemsInView);
    this.includesAllResolutions = reader.readAttributeValue(Boolean.class,
        XmlAttributeNames.IncludesLastItemInRange);

    for (int i = 0; i < totalItemsInView; i++) {
      NameResolution nameResolution = new NameResolution(this);
      nameResolution.loadFromXml(reader);
      this.items.add(nameResolution);
    }

    reader.readEndElement(XmlNamespace.Messages,
        XmlElementNames.ResolutionSet);
  }

  /**
   * Gets the session. <value>The session.</value>
   *
   * @return the session
   */
  protected ExchangeService getSession() {
    return this.service;
  }

  /**
   * Gets the total number of elements in the list.
   *
   * @return the count
   */
  public int getCount() {
    return this.items.size();
  }

  /**
   * Gets a value indicating whether more suggested resolutions are available.
   * ResolveName only returns a maximum of 100 name resolutions. When
   * IncludesAllResolutions is false, there were more than 100 matching names
   * on the server. To narrow the search, provide a more precise name to
   * ResolveName.
   *
   * @return the includes all resolutions
   */
  public boolean getIncludesAllResolutions() {
    return this.includesAllResolutions;
  }

  /**
   * Gets the name resolution at the specified index.
   *
   * @param index the index
   * @return The name resolution at the speicfied index.
   * @throws ArgumentOutOfRangeException the argument out of range exception
   */
  public NameResolution nameResolutionCollection(int index)
      throws ArgumentOutOfRangeException {
    if (index < 0 || index >= this.getCount()) {
      throw new ArgumentOutOfRangeException("index", "index is out of range.");
    }

    return this.items.get(index);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<NameResolution> iterator() {

    return items.iterator();
  }
}
