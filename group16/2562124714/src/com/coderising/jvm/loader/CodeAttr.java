import java.io.UnsupportedEncodingException;

/**
 * Created by cs on 2017/5/23.
 */
public class CodeAttr implements  AttributeInfo{
    private int nameIndex;
    private int length;
    private int maxStack;

    public int getNameIndex() {
        return nameIndex;
    }

    public int getLength() {
        return length;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxlocas() {
        return maxlocas;
    }

    public int getCodeLen() {
        return codeLen;
    }

    public String getCode() {
        return code;
    }

    public ByteCodeCommand[] getCmds()
    {
        return this.cmds;
    }

    private int maxlocas;
    private int codeLen;
    private String code;
    private ByteCodeCommand[] cmds;
    private LocalVariableTable localVariableTable;

    public LocalVariableTable getLocalVariableTable() {
        return localVariableTable;
    }

    public void setLocalVariableTable(LocalVariableTable localVariableTable) {
        this.localVariableTable = localVariableTable;
    }

    public LineNumberTable getLineNumberTable() {
        return lineNumberTable;
    }

    public void setLineNumberTable(LineNumberTable lineNumberTable) {
        this.lineNumberTable = lineNumberTable;
    }

    private LineNumberTable lineNumberTable;

    private ConstantPool pool;


    public static  CodeAttr parase(ConstantPool pool, ByteCodeIterator ite)
    {
        CodeAttr codeAttr = new CodeAttr();
        codeAttr.nameIndex = ite.nextU2ToInt();
        codeAttr.length = ite.nextU4ToInt();
        codeAttr.maxStack = ite.nextU2ToInt();
        codeAttr.maxlocas = ite.nextU2ToInt();
        codeAttr.codeLen = ite.nextU4ToInt();
        codeAttr.pool = pool;
         codeAttr.code = ite.nextUxToHexString(codeAttr.codeLen);
        codeAttr.cmds = CommandParser.parser(codeAttr.pool,codeAttr.code);
        int exceptiontable = ite.nextU2ToInt();
        if (exceptiontable > 0)
        {
            throw new RuntimeException("TODO:Exception table is not supported");
        }
        int attributecount = ite.nextU2ToInt();
        for (int i = 0; i < attributecount; i++)
        {
            int utf8Index = ite.nextU2ToInt();
            String IsCode = ((UTF8Info)codeAttr.pool.getConstantInfo(utf8Index)).getValue();
            if (IsCode.toLowerCase().equals("linenumbertable"))
            {
                ite.back2byte();
                    LineNumberTable lineNumberTable = LineNumberTable.parse(codeAttr.pool,ite );
                    codeAttr.setLineNumberTable(lineNumberTable);
            }
            else if (IsCode.toLowerCase().equals("localvariabletable"))
            {
                ite.back2byte();
                LocalVariableTable localVariableTable = LocalVariableTable.parse(codeAttr.pool, ite);
                codeAttr.setLocalVariableTable(localVariableTable);
            }
            else
            {
                throw new RuntimeException("TODO:Other attribute for code attribute");
            }


        }

        return codeAttr;
    }
}
