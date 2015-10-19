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
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


/**
 *
 * @author mac
 */
public class xmlDocImpl implements xmlDoc{
    
    int findMode;//we should enter it at the beginning
    
    Document doc;
    Element rootElement;
    String rootName;
    
    //find information
    String findName;
    String findId;

    @Override
    //Как понять на какую иерархию хотим вставлять??
    public Element[] addObject(String[] atributes) {
     //Прибавили к корневому элементу наш присоединяемый элемент, прибавили к новому элементу имя   
        String elemName = atributes[2];    
        String id = atributes[0];  
        String name = atributes[1];
        
        if (name.length()<3)
            System.err.println("Valdation error. Please, enter an appropriate name");
        
        //это создание заменить на вызов конструктора элемента 
        //Element elemname = doc.createElement("name");
        Element newElement = doc.createElement(elemName);//добавить обработку id к узлу
        newElement.setAttribute(atributes[0], atributes[1]);
        
        //newElement.appendChild(elemname);
        doc.getElementsByTagName(rootName).item(0).appendChild(newElement);
        
        //Element[] findRes = findObject(findName);
        //**********************************************************************
        //xmlElemImpl xmlElem
        
        try {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(new File("/Users/mac/NetBeansProjects/xml_netk/src/xml_netk/source.xml"));

        
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(xmlDocImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @Override
    public void transformElement() {
        try {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
	StreamResult result = new StreamResult(new File("/Users/mac/NetBeansProjects/xml_netk/src/xml_netk/source.xml"));

        
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);
        } catch (TransformerException ex) {
            Logger.getLogger(xmlDocImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public Element[] deleteObject(int name) {
             
        //Element el = doc.getElementById(id);//выбираем то, что нам нужно удалить 
        //el.getParentNode().removeChild(el);
        Element[] numberOfCandidates = findObject(name);
        
        
        
        if(numberOfCandidates.length == 1) {
            
            System.out.println("We've found one candidate for deletion. Procceding to delete...");
            numberOfCandidates[0].getParentNode().removeChild(numberOfCandidates[0]);
            
        } else {
            
           Scanner sc = new Scanner(System.in);//если мы нашли значений больше, чем надо
           
           String identityHelper = sc.nextLine();
           String identityvalue = sc.nextLine();
           
           NodeList help = null;
           int i;
           for (i = 0; i < numberOfCandidates.length; i++) {
               
               help = numberOfCandidates[i].getElementsByTagName(identityHelper);
               String text = help.item(0).getTextContent();
               if (identityvalue.equals(text)) {
                   break;
               }
           }
           help.item(i).getParentNode().removeChild(help.item(i));
              
        //it depends on what we want to pass to our find() function - id value or name value
       /* if (findMode == 1) {
            return findObjectbyName(name);
        } else if (findMode == 0) {
            return findObjectById();
        }*/
        
        
    }
        transformElement();
        return null;
    }
    
   /* @Override
    public Element[] findObjectbyName(String name) {
        
        
    }*/

    @Override
    public Element[] findObject(int id) {//find objects by id
        
        //find search elements as children of the special node
        //use finding with xpath
        Element[] elements;
        XPath xpath = XPathFactory.newInstance().newXPath();
        
        //String search = nodesName[0]+'/'+nodesName[1]+"//*[contains(., attribute)]";
        String search = "/content/emp/employee[@deptno=10]";
        
        NodeList nodeList = null;
        
        try {
            nodeList = (NodeList) xpath.compile(search).evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(xmlDocImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(nodeList.getLength());
        int curElem = 0;
        for (int i = 0; i < nodeList.getLength(); i++) {

            Node cur_node = nodeList.item(i);
            if (nodeList.item(i).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                 //elements[i] = (Element) cur_node;
                 System.out.println(cur_node.getNodeName());//getParentNode());//????
                 elements[curElem] = (Element) nodeList.item(i);
                 curElem+=1;
            }
        }
        return elements;
             
    
    }

    @Override
    public String initObject(File file) {
        try{
            
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        factory.setNamespaceAware(true);
        doc = factory.newDocumentBuilder().parse(file);
        
        rootElement = doc.getDocumentElement();
        rootName = rootElement.getTagName();
        
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(xmlDocImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ((doc != null) && (rootElement != null) && (rootName != null))
            return "The document was created";
        
        return "The document wasn't created. Check file state";
    }
    
    
    @Override
    public String editObject(String id, String[] values) {
        
       xmlElemImpl xmlElem = new xmlElemImpl();
       Element el = xmlElem.setName(id, values[0], values[1]);
       
       //setName(el, values[0], values[1]);
       
      return null; 
    }

    @Override
    public String getRootName() {
        
        return rootName;
    }

    
    
    
}