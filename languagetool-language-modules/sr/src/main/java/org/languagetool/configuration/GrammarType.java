

package org.languagetool.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for grammarType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="grammarType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rulefiles" type="{}rulefilesType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "grammarType", propOrder = {
    "rulefiles"
})
public class GrammarType {

    protected RulefilesType rulefiles;

    /**
     * Gets the value of the rulefiles property.
     * 
     * @return
     *     possible object is
     *     {@link RulefilesType }
     *     
     */
    public RulefilesType getRulefiles() {
        return rulefiles;
    }

    /**
     * Sets the value of the rulefiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link RulefilesType }
     *     
     */
    public void setRulefiles(RulefilesType value) {
        this.rulefiles = value;
    }

}
