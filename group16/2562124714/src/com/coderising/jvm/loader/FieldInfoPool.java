import java.util.LinkedList;

/**
 * Created by cs on 2017/5/23.
 */
public class FieldInfoPool {
    private LinkedList<Field> fieldInfos = new LinkedList();
    private int fieldCount;


    public void setSize(int size) {
        this.fieldCount = size;
    }
    public int getSize()
    {
        return fieldCount;
    }
}
