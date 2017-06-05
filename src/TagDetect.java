    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    /**
     *
     * @author Sanya
     */
    public class TagDetect {

        EntityDetails entity=new EntityDetails("Summarizer.");

        public boolean detectDesignator()
        {
            boolean flag=false;
            if(entity.classList.size()!=0)
            {
                //System.err.println("Entered");
                Class _class=entity.classList.get(0);
                if(_class.isEmpty==true&&_class.extend.size()==0&&_class.implement.size()==0)
                    flag=true; 
                else if(_class.isEmpty==false)
                    System.err.println("Not Empty"); 
            }
            if(flag==false)
                System.out.println("Designator not present");
            else
                System.out.println("Designator");
            return flag;
        }
        public boolean detectTaxonomy()
        {
            boolean flag=false;
            if(entity.classList.size()!=0)
            {
                Class _class=entity.classList.get(0);
                if(_class.isEmpty==true&&_class.extend.size()==1)
                    flag=true;
            }
            if(flag==false)
                System.out.println("Taxonomy Not Present");
            else
                System.out.println("Taxonomy");
            return flag;
        }
        public boolean detectJoiner()
        {
            boolean flag=false;
            if(entity.classList.size()!=0)
            {
                Class _class=entity.classList.get(0);
                if(_class.isEmpty==true&&_class.extend.size()>=2)
                    flag=true;
            }
            if(flag==false)
                System.out.println("Joiner Not Present");
            else
                System.out.println("Joiner");
            return flag;
        }
        public boolean detectPool()
        {
            boolean flag=false;
            if(entity.classList.size()!=0)
            {
                if(entity.methodsList.size()==0)
                {
                    if(entity.variablesList.size()>0)
                    {
                        for(int i=0;i<entity.variablesList.size();i++)
                        {
                            Variables variable=entity.variablesList.get(i);
                            if(variable.isStatic!=true||variable.isFinal!=true)
                            {
                                flag=false;
                                break;
                            }
                            flag=true;
                        }
                    }
                }

            }
            if(flag==false)
                System.out.println("Pool Not Present");
            else
                System.out.println("Pool");
            return flag;
        }
        public boolean detectFunctionPointer()
        {
            boolean flag=false;
            int count=0;
            if(entity.classList.size()!=0)
            {
                if(entity.variablesList.size()==0)
                {
                    if(entity.methodsList.size()==1)
                    {
                        Methods method=entity.methodsList.get(0);
                        if(method.specifier.equals("public") && (method.isStatic==false))
                            flag=true;
                    }
                    /*if(entity.methodsList.size()>0)
                    {
                        for(int i=0;i<entity.methodsList.size();i++)
                        {
                            Methods method=entity.methodsList.get(i);
                            if(method.specifier.equals("public") && (method.isStatic==false))
                                count++;
                            if(count>1)
                            {
                                flag=false;
                                break;
                            }
                            flag=true;
                        }
                        if(count==0)
                            flag=false;

                    }*/
                }

            }
            if(flag==false)
                System.out.println("FP Not Present");
            else
                System.out.println("FP");
            return flag;
        }
        public boolean detectFunctionObject()
        {
            boolean flag1=false,flag2=false;
            int count=0;
            if(entity.classList.size()!=0)
            {
                if(entity.methodsList.size()==1)
                {
                    Methods method=entity.methodsList.get(0);
                    if(method.specifier.equals("public") && (method.isStatic==false))
                        flag1=true;
                }
                /*if(entity.methodsList.size()>0)
                {
                    for(int i=0;i<entity.methodsList.size();i++)
                    {
                        Methods method=entity.methodsList.get(i);
                        if(method.specifier.equals("public") && (method.isStatic==false))
                            count++;
                        if(count>1)
                        {
                            flag=false;
                            break;
                        }
                        flag=true;
                    }
                    if(count==0)
                        flag=false;
                }*/
                if(entity.variablesList.size()>0)
                {
                    for(int i=0;i<entity.variablesList.size();i++)
                    {
                        Variables variable=entity.variablesList.get(i);
                        if(variable.isStatic==true)
                        {
                            flag2=false;
                            break;
                        }
                        flag2=true;
                    }
                }
                else
                    flag2=false;
            }
            if(flag1==false||flag2==false)
                System.out.println("FO Not Present");
            else
                System.out.println("FO");
            return flag1&flag2;
        }
        public boolean detectCobol()
        {
            boolean flag1=false,flag2=false;
            if(entity.classList.size()!=0)
            {
                if(entity.methodsList.size()==1)
                {
                    Methods method=entity.methodsList.get(0);
                    if(method.isStatic==true)
                        flag1=true;
                }   
                if(entity.variablesList.size()>0)
                {
                    for(int i=0;i<entity.variablesList.size();i++)
                    {
                        Variables variable=entity.variablesList.get(i);
                        if(variable.isStatic==false)
                        {
                            flag2=false;
                            break;
                        }
                        flag2=true;
                    }
                }
                else
                    flag2=false;
            }
            if(flag1==false||flag2==false)
                System.out.println("Cobol Not Present");
            else
                System.out.println("Cobol");
            return flag1&flag2;
        }
        public boolean detectStateless()
        {
            boolean flag=true;
            if(entity.classList.size()!=0)
            {
                if(entity.variablesList.size()>0)
                {
                    for(int i=0;i<entity.variablesList.size();i++)
                    {
                        Variables variable=entity.variablesList.get(i);
                        if(variable.isStatic!=true||variable.isFinal!=true)
                        {
                            flag=false;
                            break;
                        }
                    }
                }

            }
            if(flag==false)
                System.out.println("Stateless Not Present");
            else
                System.out.println("Stateless");
            return flag;
        }
    }