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

import javax.xml.bind.ValidationEventHandler;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;
import java.io.InputStream;

/**
 * XmlSchema with protection against DTD parsing in read overloads
 */
public class SafeXmlSchema extends Schema {

  @Override
  public Validator newValidator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ValidatorHandler newValidatorHandler() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Reads an XML Schema from the supplied stream.
   *
   * @param stream                 The supplied data stream.
   * @param validationEventHandler The validation event handler that receives information about the XML Schema syntax errors
   * @return The XmlSchema object representing the XML Schema.
   * @throws javax.xml.stream.XMLStreamException
   */
  public static Schema Read(InputStream stream, ValidationEventHandler validationEventHandler)
      throws XMLStreamException {
    XMLInputFactory inputFactory = XMLInputFactory.newInstance();

    return (Schema) inputFactory.createXMLEventReader(stream);
  }

  /**
   * Reads an XML Schema from the supplied TextReader.
   *
   * @param reader                 The TextReader containing the XML Schema to read
   * @param validationEventHandler The validation event handler that receives information about the XML Schema syntax errors.
   * @return The XmlSchema object representing the XML Schema.
   * @throws javax.xml.stream.XMLStreamException
   */

  public static Schema Read(XMLStreamReader reader, ValidationEventHandler validationEventHandler)
      throws XMLStreamException {

    XMLInputFactory inputFactory = XMLInputFactory.newInstance();

    return (Schema) inputFactory.createXMLEventReader(reader);
  }

}
