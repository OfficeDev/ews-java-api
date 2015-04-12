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

package microsoft.exchange.webservices.data.security;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;

public class SafeXmlFactory {
  public static XMLInputFactory factory = XMLInputFactory.newInstance();


  public static XMLStreamReader createSafeXmlTextReader(InputStream stream) throws Exception {
    XMLStreamReader xsr = factory.createXMLStreamReader(stream);
    return xsr;

  }


  public static XMLStreamReader createSafeXmlTextReader(String url) throws Exception {
    FileInputStream fis = new FileInputStream(url);
    XMLStreamReader xtr = factory.createXMLStreamReader(url, fis);
    return xtr;
  }

  public static XMLStreamReader createSafeXmlTextReader(XMLStreamReader reader) throws Exception {

    XMLStreamReader xmlr =
        factory.createXMLStreamReader((Reader) reader);
    return xmlr;


  }



}
