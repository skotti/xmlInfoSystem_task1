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
    
    private Element elem;
    
    private String tagName;
    private String id;
    private String rootName = "emp";
    //String elemName;
    //String elemname;

    @Override
    public xmlElem initObject(Document doc, String id, String tagname) {
        
        Element elem = doc.createElement(tagname);
        this.id = id;
        this.tagName = tagname;
        elem.setAttribute("id", id);
        doc.getElementsByTagName(rootName).item(0).appendChild(elem);
        //Element newElement = doc.createElement(elemName);//добавить обработку id к узлу
        
        //newElement.appendChild(elemname);
        
        return this;
    }


    @Override
    public Element setName(String id, String attribute, String value) {
        
        //1.Find element with this id
        elem.setAttribute(attribute, value);
        
        return elem;    
        
    }
    
    @Override
    public String getName() {
        
        return this.tagName;
    }
    
    
}
