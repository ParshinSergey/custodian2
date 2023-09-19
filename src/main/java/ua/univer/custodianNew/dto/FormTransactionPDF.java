package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
public class FormTransactionPDF extends FormTransactionFile {

    @NotBlank
    private String orderId;

    public FormTransactionPDF() {
        this.setOutputFormat(0);
    }
}
