package TestCollection;
import java.util.ArrayList;


public class ArrayUtil {
	
	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		 
			for(int i=0,j=origin.length-1;i<j;i++,j--){
				int r=origin[i];
				origin[i]=origin[j];
				origin[j]=r;
				}
  
		}
 
	
	/**
	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */ 
	
	public int[] removeZero(int[] oldArray){
		int size=0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				 size++;
			} 
		}
		int  []arr=new int[size];
		
		int j=0;
		 
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				arr[j++]=oldArray[i]; 
			} 
		}
		 
		
		return arr;
	}
	
	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int m=0;
		 for(int i=0;i<array1.length;i++){
			 for(int j=0;j<array2.length-m;j++){
				 if(array1[i]==array2[j]){
					 array2[j]=array2[array2.length-m-1];
					 m++;
				 }
			 }
		 }
		 int length=array1.length+array2.length-m;
		 System.out.println(length);
		 int array[]=new int [length];
		 int x=0;
		 for(int i=0;i<array1.length;i++){
		array[x++]=array1[i];	 
		 }
		 for(int j=0;j<array2.length-m;j++){
			 array[x+j]=array2[j];
		 }
		 int swap=0;
		 for( int i = 0; i < array.length; ++i )
			{
				for(int j = 1; j < array.length-i; ++j )
				{
					if( array[j-1] > array[j] )		// ���˳����ˣ��ͽ���һ��
					{
						swap = array[j];
						array[j] = array[j-1];
						array[j-1] = swap;
					}
				}
			}
		 
		return  array;
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
		int newArray[]=new int [oldArray.length+size];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
	}
	
	
	/**
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������
	 * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13]
	 * max = 1, �򷵻ؿ����� []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		 int[] a=new int [100];
		 a[0]=1;
		 a[1]=1;
		 int i =2;
		 for(;i<a.length;i++){
			 if(a[i-1]<max&&a[i-1]!=0){//Ҫ������һ��������֤i����ʵ�������1
			 a[i]=a[i-1]+a[i-2];
			 }
			 else{
				 break;
			 }
			} 
		 int[] b = new int[i-1];//�� a�е�ֵ��b,i-1��Ϊ�˰Ѷ����Ǹ�Ԫ��ɾ��
		 for (int j = 0; j <i-1; j++) {
			 b[j] = a[j];
		 }
		 return b;
		 
		 
		 
//		 for(int i=0;i<a.length;i++){
//			 if(a[i]<max&&max<a[i+1]){
//				 int arr[]=new int[i+1];
//				 for(int j=0;j<=i;j++){
//					 arr[j]=a[j];
//				 }
//				 return arr;
//			 }
//		 }
		 
		 
		 
		 
		 
/*		int index = 0;
		for (int i = 0; i < a.length; i++) {
			if(a[i]<max){
				index++;
			}else{
				int [] arr = new int[index];
				
				for (int j = 0; j < index; j++) {
					arr[j] = a[j];
					
				}
				return arr;
				
			}
		}*/
		 
	}
	
	/**
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		ArrayList<Integer>list=new ArrayList<Integer>();
		   for (int i = 2; i <max; i++) {  
	            list.add(i);  
	        }  
	        for (int j = 0; j < list.size() && list.get(j) * list.get(j) <max; j++) {  
	            for (int k = 0; k < list.size(); k++) {  
	                if ((list.get(k) != list.get(j))  
	                        && list.get(k) % list.get(j) == 0) {  
	                    list.remove(k);  
	                }  
	            }  
	        }  
		 
		Object[]arr= list.toArray( );
		int[]arr1=new int[arr.length];
		for(int i=0;i<arr.length;i++){
			arr1[i]=(int) arr[i];
		 
		}
		
		return arr1;
	}
	
	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		ArrayList<Integer>list1=new ArrayList<Integer>();
		ArrayList<Integer>list=new ArrayList<Integer>();
		for(int i=1;i<max;i++){
			list .removeAll(list);
			for(int j=1;j<=Math.sqrt(i);j++){
				
				if(i%j==0){
					 list.add(j);
					 list.add(i/j);
					}
			}
			 int count=0;
			  for(int k=0;i<list.size();k++){
				 
				count+=list.get(k); 
			  }
			  if(count==i){
				list1.add(i); 
				System.out.print("ssssssss:"+i);
			  }	
		}
		Object[]arr= list1.toArray( );
		int[]arr1=new int[arr.length];
		for(int i=0;i<arr.length;i++){
			arr1[i]=(int) arr[i];
		 
		}
		
		return arr1;
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
		String newString=array[0]+"";
		for(int i=1;i<array.length;i++){
			newString=newString+seperator+array[i];
		}
		return newString;
	}
	

}
