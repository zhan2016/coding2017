package com.coding.basic;

import java.util.Iterator;


public class LinkedList implements List {

	

	private Node head;

	private int size;

	

	private static class Node {

		Object data;

		Node next;

		

		public Node(Object data){

			this.data = data;

			this.next = null;

		}

	}

	

	public LinkedList(){

		this.head = new Node(null);

		this.size = 0;

	}

	

	public void add(Object o){

		Node newNode = new Node(o);

		Node pNode = head;

		while(pNode.next != null){

			pNode = pNode.next;

		}

		

		pNode.next = newNode;

		size++;

	}

	public void add(int index , Object o){

		checkIndex(index);

		

		Node newNode = new Node(o);

		Node node = new Node(null);

		Node pNode = head;

		for(int i = 0; i < index; i++){

			node = pNode;

			pNode = pNode.next;

		}

		

		node.next = newNode;

		newNode.next = pNode;

		size++;

	}

	public Object get(int index){

		checkIndex(index);

		

		Node pNode = head;

		for(int i = 0; i < index; i++){

			pNode = pNode.next;

		}

			

		return pNode.data;

	}

	public Object remove(int index){

		checkIndex(index);

		if(size == 0){

			return null;

		}

		

		Node node = new Node(null);

		Node pNode = head;

		for(int i = 0; i < index; i++){

			node = pNode;

			pNode = pNode.next;

		}

		node.next = pNode.next;

		size--;

		

		return pNode;

	}

	

	public int size(){

		Node pNode = head;

		while(pNode.next != null){

			pNode = pNode.next;

			size++;

		}

		return size;

	}

	

	public void addFirst(Object o){

		if(size == 0){

			head.data = o;

		}

		

		Node newNode = new Node(o);

		Node pNode = head;

		head = newNode;

		newNode.next = pNode.next;

		size++;

	}

	public void addLast(Object o){

		if(size == 0){

			head.data = o;

		}

		

		Node newNode = new Node(o);

		Node pNode = head;

		while(pNode.next != null){

			pNode = pNode.next;

		}

		pNode.next = newNode;

		newNode.next = null;

		size++;

	}

	public Object removeFirst(){

		if(size == 0){

			return null;

		}

		

		Node pNode = head;

		head = pNode.next;

		head.next = pNode.next.next;

		size--;

		return pNode;

	}

	public Object removeLast(){

		if(size == 0){

			return null;

		}

		

		Node pNode = head;

		Node node = new Node(null);

		while(pNode.next != null){

			node = pNode;

			pNode = pNode.next;

		}

		

		node.next = null;

		size--;

		return pNode;

	}

	public Iterator iterator(){

		return new LinkedListIterator();

	}

	

	//ע����һ������

	public class LinkedListIterator implements Iterator {

		private int position;

		

		@Override

		public boolean hasNext() {

			return position < size();

		}



		@Override

		public Object next() {

			if(hasNext()){

				return get(position++);

			}

			return null;

		}

		

	}

	

	public void checkIndex(int index){

		if(index < 0 || index >= size){

			throw new IndexOutOfBoundsException();

		}

	}

	

	/**

	 * �Ѹ���������

	 * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3

	 */

	public  void reverse(Node head){	

		if(head.next == null || head.next.next == null){

			return;

		}

		

		Node p = head.next;

		Node q = head.next.next;

		Node t = null;

		

		while(q.next != null){

			t = q.next;

			q.next = p;

			p = q;

			q = t;

		}

		

		head.next.next = null;//��������β��

		head.next = p;//��������ͷ��

	}

	

	/**

	 * ɾ��һ���������ǰ�벿��

	 * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8

	 * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10



	 */

	public  void removeFirstHalf(){

		if(size == 0 || head.next == null || head.next.next == null){

			return;

		}

		

		Node pNode = head;

		Node node = null;

		for(int i = 0; i < size/2; i++){

			node = pNode;

			pNode = pNode.next;

		}

		

		if(size %2 == 0){

			head.next = pNode;

		}else{

			head.next = node;

		}

	}

	

	/**

	 * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ

	 * @param i

	 * @param length

	 */

	public  void remove(int i, int length){

		if(size == 0 || head.next == null){

			return;

		}

		

		for(int k = i; k < i + length; k++){

			checkIndex(k);

			remove(k);

		}

	}

	/**

	 * �ٶ���ǰ�����list���������������е�����

	 * �ӵ�ǰ������ȡ����Щlist��ָ����Ԫ��

	 * ���統ǰ���� = 11->101->201->301->401->501->601->701

	 * list = 1->3->4->6

	 * ���صĽ��Ӧ����[101,301,401,601]  

	 * @param list

	 */

	public int[] getElements(LinkedList list){

		if(list.size == 0 || list == null){

			return new int[0];

		}

		

		int[] array = new int[list.size];

		int k = 0;

		for(int i = 0; i < list.size; i++){

			int index = (int) list.get(i);

			array[k] = (int) get(index);

		}

		return array;

	}

	

	/**

	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��

	 * �ӵ�ǰ��������ɾ����list�г��ֵ�Ԫ�� 



	 * @param list

	 */

	

	public  void subtract(LinkedList list,LinkedList oldList){

		if(oldList == null || oldList.size ==0 || list == null || list.size == 0){

			return;

		}

		

		for(int i = 0; i < oldList.size; i++){

			for(int j = 0; j < list.size; j++){

				if(list.get(j) == oldList.get(i)){

					oldList.remove(i);

				}

			}

		}

	}

	

	/**

	 * ��֪��ǰ�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��

	 * ɾ����������ֵ��ͬ�Ķ���Ԫ�أ�ʹ�ò���������Ա�������Ԫ�ص�ֵ������ͬ��

	 */

	public  void removeDuplicateValues(LinkedList list){

		if(list == null || list.size == 0){

			return;

		}

		

		int count = 0;

		Node pNode = head;

		while(pNode.next != null){

			pNode = pNode.next;

			count++;

			if(pNode.data == pNode.next.data){

				list.remove(count+1);

			}

		}

	}

	

	/**

	 * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��

	 * ��дһ��Ч���㷨��ɾ����������ֵ����min��С��max��Ԫ�أ������д���������Ԫ�أ�

	 * @param min

	 * @param max

	 */

	public  void removeRange(int min, int max){

		if(size == 0){

			return;

		}

		

		int count = 0;

		Node pNode = head;

		while(pNode.next != null){

			pNode = pNode.next;

			count++;

			if(min < (int)pNode.data || (int)pNode.data < max){

				remove(count);

			}

		}

	}

	

	/**

	 * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��

	 * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������

	 * @param list

	 */

	public  LinkedList intersection( LinkedList list){

		if(list.size == 0){

			return null;

		}

		

		LinkedList listC = new LinkedList();

		Node p = head;

		Node q = list.head;

		

		while(p.next != null){

			p = p.next;

			while(q.next !=null){

				q = q.next;

				if(p.data.equals(q.data)){

					listC.add(p);

				}

			}

		}

		return listC;

	}

}