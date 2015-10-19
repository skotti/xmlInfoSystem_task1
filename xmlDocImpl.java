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
 * TODO: What is this class supposed to do? Shortly describe it's functionality and scope of problems
 * @author mac
 */
public class xmlDocImpl implements xmlDoc{
    
    private int findMode;//we should enter it at the beginning
    
    private Document doc; //Not sure if it does not destroy your code, but usually fields are private. 
    //It is a bad practice to have non-private fields
    
    private Element rootElement;
    private String rootName;
    
    //find information
    private String findName;
    private String findId;

    /**
     * A perfect way for implementation of such methods is the following:
     * method addObject(attributes) {
     * 		element = retrieveElement(attributes);
     * 		validateElement(element); //Throws exception, if invalid
     * 		addElement(element);
     * }
     * And somewhere in the end or in separate class, called "controller", implement those methods.
     */

    @Override
    //Как понять на какую иерархию хотим вставлять??
    //ROTU: например, указывать помимо объекта вставки его парент. 
    public Element[] addObject(String[] atributes) {
     //Прибавили к корневому элементу наш присоединяемый элемент, прибавили к новому элементу имя   
        String elemName = atributes[2];    //Numbers inside code - bad practice.
        //You better specify somewhere at the top of class some format, i.e.
        // private final static int ELEM_NAME_ID = 2 and then use it.
        String id = atributes[0];  
        String name = atributes[1];
        
        if (name.length()<3) //It's better to handle validation separately. I.e., use private method.
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
             
        //Search what to delete ;now only function can analize only id value
        //so elese will not be executed, but it isn't the greatest problem
        NodeList numberOfCandidates = findObject(name);
        
        System.out.println(numberOfCandidates.getLength());
        
        
        
        if(numberOfCandidates.getLength() == 1) {
            
            System.out.println("We've found one candidate for deletion. Procceding to delete...");
            numberOfCandidates.item(0).getParentNode().removeChild(numberOfCandidates.item(0));
            
        } else {
            
           Scanner sc = new Scanner(System.in);//если мы нашли значений больше, чем надо
           
           String identityHelper = sc.nextLine();
           String identityvalue = sc.nextLine();
           
           NodeList help = null;
           int i;
           for (i = 0; i < numberOfCandidates.getLength(); i++) {
               
               help = numberOfCandidates.item(i).getChildNodes();
               
               //item(0) is #text - ??? what is that??
               //we should choose the right child
               int j=0;
               while(!(help.item(j).getNodeName().equals(identityHelper)))
                       j+=1;
               
               System.out.println(help.item(j).getNodeName());
               String text = help.item(j).getTextContent();
               System.out.println(text);
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
        //add changes to the document
        transformElement();
        
        //returning null is funny but now I should make the upper part working
        return null;
    }
    
   /* @Override
    public Element[] findObjectbyName(String name) {
        
        
    }*/

    @Override
    public NodeList findObject(int id) {//find objects by id(in my scheme id is deptno so it is not unique,
        //but it is made only for testing
        
        
        Element[] elements;
        XPath xpath = XPathFactory.newInstance().newXPath();
        
        //String search = nodesName[0]+'/'+nodesName[1]+"//*[contains(., attribute)]";
        //Test string, it is not clear now, how to made [@deptno=id] - it doesn't work!
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
                 System.out.println(cur_node.getNodeName());
                 //elements[curElem] = (Element) nodeList.item(i);//It seems to me that it is't right simply
                 //adding elements in array, as it doesn't work now
                 //curElem+=1;
            }
        }
        return nodeList;
             
    
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
