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
    
    xm.initObject(newFile);
    String[] atributes = new String[2];
    atributes[0] = "1226";
    atributes[1] = "employee";
    
    //xm.addObject(atributes);
    //xm.deleteObject("employee", "id", "1226");
    xm.findObject("employee", "id", "1225");
    //xm.deleteObject("employee", "id", "1222");
    
    //System.out.println(xm.getRootName());
    
    //NodeList el = xm.findObject(10);
    
    //xm.deleteObject(50);
   
    //String[] atributes = new String[3];
    /*atributes[0] = "deptno";
    atributes[1] = "1555";
    atributes[2] = "employee";*/
    
    //xm.addObject(atributes);
    }
    
}
