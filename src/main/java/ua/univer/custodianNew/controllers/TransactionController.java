package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.univer.custodianNew.dto.FormTransaction;
import ua.univer.custodianNew.exceptions.DekraException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static ua.univer.custodianNew.util.DateTimeUtil.oneBoxCalendar;

@RestController
@RequestMapping(value = "/api/transaction", consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController extends BaseController{

    private static final String TRANSACTION = "Transaction";

    private static final String URL_TEST_ASYNC = "https://10.1.2.80/API/CPAPI.REST.dll/Send/";
    private static final String URL_TEST_ASYNC_CHECK = "https://10.1.2.80/API/CPAPI.REST.dll/ReceiveOne/";
    private static final String URL_PROD_ASYNC = "https://10.1.2.204/API/CPAPI.REST.dll/Send/";
    private static final String URL_PROD_ASYNC_CHECK = "https://10.1.2.204/API/CPAPI.REST.dll/ReceiveOne/";



    public TransactionController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }


    @Operation(summary = "Транзакція")
    @PostMapping(value = "/TEST/add")
    public ResponseEntity<String> transactionTest(@RequestBody @Valid FormTransaction form) {
        form.setTest(true);
        return transaction(form);
    }


    @Hidden
    @PostMapping(value = "/secret/add")
    public ResponseEntity<String> transaction(@RequestBody @Valid FormTransaction form) {

        long time = System.nanoTime();
        String methodName = TRANSACTION;
        String ipAddress = form.isTest() ? URL_TEST_ASYNC : URL_PROD_ASYNC;
        Request request = getRequestWithHeader(methodName, form.isTest());

        TTransactionRequest transaction = new TTransactionRequest();

        var tisin = new TISIN();
        tisin.setISIN(form.getIsin());
        tisin.setDepositary(form.getDepositary());
        transaction.setISIN(tisin);
        transaction.setQuantity(form.getQuantity());

        var agreement = new TTransactionRequest.Agreement();
        agreement.setNumber(form.getNumber());
        agreement.setDate(oneBoxCalendar(form.getDate()));
        if (form.getAgreementCost() != null) {
            agreement.setAgreementCost(BigDecimal.valueOf(form.getAgreementCost()));
        }
        agreement.setAgreementCurrency(form.getAgreementCurrency());
        agreement.setAgreementType(BigInteger.valueOf(form.getAgreementType()));
        agreement.setAgreementTypeName(form.getAgreementTypeName());
        transaction.setAgreement(agreement);

        if (form.getTransactionProcessing() != null){
            transaction.setTransactionProcessing(TTransactionProcessing.valueOf(form.getTransactionProcessing()));
        }
        transaction.setCommitAfterRegistr(form.isCommitAfterRegistr());
        if (form.getTransactionType() != null) {
            transaction.setTransationType(TTransactionType.valueOf(form.getTransactionType()));
        }
        transaction.setRequestInResponce(form.isRequestInResponse());

        TParticipant participantSource = getParticipant(form);
        transaction.setParticipantSource(participantSource);

        TParticipant participantDestination = new TParticipant();
        participantDestination.setMDO(form.getMdo2());
        participantDestination.setAccount(form.getAccount2());
        participantDestination.setName(form.getName2());
        participantDestination.setIDCode(form.getIdCode2());
        Tdoc document2 = new Tdoc();
        document2.setDocSerial(form.getDocSerial2());
        document2.setDocNumber(form.getDocNumber2());
        document2.setDocDate(oneBoxCalendar(form.getDocDate2()));
        document2.setDocWho(form.getDocWho2());
        participantDestination.setDoc(document2);
        if (form.getLevel2() != null) {
            participantDestination.setLevel(BigInteger.valueOf(form.getLevel2()));
        }
        if (form.getAccountType2() != null) {
            participantDestination.setAccountType(BigInteger.valueOf(form.getAccountType2()));
        }
        TBroker broker2 = new TBroker();
        broker2.setIDCode(form.getBrokerIdCode2());
        broker2.setName(form.getBrokerName2());
        participantDestination.setBroker(broker2);
        participantDestination.setAgregateAccountID(form.getAgregateAccountID2());
        transaction.setParticipantDestination(participantDestination);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setTransaction(transaction);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, ipAddress, methodName);

    }



    private static TParticipant getParticipant(FormTransaction form) {
        TParticipant participantSource = new TParticipant();
        participantSource.setMDO(form.getMdo());
        participantSource.setAccount(form.getAccount());
        participantSource.setName(form.getName());
        participantSource.setIDCode(form.getIdCode());
        Tdoc document = new Tdoc();
        document.setDocSerial(form.getDocSerial());
        document.setDocNumber(form.getDocNumber());
        document.setDocDate(oneBoxCalendar(form.getDocDate()));
        document.setDocWho(form.getDocWho());
        participantSource.setDoc(document);
        if (form.getLevel() != null) {
            participantSource.setLevel(BigInteger.valueOf(form.getLevel()));
        }
        if (form.getAccountType() != null) {
            participantSource.setAccountType(BigInteger.valueOf(form.getAccountType()));
        }
        TBroker broker = new TBroker();
        broker.setIDCode(form.getBrokerIdCode());
        broker.setName(form.getBrokerName());
        participantSource.setBroker(broker);
        participantSource.setAgregateAccountID(form.getAgregateAccountID());
        return participantSource;
    }



    @GetMapping(value = "/check", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<String> transactionCheck(@RequestBody String check){

        final String URL_FORMAT = URL_PROD_ASYNC_CHECK + "?%s=%s&%s=%s";
        String url = String.format(URL_FORMAT, "sourceAPPidentity", Util.sourceAPP_prod, "Check", check);

        return getResponse(url);
    }



    @GetMapping(value = "/TEST/check", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<String> testCheck(@RequestBody String check){

        final String URL_FORMAT = URL_TEST_ASYNC_CHECK + "?%s=%s&%s=%s";
        String url = String.format(URL_FORMAT, "sourceAPPidentity", Util.sourceAPP_test, "Check", check);

        return getResponse(url);
    }



    @PostMapping(value = "/TEST/block")
    public ResponseEntity<String> testBlock(@RequestBody FormTransaction form) {
        form.setTest(true);
        return transactionBlock(form);
    }


    @Operation(summary = "Блокування ЦП для продажу.")
    @PostMapping(value = "/block")
    public ResponseEntity<String> transactionBlock(@RequestBody FormTransaction form) {

        long time = System.nanoTime();
        String methodName = TRANSACTION;
        String ipAddress = form.isTest() ? URL_TEST_ASYNC : URL_PROD_ASYNC;
        Request request = getRequestWithHeader(methodName, form.isTest());

        TTransactionRequest transaction = new TTransactionRequest();

        var tisin = new TISIN();
        tisin.setISIN(form.getIsin());
        tisin.setDepositary(form.getDepositary());
        transaction.setISIN(tisin);
        transaction.setQuantity(form.getQuantity());

        var agreement = new TTransactionRequest.Agreement();
        agreement.setNumber(form.getNumber());
        agreement.setDate(oneBoxCalendar(form.getDate()));
        if (form.getAgreementCost() != null){
            agreement.setAgreementCost(BigDecimal.valueOf(form.getAgreementCost()));
        }
        agreement.setAgreementCurrency(form.getAgreementCurrency());
        if (form.getAgreementType() != null){
            agreement.setAgreementType(BigInteger.valueOf(form.getAgreementType()));
        }
        agreement.setAgreementTypeName(form.getAgreementTypeName());
        transaction.setAgreement(agreement);

        if (form.getTransactionProcessing() != null){
            transaction.setTransactionProcessing(TTransactionProcessing.valueOf(form.getTransactionProcessing()));
        }
        transaction.setCommitAfterRegistr(form.isCommitAfterRegistr());
        transaction.setTransationType(TTransactionType.BLK);

        TParticipant participantSource = getParticipant(form);
        transaction.setParticipantSource(participantSource);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setTransaction(transaction);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, ipAddress, methodName);
    }



    @PostMapping(value = "/TEST/preCheck")
    private ResponseEntity<String> preCheck (@RequestBody FormTransaction form){
        form.setTest(true);
        long time = System.nanoTime();
        String methodName = TRANSACTION;
        String ipAddress = form.isTest() ? URL_TEST_ASYNC : URL_PROD_ASYNC;
        Request request = getRequestWithHeader(methodName, form.isTest());
        var preCheckRequest = new TTransactionPreCheckRequest();
        var tisin = new TISIN();
        tisin.setISIN(form.getIsin());
        preCheckRequest.setISIN(tisin);
        preCheckRequest.setQuantity(form.getQuantity());
        preCheckRequest.setAccount(form.getAccount());
        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setTransactionPreCheck(preCheckRequest);
        request.setBody(tbodyRequest);
        return getResponseEntity(time, request, ipAddress, methodName);
    }



    private ResponseEntity<String> getResponse(String url) {

        logger.info("URL = {}", url);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(20))
                .GET()
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

        String response = httpResponse.body();
        Util.writeStringToFile(response, "Check", ".xml");

        return ResponseEntity.ok(response);
    }
}
