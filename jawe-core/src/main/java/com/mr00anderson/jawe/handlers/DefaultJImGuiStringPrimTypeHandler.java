package com.mr00anderson.jawe.handlers;

import com.mr00anderson.jawe.wrappers.WrappedTextBuffer;
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
    protected transient Int2ObjectMap<Map<String, WrappedTextBuffer>> int2ObjectMap = new Int2ObjectOpenHashMap<>();

    @Override
    public void handle(JImGui imGui, int fieldCount, Field field, int instanceId, Object objectToDraw) {
        String fieldName = field.getName();
        Map<String, WrappedTextBuffer> objectMap = int2ObjectMap.computeIfAbsent(instanceId, value -> new HashMap<>(fieldCount, 1.0f));
        WrappedTextBuffer wrappedTextBuffer = objectMap.computeIfAbsent(fieldName, (s) -> new WrappedTextBuffer(field, objectToDraw));
        // Set the field from the entity to the native - TODO Caching
        wrappedTextBuffer.setDataFromField();

        if(imGui.inputText(field.getName(), wrappedTextBuffer.getData())){
            // Was inputted, need to check TODO
        }

        wrappedTextBuffer.setFieldFromData();
        nextColumn();
        imGui.text("string");
        imGui.sameLine();
        int bufferLength = wrappedTextBuffer.getBufferEndIndex();
        showHelpMarker(imGui, f( "Range 0 to %,d", wrappedTextBuffer.getData().length - 1));
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
        Map<String, WrappedTextBuffer> remove = int2ObjectMap.remove(id);
        remove.forEach((k, v) -> v.dispose());
    }
}
