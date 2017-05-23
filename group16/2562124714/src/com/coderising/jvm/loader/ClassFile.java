/*
a structure of a java class zwj 20170522
 */

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


}
