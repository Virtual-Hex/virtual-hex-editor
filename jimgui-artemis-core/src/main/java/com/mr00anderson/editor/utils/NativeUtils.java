package com.mr00anderson.editor.utils;

import org.ice1000.jimgui.*;

public class NativeUtils {

    public static NativeBool createBool(boolean value){
        NativeBool nativeValue = new NativeBool();
        nativeValue.modifyValue(value);
        return nativeValue;
    }

    public static NativeShort createShort(short value){
        NativeShort nativeValue = new NativeShort();
        nativeValue.modifyValue(value);
        return nativeValue;
    }
    public static NativeInt createInt(int value){
        NativeInt nativeValue = new NativeInt();
        nativeValue.modifyValue(value);
        return nativeValue;
    }

    public static NativeLong createLong(long value){
        NativeLong nativeValue = new NativeLong();
        nativeValue.modifyValue(value);
        return nativeValue;
    }

    public static NativeFloat createFloat(float value){
        NativeFloat nativeValue = new NativeFloat();
        nativeValue.modifyValue(value);
        return nativeValue;
    }

    public static NativeDouble createDouble(double value){
        NativeDouble nativeValue = new NativeDouble();
        nativeValue.modifyValue(value);
        return nativeValue;
    }

}
