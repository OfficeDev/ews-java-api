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

import javax.xml.stream.XMLStreamConstants;

/**
 * The Class XmlNodeType.
 */
public class XmlNodeType implements XMLStreamConstants {

  /**
   * The node type.
   */
  public int nodeType;

  /**
   * Instantiates a new Xml node type.
   *
   * @param nodeType The node type.
   */
  public XmlNodeType(int nodeType) {
    this.nodeType = nodeType;
  }

  /**
   * Returns a string representation of the object. In general, the
   * <code>toString</code> method returns a string that "textually represents"
   * this object. The result should be a concise but informative
   * representation that is easy for a person to read. It is recommended that
   * all subclasses override this method.
   * <p/>
   * The <code>toString</code> method for class <code>Object</code> returns a
   * string consisting of the name of the class of which the object is an
   * instance, the at-sign character `<code>@</code>', and the unsigned
   * hexadecimal representation of the hash code of the object. In other
   * words, this method returns a string equal to the value of: <blockquote>
   * <p/>
   * <pre>
   * getClass().getName() + '@' + Integer.toHexString(hashCode())
   * </pre>
   * <p/>
   * </blockquote>
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return getString(nodeType);
  }

  /**
   * Sets the node type.
   *
   * @param nodeType the new node type
   */
  public void setNodeType(int nodeType) {
    this.nodeType = nodeType;
  }

  /**
   * Gets the node type.
   *
   * @return the node type
   */
  public int getNodeType() {
    return nodeType;
  }

  /**
   * Gets the string.
   *
   * @param nodeType the node type
   * @return the string
   */
  public static String getString(int nodeType) {
    switch (nodeType) {
      case XMLStreamConstants.ATTRIBUTE:
        return "ATTRIBUTE";
      case XMLStreamConstants.CDATA:
        return "CDATA";
      case XMLStreamConstants.CHARACTERS:
        return "CHARACTERS";
      case XMLStreamConstants.COMMENT:
        return "COMMENT";
      case XMLStreamConstants.DTD:
        return "DTD";
      case XMLStreamConstants.END_DOCUMENT:
        return "END_DOCUMENT";
      case XMLStreamConstants.END_ELEMENT:
        return "END_ELEMENT";
      case XMLStreamConstants.ENTITY_DECLARATION:
        return "ENTITY_DECLARATION";
      case XMLStreamConstants.ENTITY_REFERENCE:
        return "ENTITY_REFERENCE";
      case XMLStreamConstants.NAMESPACE:
        return "NAMESPACE";
      case XMLStreamConstants.NOTATION_DECLARATION:
        return "NOTATION_DECLARATION";
      case XMLStreamConstants.PROCESSING_INSTRUCTION:
        return "PROCESSING_INSTRUCTION";
      case XMLStreamConstants.SPACE:
        return "SPACE";
      case XMLStreamConstants.START_DOCUMENT:
        return "START_DOCUMENT";
      case XMLStreamConstants.START_ELEMENT:
        return "START_ELEMENT";
      case 0:
        return "NONE";
      default:
        return "UNKNOWN";
    }
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * <p/>
   * The <code>equals</code> method implements an equivalence relation on
   * non-null object references:
   * <ul>
   * <li>It is <i>reflexive</i>: for any non-null reference value
   * <code>x</code>, <code>x.equals(x)</code> should return <code>true</code>.
   * <li>It is <i>symmetric</i>: for any non-null reference values
   * <code>x</code> and <code>y</code>, <code>x.equals(y)</code> should return
   * <code>true</code> if and only if <code>y.equals(x)</code> returns
   * <code>true</code>.
   * <li>It is <i>transitive</i>: for any non-null reference values
   * <code>x</code>, <code>y</code>, and <code>z</code>, if
   * <code>x.equals(y)</code> returns <code>true</code> and
   * <code>y.equals(z)</code> returns <code>true</code>, then
   * <code>x.equals(z)</code> should return <code>true</code>.
   * <li>It is <i>consistent</i>: for any non-null reference values
   * <code>x</code> and <code>y</code>, multiple invocations of
   * <tt>x.equals(y)</tt> consistently return <code>true</code> or
   * consistently return <code>false</code>, provided no information used in
   * <code>equals</code> comparisons on the objects is modified.
   * <li>For any non-null reference value <code>x</code>,
   * <code>x.equals(null)</code> should return <code>false</code>.
   * </ul>
   * <p/>
   * The <tt>equals</tt> method for class <code>Object</code> implements the
   * most discriminating possible equivalence relation on objects; that is,
   * for any non-null reference values <code>x</code> and <code>y</code>, this
   * method returns <code>true</code> if and only if <code>x</code> and
   * <code>y</code> refer to the same object (<code>x == y</code> has the
   * value <code>true</code>).
   * <p/>
   * Note that it is generally necessary to override the <tt>hashCode</tt>
   * method whenever this method is overridden, so as to maintain the general
   * contract for the <tt>hashCode</tt> method, which states that equal
   * objects must have equal hash codes.
   *
   * @param obj the reference object with which to compare.
   * @return if this object is the same as the obj argument; otherwise.
   * @see #hashCode()
   * @see java.util.Hashtable
   */
  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj instanceof XmlNodeType) {
      XmlNodeType other = (XmlNodeType) obj;
      return this.nodeType == other.nodeType;
    } else {
      return super.equals(obj);
    }
  }

  /**
   * Returns a hash code value for the object. This method is supported for
   * the benefit of hashtables such as those provided by
   * <code>java.util.Hashtable</code>.
   * <p/>
   * The general contract of <code>hashCode</code> is:
   * <ul>
   * <li>Whenever it is invoked on the same object more than once during an
   * execution of a Java application, the <tt>hashCode</tt> method must
   * consistently return the same integer, provided no information used in
   * <tt>equals</tt> comparisons on the object is modified. This integer need
   * not remain consistent from one execution of an application to another
   * execution of the same application.
   * <li>If two objects are equal according to the <tt>equals(Object)</tt>
   * method, then calling the <code>hashCode</code> method on each of the two
   * objects must produce the same integer result.
   * <li>It is <em>not</em> required that if two objects are unequal according
   * to the {@link Object#equals(Object)} method, then
   * calling the <tt>hashCode</tt> method on each of the two objects must
   * produce distinct integer results. However, the programmer should be aware
   * that producing distinct integer results for unequal objects may improve
   * the performance of hashtables.
   * </ul>
   * <p/>
   * As much as is reasonably practical, the hashCode method defined by class
   * <tt>Object</tt> does return distinct integers for distinct objects. (This
   * is typically implemented by converting the internal address of the object
   * into an integer, but this implementation technique is not required by the
   * Java<font size="-2"><sup>TM</sup></font> programming language.)
   *
   * @return a hash code value for this object.
   * @see Object#equals(Object)
   * @see java.util.Hashtable
   */
  @Override
  public int hashCode() {
    return this.nodeType;
  }
}
