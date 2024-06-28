package ua.univer.custodianNew.util;

import dmt.custodian2016.*;

import java.time.LocalDateTime;
import java.util.List;

import static ua.univer.custodianNew.util.DateTimeUtil.xmlGregorianCalendar;

public class ResponceServise {

    public ResponceServise() {
    }

    public static Responce getResponceWithBroker(Responce responce, String brokerID){

        TAccountListV2Full listV2Full = new TAccountListV2Full();
        TAccountListV2Full rows = (TAccountListV2Full) responce.getBody().getAccountListV2().getRows();
        List<TAccountListV2RowFull> accounts = rows.getAccount();
        for (TAccountListV2RowFull account : accounts) {
            if (account.getBrokerAgreements() != null) {
                var brokerAgreement = account.getBrokerAgreements().getBrokerAgreement();
                for (TnewAccountRequest.BrokerAgreements.BrokerAgreement agreement : brokerAgreement) {
                    if (agreement.getCustomer().getCustomerID().toString().equals(brokerID)
                            && (agreement.getDateStop() == null || agreement.getDateStop().compare(xmlGregorianCalendar( LocalDateTime.now())) > 0 )) {
                        listV2Full.getAccount().add(account);
                        break;
                    }
                }
            }
        }
        var accountListV2 = new TAccountListV2();
        accountListV2.setRows(listV2Full);

        TbodyResponce tbodyResponce = new TbodyResponce();
        tbodyResponce.setAccountListV2(accountListV2);
        responce.setBody(tbodyResponce);

        return responce;
    }
}
