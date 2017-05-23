/**
 * Created by cs on 2017/5/22.
 */
public class MethodRefInfo implements ConstantInfo{
    private ConstantPool pool;

    public MethodRefInfo(ConstantPool pool) {
        this.pool = pool;
    }
    public int getClassInfoIndex() {
        return ClassInfoIndex;
    }

    public void setClassInfoIndex(int classInfoIndex) {
        ClassInfoIndex = classInfoIndex;
    }

    private int  ClassInfoIndex;

    public int getNameAndTypeIndex() {
        return NameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        NameAndTypeIndex = nameAndTypeIndex;
    }

    private int NameAndTypeIndex;
}
