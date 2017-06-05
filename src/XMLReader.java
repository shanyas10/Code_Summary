
//package summarization;

import java.io.*;
import java.util.Iterator;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
    
    EntityDetails elements=new EntityDetails();
    Document doc;
    XMLReader(String fName)
    {
        File f = new File("E:/nlp_generation/MicroPatternSample/" + fName +"xml");
	try {
  
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this. doc = dBuilder.parse(f);
            doc.getDocumentElement().normalize();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        findClass();
        findInterface();
        findState();
        findBehavior();
    }
    
    /**
     *  Method to find the details of class
     */
    public void findInterface()
    {
        NodeList nList = doc.getElementsByTagName("interface");
        Node node=nList.item(0);
        
        if(node!=null)
        {
            Class tClass=new Class();
            NodeList classDetails=node.getChildNodes();
            for (int temp = 0; temp < classDetails.getLength(); temp++) 
            {
                Node cNode=classDetails.item(temp);
                if(cNode.getNodeName().equals("specifier"))
                {
                    tClass.specifier=cNode.getTextContent().trim();
                    System.out.println(cNode.getTextContent().trim());
                }
                if(cNode.getNodeName().equals("name"))
                {
                    tClass.name=cNode.getTextContent().trim();
                    System.out.println(cNode.getTextContent().trim());
                }
                if(cNode.getNodeName().equals("super"))
                {
                    NodeList inheritorDetails= cNode.getChildNodes();
                    for (int j = 0; j < inheritorDetails.getLength(); j++)
                    {
                        Node iNode=inheritorDetails.item(j);
                        if(iNode.getNodeName().equals("extends"))
                        {
                            NodeList extendDetails=iNode.getChildNodes();
                            for (int k = 0; k < extendDetails.getLength(); k++)
                            {
                                Node eNode=extendDetails.item(k);
                                if(eNode.getNodeName().equals("name"))
                                {
                                    tClass.extend.add(eNode.getTextContent().trim());
                                    System.out.println(eNode.getTextContent().trim());
                                }    
                            }
                        }
                    }
                }
                if(cNode.getNodeName().equals("block"))
                {
                    if(cNode.getChildNodes().getLength()==1)
                        tClass.isEmpty=true;
                    else
                        System.err.println(cNode.getChildNodes().getLength());
                }
            }
            if(tClass.specifier==null)
                tClass.specifier="default";
            elements.classList.add(tClass);
        }    
        
    }
    public void findClass()
    {
        NodeList nList = doc.getElementsByTagName("class");
        Node node=nList.item(0);
        if(node!=null)
        {
            Class tClass=new Class();
            NodeList classDetails=node.getChildNodes();
            for (int temp = 0; temp < classDetails.getLength(); temp++) 
            {
                Node cNode=classDetails.item(temp);
                if(cNode.getNodeName().equals("specifier"))
                {
                    tClass.specifier=cNode.getTextContent().trim();
                    System.out.println(cNode.getTextContent().trim());
                }
                if(cNode.getNodeName().equals("name"))
                {
                    tClass.name=cNode.getTextContent().trim();
                    System.out.println(cNode.getTextContent().trim());
                }
                if(cNode.getNodeName().equals("super"))
                {
                    NodeList inheritorDetails= cNode.getChildNodes();
                    for (int j = 0; j < inheritorDetails.getLength(); j++)
                    {
                        Node iNode=inheritorDetails.item(j);
                        if(iNode.getNodeName().equals("extends"))
                        {
                            NodeList extendDetails=iNode.getChildNodes();
                            for (int k = 0; k < extendDetails.getLength(); k++)
                            {
                                Node eNode=extendDetails.item(k);
                                if(eNode.getNodeName().equals("name"))
                                {
                                    tClass.extend.add(eNode.getTextContent().trim());
                                    System.out.println(eNode.getTextContent().trim());
                                }    
                            }
                        }
                        if(iNode.getNodeName().equals("implements"))
                        {
                            NodeList implementDetails=iNode.getChildNodes();
                            for (int k = 0; k < implementDetails.getLength(); k++)
                            {
                                Node imNode=implementDetails.item(k);
                                if(imNode.getNodeName().equals("name"))
                                {
                                    tClass.implement.add(imNode.getTextContent().trim());
                                    System.out.println(imNode.getTextContent().trim());
                                }    
                            }
                        }
                    }
                }
                if(cNode.getNodeName().equals("block"))
                {
                    if(cNode.getTextContent()=="{}")
                        tClass.isEmpty=true;
                }
            }
            if(tClass.specifier==null)
                tClass.specifier="default";
            elements.classList.add(tClass);
        }    
    }

    /**
     *  Method to find the variables (instance,static) in the given file
     */
    public void findState()
    {
        NodeList nList = doc.getElementsByTagName("block");
        Node node=nList.item(0);
        NodeList blockDetails=node.getChildNodes();
        for(int temp=0;temp<blockDetails.getLength();temp++)
        {
            Node blockNode=blockDetails.item(temp);
            if(blockNode.getNodeName().equals("decl_stmt"))
            {
                String preSpecifier=null,preType=null;
                boolean isStatic=false,isFinal=false;
                NodeList stateDetails = blockNode.getChildNodes();
                for(int j=0;j<stateDetails.getLength();j++)
                {
                    Node stateNode=stateDetails.item(j);
                    if(stateNode.getNodeName().equals("decl"))
                    {
                        Variables variable=new Variables();
                        NodeList variableDetails = stateNode.getChildNodes();
                        for(int k=0;k<variableDetails.getLength();k++)
                        {
                            
                            Node variableNode=variableDetails.item(k);
                            if(variableNode.getNodeName().equals("specifier"))
                            {
                                String specifier;
                                specifier=variableNode.getTextContent();
                                if(specifier.equals("static"))
                                {
                                    variable.isStatic=true;
                                        isStatic=true;
                                       
                                }
                                else
                                {
                                    variable.specifier=specifier;
                                    preSpecifier=specifier;
                                }
                            }
                            if(variableNode.getNodeName().equals("type"))
                            {
                                String typeText;
                                typeText=variableNode.getTextContent();
                                if(typeText.contains("final"))
                                {
                                    variable.type=typeText.split(" ")[1];
                                    variable.isFinal=true;
                                    isFinal=true;
                                }
                                else
                                    variable.type=typeText;
                                if(variable.type!="")
                                    preType=variableNode.getTextContent();
                            }
                            if(variableNode.getNodeName().equals("name"))
                                variable.name=variableNode.getTextContent();
                            if(variableNode.getNodeName().equals("init"))
                            {
                                Node value=variableNode.getChildNodes().item(1);
                                variable.value=value.getTextContent();
                            }
                        }
                        //System.out.println(j+" "+preSpecifier+" "+variable.specifier+" "+preType);
                        if(variable.type=="")
                        {
                                variable.type=preType;
                            variable.isStatic=isStatic;
                            variable.isFinal=isFinal;
                            variable.specifier=preSpecifier;
                        }
                        if(variable.specifier==null)
                            variable.specifier="default";
                        System.out.println(variable.isStatic+" "+variable.isFinal);
                        elements.variablesList.add(variable);
                    }
                }
                   
            }
        }
    }
    public void findBehavior()
    {
        //Element e;
        NodeList nList = doc.getElementsByTagName("block");
        Node node=nList.item(0);
        NodeList blockDetails=node.getChildNodes();
        for(int temp=0;temp<blockDetails.getLength();temp++)
        {
            Node blockNode=blockDetails.item(temp);
            if(blockNode.getNodeName().equals("function"))
            {
                Methods method=new Methods();
                if(blockNode.getTextContent().contains("static"))
                    method.isStatic=true;
                if(blockNode.getTextContent().contains("final"))
                    method.isFinal=true;
                if(blockNode.getTextContent().contains("static"))
                    method.isSynchronized=true;
                NodeList functionDetails = blockNode.getChildNodes();
                for(int j=0;j<functionDetails.getLength();j++)
                {
                    Node functionNode=functionDetails.item(j);
                    if(functionNode.getNodeName().equals("specifier"))
                        method.specifier=functionNode.getTextContent();
                    if(functionNode.getNodeName().equals("type"))
                        method.type=functionNode.getTextContent();
                    if(functionNode.getNodeName().equals("name"))
                        method.name=functionNode.getTextContent();
                    if(functionNode.getNodeName().equals("parameter_list"))
                    {
                        NodeList parameterDetails=functionNode.getChildNodes();
                        for(int k=0;k<parameterDetails.getLength();k++)
                        {
                            Node parameterNode=parameterDetails.item(k);
                            if(parameterNode.getNodeName().equals("parameter"))
                            {
                                String[] details=new String[2];
                                details[0]=parameterNode.getTextContent().split(" ")[1];
                                details[1]=parameterNode.getTextContent().split(" ")[0];
                                method.parameter.add(details);
                                
                            }
                        }
                    }                    
                }
                elements.methodsList.add(method);
            } 
        }
    }

}