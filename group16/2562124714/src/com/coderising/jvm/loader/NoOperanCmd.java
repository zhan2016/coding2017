/**
 * Created by cs on 2017/5/26.
 */
public class NoOperanCmd implements ByteCodeCommand{
    private String opCode;
    private int offset;
    ConstantPool pool;
    public NoOperanCmd(ConstantPool pool, String opCode) {
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
        return 1;
    }

    @Override
    public String getReadableCodeText() {
        return CommandParser.GetReadableCmdName(this.opCode);
    }
}
