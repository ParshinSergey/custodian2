//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2025.01.09 at 06:17:34 PM MSK 
//


package dmt.custodian2016;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for TcrsConfigAccount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TcrsConfigAccount"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="HolderType" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:dmt:custodian2016&gt;TcrsAcctHolderType"&gt;
 *                 &lt;attribute name="changed" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Dormant" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;boolean"&gt;
 *                 &lt;attribute name="changed" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Undocumented" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;boolean"&gt;
 *                 &lt;attribute name="changed" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
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
@XmlType(name = "TcrsConfigAccount", propOrder = {
    "holderType",
    "dormant",
    "undocumented"
})
public class TcrsConfigAccount {

    @XmlElement(name = "HolderType")
    protected TcrsConfigAccount.HolderType holderType;
    @XmlElement(name = "Dormant")
    protected TcrsConfigAccount.Dormant dormant;
    @XmlElement(name = "Undocumented")
    protected TcrsConfigAccount.Undocumented undocumented;

    /**
     * Gets the value of the holderType property.
     * 
     * @return
     *     possible object is
     *     {@link TcrsConfigAccount.HolderType }
     *     
     */
    public TcrsConfigAccount.HolderType getHolderType() {
        return holderType;
    }

    /**
     * Sets the value of the holderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TcrsConfigAccount.HolderType }
     *     
     */
    public void setHolderType(TcrsConfigAccount.HolderType value) {
        this.holderType = value;
    }

    /**
     * Gets the value of the dormant property.
     * 
     * @return
     *     possible object is
     *     {@link TcrsConfigAccount.Dormant }
     *     
     */
    public TcrsConfigAccount.Dormant getDormant() {
        return dormant;
    }

    /**
     * Sets the value of the dormant property.
     * 
     * @param value
     *     allowed object is
     *     {@link TcrsConfigAccount.Dormant }
     *     
     */
    public void setDormant(TcrsConfigAccount.Dormant value) {
        this.dormant = value;
    }

    /**
     * Gets the value of the undocumented property.
     * 
     * @return
     *     possible object is
     *     {@link TcrsConfigAccount.Undocumented }
     *     
     */
    public TcrsConfigAccount.Undocumented getUndocumented() {
        return undocumented;
    }

    /**
     * Sets the value of the undocumented property.
     * 
     * @param value
     *     allowed object is
     *     {@link TcrsConfigAccount.Undocumented }
     *     
     */
    public void setUndocumented(TcrsConfigAccount.Undocumented value) {
        this.undocumented = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;boolean"&gt;
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
    public static class Dormant {

        @XmlValue
        protected boolean value;
        @XmlAttribute(name = "changed")
        protected Boolean changed;

        /**
         * Gets the value of the value property.
         * 
         */
        public boolean isValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         */
        public void setValue(boolean value) {
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;urn:dmt:custodian2016&gt;TcrsAcctHolderType"&gt;
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
    public static class HolderType {

        @XmlValue
        protected TcrsAcctHolderType value;
        @XmlAttribute(name = "changed")
        protected Boolean changed;

        /**
         * Тип власника рахунку юридичної особи для цілей CRS
         * 
         * @return
         *     possible object is
         *     {@link TcrsAcctHolderType }
         *     
         */
        public TcrsAcctHolderType getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link TcrsAcctHolderType }
         *     
         */
        public void setValue(TcrsAcctHolderType value) {
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;boolean"&gt;
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
    public static class Undocumented {

        @XmlValue
        protected boolean value;
        @XmlAttribute(name = "changed")
        protected Boolean changed;

        /**
         * Gets the value of the value property.
         * 
         */
        public boolean isValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         */
        public void setValue(boolean value) {
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
