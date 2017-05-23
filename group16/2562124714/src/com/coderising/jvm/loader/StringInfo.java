/**
 * Created by cs on 2017/5/22.
 */
public class StringInfo implements  ConstantInfo{
    private ConstantPool pool;

    public StringInfo(ConstantPool pool) {
        this.pool = pool;
    }
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private int index;

}
