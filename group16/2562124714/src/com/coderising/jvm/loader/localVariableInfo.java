import java.util.LinkedList;

/**
 * Created by cs on 2017/5/23.
 */
public class localVariableInfo {
    public   int startPc;

    public int getStartPc() {
        return startPc;
    }

    public void setStartPc(int startPc) {
        this.startPc = startPc;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int length;

    public int nameIndex;
    public int descriptorIndex;
    public  int index;

    public ConstantPool getPool() {
        return pool;
    }

    public void setPool(ConstantPool pool) {
        this.pool = pool;
    }

    private ConstantPool pool;



}
