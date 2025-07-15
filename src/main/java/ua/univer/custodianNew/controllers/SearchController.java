package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.univer.custodianNew.dto.FormSearch;
import ua.univer.custodianNew.dto.FormSearchAccount;
import ua.univer.custodianNew.dto.FormSearchCustomer;
import ua.univer.custodianNew.util.ConverterUtil;
import ua.univer.custodianNew.util.ResponceServise;

import java.net.http.HttpClient;

import static ua.univer.custodianNew.controllers.Util.convertFormToSearchAccount;
import static ua.univer.custodianNew.controllers.Util.convertFormToSearchCustomer;

@RestController
@RequestMapping(value = "/api/search", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SearchController extends BaseController{

    private static final String SEARCH_ACCOUNT = "SearchAccount";
    private final static String SEARCH_ACCOUNT_V2 = "SearchAccountV2";
    private final static String SEARCH_CUSTOMER = "SearchCustomer";


    public SearchController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }


    @PostMapping(value = "/TEST/accountV2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchAccountV2WithBroker (@RequestBody @Valid FormSearch form) {
        form.setTest(true);
        return searchAccountV2(form);
    }


    @Operation(summary = "Пошуковий запит. Перелік рахунків у ЦБ(V2)")
    @PostMapping(value = "/accountV2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchAccountV2 (@RequestBody @Valid FormSearch form) {

        long time = System.nanoTime();
        String methodName = SEARCH_ACCOUNT_V2;
        Request request = getRequestWithHeader(methodName, form.isTest());
        TbodyRequest tbodyRequest = Util.convertFormToSearchAccountV2(form);
        request.setBody(tbodyRequest);

        if (form.getBrokerID() != null){
            String dekraResponse = writeAndSendRequestWriteResponseToFile(request, form.ipAddress(), "SearchWithBroker");
            Responce responce = getResponceFromXml(dekraResponse);
            Responce responceWithBroker = ResponceServise.filterWithBrokers(responce, form.getBrokerID());
            logger.info("time is " + (System.nanoTime() - time) / 1000000 + " ms");
            return ResponseEntity.ok().body(ConverterUtil.objectToJson(responceWithBroker));
        }

        return getResponseEntity(time, request, form.ipAddress(), methodName);
    }


    @PostMapping(value = "/TEST/account")
    public ResponseEntity<String> searchAccount (@RequestBody @Valid FormSearchAccount form) {

        form.setTest(true);
        long time = System.nanoTime();
        String methodName = SEARCH_ACCOUNT;
        Request request = getRequestWithHeader(methodName, form.isTest());
        TbodyRequest tbodyRequest = convertFormToSearchAccount(form);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, form.ipAddress(), methodName);
    }


    @PostMapping(value = "/TEST/customer")
    public ResponseEntity<String> searchCustomer(@RequestBody @Valid FormSearchCustomer form) {

        form.setTest(true);
        String methodName = SEARCH_CUSTOMER;
        Request request = getRequestWithHeader(methodName, form.isTest());

        TSearchCustomer customer = convertFormToSearchCustomer(form);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setSearchCustomer(customer);
        request.setBody(tbodyRequest);

        String dekraResponse = writeAndSendRequestWriteResponseToFile(request, form.ipAddress(), methodName);

        return ResponseEntity.ok().body(dekraResponse);

    }


}
