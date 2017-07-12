package com.puhui.util;

import com.puhui.exception.NotSuchConstructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by puhui on 2017/7/11.
 */
public class ClassHelperUtils {

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class getClass(String className, ClassLoader classLoader, boolean isInit) throws ClassNotFoundException {
        return Class.forName(className, isInit, classLoader);
    }

    public static Class getClass(String className) throws ClassNotFoundException {
        ClassLoader classLoader = getClassLoader();
        return Class.forName(className, false, classLoader);
    }

    public Object getObject(String className, ClassLoader classLoader, boolean isInit)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = getClass(className, classLoader, isInit);
        return clazz.newInstance();
    }

    public static Object getObject(String className)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return getClass(className).newInstance();
    }

    public static Object getObjectByConstructor(String className, Object[] args)
            throws ClassNotFoundException, IllegalAccessException, InvocationTargetException,
                   InstantiationException, NotSuchConstructor {
        Class clazz = getClass(className);
        if (args == null) {
            return clazz.newInstance();
        }
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameters = constructor.getParameterTypes();
            boolean isMatcher = true;

            if (args.length != parameters.length) {
                continue;
            }
            for (int i = 0; i < parameters.length; i++) {
                if (args[i] == null || (args[i] != null
                                                && args[i].getClass().getName().equals(parameters[i].getName()))) {
                    continue;
                }

                isMatcher = false;
                break;
            }

            if (isMatcher) {
                return constructor.newInstance(args);
            }
        }
        throw new NotSuchConstructor(String.format("not such constructor in " + className));
    }

    public static void main(String[] args) throws NotSuchConstructor {
        String className = "com.puhui.test.TestClass";
        Object[] arg = {"one", "two"};
        try {
            getObjectByConstructor(className, arg);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


}
