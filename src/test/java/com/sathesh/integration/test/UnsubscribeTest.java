package com.sathesh.integration.test;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @author Sathesh Sivashanmugam
 * date 2019-02-22
 */
public class UnsubscribeTest {
    public static void main(String[] args) throws Exception {
        ExchangeService service;
        Properties properties = getProperties("./secret.properties");
        service = initConnection(properties.getProperty("username"), properties.getProperty("pwd"), properties.getProperty("url"));
        subscriptionTesting(service);
    }

    private static Properties getProperties(String file) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        return properties;
    }

    private static ExchangeService initConnection(String username, String pwd, String url) throws URISyntaxException {
        ExchangeService service = new ExchangeService();
        ExchangeCredentials credentials = new WebCredentials(username, pwd);
        service.setCredentials(credentials);
        service.setUrl(new URI(url));
        service.setTraceEnabled(false);
        return service;
    }

    private static void subscriptionTesting(ExchangeService service) throws Exception {
        service.unsubscribe("JwBzbjZwcjA2bWI1MjQ1Lm5hbXByZDA2LnByb2Qub3V0bG9vay5jb20QAAAAlAbwumPv0kSMLBYioFvbdUamrY/AMdgIEAAAAP5/AwBgyMRNAAAAAAAAAAA=");


    }


}
