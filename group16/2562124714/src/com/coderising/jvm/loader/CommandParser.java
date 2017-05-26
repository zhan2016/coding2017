import java.util.ArrayList;
import java.util.List;

/**
 * Created by cs on 2017/5/26.
 */
public class CommandParser {
    public static final String  aconst_null = "01";
    public static final String new_object = "BB";
    public static final String Istore = "37";
    public static final String invokespecial = "B7";
    public static final String invokevirtual = "B6";
    public static final String getfield = "B4";
    public static final String putfield = "B5";
    public static final String getstatic = "B2";
    public static final String ldc = "12";
    public static final String dup = "59";
    public static final String bipush = "10";
    public static final String aload_0 = "2A";
    public static final String aload_1 = "2B";
    public static final String aload_2 = "2C";
    public static final String iload = "15";
    public static final String iload_1 = "1B";
    public static final String iload_2 = "1C";
    public static final String iload_3 = "1D";
    public static final String fload_3 = "25";
    public static final String voidreturn  = "B1";
    public static final String ireturn  = "AC";
    public static final String freturn   = "AE";
    public static final String astore_1  = "4C";
    public static final String if_icmp_ge  = "A2";
    public static final String if_icmple  = "A4";
    public static final String goto_no_condition  = "A7";
    public static final String iconst_0  = "03";
    public static final String iconst_1  = "04";
    public static final String istore_1  = "3C";
    public static final String istore_2  = "3D";
    public static final String iadd  = "60";
    public static final String iinc  = "84";
    public static String[] OpCodeTable = {"01","BB","37","B7","B6", "B4", "B5", "B2", "12", "59","10","2A","2B","2C","15","1B",
    "1C","1D", "25","B1", "AC", "AE",  "4C", "A2","A4", "A7", "03", "04", "3C", "3D", "60","84"};
    public static String[]  OpNameTable = {"aconst_null", "new", "Istore", "invokespecial", "invokevirtual","getfield", "putfield", "getstatic", "ldc",
    "dup", "bipush","aload_0", "aload_1", "aload_2", "iload", "iload_1", "iload_2", "iload_3", "fload_3","return", "ireturn", "freturn", "astore_1","if_icmp_ge","if_icmple",
    "goto_no_condition", "iconst_0", "iconst_1","istore_1", "istore_2","iadd","iinc"};

    public static ByteCodeCommand[] parser(ConstantPool pool, String code) {
        if (code == null || code.length() == 0)
        {
            return null;
        }
        code = code.toUpperCase();
        CommandIterator iter = new CommandIterator(code);

        List<ByteCodeCommand> cmds = new ArrayList<ByteCodeCommand>();

        while (iter.hasNext())
        {
            String opCode = iter.next2CharAsString();
            if (new_object.equals(opCode))
            {
                NewObjectCmd cmd = new NewObjectCmd(pool,opCode);
                cmd.setOperand1(iter.next2CharAsInt());
                cmd.setOperan2(iter.next2CharAsInt());
                cmds.add(cmd);
            }
            else if(invokespecial.equals(opCode))
            {
                InvokeSpecialCmd cmd = new InvokeSpecialCmd(pool,opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());
                cmds.add(cmd);
            }
            else if(invokevirtual.equals(opCode))
            {
                InvokeVirtualCmd  cmd = new InvokeVirtualCmd(pool,opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());
                cmds.add(cmd);
            }
            else if(getfield.equals(opCode))
            {
                GetFieldCmd cmd = new GetFieldCmd(pool,opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());
                cmds.add(cmd);
            }
            else if(getstatic.equals(opCode))
            {
                GetStaticFieldCmd cmd = new GetStaticFieldCmd(pool,opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());
                cmds.add(cmd);
            }
            else if(putfield.equals(opCode))
            {
                PutFieldCmd cmd = new PutFieldCmd(pool,opCode);
                cmd.setOprand1(iter.next2CharAsInt());
                cmd.setOprand2(iter.next2CharAsInt());
                cmds.add(cmd);
            }
            else if(ldc.equals(opCode))
            {
                LdcCmd cmd = new LdcCmd(pool,opCode);
                cmd.setOprand(iter.next2CharAsInt());
                cmds.add(cmd);
            }
            else if(bipush.equals(opCode))
            {
                BiPushCmd cmd = new BiPushCmd(pool,opCode);
                cmd.setOprand(iter.next2CharAsInt());
                cmds.add(cmd);
            }
            else if (dup.equals(opCode) || aload_0.equals(opCode) || aload_1.equals(opCode) || aload_2.equals(opCode) || iload.equals(opCode)
                    || iload_1.equals(opCode) || iload_2.equals(opCode) || iload_3.equals(opCode)|| fload_3.equals(opCode) ||voidreturn.equals(opCode) ||astore_1.equals(opCode))
            {
                NoOperanCmd cmd = new NoOperanCmd(pool,opCode);
                cmds.add(cmd);

            }
            else
            {
                throw new RuntimeException("sorry the java instruction" + opCode + " has not been implementd");
            }
        }

        caclcuateOffset(cmds);

        ByteCodeCommand[] result = new ByteCodeCommand[cmds.size()];
        cmds.toArray(result);
        return  result;

    }

    private static void caclcuateOffset(List<ByteCodeCommand> cmds) {
        int offset = 0;
        for (ByteCodeCommand cmd : cmds)
        {
            cmd.setOffset(offset);
            offset += cmd.getLength();
        }
    }

    public static String GetReadableCmdName(String opcode)
    {
        int i = 0;
        for(i = 0; i < OpCodeTable.length; i++ )
        {
            if (opcode.equals(OpCodeTable[i]))
            {
                break;
            }
        }
        return OpNameTable[i];
    }
}
