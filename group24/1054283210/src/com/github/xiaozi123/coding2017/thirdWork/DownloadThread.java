package com.github.xiaozi123.coding2017.thirdWork;

import java.io.IOException;

import com.github.xiaozi123.coding2017.thirdWork.api.Connection;


import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;



public class DownloadThread extends Thread{
	
	Connection conn;
	int startPos;
	int endPos;
	CyclicBarrier barrier;
	String localFile;
	public DownloadThread(Connection conn, int startPos, int endPos,String localFile,CyclicBarrier barrier){
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.localFile = localFile;
		this.barrier = barrier;
	}
	public void run(){
		try {
			System.out.println("Begin to read [" + startPos + "-" + endPos + "]");
			byte [] data = conn.read(startPos, endPos);
			System.out.println("����һ�������ȡ�ļ��Ķ���");
			RandomAccessFile file = new RandomAccessFile(localFile,"rw");
			file.seek(startPos);
			System.out.println("Ҫд������");
			file.write(data);
			file.close();
			conn.close();
			System.out.println(this.currentThread().getName()+"once over");
			barrier.await();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
