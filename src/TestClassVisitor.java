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
        MethodVisitor methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions);
        System.out.println("正在查找的方法：" + name);
        if ("insertFun".equals(name)) {
            methodVisitor = new TestMethodVisitor(methodVisitor, access, name, desc) {
                @Override
                protected void onMethodEnter() {
                    super.onMethodEnter();
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitVarInsn(ILOAD, 1);
                    mv.visitMethodInsn(INVOKESTATIC, "CallBackHelper", "before", "(LTestClass;I)V", false);
                    System.out.println("完成对 " + name + "方法进入时插入");
                }

                @Override
                protected void onMethodExit(int opcode) {
                    super.onMethodExit(opcode);
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitVarInsn(ILOAD, 1);
                    mv.visitMethodInsn(INVOKESTATIC, "CallBackHelper", "after", "(LTestClass;I)V", false);
                    System.out.println("完成对 " + name + "方法结束时插入");
                }
            };
        }
        return methodVisitor;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        System.out.println(mClassName + " visitEnd");
    }
}
