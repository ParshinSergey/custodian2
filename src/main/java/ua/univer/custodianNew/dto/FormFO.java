package ua.univer.custodianNew.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormFO {

    @NotBlank
    @DecimalMax(value = "100000000")
    private String requestID;

    private String account;

    @Pattern(regexp = "^\\d{3}$", message = "должно состоять из трех цифр")
    private String nssmcClientTypeCode;

    private Integer customerID;

    private String cnum;

    @NotBlank
    @Pattern(regexp = "^\\d{3}$", message = "должно состоять из трех цифр")
    private String country;

    @Pattern(regexp = "^\\d{3}$", message = "должно состоять из трех цифр")
    private String countryTax;

    @NotBlank
    @Pattern(regexp = "^\\d{8}|\\d{10}$", message = "должно состоять из 8 или 10 цифр")
    private String idCode;

    @NotBlank
    @Pattern(regexp = "-1|0|1|4|7|8|100|200|300|777|999")
    private String clientTypeCode;

    @NotBlank
    private String shortName;

    private String longName;

    private String addressType;
    private String countryAdr;
    private String postIndex;
    private String region;
    private String district;
    private String locality;
    private String street;
    private String house;
    private String flat;
    @Size(max = 256)
    private String addressFree;

    private String docSerial;
    private String docNumber;
    private String docDate;
    private String docWho;
    private String docType;
    private String docDatestart;
    private String docDateStop;
    private String docSDRnumber;

    @Size(min = 6, max = 6)
    private String mfo;
    @Size(max = 34)
    private String iban;
    @Size(max = 50)
    private String cardAccount;
    @Size(max = 80)
    private String bankName;
    @NotBlank
    @Pattern(regexp = "^\\d{3}$", message = "должно состоять из трех цифр")
    private String currency;
    @Size(max = 14)
    private String bic;
    @Size(max = 20)
    private String lei;
    private Integer bankDetailID;
    private boolean use4Income;
    private Integer type;

    @Size(max = 34)
    private String iban1;
    @Pattern(regexp = "^\\d{3}$", message = "должно состоять из трех цифр")
    private String currency1;
    private boolean use4Income1;
    private Integer type1;

    @Size(max = 34)
    private String iban2;
    @Pattern(regexp = "^\\d{3}$", message = "должно состоять из трех цифр")
    private String currency2;
    private boolean use4Income2;
    private Integer type2;

    @Size(max = 34)
    private String iban3;
    @Pattern(regexp = "^\\d{3}$", message = "должно состоять из трех цифр")
    private String currency3;
    private boolean use4Income3;
    private Integer type3;

    private String phone;
    private String mobilePhone;
    @Email
    private String eMailGeneral;
    private String birthDate;
    private String birthPlace;
    private boolean refusingCode;

    private String agreementNumber;
    private String agreementDate;
    private String agreementDateStart;
    private String agreementDateStop;
    private Integer agrID;

    private Integer brokerCustomerID;
    private String brokerAgreementNumber;
    private String brokerAgreementDate;
    private String brokerAgreementDateStart;
    private String brokerAgreementDateStop;
    private Integer brokerAgrID;



    public FormFO() {
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNssmcClientTypeCode() {
        return nssmcClientTypeCode;
    }

    public void setNssmcClientTypeCode(String nssmcClientTypeCode) {
        this.nssmcClientTypeCode = nssmcClientTypeCode;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryTax() {
        return countryTax;
    }

    public void setCountryTax(String countryTax) {
        this.countryTax = countryTax;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(String clientTypeCode) {
        this.clientTypeCode = clientTypeCode;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getCountryAdr() {
        return countryAdr;
    }

    public void setCountryAdr(String countryAdr) {
        this.countryAdr = countryAdr;
    }

    public String getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(String postIndex) {
        this.postIndex = postIndex;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getAddressFree() {
        return addressFree;
    }

    public void setAddressFree(String addressFree) {
        this.addressFree = addressFree;
    }

    public String getDocSerial() {
        return docSerial;
    }

    public void setDocSerial(String docSerial) {
        this.docSerial = docSerial;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getDocWho() {
        return docWho;
    }

    public void setDocWho(String docWho) {
        this.docWho = docWho;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocDatestart() {
        return docDatestart;
    }

    public void setDocDatestart(String docDatestart) {
        this.docDatestart = docDatestart;
    }

    public String getDocDateStop() {
        return docDateStop;
    }

    public void setDocDateStop(String docDateStop) {
        this.docDateStop = docDateStop;
    }

    public String getDocSDRnumber() {
        return docSDRnumber;
    }

    public void setDocSDRnumber(String docSDRnumber) {
        this.docSDRnumber = docSDRnumber;
    }

    public String getMfo() {
        return mfo;
    }

    public void setMfo(String mfo) {
        this.mfo = mfo;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(String cardAccount) {
        this.cardAccount = cardAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getLei() {
        return lei;
    }

    public void setLei(String lei) {
        this.lei = lei;
    }

    public Integer getBankDetailID() {
        return bankDetailID;
    }

    public void setBankDetailID(Integer bankDetailID) {
        this.bankDetailID = bankDetailID;
    }

    public boolean isUse4Income() {
        return use4Income;
    }

    public void setUse4Income(boolean use4Income) {
        this.use4Income = use4Income;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public String getIban1() {
        return iban1;
    }

    public void setIban1(String iban1) {
        this.iban1 = iban1;
    }

    public String getCurrency1() {
        return currency1;
    }

    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }

    public boolean isUse4Income1() {
        return use4Income1;
    }

    public void setUse4Income1(boolean use4Income1) {
        this.use4Income1 = use4Income1;
    }

    public Integer getType1() {
        return type1;
    }

    public void setType1(Integer type1) { this.type1 = type1; }

    public String getIban2() {
        return iban2;
    }

    public void setIban2(String iban2) {
        this.iban2 = iban2;
    }

    public String getCurrency2() {
        return currency2;
    }

    public void setCurrency2(String currency2) {
        this.currency2 = currency2;
    }

    public boolean isUse4Income2() {
        return use4Income2;
    }

    public void setUse4Income2(boolean use4Income2) {
        this.use4Income2 = use4Income2;
    }

    public Integer getType2() {
        return type2;
    }

    public void setType2(Integer type2) {
        this.type2 = type2;
    }

    public String getIban3() {
        return iban3;
    }

    public void setIban3(String iban3) {
        this.iban3 = iban3;
    }

    public String getCurrency3() {
        return currency3;
    }

    public void setCurrency3(String currency3) {
        this.currency3 = currency3;
    }

    public boolean isUse4Income3() {
        return use4Income3;
    }

    public void setUse4Income3(boolean use4Income3) {
        this.use4Income3 = use4Income3;
    }

    public Integer getType3() {
        return type3;
    }

    public void setType3(Integer type3) {
        this.type3 = type3;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String geteMailGeneral() {
        return eMailGeneral;
    }

    public void seteMailGeneral(String eMailGeneral) {
        this.eMailGeneral = eMailGeneral;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public boolean isRefusingCode() {
        return refusingCode;
    }

    public void setRefusingCode(boolean refusingCode) {
        this.refusingCode = refusingCode;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public String getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(String agreementDate) {
        this.agreementDate = agreementDate;
    }

    public String getAgreementDateStart() {
        return agreementDateStart;
    }

    public void setAgreementDateStart(String agreementDateStart) {
        this.agreementDateStart = agreementDateStart;
    }

    public String getAgreementDateStop() {
        return agreementDateStop;
    }

    public void setAgreementDateStop(String agreementDateStop) {
        this.agreementDateStop = agreementDateStop;
    }

    public Integer getAgrID() {
        return agrID;
    }

    public void setAgrID(Integer agrID) {
        this.agrID = agrID;
    }

    public Integer getBrokerCustomerID() {
        return brokerCustomerID;
    }

    public void setBrokerCustomerID(Integer brokerCustomerID) {
        this.brokerCustomerID = brokerCustomerID;
    }

    public String getBrokerAgreementNumber() {
        return brokerAgreementNumber;
    }

    public void setBrokerAgreementNumber(String brokerAgreementNumber) {
        this.brokerAgreementNumber = brokerAgreementNumber;
    }

    public String getBrokerAgreementDate() {
        return brokerAgreementDate;
    }

    public void setBrokerAgreementDate(String brokerAgreementDate) {
        this.brokerAgreementDate = brokerAgreementDate;
    }

    public String getBrokerAgreementDateStart() {
        return brokerAgreementDateStart;
    }

    public void setBrokerAgreementDateStart(String brokerAgreementDateStart) {
        this.brokerAgreementDateStart = brokerAgreementDateStart;
    }

    public String getBrokerAgreementDateStop() {
        return brokerAgreementDateStop;
    }

    public void setBrokerAgreementDateStop(String brokerAgreementDateStop) {
        this.brokerAgreementDateStop = brokerAgreementDateStop;
    }

    public Integer getBrokerAgrID() {
        return brokerAgrID;
    }

    public void setBrokerAgrID(Integer brokerAgrID) {
        this.brokerAgrID = brokerAgrID;
    }
}
