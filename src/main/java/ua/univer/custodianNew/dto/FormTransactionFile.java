package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormTransactionFile {

    @NotBlank
    private String dateStart;
    @NotBlank
    private String dateStop;
    @NotBlank
    private String account;
    private String isin;
    private String depositary;
    @NotNull
    private Integer outputFormat;


}
