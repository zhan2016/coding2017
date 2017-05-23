/**
 * Created by cs on 2017/5/23.
 */
public class Method {
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



    public static Method parase(ClassFile clzfile, ByteCodeIterator ite)
    {
        Method m = new Method();
        m.accessFlag = ite.nextU2ToInt();
        m.nameIndex = ite.nextU2ToInt();
        m.descriptorIndex = ite.nextU2ToInt();
        m.pool = clzfile.getConstantPool();

        int addtionalcount = ite.nextU2ToInt();
        //读取属性表
        for(int i = 0; i < addtionalcount; i++)
        {
            int utf8Index = ite.nextU2ToInt();
            String IsCode = ((UTF8Info)m.pool.getConstantInfo(utf8Index)).getValue();
            if (IsCode.toLowerCase().equals("code"))
            {
                ite.back2byte();
                m.codeAttr = CodeAttr.parase(m.pool,ite );
            }


        }

        return m;

    }

    private ConstantPool pool;

    public CodeAttr getCodeAttr() {
        return codeAttr;
    }

    public void setCodeAttr(CodeAttr codeAttr) {
        this.codeAttr = codeAttr;
    }

    private CodeAttr codeAttr;
}
