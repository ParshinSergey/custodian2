package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormBalance extends BaseForm{

    @NotBlank
    private String account;
    private String isin;
    private String depositary;
    private String dateState;

}
