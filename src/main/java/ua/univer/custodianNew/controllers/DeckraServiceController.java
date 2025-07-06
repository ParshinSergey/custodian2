package ua.univer.custodianNew.controllers;

import dmt.custodian2016.Request;
import dmt.custodian2016.TbodyRequest;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;

@Hidden
@RestController
@RequestMapping(value = "/api/service", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
public class DeckraServiceController extends BaseController{

    public DeckraServiceController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }

    @PostMapping(value = "/result")
    public String getResult(@RequestHeader HttpHeaders headers, @RequestBody String str){
        // System.out.println(request.getURI());
        System.out.println(headers.toString());
        System.out.println(str);
        return "successful !!";

    }

    @GetMapping(value = "/echo", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<String> echo (@RequestBody String str){

        long time = System.nanoTime();
        String methodName = "Echo";
        Request request = getRequestWithHeader(methodName, true);

        TbodyRequest tbodyRequest = new TbodyRequest();
        TbodyRequest.Echo rrr = new TbodyRequest.Echo();
        rrr.setEcho(str);
        tbodyRequest.setEcho(rrr);

        request.setBody(tbodyRequest);

        return getResponseEntity(time, request, DEKRA_URL_80, methodName);

    }



}
