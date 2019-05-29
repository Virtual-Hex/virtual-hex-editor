package com.mr00anderson.jawe;

import com.mr00anderson.jawe.drawables.JaweDrawable;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import org.ice1000.jimgui.*;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;

public class JaweJImGui extends JImGui {


    public static final JaweDrawable EMPTY_DRAWABLE = (imGui, world) -> {};
    public static final JaweDrawable SEPARATOR = (imGui, world) -> JImGui.separator();

    public static final ActivationHandler<?> EMPTY_ACTIVATION_HANDLER = imGuiDrawable -> {};

    protected DeallocatableObjectManager deallocatableObjectManager = new DeallocatableObjectManager();

    public static final JaweJimGuiStaticDeallocateManager STATIC_NATIVE_MANAGEMENT = new JaweJimGuiStaticDeallocateManager();

    public static void showHelpMarker(JImGui imGui, String description) {
        imGui.textDisabled("(?)");
        if (imGui.isItemHovered()) {
            imGui.beginTooltip();
            imGui.pushTextWrapPos(imGui.getFontSize() * 35.0f);
            imGui.text(description);
            imGui.popTextWrapPos();
            imGui.endTooltip();
        }
    }

    public static String f(String format, Object... objects) {
        return String.format(format, objects);
    }

    public void deallocateNativeObject0() {
        super.deallocateNativeObject();
        deallocatableObjectManager.close();
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @param value
     * @return
     */
    public NativeBool createBool(boolean value){
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
    public NativeShort createShort(short value){
        NativeShort nativeValue = new NativeShort();
        nativeValue.modifyValue(value);
        return nativeValue;
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @param value
     * @return
     */
    public NativeInt createInt(int value){
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
    public NativeLong createLong(long value){
        NativeLong nativeValue = new NativeLong();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @param value
     * @return
     */
    public NativeFloat createFloat(float value){
        NativeFloat nativeValue = new NativeFloat();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @param value
     * @return
     */
    public NativeDouble createDouble(double value){
        NativeDouble nativeValue = new NativeDouble();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }


}
