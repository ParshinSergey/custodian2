package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormSearch {

    private String account;
    private String name;
    private String idCode;
    private String cnum;
    private String docSerial;
    private String docNumber;
    private String state;
    private String status;
    @Positive
    private String maxRecord;
    private String type;
    @Positive
    private String brokerID;


}
