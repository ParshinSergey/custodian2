//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.02 at 09:35:26 PM MSK 
//


package dmt.custodian2016;

import java.math.BigDecimal;
import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Финансовый инструмент
 * 
 * <p>Java class for TFI complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TFI"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ISIN" type="{urn:dmt:custodian2016}TISIN"/&gt;
 *         &lt;element name="Country" type="{urn:dmt:custodian2016}TCountry"/&gt;
 *         &lt;element name="IssuerID" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="IssuerName" type="{urn:dmt:custodian2016}TName"/&gt;
 *         &lt;element name="IssuerIDCode" type="{urn:dmt:custodian2016}TIDCode"/&gt;
 *         &lt;element name="Form" type="{urn:dmt:custodian2016}TKOPFG" minOccurs="0"/&gt;
 *         &lt;element name="IssuerRegNumber" type="{urn:dmt:custodian2016}TAgreementBase" minOccurs="0"/&gt;
 *         &lt;element name="Issuer_legal_address" type="{urn:dmt:custodian2016}Taddress_base" minOccurs="0"/&gt;
 *         &lt;element name="Issuer_post_address" type="{urn:dmt:custodian2016}Taddress_base" minOccurs="0"/&gt;
 *         &lt;element name="Issuer_form" type="{urn:dmt:custodian2016}TKOPFG"/&gt;
 *         &lt;element name="Issuer_fund" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/&gt;
 *         &lt;element name="Issuer_currency" type="{urn:dmt:custodian2016}TCurrency" minOccurs="0"/&gt;
 *         &lt;element name="LEI" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="State"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *               &lt;enumeration value="3"/&gt;
 *               &lt;enumeration value="4"/&gt;
 *               &lt;enumeration value="5"/&gt;
 *               &lt;enumeration value="6"/&gt;
 *               &lt;enumeration value="7"/&gt;
 *               &lt;enumeration value="8"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Serial" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="9"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Type"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="8"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CFI"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="6"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="Nominal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="Currency" type="{urn:dmt:custodian2016}TCurrency" minOccurs="0"/&gt;
 *         &lt;element name="RegNumber" type="{urn:dmt:custodian2016}TAgreementBase" minOccurs="0"/&gt;
 *         &lt;element name="isTGS" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="cuponList" type="{urn:dmt:custodian2016}TFIcuponList" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TFI", propOrder = {
    "isin",
    "country",
    "issuerID",
    "issuerName",
    "issuerIDCode",
    "form",
    "issuerRegNumber",
    "issuerLegalAddress",
    "issuerPostAddress",
    "issuerForm",
    "issuerFund",
    "issuerCurrency",
    "lei",
    "state",
    "serial",
    "type",
    "cfi",
    "quantity",
    "nominal",
    "currency",
    "regNumber",
    "isTGS",
    "cuponList"
})
public class TFI {

