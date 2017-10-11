package org.cttphone.contact;

import java.io.InputStream;
import java.util.List;

public interface ContactParse {
	/** 
     * ���������� �õ�ContactPerson���󼯺� 
     * @param is 
     * @return 
     * @throws Exception 
     */  
    public List<ContactPerson> parse(InputStream is) throws Exception;  
      
    /** 
     * ���л�Contact���󼯺� �õ�XML��ʽ���ַ��� 
     * @param ContactPerson 
     * @return 
     * @throws Exception 
     */  
    public String serialize(List<ContactPerson> persons) throws Exception; 

}