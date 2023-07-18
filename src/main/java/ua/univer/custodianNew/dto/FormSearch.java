package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class FormSearch {

    @NotBlank
    @Pattern(regexp = "^\\d{8}|\\d{10}$", message = "должно состоять из 8 или 10 цифр")
    private String idCode;

    private String docSerial;
    private String docNumber;

    private String account;

    private String state;
    private String status;
    private String type;

    private String isin;
    private String depositary;
    private String dateState;


    public FormSearch() {
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getDepositary() {
        return depositary;
    }

    public void setDepositary(String depositary) {
        this.depositary = depositary;
    }

    public String getDateState() {
        return dateState;
    }

    public void setDateState(String dateState) {
        this.dateState = dateState;
    }
}
