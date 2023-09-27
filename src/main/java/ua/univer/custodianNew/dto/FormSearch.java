package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormSearch {

    private String name;

    @Pattern(regexp = "^\\d{8}|\\d{10}$", message = "должно состоять из 8 или 10 цифр")
    private String idCode;

    private String docSerial;
    private String docNumber;

    private String account;

    /*private String state;
    private String status;
    private String type;*/

  /*  private String isin;
    private String depositary;
    private String dateState;*/

}
