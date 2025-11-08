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
import org.springframework.http.ResponseEntity;
import ua.univer.custodianNew.exceptions.DekraException;
import ua.univer.custodianNew.exceptions.UnprocessableEntityException;
import ua.univer.custodianNew.util.ConverterUtil;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.locks.ReentrantLock;

public class BaseController {

    public static final String DEKRA_URL_80 = "https://10.1.2.80/API_BP/cp_api.dll";
    public static final String DEKRA_URL_PROD = "https://10.1.2.204/API_BP/cp_api.dll";

    Logger logger = LoggerFactory.getLogger(BaseController.class);
    ReentrantLock lockMarch = new ReentrantLock();
    ReentrantLock lockUnMarsh = new ReentrantLock();

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
        THeaderRequest tHeaderRequest = Util.getHeaderRequest(isTest);
        tHeaderRequest.setRequestType(methodName);
        request.setHeader(tHeaderRequest);

        return request;
    }


    protected void saveXmlToWriter(Request request, Writer writer) {

        lockMarch.lock();
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
            throw new UnprocessableEntityException(message);
        }
        finally {
            lockMarch.unlock();
        }
    }


    protected String writeAndSendRequestWriteResponseToFile(Request request, String ipAddress, String prefix) {

        String response = writeAndSendRequest(request, ipAddress, prefix);
        Util.writeStringToFile(response, "Response", ".xml");

        return response;
    }


    protected String writeAndSendRequest(Request request, String ipAddress, String prefix) {

        String xmlString;
        try (Writer writer = new StringWriter()) {
            saveXmlToWriter(request, writer);
            xmlString = writer.toString();
        } catch (IOException e) {
            throw new DekraException("IOException in method writeAndSendRequest " + e.getMessage());
        }
        Util.writeStringToFile(xmlString, prefix, ".xml");

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ipAddress))
                .timeout(Duration.ofSeconds(70))
                .POST(HttpRequest.BodyPublishers.ofString(xmlString))
                .header("Content-Type", "application/xml")
                .build();

        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new DekraException("Error connecting to Dekra-service. Message - " + e.getMessage());
        }
        if (httpResponse.statusCode() == 500) {
            if(httpResponse.body() != null){
                logger.warn(httpResponse.body());
            }
            throw new DekraException("Status 500 at Dekra-service Response");
        }

        return httpResponse.body();
    }


    protected Responce getResponceFromXml(String dekraResponse) {

        Responce responce;
        lockUnMarsh.lock();
        try(StringReader reader = new StringReader(dekraResponse)){
            responce = (Responce) unmarshaller.unmarshal(reader);
        }
        catch (JAXBException e) {
            throw new DekraException("Error unmarshalling. DekraResponse is: " + dekraResponse);
        }
        finally {
            lockUnMarsh.unlock();
        }
        return responce;
    }


    protected ResponseEntity<String> getResponseEntity(long time, Request request, String ipAddress, String methodName) {
        String dekraResponse = writeAndSendRequestWriteResponseToFile(request, ipAddress, methodName);
        Responce responce = getResponceFromXml(dekraResponse);
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
