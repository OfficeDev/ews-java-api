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

package microsoft.exchange.webservices.base.util;

import org.junit.Assert;
import org.junit.Ignore;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

@Ignore
public final class TestUtils {

  private TestUtils() {
    throw new UnsupportedOperationException();
  }


  /**
   * Check that class has only one private constructor without parameters that throws exception during
   * instantiation. It's necessary for util-classes.
   *
   * @param utilClass util-class
   * @throws Throwable exception during instantiation
   */
  public static void checkUtilClassConstructor(final Class<?> utilClass) throws Throwable {
    // Check count of available constructors.
    final Constructor<?>[] constructors = utilClass.getDeclaredConstructors();
    Assert.assertEquals(1, constructors.length);

    // Check accessibility.
    final Constructor<?> constructor = constructors[0];
    Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));

    // Try to create instance.
    constructor.setAccessible(true);
    try {
      constructor.newInstance();
    } catch (final InvocationTargetException ex) {
      throw ex.getTargetException();
    }

    // We should never get this situation.
    Assert.fail();
  }

}
