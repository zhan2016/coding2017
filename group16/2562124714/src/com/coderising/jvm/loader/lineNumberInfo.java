/**
 * Created by cs on 2017/5/23.
 */
public class lineNumberInfo {
    public int getStartPc() {
        return startPc;
    }

    public void setStartPc(int startPc) {
        this.startPc = startPc;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    private int startPc;
    private int lineNumber;
}
