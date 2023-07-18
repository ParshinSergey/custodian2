package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class FormGet {

    @Pattern(regexp = "^\\d{3}$", message = "должно состоять из трех цифр")
    private String nssmcClientTypeCode;

    @NotBlank
    @Pattern(regexp = "^\\d{3,10}$")
    private String cnum;

    @NotBlank
    @Pattern(regexp = "-1|0|1|4|7|8|100|200|300|777|999")
    private String clientTypeCode;

    @NotBlank
    @Pattern(regexp = "^\\d{3}$", message = "должно состоять из трех цифр")
    private String country;


    public FormGet() {
    }


    public String getNssmcClientTypeCode() {
        return nssmcClientTypeCode;
    }

    public void setNssmcClientTypeCode(String nssmcClientTypeCode) {
        this.nssmcClientTypeCode = nssmcClientTypeCode;
    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    public String getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(String clientTypeCode) {
        this.clientTypeCode = clientTypeCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
