/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml_netk;

import java.io.File;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author mac
 */
public class testClass {
    
    public static void main(String[] args) {
        
    File newFile = new File("/Users/mac/NetBeansProjects/xml_netk/src/xml_netk/source.xml");
    
    xmlDocImpl xm = new xmlDocImpl();
    
    System.out.println(xm.initObject(newFile));
    
    //System.out.println(xm.getRootName());
    
    //NodeList el = xm.findObject(10);
    
    xm.deleteObject(50);
   
    //String[] atributes = new String[3];
    /*atributes[0] = "deptno";
    atributes[1] = "1555";
    atributes[2] = "employee";*/
    
    //xm.addObject(atributes);
    }
    
}
