package com.github.chaoswang.learning.java.linkedlist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Stack;

import com.github.chaoswang.learning.java.array.ArrayUtil;

public class LinkedList<E> {
	//�����޸Ĳ�����Ҫά��size
	private int size = 0;
	//�漰����β�ڵ����ʱ��Ҫά��head��tailָ��
	private Node head = null;
	private Node tail = null;
	
	//��
	public void add(E element){
		Node tmp = new Node(element, null);
		if(tail == null){
			head = tmp;
		}else{
			tail.next = tmp;;
		}
		tail = tmp;
		size++;
	}
	
	public void add(int index, E element){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		if(index == size){
			add(element);
			return;
		}else if(index == 0){
			addFirst(element);
			return;
		}
		Node tmpBefore = getNode(index -1);
		Node tmpAfter = getNode(index);
		Node tmp = new Node(element, tmpAfter);
		tmpBefore.next = tmp;
		size++;
	}
	
	public void addFirst(E element){
		Node tmp = new Node(element, null);
		if(head != null){
			tmp.next = head;
		}else{
			tail = tmp;
		}
		head = tmp;
		size++;
	}
	
	public E removeFirst(){
		if(size <= 0){
			throw new NoSuchElementException();
		}
		Node tmp = head;
		head = head.next; 
		size--;
		if(size == 0){
			tail = null;
		}
		return (E)tmp.element;
	}
	
	//��
	public E remove(int index) {
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		if(index == 0){
			return removeFirst();
		}
		Node tmpBefore = this.getNode(index-1);
		Node tmp = this.getNode(index);
		Node tmpNext = this.getNode(index+1);
		tmpBefore.next = tmpNext;
		if(index == size - 1){
			tail = tmpBefore;
		}
		size--;
		if(size == 0){
			tail = null;
		}
		return (E)tmp.element;
	}
	
	//��
	public E get(int index){
		return (E)this.getNode(index).element;
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		if(head == null){
			return "[]" + ", head:"+head+", tail:"+tail;
		}
		StringBuffer sb = new StringBuffer("[");
		Node tmp = head;
		while(tmp != null){
			sb.append(tmp.element.toString());
			sb.append("(");
			if(tmp.next!=null){
				sb.append(tmp.next.element.toString());
			}
			sb.append(")");
			sb.append(",");
			
			tmp = tmp.next;
		}
		String returnStr = sb.toString();
		returnStr = returnStr.substring(0, returnStr.length()-1);
		return returnStr + "]" + ", head:"+head+", tail:"+tail;
	}
	
	private Node getNode(int index) {
		Node tmp = head;
		for(int i=0;i<index;i++){
			tmp = tmp.next;
		}
		return tmp;
	}
	
	/*����static�ͺ�ʵ������,��Ȼ���Թ���������ÿ��Node�ж��������һ��ָ����Χ������ã��˷�ʱ��Ϳռ�
	 * ����Oracle�ٷ���˵����
	 * Nested classes are divided into two categories: static and non-static. 
	 * Nested classes that are declared static are called static nested classes. 
	 * Non-static nested classes are called inner classes.
	 * �������Ͽ���һ������Ϊ��̬Ƕ���࣬һ������Ϊ�ڲ��ࡣ
	 * ������ĽǶȽ����������ģ�
	 * ʲô��Ƕ�ף�Ƕ�׾����Ҹ���û��ϵ���Լ�������ȫ�������ڣ������Ҿ������Ŀ���һ�£�������һ�����Լ�����TM�������
	 * ʲô���ڲ����ڲ������������һ���֣����˽��㣬��֪�����ȫ����û�����û���ҡ��������ڲ�����������ⲿ��������Ϊǰ��ģ�
	 */
	private static class Node{
		private Object element = null;
		private Node next = null;
		//˫������
//		private Node previos = null;

		public Node(Object element, Node next) {
			this.element = element;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return element.toString();
		}
	}
	
