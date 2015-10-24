/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_netk;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *This interface interacts with xml documents and can find, read, add and edit information.
 *You should pass the address of edited xml document and the call functions with appropriate arguments.
 * @author mac
 */
public interface xmlDoc {
    
    /**
     * Returns the root name in our document
     * @return String : the root name
     */
    public String getRootName();
    
    /**
     * This function inits the DOM model, sets the rootname and rootElement.
     * @param file:output file or any stream
     * @return String - the result of creating - successful or not
     */
    public String initObject(File file);
   
    
    /**
     * This function appends a new node to our structure
     * The first two attributes should be id number and the name of tag
     * @param atributes : values of atributes of new object
     */
    public void addObject(String[] atributes);
    
    /**
     * This function delete the specified object. Inside we call find() funtion which will find all appropriate nodes.
     * If there is more than one node, delete() function will ask the user to specify another attribute to be able 
     * to distinguish the concrete Node (TODO: ask user, until there will be one node, now this works only ones)
     * @param tagname Tag of the object we want to delete
     * @param atributeName Name of the atribute of the element we want to delete
     * @param atribute Value of the atribute of the alement we want to delete
     */
    public void deleteObject(String tagname, String atributeName, String atribute);
    
    /**
     * This function finds all the nodes, which have the specified attribute.
     * @param tagname Tagname of the node we want to find
     * @param atributeName The name of attribute we want to use duting the process of finding
     * @param atribute The value of attribute
     * @return an array of found elements with given textValue
     *///yes, now the parameter is id, but we will have two functions - if person search with help of id or 
    //with help of the name and any other parameter.
    public NodeList findObject(String tagname, String atributeName, String atribute);
    
    /**
     * 
     * @param tagName The tag name of edited object
     * @param id The id of he specified object 
     */
    public void editObject(String tagName, String id);
    
    /**
     * It should transform the document according to recent changes
     */
    public void transformElement();
    
    /**
     * Returns the current version of the document
     * @return Document
     */
    public Document getDocument();
    
    /**
     * Sets the tag of found information
     * @param findTag 
     */
    public void setfindTag(String findTag);
    
    /**
     * Sets the name of found information
     * @param findname 
     */
    public void setfindName(String findname);
    
    /**
     * Sets the attribute of found information
     * @param findatr 
     */
    public void setfindAtr(String findatr);
    
    
}
