package com.thinkerwolf.blog.common.util;

import org.apache.commons.lang.math.RandomUtils;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;

public class JavaCompilerUtil {


    public static Object execute(String method, String code) {
        Package pack = JavaCompilerUtil.class.getPackage();
        String simpleClassName = "Eval$" +  RandomUtils.nextLong();
        String className = pack.getName() + "." + simpleClassName;
        StringBuilder sb = new StringBuilder();
        sb.append("package " + pack.getName() + ";");
        sb.append("\n class " + simpleClassName + " {\n");
        sb.append(code);
        sb.append("\n}");
        Class<?> clazz = compile(className, sb.toString());
        if (clazz != null) {
            try {
               Object obj = clazz.newInstance();
               Method mh = obj.getClass().getMethod(method, String[].class);
               return mh.invoke(obj, new Object[]{new String[]{}});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static Class<?> compile(String className, String javaCode) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, Locale.CHINA, Charset.forName("UTF-8"));
        JavaFileObject fileObject = new StrSrcJavaObject(className, javaCode);
        Iterable<JavaFileObject> jfos = Arrays.asList(fileObject);
        String flag = "-d";
        String outDir = "";
        try {
            File file = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
            outDir = file.getAbsolutePath() + File.separator;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Iterable<String> options = Arrays.asList(flag, outDir);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, jfos);
        boolean result = task.call();
        if (result) {

            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            }

        }
        return null;
    }


    static  class StrSrcJavaObject extends SimpleJavaFileObject {

        private String className;
        private String code;

        protected StrSrcJavaObject(String name, String code) {
            super(URI.create(""), Kind.SOURCE);
            this.className = name;
            this.code = code;
        }


        @Override
        public String getName() {
            return className;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return code;
        }

        @Override
        public InputStream openInputStream() throws IOException {
            return new ByteArrayInputStream(code.getBytes());
        }

        @Override
        public OutputStream openOutputStream() throws IOException {
            return super.openOutputStream();
        }
    }

}
