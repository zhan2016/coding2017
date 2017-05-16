//��ƪ����ο���ѧԱ2415980327


import java.io.InputStream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {
    
    public static Element parseXml(String fileName) {
        InputStream input = Struts.class.getResourceAsStream(fileName);
        SAXReader reader = new SAXReader();
        Document document = null;
        
        try {
            document = reader.read(input);
            Element struts = document.getRootElement(); 
            return struts;           
        } catch (DocumentException e) {
            e.printStackTrace();
        } 
        return null; 
    }
    
    public static View runAction(String actionName, Map<String,String> parameters) {

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

        4. ����struts.xml�е� <result> ����,�Լ�execute�ķ���ֵ��  ȷ����һ��jsp��  
        �ŵ�View�����jsp�ֶ��С�

        */
        Element struts = parseXml("struts.xml");
        List<Element> actions = struts.elements();
        List<Element> resultRefs = new ArrayList<>();
        String actionClass = "";
        for (Element element : actions)
            if (actionName.equals(element.attributeValue("name"))) {
                actionClass = element.attributeValue("class");
                resultRefs = element.elements();
                break;
            }
        
        Set<String> attributes = parameters.keySet();
        Iterator<String> it = attributes.iterator();
        try {    
            Object action = Class.forName(actionClass).newInstance();
            while (it.hasNext()) {
                String attribute = it.next();
                Method method = action.getClass().getDeclaredMethod("set"  
                                + attribute.substring(0, 1).toUpperCase()
                                + attribute.substring(1), String.class);
                method.invoke(action, parameters.get(attribute));
            }
            
            Method execute = action.getClass().getDeclaredMethod("execute");
            String result = (String)execute.invoke(execute);
            
            Map<String, String> actionParam = new HashMap(); 
            Method[] methods = action.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("get")) {
                    String methodName = method.getName();
                    String name = methodName.substring(3, 4).toUpperCase() + methodName.substring(4);
                    String value = (String)method.invoke(action);
                    actionParam.put(name, value);
                }
            }
        
        
            View view = new View();
            view.setParameters(actionParam);
            for (Element element : resultRefs) {
                if (result.equals(element.attributeValue("name"))) {
                    view.setJsp((String)element.getData());
                    break;
                }
            }
            return view;
            
        } catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    public static void main(String[] args) {
        String actionName = "login";
    	Element struts = parseXml("struts.xml");
    	List<Element> actions = struts.elements();
    	for (Element element : actions) {
			if (actionName.equals(element.attributeValue("name"))) {
				System.out.println(element.attributeValue("class"));
				
				for(Element element1:(List<Element>)element.elements()){
					System.out.println(element1.getData());
				}
			}
		}
    }
}





















