package com.mr00anderson.jawe;

import com.artemis.World;
import com.mr00anderson.jawe.drawables.*;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import org.ice1000.jimgui.*;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;
import org.jetbrains.annotations.NotNull;

public class JaweJImGui extends JImGui {


    public static final JaweDrawable EMPTY_DRAWABLE = new EmptyJaweDrawable();

    /**
     *  separator, generally horizontal. inside a menu bar or in horizontal layout mode, this becomes a vertical separator.
     */
    public static final JaweDrawable SEPARATOR = new JaweSeparator();

    /**
     * next column, defaults to current row or next row if the current row is finished
     */
    public static final JaweDrawable NEXT_COLUMN = new JaweNextColumn();

    /**
     * undo a SameLine() or force a new line when in an horizontal-layout context.
     */
    public static final JaweDrawable NEW_LINE = new JaweNewLine();

    /**
     * add vertical spacing
     */
    public static final JaweDrawable JAWE_SPACING = new JaweSpacing();


    public static final ActivationHandler<?> EMPTY_ACTIVATION_HANDLER = imGuiDrawable -> {};

    protected DeallocatableObjectManager deallocatableObjectManager = new DeallocatableObjectManager();

    public static final JaweJimGuiStaticDeallocateManager STATIC_NATIVE_MANAGEMENT = new JaweJimGuiStaticDeallocateManager();

    public World world;

    public JaweJImGui(World world) {
        this.world = world;
    }

    public JaweJImGui(int width, int height, @NotNull String title, World world) {
        super(width, height, title);
        this.world = world;
    }

    public JaweJImGui(int width, int height, World world) {
        super(width, height);
        this.world = world;
    }

    public JaweJImGui(int width, int height, @NotNull JImFontAtlas fontAtlas, @NotNull String title, World world) {
        super(width, height, fontAtlas, title);
        this.world = world;
    }

    public JaweJImGui(int width, int height, @NotNull JImFontAtlas fontAtlas, @NotNull String title, long anotherWindow, World world) {
        super(width, height, fontAtlas, title, anotherWindow);
        this.world = world;
    }

    public JaweJImGui() {
    }

    public JaweJImGui(int width, int height, @NotNull String title) {
        super(width, height, title);
    }

    public JaweJImGui(int width, int height) {
        super(width, height);
    }

    public JaweJImGui(int width, int height, @NotNull JImFontAtlas fontAtlas, @NotNull String title) {
        super(width, height, fontAtlas, title);
    }

    public JaweJImGui(int width, int height, @NotNull JImFontAtlas fontAtlas, @NotNull String title, long anotherWindow) {
        super(width, height, fontAtlas, title, anotherWindow);
    }

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
