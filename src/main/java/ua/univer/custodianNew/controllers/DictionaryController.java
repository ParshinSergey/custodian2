package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.univer.custodianNew.dto.FormDictionary;

import java.io.IOException;
import java.net.http.HttpClient;

@RestController
@RequestMapping(value = "/api/dictionary", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class DictionaryController extends BaseController {

    public DictionaryController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<String> getDictionary (@RequestBody FormDictionary form) throws IOException {

        logger.info("Method GetDictionary. Production");
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest();
        tHeaderRequest.setRequestType("GetDictionary");
        request.setHeader(tHeaderRequest);

        var dictionaryCustomRequest = new TDictionaryCustomRequest();
        dictionaryCustomRequest.setCode(form.getCode());
        dictionaryCustomRequest.setData(form.isData());
        dictionaryCustomRequest.setMetadata(form.isMetadata());

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setDictionary(dictionaryCustomRequest);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DECKRA_URL_PROD, "GetDictionary");

    }

    @GetMapping(value = "/TEST/get")
    public ResponseEntity<String> testGetDictionary (@RequestBody FormDictionary form) throws IOException {

        logger.info("Method GetDictionary.");
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest();
        tHeaderRequest.setRequestType("GetDictionary");
        request.setHeader(tHeaderRequest);

        var dictionaryCustomRequest = new TDictionaryCustomRequest();
        dictionaryCustomRequest.setCode(form.getCode());
        dictionaryCustomRequest.setData(form.isData());
        dictionaryCustomRequest.setMetadata(form.isMetadata());

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setDictionary(dictionaryCustomRequest);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DECKRA_URL_80, "GetDictionary");

    }

    @GetMapping(value = "/TEST/getDictionaries")
    public ResponseEntity<String> testGetDictionaries (@RequestBody FormDictionary form) throws IOException {

        logger.info("Method GetDictionaries.");
        long time = System.nanoTime();

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequestTest();
        tHeaderRequest.setRequestType("GetDictionaries");
        request.setHeader(tHeaderRequest);


        TbodyRequest tbodyRequest = new TbodyRequest();
       // TDummy dummy = new TDummy();
        tbodyRequest.setDictionaries(null);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DECKRA_URL_80, "GetDictionaries");

    }




}
