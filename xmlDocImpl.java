/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_netk;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


/**
 *
 * @author mac
 */
public class xmlDocImpl implements xmlDoc{
    
    //Document information
    private Document doc;
    private Element rootElement;
    private String rootName;
    
    //find information from the previous find() function
    private String findName;
    private String findAtr;
    private String findTag;
    private String findId;
    
    Controller contr;
    Printer print;
    
    
    public xmlDocImpl() { 
        
        contr = new Controller();
        print = new PrinterClass();
    }
    
    private boolean validateName(String name) {
        if (name.length()<3){
            System.err.println("Valdation error. Please, enter an appropriate name");
            return false;
        }
        return true;
    }

    @Override
    public void addObject(String[] atributes) {

        xmlElem element = contr.retrieveElement(doc, rootName, atributes);
        validateName(element.getName());
        transformElement();
        
        if(element.getName().equals(findName))
            System.out.println("Added and equal with find");
        else
            System.out.println("Added and not equal with find");
    }
    
    @Override
    public void transformElement() {
        try {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(new File("/Users/mac/NetBeansProjects/xml_netk/src/xml_netk/source.xml"));

            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(xmlDocImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void deleteObject(String tagname, String atributeName, String atribute) {

        NodeList numberOfCandidates = findObject(tagname,atributeName, atribute); 
        
        if(numberOfCandidates.getLength() == 1) {
            
            System.out.println("We've found one candidate for deletion. Procceding to delete...");
            numberOfCandidates.item(0).getParentNode().removeChild(numberOfCandidates.item(0));
            
        } else {
            
           Scanner sc = new Scanner(System.in);
           String identityHelper = sc.nextLine();
           String identityvalue = sc.nextLine();
           
           int selectedItem = 0;
           selectedItem = contr.selectItem(numberOfCandidates, identityHelper, identityvalue);

           numberOfCandidates.item(selectedItem).getParentNode().removeChild(numberOfCandidates.item(selectedItem));
        }
        transformElement();      
        findObject(findTag, findName, findAtr);
    
    }

    @Override
    public NodeList findObject(String tagname, String atributeName, String atribute) {
        contr.initialiseDoc(this, tagname, atributeName, atribute);

        NodeList nodeList = null;
        nodeList = contr.findElementWithXpath(this, tagname, atributeName, atribute);
       
        print.printList(nodeList);
        return nodeList;
             
    
    }

    @Override
    public String initObject(File file) {
        try {
            doc = contr.initDocument(file);     
            rootElement = doc.getDocumentElement();
            rootName = rootElement.getTagName();
        
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(xmlDocImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ((doc != null) && (rootElement != null) && (rootName != null)) {
            
            print.printInfo(doc.getDocumentElement());
            print.printTextInfo();
            return "The document was created";
        }
        return "The document wasn't created. Check file state";
    }
    

    @Override
    public void editObject(String tagname, String id) {
       
       String scannedAtr;
       String scannedValue;
       
       Scanner sc = new Scanner(System.in);
       scannedAtr = sc.nextLine();
       scannedValue = sc.nextLine();
       
       changeAtribute(tagname, id, scannedAtr, scannedValue);
       
       System.out.println("Edited");
       transformElement();
       
       findObject(findTag, findName, findAtr); 
    }
    
    
    public void changeAtribute(String tagname, String id, String atr, String value) {
        
        NodeList list = findObject(tagname, "id", id);
        if (list.getLength() > 1) {
            System.err.println("Not a unique object");
            System.exit(-1);
        }
        Element foundEl = (Element)list.item(0);
        foundEl.setAttribute(atr, value);
    
    }

    @Override
    public String getRootName() {
        
        return rootName;
    }

    @Override
    public Document getDocument() {
        
        return doc;
    }
    
    @Override
    public void setfindTag(String findTag) {
        
        this.findTag = findTag;
    }
    
    @Override
    public void setfindName(String findname) {
        
        this.findName = findname;
    }
    
    @Override
    public void setfindAtr(String findatr) {
        
        this.findAtr = findatr;
    }
    
    
}
