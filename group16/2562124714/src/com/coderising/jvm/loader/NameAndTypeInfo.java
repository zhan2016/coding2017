/**
 * Created by cs on 2017/5/22.
 */
public class NameAndTypeInfo implements ConstantInfo{
    private ConstantPool pool;

    public NameAndTypeInfo(ConstantPool pool) {
        this.pool = pool;
    }
    public int getIndex1() {
        return index1;
    }

    public void setIndex1(int index1) {
        this.index1 = index1;
    }

    private  int index1;

    public int getIndex2() {
        return index2;
    }

    public void setIndex2(int index2) {
        this.index2 = index2;
    }

    private  int index2;

}
