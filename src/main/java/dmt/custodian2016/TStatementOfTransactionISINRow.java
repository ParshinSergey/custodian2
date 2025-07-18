//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2025.06.30 at 03:15:09 PM MSK 
//


package dmt.custodian2016;

import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Запись операции в выписке по операциям
 * 
 * <p>Java class for TStatement_of_Transaction_ISIN_row complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TStatement_of_Transaction_ISIN_row"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OperID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="OrderID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="OrderIDs" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence minOccurs="0"&gt;
 *                   &lt;element name="OrderID_Source" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *                   &lt;element name="OrderID_Destination" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="dateOper" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="BalAccount_Debit" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{urn:dmt:custodian2016}TBalAcc"&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AgregateAccountID_Debit" type="{urn:dmt:custodian2016}TAgregateAccountID" minOccurs="0"/&gt;
 *         &lt;element name="Quantity_Debit" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="BalAccount_Credit" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{urn:dmt:custodian2016}TBalAcc"&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AgregateAccountID_Credit" type="{urn:dmt:custodian2016}TAgregateAccountID" minOccurs="0"/&gt;
 *         &lt;element name="Quantity_Credit" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="counterparty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TransactionAdditional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Agreement" type="{urn:dmt:custodian2016}TAgreementBase" minOccurs="0"/&gt;
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="orderTypeCode" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="26"/&gt;
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
@XmlType(name = "TStatement_of_Transaction_ISIN_row", propOrder = {
    "operID",
    "orderID",
    "orderIDs",
    "dateOper",
    "balAccountDebit",
    "agregateAccountIDDebit",
    "quantityDebit",
    "balAccountCredit",
    "agregateAccountIDCredit",
    "quantityCredit",
    "counterparty",
    "transactionAdditional",
    "agreement",
    "quantity",
    "orderTypeCode"
})
public class TStatementOfTransactionISINRow {

    @XmlElement(name = "OperID", required = true)
    protected BigInteger operID;
    @XmlElement(name = "OrderID", required = true)
    protected BigInteger orderID;
    @XmlElement(name = "OrderIDs")
    protected TStatementOfTransactionISINRow.OrderIDs orderIDs;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateOper;
    @XmlElement(name = "BalAccount_Debit")
    protected TStatementOfTransactionISINRow.BalAccountDebit balAccountDebit;
    @XmlElement(name = "AgregateAccountID_Debit")
    protected String agregateAccountIDDebit;
    @XmlElement(name = "Quantity_Debit")
    protected Long quantityDebit;
    @XmlElement(name = "BalAccount_Credit")
    protected TStatementOfTransactionISINRow.BalAccountCredit balAccountCredit;
    @XmlElement(name = "AgregateAccountID_Credit")
    protected String agregateAccountIDCredit;
    @XmlElement(name = "Quantity_Credit")
    protected Long quantityCredit;
    protected String counterparty;
    @XmlElement(name = "TransactionAdditional")
    protected String transactionAdditional;
    @XmlElement(name = "Agreement")
    protected TAgreementBase agreement;
    @XmlElement(name = "Quantity")
    protected long quantity;
    protected String orderTypeCode;

    /**
     * Gets the value of the operID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOperID() {
        return operID;
    }

    /**
     * Sets the value of the operID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOperID(BigInteger value) {
        this.operID = value;
    }

    /**
     * Gets the value of the orderID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrderID() {
        return orderID;
    }

    /**
     * Sets the value of the orderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrderID(BigInteger value) {
        this.orderID = value;
    }

    /**
     * Gets the value of the orderIDs property.
     * 
     * @return
     *     possible object is
     *     {@link TStatementOfTransactionISINRow.OrderIDs }
     *     
     */
    public TStatementOfTransactionISINRow.OrderIDs getOrderIDs() {
        return orderIDs;
    }

    /**
     * Sets the value of the orderIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link TStatementOfTransactionISINRow.OrderIDs }
     *     
     */
    public void setOrderIDs(TStatementOfTransactionISINRow.OrderIDs value) {
        this.orderIDs = value;
    }

    /**
     * Gets the value of the dateOper property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOper() {
        return dateOper;
    }

    /**
     * Sets the value of the dateOper property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOper(XMLGregorianCalendar value) {
        this.dateOper = value;
    }

    /**
     * Gets the value of the balAccountDebit property.
     * 
     * @return
     *     possible object is
     *     {@link TStatementOfTransactionISINRow.BalAccountDebit }
     *     
     */
    public TStatementOfTransactionISINRow.BalAccountDebit getBalAccountDebit() {
        return balAccountDebit;
    }

