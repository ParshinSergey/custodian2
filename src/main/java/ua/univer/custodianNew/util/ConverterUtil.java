package ua.univer.custodianNew.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.univer.custodianNew.exceptions.DekraException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConverterUtil {

    private ConverterUtil() {
    }


    public static String objectToJson(Object obj){

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(df);
        String valueAsString;
        try {
            valueAsString = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new DekraException("Ошибка в методе objectToJson. " + e.getMessage());
        }
        return valueAsString;
    }


    public static <T> T jsonToObject(String json, Class<T> clas) {

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        objectMapper.setDateFormat(df);
        T t;
        try {
            t = objectMapper.readValue(json, clas);
        } catch (JsonProcessingException e) {
            throw new DekraException("Ошибка в методе jsonToObject. " + e.getMessage());
        }
        return t;
    }

}
