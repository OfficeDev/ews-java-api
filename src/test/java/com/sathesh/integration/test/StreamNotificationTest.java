package com.sathesh.integration.test;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.notification.EventType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.response.GetItemResponse;
import microsoft.exchange.webservices.data.core.response.ServiceResponseCollection;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.notification.*;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.ItemId;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @author Sathesh Sivashanmugam
 * date 2019-02-22
 */
public class StreamNotificationTest{
    public static void main(String[] args) throws Exception {
        ExchangeService service;
        Properties properties = getProperties("./secret.properties");
        service = initConnection(properties.getProperty("username"), properties.getProperty("pwd"), properties.getProperty("url"));
        (new StreamSubscription()).subscriptionTesting(service);
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

    public static class StreamSubscription  implements StreamingSubscriptionConnection.INotificationEventDelegate, StreamingSubscriptionConnection.ISubscriptionErrorDelegate {

        ExchangeService service;
        private void subscriptionTesting(ExchangeService service) throws Exception {

            this.service = service;
            WellKnownFolderName sd = WellKnownFolderName.Inbox;
            FolderId folderId = new FolderId(sd);

            List folder = new ArrayList<FolderId>();
            folder.add(folderId);

            StreamingSubscription subscription = service.subscribeToStreamingNotifications(folder, EventType.NewMail);

            StreamingSubscriptionConnection conn = new StreamingSubscriptionConnection(service, 30);
            conn.addSubscription(subscription);
            conn.addOnNotificationEvent(this);
            conn.addOnDisconnect(this);
            conn.open();

            System.out.println("listening..");
            Thread.sleep(30*60*1000);

        }

        void connection_OnDisconnect(Object sender, SubscriptionErrorEventArgs args) {
            System.out.println("disconnecting........");
        }

        void connection_OnNotificationEvent(Object sender, NotificationEventArgs args) throws Exception {
            System.out.println("==== hi notification event==========");
            // First retrieve the IDs of all the new emails
            List<ItemId> newMailsIds = new ArrayList<ItemId>();

            Iterator<NotificationEvent> it = args.getEvents().iterator();
            while (it.hasNext()) {
                ItemEvent itemEvent = (ItemEvent) it.next();
                if (itemEvent != null) {
                    newMailsIds.add(itemEvent.getItemId());
                }
            }

            if (newMailsIds.size() > 0) {
                // Now retrieve the Subject property of all the new emails in one call to EWS.
                ServiceResponseCollection<GetItemResponse> responses = service.bindToItems(newMailsIds, new PropertySet(ItemSchema.Subject));
                System.out.println("count=======" + responses.getCount());

                //this.listBox1.Items.Add(string.Format("{0} new mail(s)", newMailsIds.Count));

                for (GetItemResponse response : responses) {
                    System.out.println("count=======" + responses.getClass().getName());
                    System.out.println("subject=======" + response.getItem().getSubject());
                    // Console.WriteLine("subject====" + response.Item.Subject);
                }
            }
        }

        @Override
        public void notificationEventDelegate(Object sender, NotificationEventArgs args) {
            try {
                this.connection_OnNotificationEvent(sender, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void subscriptionErrorDelegate(Object sender, SubscriptionErrorEventArgs args) {
            try {
                connection_OnDisconnect(sender, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
