package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;

@RestController
@RequestMapping(value = "/api/dictionary", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class DictionaryController extends BaseController {

    public DictionaryController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<String> getDictionary (@RequestBody TDictionaryCustomRequest form) {

        long time = System.nanoTime();
        Request request = getRequestWithHeader("GetDictionary", false);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setDictionary(form);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DEKRA_URL_PROD, "GetDictionary");

    }


    @GetMapping(value = "/TEST/get")
    public ResponseEntity<String> testGetDictionary (@RequestBody TDictionaryCustomRequest form) {

        long time = System.nanoTime();
        Request request = getRequestWithHeader("GetDictionary", true);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setDictionary(form);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DEKRA_URL_80, "GetDictionary");

    }


    @GetMapping(value = "/TEST/getDictionaries", consumes = "*/*", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> testGetDictionaries () {

        Request request = getRequestWithHeader("GetDictionaries", true);

        TbodyRequest tbodyRequest = new TbodyRequest();
        request.setBody(tbodyRequest);

        String dekraResponse = writeAndSendRequestWriteResponseToFile(request, DEKRA_URL_80, "GetDictionaries");

        return ResponseEntity.ok().body(dekraResponse);

    }

    @Operation(summary = "Перегляд анкети фінансового інструменту")
    @PostMapping(value = "/getFI")
    public ResponseEntity<String> getFI (@RequestBody TbodyRequest.GetFI form) {

        long time = System.nanoTime();
        Request request = getRequestWithHeader("getFI", false);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setGetFI(form);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DEKRA_URL_PROD, "getFI");

    }



}
