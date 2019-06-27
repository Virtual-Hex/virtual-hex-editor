package com.virtual_hex.editor.jimgui;


import com.virtual_hex.editor.UIComponentWriter;
import org.ice1000.jimgui.JImGui;

import java.util.*;
import java.util.function.Function;

public abstract class ByteArrayComponentWriter implements UIComponentWriter<JImGui, DefaultUIWriter> {

    public final UUIDMappingFunction UUID_MAPPING_FUNCTION = new UUIDMappingFunction();

    protected Map<UUID, Map<String, byte[]>> cachedBytes = new WeakHashMap<>();

    public static int bufferEndIndex(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) return i;
        }
        return 0;
    }

    public byte[] getCachedBufferCopyTo(UUID id, String label, String textInput, int bufferSize) {
        Map<String, byte[]> map = cachedBytes.computeIfAbsent(id, UUID_MAPPING_FUNCTION);
        byte[] buffer = map.get(label);
        if(buffer == null){
            buffer = new byte[bufferSize];
            map.put(label, buffer);
        }
        Arrays.fill(buffer, (byte) 0);
        System.arraycopy(textInput.getBytes(), 0, buffer, 0, textInput.length());
        return buffer;
    }

    public byte[] getCachedBuffer(UUID id, String label, int bufferSize) {
        Map<String, byte[]> map = cachedBytes.computeIfAbsent(id, UUID_MAPPING_FUNCTION);
        byte[] buffer = map.get(label);
        if(buffer == null){
            buffer = new byte[bufferSize];
            map.put(label, buffer);
        }
        return buffer;
    }

    public void copyStringIntoBuffer(String textInput, byte[] buffer) {
        Arrays.fill(buffer, (byte) 0);
        System.arraycopy(textInput.getBytes(), 0, buffer, 0, textInput.length());
    }

    public int getBufferEndIndex(byte[] buffer) {
        return bufferEndIndex(buffer);
    }

    @Override
    public void dispose() {
        cachedBytes.clear();
    }

    private class UUIDMappingFunction implements Function<UUID, Map<String,byte[]>> {
        @Override
        public Map<String, byte[]> apply(UUID uuid) {
            return new HashMap<>();
        }
    }
}
