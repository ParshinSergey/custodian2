//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2025.01.09 at 06:17:34 PM MSK 
//


package dmt.custodian2016;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;


/**
 * Тип данных для окрытия нового счета в ЦБ
 * 
 * <p>Java class for TupdateAccount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TupdateAccount"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Account" type="{urn:dmt:custodian2016}TAccount_Num" minOccurs="0"/&gt;
 *         &lt;element name="nssmcClientTypeCode" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:dmt:custodian2016&gt;Tnssmc_ClientType"&gt;
 *                 &lt;attribute name="changed" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;choice&gt;
 *           &lt;element name="CustomerID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *           &lt;element name="Customer" type="{urn:dmt:custodian2016}TupdateCustomer"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="agreements" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="agreement"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;extension base="{urn:dmt:custodian2016}TAgreement"&gt;
 *                           &lt;attribute name="changed" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ISI" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{urn:dmt:custodian2016}TISI"&gt;
 *                 &lt;attribute name="changed" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CreateOrder" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TupdateAccount", propOrder = {
    "account",
    "nssmcClientTypeCode",
    "customerID",
    "customer",
    "agreements",
    "isi",
    "createOrder"
})
public class TupdateAccount {

    @XmlElement(name = "Account")
    protected String account;
    protected TupdateAccount.NssmcClientTypeCode nssmcClientTypeCode;
    @XmlElement(name = "CustomerID")
    protected BigInteger customerID;
    @XmlElement(name = "Customer")
    protected TupdateCustomer customer;
    protected TupdateAccount.Agreements agreements;
    @XmlElement(name = "ISI")
    protected TupdateAccount.ISI isi;
    @XmlElement(name = "CreateOrder")
    protected BigInteger createOrder;

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
     * Gets the value of the nssmcClientTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link TupdateAccount.NssmcClientTypeCode }
     *     
     */
    public TupdateAccount.NssmcClientTypeCode getNssmcClientTypeCode() {
        return nssmcClientTypeCode;
    }

    /**
     * Sets the value of the nssmcClientTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link TupdateAccount.NssmcClientTypeCode }
     *     
     */
    public void setNssmcClientTypeCode(TupdateAccount.NssmcClientTypeCode value) {
        this.nssmcClientTypeCode = value;
    }

    /**
     * Gets the value of the customerID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCustomerID() {
        return customerID;
    }

    /**
     * Sets the value of the customerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCustomerID(BigInteger value) {
        this.customerID = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link TupdateCustomer }
     *     
     */
    public TupdateCustomer getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link TupdateCustomer }
     *     
     */
    public void setCustomer(TupdateCustomer value) {
        this.customer = value;
    }

    /**
     * Gets the value of the agreements property.
     * 
     * @return
     *     possible object is
     *     {@link TupdateAccount.Agreements }
     *     
     */
    public TupdateAccount.Agreements getAgreements() {
        return agreements;
    }

    /**
     * Sets the value of the agreements property.
     * 
     * @param value
     *     allowed object is
     *     {@link TupdateAccount.Agreements }
     *     
     */
    public void setAgreements(TupdateAccount.Agreements value) {
        this.agreements = value;
    }

    /**
     * Gets the value of the isi property.
     * 
     * @return
     *     possible object is
     *     {@link TupdateAccount.ISI }
     *     
     */
    public TupdateAccount.ISI getISI() {
        return isi;
    }

    /**
     * Sets the value of the isi property.
     * 
     * @param value
     *     allowed object is
     *     {@link TupdateAccount.ISI }
     *     
     */
    public void setISI(TupdateAccount.ISI value) {
        this.isi = value;
    }

    /**
     * Gets the value of the createOrder property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCreateOrder() {
        return createOrder;
    }

    /**
     * Sets the value of the createOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCreateOrder(BigInteger value) {
        this.createOrder = value;
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
     *       &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
     *         &lt;element name="agreement"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;extension base="{urn:dmt:custodian2016}TAgreement"&gt;
     *                 &lt;attribute name="changed" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
     *               &lt;/extension&gt;
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
        "agreement"
    })
    public static class Agreements {

        protected List<TupdateAccount.Agreements.Agreement> agreement;

        /**
         * Gets the value of the agreement property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the agreement property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAgreement().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TupdateAccount.Agreements.Agreement }
         * 
         * 
         */
        public List<TupdateAccount.Agreements.Agreement> getAgreement() {
            if (agreement == null) {
                agreement = new ArrayList<TupdateAccount.Agreements.Agreement>();
            }
            return this.agreement;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;extension base="{urn:dmt:custodian2016}TAgreement"&gt;
         *       &lt;attribute name="changed" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
         *     &lt;/extension&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Agreement
            extends TAgreement
        {

            @XmlAttribute(name = "changed")
            protected Boolean changed;

            /**
             * Gets the value of the changed property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isChanged() {
                return changed;
            }

            /**
             * Sets the value of the changed property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setChanged(Boolean value) {
                this.changed = value;
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
     *     &lt;extension base="{urn:dmt:custodian2016}TISI"&gt;
     *       &lt;attribute name="changed" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ISI
        extends TISI
    {

        @XmlAttribute(name = "changed")
        protected Boolean changed;

        /**
         * Gets the value of the changed property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isChanged() {
            return changed;
        }

        /**
         * Sets the value of the changed property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setChanged(Boolean value) {
            this.changed = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;urn:dmt:custodian2016&gt;Tnssmc_ClientType"&gt;
     *       &lt;attribute name="changed" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class NssmcClientTypeCode {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "changed")
        protected Boolean changed;

        /**
         *  37. Довідник «Види депонентів, клієнтів депозитарної установи, клієнтів торговця цінними паперами»
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the changed property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isChanged() {
            return changed;
        }

        /**
         * Sets the value of the changed property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setChanged(Boolean value) {
            this.changed = value;
        }

    }

}
