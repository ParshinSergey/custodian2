//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.02 at 09:35:26 PM MSK 
//


package dmt.custodian2016;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Класс предназначен для установки/просмотра лимитов по транзакциям 
 * 
 * <p>Java class for Tlimit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tlimit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ISIN" type="{urn:dmt:custodian2016}TISIN"/&gt;
 *         &lt;element name="Account" type="{urn:dmt:custodian2016}TAccount_Num" minOccurs="0"/&gt;
 *         &lt;element name="MinBalance" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="Enable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tlimit", propOrder = {
    "isin",
    "account",
    "minBalance",
    "enable"
})
public class Tlimit {

    @XmlElement(name = "ISIN", required = true)
    protected TISIN isin;
    @XmlElement(name = "Account")
    protected String account;
    @XmlElement(name = "MinBalance")
    protected long minBalance;
    @XmlElement(name = "Enable")
    protected boolean enable;

    /**
     * Gets the value of the isin property.
     * 
     * @return
     *     possible object is
     *     {@link TISIN }
     *     
     */
    public TISIN getISIN() {
        return isin;
    }

    /**
     * Sets the value of the isin property.
     * 
     * @param value
     *     allowed object is
     *     {@link TISIN }
     *     
     */
    public void setISIN(TISIN value) {
        this.isin = value;
    }

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccount(String value) {
        this.account = value;
    }

    /**
     * Gets the value of the minBalance property.
     * 
     */
    public long getMinBalance() {
        return minBalance;
    }

    /**
     * Sets the value of the minBalance property.
     * 
     */
    public void setMinBalance(long value) {
        this.minBalance = value;
    }

    /**
     * Gets the value of the enable property.
     * 
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * Sets the value of the enable property.
     * 
     */
    public void setEnable(boolean value) {
        this.enable = value;
    }

}
