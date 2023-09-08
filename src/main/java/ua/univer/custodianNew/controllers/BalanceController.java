package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import jakarta.validation.Valid;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.univer.custodianNew.dto.FormBalance;
import ua.univer.custodianNew.dto.FormSearch;
import ua.univer.custodianNew.dto.FormTransaction;
import ua.univer.custodianNew.dto.FormTransactionFile;
import ua.univer.custodianNew.util.ConverterUtil;
import ua.univer.custodianNew.util.DateTimeUtil;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;

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

