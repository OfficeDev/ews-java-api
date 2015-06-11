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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

/**
 * XmlDocument that does not allow DTD parsing.
 */
public class SafeXmlDocument extends DocumentBuilder {

  private static final Log LOG = LogFactory.getLog(SafeXmlDocument.class);

  /**
   * Initializes a new instance of the SafeXmlDocument class.
   */
  private final XMLInputFactory inputFactory;

  public SafeXmlDocument() {
    super();
    inputFactory = XMLInputFactory.newInstance();
  }


  /**
   * Loads the XML document from the specified stream.
   *
   * @param inStream The stream containing the XML document to load.
   * @throws javax.xml.stream.XMLStreamException
   */
  public void load(InputStream inStream) throws XMLStreamException {
    // not in a using block because
    // the stream doesn't belong to us
    if (inputFactory != null) {
      XMLEventReader reader = inputFactory
          .createXMLEventReader(inStream);

      this.load((InputStream) reader);
    }
  }

  /**
   * Loads the XML document from the specified URL.
   *
   * @param filename URL for the file containing the XML document to load. The URL
   *                 can be either a local file or an HTTP URL (a Web address).
   */
  public void load(String filename) {
    if (inputFactory != null) {
      FileInputStream inp;

      XMLEventReader reader;
      try {
        inp = new FileInputStream(filename);
        reader = inputFactory.createXMLEventReader(inp);
        this.load((InputStream) reader);
      } catch (XMLStreamException e) {
        // TODO Auto-generated catch block
        LOG.error(e);
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        LOG.error(e);
      }
    }
  }

  /**
   * Loads the XML document from the specified TextReader.
   *
   * @param txtReader The TextReader used to feed the XML data into the document.
   */
  public void load(Reader txtReader) {
    if (inputFactory != null) {

      XMLEventReader reader;
      try {
        reader = inputFactory
            .createXMLEventReader(txtReader);

        this.load((InputStream) reader);
      } catch (XMLStreamException e) {
        // TODO Auto-generated catch block
        LOG.error(e);
      }
    }
  }

  /**
   * Loads the XML document from the specified XMLReader.
   *
   * @param reader The XMLReader used to feed the XML data into the document.
   * @throws java.io.IOException
   * @throws org.xml.sax.SAXException
   */
  public void load(XMLStreamReader reader) throws SAXException, IOException {

    super.parse((InputStream) reader);
  }

  /**
   * Loads the XML document from the specified string.
   *
   * @param xml String containing the XML document to load.
   */
  public void loadXml(String xml) {
    if (inputFactory != null) {
      try {
        XMLEventReader reader = inputFactory
            .createXMLEventReader(new StringReader(xml));

        this.load((InputStream) reader);
      } catch (XMLStreamException e) {
        // TODO Auto-generated catch block
        LOG.error(e);
      }
    }

  }

  @Override
  public DOMImplementation getDOMImplementation() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isNamespaceAware() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isValidating() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Document newDocument() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Document parse(InputSource is) throws SAXException, IOException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setEntityResolver(EntityResolver er) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setErrorHandler(ErrorHandler eh) {
    // TODO Auto-generated method stub

  }


}
