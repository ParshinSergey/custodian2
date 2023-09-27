package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormStatementFile extends FormStatement {

    @NotNull
    private Integer outputFormat;


}
