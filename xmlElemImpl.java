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
public class xmlElemImpl implements xmlElem{
    
    Element elem;
    
    //String tagName;
    //String id;
    //String elemName;
   // String name;

    @Override
    public Element initObject(Document doc, String id, String elemName) {
        
        Element elemname = doc.createElement("name");
        Element newElement = doc.createElement(elemName);//добавить обработку id к узлу
        
        newElement.appendChild(elemname);
        
        return newElement;
    }


    @Override
    public void setName(Element elem, String tagName, String attribute) {
        
        elem.setAttribute(tagName, attribute);
        
        
    }
    
    
}
