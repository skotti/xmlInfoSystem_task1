/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_netk;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author mac
 */
public class PrinterClass implements Printer{

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
        
        String info = "You can add infromation in this format:\n"
                + "id tag attributes(name of attribute and value).\n"
                + "You can find node in this format:\n"
                + "tag attributeName attributeValue.\n"
                + "You can delete node in this format:\n"
                + "tag attributeName attributeValue.";
        System.out.println(info);
                
    }
}
