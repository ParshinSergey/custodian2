package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.univer.custodianNew.dto.*;
import ua.univer.custodianNew.exceptions.UnprocessableEntityException;
import ua.univer.custodianNew.util.DateTimeUtil;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static ua.univer.custodianNew.util.DateTimeUtil.period;
import static ua.univer.custodianNew.util.FileDownloadService.byteArrStorage;

@RestController
@RequestMapping(value = "/api/balance", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BalanceController extends BaseController {

    private static final String BALANCE_V2 = "BalanceV2";
    private static final String STATEMENT_OF_HOLDINGS_V2 = "Statement_of_HoldingsV2";
    private static final String STATEMENT_OF_TRANSACTION = "Statement_of_Transaction";
    private static final String STATEMENT_OF_TRANSACTION_V2 = "Statement_of_TransactionV2";

    private static final int OUTPUT_FORMAT_PDF = 0;
    private static final int STATEMENT_TEMPLATE = 191;
    private static final int STATEMENT_TEMPLATE_WITH_FACSIMILE = 204;

    public BalanceController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }


    @PostMapping(value = "/TEST/account")
    public ResponseEntity<String> balanceAccount(@RequestBody @Valid FormBalance form) {

        form.setTest(true);
        long time = System.nanoTime();
        String methodName = BALANCE_V2;
        Request request = getRequestWithHeader(methodName, form.isTest());

        TBalanceRequest balance = new TBalanceRequest();
        balance.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            balance.setISIN(tisin);
        }
        balance.setDateState(DateTimeUtil.oneBoxCalendar(form.getDateState()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setBalance(balance);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, form.ipAddress(), methodName);
    }


    @Operation(summary = "Виписка про стан рахунку (V2)")
    @PostMapping(value = "/accountV2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> balanceAccV2(@RequestBody @Valid FormBalance form) {

        long time = System.nanoTime();
        String methodName = STATEMENT_OF_HOLDINGS_V2;
        Request request = getRequestWithHeader(methodName, form.isTest());

        TStatementOfHoldingsRequest statement = new TStatementOfHoldingsRequest();
        statement.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            statement.setISIN(tisin);
        }
        statement.setDateState(DateTimeUtil.oneBoxCalendar(form.getDateState()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setStatementOfHoldingsRequest(statement);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, form.ipAddress(), methodName);
    }


    @PostMapping(value = "/TEST/statementV2")
    public ResponseEntity<String> statementV2(@RequestBody @Valid FormStatement form) {

        form.setTest(true);
        long time = System.nanoTime();
        String methodName = STATEMENT_OF_TRANSACTION_V2;
        Request request = getRequestWithHeader(methodName, form.isTest());

        TStatementOfTransactionsRequest statement = new TStatementOfTransactionsRequest();

        statement.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            statement.setISIN(tisin);
        }
        statement.setDateStart(DateTimeUtil.oneBoxCalendar(form.getDateStart()));
        statement.setDateStop(DateTimeUtil.oneBoxCalendar(form.getDateStop()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setStatementOfTransactionsRequest(statement);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, form.ipAddress(), methodName);

    }


    @PostMapping(value = "/TEST/statementBinary")
    public ResponseEntity<String> testStatementBinary(@RequestBody @Valid FormStatementFile form) throws IOException {
        form.setTest(true);
        return statementBinary(form);
    }


    @PostMapping(value = "/statementBinary")
    public ResponseEntity<String> statementBinary(@RequestBody @Valid FormStatementFile form) {

        long time = System.nanoTime();
        String methodName = STATEMENT_OF_TRANSACTION;
        logger.info("Method %s. Binary. %s".formatted(methodName, form.isTest() ? "TEST" : "Production"));

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest(form.isTest());
        tHeaderRequest.setRequestType(methodName);
        var binary = new THeaderRequest.Binary();
        binary.setBinary(true);
        binary.setTemplate(STATEMENT_TEMPLATE);
        binary.setOutputFormat(form.getOutputFormat().compareTo(-1) == 0 ? 0 : form.getOutputFormat());
        tHeaderRequest.setBinary(binary);
        request.setHeader(tHeaderRequest);

        TStatementOfTransactionsRequest statement = new TStatementOfTransactionsRequest();

        statement.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            statement.setISIN(tisin);
        }
        statement.setDateStart(DateTimeUtil.oneBoxCalendar(form.getDateStart()));
        statement.setDateStop(DateTimeUtil.oneBoxCalendar(form.getDateStop()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setStatementOfTransactionsRequest(statement);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, form.ipAddress(), methodName);

    }


    @Operation(summary = "Виписка про операції у форматі PDF")
    @PostMapping(value = "/statementPDF")
    public ResponseEntity<String> statementPDF(@RequestBody @Valid FormStatementPDF form) {

        long time = System.nanoTime();
        if (!period(form.getDateStart(), form.getDateStop())) throw new UnprocessableEntityException("період виписки не більше 2 рокiв");
        String methodName = STATEMENT_OF_TRANSACTION;
        logger.info("Method %s. StatementPDF. %s".formatted(methodName, form.isTest() ? "TEST" : "Production"));

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest(form.isTest());
        tHeaderRequest.setRequestType(methodName);
        var binary = new THeaderRequest.Binary();
        binary.setBinary(true);
        binary.setTemplate(STATEMENT_TEMPLATE_WITH_FACSIMILE);
        binary.setOutputFormat(OUTPUT_FORMAT_PDF);
        tHeaderRequest.setBinary(binary);
        request.setHeader(tHeaderRequest);

        TStatementOfTransactionsRequest statement = new TStatementOfTransactionsRequest();

        statement.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            statement.setISIN(tisin);
        }
        statement.setDateStart(DateTimeUtil.oneBoxCalendar(form.getDateStart()));
        statement.setDateStop(DateTimeUtil.oneBoxCalendar(form.getDateStop()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setStatementOfTransactionsRequest(statement);

        request.setBody(tbodyRequest);

        String dekraResponse = writeAndSendRequest(request, form.ipAddress(), "StatementPDF");
        Responce responce = getResponceFromXml(dekraResponse);
        String b64 = responce.getBody().getBinary();

        byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(b64);
        String fileName = String.format("%s_%s_%s_%s.pdf", form.getAccount(), form.getIsin(), form.getDateStart(), form.getDateStop());
        byteArrStorage.put(fileName, decoded);

        String customOrder = "http://10.1.2.80:8081/api/downloadPDF/" + fileName;
        String jsonStr = """
                {
                "login": "APIRest",
                "password": "679271971e515557305cbb263e89e145",
                "orderid": "%s",
                "%s": "%s",
                "fastdownload": 1
                }
                """.formatted(form.getOrderId(), form.getFieldName(), customOrder);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://univer.1b.app/api/orders/update/?dataFromBody=1"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .header("Content-Type", "application/json")
                .build();

        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());

        logger.info("time is " + (System.nanoTime() - time) / 1000000 + " ms");

        return ResponseEntity.ok().body("Order updated, and file can be downloaded from " + customOrder);

    }


    @PostMapping(value = "/TEST/getStatementPDF")
    public ResponseEntity<String> getStatementPDF(@RequestBody @Valid FormStatementPDF form) {

        form.setTest(true);
        long time = System.nanoTime();
        String methodName = STATEMENT_OF_TRANSACTION;
        logger.info("Method %s. getStatementPDF. %s".formatted(methodName, form.isTest() ? "TEST" : "Production"));

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest(form.isTest());
        tHeaderRequest.setRequestType(methodName);
        var binary = new THeaderRequest.Binary();
        binary.setBinary(true);
        binary.setTemplate(STATEMENT_TEMPLATE);
        binary.setOutputFormat(OUTPUT_FORMAT_PDF);
        tHeaderRequest.setBinary(binary);
        request.setHeader(tHeaderRequest);

        TStatementOfTransactionsRequest statement = new TStatementOfTransactionsRequest();

        statement.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            statement.setISIN(tisin);
        }
        statement.setDateStart(DateTimeUtil.oneBoxCalendar(form.getDateStart()));
        statement.setDateStop(DateTimeUtil.oneBoxCalendar(form.getDateStop()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setStatementOfTransactionsRequest(statement);

        request.setBody(tbodyRequest);

        String dekraResponse = writeAndSendRequestWriteResponseToFile(request, form.ipAddress(), methodName);
        Responce responce = getResponceFromXml(dekraResponse);
        String b64 = responce.getBody().getBinary();

        byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(b64);
        String fileName = String.format("%s_%s_%s_%s.pdf", form.getAccount(), form.getIsin(), form.getDateStart(), form.getDateStop());
        byteArrStorage.put(fileName, decoded);

        String customOrder = "http://localhost:8081/api/showPDF/" + fileName;
        String jsonStr = """
                {
                "login": "APIRest",
                "password": "679271971e515557305cbb263e89e145",
                "orderid": "%s",
                "customorder_TestDekrafail": "%s",
                "fastdownload": 1
                }
                """.formatted(form.getOrderId(), customOrder);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://univer.1b.app/api/orders/update/?dataFromBody=1"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> httpResponse;
        try {
            logger.info("before send to OneBox");
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            logger.info("after send to OneBox");
        } catch (IOException | InterruptedException e) {
            logger.warn("Error connecting to OneBox");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error connecting to OneBox. Message - " + e.getMessage());
        }

        if (httpResponse.statusCode() != 200){
            return ResponseEntity.internalServerError().body(httpResponse.body());
        }

        logger.info("time is " + (System.nanoTime() - time) / 1000000 + " ms");

        return ResponseEntity.ok().body("Order updated, and file can be downloaded from " + customOrder);

    }


}

