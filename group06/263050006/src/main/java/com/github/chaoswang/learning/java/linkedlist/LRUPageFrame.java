package com.github.chaoswang.learning.java.linkedlist;

public class LRUPageFrame {

	private static class Node {

		Node prev;
		Node next;
		int pageNum;

		Node() {
		}
	}

	private int capacity;

	private int currentSize;
	private Node first;// ����ͷ
	private Node last;// ����β

	public LRUPageFrame(int capacity) {
		this.currentSize = 0;
		this.capacity = capacity;

	}

	/**
	 * ��ȡ�����ж���
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {

		Node node = find(pageNum);
		// �ڸö����д��ڣ� ���ᵽ����ͷ
		if (node != null) {

			moveExistingNodeToHead(node);

		} else {

			node = new Node();
			node.pageNum = pageNum;

			// ���������Ƿ��Ѿ�������С.
			if (currentSize >= capacity) {
				removeLast();

			}

			addNewNodetoHead(node);

		}
	}

	private void addNewNodetoHead(Node node) {

		if (isEmpty()) {

			node.prev = null;
			node.next = null;
			first = node;
			last = node;

		} else {
			node.prev = null;
			node.next = first;
			first.prev = node;
			first = node;
		}
		this.currentSize++;
	}

	private Node find(int data) {

		Node node = first;
		while (node != null) {
			if (node.pageNum == data) {
				return node;
			}
			node = node.next;
		}
		return null;

	}

	/**
	 * ɾ������β���ڵ� ��ʾ ɾ������ʹ�õĻ������
	 */
	private void removeLast() {
		Node prev = last.prev;
		prev.next = null;
		last.prev = null;
		last = prev;
		this.currentSize--;
	}

	/**
	 * �ƶ�������ͷ����ʾ����ڵ�������ʹ�ù���
	 * 
	 * @param node
	 */
	private void moveExistingNodeToHead(Node node) {

		if (node == first) {

			return;
		} else if (node == last) {
			// ��ǰ�ڵ�������β�� ��Ҫ�ŵ�����ͷ
			Node prevNode = node.prev;
			prevNode.next = null;
			last.prev = null;
			last = prevNode;

		} else {
			// node ��������м䣬 ��node ��ǰ��ڵ���������
			Node prevNode = node.prev;
			prevNode.next = node.next;

			Node nextNode = node.next;
			nextNode.prev = prevNode;

		}

		node.prev = null;
		node.next = first;
		first.prev = node;
		first = node;

	}

	private boolean isEmpty() {
		return (first == null) && (last == null);
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while (node != null) {
			buffer.append(node.pageNum);

			node = node.next;
			if (node != null) {
				buffer.append(",");
			}
		}
		return buffer.toString();
	}

}
