/*
 * The MIT License Copyright (c) 2017 Kevin Burek <khb718@g.harvard.edu>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.credential;

import java.util.Map;

import microsoft.exchange.webservices.data.core.request.HttpWebRequest;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;

/**
 * BearerTokenCredentials is used for OAuth2 bearer-token credentials. https://tools.ietf.org/html/rfc6750
 */
public class BearerTokenCredentials extends ExchangeCredentials {

    /**
     * Bearer token format regular expression. https://tools.ietf.org/html/rfc6750#section-2.1
     */
    private static final String BEARER_TOKEN_FORMAT_REGEX = "^[-._~+/A-Za-z0-9]+=*$";

    private static final String AUTHORIZATION = "Authorization";

    private static final String BEARER_AUTH_PREAMBLE = "Bearer ";

    /**
     * The domain.
     */
    private String token;

    /**
     * Gets the token string.
     *
     * @return the token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Initializes a new instance to specified token string.
     */
    public BearerTokenCredentials(String bearerToken) {
        if (bearerToken == null) {
            throw new IllegalArgumentException("Bearer token can not be null");
        }

        this.validateToken(bearerToken);

        this.token = bearerToken;
    }

    /**
     * Validates the format of the bearer token, per RFC 6750.
     *
     * @param bearerToken The token string.
     * @throws IllegalArgumentException When the token fails validation.
     */
    protected void validateToken(String bearerToken) throws IllegalArgumentException {
        if (!bearerToken.matches(BEARER_TOKEN_FORMAT_REGEX)) {
            throw new IllegalArgumentException("Bearer token format is invalid.");
        }
    }

    /**
     * This method is called to apply credential to a service request before the request is made.
     *
     * @param request The request.
     */
    @Override
    public void prepareWebRequest(HttpWebRequest request) {
        Map<String, String> headersMap = request.getHeaders();
        String bearerValue = BEARER_AUTH_PREAMBLE + token;
        headersMap.put(AUTHORIZATION, bearerValue);
        request.setHeaders(headersMap);
    }
}
