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

package microsoft.exchange.webservices.data;

import javax.xml.stream.XMLStreamException;

/**
 * Interface defined for properties that produce their own update serialization.
 */
interface ICustomXmlUpdateSerializer {

  /**
   * Writes the update to XML.
   *
   * @param writer             The writer.
   * @param ewsObject          The ews object.
   * @param propertyDefinition Property definition.
   * @return True if property generated serialization.
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlSerializationException    the service xml serialization exception
   * @throws InstantiationException              the instantiation exception
   * @throws IllegalAccessException              the illegal access exception
   * @throws ServiceValidationException          the service validation exception
   * @throws Exception                           the exception
   */
  boolean writeSetUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject, PropertyDefinition propertyDefinition)
      throws XMLStreamException, ServiceXmlSerializationException,
      InstantiationException, IllegalAccessException,
      ServiceValidationException, Exception;

  /**
   * Writes the deletion update to XML.
   *
   * @param writer    The writer.
   * @param ewsObject The ews object.
   * @return True if property generated serialization.
   * @throws javax.xml.stream.XMLStreamException the xML stream exception
   * @throws ServiceXmlSerializationException    the service xml serialization exception
   * @throws Exception                           the exception
   */
  boolean writeDeleteUpdateToXml(EwsServiceXmlWriter writer,
      ServiceObject ewsObject) throws XMLStreamException,
      ServiceXmlSerializationException, Exception;
}
