/**
 * Created by cs on 2017/5/22. byte convert util.Only support big endian.
 */
public class Util {
    public static int byteToInt(byte[] bytes) {
        int value= 0;
        for(int i=0; i<bytes.length; i++)
            value = (value << 8) | bytes[i];
        return value;
    }

    public static String byteToString(byte[] bytes) {
        return new String(bytes);
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<bytes.length;i++){
            byte b = bytes[i];
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
