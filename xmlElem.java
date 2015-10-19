/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_netk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author mac
 */
public interface xmlElem {
    
    public Element initObject(Document doc, String id, String elemName);
    
    //public Element modifyObject(Element currentElement, String[] values);
    
    public void setName(Element elem, String tagName, String attribute);
    
}
