package com.github.chaoswang.learning.java.downloader;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.github.chaoswang.learning.java.downloader.api.Connection;
import com.github.chaoswang.learning.java.downloader.api.ConnectionException;
import com.github.chaoswang.learning.java.downloader.api.ConnectionManager;
import com.github.chaoswang.learning.java.downloader.api.DownloadListener;

public class FileDownloader {
	String url;
	DownloadListener listener;
	ConnectionManager cm;
	int threadNum = 10;
	
	public FileDownloader(String _url, int threadNum) {
		this.url = _url;
		this.threadNum = threadNum;
	}
	
	public void execute(){
		// ������ʵ����Ĵ��룬 ע�⣺ ��Ҫ�ö��߳�ʵ������
		// ��������������������ӿ�, ����Ҫд�⼸���ӿڵ�ʵ�ִ���
		// (1) ConnectionManager , ���Դ�һ�����ӣ�ͨ��Connection���Զ�ȡ���е�һ�Σ���startPos, endPos��ָ����
		// (2) DownloadListener, �����Ƕ��߳����أ� ���������Ŀͻ��˲�֪��ʲôʱ���������������Ҫʵ�ֵ�����
		//     �̶߳�ִ�����Ժ� ����listener��notifiedFinished������ �����ͻ��˾����յ�֪ͨ��
		// �����ʵ��˼·��
		// 1. ��Ҫ����ConnectionManager��open���������ӣ� Ȼ��ͨ��Connection.getContentLength��������ļ��ĳ���
		// 2. ��������3���߳����أ�  ע��ÿ���߳���Ҫ�ȵ���ConnectionManager��open����
		// Ȼ�����read������ read�������ж�ȡ�ļ��Ŀ�ʼλ�úͽ���λ�õĲ����� ����ֵ��byte[]����
		// 3. ��byte����д�뵽�ļ���
		// 4. ���е��̶߳���������Ժ� ��Ҫ����listener��notifiedFinished����
		
		// ����Ĵ�����ʾ�����룬 Ҳ����˵ֻ��һ���̣߳� ����Ҫ����ɶ��̵߳ġ�
		// �ο���http://blog.csdn.net/yan8024/article/details/46474239
		
		long startTime = System.currentTimeMillis();
		//�ж������߳��Ƿ��������
		ArrayList<Thread> list = new ArrayList<Thread>();
		
		try{
			for(int i=1; i<=threadNum; i++){
				Connection conn = cm.open(url);
				Thread dt = new DownloadThread(conn, threadNum, i);
				dt.start();
				list.add(dt);
			}
		}catch(ConnectionException e){
			e.printStackTrace();
			return;
		}
		
		while(true){
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			if(!isAllFinished(list)){
				continue;
			}
			System.out.println("finished, cost time:" + (System.currentTimeMillis() - startTime));
			listener.notifyFinished();
			break;
		}
	}
	
	private boolean isAllFinished(ArrayList<Thread> list){
		for(Thread t : list){
			if(t.getState() != Thread.State.TERMINATED){
				return false;
			}
		}
		return true;
	}
	
	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}
	
	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}
	
	public DownloadListener getListener(){
		return this.listener;
	}
}
