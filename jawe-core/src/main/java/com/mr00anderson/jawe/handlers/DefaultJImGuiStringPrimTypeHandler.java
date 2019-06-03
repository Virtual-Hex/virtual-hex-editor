package com.mr00anderson.jawe.handlers;

import com.mr00anderson.jawe.wrappers.StringDataFieldMapper;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.ice1000.jimgui.JImGui;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.mr00anderson.jawe.JaweJImGui.f;
import static com.mr00anderson.jawe.JaweJImGui.showHelpMarker;
import static org.ice1000.jimgui.JImGuiGen.nextColumn;

public class DefaultJImGuiStringPrimTypeHandler implements JImGuiTypeHandler {

    // Cache
    protected transient Int2ObjectMap<Map<String, StringDataFieldMapper>> int2ObjectMap = new Int2ObjectOpenHashMap<>();

    @Override
    public void handle(JImGui imGui, int fieldCount, Field field, int instanceId, Object objectToDraw) {
        String fieldName = field.getName();
        Map<String, StringDataFieldMapper> objectMap = int2ObjectMap.computeIfAbsent(instanceId, value -> new HashMap<>(fieldCount, 1.0f));
        StringDataFieldMapper stringDataFieldMapper = objectMap.computeIfAbsent(fieldName, (s) -> new StringDataFieldMapper(field, objectToDraw));
        // Set the field from the entity to the native - TODO Caching
        stringDataFieldMapper.setFieldFromNative();

        if(imGui.inputText(field.getName(), stringDataFieldMapper.getNativeData())){
            // Was inputted, need to check TODO
        }

        stringDataFieldMapper.setNativeFromField();
        nextColumn();
        imGui.text("path");
        imGui.sameLine();
        int bufferLength = stringDataFieldMapper.getBufferEndIndex();
        showHelpMarker(imGui, f( "Range 0 to %,d", stringDataFieldMapper.getNativeData().length - 1));
        nextColumn();
        imGui.text(String.valueOf(bufferLength));
        nextColumn();
    }

    @Override
    public void dispose() {
        int2ObjectMap.forEach((k, v) -> v.forEach((k2, v2) -> v2.dispose()));
        int2ObjectMap.clear();
    }

    @Override
    public void purge(int id) {
        Map<String, StringDataFieldMapper> remove = int2ObjectMap.remove(id);
        remove.forEach((k, v) -> v.dispose());
    }
}
