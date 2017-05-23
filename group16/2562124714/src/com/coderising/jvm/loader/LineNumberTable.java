import javax.sound.sampled.Line;
import java.util.LinkedList;

/**
 * Created by cs on 2017/5/23.
 */
public class LineNumberTable implements  AttributeInfo {
    private int nameIndex;
    private int length;
    private int tableLen;
    private LinkedList<lineNumberInfo> lineNumberInfos = new LinkedList<lineNumberInfo>();
    private ConstantPool pool;

    public static LineNumberTable parse(ConstantPool pool, ByteCodeIterator ite)
    {
        LineNumberTable lineNumberTable = new LineNumberTable();
        lineNumberTable.pool = pool;
        lineNumberTable.nameIndex = ite.nextU2ToInt();
        lineNumberTable.length = ite.nextU4ToInt();
        lineNumberTable.tableLen = ite.nextU2ToInt();
        for (int i = 0; i < lineNumberTable.tableLen; i++)
        {
            lineNumberInfo linenumberinfo = new lineNumberInfo();
            linenumberinfo.setStartPc(ite.nextU2ToInt());
            linenumberinfo.setLineNumber(ite.nextU2ToInt());
            lineNumberTable.lineNumberInfos.add(linenumberinfo);
        }
        return lineNumberTable;

    }

}