	/**
	 * �Ѹ���������
	 * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3
	 */
	public void reverse(){	
		Stack<Node> stackCache = new Stack<Node>();
		Node currentNode = head;
		for(int i=0;i<size;i++){
			Node tmp = currentNode;
			currentNode = currentNode.next;
			//�����ȶ�������Ȼ�޸���nextָ��֮���ٱ������γ���ѭ��?
//			tmp.next = null;
			stackCache.push(tmp);//��Ȼ�Ѷ���������˳���Ѿ��洢��ջ����
		}
		head = stackCache.pop();
		currentNode = head;
		while(!stackCache.isEmpty()){
			currentNode.next = stackCache.pop();
			currentNode = currentNode.next;
		}
		tail = currentNode;
		tail.next = null;
	}
	
	/**
	 * ɾ��һ���������ǰ�벿��
	 * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8
	 * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10
	 */
	public  void removeFirstHalf(){
		Node halfNodeBefore = getNode((size/2)-1);
		head = halfNodeBefore.next;
		halfNodeBefore.next = null;
	}
	
	/**
	 * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
	 * ���list = 2->5->7->8->10 ,remove(2,2)�Ժ��ֵΪ2->5->10
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length){
		Node nodePointer = getNode(i-1);
		Node nodeTargetBefore = getNode(i-1+length);
		nodePointer.next = nodeTargetBefore.next;
		nodeTargetBefore.next = null;
	}
	
	/**
	 * �ٶ���ǰ�����listB���������������е�����
	 * �ӵ�ǰ������ȡ����ЩlistB��ָ����Ԫ��
	 * ���統ǰ���� = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * ���صĽ��Ӧ����[101,301,401,601]  
	 * @param list
	 */
	public int[] getElements(LinkedList list){
		ArrayList<Integer> cache = new ArrayList<Integer>();
		for(int i=0;i<list.size();i++){
			int index = Integer.parseInt(list.get(i).toString());
			Node indexNode = getNode(index);
			cache.add(Integer.parseInt(indexNode.element.toString()));
		}
		return ArrayUtil.returnByIntArray(cache);
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * �ӵ�ǰ��������ɾ����listB�г��ֵ�Ԫ�� 
	 * ���統ǰ���� = 11->101->201->301->401->501->601->701
	 * listB = [11,201,501,701]
	 * ���صĽ��Ӧ����[101,301,401,601]  
	 * @param list
	 */
	public void subtract(LinkedList list){
		HashSet<String> set = new HashSet<String>();
		for(int i=0;i<list.size();i++){
			set.add(list.get(i).toString());
		}
		
		for(int i=0;i<size;i++){
			if(set.contains(getNode(i).element.toString())){
				remove(i);
			}
		}
	}
	
	/**
	 * ��֪��ǰ�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ɾ����������ֵ��ͬ�Ķ���Ԫ�أ�ʹ�ò���������Ա�������Ԫ�ص�ֵ������ͬ��
	 * ��һȺͬѧ��ӳ��removeDuplicateValues������removeRange���������������Ƚ�����ʵ�֣� ���Բ���
	 */
	public  void removeDuplicateValues(){
		
	}
	
	/**
	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
	 * ��дһ��Ч���㷨��ɾ����������ֵ����min��С��max��Ԫ�أ������д���������Ԫ�أ�
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max){
		
	}
	
	/**
	 * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
	 * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
	 * ���統ǰ���� = 11->101->201->301->401->501->601->701
	 * listB = [11,201,801,901]
	 * ���صĽ��Ӧ����[11,201]  
	 * @param list
	 */
	public LinkedList intersection(LinkedList list){
		HashSet<String> set = new HashSet<String>();
		for(int i=0;i<list.size();i++){
			set.add(list.get(i).toString());
		}
		LinkedList result = new LinkedList();
		for(int i=0;i<size;i++){
			if(set.contains(getNode(i).element.toString())){
				result.add(new Node(getNode(i).element, null));
			}
		}
		return result;
	}
}
