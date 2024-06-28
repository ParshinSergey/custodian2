package ua.univer.custodianNew.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FormAccount extends BaseForm{

    @NotBlank
    private String account;

    public FormAccount(String account, boolean isTest) {
        this.account = account;
        this.setTest(isTest);
    }
}
