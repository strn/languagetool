//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.06 at 11:16:44 AM CET 
//


package org.languagetool.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="grammar" type="{}grammarType" minOccurs="0"/>
 *         &lt;element name="morfologik" type="{}morfologikType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "grammar",
    "morfologik"
})
@XmlRootElement(name = "ltconfiguration")
public class Ltconfiguration {

    protected GrammarType grammar;
    protected MorfologikType morfologik;

    /**
     * Gets the value of the grammar property.
     * 
     * @return
     *     possible object is
     *     {@link GrammarType }
     *     
     */
    public GrammarType getGrammar() {
        return grammar;
    }

    /**
     * Sets the value of the grammar property.
     * 
     * @param value
     *     allowed object is
     *     {@link GrammarType }
     *     
     */
    public void setGrammar(GrammarType value) {
        this.grammar = value;
    }

    /**
     * Gets the value of the morfologik property.
     * 
     * @return
     *     possible object is
     *     {@link MorfologikType }
     *     
     */
    public MorfologikType getMorfologik() {
        return morfologik;
    }

    /**
     * Sets the value of the morfologik property.
     * 
     * @param value
     *     allowed object is
     *     {@link MorfologikType }
     *     
     */
    public void setMorfologik(MorfologikType value) {
        this.morfologik = value;
    }

}