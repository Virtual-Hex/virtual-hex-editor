package com.virtual_hex.editor.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class ReflectionUtils {

    // only field and method are supported

    /**
     * This does not support private or some protected instances. This does not change
     * Member accessibility.
     *
     * @param member Member which can be a Field or Method
     * @return boolean true if it is null or an unsupported type
     */
    public static boolean isNull(Member member) throws IllegalAccessException, InvocationTargetException {
        if(member instanceof Field){
            Field field = (Field) member;
            Object o = field.get(member);
            return o == null;
        } else if (member instanceof Method) {
            Method method = (Method) member;
            Object o = method.invoke(member);
            return o == null;
        } else {
            return true;
        }
    }

    /**
     * This does not support private or some protected instances. This does not change
     * Member accessibility. This does not support methods with parameters.
     *
     * @param member Member which can be a Field or Method
     * @return boolean true if it is null or an unsupported type
     */
    public static boolean isNullOrEmpty(Object object, Member member) throws IllegalAccessException, InvocationTargetException {
        if(member instanceof Field){
            Field field = (Field) member;
            Object o = field.get(object);
            return ObjectUtils.isObjectNullOrEmpty(o);
        } else if (member instanceof Method) {
            Method method = (Method) member;
            Object o = method.invoke(object);
            return ObjectUtils.isObjectNullOrEmpty(o);
        } else {
            return true;
        }
    }


//    public static <T> T getFieldValue(){
//
//
//    }
//
//    public static <T> T getMethodValue(){
//
//
//    }
}
