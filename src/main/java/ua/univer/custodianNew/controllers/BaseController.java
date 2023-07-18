package ua.univer.custodianNew.controllers;

import dmt.custodian2016.Request;
import dmt.custodian2016.Responce;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

public class BaseController {

    //public static final String DECKRA_URL = "https://10.1.2.80/API_BP/cp_api.dll";
    public static final String DECKRA_URL = "https://localhost/API_BP/cp_api.dll";
    public static final String DECKRA_URL_TEST = "http://localhost:8081/api/service/result";

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private Unmarshaller unmarshaller;
    private final Marshaller marshaller;
    protected final HttpClient httpClient;

    public BaseController(Marshaller marshaller, HttpClient httpClient) {
        this.marshaller = marshaller;
        this.httpClient = httpClient;
    }


    protected void saveToFileXml(Request request, File file) {
        try {
            marshaller.marshal(request, file);
        }
        catch (JAXBException ex) {
            String message = ex.getMessage();
            if (message == null) {
                message = ex.getCause().getMessage();
                if (message == null) {
                    message = "Unidentified JAXB error";
                }
            }
            System.out.println(message);
        }
    }

    protected void saveXmlToWriter(Request request, Writer writer) {
        try {
            marshaller.marshal(request, writer);
        }
        catch (JAXBException ex) {
            String message = ex.getMessage();
            if (message == null) {
                message = ex.getCause().getMessage();
                if (message == null) {
                    message = "Unidentified JAXB error";
                }
            }
            System.out.println(message);
        }
    }

    protected String writeAndSendRequestWriteResponseToFile(Request request, String prefix) throws IOException {

        Writer writer = new StringWriter();
        saveXmlToWriter(request, writer);
        File file = Util.getFile(prefix, ".xml");
        Files.writeString(file.toPath(), writer.toString());

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(DECKRA_URL))
                .POST(HttpRequest.BodyPublishers.ofString(writer.toString()))
                .header("Content-Type", "application/xml")
                .build();

        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.warn("Error connecting to Deckra-service");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error connecting to Deckra-service. Message - " + e.getMessage());
        }

        String response = httpResponse.body();

        try {
            file = Util.getFile("Response", ".xml");
            Files.writeString(file.toPath(), response);
        }
        catch (IOException e){
            logger.info("Error writing Output message to file.");
        }
        finally {
            writer.close();
        }

        return response;
    }

    protected Responce getResponceFromXml(String deckraResponse) {
        StringReader reader = new StringReader(deckraResponse);

        Responce responce = null;
        try {
            responce = (Responce) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            logger.warn("Error unmarshalling");
        }
        finally {
            reader.close();
        }
        return responce;
    }


}
