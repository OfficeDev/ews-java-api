package com.sathesh.integration.test;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.notification.EventType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.notification.GetEventsResults;
import microsoft.exchange.webservices.data.notification.ItemEvent;
import microsoft.exchange.webservices.data.notification.PullSubscription;
import microsoft.exchange.webservices.data.property.complex.FolderId;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Sathesh Sivashanmugam
 * date 2019-02-22
 */
public class PullSubscriptionTest {
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

        // Subscribe to pull notifications in the Inbox folder, and get notified when a new mail is received, when an item or folder is created, or when an item or folder is deleted.
        List folder = new ArrayList();
        folder.add(new FolderId().getFolderIdFromWellKnownFolderName(WellKnownFolderName.Inbox));

        PullSubscription subscription = service.subscribeToPullNotifications(folder, 1

              /* timeOut: the subscription will end if the server is not polled within 5 minutes. */, "AQAAAE0/7+Wfk0NCmqLvwJ4Ma7Mmv0+PAAAAAAA=" /* watermark: null to start a new subscription. */, EventType.NewMail, EventType.Created, EventType.Deleted);


       pullEvents(service, subscription, folder);

    }

    private static void pullEvents(ExchangeService service, PullSubscription subscription,  List folder ) {
        // Wait a couple minutes, then poll the server for new events.
        GetEventsResults events1;
        PullSubscription ps = null;
        try {
            System.out.println("ID 1 " + subscription.getId());
            events1 = subscription.getEvents();
            System.out.println("ID 2 " + subscription.getId());

            System.out.println("THIS WATERMARK: " + subscription.getWaterMark());
            //Creating a new subscription object just to check whether this object can be created from scratch.
            // Ideally we can call the same subscription obj forever.
            // The same subscription will get updated with the new id and watermark after each call to the subscription.getEvents()
            ps = new PullSubscription(service);

            ps.setId(subscription.getId());
            ps.setWaterMark(subscription.getWaterMark());

        // Loop through all item-related events.
        for (ItemEvent itemEvent : events1.getItemEvents()) {
            if (itemEvent.getEventType() == EventType.NewMail) {
                EmailMessage message = EmailMessage.bind(service, itemEvent.getItemId());
                System.out.println("NEW EMAIL: " + message.getSubject() + " BODY: " + message.getBody());
            } else if (itemEvent.getEventType() == EventType.Created) {
                Item item = Item.bind(service, itemEvent.getItemId());
            } else if (itemEvent.getEventType() == EventType.Deleted) {
                break;
            }
        }
            System.out.println("-----------------------------");
            Thread.sleep(1000 * 15);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        pullEvents(service, ps, folder);


    }
}
