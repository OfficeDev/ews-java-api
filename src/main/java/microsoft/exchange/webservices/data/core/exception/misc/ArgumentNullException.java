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

package microsoft.exchange.webservices.data.core.exception.misc;

public class ArgumentNullException extends ArgumentException {

  /**
   * Constructs an <code>IllegalArgumentException</code> with the specified detail message.
   *
   * @param message the detail message.
   */
  public ArgumentNullException(String message) {
    super(message);
  }

  /**
   * Constructs an <code>IllegalArgumentException</code> with the specified detail message.
   *
   * @param s         the detail message.
   * @param paramName the Name of the Param that causes the exception
   */
  public ArgumentNullException(String s, String paramName) {
    super(s, paramName);
  }

  /**
   * Constructs a new exception with the specified detail message and cause.
   * <p/>
   * <p>Note that the detail message associated with <code>cause</code> is <i>not</i> automatically
   * incorporated in this exception's detail message.
   *
   * @param message the detail message (which is saved for later retrieval by the {@link
   *                Throwable#getMessage()} method).
   * @param cause   the cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
   *                (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or
   *                unknown.)
   * @since 1.5
   */
  public ArgumentNullException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new exception with the specified cause and a detail message of <tt>(cause==null ? null :
   * cause.toString())</tt> (which typically contains the class and detail message of <tt>cause</tt>). This
   * constructor is useful for exceptions that are little more than wrappers for other throwables (for
   * example, {@link java.security.PrivilegedActionException}).
   *
   * @param cause the cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
   *              (A <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or
   *              unknown.)
   * @since 1.5
   */
  public ArgumentNullException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new exception with the specified cause and a detail message of <tt>(cause==null ? null :
   * cause.toString())</tt> (which typically contains the class and detail message of <tt>cause</tt>). This
   * constructor is useful for exceptions that are little more than wrappers for other throwables (for
   * example, {@link java.security.PrivilegedActionException}).
   *
   * @param cause     the cause (which is saved for later retrieval by the {@link Throwable#getCause()}
   *                  method). (A <tt>null</tt> value is permitted, and indicates that the cause is
   *                  nonexistent or unknown.)
   * @param paramName the Name of the Param that causes the exception
   */
  public ArgumentNullException(Throwable cause, String paramName) {
    super(cause, paramName);
  }

  /**
   * Initializes a new instance of the System. ArgumentException class with a specified error message and the
   * name of the parameter that causes this exception.
   *
   * @param message   The error message that explains the reason for the exception.
   * @param cause     the cause (which is saved for later retrieval by the {@link Throwable#getCause()}
   *                  method). (A <tt>null</tt> value is permitted, and indicates that the cause is
   *                  nonexistent or unknown.)
   * @param paramName the Name of the Param that causes the exception
   */
  public ArgumentNullException(String message, Throwable cause, String paramName) {
    super(message, cause, paramName);
  }
}
