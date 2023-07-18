package ua.univer.custodianNew.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/service", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
public class DeckraServiceController {

    @PostMapping(value = "/result")
    public String getResult(@RequestHeader HttpHeaders headers, @RequestBody String str){
        // System.out.println(request.getURI());
        System.out.println(headers.toString());
        System.out.println(str);
        return "successful !!!";

    }
}
