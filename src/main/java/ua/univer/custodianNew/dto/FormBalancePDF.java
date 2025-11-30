package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormBalancePDF extends FormBalance{

    @NotBlank
    private String orderId;

    @NotBlank
    private String fieldName;
}
