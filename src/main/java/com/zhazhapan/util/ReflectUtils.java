package com.zhazhapan.util;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author pantao
 * @since 2018/1/20
 */
public class ReflectUtils {

    private ReflectUtils() { }

    /**
     * 扫描包下面的所有类
     *
     * @param packages 包名
     *
     * @return {@link List}
     *
     * @throws IOException 异常
     * @throws ClassNotFoundException 异常
     */
    public static List<Class<?>> scanPackage(String... packages) throws IOException, ClassNotFoundException {
        List<Class<?>> list = new ArrayList<>();
        for (String pkg : packages) {
            list.addAll(getClasses(pkg));
        }
        return list;
    }

    /**
     * 从包中获取所有的类
     *
     * @param packageName 包名
     *
     * @return {@link List}
     *
     * @throws IOException 异常
     * @throws ClassNotFoundException 异常
     */
    public static List<Class<?>> getClasses(String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                addClassesInPackageByFile(packageName, filePath, classes);
            } else if ("jar".equals(protocol)) {
                JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.charAt(0) == '/') {
                        name = name.substring(1);
                    }
                    if (name.startsWith(packageDirName)) {
                        int idx = name.lastIndexOf('/');
                        if (idx != -1) {
                            packageName = name.substring(0, idx).replace('/', '.');
                        }
                        if (name.endsWith(".class") && !entry.isDirectory()) {
                            String className = name.substring(packageName.length() + 1, name.length() - 6);
                            classes.add(Class.forName(packageName + '.' + className));
                        }
                    }
                }
            }
        }
        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有类
     *
     * @param packageName 包名
     * @param packagePath 包路径
     * @param classes 文件列表
     *
     * @throws ClassNotFoundException 异常
     */
    public static void addClassesInPackageByFile(String packageName, String packagePath, List<Class<?>> classes)
            throws ClassNotFoundException {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] files = dir.listFiles(file -> (file.isDirectory()) || (file.getName().endsWith(".class")));
        if (Checker.isNotNull(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    addClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), classes);
                } else {
                    String className = file.getName().substring(0, file.getName().length() - 6);
                    classes.add(Class.forName(packageName + '.' + className));
                }
            }
        }
    }
}
