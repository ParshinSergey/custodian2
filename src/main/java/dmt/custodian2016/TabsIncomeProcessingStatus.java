//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2025.01.09 at 06:17:34 PM MSK 
//


package dmt.custodian2016;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TabsIncomeProcessingStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="TabsIncomeProcessingStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Received"/&gt;
 *     &lt;enumeration value="Error"/&gt;
 *     &lt;enumeration value="Rollback"/&gt;
 *     &lt;enumeration value="Processed"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TabsIncomeProcessingStatus")
@XmlEnum
public enum TabsIncomeProcessingStatus {


    /**
     * Документ отримано АБС
     * 
     */
    @XmlEnumValue("Received")
    RECEIVED("Received"),

    /**
     * Помилка при первинному опрацюванні документа АБС
     * 
     */
    @XmlEnumValue("Error")
    ERROR("Error"),

    /**
     * Відмова в опрацюванні документа в АБС
     * 
     */
    @XmlEnumValue("Rollback")
    ROLLBACK("Rollback"),

    /**
     * Документ опрацьованний АБС
     * 
     */
    @XmlEnumValue("Processed")
    PROCESSED("Processed");
    private final String value;

    TabsIncomeProcessingStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TabsIncomeProcessingStatus fromValue(String v) {
        for (TabsIncomeProcessingStatus c: TabsIncomeProcessingStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
