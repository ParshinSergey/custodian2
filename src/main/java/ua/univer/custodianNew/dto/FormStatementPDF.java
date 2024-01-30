package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormStatementPDF extends FormStatementFile {

    @NotBlank
    private String orderId;

    @NotBlank
    private String fieldName;

    public FormStatementPDF() {
        this.setOutputFormat(0);
    }
}
