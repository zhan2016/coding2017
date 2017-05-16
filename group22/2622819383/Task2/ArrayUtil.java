public class ArrayUtil {

    /**
    * ����һ����������a , �Ը������ֵ�����û�
    ���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
    ���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
    * @param origin
    * @return
    */
    public void reverseArray(int[] origin){
        int lo = 0;
        int hi = origin.length - 1;
        while (lo < hi)
            swap(origin, lo++, hi--);
    }
    private void swap(int[] array, int lo, int hi) {
        int temp = array[lo];
        array[lo] = array[hi];
        array[hi] = temp;
    }

    /**
    * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
    * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
    * {1,3,4,5,6,6,5,4,7,6,7,5}  
    * @param oldArray
    * @return
    */

    public int[] removeZero(int[] oldArray){
        int[] ret = new int[oldArray.length];
        int i = 0;
        for (int j = 0; j < oldArray.length; j++) {
            if (oldArray[j] != 0) 
            ret[i++] = oldArray[j];
        }
        int[] old = ret;
        ret = new int[i];
        for (int j = 0; j < i; j++)
            ret[j] = old[j];

        return ret;
    }

    /**
    * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
    * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
    * @param array1
    * @param array2
    * @return
    */

    public int[] merge(int[] array1, int[] array2){
        int m = array1.length;  //array1 ���� m ���� i
        int n = array2.length;  //array2 ���� n ���� j
        int[] ret = new int[m + n]; // ret ���� m+n ���� k
        int k = 0;
        for (int i = 0, j = 0; i < m || j < n; ) {
            if (i < m && (n <= j || array1[i] < array2[j])) ret[k++] = array1[i++];
            if (j < n && (m <= i || array2[j] < array1[i])) ret[k++] = array2[j++];
            if (i < m && j < n && array1[i] == array2[j]) {
                ret[k++] = array1[i++];
                j++;
            }
        }
        int[] old = ret;
        ret = new int[k];
        for (int i = 0; i < k; i++)
            ret[i] = old[i];

        return ret;
    }
    /**
    * ��һ���Ѿ��������ݵ����� oldArray������������չ�� ��չ��������ݴ�СΪoldArray.length + size
    * ע�⣬�������Ԫ��������������Ҫ����
    * ���� oldArray = [2,3,6] , size = 3,�򷵻ص�������Ϊ
    * [2,3,6,0,0,0]
    * @param oldArray
    * @param size
    * @return
    */
    public int[] grow(int [] oldArray,  int size){
        int[] ret = new int[oldArray.length + size];

        for (int i = 0; i < oldArray.length; i++)
            ret[i] = oldArray[i];        

        return ret;
    }

    /**
    * 쳲���������Ϊ��1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������
    * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13]
    * max = 1, �򷵻ؿ����� []
    * @param max
    * @return
    */
    public int[] fibonacci(int max){
        int[] ret = new int[max / 2 + 10];
        int f = 1, g = 0, i = 0;
        for ( ; f < max; i++) {
            ret[i] = f;
            f = g + f;
            g = f - g;
        }
        int[] old = ret;
        ret = new int[i];
        for (int j = 0; j < i; j++)
            ret[j] = old[j];
        return ret;
    }

    /**
    * ����С�ڸ������ֵmax��������������
    * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
    * @param max
    * @return
    */
    public int[] getPrimes(int max){
        int[] ret = new int[max / 3 + 10]; //��ʡ��ʼ�����ٵĿռ䣻ret ���� i
        int i = 0;                //i������ret���������
        //�˻����: max < 5�����
        if (2 < max) { ret[i++] = 2; }                    
        if (3 < max) { ret[i++] = 3; }
        if (5 < max) { ret[i++] = 5; }
        if (7 < max) { 
            //����ֻ��Ϊ6k+1��6k+5������
            //k����Сֵ��1
            //�ж�k�����ֵ��6k + 1 <= max����6k + 5��max�ıȽ���Ҫ�Լ�ȷ��            
            int k = 1;
            while (6 * k + 1 <= max) {
                int m = 6 * k + 1;
                int n = 6 * k + 5;
                if(isPrime(ret, m))  ret[i++] = m;
                if (max < n) break;
                if (isPrime(ret, n)) ret[i++] = n;
                k++;
            }            
        }//O(n/3) * O((n^0.5) / 3)
        int[] old = ret;
        ret = new int[i];
        for (int j = 0; j < i; j++)
            ret[j] = old[j];
        return ret;
        }

        private boolean isPrime(int[] primeArray, int target) {
        //O((n^0.5) / 3)
            boolean isPrime = true;
            int min = (int)Math.sqrt(target);
            for (int i = 0; primeArray[i] <= min; i++) {
            if (target % primeArray[i] == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;       
    }

    /**
    * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
    * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
    * @param max
    * @return
    */
    public int[] getPerfectNumbers(int max){
        int[] ret = new int[48];
        int[] supportArr = getPrimes(max);
        int j = 0;
        for (int i = 2; i < max; i++) {
            if (i % 2 != 0) continue;
            if (isPerfectNumber(i, supportArr)) ret[j++] = i;
        }
        int[] old = ret;
        ret = new int[j];
        for (int i = 0; i < j; i++)
            ret[i] = old[i];
        return ret;
    }
    private boolean isPerfectNumber(int target, int[] supportArr) {
        //���ù�ʽ��perfectNum = ( 2^p-1 ) * 2^(p-1) = ( 2^(count+1)-1 ) * ( 2^count )
        //����p=count+1��������2^p-1=2^(count+1)-1Ҳ������
        //count: ����������2�ĸ���    
        boolean isPerfectNum = true;
        int count = amountOfTwo(target);            

        int test0 = (int)Math.pow(2, count);
        int test1 = count + 1;
        int test2 = test0 * 2 - 1;

        if (count == 0) isPerfectNum = false;
        else if (!isPrime(supportArr, test1)) isPerfectNum = false;
        else if (!isPrime(supportArr, test2)) isPerfectNum = false;
        else if (test0 * test2 != target) isPerfectNum = false;

        return isPerfectNum;
    }
    private int amountOfTwo(int num) {
        int count = 0;
        while (num % 2 == 0) {
            num /= 2;
            count++;
        }
        return count;
    }


    /**
    * ��seperator ������ array����������
    * ����array= [3,8,9], seperator = "-"
    * �򷵻�ֵΪ"3-8-9"
    * @param array
    * @param s
    * @return
    */
    public String join(int[] array, String seperator){
        String ret = "";
        if (array.length < 1) return ret;
        ret += array[0];
        for (int i = 1; i < array.length; i++)
            ret += seperator + array[i];
        return ret;
    }

    public static void main(String[] args) {
        ArrayUtil au = new ArrayUtil();

        int[] arr0 = au.fibonacci(50000000);
        for (int i = 0; i < arr0.length; i++)
        System.out.print(arr0[i] + " ");
        //      arr1 = {3,};
        //System.out.println(au.join(arr0, "-"));
        //System.out.println(au.join(arr1, "-"));

    }

}


































