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
import ua.univer.custodianNew.util.ConverterUtil;
import ua.univer.custodianNew.util.DateTimeUtil;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static ua.univer.custodianNew.config.AppConfiguration.DIRECTORY;
import static ua.univer.custodianNew.util.FileDownloadUtil.byteArrStorage;
import static ua.univer.custodianNew.util.FileDownloadUtil.fileStorage;

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

    @PostMapping(value = "/transactionFile")
    public ResponseEntity<String> transactionFile(@RequestBody @Valid FormTransactionFile form, BindingResult result) throws IOException {

        logger.info("Statement_of_Transaction. File. Production");
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

        return getResponseEntity(time, request, DECKRA_URL_PROD, "TransactionFile");

    }

    @PostMapping(value = "/TEST/transactionFile")
    public ResponseEntity<String> testTransactionFile(@RequestBody @Valid FormTransactionFile form, BindingResult result) throws IOException {

        logger.info("Statement_of_Transaction. File. TEST.");
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

        return getResponseEntity(time, request, DECKRA_URL_80, "TransactionFile");

    }



    @PostMapping(value = "/TEST/transactionPDF")
    public ResponseEntity<String> testTransactionPDF(@RequestBody @Valid FormTransactionPDF form, BindingResult result) throws IOException {

        logger.info("Statement_of_Transaction. FilePDF. TEST.");
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
        //String b64Modified = b64.replace("#x0D", "");
        //String b65 = "JVBERi0xLjMNCiXi48/TDQoNCjEgMCBvYmoNCjw8DQovVHlwZSAvQ2F0YWxvZw0KL091dGxpbmVzIDIgMCBSDQovUGFnZXMgMyAwIFINCj4+DQplbmRvYmoNCg0KMiAwIG9iag0KPDwNCi9UeXBlIC9PdXRsaW5lcw0KL0NvdW50IDANCj4+DQplbmRvYmoNCg0KMyAwIG9iag0KPDwNCi9UeXBlIC9QYWdlcw0KL0NvdW50IDINCi9LaWRzIFsgNCAwIFIgNiAwIFIgXSANCj4+DQplbmRvYmoNCg0KNCAwIG9iag0KPDwNCi9UeXBlIC9QYWdlDQovUGFyZW50IDMgMCBSDQovUmVzb3VyY2VzIDw8DQovRm9udCA8PA0KL0YxIDkgMCBSIA0KPj4NCi9Qcm9jU2V0IDggMCBSDQo+Pg0KL01lZGlhQm94IFswIDAgNjEyLjAwMDAgNzkyLjAwMDBdDQovQ29udGVudHMgNSAwIFINCj4+DQplbmRvYmoNCg0KNSAwIG9iag0KPDwgL0xlbmd0aCAxMDc0ID4+DQpzdHJlYW0NCjIgSg0KQlQNCjAgMCAwIHJnDQovRjEgMDAyNyBUZg0KNTcuMzc1MCA3MjIuMjgwMCBUZA0KKCBBIFNpbXBsZSBQREYgRmlsZSApIFRqDQpFVA0KQlQNCi9GMSAwMDEwIFRmDQo2OS4yNTAwIDY4OC42MDgwIFRkDQooIFRoaXMgaXMgYSBzbWFsbCBkZW1vbnN0cmF0aW9uIC5wZGYgZmlsZSAtICkgVGoNCkVUDQpCVA0KL0YxIDAwMTAgVGYNCjY5LjI1MDAgNjY0LjcwNDAgVGQNCigganVzdCBmb3IgdXNlIGluIHRoZSBWaXJ0dWFsIE1lY2hhbmljcyB0dXRvcmlhbHMuIE1vcmUgdGV4dC4gQW5kIG1vcmUgKSBUag0KRVQNCkJUDQovRjEgMDAxMCBUZg0KNjkuMjUwMCA2NTIuNzUyMCBUZA0KKCB0ZXh0LiBBbmQgbW9yZSB0ZXh0LiBBbmQgbW9yZSB0ZXh0LiBBbmQgbW9yZSB0ZXh0LiApIFRqDQpFVA0KQlQNCi9GMSAwMDEwIFRmDQo2OS4yNTAwIDYyOC44NDgwIFRkDQooIEFuZCBtb3JlIHRleHQuIEFuZCBtb3JlIHRleHQuIEFuZCBtb3JlIHRleHQuIEFuZCBtb3JlIHRleHQuIEFuZCBtb3JlICkgVGoNCkVUDQpCVA0KL0YxIDAwMTAgVGYNCjY5LjI1MDAgNjE2Ljg5NjAgVGQNCiggdGV4dC4gQW5kIG1vcmUgdGV4dC4gQm9yaW5nLCB6enp6ei4gQW5kIG1vcmUgdGV4dC4gQW5kIG1vcmUgdGV4dC4gQW5kICkgVGoNCkVUDQpCVA0KL0YxIDAwMTAgVGYNCjY5LjI1MDAgNjA0Ljk0NDAgVGQNCiggbW9yZSB0ZXh0LiBBbmQgbW9yZSB0ZXh0LiBBbmQgbW9yZSB0ZXh0LiBBbmQgbW9yZSB0ZXh0LiBBbmQgbW9yZSB0ZXh0LiApIFRqDQpFVA0KQlQNCi9GMSAwMDEwIFRmDQo2OS4yNTAwIDU5Mi45OTIwIFRkDQooIEFuZCBtb3JlIHRleHQuIEFuZCBtb3JlIHRleHQuICkgVGoNCkVUDQpCVA0KL0YxIDAwMTAgVGYNCjY5LjI1MDAgNTY5LjA4ODAgVGQNCiggQW5kIG1vcmUgdGV4dC4gQW5kIG1vcmUgdGV4dC4gQW5kIG1vcmUgdGV4dC4gQW5kIG1vcmUgdGV4dC4gQW5kIG1vcmUgKSBUag0KRVQNCkJUDQovRjEgMDAxMCBUZg0KNjkuMjUwMCA1NTcuMTM2MCBUZA0KKCB0ZXh0LiBBbmQgbW9yZSB0ZXh0LiBBbmQgbW9yZSB0ZXh0LiBFdmVuIG1vcmUuIENvbnRpbnVlZCBvbiBwYWdlIDIgLi4uKSBUag0KRVQNCmVuZHN0cmVhbQ0KZW5kb2JqDQoNCjYgMCBvYmoNCjw8DQovVHlwZSAvUGFnZQ0KL1BhcmVudCAzIDAgUg0KL1Jlc291cmNlcyA8PA0KL0ZvbnQgPDwNCi9GMSA5IDAgUiANCj4+DQovUHJvY1NldCA4IDAgUg0KPj4NCi9NZWRpYUJveCBbMCAwIDYxMi4wMDAwIDc5Mi4wMDAwXQ0KL0NvbnRlbnRzIDcgMCBSDQo+Pg0KZW5kb2JqDQoNCjcgMCBvYmoNCjw8IC9MZW5ndGggNjc2ID4+DQpzdHJlYW0NCjIgSg0KQlQNCjAgMCAwIHJnDQovRjEgMDAyNyBUZg0KNTcuMzc1MCA3MjIuMjgwMCBUZA0KKCBTaW1wbGUgUERGIEZpbGUgMiApIFRqDQpFVA0KQlQNCi9GMSAwMDEwIFRmDQo2OS4yNTAwIDY4OC42MDgwIFRkDQooIC4uLmNvbnRpbnVlZCBmcm9tIHBhZ2UgMS4gWWV0IG1vcmUgdGV4dC4gQW5kIG1vcmUgdGV4dC4gQW5kIG1vcmUgdGV4dC4gKSBUag0KRVQNCkJUDQovRjEgMDAxMCBUZg0KNjkuMjUwMCA2NzYuNjU2MCBUZA0KKCBBbmQgbW9yZSB0ZXh0LiBBbmQgbW9yZSB0ZXh0LiBBbmQgbW9yZSB0ZXh0LiBBbmQgbW9yZSB0ZXh0LiBBbmQgbW9yZSApIFRqDQpFVA0KQlQNCi9GMSAwMDEwIFRmDQo2OS4yNTAwIDY2NC43MDQwIFRkDQooIHRleHQuIE9oLCBob3cgYm9yaW5nIHR5cGluZyB0aGlzIHN0dWZmLiBCdXQgbm90IGFzIGJvcmluZyBhcyB3YXRjaGluZyApIFRqDQpFVA0KQlQNCi9GMSAwMDEwIFRmDQo2OS4yNTAwIDY1Mi43NTIwIFRkDQooIHBhaW50IGRyeS4gQW5kIG1vcmUgdGV4dC4gQW5kIG1vcmUgdGV4dC4gQW5kIG1vcmUgdGV4dC4gQW5kIG1vcmUgdGV4dC4gKSBUag0KRVQNCkJUDQovRjEgMDAxMCBUZg0KNjkuMjUwMCA2NDAuODAwMCBUZA0KKCBCb3JpbmcuICBNb3JlLCBhIGxpdHRsZSBtb3JlIHRleHQuIFRoZSBlbmQsIGFuZCBqdXN0IGFzIHdlbGwuICkgVGoNCkVUDQplbmRzdHJlYW0NCmVuZG9iag0KDQo4IDAgb2JqDQpbL1BERiAvVGV4dF0NCmVuZG9iag0KDQo5IDAgb2JqDQo8PA0KL1R5cGUgL0ZvbnQNCi9TdWJ0eXBlIC9UeXBlMQ0KL05hbWUgL0YxDQovQmFzZUZvbnQgL0hlbHZldGljYQ0KL0VuY29kaW5nIC9XaW5BbnNpRW5jb2RpbmcNCj4+DQplbmRvYmoNCg0KMTAgMCBvYmoNCjw8DQovQ3JlYXRvciAoUmF2ZSBcKGh0dHA6Ly93d3cubmV2cm9uYS5jb20vcmF2ZVwpKQ0KL1Byb2R1Y2VyIChOZXZyb25hIERlc2lnbnMpDQovQ3JlYXRpb25EYXRlIChEOjIwMDYwMzAxMDcyODI2KQ0KPj4NCmVuZG9iag0KDQp4cmVmDQowIDExDQowMDAwMDAwMDAwIDY1NTM1IGYNCjAwMDAwMDAwMTkgMDAwMDAgbg0KMDAwMDAwMDA5MyAwMDAwMCBuDQowMDAwMDAwMTQ3IDAwMDAwIG4NCjAwMDAwMDAyMjIgMDAwMDAgbg0KMDAwMDAwMDM5MCAwMDAwMCBuDQowMDAwMDAxNTIyIDAwMDAwIG4NCjAwMDAwMDE2OTAgMDAwMDAgbg0KMDAwMDAwMjQyMyAwMDAwMCBuDQowMDAwMDAyNDU2IDAwMDAwIG4NCjAwMDAwMDI1NzQgMDAwMDAgbg0KDQp0cmFpbGVyDQo8PA0KL1NpemUgMTENCi9Sb290IDEgMCBSDQovSW5mbyAxMCAwIFINCj4+DQoNCnN0YXJ0eHJlZg0KMjcxNA0KJSVFT0YNCg==";


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

        HttpResponse<String> httpResponse = null;
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



    @PostMapping(value = "/transactionPDF")
    public ResponseEntity<String> transactionPDF(@RequestBody @Valid FormTransactionPDF form, BindingResult result) throws IOException {

        logger.info("Statement_of_Transaction. FilePDF.");
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


        Writer writer = new StringWriter();
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

        byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(b64);
        String fileName = String.format("%s_%s_%s_%s.pdf", form.getAccount(), form.getIsin(), form.getDateStart(), form.getDateStop());
        byteArrStorage.put(fileName, decoded);

        String customorder = "http://10.1.2.80:8081/api/downloadPDF/" + fileName;
        String jsonStr = """
                {
                "login": "APIRest",
                "password": "679271971e515557305cbb263e89e145",
                "orderid": "%s",
                "customorder_TestDekrafail": "%s"
                }
                """.formatted(form.getOrderId(), customorder);

        HttpRequest httpRequest2 = HttpRequest.newBuilder()
                .uri(URI.create("https://univer.1b.app/api/orders/update/?dataFromBody=1"))
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> httpResponse2 = null;
        try {
            httpResponse2 = httpClient.send(httpRequest2, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.warn("Error connecting to OneBox");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error connecting to OneBox. Message - " + e.getMessage());
        }

        if (httpResponse2.statusCode() != 200){
            return ResponseEntity.internalServerError().body(httpResponse2.body());
        }

        logger.info("time is " + (System.nanoTime() - time) / 1000000 + " ms");

        return ResponseEntity.ok().body("Order updated, and file can be downloaded from " + customorder);

    }



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
    }

}

