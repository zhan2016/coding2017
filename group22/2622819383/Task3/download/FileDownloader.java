import java.util.List;
import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.io.IOException;
public class FileDownloader {	
    
    String url;	

    public FileDownloader(String url) {
        this.url = url;	
    }

    public void execute() throws IOException {
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
        Connection conn = null;                				
        conn = new Connection(url);	
        int length = conn.getContentLength();
        String targetURL = "E:/" + url.substring(url.lastIndexOf("/") + 1);
        RandomAccessFile raf = new RandomAccessFile(targetURL, "rw");
        raf.setLength(length);
        raf.close();          
                             
        for (int i = 0; i < 3; i++) {
            int part = length / 3;
            int start = i * part;
            int end = (i == 2) ? length - 1 : (i + 1) * part - 1;
            DownloadThread t = new DownloadThread(conn, start, end, targetURL);
            t.start();                
        }        										
    }
    
    /*
    DownloadListener listener;   
    public void setListener(DownloadListener listener) {
        this.listener = listener;
    }
    
    public void setConnectionManager(ConnectionManager ucm){
        this.cm = ucm;
    }

    public DownloadListener getListener(){
        return this.listener;
    }
    */
    
    public static void main(String[] args) {
        try {
            new FileDownloader("http://images2015.cnblogs.com/blog/610238/201604/610238-20160421154632101-286208268.png").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
