package com.sathesh.integration.test;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.notification.EventType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.notification.PushSubscription;
import microsoft.exchange.webservices.data.property.complex.FolderId;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Properties;

/**
 * @author Sathesh Sivashanmugam
 * date 2019-02-22
 */
public class PushSubscriptionTest {
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
// Subscribe to push notifications on the Inbox folder, and only listen
// to "new mail" events.
        FolderId fid = new FolderId().getFolderIdFromWellKnownFolderName(WellKnownFolderName.Inbox);

      //  service.unsubscribe("JwBzbjZwcjA2bWI1MjQ1Lm5hbXByZDA2LnByb2Qub3V0bG9vay5jb20QAAAArfTSYXBLkUiuRRWptHA9i9MTOne3MdgIEAAAAP5/AwBgyMRNAAAAAAAAAAA=");
        PushSubscription pushSubscription = service.subscribeToPushNotifications(
                Collections.singletonList(fid),
               new URI("https://webhook.site/a8e9c1b8-e234-4dab-96a6-848350a5fca3"),
                1,
                                null,
                EventType.NewMail);
        System.out.println(pushSubscription.getId());

    }

}
