//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.09.08 um 03:06:37 PM CEST 
//


package com.saucecode.packer.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.saucecode.com/packer/xml}sets"/>
 *         &lt;element ref="{http://www.saucecode.com/packer/xml}categories"/>
 *         &lt;element ref="{http://www.saucecode.com/packer/xml}items"/>
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
    "sets",
    "categories",
    "items"
})
@XmlRootElement(name = "library")
public class Library {

    @XmlElement(required = true)
    protected Sets sets;
    @XmlElement(required = true)
    protected Categories categories;
    @XmlElement(required = true)
    protected Items items;

    /**
     * Ruft den Wert der sets-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Sets }
     *     
     */
    public Sets getSets() {
        return sets;
    }

    /**
     * Legt den Wert der sets-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Sets }
     *     
     */
    public void setSets(Sets value) {
        this.sets = value;
    }

    /**
     * Ruft den Wert der categories-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Categories }
     *     
     */
    public Categories getCategories() {
        return categories;
    }

    /**
     * Legt den Wert der categories-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Categories }
     *     
     */
    public void setCategories(Categories value) {
        this.categories = value;
    }

    /**
     * Ruft den Wert der items-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Items }
     *     
     */
    public Items getItems() {
        return items;
    }

    /**
     * Legt den Wert der items-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Items }
     *     
     */
    public void setItems(Items value) {
        this.items = value;
    }

}
