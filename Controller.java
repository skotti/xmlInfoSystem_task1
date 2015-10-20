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
public class Controller {
    
    
    public xmlElem retrieveElement(Document doc, String[] atributes) {
        
        xmlElem elem = new xmlElemImpl();
        //we pass here doc, id, tagname
        elem.initObject(doc,atributes[0],atributes[1]);//Correct in xmlElem class; we should pass here doc, id, tagname
        //I suppose that the user set initially id, then one attribute and it's value
        //There may be another option when we set id, child node, e.x. <name> and it's text value
        //elem.setName(atributes[0], atributes[2], atributes[3]);//(id, atribute,value)
        return elem;
    }
    
}
