package TestCollection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;







public class Struts {
    public static View runAction(String actionName, Map<String,String> parameters) {
    	
    	View view = new View();
        /*
		0. ��ȡ�����ļ�struts.xml
 		1. ����actionName�ҵ����Ӧ��class �� ����LoginAction,   ͨ������ʵ��������������
		��parameters�е����ݣ����ö����setter������ ����parameters�е������� 
		("name"="test" ,  "password"="1234") ,     	
		�Ǿ�Ӧ�õ��� setName��setPassword����
		2. ͨ��������ö����exectue ������ ����÷���ֵ������"success"
		3. ͨ�������ҵ����������getter���������� getMessage��,  
		ͨ�����������ã� ��ֵ�������γ�һ��HashMap , ���� {"message":  "��¼�ɹ�"} ,  
		�ŵ�View�����parameters
		�ŵ�View�����jsp�ֶ��С�
        */
    	
    	SAXReader reader = new SAXReader();
    	Document document = null;
		try {
			document = reader.read("src/struts.xml");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root= document.getRootElement();
		List<Element> list = root.elements("action");
		String className = null;
		Element newElement = null;
		for (Element element : list) {
			if(element.attribute("name").getValue().equals(actionName)){
				Attribute  attribute = 	element.attribute("class");
				newElement = element;
				className = attribute.getValue();
			}
		}
		Class clazz = null;
		try {
			clazz = Class.forName(className);
			Object obj = clazz.newInstance();
		for (String key : parameters.keySet())
		{
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if(method.getName().toLowerCase().equals(("set"+key).toLowerCase())){
					method.invoke(obj,parameters.get(key));
				}
			}
			
		}
		
		String value = (String) clazz.getMethod("execute").invoke(obj);
		List<Element> elements = newElement.elements();
		
		String message = "";
		String jsp = "";
		for (Element element : elements) {
			if(element.attribute("name").getValue().equals(value)){
				jsp = element.getText();
			}
		}
		if("success".equals(value)){
			message = "login successful";
		}else if("fail".equals(value)){
			message = "login failed,please check your user/pwd";
		}
		view.setJsp(jsp);
		Map<String,String> p = new HashMap<String, String>();
		p.put("message",message);
		view.setParameters(p);
		
		return view;
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return view;
    }    
}