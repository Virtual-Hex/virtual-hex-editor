package com.virtual_hex.editor.data;

import java.nio.charset.StandardCharsets;

public class InputText extends StringLabel {

    public int flags;
    public byte[] buffer;
    public int bufferSize;

    public InputText() {
    }

    public InputText(String label) {
        super(label);
    }

    public InputText(String label, int bufferSize) {
        super(label);
        this.buffer = new byte[bufferSize];
    }

    public InputText(String label, String buffer) {
        super(label);
        this.buffer = buffer.getBytes(StandardCharsets.UTF_8);
    }

    public InputText(String label, int flags, byte[] buffer) {
        super(label);
        this.flags = flags;
        this.buffer = buffer;
    }
}
