/**
 * Created by cs on 2017/5/22.
 */
public class ClassInfo implements  ConstantInfo{
    public ClassInfo(ConstantPool pool) {
        this.pool = pool;
    }

    private int utf8Index;
    private ConstantPool pool;
    public int getUtf8Index() {
        return utf8Index;
    }

    public void setUtf8Index(int utf8Index) {
        this.utf8Index = utf8Index;
    }

    public String getClassName() {
        return ((UTF8Info)pool.getConstantInfo(utf8Index)).getValue();
    }
}
