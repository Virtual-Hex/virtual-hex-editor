package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.NativeBool;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@NativeExchange
public abstract class NativeBoolComponentWriter extends NativeAllocComponentWriter {

    public final UUIDMappingFunction UUID_MAPPING_FUNCTION = new UUIDMappingFunction();
    public final StringMappingFunction STRING_MAPPING_FUNCTION = new StringMappingFunction();

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
                .computeIfAbsent(object.id, UUID_MAPPING_FUNCTION)
                .computeIfAbsent(fieldName, STRING_MAPPING_FUNCTION);
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

    private class UUIDMappingFunction implements Function<UUID, Map<String, NativeBool>> {
        @Override
        public Map<String, NativeBool> apply(UUID uuid) {
            return new HashMap<>();
        }
    }

    private class StringMappingFunction implements Function<String, NativeBool> {

        @Override
        public NativeBool apply(String s) {
            return create();
        }
    }
}
