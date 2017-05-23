/**
 * Created by cs on 2017/5/22.
 */
public class UTF8Info implements ConstantInfo {
    private ConstantPool pool;

    public UTF8Info(ConstantPool pool) {
        this.pool = pool;
    }
    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    private int len;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String value;
}
