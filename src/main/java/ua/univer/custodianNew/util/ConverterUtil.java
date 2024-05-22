package ua.univer.custodianNew.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConverterUtil {

    private ConverterUtil() {
    }


    public static String objectToJson(Object obj) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(df);

        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T jsonToObject(String json, Class<T> clas) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        objectMapper.setDateFormat(df);

        return objectMapper.readValue(json, clas);
    }

}
