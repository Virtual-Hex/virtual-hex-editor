package com.mr00anderson.jawe;

import org.ice1000.jimgui.*;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;

public class JaweStaticDeallocateManager {


    protected static final DeallocatableObjectManager deallocatableObjectManager = new DeallocatableObjectManager();

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @param value
     * @return
     */
    public static NativeBool createBool(boolean value){
        NativeBool nativeValue = new NativeBool();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @param value
     * @return
     */
    public static NativeShort createShort(short value){
        NativeShort nativeValue = new NativeShort();
        nativeValue.modifyValue(value);
        return nativeValue;
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @param value
     * @return
     */
    public static NativeInt createInt(int value){
        NativeInt nativeValue = new NativeInt();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @param value
     * @return
     */
    public static NativeLong createLong(long value){
        NativeLong nativeValue = new NativeLong();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Will be deallocated after this deallocateNativeObject0() is called
     * @param value
     * @return
     */
    public static NativeFloat createFloat(float value){
        NativeFloat nativeValue = new NativeFloat();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Will be deallocated after this deallocateNativeObject0() is called
     * @param value
     * @return
     */
    public static NativeDouble createDouble(double value){
        NativeDouble nativeValue = new NativeDouble();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Will be deallocated after this deallocateNativeObject0() is called
     * @return
     */
    public static JImVec4 createJImVec4(float x, float y, float z, float w){
        JImVec4 jImVec4 = new JImVec4(x, y, z, w);
        deallocatableObjectManager.add(jImVec4);
        return jImVec4;
    }

    public static void deallocateNativeObject0() {
        deallocatableObjectManager.close();
    }
}
