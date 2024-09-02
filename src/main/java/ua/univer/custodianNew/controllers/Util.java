package ua.univer.custodianNew.controllers;


import dmt.custodian2016.*;
import org.springframework.util.StringUtils;
import ua.univer.custodianNew.dto.*;
import ua.univer.custodianNew.exceptions.DekraException;

import javax.xml.datatype.XMLGregorianCalendar;

import static ua.univer.custodianNew.util.DateTimeUtil.*;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static ua.univer.custodianNew.config.AppConfiguration.DIRECTORY;

public final class Util {

    private static final String sourceAPP_prod = "E0D397FA-146D-434B-89E0-EA9FF9CDCBC5";
    private static final String sourceAPP_test = "1DD4EC32-45DB-404A-A123-6F657895E502";
    private static final String sourceAPP_80 = "3000350A-429D-4632-81B7-B31C02C7D980";


    private Util() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }


    static void writeStringToFile(String str, String prefix, String suffix){
        File file;
        try {
            Path tempRequest = Files.createTempFile(Path.of(DIRECTORY), prefix, suffix);
            file = new File(tempRequest.toString());
            Files.writeString(file.toPath(), str);
        } catch (IOException e) {
            throw new DekraException("Error in method writeStringToFile");
        }
    }


    static THeaderRequest getHeaderRequest(String number, boolean isTest) {
        int end = Integer.parseInt(number);
        int res = 1_000_000_000 + end;
        THeaderRequest header = new THeaderRequest();
        header.setRequestID("AA0966B1-2E36-4242-85F1-BF" + res);
        header.setTimeStamp(xmlGregorianCalendar( LocalDateTime.now()));
        header.setSourceAPPidentity(isTest ? sourceAPP_test : sourceAPP_prod);
        return header;
    }


    static THeaderRequest getHeaderRequest(boolean isTest) {
        THeaderRequest header = new THeaderRequest();
        header.setRequestID(UUID.randomUUID().toString());
        header.setTimeStamp(xmlGregorianCalendar( LocalDateTime.now()));
        header.setSourceAPPidentity(isTest ? sourceAPP_test : sourceAPP_prod);
        return header;
    }


    static TbodyRequest convertFormToNewAccount(FormNewAccount form){
        TCustomer tCustomer = new TCustomer();

        tCustomer.setAccount(null);

        if (form.getCustomerID() != null) {
            tCustomer.setCustomerID(new BigInteger(form.getCustomerID().toString()));
        }
        var cnum = new TCustomer.CNUM();
        cnum.setValue(form.getCnum());
        tCustomer.setCNUM(cnum);

        var country = new TCustomer.Country();
        country.setValue(form.getCountry());
        tCustomer.setCountry(country);

        var countryTax = new TCustomer.CountryTax();
        countryTax.setValue(form.getCountryTax());
        tCustomer.setCountryTax(countryTax);

        var idCode = new TCustomer.IdCode();
        idCode.setValue(form.getIdCode());
        tCustomer.setIdCode(idCode);

        // OneBox не может передать 0
        var clientTypeCode = new TCustomer.ClientTypeCode();
        clientTypeCode.setValue("-1".equals(form.getClientTypeCode()) ? "0" : form.getClientTypeCode());
        tCustomer.setClientTypeCode(clientTypeCode);

        TName tName = new TName();
        var longName = new TName.LongName();
        longName.setValue(form.getLongName());
        tName.setLongName(longName);
        var shortName = new TName.ShortName();
        shortName.setValue(form.getShortName());
        tName.setShortName(shortName);
        var shortNameInt = new TName.ShortNameInternational();
        shortNameInt.setValue(form.getShortNameInternational());
        tName.setShortNameInternational(shortNameInt);
        var longNameInt = new TName.LongNameInternational();
        longNameInt.setValue(form.getLongNameInternational());
        tName.setLongNameInternational(longNameInt);
        tCustomer.setName(tName);


        var addresses = new TCustomer.Addresses();
        if (form.getAddressFree() != null) {
            String phrase = "фактична адреса згідно довідки ВПО";
            String[] arrAddress = form.getAddressFree().split(phrase, 2);
            Taddress address = new Taddress();
            address.setAddressType(TAddressType.LEGAL);
            address.setCountry(form.getCountryAdr());
            address.setPostIndex(form.getPostIndex());
            address.setRegion(form.getRegion());
            address.setDistrict(form.getDistrict());
            address.setLocality(form.getLocality());
            address.setStreet(form.getStreet());
            address.setHouse(form.getHouse());
            address.setFlat(form.getFlat());
            address.setAddressFree(arrAddress[0].trim());
            addresses.getAddress().add(address);
            if (arrAddress.length > 1) {
                Taddress address2 = new Taddress();
                address2.setAddressType(TAddressType.POST);
                address2.setAddressFree(phrase + " " + arrAddress[1].trim());
                addresses.getAddress().add(address2);
            }
        }
        tCustomer.setAddresses(addresses);


        if (isPerson(form)) {
            TdocFO document = new TdocFO();
            document.setDocSerial(form.getDocSerial());
            document.setDocNumber(form.getDocNumber());
            document.setDocDate(oneBoxCalendar(form.getDocDate()));
            document.setDocWho(form.getDocWho());
            document.setDocType(form.getDocType());
            document.setDocDatestart(oneBoxCalendar(form.getDocDatestart()));
            document.setDocDateStop(oneBoxCalendar(form.getDocDateStop()));
            document.setDocSDRnumber(form.getDocSDRnumber());
            tCustomer.setDocFO(document);
        }
        else {
            // секция длю Юридических лиц
            TdocUO document = new TdocUO();
            document.setDocSerial(form.getDocSerial());
            document.setDocNumber(form.getDocNumber());
            document.setDocDate(oneBoxCalendar(form.getDocDate()));
            document.setDocWho(form.getDocWho());
            tCustomer.setDocUO(document);

            var fund = new TCustomer.Fund();
            fund.setValue(form.getFund());
            tCustomer.setFund(fund);

            var currencyFund = new TCustomer.Currency();
            currencyFund.setValue(form.getCurrencyFund());
            tCustomer.setCurrency(currencyFund);

            var kopf = new TCustomer.Form();
            kopf.setValue(form.getKopf());
            tCustomer.setForm(kopf);
        }


        var bankDetails = new TCustomer.BankDetails();
        /*
        addBankDetail(bankDetails, form.getMfo(), form.getIban(), form.getCardAccount(), form.getBankName(),
                form.getCurrency(), form.getBic(), form.getLei(), form.isUse4Income(), form.getType());
        addBankDetail(bankDetails, form.getMfo1(), form.getIban1(), form.getCardAccount1(), form.getBankName1(),
                form.getCurrency1(), form.getBic1(), form.getLei1(), form.isUse4Income1(), form.getType1());
        addBankDetail(bankDetails, form.getMfo2(), form.getIban2(), form.getCardAccount2(), form.getBankName2(),
                form.getCurrency2(), form.getBic2(), form.getLei2(), form.isUse4Income2(), form.getType2());
        addBankDetail(bankDetails, form.getMfo3(), form.getIban3(), form.getCardAccount3(), form.getBankName3(),
                form.getCurrency3(), form.getBic3(), form.getLei3(), form.isUse4Income3(), form.getType3());
*/
        addBankDetail(bankDetails, form.getMfo(), form.getIban(), form.getCardAccount(), form.getBankName(), form.getCurrency(), form.getBic(),
                form.getLei(), form.isUse4Income(), form.getType(), form.getCorrBankIban(), form.getCorrBankName(), form.getCorrBankBic());
        addBankDetail(bankDetails, form.getMfo1(), form.getIban1(), form.getCardAccount1(), form.getBankName1(), form.getCurrency1(), form.getBic1(),
                form.getLei1(), form.isUse4Income1(), form.getType1(), form.getCorrBankIban1(), form.getCorrBankName1(), form.getCorrBankBic1());
        addBankDetail(bankDetails, form.getMfo2(), form.getIban2(), form.getCardAccount2(), form.getBankName2(), form.getCurrency2(), form.getBic2(),
                form.getLei2(), form.isUse4Income2(), form.getType2(), form.getCorrBankIban2(), form.getCorrBankName2(), form.getCorrBankBic2());
        addBankDetail(bankDetails, form.getMfo3(), form.getIban3(), form.getCardAccount3(), form.getBankName3(), form.getCurrency3(), form.getBic3(),
                form.getLei3(), form.isUse4Income3(), form.getType3(), form.getCorrBankIban3(), form.getCorrBankName3(), form.getCorrBankBic3());

        tCustomer.setBankDetails(bankDetails);


        var contact = new TCustomer.Contact();
        var hhh = new TContact.Phone();
        hhh.setValue(form.getPhone());
        contact.setPhone(hhh);
        var mobilePhone = new TContact.MobilePhone();
        mobilePhone.setValue(form.getMobilePhone());
        contact.setMobilePhone(mobilePhone);
        var eMail = new TContact.EMails();
        var eMailGeneral = new TContact.EMails.EMailGeneral();
        eMailGeneral.setValue(form.geteMailGeneral());
        eMail.setEMailGeneral(eMailGeneral);
        var eMailInvoice = new TContact.EMails.EMailInvoice();
        eMailInvoice.setValue(form.geteMailGeneral());
        eMail.setEMailInvoice(eMailInvoice);
        var eMailCorporateEvent = new TContact.EMails.EMailCorporateEvent();
        eMailCorporateEvent.setValue(form.geteMailGeneral());
        eMail.setEMailCorporateEvent(eMailCorporateEvent);
        var eMailStatement = new TContact.EMails.EMailStatement();
        eMailStatement.setValue(form.geteMailGeneral());
        eMail.setEMailStatement(eMailStatement);
        contact.setEMails(eMail);
        tCustomer.setContact(contact);

        var birthInfo = new TCustomer.BirthInfo();
        birthInfo.setBirthDate(xmlGregorianCalendar(form.getBirthDate()));
        birthInfo.setBirthPlace(form.getBirthPlace());
        tCustomer.setBirthInfo(birthInfo);

        var refusingCode = new TCustomer.RefusingCode();
        refusingCode.setValue(form.isRefusingCode());
        tCustomer.setRefusingCode(refusingCode);


        TnewAccountRequest tnewAccountRequest = new TnewAccountRequest();

        var typeCode = new TnewAccountRequest.NssmcClientTypeCode();
        typeCode.setValue(form.getNssmcClientTypeCode());
        tnewAccountRequest.setNssmcClientTypeCode(typeCode);

        tnewAccountRequest.setCustomer(tCustomer);


        var agreements = new TnewAccountRequest.Agreements();
        var agreement = new TnewAccountRequest.Agreements.Agreement();
        agreement.setNumber(form.getAgreementNumber());
        agreement.setDate(oneBoxCalendar(form.getAgreementDate()));
        agreement.setDateStart(oneBoxCalendar(form.getAgreementDateStart()));
        agreement.setDateStop(oneBoxCalendar(form.getAgreementDateStop()));
        if (form.getAgrID() != null) {
            agreement.setAgrID(new BigInteger(form.getAgrID().toString()));
        }
        agreements.getAgreement().add(agreement);
        tnewAccountRequest.setAgreements(agreements);


        var brokerAgreements = new TnewAccountRequest.BrokerAgreements();
        var brokerAgreement = new TnewAccountRequest.BrokerAgreements.BrokerAgreement();
        if (form.getBrokerCustomerID() != null) {
            brokerAgreement.setCustomerID(new BigInteger(form.getBrokerCustomerID().toString()));
        }
        brokerAgreement.setNumber(form.getBrokerAgreementNumber());
        brokerAgreement.setDate(oneBoxCalendar(form.getBrokerAgreementDate()));
        brokerAgreement.setDateStart(oneBoxCalendar(form.getBrokerAgreementDateStart()));
        brokerAgreement.setDateStop(oneBoxCalendar(form.getBrokerAgreementDateStop()));
        if (form.getBrokerAgrID() != null) {
            brokerAgreement.setAgrID(new BigInteger(form.getBrokerAgrID().toString()));
        }
        brokerAgreements.getBrokerAgreement().add(brokerAgreement);
        tnewAccountRequest.setBrokerAgreements(brokerAgreements);


        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setNewAccount(tnewAccountRequest);

        return tbodyRequest;
    }

