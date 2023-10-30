package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.univer.custodianNew.dto.FormFO;
import ua.univer.custodianNew.dto.FormGet;
import ua.univer.custodianNew.dto.FormReserveCancel;

import java.io.*;
import java.net.http.HttpClient;

@RestController
@RequestMapping(value = "/api/request", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AccountController extends BaseController {

    private final static String NEW_ACCOUNT = "newAccount";

    public AccountController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }


    @PostMapping(value = "/TEST/" + NEW_ACCOUNT + "FO")
    public ResponseEntity<String> testGetNewAccountFO (@RequestBody @Valid FormFO form) throws IOException {

        logger.info("Method NewAccount. TEST.");
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest(form.getRequestID());
        tHeaderRequest.setRequestType(NEW_ACCOUNT);
        request.setHeader(tHeaderRequest);

        TbodyRequest tbodyRequest = Util.convertFormToNewAccount(form);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DEKRA_URL_80, "NewAccountTEST");
    }


    @Operation(summary = "Відкриття рахунку")
    @PostMapping(value = "/" + NEW_ACCOUNT + "FO")
    public ResponseEntity<String> getNewAccountFO (@RequestBody @Valid FormFO form) throws IOException {

        logger.info("Method NewAccount. Production");
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest(form.getRequestID());
        tHeaderRequest.setRequestType(NEW_ACCOUNT);
        request.setHeader(tHeaderRequest);

        TbodyRequest tbodyRequest = Util.convertFormToNewAccount(form);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DEKRA_URL_PROD, "NewAccount");

    }


    @PostMapping(value = "/TEST/getAccount")
    public ResponseEntity<String> testGetAccount (@RequestBody @Valid FormGet form) throws IOException {

        long time = System.nanoTime();
        Request request = getRequestWithHeader("GetAccountNum", true);

        TAccountNumRequest accountNumRequest = new TAccountNumRequest();

        var nssmc = new TAccountNumRequest.NssmcClientTypeCode();
        nssmc.setValue(form.getNssmcClientTypeCode());
        accountNumRequest.setNssmcClientTypeCode(nssmc);

        var cnum = new TAccountNumRequest.CNUM();
        cnum.setValue(form.getCnum());
        accountNumRequest.setCNUM(cnum);

        var typeCode = new TAccountNumRequest.ClientTypeCode();
        typeCode.setValue("-1".equals(form.getClientTypeCode()) ? "0" : form.getClientTypeCode());
        accountNumRequest.setClientTypeCode(typeCode);

        var country = new TAccountNumRequest.Country();
        country.setValue(form.getCountry());
        accountNumRequest.setCountry(country);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setAccountNum(accountNumRequest);
        request.setBody(tbodyRequest);

        String deckraResponse = writeAndSendRequestWriteResponseToFile(request, DEKRA_URL_80, "GetAccountTEST");

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

    @Operation(summary = "Запит номера рахунку")
    @PostMapping(value = "/getAccount")
    public ResponseEntity<String> getAccount (@RequestBody @Valid FormGet form) throws IOException {

        long time = System.nanoTime();
        Request request = getRequestWithHeader("GetAccountNum", false);

        TAccountNumRequest accountNumRequest = new TAccountNumRequest();

        var nssmc = new TAccountNumRequest.NssmcClientTypeCode();
        nssmc.setValue(form.getNssmcClientTypeCode());
        accountNumRequest.setNssmcClientTypeCode(nssmc);

        var cnum = new TAccountNumRequest.CNUM();
        cnum.setValue(form.getCnum());
        accountNumRequest.setCNUM(cnum);

        var typeCode = new TAccountNumRequest.ClientTypeCode();
        typeCode.setValue("-1".equals(form.getClientTypeCode()) ? "0" : form.getClientTypeCode());
        accountNumRequest.setClientTypeCode(typeCode);

        var country = new TAccountNumRequest.Country();
        country.setValue(form.getCountry());
        accountNumRequest.setCountry(country);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setAccountNum(accountNumRequest);
        request.setBody(tbodyRequest);

        String deckraResponse = writeAndSendRequestWriteResponseToFile(request, DEKRA_URL_PROD, "GetAccount");

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


    @Operation(summary = "Скасування резервування номера рахунку")
    @PostMapping(value = "/accountReserveCancel")
    public ResponseEntity<String> accountReserveCancel (@RequestBody @Valid FormReserveCancel form) throws IOException {

        long time = System.nanoTime();
        Request request = getRequestWithHeader("AccountNumReserveCancel", false);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setAccountNumReserveCancel(form.getAccount());
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DEKRA_URL_PROD, "ReserveCancel");
    }


    @Hidden
    @PostMapping(value = "/TEST/updateCustomer")
    public ResponseEntity<String> testUpdateCustomer (@RequestBody @Valid FormFO form) throws IOException {

        long time = System.nanoTime();

        Request request1 = getRequestWithHeader("Account", true);

        TbodyRequest tbodyRequest = new TbodyRequest();
        var account = new TbodyRequest.Account();
        account.setAccount(form.getAccount());
        tbodyRequest.setAccount(account);
        request1.setBody(tbodyRequest);

        //String dekraResponse = writeAndSendRequest(request1, DEKRA_URL_80, "Account");
        String dekraResponse = writeAndSendRequestWriteResponseToFile(request1, DEKRA_URL_80, "Account");
        Responce responce = getResponceFromXml(dekraResponse);
        TCustomer customer = responce.getBody().getAccount().getCustomer();

        // check for equal INN

        Request request2 = getRequestWithHeader("UpdateCustomerV2", true);
        TbodyRequest tbodyRequest2 = new TbodyRequest();
        TupdateCustomer updCustomer = Util.makeCustomerForUpdate(customer, form);
        tbodyRequest2.setUpdateCustomer(updCustomer);
        request2.setBody(tbodyRequest2);


        //return ResponseEntity.ok().body(dekraResponse);
        return getResponseEntity(time, request2, DEKRA_URL_80, "UpdateCustomer");
    }


    @Operation(summary = "Анкета рахунку у ЦБ")
    @PostMapping(value = "/account", consumes = "*/*")
    public ResponseEntity<String> account (@RequestBody @NotBlank String accountNum) throws IOException {
        long time = System.nanoTime();

        Request request = getRequestWithHeader("Account", false);

        TbodyRequest tbodyRequest = new TbodyRequest();
        var account = new TbodyRequest.Account();
        account.setAccount(accountNum);
        tbodyRequest.setAccount(account);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DEKRA_URL_PROD, "Account");

    }

}

