/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_netk;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *This class will be responsible for printing information
 * @author mac
 */
public interface Printer {
    
    /**
     * Print the information about the node
     * @param node 
     */
    public void printInfo(Node node);
    
    /**
     * Print the result of find information
     * @param list 
     */
    public void printList(NodeList list);
    
    /**
     * Prints information about possibilities
     */
    public void printTextInfo();
    
}
    
