package ua.univer.custodianNew.controllers;

import dmt.custodian2016.*;
import jakarta.xml.bind.Marshaller;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.univer.custodianNew.dto.FormSearch;
import ua.univer.custodianNew.util.ConverterUtil;
import ua.univer.custodianNew.util.DateTimeUtil;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;

@RestController
@RequestMapping(value = "/api/balance", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BalanceController extends BaseController {


    public BalanceController(Marshaller marshaller, HttpClient httpClient) {
        super(marshaller, httpClient);
    }

    @GetMapping(value = "/account")
    public ResponseEntity<String> balanceAccount(@RequestBody FormSearch form) throws IOException {

        logger.info("Method Balance.");

        Request request = new Request();

        THeaderRequest tHeaderRequest = Util.getHeaderRequest();
        tHeaderRequest.setRequestType("BalanceV2");
        request.setHeader(tHeaderRequest);

        TBalanceRequest balance = new TBalanceRequest();
        balance.setAccount(form.getAccount());
        if (form.getIsin() != null) {
            var tisin = new TISIN();
            tisin.setISIN(form.getIsin());
            tisin.setDepositary(form.getDepositary());
            balance.setISIN(tisin);
        }
        balance.setDateState(DateTimeUtil.oneBoxCalendar(form.getDateState()));

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setBalance(balance);

        request.setBody(tbodyRequest);

        String deckraResponse = writeAndSendRequestWriteResponseToFile(request, "Balance");
        Responce responce = getResponceFromXml(deckraResponse);
        String jsonResponse = ConverterUtil.objectToJson(responce);

        if (responce == null) {
            return ResponseEntity.internalServerError().body("Произошла ошибка " + deckraResponse);
        } else {
            if ("Error".equalsIgnoreCase(responce.getHeader().getResponceType())){
                String answer = String.format("{\"textmistake\": \"%s\"}", responce.getBody().getStatus().getMessage());
                return ResponseEntity.badRequest().body(answer);
            }
            else {
                //String answer = answer(responce);
                return ResponseEntity.ok().body(jsonResponse);
            }
        }
    }

    private static String answer(Responce responce) {
        TStatementOfHoldingRowsV2 rows = (TStatementOfHoldingRowsV2) responce.getBody().getBalance().getRows();
        StringBuilder sb = new StringBuilder();
        List<TStatementOfHoldingRowV2> row = rows.getRow();
        for (var item : row) {
            sb.append("ISIN " + item.getISIN().getISIN() + "\n")
                    .append("Depositary " + item.getISIN().getDepositary() + "\n")
                    .append("ShortName " + item.getISIN().getIssuer().getShortName() + "\n" )
                    .append("IDCode " + item.getISIN().getIssuer().getIDCode() + "\n")
                    .append("Code " + item.getISIN().getType().getCode() + "\n")
                    .append("Name " + item.getISIN().getType().getName() + "\n")
                    .append("Nominal " + item.getISIN().getNominal().getNominal() + "\n")
                    .append("Currency " + item.getISIN().getNominal().getCurrency() + "\n")

                    .append("Quantity " + item.getQuantity() + "\n")
                    .append("faceAmount " + item.getFaceAmount() + "\n")
                    .append("\n");
        }
        return sb.toString();
    }

}

