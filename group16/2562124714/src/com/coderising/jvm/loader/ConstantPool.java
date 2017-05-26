import java.util.LinkedList;
import java.util.List;

/**
 * Created by cs on 2017/5/22.
 */
public class ConstantPool {
    LinkedList<ConstantInfo> constantInfos = new LinkedList<ConstantInfo>();

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size;


    public void addConstantInfo(ConstantInfo constantinfo) {
        constantInfos.add(constantinfo);
    }

    public ConstantInfo getConstantInfo(int i) {
        return constantInfos.get(i);
    }

    public String getUTF8String(int nameIndex) {
       return  ((UTF8Info)this.constantInfos.get(nameIndex)).getValue();
    }
}
