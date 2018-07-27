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

package microsoft.exchange.webservices.data.credential;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.EwsXmlReader;
import microsoft.exchange.webservices.data.core.request.HttpWebRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class) public class BearerTokenCredentialsTest {

  private static final Log LOG = LogFactory.getLog(BearerTokenCredentialsTest.class);

  private static final String SUPERFICIALLY_VALID_TOKEN = "Zm9vLmJhcg==";

  @Mock HttpWebRequest webRequest;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test public void testEmitBearerAuthorizationHeader() throws URISyntaxException {
	        
	  final String authorizationHeaderKey = "Authorization";
	        final String bearerTokenPrefix = "Bearer";
	        final String anyValidToken = SUPERFICIALLY_VALID_TOKEN;
	        HttpWebRequest mockRequest = mock(HttpWebRequest.class);

	        final ArrayList<HashMap<String, String>> headersContainer = new ArrayList<HashMap<String, String>>();
	        headersContainer.add(new HashMap<String, String>());
	        when(mockRequest.getHeaders()).thenReturn(headersContainer.get(0));
	        doAnswer(new Answer() {
				@Override
				public Object answer(InvocationOnMock invocation) throws Throwable {
		            headersContainer.set(0, (HashMap<String, String>) invocation.getArguments()[0]);
		            return null;
				}
	        }).when(mockRequest).setHeaders((Map<String, String>) any());

	        ExchangeCredentials creds = new BearerTokenCredentials(anyValidToken);

	        creds.prepareWebRequest(mockRequest);

	        Map<String, String> actualHeaders = mockRequest.getHeaders();
	        Assert.assertTrue("Headers must contain authenticate line.",
	                        actualHeaders.containsKey(authorizationHeaderKey));
	        String actualAuthorizationHeader = actualHeaders.get(authorizationHeaderKey);
	        Assert.assertTrue("Header value must start with " + bearerTokenPrefix, 
	                        actualAuthorizationHeader.startsWith(bearerTokenPrefix));
	        Assert.assertTrue("Header value must contain token string.",
	                        actualAuthorizationHeader.contains(anyValidToken));
	    }

}
