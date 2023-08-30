package ua.univer.custodianNew.controllers;

import dmt.custodian2016.Request;
import dmt.custodian2016.TDictionaryCustomRequest;
import dmt.custodian2016.THeaderRequest;
import dmt.custodian2016.TbodyRequest;
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

        return getResponseEntity(time, request, "GetDictionary");

    }
}
