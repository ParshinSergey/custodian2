//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2025.01.09 at 06:17:34 PM MSK 
//


package dmt.custodian2016;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TConnection_status_request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TConnection_status_request"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Depositary" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{urn:dmt:custodian2016}TDepositary"&gt;
 *               &lt;enumeration value="100"/&gt;
 *               &lt;enumeration value="200"/&gt;
 *               &lt;enumeration value="1000"/&gt;
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
@XmlType(name = "TConnection_status_request", propOrder = {
    "depositary"
})
public class TConnectionStatusRequest {

    @XmlElement(name = "Depositary")
    protected String depositary;

    /**
     * Gets the value of the depositary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepositary() {
        return depositary;
    }

    /**
     * Sets the value of the depositary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepositary(String value) {
        this.depositary = value;
    }

}
