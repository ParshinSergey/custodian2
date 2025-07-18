//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2025.06.30 at 03:15:09 PM MSK 
//


package dmt.custodian2016;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TcrsAccount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TcrsAccount"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="config" type="{urn:dmt:custodian2016}TcrsConfigAccount" minOccurs="0"/&gt;
 *         &lt;element name="reportings" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="reporting"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;extension base="{urn:dmt:custodian2016}TcrsReporting"&gt;
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
 *         &lt;element name="TINs" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *                   &lt;element name="TIN"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;extension base="{urn:dmt:custodian2016}TcrsTIN"&gt;
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
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TcrsAccount", propOrder = {
    "config",
    "reportings",
    "tiNs"
})
public class TcrsAccount {

    protected TcrsConfigAccount config;
    protected TcrsAccount.Reportings reportings;
    @XmlElement(name = "TINs")
    protected TcrsAccount.TINs tiNs;

    /**
     * Gets the value of the config property.
     * 
     * @return
     *     possible object is
     *     {@link TcrsConfigAccount }
     *     
     */
    public TcrsConfigAccount getConfig() {
        return config;
    }

    /**
     * Sets the value of the config property.
     * 
     * @param value
     *     allowed object is
     *     {@link TcrsConfigAccount }
     *     
     */
    public void setConfig(TcrsConfigAccount value) {
        this.config = value;
    }

    /**
     * Gets the value of the reportings property.
     * 
     * @return
     *     possible object is
     *     {@link TcrsAccount.Reportings }
     *     
     */
    public TcrsAccount.Reportings getReportings() {
        return reportings;
    }

    /**
     * Sets the value of the reportings property.
     * 
     * @param value
     *     allowed object is
     *     {@link TcrsAccount.Reportings }
     *     
     */
    public void setReportings(TcrsAccount.Reportings value) {
        this.reportings = value;
    }

    /**
     * Gets the value of the tiNs property.
     * 
     * @return
     *     possible object is
     *     {@link TcrsAccount.TINs }
     *     
     */
    public TcrsAccount.TINs getTINs() {
        return tiNs;
    }

    /**
     * Sets the value of the tiNs property.
     * 
     * @param value
     *     allowed object is
     *     {@link TcrsAccount.TINs }
     *     
     */
    public void setTINs(TcrsAccount.TINs value) {
        this.tiNs = value;
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
     *         &lt;element name="reporting"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;extension base="{urn:dmt:custodian2016}TcrsReporting"&gt;
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
        "reporting"
    })
    public static class Reportings {

        protected List<TcrsAccount.Reportings.Reporting> reporting;

        /**
         * Gets the value of the reporting property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the reporting property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReporting().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TcrsAccount.Reportings.Reporting }
         * 
         * 
         */
        public List<TcrsAccount.Reportings.Reporting> getReporting() {
            if (reporting == null) {
                reporting = new ArrayList<TcrsAccount.Reportings.Reporting>();
            }
            return this.reporting;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;extension base="{urn:dmt:custodian2016}TcrsReporting"&gt;
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
        public static class Reporting
            extends TcrsReporting
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
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
     *         &lt;element name="TIN"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;extension base="{urn:dmt:custodian2016}TcrsTIN"&gt;
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
        "tin"
    })
    public static class TINs {

        @XmlElement(name = "TIN")
        protected List<TcrsAccount.TINs.TIN> tin;

        /**
         * Gets the value of the tin property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the tin property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTIN().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TcrsAccount.TINs.TIN }
         * 
         * 
         */
        public List<TcrsAccount.TINs.TIN> getTIN() {
            if (tin == null) {
                tin = new ArrayList<TcrsAccount.TINs.TIN>();
            }
            return this.tin;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;extension base="{urn:dmt:custodian2016}TcrsTIN"&gt;
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
        public static class TIN
            extends TcrsTIN
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

}
