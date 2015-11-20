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

package microsoft.exchange.webservices.data.property.complex;

import java.util.ArrayList;

import microsoft.exchange.webservices.data.core.enumeration.property.MapiPropertyType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import microsoft.exchange.webservices.data.core.exception.misc.ArgumentException;
import microsoft.exchange.webservices.data.misc.OutParam;
import microsoft.exchange.webservices.data.property.definition.ExtendedPropertyDefinition;

@RunWith(JUnit4.class)
public class ExtendedPropertyCollectionTest {
  
  /**
   * Calling tryGetValue with invalid input
   * expecting exception.
   * 
   * @throws Exception
   */
   @Test(expected=ArgumentException.class)
   public void tryGetValue() throws Exception{
     ExtendedPropertyCollection epc = new ExtendedPropertyCollection();
     epc.setExtendedProperty(new ExtendedPropertyDefinition(), new ArrayList<Boolean>());
     Class<Long> cls = Long.class;
     // By default - type of ExtendedPropertyDefinition will be String
     ExtendedPropertyDefinition propertyDefinition = new ExtendedPropertyDefinition();
     
     OutParam<Long> propertyValueOut = new OutParam<Long>();
     // It should fail here due to incompatibility between default String and passed Long
     Assert.assertTrue(epc.tryGetValue(cls, propertyDefinition, propertyValueOut));
   }

   /**
    * Calling tryGetValue with non-default input 
    * expecting positive result.
    * 
    */
    @Test()
    public void tryGetValueWithProperInput() throws Exception{
      ExtendedPropertyCollection epc = new ExtendedPropertyCollection();
      Class<Integer> cls = Integer.class;
      Integer testValue = new Integer(456);
      ExtendedPropertyDefinition propertyDefinition = new ExtendedPropertyDefinition(123, MapiPropertyType.Integer);
      epc.setExtendedProperty(propertyDefinition, testValue);
      
      OutParam<Integer> propertyValueOut = new OutParam<Integer>();
      Assert.assertTrue(epc.tryGetValue(cls, propertyDefinition, propertyValueOut));
      Assert.assertTrue(propertyValueOut.getParam().equals(testValue));
    }
}
