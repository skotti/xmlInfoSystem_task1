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
    final static int ELEM_ID = 0;
    final static int ELEM_TAG = 1;
    private Element elem;
    
    private String tagName;
    private String id;
    private String rootName = "emp";

    @Override
    public xmlElem initObject(Document doc, String rootName,String[] atributes) {
        
        elem = doc.createElement(atributes[ELEM_TAG]);
        this.id = atributes[ELEM_ID];
        this.tagName = atributes[ELEM_TAG];
        this.rootName = rootName;
        elem.setAttribute("id", atributes[ELEM_ID]);
        elem.setIdAttribute("id", true);
        
        
        //set the other part of attribute; we consider that after an attribute goes its value
        for (int i = 2; i < atributes.length-1; i++)
            elem.setAttribute(atributes[i], atributes[i+1]);
        
        //check if there is any root in this document, simply is this document empty or not
        if (rootName!=null)
            doc.getElementsByTagName(rootName).item(0).appendChild(elem);
        else
            doc.appendChild(elem);
        
        return this;
    }


    @Override
    public Element setName(String id, String attribute, String value) {
        
        elem.setAttribute(attribute, value);
        
        return elem;    
        
    }
    
    @Override
    public String getName() {
        
        return this.tagName;
    }
    
    
}
