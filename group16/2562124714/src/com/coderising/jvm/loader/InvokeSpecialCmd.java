/**
 * Created by cs on 2017/5/26.
 */
public class InvokeSpecialCmd implements  ByteCodeCommand,TwoOperandCmd{
    private String opCode;
    private int op1;
    private int op2;
    private  int offset;
    ConstantPool pool;
    public InvokeSpecialCmd(ConstantPool pool, String opCode) {
        this.opCode = opCode;
        this.pool = pool;
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

    public void setOprand1(int i) {
        this.op1 = i;
    }

    public void setOprand2(int i) {
        this.op2 = i;
    }

    @Override
    public String getIndex() {
        int index = (op1 << 8) | op2;
        return Integer.toString(index);
       // return ((UTF8Info)this.pool.getConstantInfo(index)).getValue();
    }
}
