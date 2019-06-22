package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.io.UIComponentWriter;
import org.ice1000.jimgui.JImGui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class ByteArrayComponentWriter implements UIComponentWriter<JImGui> {

    protected Map<UUID, Map<String, byte[]>> cachedBytes = new HashMap<>();

    public static int bufferEndIndex(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) return i;
        }
        return 0;
    }

    protected byte[] getCachedBuffer(UUID id, String label, int bufferSize) {
        return cachedBytes.computeIfAbsent(id, value -> new HashMap<>())
                .computeIfAbsent(label, value -> new byte[bufferSize]);
    }

    protected void copyStringIntoBuffer(String textInput, byte[] buffer) {
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
}
