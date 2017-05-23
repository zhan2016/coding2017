/*
a util for byte read and convert zwj 20170522.
 */

public class ByteCodeIterator
{
    private byte[] data;
    private int pos;

    public ByteCodeIterator(byte[] codes) {
        data = codes;
        pos  = 0;
    }


    public String nextU4ToHexString() {
        return Util.byteToHexString(new byte[] {data[pos++], data[pos++], data[pos++], data[pos++]});
    }

    public int nextU4ToInt()
    {
        return Util.byteToInt(new byte[]{data[pos++], data[pos++],data[pos++], data[pos++]});
    }

    public int nextU2ToInt()
    {
        return Util.byteToInt(new byte[] {data[pos++], data[pos++]});
    }

    public int nextU1ToInt()
    {
        return Util.byteToInt(new byte[]{data[pos++]});
    }

    public byte[] getBytes(int len) {
        byte[] result = new byte[len];

        for (int i = 0; i < len; i++)
        {
            result[i] = data[pos++];
        }
        return result;
    }

    public void back2byte() {
        this.pos -= 2;

    }
}