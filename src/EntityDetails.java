
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sanya
 */
public class EntityDetails {
    
    EntityDetails(String fname)
    {
        XMLReader xml=new XMLReader(fname);
    }
    EntityDetails()
    {
        
    }
    static ArrayList<Class> classList           =new ArrayList();
    //static ArrayList<Class> interfaceList       =new ArrayList();
    //static ArrayList<Class> enumList            =new ArrayList();
    static ArrayList<Methods> methodsList       =new ArrayList();
    static ArrayList<Variables> variablesList   =new ArrayList();
    
}
