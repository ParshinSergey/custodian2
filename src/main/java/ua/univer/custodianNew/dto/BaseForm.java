package ua.univer.custodianNew.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseForm {

    private static final String DEKRA_URL_PROD = "https://10.1.2.204/API_BP/cp_api.dll";
    private static final String DEKRA_URL_80 = "https://10.1.2.80/API_BP/cp_api.dll";
    static final String KEYBOARD_SYMBOLS = "^[\\u0020-\\u007EА-Яа-яёЁЇїІіЄєҐґ№\\s]+$|^$";

    // поле для работы Тестовых Методов
    @Hidden
    private boolean test;

    public String ipAddress(){
        return this.isTest()? DEKRA_URL_80 : DEKRA_URL_PROD;
    }

}
