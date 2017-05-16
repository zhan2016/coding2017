package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import ClassFileLoader.ClassFileLoader;

import org.junit.After; 
import org.junit.Assert; 
import org.junit.Before; 
import org.junit.Test; 

public class ClassFileloaderTest { 

	static String path1 = "C:\\Users\\yanght\\Documents\\mygit\\coding2017-1\\group06\\1454385822\\bin"; 
	static String path2 = "D:\temp"; 
	@Before 
	public void setUp() throws Exception {		  
	} 
	@After 
	public void tearDown() throws Exception { 
	} 
	@Test 
	public void testClassPath(){		 
		ClassFileLoader loader = new ClassFileLoader(); 
		loader.addClassPath(path1); 
		loader.addClassPath(path2); 
		String clzPath = loader.getClassPath(); 
		Assert.assertEquals(path1+";"+path2,clzPath); 
	} 
	@Test 
	public void testClassFileLength() {		 
		ClassFileLoader loader = new ClassFileLoader(); 
		loader.addClassPath(path1); 
		String className = "com.coding.basic.homework_04.jvm.test.EmployeeV1"; 
//		String className = "EmployeeV1"; 
		byte[] byteCodes = loader.readBinaryCode(className); 
		// ע�⣺����ֽ������ܺ����JVM�汾�й�ϵ�� ����Կ�������õ��ൽ���ж�� 
		Assert.assertEquals(1084, byteCodes.length); 
	} 
    @Test	 
	public void testMagicNumber(){ 
    	ClassFileLoader loader = new ClassFileLoader(); 
		loader.addClassPath(path1); 
		String className = "com.coding.basic.homework_04.jvm.test.EmployeeV1"; 
		byte[] byteCodes = loader.readBinaryCode(className); 
		byte[] codes = new byte[]{byteCodes[0],byteCodes[1],byteCodes[2],byteCodes[3]}; 
		String acctualValue = this.byteToHexString(codes); 
		Assert.assertEquals("cafebabe", acctualValue); 
	} 
	private String byteToHexString(byte[] codes ){ 
		StringBuffer buffer = new StringBuffer(); 
		for(int i=0;i<codes.length;i++){ 
			byte b = codes[i]; 
			int value = b & 0xFF; 
			String strHex = Integer.toHexString(value); 
			if(strHex.length()< 2){ 
				strHex = "0" + strHex; 
			}		 
			buffer.append(strHex); 
		} 
		return buffer.toString(); 
	} 
}
