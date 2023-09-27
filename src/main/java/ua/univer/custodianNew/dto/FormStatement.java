package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormStatement {

    @NotBlank
    private String dateStart;
    @NotBlank
    private String dateStop;
    @NotBlank
    private String account;
    private String isin;
    private String depositary;

}
