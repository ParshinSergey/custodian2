package ua.univer.custodianNew.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class FormNewAccount extends BaseForm{

    @NotBlank
    @DecimalMax(value = "100000000")
    private String requestID;

    private String account;

    @NotBlank
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
    @Pattern(regexp = KEYBOARD_SYMBOLS, message = "- содержит недопустимые символы")
    private String addressFree;

    private String docSerial;
    private String docNumber;
    private String docDate;
    @Pattern(regexp = KEYBOARD_SYMBOLS, message = "- содержит недопустимые символы")
    private String docWho;
    private String docType;
    private String docDatestart;
    private String docDateStop;
    private String docSDRnumber;

    @Pattern(regexp = "^\\d{6}$|^$", message = "должно состоять из шести цифр или быть пустым")
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
    private boolean use4Income;
    private Integer type;

    private String corrBankIban;
    private String corrBankName;
    private String corrBankBic;


    @Pattern(regexp = "^\\d{6}$|^$", message = "должно состоять из шести цифр или быть пустым")
    private String mfo1;
    @Size(max = 34)
    private String iban1;
    @Size(max = 50)
    private String cardAccount1;
    @Size(max = 80)
    private String bankName1;
    @Pattern(regexp = "^\\d{3}$", message = "должно состоять из трех цифр")
    private String currency1;
    @Size(max = 14)
    private String bic1;
    @Size(max = 20)
    private String lei1;
    private boolean use4Income1;
    private Integer type1;
    private String corrBankIban1;
    private String corrBankName1;
    private String corrBankBic1;

    @Pattern(regexp = "^\\d{6}$|^$", message = "должно состоять из шести цифр или быть пустым")
    private String mfo2;
    @Size(max = 34)
    private String iban2;
    @Size(max = 50)
    private String cardAccount2;
    @Size(max = 80)
    private String bankName2;
    @Pattern(regexp = "^\\d{3}$", message = "должно состоять из трех цифр")
    private String currency2;
    @Size(max = 14)
    private String bic2;
    @Size(max = 20)
    private String lei2;
    private boolean use4Income2;
    private Integer type2;
    private String corrBankIban2;
    private String corrBankName2;
    private String corrBankBic2;

    @Pattern(regexp = "^\\d{6}$|^$", message = "должно состоять из шести цифр или быть пустым")
    private String mfo3;
    @Size(max = 34)
    private String iban3;
    @Size(max = 50)
    private String cardAccount3;
    @Size(max = 80)
    private String bankName3;
    @Pattern(regexp = "^\\d{3}$", message = "должно состоять из трех цифр")
    private String currency3;
    @Size(max = 14)
    private String bic3;
    @Size(max = 20)
    private String lei3;
    private boolean use4Income3;
    private Integer type3;
    private String corrBankIban3;
    private String corrBankName3;
    private String corrBankBic3;

    private String phone;
    private String mobilePhone;
    @Email
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String eMailGeneral;
    private String birthDate;
    @Pattern(regexp = KEYBOARD_SYMBOLS, message = "- содержит недопустимые символы")
    private String birthPlace;
    private boolean refusingCode;

    private String agreementNumber;
    private String agreementDate;
    private String agreementDateStart;
    private String agreementDateStop;

    private Integer brokerCustomerID;
    private String brokerAgreementNumber;
    private String brokerAgreementDate;
    private String brokerAgreementDateStart;
    private String brokerAgreementDateStop;

    // поля для Юридических лиц
    private String shortNameInternational;
    private String longNameInternational;
    private double fund;
    private String currencyFund;
    private String kopf;

    // поля для Распорядителя Счета
    private String controllingSubjectType;
    @Pattern(regexp = "^\\d{10}$", message = "должно состоять из 10 цифр")
    private String managerIdCode;
    private boolean managerRefusingCode;
    private String managerCountry;
    @Size(max = 150)
    private String managerLastName;
    @Size(max = 150)
    private String managerFirstName;
    @Size(max = 150)
    private String managerMiddleName;
    private String managerDocSerial;
    private String managerDocNumber;
    private String managerDocDate;
    @Pattern(regexp = KEYBOARD_SYMBOLS, message = "- содержит недопустимые символы")
    private String managerDocWho;
    private String managerDocType;
    private String managerDocDateStart;
    private String managerDocDateStop;
    private String managerDocSDRNumber;
    @Size(max = 300)
    @Pattern(regexp = KEYBOARD_SYMBOLS, message = "- содержит недопустимые символы")
    private String managerPostAddress;
    @Size(max = 300)
    @Pattern(regexp = KEYBOARD_SYMBOLS, message = "- содержит недопустимые символы")
    private String managerLegalAddress;
    @Size(max = 100)
    private String managerPost;
    @Size(max = 100)
    private String managerDepartment;
    private String managerPhone;
    private String managerEMail;
    private String managerComment;
    private String managerDateStart;
    private String managerDateStop;
    @Size(max = 80)
    private String managerReason;



    public FormNewAccount() {
    }

    public String geteMailGeneral() {
        return eMailGeneral;
    }

    public void seteMailGeneral(String eMailGeneral) {
        this.eMailGeneral = eMailGeneral;
    }


}
