package com.johnChnia.coderising2017.download;

import com.johnChnia.coderising2017.download.api.Connection;

import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

public class DownloadThread extends Thread {
    Connection conn;
    int startPos;
    int endPos;
    CyclicBarrier barrier;
    String localFile;

    public DownloadThread(Connection conn, int startPos, int endPos, String localFile, CyclicBarrier barrier) {
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
        this.localFile = localFile;
        this.barrier = barrier;
    }

    public void run() {
        try {
            System.out.println("Begin to read [" + startPos + "-" + endPos + "]");
            byte[] data = conn.read(startPos, endPos);
            System.out.println("创建一个随机读取文件的对象");
            RandomAccessFile file = new RandomAccessFile(localFile, "rw");
            file.seek(startPos);
            System.out.println("要写数据了");
            file.write(data);
            file.close();
            conn.close();
            System.out.println(this.currentThread().getName() + "once over");
            barrier.await();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
