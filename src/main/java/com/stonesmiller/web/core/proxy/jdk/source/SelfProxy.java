package com.stonesmiller.web.core.proxy.jdk.source;


import com.stonesmiller.web.core.proxy.SelfInvocationHandler;
import org.apache.commons.io.FileUtils;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/10 19:47
 * Description:
 */
public class SelfProxy {

    public static Object newProxyInstance(Class<?> clazz, SelfInvocationHandler invocationHandler) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String methodStr = "";
        for (Method method : clazz.getMethods()) {
            methodStr += "    @Override\n" +
                    "    public void " + method.getName() + "() {\n" +
                    "        try {\n" +
                    "            Method method = " + clazz.getName() + ".super.getClass().getMethod(\"" + method.getName() + "\");\n" +
                    "            handler.invoke(this,method);\n" +
                    "        } catch (Exception e) {\n" +
                    "            e.printStackTrace();\n" +
                    "        }\n" +
                    "    }\n";
        }

        String source = "package com.stonesmiller.web.core.proxy;\n" +
                "import java.lang.reflect.Method;\n" +
                "public class $Proxy0 implements " + clazz.getName() + " {\n" +
                "    private SelfInvocationHandler handler;\n" +
                "    public $Proxy0(SelfInvocationHandler handler) {\n" +
                "        this.handler = handler;\n" +
                "    }\n" +
                methodStr +
                "}\n";
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\stonesmiller\\web\\core\\proxy\\$Proxy0.java";
        File file = new File(path);
        FileUtils.writeStringToFile(file, source);
        //编译
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> fileObjects = standardFileManager.getJavaFileObjects(path);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null, null, null, fileObjects);
        task.call();
        standardFileManager.close();
        //加载
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> proxyClazz = classLoader.loadClass("com.stonesmiller.web.core.proxy.$Proxy0");
        //对象
        Constructor<?> constructor = proxyClazz.getConstructor(SelfInvocationHandler.class);
        return constructor.newInstance(invocationHandler);
    }

}
