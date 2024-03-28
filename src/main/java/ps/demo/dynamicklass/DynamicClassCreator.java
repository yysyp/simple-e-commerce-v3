package ps.demo.dynamicklass;
import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicClassCreator {

    //https://www.cnblogs.com/barrywxx/p/13233373.html
//CGLib, Javassist, JavaCompiler, Groovy, Scala, Aviator
    //https://github.com/barrywang88/compiler

    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ClassPool classPool = ClassPool.getDefault();

        // 创建一个新的类定义
        CtClass cc = classPool.makeClass("ps.demo.dynamicklass.DynamicallyCreatedClass");

        // 创建一个新的方法
        CtMethod method = CtNewMethod.make("public static void printHello() { System.out.println(\"Hello, World!\"); }", cc);
        cc.addMethod(method);

        // 将类的字节码转换成Class对象
        Class<?> clazz = cc.toClass();

        // 获取类的实例并调用方法
        Object instance = clazz.newInstance();
        clazz.getMethod("printHello").invoke(instance);


//        Map<String, byte[]> results = null;
//        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//        StandardJavaFileManager stdManager = compiler.getStandardFileManager(null, null, null);
//        try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
//            JavaFileObject javaFileObject = manager.makeStringSource(fileName, source);
//            CompilationTask task = compiler.getTask(null, manager, null, null, null, Arrays.asList(javaFileObject));
//            if (task.call()) {
//                results = manager.getClassBytes();
//            }
//        }

    }

}
