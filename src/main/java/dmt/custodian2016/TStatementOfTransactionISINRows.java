//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.05.02 at 09:35:26 PM MSK 
//


package dmt.custodian2016;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Заиписи операций в разрезе ISIN
 * 
 * <p>Java class for TStatement_of_Transaction_ISIN_rows complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TStatement_of_Transaction_ISIN_rows"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0"&gt;
 *         &lt;element name="Row" type="{urn:dmt:custodian2016}TStatement_of_Transaction_ISIN_row"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TStatement_of_Transaction_ISIN_rows", propOrder = {
    "row"
})
public class TStatementOfTransactionISINRows {

    @XmlElement(name = "Row")
    protected List<TStatementOfTransactionISINRow> row;

    /**
     * Gets the value of the row property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the row property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TStatementOfTransactionISINRow }
     * 
     * 
     */
    public List<TStatementOfTransactionISINRow> getRow() {
        if (row == null) {
            row = new ArrayList<TStatementOfTransactionISINRow>();
        }
        return this.row;
    }

}
