/**
 * Created by cs on 2017/5/26.
 */
public class BiPushCmd  implements  ByteCodeCommand,OneOperandCmd{
    private String opCode;
    private int op;
    private int offset;
    ConstantPool pool;

    public BiPushCmd(ConstantPool pool, String opCode) {
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
        return 2;
    }

    @Override
    public String getReadableCodeText() {
        return CommandParser.GetReadableCmdName(this.opCode);
    }

    public void setOprand(int i) {
        this.op = i;
    }

    @Override
    public String getOperand() {
        return Integer.toString(this.op);
    }
}