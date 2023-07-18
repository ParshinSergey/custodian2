package ua.univer.custodianNew.config;


import dmt.custodian2016.Request;
import dmt.custodian2016.Responce;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.http.HttpClient;
import java.util.Properties;

@Configuration
public class AppConfiguration {
    final static String PACKAGE = Request.class.getPackage().getName();
    public final static String DIRECTORY = "INBOX_OUTBOX";

    @Bean
    public Marshaller getMarshaller() throws JAXBException {
        File directory = new File(DIRECTORY);
        if(!directory.exists()) {
            directory.mkdir();
        }
        JAXBContext jc = JAXBContext.newInstance(PACKAGE);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    @Bean
    public Unmarshaller getUnmarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Responce.class);
        return context.createUnmarshaller();
    }




    @Bean
    public HttpClient getHttpClient(){

        // to disable hostname verification
        Properties props = System.getProperties();
        props.setProperty("jdk.internal.httpclient.disableHostnameVerification", Boolean.TRUE.toString());

        return HttpClient.newHttpClient();
    }

}


