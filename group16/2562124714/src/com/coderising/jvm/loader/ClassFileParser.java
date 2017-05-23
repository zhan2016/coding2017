import com.sun.org.apache.bcel.internal.util.ClassPath;

import java.io.Console;
import java.io.UnsupportedEncodingException;

/*
util for parser byte[] zwj 20170522
 */
public class ClassFileParser
{
   public ClassFile Parser(byte[] codes)
    {
        if (codes == null)
        {
            return null;
        }
        //validate caffebaby
        ByteCodeIterator ite = new ByteCodeIterator(codes);
        String magicNumber = ite.nextU4ToHexString();
        if (!"cafebabe".equals(magicNumber))
        {
            return null;
        }
        ClassFile classFile = new ClassFile();
        classFile.setMinorversion(ite.nextU2ToInt());
        classFile.setMajorversion(ite.nextU2ToInt());

        //constantPool paraser
        ConstantPool pool = parseConstantPool(ite);
        classFile.setConstantPool(pool);
        int accessFlag = parseAccessFlag(ite);
        classFile.setAccessflag(accessFlag);
        ClassIndex clzindex = new ClassIndex();
        clzindex.setThisClassIndex(ite.nextU2ToInt());
        clzindex.setSuperClassIndex(ite.nextU2ToInt());
        classFile.setClzIndex(clzindex);

        //interface paraser
        paraseInterface(ite);

        //field atrribute paraser. Add a reference to constant pool.
        FieldInfoPool fieldpool = parseFieldPool(ite,pool);
       // fieldpool = parseFieldPool(ite);

        return classFile;
    }

    private void paraseInterface(ByteCodeIterator ite) {
      int interfaceCount =  ite.nextU2ToInt();
      System.out.println("interface count:" + interfaceCount);

      //TODO: 如果实现了interface，这里需要解析

    }

    private FieldInfoPool parseFieldPool(ByteCodeIterator ite, ConstantPool pool) {
       int FieldAttrCount = ite.nextU2ToInt();
       FieldInfoPool FieldAttrPool = new FieldInfoPool();
       FieldAttrPool.setSize(FieldAttrCount);

       for(int i = 0; i < FieldAttrCount; i++)
       {

       }

       return null;
    }

    private int parseAccessFlag(ByteCodeIterator ite) {
       return ite.nextU2ToInt();
    }

    private ConstantPool parseConstantPool(ByteCodeIterator ite)
    {
        int constPoolCount = ite.nextU2ToInt();
        ConstantPool pool  = new ConstantPool();
        pool.addConstantInfo(new NullConstantInfo());
        pool.setSize(constPoolCount);

        for(int i = 1; i < constPoolCount; i++)
        {
            int tag = ite.nextU1ToInt();

            if (tag == 7)
            {
                //class info
                int utf8Index = ite.nextU2ToInt();
                ClassInfo clzInfo = new ClassInfo(pool);
                clzInfo.setUtf8Index(utf8Index);

                pool.addConstantInfo(clzInfo);
            } else if (tag  == 1)
            {
                //UTF-8 String
                int len = ite.nextU2ToInt();
                byte[] data = ite.getBytes(len);
                String value = null;
                try
                {
                    value = new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                UTF8Info utf8info = new UTF8Info(pool);
                utf8info.setLen(len);
                utf8info.setValue(value);
                pool.addConstantInfo(utf8info);
            } else if (tag == 8)
            {
                StringInfo info = new StringInfo(pool);
                info.setIndex(ite.nextU2ToInt());
                pool.addConstantInfo(info);
            } else if (tag == 9)
            {
                FieldRefInfo field = new FieldRefInfo(pool);
                field.setClassInfoIndex(ite.nextU2ToInt());
                field.setNameAndTypeIndex(ite.nextU2ToInt());
                pool.addConstantInfo(field);
            } else if (tag == 10)
            {
                MethodRefInfo method = new MethodRefInfo(pool);
                method.setClassInfoIndex(ite.nextU2ToInt());
                method.setNameAndTypeIndex(ite.nextU2ToInt());
                pool.addConstantInfo(method);

            }else if (tag == 12)
            {
                NameAndTypeInfo nametype = new NameAndTypeInfo(pool);
                nametype.setIndex1(ite.nextU2ToInt());
                nametype.setIndex2(ite.nextU2ToInt());
                pool.addConstantInfo(nametype);


            }else
            {
                throw new RuntimeException("The constant pool tag" + tag + "has not supported");

            }


        }
        return pool;
    }

}