package com.github.chaoswang.learning.java.linkedlist;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.github.chaoswang.learning.java.linkedlist.LinkedList;

public class LinkedListTest {

	@Test
	public void testAdd(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		System.out.println(myList);
		Assert.assertEquals(3, myList.size());
		myList.add("4");
		System.out.println(myList);
		Assert.assertEquals(4, myList.size());
		String str = myList.get(2);
		Assert.assertEquals("3", str);
		System.out.println(myList);
	}
	
	@Test
	public void testInsert(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("2");
		myList.add("4");
		System.out.println(myList);
		myList.add(0,"1");
		System.out.println(myList);
		String str = myList.get(2);
		Assert.assertEquals("4", str);
		myList.add(2,"3");
		str = myList.get(2);
		System.out.println(myList);
		Assert.assertEquals("3", str);
	}
	
	@Test
	public void testAddFirst(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("2");
		myList.add("3");
		myList.add("4");
		System.out.println(myList);
		Assert.assertEquals("2", myList.get(0));
		myList.addFirst("1");
		Assert.assertEquals("1", myList.get(0));
		System.out.println(myList);
	}
	
	@Test
	public void testRemoveFirst(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		String str = myList.removeFirst();
		Assert.assertEquals("1", str);
		Assert.assertEquals("2", myList.get(0));
		System.out.println(myList);
	}
	
	@Test
	public void testRemove(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		String str = myList.remove(2);
		System.out.println(myList);
		Assert.assertEquals("3", str);
		str = myList.get(2);
		Assert.assertEquals("4", str);
		Assert.assertEquals(3, myList.size());
		System.out.println(myList);
	}
	
	@Test
	public void testRemoveAll(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("1");
		myList.add("2");
		System.out.println(myList);
		String str = myList.removeFirst();
		System.out.println(myList);
		Assert.assertEquals("1", str);
		str = myList.removeFirst();
		Assert.assertEquals("2", str);
		Assert.assertEquals(0, myList.size());
		System.out.println(myList);
	}
	
	/**
	 * �Ѹ���������
	 * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3
	 */
	@Test
	public void testReverse(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("1");
		myList.add("3");
		myList.add("5");
		myList.add("7");
		myList.add("9");
		System.out.println(myList);
		myList.reverse();
		System.out.println(myList);
	}
	
	/**
	 * ɾ��һ���������ǰ�벿��
	 * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8
	 * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10
	 */
	@Test
	public void testRemoveFirstHalf(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("2");
		myList.add("5");
		myList.add("7");
		myList.add("8");
		myList.add("10");
		System.out.println(myList);
		myList.removeFirstHalf();
		System.out.println(myList);
	}
	
	/**
	 * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
	 * ���list = 2->5->7->8->10 ,remove(2,2)�Ժ��ֵΪ2->5->10
	 * @param i
	 * @param length
	 */
	@Test
	public void testRemoveBySpecificLength(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("2");
		myList.add("5");
		myList.add("7");
		myList.add("8");
		myList.add("10");
		System.out.println(myList);
		myList.remove(2,2);
		System.out.println(myList);
	}
	
	/**
	 * �ٶ���ǰ�����listB���������������е�����
	 * �ӵ�ǰ������ȡ����ЩlistB��ָ����Ԫ��
	 * ���統ǰ���� = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * ���صĽ��Ӧ����[101,301,401,601]  
	 * @param list
	 */
	@Test
	public void testGetElements(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("11");
		myList.add("101");
		myList.add("201");
		myList.add("301");
		myList.add("401");
		myList.add("501");
		myList.add("601");
		myList.add("701");
		System.out.println(myList);
		LinkedList testList = new LinkedList();
		testList.add(1);
		testList.add(3);
		testList.add(4);
		testList.add(6);
		System.out.println(Arrays.toString(myList.getElements(testList)));
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * �ӵ�ǰ��������ɾ����listB�г��ֵ�Ԫ�� 
	 * ���統ǰ���� = 11->101->201->301->401->501->601->701
	 * listB = [11,201,501,701]
	 * ���صĽ��Ӧ����[101,301,401,601]  
	 * @param list
	 */
	@Test
	public void subtract(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("11");
		myList.add("101");
		myList.add("201");
		myList.add("301");
		myList.add("401");
		myList.add("501");
		myList.add("601");
		myList.add("701");
		System.out.println(myList);
		LinkedList testList = new LinkedList();
		testList.add(11);
		testList.add(201);
		testList.add(501);
		testList.add(701);
		myList.subtract(testList);
		System.out.println(myList);
	}
	
	/**
	 * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
	 * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
	 * ���統ǰ���� = 11->101->201->301->401->501->601->701
	 * listB = [11,201,801,901]
	 * ���صĽ��Ӧ����[11,201]  
	 * @param list
	 */
	@Test
	public void intersection(){
		LinkedList<String> myList = new LinkedList<String>();
		myList.add("11");
		myList.add("101");
		myList.add("201");
		myList.add("301");
		myList.add("401");
		myList.add("501");
		myList.add("601");
		myList.add("701");
		System.out.println(myList);
		LinkedList testList = new LinkedList();
		testList.add(11);
		testList.add(201);
		testList.add(801);
		testList.add(901);
		System.out.println(myList.intersection(testList));
	}
}
