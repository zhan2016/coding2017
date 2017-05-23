/**
 * Created by cs on 2017/5/22.
 */
public class FieldRefInfo implements ConstantInfo{
    private int ClassInfoIndex;
    private ConstantPool pool;

    public FieldRefInfo(ConstantPool pool) {
        this.pool = pool;
    }

    public int getClassInfoIndex() {
        return ClassInfoIndex;
    }

    public void setClassInfoIndex(int classInfoIndex) {
        ClassInfoIndex = classInfoIndex;
    }

    public int getNameAndTypeIndex() {
        return NameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        NameAndTypeIndex = nameAndTypeIndex;
    }

    private  int NameAndTypeIndex;

}
