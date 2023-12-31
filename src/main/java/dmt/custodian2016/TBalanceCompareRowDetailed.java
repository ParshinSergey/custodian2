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
 * <p>Java class for TBalance_compare_row_detailed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TBalance_compare_row_detailed"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:dmt:custodian2016}TBalance_compare_row_standart"&gt;
 *       &lt;sequence&gt;
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
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TBalance_compare_row_detailed", propOrder = {
    "balAccount"
})
public class TBalanceCompareRowDetailed
    extends TBalanceCompareRowStandart
{

    @XmlElement(name = "BalAccount", required = true)
    protected TBalanceCompareRowDetailed.BalAccount balAccount;

    /**
     * Gets the value of the balAccount property.
     * 
     * @return
     *     possible object is
     *     {@link TBalanceCompareRowDetailed.BalAccount }
     *     
     */
    public TBalanceCompareRowDetailed.BalAccount getBalAccount() {
        return balAccount;
    }

    /**
     * Sets the value of the balAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBalanceCompareRowDetailed.BalAccount }
     *     
     */
    public void setBalAccount(TBalanceCompareRowDetailed.BalAccount value) {
        this.balAccount = value;
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
        protected TBalanceCompareRowDetailed.BalAccount.BlockedFor blockedFor;

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
         *     {@link TBalanceCompareRowDetailed.BalAccount.BlockedFor }
         *     
         */
        public TBalanceCompareRowDetailed.BalAccount.BlockedFor getBlockedFor() {
            return blockedFor;
        }

        /**
         * Sets the value of the blockedFor property.
         * 
         * @param value
         *     allowed object is
         *     {@link TBalanceCompareRowDetailed.BalAccount.BlockedFor }
         *     
         */
        public void setBlockedFor(TBalanceCompareRowDetailed.BalAccount.BlockedFor value) {
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

}
