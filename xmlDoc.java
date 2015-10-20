/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_netk;

import java.io.File;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author mac
 */
public interface xmlDoc {
    
    /**
     * Returns the root name in our document
     * @return String : the root name
     */
    public String getRootName();
    
    /**
     * This function inits the DOM model, sets the rootname and rootid.
     * @param file:output file or any stream
     * @return String - the result of creating - successful or not
     */
    public String initObject(File file);
    
   // public String initxmlObject(Element elem);
    
    /**
     * 
     * @param atributes : initial values as tagname, attribute and the value o attribute
     * @return Elements[] : the last find execution, taking the added value into account
     */
    public Element[] addObject(String[] atributes);
    
    /**
     * 
     * @param id : id parameter for the object, that should be deleted
     * @return the result of th last find operation
     */
    public Element[] deleteObject(int id);
    
    /**
     * 
     * 
     * @param attribute :if we know the concrete document structure, then we can use XPath and get the part of it 
     * with help of these nodeName , f.e. /emp/employee will look like /nodesName[0]/nodesName[1]
     * @return an array of found elements with given textValue
     *///yes, now the parameter is id, but we will have two functions - if person search with help of id or 
    //with help of the name and any other parameter.
    public NodeList findObject(int id) ;
    
    public String editObject(String id,String[] values) ;
    /**
     * It should transform the document according to recent changes
     */
    public void transformElement();
    
    
}
