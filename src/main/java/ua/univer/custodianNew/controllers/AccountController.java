package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import jakarta.validation.Valid;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.univer.custodianNew.dto.FormFO;
import ua.univer.custodianNew.dto.FormGet;
import ua.univer.custodianNew.dto.FormReserveCancel;
import ua.univer.custodianNew.util.ConverterUtil;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

@RestController
@RequestMapping(value = "/api/request", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AccountController extends BaseController {

    private final static String NEW_ACCOUNT = "newAccount";

    public AccountController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }

/*

    @PostMapping (value = "/" + NEW_ACCOUNT + "Test")
    public ResponseEntity<String> getNewAccountTest (@RequestBody @Valid FormFO form, BindingResult result) throws IOException {

        if (result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(sb.toString(),HttpStatus.BAD_REQUEST);
        }

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest(form.getRequestID());
        tHeaderRequest.setRequestType(NEW_ACCOUNT);
        request.setHeader(tHeaderRequest);

        TbodyRequest tbodyRequest = Util.convertFormToNewAccount(form);
        request.setBody(tbodyRequest);

        Writer writer = new StringWriter();
        saveXmlToWriter(request, writer);
        File file = Util.getFile("ReqNewAcc", ".xml");
        Files.writeString(file.toPath(), writer.toString());

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(DECKRA_URL_FAKE))
                .POST(HttpRequest.BodyPublishers.ofString(writer.toString()))
                .header("Content-Type", "application/xml")
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error connecting to Deckra-service. Message - " + e.getMessage());
        }

        file = Util.getFile("ResponceNewAcc", ".txt");
        Files.writeString(file.toPath(), response.body());
        writer.close();

        return ResponseEntity.ok().body(response.body());
    }

*/

    @PostMapping(value = "/TEST/" + NEW_ACCOUNT + "FO")
    public ResponseEntity<String> testGetNewAccountFO (@RequestBody @Valid FormFO form, BindingResult result) throws IOException {

        logger.info("Method NewAccount TEST.");
        long time = System.nanoTime();

        if (result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(sb.toString(),HttpStatus.BAD_REQUEST);
        }

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest(form.getRequestID());
        tHeaderRequest.setRequestType(NEW_ACCOUNT);
        request.setHeader(tHeaderRequest);

        TbodyRequest tbodyRequest = Util.convertFormToNewAccount(form);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DECKRA_URL_80, "NewAccountTEST");
    }


    @PostMapping(value = "/" + NEW_ACCOUNT + "FO")
    public ResponseEntity<String> getNewAccountFO (@RequestBody @Valid FormFO form, BindingResult result) throws IOException {

        logger.info("Method NewAccount. Production");
        long time = System.nanoTime();

        if (result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(sb.toString(),HttpStatus.BAD_REQUEST);
        }

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest(form.getRequestID());
        tHeaderRequest.setRequestType(NEW_ACCOUNT);
        request.setHeader(tHeaderRequest);

        TbodyRequest tbodyRequest = Util.convertFormToNewAccount(form);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DECKRA_URL_PROD, "NewAccount");

     /*   String deckraResponse = writeAndSendRequestWriteResponseToFile(request, "NewAccount");
        Responce responce = getResponceFromXml(deckraResponse);
        String jsonResponse = ConverterUtil.objectToJson(responce);

        if (responce == null) {
            return ResponseEntity.internalServerError().body("Произошла ошибка " + deckraResponse);
        } else {
            if ("Error".equalsIgnoreCase(responce.getHeader().getResponceType())){
                String answer = String.format("{\"textmistake\": \"%s\"}", responce.getBody().getStatus().getMessage());
                return ResponseEntity.badRequest().body(answer);
            }
            else {
                logger.info("time is " + (System.nanoTime() - time) / 1000000 + " ms");
                return ResponseEntity.ok().body(jsonResponse);
            }
        }*/
    }



    @PostMapping(value = "/TEST/getAccount")
    public ResponseEntity<String> testGetAccount (@RequestBody @Valid FormGet form, BindingResult result) throws IOException {

        logger.info("Method GetAccount TEST.");
        long time = System.nanoTime();

        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest();
        tHeaderRequest.setRequestType("GetAccountNum");
        request.setHeader(tHeaderRequest);

        TAccountNumRequest accountNumRequest = new TAccountNumRequest();

        var nssmc = new TAccountNumRequest.NssmcClientTypeCode();
        nssmc.setValue(form.getNssmcClientTypeCode());
        accountNumRequest.setNssmcClientTypeCode(nssmc);

        var cnum = new TAccountNumRequest.CNUM();
        cnum.setValue(form.getCnum());
        accountNumRequest.setCNUM(cnum);

        var typeCode = new TAccountNumRequest.ClientTypeCode();
        if ("-1".equals(form.getClientTypeCode())){
            form.setClientTypeCode("0");
        }
        typeCode.setValue(form.getClientTypeCode());
        accountNumRequest.setClientTypeCode(typeCode);

        var country = new TAccountNumRequest.Country();
        country.setValue(form.getCountry());
        accountNumRequest.setCountry(country);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setAccountNum(accountNumRequest);
        request.setBody(tbodyRequest);

        String deckraResponse = writeAndSendRequestWriteResponseToFile(request, DECKRA_URL_80, "GetAccountTEST");

        Responce responce = getResponceFromXml(deckraResponse);

        if (responce == null) {
            return ResponseEntity.internalServerError().body("Произошла ошибка " + deckraResponse);
        } else {
            if ("Error".equalsIgnoreCase(responce.getHeader().getResponceType())){
                String answer = String.format("{\"textmistake\": \"%s\"}", responce.getBody().getStatus().getMessage());
                return ResponseEntity.badRequest().body(answer);
            }
            else {
                String accountNum = responce.getBody().getAccountNum().trim();
                int length = accountNum.length();
                String lastSymbols = accountNum.substring(length-6, length);
                String answer = String.format("{\"account_bill\": \"%s\", \"last_symbols\":\"%s\", \"CNUM\":\"%s\"}", accountNum, lastSymbols, form.getCnum());
                logger.info("time is " + (System.nanoTime() - time) / 1000000 + " ms");
                return ResponseEntity.ok().body(answer);
            }
        }

    }

    @PostMapping(value = "/getAccount")
    public ResponseEntity<String> getAccount (@RequestBody @Valid FormGet form, BindingResult result) throws IOException {

        logger.info("Method GetAccount. Production");
        long time = System.nanoTime();

        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest();
        tHeaderRequest.setRequestType("GetAccountNum");
        request.setHeader(tHeaderRequest);

        TAccountNumRequest accountNumRequest = new TAccountNumRequest();

        var nssmc = new TAccountNumRequest.NssmcClientTypeCode();
        nssmc.setValue(form.getNssmcClientTypeCode());
        accountNumRequest.setNssmcClientTypeCode(nssmc);

        var cnum = new TAccountNumRequest.CNUM();
        cnum.setValue(form.getCnum());
        accountNumRequest.setCNUM(cnum);

        var typeCode = new TAccountNumRequest.ClientTypeCode();
        if ("-1".equals(form.getClientTypeCode())){
            form.setClientTypeCode("0");
        }
        typeCode.setValue(form.getClientTypeCode());
        accountNumRequest.setClientTypeCode(typeCode);

        var country = new TAccountNumRequest.Country();
        country.setValue(form.getCountry());
        accountNumRequest.setCountry(country);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setAccountNum(accountNumRequest);
        request.setBody(tbodyRequest);

        String deckraResponse = writeAndSendRequestWriteResponseToFile(request, DECKRA_URL_PROD, "GetAccount");

        Responce responce = getResponceFromXml(deckraResponse);

        if (responce == null) {
            return ResponseEntity.internalServerError().body("Произошла ошибка " + deckraResponse);
        } else {
            if ("Error".equalsIgnoreCase(responce.getHeader().getResponceType())){
                String answer = String.format("{\"textmistake\": \"%s\"}", responce.getBody().getStatus().getMessage());
                return ResponseEntity.badRequest().body(answer);
            }
            else {
                String accountNum = responce.getBody().getAccountNum().trim();
                int length = accountNum.length();
                String lastSymbols = accountNum.substring(length-6, length);
                String answer = String.format("{\"account_bill\": \"%s\", \"last_symbols\":\"%s\", \"CNUM\":\"%s\"}", accountNum, lastSymbols, form.getCnum());
                logger.info("time is " + (System.nanoTime() - time) / 1000000 + " ms");
                return ResponseEntity.ok().body(answer);
            }
        }
    }



    @PostMapping(value = "/accountReserveCancel")
    public ResponseEntity<String> accountReserveCancel (@RequestBody FormReserveCancel form) throws IOException {

        logger.info("Method AccountNumReserveCancel. Production");
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest();
        tHeaderRequest.setRequestType("AccountNumReserveCancel");
        request.setHeader(tHeaderRequest);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setAccountNumReserveCancel(form.getAccount());
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DECKRA_URL_PROD, "ReserveCancel");
    }

}

