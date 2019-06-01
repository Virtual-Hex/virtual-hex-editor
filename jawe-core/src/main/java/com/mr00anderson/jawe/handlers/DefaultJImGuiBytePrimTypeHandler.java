package com.mr00anderson.jawe.handlers;

import com.mr00anderson.jawe.wrappers.DataFieldMapper;
import com.mr00anderson.jawe.wrappers.NativeByteDataFieldMapper;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeInt;
import org.ice1000.jimgui.flag.JImInputTextFlags;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.mr00anderson.jawe.JaweJImGui.f;
import static com.mr00anderson.jawe.JaweJImGui.showHelpMarker;
import static org.ice1000.jimgui.JImGuiGen.nextColumn;

public class DefaultJImGuiBytePrimTypeHandler implements JImGuiTypeHandler {

    // Cache
    protected transient Int2ObjectMap<Map<String, DataFieldMapper<NativeInt>>> int2ObjectMap = new Int2ObjectOpenHashMap<>();

    @Override
    public void handle(JImGui imGui, int fieldCount, Field field, int instanceId, Object objectToDraw) {
        String fieldName = field.getName();
        Map<String, DataFieldMapper<NativeInt>> objectMap = int2ObjectMap.computeIfAbsent(instanceId, value -> new HashMap<>(fieldCount, 1.0f));
        DataFieldMapper<NativeInt> nativeIntWrapperByte = objectMap.computeIfAbsent(fieldName, (s) -> new NativeByteDataFieldMapper(field, objectToDraw));
        // Set the native field from the app field, allowing the app to update the UI
        nativeIntWrapperByte.setFieldFromNative();
        if(imGui.inputInt(field.getName(), nativeIntWrapperByte.getNativeData(), 1, 1, JImInputTextFlags.CharsDecimal & JImInputTextFlags.CallbackHistory)){
            // Was inputted, need to check TODO
            System.out.println("Text input received");

        }
        // Set the app field from the native field
        nativeIntWrapperByte.setNativeFromField();
        nextColumn();
        imGui.text("byte");
        imGui.sameLine();
        showHelpMarker(imGui, f( "Signed range %,d to %,d", Byte.MIN_VALUE, Byte.MAX_VALUE));
        nextColumn();
        imGui.text("1");
        nextColumn();
    }

    @Override
    public void dispose() {
        int2ObjectMap.forEach((k, v) -> v.forEach((k2, v2) -> v2.dispose()));
        int2ObjectMap.clear();
    }

    @Override
    public void purge(int id) {
        Map<String, DataFieldMapper<NativeInt>> remove = int2ObjectMap.remove(id);
        remove.forEach((k, v) -> v.dispose());
    }
}
