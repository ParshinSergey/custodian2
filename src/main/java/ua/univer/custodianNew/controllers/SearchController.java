package ua.univer.custodianNew.controllers;

import dmt.custodian2016.Request;
import dmt.custodian2016.THeaderRequest;
import dmt.custodian2016.TbodyRequest;
import jakarta.xml.bind.Marshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.univer.custodianNew.dto.FormSearch;

import java.io.IOException;
import java.net.http.HttpClient;

@RestController
@RequestMapping(value = "/api/search", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SearchController extends BaseController{

    //Logger logger = LoggerFactory.getLogger(SearchController.class);

    public SearchController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }


    @GetMapping(value = "/accountV2")
    public ResponseEntity<String> searchAccountV2 (@RequestBody FormSearch form) throws IOException {

        logger.info("Method SearchAccountV2.");
        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest();
        tHeaderRequest.setRequestType("SearchAccountV2");
        request.setHeader(tHeaderRequest);

        TbodyRequest tbodyRequest = Util.convertFormToSearchAccountV2(form);
        request.setBody(tbodyRequest);

        String deckraResponse = writeAndSendRequestWriteResponseToFile(request, "SearchAccV2");

        return ResponseEntity.ok().body(deckraResponse);
    }


    @GetMapping(value = "/account")
    public ResponseEntity<String> searchAccount (@RequestBody FormSearch form) throws IOException {

        logger.info("Method SearchAccount.");
        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest();
        tHeaderRequest.setRequestType("SearchAccount");
        request.setHeader(tHeaderRequest);

        TbodyRequest tbodyRequest = Util.convertFormToSearchAccount(form);
        request.setBody(tbodyRequest);

        String deckraResponse = writeAndSendRequestWriteResponseToFile(request, "SearchAcc");

        return ResponseEntity.ok().body(deckraResponse);
    }



}
