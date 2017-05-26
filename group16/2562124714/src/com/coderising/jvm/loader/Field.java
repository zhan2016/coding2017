/**
 * Created by cs on 2017/5/23.
 */
public class Field {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    public int getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(int accessFlag) {
        this.accessFlag = accessFlag;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public ConstantPool getPool() {
        return pool;
    }

    public void setPool(ConstantPool pool) {
        this.pool = pool;
    }

    public ConstantValueInfo getConstantvalueinfos() {
        return constantvalueinfos;
    }

    public void setConstantvalueinfos(ConstantValueInfo constantvalueinfos) {
        this.constantvalueinfos = constantvalueinfos;
    }

    public static Field parase(ClassFile clzfile, ByteCodeIterator ite)
    {
        Field f = new Field();
        f.accessFlag = ite.nextU2ToInt();
        f.nameIndex = ite.nextU2ToInt();
        f.descriptorIndex = ite.nextU2ToInt();
        f.pool = clzfile.getConstantPool();

        int addtionalcount = ite.nextU2ToInt();
        if (addtionalcount > 0 )
        {
            throw new RuntimeException("TODO:addtional attribute need to support");
        }

        return f;

    }

    private ConstantPool pool;
    private ConstantValueInfo constantvalueinfos;

    public String toString()
    {
        String fieldName = ((UTF8Info)this.pool.constantInfos.get(this.nameIndex)).getValue();
        String fieldDescriptor = ((UTF8Info)this.pool.constantInfos.get(this.descriptorIndex)).getValue();

        return new String(fieldName + ":" + fieldDescriptor);
    }


}
