/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_netk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *This interface represents Element structure
 * (TODO: make it more involved in the work of main Doc class)
 * @author mac
 */
public interface xmlElem {
    
    /**
     * 
     * @param doc       Document, where we want to create a new object
     * @param rootName  name of the root
     * @param atributes atributes of this element
     * @return        Element
     */
    public xmlElem initObject(Document doc, String rootName,String[] atributes);
    
    //public Element modifyObject(Element currentElement, String[] values);
    
    /**
     * 
     * @param id        : which id of this element we should modify ( if we have some children for example)
     * @param attribute : which attribute we should modify
     * @param value     : which value we should modify
     * @return 
     */
    public Element setName(String id, String attribute, String value);
    
    public String getName();
    
}
