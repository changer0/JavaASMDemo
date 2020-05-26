import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;

public class ASMJava {
    public static void main(String args[]) throws IOException {
        ClassReader cr = new ClassReader(TestClass.class.getName());
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new TestClassVisitor(cw);
        cr.accept(cv, Opcodes.ASM5);
        // 获取生成的class文件对应的二进制流
        byte[] code = cw.toByteArray();
        //将二进制流写到out/下
        FileOutputStream fos = new FileOutputStream("out/TestClass.class");
        fos.write(code);
        fos.close();
    }
}
