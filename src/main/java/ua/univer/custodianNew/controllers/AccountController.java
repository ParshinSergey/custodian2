package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.univer.custodianNew.dto.FormFO;
import ua.univer.custodianNew.dto.FormGet;
import ua.univer.custodianNew.dto.FormReserveCancel;
import ua.univer.custodianNew.util.ConverterUtil;

import java.io.*;
import java.net.http.HttpClient;
import java.util.List;

import static ua.univer.custodianNew.exceptions.AppExceptionHandler.TEXT_MISTAKE;

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
    @PostMapping(value = "/getAccountNum")
    public ResponseEntity<String> getAccountNum (@RequestBody @Valid FormGet form) throws IOException {

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

        String deckraResponse = writeAndSendRequestWriteResponseToFile(request, DEKRA_URL_PROD, "GetAccountNum");

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


    @PostMapping(value = "/TEST/updateCustomer")
    public ResponseEntity<String> testUpdateCustomer (@RequestBody FormFO form) throws IOException {

        long time = System.nanoTime();

        /*Request request1 = getRequestWithHeader("Account", true);

        TbodyRequest tbodyRequest = new TbodyRequest();
        var account = new TbodyRequest.Account();
        account.setAccount(form.getAccount());
        tbodyRequest.setAccount(account);
        request1.setBody(tbodyRequest);

        String dekraResponse = writeAndSendRequestWriteResponseToFile(request1, DEKRA_URL_80, "Account");
        Responce responce = getResponceFromXml(dekraResponse);*/

        String dekraResponse = testAccount(form.getAccount()).getBody();
        Responce responce = ConverterUtil.jsonToObject(dekraResponse, Responce.class);
        if (responce.getBody() == null){
            return ResponseEntity.ok().body("Не найдено аккаунта " + form.getAccount());
        }
        TCustomer customer = responce.getBody().getAccount().getCustomer();

        // check for equal INN
        if (!customer.getIdCode().getValue().equals(form.getIdCode())){
            return ResponseEntity.unprocessableEntity().body("Несоответствие ИНН и Account");
        }

        Request request2 = getRequestWithHeader("UpdateCustomerV2", true);
        TbodyRequest tbodyRequest2 = new TbodyRequest();
        TupdateCustomer updCustomer = Util.makeCustomerForUpdate(customer, form);
        tbodyRequest2.setUpdateCustomer(updCustomer);
        request2.setBody(tbodyRequest2);

        return getResponseEntity(time, request2, DEKRA_URL_80, "UpdateCustomer");
    }



    @PostMapping(value = "/TEST/cancelBankDetail")
    public ResponseEntity<String> testCancelBankDetail (@RequestBody FormFO form) throws IOException {

        long time = System.nanoTime();

        String dekraResponse = testAccount(form.getAccount()).getBody();
        Responce responce = ConverterUtil.jsonToObject(dekraResponse, Responce.class);

        if (responce.getBody() == null){
            String answer = String.format(TEXT_MISTAKE, "Не найдено аккаунта " + form.getAccount());
            logger.warn(answer);
            return ResponseEntity.unprocessableEntity().body(answer);
           // return ResponseEntity.ok().body("Не найдено аккаунта " + form.getAccount());
        }
        TCustomer customer = responce.getBody().getAccount().getCustomer();

        Request request = getRequestWithHeader("UpdateCustomerV2", true);
        TbodyRequest tbodyRequest = new TbodyRequest();
        TupdateCustomer updCustomer = Util.cancelBankDetail(customer, form);
        tbodyRequest.setUpdateCustomer(updCustomer);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DEKRA_URL_80, "UpdateCustomer");
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

    @Hidden
    @PostMapping(value = "/TEST/account", consumes = "*/*")
    public ResponseEntity<String> testAccount (@RequestBody @NotBlank String accountNum) throws IOException {
        long time = System.nanoTime();

        Request request = getRequestWithHeader("Account", true);

        TbodyRequest tbodyRequest = new TbodyRequest();
        var account = new TbodyRequest.Account();
        account.setAccount(accountNum);
        tbodyRequest.setAccount(account);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DEKRA_URL_80, "Account");

    }




}

