package com.sathesh;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;

import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @author Sathesh Sivashanmugam
 * date 2019-02-22
 */
public class TestConnection {
    public static void main(String[] args) throws Exception {
        ExchangeService service;
        Properties properties = new Properties();

        String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+current);


        properties.load(new FileInputStream("./secret.properties") );
        service = initConnection(properties.getProperty("username"), properties.getProperty("pwd"));
        getFirstMail(service);
    }

    private static ExchangeService initConnection(String username, String pwd) throws URISyntaxException {
        ExchangeService service = new ExchangeService();
        ExchangeCredentials credentials = new WebCredentials(username, pwd);
        service.setCredentials(credentials);
        service.setUrl(new URI("https://webmail.wipro.com/EWS/Exchange.asmx"));
        return service;
    }

    private static void getFirstMail(ExchangeService service) throws Exception {

        int counter=0;
        ItemView view = new ItemView(1);
        FindItemsResults<Item> findResults = null;
        findResults = service.findItems(WellKnownFolderName.Inbox, view);

        //convert the list of Item to List of VO
        for(Item item : findResults){
            System.out.println("MAIL::" + ++counter);
            System.out.println(item.getDateTimeReceived());
            System.out.println(item.getSubject());
        }
    }
}
