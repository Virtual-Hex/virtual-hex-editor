package com.mr00anderson.jawe.handlers;

import com.mr00anderson.jawe.wrappers.DataFieldMapper;
import com.mr00anderson.jawe.wrappers.NativeBooleanDataFieldMapper;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.mr00anderson.jawe.JImGuiExtension.f;
import static com.mr00anderson.jawe.JImGuiExtension.showHelpMarker;
import static org.ice1000.jimgui.JImGuiGen.nextColumn;

public class DefaultJImGuiBooleanPrimTypeHandler implements JImGuiTypeHandler {

    protected transient Int2ObjectMap<Map<String, DataFieldMapper<NativeBool>>> int2ObjectMap = new Int2ObjectOpenHashMap<>();

    @Override
    public void handle(JImGui imGui, int fieldCount, Field field, int instanceId, Object objectToDraw) {
        String fieldName = field.getName();
        Map<String, DataFieldMapper<NativeBool>> objectMap = int2ObjectMap.computeIfAbsent(instanceId, value -> new HashMap<>(fieldCount, 1.0f));
        DataFieldMapper<NativeBool> nativeBoolWrapperBoolean = objectMap.computeIfAbsent(fieldName, (s) -> new NativeBooleanDataFieldMapper(field, objectToDraw));
        // Set the native field from the app field, allowing the app to update the UI
        nativeBoolWrapperBoolean.setDataFromField();
        if(imGui.checkbox(fieldName, nativeBoolWrapperBoolean.getData())){
            // Was inputted, need to check TODO

        }
        // Set the app field from the native field
        nativeBoolWrapperBoolean.setFieldFromData();
        nextColumn();
        imGui.text("boolean");
        imGui.sameLine();
        showHelpMarker(imGui, f( "Boolean range %s to %s, or Checked and Un-Checked", Boolean.FALSE, Boolean.TRUE));
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
        Map<String, DataFieldMapper<NativeBool>> remove = int2ObjectMap.remove(id);
        remove.forEach((k, v) -> v.dispose());
    }
}
