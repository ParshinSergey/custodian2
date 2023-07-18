//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.02 at 09:35:26 PM MSK 
//


package dmt.custodian2016;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Тип данных заголовка ответа
 * 
 * <p>Java class for THeaderResponce complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="THeaderResponce"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ResponceType" type="{urn:dmt:custodian2016}TPacketType"/&gt;
 *         &lt;element name="ResponceID" type="{urn:dmt:custodian2016}TGUID"/&gt;
 *         &lt;element name="TimeStamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="RequestID" type="{urn:dmt:custodian2016}TGUID"/&gt;
 *         &lt;element name="EndUserID" type="{urn:dmt:custodian2016}TGUID" minOccurs="0"/&gt;
 *         &lt;element name="binary" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="binary" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *                   &lt;element name="Template" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *                   &lt;element name="outputFormat" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
 *                         &lt;enumeration value="0"/&gt;
 *                         &lt;enumeration value="1"/&gt;
 *                         &lt;enumeration value="2"/&gt;
 *                         &lt;enumeration value="3"/&gt;
 *                         &lt;enumeration value="4"/&gt;
 *                         &lt;enumeration value="5"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ExternalID" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="36"/&gt;
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
@XmlType(name = "THeaderResponce", propOrder = {
    "responceType",
    "responceID",
    "timeStamp",
    "requestID",
    "endUserID",
    "binary",
    "externalID"
})
public class THeaderResponce {

    @XmlElement(name = "ResponceType", required = true)
    protected String responceType;
    @XmlElement(name = "ResponceID", required = true)
    protected String responceID;
    @XmlElement(name = "TimeStamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeStamp;
    @XmlElement(name = "RequestID", required = true)
    protected String requestID;
    @XmlElement(name = "EndUserID")
    protected String endUserID;
    protected THeaderResponce.Binary binary;
    @XmlElement(name = "ExternalID")
    protected String externalID;

    /**
     * Gets the value of the responceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponceType() {
        return responceType;
    }

    /**
     * Sets the value of the responceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponceType(String value) {
        this.responceType = value;
    }

    /**
     * Gets the value of the responceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponceID() {
        return responceID;
    }

    /**
     * Sets the value of the responceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponceID(String value) {
        this.responceID = value;
    }

    /**
     * Gets the value of the timeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeStamp(XMLGregorianCalendar value) {
        this.timeStamp = value;
    }

    /**
     * Gets the value of the requestID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * Sets the value of the requestID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestID(String value) {
        this.requestID = value;
    }

    /**
     * Gets the value of the endUserID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndUserID() {
        return endUserID;
    }

    /**
     * Sets the value of the endUserID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndUserID(String value) {
        this.endUserID = value;
    }

    /**
     * Gets the value of the binary property.
     * 
     * @return
     *     possible object is
     *     {@link THeaderResponce.Binary }
     *     
     */
    public THeaderResponce.Binary getBinary() {
        return binary;
    }

    /**
     * Sets the value of the binary property.
     * 
     * @param value
     *     allowed object is
     *     {@link THeaderResponce.Binary }
     *     
     */
    public void setBinary(THeaderResponce.Binary value) {
        this.binary = value;
    }

    /**
     * Gets the value of the externalID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalID() {
        return externalID;
    }

    /**
     * Sets the value of the externalID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalID(String value) {
        this.externalID = value;
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
     *         &lt;element name="binary" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
     *         &lt;element name="Template" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
     *         &lt;element name="outputFormat" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int"&gt;
     *               &lt;enumeration value="0"/&gt;
     *               &lt;enumeration value="1"/&gt;
     *               &lt;enumeration value="2"/&gt;
     *               &lt;enumeration value="3"/&gt;
     *               &lt;enumeration value="4"/&gt;
     *               &lt;enumeration value="5"/&gt;
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
        "binary",
        "template",
        "outputFormat"
    })
    public static class Binary {

        protected boolean binary;
        @XmlElement(name = "Template")
        protected Integer template;
        protected Integer outputFormat;

        /**
         * Gets the value of the binary property.
         * 
         */
        public boolean isBinary() {
            return binary;
        }

        /**
         * Sets the value of the binary property.
         * 
         */
        public void setBinary(boolean value) {
            this.binary = value;
        }

        /**
         * Gets the value of the template property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getTemplate() {
            return template;
        }

        /**
         * Sets the value of the template property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setTemplate(Integer value) {
            this.template = value;
        }

        /**
         * Gets the value of the outputFormat property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getOutputFormat() {
            return outputFormat;
        }

        /**
         * Sets the value of the outputFormat property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setOutputFormat(Integer value) {
            this.outputFormat = value;
        }

    }

}
