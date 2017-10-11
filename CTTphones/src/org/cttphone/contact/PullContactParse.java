package org.cttphone.contact;

import java.io.InputStream;  
import java.io.StringWriter;  
import java.util.ArrayList;  
import java.util.List;  
  
import org.xmlpull.v1.XmlPullParser;  
import org.xmlpull.v1.XmlSerializer;  
  
import android.util.Xml; 
import android.widget.Toast;

public class PullContactParse implements ContactParse{
	@Override  
    public List<ContactPerson> parse(InputStream is) throws Exception {  
		       List<ContactPerson> persons = null;  
        ContactPerson person = null;  
          
//      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();  
//      XmlPullParser parser = factory.newPullParser();  
          
        XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例  
        parser.setInput(is, "UTF-8");               //设置输入流 并指明编码方式  
  
        int eventType = parser.getEventType();  
        while (eventType != XmlPullParser.END_DOCUMENT) {  
            switch (eventType) {  
            case XmlPullParser.START_DOCUMENT:  
            	persons = new ArrayList<ContactPerson>();  
                break;  
            case XmlPullParser.START_TAG:  
                if (parser.getName().equals("person")) {  
                    person = new ContactPerson();  
                } else if (parser.getName().equals("name")) {  
                    eventType = parser.next();  
                    person.setPersonName(parser.getText());  
                } else if (parser.getName().equals("PhoneNumber")) {  
                    eventType = parser.next();  
                    person.setPhoneNumber(parser.getText());  
                } else if (parser.getName().equals("VoipNumber")) {  
                    eventType = parser.next();  
                    person.setVoipNumber(parser.getText());  
                							}  
                else if (parser.getName().equals("HomeNumber")) {  
                			 eventType = parser.next();  
                			 person.setHomeNumber(parser.getText());  
                			                }  
                else if (parser.getName().equals("company")) {  
       			 						eventType = parser.next();  
       			 						person.setCompany(parser.getText());  
       			                			}  
                else if (parser.getName().equals("email")) {  
													eventType = parser.next();  
													person.setEmail(parser.getText());  
               								}  
                else if (parser.getName().equals("id")) {  
												eventType = parser.next();  
												person.setId(Integer.parseInt(parser.getText()));  
											}  
                break;  
            case XmlPullParser.END_TAG:  
                if (parser.getName().equals("person")) {  
                	   persons.add(person);  
                   person = null;      
                }  
                break;  
            }  
            eventType = parser.next();  
        }  
        return persons;  
    }  
      
    @Override  
    public String serialize(List<ContactPerson> persons) throws Exception {  
//      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();  
//      XmlSerializer serializer = factory.newSerializer();  
          
        XmlSerializer serializer = Xml.newSerializer(); //由android.util.Xml创建一个XmlSerializer实例  
        StringWriter writer = new StringWriter();  
        serializer.setOutput(writer);   //设置输出方向为writer  
        serializer.startDocument("UTF-8", true);  
        serializer.startTag("", "contacts");  
        for (ContactPerson person : persons) {  
            serializer.startTag("", "person");  
            serializer.attribute("", "id", person.getId() + "");  
              
            serializer.startTag("", "name");  
            serializer.text(person.getPersonName());  
            serializer.endTag("", "name");  
              
            serializer.startTag("", "PhoneNumber");  
            serializer.text(person.getPhoneNumber() + "");  
            serializer.endTag("", "PhoneNumber"); 
            
            serializer.startTag("", "VoipNumber");  
            serializer.text(person.getVoipNumber() + "");  
            serializer.endTag("", "VoipNumber"); 
            
            serializer.startTag("", "HomeNumber");  
            serializer.text(person.getHomeNumber() + "");  
            serializer.endTag("", "HomeNumber"); 
            
            serializer.startTag("", "company");  
            serializer.text(person.getCompany() + "");  
            serializer.endTag("", "company"); 
            
            serializer.startTag("", "email");  
            serializer.text(person.getEmail()+ "");  
            serializer.endTag("", "email"); 
              
              
            serializer.endTag("", "person");  
        }  
        serializer.endTag("", "contacts");  
        serializer.endDocument();  
        
        return writer.toString();  
    }  
	
}
