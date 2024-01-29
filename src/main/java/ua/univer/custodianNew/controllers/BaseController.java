package ua.univer.custodianNew.controllers;

import dmt.custodian2016.Request;
import dmt.custodian2016.Responce;
import dmt.custodian2016.THeaderRequest;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import ua.univer.custodianNew.exceptions.DekraException;
import ua.univer.custodianNew.util.ConverterUtil;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

public class BaseController {

    public static final String DEKRA_URL_80 = "https://10.1.2.80/API_BP/cp_api.dll";
    public static final String DEKRA_URL_PROD = "https://10.1.2.204/API_BP/cp_api.dll";
    public static final String DEKRA_URL_FAKE = "http://localhost:8081/api/service/result";

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private Unmarshaller unmarshaller;
    private final Marshaller marshaller;
    protected final HttpClient httpClient;

    public BaseController(Marshaller marshaller, HttpClient httpClient) {
        this.marshaller = marshaller;
        this.httpClient = httpClient;
    }


    protected Request getRequestWithHeader(String methodName, boolean isTest) {

        logger.info("Method %s. %s".formatted(methodName, isTest ? "TEST" : "Production"));

        Request request = new Request();

        THeaderRequest tHeaderRequest = isTest ? Util.getHeaderRequestTest() : Util.getHeaderRequest();
        tHeaderRequest.setRequestType(methodName);
        request.setHeader(tHeaderRequest);

        return request;
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

    protected String writeAndSendRequestWriteResponseToFile(Request request, String ipAddress, String prefix) throws IOException {

        String response = writeAndSendRequest(request, ipAddress, prefix);

        File file;
        try {
            file = Util.getFile("Response", ".xml");
            Files.writeString(file.toPath(), response);
        }
        catch (IOException e){
            logger.info("Error writing Output message to file.");
        }

        return response;
    }


    protected String writeAndSendRequest(Request request, String ipAddress, String prefix) throws IOException {

        Writer writer = new StringWriter();
        saveXmlToWriter(request, writer);
        File file = Util.getFile(prefix, ".xml");
        try {
            Files.writeString(file.toPath(), writer.toString());
        } catch (IOException e) {
            logger.warn("IOException при попытке записи в файл " + file.getPath());
            throw new RuntimeException(e);
        }

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ipAddress))
                .POST(HttpRequest.BodyPublishers.ofString(writer.toString()))
                .header("Content-Type", "application/xml")
                .build();
        writer.close();

        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.warn("Error connecting to Dekra-service");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error connecting to Dekra-service. Message - " + e.getMessage());
        }
        if (httpResponse.statusCode() == 500) {
            logger.warn("Status 500 at Dekra-service Response");
            throw new DekraException("Status 500 at Dekra-service Response");
        }

        return httpResponse.body();
    }


    protected Responce getResponceFromXml(String dekraResponse) {
        StringReader reader = new StringReader(dekraResponse);

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


    protected ResponseEntity<String> getResponseEntity(long time, Request request, String ipAddress, String methodName) throws IOException {
        String dekraResponse = writeAndSendRequestWriteResponseToFile(request, ipAddress, methodName);
        Responce responce = getResponceFromXml(dekraResponse);
       // String jsonResponse = ConverterUtil.objectToJson(responce);
        if (responce == null) {
            logger.warn("Произошла ошибка " + dekraResponse);
            return ResponseEntity.internalServerError().body("Произошла ошибка " + dekraResponse);
        } else {
            if ("Error".equalsIgnoreCase(responce.getHeader().getResponceType())) {
                String answer = String.format("{\"textmistake\": \"%s\"}", responce.getBody().getStatus().getMessage());
                return ResponseEntity.badRequest().body(answer);
            } else {
                logger.info("time is " + (System.nanoTime() - time) / 1000000 + " ms");
                return ResponseEntity.ok().body(ConverterUtil.objectToJson(responce));
            }
        }
    }


}
