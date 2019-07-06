package com.virtual_hex.editor;

import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.jimgui.DefaultUIWriter;
import com.virtual_hex.editor.jimgui.JImGuiComponentWriter;
import org.ice1000.jimgui.*;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

@ComponentRegister(typeKey = UIComponentsDataStructure.class)
public class UIComponentsDataStructureWriter extends JImGuiComponentWriter {

    public static final int STRING_SIZE_CAP = 512;

    private static final JImStr ROOT = new JImStr("root");
    private static final JImStr BYTE = new JImStr("byte");
    private static final JImStr SHORT = new JImStr("short");
    private static final JImStr INT = new JImStr("int");
    private static final JImStr LONG = new JImStr("long");
    private static final JImStr FLOAT = new JImStr("float");
    private static final JImStr DOUBLE = new JImStr("double");
    private static final JImStr BOOLEAN = new JImStr("boolean");
    private static final JImStr STRING = new JImStr("String");
    private static final JImStr FLOAT_FORMAT = new JImStr("%.3f");
    private static final JImStr DOUBLE_FORMAT = new JImStr("%.6f");


    public final DeallocatableObjectManager manager = new DeallocatableObjectManager();

    private final Map<Class<?>, JImStr> stringCache = new WeakHashMap<>();
    private final Map<Class<?>, WriteHandler<JImGui, DefaultUIWriter>> writeHandling = new WeakHashMap<>();
    private final Map<Field, JImStr> fieldStringCache = new WeakHashMap<>();

