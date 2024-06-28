package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormGet extends BaseForm {

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


}
