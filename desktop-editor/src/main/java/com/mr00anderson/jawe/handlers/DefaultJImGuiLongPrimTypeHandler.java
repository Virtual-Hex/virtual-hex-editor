package com.mr00anderson.jawe.handlers;

import com.mr00anderson.jawe.wrappers.DataFieldMapper;
import com.mr00anderson.jawe.wrappers.WrappedLongTextBuffer;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.ice1000.jimgui.JImGui;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.mr00anderson.jawe.JaweJImGui.showHelpMarker;
import static org.ice1000.jimgui.JImGuiGen.nextColumn;

public class DefaultJImGuiLongPrimTypeHandler implements JImGuiTypeHandler {

    // Cache
    protected transient Int2ObjectMap<Map<String, DataFieldMapper<byte[]>>> int2ObjectMap = new Int2ObjectOpenHashMap<>();

    @Override
    public void handle(JImGui imGui, int fieldCount, Field field, int instanceId, Object objectToDraw) {
        String fieldName = field.getName();
        Map<String, DataFieldMapper<byte[]>> objectMap = int2ObjectMap.computeIfAbsent(instanceId, value -> new HashMap<>(fieldCount, 1.0f));
        DataFieldMapper<byte[]> nativeIntWrapperLong = objectMap.computeIfAbsent(fieldName, (s) -> new WrappedLongTextBuffer(field, objectToDraw));
        // Set the native field from the app field, allowing the app to update the UI
        nativeIntWrapperLong.setFieldFromNative();

        if(imGui.inputText(field.getName(), nativeIntWrapperLong.getNativeData())){
            // Was inputted, need to check TODO
            System.out.println("Text input received");
            // TODO Plus buttons
        }
        // Set the a
        // pp field from the native field
        nativeIntWrapperLong.setNativeFromField();
        nextColumn();
        imGui.text("long");
        imGui.sameLine();
        showHelpMarker(imGui, f( "Signed range %,d to %,d", Long.MIN_VALUE, Long.MAX_VALUE));
        nextColumn();
        imGui.text("8");
        nextColumn();
    }

    private String f(String s, long minValue, long maxValue) {
        return null;
    }

    @Override
    public void dispose() {
        int2ObjectMap.forEach((k, v) -> v.forEach((k2, v2) -> v2.dispose()));
        int2ObjectMap.clear();
    }

    @Override
    public void purge(int id) {
        Map<String, DataFieldMapper<byte[]>> remove = int2ObjectMap.remove(id);
        remove.forEach((k, v) -> v.dispose());
    }
}