/*
    private static TBankDetail getBankDetail(String mfo, String iban, String card, String bank, String currency,
                                             String bic, String lei, boolean use4Income, Integer type) {
        TBankDetail bankDetail = new TBankDetail();
        bankDetail.setMFO(mfo);
        bankDetail.setIBAN(iban);
        bankDetail.setCardAccount(card);
        bankDetail.setBankName(bank);
        bankDetail.setCurrency(currency);
        bankDetail.setBIC(bic);
        bankDetail.setLEI(lei);
        bankDetail.setUse4Income(use4Income);
        bankDetail.setType(new BigInteger(type.toString()));

        return bankDetail;
    }

    private static void addBankDetail(TCustomer.BankDetails bankDetails, String mfo, String iban, String card, String bank, String currency, String bic, String lei, boolean use4Income, Integer type) {
        if (type != null && StringUtils.hasLength(iban)) {
            TBankDetail bankDetail = getBankDetail(mfo, iban, card, bank, currency, bic, lei, use4Income, type);
            bankDetails.getBankDetail().add(bankDetail);
        }
    }*/

    private static void addBankDetailForUpdate(TupdateCustomer.BankDetails bankDetails, String mfo, String iban, String card, String bank, String currency, String bic, String lei, boolean use4Income, Integer type,
                                               String corrBankIban, String corrBankName, String corrBankBic) {
        if (type != null && StringUtils.hasLength(iban)) {
            TBankDetail bankDetail = getBankDetail(mfo, iban, card, bank, currency, bic, lei, use4Income, type, corrBankIban, corrBankName, corrBankBic);
            bankDetail.setChanged(true);
            bankDetails.getBankDetail().add(bankDetail);
        }
    }


    private static void addBankDetail(TCustomer.BankDetails bankDetails, String mfo, String iban, String card, String bank, String currency, String bic, String lei, boolean use4Income, Integer type,
                                         String corrBankIban, String corrBankName, String corrBankBic) {
        if (type != null && StringUtils.hasLength(iban)) {
            TBankDetail bankDetail = getBankDetail(mfo, iban, card, bank, currency, bic, lei, use4Income, type, corrBankIban, corrBankName, corrBankBic);
            bankDetails.getBankDetail().add(bankDetail);
        }
    }

    private static TBankDetail getBankDetail(String mfo, String iban, String card, String bank, String currency, String bic, String lei, boolean use4Income, Integer type,
                                                String corrBankIban, String corrBankName, String corrBankBic) {
        TBankDetail bankDetail = new TBankDetail();
        bankDetail.setMFO(mfo);
        bankDetail.setIBAN(iban);
        bankDetail.setCardAccount(card);
        bankDetail.setBankName(bank);
        bankDetail.setCurrency(currency);
        bankDetail.setBIC(bic);
        bankDetail.setLEI(lei);
        bankDetail.setUse4Income(use4Income);
        bankDetail.setType(new BigInteger(type.toString()));
        var corrBank = new TBankDetailBase.CorrBank();
        corrBank.setIBAN(corrBankIban);
        corrBank.setBankName(corrBankName);
        corrBank.setBIC(corrBankBic);
        bankDetail.setCorrBank(corrBank);

        return bankDetail;
    }


    public static TbodyRequest convertFormToSearchAccountV2(FormSearch form) {

        var searchAccountV2 = new TSearchAccountV2();
        searchAccountV2.setAccountNum(form.getAccount());
        searchAccountV2.setName(form.getName());
        searchAccountV2.setIDCode(form.getIdCode());
        searchAccountV2.setCNUM(form.getCnum());
        searchAccountV2.setState("-1".equals(form.getState()) ? "0" : form.getState());
        searchAccountV2.setStatus(form.getStatus());
        if (form.getDocNumber() != null) {
            var document = new TSearchAccountV2.DocFO();
            document.setDocSerial(form.getDocSerial());
            document.setDocNumber(form.getDocNumber());
            searchAccountV2.setDocFO(document);
        }
        searchAccountV2.setMaxRecord(form.getMaxRecord() == null ? BigInteger.valueOf(20) : new BigInteger(form.getMaxRecord()));
        searchAccountV2.setType(form.getType() == null ? TResponceFilling.SIMPLE : TResponceFilling.DETAILED);

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setSearchAccountV2(searchAccountV2);

        return tbodyRequest;
    }

    public static TbodyRequest convertFormToSearchAccount(FormSearchAccount form) {

        var searchAccount = new TSearchAccount();
        searchAccount.setAccountNum(form.getAccount());
        searchAccount.setIDCode(form.getIdCode());
        searchAccount.setName(form.getName());
        searchAccount.setCNUM(form.getCnum());
        searchAccount.setBoolOper(form.getBoolOper());
        if (form.getDocNumber() != null) {
            var document = new TSearchCustomer.DocFO();
            document.setDocSerial(form.getDocSerial());
            document.setDocNumber(form.getDocNumber());
            searchAccount.setDocFO(document);
        }

        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setSearchAccount(searchAccount);

        return tbodyRequest;
    }

    public static TSearchCustomer convertFormToSearchCustomer(FormSearchCustomer form) {

        var customer = new TSearchCustomer();
        customer.setIDCode(form.getIdCode());
        customer.setName(form.getName());
        customer.setCNUM(form.getCnum());
        customer.setBoolOper(form.getBoolOper());
        if (form.getDocNumber() != null) {
            var document = new TSearchCustomer.DocFO();
            document.setDocSerial(form.getDocSerial());
            document.setDocNumber(form.getDocNumber());
            customer.setDocFO(document);
        }

        return customer;
    }


    public static TupdateCustomer makeCustomerForUpdate (TCustomer origin, FormNewAccount form){

        TupdateCustomer result = new TupdateCustomer();

        result.setAccount(form.getAccount());
        result.setCustomerID(origin.getCustomerID());

        String updatedCnum = form.getCnum();
        if (updatedCnum != null && !updatedCnum.trim().equalsIgnoreCase(origin.getCNUM().getValue())){
            var cnum = new TupdateCustomer.CNUM();
            cnum.setValue(updatedCnum);
            cnum.setChanged(true);
            result.setCNUM(cnum);
        }

        String updatedCountry = form.getCountry();
        if (updatedCountry != null && !updatedCountry.trim().equalsIgnoreCase(origin.getCountry().getValue())){
            var country = new TupdateCustomer.Country();
            country.setValue(updatedCountry);
            country.setChanged(true);
            result.setCountry(country);
        }

        String updatedCountryTax = form.getCountryTax();
        if (updatedCountryTax != null && !updatedCountryTax.trim().equalsIgnoreCase(origin.getCountryTax().getValue())){
            var countryTax = new TupdateCustomer.CountryTax();
            countryTax.setValue(updatedCountryTax);
            countryTax.setChanged(true);
            result.setCountryTax(countryTax);
        }

        /*
        // лишняя секция. clientTypeCode не меняется.
        String updatedClientTypeCode = form.getClientTypeCode();
        if (updatedClientTypeCode != null && !updatedClientTypeCode.trim().equalsIgnoreCase(origin.getClientTypeCode().getValue().trim())){
            var clientTypeCode = new TupdateCustomer.ClientTypeCode();
            clientTypeCode.setValue("-1".equals(updatedClientTypeCode) ? "0" : updatedClientTypeCode);
            clientTypeCode.setChanged(true);
            result.setClientTypeCode(clientTypeCode);
        }
        */

        String updatedShortName = form.getShortName();
        String updatedLongName = form.getLongName();
        var name = new TName();
        if (updatedShortName != null && !updatedShortName.trim().equalsIgnoreCase(origin.getName().getShortName().getValue())) {
            var shortName = new TName.ShortName();
            shortName.setValue(updatedShortName.trim());
            shortName.setChanged(true);
            name.setShortName(shortName);
        }
        if (updatedLongName != null && !updatedLongName.trim().equalsIgnoreCase(origin.getName().getLongName().getValue())) {
            var longName = new TName.LongName();
            longName.setValue(updatedLongName.trim());
            longName.setChanged(true);
            name.setLongName(longName);
        }
        result.setName(name);

        // Меняется только adressFree. Чтобы не затирать в Декре дома, улицы и т.п.
        if (form.getAddressFree() != null){
            String phrase = "фактична адреса згідно довідки ВПО";
            String[] arrAddress = form.getAddressFree().split(phrase, 2);
            List<Taddress> listAddress = origin.getAddresses().getAddress();
            var addressesRes = new TupdateCustomer.Addresses();
            boolean find = false;
            for (Taddress taddress : listAddress) {
                if (taddress.getAddressType() == TAddressType.LEGAL && !arrAddress[0].trim().equals(taddress.getAddressFree())) {
                    Taddress address = new Taddress();
                    address.setAddressType(TAddressType.LEGAL);
                    address.setAddressFree(arrAddress[0].trim());
                    address.setAddressID(BigInteger.valueOf(-1));
                    address.setChanged(true);
                    addressesRes.getAddress().add(address);
                }
                if (arrAddress.length > 1 && taddress.getAddressType() == TAddressType.POST && (phrase + " " + arrAddress[1].trim()).equals(taddress.getAddressFree())) {
                    find = true;
                }
            }
            if ( arrAddress.length > 1 && !find ){
                Taddress address = new Taddress();
                address.setAddressType(TAddressType.POST);
                address.setAddressFree(phrase + " " + arrAddress[1].trim());
                address.setAddressID(BigInteger.valueOf(-1));
                address.setChanged(true);
                addressesRes.getAddress().add(address);
            }
            result.setAddresses(addressesRes);
        }


        String updatedDocNumber = form.getDocNumber();
        if (isPerson(form)) {
            if (updatedDocNumber != null && (origin.getDocFO() == null || !updatedDocNumber.trim().equalsIgnoreCase(origin.getDocFO().getDocNumber()))) {
                TdocFO document = new TdocFO();
                document.setDocSerial(form.getDocSerial());
                document.setDocNumber(form.getDocNumber());
                document.setDocDate(oneBoxCalendar(form.getDocDate()));
                document.setDocWho(form.getDocWho());
                document.setDocType(form.getDocType());
                document.setDocDatestart(oneBoxCalendar(form.getDocDatestart()));
                document.setDocDateStop(oneBoxCalendar(form.getDocDateStop()));
                document.setDocSDRnumber(form.getDocSDRnumber());
                document.setChanged(true);
                result.setDocFO(document);
            }
        }
        else {
            if (updatedDocNumber != null && (origin.getDocUO() == null || !updatedDocNumber.trim().equalsIgnoreCase(origin.getDocUO().getDocNumber()))) {
                TdocUO document = new TdocUO();
                document.setDocSerial(form.getDocSerial());
                document.setDocNumber(form.getDocNumber());
                document.setDocDate(oneBoxCalendar(form.getDocDate()));
                document.setDocWho(form.getDocWho());
                document.setChanged(true);
                result.setDocUO(document);
            }
        }

        var bankDetails = new TupdateCustomer.BankDetails();
        List<TBankDetail> originListBankDetail = origin.getBankDetails().getBankDetail();
        addUpdatedBankDetail(bankDetails, originListBankDetail, form.getMfo(), form.getIban(), form.getCardAccount(), form.getBankName(),
                form.getCurrency(), form.getBic(), form.getLei(), form.isUse4Income(), form.getType(), form.getCorrBankIban(), form.getCorrBankName(), form.getCorrBankBic());
        addUpdatedBankDetail(bankDetails, originListBankDetail, form.getMfo1(), form.getIban1(), form.getCardAccount1(), form.getBankName1(),
                form.getCurrency1(), form.getBic1(), form.getLei1(), form.isUse4Income1(), form.getType1(), form.getCorrBankIban1(), form.getCorrBankName1(), form.getCorrBankBic1());
        addUpdatedBankDetail(bankDetails, originListBankDetail, form.getMfo2(), form.getIban2(), form.getCardAccount2(), form.getBankName2(),
                form.getCurrency2(), form.getBic2(), form.getLei2(), form.isUse4Income2(), form.getType2(), form.getCorrBankIban2(), form.getCorrBankName2(), form.getCorrBankBic2());
        addUpdatedBankDetail(bankDetails, originListBankDetail, form.getMfo3(), form.getIban3(), form.getCardAccount3(), form.getBankName3(),
                form.getCurrency3(), form.getBic3(), form.getLei3(), form.isUse4Income3(), form.getType3(), form.getCorrBankIban3(), form.getCorrBankName3(), form.getCorrBankBic3());
        result.setBankDetails(bankDetails);

        var contact = new TupdateCustomer.Contact();
        String updatedPhone = form.getPhone();
        if (updatedPhone != null && !updatedPhone.trim().equalsIgnoreCase(origin.getContact().getPhone().getValue())) {
            var phone = new TContact.Phone();
            phone.setValue(updatedPhone);
            phone.setChanged(true);
            contact.setPhone(phone);
        }
        String updatedMobilePhone = form.getMobilePhone();
        if (updatedMobilePhone != null && !updatedMobilePhone.trim().equalsIgnoreCase(origin.getContact().getMobilePhone().getValue())){
            var mobilePhone = new TContact.MobilePhone();
            mobilePhone.setValue(updatedMobilePhone);
            mobilePhone.setChanged(true);
            contact.setMobilePhone(mobilePhone);
        }
        String updatedMailGeneral = form.geteMailGeneral();
        if (updatedMailGeneral != null && !updatedMailGeneral.trim().equalsIgnoreCase(origin.getContact().getEMails().getEMailGeneral().getValue())){
            var eMails = new TContact.EMails();
            var eMailGeneral = new TContact.EMails.EMailGeneral();
            eMailGeneral.setValue(updatedMailGeneral);
            eMailGeneral.setChanged(true);
            eMails.setEMailGeneral(eMailGeneral);
            var eMailInvoice = new TContact.EMails.EMailInvoice();
            eMailInvoice.setValue(updatedMailGeneral);
            eMailInvoice.setChanged(true);
            eMails.setEMailInvoice(eMailInvoice);
            var eMailCorporateEvent = new TContact.EMails.EMailCorporateEvent();
            eMailCorporateEvent.setValue(updatedMailGeneral);
            eMailCorporateEvent.setChanged(true);
            eMails.setEMailCorporateEvent(eMailCorporateEvent);
            var eMailStatement = new TContact.EMails.EMailStatement();
            eMailStatement.setValue(updatedMailGeneral);
            eMailStatement.setChanged(true);
            eMails.setEMailStatement(eMailStatement);
            contact.setEMails(eMails);
        }
        result.setContact(contact);

        var birthInfo = new TupdateCustomer.BirthInfo();
        String updatedBirthPlace = form.getBirthPlace();
        if (updatedBirthPlace != null && !updatedBirthPlace.trim().equalsIgnoreCase(origin.getBirthInfo().getBirthPlace())){
            birthInfo.setBirthPlace(updatedBirthPlace.trim());
            birthInfo.setChanged(true);
        }
        XMLGregorianCalendar updatedBirthDate = oneBoxCalendar(form.getBirthDate());
        if (updatedBirthDate != null && updatedBirthDate.compare(origin.getBirthInfo().getBirthDate()) != 0){
            birthInfo.setBirthDate(updatedBirthDate);
            birthInfo.setChanged(true);
        }
        result.setBirthInfo(birthInfo);


        return result;
    }


    private static void addUpdatedBankDetail(TupdateCustomer.BankDetails bankDetails, List<TBankDetail> originListBankDetail, String mfo, String iban, String cardAccount,
                                             String bankName, String currency, String bic, String lei, boolean use4Income, Integer type, String corrBankIban, String corrBankName, String corrBankBic) {
        boolean find = false;
        if (iban != null) {
            for (TBankDetail tBankDetail : originListBankDetail) {
                if (iban.trim().equalsIgnoreCase(tBankDetail.getIBAN())) {
                    find = true;
                    break;
                }
            }
            if (!find) {
                addBankDetailForUpdate(bankDetails, mfo, iban, cardAccount, bankName, currency, bic, lei, use4Income, type, corrBankIban, corrBankName, corrBankBic);
            }
        }
    }

    public static TupdateCustomer cancelBankDetail(TCustomer customer, FormNewAccount form) {

        TupdateCustomer updateCustomer = new TupdateCustomer();
        updateCustomer.setAccount(form.getAccount());
        updateCustomer.setCustomerID(customer.getCustomerID());

        TBankDetail newRekv = new TBankDetail();
        List<TBankDetail> originListBankDetail = customer.getBankDetails().getBankDetail();
        for (TBankDetail tBankDetail : originListBankDetail) {
            if (form.getIban().trim().equalsIgnoreCase(tBankDetail.getIBAN())) {
                TBankDetail.Period period = tBankDetail.getPeriod();
                if(period == null){
                    period = new TBankDetail.Period();
                    System.out.println("------ Attention!! Very rare case. ------");
                }
                period.setDateStop(xmlGregorianCalendar(LocalDateTime.now()));
                tBankDetail.setPeriod(period);
                tBankDetail.setUse4Income(false);
                tBankDetail.setChanged(true);
                newRekv = tBankDetail;
                break;
            }
        }
        var bankDetails = new TupdateCustomer.BankDetails();
        bankDetails.getBankDetail().add(newRekv);
        updateCustomer.setBankDetails(bankDetails);

        return updateCustomer;
    }

    public static boolean isPerson (BaseForm obj){
        if(obj instanceof FormNewAccount form){
            if ("110".equals(form.getNssmcClientTypeCode()) || "310".equals(form.getNssmcClientTypeCode())){
                return true;
            }
            if (form.getIdCode() != null && form.getIdCode().length() == 10){
                return true;
            }
        }
        return false;
    }
}