    /**
     * Sets the value of the balAccountDebit property.
     * 
     * @param value
     *     allowed object is
     *     {@link TStatementOfTransactionISINRow.BalAccountDebit }
     *     
     */
    public void setBalAccountDebit(TStatementOfTransactionISINRow.BalAccountDebit value) {
        this.balAccountDebit = value;
    }

    /**
     * Gets the value of the agregateAccountIDDebit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgregateAccountIDDebit() {
        return agregateAccountIDDebit;
    }

    /**
     * Sets the value of the agregateAccountIDDebit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgregateAccountIDDebit(String value) {
        this.agregateAccountIDDebit = value;
    }

    /**
     * Gets the value of the quantityDebit property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getQuantityDebit() {
        return quantityDebit;
    }

    /**
     * Sets the value of the quantityDebit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setQuantityDebit(Long value) {
        this.quantityDebit = value;
    }

    /**
     * Gets the value of the balAccountCredit property.
     * 
     * @return
     *     possible object is
     *     {@link TStatementOfTransactionISINRow.BalAccountCredit }
     *     
     */
    public TStatementOfTransactionISINRow.BalAccountCredit getBalAccountCredit() {
        return balAccountCredit;
    }

    /**
     * Sets the value of the balAccountCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link TStatementOfTransactionISINRow.BalAccountCredit }
     *     
     */
    public void setBalAccountCredit(TStatementOfTransactionISINRow.BalAccountCredit value) {
        this.balAccountCredit = value;
    }

    /**
     * Gets the value of the agregateAccountIDCredit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgregateAccountIDCredit() {
        return agregateAccountIDCredit;
    }

    /**
     * Sets the value of the agregateAccountIDCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgregateAccountIDCredit(String value) {
        this.agregateAccountIDCredit = value;
    }

    /**
     * Gets the value of the quantityCredit property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getQuantityCredit() {
        return quantityCredit;
    }

    /**
     * Sets the value of the quantityCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setQuantityCredit(Long value) {
        this.quantityCredit = value;
    }

    /**
     * Gets the value of the counterparty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounterparty() {
        return counterparty;
    }

    /**
     * Sets the value of the counterparty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounterparty(String value) {
        this.counterparty = value;
    }

    /**
     * Gets the value of the transactionAdditional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionAdditional() {
        return transactionAdditional;
    }

    /**
     * Sets the value of the transactionAdditional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionAdditional(String value) {
        this.transactionAdditional = value;
    }

    /**
     * Gets the value of the agreement property.
     * 
     * @return
     *     possible object is
     *     {@link TAgreementBase }
     *     
     */
    public TAgreementBase getAgreement() {
        return agreement;
    }

    /**
     * Sets the value of the agreement property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAgreementBase }
     *     
     */
    public void setAgreement(TAgreementBase value) {
        this.agreement = value;
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
     * Gets the value of the orderTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderTypeCode() {
        return orderTypeCode;
    }

    /**
     * Sets the value of the orderTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderTypeCode(String value) {
        this.orderTypeCode = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;extension base="{urn:dmt:custodian2016}TBalAcc"&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class BalAccountCredit
        extends TBalAcc
    {


    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;extension base="{urn:dmt:custodian2016}TBalAcc"&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class BalAccountDebit
        extends TBalAcc
    {


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
     *       &lt;sequence minOccurs="0"&gt;
     *         &lt;element name="OrderID_Source" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
     *         &lt;element name="OrderID_Destination" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
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
        "orderIDSource",
        "orderIDDestination"
    })
    public static class OrderIDs {

        @XmlElement(name = "OrderID_Source")
        protected Integer orderIDSource;
        @XmlElement(name = "OrderID_Destination")
        protected Integer orderIDDestination;

        /**
         * Gets the value of the orderIDSource property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getOrderIDSource() {
            return orderIDSource;
        }

        /**
         * Sets the value of the orderIDSource property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setOrderIDSource(Integer value) {
            this.orderIDSource = value;
        }

        /**
         * Gets the value of the orderIDDestination property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getOrderIDDestination() {
            return orderIDDestination;
        }

        /**
         * Sets the value of the orderIDDestination property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setOrderIDDestination(Integer value) {
            this.orderIDDestination = value;
        }

    }

}