    @XmlElement(name = "ISIN", required = true)
    protected TISIN isin;
    @XmlElement(name = "Country", required = true)
    protected String country;
    @XmlElement(name = "IssuerID")
    protected BigInteger issuerID;
    @XmlElement(name = "IssuerName", required = true)
    protected TName issuerName;
    @XmlElement(name = "IssuerIDCode", required = true)
    protected String issuerIDCode;
    @XmlElement(name = "Form")
    protected String form;
    @XmlElement(name = "IssuerRegNumber")
    protected TAgreementBase issuerRegNumber;
    @XmlElement(name = "Issuer_legal_address")
    protected TaddressBase issuerLegalAddress;
    @XmlElement(name = "Issuer_post_address")
    protected TaddressBase issuerPostAddress;
    @XmlElement(name = "Issuer_form", required = true)
    protected String issuerForm;
    @XmlElement(name = "Issuer_fund")
    protected Float issuerFund;
    @XmlElement(name = "Issuer_currency")
    protected String issuerCurrency;
    @XmlElement(name = "LEI")
    protected String lei;
    @XmlElement(name = "State", required = true)
    protected BigInteger state;
    @XmlElement(name = "Serial")
    protected String serial;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "CFI", required = true)
    protected String cfi;
    @XmlElement(name = "Quantity")
    protected Long quantity;
    @XmlElement(name = "Nominal")
    protected BigDecimal nominal;
    @XmlElement(name = "Currency")
    protected String currency;
    @XmlElement(name = "RegNumber")
    protected TAgreementBase regNumber;
    protected boolean isTGS;
    protected TFIcuponList cuponList;

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
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the issuerID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIssuerID() {
        return issuerID;
    }

    /**
     * Sets the value of the issuerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIssuerID(BigInteger value) {
        this.issuerID = value;
    }

    /**
     * Gets the value of the issuerName property.
     * 
     * @return
     *     possible object is
     *     {@link TName }
     *     
     */
    public TName getIssuerName() {
        return issuerName;
    }

    /**
     * Sets the value of the issuerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link TName }
     *     
     */
    public void setIssuerName(TName value) {
        this.issuerName = value;
    }

    /**
     * Gets the value of the issuerIDCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuerIDCode() {
        return issuerIDCode;
    }

    /**
     * Sets the value of the issuerIDCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuerIDCode(String value) {
        this.issuerIDCode = value;
    }

    /**
     * Gets the value of the form property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForm() {
        return form;
    }

    /**
     * Sets the value of the form property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForm(String value) {
        this.form = value;
    }

    /**
     * Gets the value of the issuerRegNumber property.
     * 
     * @return
     *     possible object is
     *     {@link TAgreementBase }
     *     
     */
    public TAgreementBase getIssuerRegNumber() {
        return issuerRegNumber;
    }

    /**
     * Sets the value of the issuerRegNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAgreementBase }
     *     
     */
    public void setIssuerRegNumber(TAgreementBase value) {
        this.issuerRegNumber = value;
    }

    /**
     * Gets the value of the issuerLegalAddress property.
     * 
     * @return
     *     possible object is
     *     {@link TaddressBase }
     *     
     */
    public TaddressBase getIssuerLegalAddress() {
        return issuerLegalAddress;
    }

    /**
     * Sets the value of the issuerLegalAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaddressBase }
     *     
     */
    public void setIssuerLegalAddress(TaddressBase value) {
        this.issuerLegalAddress = value;
    }

    /**
     * Gets the value of the issuerPostAddress property.
     * 
     * @return
     *     possible object is
     *     {@link TaddressBase }
     *     
     */
    public TaddressBase getIssuerPostAddress() {
        return issuerPostAddress;
    }

    /**
     * Sets the value of the issuerPostAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaddressBase }
     *     
     */
    public void setIssuerPostAddress(TaddressBase value) {
        this.issuerPostAddress = value;
    }

    /**
     * Gets the value of the issuerForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuerForm() {
        return issuerForm;
    }

    /**
     * Sets the value of the issuerForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuerForm(String value) {
        this.issuerForm = value;
    }

    /**
     * Gets the value of the issuerFund property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getIssuerFund() {
        return issuerFund;
    }

    /**
     * Sets the value of the issuerFund property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setIssuerFund(Float value) {
        this.issuerFund = value;
    }

    /**
     * Gets the value of the issuerCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuerCurrency() {
        return issuerCurrency;
    }

    /**
     * Sets the value of the issuerCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuerCurrency(String value) {
        this.issuerCurrency = value;
    }

    /**
     * Gets the value of the lei property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLEI() {
        return lei;
    }

    /**
     * Sets the value of the lei property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLEI(String value) {
        this.lei = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setState(BigInteger value) {
        this.state = value;
    }

    /**
     * Gets the value of the serial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerial() {
        return serial;
    }

    /**
     * Sets the value of the serial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerial(String value) {
        this.serial = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the cfi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCFI() {
        return cfi;
    }

    /**
     * Sets the value of the cfi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCFI(String value) {
        this.cfi = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setQuantity(Long value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the nominal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNominal() {
        return nominal;
    }

    /**
     * Sets the value of the nominal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNominal(BigDecimal value) {
        this.nominal = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the regNumber property.
     * 
     * @return
     *     possible object is
     *     {@link TAgreementBase }
     *     
     */
    public TAgreementBase getRegNumber() {
        return regNumber;
    }

    /**
     * Sets the value of the regNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAgreementBase }
     *     
     */
    public void setRegNumber(TAgreementBase value) {
        this.regNumber = value;
    }

    /**
     * Gets the value of the isTGS property.
     * 
     */
    public boolean isIsTGS() {
        return isTGS;
    }

    /**
     * Sets the value of the isTGS property.
     * 
     */
    public void setIsTGS(boolean value) {
        this.isTGS = value;
    }

    /**
     * Gets the value of the cuponList property.
     * 
     * @return
     *     possible object is
     *     {@link TFIcuponList }
     *     
     */
    public TFIcuponList getCuponList() {
        return cuponList;
    }

    /**
     * Sets the value of the cuponList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFIcuponList }
     *     
     */
    public void setCuponList(TFIcuponList value) {
        this.cuponList = value;
    }

}