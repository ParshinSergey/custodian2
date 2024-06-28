package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.univer.custodianNew.dto.FormTransaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.http.HttpClient;

import static ua.univer.custodianNew.util.DateTimeUtil.oneBoxCalendar;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController extends BaseController{

    private static final String TRANSACTION = "Transaction";

    public TransactionController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }

    //@Operation(summary = "Выполнение транзакции")
    @PostMapping(value = "/TEST/transaction")
    public ResponseEntity<String> transaction(@RequestBody FormTransaction form) {

        form.setTest(true);
        long time = System.nanoTime();
        String methodName = TRANSACTION;
        Request request = getRequestWithHeader(methodName, form.isTest());
       /* logger.info("Method Transaction.");
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest();
        tHeaderRequest.setRequestType("Transaction");
        request.setHeader(tHeaderRequest);*/

        TTransactionRequest transaction = new TTransactionRequest();

        var tisin = new TISIN();
        tisin.setISIN(form.getIsin());
        tisin.setDepositary(form.getDepositary());
        transaction.setISIN(tisin);
        transaction.setQuantity(form.getQuantity());

        var agreement = new TTransactionHeaderRequest.Agreement();
        agreement.setNumber(form.getNumber());
        agreement.setDate(oneBoxCalendar(form.getDate()));
        agreement.setAgreementCost(BigDecimal.valueOf(form.getAgreementCost()));
        agreement.setAgreementCurrency(form.getAgreementCurrency());
        agreement.setAgreementType(BigInteger.valueOf(form.getAgreementType()));
        agreement.setAgreementTypeName(form.getAgreementTypeName());
        transaction.setAgreement(agreement);

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
        participantSource.setLevel(BigInteger.valueOf(form.getLevel()));
        TBroker broker = new TBroker();
        broker.setIDCode(form.getBrokerIdCode());
        broker.setName(form.getBrokerName());
        participantSource.setBroker(broker);
        participantSource.setAgregateAccountID(form.getAgregateAccountID());
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
        participantDestination.setLevel(BigInteger.valueOf(form.getLevel2()));
        TBroker broker2 = new TBroker();
        broker2.setIDCode(form.getBrokerIdCode2());
        broker2.setName(form.getBrokerName2());
        participantDestination.setBroker(broker2);
        participantDestination.setAgregateAccountID(form.getAgregateAccountID2());
        transaction.setParticipantDestination(participantDestination);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setTransaction(transaction);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, form.ipAddress(),methodName);

    }





}
