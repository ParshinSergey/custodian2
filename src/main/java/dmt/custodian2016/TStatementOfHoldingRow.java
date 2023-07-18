//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.02 at 09:35:26 PM MSK 
//


package dmt.custodian2016;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Строка выписки про состояние счета
 * 
 * <p>Java class for TStatement_of_Holding_row complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TStatement_of_Holding_row"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ISIN" type="{urn:dmt:custodian2016}TISIN"/&gt;
 *         &lt;element name="Issuer"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ShortName" type="{urn:dmt:custodian2016}TShortName"/&gt;
 *                   &lt;element name="IDCode" type="{urn:dmt:custodian2016}TIDCode"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ISINState"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="Name"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="100"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="BalAccount"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Code"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;length value="6"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Name"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="256"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="BlockedFor" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="ShortName" type="{urn:dmt:custodian2016}TShortName"/&gt;
 *                             &lt;element name="IDCode" type="{urn:dmt:custodian2016}TIDCode"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="faceAmount" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="Percent" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/&gt;
 *         &lt;element name="Currency" type="{urn:dmt:custodian2016}TCurrency"/&gt;
 *         &lt;element name="AgregateAccountID" type="{urn:dmt:custodian2016}TAgregateAccountID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TStatement_of_Holding_row", propOrder = {
    "isin",
    "issuer",
    "isinState",
    "balAccount",
    "quantity",
    "faceAmount",
    "percent",
    "currency",
    "agregateAccountID"
})
public class TStatementOfHoldingRow {

    @XmlElement(name = "ISIN", required = true)
    protected TISIN isin;
    @XmlElement(name = "Issuer", required = true)
    protected TStatementOfHoldingRow.Issuer issuer;
    @XmlElement(name = "ISINState", required = true)
    protected TStatementOfHoldingRow.ISINState isinState;
    @XmlElement(name = "BalAccount", required = true)
    protected TStatementOfHoldingRow.BalAccount balAccount;
    @XmlElement(name = "Quantity")
    protected long quantity;
    protected float faceAmount;
    @XmlElement(name = "Percent")
    protected Float percent;
    @XmlElement(name = "Currency", required = true)
    protected String currency;
    @XmlElement(name = "AgregateAccountID")
    protected String agregateAccountID;

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
     * Gets the value of the issuer property.
     * 
     * @return
     *     possible object is
     *     {@link TStatementOfHoldingRow.Issuer }
     *     
     */
    public TStatementOfHoldingRow.Issuer getIssuer() {
        return issuer;
    }

    /**
     * Sets the value of the issuer property.
     * 
     * @param value
     *     allowed object is
     *     {@link TStatementOfHoldingRow.Issuer }
     *     
     */
    public void setIssuer(TStatementOfHoldingRow.Issuer value) {
        this.issuer = value;
    }

    /**
     * Gets the value of the isinState property.
     * 
     * @return
     *     possible object is
     *     {@link TStatementOfHoldingRow.ISINState }
     *     
     */
    public TStatementOfHoldingRow.ISINState getISINState() {
        return isinState;
    }

    /**
     * Sets the value of the isinState property.
     * 
     * @param value
     *     allowed object is
     *     {@link TStatementOfHoldingRow.ISINState }
     *     
     */
    public void setISINState(TStatementOfHoldingRow.ISINState value) {
        this.isinState = value;
    }

    /**
     * Gets the value of the balAccount property.
     * 
     * @return
     *     possible object is
     *     {@link TStatementOfHoldingRow.BalAccount }
     *     
     */
    public TStatementOfHoldingRow.BalAccount getBalAccount() {
        return balAccount;
    }

    /**
     * Sets the value of the balAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TStatementOfHoldingRow.BalAccount }
     *     
     */
    public void setBalAccount(TStatementOfHoldingRow.BalAccount value) {
        this.balAccount = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(long value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the faceAmount property.
     * 
     */
    public float getFaceAmount() {
        return faceAmount;
    }

    /**
     * Sets the value of the faceAmount property.
     * 
     */
    public void setFaceAmount(float value) {
        this.faceAmount = value;
    }

    /**
     * Gets the value of the percent property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getPercent() {
        return percent;
    }

    /**
     * Sets the value of the percent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setPercent(Float value) {
        this.percent = value;
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
     * Gets the value of the agregateAccountID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgregateAccountID() {
        return agregateAccountID;
    }

    /**
     * Sets the value of the agregateAccountID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgregateAccountID(String value) {
        this.agregateAccountID = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Code"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;length value="6"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Name"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="256"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="BlockedFor" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="ShortName" type="{urn:dmt:custodian2016}TShortName"/&gt;
     *                   &lt;element name="IDCode" type="{urn:dmt:custodian2016}TIDCode"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "code",
        "name",
        "blockedFor"
    })
    public static class BalAccount {

        @XmlElement(name = "Code", required = true)
        protected String code;
        @XmlElement(name = "Name", required = true)
        protected String name;
        @XmlElement(name = "BlockedFor")
        protected TStatementOfHoldingRow.BalAccount.BlockedFor blockedFor;

        /**
         * Gets the value of the code property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the blockedFor property.
         * 
         * @return
         *     possible object is
         *     {@link TStatementOfHoldingRow.BalAccount.BlockedFor }
         *     
         */
        public TStatementOfHoldingRow.BalAccount.BlockedFor getBlockedFor() {
            return blockedFor;
        }

        /**
         * Sets the value of the blockedFor property.
         * 
         * @param value
         *     allowed object is
         *     {@link TStatementOfHoldingRow.BalAccount.BlockedFor }
         *     
         */
        public void setBlockedFor(TStatementOfHoldingRow.BalAccount.BlockedFor value) {
            this.blockedFor = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="ShortName" type="{urn:dmt:custodian2016}TShortName"/&gt;
         *         &lt;element name="IDCode" type="{urn:dmt:custodian2016}TIDCode"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "shortName",
            "idCode"
        })
        public static class BlockedFor {

            @XmlElement(name = "ShortName", required = true)
            protected String shortName;
            @XmlElement(name = "IDCode", required = true)
            protected String idCode;

            /**
             * Gets the value of the shortName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShortName() {
                return shortName;
            }

            /**
             * Sets the value of the shortName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShortName(String value) {
                this.shortName = value;
            }

            /**
             * Gets the value of the idCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIDCode() {
                return idCode;
            }

            /**
             * Sets the value of the idCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIDCode(String value) {
                this.idCode = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="Name"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;maxLength value="100"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "code",
        "name"
    })
    public static class ISINState {

        @XmlElement(name = "Code", required = true)
        protected BigInteger code;
        @XmlElement(name = "Name", required = true)
        protected String name;

        /**
         * Gets the value of the code property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setCode(BigInteger value) {
            this.code = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="ShortName" type="{urn:dmt:custodian2016}TShortName"/&gt;
     *         &lt;element name="IDCode" type="{urn:dmt:custodian2016}TIDCode"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "shortName",
        "idCode"
    })
    public static class Issuer {

        @XmlElement(name = "ShortName", required = true)
        protected String shortName;
        @XmlElement(name = "IDCode", required = true)
        protected String idCode;

        /**
         * Gets the value of the shortName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShortName() {
            return shortName;
        }

        /**
         * Sets the value of the shortName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShortName(String value) {
            this.shortName = value;
        }

        /**
         * Gets the value of the idCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIDCode() {
            return idCode;
        }

        /**
         * Sets the value of the idCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIDCode(String value) {
            this.idCode = value;
        }

    }

}