package com.sathesh.integration.test;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.search.ResolveNameSearchLocation;
import microsoft.exchange.webservices.data.core.service.item.Contact;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.misc.NameResolution;
import microsoft.exchange.webservices.data.misc.NameResolutionCollection;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @author Sathesh Sivashanmugam
 * date 2019-02-22
 */
public class ContactReadTest {
    public static void main(String[] args) throws Exception {
        ExchangeService service;
        Properties properties = getProperties("./secret3.properties");
        service = initConnection(properties.getProperty("username"), properties.getProperty("pwd"), properties.getProperty("url"));
        getFirstMail(service);
    }

    private static Properties getProperties(String file) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(file) );
        return properties;
    }

    private static ExchangeService initConnection(String username, String pwd, String url) throws URISyntaxException {
        ExchangeService service = new ExchangeService();
        ExchangeCredentials credentials = new WebCredentials(username, pwd);
        service.setCredentials(credentials);
        service.setUrl(new URI(url));
        return service;
    }

    private static void getFirstMail(ExchangeService service) throws Exception {

        // Resolve a partial name against the Active Directory and the Contacts folder (in that order).
        NameResolutionCollection nameResolutions = service.resolveName("sathesh", ResolveNameSearchLocation.DirectoryOnly, true);
        System.out.println("nameResolutions==="+nameResolutions.getCount());

        for (NameResolution nameResolution : nameResolutions) {
            System.out.println("NAME==="+nameResolution.getMailbox().getName());
            Contact contact = nameResolution.getContact();
            System.out.println(" Contact===" + contact.getAlias());

            System.out.println("----------");
        }
    }
}
