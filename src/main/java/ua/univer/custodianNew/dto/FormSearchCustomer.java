package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormSearchCustomer extends BaseForm{

    private String name;

    @Pattern(regexp = "\\d{8}|\\d{10}", message = "должно состоять из 8 или 10 цифр")
    private String idCode;

    private String cnum;

    private String docSerial;
    private String docNumber;

    @Pattern(regexp = "AND|OR", message = "должно быть либо AND либо OR")
    private String boolOper;

}
