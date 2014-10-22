/**************************************************************************
 * copyright file="EwsUtilitiesTest.java" company="Microsoft"
 *     Copyright (c) Microsoft Corporation.  All rights reserved.
 *
 * Defines the BaseTest.java.
 **************************************************************************/
package microsoft.exchange.webservices.data;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * A base class with "Common-Services"
 */
@RunWith(JUnit4.class)
public abstract class BaseTest {

    /**
     * Mock for the ExchangeServiceBase
     */
    protected static ExchangeServiceBase exchangeServiceBaseMock;

    /**
     * Mock for the ExchangeService
     */
    protected static ExchangeService exchangeServiceMock;

    /**
     * Setup Mocks
     *
     * @throws Exception
     */
    @BeforeClass
    public static final void setUpBaseClass() throws Exception {
        // Mock up ExchangeServiceBase
        exchangeServiceBaseMock = new ExchangeServiceBase() {
            @Override
            protected void processHttpErrorResponse(HttpWebRequest httpWebResponse, Exception webException) throws Exception {
                throw webException;
            }
        };
        exchangeServiceMock = new ExchangeService();
    }
}
