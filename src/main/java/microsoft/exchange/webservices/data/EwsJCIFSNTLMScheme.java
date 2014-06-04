/**************************************************************************
 * copyright file="EwsJCIFSNTLMScheme.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 * 
 * Defines the EwsJCIFSNTLMScheme.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import java.io.IOException;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.auth.AuthChallengeParser;
import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.auth.AuthenticationException;
import org.apache.commons.httpclient.auth.InvalidCredentialsException;
import org.apache.commons.httpclient.auth.MalformedChallengeException;


/**
 * This is a reimplementation of HTTPClient 3.x's
 * org.apache.commons.httpclient.auth.NTLMScheme.<BR/>
 * It will basically use JCIFS (v1.3.15) in order to provide added support for
 * NTLMv2 (instead of trying to create its own Type, 2 and 3 messages). <BR/>
 * This class has to be registered manually with HTTPClient before setting
 * NTCredentials: AuthPolicy.registerAuthScheme(AuthPolicy.NTLM,
 * JCIFS_NTLMScheme.class); <BR/>
 * Will <B>not</B> work with HttpClient 4.x which requires AuthEngine to be overriden instead of AuthScheme.
 * 
 */

public class EwsJCIFSNTLMScheme implements AuthScheme {

	/** NTLM challenge string. */
	private String ntlmchallenge = null;

	private static final int UNINITIATED = 0;

	private static final int INITIATED = 1;

	private static final int TYPE1_MSG_GENERATED = 2;

	private static final int TYPE2_MSG_RECEIVED = 3;

	private static final int TYPE3_MSG_GENERATED = 4;

	private static final int FAILED = Integer.MAX_VALUE;

	/** Authentication process state */
	private int state;

	public EwsJCIFSNTLMScheme() throws AuthenticationException {
		// Check if JCIFS is present. If not present, do not proceed.
		try {
			Class.forName("jcifs.ntlmssp.NtlmMessage",false,this.getClass().getClassLoader());
		} catch (ClassNotFoundException e) {
			throw new AuthenticationException("Unable to proceed as JCIFS library is not found.");
		}
	}

	public String authenticate(Credentials credentials, HttpMethod method)
	throws AuthenticationException {

		if (this.state == UNINITIATED) {
			throw new IllegalStateException(
			"NTLM authentication process has not been initiated");
		}

		NTCredentials ntcredentials = null;
		try {
			ntcredentials = (NTCredentials) credentials;
		} catch (ClassCastException e) {
			throw new InvalidCredentialsException(
					"Credentials cannot be used for NTLM authentication: "
					+ credentials.getClass().getName());
		}

		NTLM ntlm = new NTLM();
		ntlm.setCredentialCharset(method.getParams().getCredentialCharset());
		String response = null;
		if (this.state == INITIATED || this.state == FAILED) {
			response = ntlm.generateType1Msg(ntcredentials.getHost(),
					ntcredentials.getDomain());
			this.state = TYPE1_MSG_GENERATED;
		} else {
			response = ntlm.generateType3Msg(ntcredentials.getUserName(),
					ntcredentials.getPassword(), ntcredentials.getHost(),
					ntcredentials.getDomain(), this.ntlmchallenge);
			this.state = TYPE3_MSG_GENERATED;
		}

		return "NTLM " + response;
	}

	public String authenticate(Credentials credentials, String method,
			String uri) throws AuthenticationException {
		throw new RuntimeException(
		"Not implemented as it is deprecated anyway in Httpclient 3.x");
	}

	public String getID() {
		throw new RuntimeException(
		"Not implemented as it is deprecated anyway in Httpclient 3.x");
	}

	/**
	 * Returns the authentication parameter with the given name, if available.
	 * 
	 * <p>
	 * There are no valid parameters for NTLM authentication so this method
	 * always returns <tt>null</tt>.
	 * </p>
	 * 
	 * @param name
	 *            The name of the parameter to be returned
	 * 
	 * @return the parameter with the given name
	 */
	public String getParameter(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter name may not be null");
		}
		return null;
	}

	/**
	 * The concept of an authentication realm is not supported by the NTLM
	 * authentication scheme. Always returns <code>null</code>.
	 * 
	 * @return <code>null</code>
	 */
	public String getRealm() {
		return null;
	}

	/**
	 * Returns textual designation of the NTLM authentication scheme.
	 * 
	 * @return <code>ntlm</code>
	 */
	public String getSchemeName() {
		return "ntlm";
	}

	/**
	 * Tests if the NTLM authentication process has been completed.
	 * 
	 * @return <tt>true</tt> if Basic authorization has been processed,
	 *         <tt>false</tt> otherwise.
	 * 
	 * @since 3.0
	 */
	public boolean isComplete() {
		return this.state == TYPE3_MSG_GENERATED || this.state == FAILED;
	}

	/**
	 * Returns <tt>true</tt>. NTLM authentication scheme is connection based.
	 * 
	 * @return <tt>true</tt>.
	 * 
	 * @since 3.0
	 */
	public boolean isConnectionBased() {
		return true;
	}

	/**
	 * Processes the NTLM challenge.
	 * 
	 * @param challenge
	 *            the challenge string
	 * 
	 * @throws MalformedChallengeException
	 *             is thrown if the authentication challenge is malformed
	 *
	 * @since 3.0
	 */
	public void processChallenge(final String challenge)
	throws MalformedChallengeException {
		String s = AuthChallengeParser.extractScheme(challenge);
		if (!s.equalsIgnoreCase(getSchemeName())) {
			throw new MalformedChallengeException("Invalid NTLM challenge: "
					+ challenge);
		}
		int i = challenge.indexOf(' ');
		if (i != -1) {
			s = challenge.substring(i, challenge.length());
			this.ntlmchallenge = s.trim();
			this.state = TYPE2_MSG_RECEIVED;
		} else {
			this.ntlmchallenge = "";
			if (this.state == UNINITIATED) {
				this.state = INITIATED;
			} else {
				this.state = FAILED;
			}
		}
	}

	private class NTLM {
		/** Character encoding */
		public static final String DEFAULT_CHARSET = "ASCII";

		/**
		 * The character was used by 3.x's NTLM to encode the username and
		 * password. Apparently, this is not needed in when passing username,
		 * password from NTCredentials to the JCIFS library
		 */
		private String credentialCharset = DEFAULT_CHARSET;

		void setCredentialCharset(String credentialCharset) {
			this.credentialCharset = credentialCharset;
		}

		private String generateType1Msg(String host, String domain) {
			jcifs.ntlmssp.Type1Message t1m = new jcifs.ntlmssp.Type1Message(jcifs.ntlmssp.Type1Message.getDefaultFlags(),
					domain, host);
			return jcifs.util.Base64.encode(t1m.toByteArray());
		}

		private String generateType3Msg(String username, String password, String host,
				String domain, String challenge) {
			jcifs.ntlmssp.Type2Message t2m;
			try {
				t2m = new jcifs.ntlmssp.Type2Message(jcifs.util.Base64.decode(challenge));
			} catch (IOException e) {
				throw new RuntimeException("Invalid Type2 message", e);
			}

			jcifs.ntlmssp.Type3Message t3m = new jcifs.ntlmssp.Type3Message(t2m, password, domain,
					username, host, 0);
			return jcifs.util.Base64.encode(t3m.toByteArray());
		}
	}
}

