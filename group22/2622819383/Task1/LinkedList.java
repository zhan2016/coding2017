//����ο��ԡ����ݽṹ���㷨������
public class LinkedList implements List {
	
    private Node header;
    
    private Node trailer;
    
    private int theSize;
    
    public LinkedList() {
        header = new Node(null, null, null);
        trailer = new Node(null, header, null);
        header.succ = trailer;
        theSize = 0;
    }
    
    public void add(Object o) {
        add(size(), o);
    }

    public void add(int index , Object o) {
        if (index < 0 || theSize < index) throw new IndexOutOfBoundsException();

        Node p = header;
        while (0 < index--) p = p.succ();
        p.insertAsSucc(o);
        theSize++;        
    }

    public Object get(int index) {
        if (index < 0 || theSize <= index) throw new IndexOutOfBoundsException();

        Node p = header.succ();
        while (0 < index--) p = p.succ();
        return p.data();
    }

    public Object remove(int index) {
        if (0 < index || theSize <= index) throw new IndexOutOfBoundsException();

        Node p = header.succ();
        while (0 < index--) p = p.succ();
        Object removed = p.data();
        p.pred().succ = p.succ();
        p.succ().pred = p.pred();
        theSize--;
        return removed;
    }

    public int size() {
        return theSize;
    }

    public void addFirst(Object o) {
        header.insertAsSucc(o);
    }

    public void addLast(Object o) {
        trailer.insertAsPred(o);
    }

    public Object removeFirst() {
        return remove(0);
    }

    public Object removeLast() {
        return remove(theSize - 1);
    }

    public Iterator iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator {
        private Node current = header.succ();

        public boolean hasNext() {
            return current != trailer;
        }

        public Object next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Object item = current.data();
            current = current.succ();
            return item;
        }
    }

    private static class Node {
        //pred��succ�������ԣ�pred()��succ()����Node�ڵ�
        private Object data;
        private Node pred;
        private Node succ;

        public Node(Object d, Node p, Node s) {
            data = d;
            pred = p;
            succ = s;
        }

        public Object data() { 
            return data; 
        }

        public Node succ() { 
            return succ; 
        }

        public Node pred() { 
            return pred; 
        }

        //����ǰ���ڵ㣬���ز�����½ڵ�
        public Node insertAsPred(Object data) {
            Node p = new Node(data, pred, this);
            pred = pred().succ = p;
            return p;
        }

        //�����̽ڵ㣬���ز�����½ڵ�
        public Node insertAsSucc(Object data) {
            Node p = new Node(data, this, succ);
            succ = succ().pred = p;
            return p;
        }                           
    }

    /**
    * �Ѹ���������
    * ��������Ϊ 3->7->10 , ���ú��Ϊ  10->7->3
    */
    public void reverse(){		
        int times = theSize;
        int index = 0;
        while (0 < --times)
            add(index++, removeLast());
    }

    /**
    * ɾ��һ���������ǰ�벿��
    * ���磺list = 2->5->7->8 , ɾ���Ժ��ֵΪ 7->8
    * ���list = 2->5->7->8->10 ,ɾ���Ժ��ֵΪ7,8,10

    */
    public void removeFirstHalf(){
        int times = theSize / 2;
        while (0 < times--)
            removeFirst();
    }

    /**
    * �ӵ�i��Ԫ�ؿ�ʼ�� ɾ��length ��Ԫ�� �� ע��i��0��ʼ
    * @param i
    * @param length
    */
    public void remove(int i, int length){
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
    public int[] getElements(LinkedList list){
        Iterator itSelf = iterator();
        Iterator itList = list.iterator();
        int[] ret = new int[list.size()];
        
        int i = 0;    //list��Ԫ�ص�ֵ������ǰ�б���Ҫȡ��Ԫ�ص���
            lastI = 0;//��һ��ȡ��Ԫ�ص���
            moveTimes = 0;
            value = itSelf.next();
            index = 0;//Ҫ���ص�������Ԫ�ص���

        while (itList.hasNext()) {
            i = itList.next();
            if (theSize <= i) throw new IndexOutOfBoundsException();

            moveTimes = i - lastI;            
            while (0 < moveTimes--)
                value = itSelf.next();

            ret[index++] = value;
            lastI = i;
        }

        return ret;
    }

    /**
    * ��֪�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
    * �ӵ�ǰ��������ɾ����list�г��ֵ�Ԫ�� 

    * @param list
    */
    //������e��ȵ�Ԫ�ص��ȣ��������ʧ���򷵻�-1
    private int find(Object e) {
        Iterator it = iterator();
        int i = -1;    //Ҫ���ص�Ԫ�ص���
        Object value = null;
        
        while (it.hasNext()) {
            value = it.next();
            i++;
            if (value == e) return i;
            if (e < value) return -1;
        }

        return -1;
    }       

    public void subtract(LinkedList list){
        Iterator it = list.iterator();
        Object value = null;
        int i = -1;
        
        while (it.hasNext()) {
            value = it.next();
            i = find(value);
            
            //ɾȥ�ظ�Ԫ��
            while (0 <= i) {
                remove(i);
                i = find(value);
            }
        }
    }

    /**
    * ��֪��ǰ�����е�Ԫ����ֵ�����������У����Ե��������洢�ṹ��
    * ɾ����������ֵ��ͬ�Ķ���Ԫ�أ�ʹ�ò���������Ա�������Ԫ�ص�ֵ������ͬ��
    */
    public  void removeDuplicateValues(){
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
    //[low, min]U[max, end]


    public  void removeRange(int min, int max){
        //ɾȥ(i, j]
        int i = 0, j = 0;
        Iterator it = iterator();
        while (it.hasNext()) {
            Object value = it.next();
            if (value <= min) i++;
            if (value < max) j++;
            else break; //if(max <= value) break;
        }
        
        Node head = get(i);
        Node tail = get(j).succ();
        
        head.succ = tail;
        tail.pred = head;
        theSize -= (j - i);

    }

    /**
    * ���赱ǰ����Ͳ���listָ�����������Ԫ����ֵ�����������У�ͬһ���е�Ԫ��ֵ������ͬ��
    * ��Ҫ������������C����Ԫ��Ϊ��ǰ�����list��Ԫ�صĽ������ұ�C�е�Ԫ������ֵ������������
    * @param list
    */
    //����������A������B��Ԫ�صĺϼ�
    public  LinkedList intersection(LinkedList list){
        LinkedList ret = new LinkedList();
        Iterator it = iterator();
        Iterator itList = list.iterator();
        Object value1 = null, value2 = null;
        
        if (it.hasNext() && itList.hasNext()) {
            value1 = it.next();
            value2 = itList.next();
        }
        
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
