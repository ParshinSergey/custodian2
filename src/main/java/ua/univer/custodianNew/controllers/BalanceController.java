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
import ua.univer.custodianNew.dto.*;
import ua.univer.custodianNew.util.DateTimeUtil;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static ua.univer.custodianNew.config.AppConfiguration.DIRECTORY;
import static ua.univer.custodianNew.util.FileDownloadService.byteArrStorage;
import static ua.univer.custodianNew.util.FileDownloadService.fileStorage;

@RestController
@RequestMapping(value = "/api/balance", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BalanceController extends BaseController {


    public BalanceController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }

    @PostMapping(value = "/TEST/account")
    public ResponseEntity<String> balanceAccount(@RequestBody FormBalance form) throws IOException {

        logger.info("Method BalanceV2.");
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest();
        tHeaderRequest.setRequestType("BalanceV2");
        request.setHeader(tHeaderRequest);

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

        return getResponseEntity(time, request, DECKRA_URL_80,"Balance");
    }

    @PostMapping(value = "/accountV2")
    public ResponseEntity<String> balanceAccV2(@RequestBody FormBalance form) throws IOException {

        logger.info("Statement_of_HoldingsV2. Production");
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest();
        tHeaderRequest.setRequestType("Statement_of_HoldingsV2");
        request.setHeader(tHeaderRequest);

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

        return getResponseEntity(time, request, DECKRA_URL_PROD, "Statement");
    }

    @PostMapping(value = "/TEST/transactionV2")
    public ResponseEntity<String> transactionV2(@RequestBody @Valid FormTransaction form, BindingResult result) throws IOException {

        logger.info("Statement_of_TransactionV2.");
        if (result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest();
        tHeaderRequest.setRequestType("Statement_of_TransactionV2");
        request.setHeader(tHeaderRequest);

        TStatementOfTransactionsRequest transaction = new TStatementOfTransactionsRequest();

        transaction.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            transaction.setISIN(tisin);
        }
        transaction.setDateStart(DateTimeUtil.oneBoxCalendar(form.getDateStart()));
        transaction.setDateStop(DateTimeUtil.oneBoxCalendar(form.getDateStop()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setStatementOfTransactionsRequest(transaction);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DECKRA_URL_80, "Transaction");

    }

    @PostMapping(value = "/transactionBinary")
    public ResponseEntity<String> transactionBinary(@RequestBody @Valid FormTransactionFile form, BindingResult result) throws IOException {

        logger.info("Statement_of_Transaction. Binary. Production");
        if (result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest();
        tHeaderRequest.setRequestType("Statement_of_Transaction");

        // tHeaderRequest.setSourceAPPidentity("3000350A-429D-4632-81B7-B31C02C7D980");

        var binary = new THeaderRequest.Binary();
        binary.setBinary(true);
        binary.setTemplate(191);
        binary.setOutputFormat(form.getOutputFormat().compareTo(-1) == 0 ? 0 : form.getOutputFormat());
        tHeaderRequest.setBinary(binary);
        request.setHeader(tHeaderRequest);

        TStatementOfTransactionsRequest transaction = new TStatementOfTransactionsRequest();

        transaction.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            transaction.setISIN(tisin);
        }
        transaction.setDateStart(DateTimeUtil.oneBoxCalendar(form.getDateStart()));
        transaction.setDateStop(DateTimeUtil.oneBoxCalendar(form.getDateStop()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setStatementOfTransactionsRequest(transaction);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DECKRA_URL_PROD, "TransactionBinary");

    }

    @PostMapping(value = "/TEST/transactionBinary")
    public ResponseEntity<String> testTransactionBinary(@RequestBody @Valid FormTransactionFile form, BindingResult result) throws IOException {

        logger.info("Statement_of_Transaction. Binary. TEST.");
        if (result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest();
        tHeaderRequest.setRequestType("Statement_of_Transaction");
        var binary = new THeaderRequest.Binary();
        binary.setBinary(true);
        binary.setTemplate(191);
        binary.setOutputFormat(form.getOutputFormat().compareTo(-1) == 0 ? 0 : form.getOutputFormat());
        tHeaderRequest.setBinary(binary);
        request.setHeader(tHeaderRequest);

        TStatementOfTransactionsRequest transaction = new TStatementOfTransactionsRequest();

        transaction.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            transaction.setISIN(tisin);
        }
        transaction.setDateStart(DateTimeUtil.oneBoxCalendar(form.getDateStart()));
        transaction.setDateStop(DateTimeUtil.oneBoxCalendar(form.getDateStop()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setStatementOfTransactionsRequest(transaction);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DECKRA_URL_80, "TransactionBinary");

    }



    @PostMapping(value = "/TEST/transactionFile")
    public ResponseEntity<String> testTransactionFile(@RequestBody @Valid FormTransactionPDF form, BindingResult result) throws IOException {

        logger.info("Statement_of_Transaction. Through File. TEST.");
        if (result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest();
        tHeaderRequest.setRequestType("Statement_of_Transaction");
        var binary = new THeaderRequest.Binary();
        binary.setBinary(true);
        binary.setTemplate(191);
        binary.setOutputFormat(0);
        tHeaderRequest.setBinary(binary);
        request.setHeader(tHeaderRequest);

        TStatementOfTransactionsRequest transaction = new TStatementOfTransactionsRequest();

        transaction.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            transaction.setISIN(tisin);
        }
        transaction.setDateStart(DateTimeUtil.oneBoxCalendar(form.getDateStart()));
        transaction.setDateStop(DateTimeUtil.oneBoxCalendar(form.getDateStop()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setStatementOfTransactionsRequest(transaction);

        request.setBody(tbodyRequest);

        String dekraResponse = writeAndSendRequestWriteResponseToFile(request, DECKRA_URL_80, "TransactionFile");
        Responce responce = getResponceFromXml(dekraResponse);
        String b64 = responce.getBody().getBinary();

        String prefix = String.format("%s_%s_%s_%s", form.getAccount(), form.getIsin(), form.getDateStart(), form.getDateStop());
        //File file = Util.getFile(prefix, ".pdf");
       // Files.createFile(Path.of(DIRECTORY + prefix + ".pdf"));
        File file = new File(DIRECTORY,  prefix + ".pdf");

        byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(b64);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(decoded);
        fos.flush();
        fos.close();
        fileStorage.put(Integer.valueOf(form.getOrderId()), file);

       /* byte[] decoder = Base64.getDecoder().decode(b64Modified);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(decoder);
        fos.flush();
        fos.close();
        logger.info("PDF File Saved");*/

        //String customorder = file.getAbsolutePath();
        String customorder = "http://10.1.2.80:8081/api/downloadFile/" + form.getOrderId();
        String jsonStr = """
                {
                "login": "APIRest",
                "password": "679271971e515557305cbb263e89e145",
                "orderid": "%s",
                "customorder_TestDekrafail": "%s"
                }
                """.formatted(form.getOrderId(), customorder);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://univer.1b.app/api/orders/update/?dataFromBody=1"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.warn("Error connecting to OneBox");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error connecting to OneBox. Message - " + e.getMessage());
        }

        if (httpResponse.statusCode() != 200){
            return ResponseEntity.internalServerError().body(httpResponse.body());
        }

        logger.info("time is " + (System.nanoTime() - time) / 1000000 + " ms");

        return ResponseEntity.ok().body("Order updated, and file can be downloaded from " + customorder);

    }



    @PostMapping(value = "/TEST/transactionPDF")
    public ResponseEntity<String> transactionPDF(@RequestBody @Valid FormTransactionPDF form, BindingResult result) throws IOException {

        logger.info("Statement_of_Transaction. transactionPDF. TEST");
        if (result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest();
        tHeaderRequest.setRequestType("Statement_of_Transaction");
        var binary = new THeaderRequest.Binary();
        binary.setBinary(true);
        binary.setTemplate(191);
        binary.setOutputFormat(0);
        tHeaderRequest.setBinary(binary);
        request.setHeader(tHeaderRequest);

        TStatementOfTransactionsRequest transaction = new TStatementOfTransactionsRequest();

        transaction.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            transaction.setISIN(tisin);
        }
        transaction.setDateStart(DateTimeUtil.oneBoxCalendar(form.getDateStart()));
        transaction.setDateStop(DateTimeUtil.oneBoxCalendar(form.getDateStop()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setStatementOfTransactionsRequest(transaction);

        request.setBody(tbodyRequest);

        String dekraResponse = writeAndSendRequestWriteResponseToFile(request, DECKRA_URL_80, "TransactionPDF");
        Responce responce = getResponceFromXml(dekraResponse);
        String b64 = responce.getBody().getBinary();

       /* Writer writer = new StringWriter();
        saveXmlToWriter(request, writer);
        File file1 = Util.getFile("TransactionPDF", ".xml");
        Files.writeString(file1.toPath(), writer.toString());


        HttpRequest httpRequest1 = HttpRequest.newBuilder()
                .uri(URI.create(DECKRA_URL_80))
                .POST(HttpRequest.BodyPublishers.ofString(writer.toString()))
                .header("Content-Type", "application/xml")
                .build();

        writer.close();

        HttpResponse<String> httpResponse1 = null;
        try {
            httpResponse1 = httpClient.send(httpRequest1, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.warn("Error connecting to Deckra-service");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error connecting to Deckra-service. Message - " + e.getMessage());
        }

        String response = httpResponse1.body();

        Responce responce = getResponceFromXml(response);
        String b64 = responce.getBody().getBinary();
*/

        byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(b64);
        String fileName = String.format("%s_%s_%s_%s.pdf", form.getAccount(), form.getIsin(), form.getDateStart(), form.getDateStop());
        byteArrStorage.put(fileName, decoded);

        String customOrder = "http://10.1.2.80:8081/api/downloadPDF/" + fileName;
        String jsonStr = """
                {
                "login": "APIRest",
                "password": "679271971e515557305cbb263e89e145",
                "orderid": "%s",
                "customorder_TestDekrafail": "%s"
                }
                """.formatted(form.getOrderId(), customOrder);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://univer.1b.app/api/orders/update/?dataFromBody=1"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
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


    @PostMapping(value = "/TEST/getTransactionPDF")
    public ResponseEntity<String> getTransactionPDF(@RequestBody @Valid FormTransactionPDF form, BindingResult result) throws IOException {

        logger.info("Statement_of_Transaction. getTransactionPDF. TEST");
        if (result.hasErrors()){
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest();
        tHeaderRequest.setRequestType("Statement_of_Transaction");
        var binary = new THeaderRequest.Binary();
        binary.setBinary(true);
        binary.setTemplate(191);
        binary.setOutputFormat(0);
        tHeaderRequest.setBinary(binary);
        request.setHeader(tHeaderRequest);

        TStatementOfTransactionsRequest transaction = new TStatementOfTransactionsRequest();

        transaction.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            transaction.setISIN(tisin);
        }
        transaction.setDateStart(DateTimeUtil.oneBoxCalendar(form.getDateStart()));
        transaction.setDateStop(DateTimeUtil.oneBoxCalendar(form.getDateStop()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setStatementOfTransactionsRequest(transaction);

        request.setBody(tbodyRequest);

        String dekraResponse = writeAndSendRequestWriteResponseToFile(request, DECKRA_URL_80, "getTransactionPDF");
        Responce responce = getResponceFromXml(dekraResponse);
        String b64 = responce.getBody().getBinary();

        byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(b64);
        String fileName = String.format("%s_%s_%s_%s.pdf", form.getAccount(), form.getIsin(), form.getDateStart(), form.getDateStop());
        byteArrStorage.put(fileName, decoded);

        String customOrder = "http://10.1.2.80:8081/api/showPDF/" + fileName;
        String jsonStr = """
                {
                "login": "APIRest",
                "password": "679271971e515557305cbb263e89e145",
                "orderid": "%s",
                "customorder_TestDekrafail": "%s"
                }
                """.formatted(form.getOrderId(), customOrder);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://univer.1b.app/api/orders/update/?dataFromBody=1"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
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


/*
    private static String answer(Responce responce) {
        TStatementOfHoldingRowsV2 rows = (TStatementOfHoldingRowsV2) responce.getBody().getBalance().getRows();
        StringBuilder sb = new StringBuilder();
        List<TStatementOfHoldingRowV2> row = rows.getRow();
        for (var item : row) {
            sb.append("ISIN " + item.getISIN().getISIN() + "\n")
                    .append("Depositary " + item.getISIN().getDepositary() + "\n")
                    .append("ShortName " + item.getISIN().getIssuer().getShortName() + "\n" )
                    .append("IDCode " + item.getISIN().getIssuer().getIDCode() + "\n")
                    .append("Code " + item.getISIN().getType().getCode() + "\n")
                    .append("Name " + item.getISIN().getType().getName() + "\n")
                    .append("Nominal " + item.getISIN().getNominal().getNominal() + "\n")
                    .append("Currency " + item.getISIN().getNominal().getCurrency() + "\n")

                    .append("Quantity " + item.getQuantity() + "\n")
                    .append("faceAmount " + item.getFaceAmount() + "\n")
                    .append("\n");
        }
        return sb.toString();
    }*/

}

