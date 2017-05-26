/*
a structure of a java class zwj 20170522
 */

import java.util.LinkedList;

public class ClassFile
{
    public int getMinorversion() {
        return minorversion;
    }

    private int minorversion;

    public int getMajorversion() {
        return majorversion;
    }

    private int majorversion;

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    private ConstantPool constantPool;

    public void setMinorversion(int minorversion)
    {
        this.minorversion = minorversion;
    }

    public void setMajorversion(int majorversion)
    {
        this.majorversion = majorversion;
    }

    public int getAccessflag() {
        return accessflag;
    }

    public void setAccessflag(int accessflag) {
        this.accessflag = accessflag;
    }

    private int accessflag;

    public ClassIndex getClzIndex() {
        return clzIndex;
    }

    public void setClzIndex(ClassIndex clzIndex) {
        this.clzIndex = clzIndex;
    }

    private ClassIndex clzIndex;

    private LinkedList<Field> FieldList = new LinkedList<Field>();
    public void AddField(Field f)
    {
        FieldList.add(f);
    }

    public LinkedList<Field> GetFieldList()
    {
        return this.FieldList;
    }

    private LinkedList<Method> MethodList = new LinkedList<Method>();
    public void AddMethod(Method m)
    {
        MethodList.add(m);
    }

    public LinkedList<Method> GetMethodList()
    {
        return this.MethodList;
    }


    public Method getMethod(String s, String s1) {
        for (Method m:MethodList
             ) {
           String methodname =  ((UTF8Info)this.constantPool.constantInfos.get(m.getNameIndex())).getValue();
           String methodDescriptor = ((UTF8Info)this.constantPool.constantInfos.get(m.getDescriptorIndex())).getValue();
           if (methodname.toLowerCase().equals(s.toLowerCase()))
           {
               if (methodDescriptor.toLowerCase().equals(s1.toLowerCase())) {
                   return m;
               }
           }

        }
        return null;

    }

    public Method getMainMethod() {
        for (Method m : MethodList)
        {
            String methodName = ((UTF8Info)constantPool.constantInfos.get(m.getNameIndex())).getValue();
            if (methodName.toLowerCase().equals("main"))
            {
                return m;
            }

        }
        return null;
    }
}
