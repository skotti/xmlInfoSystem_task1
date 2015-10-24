/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_netk;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *This is a special class, where all second sort functions are. 
 * They help to manipulate with objects and elements.
 * @author mac
 */
public class Controller implements ControllerInterface{
    
   
    //Element functions//
    @Override
    public xmlElem retrieveElement(Document doc, String rootName,String[] atributes) {
        
        xmlElem elem = new xmlElemImpl();
        elem.initObject(doc,rootName,atributes);
        return elem;
    }
    
    @Override
    public int selectItem(NodeList list, String identityHelper, String identityvalue) {
        int i = 0;
        int counterOfEquals = 0;
        int selectedItem = 0;
        for (i = 0; i < list.getLength(); i++) {
               
               NamedNodeMap nnm = list.item(i).getAttributes();
               if (identityvalue.equals(nnm.getNamedItem(identityHelper).getNodeValue())) {
                   selectedItem = i;
                   counterOfEquals+=1;
                   
               }
               
        
    }
        if(counterOfEquals > 1) {
            System.err.println("Bad query. The node is not specified");
            System.exit(-1);
        }
            
        return selectedItem;
    }
    
    @Override
    public NodeList findElementWithXpath(xmlDocImpl doc, String tagname, String attributename, String attribute) {
        
        XPath xpath = XPathFactory.newInstance().newXPath();
        
        String search = "//"+tagname+"[@"+attributename+"="+attribute+"]";
        NodeList nodeList = null;
        
        try {
            nodeList = (NodeList) xpath.compile(search).evaluate(doc.getDocument(), XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(xmlDocImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nodeList;
        
    }
    
    @Override
    public String findPathToElement(Document doc, String rootname, String tagname) {
        
        NodeList nodes = doc.getElementsByTagName(tagname);
        Node n = nodes.item(0);
        String[] foundNodes = new String[10];
        int i = 0;

        while (n.getParentNode() != null) {
            System.out.println(n.getNodeName());
            foundNodes[i] = n.getNodeName();
            n = n.getParentNode();
            i+=1;
            
        }
        
        String result = "";
        for (int j = i-1; j > 0;j--) {
            
            result = result+foundNodes[j];
        }
        return result;
    }
    
    
    
    
    
    
    //Document functions//
    @Override
    public Document initDocument(File file) throws ParserConfigurationException, SAXException, IOException {
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().parse(file);
        return doc;
    }
    
    @Override
    public xmlDocImpl initialiseDoc(xmlDocImpl doc, String findtag, String findname, String findatr) {
        
        doc.setfindAtr(findatr);
        doc.setfindName(findname);
        doc.setfindTag(findtag);
        return doc;
    }

    
    
  //Moved to printer class  
    
  /*  
    
    //print functions
    @Override
    public void printInfo(Node node) {

        NamedNodeMap atributes = node.getAttributes();
        if (atributes.getLength()!=0) {

        for (int i = 0; i < atributes.getLength(); i++) {
            
            System.out.print(atributes.item(i).getNodeName() + " " + atributes.item(i).getNodeValue()+"\t");
        }
        System.out.print("\n");
        }

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                printInfo(currentNode);
            }
    
        }
    }
    
    @Override
    public void printList(NodeList list) {
        
        
        for(int i = 0;i < list.getLength();i++) {
            NamedNodeMap atributes = list.item(i).getAttributes();
            for(int j = 0;j < atributes.getLength(); j++) {
                System.out.print(atributes.item(i).getNodeName() + " " + atributes.item(i).getNodeValue()+"\t");
            }
        }
        
    }
    
    @Override 
    public void printTextInfo() {
        
        String info = "You can add infromatio in this format:\n"
                + "id tag attributes(name of attribute and value).\n"
                + "You can find node in this format:\n"
                + "tag attributeName attributeValue.\n"
                + "You can delete node in this format:"
                + "tag attributeName attributeValue.";
        System.out.println(info);
                
    }*/
    
}
