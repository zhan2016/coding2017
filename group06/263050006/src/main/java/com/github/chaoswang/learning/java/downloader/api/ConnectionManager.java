package com.github.chaoswang.learning.java.downloader.api;

public interface ConnectionManager {
	/**
	 * ����һ��url , ��һ������
	 * @param url
	 * @return
	 */
	public Connection open(String url) throws ConnectionException;	
}
