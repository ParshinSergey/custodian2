package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpClient;

@RestController
@RequestMapping(value = "/api/dictionary", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class DictionaryController extends BaseController {

    public DictionaryController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<String> getDictionary (@RequestBody TDictionaryCustomRequest form) throws IOException {

        long time = System.nanoTime();
        Request request = getRequestWithHeader("GetDictionary", false);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setDictionary(form);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DECKRA_URL_PROD, "GetDictionary");

    }

    @GetMapping(value = "/TEST/get")
    public ResponseEntity<String> testGetDictionary (@RequestBody TDictionaryCustomRequest form) throws IOException {

        long time = System.nanoTime();
        Request request = getRequestWithHeader("GetDictionary", true);

       /* THeaderRequest tHeaderRequest = Util.getHeaderRequestTest();
        tHeaderRequest.setRequestType("GetDictionary");
        request.setHeader(tHeaderRequest);

        var dictionaryCustomRequest = new TDictionaryCustomRequest();
        dictionaryCustomRequest.setCode(form.getCode());
        dictionaryCustomRequest.setData(form.isData());
        dictionaryCustomRequest.setMetadata(form.isMetadata());*/

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setDictionary(form);
        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DECKRA_URL_80, "GetDictionary");

    }

    @GetMapping(value = "/TEST/getDictionaries", consumes = "*/*", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> testGetDictionaries () throws IOException {

        Request request = getRequestWithHeader("GetDictionaries", true);

        TbodyRequest tbodyRequest = new TbodyRequest();
        request.setBody(tbodyRequest);

        String dekraResponse = writeAndSendRequestWriteResponseToFile(request, DECKRA_URL_80, "GetDictionaries");

        return ResponseEntity.ok().body(dekraResponse);

    }


}
