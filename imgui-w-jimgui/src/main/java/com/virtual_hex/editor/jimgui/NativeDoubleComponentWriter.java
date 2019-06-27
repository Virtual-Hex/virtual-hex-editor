package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.NativeDouble;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NativeExchange
public abstract class NativeDoubleComponentWriter extends NativeAllocComponentWriter {

    /**
     * These get cached first cycle through
     */
    protected transient Map<UUID, Map<String, NativeDouble>> cachedDoubles = new HashMap<>();

    @Override
    public void dispose() {
        super.dispose();
        cachedDoubles.clear();
    }

    protected NativeDouble getNative(String fieldName, UIComponent object) {
        return cachedDoubles
                .computeIfAbsent(object.getId(), value -> new HashMap<>())
                .computeIfAbsent(fieldName, aClass -> create());
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     *
     * @return
     */
    public NativeDouble create() {
        NativeDouble nativeValue = new NativeDouble();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }
}
