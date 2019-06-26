package com.virtual_hex.editor.utils;

import java.lang.reflect.Field;
import java.util.Vector;

public class ClassUtils {

    public static Vector<Class> getClasses(ClassLoader classLoader) throws NoSuchFieldException, IllegalAccessException {
        Field f = classLoader.getClass().getDeclaredField("classes");
        boolean originalValue = f.isAccessible();
        f.setAccessible(true);
        Vector<Class> classes = (Vector<Class>) f.get(classLoader);
        f.setAccessible(originalValue);
        return classes;
    }
}
