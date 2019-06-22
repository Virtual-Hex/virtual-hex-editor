package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.NativeBool;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NativeExchange
public abstract class NativeBoolComponentWriter extends NativeAllocComponentWriter {

    /**
     * These get cached first cycle through
     */
    protected Map<UUID, Map<String, NativeBool>> cachedBools = new HashMap<>();

    @Override
    public void dispose() {
        super.dispose();
        cachedBools.clear();
    }

    protected NativeBool getNative(String fieldName, UIComponent object) {
        return cachedBools
                .computeIfAbsent(object.id, value -> new HashMap<>())
                .computeIfAbsent(fieldName, value -> create());
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     *
     * @return
     */
    protected NativeBool create() {
        NativeBool nativeValue = new NativeBool();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }
}
