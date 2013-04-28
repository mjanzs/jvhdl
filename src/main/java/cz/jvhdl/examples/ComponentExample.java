/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.jvhdl.examples;

import cz.jvhdl.EntityVhdl;
import cz.jvhdl.ComponentVhdl;
import cz.jvhdl.VHDL;

/**
 *
 * @author Martin
 */
public class ComponentExample  extends VHDL {

    public ComponentExample() {
        ComponentVhdl portMap = Component(new EntityVhdl("entity"), false);
        portMap.put("map1", "MAP1");
        portMap.put("map2", "MAP2");
        portMap.put("map3", "MAP3");
    }
    
    public ComponentExample(boolean standalone)  {
        ComponentVhdl portMap = Component(new EntityVhdl("entity"), standalone);
        portMap.put("map1", "MAP1");
        portMap.put("map2", "MAP2");
        portMap.put("map3", "MAP3");
        
    }
    
    public static void main(String[] args) {
        System.out.println(new ComponentExample());
        System.out.println(new ComponentExample(true));
    }
}
