package com.example.classload;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ClassLoaderTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testBootstrapClassLoader() {
        System.out.println(System.getProperty("sun.boot.class.path"));
    }

    @Test
    public void testExtensionsClassLoader() {
        System.out.println(System.getProperty("java.ext.dirs"));
    }

    @Test
    public void testApplicationClassLoader() {
        System.out.println(System.getProperty("java.class.path"));
    }

    @Test
    public void testClassLoader() {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader);
            loader = loader.getParent();
        }
    }

    @Test
    public void test() {
        System.out.println(getFileName(".danke.class"));
    }

    @Test
    public void testDiskClassLoader() {
        DiskClassLoader diskClassLoader = new DiskClassLoader("/Users/danke/Study/libs");
        try {
            Class c = diskClassLoader.loadClass("com.example.memoryleaks.Jobs");
            if (c != null) {
                try {
                    Object obj = c.newInstance();
                    System.out.println(obj.getClass().getClassLoader());
                    Method method = c.getDeclaredMethod("say", null);
                    method.invoke(obj, null);
                } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if (index == -1) {
            return name + ".class";
        } else {
            return name.substring(index + 1) + ".class";
        }
    }
}