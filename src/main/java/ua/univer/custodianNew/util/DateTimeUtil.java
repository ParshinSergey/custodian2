package ua.univer.custodianNew.util;

import org.springframework.util.StringUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("[yyyy-M-d]" + "[yyyyMMdd]" + "[yyyy.M.d]");

    private DateTimeUtil() {
    }

    public static LocalDate parseLocalDate( String str) {
        return StringUtils.hasLength(str) ? LocalDate.parse(str, DATE_FORMATTER) : null;
    }

    public static XMLGregorianCalendar xmlGregorianCalendar(LocalDateTime dateTime){
        XMLGregorianCalendar date = null;
        try {
            date = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTime.toString());
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public static XMLGregorianCalendar xmlGregorianCalendar(String str){
        if (!StringUtils.hasLength(str)) return null;
        XMLGregorianCalendar date = null;
        try {
            date = DatatypeFactory.newInstance().newXMLGregorianCalendar(parseLocalDate(str).toString());
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public static XMLGregorianCalendar oneBoxCalendar(String str){
        if ("00010101".equals(str) || "19700101".equals(str)) return null;
        return xmlGregorianCalendar(str);
    }

    public static boolean period (String first, String second){
        XMLGregorianCalendar data1 = oneBoxCalendar(first);
        XMLGregorianCalendar data2 = oneBoxCalendar(second);
        int year = data2.getYear() - data1.getYear();
        int month = data2.getMonth() - data1.getMonth();
        return (year * 12 + month) < 13;
    }




}
