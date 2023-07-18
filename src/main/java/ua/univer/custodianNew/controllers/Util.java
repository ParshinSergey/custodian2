package ua.univer.custodianNew.controllers;


import dmt.custodian2016.*;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import ua.univer.custodianNew.dto.FormFO;
import ua.univer.custodianNew.dto.FormSearch;
import static ua.univer.custodianNew.util.DateTimeUtil.*;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.UUID;

import static ua.univer.custodianNew.config.AppConfiguration.DIRECTORY;

public final class Util {

    public static String RESP_EXAMPLE = """
                <cust:responce xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:cust="urn:dmt:custodian2016"><cust:header><cust:ResponceType>newAccount</cust:ResponceType><cust:ResponceID>E1DFEE6F-F23B-47F0-98CE-4E29377C1300</cust:ResponceID><cust:TimeStamp>2023-07-05T15:42:24.017</cust:TimeStamp><cust:RequestID>AA0966B1-2E36-4242-85F1-BF1000059158</cust:RequestID><cust:binary><cust:binary>0</cust:binary><cust:outputFormat>0</cust:outputFormat></cust:binary></cust:header><cust:body><cust:account><cust:nssmcClientTypeCode>110</cust:nssmcClientTypeCode><cust:Customer><cust:Account>401836-UA10700082</cust:Account><cust:CustomerID>105633</cust:CustomerID><cust:CNUM>59158</cust:CNUM><cust:country>804</cust:country><cust:CountryTax>804</cust:CountryTax><cust:idCode>3362315014</cust:idCode><cust:ClientTypeCode>0</cust:ClientTypeCode><cust:name><cust:shortName>Шкурко В'ячеслав Миколайович</cust:shortName><cust:longName>Шкурко В'ячеслав Миколайович</cust:longName></cust:name><cust:addresses><cust:address><cust:addressID>-1</cust:addressID><cust:addressType>legal</cust:addressType><cust:country>804</cust:country><cust:addressFree>90300, Україна, ЗАКАРПАТСЬКА ОБЛ., БЕРЕГІВСЬКИЙ Р-Н, М.ВИНОГРАДІВ, ВУЛ. ТЮЛЬПАНІВ, БУД.1</cust:addressFree></cust:address></cust:addresses><cust:docFO><cust:docSerial/><cust:docNumber>007481641</cust:docNumber><cust:docDate>2022-02-02</cust:docDate><cust:docWho>2114</cust:docWho><cust:docType>Паспорт громадянина України з безконтактним електронним носієм</cust:docType><cust:docDatestart>2022-02-02</cust:docDatestart><cust:docDateStop>2032-02-02</cust:docDateStop><cust:docSDRnumber>19920121-10811</cust:docSDRnumber></cust:docFO><cust:bankDetails><cust:bankDetail><cust:IBAN>UA173052990000026203730586229</cust:IBAN><cust:currency>840</cust:currency><cust:bankDetailID>13608</cust:bankDetailID><cust:use4income>false</cust:use4income><cust:Period><cust:DateStart>2023-07-05</cust:DateStart></cust:Period><cust:Type>1</cust:Type></cust:bankDetail><cust:bankDetail><cust:IBAN>UA343052990000026205678832572</cust:IBAN><cust:currency>980</cust:currency><cust:bankDetailID>13607</cust:bankDetailID><cust:use4income>false</cust:use4income><cust:Period><cust:DateStart>2023-07-05</cust:DateStart></cust:Period><cust:Type>1</cust:Type></cust:bankDetail></cust:bankDetails><cust:addParams><cust:param><cust:Name>CNUM</cust:Name><cust:Value>59158</cust:Value></cust:param><cust:param><cust:Name>SDRnumber</cust:Name><cust:Value>19920121-10811</cust:Value></cust:param><cust:param><cust:Name>MainPhone</cust:Name><cust:Value>380678896093</cust:Value></cust:param><cust:param><cust:Name>MobilePhone</cust:Name><cust:Value>380678896093</cust:Value></cust:param><cust:param><cust:Name>EMail_Alter</cust:Name><cust:Value>slavian210192@gmail.com</cust:Value></cust:param></cust:addParams><cust:contact><cust:Phone>380678896093</cust:Phone><cust:mobilePhone>380678896093</cust:mobilePhone><cust:eMails><cust:eMail_general>slavian210192@gmail.com</cust:eMail_general></cust:eMails></cust:contact><cust:BirthInfo><cust:BirthDate>1992-01-21</cust:BirthDate><cust:BirthPlace>м. Белгород</cust:BirthPlace></cust:BirthInfo><cust:refusingCode>0</cust:refusingCode></cust:Customer><cust:agreements><cust:agreement><cust:number>105300</cust:number><cust:date>2023-06-15</cust:date><cust:dateStart>2023-06-15</cust:dateStart><cust:AgrID>10627</cust:AgrID></cust:agreement></cust:agreements><cust:brokerAgreements><cust:brokerAgreement><cust:number>БО-230615-0001</cust:number><cust:date>2023-06-15</cust:date><cust:dateStart>2023-06-15</cust:dateStart><cust:Customer><cust:CustomerID>87140</cust:CustomerID><cust:country>804</cust:country><cust:CountryTax>804</cust:CountryTax><cust:idCode>3451316470</cust:idCode><cust:ClientTypeCode>0</cust:ClientTypeCode><cust:name><cust:shortName>Халос Н. О.</cust:shortName><cust:longName>Халос Назар Олегович</cust:longName><cust:shortName_International/><cust:longName_International/></cust:name><cust:addresses><cust:address><cust:addressID>-1</cust:addressID><cust:addressType>legal</cust:addressType><cust:country>804</cust:country><cust:postIndex>82000</cust:postIndex><cust:region>46000</cust:region><cust:district>Старосамбірський р-н.</cust:district><cust:locality>місто Старий Самбір</cust:locality><cust:street>вул. Дністрова</cust:street><cust:house>87</cust:house><cust:flat>29</cust:flat></cust:address></cust:addresses><cust:docFO><cust:docSerial>КС</cust:docSerial><cust:docNumber>787651</cust:docNumber><cust:docDate>2010-11-10</cust:docDate><cust:docWho>Старосамбірським РВ ГУМВС України у Львівській обл.</cust:docWho><cust:docType>Паспорт</cust:docType><cust:docDatestart>2019-07-04</cust:docDatestart><cust:docDateStop>1899-12-30</cust:docDateStop></cust:docFO><cust:contact/><cust:BirthInfo><cust:BirthPlace>село Ралівка, Самбірського району, Львівської області</cust:BirthPlace></cust:BirthInfo><cust:refusingCode>0</cust:refusingCode><cust:Form/></cust:Customer></cust:brokerAgreement></cust:brokerAgreements><cust:Controlling/><cust:accountNumber>401836-UA10700082</cust:accountNumber><cust:State>0</cust:State><cust:Status>1</cust:Status><cust:accountDateopen>2023-07-05</cust:accountDateopen><cust:Level><cust:NDU>2</cust:NDU><cust:NBU>2</cust:NBU></cust:Level><cust:NBU><cust:BaseAggregated_id>5</cust:BaseAggregated_id></cust:NBU><cust:AccountID>9626</cust:AccountID></cust:account></cust:body></cust:responce>
                """;


