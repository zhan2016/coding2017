public class LinkedList {
    /**
    * �Ѹ���������
    * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3
    */
    public void reverse() {		
        int times = theSize - 1;  //��һ��Ԫ����Ȼ�ƶ����������ֻ�����theSize - 1�β���        
        int index = 0;
        while (0 < times) {
            add(index++, removeLast());
            times--;
        }
    }

    /**
    * ɾ��һ���������ǰ�벿��
    * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8
    * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10

    */
    public void removeFirstHalf() {
        int times = theSize / 2;
        while (0 < times--)
            removeFirst();
    }

    /**
    * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
    * @param i
    * @param length
    */
    public void remove(int i, int length) {
        Node head = get(i).pred();   //ɾ��(head, tail)֮��Ԫ��  ɾ��[i, i + length - 1]֮��Ԫ��
        Node tail = get(i + length - 1).succ();

        head.succ = tail;
        tail.pred = head;
        theSize -= length;
    }
    /**
    * �ٶ���ǰ�����list���������������е�����
    * �ӵ�ǰ������ȡ����Щlist��ָ����Ԫ��
    * ���統ǰ���� = 11->101->201->301->401->501->601->701
    * list = 1->3->4->6
    * ���صĽ��Ӧ����[101,301,401,601]  
    * @param list
    */
    public int[] getElements(LinkedList list) {
        Iterator it = iterator();        
        int[] ret = new int[list.size()];
        int start = -1;
        int value = 0;
        int i = 0;   //����ret������
        
        for (Integer num : list) {
            while (start < num && it.hasNext()) {
                value = it.next();
                start++;
            }
            ret[i++] = value;
        }
        return ret;
    }
    
    /**
    * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
    * �ӵ�ǰ��������ɾ����list�г��ֵ�Ԫ�� 

    * @param list
    */ 
    public void subtract(LinkedList list) {
        Object current = null;
        for (Object e : list) {
            Iterator it = iterator();
            while (it.hasNext()) {
                current = it.next();
                if (current.compareTo(e) == 0)
                    it.remove();
                if (current.compareTo(e) > 0)
                    break;
            }
        }
    }

    /**
    * ��֪��ǰ�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
    * ɾ����������ֵ��ͬ�Ķ���Ԫ�أ�ʹ�ò���������Ա�������Ԫ�ص�ֵ������ͬ��
    */
    public  void removeDuplicateValues() {
        Node current = header.succ();
        Node next = current;
        int removedNum = 0;
        
        while ((next = next.succ()) != trailer) {
            if (current.data() == next.data()) {                
                removedNum++;
            } else {
                current.succ = next;
                next.pred = current;
                current = next;                
            }
        }
        theSize -= removedNum;
    }

    /**
    * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
    * ��дһ��Ч���㷨��ɾ����������ֵ����min��С��max��Ԫ�أ������д���������Ԫ�أ�
    * @param min
    * @param max
    */


    public  void removeRange(int min, int max) {
        //˫����ɾȥ(p, q)��Ľڵ�
        Node p = header;
        Node q = null;
        int removedNum = 0; //Ҫɾȥ�ڵ����Ŀ
        while ((p = p.succ()) != trailer && (p.data() <= min))
            ��
        p = p.prev();
        q = p;
        while ((q = q.succ()) != trailer && (q.data() < max))
            removedNum++;
        p.succ = q;
        q.prev = p;
        theSize -= removedNum;
        
        
        
        /*
        //ɾȥ(i, j]
        int i = 0, j = 0;
        Iterator it = iterator();
        while (it.hasNext()) {
            int value = it.next();
            if (value <= min) i++;
            if (value < max) j++;
            else break; //if(max <= value) break;
        }
        
        Node head = get(i);
        Node tail = get(j).succ();
        
        head.succ = tail;
        tail.pred = head;
        theSize -= (j - i);
        */

    }

    /**
    * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
    * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
    * @param list
    */
    //����������A������B��Ԫ�صĺϼ�
    public  LinkedList intersection(LinkedList list) {
        LinkedList ret = new LinkedList();
        Iterator it = iterator();
        Iterator itList = list.iterator();
        Object value1 = null, value2 = null;
        
        if (it.hasNext() && itList.hasNext()) {
            value1 = it.next();
            value2 = itList.next();
        }
        //��null��Ϊ������ϵı�־
        //ѭ��������־������һ��LinkedList�Ѿ��������
        while (value1 != null && value2 != null) {
            if (value1 < value2)      value1 = it.hasNext() ? it.next() : null;
            else if (value2 < value1) value2 = itList.hasNext() ? itList.next() : null;            
            else {
                ret.add(value1);
                value1 = it.hasNext() ? it.next() : null;
                value2 = itList.hasNext() ? itList.next() : null;
            }       
        }
        return ret;
    }
}
