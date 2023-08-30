package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormReserveCancel {

    @NotBlank
    private String account;
}
