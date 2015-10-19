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
    
    Document doc;
    Element rootElement;
    String rootName;
    
    //find information
    String findName;
    String findId;

    @Override
    public Element[] addObject(String[] atributes) {
     //Прибавили к корневому элементу наш присоединяемый элемент, прибавили к новому элементу имя   
        String elemName = atributes[2];    
        String id = atributes[0];  
        String name = atributes[1];
        
        //это создание заменить на вызов конструктора элемента 
        //Element elemname = doc.createElement("name");
        Element newElement = doc.createElement(elemName);//добавить обработку id к узлу
        newElement.setAttribute(atributes[0], atributes[1]);
        
        //newElement.appendChild(elemname);
        doc.getElementsByTagName("emp").item(0).appendChild(newElement);
        
        //Element[] findRes = findObject(findName);
        
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
    public String deleteObject(String[] atributes) {
        
        String id = atributes[1];//берем tagname для нашего узла       
        Element el = doc.getElementById(id);//выбираем то, что нам нужно удалить
        
        el.getParentNode().removeChild(el);
        
        //+ return last find operation 
        
        return "Success";
        
        
    }

    @Override
    public Element[] findObject(String attribute) {//find objects by id
        
        //find search elements as children of the special node
        //use finding with xpath
        Element[] elements = null;
        XPath xpath = XPathFactory.newInstance().newXPath();
        
        //String search = nodesName[0]+'/'+nodesName[1]+"//*[contains(., attribute)]";
        String search = "/content/emp/employee";
        
        NodeList nodeList = null;
        
        try {
            nodeList = (NodeList) xpath.compile(search).evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(xmlDocImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(nodeList.getLength());
        for (int i = 0; i < nodeList.getLength(); i++) {

            Node cur_node = nodeList.item(i);
            if (nodeList.item(i).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                 //elements[i] = (Element) cur_node;
                 System.out.println(cur_node.getNodeName());//getParentNode());//????
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

   /* @Override
    public String editObject(String id,String[] values) {
        
        this.findObject(id).setAttribute(values[0], values[1]);
        
        //initxmlObject.modifyElement(foundObject);
        
    }*/

    @Override
    public String editObject(String id, String[] values) {
        
       Element el = doc.getElementById(id);
       
       //setName(el, values[0], values[1]);
       
      return null; 
    }
    
public static void main(String[] args) {
    
    File newFile = new File("/Users/mac/NetBeansProjects/xml_netk/src/xml_netk/source.xml");
    
    xmlDocImpl xm = new xmlDocImpl();
    
    System.out.println(xm.initObject(newFile));
    
    System.out.println(xm.getRootName());
    
    Element[] el = xm.findObject("SMITH");
    
    String[] atributes = new String[3];
    atributes[0] = "deptno";
    atributes[1] = "1555";
    atributes[2] = "employee";
    
    xm.addObject(atributes);
    
    
    
}

    @Override
    public String getRootName() {
        
        return rootName;
    }

    
    
    
}
