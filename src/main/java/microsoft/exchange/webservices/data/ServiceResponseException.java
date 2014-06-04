/**************************************************************************
 * copyright file="ServiceResponseException.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the ServiceResponseException.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

/**
 * Represents a remote service exception that has a single response.
 * 
 */
public class ServiceResponseException extends ServiceRemoteException {

	/** Error details Value keys. */
	private static final String ExceptionClassKey = "ExceptionClass";

	/** The Exception message key. */
	private static final String ExceptionMessageKey = "ExceptionMessage";

	/** The Stack trace key. */
	private static final String StackTraceKey = "StackTrace";

	/**
	 * ServiceResponse when service operation failed remotely.
	 */
	private ServiceResponse response;

	/**
	 * Initializes a new instance.
	 * 
	 * @param response
	 *            the response
	 */
	protected ServiceResponseException(ServiceResponse response) {
		this.response = response;
	}

	/**
	 * Gets the ServiceResponse for the exception.
	 * 
	 * @return the response
	 */
	public ServiceResponse getResponse() {
		return response;
	}

	/**
	 * Gets the service error code.
	 * 
	 * @return the error code
	 */
	public ServiceError getErrorCode() {
		return this.response.getErrorCode();
	}

	/**
	 * Gets a message that describes the current exception.
	 * 
	 * @return The error message that explains the reason for the exception.
	 */

	public String getMessage() {

		// Bug E14:134792 -- Special case for Internal Server Error. If the
		// server returned
		// stack trace information, include it in the exception message.
		if (this.response.getErrorCode() == ServiceError.ErrorInternalServerError) {
			String exceptionClass;
			String exceptionMessage;
			String stackTrace;

			if (this.response.getErrorDetails().containsKey(ExceptionClassKey) &&
					 this.response.getErrorDetails().containsKey(
							ExceptionMessageKey) &&
					 this.response.getErrorDetails().containsKey(
							StackTraceKey)) {
				exceptionClass = this.response.getErrorDetails().get(
						ExceptionClassKey);
				exceptionMessage = this.response.getErrorDetails().get(
						ExceptionMessageKey);
				stackTrace = this.response.getErrorDetails().get(StackTraceKey);

				// return
				return String.format(
						Strings.ServerErrorAndStackTraceDetails, this.response
								.getErrorMessage(), exceptionClass,
						exceptionMessage, stackTrace);
			}
		}

		return this.response.getErrorMessage();
	}
}
