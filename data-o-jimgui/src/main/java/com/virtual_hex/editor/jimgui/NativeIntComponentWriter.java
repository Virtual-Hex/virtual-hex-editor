package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.NativeInt;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NativeExchange
public abstract class NativeIntComponentWriter extends NativeAllocComponentWriter {

    /**
     * These get cached first cycle through
     */
    protected transient Map<UUID, Map<String, NativeInt>> cachedInts = new HashMap<>();

    @Override
    public void dispose() {
        super.dispose();
        cachedInts.clear();
    }

    protected NativeInt getNative(String fieldName, UIComponent object) {
        return cachedInts
                .computeIfAbsent(object.id, value -> new HashMap<>())
                .computeIfAbsent(fieldName, aClass -> create());
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     *
     * @return
     */
    public NativeInt create() {
        NativeInt nativeValue = new NativeInt();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }
}
