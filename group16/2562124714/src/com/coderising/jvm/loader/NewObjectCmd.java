/**
 * Created by cs on 2017/5/26.
 */
public class NewObjectCmd implements ByteCodeCommand,TwoOperandCmd{
    private String opCode;
    private int op1;
    private int op2;
    private  int offset;
    ConstantPool pool;

    public NewObjectCmd(ConstantPool pool, String opCode) {
        this.opCode = opCode;
        this.pool =    pool;
    }

    @Override
    public int getOffset() {
        return this.offset;
    }

    @Override
    public void setOffset(int offeset) {
        this.offset = offeset;

    }

    @Override
    public int getLength() {
        return 3;
    }

    @Override
    public String getReadableCodeText() {
        return CommandParser.GetReadableCmdName(this.opCode);
    }

    public void setOperand1(int op1)
    {
        this.op1 = op1;
    }

    public void setOperan2(int op2)
    {
        this.op2 = op2;
    }
    @Override
    public String getIndex() {
        int index = (op1 << 8) | op2;
        return Integer.toString(index);
        // return ((UTF8Info)this.pool.getConstantInfo(index)).getValue();
    }
}
