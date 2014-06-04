/**************************************************************************
 * copyright file="Convert.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the Convert.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * The Class Convert.
 */
 class Convert {

	/**
	 * Change type.
	 * 
	 * @param value
	 *            the value
	 * @param cls
	 *            the cls
	 * @return the object
	 * @throws ClassCastException
	 *             the class cast exception
	 */
	public static Object changeType(Object value, Class cls)
			throws ClassCastException {

		if (!(cls.isPrimitive() || cls.equals(String.class))) {
			throw new ClassCastException("String");
		}

		if (value.getClass().equals(Integer.class)) {
			Integer i = (Integer)value;

			if (cls.equals(int.class)) {
				return i;
			} else if (cls.equals(float.class)) {
				return i.floatValue();
			} else if (cls.equals(long.class)) {
				return i.longValue();
			} else if (cls.equals(double.class)) {
				return i.doubleValue();
			} else if (cls.equals(short.class)) {
				return i.shortValue();
			} else if (cls.equals(String.class)) {
				return i.toString();
			}

		} else if (value.getClass().equals(Short.class)) {
			Short i = (Short)value;

			if (cls.equals(int.class)) {
				return i.intValue();
			} else if (cls.equals(float.class)) {

				return i.floatValue();
			} else if (cls.equals(long.class)) {

				return i.longValue();
			} else if (cls.equals(double.class)) {

				return i.doubleValue();
			} else if (cls.equals(short.class)) {

				return i;
			} else if (cls.equals(String.class)) {
				return i.toString();
			}
		} else if (value.getClass().equals(Float.class)) {
			Float i = (Float)value;

			if (cls.equals(int.class)) {
				return i.intValue();
			} else if (cls.equals(float.class)) {

				return i;
			} else if (cls.equals(long.class)) {

				return i.longValue();
			} else if (cls.equals(double.class)) {

				return i.doubleValue();
			} else if (cls.equals(short.class)) {

				return i.shortValue();
			} else if (cls.equals(String.class)) {
				return i.toString();
			}
		} else if (value.getClass().equals(Double.class)) {
			Double i = (Double)value;

			if (cls.equals(int.class)) {
				return i.intValue();
			} else if (cls.equals(float.class)) {

				return i.floatValue();
			} else if (cls.equals(long.class)) {

				return i.longValue();
			} else if (cls.equals(double.class)) {

				return i;
			} else if (cls.equals(short.class)) {

				return i.shortValue();
			} else if (cls.equals(String.class)) {
				return i.toString();
			}
		} else {
			throw new ClassCastException();
		}

		return value;
	}

	/**
	 * The main method.
	 * 
	 * @param arg
	 *            the arguments
	 * @throws ClassCastException
	 *             the class cast exception
	 */
	public static void main(String[] arg) throws ClassCastException {
		System.out.println("value =" + Convert.changeType(10, double.class));
	}

	public static Object ToBase64String(byte[] sessionKey) {
		// TODO Auto-generated method stub
		return sessionKey.toString();
	}
}
