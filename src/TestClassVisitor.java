import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * author : liupu
 * date   : 2019/4/28
 * desc   :
 */
public class TestClassVisitor extends ClassVisitor {
    public TestClassVisitor(final ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        if (cv != null) {
            cv.visit(version, access, name, signature, superName, interfaces);
        }
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        //如果methodName是show，则返回我们自定义的TestMethodVisitor
        if ("show".equals(name)) {
            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
            return new TestMethodVisitor(mv);
        }
        if (cv != null) {
            return cv.visitMethod(access, name, desc, signature, exceptions);
        }
        return null;
    }
}
