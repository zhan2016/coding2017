/**
 * Created by cs on 2017/5/25.
 */
public interface ByteCodeCommand {
     int getOffset();
     void setOffset(int offeset);
    int getLength();
    String getReadableCodeText();
}
