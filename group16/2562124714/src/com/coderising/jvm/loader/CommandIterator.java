/**
 * Created by cs on 2017/5/26.
 */
public class CommandIterator {
    private String data;
    private int pos;
    private int len;
    public CommandIterator(String code) {
        this.data = code;
        this.pos = 0;
        this.len = code.length();
    }


    public boolean hasNext() {
        if (pos + 1 >= len)
        {
            return false;
        }
        return true;
    }

    public String next2CharAsString() {
        StringBuilder s = new StringBuilder();
        s.append(data.charAt(pos++));
        s.append(data.charAt(pos++));

        return s.toString();


    }

    public int next2CharAsInt() {
        String s = next2CharAsString();
        int temp = Integer.parseInt(s, 16);
        return temp;
    }
}
