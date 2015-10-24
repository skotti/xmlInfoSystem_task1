/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_netk;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *This interface is responsible for some additional functions, that are executed in xmlDocImpl
 * There three groups of them : related to elements, to nodes and responsible for printing information.
 * @author mac
 */
public interface ControllerInterface {
    
    /**
     * Initialise a new element. Is called from addElement() function in the main class. Calls inside 
     * a function from xmlElem
     * @param doc
     * @param rootName
     * @param atributes
     * @return xmlElem object
     */
    public xmlElem retrieveElement(Document doc, String rootName,String[] atributes);
    
    /**
     * Select a node which is specified with an additional parameter
     * @param list
     * @param identityHelper
     * @param identityvalue
     * @return int value
     */
    public int selectItem(NodeList list, String identityHelper, String identityvalue);
    
    /**
     * Find elements with the help of XPath expression
     * @param doc
     * @param tagname
     * @param attributename
     * @param attribute
     * @return NodeList
     */
    public NodeList findElementWithXpath(xmlDocImpl doc, String tagname, String attributename, String attribute);
    
    /**
     * Find path to a specified element (TODO: will be used^ when adding to any place will be added)
     * @param doc
     * @param rootname
     * @param tagname
     * @return String
     */
    public String findPathToElement(Document doc, String rootname, String tagname);
    
    /**
     * Create a new document
     * @param file
     * @return Document
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public Document initDocument(File file) throws ParserConfigurationException, SAXException, IOException;
    
    /**
     * Initialise the documnet with values from find() functions. It is necessary,
     * because we will use these values in our later functions as the ending
     * @param doc
     * @param findtag
     * @param findname
     * @param findatr
     * @return xmlDoc
     */
    public xmlDocImpl initialiseDoc(xmlDocImpl doc, String findtag, String findname, String findatr);
    

    
}
