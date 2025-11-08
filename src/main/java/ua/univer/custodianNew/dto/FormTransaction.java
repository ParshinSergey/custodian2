package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormTransaction extends BaseForm{

    @NotBlank
    private String isin;
    private String depositary;
    private long quantity;

    @NotBlank
    private String number;
    @NotBlank
    private String date;
    private double agreementCost;
    private String agreementCurrency;
    private Integer agreementType;
    private String agreementTypeName;
/*
    private String transactionProcessing;
    private boolean commitAfterRegistr;
    // private String statementInResponse;
    private boolean requestInResponse;
    private String transationType;
    private boolean individualBLK;
*/

    private String mdo;
    @NotBlank
    private String account;
    private String name;
    private String idCode;
    private String docSerial;
    private String docNumber;
    private String docDate;
    private String docWho;
    private Integer level;
    private Integer accountType;
    private String brokerIdCode;
    private String brokerName;
    private String agregateAccountID;
    private String participantMFO;
    private String participantIban;
    private String participantCode;
    private String finalBeneficiary;

    private String mdo2;
    @NotBlank
    private String account2;
    private String name2;
    private String idCode2;
    private String docSerial2;
    private String docNumber2;
    private String docDate2;
    private String docWho2;
    private Integer level2;
    private Integer accountType2;
    private String brokerIdCode2;
    private String brokerName2;
    private String agregateAccountID2;
    private String participantMFO2;
    private String participantIban2;
    private String participantCode2;
    private String finalBeneficiary2;



}
