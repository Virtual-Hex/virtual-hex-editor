package com.virtual_hex.editor.utils;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotationUtils {


    /**
     *
     * @param members Member members of a class to convert into a list of strings
     * @return String[] array of strings formerly a list of Members
     */
    public static String[] convertToString(List<Member> members){
        int size = members.size();
        String[] nameList = new String[size];
        for (int i = 0; i < size; i++) {
            nameList[i] = members.get(i).getName();
        }
        return nameList;
    }

    /**
     *
     * @param parentClazz Class representing the class to check for specific annotations
     * @param annotationClazz Class representing the annotation class to check for
     * @param elementType ElementType representing the element to check for annotations on
     * @param <T> extends Member can be a Field, Method or Constructor
     * @param <T> class type of the parentClazz
     * @param <T> extends Annotation the class of the annotation to be searched for
     * @return
     */
    public static <T extends Member, P, A extends Annotation> List<T> getPresent(Class<P> parentClazz, Class<A> annotationClazz, ElementType elementType) {
        List<T> annotationsPresent = new ArrayList<>();

        switch (elementType) {
            case FIELD: {
                Field[] fields = parentClazz.getDeclaredFields();
                for (Field field : fields) {
                    boolean annotationPresent = field.isAnnotationPresent(annotationClazz);
                    if (annotationPresent) {
                        annotationsPresent.add((T) field);
                    }
                }
                break;
            }
            case METHOD: {
                Method[] methods = parentClazz.getDeclaredMethods();
                for (Method method : methods) {
                    boolean annotationPresent = method.isAnnotationPresent(annotationClazz);
                    if (annotationPresent) {
                        annotationsPresent.add((T) method);
                    }
                }
                break;
            }
            case CONSTRUCTOR: {
                Constructor<?>[] constructors = parentClazz.getConstructors();
                for (Constructor<?> constructor : constructors) {
                    boolean annotationPresent = constructor.isAnnotationPresent(annotationClazz);
                    if (annotationPresent) {
                        annotationsPresent.add((T) constructor);
                    }
                }
                break;
            }
        }
        return annotationsPresent;
    }
}
