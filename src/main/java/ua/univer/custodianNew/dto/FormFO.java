package ua.univer.custodianNew.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
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

    private String phone;
    private String mobilePhone;
    @Email
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
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

    // поля для Юридических лиц
    private String shortNameInternational;
    private String longNameInternational;
    private float fund;
    private String currencyFund;
    private String form;


    public FormFO() {
    }

    public String geteMailGeneral() {
        return eMailGeneral;
    }

    public void seteMailGeneral(String eMailGeneral) {
        this.eMailGeneral = eMailGeneral;
    }


}
