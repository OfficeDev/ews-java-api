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

package microsoft.exchange.webservices.data.core.request;

import microsoft.exchange.webservices.base.BaseTest;
import microsoft.exchange.webservices.data.core.EwsServiceXmlReader;
import microsoft.exchange.webservices.data.core.enumeration.service.ServiceResult;
import microsoft.exchange.webservices.data.core.response.GetUserConfigurationResponse;
import microsoft.exchange.webservices.data.core.response.ServiceResponseCollection;
import microsoft.exchange.webservices.data.misc.OutParam;
import microsoft.exchange.webservices.data.property.complex.UserConfigurationDictionary;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.InputStream;

@RunWith(JUnit4.class)
public class UserConfigurationTest extends BaseTest {

  @Test
  public void testNilDictionaryEntry() throws Exception {
    GetUserConfigurationRequest request = new GetUserConfigurationRequest(exchangeServiceMock);
    request.setName("Calendar");

    InputStream inputStream = getClass().getResourceAsStream("/nil.xml");
    EwsServiceXmlReader ewsXmlReader = new EwsServiceXmlReader(inputStream, exchangeServiceMock);
    ServiceResponseCollection<GetUserConfigurationResponse> responses = request.readResponse(ewsXmlReader);

    Assert.assertEquals(ServiceResult.Success, responses.getOverallResult());
    Assert.assertNull(getDictionaryEntryValue(responses, "RequestInPolicy"));
  }

  private Object getDictionaryEntryValue(ServiceResponseCollection<GetUserConfigurationResponse> responses,
                                         String requestInPolicy) {
    UserConfigurationDictionary dictionary = responses.iterator().next().getUserConfiguration().getDictionary();
    OutParam<Object> value = new OutParam<Object>();
    dictionary.tryGetValue(requestInPolicy, value);
    return value.getParam();
  }

}
