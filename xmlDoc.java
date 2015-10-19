/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_netk;

import java.io.File;
import org.w3c.dom.Element;

/**
 *
 * @author mac
 */
public interface xmlDoc {
    
    public String getRootName();
    
    /**
     * This function inits the DOM model, sets the rootname and rootid.
     * @param atributes:output file or any stream
     * @return String - the result of creating - successful or not
     */
    public String initObject(File file);
    
   // public String initxmlObject(Element elem);
    
    public Element[] addObject(String[] atributes);
    
    public String deleteObject(String[] atributes);
    
    /**
     * 
     * 
     * @param attribute :if we know the concrete document structure, then we can use XPath and get the part of it 
     * with help of these nodeName , f.e. /emp/employee will look like /nodesName[0]/nodesName[1]
     * @return an array of found elements with given textValue
     */
    public Element[] findObject(String attribute) ;
    
    public String editObject(String id,String[] values) ;
    
    
}
