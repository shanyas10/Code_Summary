/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sanya
 */
public class Summarizer {
    
    public static void main(String args[])
    {
        TagDetect tag=new TagDetect();
        tag.detectDesignator();
        tag.detectTaxonomy();
        tag.detectJoiner();
        tag.detectPool();
        tag.detectFunctionPointer();
        tag.detectFunctionObject();
        tag.detectStateless();
        tag.detectCobol();
    }
}
