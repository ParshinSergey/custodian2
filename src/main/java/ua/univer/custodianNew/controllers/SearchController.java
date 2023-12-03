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

import java.io.IOException;
import java.net.http.HttpClient;

import static ua.univer.custodianNew.controllers.Util.convertFormToSearchAccount;
import static ua.univer.custodianNew.controllers.Util.convertFormToSearchCustomer;

@RestController
@RequestMapping(value = "/api/search", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SearchController extends BaseController{


    public SearchController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }

    @Operation(summary = "Пошуковий запит. Перелік рахунків у ЦБ(V2)")
    @PostMapping(value = "/accountV2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchAccountV2 (@RequestBody @Valid FormSearch form) throws IOException {

        long time = System.nanoTime();
        Request request = getRequestWithHeader("SearchAccountV2", false);

        TbodyRequest tbodyRequest = Util.convertFormToSearchAccountV2(form);
        request.setBody(tbodyRequest);

        if (form.getBrokerID() != null){
            String dekraResponse = writeAndSendRequestWriteResponseToFile(request, DEKRA_URL_PROD, "SearchAccV2");
            Responce responce = getResponceFromXml(dekraResponse);
            Responce responceWithBroker = ResponceServise.getResponceWithBroker(responce, form.getBrokerID());
            logger.info("time is " + (System.nanoTime() - time) / 1000000 + " ms");
            return ResponseEntity.ok().body(ConverterUtil.objectToJson(responceWithBroker));
        }

        return getResponseEntity(time, request, DEKRA_URL_PROD, "SearchAccountV2");
    }


    @PostMapping(value = "/TEST/accountV2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchAccountV2WithBroker (@RequestBody @Valid FormSearch form) throws IOException {

        long time = System.nanoTime();
        Request request = getRequestWithHeader("SearchAccountV2", true);

        TbodyRequest tbodyRequest = Util.convertFormToSearchAccountV2(form);
        request.setBody(tbodyRequest);

        if (form.getBrokerID() != null){
            String dekraResponse = writeAndSendRequestWriteResponseToFile(request, DEKRA_URL_80, "SearchAccV2");
            //String dekraResponse = writeAndSendRequest(request, DEKRA_URL_80, "SearchAccV2");
            Responce responce = getResponceFromXml(dekraResponse);
            Responce responceWithBroker = ResponceServise.getResponceWithBroker(responce, form.getBrokerID());
            logger.info("time is " + (System.nanoTime() - time) / 1000000 + " ms");
            return ResponseEntity.ok().body(ConverterUtil.objectToJson(responceWithBroker));
        }

        return getResponseEntity(time, request, DEKRA_URL_80, "SearchAccountV2");

    }


    @PostMapping(value = "/TEST/account")
    public ResponseEntity<String> searchAccount (@RequestBody @Valid FormSearchAccount account) throws IOException {

        long time = System.nanoTime();
        Request request = getRequestWithHeader("SearchAccount", true);

        TbodyRequest tbodyRequest = convertFormToSearchAccount(account);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DEKRA_URL_80, "SearchAcc");
    }


    @PostMapping(value = "/TEST/customer")
    public ResponseEntity<String> searchCustomer(@RequestBody @Valid FormSearchCustomer form) throws IOException {

        Request request = getRequestWithHeader("SearchCustomer", true);

        TSearchCustomer customer = convertFormToSearchCustomer(form);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setSearchCustomer(customer);
        request.setBody(tbodyRequest);

        String dekraResponse = writeAndSendRequestWriteResponseToFile(request, DEKRA_URL_80, "SearchCustomer");

        return ResponseEntity.ok().body(dekraResponse);

    }


}