    private Util() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }


    static File getFile(String prefix, String suffix) {
        File file;
        try {
            Path tempRequest = Files.createTempFile(Path.of(DIRECTORY), prefix, suffix);
            file = new File(tempRequest.toString());
        } catch (IOException e) {
            throw new RuntimeException("File was not created");
        }
        return file;
    }


    static THeaderRequest getHeaderRequest(String number) {
        int end = Integer.parseInt(number);
        int res = 1_000_000_000 + end;
        THeaderRequest header = new THeaderRequest();
        header.setRequestID("AA0966B1-2E36-4242-85F1-BF" + res);
        header.setTimeStamp(xmlGregorianCalendar( LocalDateTime.now()));
        header.setSourceAPPidentity("1DD4EC32-45DB-404A-A123-6F657895E502");
        return header;
    }


    static THeaderRequest getHeaderRequest() {
        THeaderRequest header = new THeaderRequest();
        header.setRequestID(UUID.randomUUID().toString());
        header.setTimeStamp(xmlGregorianCalendar( LocalDateTime.now()));
        header.setSourceAPPidentity("1DD4EC32-45DB-404A-A123-6F657895E502");
        return header;
    }

    static TbodyRequest convertFormToNewAccount(FormFO form){
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
        if ("-1".equals(form.getClientTypeCode())){
            form.setClientTypeCode("0");
        }
        clientTypeCode.setValue(form.getClientTypeCode());
        tCustomer.setClientTypeCode(clientTypeCode);

        TName tName = new TName();
        var longName = new TName.LongName();
        longName.setValue(form.getLongName());
        var shortName = new TName.ShortName();
        shortName.setValue(form.getShortName());
        tName.setLongName(longName);
        tName.setShortName(shortName);
        tCustomer.setName(tName);

        var addresses = new TCustomer.Addresses();
        Taddress address = new Taddress();
        if(form.getAddressType() != null){
        switch (form.getAddressType().toUpperCase().trim()){
           case "LEGAL" ->  address.setAddressType(TAddressType.LEGAL);
           case "POST" ->  address.setAddressType(TAddressType.POST);
           case "OTHER" ->  address.setAddressType(TAddressType.OTHER);
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "addressType должно быть LEGAL|POST|OTHER");
        }}
        address.setCountry(form.getCountryAdr());
        address.setPostIndex(form.getPostIndex());
        address.setRegion(form.getRegion());
        address.setDistrict(form.getDistrict());
        address.setLocality(form.getLocality());
        address.setStreet(form.getStreet());
        address.setHouse(form.getHouse());
        address.setFlat(form.getFlat());
        address.setAddressFree(form.getAddressFree());
        addresses.getAddress().add(address);
        tCustomer.setAddresses(addresses);

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

        TBankDetail bankDetail = new TBankDetail();
        bankDetail.setMFO(form.getMfo());
        bankDetail.setIBAN(form.getIban());
        bankDetail.setCardAccount(form.getCardAccount());
        bankDetail.setBankName(form.getBankName());
        bankDetail.setCurrency(form.getCurrency());
        bankDetail.setBIC(form.getBic());
        bankDetail.setLEI(form.getLei());
        if (form.getBankDetailID() != null) {
            bankDetail.setBankDetailID(new BigInteger(form.getBankDetailID().toString()));
        }
        if (form.isUse4Income()) {
            bankDetail.setUse4Income(form.isUse4Income());
        }
        if (form.getType() != null) {
            bankDetail.setType(new BigInteger(form.getType().toString()));
        }
        var bankDetails = new TCustomer.BankDetails();
        bankDetails.getBankDetail().add(bankDetail);

        if (form.getType1() != null && StringUtils.hasLength(form.getIban1())) {
            TBankDetail bankDetail1 = new TBankDetail();
            bankDetail1.setIBAN(form.getIban1());
            bankDetail1.setCurrency(form.getCurrency1());
            if (form.isUse4Income1()) {
                bankDetail1.setUse4Income(form.isUse4Income1());}
            bankDetail1.setType(new BigInteger(form.getType1().toString()));
            bankDetails.getBankDetail().add(bankDetail1);
        }
        if (form.getType2() != null && StringUtils.hasLength(form.getIban2())) {
            TBankDetail bankDetail2 = new TBankDetail();
            bankDetail2.setIBAN(form.getIban2());
            bankDetail2.setCurrency(form.getCurrency2());
            if (form.isUse4Income2()) {
                bankDetail2.setUse4Income(form.isUse4Income2());}
            bankDetail2.setType(new BigInteger(form.getType2().toString()));
            bankDetails.getBankDetail().add(bankDetail2);
        }
        if (form.getType3() != null && StringUtils.hasLength(form.getIban3())) {
            TBankDetail bankDetail3 = new TBankDetail();
            bankDetail3.setIBAN(form.getIban3());
            bankDetail3.setCurrency(form.getCurrency3());
            if (form.isUse4Income3()) {
                bankDetail3.setUse4Income(form.isUse4Income3());}
            bankDetail3.setType(new BigInteger(form.getType3().toString()));
            bankDetails.getBankDetail().add(bankDetail3);
        }
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


    public static TbodyRequest convertFormToSearchAccountV2(FormSearch form) {

        var searchAccountV2 = new TSearchAccountV2();
        searchAccountV2.setAccountNum(form.getAccount());
        searchAccountV2.setIDCode(form.getIdCode());
        searchAccountV2.setState(form.getState());
        searchAccountV2.setStatus(form.getStatus());
        searchAccountV2.setType(TResponceFilling.SIMPLE);

        if (form.getDocNumber() != null) {
            var document = new TSearchAccountV2.DocFO();
            document.setDocSerial(form.getDocSerial());
            document.setDocNumber(form.getDocNumber());
            searchAccountV2.setDocFO(document);
        }
        TbodyRequest tbodyRequest = new TbodyRequest();
        tbodyRequest.setSearchAccountV2(searchAccountV2);

        return tbodyRequest;

    }

    public static TbodyRequest convertFormToSearchAccount(FormSearch form) {

        var searchAccount = new TSearchAccount();
        searchAccount.setAccountNum(form.getAccount());
        searchAccount.setIDCode(form.getIdCode());
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
}

