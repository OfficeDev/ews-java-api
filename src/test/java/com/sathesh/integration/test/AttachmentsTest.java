package com.sathesh.integration.test;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.Attachment;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
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
public class AttachmentsTest {
    public static void main(String[] args) throws Exception {
        ExchangeService service;
        Properties properties = getProperties("./secret.properties");
        service = initConnection(properties.getProperty("username"), properties.getProperty("pwd"), properties.getProperty("url"));
        service.setTraceEnabled(true);
        getMailAttachments(service);
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

    private static void getMailAttachments(ExchangeService service) throws Exception {

        int counter=0;
        ItemView view = new ItemView(1);
        FindItemsResults<Item> findResults = null;
        findResults = service.findItems(WellKnownFolderName.Inbox, view);

        System.out.println("CONNECTION SUCCESSFUL");
        //convert the list of Item to List of VO
        for(Item item : findResults){
            System.out.println("MAIL::" + ++counter);
            System.out.println(item.getDateTimeReceived());
            System.out.println(item.getSubject());

            EmailMessage message = EmailMessage.bind(service, item.getId());

            for(Attachment attachment : message.getAttachments()){
                if (!attachment.getIsInline()){
                    System.out.println("Attachment ContentId: " + attachment.getContentId());
                    System.out.println("Attachment Name: " + attachment.getName());
                    System.out.println("Attachment Size: " + attachment.getSize());
                    System.out.println("Attachment Content Type: " + attachment.getContentType());
                    System.out.println("Attachment Item Id: " + item.getId());
                    System.out.println("Attachment Message Id: " + message.getId());
                    System.out.println("\n\n\n");

                    // FileAttachment fileAttachment = (FileAttachment) attachment;

                    //Recreating the object to test whether the attachment object can be stored and retrieved
                   FileAttachment fileAttachment = new FileAttachment(new Item(service));
                    fileAttachment.setId(attachment.getId());

                    service.getAttachment(fileAttachment, null, null);


                    fileAttachment.load("/Users/ssiva17/Desktop/test/"+attachment.getName());



                }
            }
        }
    }
}
