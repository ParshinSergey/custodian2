package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.univer.custodianNew.dto.FormAccount;
import ua.univer.custodianNew.dto.FormNewAccount;
import ua.univer.custodianNew.dto.FormGet;
import ua.univer.custodianNew.exceptions.UnprocessableEntityException;
import ua.univer.custodianNew.util.ConverterUtil;

import java.net.http.HttpClient;


@RestController
@RequestMapping(value = "/api/request", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AccountController extends BaseController {

    private static final String NEW_ACCOUNT = "NewAccount";
    private static final String GET_ACCOUNT_NUM = "GetAccountNum";
    private static final String UPDATE_CUSTOMER = "UpdateCustomerV2";
    private static final String ACCOUNT = "Account";
    private static final String ACCOUNT_NUM_RESERVE_CANCEL = "AccountNumReserveCancel";
    private static final String UPDATE_ACCOUNT = "UpdateAccountV2";

    public AccountController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }


    @PostMapping(value = "/TEST/newAccount")
    public ResponseEntity<String> testNewAccount (@RequestBody @Valid FormNewAccount form) {
        form.setTest(true);
        return newAccount(form);
    }


    @Operation(summary = "Відкриття рахунку")
    @PostMapping(value = "/newAccount")
    public ResponseEntity<String> newAccount (@RequestBody @Valid FormNewAccount form) {

        long time = System.nanoTime();
        String methodName = NEW_ACCOUNT;
        Request request = getRequestWithHeader(methodName, form.isTest());

        TbodyRequest tbodyRequest = Util.convertFormToNewAccount(form);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, form.ipAddress(), methodName);
    }


    @Operation(summary = "Відкриття рахунку")
    @PostMapping(value = "/newAccountFO")
    public ResponseEntity<String> getNewAccountFO (@RequestBody @Valid FormNewAccount form) {

        logger.info("Method NewAccount. Production. OLD VERSION.");
        long time = System.nanoTime();
        String methodName = NEW_ACCOUNT;

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest(form.getRequestID(), form.isTest());
        tHeaderRequest.setRequestType(methodName);
        request.setHeader(tHeaderRequest);

        TbodyRequest tbodyRequest = Util.convertFormToNewAccount(form);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, form.ipAddress(), methodName);
    }


    @PostMapping(value = "/TEST/getAccountNum")
    public ResponseEntity<String> testGetAccountNum (@RequestBody @Valid FormGet form) {
        form.setTest(true);
        return getAccountNum(form);
    }


    @Operation(summary = "Запит номера рахунку")
    @PostMapping(value = "/getAccountNum")
    public ResponseEntity<String> getAccountNum (@RequestBody @Valid FormGet form) {

        long time = System.nanoTime();
        String methodName = GET_ACCOUNT_NUM;
        Request request = getRequestWithHeader(methodName, form.isTest());

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

        String deckraResponse = writeAndSendRequestWriteResponseToFile(request, form.ipAddress(), methodName);

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
    public ResponseEntity<String> accountReserveCancel (@RequestBody @Valid FormAccount form) {

        long time = System.nanoTime();
        String methodName = ACCOUNT_NUM_RESERVE_CANCEL;
        Request request = getRequestWithHeader(methodName, form.isTest());

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setAccountNumReserveCancel(form.getAccount());
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, form.ipAddress(), methodName);
    }


    @PostMapping(value = "/TEST/updateCustomer")
    public ResponseEntity<String> testUpdateCustomer (@RequestBody FormNewAccount form) {
        form.setTest(true);
        return updateCustomer(form);
    }


    @Operation(summary = "Редагування картки клієнта")
    @PostMapping(value = "/updateCustomer")
    public ResponseEntity<String> updateCustomer (@RequestBody FormNewAccount form) {

        if (form.getIdCode().length() == 8){
            return ResponseEntity.badRequest().body("ВНИМАНИЕ!! Попытка изменить Анкету Юр.лица. Account " + form.getAccount() + ", ИНН " + form.getIdCode());
        }

        long time = System.nanoTime();
        String methodName = UPDATE_CUSTOMER;

       /* String dekraResponse = account(new FormAccount(form.getAccount(), form.isTest())).getBody();
        Responce responce = ConverterUtil.jsonToObject(dekraResponse, Responce.class);
        if (responce.getBody() == null){
            return ResponseEntity.ok().body("Не найдено аккаунта " + form.getAccount());
        }*/

        TCustomer customer = getCustomerByAccount(form.getAccount(), form.isTest());

        // check for equal INN
        if (!customer.getIdCode().getValue().equals(form.getIdCode())){
            return ResponseEntity.unprocessableEntity().body("Несоответствие ИНН и Account");
        }

        Request request = getRequestWithHeader(methodName, form.isTest());
        TbodyRequest tbodyRequest2 = new TbodyRequest();
        TupdateCustomer updCustomer = Util.makeCustomerForUpdate(customer, form);
        tbodyRequest2.setUpdateCustomer(updCustomer);
        request.setBody(tbodyRequest2);

        return getResponseEntity(time, request, form.ipAddress(), methodName);
    }


    @PostMapping(value = "/TEST/cancelBankDetail")
    public ResponseEntity<String> testCancelBankDetail (@RequestBody FormNewAccount form) {
        form.setTest(true);
        return cancelBankDetail(form);
    }


    @Operation(summary = "Скасування банківських реквізитів")
    @PostMapping(value = "/cancelBankDetail")
    public ResponseEntity<String> cancelBankDetail (@RequestBody FormNewAccount form) {

        long time = System.nanoTime();
        String methodName = UPDATE_CUSTOMER;

        TCustomer customer = getCustomerByAccount(form.getAccount(), form.isTest());

        /*String dekraResponse = account(new FormAccount(form.getAccount(), form.isTest())).getBody();
        Responce responce = ConverterUtil.jsonToObject(dekraResponse, Responce.class);

        if (responce.getBody() == null){
            String answer = String.format(TEXT_MISTAKE, "Не найдено аккаунта " + form.getAccount());
            logger.warn(answer);
            return ResponseEntity.unprocessableEntity().body(answer);
           // return ResponseEntity.ok().body("Не найдено аккаунта " + form.getAccount());
        }
        TCustomer customer = responce.getBody().getAccount().getCustomer();*/

        Request request = getRequestWithHeader(methodName, form.isTest());
        TbodyRequest tbodyRequest = new TbodyRequest();
        TupdateCustomer updCustomer = Util.cancelBankDetail(customer, form);
        tbodyRequest.setUpdateCustomer(updCustomer);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, form.ipAddress(), methodName);
    }


    @Hidden
    @PostMapping(value = "/TEST/account")
    public ResponseEntity<String> testAccount (@RequestBody @Valid FormAccount form) {
        form.setTest(true);
        return account(form);
    }


    @Operation(summary = "Анкета рахунку у ЦБ")
    @PostMapping(value = "/account")
    public ResponseEntity<String> account (@RequestBody @Valid FormAccount form) {
        long time = System.nanoTime();
        String methodName = ACCOUNT;

        Request request = getRequestWithHeader(methodName, form.isTest());

        TbodyRequest tbodyRequest = new TbodyRequest();
        var account = new TbodyRequest.Account();
        account.setAccount(form.getAccount());
        tbodyRequest.setAccount(account);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, form.ipAddress(), methodName);
    }


    @PostMapping(value = "/TEST/updateAccount")
    public ResponseEntity<String> testUpdateAccount (@RequestBody FormNewAccount form) {
        form.setTest(true);
        return updateAccount(form);
    }


    @Operation(summary = "Редагування рахунку")
    @PostMapping(value = "/updateAccount")
    public ResponseEntity<String> updateAccount (@RequestBody FormNewAccount form){

        long time = System.nanoTime();
        String methodName = UPDATE_ACCOUNT;

        TCustomer customer = getCustomerByAccount(form.getAccount(), form.isTest());

        if (!customer.getIdCode().getValue().equals(form.getIdCode())){
            throw new UnprocessableEntityException("Несоответствие ИНН и Account");
        }

        Request request = getRequestWithHeader(methodName, form.isTest());

        TupdateCustomer updCustomer = Util.makeCustomerForUpdate(customer, form);
        TupdateAccount updAccount = new TupdateAccount();
        updAccount.setAccount(form.getAccount());
        updAccount.setCustomer(updCustomer);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setUpdateAccount(updAccount);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, form.ipAddress(), methodName);
    }


    private TCustomer getCustomerByAccount(String account, boolean test) {

        String dekraResponse = account(new FormAccount(account, test)).getBody();
        Responce responce = ConverterUtil.jsonToObject(dekraResponse, Responce.class);
        if (responce.getBody() == null || responce.getBody().getAccount() == null){
            throw new UnprocessableEntityException("Не найдено аккаунта " + account);
        }

        return responce.getBody().getAccount().getCustomer();
    }

}

