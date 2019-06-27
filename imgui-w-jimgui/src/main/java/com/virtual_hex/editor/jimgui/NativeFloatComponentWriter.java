package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.NativeFloat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NativeExchange
public abstract class NativeFloatComponentWriter extends NativeAllocComponentWriter {

    /**
     * These get cached first cycle through
     */
    protected transient Map<UUID, Map<String, NativeFloat>> cachedFloats = new HashMap<>();

    @Override
    public void dispose() {
        super.dispose();
        cachedFloats.clear();
    }

    protected NativeFloat getNative(String fieldName, UIComponent object) {
        return cachedFloats
                .computeIfAbsent(object.getId(), value -> new HashMap<>())
                .computeIfAbsent(fieldName, aClass -> create());
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     *
     * @return
     */
    public NativeFloat create() {
        NativeFloat nativeValue = new NativeFloat();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }
}
