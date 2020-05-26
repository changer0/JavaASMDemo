import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 自定义一个 ClassVisitor
 */
public class TestClassVisitor extends ClassVisitor {
    private String mClassName;

    public TestClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        mClassName = name;
        System.out.println(mClassName + " visit");
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        System.out.println("正在查找的方法：" + name);
        if ("newFun".equals(name)) {
            mv = new TestMethodVisitor(mv, access, name, desc) {
                @Override
                protected void onMethodExit(int opcode) {
                    super.onMethodExit(opcode);
                    mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    mv.visitLdcInsn("after insert");
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                }
            };
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        System.out.println(mClassName + " visitEnd");
    }
}