    private final Map<UIComponent, Map<Field, NativeInt>> nativeInts = new WeakHashMap<>();
    private final Map<UIComponent, Map<Field, NativeFloat>> nativeFloats = new WeakHashMap<>();
    private final Map<UIComponent, Map<Field, NativeDouble>> nativeDoubles = new WeakHashMap<>();
    private final Map<UIComponent, Map<Field, NativeBool>> nativeBooleans = new WeakHashMap<>();
    private final Map<UIComponent, Map<Field, byte[]>> stringFieldCache = new WeakHashMap<>();
    private final Map<UIComponent, Map<Field, String>> jimStrFieldCache = new WeakHashMap<>();

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        UIComponentsDataStructure component = (UIComponentsDataStructure) uiComponent;
        boolean open = out.treeNode(this.ROOT);
        if(open){
            try {
                write(out, component.uiComponents, writer);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                // TODO
            }
            out.treePop();
        }
    }

    public void write(JImGui out, UIComponent[] uiComponents, DefaultUIWriter writer) throws IllegalAccessException {
        for (int i = 0; i < uiComponents.length; i++) {
            UIComponent uiComponent = uiComponents[i];
                write0(out, uiComponent, writer);
            }
        }



    protected void write0(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) throws IllegalAccessException  {
        Class<? extends UIComponent> aClass = uiComponent.getClass();
//            JImStr jImStr = stringCache.computeIfAbsent(aClass, aClass1 -> );
        // Put a tree together for this widget
        JImStr imStr = new JImStr(aClass.toString() + " " + uiComponent.getId());
        boolean openWidget = out.treeNode(imStr);
        if(openWidget) {
            Field[] declaredFields = aClass.getDeclaredFields();
            for (int j = 0; j < declaredFields.length; j++) {
                Field field = declaredFields[j];
                String fieldName = field.getName();

                Class<?> type = field.getType();

                if (type.isAssignableFrom(byte.class)) {
                    JImStr jimStr = fieldStringCache.computeIfAbsent(field, s -> new JImStr(fieldName));
                    NativeInt value = nativeInts.computeIfAbsent(uiComponent, uiComponent1 -> new HashMap<>()).computeIfAbsent(field, declaredField1 -> getNativeInt());
                    value.modifyValue(field.getInt(uiComponent));
                    boolean valueChanged = out.inputInt(jimStr, value, 1, 1, 0);
                    out.sameLine();
                    out.textWrapped(BYTE);
                    if (valueChanged) {
                        field.setInt(uiComponent, value.accessValue());
                    }
                } else if (type.isAssignableFrom(short.class)) {
                    JImStr jimStr = fieldStringCache.computeIfAbsent(field, s -> new JImStr(fieldName));
                    NativeInt value = nativeInts.computeIfAbsent(uiComponent, uiComponent1 -> new HashMap<>()).computeIfAbsent(field, declaredField1 -> getNativeInt());
                    value.modifyValue(field.getInt(uiComponent));
                    boolean valueChanged = out.inputInt(jimStr, value, 1, 1, 0);
                    out.sameLine();
                    out.textWrapped(SHORT);
                    if (valueChanged) {
                        field.setInt(uiComponent, value.accessValue());
                    }
                } else if (type.isAssignableFrom(int.class)) {
                    JImStr jimStr = fieldStringCache.computeIfAbsent(field, s -> new JImStr(fieldName));
                    NativeInt value = nativeInts.computeIfAbsent(uiComponent, uiComponent1 -> new HashMap<>()).computeIfAbsent(field, declaredField1 -> getNativeInt());
                    value.modifyValue(field.getInt(uiComponent));
                    boolean valueChanged = out.inputInt(jimStr, value, 1, 1, 0);
                    out.sameLine();
                    out.textWrapped(INT);
                    if (valueChanged) {
                        field.setInt(uiComponent, value.accessValue());
                    }
                    // TODO convert into a pop up selectable list?
                    if (fieldName.equals("flags")) {

                    }

                } else if (type.isAssignableFrom(long.class)) {

                    // TODO is a buffer
                } else if (type.isAssignableFrom(float.class)) {
                    JImStr jimStr = fieldStringCache.computeIfAbsent(field, s -> new JImStr(fieldName));
                    NativeFloat value = nativeFloats.computeIfAbsent(uiComponent, uiComponent1 -> new HashMap<>()).computeIfAbsent(field, declaredField1 -> getNativeFloat());
                    value.modifyValue(field.getFloat(uiComponent));
                    boolean valueChanged = out.inputFloat(jimStr, value, 1, 1, FLOAT_FORMAT, 0);
                    out.sameLine();
                    out.textWrapped(FLOAT);
                    if (valueChanged) {
                        field.setFloat(uiComponent, value.accessValue());
                    }
                } else if (type.isAssignableFrom(double.class)) {
                    JImStr jimStr = fieldStringCache.computeIfAbsent(field, s -> new JImStr(fieldName));
                    NativeDouble value = nativeDoubles.computeIfAbsent(uiComponent, uiComponent1 -> new HashMap<>()).computeIfAbsent(field, declaredField1 -> getNativeDouble());
                    value.modifyValue(field.getDouble(uiComponent));
                    boolean valueChanged = out.inputDouble(jimStr, value, 1, 1, DOUBLE_FORMAT, 0);
                    out.sameLine();
                    out.textWrapped(DOUBLE);
                    if (valueChanged) {
                        field.setDouble(uiComponent, value.accessValue());
                    }
                } else if (type.isAssignableFrom(boolean.class)) {
                    JImStr jimStr = fieldStringCache.computeIfAbsent(field, s -> new JImStr(fieldName));
                    NativeBool value = nativeBooleans.computeIfAbsent(uiComponent, uiComponent1 -> new HashMap<>()).computeIfAbsent(field, declaredField1 -> getNativeBoolean());
                    value.modifyValue(field.getBoolean(uiComponent));
                    boolean valueChanged = out.checkbox(jimStr, value);
                    out.sameLine();
                    out.textWrapped(BOOLEAN);
                    if (valueChanged) {
                        field.setBoolean(uiComponent, value.accessValue());
                    }
                } else {
                    Object object = field.get(uiComponent);
                    if (object instanceof String) {
                        String string = (String) object;
                        byte[] buffer = stringFieldCache.computeIfAbsent(uiComponent, uiComponent1 -> new HashMap<>()).computeIfAbsent(field, declaredField1 -> new byte[STRING_SIZE_CAP]);

                        Arrays.fill(buffer, (byte) 0);

                        System.arraycopy(string.getBytes(), 0, buffer, 0, string.length());

                        boolean valueChanged = out.inputText(fieldName, buffer, 0);
                        out.sameLine();
                        out.textWrapped(STRING);
                        if (valueChanged) {
                            String s = new String(buffer, 0, bufferEndIndex(buffer), StandardCharsets.UTF_8);
                            field.set(uiComponent, s);
                        }
                    } else if (object instanceof JImStr) {
                        JImStr jImStr = (JImStr) object;
                        try {
                            // TODO This is TEMPORARY waiting for PR #39
                            Field bytesField = jImStr.getClass().getDeclaredField("bytes");
                            bytesField.setAccessible(true);
                            // Get the JIMSTR Bytes
                            byte[] bytes = (byte[]) bytesField.get(jImStr);

                            // Lets get the cached buffer object and then fill and copy the data
                            byte[] buffer = stringFieldCache.computeIfAbsent(uiComponent, uiComponent1 -> new HashMap<>()).computeIfAbsent(field, declaredField1 -> new byte[STRING_SIZE_CAP]);
                            Arrays.fill(buffer, (byte) 0);
                            System.arraycopy(bytes, 0, buffer, 0, bytes.length);

                            boolean valueChanged = out.inputText(fieldName, buffer, 0);
                            out.sameLine();
                            out.textWrapped(STRING);
                            if (valueChanged) {
                                // Lets make a new JImStr
                                JImStr s = new JImStr(new String(buffer, 0, bufferEndIndex(buffer), StandardCharsets.UTF_8));
                                field.set(uiComponent, s);
                            }
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                    } else if (object instanceof UIComponent) {
                        write0(out, (UIComponent) object, writer);
                    } else if (object instanceof UIComponent[]) {
                        write(out, (UIComponent[]) object, writer);
                    }
                }
            }
            out.treePop();
        }
    }

    private NativeInt getNativeInt() {
        NativeInt nativeInt = new NativeInt();
        manager.add(nativeInt);
        return nativeInt;
    }

    private NativeFloat getNativeFloat() {
        NativeFloat nativeInt = new NativeFloat();
        manager.add(nativeInt);
        return nativeInt;
    }


    private NativeDouble getNativeDouble() {
        NativeDouble nativeInt = new NativeDouble();
        manager.add(nativeInt);
        return nativeInt;
    }

    private NativeBool getNativeBoolean() {
        NativeBool nativeInt = new NativeBool();
        manager.add(nativeInt);
        return nativeInt;
    }

    public static int bufferEndIndex(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) return i;
        }
        return 0;
    }

    public void copyStringIntoBuffer(String textInput, byte[] buffer) {
        Arrays.fill(buffer, (byte) 0);
        System.arraycopy(textInput.getBytes(), 0, buffer, 0, textInput.length());
    }

    public int getBufferEndIndex(byte[] buffer) {
        return bufferEndIndex(buffer);
    }
    @Override
    public void dispose() {
        super.dispose();
        manager.deallocateAll();
    }

    static {
//        writeHandling.put(byte.class, (out, uiComponent, writer) -> out.dragIntRange2());
    }
}