//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2025.01.09 at 06:17:34 PM MSK 
//


package dmt.custodian2016;

import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Обратите внимание удалять банковские ревизиты нельзя. Для отключения, устанавливайте дату DateStop (//element(*,cust:TBankDetail)/cust:Period/cust:DateStop)
 * 
 * <p>Java class for TBankDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TBankDetail"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:dmt:custodian2016}TBankDetailBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bankDetailID" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="use4income" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Period" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DateStart" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                   &lt;element name="DateStop" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AgregateAccountID" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{urn:dmt:custodian2016}TAgregateAccountID"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Type" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;enumeration value="-1"/&gt;
 *               &lt;enumeration value="0"/&gt;
 *               &lt;enumeration value="1"/&gt;
 *               &lt;enumeration value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="changed" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TBankDetail", propOrder = {
    "bankDetailID",
    "use4Income",
    "period",
    "agregateAccountID",
    "type"
})
public class TBankDetail
    extends TBankDetailBase
{

    protected BigInteger bankDetailID;
    @XmlElement(name = "use4income")
    protected boolean use4Income;
    @XmlElement(name = "Period")
    protected TBankDetail.Period period;
    @XmlElement(name = "AgregateAccountID")
    protected String agregateAccountID;
    @XmlElement(name = "Type")
    protected BigInteger type;
    @XmlAttribute(name = "changed")
    protected Boolean changed;

    /**
     * Gets the value of the bankDetailID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBankDetailID() {
        return bankDetailID;
    }

    /**
     * Sets the value of the bankDetailID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBankDetailID(BigInteger value) {
        this.bankDetailID = value;
    }

    /**
     * Gets the value of the use4Income property.
     * 
     */
    public boolean isUse4Income() {
        return use4Income;
    }

    /**
     * Sets the value of the use4Income property.
     * 
     */
    public void setUse4Income(boolean value) {
        this.use4Income = value;
    }

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link TBankDetail.Period }
     *     
     */
    public TBankDetail.Period getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBankDetail.Period }
     *     
     */
    public void setPeriod(TBankDetail.Period value) {
        this.period = value;
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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setType(BigInteger value) {
        this.type = value;
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
     *         &lt;element name="DateStart" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *         &lt;element name="DateStop" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
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
        "dateStart",
        "dateStop"
    })
    public static class Period {

        @XmlElement(name = "DateStart", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dateStart;
        @XmlElement(name = "DateStop")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dateStop;

        /**
         * Gets the value of the dateStart property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDateStart() {
            return dateStart;
        }

        /**
         * Sets the value of the dateStart property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDateStart(XMLGregorianCalendar value) {
            this.dateStart = value;
        }

        /**
         * Gets the value of the dateStop property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDateStop() {
            return dateStop;
        }

        /**
         * Sets the value of the dateStop property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDateStop(XMLGregorianCalendar value) {
            this.dateStop = value;
        }

    }

}
