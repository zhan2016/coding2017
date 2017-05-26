import java.util.LinkedList;

/**
 * Created by cs on 2017/5/23.
 */
public class LocalVariableTable {
    private int nameIndex;
    private int length;
    private int tableLen;
    private LinkedList<localVariableInfo> localVariableInfos = new LinkedList<localVariableInfo>();
    private ConstantPool pool;

    public static LocalVariableTable parse(ConstantPool pool, ByteCodeIterator ite)
    {
        LocalVariableTable localVariableTable = new LocalVariableTable();
        localVariableTable.pool = pool;
        localVariableTable.nameIndex = ite.nextU2ToInt();
        localVariableTable.length = ite.nextU4ToInt();
        localVariableTable.tableLen = ite.nextU2ToInt();
        for (int i = 0; i < localVariableTable.tableLen; i++)
        {
            localVariableInfo localvariableinfo = new localVariableInfo();
            localvariableinfo.startPc = ite.nextU2ToInt();
            localvariableinfo.length = ite.nextU2ToInt();
            localvariableinfo.nameIndex = ite.nextU2ToInt();
            localvariableinfo.descriptorIndex = ite.nextU2ToInt();
            localvariableinfo.index = ite.nextU2ToInt();
            localvariableinfo.setPool(pool);
            localVariableTable.localVariableInfos.add(localvariableinfo);
        }
        return localVariableTable;

    }
}
